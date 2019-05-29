package com.passer.api.model;

import lombok.Data;

/**
 * @Author: passer
 * @Date: 19-5-27 下午10:00
 * @Version 1.0
 */
@Data
public class Match implements Comparable<Match>{
    private String userId;
    private User user;
    private Double weight;

    @Override
    public int compareTo(Match match) {
        return Double.compare(match.weight, weight);
    }

    public Match(String userId, Double weight) {
        this.userId = userId;
        this.weight = weight;
    }
}
