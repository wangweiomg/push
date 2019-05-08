package com.honeywen.push.controller;

import com.alibaba.fastjson.JSONObject;
import com.honeywen.push.entity.Channel;
import com.honeywen.push.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/addChannel")
    public String addChannel(Channel channel){
        JSONObject jo = new JSONObject();//统一json返回 todo

        //数据校验 TODO

        //获取用户id todo
        channel.setUserid(1);
        //生成sendkey todo
        channel.setSendkey("dddd");
        boolean flag = channelService.addChannel(channel);


        jo.put("status",0);
        jo.put("msg","创建成功");
        jo.put("data","");
        return jo.toJSONString();
    }

}
