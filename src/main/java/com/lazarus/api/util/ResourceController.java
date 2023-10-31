package com.lazarus.api.util;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RequestMapping("/api/resource/")
@RequiredArgsConstructor
@RestController
public class ResourceController {

    private final Log log = LogFactory.getLog(getClass());

    private final ResourceService service;


    @PostMapping("/feature-def/{filename}")
    @ResponseStatus(HttpStatus.OK)
    Mono<String> getFeatureDef(@PathVariable("filename") String filename) throws IOException {
        String data = service.load(filename);
        return Mono.just(data);
    }
}
