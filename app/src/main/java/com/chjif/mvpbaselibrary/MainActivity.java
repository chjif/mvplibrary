package com.chjif.mvpbaselibrary;

import android.view.View;
import android.widget.TextView;

import com.chjif.library.base.BaseAty;
import com.chjif.library.util.ActivityUtil;
import com.chjif.mvpbaselibrary.contract.SampleContract;
import com.chjif.mvpbaselibrary.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAty<SampleContract.Presenter> implements SampleContract.View {

    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        new MainPresenter(this).start();
    }

    @OnClick(R.id.bt_jump)
    public void onClick(View v) {
        ActivityUtil.toAnotherActivity(this, TwoActivity.class);
    }

    @Override
    public void setPresenter(SampleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showExpress(String loginResult) {
        tvResult.setText(loginResult);
    }

}
