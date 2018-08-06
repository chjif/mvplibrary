package com.chjif.library.base;

import java.lang.ref.WeakReference;

/**
 * Created by cjf on 2018-07-26 17:49
 */
public abstract class BasePresenter<V extends BaseView> {
    //WeakReference防止内存泄露
    protected WeakReference<V> mView;

    public BasePresenter() {
    }

    public void setView(V v) {
        mView = new WeakReference<V>(v);
    }

    public V getView() {
        return mView == null ? null : mView.get();
    }

    protected abstract void start();


    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
