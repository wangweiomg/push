package com.honeywen.push.service.impl;

import com.honeywen.push.dao.ChannelMapper;
import com.honeywen.push.dao.UserMapper;
import com.honeywen.push.entity.Channel;
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
    @Autowired
    private ChannelMapper channelMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void saveOrUpdate(User user) {

        if (isExist(user.getOpenId())) {
            userMapper.update(user);
        } else {
            userMapper.save(user);
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
    public void saveToUserChannel(Integer userId, String ticket) {

        Channel channel = channelMapper.getChannelByTicket(ticket);
        if (channel == null) {
            return;
        }

        userMapper.saveUserChannel(userId, channel.getId());


    }


}
