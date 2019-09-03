package com.magic.magicprojectbaseapp.core;

import android.content.Context;
import android.os.Environment;

import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;


public class Constance {
    //数据库名
    public static final String DATA_BASE_NAME = "";


    public static final int REQUEST_SUCCESS_CODE = 1;
    public static final int REQUEST_OTHER_CODE = 2;
    public static final int REQUEST_ERRO_CODE = 1;

    /**
     * 拍照相关
     */
    public static final int TAKE_PHOTO_RESULT = 13;//拍照返回
    public static final int TAKE_PHOTO_MAX = 3;//最多拍照


    /**
     * sp相关
     */
    public static final String SP = "sp";
    //记住密码
    public static final String ISR = "isremember";

    /**
     * 登录 用户信息
     */
    //用户id
    public static final String LOGIN_UID_MVP = "uid";
    //用户账号
    public static final String LOGIN_NAME_MVP = "name";
    //密码
    public static final String LOGIN_PWD_MVP = "pwd";
    //用户姓名
    public static final String USERNAME_MVP = "username";
    //用户角色id
    public static final String USERROLE_MVP = "roleid";
    //机构编码
    public static final String ORGID_MVP = "orgcode";
    //机构名称
    public static final String ORGNAME_MVP = "orgname";
    //是否录音
    public static final String IFRECORD_MVP = "ifrecord";

    //第一次加载
    public static final String ISFIRST = "isfirst";
    // 是否启动pstn服务
    public static final String ISSTARTPUSHSERVICE = "isStartPushService";
    //设备编码
    public static final String DEVICE = "device";
    //服务器地址
    public static final String Web_URL = "web_uri";//服务器地址1
    public static final String Web_URL2 = "web_uri";//服务器地址2
    public static final String TS_URL = "tuisong_uri";//推送地址
    public static final String APKDOWN_URL = "down_uri";//更新下载地址
    public static final String RECORDUP_URL = "recordomup_uri";//录音上传地址
    public static final String RECORDDOWN_URL = "recordomdown_uri";//录音下载地址

    //IP地址
    public static final String IPJSON1 = "IPString1";//以太网设置集合1
    public static final String IPJSON2 = "IPString2";//以太网设置集合2
    public static final String IPAdr= "ipaddress";//以太网设置集合2
    public static final String TMKP_FIRST_USE = "TMKP_FIRST_USE";//是否是第一次使用该软件
    public static final String ALL_URL_JSON_1 = "ALL_URL_JSON_1";//存储本地服务器地址到sp1
    public static final String ALL_URL_JSON_2 = "ALL_URL_JSON_2";//存储本地服务器地址到sp2

    //第一次加载条数
    public static final int  FIRSTNUM = 20;

    // 文件分隔符
    public static final String FILE_SEPARATOR = "/";

    // 外存sdcard存放路径
    public static final String FILE_PATH = Environment.getExternalStorageDirectory()
            + FILE_SEPARATOR
            +"bibleAsk" + FILE_SEPARATOR;
    public static final String FILENAME = "autoapk.apk";
    public static final String FILE_NAME = FILE_PATH + FILENAME;

    /**
     * 获取状态栏的高度
     * @return
     */
    public static int getStatusBarHeight(Context mContext){
        try
        {
            Class<?> c= Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x= Integer.parseInt(field.get(obj).toString());
            return  mContext.getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //推送状态
    public static int IDLE = 1001;
    public static int WAIT_RECONNECT = 1002;
    public static int CONNECTING = 1003;
    public static int LOGGING = 1004;
    public static int LOGGED = 1005;
    public static int STOP = 1006;

    public static String NO_ERROR = "NO_ERROR";
    public static String LOGIN_ERROR="LOGIN_ERROR";
    public static String LOGIN_CONFLICT="LOGIN_CONFLICT";

//    sp常量
    public static String MISS_CALL_COUNT="missCallCount";//未接电话数量
    public static String TONGHUASAVE = "TONGHUA_SAVE";//本地通话记录保存
    public static String LASTPHONE = "lastphone";//拨号界面保存前一次拨号号码
    public static String BHSOUND = "bhsound";//拨号界面保存前一次拨号号码
    public static String PATH = "path";       //录音文件名称
    public static String ZHINENGBOHAO = "zhinengbohao";
    public static String QUHAO = "quhao";
    public static String YIDIBOHAOQIANZHUI = "yidibohaoqianzhui";
    public static String WAIXIANQIANZHUI = "waixianqianzhui";
    public static String LAIDIANQIANZHUI = "laidianqianzhui";




    //本地客户联系人
    public static String LOCALKEHU = "LOCAL_KEHU";//本地客户保存

    //    intent 常量
    public static String PHONE = "phone";
    public static String TYPE = "type";

    public static String getMsgByCode(int Code){
         if(Code == 500){
             return "服务器异常";
         }else if(Code == 400){
             return "请求错误";
         }else if(Code == 401){
             return "登录过期，请重新登录";
         }else if(Code == 404){
             return "操作异常，请联系网络管理员";
         }
         return String.valueOf(Code);
     }

      public static String getMsgByException(Throwable t){
        if(t instanceof SocketTimeoutException){
            return "连接超时，请检查网络";
        }else if(t instanceof ConnectException){
            return "网络连接错误";
        }else if(t instanceof SocketException){
            return "请求被关闭";
        }else if(t.getMessage().toString().contains("Canceled")){
            return "请求被关闭";
        }else if(t.getMessage().toString().contains("401")){
            return "登录过期，请重新登录";
        }else if(t.getMessage().toString().contains("500")){
            return "系统错误，请联系系统管理员哦";
        }else if(t.getMessage().toString().contains("503")){
            return "系统异常，请退出后重试";
        } else if(t.getMessage().toString().contains("502")){
            return "系统重启中，请稍后重试";
        }

        return t.getMessage().toString();
    }



}
