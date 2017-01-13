package com.laichushu.book.mvp.find.coursera.videodetail;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 发现 - 课程 - 视频详情 View
 * Created by wangtong on 2017/1/6.
 */

public interface VideoDetailView {
    void loadVideoDetailDataSuccess(VideoDetailModle model);

    void loadVideoDetailDataFail(String msg);

    void loadCollectDataSuccess(RewardResult model, String type);

    void loadCollectDataFail(String msg, String type);
}
