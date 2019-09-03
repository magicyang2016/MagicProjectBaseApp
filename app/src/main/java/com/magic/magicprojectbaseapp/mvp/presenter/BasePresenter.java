package com.magic.magicprojectbaseapp.mvp.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.magic.magicprojectbaseapp.base.BaseActivity;
import com.magic.magicprojectbaseapp.base.BaseFragment;
import com.magic.magicprojectbaseapp.base.BaseFragmentActivity;
import com.magic.magicprojectbaseapp.mvp.model.callback.LifeCycleListener;

import java.lang.ref.WeakReference;


/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :BasePresenter 基础类
 * </pre>
 */
public class BasePresenter<V, T> implements LifeCycleListener {

//    protected Reference<T> mActivityRef;
    //弱引用
    public WeakReference<V> weakReferenceView;
    protected V mView;


    public WeakReference<T> weakReferenceActivity;
    protected T mActivity;

    public BasePresenter(V view, T activity) {
        attachView(view);
        attachActivity(activity);
        setListener(activity);
    }



    /**
     * 设置生命周期监听
     *
     * @author ZhongDaFeng
     */
    private void setListener(T activity) {
        if (getActivity() != null) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) getActivity()).setOnLifeCycleListener(this);
            }
            else if (activity instanceof BaseFragmentActivity) {
                ((BaseFragmentActivity) getActivity()).setOnLifeCycleListener(this);
            } else if (activity instanceof BaseFragment) {
//                ((BaseFragment) activity).setOnLifeCycleListener(this);
            }
        }
    }


    //关联View
    public void attachView(V view){
        weakReferenceView = new WeakReference<V>(view);
        mView = weakReferenceView.get();
    }

    /**
     * 关联Activity
     *
     * @param activity
     */
    private void attachActivity(T activity) {
        weakReferenceActivity = new WeakReference<T>(activity);
        mActivity = weakReferenceActivity.get();
    }


    //解除关联
    public void dettach(){
        if(isViewAttached()){
            weakReferenceView.clear();
        }
        if(isActivityAttached()){
            weakReferenceActivity.clear();
        }
    }

    /**
     * 销毁
     */
    public void detachView() {
        if (isViewAttached()) {
            weakReferenceView.clear();
            weakReferenceView = null;
        }
    }

    /**
     * 销毁
     */
    private void detachActivity() {
        if (isActivityAttached()) {
            weakReferenceActivity.clear();
            weakReferenceActivity = null;
        }
    }


    public V getView(){
        if (weakReferenceView == null) {
            return null;
        }
        return weakReferenceView.get();
    }


    /**
     * 获取
     *
     * @return
     */
    public T getActivity() {
        if (weakReferenceActivity == null) {
            return null;
        }
        return weakReferenceActivity.get();
    }

    /**
     * 是否已经关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return weakReferenceView != null && weakReferenceView.get() != null;
    }
    /**
     * 是否已经关联
     *
     * @return
     */
    public boolean isActivityAttached() {
        return weakReferenceActivity != null && weakReferenceActivity.get() != null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        detachView();
        detachActivity();
    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {

    }

    @Override
    public void onActivityCreated(Bundle bundle) {

    }

    @Override
    public void onDestroyView() {
        detachView();
        detachActivity();
    }

    @Override
    public void onDetach() {

    }
}
