package com.honeywen.push.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @date 2019/6/18
 */
@Configuration
public class BusinessConfiguration {

    @Bean
    public Cache<Object, Object> userCache() {
        return CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(5, TimeUnit.MINUTES).build();
    }




}
