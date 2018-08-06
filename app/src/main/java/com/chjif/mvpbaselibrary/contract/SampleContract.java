package com.chjif.mvpbaselibrary.contract;


import com.chjif.library.base.BasePresenter;
import com.chjif.library.base.BaseView;

public interface SampleContract {
    interface View extends BaseView<Presenter> {
        void showExpress(String result);
    }

    abstract class  Presenter extends BasePresenter<View> {
        public abstract void getExpress();
    }
}



