package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.findservicemainpage.FindServiceMainPagePresenter;
import com.laichushu.book.ui.activity.CommentDetailActivity;
import com.laichushu.book.ui.activity.FindServerMainPageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2016/12/30.
 */

public class ServiceCommentListAdapter extends RecyclerView.Adapter<ServiceCommentListAdapter.CommentViewHolder> {
    private FindServerMainPageActivity mActivity;
    private ArrayList<CommentDetailModle.DataBean> mData;
    private FindServiceMainPagePresenter mvpPresenter;

    public ServiceCommentListAdapter(FindServerMainPageActivity mActivity, ArrayList<CommentDetailModle.DataBean> mData, FindServiceMainPagePresenter mvpPresenter) {
        this.mActivity = mActivity;
        this.mData = mData;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void refreshAdapter(List<CommentDetailModle.DataBean> listData) {
        mData.clear();
        if (listData.size() > 0) {
            mData.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public ServiceCommentListAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_comment_connet);
        return new ServiceCommentListAdapter.CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ServiceCommentListAdapter.CommentViewHolder holder, int position) {
        final CommentDetailModle.DataBean dataBean = mData.get(position);
        GlideUitl.loadRandImg(mActivity, dataBean.getPhoto(), holder.headIv);//头像
        holder.nameTv.setText(dataBean.getNickName());//用户名
        holder.contentTv.setText(dataBean.getContent());//评论内容
        holder.timeTv.setText(dataBean.getCommentTime());//创建时间
        holder.likeTv.setText(dataBean.getLikeNum() + "");//喜欢人数
        holder.numberTv.setText(dataBean.getReplyNum() + "");//回复人数
        if (dataBean.isIsLike()) {
            GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, holder.likeIv);
        } else {
            GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, holder.likeIv);
        }
//        holder.numberTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("commentId", dataBean.getSourceId());
//                UIUtil.openActivity(mActivity,CommentSendActivity.class,bundle);
//            }
//        });
        holder.likeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.isIsLike()) {
                    mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "1");
//                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, holder.likeIv);
                    dataBean.setIsLike(false);
                    dataBean.setLikeNum(dataBean.getLikeNum() - 1);
                    holder.likeTv.setText(dataBean.getLikeNum() + "");
                } else {
                    mvpPresenter.saveScoreLikeData(dataBean.getSourceId(), "0");
//                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, holder.likeIv);
                    dataBean.setIsLike(true);
                    dataBean.setLikeNum(dataBean.getLikeNum() + 1);
                    holder.likeTv.setText(dataBean.getLikeNum() + "");
                }
                notifyDataSetChanged();
            }
        });
        holder.inIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/11/4  去评论详情
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", dataBean);
                UIUtil.openActivity(mActivity, CommentDetailActivity.class, bundle);
            }
        });
    }

    public ArrayList<CommentDetailModle.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<CommentDetailModle.DataBean> mData) {
        this.mData = mData;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        public ImageView headIv;
        public TextView nameTv;
        public TextView contentTv;
        public TextView timeTv;
        public ImageView likeIv;
        public TextView likeTv;
        public TextView numberTv;
        public ImageView inIv;
        public View commentItemView;
        public ImageView commentIv;

        public CommentViewHolder(View commentItemView) {
            super(commentItemView);
            this.commentItemView = commentItemView;
            headIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_head);
            nameTv = (TextView) commentItemView.findViewById(R.id.tv_comment_name);
            contentTv = (TextView) commentItemView.findViewById(R.id.tv_comment_content);
            timeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_time);
            likeIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_like);
            likeTv = (TextView) commentItemView.findViewById(R.id.tv_comment_like);
            numberTv = (TextView) commentItemView.findViewById(R.id.tv_comment_number);
            commentIv = (ImageView) commentItemView.findViewById(R.id.iv_comment);
            inIv = (ImageView) commentItemView.findViewById(R.id.iv_comment_in);
        }
    }
}


