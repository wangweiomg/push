package com.honeywen.push.controller;

import com.honeywen.push.entity.SysUser;
import com.honeywen.push.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 系统用户控制类
 * @Author RYF
 * @Date 2019/5/6
 **/
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取所有的用户
     * @return 所有用户 List<SysUser>
     */
    @PostMapping("/api/admin/getAllUsers")
    public List<SysUser> getAllUsers(){
        return sysUserService.getAllUsers();
    }

}
