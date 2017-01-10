package com.laichushu.book.mvp.find.coursera.video;

import android.text.TextUtils;

import com.laichushu.book.bean.netbean.LessonList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.FindClassVideoFragment;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 视频 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class FindClassVideoPresenter extends BasePresenter<FindClassVideoView> {

    private FindClassVideoFragment mFragment;
    private String lessonType = ConstantValue.VIDEO_PLAY_ON_DEMAND;
    private String fileType = ConstantValue.FILETYPE_VIDEO;
    private String pageNo = "1";
    private java.lang.String pageSize = ConstantValue.PAGESIZE1;
    private LessonList_Paramet paramet = new LessonList_Paramet(lessonType, fileType, pageNo, pageSize);

    public FindClassVideoPresenter(FindClassVideoView view) {
        attachView(view);
        mFragment = (FindClassVideoFragment) view;
    }

    /**
     * 点播视频列表
     */
    public void loadVideoList(boolean isRefrush) {

        if (!isRefrush && getParamet().getPageNo().equals("1") && (!TextUtils.isEmpty(getParamet().getLessonCategoryId()) || !TextUtils.isEmpty(getParamet().getSortWay()))) {
            mFragment.refreshPage(LoadingPager.PageState.STATE_LOADING);
        }
        LoggerUtil.e("发现 - 课程 - 视频列表");
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

    /**
     * 排行
     *
     * @param position
     */
    public void loadVideoList(int position) {
        getParamet().setLessonCategoryId("");
        getParamet().setPageNo("1");
        switch (position) {
            case 0:
                getParamet().setSortWay("");
                break;
            case 1:
                getParamet().setSortWay(ConstantValue.SORTWAY_CLICK);
                break;
            case 2:
                getParamet().setSortWay(ConstantValue.SORTWAY_DOWNLOAD);
                break;
            case 3:
                getParamet().setSortWay(ConstantValue.SORTWAY_COLLCETION);
                break;
        }
        loadVideoList(false);
    }

    /**
     * 分类
     *
     * @param lessonCategoryId
     */
    public void loadVideoList(String lessonCategoryId) {
        getParamet().setSortWay("");
        getParamet().setPageNo("1");
        if (lessonCategoryId.equals("默认分类")) {
            getParamet().setLessonCategoryId("");
        } else {
            getParamet().setLessonCategoryId(lessonCategoryId);
        }
        loadVideoList(false);
    }

    public LessonList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(LessonList_Paramet paramet) {
        this.paramet = paramet;
    }

    /**
     * 转换分类String 集合
     *
     * @param mCategoryList 分类集合
     */
    public ArrayList<String> fromArrayList(ArrayList<CourseraModle.DataBean.LessonCategoryListBean> mCategoryList) {

        ArrayList<String> mlist = new ArrayList<>();
        mlist.add("默认分类");
        for (CourseraModle.DataBean.LessonCategoryListBean bean : mCategoryList) {
            String name = bean.getLessonCategoryName();
            mlist.add(name);
        }
        return mlist;
    }

    /**
     * @param name  分类名字
     * @param mlist
     */
    public String findListByName(String name, ArrayList<CourseraModle.DataBean.LessonCategoryListBean> mlist) {
        for (CourseraModle.DataBean.LessonCategoryListBean bean : mlist) {
            if (bean.getLessonCategoryName().equals(name)) {
                return bean.getLessonCategoryId();
            }
        }
        return "默认分类";
    }
}