package com.passer.api.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: passer
 * @Date: 19-5-27 下午7:15
 * @Version 1.0
 */
@Data
public class Song {
    @JSONField(name="id")
    private String songId;

    @JSONField(name="name")
    private String songName;

    private Artist artist;
}
