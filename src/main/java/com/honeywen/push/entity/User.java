package com.honeywen.push.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangwei
 * @date 2019/5/5
 */
public @Data class User extends BaseModel implements Serializable {

    private Long token;
    private String loginName;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private Integer status;

    private String openId;
    private String unionId;
    private String nickname;
    /**
     * 性别描述信息：男、女、未知等.
     */
    private String sexDesc;
    /**
     * 性别表示：1，2等数字.
     */
    private Integer sex;
    private String headImgUrl;
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
