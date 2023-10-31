package com.lazarus.api.rank;

import lombok.ToString;

import java.util.Optional;

@ToString
public class ScreenerDef {
    public String key;
//    public Optional<String> naics;
//    public Optional<String> exchange;
    public String keyType;
    public String value;
    public String desc;
}
