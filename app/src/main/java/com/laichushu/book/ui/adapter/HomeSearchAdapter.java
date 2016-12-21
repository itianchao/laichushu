package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.ui.activity.BookDetailActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;
import com.laichushu.book.R;
import com.laichushu.book.ui.activity.HomeSearchActivity;

import java.util.ArrayList;

/**
 * 搜索 结果
 * Created by wangtong on 22.
 */
public class HomeSearchAdapter extends RecyclerView.Adapter<HomeSearchAdapter.ViewHolder> {

    private ArrayList<HomeHotModel.DataBean> mAllData;
    private HomeSearchActivity mActivity;
    private View itemView;

    public HomeSearchAdapter(ArrayList<HomeHotModel.DataBean> mAllData, HomeSearchActivity homeSearchActivity) {
        this.mAllData = mAllData;
        this.mActivity = homeSearchActivity;
    }

    @Override
    public int getItemCount() {
        return mAllData == null? 0:mAllData.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = UIUtil.inflate(R.layout.item_home_book);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HomeHotModel.DataBean dataBean = mAllData.get(position);
        GlideUitl.loadImg(mActivity, dataBean.getCoverUrl(), holder.bookIv);
        holder.titleTv.setText(dataBean.getArticleName());
        holder.typeTv.setText(dataBean.getTopCategoryName());
        holder.markTv.setText(dataBean.getScore()+"分");
        holder.commentTv.setText("("+dataBean.getCommentNum()+"评论)");
        holder.authorTv.setText(dataBean.getAuthorName());
        holder.wordTv.setText("约"+dataBean.getWordNum());
        holder.moneyTv.setText(dataBean.getAwardMoney()+"元");
        holder.rewardTv.setText("("+dataBean.getAwardNum()+"人打赏)");
        holder.numRb.setRating(dataBean.getLevel());
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
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", dataBean);
                bundle.putString("pageMsg", "首页搜索");
                UIUtil.openActivity(mActivity, BookDetailActivity.class, bundle);
            }
        });
    }

    public void setmAllData(ArrayList<HomeHotModel.DataBean> mAllData) {
        this.mAllData = mAllData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(View itemView) {
            super(itemView);
            bookIv = (ImageView) itemView.findViewById(R.id.iv_book);
            bookStatueIv = (ImageView) itemView.findViewById(R.id.iv_book_statue);
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
