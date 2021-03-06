package com.laichushu.book.ui.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.group.member.FindGroupMemberModle;
import com.laichushu.book.mvp.find.group.member.FindGroupMemberPresenter;
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

public class FindGroupMemberAdapter extends RecyclerView.Adapter<FindGroupMemberAdapter.ViewHolder> {
    private final static int TYPE1 = 0;
    private final static int TYPE2 = 1;
    private int offset = 1;
    private final FindGroupMemberPresenter mvpPresenter;
    private BaseActivity mActivity;
    private ArrayList<FindGroupMemberModle.DataBean> mData = new ArrayList<>();
    private int type;

    public FindGroupMemberAdapter(BaseActivity mActivity, ArrayList<FindGroupMemberModle.DataBean> mData, int type, FindGroupMemberPresenter mvpPresenter) {
        this.mData = mData;
        this.mActivity = mActivity;
        this.type = type;
        this.mvpPresenter = mvpPresenter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2) {
            return TYPE1;
        } else {
            return TYPE2;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder hodle = null;
        if (viewType == TYPE1) {
            View itemView = UIUtil.inflate(R.layout.item_find_group_menber_category);
            hodle = new MemberCategoryViewHolder(itemView);
        } else {
            View itemView = UIUtil.inflate(R.layout.item_find_group_menber);
            hodle = new FindGroupMemberViewHolder(itemView);
        }
        return hodle;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mData.isEmpty()) {
            return;
        }
        if (position == 0 || position == 2) {
            if (position==2){
                offset = 2;
            }
            if (position==0){
                offset = 1;
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.itemView.setLayoutParams(params);
            switch(position){
                case 0:
                    ((MemberCategoryViewHolder) holder).categroyTv.setText("组长");
                    break;
                case 2:
                    ((MemberCategoryViewHolder) holder).categroyTv.setText("成员");
//                    if(mData.size() > 1){
//                        ((MemberCategoryViewHolder) holder).categroyTv.setVisibility(View.VISIBLE);
//                    }else {
//                        ((MemberCategoryViewHolder) holder).categroyTv.setVisibility(View.VISIBLE);
//                    }
                    break;
            }
        } else {
            final FindGroupMemberModle.DataBean dataBean = mData.get(position - offset);
            switch (type) {
                case 1://关注 已经关注
                    ((FindGroupMemberViewHolder) holder).colorTv.setVisibility(View.VISIBLE);
                    if (dataBean.isFollow()) {//已关注
                        ((FindGroupMemberViewHolder) holder).colorTv.setTextColor(UIUtil.getColor(R.color.global));
                        ((FindGroupMemberViewHolder) holder).colorTv.setText("已关注");
                    } else {//关注
                        ((FindGroupMemberViewHolder) holder).colorTv.setTextColor(UIUtil.getColor(R.color.hint));
                        ((FindGroupMemberViewHolder) holder).colorTv.setText("关注");
                    }
                    ((FindGroupMemberViewHolder) holder).colorTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((FindGroupMemberViewHolder) holder).colorTv.getText().equals("关注")) {
                                mvpPresenter.loadAddFocus(dataBean.getUserId(), true, position-offset);
                            } else {
                                mvpPresenter.loadDelFocus(dataBean.getUserId(), false, position-offset);
                            }
                        }
                    });
                    break;
                case 2://待审核 同意 拒绝
                    switch (dataBean.getStatus()) {
                        case "1":// 申请加入
                            ((FindGroupMemberViewHolder) holder).colorTv.setVisibility(View.VISIBLE);
                            ((FindGroupMemberViewHolder) holder).normalTv.setVisibility(View.GONE);
                            ((FindGroupMemberViewHolder) holder).colorTv.setText("待处理");
                            ((FindGroupMemberViewHolder) holder).colorTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //同意 or 拒绝
                                    mvpPresenter.showApplyMemberDialog(position-offset, dataBean);
                                }
                            });
                            break;
                        case "2":// 拒绝
                            ((FindGroupMemberViewHolder) holder).colorTv.setVisibility(View.GONE);
                            ((FindGroupMemberViewHolder) holder).normalTv.setVisibility(View.VISIBLE);
                            ((FindGroupMemberViewHolder) holder).normalTv.setText("已拒绝");
                            break;
                        case "3":// 同意
                            ((FindGroupMemberViewHolder) holder).colorTv.setVisibility(View.GONE);
                            ((FindGroupMemberViewHolder) holder).normalTv.setVisibility(View.VISIBLE);
                            ((FindGroupMemberViewHolder) holder).normalTv.setText("已同意");
                            break;
                    }
                    if (((FindGroupMemberViewHolder) holder).colorTv.getText().toString().equals("待处理")) {
                        ((FindGroupMemberViewHolder) holder).colorTv.setTextColor(Color.RED);
                    } else {
                        ((FindGroupMemberViewHolder) holder).colorTv.setTextColor(UIUtil.getColor(R.color.hint));
                    }
                    break;
                case 3://删除成员
                    ((FindGroupMemberViewHolder) holder).deleteIv.setVisibility(View.VISIBLE);
                    ((FindGroupMemberViewHolder) holder).deleteIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mvpPresenter.showDeleteMemberDialog(position-offset, dataBean);
                        }
                    });
                    break;
            }
            if (dataBean.isShowFollow()) {
                ((FindGroupMemberViewHolder) holder).colorTv.setVisibility(View.GONE);
                ((FindGroupMemberViewHolder) holder).normalTv.setVisibility(View.GONE);
                ((FindGroupMemberViewHolder) holder).deleteIv.setVisibility(View.GONE);
            }

            GlideUitl.loadRandImg(mActivity, dataBean.getPhoto(), ((FindGroupMemberViewHolder) holder).headIv);//头像
            ((FindGroupMemberViewHolder) holder).nameTv.setText(dataBean.getName());//名字
            ((FindGroupMemberViewHolder) holder).headIv.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return mData == null ? 2 : mData.size() + 2;
    }

    public void setmData(ArrayList<FindGroupMemberModle.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FindGroupMemberViewHolder extends ViewHolder {
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

    class MemberCategoryViewHolder extends ViewHolder {

        private TextView categroyTv;

        private MemberCategoryViewHolder(View itemView) {
            super(itemView);
            categroyTv = (TextView) itemView.findViewById(R.id.tv_categroy);
        }
    }
}
