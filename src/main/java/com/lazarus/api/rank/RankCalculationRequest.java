package com.lazarus.api.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
@ToString
public class RankCalculationRequest {

//    @Getter
//    @AllArgsConstructor
//    public class FeatureVariation {
//        private boolean lowerIsBetter;
//    }

    private String cq;
    private String key;
//    private Optional<String> exchange;
//    private Optional<String> sector;
//    private List<String> features;
    private List<FeatureVariation> features;
}
