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
    public List<SysUser> getAllUsers(String queryOpenid,String queryForbidden,String queryAdmin) {
        return sysUserMapper.getAllUsers(queryOpenid,queryForbidden,queryAdmin);
    }

    /**
     * 禁用启用,原有是禁用(0)改为启用(1)，是启用(1)改为禁用(0)
     * @param id
     * @param forbidden
     * @return
     */
    @Override
    public boolean setForbiddenById(String id, String forbidden) {
        if("0".equals(forbidden)){
            forbidden = "1";
        }else if("1".equals(forbidden)){
            forbidden = "0";
        }else{
            return false;
        }
        return sysUserMapper.setForbiddenById(id,forbidden);
    }
}
