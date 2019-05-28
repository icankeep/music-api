package com.passer.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: passer
 * @Date: 19-5-27 下午7:15
 * @Version 1.0
 */
@Data
public class DiscussComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态的id
     */
    private Long id;

    /**
     * 网易云用户id
     */
    private String userId;

    private User user;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论创建时间
     */
    private Date createTime;

    /**
     * 评论所属动态
     */
    private Long eventId;

}