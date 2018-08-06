package com.chjif.library.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chjif.library.widget.MyProgressDialog;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cjf on 2018-07-24 18:03
 */
public abstract class BaseFrgm<P extends BasePresenter> extends RxFragment {
    protected P mPresenter;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    protected abstract int layoutRes();

    protected abstract void init();

    public <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(bindToLifecycle());//绑定fragment,默认取消订阅的时间
    }

    public Activity getContext() {
        return getActivity();
    }

    public void showToast(@StringRes int res) {
        if (getActivity() instanceof BaseAty) {
            ((BaseAty) getActivity()).showToast(res);
        }
    }

    public void showToast(@NonNull String tips) {
        if (getActivity() instanceof BaseAty) {
            ((BaseAty) getActivity()).showToast(tips);
        }
    }

    public void showLoading() {
        if (getActivity() instanceof BaseAty) {
            ((BaseAty) getActivity()).showLoading();
        }
    }

    public void hideLoading() {
        if (getActivity() instanceof BaseAty) {
            ((BaseAty) getActivity()).hideLoading();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.detachView();//释放资源
        this.mPresenter = null;
    }
}
