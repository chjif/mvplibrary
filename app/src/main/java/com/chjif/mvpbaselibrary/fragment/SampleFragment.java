package com.chjif.mvpbaselibrary.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.chjif.library.base.BaseFrgm;
import com.chjif.mvpbaselibrary.R;
import com.chjif.mvpbaselibrary.contract.SampleContract;

import butterknife.BindView;

/**
 * Created by cjf on 2018-07-31 17:34
 */
public class SampleFragment extends BaseFrgm<SampleContract.Presenter> implements SampleContract.View {
    int index;
    @BindView(R.id.tv_result)
    TextView tvResult;

    public SampleFragment() {
        // Requires empty public constructor
    }

    public static SampleFragment newInstance(int index) {
        SampleFragment sampleFragment = new SampleFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        sampleFragment.setArguments(args);
        return sampleFragment;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        }
    }

    @Override
    public void showExpress(String result) {
        tvResult.setText(result);
    }

    @Override
    public void setPresenter(SampleContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
