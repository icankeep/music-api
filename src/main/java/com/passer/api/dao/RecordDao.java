package com.passer.api.dao;

import com.passer.api.model.Record;

import java.util.List;

public interface RecordDao {
    List<Record> selectByUserId(String uid);

    List<String> selectAllUserIdExcludeOne(String uid);

    int deleteByUserId(String uid);

    int insert(Record record);

    int insertByRecordList(List<Record> list);
}
