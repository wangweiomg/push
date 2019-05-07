package com.honeywen.push.mapper;

import com.honeywen.push.entity.SysUser;

import java.util.List;

/**
 * @Description
 * @Author RYF
 * @Date 2019/5/6
 **/
public interface SysUserMapper {
    List<SysUser> getAllUsers(String queryOpenid,String queryForbidden,String queryAdmin);

    boolean setForbiddenById(String id,String forbidden);
}
