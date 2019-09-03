package com.magic.magicprojectbaseapp.utils.tag;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : 山东御银智慧
 *     time   : 2019/06/13
 *     desc   :RxJavaAction管理接口
 * </pre>
 */
public interface ITagListener<T> {
    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除指定tag
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消指定tag
     *
     * @param tag
     */
    void cancel(T tag);

    /**
     *取消所有
     */
    void cancelAll();
}
