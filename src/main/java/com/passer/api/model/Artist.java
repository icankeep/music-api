package com.passer.api.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: passer
 * @Date: 19-5-27 下午7:15
 * @Version 1.0
 */
@Data
public class Artist {

    @JSONField(name="id")
    private String artistId;

    @JSONField(name="name")
    private String artistName;
}
