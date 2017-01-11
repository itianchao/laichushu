package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.otherbean.SpeakListModle;
import com.laichushu.book.ui.activity.PDFActivity;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 讲义列表
 * Created by wangtong on 2017/1/11.
 */

public class CourseraSpeakAdapter extends RecyclerView.Adapter<CourseraSpeakAdapter.ViewHolder> {
    private ArrayList<SpeakListModle.DataBean.HandOutsListBean> mData;
    private BaseActivity mActivity;

    public CourseraSpeakAdapter(BaseActivity mActivity, ArrayList<SpeakListModle.DataBean.HandOutsListBean> mData) {
        this.mData = mData;
        this.mActivity = mActivity;
    }

    @Override
    public CourseraSpeakAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_class_video_speak);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseraSpeakAdapter.ViewHolder holder, final int position) {
        holder.titleTv.setText(mData.get(position).getName());
        final SpeakListModle.DataBean.HandOutsListBean bean = mData.get(position);
        if (bean.isSelect()) {
            holder.titleTv.setTextColor(UIUtil.getColor(R.color.global));
        } else {
            holder.titleTv.setTextColor(UIUtil.getColor(R.color.edit2));
        }
        holder.titleTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < mData.size(); i++) {
                    if (i == position) {
                        mData.get(i).setSelect(true);
                    } else {
                        mData.get(i).setSelect(false);
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString("path", bean.getUrl());
                bundle.putString("name", bean.getHandOutsId());
                bundle.putString("title", bean.getName());
                UIUtil.openActivity(mActivity, PDFActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setmData(ArrayList<SpeakListModle.DataBean.HandOutsListBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
