package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.userhomepage.UserHomePagePresener;
import com.laichushu.book.mvp.userhomepage.UserHomePageView;
import com.laichushu.book.ui.base.MvpActivity2;

public class UserHomePageActivity extends MvpActivity2<UserHomePagePresener> implements UserHomePageView, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
    }

    @Override
    protected UserHomePagePresener createPresenter() {
        return new UserHomePagePresener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected View createSuccessView() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getUserHeadDateSuccess(HomeUserResult result) {

    }

    @Override
    public void getUserHomeDyDateSuccess(HomeUseDyrResult result) {

    }

    @Override
    public void getUserHomeBookListSuccess(HomeHotModel result) {

    }

    @Override
    public void getUserHomeFocusHeSuccess(HomeHotModel result) {

    }

    @Override
    public void getUserHomeHeFocusSuccess(HomeHotModel result) {

    }

    @Override
    public void getDataFail(String errorMsg) {

    }
}
