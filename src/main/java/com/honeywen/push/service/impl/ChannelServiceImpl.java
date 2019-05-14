package com.honeywen.push.service.impl;

import com.honeywen.push.dao.UserMapper;
import com.honeywen.push.entity.Channel;
import com.honeywen.push.dao.ChannelMapper;
import com.honeywen.push.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author RYF
 * @Description 通道管理服务实现类
 * @Date 2019-05-07 22:51
 **/
@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WxMpService wxMpService;

    @Override
    public int addChannel(Channel channel) {
        //通道名称重复校验
        Channel channel1 = channelMapper.getChannelByName(channel);
        if(channel1 != null){
            return 1;
        }
        boolean flag = channelMapper.addChannel(channel);

        // 默认保存管理员为通道第一个用户
        userMapper.saveUserChannel(channel.getUserId(), channel.getId());

        try {
            WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(channel.getId());
        } catch (WxErrorException e) {
            log.error("创建qrcode失败", e);

        }
//        channelMapper.editChannel()


        if(flag){
            return 0;
        }else{
            return -1;
        }
    }

    @Override
    public List<Channel> getChannel(Integer userId) {
        return channelMapper.getChannel(userId);
    }

    @Override
    public Channel getChannelById(Channel channel) {
        return channelMapper.getChannelById(channel);
    }

    @Override
    public int editChannel(Channel channel) {
        //通道名称重复校验
        Channel channel1 = channelMapper.getChannelByName(channel);
        if(channel1 != null && !channel1.getName().equals(channel.getOldname())){
            return 1;
        }
        boolean flag = channelMapper.editChannel(channel);
        if(flag){
            return 0;
        }else{
            return -1;
        }
    }

    @Override
    public int deleteChannelById(Channel channel) {
        boolean flag = channelMapper.deleteChannelById(channel);
        if(flag){
            return 0;
        }else{
            return -1;
        }
    }
}
