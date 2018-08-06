package com.chjif.mvpbaselibrary;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.chjif.library.base.BaseAty;
import com.chjif.mvpbaselibrary.fragment.SampleFragment;
import com.chjif.mvpbaselibrary.presenter.MainPresenter;

import butterknife.OnClick;

/**
 * Created by cjf on 2018-08-06 14:59
 */
public class TwoActivity extends BaseAty {
    SampleFragment sampleFragment;
    MainPresenter presenter;

    @Override
    protected int layoutRes() {
        return R.layout.activity_two;
    }

    @Override
    protected void init() {
        sampleFragment =
                (SampleFragment) getSupportFragmentManager().findFragmentById(R.id.frame_lay);
        if (sampleFragment == null) {
            // Create the fragment
            sampleFragment = SampleFragment.newInstance(200);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_lay, sampleFragment);
            transaction.commit();
        }
        presenter = new MainPresenter(sampleFragment);
    }

    @OnClick(R.id.btn_query)
    public void onClick(View view){
        presenter.getExpress();
    }




}
