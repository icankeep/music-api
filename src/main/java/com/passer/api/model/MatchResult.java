package com.passer.api.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: passer
 * @Date: 19-5-27 下午10:32
 * @Version 1.0
 */
@Data
public class MatchResult {
    private List<Match> allMatchData;

    public MatchResult(List<Match> allMatchData) {
        this.allMatchData = allMatchData;
    }
}
