package com.passer.api.dao;

import com.passer.api.model.DiscussEvent;

import java.util.List;

public interface DiscussEventDao {
    int deleteById(Long id);

    int insert(DiscussEvent record);

    DiscussEvent selectById(Long id);

    List<DiscussEvent> selectAll();
}