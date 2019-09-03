package com.magic.magicprojectbaseapp.mvp.entity.eventbean;

/**
 * Created by ${LiBing} on 2019/2/18.
 */

public class SubmitReportBean {
    /**
     * result : true
     * message : 提交成功！
     */

    private boolean result;
    private String message;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
