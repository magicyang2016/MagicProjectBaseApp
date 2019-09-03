package com.magic.magicprojectbaseapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/09
 *     desc   :自定义弹出框工具类
 * </pre>
 */
public class AlertDialogUtils {
    public static AlertDialog.Builder localBuilder;
    private static Context mContext;

    /**
     * 展示对话框
     */
    public static void showDialog(String title, String message, String bt1, String bt2, final DialogInterface.OnClickListener click1, final DialogInterface.OnClickListener click2) {
        try {

            if (localBuilder == null || mContext != ActivityStackManager.getManager().peek()) {
                mContext  = ActivityStackManager.getManager().peek();
                localBuilder = new AlertDialog.Builder(mContext);
                LogUtils.v("新建AlertDialog");

            }
            if(!StringUtils.isEmpty(title)) {
                localBuilder.setTitle(title);
            }else{
                localBuilder.setTitle(null);
            }
            if(!StringUtils.isEmpty(message)) {
                localBuilder.setMessage(message);
            }else{
                localBuilder.setMessage(null);
            }
            if (!StringUtils.isEmpty(bt1)) {
                localBuilder.setPositiveButton(bt1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        paramAnonymousDialogInterface.dismiss();
                        if (click1 != null) {
                            click1.onClick(paramAnonymousDialogInterface, paramAnonymousInt);
                        }
                    }
                });
            }else{
                localBuilder.setPositiveButton(null,null);
            }

            if (!StringUtils.isEmpty(bt2)) {
                localBuilder.setNegativeButton(bt2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        paramAnonymousDialogInterface.dismiss();
                        if (click2 != null) {
                            click2.onClick(paramAnonymousDialogInterface, paramAnonymousInt);
                        }
                    }
                });
            }else{
                localBuilder.setNegativeButton(null,null);
            }

            localBuilder.setCancelable(true);
            localBuilder.create();
            localBuilder.show();

        } catch (Exception e) {
            LogUtils.e(e);
        }

    }


}
