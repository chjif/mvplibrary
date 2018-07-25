package com.chjif.mvpbaselibrary;

import android.view.View;
import android.widget.TextView;

import com.chjif.library.base.BaseAty;
import com.chjif.library.network.RxException;
import com.chjif.library.network.RxHelper;
import com.chjif.library.util.LoggerUtil;
import com.chjif.mvpbaselibrary.network.HttpHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAty {

    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected int layoutInit() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.bt_querry)
    public void onClick(View v) {
        HttpHelper.getApiService().getExpress("yuantong", "11111111111")
                .compose(RxHelper.io_main(this))
                .subscribe((loginResult) -> {
                    tvResult.setText(loginResult);
                    LoggerUtil.d("mytest", (String) loginResult);
                }, new RxException<>(e -> e.printStackTrace()));
    }

}
