package com.honeywen.push.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangwei
 * @date 2019/4/25
 */
@Slf4j
@RestController
@RequestMapping("/wx")
public class WxMpController {

    private WxMpService wxMpService;

    private WxMpMessageRouter messageRouter;

    @Autowired
    public WxMpController(WxMpService wxMpService, WxMpMessageRouter messageRouter) {
        this.wxMpService = wxMpService;
        this.messageRouter = messageRouter;
    }


    @GetMapping("/validateToken/")
    public String validateToken(String signature, String timestamp, String nonce, String echostr) {

        return echostr;

    }

    @PostMapping("/validateToken/")
    public String post(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("openid") String openid,
            @RequestParam(name = "encrypt_type", required = false) String encType,
            @RequestParam(name = "msg_signature", required = false) String msgSignature) {

        log.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                openid, signature, encType, msgSignature, timestamp, nonce, requestBody);

        String out = null;
        // 明文传输
        if (encType == null) {
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            log.info("<--明文传输--> 返回消息outMessage-->{}", outMessage);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toXml();
        } else if ("aes".equalsIgnoreCase(encType)) {
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(),
                    timestamp, nonce, msgSignature);
            log.debug("<--消息解密后内容：-->{}", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        }
        log.debug("\n 组装回复信息: {}", out);


        return out;

    }


    private WxMpXmlOutMessage route(WxMpXmlMessage message) {


        return messageRouter.route(message);

    }

}
