package com.magic.magicprojectbaseapp.utils.exception;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/05
 *     desc   :整个HTTP请求过程中统一的错误接口格式
 * </pre>
 */
public class NetException extends Exception {
    private int errorCode;//错误码
    private String[] tips;//对应错误的处理方法

    public NetException(Throwable throwable, int errorCode, String errorMsg, String[] tips){
        super(errorMsg,throwable);
        this.errorCode = errorCode;
        this.tips = tips;
    }

    public NetException(int errorCode, String errorMsg, String[] tips){
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
