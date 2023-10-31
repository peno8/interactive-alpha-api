package com.lazarus.api.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@AllArgsConstructor
@Table(name = "financial.features")
public class Feature {
    @Id
    private String symbol;

    private String json;
}
