package com.chjif.mvpbaselibrary;

import android.view.View;
import android.widget.TextView;

import com.chjif.library.base.BaseAty;
import com.chjif.mvpbaselibrary.contract.SampleContract;
import com.chjif.mvpbaselibrary.network.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAty implements SampleContract.View{

    @BindView(R.id.tv_result)
    TextView tvResult;
    SampleContract.Presenter mPresenter;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
       new MainPresenter(this).start();
    }

    @OnClick(R.id.bt_querry)
    public void onClick(View v) {
//        HttpHelper.getApiService().getExpress("yuantong", "11111111111")
//                .compose(RxHelper.io_main(this))
//                .subscribe((loginResult) -> {
//                    tvResult.setText(loginResult);
//                    LoggerUtil.d("mytest", (String) loginResult);
//                }, new RxException<>(e -> e.printStackTrace()));
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
