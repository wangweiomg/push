package com.honeywen.push.aop;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwei
 * @date 2019/5/28
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "wangwei");

    }

    /**
     * 全局异常捕捉
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> errorHandler(Exception e) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("code", 100);
        map.put("msg", e.getMessage());
        return map;
    }

}
