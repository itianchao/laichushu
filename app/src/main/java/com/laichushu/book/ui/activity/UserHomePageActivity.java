package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;

public class UserHomePageActivity extends MvpActivity2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected View createSuccessView() {
        return null;
    }
}
