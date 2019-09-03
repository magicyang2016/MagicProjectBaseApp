package com.magic.magicprojectbaseapp.mvp.entity;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/09
 *     desc   : PagerLayout中存放异常信息的bean类
 * </pre>
 */
public class ErrorBean {
    private String msg;
    private int code;
    private Exception exce;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Exception getExce() {
        return exce;
    }

    public void setExce(Exception exce) {
        this.exce = exce;
    }
}
