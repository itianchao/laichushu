package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.write.draftmodle.DraftModle;
import com.laichushu.book.mvp.write.draftmodle.DraftModlePresenter;
import com.laichushu.book.ui.activity.DraftModleActivity;
import com.laichushu.book.ui.activity.NopublishBookActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 草稿列表
 * Created by wangtong on 2016/11/18.
 */

public class DraftListAdapter extends RecyclerView.Adapter<DraftListAdapter.DraftListViewHolder> {

    private boolean isGone = false;
    private DraftModleActivity mActivity;
    private ArrayList<DraftModle.DataBean> mData;
    private DraftModlePresenter mvpPresenter;

    public DraftListAdapter(DraftModleActivity draftModleActivity, ArrayList<DraftModle.DataBean> mData, DraftModlePresenter mvpPresenter) {
        this.mActivity = draftModleActivity;
        this.mData = mData;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public DraftListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_draft_list);
        return new DraftListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DraftListViewHolder holder, final int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.itemView.setLayoutParams(params);
        final DraftModle.DataBean dataBean = mData.get(position);
        if (isGone) {
            holder.deleteIv.setVisibility(View.VISIBLE);
            holder.renameTv.setVisibility(View.INVISIBLE);
            holder.reviseTv.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvpPresenter.openDeleteDialog(dataBean.getId(), position);
                }
            });
            holder.deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvpPresenter.openDeleteDialog(dataBean.getId(), position);
                }
            });
        } else {
            holder.deleteIv.setVisibility(View.GONE);
            holder.renameTv.setVisibility(View.VISIBLE);
            holder.reviseTv.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", dataBean.getName());
                    bundle.putString("path", dataBean.getContentUrlApp());
                    bundle.putString("content", dataBean.getContent());
                    bundle.putString("name", dataBean.getName());
                    bundle.putString("articleId", mActivity.getArticleId());
                    UIUtil.openActivity(mActivity, NopublishBookActivity.class, bundle);
                }
            });
            holder.nameTv.setText(dataBean.getName());
            holder.renameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvpPresenter.openRameDialog(dataBean.getId(), position, dataBean.getName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class DraftListViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;
        private TextView renameTv;
        private TextView reviseTv;
        private ImageView deleteIv;
        private View itemView;

        public DraftListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            renameTv = (TextView) itemView.findViewById(R.id.tv_rename);
            reviseTv = (TextView) itemView.findViewById(R.id.tv_revise);
            deleteIv = (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }

    public boolean isGone() {
        return isGone;
    }

    public void setGone(boolean gone) {
        isGone = gone;
    }

    public ArrayList<DraftModle.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<DraftModle.DataBean> mData) {
        this.mData = mData;
    }
}
