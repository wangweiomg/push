package com.honeywen.push.controller;

import com.honeywen.push.entity.SysUser;
import com.honeywen.push.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 系统用户控制类
 * @Author RYF
 * @Date 2019/5/6
 **/
@RestController
@RequestMapping("/api/admin")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取所有的用户
     * @return 所有用户 List<SysUser>
     */
    @PostMapping("/getAllUsers")
    public List<SysUser> getAllUsers(String queryOpenid,String queryForbidden,String queryAdmin){
        //TODO 判断是否具有管理员权限
        return sysUserService.getAllUsers(queryOpenid,queryForbidden,queryAdmin);
    }

    /**
     * 禁用启用,原有是禁用(0)改为启用(1)，是启用(1)改为禁用(0)
     * @param id
     * @param forbidden
     * @return
     */
    @PostMapping("/setForbiddenById")
    public boolean setForbiddenById(String id,String forbidden ){
        //TODO 判断是否具有管理员权限
        return sysUserService.setForbiddenById(id,forbidden);
    }

}
