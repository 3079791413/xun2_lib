package com.oldsinger.lib_core.mvp.bean;

public class BaseDataBean<T> {

    private int code;
    private String msg;

    @Override
    public String toString() {
        return "BaseDataBean{" +
                "status=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
