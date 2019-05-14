package com.honeywen.push.handler;

import com.honeywen.push.entity.User;
import com.honeywen.push.service.ChannelService;
import com.honeywen.push.service.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangwei
 * @date 2019/5/13
 */
@Slf4j
@Component
public class ScanHandler extends AbstractHandler {

    @Autowired
    private UserService userService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        log.info("<--ScanHandler 被调用了wxMessage-->{}", wxMessage);
        log.info("<--ScanHandler 被调用了context-->{}", context);
        String fromUser = wxMessage.getFromUser();
        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(fromUser);
        User user = new User();
        BeanUtils.copyProperties(wxMpUser, user);
        userService.saveOrUpdate(user);

        String ticket = wxMessage.getTicket();
        // 扫完码登录，跳转到通道页面



        // 扫码事件处理
        return null;
    }
}
