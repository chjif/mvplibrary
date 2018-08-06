package com.chjif.mvpbaselibrary.presenter;

import android.Manifest;

import com.chjif.library.network.RxException;
import com.chjif.mvpbaselibrary.contract.SampleContract;
import com.chjif.mvpbaselibrary.network.HttpHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by cjf on 2018-07-30 16:20
 */
public class MainPresenter extends SampleContract.Presenter {

    public MainPresenter(SampleContract.View view) {
        setView(view);
        getView().setPresenter(this);
    }

    @Override
    public void start() {
        getExpress();
    }

    /**
     * 模拟检查权限，在需要使用对应权限的位置对应加上相应权限
     */
    public void checkPermission() {
        RxPermissions permissions = new RxPermissions(getView().getContext());
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (!granted) {
                        //没有对应权限
                    }
                });
    }

    @Override
    public void getExpress() {
        checkPermission();
        getView().showLoading();
        HttpHelper.getApiService().getExpress("yuantong", "11111111111")
                .compose(getView().io_main())
                .subscribe((loginResult) -> {
                            getView().hideLoading();
                            //最好加上，防止刚好出现Activity关闭后运行异常
                            if (getView() != null) {
                                getView().showExpress(loginResult);
                            }
                        }, new RxException<>(
                                e -> {
                                    e.printStackTrace();
                                    getView().hideLoading();
                                })
                );
    }
}
