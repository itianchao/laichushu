package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.activity.DraftModleActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 写书
 * Created by wangtong on 2016/11/17.
 */

public class WriteBookAdapter extends RecyclerView.Adapter<WriteBookAdapter.WriteBookViewHolder> {
    private ArrayList<HomeHotModel.DataBean> mData;
    private Activity mActivity;

    public WriteBookAdapter(ArrayList<HomeHotModel.DataBean> mData, Activity mActivity) {
        this.mData = mData;
        this.mActivity = mActivity;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public WriteBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_newbook);
        return new WriteBookViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(WriteBookViewHolder holder, int position) {
        final HomeHotModel.DataBean dataBean = mData.get(position);
        GlideUitl.loadImg(mActivity, dataBean.getCoverUrl(), holder.bookIv);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //跳转图书详情页
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("bean", dataBean);
//                UIUtil.openActivity(mActivity, BookDetailActivity.class, bundle);
//            }
//        });
        holder.titleTv.setText(dataBean.getArticleName());
        holder.typeTv.setText(dataBean.getTopCategoryName());
        holder.authorTv.setText(dataBean.getAuthorName());
        holder.numRb.setRating(dataBean.getLevel());
        holder.commentTv.setText("(" + dataBean.getCommentNum() + "评论)");
        holder.wordTv.setText("约" + dataBean.getWordNum());
        holder.moneyTv.setText(dataBean.getAwardMoney() + "元");
        holder.rewardTv.setText("(" + dataBean.getAwardNum() + "人打赏)");
        holder.markTv.setText(dataBean.getScore() + "分");
        /**
         * 草稿
         */
        holder.draftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("articleId",dataBean.getArticleId());
                UIUtil.openActivity(mActivity, DraftModleActivity.class,bundle);
            }
        });
        /**
         * 素材
         */
        holder.materialTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 投稿
         */
        holder.submissionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 发表
         */
        holder.publishlTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 删除
         */
        holder.deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class WriteBookViewHolder extends RecyclerView.ViewHolder {

        private TextView draftTv;//草稿
        private TextView materialTv;//素材
        private TextView submissionTv;//投稿
        private TextView publishlTv;//发布
        private TextView deleteTv;//删除
        private ImageView bookIv;
        private TextView titleTv;
        private TextView typeTv;
        private RatingBar numRb;
        private TextView markTv;
        private TextView commentTv;
        private TextView authorTv;
        private TextView wordTv;
        private TextView moneyTv;
        private TextView rewardTv;
        private View itemView;

        public WriteBookViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            draftTv = (TextView) itemView.findViewById(R.id.tv_draft);
            materialTv = (TextView) itemView.findViewById(R.id.tv_material);
            submissionTv = (TextView) itemView.findViewById(R.id.tv_submission);
            publishlTv = (TextView) itemView.findViewById(R.id.tv_publishl);
            deleteTv = (TextView) itemView.findViewById(R.id.tv_delete);

            bookIv = (ImageView) itemView.findViewById(R.id.iv_book);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            typeTv = (TextView) itemView.findViewById(R.id.tv_type);
            numRb = (RatingBar) itemView.findViewById(R.id.ratbar_num);
            markTv = (TextView) itemView.findViewById(R.id.tv_mark);
            commentTv = (TextView) itemView.findViewById(R.id.tv_comment);
            authorTv = (TextView) itemView.findViewById(R.id.tv_author);
            wordTv = (TextView) itemView.findViewById(R.id.tv_word);
            moneyTv = (TextView) itemView.findViewById(R.id.tv_money);
            rewardTv = (TextView) itemView.findViewById(R.id.tv_reward);
        }
    }
}
