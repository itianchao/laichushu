package com.laichushu.book.mvp.find.coursera.documentdetail;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailModle;

/**
 * 发现 - 课程 - 文档详情 View
 * Created by wangtong on 2017/1/6.
 */

public interface DocDetailView {
    void loadDocDetailDataSuccess(VideoDetailModle model);

    void loadDocDetailDataFail(String msg);

    void loadCollectDataSuccess(RewardResult model, String type);

    void loadCollectDataFail(String msg, String type);
}
