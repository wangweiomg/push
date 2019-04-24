package com.honeywen.push.controller;

import com.honeywen.push.entity.Account;
import com.honeywen.push.service.AccountService;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
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


    @GetMapping("/msg")
    public void wxMsg() {

        String wangwei = "o7ugQszTbCaw1pQkTSMVO7327-z4";
        String yuanfang = "o7ugQszzrhLLsHKZA4r7x0dgrmRI";
        WxMpKefuMessage message1 = WxMpKefuMessage.TEXT().toUser(wangwei).content("hello world, wangwei").build();
        WxMpKefuMessage message2 = WxMpKefuMessage.TEXT().toUser(yuanfang).content("hello world, yuanfang").build();
        try {
            wxMpService.getKefuService().sendKefuMessage(message1);
            wxMpService.getKefuService().sendKefuMessage(message2);
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
