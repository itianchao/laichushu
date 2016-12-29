package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findeditmainpage.FindEditMainPagePresenter;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.activity.BookDetailActivity;
import com.laichushu.book.ui.activity.FindEditMainPageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/27.
 */

public class EditorFindCaseAdapter extends RecyclerView.Adapter<EditorFindCaseAdapter.WriteBookViewHolder> {
    private List<HomeHotModel.DataBean> mData;
    private FindEditMainPageActivity mActivity;
    private FindEditMainPagePresenter mvpPresenter;

    public EditorFindCaseAdapter(FindEditMainPageActivity mActivity, List<HomeHotModel.DataBean> mData, FindEditMainPagePresenter mvpPresenter) {
        this.mData = mData;
        this.mActivity = mActivity;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public EditorFindCaseAdapter.WriteBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_newbook);
        return new EditorFindCaseAdapter.WriteBookViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final EditorFindCaseAdapter.WriteBookViewHolder holder, final int position) {
        final HomeHotModel.DataBean dataBean = mData.get(position);
        if(null!=dataBean.getStatus()){
            switch(dataBean.getStatus()){
                case "1":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue2,  holder.bookStatueIv);
                    break;
                case "2":
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue3,  holder.bookStatueIv);
                    break;
                default:
                    GlideUitl.loadImg(mActivity, R.drawable.icon_book_statue1,  holder.bookStatueIv);
                    break;
            }
        }
        holder.jurTv.setVisibility(View.GONE);
        holder.rlHs.setVisibility(View.GONE);
        holder.ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.hsView.arrowScroll(View.FOCUS_RIGHT);
                holder.ivRight.setVisibility(View.GONE);
                holder.ivLeft.setVisibility(View.VISIBLE);
            }
        });
        holder.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.hsView.arrowScroll(View.FOCUS_LEFT);
                holder.ivRight.setVisibility(View.VISIBLE);
                holder.ivLeft.setVisibility(View.GONE);
            }
        });
        GlideUitl.loadImg(mActivity, dataBean.getCoverUrl(), holder.bookIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转图书详情页
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", dataBean);
                bundle.putString("pageMsg", "写作和作品管理");//页面信息
                UIUtil.openActivity(mActivity, BookDetailActivity.class, bundle);
            }
        });
        holder.titleTv.setText(dataBean.getArticleName());
        holder.typeTv.setText(dataBean.getTopCategoryName());
        holder.authorTv.setText(dataBean.getAuthorName());
        holder.numRb.setRating(dataBean.getLevel());
        holder.commentTv.setText("(" + dataBean.getCommentNum() + "评论)");
        holder.wordTv.setText("约" + dataBean.getWordNum());
        holder.moneyTv.setText(dataBean.getAwardMoney() + "元");
        holder.rewardTv.setText("(" + dataBean.getAwardNum() + "人打赏)");

        holder.markTv.setText(dataBean.getScore() + "分");
    }

    class WriteBookViewHolder extends RecyclerView.ViewHolder {

        private ImageView bookIv;
        private ImageView bookStatueIv;
        private TextView titleTv;
        private TextView typeTv;
        private RatingBar numRb;
        private TextView markTv;
        private TextView commentTv;
        private TextView authorTv;
        private TextView wordTv;
        private TextView moneyTv;
        private TextView rewardTv;
        private ImageView jurTv;
        private LinearLayout llTab;
        private ImageView ivLeft, ivRight;
        private HorizontalScrollView hsView;
        private RelativeLayout rlHs;
        private View itemView;

        public WriteBookViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            rewardTv = (TextView) itemView.findViewById(R.id.tv_reward);
            llTab = (LinearLayout) itemView.findViewById(R.id.ll_tab);
            hsView = (HorizontalScrollView) itemView.findViewById(R.id.hs_view);
            hsView = (HorizontalScrollView) itemView.findViewById(R.id.hs_view);

            bookIv = (ImageView) itemView.findViewById(R.id.iv_book);
            bookStatueIv = (ImageView) itemView.findViewById(R.id.iv_book_statue);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            jurTv = (ImageView) itemView.findViewById(R.id.tv_jurisdiction);
            typeTv = (TextView) itemView.findViewById(R.id.tv_type);
            numRb = (RatingBar) itemView.findViewById(R.id.ratbar_num);
            markTv = (TextView) itemView.findViewById(R.id.tv_mark);
            commentTv = (TextView) itemView.findViewById(R.id.tv_comment);
            authorTv = (TextView) itemView.findViewById(R.id.tv_author);
            wordTv = (TextView) itemView.findViewById(R.id.tv_word);
            moneyTv = (TextView) itemView.findViewById(R.id.tv_money);
            ivLeft = (ImageView) itemView.findViewById(R.id.iv_left);
            ivRight = (ImageView) itemView.findViewById(R.id.iv_right);
            rlHs=(RelativeLayout) itemView.findViewById(R.id.rl_hsView);
        }
    }
    public void refreshAdapter(List<HomeHotModel.DataBean> listData) {
        mData.clear();
        if (listData.size() > 0) {
            mData.addAll(listData);
            this.notifyDataSetChanged();
        }
    }


}
