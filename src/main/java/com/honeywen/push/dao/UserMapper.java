package com.honeywen.push.dao;

import com.honeywen.push.entity.User;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/5/5
 */
public interface UserMapper {
    /**
     * 通过登录名获取用户信息
     *
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);

    /**
     * 通过id找用户
     *
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 保存用户
     *
     * @param user
     */
    void save(User user);

    User findByOpenId(String openId);

    void update(User user);

    List<User> findAll();
}
