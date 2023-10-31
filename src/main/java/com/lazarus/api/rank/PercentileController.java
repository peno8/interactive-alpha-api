package com.lazarus.api.rank;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/percentile/")
@RequiredArgsConstructor
@RestController
public class PercentileController {

    private final Log log = LogFactory.getLog(getClass());

    private final PercentileService percentileService;


    @CrossOrigin(origins = "*")
    @PostMapping("/ranks")
    @ResponseStatus(HttpStatus.OK)
    Flux<RankCalculationResult> getRanks(@RequestBody RankCalculationRequest request) {
        log.debug(request.toString());

        List<RankCalculationResult> response = percentileService.getRanks(request,100);
        log.debug(response.size());
        return Flux.fromIterable(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/percentile")
    @ResponseStatus(HttpStatus.OK)
    Mono<Optional<Percentile>> getPercentile(@RequestBody PercentileRequest request) {
        log.debug(request.toString());

        Optional<Percentile> response = percentileService.getPercentile(request.getCq(), request.getSector(), request.getSymbol());

        return Mono.just(response);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
