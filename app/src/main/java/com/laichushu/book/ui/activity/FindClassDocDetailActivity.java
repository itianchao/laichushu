package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.coursera.documentdetail.DocDetailPresenter;
import com.laichushu.book.mvp.find.coursera.documentdetail.DocDetailView;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailModle;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 课程 - 文档详情
 * Created by wangtong on 2017/1/6.
 */

public class FindClassDocDetailActivity extends MvpActivity2<DocDetailPresenter> implements DocDetailView, View.OnClickListener {

    private ImageView backIv,bookIv;
    private TextView titleTv,nameTv,downloadTv,categroyTv,timeTv;
    private ImageView collectionIv, shareIv;
    private RadioButton briefRbn,commentRbn;
    public int index = -1;
    private String lessonId;
    private boolean isFrist = true;
    private VideoDetailModle.DataBean mdata;

    @Override
    protected DocDetailPresenter createPresenter() {
        return new DocDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findclassdocdetail);
        backIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);//返回按钮
        bookIv = (ImageView) mSuccessView.findViewById(R.id.iv_book);//封面
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);//文档详情
        nameTv = (TextView) mSuccessView.findViewById(R.id.tv_name);//标题
        categroyTv = (TextView) mSuccessView.findViewById(R.id.tv_categroy);//分类
        timeTv = (TextView) mSuccessView.findViewById(R.id.tv_time);//入库时间
        downloadTv = (TextView) mSuccessView.findViewById(R.id.tv_download);//下载按钮
        collectionIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);//收藏
        shareIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);//分享
        briefRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_brief);//简介
        commentRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_comment);//评论
        return mSuccessView;
    }

    @Override
    protected void initData() {
        GlideUitl.loadImg(mActivity,R.drawable.icon_praise_no2x,collectionIv);
        GlideUitl.loadImg(mActivity,R.drawable.icon_share,shareIv);
        backIv.setOnClickListener(this);
        downloadTv.setOnClickListener(this);
        collectionIv.setOnClickListener(this);
        briefRbn.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        commentRbn.setOnClickListener(this);
        lessonId = getIntent().getStringExtra("lessonId");
        mvpPresenter.loadVideoDetailData(lessonId);
        titleTv.setText("文档详情");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://退出
                finish();
                break;
            case R.id.tv_download://下载
                break;
            case R.id.iv_title_other://收藏
                break;
            case R.id.iv_title_another://分享
                break;
            case R.id.rbn_brief://简介
                if (index != 0) {
                    index = 0;
                    mvpPresenter.setTabSelection(index);
                }
                break;
            case R.id.rbn_comment://评论
                if (index != 1) {
                    index = 1;
                    mvpPresenter.setTabSelection(index);
                }
                break;
        }
    }

    @Override
    public void loadDocDetailDataSuccess(VideoDetailModle model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            mdata = model.getData();
            nameTv.setText(mdata.getName());
            if (mdata.getIsCollect().equals("1")) {//收藏
                GlideUitl.loadImg(mActivity, R.drawable.icon_praise_yes2x, collectionIv);
            } else {//未收藏
                GlideUitl.loadImg(mActivity, R.drawable.icon_praise_no, collectionIv);
            }
            GlideUitl.loadImg(mActivity,model.getData().getThumbUrl(), bookIv);
            categroyTv.setText(model.getData().getLessonType());
            timeTv.setText(model.getData().getCreateDate());
            if (isFrist) {
                onClick(briefRbn);
                isFrist = false;
            }
        } else {
            reloadErrorBtn();
            LoggerUtil.e(model.getErrMsg());
        }
    }

    @Override
    public void loadDocDetailDataFail(String msg) {
        reloadErrorBtn();
        LoggerUtil.e(msg);
    }

    public void reloadErrorBtn() {
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
