package com.lazarus.api.rank;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RankCalculationResult implements Comparable {
    public Percentile percentile;
    public Double score;

    @Override
    public int compareTo(Object o) {
        return this.score.compareTo(((RankCalculationResult) o).score);
    }
}
