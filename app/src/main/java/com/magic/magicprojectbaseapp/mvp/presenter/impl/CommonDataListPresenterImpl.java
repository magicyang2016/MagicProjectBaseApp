package com.magic.magicprojectbaseapp.mvp.presenter.impl;

import com.google.gson.Gson;
import com.magic.magicprojectbaseapp.core.http.converter.RxApiManager;
import com.magic.magicprojectbaseapp.core.http.erro.ExceptionHandle;
import com.magic.magicprojectbaseapp.mvp.entity.StudentBean;
import com.magic.magicprojectbaseapp.mvp.model.DefaultModel;
import com.magic.magicprojectbaseapp.mvp.model.callback.Observer;
import com.magic.magicprojectbaseapp.mvp.model.impl.CommonDataListModelImpl;
import com.magic.magicprojectbaseapp.mvp.presenter.BasePresenter;
import com.magic.magicprojectbaseapp.mvp.presenter.DefaultPresenter;
import com.magic.magicprojectbaseapp.mvp.view.ICommonDataListView;
import com.magic.magicprojectbaseapp.utils.LogUtils;
import com.magic.magicprojectbaseapp.utils.ToolUtil;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :任务单Presenter
 * </pre>
 */
public class CommonDataListPresenterImpl extends BasePresenter<ICommonDataListView, LifecycleProvider> implements DefaultPresenter {
    private DefaultModel dataListModel = CommonDataListModelImpl.getInstance();

    private Disposable disposable;
    private LifecycleProvider mLifecycleProvider;

    public CommonDataListPresenterImpl(ICommonDataListView view, LifecycleProvider lifecycleProvider) {
        super(view, lifecycleProvider);
        this.mLifecycleProvider = lifecycleProvider;
    }
    @Override
    public void load(HashMap<String, Object> params) {

        if (getView() != null) {
            getView().showLoading();

        }
        dataListModel.load(params, mLifecycleProvider, new Observer<StudentBean>() {
            @Override
            public void OnSuccess(StudentBean data ) {
//                打印json
//                Logger.json(new Gson().toJson(data));
                Logger.e("获取数据：OnSuccess:");
                if (getView() != null) {
                      getView().showDataList(data);

                }
            }

            @Override
            public void OnFail(ExceptionHandle.ResponeThrowable e) {
                Logger.e("获取数据：OnFail:"+e.getMessage());
                if (getView() != null) {
                    getView().showToast(e.message);
                    getView().closeLoading();
                }

            }

            @Override
            public void OnCompleted() {
                Logger.e("获取数据：OnCompleted:");
                if (getView() != null) {
                    getView().closeLoading();
                }
            }

            @Override
            public void OnDisposable(Disposable d) {
                //把之前请求订阅关闭，避免重复请求
                RxApiManager.getInstance().cancel(disposable);
                disposable = d;
                RxApiManager.getInstance().add(d);
            }
        });


    }



    @Override
    public void cancleLoad() {
        RxApiManager.getInstance().cancel(disposable);
    }
}
