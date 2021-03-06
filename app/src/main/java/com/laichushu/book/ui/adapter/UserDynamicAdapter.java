package com.laichushu.book.ui.adapter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mine.userhomepage.UserHomePagePresener;
import com.laichushu.book.ui.activity.TopicDetilActivity;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/25.
 */

public class UserDynamicAdapter extends RecyclerView.Adapter<UserDynamicAdapter.ViewHolder> {
    private UserHomePageActivity context;
    private List<HomeUseDyrResult.DataBean> dataBeen;
    private UserHomePagePresener userHomePagePresener;
    private String type;
    private int currentNum = 0;

    public UserDynamicAdapter(UserHomePageActivity context, List<HomeUseDyrResult.DataBean> dataBean, UserHomePagePresener userHomePagePresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.userHomePagePresener = userHomePagePresener;
    }

    @Override
    public UserDynamicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_homepage_dynemic);
        return new UserDynamicAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUitl.loadRandImg(context, null, holder.ivImg);
        holder.tvShopName.setText(dataBeen.get(position).getCreatUserName());
        holder.tvTime.setText(dataBeen.get(position).getCreateDate());
        holder.tvTitle.setText(dataBeen.get(position).getTitle());
        holder.tvTitleContent.setText(dataBeen.get(position).getContent());
        currentNum = dataBeen.get(position).getCollectNum();
        if (dataBeen.get(position).isCollect()) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_yes2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tvCollection.setCompoundDrawables(drawable, null, null, null);
            type = "1";
            holder.tvCollection.setText("已收藏(" + dataBeen.get(position).getCollectNum() + ")");
        } else {
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_no2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tvCollection.setCompoundDrawables(drawable, null, null, null);
            type = "0";
            holder.tvCollection.setText("收藏(" + dataBeen.get(position).getCollectNum() + ")");
        }
        holder.llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dataBeen.get(position).isCollect()) {
                    //添加收藏
                    type = "0";
                    Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_yes2x);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder.tvCollection.setCompoundDrawables(drawable, null, null, null);
                    userHomePagePresener.loadCollectSaveDate(dataBeen.get(position).getId(), ConstantValue.COLLECTTOPIC_TYPE, type,dataBeen.get(position),position);
                } else {
                    //取消收藏
                    type = "1";
                    Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_no2x);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder.tvCollection.setCompoundDrawables(drawable, null, null, null);
                    userHomePagePresener.loadCollectSaveDate(dataBeen.get(position).getId(), ConstantValue.COLLECTTOPIC_TYPE, type,dataBeen.get(position),position);
                }
            }
        });
        holder.llScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看话题详情
                Bundle topic = new Bundle();
                topic.putString("type", "homepage");
                topic.putString("tag", "user");
                topic.putSerializable("topBean", dataBeen.get(position));
                UIUtil.openActivity(context, TopicDetilActivity.class, topic);
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

    public void refreshAdapter(List<HomeUseDyrResult.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvShopName, tvTime, tvTitle, tvTitleContent, tvMsg, tvScan, tvCollection;
        public final ImageView ivImg;
        public final View root;
        public final LinearLayout llCollection, llScan;

        public ViewHolder(View root) {
            super(root);
            tvShopName = (TextView) root.findViewById(R.id.tv_dyName);
            tvTime = (TextView) root.findViewById(R.id.tv_dyTime);
            tvTitle = (TextView) root.findViewById(R.id.tv_dyTitleName);
            tvTitleContent = (TextView) root.findViewById(R.id.tv_dyTitleContent);
            tvMsg = (TextView) root.findViewById(R.id.tv_msg);
            tvScan = (TextView) root.findViewById(R.id.tv_scan);
            tvCollection = (TextView) root.findViewById(R.id.tv_dyCollection);
            ivImg = (ImageView) root.findViewById(R.id.iv_dyHead);
            llCollection = (LinearLayout) root.findViewById(R.id.ll_collection);
            llScan = (LinearLayout) root.findViewById(R.id.ll_scan);
            this.root = root;
        }
    }
    public void setDataBeen(List<HomeUseDyrResult.DataBean> dataBeen) {
        this.dataBeen = dataBeen;
        notifyDataSetChanged();
    }
}