package com.lazarus.api.rank;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class PercentileService {

    private final Log log = LogFactory.getLog(getClass());

    @Value("${DATA_ROOT}")
    private String dataRoot;

    @Value("${CURRENT_CQ}")
    private String currentCq;

    @Value("${PREV_CQ}")
    private String prevCq;

    @Resource
    private ScreenerDefLoader loader;

    private Map<String, Map<String, List<Percentile>>> percentiles = new HashMap<>();

    public Optional<Percentile> getPercentile(String cq, String sector, String symbol) {
        Map<String, List<Percentile>> percentilesByQuarter = percentiles.get(cq);
        List<Percentile> bySectorOrExchange = percentilesByQuarter.get(sector);
        return bySectorOrExchange.stream().filter(e -> e.getSymbol().equals(symbol)).findFirst();
    }

    public List<RankCalculationResult> getRanks(RankCalculationRequest request, Integer limit) {
        Map<String, List<Percentile>> percentilesByQuarter = percentiles.get(request.getCq());
        List<Percentile> bySectorOrExchange;

        System.out.println(percentilesByQuarter.keySet());

//        if(request.getSector().isPresent()) {
//            bySectorOrExchange = percentilesByQuarter.get(request.getSector().get());
//        } else if(request.getExchange().isPresent()) {
//            bySectorOrExchange = percentilesByQuarter.get(request.getExchange().get());
//        } else {
//            throw new IllegalArgumentException("Wrong Screener Param: " + request);
//        }

        bySectorOrExchange = percentilesByQuarter.get(request.getKey());

        List<RankCalculationResult> result =  bySectorOrExchange.stream()
                .filter(e -> request.getFeatures().stream().allMatch(f -> e.getPercentiles().containsKey(f.getFeature())))
                .map(e -> {
                        double totalPercentile = request.getFeatures().stream().mapToDouble(f ->
                        {
                            Double d = e.getPercentiles().get(f.getFeature()).getP();
                            if(f.isLowerIsBetter()) return d;
                            else return 1 - d;
                        }).sum(); // Later: request.getFeatures().size();
                        return new RankCalculationResult(e, totalPercentile / request.getFeatures().size());
                })
                .sorted()
                .limit(limit)
                .toList();
//                .subList(0, limit);
        return result;
    }

    @PostConstruct
    public void initialize() {
        addByQuarter(currentCq);
    }

    public void addByQuarter(String cq) {

        try {
            List<ScreenerDef> defs = loader.load();
            Map<String, List<Percentile>> map = new HashMap<>();
            for(ScreenerDef def : defs) {
                String path = dataRoot + String.format("/%s/percentiles_%s_pg.tsv", cq, def.key.replace("|", "_"));
                List<String> lines = Files.readAllLines(
                    Paths.get(path),
                    StandardCharsets.UTF_8);
                List<Percentile> percentiles = lines.stream()
                    .map(l -> l.split("\t"))
                    .map(arr -> JSON.parseObject(arr[2], Percentile.class)).toList();
                map.put(def.key, percentiles);
            }
            percentiles.put(cq, map);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
