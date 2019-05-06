package com.honeywen.push.service.impl;

import com.honeywen.push.entity.SysUser;
import com.honeywen.push.mapper.SysUserMapper;
import com.honeywen.push.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 系统用户 服务实现类
 * @Author RYF
 * @Date 2019/5/6
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getAllUsers() {
        return sysUserMapper.getAllUsers();
    }
}
