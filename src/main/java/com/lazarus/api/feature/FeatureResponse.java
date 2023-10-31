package com.lazarus.api.feature;

import com.lazarus.api.rank.Percentile;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@ToString
@AllArgsConstructor
public class FeatureResponse {
    public String status;
    public String featureString;
    public Optional<Percentile> stockPercentile;
    public Optional<Percentile> sectorPercentile;
}
