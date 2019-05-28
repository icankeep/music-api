package com.passer.api.dao;

import com.passer.api.model.MatchRecord;

public interface MatchRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(MatchRecord record);

    int insertSelective(MatchRecord record);

    MatchRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MatchRecord record);

    int updateByPrimaryKey(MatchRecord record);
}