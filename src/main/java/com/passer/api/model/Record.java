package com.passer.api.model;

import lombok.Data;

/**
 * @Author: passer
 * @Date: 19-5-27 下午7:15
 * @Version 1.0
 */
@Data
public class Record {
    private Long id;
    private String userId;
    private int playCount;
    private int score;
    private SongData song;
}
