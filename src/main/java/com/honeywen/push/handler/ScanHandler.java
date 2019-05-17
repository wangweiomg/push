package com.honeywen.push.handler;

import com.honeywen.push.builder.TextBuilder;
import com.honeywen.push.entity.User;
import com.honeywen.push.service.ChannelService;
import com.honeywen.push.service.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
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
    @Autowired
    private ChannelService channelService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        log.info("<--ScanHandler 被调用了wxMessage-->{}", wxMessage);
        log.info("<--ScanHandler 被调用了context-->{}", context);
        log.info("<--ScanHandler 被调用了, eventKey-->{}", wxMessage.getEventKey());

        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMessage.getFromUser());


        User user = new User();
        if (wxMpUser != null) {
            BeanUtils.copyProperties(wxMpUser, user);
            userService.saveOrUpdate(user);
        }

        // 获取扫码场景值id
        // 场景值如果是UUID字符型，那么就是扫码登陆，如果数字型就是关联通道
        String eventKey = wxMessage.getEventKey();
        if (eventKey.length() > 5) {
            WxSession session = sessionManager.getSession(eventKey);
            session.setAttribute(eventKey, Boolean.TRUE);

        } else {
            // 关联操作
            userService.saveToUserChannel(user.getId(), Integer.valueOf(wxMessage.getEventKey()));

        }


        // 扫码事件处理
        return new TextBuilder().build("欢迎来到兄弟干果商行", wxMessage, wxMpService);
    }
}
