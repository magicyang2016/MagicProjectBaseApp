package com.magic.magicprojectbaseapp.utils.exception;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 22019/06/05
 *     desc   :网络封装框架下错误类型的枚举，以后可以扩展
 *             <无网络/>                       请检查网线；检查ip地址；检查服务器地址；重新加载；
 *             <Http错误均视作网络错误/>       请检查网线；检查ip地址；检查服务器地址；重新加载；
 *
 *             <服务器返回：token失效/>        跳转到登录界面；重新加载；
 *             <服务器返回：账号登录冲突/>     提示退出或跳转到登录界面或直接重新登录；
 *             <服务器返回：无账号/>           提示账号错误,显示清空按钮；
 *             <服务器返回：密码错误/>         提示密码错误，显示清空按钮；
 *             <服务器返回：参数异常/>         提示参数错误；重新加载；
 *             <服务器返回：空视图/>           空；重新加载；
 *
 *             <服务器返回：账号被禁/>         跳转到登录界面；重新加载；
 *             <服务器返回：已经注册/>         跳转到登录界面；重新加载；
 *             <服务器返回：重新登陆/>         跳转到登录界面；
 *             <服务器返回：验证码错误/>       重新发送验证码；
 *             <服务器返回：无权限/>           提示无权限；重新加载；
 *
 *             <连接网络错误：/>               请检查网线；检查ip地址；检查服务器地址；重新加载；
 *             <网络超时：/>                   请检查网线；检查ip地址；检查服务器地址；重新加载；
 *             <解析响应数据错误：/>           获取响应数据；重新加载；联系厂家；
 *             <请求数据错误：/>               获取请求数据；重新加载；联系厂家；
 *             <未知错误：/>                   显示未知错误信息；重新加载；联系厂家；
 * </pre>
 */
public enum ExceptionEnum {
    //无网络
    NO_NETWORK(10000),
    //HTTP网络错误
    HTTP_ERROR(11000),

    //服务器返回：token失效
    REMOTE_SERVER_TOKEN(11001),
    //服务器返回：账号被禁
    REMOTE_SERVER_ACCOUNT_BANNED(11002),
    //服务器返回：已经注册
    REMOTE_SERVER_REGISTERED(11003),
    //服务器返回：账号登录冲突
    REMOTE_SERVER_ACCOUNT_CONFLICT(11004),
    //服务器返回：重新登陆
    REMOTE_SERVER_RELOGIN(11005),
    //服务器返回：验证码错误
    REMOTE_SERVER_AUTOCODE_ERROR(11006),
    //服务器返回：无该账号
    REMOTE_SERVER_ACCOUNT_ERROR(11007),
    //服务器返回：密码错误
    REMOTE_SERVER_PASSWORD_ERROR(11008),
    //服务器返回：无权限
    REMOTE_SERVER_NOACCESS(11009),
    //服务器返回：参数异常
    REMOTE_SERVER_PARAM_ERROR(11010),
    //服务器返回：空视图
    REMOTE_SERVER_EMPTY(11011),

    //连接网络错误
    NET_CONN_ERROR(12000),
    //网络超时
    TIME_OUT(13000),
    //解析响应数据错误
    PARSER_RESPON_DATA_ERROR(14000),
    //请求数据错误
    REQUEST_DATA_ERROR(15000),
    //未知错误
    UNKNOWN(16000);


    private final int code;
    private String desc;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ExceptionEnum(int code) {
        this.code = code;
    }

    ExceptionEnum(int code, String description) {
        this.code = code;
        this.desc = desc;
    }


    public int getCode() {
        return code;
    }

    public String getDescription() {
        return desc;
    }

    public static ExceptionEnum valueOf(int code) {
        switch (code) {
            case 10000:
                return ExceptionEnum.NO_NETWORK;
            case 11000:
                return ExceptionEnum.HTTP_ERROR;
            case 11001:
                return ExceptionEnum.REMOTE_SERVER_TOKEN;
            case 11002:
                return ExceptionEnum.REMOTE_SERVER_ACCOUNT_BANNED;
            case 11003:
                return ExceptionEnum.REMOTE_SERVER_REGISTERED;
            case 11004:
                return ExceptionEnum.REMOTE_SERVER_ACCOUNT_CONFLICT;
            case 11005:
                return ExceptionEnum.REMOTE_SERVER_RELOGIN;
            case 11006:
                return ExceptionEnum.REMOTE_SERVER_AUTOCODE_ERROR;
            case 11007:
                return ExceptionEnum.REMOTE_SERVER_ACCOUNT_ERROR;
            case 11008:
                return ExceptionEnum.REMOTE_SERVER_PASSWORD_ERROR;
            case 11009:
                return ExceptionEnum.REMOTE_SERVER_NOACCESS;
            case 11010:
                return ExceptionEnum.REMOTE_SERVER_PARAM_ERROR;
            case 11011:
                return ExceptionEnum.REMOTE_SERVER_EMPTY;
            case 12000:
                return ExceptionEnum.NET_CONN_ERROR;
            case 13000:
                return ExceptionEnum.TIME_OUT;
            case 14000:
                return ExceptionEnum.PARSER_RESPON_DATA_ERROR;
            case 15000:
                return ExceptionEnum.REQUEST_DATA_ERROR;
            case 16000:
            default:
                return ExceptionEnum.UNKNOWN;
        }
    }

}
