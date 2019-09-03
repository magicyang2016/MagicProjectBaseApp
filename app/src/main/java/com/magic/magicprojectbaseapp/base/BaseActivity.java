package com.magic.magicprojectbaseapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;


import com.gyf.immersionbar.ImmersionBar;
import com.magic.magicprojectbaseapp.core.http.converter.RxApiManager;
import com.magic.magicprojectbaseapp.mvp.model.callback.LifeCycleListener;
import com.magic.magicprojectbaseapp.mvp.presenter.BasePresenter;
import com.magic.magicprojectbaseapp.utils.ActivityStackManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : yang
 *     time   : 2019/06/05
 *     desc   :BaseActivity
 * </pre>
 */
public abstract class BaseActivity<p extends BasePresenter> extends RxAppCompatActivity implements View.OnClickListener{

    public p mPresener;
//    public Toolbar mToolbar;
    public boolean isSHOW;//记录本Activity是否可视区域
    public LifeCycleListener mListener;
    protected Unbinder unBinder;
    protected Context mContext;
    protected ImmersionBar mImmersionBar;

    protected  ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(this);

        progressDialog.setTitle("加载");
        progressDialog.setMessage("加载中。。。");

        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        ActivityStackManager.getManager().push(this);
        mPresener = initPresener();
        //把所有继承此类的Activity都绑定到这里了，这样View就和Present联系起来了。
        if(mPresener !=null) {
            mPresener.attachView(this);
        }

        initContentView(savedInstanceState);

//        //初始化沉浸式
//        if (isImmersionBarEnabled()){
//            initImmersionBar();
//        }

        mContext = this;
        unBinder = ButterKnife.bind(this);

        initBundleData();

        isSHOW = true;
//        mToolbar = findViewById(R.id.toolbar);
//        if(mToolbar != null) {
//            //mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black));
//            toolbarInIt();
//        }

        initViews();
        initToolBar();
//        if(mToolbar != null) {
//            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
        initData();
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
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        if (ImmersionBar.isSupportStatusBarDarkFont()){
            //mImmersionBar.statusBarDarkFont(true).init();
        }
    }
    /**
     * 设置Title
     */
    private void toolbarInIt(){
//        if(mToolbar != null) {
//            TextView tvTitle = findViewById(R.id.toolbar_title);
//            if(tvTitle != null){
//                initToolBar(tvTitle);
//            }
//        }
    }


    /**
     * 设置生命周期回调函数
     *
     * @param listener
     */
    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }


    @Override
    public void onClick(View view) {
    }





    @Override
    protected void onStart() {
        super.onStart();
        isSHOW = true;
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
        isSHOW = false;
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isSHOW = false;
        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isSHOW = false;
        EventBus.getDefault().unregister(this);
//        overridePendingTransition(R.anim.anim_none, R.anim.trans_center_2_right);
        if (mListener != null) {
            mListener.onDestroy();
        }


        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }

        //删除Activity
        ActivityStackManager.getManager().remove(this);


        //解除View关联
        if(mPresener != null) {
            mPresener.detachView();
        }
        //View消除时取消订阅关系
        RxApiManager.getInstance().cancelall();
    }

    // 初始化UI，setContentView
    protected abstract p initPresener();
    protected abstract void initContentView(Bundle savedInstanceState);
    protected abstract void initToolBar();
    public abstract void initViews();
    public abstract void initData();

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initBundleData();

}
