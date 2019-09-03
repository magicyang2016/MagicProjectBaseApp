package com.magic.magicprojectbaseapp.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.magic.magicprojectbaseapp.core.http.converter.RxApiManager;
import com.magic.magicprojectbaseapp.mvp.entity.eventbean.NetWorkEvent;
import com.magic.magicprojectbaseapp.mvp.model.callback.LifeCycleListener;
import com.magic.magicprojectbaseapp.mvp.presenter.BasePresenter;
import com.magic.magicprojectbaseapp.utils.ActivityStackManager;
import com.magic.magicprojectbaseapp.utils.NetWorkUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :BaseFragmentActivity
 * </pre>
 */
public abstract class BaseFragmentActivity<p extends BasePresenter> extends RxAppCompatActivity implements View.OnClickListener{
    public p mPresener;
    public LifeCycleListener mListener;
    private BroadcastReceiver netStateReceiver;
    protected Unbinder unBinder;
    protected ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        ActivityStackManager.getManager().push(this);
        mPresener = initPresener();
        //把所有继承此类的Activity都绑定到这里了，这样View就和Present联系起来了。
        if(mPresener != null) {
            mPresener.attachView(this);
        }
        initContentView(savedInstanceState);
        unBinder = ButterKnife.bind(this);

        EventBus.getDefault().register(this);
        //初始化沉浸式
        if (isImmersionBarEnabled()){
            initImmersionBar();
        }

//      initNetWork();
        initViews();
        initData();

    }




    protected abstract p initPresener();
    // 初始化UI，setContentView
    protected abstract void initContentView(Bundle savedInstanceState);
    public abstract void initViews();
    public abstract void initData();

    /**
     * 设置生命周期回调函数
     *
     * @param listener
     */
    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }


    public void initNetWork(){
        netStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(
                        ConnectivityManager.CONNECTIVITY_ACTION)) {
                    if (NetWorkUtil.isConnected()) {
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.AVAILABLE));
                    } else {
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.UNAVAILABLE));
                    }
                }
            }
        };

        registerReceiver(netStateReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));
    }
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        if (ImmersionBar.isSupportStatusBarDarkFont()){
            //mImmersionBar.statusBarDarkFont(true).init();
        }
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    @Subscribe
    public void onEvent(NetWorkEvent event) {
        if (event.getType() == NetWorkEvent.UNAVAILABLE) {
          //  ToastUtil3.showToast(BaseFragmentActivity.this,R.string.no_network);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unBinder != null) {
            unBinder.unbind();
        }
        if (mListener != null) {
            mListener.onDestroy();
        }
        //删除Activity
        ActivityStackManager.getManager().remove(this);


        //解除View关联
        if(mPresener != null) {
            mPresener.detachView();
        }
        //View消除时取消订阅关系
        RxApiManager.getInstance().cancelall();
        EventBus.getDefault().unregister(this);
//        unregisterReceiver(netStateReceiver);
        //移除view绑定
//        if (mImmersionBar != null)
//            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

}
