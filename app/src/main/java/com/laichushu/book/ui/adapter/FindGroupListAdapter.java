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
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 发现 - 小组主页- 小组列表
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupListAdapter extends RecyclerView.Adapter<FindGroupListAdapter.FindGroupListViewHolder> {

    private FindGroupMainActivity mActivity;
    private ArrayList<GroupListModle.DataBean> mData;

    /**
     * 构造方法
     */
    public FindGroupListAdapter(ArrayList<GroupListModle.DataBean> mData, FindGroupMainActivity mActivity) {
        this.mActivity = mActivity;
        this.mData = mData;
    }

    /***
     * 多重行视图标记
     */
    private final static int TYPE1 = 0;
    private final static int TYPE2 = 1;
    private final static int TYPE3 = 2;

    /**
     * @return 条目数量
     */
    @Override
    public int getItemCount() {
        return mData == null ? 2 : mData.size() + 2;
    }

    /**
     * 多种行视图
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE1;
        } else if (position == (mData == null ? 1 : mData.size() + 1)) {
            return TYPE3;
        } else {
            return TYPE2;
        }
    }

    @Override
    public FindGroupListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        FindGroupListViewHolder holder = null;
        switch (viewType) {
            case TYPE1:
                itemView = UIUtil.inflate(R.layout.item_find_group_create);
                holder = new ViewHolder1(itemView);
                break;
            case TYPE2:
                itemView = UIUtil.inflate(R.layout.item_find_group_list);
                holder = new ViewHolder2(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(FindGroupListViewHolder holder, int position) {
        if (position == 0) {
            ((ViewHolder1) holder).createGroupTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2016/12/27 跳转创建页面
                    UIUtil.openActivity(mActivity, FindGroupCreateNewActivity.class);
                }
            });
            RelativeLayout.LayoutParams parames = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtil.dip2px(40));
            ((ViewHolder1) holder).itemView.setLayoutParams(parames);
        } else {
            GroupListModle.DataBean dataBean = mData.get(position - 1);
            ((ViewHolder2) holder).groupNameTv.setText(dataBean.getName());
            GlideUitl.loadImg(mActivity, dataBean.getPhoto(), ((ViewHolder2) holder).groupIv);
        }
    }

    /**
     * ViewHolder
     */
    class FindGroupListViewHolder extends RecyclerView.ViewHolder {
        private FindGroupListViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 加入小组 创建小组
     */
    private class ViewHolder1 extends FindGroupListViewHolder {

        private TextView createGroupTv;
        private View itemView;

        private ViewHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            createGroupTv = (TextView) itemView.findViewById(R.id.tv_create_group);//创建小组按钮
        }

    }

    /**
     * 小组列表
     */
    private class ViewHolder2 extends FindGroupListViewHolder {

        private TextView groupNameTv;
        private ImageView groupIv;
        private View itemView;

        private ViewHolder2(View itemView) {
            super(itemView);
            this.itemView = itemView;
            groupIv = (ImageView) itemView.findViewById(R.id.iv_group);
            groupNameTv = (TextView) itemView.findViewById(R.id.tv_group_name);

        }
    }


    public void setmData(ArrayList<GroupListModle.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
}
