package com.chjif.mvpbaselibrary.network;

import com.chjif.library.network.RxException;
import com.chjif.library.network.RxHelper;
import com.chjif.library.util.LoggerUtil;
import com.chjif.mvpbaselibrary.contract.SampleContract;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

/**
 * Created by cjf on 2018-07-30 16:20
 */
public class MainPresenter implements SampleContract.Presenter {
    SampleContract.View mView;

    public MainPresenter(SampleContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        get();
    }

    public void get() {
        HttpHelper.getApiService().getExpress("yuantong", "11111111111")
                .compose(mView.io_main())
                .subscribe((loginResult) -> {
                    mView.showExpress(loginResult);
                }, new RxException<>(e -> e.printStackTrace()));
    }
}
