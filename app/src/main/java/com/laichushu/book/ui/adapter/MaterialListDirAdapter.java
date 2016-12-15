package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.sourcematerialdir.SourceMaterialDirModle;
import com.laichushu.book.mvp.sourcematerialdir.SourceMaterialDirPresenter;
import com.laichushu.book.ui.activity.SourceMaterialActivity;
import com.laichushu.book.ui.activity.SourceMaterialDirActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 素材文件夹列表
 * Created by wangtong on 2016/11/18.
 */

public class MaterialListDirAdapter extends RecyclerView.Adapter<MaterialListDirAdapter.DraftListViewHolder> {

    private boolean isGone = false;
    private SourceMaterialDirActivity mActivity;
    private ArrayList<SourceMaterialDirModle.DataBean> mData;
    private SourceMaterialDirPresenter mvpPresenter;

    public MaterialListDirAdapter(SourceMaterialDirActivity mActivity, ArrayList<SourceMaterialDirModle.DataBean> mData, SourceMaterialDirPresenter mvpPresenter) {
        this.mActivity = mActivity;
        this.mData = mData;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public DraftListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_materia_list);
        return new DraftListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DraftListViewHolder holder, final int position) {

        if (isGone) {
            holder.deleteIv.setVisibility(View.VISIBLE);
            holder.renameTv.setVisibility(View.INVISIBLE);
        } else {
            holder.deleteIv.setVisibility(View.GONE);
            holder.renameTv.setVisibility(View.VISIBLE);
        }
        final SourceMaterialDirModle.DataBean dataBean = mData.get(position);
        holder.nameTv.setText(dataBean.getName());
        holder.renameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.openRenameDialog(dataBean.getId(),position);
            }
        });
        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.openDeleteDialog(dataBean.getId(),position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/11/22 跳转素材目录
                Bundle bundle = new Bundle();
                bundle.putString("title", dataBean.getName());
                bundle.putString("parentId", dataBean.getId());
                bundle.putString("articleId", dataBean.getArticleId());
                bundle.putString("dir", dataBean.getName());
                UIUtil.openActivity(mActivity, SourceMaterialActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class DraftListViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;
        private TextView renameTv;
        private ImageView deleteIv;
        private View itemView;

        public DraftListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            renameTv = (TextView) itemView.findViewById(R.id.tv_rename);
            deleteIv = (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }

    public boolean isGone() {
        return isGone;
    }

    public void setGone(boolean gone) {
        isGone = gone;
    }

    public ArrayList<SourceMaterialDirModle.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<SourceMaterialDirModle.DataBean> mData) {
        this.mData = mData;
    }
}
