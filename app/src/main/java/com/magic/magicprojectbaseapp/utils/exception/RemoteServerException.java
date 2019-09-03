package com.magic.magicprojectbaseapp.utils.exception;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/05
 *     desc   :和后端沟通后自定义的服务器信息异常类
 * </pre>
 */
public class RemoteServerException extends RuntimeException {
    private int errorCode;//错误码
    private String[] tips;//对应错误的处理方法

    public RemoteServerException(Throwable cause, int errorCode, String errorMsg, String[] tips) {
        super(cause);
        this.errorCode = errorCode;
        this.tips = tips;
    }

    public RemoteServerException(int errorCode, String errorMsg, String[] tips) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.tips = tips;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getTips() {
        return tips;
    }

    public void setTips(String[] tips) {
        this.tips = tips;
    }
}
