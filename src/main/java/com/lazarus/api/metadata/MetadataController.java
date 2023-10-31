package com.lazarus.api.metadata;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/metadata/")
@RequiredArgsConstructor
@RestController
public class MetadataController {

    private final Log log = LogFactory.getLog(getClass());

    private final MetadataService metadataService;

    @CrossOrigin(origins = "*")
    @GetMapping("/get")
    Mono<Metadata> getMetadata() {
        return Mono.just(metadataService.getMetadata());
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
