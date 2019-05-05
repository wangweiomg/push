package com.honeywen.push.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangwei
 * @date 2019/5/5
 */
@Slf4j
@Controller
public class WxVerifyController {


    @Value("${wx.verifyFile}")
    private String content;

    @GetMapping("/MP_verify_nPamSDXCVOlXvM6C.txt")
    public void verifyFile(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        response.setHeader("Content-Disposition", "attachment; filename=MP_verify_nPamSDXCVOlXvM6C.txt");

        try {
            response.getWriter().write(content);
        } catch (IOException e) {
            log.error("wx verify file error!", e);
        }


    }
}
