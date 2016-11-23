package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.otherbean.CoverDirBean;
import com.laichushu.book.ui.activity.CoverDirActivity;
import com.laichushu.book.ui.activity.CoverListActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 封面模版文件夹
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirAdapter extends RecyclerView.Adapter<CoverDirAdapter.CoverDirViewHolder> {
    private ArrayList<CoverDirBean> mData;
    private CoverDirActivity mActivity;

    public CoverDirAdapter(ArrayList<CoverDirBean> mData, CoverDirActivity mActivity) {
        this.mActivity = mActivity;
        this.mData = mData;
    }

    @Override
    public CoverDirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_coverdir);
        return new CoverDirViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CoverDirViewHolder holder, final int position) {
        CoverDirBean bean = mData.get(position);
        final String name = bean.getName();
        int drawableId = bean.getDrawableId();
        holder.tv_imgname.setText(name);
        GlideUitl.loadImg(mActivity,drawableId,holder.iv_cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type",position+1+"");
                bundle.putString("title",name);
                bundle.putString("bookname",mActivity.getBookname());
                UIUtil.openActivity(mActivity,CoverListActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class CoverDirViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_imgname;
        private ImageView iv_cover;
        private View itemView;

        public CoverDirViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tv_imgname = (TextView) itemView.findViewById(R.id.tv_imgname);
        }
    }
}
