package com.honeywen.push.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author wangwei
 * @date 2019/5/7
 */
public class TextBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        return WxMpXmlOutMessage
                .TEXT()
                .content(content)
                .fromUser(wxMessage.getFromUser())
                .toUser(wxMessage.getToUser())
                .build();
    }
}
