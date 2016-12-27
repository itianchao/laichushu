package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.messagecomment.MessageCommentPresenter;
import com.laichushu.book.ui.activity.MessageCommentDetailsActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * 消息评论
 * Created by PCPC on 2016/11/28.
 */

public class MessageCommentAdapter extends RecyclerView.Adapter<MessageCommentAdapter.ViewHolder> {
    private MessageCommentDetailsActivity context;
    private List<MessageCommentResult.DataBean> dataBeen;
    private MessageCommentPresenter messageCommentPresenter;

    public MessageCommentAdapter(MessageCommentDetailsActivity context, List<MessageCommentResult.DataBean> dataBean, MessageCommentPresenter messageCommentPresenter) {
        this.context = context;
        this.dataBeen = dataBean;
        this.messageCommentPresenter = messageCommentPresenter;
    }

    @Override
    public MessageCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.iten_msg_comment);
        return new MessageCommentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageCommentAdapter.ViewHolder holder, final int position) {
        GlideUitl.loadRandImg(context, dataBeen.get(position).getSenderPhoto(), holder.ivImg);
        holder.tvName.setTextColor(context.getResources().getColor(R.color.auditing));
        holder.tvName.setText(dataBeen.get(position).getSenderName());
       if(dataBeen.get(position).getSourceType().equals("1")){
           holder.tvAppend.setText(dataBeen.get(position).getSenderName() + " 评论了你的书");
           if (!TextUtils.isEmpty(dataBeen.get(position).getSourceName())) {
               holder.tvBookName.setText("《" + dataBeen.get(position).getSourceName() + "》");
           }
       }else{
           holder.tvAppend.setText(dataBeen.get(position).getSenderName() + " 评论了你的话题");
           if (!TextUtils.isEmpty(dataBeen.get(position).getSourceName())) {
               holder.tvBookName.setTextColor(context.getResources().getColor(R.color.characterGray));
               holder.tvBookName.setText("#" + dataBeen.get(position).getSourceName() + "#");
           }
       }


        holder.tvContent.setText(dataBeen.get(position).getContent());
        holder.tvData.setText(dataBeen.get(position).getSendTime() + "");
        holder.tvName.setOnClickListener(new View.OnClickListener() {
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
        holder.ivImg.setOnClickListener(new View.OnClickListener() {
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
        if(dataBeen.get(position).getSourceType().equals("1")){
            holder.tvBookName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转书详情
                    messageCommentPresenter.loadBookDetailsByid(dataBeen.get(position).getArticleId());
                }
            });
        }
        holder.ivReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                dataBeen.remove(position);
                String id = dataBeen.get(position).getId();
                messageCommentPresenter.messageDeleteComment(position, id);
            }
        });
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
        public final LinearLayout llItem;
        public final TextView tvName, tvAppend, tvBookName, tvContent, tvData;
        public final ImageView ivImg, ivReplay;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            llItem = (LinearLayout) root.findViewById(R.id.ll_item);
            tvName = (TextView) root.findViewById(R.id.tv_commentName);
            tvAppend = (TextView) root.findViewById(R.id.tv_appendName);
            tvBookName = (TextView) root.findViewById(R.id.tv_bookName);
            tvContent = (TextView) root.findViewById(R.id.tv_commentContent);
            tvData = (TextView) root.findViewById(R.id.tv_commentDate);
            ivImg = (ImageView) root.findViewById(R.id.iv_commentHead);
            ivReplay = (ImageView) root.findViewById(R.id.iv_replayMsg);
            this.root = root;
        }
    }
}
