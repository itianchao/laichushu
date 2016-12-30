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
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CollectSaveDate_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.TopicDetilActivity;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 机构话题列表
 * Created by wangtong on 2016/11/26.
 */

public class MechanismTopicListAdapter extends RecyclerView.Adapter<MechanismTopicListAdapter.ViewHolder> {
    private ArrayList<MechanismTopicListModel.DataBean> mData;
    private BaseActivity mActivity;
    private String type;
    private int currentNum;
    private String userId = ConstantValue.USERID;

    public MechanismTopicListAdapter(ArrayList<MechanismTopicListModel.DataBean> mData, BaseActivity mActivity) {
        this.mData = mData;
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_mechanismtopiclist);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
        holder.contentIay.setVisibility(View.VISIBLE);
        final MechanismTopicListModel.DataBean dataBean = mData.get(position);
        holder.topicNameTv.setText(dataBean.getTitle());
        holder.topicAuthorTv.setText(dataBean.getCreatUserName());
        holder.topicContentTv.setText(dataBean.getContent());
        holder.topicTiemTv.setText(dataBean.getCreateDate());
        GlideUitl.loadRandImg(mActivity, dataBean.getCreaterPhoto(), holder.topicUserheadIv);
        holder.scanIay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", dataBean);
                bundle.putString("type", "");
                UIUtil.openActivity(mActivity, TopicDetilActivity.class, bundle);
            }
        });
        currentNum = dataBean.getCollectNum();
        if (dataBean.isCollect()) {
            holder.likeTv.setText("已收藏(" + dataBean.getCollectNum() + ")");
            Drawable drawable = mActivity.getResources().getDrawable(R.drawable.icon_praise_yes2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.likeTv.setCompoundDrawables(drawable, null, null, null);
        } else {
            holder.likeTv.setText("收藏(" + dataBean.getCollectNum() + ")");
            Drawable drawable = mActivity.getResources().getDrawable(R.drawable.icon_praise_no2x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.likeTv.setCompoundDrawables(drawable, null, null, null);
        }
        holder.likeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dataBean.isCollect()) {
                    //添加收藏
                    type = "0";
                    loadCollectSaveDate(dataBean, ConstantValue.COLLECTTOPIC_TYPE, type,position);
                } else {
                    //取消收藏
                    type = "1";
                    loadCollectSaveDate(dataBean, ConstantValue.COLLECTTOPIC_TYPE, type,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout scanIay;
        private LinearLayout contentIay;
        private ImageView topicUserheadIv;
        private TextView likeTv;
        private TextView topicAuthorTv;
        private TextView topicNameTv;
        private TextView topicContentTv;
        private TextView topicTiemTv;

        public ViewHolder(View itemView) {
            super(itemView);
            topicUserheadIv = (ImageView) itemView.findViewById(R.id.iv_topic_userhead);
            likeTv = (TextView) itemView.findViewById(R.id.tv_like);
            scanIay = (LinearLayout) itemView.findViewById(R.id.ll_scan);
            contentIay = (LinearLayout) itemView.findViewById(R.id.lay_content);
            topicAuthorTv = (TextView) itemView.findViewById(R.id.tv_topic_author);
            topicContentTv = (TextView) itemView.findViewById(R.id.tv_topic_content);
            topicTiemTv = (TextView) itemView.findViewById(R.id.tv_topic_time);
            topicNameTv = (TextView) itemView.findViewById(R.id.tv_topic_name);
        }
    }

    public ArrayList<MechanismTopicListModel.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<MechanismTopicListModel.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    /**
     * 话题收藏
     *
     * @param dataBean
     * @param sourceType
     * @param type
     */
    public void loadCollectSaveDate(final MechanismTopicListModel.DataBean dataBean, String sourceType, final String type, final int position) {
        mActivity.showProgressDialog();
        String sourceId = dataBean.getId();
        CollectSaveDate_Paramet collectSave = new CollectSaveDate_Paramet(userId, sourceId, sourceType, type);
        LoggerUtil.toJson(collectSave);
        mActivity.addSubscription(mActivity.apiStores.collectSaveData(collectSave), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mActivity.dismissProgressDialog();
                if (model.isSuccess()) {
                    if (type.equals("0")) {//收藏
                        currentNum++;
                        dataBean.setCollect(true);
                    } else {//取消收藏
                        currentNum--;
                        dataBean.setCollect(false);
                    }
                    dataBean.setCollectNum(currentNum);
                    mData.set(position,dataBean);
                    notifyDataSetChanged();
                } else {
                    if (type.equals("1")) {//收藏
                        ToastUtil.showToast("收藏失败");
                    } else {//取消收藏
                        ToastUtil.showToast("取消失败");
                    }
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                mActivity.dismissProgressDialog();
                if (type.equals("0")) {//收藏
                    ToastUtil.showToast("收藏失败");
                } else {//取消收藏
                    ToastUtil.showToast("取消失败");
                }
            }

            @Override
            public void onFinish() {
                mActivity.dismissProgressDialog();
            }
        });
    }
}
