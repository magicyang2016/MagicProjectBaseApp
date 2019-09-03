package com.magic.magicprojectbaseapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magic.magicprojectbaseapp.R;
import com.magic.magicprojectbaseapp.utils.LogUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    Unbinder unbinder;
    private  String mParam1;


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

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     */
    protected void onFragmentVisibleChange(int mType) {
        Logger.e( mParam1 +"：isVisibleToUser=" + isFragmentVisible+" hasCreateView="+hasCreateView+" isFirst="+isFirst);
        if(isFirst && isFragmentVisible && hasCreateView){
            isFirst = false;
            lazyLoad();
        }else if(!isFirst && isFragmentVisible && hasCreateView){
            freshLoad();
        } else {

        }
    }
    protected  void lazyLoad(){
        Logger.e(mParam1 +":懒加载lazyLoad！！！！！！");

    }
    protected  void freshLoad(){
        Logger.e(mParam1 +":可见就刷新freshLoad！！！！");

    }
    public TestFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        Logger.e(mParam1 + ":onAttach");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

            Logger.e(mParam1 + "：Fragment！！onSaveInstanceState() ，保存信息！！！");

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            Logger.e(mParam1 + "：Fragment！！onCreate() .savedInstanceState不为null，是异常销毁后的重建");
        }
        Logger.e(mParam1 + ":onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            Logger.e(mParam1 + "：Fragment！！onViewStateRestored() .savedInstanceState不为null，是异常销毁后的重建");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.e(mParam1 + ":onCreateView");
        Log.e(mParam1,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitle.setText(mParam1);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.e(mParam1 + ":onViewCreated");
        hasCreateView=true;
        onFragmentVisibleChange(0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e(mParam1 + ":onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e(mParam1 + ":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e(mParam1 + ":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e(mParam1 + ":onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.e(mParam1 + ":onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e(mParam1 + ":onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e(mParam1 + ":onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e(mParam1 + ":onDetach");

    }
}
