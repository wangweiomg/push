package com.honeywen.push.util;

import com.honeywen.push.config.SpringContextHolder;
import com.honeywen.push.entity.User;
import com.honeywen.push.service.UserService;

import static com.honeywen.push.constant.Constants.*;

/**
 * @author wangwei
 * @date 2019/6/9
 */
public class UserUtil {

    private static UserService userService = SpringContextHolder.getBean("userService");

    public static User getByToken(String token) {
        // from cache
        User user = (User) CacheHelper.getObject(GLOBAL_SCHEMA, USER_PREFIX + token);
        if (user != null) {
            return user;
        }
        // from db
        user = userService.findByToken(token);
        if (user == null) {
            return new User();
        }
        return user;

    }

    public static User getById(Integer id) {
        // from cache
        User user = (User) CacheHelper.getObject(GLOBAL_SCHEMA, USER_PREFIX + id);
        if (user != null) {
            return user;
        }
        // from db
        user = userService.findById(id);
        if (user == null) {
            return new User();
        }
        return user;
    }
}
