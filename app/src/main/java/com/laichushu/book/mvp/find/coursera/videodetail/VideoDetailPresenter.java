package com.laichushu.book.mvp.find.coursera.videodetail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.LessonDetail_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindClassVideoDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.CourseAboutFragment;
import com.laichushu.book.ui.fragment.CourseAppraiseFragment;
import com.laichushu.book.ui.fragment.CourseIntroFragment;
import com.laichushu.book.ui.fragment.CourseNoteFragment;
import com.laichushu.book.ui.fragment.CourseSpeakFragment;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 发现 - 课程 - 视频详情 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class VideoDetailPresenter extends BasePresenter<VideoDetailView> {
    private FindClassVideoDetailActivity mActivity;
    private CourseIntroFragment courseIntro;
    private CourseSpeakFragment courseSpeak;
    private CourseNoteFragment courseNote;
    private CourseAppraiseFragment courseAppraise;
    private CourseAboutFragment courseAbout;
    private String userId = ConstantValue.USERID;
    private String operateType = ConstantValue.OPERATE_TYPE2;

    public VideoDetailPresenter(VideoDetailView view) {
        attachView(view);
        mActivity = (FindClassVideoDetailActivity) view;
    }

    /**
     * 切换 fragment
     *
     * @param position
     */
    public void setTabSelection(int position) {
        //记录position
        mActivity.index = position;
        FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                if (courseIntro == null) {
                    courseIntro = new CourseIntroFragment();
                    bundle.putString("brief", mActivity.getMdata().getRemarks() == null ? "" : mActivity.getMdata().getRemarks());
                    courseIntro.setArguments(bundle);
                    transaction.add(R.id.fay_content, courseIntro);
                } else {
                    transaction.show(courseIntro);
                }
                break;
            case 1:
                if (courseSpeak == null) {
                    courseSpeak = new CourseSpeakFragment();
                    bundle.putString("lessonId", mActivity.getMdata().getId());
                    courseIntro.setArguments(bundle);
                    transaction.add(R.id.fay_content, courseSpeak);
                } else {
                    transaction.show(courseSpeak);
                }
                break;
            case 2:
                if (courseNote == null) {
                    courseNote = new CourseNoteFragment();
                    bundle.putString("lessonId", mActivity.getMdata().getId());
                    courseIntro.setArguments(bundle);
                    transaction.add(R.id.fay_content, courseNote);
                } else {
                    transaction.show(courseNote);
                }
                break;
            case 3:
                if (courseAppraise == null) {
                    courseAppraise = new CourseAppraiseFragment();
                    bundle.putString("lessonId", mActivity.getMdata().getId());
                    courseIntro.setArguments(bundle);
                    transaction.add(R.id.fay_content, courseAppraise);
                } else {
                    transaction.show(courseNote);
                }
                break;
            case 4:
                if (courseAbout == null) {
                    courseAbout = new CourseAboutFragment();
                    bundle.putString("lessonId", mActivity.getMdata().getId());
                    courseIntro.setArguments(bundle);
                    transaction.add(R.id.fay_content, courseAbout);
                } else {
                    transaction.show(courseAbout);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (courseIntro != null) transaction.hide(courseIntro);
        if (courseSpeak != null) transaction.hide(courseSpeak);
        if (courseNote != null) transaction.hide(courseNote);
        if (courseAppraise != null) transaction.hide(courseAppraise);
        if (courseAbout != null) transaction.hide(courseAbout);
    }

    /**
     * 加载视频详情页接口 数据
     *
     * @param lessonId
     */
    public void loadVideoDetailData(String lessonId) {
        LoggerUtil.e("视频详情页");
        LessonDetail_Paramet paramet = new LessonDetail_Paramet(lessonId, userId, operateType);
        addSubscription(apiStores.getLessonDetail(paramet), new ApiCallback<VideoDetailModle>() {
            @Override
            public void onSuccess(VideoDetailModle model) {
                mvpView.loadVideoDetailDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.loadVideoDetailDataFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}