package com.chjif.library.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.chjif.library.R;


/**
 * Created by cjf on 2018-05-28 15:21
 */
public class MyProgressDialog extends ProgressDialog {

    public MyProgressDialog(Context context) {
        super(context, R.style.baseProressDialog);
    }

    public MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
//        View view = LayoutInflater.from(context).inflate(
//                R.layout.dialog_loading, null);
//        AutoUtils.auto(view);
        setContentView(R.layout.dialog_loading);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {//开启
        super.show();
    }

    @Override
    public void dismiss() {//关闭
        super.dismiss();
    }
}

