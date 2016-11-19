package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.utils.UIUtil;

/**
 * 草稿列表
 * Created by wangtong on 2016/11/18.
 */

public class DraftListAdapter extends RecyclerView.Adapter<DraftListAdapter.DraftListViewHolder>{

    private boolean isGone = false;
    @Override
    public DraftListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_draft_list);
        return new DraftListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DraftListViewHolder holder, int position) {

        if (isGone){
            holder.deleteIv.setVisibility(View.GONE);
        }else {
            holder.deleteIv.setVisibility(View.VISIBLE);
        }
        holder.numberTv.setText("");
        holder.nameTv.setText("");
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

        private TextView numberTv;
        private TextView nameTv;
        private TextView renameTv;
        private TextView reviseTv;
        private ImageView deleteIv;

        public DraftListViewHolder(View itemView) {
            super(itemView);
            numberTv = (TextView)itemView.findViewById(R.id.tv_number);
            nameTv = (TextView)itemView.findViewById(R.id.tv_name);
            renameTv = (TextView)itemView.findViewById(R.id.tv_rename);
            reviseTv = (TextView)itemView.findViewById(R.id.tv_revise);
            deleteIv = (ImageView)itemView.findViewById(R.id.iv_delete);
        }
    }

    public boolean isGone() {
        return isGone;
    }

    public void setGone(boolean gone) {
        isGone = gone;
    }
}
