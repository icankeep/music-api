package com.passer.api.model;

import lombok.Data;

/**
 * @Author: passer
 * @Date: 19-5-27 下午3:02
 * @Version 1.0
 */
@Data
public class User {
    private String phone;
    private String email;
    private String password;
    private String level;
    private String listenSongs;
    private Profile profile;
    private boolean peopleCanSeeMyPlayRecord;
    private String createTime;
    private String createDays;
}

