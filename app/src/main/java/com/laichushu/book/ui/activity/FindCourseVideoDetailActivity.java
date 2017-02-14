package com.laichushu.book.ui.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bokecc.sdk.mobile.download.Downloader;
import com.bokecc.sdk.mobile.exception.ErrorCode;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.otherbean.DownloadInfo;
import com.laichushu.book.global.CCDownloadFactory;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailModle;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailPresenter;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.cc.ConfigUtil;
import com.laichushu.book.ui.cc.DataSet;
import com.laichushu.book.ui.cc.MediaUtil;
import com.laichushu.book.ui.cc.ParamsUtil;
import com.laichushu.book.ui.cc.download.DownloadService;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.Base64Utils;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ShareUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    private boolean isLocalPlay;
    private DownloadedReceiver receiver;
    public DownloadService.DownloadBinder binder;
    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("service disconnected", name + "");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (DownloadService.DownloadBinder) service;
        }
    };
    private Intent service;

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
        receiver = new DownloadedReceiver();
        registerReceiver(receiver, new IntentFilter(ConfigUtil.ACTION_DOWNLOADING));
        service = new Intent(this, DownloadService.class);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back://退出
                finish();
                break;
            case R.id.iv_download://下载
                List<DownloadInfo> downloadInfos = DataSet.getDownloadInfos();
                for (DownloadInfo downloadInfo : downloadInfos) {
//                    downloadInfo
                }
                for (String id : CCDownloadFactory.downloaderHashMap.keySet()) {
                    if (id.equals(ccVideoId)){
                        ToastUtil.showToast("视频下载中");
                        return;
                    }
                }
                if (isLocalPlay){
                    ToastUtil.showToast("视频已下载");
                }else {
                    ToastUtil.showToast("开始下载视频");
                    mvpPresenter.starDownload(ccVideoId);
                }
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
                String shareContent = "#来出书为您推荐"+titleTv.getText().toString().trim()+"#";
                String linkUrl= Base64Utils.getStringUrl(lessonId, ConstantValue.SHARE_TYPR_COURSE);
                ShareUtil.showShare(mActivity, linkUrl,shareContent,logoUrl,introduce,title);
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
                GlideUitl.loadImg(mActivity,R.drawable.icon_uncollect,collectionIv);
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
//            ccVideoId = mdata.getCcVideoId();
            ccVideoId = "920DA2A620CC9A459C33DC5901307461";

            if (!TextUtils.isEmpty(ccVideoId)){
                File file = MediaUtil.createFile(ccVideoId);
                if (file.exists()){
                    isLocalPlay = true;
                }else {
                    isLocalPlay = false;
                }
            }
            mvpPresenter.replaceFrameLayout(ccVideoId, isLocalPlay);//替换视频播放器
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
                GlideUitl.loadImg(mActivity, R.drawable.icon_uncollect, collectionIv);
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
    private class DownloadedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 若下载出现异常，提示用户处理
            int errorCode = intent.getIntExtra("errorCode", ParamsUtil.INVALID);
            if (errorCode == ErrorCode.NETWORK_ERROR.Value()) {
                Toast.makeText(context, "网络异常，请检查", Toast.LENGTH_SHORT).show();
            } else if (errorCode == ErrorCode.PROCESS_FAIL.Value()) {
                Toast.makeText(context, "下载失败，请重试", Toast.LENGTH_SHORT).show();
            } else if (errorCode == ErrorCode.INVALID_REQUEST.Value()) {
                Toast.makeText(context, "下载失败，请检查帐户信息", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        //停止shareSDK
        ShareSDK.stopSDK(mActivity);

        if (serviceConnection != null) {
             unbindService(serviceConnection);
        }

        unregisterReceiver(receiver);
        super.onDestroy();
    }

}
