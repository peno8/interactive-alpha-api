package com.lazarus.api.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FeatureRequest {

    private String cq;
    private String sector;
    private String symbol;
}
