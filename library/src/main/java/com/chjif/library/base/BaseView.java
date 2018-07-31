package com.chjif.library.base;

import io.reactivex.ObservableTransformer;

/**
 * Created by cjf on 2018-07-27 15:51
 */
public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);
    <T> ObservableTransformer<T, T> io_main();
}