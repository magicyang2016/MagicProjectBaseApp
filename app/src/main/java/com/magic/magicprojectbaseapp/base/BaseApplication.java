package com.magic.magicprojectbaseapp.base;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.magic.magicprojectbaseapp.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :Application
 * </pre>
 */
public class BaseApplication extends MultiDexApplication {
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器，下拉数显，上拉加载
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();


        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }

        initTimber();
//        initSkin();
//        setupDataBase(getApplicationContext());
        initLeakcanary();
//        initUM();
//        initTangram(this);
        //对单位的自定义配置, 请在 App 启动时完成
        configUnits();
//        initParallaxHelper();
//        initSpUtils();
        initLogger();
    }


    protected void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)       // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // 实现打印策略接口，默认用logcat打印，可以实现LogStrategy其中的抽象函数，定义数据的打印输出
                .tag("(tag)学习笔记===》")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        //debug模式关闭打印,发布本开启日志打印，杨实测确实实现了日志的开关
        //下面语句应用上面的日志配置选项和实现debug和发布版本的打印控制开关
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return com.magic.magicprojectbaseapp.BuildConfig.DEBUG;
            }
        });
    }

    /**
     * Timer初始化
     */
    private void initTimber(){
        //设置log自动在apk为debug版本时打开，在release版本时关闭
//        TimberUtil.setLogAuto();
        //也可以设置log一直开
        //TimberUtil.setLogDebug();
    }
//    private void initUM(){
//        UMConfigure.init(this,"5baddb35f1f556a7a60001ae"
//                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
//
//        PlatformConfig.setWeixin("wx116bb648d30156d2", "81507aae7ddf0002ef94fe43052ee33a");
//        PlatformConfig.setQQZone("1105176662", "4123w3dnR1B7Am9i");
//    }

    /**
     * 初始化侧滑返回
     */
//    private void initParallaxHelper(){
//        registerActivityLifecycleCallbacks(ParallaxHelper.getInstance());
//    }

    /**
     * 初始化"淘宝"布局框架
     */
//    private void initTangram(Context context){
//        TangramBuilder.init(context, new IInnerImageSetter() {
//            @Override
//            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view,
//                                                                 @Nullable String url) {
//                //假设你使用 Picasso 加载图片
//                Glide.with(context).load(url).into(view);
//            }
//        }, ImageView.class);
//    }

    /**
     * 管理内存
     */
    private void initLeakcanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // -This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 初始化换肤控件
     */
//    private void initSkin(){
//        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
//                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
//                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
//                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
//                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
//                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
//                .loadSkin();
//    }


    /**
     * init GreenDao
     */
//    private void setupDataBase(Context context){
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, Constance.DATA_BASE_NAME);
//        Database db = openHelper.getWritableDb();
//        DaoMaster daoMaster = new DaoMaster(db);
//        mDaoSession = daoMaster.newSession();
//    }

    /**
     * 返回Dao
     * @return
     */
//    public static DaoSession getDaoSession(){
//        return mDaoSession;
//    }


    private void configUnits() {
        /**
         * 以下是 AndroidAutoSize 可以自定义的参数, {@link AutoSizeConfig} 的每个方法的注释都写的很详细
         * 使用前请一定记得跳进源码，查看方法的注释, 下面的注释只是简单描述!!!
         */
//        AutoSizeConfig.getInstance()

                //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
                //如果没有这个需求建议不开启
//                .setCustomFragment(true)

        //是否打印 AutoSize 的内部日志, 默认为 true, 如果您不想 AutoSize 打印日志, 则请设置为 false
//                .setLog(false)

        //是否使用设备的实际尺寸做适配, 默认为 false, 如果设置为 false, 在以屏幕高度为基准进行适配时
        //AutoSize 会将屏幕总高度减去状态栏高度来做适配, 如果设备上有导航栏还会减去导航栏的高度
        //设置为 true 则使用设备的实际屏幕高度, 不会减去状态栏以及导航栏高度
//                .setUseDeviceSize(true)

        //是否全局按照宽度进行等比例适配, 默认为 true, 如果设置为 false, AutoSize 会全局按照高度进行适配
//                .setBaseOnWidth(false)

        //设置屏幕适配逻辑策略类, 一般不用设置, 使用框架默认的就好
//                .setAutoAdaptStrategy(new AutoAdaptStrategy())
        ;
        //当 App 中出现多进程, 并且您需要适配所有的进程, 就需要在 App 初始化时调用 initCompatMultiProcess()
        //在 Demo 中跳转的三方库中的 DefaultErrorActivity 就是在另外一个进程中, 所以要想适配这个 Activity 就需要调用 initCompatMultiProcess()
//        AutoSize.initCompatMultiProcess(this);
    }

//    private void initSpUtils(){
//        SPUtils.getInstance(getApplicationContext(),Constance.SP);
//    }

    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(base);
        android.support.multidex.MultiDex.install(this);
    }
}
