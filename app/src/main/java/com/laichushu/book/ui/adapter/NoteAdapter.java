package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 课程 - 笔记
 * Created by wangtong on 2017/1/9.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_class_note);
        return new NoteAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTv;
        private TextView contentTv;
        private ImageView editIv;
        private TextView titleTv;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            timeTv = (TextView) itemView.findViewById(R.id.tv_time);
            contentTv = (TextView) itemView.findViewById(R.id.tv_content);
            editIv = (ImageView) itemView.findViewById(R.id.iv_edit);
        }
    }
}
