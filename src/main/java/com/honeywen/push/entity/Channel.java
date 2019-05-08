package com.honeywen.push.entity;

/**
 * @Author RYF
 * @Description 通道实体列 物理表 channel
 * @Date 2019-05-07 22:40
 **/
public class Channel {

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
    private Integer userid;

    /**
     * 通道sendkey
     */
    private String sendkey;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSendkey() {
        return sendkey;
    }

    public void setSendkey(String sendkey) {
        this.sendkey = sendkey;
    }
}
