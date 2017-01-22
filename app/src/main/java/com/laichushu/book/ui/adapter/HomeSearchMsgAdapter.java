package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.mvp.msg.messagecomment.MessageCommentPresenter;
import com.laichushu.book.ui.activity.HomeSearchActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.activity.PersonalInfomationPageActivity;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2017/1/20.
 */

public class HomeSearchMsgAdapter extends RecyclerView.Adapter<HomeSearchMsgAdapter.ViewHolder> {
    private HomeSearchActivity context;
    private List<MessageCommentResult.DataBean> dataBeen;

    public HomeSearchMsgAdapter(HomeSearchActivity context, List<MessageCommentResult.DataBean> dataBean) {
        this.context = context;
        this.dataBeen = dataBean;
    }

    @Override
    public HomeSearchMsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_msg_focus);
        return new HomeSearchMsgAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
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
        if (listData.size() > 0) {
            dataBeen.clear();
            dataBeen.addAll(listData);
        }
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFocusTime, tvFocusTimes, tvFocusName, tvFocusContent, tvFocusContent1, tvFocusContent2, tvFocusContent3, tvFocusContent4;
        public ImageView ivFocusIcon, ivFocusNotice, ivFocusDelete;
        public LinearLayout llFocusItem;
        public View root;

        public ViewHolder(View root) {
            super(root);
            ivFocusIcon = (ImageView) root.findViewById(R.id.iv_focusIcon);
            ivFocusNotice = (ImageView) root.findViewById(R.id.iv_focusNoticeIcon);
            ivFocusDelete = (ImageView) root.findViewById(R.id.iv_deleteMsg);
            tvFocusTime = (TextView) root.findViewById(R.id.tv_focusTime);
            tvFocusTimes = (TextView) root.findViewById(R.id.tv_focusTimes);
            tvFocusName = (TextView) root.findViewById(R.id.tv_focusName);
            tvFocusContent = (TextView) root.findViewById(R.id.tv_focusContent);
            tvFocusContent1 = (TextView) root.findViewById(R.id.tv_focusContent1);
            tvFocusContent2 = (TextView) root.findViewById(R.id.tv_focusContent2);
            tvFocusContent3 = (TextView) root.findViewById(R.id.tv_focusContent3);
            tvFocusContent4 = (TextView) root.findViewById(R.id.tv_focusContent4);
            llFocusItem = (LinearLayout) root.findViewById(R.id.ll_item);
            this.root = root;
        }
    }
}

