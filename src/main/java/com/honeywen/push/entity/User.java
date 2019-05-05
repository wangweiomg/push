package com.honeywen.push.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangwei
 * @date 2019/5/5
 */
public @Data class User implements Serializable {

    private Integer id;
    private String loginName;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private Integer status;
    private String wxOpenId;
    private String wxUnionId;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String country;
    private String province;
    private String city;
    private String language;


    public boolean isAdmin() {
        return id != null && 1 == id;
    }

    public enum StatusEnum {
        /**
         * 1. 启用 2停用
         */
        ON(1), OFF(2);

        private int value;

        StatusEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
