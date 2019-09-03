package com.magic.magicprojectbaseapp.mvp.model.impl;



import com.magic.magicprojectbaseapp.core.Constance;
import com.magic.magicprojectbaseapp.core.http.api.MallRequest;
import com.magic.magicprojectbaseapp.core.http.converter.ServiceGenerator;
import com.magic.magicprojectbaseapp.mvp.entity.LoginBean;
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
public class LoginModelImpl implements DefaultModel<LoginBean> {
    private MallRequest mClient;
    public LoginModelImpl() {
        mClient = ServiceGenerator.createService(MallRequest.class);

    }

    //单例
    public static LoginModelImpl getInstance() {
        return LoginModelImpl.SingletonHolder.instance;
    }


    private static class SingletonHolder {
        public static final LoginModelImpl instance = new LoginModelImpl();
    }

    @Override
    public void load(Map<String, Object> params, LifecycleProvider lifecycle, Observer<LoginBean> observer) {
        LogUtils.d("第二步");
         String uid = (String) params.get(Constance.LOGIN_NAME_MVP);
         String page = (String) params.get(Constance.LOGIN_PWD_MVP);
         String device = (String) params.get(Constance.DEVICE);

        Observable<LoginBean> ob = mClient
                .login(uid, page,device);
        ob.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }


}
