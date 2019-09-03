package com.magic.magicprojectbaseapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.magic.magicprojectbaseapp.R;
import com.magic.magicprojectbaseapp.utils.helper.ProgressHelper;


/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/12
 *     desc   :对话框工具类==创建对话框的工具类，可以设置不同样式，和动画风格
 *      参考自：CBDialog
 * </pre>
 */
public class DialogBuilder {
    /**
     * 普通对话框
     */
    public static final int DIALOG_STYLE_NORMAL = R.layout.dialog_normal;
    /**
     * metrial进度条风格
     */
    public static final int DIALOG_STYLE_PROGRESS = R.layout.dialog_progress;

    /**
     * 自定义dialog 布局样式
     */
//    public static final int DIALOG_STYLE_CUSTOM = -1;
    /**
     * 缩放动画
     */
    public static final int DIALOG_ANIM_NORMAL = R.style.DialogAnimation;
    /**
     * 从下往上滑动动画
     */
    public static final int DIALOG_ANIM_SLID_BOTTOM = R.style.DialogAnimationSlidBottom;
    /**
     * 从上往下滑动动画
     */
    public static final int DIALOG_ANIM_SLID_TOP = R.style.DialogAnimationSlidTop;
    /**
     * 从右往左滑动动画
     */
    public static final int DIALOG_ANIM_SLID_RIGHT = R.style.DialogAnimationSlidRight;
    /**
     * 对话框宽度所占屏幕宽度的比例
     */
    public static final float WIDTHFACTOR = 0.75f;
    /**
     * 对话框透明比例
     */
    public static final float ALPHAFACTOR = 1.0f;
    /**
     * 对话框处于屏幕顶部位置
     */
    public static final int DIALOG_LOCATION_TOP = 12;
    /**
     * 对话框处于屏幕中间位置
     */
    public static final int DIALOG_LOCATION_CENTER = 10;
    /**
     * 对话框处于屏幕底部位置
     */
    public static final int DIALOG_LOCATION_BOTTOM = 11;
    /**
     * 消息位于对话框的位置 居左
     */
    public static final int MSG_LAYOUT_LEFT = 1;
    /**
     * 消息位于对话框的位置 居中
     */
    public static final int MSG_LAYOUT_CENTER = 0;
    /**
     * 当前使用的风格
     */
    private int DIALOG_STYLE_CURRENT = DIALOG_STYLE_NORMAL;
    /**
     * 上下文
     */
    private Context context;
    /**
     * Dialog对象
     */
    private Dialog dialog;
    /**
     * 右边（确定）按钮
     */
    private Button confrimBtn;
    /*
     * confrim btn background resID
     */
    private int confirmBtnBG;
    /**
     * 左边（取消）按钮
     */
    private Button cancelBtn;
    /*
    * cancel btn background resID
    */
    private int cancelBtnBG;
    /**
     * 按钮中间分隔线
     */
    private View verticleLine;
    /**
     * 按钮水平分隔线
     */
    private View horizatonalLine;
    /**
     * dialog 根布局
     */
    private View dialogRootLayout;
    /**
     * 消息框布局
     */
    ViewGroup msglayout;
    /**
     * 是否显示cancelbutton;
     */
    private boolean showCancelButton = false;
    /**
     * 是否点击对话框外面取消对话框
     */
    private boolean touchOutSideCancel = false;
    private String confirmBtnTX = "确定", cancleBtnTX = "取消";
    private onDialogbtnClickListener btnClickListener;
    /**
     * 是否显示顶部图标
     */
    private boolean showTopIcon = true;
    /**
     * 进度框样式用到的metrial风格
     */
    private ProgressHelper mProgressHelper;

    protected int count = -1;
    /**
     * 进度框超时时间倍率
     */
    private int outOfTime = 10;
    private onProgressOutTimeListener mProgressOutTimeListener;
    private OnConvertItemViewListener convertItemViewListener;
    private TextView dialogTitle, dialogMsg;
    // 弹出dialog时候是否要显示阴影
    private static boolean dimEnable = true;
    /**
     * 是否显示确认按钮
     */
    private boolean showConfirmBtn = true;
    /**
     * 进度条
     */
    private boolean progressTimeOutLimit = true;
    /**
     * 进度条颜色值数组 size =7
     */
    private int[] progressColors;

    /**
     * 构造器一 创建一个基本dialog
     *
     * @param context
     */
    public DialogBuilder(Context context) {
        this(context, DIALOG_STYLE_NORMAL);
    }

    // 重构构造函数 方便用户使用内部类监听器时使用

    /**
     * 构造器二
     *
     * @param context     上下文
     * @param layoutStyle 对话框风格
     */
    public DialogBuilder(Context context, int layoutStyle) {
        this(context, layoutStyle, false);
    }

    /**
     * 构造器三
     *
     * @param context
     * @param layoutStyle   布局样式
     * @param isSystemAlert 是否是系统弹框（service等地方用到系统级别不依赖activity）
     */
    public DialogBuilder(Context context, int layoutStyle,
                         boolean isSystemAlert) {
        this(context, layoutStyle, isSystemAlert, WIDTHFACTOR, ALPHAFACTOR, dimEnable);
    }

    /**
     * 构造器三
     *
     * @param context
     * @param layoutStyle   布局样式
     * @param isSystemAlert 是否是系统弹框（service等地方用到系统级别不依赖activity）
     */
    public DialogBuilder(Context context, int layoutStyle,
                         boolean isSystemAlert, boolean dimEnable) {
        this(context, layoutStyle, isSystemAlert, WIDTHFACTOR, ALPHAFACTOR, dimEnable);
    }

    /**
     * @param context
     * @param layoutStyle
     * @param widthcoefficient 宽度屏占比
     * @param dimEnable        是否显示阴影
     */
    public DialogBuilder(Context context, int layoutStyle,
                         float widthcoefficient, boolean dimEnable) {
        this(context, layoutStyle, false, widthcoefficient, ALPHAFACTOR, dimEnable);
    }

    /**
     * 构造器四
     *
     * @param context          上下文
     * @param layoutStyle      对话框布局样式
     * @param widthcoefficient 对话框宽度时占屏幕宽度的比重（0-1）
     */
    public DialogBuilder(Context context, int layoutStyle,
                         float widthcoefficient) {
        this(context, layoutStyle, false, widthcoefficient, ALPHAFACTOR, dimEnable);
    }

    /**
     * 对话框生成器五
     *
     * @param context          上下文
     * @param layoutStyle      样式
     * @param widthcoefficient 对话框宽度所占屏幕宽度的比重（0-1）
     * @param alpha            对话框透明度
     */
    public DialogBuilder(Context context, int layoutStyle,
                         float widthcoefficient, float alpha) {
        this(context, layoutStyle, false, widthcoefficient, alpha, dimEnable);
    }

    /**
     * 构造器
     *
     * @param context
     * @param layoutStyle      布局样式
     * @param isSystemAlert    是否是系统弹框（service等地方用到系统级别不依赖activity）
     * @param widthcoefficient 对话框宽度所占屏幕宽度的比重（0-1）
     * @param alpha            对话框透明度
     */
    public DialogBuilder(Context context, int layoutStyle,
                         boolean isSystemAlert, float widthcoefficient, float alpha, boolean dimEnable) {
        this.DIALOG_STYLE_CURRENT = layoutStyle;
        // theme 要传入一个样式去掉系统对话框的标题
        Dialog dialog = null;
        if (dimEnable) {
            dialog = new Dialog(context, R.style.Dialog);
        } else {
            dialog = new Dialog(context, R.style.DialogDim);
        }
        // 设置对话框风格
        dialog.setContentView(layoutStyle);
        Window window = dialog.getWindow();
        // 是否系统级弹框
        if (isSystemAlert) {
            //8.0新特性 悬浮窗权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            } else {
                window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }
             /*解决方案一： 6.0悬浮窗权限*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(context)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);
                }
            }
        }

        // 获取屏幕宽度
        DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenwidth = metrics.widthPixels;
        int width = 0;
        if (widthcoefficient > 0) {
            width = (int) (screenwidth * widthcoefficient);
        } else {
            width = (int) (screenwidth * WIDTHFACTOR);
        }
        // 设置对话框宽度
        window.getAttributes().width = width;

        // 设置透明
        WindowManager.LayoutParams lp = window.getAttributes();
        if (alpha > 0) {
            lp.alpha = 1.0f;
        } else {
            lp.alpha = ALPHAFACTOR;
        }
        window.setAttributes(lp);
        // 设置动画样式
        window.setWindowAnimations(DIALOG_ANIM_NORMAL);
        this.context = context;
        this.dialog = dialog;
        if (layoutStyle == DIALOG_STYLE_PROGRESS) {
            showConfirmButton(false);
            mProgressHelper = new ProgressHelper(context);
            mProgressHelper.setProgressWheel((ProgressWheel) getView(R.id.progressWheel));
        }
        dialogRootLayout = getView(R.id.dialog_root_layout);
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
    }

    /**
     * 创建对话框
     *
     * @return
     */
    public Dialog create() {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if (showConfirmBtn) {
            confrimBtn.setVisibility(View.VISIBLE);
        } else {
            confrimBtn.setVisibility(View.GONE);
            LinearLayout btnLayout = getView(R.id.dialog_btnlayout);
            if (btnLayout != null) {
                btnLayout.setVisibility(View.GONE);
            }
        }
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
        if (verticleLine == null) {
            verticleLine = getView(R.id.btn_line_verticle);
        }
        if (horizatonalLine == null) {
            horizatonalLine = getView(R.id.dialog_btn_line_horizontal);
        }
        // 判断是否需要创建取消按钮
        if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS) {
            if (confrimBtn != null) {
                confrimBtn.setBackgroundResource(confirmBtnBG > 0 ? confirmBtnBG : R.drawable.sel_btn_rip_gray);
            }
        } else {
            if (showCancelButton) {
                if (cancelBtn != null) {
                    cancelBtn.setVisibility(View.VISIBLE);
                }
                if (verticleLine != null) {
                    verticleLine.setVisibility(View.VISIBLE);
                }
                if (confrimBtn != null && cancelBtn != null) {
                    confrimBtn.setBackgroundResource(confirmBtnBG > 0 ? confirmBtnBG : R.drawable.sel_btn_rip_gray_right);
                }
            } else {
                if (confrimBtn != null) {
                    confrimBtn.setBackgroundResource(confirmBtnBG > 0 ? confirmBtnBG : R.drawable.sel_btn_rip_gray);
                }
            }
        }

        if (confrimBtn != null) {
            confrimBtn.setText(confirmBtnTX);
            if (btnClickListener == null) {
                confrimBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }
        if (cancelBtn != null) {
            cancelBtn.setText(cancleBtnTX);
            if (btnClickListener == null) {
                cancelBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }
        if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS) {
            dialog.setCanceledOnTouchOutside(false);
            new CountDownTimer(800 * 7 * outOfTime, 800) {
                public void onTick(long millisUntilFinished) {
                    if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS) {
                        count++;
                        if (progressColors != null) {
                            switch (count % 7) {
                                case 0:
                                    mProgressHelper.setBarColor(progressColors.length >= 1 ? progressColors[0] : ContextCompat.getColor(context, R.color.md_green_300));
                                    break;
                                case 1:
                                    mProgressHelper.setBarColor(progressColors.length >= 2 ? progressColors[1] : ContextCompat.getColor(context, R.color.md_teal_A400));
                                    break;
                                case 2:
                                    mProgressHelper.setBarColor(progressColors.length >= 3 ? progressColors[2] : ContextCompat.getColor(context, R.color.md_light_blue_A200));
                                    break;
                                case 3:
                                    mProgressHelper.setBarColor(progressColors.length >= 4 ? progressColors[3] : ContextCompat.getColor(context, R.color.md_light_blue_200));
                                    break;
                                case 4:
                                    mProgressHelper.setBarColor(progressColors.length >= 5 ? progressColors[4] : ContextCompat.getColor(context, R.color.md_cyan_900));
                                    break;
                                case 5:
                                    mProgressHelper.setBarColor(progressColors.length >= 6 ? progressColors[5] : ContextCompat.getColor(context, R.color.md_yellow_800));
                                    break;
                                case 6:
                                    mProgressHelper.setBarColor(progressColors.length >= 7 ? progressColors[6] : ContextCompat.getColor(context, R.color.md_blue_grey_300));
                                    break;
                            }
                        } else {
                            switch (count % 7) {
                                case 0:
                                    mProgressHelper.setBarColor(ContextCompat.getColor(context, R.color.md_green_300));
                                    break;
                                case 1:
                                    mProgressHelper.setBarColor(ContextCompat.getColor(context, R.color.md_teal_A400));
                                    break;
                                case 2:
                                    mProgressHelper.setBarColor(ContextCompat.getColor(context, R.color.md_light_blue_A200));
                                    break;
                                case 3:
                                    mProgressHelper.setBarColor(ContextCompat.getColor(context, R.color.md_light_blue_200));
                                    break;
                                case 4:
                                    mProgressHelper.setBarColor(ContextCompat.getColor(context, R.color.md_cyan_900));
                                    break;
                                case 5:
                                    mProgressHelper.setBarColor(ContextCompat.getColor(context, R.color.md_yellow_800));
                                    break;
                                case 6:
                                    mProgressHelper.setBarColor(ContextCompat.getColor(context, R.color.md_blue_grey_300));
                                    break;
                            }
                        }
                    }
                }

                public void onFinish() {
                    if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS) {
                        count = -1;
                        if (mProgressHelper.getProgressWheel() != null && progressTimeOutLimit) {
                            mProgressHelper.getProgressWheel().setVisibility(
                                    View.GONE);
                        }
                        if (horizatonalLine != null && progressTimeOutLimit) {
                            horizatonalLine.setVisibility(View.VISIBLE);
                        }
                    }


                    FrameLayout erroricon = getView(R.id.error_frame);
                    if (erroricon != null && progressTimeOutLimit) {
                        erroricon.setVisibility(View.VISIBLE);
                    }
                    if (confrimBtn != null && progressTimeOutLimit) {
                        confrimBtn.setVisibility(View.VISIBLE);
                    }
                    if (dialogMsg == null) {
                        dialogMsg = getView(R.id.dialog_message);
                    } else {
                        if (progressTimeOutLimit) {
                            dialogMsg.setText(R.string.progress_dialog_outtime_msg);
                        }
                    }
                    if (mProgressOutTimeListener != null) {
                        mProgressOutTimeListener.onProgressOutTime(dialog,
                                dialogMsg);
                    }
                }
            }.start();
        }
        if (context instanceof Activity) {
            dialog.setOwnerActivity((Activity) context);
        }
        return dialog;
    }


    /**
     * 设置进度条颜色数组
     *
     * @param colors
     * @return
     */
    public DialogBuilder setProgressStyleColorRes(int[] colors) {
        this.progressColors = colors;
        return this;
    }

    /**
     * set weather show cancel button, if true,the Dialog show two buttons
     *
     * @param showCancelButton
     * @return
     */
    public DialogBuilder showCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
        return this;
    }

    public DialogBuilder showConfirmButton(boolean showConfirmbtn) {
        this.showConfirmBtn = showConfirmbtn;
        return this;
    }

    /**
     * 设置对话框标题
     *
     * @param title
     * @return
     */
    public DialogBuilder setTitle(Object title) {

        dialogTitle = getView(R.id.dialog_title);
        if (dialogTitle != null) {
            if (title != null) {
                dialogTitle.setText(parseParam(title));
            } else {
                dialogTitle.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 给对话框设置动画
     *
     * @param resId
     */
    public DialogBuilder setDialogAnimation(int resId) {
        this.dialog.getWindow().setWindowAnimations(resId);
        return this;
    }

    /**
     * 设置对话框的位置
     *
     * @param location
     * @return
     */
    public DialogBuilder setDialoglocation(int location) {
        Window window = this.dialog.getWindow();
        switch (location) {
            case DIALOG_LOCATION_CENTER:
                window.setGravity(Gravity.CENTER);
                break;
            case DIALOG_LOCATION_BOTTOM:
                window.setGravity(Gravity.BOTTOM);
                break;
            case DIALOG_LOCATION_TOP:
                window.setGravity(Gravity.TOP);
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * 设置进度框超时监听
     *
     * @param durationRate            超时时间倍率 设置后得到的时间为800*7*durationRate， durationRate 默认10
     * @param progressOutTimeListener
     */
    public DialogBuilder setOnProgressOutTimeListener(int durationRate,
                                                      onProgressOutTimeListener progressOutTimeListener) {
        this.mProgressOutTimeListener = progressOutTimeListener;
        this.outOfTime = durationRate;
        return this;
    }

    /**
     * 设置对话框的消息内容
     *
     * @param message
     * @return
     */
    public DialogBuilder setMessage(Object message) {
        dialogMsg = getView(R.id.dialog_message);
        if (dialogMsg != null) {
            if (message != null) {
                dialogMsg.setText(parseParam(message));
            } else {
                dialogMsg.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 设置消息在对话框中的位置 MSG_LAYOUT_LEFT 居左 MSG_LAYOUT_CENTER 居中 默认居中
     *
     * @param layout
     * @return
     */
    public DialogBuilder setMessageGravity(int layout) {
        TextView dialogcontent = getView(R.id.dialog_message);
        if (dialogcontent != null && layout > 0) {
            if (layout == MSG_LAYOUT_LEFT) {
                dialogcontent.setGravity(Gravity.LEFT);
            } else if (layout == MSG_LAYOUT_CENTER) {
                dialogcontent.setGravity(Gravity.CENTER);
            }
        }
        return this;
    }

    /**
     * 给按钮设置回调监听
     *
     * @param btnClickListener 按钮的回调监听
     * @param isDissmiss       点击按钮后是否取消对话框
     * @return
     */
    public DialogBuilder setButtonClickListener(final boolean isDissmiss,
                                                final onDialogbtnClickListener btnClickListener) {
//        if (DIALOG_STYLE_CURRENT != DIALOG_STYLE_NORMAL) {
//            return this;
//        }
        this.btnClickListener = btnClickListener;
        // 设置确认按钮
        final Button btnConfirm = getView(R.id.dialog_posi_btn);

        // 给按钮绑定监听器
        if (btnConfirm != null) {

            btnConfirm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (isDissmiss) {
                        dialog.dismiss();
                    }
                    if (btnClickListener != null) {
                        btnClickListener.onDialogbtnClick(context, dialog,
                                onDialogbtnClickListener.BUTTON_CONFIRM);
                    }
                }
            });
        }

        // 设置消极按钮
        final Button btnCancel = getView(R.id.dialog_neg_btn);
        if (btnCancel != null) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isDissmiss) {
                        dialog.dismiss();
                    }
                    if (btnClickListener != null) {
                        btnClickListener.onDialogbtnClick(context, dialog,
                                onDialogbtnClickListener.BUTTON_CANCEL);
                    }
                }
            });
        }
        return this;
    }

    /**
     * 给对话框中间内容设置为一个自定义view
     *
     * @param v
     * @return
     */
    public DialogBuilder setView(View v) {
        msglayout = getView(R.id.dialog_msg_layout);
        if (msglayout != null) {
            // 删除原来的textview
            msglayout.removeAllViews();
            // 添加新的view
            msglayout.addView(v);
        }
        return this;
    }

    /**
     * 根据用户传入的布局文件加载view到对话框
     *
     * @param nameInput
     * @return
     */
    public DialogBuilder setView(int nameInput) {
        ViewGroup msglayout = getView(R.id.dialog_msg_layout);
        // 需要传入添加的布局文件的父控件，false表示不需要inflate方法添加到父控件下，让我们自己添加
        return setView(LayoutInflater.from(context).inflate(nameInput,
                msglayout, false));
    }

    /**
     * 是否显示顶部图标
     *
     * @param showIcon
     * @return
     */
    public DialogBuilder showIcon(boolean showIcon) {
        this.showTopIcon = showIcon;
        View wraningIcon = getView(R.id.warning_frame);
        if (!showTopIcon) {
            wraningIcon.setVisibility(View.GONE);
        }
        ImageView customIcon = getView(R.id.custom_icon);
        if (!showTopIcon) {
            customIcon.setVisibility(View.GONE);
        }
        return this;
    }

    public DialogBuilder setItems(String[] items,
                                  final onDialogItemClickListener listener) {
        return setItems(items, listener, null, -1);
    }

    public DialogBuilder setItems(String[] items,
                                  final onDialogItemClickListener listener, int selectedPos) {
        return setItems(items, listener, null, selectedPos);
    }

    /**
     * 给对话框设置一个数组列表
     *
     * @param items
     * @param listener
     * @param curSelectedPos 当前选中的position
     * @return
     */
    public DialogBuilder setItems(String[] items,
                                  final onDialogItemClickListener listener, final OnConvertItemViewListener convertItemViewListener, int curSelectedPos) {
        if (DIALOG_STYLE_CURRENT != DIALOG_STYLE_NORMAL) {
            return this;
        }
        this.showIcon(false);
        // 给对话框设置listview
        setView(R.layout.item_dialog_option_text);
        // 给listview 设置数据
        ListView listview = getView(android.R.id.list);

        this.convertItemViewListener = convertItemViewListener;
        final DialogItemAdapter adapter = new DialogItemAdapter(items,
                curSelectedPos);
        listview.setAdapter(adapter);
        // 给listview里面的选项设置监听器
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 让调用者决定是否关闭对话框
                // dialog.dismiss();
                adapter.setSelectedPos(position);
                adapter.notifyDataSetChanged();
                if (listener != null) {
                    listener.onDialogItemClick(adapter, context,
                            DialogBuilder.this, dialog, position);
                }
            }
        });

        return this;
    }

    /**
     * 重载方法传入一个资源文件
     *
     * @param resId
     * @param listener
     * @return
     */

    public DialogBuilder setItems(int resId,
                                  onDialogItemClickListener listener) {
        String[] items = context.getResources().getStringArray(resId);
        return setItems(items, listener, null, -1);
    }

    /**
     * 传入一个数组资源ID
     *
     * @param resId
     * @param listener
     * @param selectedPos 当前选中项
     * @return
     */
    public DialogBuilder setItems(int resId,
                                  onDialogItemClickListener listener, int selectedPos) {
        String[] items = context.getResources().getStringArray(resId);
        return setItems(items, listener, null, selectedPos);
    }

    /**
     * 传入一个数组资源ID
     *
     * @param resId
     * @param listener
     * @param selectedPos 当前选中项
     * @return
     */
    public DialogBuilder setItems(int resId,
                                  onDialogItemClickListener listener, final OnConvertItemViewListener convertItemViewListener, int selectedPos) {
        String[] items = context.getResources().getStringArray(resId);
        return setItems(items, listener, convertItemViewListener, selectedPos);
    }

    /**
     * set wheather dialog cancelable when touch outside
     *
     * @return
     */
    public DialogBuilder setTouchOutSideCancelable(boolean touchOutSideCancel) {
        this.touchOutSideCancel = touchOutSideCancel;
        if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_NORMAL) {
            this.dialog.setCanceledOnTouchOutside(touchOutSideCancel);
        }
        return this;
    }

    /**
     * set the dialog wheather can cancelable;
     *
     * @param cancleable
     * @return
     */
    public DialogBuilder setCancelable(boolean cancleable) {
        this.dialog.setCancelable(cancleable);
        return this;
    }

    /**
     * 设置确定按钮文字
     *
     * @param confrim
     * @return
     */
    public DialogBuilder setConfirmButtonText(Object confrim) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        this.confirmBtnTX = getString(confrim);
        return this;
    }

    /**
     * 设置按钮文字大小
     *
     * @param textSizeSP unit sp
     * @return
     */
    public DialogBuilder setConfirmButtonTextSize(int textSizeSP) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if (this.confrimBtn != null) {
            this.confrimBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        }
        return this;
    }

    /**
     * 设置自定义图标
     *
     * @param ResID
     * @return
     */
    public DialogBuilder setCustomIcon(int ResID) {
        ImageView icon = getView(R.id.custom_icon);
        FrameLayout warningFrame = getView(R.id.warning_frame);
        if (icon != null && warningFrame != null && ResID > 0) {
            icon.setVisibility(View.VISIBLE);
            warningFrame.setVisibility(View.GONE);
            icon.setImageResource(ResID);
        }
        return this;
    }

    private String getString(Object confrim) {
        if (confrim == null) {
            return this.confirmBtnTX;
        }
        if (confrim instanceof String) {
            return (String) confrim;
        }
        if (confrim instanceof Integer) {
            return context.getString((Integer) confrim);
        }
        return this.confirmBtnTX;
    }

    /**
     * 设置取消按钮文字
     *
     * @param cancelTx
     * @return
     */
    public DialogBuilder setCancelButtonText(Object cancelTx) {
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
        this.cancleBtnTX = getString(cancelTx);
        return this;
    }

    /**
     * 设置按钮文字大小
     *
     * @param textSizeSP unit sp
     * @return
     */
    public DialogBuilder setCancelButtonTextSize(int textSizeSP) {
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
        if (this.cancelBtn != null) {
            this.cancelBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        }
        return this;
    }


    /**
     * 根据子控件ID得到子控件
     *
     * @param id 子控件ID
     * @return 返回子控件
     */
    public <T extends View> T getView(int id) {
        return (T) dialog.findViewById(id);
    }

    /**
     * 解析用户传入的数据，字符串或者资源ID
     *
     * @return
     */
    private String parseParam(Object param) {
        // 如果是资源id 就通过上下文 获取资源
        if (param instanceof Integer) {
            return context.getString((Integer) param);
        } else if (param instanceof String) {
            return param.toString();
        }
        return null;

    }

    /**
     * set Confirm Button TextColor
     *
     * @param color
     * @return
     */
    public DialogBuilder setConfirmButtonTextColor(int color) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if (this.confrimBtn != null) {
            this.confrimBtn.setTextColor(color);
        }
        return this;
    }

    /**
     * set Cancel Button TextColor
     *
     * @param color
     * @return
     */
    public DialogBuilder setCancelButtonTextColor(int color) {
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
        if (this.cancelBtn != null) {
            this.cancelBtn.setTextColor(color);
        }
        return this;
    }

    /**
     * set message text size
     *
     * @param textsize unit sp
     * @return
     */
    public DialogBuilder setMessageTextSize(int textsize) {
        if (dialogMsg != null) {
            dialogMsg.setTextSize(TypedValue.COMPLEX_UNIT_SP, textsize);
        }
        return this;
    }

    /**
     * set message text size
     *
     * @param color colorvalue
     * @return
     */
    public DialogBuilder setMessageTextColor(int color) {
        if (dialogMsg != null) {
            dialogMsg.setTextColor(color);
        }
        return this;
    }

    /**
     * set title text size
     *
     * @param textsize unit sp
     * @return
     */
    public DialogBuilder setTitleTextSize(int textsize) {
        if (dialogTitle != null) {
            dialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textsize);
        }
        return this;
    }

    /**
     * set title text size
     *
     * @param color colorvalue
     * @return
     */
    public DialogBuilder setTitleTextColor(int color) {
        if (dialogTitle != null) {
            dialogTitle.setTextColor(color);
        }
        return this;
    }

    public DialogBuilder setDialogBackground(int ResID) {
        if (dialogRootLayout != null) {
            dialogRootLayout.setBackgroundResource(ResID);
        }
        return this;
    }


    /**
     * 监听器监听对话框按钮点击
     *
     * @author zhl
     */
    public interface onDialogbtnClickListener {
        /**
         * （区分点击的事左边按钮还是右边按钮）--确认
         */
        public static final int BUTTON_CONFIRM = 0;
        /**
         * （区分点击的事左边按钮还是右边按钮）--取消
         */
        public static final int BUTTON_CANCEL = 1;

        /**
         * @param context  上下文
         * @param dialog   点击的哪个对话框
         * @param whichBtn 点击的哪个按钮
         */
        void onDialogbtnClick(Context context, Dialog dialog, int whichBtn);

    }

    /**
     * 监听器监听对话框中的选项点击
     *
     * @author zhl
     */
    public interface onDialogItemClickListener {

        /**
         * @param context
         * @param dialogbuilder 对象
         * @param dialog
         * @param position      选项角标
         */
        public void onDialogItemClick(DialogItemAdapter ItemAdapter,
                                      Context context, DialogBuilder dialogbuilder, Dialog dialog,
                                      int position);

    }

    /**
     * the convert view listerner which used in setItems to custom itemview
     *
     * @author zhl
     */
    public interface OnConvertItemViewListener {
        /**
         * convert the item view when you want custom itemview
         *
         * @param position
         * @param convertView
         * @param parent
         */
        public View convertItemView(int position, View convertView, ViewGroup parent);
    }

    /**
     * 进度框超时监听
     *
     * @author zhl
     */
    public interface onProgressOutTimeListener {

        public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView);

    }

    /**
     * set positive btn BackgroundResouce
     *
     * @param resID
     * @return
     */
    public DialogBuilder setConfirmBackgroundResouce(int resID) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if (confrimBtn != null && resID != -1) {
            confirmBtnBG = resID;
            confrimBtn.setBackgroundResource(resID);
        }
        return this;
    }

    /**
     * set negative btn BackgroundResouce
     *
     * @param resID
     * @return
     */
    public DialogBuilder setCancelBackgroundResouce(int resID) {
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
        if (cancelBtn != null && resID != -1) {
            cancelBtnBG = resID;
            cancelBtn.setBackgroundResource(resID);
        }
        return this;
    }

    /**
     * set progress style dialog has timeout limit
     *
     * @param TimeOutLimit
     * @return
     */
    public DialogBuilder setProgressTimeOutLimit(boolean TimeOutLimit) {
        this.progressTimeOutLimit = TimeOutLimit;
        return this;
    }

    /**
     * dialog列表选项的适配器
     *
     * @author zhl
     */
    public class DialogItemAdapter extends BaseAdapter {
        private String[] dataArrays;
        private int selectedPos;

        public DialogItemAdapter(String[] dataArrays, int selectedPos) {
            this.dataArrays = dataArrays;
            this.selectedPos = selectedPos;
        }

        @Override
        public int getCount() {
            return dataArrays == null ? 0 : dataArrays.length;
        }

        @Override
        public Object getItem(int position) {
            return dataArrays[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void setSelectedPos(int position) {
            this.selectedPos = position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertItemViewListener != null) {
                return convertItemViewListener.convertItemView(position, convertView, parent);
            } else {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.item_dialog_option_text, parent, false);
                    viewHolder.txView = (TextView) convertView
                            .findViewById(R.id.item_tx);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.txView.setTextColor(context.getResources().getColor(
                        R.color.md_blue_grey_900));

                if (position == selectedPos) {
//				viewHolder.txView.setTextColor(context.getResources().getColor(
//						R.color.item_text_color_pressed));
                    // 暂时选中不变色
                    viewHolder.txView.setBackgroundResource(R.drawable.border_rect_round_green);
                } else {
                    viewHolder.txView.setBackgroundResource(R.color.color_00000000);
                }
                viewHolder.txView.setText(dataArrays[position]);
                return convertView;
            }
        }

    }

    public class ViewHolder {
        TextView txView;
    }

    public ProgressHelper getProgressHelper() {
        return mProgressHelper;
    }
}
