package com.honeywen.push.controller;

import com.google.common.collect.Lists;
import com.honeywen.push.entity.User;
import com.honeywen.push.service.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 发布
 *
 * @author wangwei
 * @date 2019/5/9
 */
@Slf4j
@RestController
public class PublishController {

    @Value("${wx.mp.template3}")
    private String template2;

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private UserService userService;

    @GetMapping("/send")
    public void sendMsg(@RequestParam String sendKey, @RequestParam String title, @RequestParam String content) {


        WxMpTemplateData first = new WxMpTemplateData("first", title);
        WxMpTemplateData keyword1 = new WxMpTemplateData("keyword1", "正常");
        WxMpTemplateData keyword2 = new WxMpTemplateData("keyword2", DateTimeFormatter.ofPattern("MM月dd日HH:mm").format(LocalDateTime.now()));
        WxMpTemplateData remark = new WxMpTemplateData("remark", content);
        List<WxMpTemplateData> list = Lists.newArrayList(first, keyword1, keyword2, remark);
        try {

            WxMpTemplateMessage msg = WxMpTemplateMessage.builder().templateId(template2).data(list).build();

            // 通过key找通道，找到要接收人
            List<User> users = userService.findAll();
            for (User user : users) {
                log.info("toUser nickname-->{}, openId-->{}", user.getNickname(), user.getOpenId());
                msg.setToUser(user.getOpenId());
                wxMpService.getTemplateMsgService().sendTemplateMsg(msg);
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("发送消息异常", e);
        }

    }


}
