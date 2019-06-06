package com.honeywen.push.service.impl;

import com.honeywen.push.dao.ChannelMapper;
import com.honeywen.push.dao.UserMapper;
import com.honeywen.push.entity.Channel;
import com.honeywen.push.entity.User;
import com.honeywen.push.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/5/5
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChannelMapper channelMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(User user) {

        User oldUser = userMapper.findByOpenId(user.getOpenId());
        if (oldUser == null) {

            userMapper.save(user);
        } else {
            user.setId(oldUser.getId());
            userMapper.update(user);
        }

    }

    @Override
    public boolean isExist(String openId) {

        return userMapper.findByOpenId(openId) != null;
    }

    @Override
    public List<String> findOpenIdList(String sendKey) {

        return userMapper.findOpenIdList(sendKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveToUserChannel(Integer userId, Integer channelId) {

        userMapper.saveUserChannel(userId, channelId);


    }

    /**
     *  是否存在
     * @param userId
     * @param channelId
     * @return
     */
    @Override
    public boolean existUserChannel(Integer userId, Integer channelId) {
        int count = userMapper.findUserChannelCount(userId, channelId);
        return count > 0;
    }


}
