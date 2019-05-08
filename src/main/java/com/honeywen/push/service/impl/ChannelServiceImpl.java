package com.honeywen.push.service.impl;

import com.honeywen.push.entity.Channel;
import com.honeywen.push.mapper.ChannelMapper;
import com.honeywen.push.service.ChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author RYF
 * @Description 通道管理服务实现类
 * @Date 2019-05-07 22:51
 **/
@Service
public class ChannelServiceImpl implements ChannelService {

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public boolean addChannel(Channel channel) {
        return channelMapper.addChannel(channel);
    }
}
