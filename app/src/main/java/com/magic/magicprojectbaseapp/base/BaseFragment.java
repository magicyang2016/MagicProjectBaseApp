package com.magic.magicprojectbaseapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gyf.immersionbar.ImmersionBar;
import com.magic.magicprojectbaseapp.core.http.api.MallRequest;
import com.magic.magicprojectbaseapp.mvp.presenter.BasePresenter;
import com.magic.magicprojectbaseapp.utils.LogUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * Created  by
 * Viewpager + Fragment情况下，fragment的生命周期因Viewpager的缓存机制而失去了具体意义
 * 该抽象类自定义一个新的回调方法，当fragment可见状态改变时会触发的回调方法，介绍看下面
 */
public abstract class BaseFragment<p extends BasePresenter> extends RxFragment {
    public p mPresener;
    protected Unbinder unBinder;
    public View mView;
    public Context mContext;
    private int layoutID;
    public LayoutInflater mInflater;
    public Bundle mSavedInstanceState;
    public MallRequest mRequestClient;
    protected ImmersionBar mImmersionBar;
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    public boolean isFragmentVisible;
    public boolean isFirst = true;//是否第一次显示

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;
        onFragmentVisibleChange(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mSavedInstanceState = savedInstanceState;
        mInflater = inflater;
        layoutID = initCreatView();
        mView = inflater.inflate(layoutID, container, false);
        hasCreateView = true;
        mContext = getActivity();
        unBinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        mPresener = initPresener();
        hasCreateView = true;
        if (isImmersionBarEnabled()){
            initImmersionBar();
        }
        initData();
        onFragmentVisibleChange(0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }


    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    /**
     * 是否在状态栏字体为黑色
     *
     * @return the boolean
     */
    protected boolean isStatusBarBlack() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        if(isStatusBarBlack()){
            if (ImmersionBar.isSupportStatusBarDarkFont()){
//                mImmersionBar.statusBarDarkFont(true).init();
            }
        }
    }


    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     */
    protected void onFragmentVisibleChange(int mType) {
        if(isFirst && isFragmentVisible && hasCreateView){
            isFirst = false;
            lazyLoad();
        }else if(!isFirst && isFragmentVisible && hasCreateView){

        } else {

        }
    }



    protected abstract p initPresener();
    public abstract void initTheme();
    public abstract int initCreatView();

    public abstract void initViews();

    public abstract void initData();
    /**
     * 延迟加载
     * <p>
     * <p>
     * 子类必须重写此方法
     */

    protected abstract void lazyLoad();
    /**
     *
     * 非第一次可见的时候调用的函数
     */
    protected  void freshLoad(){}
}
