package com.magic.magicprojectbaseapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :RecyclerViewHolder
 * </pre>
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    Context mContext;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();

    }
    /**
     * 获取 itemView
     */
    public View getItemView() {
        return itemView;
    }
    /**
     * 关联id
     */
    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    /**
     * 获取 View
     */
    public View getView(int viewId) {
        return findViewById(viewId);
    }
    /**
     * 获取 TextView
     */
    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }
    /**
     * 获取 Button
     */
    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }
    /**
     * 获取 ImageView
     */
    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }
    /**
     * 获取 ImageButton
     */
    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }
    /**
     * 获取 EditText
     */
    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }
    /**
     * 设置TextView值
     */
    public RecyclerViewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }
    /**
     * 设置Text颜色
     */
    public RecyclerViewHolder setTextColor(int viewId, int color) {
        TextView view = findViewById(viewId);
        view.setTextColor(color);
        return this;
    }

    /**
     * 设置背景
     */
    public RecyclerViewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }
    /**
     * 设置点击事件
     */
    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    /**
     * 设置图片资源
     */
    public RecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView view = findViewById(viewId);
        view.setImageResource(resId);
        return this;
    }
}
