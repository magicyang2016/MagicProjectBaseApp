package com.magic.magicprojectbaseapp.utils;

import com.magic.magicprojectbaseapp.BuildConfig;

import timber.log.Timber;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/09
 *     desc   :log日志打印工具类
 * </pre>
 */
public class LogUtils {
    private static final String TAG = "YYZH";
    private static boolean allowD = true;
    private static boolean allowE = true;
    private static boolean allowI = true;
    private static boolean allowV = true;
    private static boolean allowW = true;

    static {
            allowD = allowE = allowI = allowV = allowW = BuildConfig.LOG_DEBUG;
    }

    private LogUtils() {
    }

    /**
     * 开启Log
     *
     * @author ZhongDaFeng
     */
    public static void openLog(boolean flag) {
        allowD = flag;
        allowE = flag;
        allowI = flag;
        allowV = flag;
        allowW = flag;
    }

    public static void d(String content) {
        if (!allowD) {
            return;
        }
        Timber.d(content);
    }

    public static void e(String content) {
        if (!allowE)
            return;
        Timber.e(content);
    }
    public static void e(Exception content) {
        if (!allowE)
            return;
        Timber.e(content);
    }

    public static void i(String content) {
        if (!allowI)
            return;
        Timber.i(content);
    }

    public static void v(String content) {
        if (!allowV)
            return;
        Timber.v(content);
    }

    public static void w(String content) {
        if (!allowW)
            return;
        Timber.w(content);
    }

}
