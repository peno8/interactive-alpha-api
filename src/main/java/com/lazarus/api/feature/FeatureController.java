package com.lazarus.api.feature;

import com.lazarus.api.rank.Percentile;
import com.lazarus.api.rank.PercentileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RequestMapping("/api/feature/")
@RequiredArgsConstructor
@RestController
public class FeatureController {

    private final Log log = LogFactory.getLog(getClass());

    private final FeatureService featureService;

    private final PercentileService percentileService;

    @CrossOrigin(origins = "*")
    @GetMapping("/{symbol}")
    Mono<Feature> findById(@PathVariable("symbol") String symbol) {
        System.out.println(symbol);
        return featureService.findById(symbol);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/aggregate")
    Mono<FeatureResponse> getFeature(@RequestBody FeatureRequest request) {
        log.info(request.toString());
        Mono<Feature> feature = featureService.findById(request.getSymbol());

        Mono<FeatureResponse> result = feature.map(m -> {
            Optional<Percentile> stockPercentile =
                    percentileService.getPercentile(request.getCq(), request.getSector(), request.getSymbol());
            Optional<Percentile> sectorPercentile =
                    percentileService.getPercentile(request.getCq(), request.getSector(), request.getSector());
            FeatureResponse response =
                    new FeatureResponse("OK", m.getJson(), stockPercentile, sectorPercentile);
            return response;
        }).defaultIfEmpty(new FeatureResponse("EMPTY", null, null, null));

        return result;
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
