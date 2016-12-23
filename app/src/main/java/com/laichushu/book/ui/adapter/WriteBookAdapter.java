package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MyTabStrip;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.write.WritePresenter;
import com.laichushu.book.ui.activity.BookDetailActivity;
import com.laichushu.book.ui.activity.DraftModleActivity;
import com.laichushu.book.ui.activity.MechanismListActivity;
import com.laichushu.book.ui.activity.SourceMaterialDirActivity;
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
    private WritePresenter mvpPresenter;
    private ArrayList<MyTabStrip> mStrip;
    private MyTabStripAdapter adapter;
    private View finalItemView;

    public WriteBookAdapter(ArrayList<HomeHotModel.DataBean> mData, Activity mActivity, WritePresenter mvpPresenter, ArrayList<MyTabStrip> mStrip) {
        this.mData = mData;
        this.mActivity = mActivity;
        this.mvpPresenter = mvpPresenter;
        this.mStrip = mStrip;
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
    public void onBindViewHolder(final WriteBookViewHolder holder, final int position) {
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

        View itemView = null;
        ImageView imageView = null;
        TextView textView;
        int img[] = {R.drawable.icon_draft2x, R.drawable.icon_material2x, R.drawable.icon_delete2x, R.drawable.icon_publishl2x, R.drawable.icon_submission2x, R.drawable.icon_sign2x};
        int grayImgArray[] = {R.drawable.icon_draft_gray, R.drawable.icon_material_gray, R.drawable.icon_delete_gray, R.drawable.icon_publishl_gray, R.drawable.icon_submission_gray, R.drawable.icon_sign_gray};
        holder.llTab.removeAllViews();
        if (dataBean.getExpressStatus().equals("0") || dataBean.getExpressStatus().equals("4")) {
            mStrip.get(3).setTitle("发表");
        } else {
            mStrip.get(3).setTitle("取消发表");
        }
        if (dataBean.getExpressStatus().equals("2")){
            mStrip.get(3).setTitle("制作中");
        }
        for (int i = 0; i < mStrip.size(); i++) {
            itemView = UIUtil.inflate(R.layout.item_tabstrip, null);
            imageView = (ImageView) itemView.findViewById(R.id.iv_stripIcon);
            textView = (TextView) itemView.findViewById(R.id.tv_stripContent);
            if (dataBean.getStatus().equals("4")||dataBean.getFreezeStatus().equals("2")) {
                imageView.setImageResource(grayImgArray[i]);
            } else if (!dataBean.isEdit() | dataBean.getStatus().equals("3") | dataBean.getFreezeStatus().equals("2")) {
                if (i!=3&&i!=4&&i!=5){
                    imageView.setImageResource(grayImgArray[i]);
                    if (dataBean.getStatus().equals("4")||dataBean.getFreezeStatus().equals("2")||!mStrip.get(3).equals("发表")) {
                        holder.jurTv.setImageResource(R.drawable.icon_authority_gray);
                    }else {
                        holder.jurTv.setImageResource(R.drawable.manage_jur2x);
                        //权限
                    }
                }else {
                    imageView.setImageResource(mStrip.get(i).getDrawble());
                }
            } else {
                imageView.setImageResource(mStrip.get(i).getDrawble());
            }
            textView.setText(mStrip.get(i).getTitle());
            holder.llTab.addView(itemView);
            final int finalJ = i;
            finalItemView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.getStatus().equals("4")|| dataBean.getFreezeStatus().equals("2")) {
                        return;
                    }
                    switch (finalJ) {
                        case 0:
                            //目录
                            if (!dataBean.isEdit() | dataBean.getStatus().equals("3")) {

                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("articleId", dataBean.getArticleId());
                                UIUtil.openActivity(mActivity, DraftModleActivity.class, bundle);
                            }

                            break;
                        case 1:
                            //素材
                            if (!dataBean.isEdit() | dataBean.getStatus().equals("3")) {

                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("articleId", dataBean.getArticleId());
                                UIUtil.openActivity(mActivity, SourceMaterialDirActivity.class, bundle);
                            }
                            break;
                        case 2:
                            //删除
                            /**
                             * 删除
                             */
                            if (!dataBean.isEdit() | dataBean.getStatus().equals("3")) {

                            } else {
                                String articleId = dataBean.getArticleId();
                                mvpPresenter.loadDelete(mActivity, articleId, position);
                            }
                            break;
                        case 3:
                            /**
                             * 发表
                             */
                            //发表状态为 制作中  电子书 则不可发表
                            if (dataBean.getExpressStatus().equals("2")||dataBean.getExpressStatus().equals("3")) {
                                return;
                            }

                            String articleId = dataBean.getArticleId();
                            String type;
                            if (dataBean.getExpressStatus().equals("0")) {
                                type = "0";
                            } else {
                                type = "1";
                            }
                            mvpPresenter.publishNewBook(articleId, type, position);
                            break;
                        case 4:
                            //投稿

                            Bundle bundle = new Bundle();
                            bundle.putString("articleId", dataBean.getArticleId());
                            UIUtil.openActivity(mActivity, MechanismListActivity.class, bundle);

//                String articleId = dataBean.getArticleId();
//                String pressId = "";
//                mvpPresenter.voteBook(articleId, pressId);
                            break;
                        case 5:
                            //签约状态
                            finalItemView.setEnabled(false);
                            mvpPresenter.getSignStateDeta(dataBean.getArticleId());
                            break;
                    }
                }
            });

        }
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

        holder.jurTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.getStatus().equals("4")||dataBean.getStatus().equals("2")||dataBean.getFreezeStatus().equals("2")) {
                    return;
                }
                //添加权限
                mvpPresenter.openPermissionAlertDialog(mActivity, dataBean);
//                if (!dataBean.isEdit()) {
//                    ToastUtil.showToast("不可改编权限");
//                } else {
//                }
            }
        });

    }

    class WriteBookViewHolder extends RecyclerView.ViewHolder {

        private TextView draftTv;//草稿
        private TextView materialTv;//素材
        private TextView submissionTv;//投稿
        private TextView publishlTv;//发布
        private TextView deleteTv;//删除
        private ImageView jurTv;//权限
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
        private LinearLayout llTab;
        private ImageView ivLeft, ivRight;
        private HorizontalScrollView hsView;
        private View itemView;

        public WriteBookViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            draftTv = (TextView) itemView.findViewById(R.id.tv_draft);
            materialTv = (TextView) itemView.findViewById(R.id.tv_material);
            submissionTv = (TextView) itemView.findViewById(R.id.tv_submission);
            publishlTv = (TextView) itemView.findViewById(R.id.tv_publishl);
            deleteTv = (TextView) itemView.findViewById(R.id.tv_delete);
            jurTv = (ImageView) itemView.findViewById(R.id.tv_jurisdiction);
            rewardTv = (TextView) itemView.findViewById(R.id.tv_reward);
            llTab = (LinearLayout) itemView.findViewById(R.id.ll_tab);
            hsView = (HorizontalScrollView) itemView.findViewById(R.id.hs_view);
            hsView = (HorizontalScrollView) itemView.findViewById(R.id.hs_view);

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
            ivLeft = (ImageView) itemView.findViewById(R.id.iv_left);
            ivRight = (ImageView) itemView.findViewById(R.id.iv_right);
        }
    }

    public ArrayList<HomeHotModel.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<HomeHotModel.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public View getFinalItemView() {
        return finalItemView;
    }

}
