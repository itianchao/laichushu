package com.laichushu.book.ui.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.findgroup.findgroupmenber.FindGroupMenberModle;
import com.laichushu.book.mvp.findgroup.findgroupmenber.FindGroupMenberPresenter;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 小组
 * Created by wangtong on 2016/12/30.
 */

public class FindGroupMemberAdapter extends RecyclerView.Adapter<FindGroupMemberAdapter.FindGroupMemberViewHolder> {
    private final FindGroupMenberPresenter mvpPresenter;
    private BaseActivity mActivity;
    private ArrayList<FindGroupMenberModle.DataBean> mData = new ArrayList<>();
    private int type;

    public FindGroupMemberAdapter(BaseActivity mActivity, ArrayList<FindGroupMenberModle.DataBean> mData, int type, FindGroupMenberPresenter mvpPresenter) {
        this.mData = mData;
        this.mActivity = mActivity;
        this.type = type;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public FindGroupMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_group_menber);
        return new FindGroupMemberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FindGroupMemberViewHolder holder, final int position) {

        final FindGroupMenberModle.DataBean dataBean = mData.get(position);
        switch (type) {
            case 1://关注 已经关注
                holder.colorTv.setVisibility(View.VISIBLE);
                if (dataBean.isFollow()) {//已关注
                    holder.colorTv.setTextColor(UIUtil.getColor(R.color.global));
                    holder.colorTv.setText("已关注");
                } else {//关注
                    holder.colorTv.setTextColor(UIUtil.getColor(R.color.hint));
                    holder.colorTv.setText("关注");
                }
                holder.colorTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.colorTv.getText().equals("关注")) {
                            mvpPresenter.loadAddFocus(dataBean.getUserId(), true, position);
                        } else {
                            mvpPresenter.loadDelFocus(dataBean.getUserId(), false, position);
                        }
                    }
                });
                break;
            case 2://待审核 同意 拒绝
                switch (dataBean.getStatus()) {
                    case "1":// 申请加入
                        holder.colorTv.setVisibility(View.VISIBLE);
                        holder.normalTv.setVisibility(View.GONE);
                        holder.colorTv.setText("待处理");
                        holder.colorTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //同意 or 拒绝
                                mvpPresenter.showApplyMemberDialog(position, dataBean);
                            }
                        });
                        break;
                    case "2":// 拒绝
                        holder.colorTv.setVisibility(View.GONE);
                        holder.normalTv.setVisibility(View.VISIBLE);
                        holder.normalTv.setText("已拒绝");
                        break;
                    case "3":// 同意
                        holder.colorTv.setVisibility(View.GONE);
                        holder.normalTv.setVisibility(View.VISIBLE);
                        holder.normalTv.setText("已同意");
                        break;
                }
                if (holder.colorTv.getText().toString().equals("待处理")) {
                    holder.colorTv.setTextColor(Color.RED);
                } else {
                    holder.colorTv.setTextColor(UIUtil.getColor(R.color.hint));
                }
                break;
            case 3://删除成员
                holder.deleteIv.setVisibility(View.VISIBLE);
                holder.deleteIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mvpPresenter.showDeleteMemberDialog(position, dataBean);
                    }
                });
                break;
        }

        GlideUitl.loadRandImg(mActivity, dataBean.getPhoto(), holder.headIv);//头像
        holder.nameTv.setText(dataBean.getName());//名字
        holder.headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转用户主页
                Bundle bundle = new Bundle();
                bundle.putSerializable("userId", dataBean.getUserId());
                if (ConstantValue.USERID.equals(dataBean.getUserId())) {
                    UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, bundle);
                } else {
                    UIUtil.openActivity(mActivity, UserHomePageActivity.class, bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setmData(ArrayList<FindGroupMenberModle.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    class FindGroupMemberViewHolder extends RecyclerView.ViewHolder {
        private View itemView;//条目容器
        private ImageView headIv;//头像
        private ImageView deleteIv;//删除
        private TextView nameTv;//名字
        private TextView normalTv;//无背景
        private TextView colorTv;//有背景

        private FindGroupMemberViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            headIv = (ImageView) itemView.findViewById(R.id.iv_head);
            deleteIv = (ImageView) itemView.findViewById(R.id.iv_delete);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            normalTv = (TextView) itemView.findViewById(R.id.tv_normal);
            colorTv = (TextView) itemView.findViewById(R.id.tv_color);
        }
    }
}
