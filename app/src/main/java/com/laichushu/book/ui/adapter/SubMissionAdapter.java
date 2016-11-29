package com.laichushu.book.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/29.
 */

public class SubMissionAdapter extends RecyclerView.Adapter<SubMissionAdapter.ViewHolder> {
    private Context context;
    private List<MessageCommentResult.DataBean> dataBeen;

    public SubMissionAdapter(Context context, List<MessageCommentResult.DataBean> dataBean) {
        this.context = context;
        this.dataBeen = dataBean;
    }

    @Override
    public SubMissionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_msg_submission);
        return new SubMissionAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubMissionAdapter.ViewHolder holder, final int position) {
        GlideUitl.loadRandImg(context, "", holder.ivImg);
        holder.tvName.setText(dataBeen.get(position).getSenderName());
        holder.tvAppend.setText(dataBeen.get(position).getSenderName() + "评论了你的文章");
        holder.tvBookName.setText("");
        holder.tvContent.setText(dataBeen.get(position).getContent());
        holder.tvData.setText(dataBeen.get(position).getSendTime() + "");
        holder.ivReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("回复消息！");
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
