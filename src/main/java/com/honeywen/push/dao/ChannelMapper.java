package com.honeywen.push.dao;

import com.honeywen.push.entity.Channel;
import com.honeywen.push.entity.SysUser;

import java.util.List;

/**
 * @Description
 * @Author RYF
 * @Date 2019/5/6
 **/
public interface ChannelMapper {

    boolean addChannel(Channel channel);

    List<Channel> getChannel(Integer userid);

    Channel getChannelByName(Channel channel);

    Channel getChannelById(Channel channel);

    boolean editChannel(Channel channel);

    boolean deleteChannelById(Channel channel);
}
