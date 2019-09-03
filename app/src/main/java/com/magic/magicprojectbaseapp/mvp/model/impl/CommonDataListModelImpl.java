package com.magic.magicprojectbaseapp.mvp.model.impl;



import com.magic.magicprojectbaseapp.core.Constance;
import com.magic.magicprojectbaseapp.core.http.api.MallRequest;
import com.magic.magicprojectbaseapp.core.http.converter.ServiceGenerator;
import com.magic.magicprojectbaseapp.mvp.entity.LoginBean;
import com.magic.magicprojectbaseapp.mvp.entity.StudentBean;
import com.magic.magicprojectbaseapp.mvp.model.DefaultModel;
import com.magic.magicprojectbaseapp.mvp.model.callback.Observer;
import com.magic.magicprojectbaseapp.utils.LogUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :登录Model
 * </pre>
 */
public class CommonDataListModelImpl implements DefaultModel<StudentBean> {
    private MallRequest mClient;
    public CommonDataListModelImpl() {
        mClient = ServiceGenerator.createService(MallRequest.class);

    }

    //单例
    public static CommonDataListModelImpl getInstance() {
        return CommonDataListModelImpl.SingletonHolder.instance;
    }


    private static class SingletonHolder {
        public static final CommonDataListModelImpl instance = new CommonDataListModelImpl();
    }

    @Override
    public void load(Map<String, Object> params, LifecycleProvider lifecycle, Observer<StudentBean> observer) {
        Observable<StudentBean> ob = mClient
                .getStudent(params);

        ob.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }


}
