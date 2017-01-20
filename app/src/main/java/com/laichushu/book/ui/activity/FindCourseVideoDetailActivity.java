package com.laichushu.book.ui.activity;

import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailModle;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailPresenter;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.Base64Utils;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ShareUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;

/**
 * 发现 - 课程 - 视频详情
 * Created by wangtong on 2017/1/6.
 */

public class FindCourseVideoDetailActivity extends MvpActivity2<VideoDetailPresenter> implements VideoDetailView, View.OnClickListener {

    private ImageView backIv;
    private TextView titleTv;
    private ImageView downloadIv, collectionIv, shareIv;
    private RadioButton briefRbn, pdfRbn, noteRbn, commentRbn, aboutRbn;
    public int index = -1;
    private String lessonId;
    private boolean isFrist = true;
    private VideoDetailModle.DataBean mdata;
    private String logoUrl;
    private String introduce;
    private String title;
    private LinearLayout contentLay;
    private String ccVideoId;

    public LinearLayout getContentLay() {
        return contentLay;
    }

    @Override
    protected VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findclassvideodetail);
        contentLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_content);//返回按钮
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
                String type;
                if (mdata.getIsCollect().equals("2")) {//未收藏
                    type = "0";
                }else {
                    type = "1";
                }
                mvpPresenter.collectSave(lessonId,type);
                break;
            case R.id.iv_share://分享
                String linkUrl= Base64Utils.getStringUrl(lessonId, ConstantValue.SHARE_TYPR_COURSE);
                ShareUtil.showShare(mActivity, linkUrl,linkUrl,logoUrl,introduce,title);
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
            if (mdata.getIsCollect().equals("2")) {//未收藏
                GlideUitl.loadImg(mActivity,R.drawable.icon_praise_no,collectionIv);
            }else {//已收藏
                GlideUitl.loadImg(mActivity,R.drawable.icon_praise_yes2x,collectionIv);
            }
            if (isFrist){
                onClick(briefRbn);
                isFrist = false;
            }
            logoUrl = mdata.getThumbUrl();
            introduce = mdata.getRemarks();
            title = mdata.getName();
            ccVideoId = mdata.getCcVideoId();
            mvpPresenter.replaceFrameLayout(ccVideoId,false);//替换视频播放器
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
    /**
     * 收藏 or 取消收藏 成功
     * @param model
     * @param type 0 收藏 1 取消
     */
    @Override
    public void loadCollectDataSuccess(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {//收藏
                ToastUtil.showToast("收藏成功");
                mdata.setIsCollect("1");
                GlideUitl.loadImg(mActivity, R.drawable.icon_praise_yes2x, collectionIv);
            }else {//取消
                ToastUtil.showToast("取消收藏成功");
                mdata.setIsCollect("2");
                GlideUitl.loadImg(mActivity, R.drawable.icon_praise_no, collectionIv);
            }
        }else {
            if (type.equals("0")) {//收藏
                ToastUtil.showToast("收藏失败");
            }else {//取消
                ToastUtil.showToast("取消收藏失败");
            }
        }
    }

    /**
     * 收藏 or 取消收藏 失败
     * @param msg
     * @param type 0 收藏 1 取消
     */
    @Override
    public void loadCollectDataFail(String msg, String type) {
        if (type.equals("0")) {//收藏
            ToastUtil.showToast("收藏失败");
        }else {//取消
            ToastUtil.showToast("取消收藏失败");
        }
    }

    /**
     * 重新加载按钮
     */
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止shareSDK
        ShareSDK.stopSDK(mActivity);
    }
    /**
     * 接口回调
     */
    public interface MyListener {
        void onTouchEvent(KeyEvent event);

        void onBackPressed();
    }

    /**
     * 保存MyTouchListener接口的列表
     */
    public ArrayList<MyListener> myTouchListeners = new ArrayList<>();
    public ArrayList<MyListener> myBackListeners = new ArrayList<>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的方法
     *
     * @param listener
     */
    public void registerMyTouchListener(MyListener listener) {
        myTouchListeners.add(listener);
    }

    public void registerMyBackListener(MyListener listener) {
        myBackListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的方法
     *
     * @param listener
     */
    public void unRegisterMyTouchListener(MyListener listener) {
        myTouchListeners.remove(listener);
    }

    public void unRegisterMyBackListener(MyListener listener) {
        myBackListeners.remove(listener);
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent ev) {
        for (MyListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchKeyEvent(ev);
    }
    // 获得当前屏幕的方向
    private boolean isPortrait() {
        int mOrientation = this.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        if (isPortrait()) {
            super.onBackPressed();
        } else {
            for (MyListener listener : myBackListeners) {
                listener.onBackPressed();
            }
        }
    }

}
