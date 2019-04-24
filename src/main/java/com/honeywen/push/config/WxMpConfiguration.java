package com.honeywen.push.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    private WxMpInMemoryConfigStorage config() {

        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(appId);
        config.setSecret(secret);
        config.setToken(token);
        config.setAesKey(aesKey);
        return config;
    }


    @Bean
    public WxMpService wxMpService() {

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config());
        return wxMpService;

    }

}
