package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public FindGroupRecommenAdapter(ArrayList<FindCourseCommResult.DataBean> mRecommendData, FindGroupMainActivity mActivity) {
        this.mActivity = mActivity;
        this.mRecommendData = mRecommendData;
    }

    @Override
    public FindGroupRecommenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_group_recommen2);
        return new FindGroupRecommenViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindGroupRecommenViewHolder holder, int position) {
        FindCourseCommResult.DataBean dataBean = mRecommendData.get(position);
        holder.groupRecommenNameTv.setText(dataBean.getName());
        GlideUitl.loadImg(mActivity, dataBean.getPhoto(), holder.groupRecommenIv);
    }

    @Override
    public int getItemCount() {
        return mRecommendData.size() > 4 ? 4 : mRecommendData.size();
    }

    /**
     * 推荐列表
     */
    class FindGroupRecommenViewHolder extends RecyclerView.ViewHolder {
        private ImageView groupRecommenIv;
        private TextView groupRecommenNameTv;

        private FindGroupRecommenViewHolder(View itemView) {
            super(itemView);
            groupRecommenIv = (ImageView) itemView.findViewById(R.id.iv_group_recommen);
            groupRecommenNameTv = (TextView) itemView.findViewById(R.id.tv_group_recommen_name);
        }
    }

    public void setmRecommendData(ArrayList<FindCourseCommResult.DataBean> mRecommendData) {
        this.mRecommendData = mRecommendData;
        notifyDataSetChanged();
    }
}
