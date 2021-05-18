package com.oldsinger.lib_core.mvp.bean;

public class ErrorBean {

    public int code;
    public String msg;

    public ErrorBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
