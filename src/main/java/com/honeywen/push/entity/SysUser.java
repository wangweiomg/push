package com.honeywen.push.entity;

/**
 * @Description 系统用户 物理表:sys_users
 * @Author RYF
 * @Date 2019/5/6
 **/
public class SysUser {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 微信ID
     */
    private String openid;

    /**
     * 是否是管理员 0 否 1 是
     */
    private String isAdmin;

    /**
     * 是否禁用 0 否 1 是
     */
    private String isForbidden;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(String isForbidden) {
        this.isForbidden = isForbidden;
    }
}
