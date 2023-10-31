package com.lazarus.api.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FeatureVariation {
    private String feature;
    private boolean lowerIsBetter;
}
