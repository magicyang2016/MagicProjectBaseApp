package com.magic.magicprojectbaseapp.core.http.erro;

/**
 * <pre>
 *     author :
 *     time   : 2019/06/05
 *     desc   :ErrorBodyDTO
 * </pre>
 */
public class ErrorBodyDTO {
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
