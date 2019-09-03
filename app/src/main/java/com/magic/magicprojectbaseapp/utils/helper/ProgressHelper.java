package com.magic.magicprojectbaseapp.utils.helper;

import android.content.Context;

import com.magic.magicprojectbaseapp.R;
import com.magic.magicprojectbaseapp.utils.ProgressWheel;


/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/05
 *     desc   :进度条加载工具类
 * </pre>
 */
public class ProgressHelper {
    private ProgressWheel mProgressWheel;
    private boolean mToSpin;
    private float mSpinSpeed;
    private int mBarWidth;
    private int mBarColor;
    private int mRimWidth;
    private int mRimColor;
    private boolean mIsInstantProgress;
    private float mProgressVal;
    private int mCircleRadius;
    /**
     * 初始化
     */
    public ProgressHelper(Context ctx) {
        mToSpin = true;
        mSpinSpeed = 0.75f;
        mBarWidth = ctx.getResources().getDimensionPixelSize(R.dimen.dp_3) + 1;
        mBarColor = ctx.getResources().getColor(R.color.md_green_300);
        mRimWidth = 0;
        mRimColor = 0x00000000;
        mIsInstantProgress = false;
        mProgressVal = -1;
        mCircleRadius = ctx.getResources().getDimensionPixelOffset(R.dimen.dp_34);
    }
    /**
     * 初始化 mProgressWheel
     */
    public ProgressWheel getProgressWheel () {
        return mProgressWheel;
    }
    /**
     * 加载进度条控件
     */
    public void setProgressWheel (ProgressWheel progressWheel) {
        mProgressWheel = progressWheel;
        updatePropsIfNeed();
    }
    /**
     * 更新进度条
     */
    private void updatePropsIfNeed () {
        if (mProgressWheel != null) {
            if (!mToSpin && mProgressWheel.isSpinning()) {
                mProgressWheel.stopSpinning();
            } else if (mToSpin && !mProgressWheel.isSpinning()) {
                mProgressWheel.spin();
            }
            if (mSpinSpeed != mProgressWheel.getSpinSpeed()) {
                mProgressWheel.setSpinSpeed(mSpinSpeed);
            }
            if (mBarWidth != mProgressWheel.getBarWidth()) {
                mProgressWheel.setBarWidth(mBarWidth);
            }
            if (mBarColor != mProgressWheel.getBarColor()) {
                mProgressWheel.setBarColor(mBarColor);
            }
            if (mRimWidth != mProgressWheel.getRimWidth()) {
                mProgressWheel.setRimWidth(mRimWidth);
            }
            if (mRimColor != mProgressWheel.getRimColor()) {
                mProgressWheel.setRimColor(mRimColor);
            }
            if (mProgressVal != mProgressWheel.getProgress()) {
                if (mIsInstantProgress) {
                    mProgressWheel.setInstantProgress(mProgressVal);
                } else {
                    mProgressWheel.setProgress(mProgressVal);
                }
            }
            if (mCircleRadius != mProgressWheel.getCircleRadius()) {
                mProgressWheel.setCircleRadius(mCircleRadius);
            }
        }
    }
    /**
     * 设置数量
     */
    public void resetCount() {
        if (mProgressWheel != null) {
            mProgressWheel.resetCount();
        }
    }
    /**
     * 判断进度条是否前进
     */
    public boolean isSpinning() {
        return mToSpin;
    }
    /**
     * 开始进度条
     */
    public void spin() {
        mToSpin = true;
        updatePropsIfNeed();
    }
    /**
     * 暂停进度条
     */
    public void stopSpinning() {
        mToSpin = false;
        updatePropsIfNeed();
    }
    /**
     * 获取进度
     */
    public float getProgress() {
        return mProgressVal;
    }
    /**
     * 设置进度
     */
    public void setProgress(float progress) {
        mIsInstantProgress = false;
        mProgressVal = progress;
        updatePropsIfNeed();
    }

    public void setInstantProgress(float progress) {
        mProgressVal = progress;
        mIsInstantProgress = true;
        updatePropsIfNeed();
    }
    /**
     * 获取circleRadius
     */
    public int getCircleRadius() {
        return mCircleRadius;
    }

    /**
     * @param circleRadius units using pixel
     * **/
    public void setCircleRadius(int circleRadius) {
        mCircleRadius = circleRadius;
        updatePropsIfNeed();
    }

    /**
     * 获取宽度
     */
    public int getBarWidth() {
        return mBarWidth;
    }
    /**
     * 设置进度条 宽度
     */
    public void setBarWidth(int barWidth) {
        mBarWidth = barWidth;
        updatePropsIfNeed();
    }
    /**
     * 获取进度条 颜色
     */
    public int getBarColor() {
        return mBarColor;
    }
    /**
     * 设置进度条 颜色
     */
    public void setBarColor(int barColor) {
        mBarColor = barColor;
        updatePropsIfNeed();
    }
    /**
     * 获取mRimWidth
     */
    public int getRimWidth() {
        return mRimWidth;
    }
    /**
     * 设置mRimWidth
     */
    public void setRimWidth(int rimWidth) {
        mRimWidth = rimWidth;
        updatePropsIfNeed();
    }
    /**
     * 获取mRimColor
     */
    public int getRimColor() {
        return mRimColor;
    }
    /**
     * 设置rimColor
     */
    public void setRimColor(int rimColor) {
        mRimColor = rimColor;
        updatePropsIfNeed();
    }
    /**
     * 获取mSpinSpeed
     */
    public float getSpinSpeed() {
        return mSpinSpeed;
    }
    /**
     * 设置spinSpeed
     */
    public void setSpinSpeed(float spinSpeed) {
        mSpinSpeed = spinSpeed;
        updatePropsIfNeed();
    }
}
