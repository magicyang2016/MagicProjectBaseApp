package com.magic.magicprojectbaseapp.mvp.model;

import com.magic.magicprojectbaseapp.mvp.model.callback.Observer;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;


/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :DefaultModel 基础类
 * </pre>
 */
public interface DefaultModel<T> {
    void load(Map<String, Object> params, LifecycleProvider lifecycle, Observer<T> observer);
}
