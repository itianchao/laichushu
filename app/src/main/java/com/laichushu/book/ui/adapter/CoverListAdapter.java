package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.coverdir.CoverDirModle;
import com.laichushu.book.mvp.coverdir.CoverDirPresenter;
import com.laichushu.book.ui.activity.CoverListActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 封面模版列表
 * Created by wangtong on 2016/11/23.
 */

public class CoverListAdapter extends RecyclerView.Adapter<CoverListAdapter.CoverDirViewHolder> {

    private ArrayList<CoverDirModle.DataBean> mData;
    private CoverListActivity mActivity;
    private CoverDirPresenter mvpPresenter;

    public CoverListAdapter(ArrayList<CoverDirModle.DataBean> mData, CoverListActivity mActivity, CoverDirPresenter mvpPresenter) {
        this.mActivity = mActivity;
        this.mData = mData;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public CoverDirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_coverlist);
        return new CoverDirViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CoverDirViewHolder holder, final int position) {
        CoverDirModle.DataBean bean = mData.get(position);
        String imgName = bean.getImgName();
        String imgUrl = bean.getImgUrl();
        // TODO: 2016/11/23 作者名未确定先用id代替
        final String id = bean.getId();
        imgName = imgName.replace(".jpg", "");
        holder.tv_imgname.setText(imgName);
        holder.tv_imgauthor.setText(id);
        GlideUitl.loadImg(mActivity, imgUrl, holder.iv_cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.makePreviewCover(id, mActivity.getBookname(), mActivity.getType(), mActivity.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class CoverDirViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_imgname;
        private TextView tv_imgauthor;
        private ImageView iv_cover;
        private View itemView;

        public CoverDirViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tv_imgname = (TextView) itemView.findViewById(R.id.tv_imgname);
            tv_imgauthor = (TextView) itemView.findViewById(R.id.tv_imgauthor);
        }
    }

    public ArrayList<CoverDirModle.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<CoverDirModle.DataBean> mData) {
        this.mData = mData;
    }
}
