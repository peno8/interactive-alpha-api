package com.lazarus.api.feature;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository repository;

    public Mono<Feature> findById(String id) {
        return repository.findById(id);
    }
}
