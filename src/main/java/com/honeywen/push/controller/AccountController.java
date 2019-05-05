package com.honeywen.push.controller;

import com.google.common.collect.Lists;
import com.honeywen.push.entity.Account;
import com.honeywen.push.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;

/**
 * @author wangwei
 * @date 2019/4/20
 */
@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WxMpService wxMpService;

    private String template1 = "An1aqe9SFEfVG7gWE4C8thJiCMPaeWSl-b397wb_2C8";
    private String template2 = "VrmkUnF0pyVlHKwK-1nIURYioBQSioYF3dJAk6f6D0A";
//    private String template3 = "waz4TzcmYxU32_FPvb3fQ2mC9zqMIdV45yH4MhB6UAQ";
    private String template3 = "awwsyi8C6UFIPD1F1PT8aJrHPtiqP3-UoVWLxf9JsIg";


    @GetMapping("/msg")
    public void wxMsg() {

        String wangwei = "o7ugQszTbCaw1pQkTSMVO7327-z4";
//        String yuanfang = "o7ugQszzrhLLsHKZA4r7x0dgrmRI";
//        WxMpKefuMessage message1 = WxMpKefuMessage.TEXT().toUser(wangwei).content("hello world, wangwei").build();
//        WxMpKefuMessage message2 = WxMpKefuMessage.TEXT().toUser(yuanfang).content("hello world, yuanfang").build();


//        WxMpTemplateData title = new WxMpTemplateData("title", "您尾号2269的招行一卡通入账人民币6720833.21元", "#0000FF");
//        WxMpTemplateData amount = new WxMpTemplateData("amount", "人民币6720833.21元", "#0000FF");
//        WxMpTemplateData tradeTime = new WxMpTemplateData("tradeTime", DateTimeFormatter.ofPattern("MM月dd日HH:mm").format(LocalDateTime.now()));
//        WxMpTemplateData tradeType = new WxMpTemplateData("tradeType", "汇款到账");
//        WxMpTemplateData payName = new WxMpTemplateData("payName", "唐丽娜");
//        WxMpTemplateData remark = new WxMpTemplateData("remark", "域名转让费");
//        List<WxMpTemplateData> list3 = Lists.newArrayList(title, amount, tradeTime, tradeType, payName, remark);


        WxMpTemplateData first = new WxMpTemplateData("first", "您好，您的订单已付款成功");
        WxMpTemplateData keyword1 = new WxMpTemplateData("keyword1", "88888888");
        WxMpTemplateData keyword2 = new WxMpTemplateData("keyword2", DateTimeFormatter.ofPattern("MM月dd日HH:mm").format(LocalDateTime.now()));
        WxMpTemplateData keyword3 = new WxMpTemplateData("keyword3", "274元");
        WxMpTemplateData keyword4 = new WxMpTemplateData("keyword4", "信用卡");
        WxMpTemplateData remark = new WxMpTemplateData("remark", "感谢您的惠顾");
        List<WxMpTemplateData> list = Lists.newArrayList(first, keyword1, keyword2, keyword3, keyword4, remark);

        try {
            String accessToken = wxMpService.getAccessToken();
            log.info("<--accessToken-->{}", accessToken);
            System.out.println("<--accessToken-->" + accessToken);

            WxMpUser wxMpUser = wxMpService.getUserService().userInfo("");
            WxMpTemplateMessage templateMessage3 = WxMpTemplateMessage.builder().templateId(template3).toUser(wangwei).data(list).build();
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage3);

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


    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDate.now()));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now()));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.now()));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(LocalDate.now()));

        System.out.println(DateTimeFormatter.ofPattern("MM月dd日HH:mm").format(LocalDateTime.now()));



    }

}
