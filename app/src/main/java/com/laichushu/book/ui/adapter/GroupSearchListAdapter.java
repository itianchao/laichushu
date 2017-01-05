package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;
import com.laichushu.book.ui.activity.FindGroupDetailActivity;
import com.laichushu.book.ui.activity.FindGroupSearchActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 搜索小组
 * Created by wangtong on 2016/12/28.
 */

public class GroupSearchListAdapter extends RecyclerView.Adapter<GroupSearchListAdapter.GroupSearchListViewHolder> {

    private FindGroupSearchActivity mActivity;
    private ArrayList<GroupListModle.DataBean> mData;

    public GroupSearchListAdapter(FindGroupSearchActivity mActivity, ArrayList<GroupListModle.DataBean> mData) {
        this.mActivity = mActivity;
        this.mData = mData;
    }

    @Override
    public GroupSearchListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_group_list);
        return new GroupSearchListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupSearchListViewHolder holder, final int position) {
        RelativeLayout.LayoutParams parames = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtil.dip2px(70));
        holder.itemView.setLayoutParams(parames);
        final GroupListModle.DataBean dataBean = mData.get(position);
        holder.groupNameTv.setText(dataBean.getName());
        GlideUitl.loadImg(mActivity, dataBean.getPhoto(), holder.groupIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean",dataBean);
                bundle.putInt("position",position);
                UIUtil.openActivity(mActivity,FindGroupDetailActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class GroupSearchListViewHolder extends RecyclerView.ViewHolder {

        private TextView groupNameTv;
        private ImageView groupIv;
        private View itemView;

        private GroupSearchListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            groupIv = (ImageView) itemView.findViewById(R.id.iv_group);
            groupNameTv = (TextView) itemView.findViewById(R.id.tv_group_name);

        }
    }

    public void setmData(ArrayList<GroupListModle.DataBean> mData) {
        this.mData = mData;
    }
}
