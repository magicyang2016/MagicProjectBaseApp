package com.magic.magicprojectbaseapp.core.http.converter;


import com.magic.magicprojectbaseapp.BuildConfig;
import com.magic.magicprojectbaseapp.core.http.converter.string.StringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author :
 *     time   : 2019/06/05
 *     desc   :请求体
 * </pre>
 */
public class ServiceGenerator {
    private volatile static ServiceGenerator retrofitManager;
    private Retrofit retrofit;

    //无参的单利模式
    public static ServiceGenerator getSingleton() {
        if (retrofitManager == null) {
            synchronized (ServiceGenerator.class) {
                if (retrofitManager == null) {
                    retrofitManager = new ServiceGenerator();
                }
            }
        }
        return retrofitManager;
    }


//    设置默认的url地址
    public static final String BASE_API = BuildConfig.API_HOST;//http://apicloud.mob.com/

    public static OkHttpClient sOkHttpClient = new OkHttpClient.Builder().
    connectTimeout(60, TimeUnit.SECONDS).
    readTimeout(60, TimeUnit.SECONDS).
    writeTimeout(60, TimeUnit.SECONDS).build();

    public static Retrofit.Builder mBuilder = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit.Builder mStringBuilder = new Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(StringConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit =mBuilder
                .baseUrl(BASE_API)
                .client(sOkHttpClient)
                .build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService2(Class<S> serviceClass) {
        Retrofit retrofit = mStringBuilder.client(sOkHttpClient).build();
        return retrofit.create(serviceClass);
    }
}
