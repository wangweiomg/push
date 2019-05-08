package com.honeywen.push.entity;

/**
 * @Description 统一返回对象
 * @Author RYF
 * @Date 2019/5/8 
 **/
public class Result<T> {

    /**
     * 状态码(0是成功,1是失败)
     */
    private Integer status;

    /**
     * 错误码
     */
    private Integer error_code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体数据
     */
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }
}
