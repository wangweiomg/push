package com.honeywen.push.service;

import com.honeywen.push.entity.SysUser;

import java.util.List;

/**
 * @Description 系统用户 服务类
 * @Author RYF
 * @Date 2019/5/6
 **/
public interface SysUserService {
    /**
     * 获取所有的用户
     * @return 所有用户 List<SysUser>
     */
    List<SysUser> getAllUsers(String queryOpenid,String queryForbidden,String queryAdmin);

    /**
     * 禁用启用,原有是禁用(0)改为启用(1)，是启用(1)改为禁用(0)
     * @param id
     * @param forbidden
     * @return
     */
    boolean setForbiddenById(String id,String forbidden);
}
