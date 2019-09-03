package com.magic.magicprojectbaseapp.utils.eventEntity;

/**
 * Created by yangshoushan on 2019/06/15.
 * 挂断和接听操作.后台服务监听手柄的接起和挂断
 */


public class CallbackEvent {
    private String state;

    public CallbackEvent() {
    }

    public CallbackEvent(String state) {

        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
