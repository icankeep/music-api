package com.passer.api.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: passer
 * @Date: 19-5-27 下午5:30
 * @Version 1.0
 */
@Data
public class RecordData {
    private List<Record> allData;
    private List<Record> weekData;
}

