package com.passer.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: passer
 * @Date: 19-5-27 下午7:15
 * @Version 1.0
 */
@Data
public class DiscussEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 动态id
     */
    private Long id;

    /**
     * 网易云用户
     */
    private String userId;
    private User user;

    private List<DiscussComment> comments;

    /**
     * 动态标题
     */
    private String title;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 动态创建时间
     */
    private Date createTime;

}