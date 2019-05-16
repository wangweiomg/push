package com.honeywen.push.service;


import com.honeywen.push.entity.Channel;

import java.util.List;

/**
 * @Description 通道管理 服务类
 * @Author RYF
 * @Date 2019/5/6
 **/
public interface ChannelService {

    int addChannel(Channel channel);

    List<Channel> getChannel(Integer userid);

    Channel getChannelById(Channel channel);

    Channel getChannelByChannelId(Integer channelId);

    int editChannel(Channel channel);

    int deleteChannelById(Channel channel);

    boolean isExist(Integer channelId);
}
