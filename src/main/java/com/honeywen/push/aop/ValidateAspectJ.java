package com.honeywen.push.aop;

import com.honeywen.push.entity.User;
import com.honeywen.push.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author wangwei
 * @date 2019/5/28
 */
@Slf4j
@Aspect
@Configuration
public class ValidateAspectJ {

    /**
     * 定义切入点
     */
//    @Pointcut("execution(* com.honeywen.push.service.find*(..))")
//    public void executeSevice() {}

    @Pointcut("execution(* com.honeywen.push.controller.ChannelController.*(..))")
    public void validateToken() {}

    /**
     * 通过连接点切入
     */
    @Before("execution(* findById*(..))")
    public void validateLogin() {
        System.out.println("before 执行了");
    }



    @Before("validateToken()")
    public void beforeChannel(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.debug("<--url-->{}, method-->{}, ip-->{}, class_method-->{}, args-->{}",
                request.getRequestURL().toString(),
                request.getMethod(),
                request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));

        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("no login");
        }

        User user = UserUtil.getByToken(token);

        log.info("<--登陆人是-->{}", user.getNickname());


    }


}
