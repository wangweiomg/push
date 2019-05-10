package com.honeywen.push.util;

import com.honeywen.push.entity.Result;

/**
 * @Description 统一返回工具类
 * @Author RYF
 * @Date 2019/5/8
 **/
public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setStatus(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result success(String msg ,Object object){
        Result result = new Result();
        result.setStatus(0);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setStatus(1);
        result.setError_code(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setStatus(1);
        result.setError_code(-1);
        result.setMsg("未知错误");
        return result;
    }
}
