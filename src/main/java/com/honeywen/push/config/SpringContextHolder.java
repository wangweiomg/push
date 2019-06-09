package com.honeywen.push.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wangwei
 * @date 2019/6/9
 */
public class SpringContextHolder implements ApplicationContextAware {

    public static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        SpringContextHolder.applicationContext = applicationContext;

    }


    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    private static void assertApplicationContext() {
        if (SpringContextHolder.applicationContext == null) {
            throw new RuntimeException("applicaitonContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }
}
