package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 课程 - 文档
 * Created by wangtong on 2017/1/6.
 */

public class FindClassDocAdapter extends RecyclerView.Adapter<FindClassDocAdapter.ViewHolder> {
    private MvpFragment2 mFragment;

    public FindClassDocAdapter(MvpFragment2 mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_class_document);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoIv;
        private TextView nameTv;
        private TextView authorTv;

        public ViewHolder(View itemView) {
            super(itemView);
            photoIv = (ImageView) itemView.findViewById(R.id.iv_photo);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            authorTv = (TextView) itemView.findViewById(R.id.tv_author);
        }
    }
}
