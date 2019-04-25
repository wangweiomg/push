package com.honeywen.push.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwei
 * @date 2019/4/25
 */
@RestController
@RequestMapping("/wx")
public class WxMpController {

    @GetMapping("/validateToken/")
    public String validateToken(String signature, String timestamp, String nonce, String echostr) {

        return echostr;

    }

}
