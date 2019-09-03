package com.magic.magicprojectbaseapp.utils.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.magic.magicprojectbaseapp.R;
import com.magic.magicprojectbaseapp.utils.ActivityStackManager;
import com.magic.magicprojectbaseapp.utils.LogUtils;


/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/11
 *     desc   :加载等待...类
 *
 * </pre>
 */
public class WaitingDialog {

    public static Dialog loadingDialog;
    public static View v;
    public static Context mContext;


    public interface onMyDismissListener {
        void onDismiss();
    }
    /**
     *加载布局控件
     */
    public static Dialog createLoadingDialog(final onMyDismissListener onMyDismissListener) {
        try {
            //updated
            if (v == null || mContext != ActivityStackManager.getManager().peek()) {
                //updated
                mContext = ActivityStackManager.getManager().peek();
                LayoutInflater inflater = LayoutInflater.from(mContext);
                v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
                LinearLayout layout = v.findViewById(R.id.dialog_loading_view);// 加载布局
                loadingDialog = new Dialog(mContext, R.style.MyDialogStyle);// 创建自定义样式dialog
                loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
                loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
                loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

                /**
                 *将显示Dialog的方法封装在这里面
                 */
                Window window = loadingDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setGravity(Gravity.CENTER);
                window.setAttributes(lp);
                window.setWindowAnimations(R.style.PopWindowAnimStyle);
                v.setTag(loadingDialog);
            } else {
                loadingDialog = (Dialog) v.getTag();
            }
            loadingDialog.show();



            loadingDialog.setOnDismissListener(dialogInterface -> {
                if(onMyDismissListener != null){
                    onMyDismissListener.onDismiss();
                }
            });



        }catch (Exception e){
            LogUtils.e("展示加载Dialog异常"+e.toString());
            e.printStackTrace();
        }

        return loadingDialog;
    }

    /**
     * 关闭dialog
     *
     */
    public static void closeDialog() {
        try {
            if (v != null && loadingDialog != null) {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        }catch (Exception e){
            LogUtils.e("关闭加载Dialog异常"+e.toString());
            e.printStackTrace();
        }
    }

}