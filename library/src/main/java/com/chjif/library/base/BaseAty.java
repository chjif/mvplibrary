package com.chjif.library.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.chjif.library.widget.MyProgressDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cjf on 2018-07-12 16:46
 */
public abstract class BaseAty<P extends BasePresenter> extends RxAppCompatActivity {
    //引用P层，如果界面逻辑简单可以为空
    protected P mPresenter;
    private Unbinder mUnbinder;
    private Toast toast = null;
    private MyProgressDialog myProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutRes() != 0) {
            setContentView(layoutRes());
            //绑定到butterknife
            mUnbinder = ButterKnife.bind(this);
        }
        init();
    }

    protected abstract int layoutRes();

    protected abstract void init();

    public <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(bindToLifecycle());//绑定activity，与activity生命周期一样
    }

    public Activity getContext() {
        return this;
    }

    public void showToast(@StringRes int res) {
        if (toast == null) {
            toast = Toast.makeText(this, res, Toast.LENGTH_SHORT);
        } else {
            toast.setText(getString(res));
        }
        toast.show();
    }

    public void showToast(@NonNull String tips) {
        if (toast == null) {
            toast = Toast.makeText(this, tips, Toast.LENGTH_SHORT);
        } else {
            toast.setText(tips);
        }
        toast.show();
    }

    public void showLoading() {
        if (myProgressDialog == null) {
            myProgressDialog = new MyProgressDialog(this);
        }
        myProgressDialog.show();
    }

    public void hideLoading() {
        if (myProgressDialog != null) {
            myProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.detachView();//释放资源
        this.mPresenter = null;
    }
}
