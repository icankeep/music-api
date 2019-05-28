package com.passer.api.dao;

import com.passer.api.model.ChatList;

public interface ChatListDao {
    int deleteByPrimaryKey(Long id);

    int insert(ChatList record);

    int insertSelective(ChatList record);

    ChatList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChatList record);

    int updateByPrimaryKey(ChatList record);
}