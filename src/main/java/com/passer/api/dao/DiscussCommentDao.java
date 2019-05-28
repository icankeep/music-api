package com.passer.api.dao;

import com.passer.api.model.DiscussComment;

import java.util.List;

public interface DiscussCommentDao {
    int deleteById(Long id);

    int insert(DiscussComment record);

    DiscussComment selectById(Long id);

    List<DiscussComment> selectByDiscussEventId(Long id);
}