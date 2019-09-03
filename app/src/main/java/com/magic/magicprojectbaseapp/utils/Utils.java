package com.magic.magicprojectbaseapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/11
 *     desc   :
 * </pre>
 */
public final class Utils {
   /* @SuppressLint("StaticFieldLeak")
    private static Application sApp;*/
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;
    private static Handler sHandler;
    private static Application sApplication;

    private Utils() {
        throw new UnsupportedOperationException("error init "+"（Utils）...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Application context) {
        Utils.sApplication = context;
        Utils.sContext = context.getApplicationContext();
        Utils.sHandler = new Handler(Looper.getMainLooper());


        sApplication.registerActivityLifecycleCallbacks(mCallbacks);
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getAppContext() {
        if (sContext != null) return sContext;
        throw new NullPointerException("null "+"(Utils)sContext");
    }

    public static Handler getHandler() {
        if (sHandler != null) return sHandler;
        throw new NullPointerException("null "+"(Utils)sHandler");
    }

    /**
     * 获取 Application
     *
     * @return Application
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        throw new NullPointerException("u should init first");
    }

    public static void startAct(Class toclass) {
        Intent intent = new Intent(sContext, toclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        sContext.startActivity(intent);
    }

    static WeakReference<Activity> sTopActivityWeakRef;
    static List<Activity> sActivityList = new LinkedList<>();

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            sActivityList.add(activity);
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };

    private static void setTopActivityWeakRef(Activity activity) {
        if (sTopActivityWeakRef == null || !activity.equals(sTopActivityWeakRef.get())) {
            sTopActivityWeakRef = new WeakReference<>(activity);
        }
    }

    /**
     * 判断App是否是Debug版本
     */
    public static boolean isDebug() {
        if (TextUtils.isEmpty(sContext.getPackageName())) return false;
        try {
            PackageManager pm = sContext.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(sContext.getPackageName(), 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

}
