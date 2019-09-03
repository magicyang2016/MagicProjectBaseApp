package com.magic.magicprojectbaseapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;
import com.magic.magicprojectbaseapp.R;
import com.magic.magicprojectbaseapp.base.BaseActivity;
import com.magic.magicprojectbaseapp.mvp.entity.StudentBean;
import com.magic.magicprojectbaseapp.mvp.presenter.impl.CommonDataListPresenterImpl;
import com.magic.magicprojectbaseapp.mvp.view.ICommonDataListView;
import com.magic.magicprojectbaseapp.ui.adapter.CommonDataListAdapter;
import com.magic.magicprojectbaseapp.utils.ToastUtil;
import com.magic.magicprojectbaseapp.utils.ToolUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommonDataListActivity extends BaseActivity<CommonDataListPresenterImpl> implements ICommonDataListView, OnRefreshLoadMoreListener {

    @BindView(R.id.rvListData)
    RecyclerView rvListData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    CommonDataListAdapter adapter;
    List<StudentBean.Student> dataStudents=new ArrayList();

    int currentPage=1;
    int pageSize=10;
    boolean isRefresh=false;

    @Override
    protected CommonDataListPresenterImpl initPresener() {
        return new CommonDataListPresenterImpl(this, this);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_common_data_list);

    }

    @Override
    protected void initToolBar() {

    }

    @Override
    public void initViews() {
        setRefreshLayout();
    }

    @Override
    public void initData() {
        rvListData.setLayoutManager(new LinearLayoutManager(this));
        rvListData.setAdapter(adapter=new CommonDataListAdapter(this,dataStudents));
        getData();

        testCode();

    }

    private void testCode() {
        LruCache l;
        LinkedHashMap a;
        DiskLruCache mDiskCache = null;
        try {
            mDiskCache = DiskLruCache.open(getExternalCacheDir(),1,1,2000000);

        DiskLruCache.Editor editor = mDiskCache.edit("magic");
        OutputStream outputStream = editor.newOutputStream(0);// 0表示第一个缓存文件，不能超过valueCount
//        outputStream.write("");

        outputStream.close();

        editor.commit();

        mDiskCache.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    public void showDataList(StudentBean data) {
        if(currentPage==1)
        dataStudents.clear();
       if(!ToolUtil.isEmpty(data.getData())){
           dataStudents.addAll(data.getData());
       }
        adapter.notifyDataSetChanged();


    }

    @Override
    public void showLoading() {
        if(!isRefresh){
            progressDialog.show();
        }else {

        }



    }

    @Override
    public void closeLoading() {
        if(!isRefresh){
            progressDialog.dismiss();
        }else {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }

    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this,msg,2);

    }

    private void setRefreshLayout() {
//        设置 刷新监听
        refreshLayout.setOnRefreshLoadMoreListener(this);
//        允许上拉加载
        refreshLayout.setEnableLoadMore(true);
    }

    //    触发刷新操作
    @Override
    public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
        currentPage=1;
        isRefresh=true;
        getData();

    }

    //    触发加载更多的操作
    @Override
    public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
        currentPage++;
        isRefresh=true;
        getData();

    }

    private void getData() {
        HashMap map = new HashMap();
        map.put("id", "666");
        map.put("name", "小马666");
        map.put("pageNum", currentPage);
        map.put("pageSize", pageSize);
//                网络请求数据
        mPresener.load(map);
    }


}
