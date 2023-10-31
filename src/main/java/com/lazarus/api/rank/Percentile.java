package com.lazarus.api.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Percentile {

    @Getter
    @AllArgsConstructor
    public class Value {
//        private String f;
        private Double v;
        private Double p;
    }

    private String symbol;
    private String cq;
    private String end;
    private Map<String, Value> percentiles;
//    private List<Value> percentiles;
}
