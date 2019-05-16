package com.honeywen.push.controller;

import com.honeywen.push.entity.Result;
import com.honeywen.push.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangwei
 * @date 2019/5/5
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/check")
    public Result<Boolean> loginHandle(String logintoken, HttpServletRequest request) {

        String token = (String) request.getSession().getAttribute(logintoken);
        if (StringUtils.isNotEmpty(token)) {

            return ResultUtil.success(Boolean.TRUE);

        }

        return ResultUtil.success(Boolean.FALSE);
    }

}
