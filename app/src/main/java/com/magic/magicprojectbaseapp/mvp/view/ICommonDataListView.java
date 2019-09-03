package com.magic.magicprojectbaseapp.mvp.view;

import com.magic.magicprojectbaseapp.mvp.entity.StudentBean;

import java.util.List;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/11
 *     desc   : 任务列表视图
 * </pre>
 */
public interface ICommonDataListView extends IBaseView{
    //下拉刷新、第一次加载
    void showDataList(StudentBean data);
    //加载更多
//    void showMoreList(List<TaskListCallBean.TaskListBean> moreWorkList);
    //加载错误
//    void showListErro(Object erroInfo);


}
