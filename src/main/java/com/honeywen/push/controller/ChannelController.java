package com.honeywen.push.controller;

import com.alibaba.fastjson.JSONObject;
import com.honeywen.push.entity.Channel;
import com.honeywen.push.entity.Result;
import com.honeywen.push.service.ChannelService;
import com.honeywen.push.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author RYF
 * @Description 通道管理的控制类
 * @Date 2019-05-07 22:45
 **/
@RestController
@RequestMapping("/api/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

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
    public Result addChannel(Channel channel){
        //数据校验
        Result rs =  ChannelValidate(channel);
        if(rs.getStatus() != 0){
            return rs;
        }
        channel.setIsforbidden("0");
        //获取用户id todo
        channel.setUserid(1);
        //生成sendkey todo
        channel.setSendkey("dddd");
        int flag = channelService.addChannel(channel);
        return returnResutl(flag);
    }

    /**
     * 获取用户的通道(通过用户ID)
     * @return
     */
    @PostMapping("/getChannel")
    public Result getChannel(){
        //获取用户id todo
        Integer userid = 1;
        List<Channel> channels = channelService.getChannel(userid);
        return ResultUtil.success(channels);
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
        channel.setUserid(userid);

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
        channel.setUserid(1);
        int flag = channelService.editChannel(channel);
        return returnResutl(flag);
    }

    @PostMapping("/deleteChannelById")
    public Result deleteChannelById(Integer id){
        Channel channel = new Channel();
        channel.setId(id);
        //获取用户id todo
        Integer userid = 1;
        channel.setUserid(userid);

        int flag = channelService.deleteChannelById(channel);
        if(0 == flag ){
            return ResultUtil.success();
        }
        return ResultUtil.error();
    }

}


