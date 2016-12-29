package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;
import com.laichushu.book.ui.activity.FindGroupCreateNewActivity;
import com.laichushu.book.ui.activity.FindGroupMainActivity;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 发现 - 小组主页- 小组列表
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupListAdapter extends RecyclerView.Adapter<FindGroupListAdapter.FindGroupListViewHolder> {

    private BaseActivity mActivity;
    private ArrayList<GroupListModle.DataBean> mData;

    /**
     * 构造方法
     */
    public FindGroupListAdapter(ArrayList<GroupListModle.DataBean> mData, BaseActivity mActivity) {
        this.mActivity = mActivity;
        this.mData = mData;
    }
    
    /**
     * @return 条目数量
     */
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public FindGroupListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_group_list);
        return new FindGroupListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindGroupListViewHolder holder, int position) {
        RelativeLayout.LayoutParams parames = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtil.dip2px(70));
        holder.itemView.setLayoutParams(parames);
        GroupListModle.DataBean dataBean = mData.get(position);
        holder.groupNameTv.setText(dataBean.getName());
        holder.groupNumberTv.setText(dataBean.getJoinNum()+"人");
        GlideUitl.loadImg(mActivity, dataBean.getPhoto(), holder.groupIv);

    }

    /**
     * ViewHolder
     */
    class FindGroupListViewHolder extends RecyclerView.ViewHolder {
        private TextView groupNameTv;
        private TextView groupNumberTv;
        private ImageView groupIv;
        private View itemView;

        private FindGroupListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            groupIv = (ImageView) itemView.findViewById(R.id.iv_group);
            groupNameTv = (TextView) itemView.findViewById(R.id.tv_group_name);
            groupNumberTv = (TextView) itemView.findViewById(R.id.tv_group_number);
        }
    }

    public void setmData(ArrayList<GroupListModle.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}
