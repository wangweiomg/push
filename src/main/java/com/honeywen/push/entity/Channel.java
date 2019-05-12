package com.honeywen.push.entity;

import lombok.Data;

/**
 * @Author RYF
 * @Description 通道实体列 物理表 channel
 * @Date 2019-05-07 22:40
 **/
public @Data class Channel {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 通道名称
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 通道创建者ID
     */
    private Integer userId;

    /**
     * 通道sendkey
     */
    private String sendKey;

    /**
     * 是否禁用
     */
    private String isforbidden;

    /**
     * 原有用户名-非字段
     */
    private String oldname;


}
