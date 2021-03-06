package com.honeywen.push.controller;

import com.google.common.cache.Cache;
import com.honeywen.push.entity.Channel;
import com.honeywen.push.entity.Result;
import com.honeywen.push.service.ChannelService;
import com.honeywen.push.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Author RYF
 * @Description 通道管理的控制类
 * @Date 2019-05-07 22:45
 **/
@Slf4j
@RestController
@RequestMapping("/api/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private WxMpService wxMpService;
    @Qualifier("userCache")
    @Autowired
    private Cache<Object, Object> userCache;

    /**
     * 通道数据校验
     * @param channel
     * @return
     */
    public Result ChannelValidate(Channel channel){
        if(StringUtils.isEmpty(channel.getName())){
            return ResultUtil.error(1,"通道名称不能为空");
        }
        if(StringUtils.isEmpty(channel.getEmail())){
            return ResultUtil.error(2,"Email不能为空");
        }
        if(channel.getName().length()>300){
            return ResultUtil.error(3,"通道名称不能超过300字符");
        }
        if(channel.getEmail().length()>300){
            return ResultUtil.error(4,"Email不能超过300字符");
        }
        String pattern = "^[0-9A-Za-z][\\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\\.[0-9A-Za-z]+)+$";
        if(!channel.getEmail().matches(pattern)){
            return ResultUtil.error(4,"Email格式不正确");
        }
        return ResultUtil.success();
    }

    /**
     *
     * @param flag
     * @return
     */
    public Result returnResutl(int flag){
        if(0 == flag ){
            return ResultUtil.success();
        }else if(1 == flag ){
            return ResultUtil.error(5,"通道名称重复了");
        }
        return ResultUtil.error();
    }

    /**
     * 新增通道
     * @param channel
     * @return
     */
    @PostMapping("/addChannel")
    public Result addChannel(Channel channel) throws WxErrorException {
        //数据校验
        Result rs =  ChannelValidate(channel);
        if(rs.getStatus() != 0){
            return rs;
        }
        channel.setIsforbidden("0");
        //获取用户id todo
        channel.setUserId(1);
        //生成sendkey todo
        String key = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        channel.setSendKey(key);


        int flag = channelService.addChannel(channel);
        // 生成通道永久二维码
        WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(channel.getId());
        log.info("<--start create qrcode--> channelId-->{}, ticket-->{}", channel.getId(), ticket.getTicket());
        channel.setTicket(ticket.getTicket());
        channelService.editChannel(channel);

        return returnResutl(flag);
    }

    /**
     * 获取用户的通道(通过用户ID)
     * @return
     */
    @PostMapping("/getChannel")
    public Result getChannel(String token){
        //获取用户id todo
        Object openId = userCache.getIfPresent(token);
        if (openId != null) {
            List<Channel> channels = channelService.getUserChannel((String)openId);
            return ResultUtil.success(channels);

        }
        return ResultUtil.error(404, "no login");
    }

    /**
     * 获取用户的通道(通过通道ID)
     * @param id
     * @return
     */
    @PostMapping("/getChannelById")
    public Result getChannelById(Integer id){
        Channel channel = new Channel();
        channel.setId(id);
        //获取用户id todo
        Integer userid = 1;
        channel.setUserId(userid);

        Channel channel1 = channelService.getChannelById(channel);
        return ResultUtil.success(channel1);
    }

    /**
     * 更新通道
     * @param channel
     * @return
     */
    @PostMapping("/editChannel")
    public Result editChannel(Channel channel){
        //数据校验
        Result rs =  ChannelValidate(channel);
        if(rs.getStatus() != 0){
            return rs;
        }
        //获取用户id todo
        channel.setUserId(1);
        int flag = channelService.editChannel(channel);
        return returnResutl(flag);
    }

    @PostMapping("/deleteChannelById")
    public Result deleteChannelById(Integer id){
        Channel channel = new Channel();
        channel.setId(id);
        //获取用户id todo
        Integer userid = 1;
        channel.setUserId(userid);

        int flag = channelService.deleteChannelById(channel);
        if(0 == flag ){
            return ResultUtil.success();
        }
        return ResultUtil.error();
    }

}


