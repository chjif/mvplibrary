package com.chjif.library.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.reactivex.ObservableTransformer;

/**
 * Created by cjf on 2018-07-27 15:51
 */
public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);

    Activity getContext();

    /**
     * IO线程发送主线程观察
     * 绑定周期，onCreate注册onDestroy取消，onStart注册onStop取消，onResume注册onPause取消
     *
     * @param <T>
     * @return
     */
    <T> ObservableTransformer<T, T> io_main();

    /**
     * 显示提示语句
     */
    void showToast(@StringRes int res);

    void showToast(@NonNull String tips);

    /**
     * 显示正在加载进度框
     */
    void showLoading();

    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();
}