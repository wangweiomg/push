package com.honeywen.push.handler;

import com.alibaba.fastjson.JSON;
import com.honeywen.push.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangwei
 * @date 2019/5/8
 */
@Slf4j
@Component
public class MsgHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        log.info("<--MsgHandler 被调用了-->{}", wxMessage.getContent());
        if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
            // TODO save to db
        }
        //
        log.info("<--kfonline-->{}", wxMpService.getKefuService().kfOnlineList());
        if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服", "hello")
                && wxMpService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
            log.info("<--走了kfonline消息-->>");
            return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
        }

        // 组装回复消息
        String content = "收到消息内容： " + JSON.toJSONString(wxMessage);


        WxMpXmlOutMessage msg = new TextBuilder().build(content, wxMessage, wxMpService);
        log.info("<--msgHandler最后回复-->{}", msg);
//        return msg;
        // 接入了图灵机器人测试
        return null;


    }
}
