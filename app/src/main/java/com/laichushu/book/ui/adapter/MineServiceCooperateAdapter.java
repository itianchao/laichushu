package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindEditorListModel;
import com.laichushu.book.bean.netbean.FindServiceCooperateMode;
import com.laichushu.book.mvp.mineservice.MineServicePresenter;
import com.laichushu.book.ui.activity.FindServerMainPageActivity;
import com.laichushu.book.ui.activity.MineServicePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * 我的服务 合作
 * Created by PCPC on 2016/12/31.
 */

public class MineServiceCooperateAdapter extends RecyclerView.Adapter<MineServiceCooperateAdapter.ViewHolder> {
    private MineServicePageActivity context;
    private List<FindServiceCooperateMode.DataBean> dataBeen;
    private MineServicePresenter findEditPagePresenter;

    public MineServiceCooperateAdapter(MineServicePageActivity context, List<FindServiceCooperateMode.DataBean> dataBean, MineServicePresenter findEditPagePresenter) {
        this.context = context;
        this.dataBeen = dataBean;
        this.findEditPagePresenter = findEditPagePresenter;
    }

    @Override
    public MineServiceCooperateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_mine_service_cooperate);
        return new MineServiceCooperateAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MineServiceCooperateAdapter.ViewHolder holder, final int position) {
        GlideUitl.loadRandImg(context, dataBeen.get(position).getPhoto(), holder.ivImg);
        holder.nickname.setText(dataBeen.get(position).getName());
        switch (dataBeen.get(position).getServiceType()) {
            case 1:
                holder.serviceType.setText("五星服务者");
                break;
            case 2:
                holder.serviceType.setText("四星服务者");
                break;
            case 3:
                holder.serviceType.setText("三星服务者");
                break;
        }
        holder.teamworkNum.setText(dataBeen.get(position).getCooperateNum() + "已合作");
        holder.designContent.setText(dataBeen.get(position).getServiceIntroduce());
        holder.design.setText(dataBeen.get(position).getServiceIntroduce());
        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转服务主页
                Bundle bundle = new Bundle();
                bundle.putSerializable("userId", dataBeen.get(position).getUserId());
                UIUtil.openActivity(context, FindServerMainPageActivity.class, bundle);
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

    public void refreshAdapter(List<FindServiceCooperateMode.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
        }
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout rlItem;
        public final TextView nickname, serviceType, designContent, design, teamworkNum;
        public final ImageView ivImg;

        public final View root;

        public ViewHolder(View root) {
            super(root);
            rlItem = (RelativeLayout) root.findViewById(R.id.rl_item);
            ivImg = (ImageView) root.findViewById(R.id.iv_userHeadImg);
            nickname = (TextView) root.findViewById(R.id.tv_nickname);
            serviceType = (TextView) root.findViewById(R.id.tv_serviceType);
            design = (TextView) root.findViewById(R.id.tv_bgDesign);
            designContent = (TextView) root.findViewById(R.id.tv_bgDesignContent);
            teamworkNum = (TextView) root.findViewById(R.id.tv_teamworkNumber);
            this.root = root;
        }
    }
}

