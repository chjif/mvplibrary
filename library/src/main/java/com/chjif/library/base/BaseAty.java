package com.chjif.library.base;

import android.os.Bundle;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by cjf on 2018-07-12 16:46
 */
public abstract class BaseAty extends RxAppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutInit());
        ButterKnife.bind(this);
    }
    protected abstract int layoutInit();
}
