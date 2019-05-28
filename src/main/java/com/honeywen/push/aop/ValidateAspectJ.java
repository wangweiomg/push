package com.honeywen.push.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangwei
 * @date 2019/5/28
 */
@Aspect
@Configuration
public class ValidateAspectJ {

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.honeywen.push.service.find*(..))")
    public void executeSevice() {}

    /**
     * 通过连接点切入
     */
    @Before("execution(* findById*(..))")
    public void validateLogin() {
        System.out.println("before 执行了");
    }
}
