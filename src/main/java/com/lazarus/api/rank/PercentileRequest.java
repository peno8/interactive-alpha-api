package com.lazarus.api.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
@ToString
public class PercentileRequest {

    private String cq;
    private String sector;
    private String symbol;
}
