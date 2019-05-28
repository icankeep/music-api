package com.passer.api.dao;

import com.passer.api.model.User;

import java.util.List;

public interface UserDao {

    int insert(User user);

    int deleteById(String id);

    int update(User user);

    User selectById(String id);

    List<User> selectAll();
}