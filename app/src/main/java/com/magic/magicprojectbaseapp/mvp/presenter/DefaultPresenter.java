package com.magic.magicprojectbaseapp.mvp.presenter;


import java.util.HashMap;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :DefaultPresenter
 * </pre>
 */
public interface DefaultPresenter {
    void load(HashMap<String, Object> params);//
    void cancleLoad();//取消本次加载
}
