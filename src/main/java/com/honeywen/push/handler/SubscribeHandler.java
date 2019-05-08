package com.honeywen.push.handler;

import com.honeywen.push.builder.TextBuilder;
import com.honeywen.push.entity.User;
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
 * 关注事件处理
 *
 * @author wangwei
 * @date 2019/5/7
 */
@Slf4j
@Component
public class SubscribeHandler extends AbstractHandler {
    
    @Autowired
    private UserService userService;


    @Override
    public WxMpXmlOutMessage handle(
            WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
            WxSessionManager sessionManager) {
        log.info("<--新关注用户 fromUser-->{}, wxMessage-->{}", wxMessage.getFromUser(), wxMessage);

        try {
            WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);
            if (wxMpUser != null) {
                // save to db
                log.info("<--新关注的用户信息-->{}, unionId-->{}", wxMpUser, wxMpUser.getUnionId());

                User user = new User();
                BeanUtils.copyProperties(wxMpUser, user);
                userService.saveOrUpdate(user);

            }
        } catch (WxErrorException e) {
            log.error("获取用户信息异常！", e);
            if (e.getError().getErrorCode() == 48001) {
                log.info("该公众号没有获取用户信息权限！");
            }
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            log.error("处理特殊请求异常", e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, wxMpService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }



        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }
}
