package com.honeywen.push.controller;

import com.google.common.collect.Lists;
import com.honeywen.push.entity.Account;
import com.honeywen.push.service.AccountService;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/4/20
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WxMpService wxMpService;

    private String template1 = "An1aqe9SFEfVG7gWE4C8thJiCMPaeWSl-b397wb_2C8";
    private String template2 = "VrmkUnF0pyVlHKwK-1nIURYioBQSioYF3dJAk6f6D0A";


    @GetMapping("/msg")
    public void wxMsg() {

        String wangwei = "o7ugQszTbCaw1pQkTSMVO7327-z4";
        String yuanfang = "o7ugQszzrhLLsHKZA4r7x0dgrmRI";
        WxMpKefuMessage message1 = WxMpKefuMessage.TEXT().toUser(wangwei).content("hello world, wangwei").build();
        WxMpKefuMessage message2 = WxMpKefuMessage.TEXT().toUser(yuanfang).content("hello world, yuanfang").build();

        WxMpTemplateData data1 = new WxMpTemplateData("keyword1", "转账提醒");
        WxMpTemplateData data2 = new WxMpTemplateData("keyword2", "任远方");
        WxMpTemplateData data3 = new WxMpTemplateData("keyword3", "收到拆迁款 4920126.78元。");
        List<WxMpTemplateData> list = Lists.newArrayList(data1, data2, data3);

        WxMpTemplateMessage templateMessage1 = WxMpTemplateMessage.builder().templateId(template2).toUser(wangwei).data(list).build();
        WxMpTemplateMessage templateMessage2 = WxMpTemplateMessage.builder().templateId(template2).toUser(yuanfang).data(list).build();
        try {
            wxMpService.getKefuService().sendKefuMessage(message1);
            wxMpService.getKefuService().sendKefuMessage(message2);

            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage1);
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage2);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }




    @GetMapping("/hello")
    public String test() {
        return "hello world!";
    }


    @GetMapping("/account")
    public List<Account> getAccount() {

        List<Account> list = accountService.findAll();
        
        return list;

    }

}
