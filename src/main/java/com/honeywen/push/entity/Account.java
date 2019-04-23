package com.honeywen.push.entity;

import lombok.Data;

/**
 * @author wangwei
 * @date 2019/4/21
 */
public @Data class Account {

    private Integer id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String remark;

}
