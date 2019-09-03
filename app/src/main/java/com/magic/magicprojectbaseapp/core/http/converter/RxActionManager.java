package com.magic.magicprojectbaseapp.core.http.converter;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author :
 *     time   : 2019/06/05
 *     desc   :RxActionManager
 * </pre>
 */
public interface RxActionManager<T> {

    void add(Disposable subscription);

    void cancel(Disposable t);

    void cancelall();
}