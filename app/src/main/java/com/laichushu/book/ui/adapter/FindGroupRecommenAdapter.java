package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindCourseCommResult;
import com.laichushu.book.ui.activity.FindGroupMainActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 发现 - 小组主页 - 小组推荐
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupRecommenAdapter extends RecyclerView.Adapter<FindGroupRecommenAdapter.FindGroupRecommenViewHolder> {
    private FindGroupMainActivity mActivity;
    private ArrayList<FindCourseCommResult.DataBean> mRecommendData;
    private final static int TYPE1 = 0;
    private final static int TYPE2 = 1;

    public FindGroupRecommenAdapter(ArrayList<FindCourseCommResult.DataBean> mRecommendData, FindGroupMainActivity mActivity) {
        this.mActivity = mActivity;
        this.mRecommendData = mRecommendData;
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
        } else {
            return TYPE2;
        }
    }

    @Override
    public FindGroupRecommenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        FindGroupRecommenViewHolder holder = null;
        switch (viewType) {
            case TYPE1:
                itemView = UIUtil.inflate(R.layout.item_find_group_recommen2);
                holder = new ViewHolder2(itemView);
                break;
            case TYPE2:
                itemView = UIUtil.inflate(R.layout.item_find_group_recommen);
                holder = new ViewHolder1(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(FindGroupRecommenViewHolder holder, int position) {
        if (position == 0) {
            LinearLayout.LayoutParams parames = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, UIUtil.dip2px(40));
            ((ViewHolder2) holder).itemView.setLayoutParams(parames);
        } else {
            FindCourseCommResult.DataBean dataBean = mRecommendData.get(position);
            ((ViewHolder1) holder).groupRecommenNameTv.setText(dataBean.getName());
            GlideUitl.loadImg(mActivity, dataBean.getPhoto(), ((ViewHolder1) holder).groupRecommenIv);
        }
    }

    @Override
    public int getItemCount() {
        return mRecommendData.size() > 4 ? 5 : mRecommendData.size() + 1;
    }

    class FindGroupRecommenViewHolder extends RecyclerView.ViewHolder {
        private FindGroupRecommenViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 推荐列表
     */
    class ViewHolder1 extends FindGroupRecommenViewHolder {
        private ImageView groupRecommenIv;
        private TextView groupRecommenNameTv;

        private ViewHolder1(View itemView) {
            super(itemView);
            groupRecommenIv = (ImageView) itemView.findViewById(R.id.iv_group_recommen);
            groupRecommenNameTv = (TextView) itemView.findViewById(R.id.tv_group_recommen_name);
        }
    }

    /**
     * 小组推荐
     */
    private class ViewHolder2 extends FindGroupRecommenViewHolder {
        private View itemView;

        private ViewHolder2(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    public void setmRecommendData(ArrayList<FindCourseCommResult.DataBean> mRecommendData) {
        this.mRecommendData = mRecommendData;
        notifyDataSetChanged();
    }
}
