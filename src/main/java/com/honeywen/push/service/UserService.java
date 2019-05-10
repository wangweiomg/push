package com.honeywen.push.service;

import com.honeywen.push.entity.User;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/5/5
 */
public interface UserService {

    List<User> findAll();

    void save(User user);

    void saveOrUpdate(User user);

    boolean isExist(String openId);

    List<String> findOpenIdList(String sendKey);
}
