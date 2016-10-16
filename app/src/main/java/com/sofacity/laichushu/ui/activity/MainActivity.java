package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.ui.base.MvpActivity;

public class MainActivity extends MvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
