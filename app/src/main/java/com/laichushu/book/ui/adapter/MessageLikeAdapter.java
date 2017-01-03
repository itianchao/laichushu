package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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
                holder.ivDeleteMsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = dataBeen.get(position).getId();
                        dataBeen.remove(position);
                        messageCommentPresenter.messageDeleteComment(position, id);


                    }
                });
                switch (dataBeen.get(position).getSourceType()) {
                    case "1":
                    case "2":
                        //
                        holder.tvType.setText("收藏了你的书");
                        holder.tvContent.setVisibility(View.GONE);
                        holder.tvContent.setText(dataBeen.get(position).getContent());
                        if (!TextUtils.isEmpty(dataBeen.get(position).getSourceName())) {
                            holder.tvBookName.setText("《" + dataBeen.get(position).getSourceName() + "》");
                        }
                        holder.tvBookName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
                            }
                        });
                        break;
                    case "3":
                        //话题
                        holder.tvContent.setVisibility(View.VISIBLE);
                        holder.tvType.setText("收藏了你的话题");
                        holder.tvContent.setText(dataBeen.get(position).getContent());
                        if (!TextUtils.isEmpty(dataBeen.get(position).getSourceName())) {
                            holder.tvBookName.setText("#" + dataBeen.get(position).getSourceName() + "#");
                        } else {
                            holder.tvContent.setVisibility(View.GONE);
                        }
                        break;
                    case "4":
                    case "6":
                        //服务+编辑
                        holder.tvType.setText("收藏了你");
                        holder.tvContent.setVisibility(View.GONE);
                        break;
                    case "8":
                        //评论
                        holder.tvContent.setVisibility(View.VISIBLE);
                        holder.tvType.setText("点赞了你的评论");
                        if (!TextUtils.isEmpty(dataBeen.get(position).getContent())) {
                            holder.tvContent.setText(dataBeen.get(position).getContent());
                        } else {
                            holder.tvContent.setVisibility(View.GONE);
                        }
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
                String msg = dataBeen.get(position).getSenderName() + "  打赏了你的书  " + "《" + dataBeen.get(position).getSourceName() + "》";
                SpannableStringBuilder msgSpan = new SpannableStringBuilder();
                msgSpan.append(msg);
                //setSpan时需要指定的 flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括).
                msgSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.auditing)), 0, dataBeen.get(position).getSenderName().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                msgSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.characterGray)), dataBeen.get(position).getSenderName().length(), dataBeen.get(position).getSenderName().length() + 6 + (" ").length() * 4,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                msgSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.auditing)), msg.length() - ("《" + dataBeen.get(position).getSourceName() + "》").length(), msg.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ClickableSpan nameSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                };
                ClickableSpan bookNameSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
                    }
                };
                msgSpan.setSpan(nameSpan, 0, dataBeen.get(position).getSenderName().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                msgSpan.setSpan(bookNameSpan,(dataBeen.get(position).getSenderName() + "打赏了你的书").length()+(" ").length()*5, msg.length()-(" ").length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                holder.tvReward.setText(msgSpan);
                holder.tvReward.setMovementMethod(LinkMovementMethod.getInstance());

//                holder.tvReward.setText(dataBeen.get(position).getSenderName());
                holder.tvRewardTime.setText(dataBeen.get(position).getSendTime());
//                holder.tvRewardBookName.setText("《" + dataBeen.get(position).getSourceName() + "》");
                holder.btnWallet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIUtil.openActivity(context, MyWalletDetailsActivity.class);
                    }
                });
//                holder.tvReward.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //跳转用户主页
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
//                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
//                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
//                        } else {
//                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
//                        }
//                    }
//                });
//                holder.tvRewardBookName.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
//                    }
//                });
                holder.ivRewardDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = dataBeen.get(position).getId();
                        dataBeen.remove(position);
                        messageCommentPresenter.messageDeleteComment(position, id);

                    }
                });
                break;
            case "3":
                //关注
                GlideUitl.loadRandImg(context, dataBeen.get(position).getSenderPhoto(), holder.ivFocusIcon);
                holder.tvFocusTime.setText(dataBeen.get(position).getSendTime());
                holder.tvFocusName.setText(dataBeen.get(position).getSenderName());
                holder.tvFocusContent1.setVisibility(View.VISIBLE);
                holder.tvFocusContent1.setTextColor(context.getResources().getColor(R.color.characterGray));
                holder.tvFocusContent1.setText("关注了你");
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
                holder.ivFocusDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = dataBeen.get(position).getId();
                        dataBeen.remove(position);
                        messageCommentPresenter.messageDeleteComment(position, id);

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
                holder.ivFocusDelete.setVisibility(View.GONE);
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
                String msgfoucs = "您订阅  " + dataBeen.get(position).getSenderName() + "  的作品  " + "《" + dataBeen.get(position).getSourceName() + "》" + "更新了";
                SpannableStringBuilder msgFoucsSpan = new SpannableStringBuilder();
                msgFoucsSpan.append(msgfoucs);
                holder.ivFocusIcon.setVisibility(View.GONE);
                msgFoucsSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.characterGray)), 0, ("您订阅  ").length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                msgFoucsSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.auditing)), ("您订阅  ").length(), ("您订阅  ").length() + dataBeen.get(position).getSenderName().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                msgFoucsSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.characterGray)), ("您订阅  ").length() + dataBeen.get(position).getSenderName().length(), ("您订阅  ").length() + dataBeen.get(position).getSenderName().length() + (" ").length() * 4 + 3,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                msgFoucsSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.auditing)), 6 + (" ").length() * 6 + dataBeen.get(position).getSenderName().length(), msgfoucs.length() - 3,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                msgFoucsSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.characterGray)), msgfoucs.length() - 3, msgfoucs.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ClickableSpan userSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        //跳转用户主页
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                        } else {
                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
                        }
                    }
                };
                ClickableSpan bookSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
                    }
                };
                msgFoucsSpan.setSpan(userSpan, ("您订阅  ").length(), ("您订阅  ").length() + dataBeen.get(position).getSenderName().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                msgFoucsSpan.setSpan(bookSpan, msgfoucs.length() - 3 - ("《" + dataBeen.get(position).getSourceName() + "》").length(), msgfoucs.length() - 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                holder.tvFocusName.setText(msgFoucsSpan);
                holder.tvFocusName.setMovementMethod(LinkMovementMethod.getInstance());

                holder.ivFocusNotice.setVisibility(View.VISIBLE);
                GlideUitl.loadRandImgLocal(context, R.drawable.msg_subscribe2x, holder.ivFocusNotice);
                holder.tvFocusTime.setText(dataBeen.get(position).getSendTime());
//                holder.tvFocusName.setTextColor(context.getResources().getColor(R.color.characterLightGray2));
//                holder.tvFocusName.setText("您订阅");
//                holder.tvFocusContent1.setVisibility(View.VISIBLE);
//                holder.tvFocusContent2.setVisibility(View.VISIBLE);
//                holder.tvFocusContent3.setVisibility(View.VISIBLE);
//                holder.tvFocusContent4.setVisibility(View.VISIBLE);
//                holder.tvFocusContent1.setText(dataBeen.get(position).getAuthorName());
//                holder.tvFocusContent2.setText("的作品:");
//                holder.tvFocusContent3.setText("《" + dataBeen.get(position).getSourceName() + "》");
//                holder.tvFocusContent4.setText("更新了!");
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
//                holder.tvFocusContent1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //跳转用户主页
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("userId", dataBeen.get(position).getSenderId());
//                        if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSenderId())) {
//                            UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
//                        } else {
//                            UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
//                        }
//                    }
//                });
//                //跳转图书详情
//                holder.tvFocusContent3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
//                    }
//                });
                holder.ivFocusDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = dataBeen.get(position).getId();
                        dataBeen.remove(position);
                        messageCommentPresenter.messageDeleteComment(position, id);
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
                holder.ivFocusDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = dataBeen.get(position).getId();
                        dataBeen.remove(position);
                        messageCommentPresenter.messageDeleteComment(position, id);
                    }
                });
                break;
            case "7":
                //其他消息
                holder.ivFocusNotice.setVisibility(View.VISIBLE);
                holder.ivFocusIcon.setVisibility(View.INVISIBLE);
                holder.tvFocusTime.setText(dataBeen.get(position).getSendTime());
                holder.tvFocusName.setText(dataBeen.get(position).getContent());
                holder.tvFocusName.setTextColor(context.getResources().getColor(R.color.black));
                holder.tvFocusContent.setVisibility(View.GONE);
                holder.ivFocusDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = dataBeen.get(position).getId();
                        dataBeen.remove(position);
                        messageCommentPresenter.messageDeleteComment(position, id);
                    }
                });
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
        public ImageView ivLikeIcon, ivDeleteMsg;
        public final View root;
        //打赏
        public TextView tvReward, tvRewardBookName, tvRewardTime;
        public Button btnWallet;
        public ImageView ivRewardDelete;
        //关注
        public TextView tvFocusTime, tvFocusName, tvFocusContent, tvFocusContent1, tvFocusContent2, tvFocusContent3, tvFocusContent4;
        public ImageView ivFocusIcon, ivFocusNotice, ivFocusDelete;
        public LinearLayout llFocusItem;

        public ViewHolder(View root) {
            super(root);
            switch (type) {
                case "1":
                    //喜欢
                    ivLikeIcon = (ImageView) root.findViewById(R.id.iv_likeIcons);
                    ivDeleteMsg = (ImageView) root.findViewById(R.id.iv_deleteMsg);
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
                    ivRewardDelete = (ImageView) root.findViewById(R.id.iv_rewardDeleteMsg);
                    break;
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                    //关注
                    ivFocusIcon = (ImageView) root.findViewById(R.id.iv_focusIcon);
                    ivFocusNotice = (ImageView) root.findViewById(R.id.iv_focusNoticeIcon);
                    ivFocusDelete = (ImageView) root.findViewById(R.id.iv_deleteMsg);
                    tvFocusTime = (TextView) root.findViewById(R.id.tv_focusTime);
                    tvFocusName = (TextView) root.findViewById(R.id.tv_focusName);
                    tvFocusContent = (TextView) root.findViewById(R.id.tv_focusContent);
                    tvFocusContent1 = (TextView) root.findViewById(R.id.tv_focusContent1);
                    tvFocusContent2 = (TextView) root.findViewById(R.id.tv_focusContent2);
                    tvFocusContent3 = (TextView) root.findViewById(R.id.tv_focusContent3);
                    tvFocusContent4 = (TextView) root.findViewById(R.id.tv_focusContent4);
                    llFocusItem = (LinearLayout) root.findViewById(R.id.ll_item);
                    break;
            }
            this.root = root;
        }
    }
}
