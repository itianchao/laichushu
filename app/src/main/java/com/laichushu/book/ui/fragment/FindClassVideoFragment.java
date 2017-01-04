package com.laichushu.book.ui.fragment;

import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 课程 - 视频
 * Created by wangtong on 2017/1/4.
 */

public class FindClassVideoFragment extends MvpFragment2 {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_video);

        return mSuccessView;
    }
}
