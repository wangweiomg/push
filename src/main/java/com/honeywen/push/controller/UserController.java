package com.honeywen.push.controller;

import com.google.common.collect.Maps;
import com.honeywen.push.entity.Result;
import com.honeywen.push.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author wangwei
 * @date 2019/5/5
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @PostMapping("/signin")
    public Result<Map<String, Object>> signin(HttpServletRequest request) throws WxErrorException {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        int expires = 1800;
        WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(uuid, expires);

        log.info("<--ticket-->{}, ticket.getUrl-->{}", ticket, ticket.getUrl());
        Map<String, Object> result = Maps.newHashMap();
        result.put("expire_seconds", expires);
        result.put("ticket", ticket.getTicket());
//        result.put("qr_url", ticket.getUrl());
        result.put("qr_url", "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket.getTicket());
        result.put("logintoken", uuid);




        request.getSession().setAttribute(uuid, Boolean.FALSE.toString());


        return ResultUtil.success(result);


    }

    @GetMapping("/check")
    public Result<Boolean> loginHandle(String logintoken, HttpServletRequest request) {

        String result = (String) request.getSession().getAttribute(logintoken);
        boolean flag = Objects.equals(result, Boolean.TRUE.toString());
        log.info("<--equals-->{}, result -->{}, logintoken-->{}", flag, result, logintoken);
        if (flag) {

            return ResultUtil.success(Boolean.TRUE);

        }

        return ResultUtil.success(Boolean.FALSE);
    }

}
