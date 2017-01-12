package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailModle;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailPresenter;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 课程 - 视频详情
 * Created by wangtong on 2017/1/6.
 */

public class FindClassVideoDetailActivity extends MvpActivity2<VideoDetailPresenter> implements VideoDetailView, View.OnClickListener {

    private ImageView backIv;
    private TextView titleTv;
    private ImageView downloadIv, collectionIv, shareIv;
    private RadioButton briefRbn, pdfRbn, noteRbn, commentRbn, aboutRbn;
    public int index = -1;
    private String lessonId;
    private boolean isFrist = true;
    private VideoDetailModle.DataBean mdata;

    @Override
    protected VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findclassvideodetail);
        backIv = (ImageView) mSuccessView.findViewById(R.id.iv_back);//返回按钮
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);//标题
        downloadIv = (ImageView) mSuccessView.findViewById(R.id.iv_download);//下载按钮
        collectionIv = (ImageView) mSuccessView.findViewById(R.id.iv_collection);//收藏
        shareIv = (ImageView) mSuccessView.findViewById(R.id.iv_share);//分享
        briefRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_brief);//简介
        pdfRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_pdf);//讲义
        noteRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_note);//笔记
        commentRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_comment);//评论
        aboutRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_about);//相关
        return mSuccessView;
    }

    @Override
    protected void initData() {
        backIv.setOnClickListener(this);
        downloadIv.setOnClickListener(this);
        collectionIv.setOnClickListener(this);
        briefRbn.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        pdfRbn.setOnClickListener(this);
        noteRbn.setOnClickListener(this);
        commentRbn.setOnClickListener(this);
        aboutRbn.setOnClickListener(this);
        lessonId = getIntent().getStringExtra("lessonId");
        mvpPresenter.loadVideoDetailData(lessonId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back://退出
                finish();
                break;
            case R.id.iv_download://下载
                break;
            case R.id.iv_collection://收藏
                break;
            case R.id.iv_share://分享
                break;
            case R.id.rbn_brief://简介
                if (index != 0) {
                    index = 0;
                    mvpPresenter.setTabSelection(index);
                }
                break;
            case R.id.rbn_pdf://讲义
                if (index != 1) {
                    index = 1;
                    mvpPresenter.setTabSelection(index);
                }
                break;
            case R.id.rbn_note://笔记
                if (index != 2) {
                    index = 2;
                    mvpPresenter.setTabSelection(index);
                }
                break;
            case R.id.rbn_comment://评论
                if (index != 3) {
                    index = 3;
                    mvpPresenter.setTabSelection(index);
                }
                break;
            case R.id.rbn_about://相关
                if (index != 4) {
                    index = 4;
                    mvpPresenter.setTabSelection(index);
                }
                break;
        }
    }

    @Override
    public void loadVideoDetailDataSuccess(VideoDetailModle model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            mdata = model.getData();
            titleTv.setText(mdata.getName());
            if (mdata.getIsCollect().equals("1")) {//收藏
                GlideUitl.loadImg(mActivity,R.drawable.icon_praise_yes2x,collectionIv);
            }else {//未收藏
                GlideUitl.loadImg(mActivity,R.drawable.icon_praise_no,collectionIv);
            }
            if (isFrist){
                onClick(briefRbn);
                isFrist = false;
            }
        }else {
            reloadErrorBtn();
            LoggerUtil.e(model.getErrMsg());
        }
    }

    @Override
    public void loadVideoDetailDataFail(String msg) {
        reloadErrorBtn();
        LoggerUtil.e(msg);
    }
    public void reloadErrorBtn(){
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                mvpPresenter.loadVideoDetailData(lessonId);
            }
        });
    }

    public VideoDetailModle.DataBean getMdata() {
        return mdata;
    }
}
