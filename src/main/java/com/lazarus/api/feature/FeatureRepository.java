package com.lazarus.api.feature;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends ReactiveCrudRepository<Feature, String> {

}
