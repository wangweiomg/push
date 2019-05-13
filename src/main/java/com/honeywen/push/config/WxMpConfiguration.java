package com.honeywen.push.config;

import com.google.common.collect.Maps;
import com.honeywen.push.handler.MsgHandler;
import com.honeywen.push.handler.ScanHandler;
import com.honeywen.push.handler.SubscribeHandler;
import static me.chanjar.weixin.common.api.WxConsts.*;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author wangwei
 * @date 2019/4/24
 */
@Configuration
public class WxMpConfiguration {

    @Value("${wx.mp.appID}")
    private String appId;
    @Value("${wx.mp.appsecret}")
    private String secret;
    @Value("${wx.mp.token}")
    private String token;
    @Value("${wx.mp.aesKey}")
    private String aesKey;

    @Value("${wx.mp.template1}")
    private String templateOrderNotice;

    private SubscribeHandler subscribeHandler;
    private ScanHandler scanHandler;
    private MsgHandler msgHandler;

    private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();

    @Autowired
    public WxMpConfiguration(SubscribeHandler subscribeHandler, ScanHandler scanHandler, MsgHandler msgHandler) {
        this.subscribeHandler = subscribeHandler;
        this.scanHandler = scanHandler;
        this.msgHandler = msgHandler;
    }


    @Bean
    public WxMpService wxMpService() {

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config());
        return wxMpService;

    }

    private WxMpInMemoryConfigStorage config() {

        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(appId);
        config.setSecret(secret);
        config.setToken(token);
        config.setAesKey(aesKey);
        return config;
    }


    @Bean
    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE).handler(subscribeHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCAN).handler(this.scanHandler).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();


        return newRouter;
    }

}
