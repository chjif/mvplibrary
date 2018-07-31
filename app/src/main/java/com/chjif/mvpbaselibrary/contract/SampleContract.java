package com.chjif.mvpbaselibrary.contract;


import com.chjif.library.base.BasePresenter;
import com.chjif.library.base.BaseView;

public interface SampleContract {
    interface View extends BaseView<Presenter> {
        void showExpress(String result);
    }

    interface Presenter extends BasePresenter {

    }
}



