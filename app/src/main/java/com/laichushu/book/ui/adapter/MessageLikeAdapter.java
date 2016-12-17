package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.ui.activity.MsgLikeDetailsActivity;
import com.laichushu.book.ui.activity.MyWalletDetailsActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.activity.PersonalInfomationPageActivity;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * 消息喜欢界面
 * Created by PCPC on 2016/11/29.
 */

public class MessageLikeAdapter extends RecyclerView.Adapter<MessageLikeAdapter.ViewHolder> {
    private MsgLikeDetailsActivity context;
    private List<MessageCommentResult.DataBean> dataBeen;
    private String type;
    private MessageCommentPresenter messageCommentPresenter;

    public MessageLikeAdapter(MsgLikeDetailsActivity context, List<MessageCommentResult.DataBean> dataBean, String type, MessageCommentPresenter messageCommentPresenter) {
        this.context = context;
        this.dataBeen = dataBean;
        this.type = type;
        this.messageCommentPresenter = messageCommentPresenter;
    }

    @Override
    public MessageLikeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (type) {
            case "1":
                itemView = UIUtil.inflate(R.layout.item_msg_like_datails);
                break;
            case "2":
                itemView = UIUtil.inflate(R.layout.item_msg_reward);
                break;
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
                itemView = UIUtil.inflate(R.layout.item_msg_focus);
                break;
        }
        return new MessageLikeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageLikeAdapter.ViewHolder holder, final int position) {
        switch (type) {
            case "1":
                //喜欢
                holder.tvTime.setText(dataBeen.get(position).getSendTime());
                holder.tvReaderName.setText(dataBeen.get(position).getSenderName());
                switch (dataBeen.get(position).getSourceType()) {
                    case "1":
                        //
                        holder.tvContent.setVisibility(View.GONE);
                        holder.tvType.setText("收藏了你的书");
                        holder.tvBookName.setText("《" + dataBeen.get(position).getSourceName() + "》");
                        holder.tvBookName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
                            }
                        });
                        break;
                    case "3":
                        //点赞
                        holder.tvContent.setVisibility(View.VISIBLE);
                        holder.tvType.setText("点赞了您的评论");
                        holder.tvContent.setText(dataBeen.get(position).getContent());
                        break;
                }
                holder.tvReaderName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                });

                break;
            case "2":
                //打赏
                holder.tvReward.setText(dataBeen.get(position).getSenderName());
                holder.tvRewardTime.setText(dataBeen.get(position).getSendTime());
                holder.tvRewardBookName.setText("《" + dataBeen.get(position).getSourceName() + "》");
                holder.btnWallet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIUtil.openActivity(context, MyWalletDetailsActivity.class);
                    }
                });
                holder.tvReward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                });
                holder.tvRewardBookName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
                    }
                });
                break;
            case "3":
                //关注
                GlideUitl.loadRandImg(context, dataBeen.get(position).getSenderPhoto(), holder.ivFocusIcon);
                holder.tvFocusTime.setText(dataBeen.get(position).getSendTime());
                holder.tvFocusName.setText(dataBeen.get(position).getSenderName());
                holder.tvFocusName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                });
                holder.ivFocusIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                });
                break;
            case "4":
                //私信
                GlideUitl.loadRandImg(context, dataBeen.get(position).getSenderPhoto(), holder.ivFocusIcon);
                holder.tvFocusTime.setText(dataBeen.get(position).getCreateDate());
                holder.tvFocusName.setText(dataBeen.get(position).getContent());
                holder.tvFocusName.setTextColor(context.getResources().getColor(R.color.characterLightGray2));
                holder.tvFocusContent.setVisibility(View.GONE);
                holder.ivFocusIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                });
                holder.llFocusItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转私信详情
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("perInfoDetails", dataBeen.get(position));
                        UIUtil.openActivity(context, PersonalInfomationPageActivity.class, bundle);

                    }
                });
                break;
            case "5":
                //订阅
                holder.ivFocusIcon.setVisibility(View.GONE);
                holder.ivFocusNotice.setVisibility(View.VISIBLE);
                GlideUitl.loadRandImgLocal(context, R.drawable.msg_subscribe2x, holder.ivFocusNotice);
                holder.tvFocusTime.setText(dataBeen.get(position).getSendTime());
                holder.tvFocusName.setTextColor(context.getResources().getColor(R.color.characterLightGray2));
                holder.tvFocusName.setText("您订阅");
                holder.tvFocusContent1.setVisibility(View.VISIBLE);
                holder.tvFocusContent2.setVisibility(View.VISIBLE);
                holder.tvFocusContent3.setVisibility(View.VISIBLE);
                holder.tvFocusContent1.setText(dataBeen.get(position).getAuthorName());
                holder.tvFocusContent2.setText("的作品:");
                holder.tvFocusContent3.setText("《"+dataBeen.get(position).getSourceName()+"》");
                holder.tvFocusContent.setText("更新了!");
                holder.ivFocusIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                });
                holder.tvFocusContent1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                });
                //跳转图书详情
                holder.tvFocusContent3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
                    }
                });
                break;
            case "6":
                //活动通知
                holder.ivFocusNotice.setVisibility(View.VISIBLE);
                holder.ivFocusIcon.setVisibility(View.INVISIBLE);
                holder.tvFocusTime.setText(dataBeen.get(position).getSendTime());
                holder.tvFocusName.setText(dataBeen.get(position).getContent());
                holder.tvFocusName.setTextColor(context.getResources().getColor(R.color.characterLightGray2));
                holder.tvFocusContent.setVisibility(View.GONE);
//                holder.llFocusItem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //活动详情
//                        Bundle bundle = new Bundle();
//                        bundle.putString("type","activity");
//                        bundle.putSerializable("activityDetails", dataBeen.get(position));
//                            UIUtil.openActivity(context, CampaignActivity.class, bundle);
//                    }
//                });
                break;
            case "7":
                //其他消息
                holder.ivFocusNotice.setVisibility(View.VISIBLE);
                holder.ivFocusIcon.setVisibility(View.INVISIBLE);
                holder.tvFocusTime.setText(dataBeen.get(position).getSendTime());
                holder.tvFocusName.setText(dataBeen.get(position).getContent());
                holder.tvFocusName.setTextColor(context.getResources().getColor(R.color.black));
                holder.tvFocusContent.setVisibility(View.GONE);
                break;
        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }

    public void refreshAdapter(List<MessageCommentResult.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //喜欢
        public LinearLayout llItem;
        public TextView tvTime, tvReaderName, tvType, tvBookName, tvContent;
        public ImageView ivLikeIcon;
        public final View root;
        //打赏
        public TextView tvReward, tvRewardBookName, tvRewardTime;
        public Button btnWallet;
        //关注
        public TextView tvFocusTime, tvFocusName, tvFocusContent,tvFocusContent1,tvFocusContent2,tvFocusContent3;
        public ImageView ivFocusIcon, ivFocusNotice;
        public LinearLayout llFocusItem;

        public ViewHolder(View root) {
            super(root);
            switch (type) {
                case "1":
                    //喜欢
                    ivLikeIcon = (ImageView) root.findViewById(R.id.iv_likeIcons);
                    llItem = (LinearLayout) root.findViewById(R.id.ll_item);
                    tvTime = (TextView) root.findViewById(R.id.tv_likeTime);
                    tvReaderName = (TextView) root.findViewById(R.id.tv_readerName);
                    tvType = (TextView) root.findViewById(R.id.tv_type);
                    tvBookName = (TextView) root.findViewById(R.id.tv_bookName);
                    //评论内容
                    tvContent = (TextView) root.findViewById(R.id.tv_msgCommentContent);
                    break;
                case "2":
                    //打赏
                    tvReward = (TextView) root.findViewById(R.id.tv_rewardName);
                    tvRewardBookName = (TextView) root.findViewById(R.id.tv_rewardBookName);
                    tvRewardTime = (TextView) root.findViewById(R.id.tv_rewardTime);
                    btnWallet = (Button) root.findViewById(R.id.tv_checkWallet);
                    break;
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                    //关注
                    ivFocusIcon = (ImageView) root.findViewById(R.id.iv_focusIcon);
                    ivFocusNotice = (ImageView) root.findViewById(R.id.iv_focusNoticeIcon);
                    tvFocusTime = (TextView) root.findViewById(R.id.tv_focusTime);
                    tvFocusName = (TextView) root.findViewById(R.id.tv_focusName);
                    tvFocusContent = (TextView) root.findViewById(R.id.tv_focusContent);
                    tvFocusContent1 = (TextView) root.findViewById(R.id.tv_focusContent1);
                    tvFocusContent2 = (TextView) root.findViewById(R.id.tv_focusContent2);
                    tvFocusContent3 = (TextView) root.findViewById(R.id.tv_focusContent3);
                    llFocusItem = (LinearLayout) root.findViewById(R.id.ll_item);
                    break;
            }
            this.root = root;
        }
    }
}
