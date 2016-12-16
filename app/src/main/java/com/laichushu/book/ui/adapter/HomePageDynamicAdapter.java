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
import com.laichushu.book.mvp.homepage.HomePagePresener;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.activity.TopicDetilActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomePageDynamicAdapter extends RecyclerView.Adapter<HomePageDynamicAdapter.ViewHolder> {
    private PersonalHomePageActivity context;
    private List<HomeUseDyrResult.DataBean> dataBeen;
    private HomePagePresener homePagePresener;
    private String type=null;
    private int currentNum = 0;
    public HomePageDynamicAdapter(PersonalHomePageActivity context, List<HomeUseDyrResult.DataBean> dataBean,HomePagePresener homePagePresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.homePagePresener=homePagePresener;
    }

    @Override
    public HomePageDynamicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_homepage_dynemic);
        return new HomePageDynamicAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUitl.loadRandImg(context, dataBeen.get(position).getCreaterPhoto(), holder.ivImg);
        holder.tvShopName.setText(dataBeen.get(position).getCreatUserName());
        holder.tvTime.setText(dataBeen.get(position).getCreateDate());
        holder.tvTitle.setText(dataBeen.get(position).getTitle());
        holder.tvTitleContent.setText(dataBeen.get(position).getContent());
        currentNum = dataBeen.get(position).getCollectNum();
        if(dataBeen.get(position).isCollect()){
            holder.tvCollect.setText("已收藏("+dataBeen.get(position).getCollectNum() + ")");
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_no2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tvCollect.setCompoundDrawables(drawable, null, null, null);
            type = "1";
        }else{
            holder.tvCollect.setText("收藏("+dataBeen.get(position).getCollectNum() + ")");
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_yes2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tvCollect.setCompoundDrawables(drawable, null, null, null);
            type = "0";
        }
        holder.llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (("0").equals(type)) {
                    //添加收藏
                    currentNum++;
                    Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_no2x);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder.tvCollect.setCompoundDrawables(drawable, null, null, null);
                    homePagePresener.loadCollectSaveDate(dataBeen.get(position).getId(), ConstantValue.COLLECTTOPIC_TYPE, type);
                    type = "1";
                    holder.tvCollect.setText("已收藏("+currentNum + ")");
                } else {
                    //取消收藏
                    currentNum--;
                    Drawable drawable = context.getResources().getDrawable(R.drawable.icon_praise_yes2x);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    holder.tvCollect.setCompoundDrawables(drawable, null, null, null);
                    homePagePresener.loadCollectSaveDate(dataBeen.get(position).getId(), ConstantValue.COLLECTTOPIC_TYPE, type);
                    type = "0";
                    holder.tvCollect.setText("收藏("+currentNum + ")");
                }
            }
        });

        holder.llScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看话题详情
                Bundle topic = new Bundle();
                topic.putString("type", "homepage");
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
        public final TextView tvShopName, tvTime, tvTitle, tvTitleContent, tvMsg, tvCollect, tvScan;
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
            tvCollect = (TextView) root.findViewById(R.id.tv_dyCollection);
            tvScan = (TextView) root.findViewById(R.id.tv_scan);
            ivImg = (ImageView) root.findViewById(R.id.iv_dyHead);
            llCollection = (LinearLayout) root.findViewById(R.id.ll_collection);
            llScan = (LinearLayout) root.findViewById(R.id.ll_scan);
            this.root = root;
        }
    }
}