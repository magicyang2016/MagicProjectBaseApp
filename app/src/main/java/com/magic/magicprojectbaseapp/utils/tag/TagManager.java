package com.magic.magicprojectbaseapp.utils.tag;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/13
 *     desc   :RxJavaAction管理实现类
 *     本类有个缺陷:当多个相同tag传入时会，出现取消异常
 * </pre>
 */
public class TagManager implements ITagListener<Object> {
    private static volatile TagManager mInstance;
//    private ConcurrentHashMap<Long, Disposable> mTimeMaps;//处理,请求列表
    private ConcurrentHashMap<Object, Disposable> mMaps;//处理,请求列表

    public static TagManager getInstance() {
        if (mInstance == null) {
            synchronized (TagManager.class) {
                if (mInstance == null) {
                    mInstance = new TagManager();
                }
            }
        }
        return mInstance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private TagManager() {
        mMaps = new ConcurrentHashMap<>();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void add(Object tag, Disposable disposable) {
        //下方的判断是用来去除重复接口的，可根据实际情况来处理
        if (mMaps.containsKey(tag)) {
            Disposable preDispos = mMaps.get(tag);
            if (preDispos != null && !preDispos.isDisposed()) {
                preDispos.dispose();
                remove(tag);
            }
        }
        mMaps.put(tag, disposable);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void remove(Object tag) {
        if (!mMaps.isEmpty()) {
            mMaps.remove(tag);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancel(Object tag) {
        if (mMaps.isEmpty()) {
            return;
        }
        if (mMaps.get(tag) == null) {
            return;
        }
        if (!mMaps.get(tag).isDisposed()) {
            mMaps.get(tag).dispose();
        }
        mMaps.remove(tag);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancelAll() {
        if (mMaps.isEmpty()) {
            return;
        }
        for (Object tag : mMaps.keySet()) {
            cancel(tag);
        }
    }


    /**
     * 判断是否取消了请求
     *
     * @param tag
     * @return
     */
    public boolean isDisposed(Object tag) {
        if (mMaps.isEmpty() || mMaps.get(tag) == null) return true;
        return mMaps.get(tag).isDisposed();
    }

    public int getTagCount() {
        return mMaps != null ? mMaps.size() : 0;
    }

    public ConcurrentHashMap<Object, Disposable> getMaps() {
        return mMaps;
    }

    @Override
    public String toString() {
        return "TagManager{" +
                "mMaps=" + mMaps.toString() +
                '}';
    }
}
