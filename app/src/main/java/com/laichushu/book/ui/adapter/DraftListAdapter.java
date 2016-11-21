package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.draftmodle.DraftModle;
import com.laichushu.book.ui.activity.DraftModleActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 草稿列表
 * Created by wangtong on 2016/11/18.
 */

public class DraftListAdapter extends RecyclerView.Adapter<DraftListAdapter.DraftListViewHolder> {

    private boolean isGone = false;
    private DraftModleActivity draftModleActivity;
    private ArrayList<DraftModle.DataBean> mData;

    public DraftListAdapter(DraftModleActivity draftModleActivity, ArrayList<DraftModle.DataBean> mData) {
        this.draftModleActivity = draftModleActivity;
        this.mData = mData;
    }

    @Override
    public DraftListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_draft_list);
        return new DraftListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DraftListViewHolder holder, int position) {

        if (isGone) {
            holder.deleteIv.setVisibility(View.GONE);
        } else {
            holder.deleteIv.setVisibility(View.VISIBLE);
        }
        DraftModle.DataBean dataBean = mData.get(position);
        holder.nameTv.setText(dataBean.getName());
        holder.renameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.reviseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DraftListViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;
        private TextView renameTv;
        private TextView reviseTv;
        private ImageView deleteIv;

        public DraftListViewHolder(View itemView) {
            super(itemView);

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
