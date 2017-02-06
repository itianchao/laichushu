package com.laichushu.book.mvp.find.coursera.live;

import android.text.TextUtils;

import com.laichushu.book.bean.netbean.LessonList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.mvp.find.coursera.video.FindClassVideoView;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.FindClassLiveFragment;
import com.laichushu.book.ui.fragment.FindClassVideoFragment;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 直播 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class FindClassLivePresenter extends BasePresenter<FindClassLiveView> {

    private FindClassLiveFragment mFragment;
    private String lessonType = ConstantValue.VIDEO_PLAY_LIVE_DEMAND;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE1;
    private LessonList_Paramet paramet = new LessonList_Paramet(lessonType, pageNo, pageSize);

    public FindClassLivePresenter(FindClassLiveView view) {
        attachView(view);
        mFragment = (FindClassLiveFragment) view;
    }

    /**
     * 直播视频列表
     */
    public void loadVideoList(boolean isRefrush) {

        if (!isRefrush && getParamet().getPageNo().equals("1") && (!TextUtils.isEmpty(getParamet().getLessonCategoryId()) || !TextUtils.isEmpty(getParamet().getSortWay()))) {
            mFragment.refreshPage(LoadingPager.PageState.STATE_LOADING);
        }
        LoggerUtil.e("发现 - 课程 - 直播视频列表");
        addSubscription(apiStores.getLessonList(paramet), new ApiCallback<CourseraModle>() {
            @Override
            public void onSuccess(CourseraModle modle) {
                mvpView.getVideoListDataSuccess(modle);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getVideoListDataFail("code:" + code + " msg:" + msg);
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public LessonList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(LessonList_Paramet paramet) {
        this.paramet = paramet;
    }
}