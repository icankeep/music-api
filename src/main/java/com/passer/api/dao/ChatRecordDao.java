package com.passer.api.dao;

import com.passer.api.model.ChatRecord;

public interface ChatRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ChatRecord record);

    int insertSelective(ChatRecord record);

    ChatRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChatRecord record);

    int updateByPrimaryKey(ChatRecord record);
}