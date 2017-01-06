package com.laichushu.book.mvp.findclass.videodetail;

import com.laichushu.book.ui.base.BasePresenter;

/**
 * 发现 - 课程 - 视频详情 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class VideoDetailPresenter extends BasePresenter<VideoDetailView> {
    public VideoDetailPresenter(VideoDetailView view) {
        attachView(view);
    }
}