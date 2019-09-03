package com.magic.magicprojectbaseapp.utils.eventEntity;

/**
 * Created by 山东御银智慧 on 2019/06/18.
 * 传递录音上传的事件
 */

public class UpRecordEvent {
    String message;

    public UpRecordEvent() {
    }

    public UpRecordEvent(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
