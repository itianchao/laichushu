package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.msg.notice.NoticeModle;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 公告
 * Created by wangtong on 2016/11/26.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    private Activity mActivity;
    private ArrayList<NoticeModle.DataBean> mData;

    public NoticeAdapter(Activity mActivity, ArrayList<NoticeModle.DataBean> mData) {
        this.mActivity = mActivity;
        this.mData = mData;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_notice);
        return new NoticeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        NoticeModle.DataBean dataBean = mData.get(position);
        holder.itemView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
        holder.contentTv.setText(dataBean.getContent());
        holder.timeTv.setText(dataBean.getReleaseTime());
        holder.shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/11/26  分享
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        private ImageView shareIv;
        private TextView contentTv;
        private TextView timeTv;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            shareIv = (ImageView) itemView.findViewById(R.id.iv_share);
            contentTv = (TextView) itemView.findViewById(R.id.tv_content);
            timeTv = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
