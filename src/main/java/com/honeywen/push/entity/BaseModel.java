package com.honeywen.push.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wangwei
 * @date 2019/5/8
 */
public @Data class BaseModel {

    protected Integer id;
    protected String remark;
    protected Date createAt;
    protected Integer createBy;
    protected Date updateAt;
    protected Integer updateBy;
    protected Integer deleteFlag;

    public enum DeleteFlagEnum {
        /**
         * 1. 正常 0 删除
         */
        NORMAL(1),
        DELETED(2);

        private int value;
        DeleteFlagEnum(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
