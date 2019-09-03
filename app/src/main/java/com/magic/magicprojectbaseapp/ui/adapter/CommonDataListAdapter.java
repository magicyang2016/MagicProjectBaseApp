package com.magic.magicprojectbaseapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.magic.magicprojectbaseapp.R;
import com.magic.magicprojectbaseapp.base.BaseRecyclerAdapter;
import com.magic.magicprojectbaseapp.base.RecyclerViewHolder;
import com.magic.magicprojectbaseapp.mvp.entity.StudentBean;

import java.util.List;


/**
 * Created by yangshoushan on 2019/6/6.
 * 拨号界面,查询列表适配器
 */

public class CommonDataListAdapter extends BaseRecyclerAdapter<StudentBean.Student> {

    private Context mContext;

    public CommonDataListAdapter(Context ctx, List<StudentBean.Student> list) {
        super(ctx, list);
        mContext = ctx;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_common_data_list;
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, int position, final StudentBean.Student item) {

            holder.setText(R.id.tvId,item.getId()+"")
                    .setText(R.id.tvAge, item.getAge()+"")
                    .setText(R.id.tvName,item.getName());


    }
}
