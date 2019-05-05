package com.honeywen.push.service.impl;

import com.honeywen.push.dao.UserMapper;
import com.honeywen.push.entity.User;
import com.honeywen.push.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/5/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
