package com.passer.api.model;

import lombok.Data;

/**
 * @Author: passer
 * @Date: 19-5-27 下午10:00
 * @Version 1.0
 */
@Data
public class Match implements Comparable<Double>{
    private String userId;
    private User user;
    private Double weight;

    @Override
    public int compareTo(Double o) {
        return o.compareTo(weight);
    }

    public Match(String userId, Double weight) {
        this.userId = userId;
        this.weight = weight;
    }
}
