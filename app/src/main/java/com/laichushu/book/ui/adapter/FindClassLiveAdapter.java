package com.laichushu.book.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bokecc.sdk.mobile.live.DWLive;
import com.bokecc.sdk.mobile.live.DWLiveLoginListener;
import com.bokecc.sdk.mobile.live.Exception.DWLiveException;
import com.bokecc.sdk.mobile.live.pojo.RoomInfo;
import com.bokecc.sdk.mobile.live.pojo.TemplateInfo;
import com.bokecc.sdk.mobile.live.pojo.Viewer;
import com.bokecc.sdk.mobile.live.replay.DWLiveReplay;
import com.bokecc.sdk.mobile.live.replay.DWLiveReplayLoginListener;
import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.ui.activity.FindCourseDocDetailActivity;
import com.laichushu.book.ui.activity.FindCourseVideoDetailActivity;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.cc.ConfigUtil;
import com.laichushu.book.ui.cc.LiveRoomActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 发现 - 课程 - 直播 适配器
 * Created by wangtong on 2017/1/6.
 */

public class FindClassLiveAdapter extends RecyclerView.Adapter<FindClassLiveAdapter.ViewHolder> {
    private MvpActivity2 mActivity;
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData = new ArrayList<>();
    private DWLive dwLive = DWLive.getInstance();

    public FindClassLiveAdapter(MvpActivity2 mActivity, ArrayList mData) {
        this.mActivity = mActivity;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_class_video);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.itemView.setLayoutParams(params);
        final CourseraModle.DataBean.LessonListBean bean = mData.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomId = bean.getBroadcastId();
                String userId = ConfigUtil.USERID;
                String viewerName = SharePrefManager.getNickName();
                LoggerUtil.e("roomId:"+roomId+" userId:"+userId+" viewerName:"+viewerName);
                dwLive.setDWLiveLoginParams(dwLiveLoginListener, userId, roomId, viewerName);
                dwLive.startLogin();
            }
        });
        holder.nameTv.setText(bean.getBroadcastName());
        holder.briefTv.setText(bean.getBroadcastDesc());
        holder.lookTv.setText(bean.getClickNum() + "");
        holder.downloadTv.setText(bean.getDownloadNum() + "");
        holder.likeTv.setText(bean.getCollectionNum() + "");
        GlideUitl.loadImg(mActivity, bean.getThumbUrl(), holder.photoIv);
        GlideUitl.loadImg(mActivity, R.drawable.icon_video, holder.typeIv);
        holder.markTv.setText(bean.getAverageStar() + "分");
        holder.mRatingBar.setRating((float) bean.getAverageStar());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoIv;//图片
        private ImageView typeIv;//类型
        private TextView nameTv;//标题
        private TextView briefTv;//简介
        private TextView lookTv;//浏览数
        private TextView downloadTv;//下载数
        private TextView likeTv;//收藏
        private RatingBar mRatingBar;//星
        private TextView markTv;//评分

        public ViewHolder(View itemView) {
            super(itemView);
            photoIv = (ImageView) itemView.findViewById(R.id.iv_photo);
            typeIv = (ImageView) itemView.findViewById(R.id.iv_type);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            briefTv = (TextView) itemView.findViewById(R.id.tv_brief);
            lookTv = (TextView) itemView.findViewById(R.id.tv_look);
            downloadTv = (TextView) itemView.findViewById(R.id.tv_download);
            likeTv = (TextView) itemView.findViewById(R.id.tv_like);
            markTv = (TextView) itemView.findViewById(R.id.tv_mark);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.ratbar_num);
        }
    }

    public void setmData(ArrayList<CourseraModle.DataBean.LessonListBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    private DWLiveLoginListener dwLiveLoginListener = new DWLiveLoginListener() {

        @Override
        public void onLogin(TemplateInfo info, Viewer viewer, RoomInfo roomInfo) {
            Bundle bundle = new Bundle();
            bundle.putString("chat", info.getChatView());
            bundle.putString("pdf", info.getPdfView());
            bundle.putString("qa", info.getQaView());
            bundle.putInt("dvr", roomInfo.getDvr());
            UIUtil.openActivity(mActivity, LiveRoomActivity.class,bundle);
        }

        @Override
        public void onException(DWLiveException exception) {
            Message msg = new Message();
            msg.what = -1;
            msg.obj = exception.getMessage();
            mHandler.sendMessage(msg);
        }
    };
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case -1:
                    String failLiveReason = (String) msg.obj;
                    Toast.makeText(mActivity, failLiveReason, Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    String failReplayReason = (String) msg.obj;
                    Toast.makeText(mActivity, failReplayReason, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
