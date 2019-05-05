package com.honeywen.push.entity;

import lombok.Data;

/**
 * @author wangwei
 * @date 2019/5/5
 */
public @Data class SysDict {

    private Integer id;
    private String type;
    private String label;
    private String value;
    private String description;
    private Integer sort;
    private String remark;

}
