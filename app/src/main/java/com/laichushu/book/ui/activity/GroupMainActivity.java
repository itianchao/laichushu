package com.laichushu.book.ui.activity;

import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.mvp.group.main.GroupMainPresenter;
import com.laichushu.book.mvp.group.main.GroupMainView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.utils.UIUtil;

/**
 * 小组主页
 * Created by wangtong on 2016/12/26.
 */

public class GroupMainActivity extends MvpActivity2<GroupMainPresenter> implements GroupMainView {

    @Override
    protected GroupMainPresenter createPresenter() {
        return new GroupMainPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_groupmain);
        return mSuccessView;
    }
}
