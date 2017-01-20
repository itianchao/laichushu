package com.laichushu.book.mvp.find.coursera.documentdetail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.bean.netbean.LessonDetail_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.retrofit.ApiDownBack;
import com.laichushu.book.ui.activity.FindCourseDocDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.CourseAppraiseFragment;
import com.laichushu.book.ui.fragment.CourseIntroFragment;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 发现 - 课程 - 文档详情 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class DocDetailPresenter extends BasePresenter<DocDetailView> {
    private FindCourseDocDetailActivity mActivity;
    private CourseIntroFragment courseIntro;
    private CourseAppraiseFragment courseAppraise;
    private String userId = ConstantValue.USERID;
    private String operateType = ConstantValue.OPERATE_TYPE2;
    private String sourceType = ConstantValue.COLLECTCOURSE_TYPE;

    public DocDetailPresenter(DocDetailView view) {
        attachView(view);
        mActivity = (FindCourseDocDetailActivity) view;
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
                if (courseAppraise == null) {
                    courseAppraise = new CourseAppraiseFragment();
                    bundle.putInt("lessonId", Integer.parseInt(mActivity.getMdata().getId()));
                    bundle.putBoolean("isComment", mActivity.getMdata().isComment());
                    courseAppraise.setArguments(bundle);
                    transaction.add(R.id.fay_content, courseAppraise);
                } else {
                    transaction.show(courseAppraise);
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
        if (courseAppraise != null) transaction.hide(courseAppraise);
    }

    /**
     * 加载文档详情页接口 数据
     *
     * @param lessonId
     */
    public void loadVideoDetailData(String lessonId) {
        LoggerUtil.e("文档详情页");
        LessonDetail_Paramet paramet = new LessonDetail_Paramet(lessonId, userId, operateType);
        addSubscription(apiStores.getLessonDetail(paramet), new ApiCallback<VideoDetailModle>() {
            @Override
            public void onSuccess(VideoDetailModle model) {
                mvpView.loadDocDetailDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.loadDocDetailDataFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 收藏
     */
    public void collectSave(String sourceId, final String type) {
        mActivity.showProgressDialog();
        LoggerUtil.e("收藏");
        CollectSave_Paramet paramet = new CollectSave_Paramet(userId, sourceId, sourceType, type);
        addSubscription(apiStores.collectSave(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mActivity.dismissProgressDialog();
                mvpView.loadCollectDataSuccess(model, type);
            }

            @Override
            public void onFailure(int code, String msg) {
                mActivity.dismissProgressDialog();
                mvpView.loadCollectDataFail(code + "|" + msg, type);
            }

            @Override
            public void onFinish() {
                mActivity.dismissProgressDialog();
            }
        });
    }

    /**
     * 下载文件
     *
     * @param path
     * @param name
     */
    public void downloadPdf(String path, final String name) {
        mActivity.showProgressDialog("下载中...");
        Call<ResponseBody> call = apiStores.downloadFile(path);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = ApiDownBack.writeResponseBodyToDisk(response.body(), name, "pdf");
                    if (writtenToDisk) {
                        mActivity.dismissProgressDialog();
                        String path = ConstantValue.LOCAL_PATH.SD_PATH + name + ".pdf";
                        //下载成功
                        ToastUtil.showToast("下载成功");
                        mActivity.downloadTv.setText("观看");
                    } else {
                        mActivity.dismissProgressDialog();
                        ToastUtil.showToast("下载失败");
                    }
                } else {
                    mActivity.dismissProgressDialog();
                    ToastUtil.showToast("下载失败");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mActivity.dismissProgressDialog();
                ToastUtil.showToast("下载失败");
            }
        });
    }
}