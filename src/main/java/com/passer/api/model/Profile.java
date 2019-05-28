package com.passer.api.model;

import lombok.Data;

/**
 * @Author: passer
 * @Date: 19-5-27 下午6:11
 * @Version 1.0
 */
@Data
public class Profile {
    private String detailDescription;
    private boolean followed;
    private String province;
    private boolean defaultAvatar;
    private String avatarUrl;
    private String backgroundUrl;
    private String userId;
    private String gender;
    private String nickname;
    private String birthday;
    private String city;
    private String signature;
    private String description;
    private String followeds;
    private String follows;
    private String playlistCount;
    private String eventCount;
}
