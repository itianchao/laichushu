package com.laichushu.book.mvp.find.coursera.videodetail;

/**
 * 发现 - 课程 - 视频详情 View
 * Created by wangtong on 2017/1/6.
 */

public interface VideoDetailView {
    void loadVideoDetailDataSuccess(VideoDetailModle model);

    void loadVideoDetailDataFail(String msg);
}
