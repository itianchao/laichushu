package com.laichushu.book.ui.activity;

import android.view.View;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findclass.videodetail.VideoDetailPresenter;
import com.laichushu.book.mvp.findclass.videodetail.VideoDetailView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 课程 - 视频详情
 * Created by wangtong on 2017/1/6.
 */

public class FindClassVideoDetailActivity extends MvpActivity2<VideoDetailPresenter> implements VideoDetailView{
    @Override
    protected VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findclassvideodetail);
        return mSuccessView;
    }
}
