package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.ui.activity.MechanismDetailActivity;
import com.laichushu.book.ui.activity.MechanismSearchActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 机构列表Adapter
 * Created by wangtong on 2016/11/25.
 */

public class MechanismSearchListAdapter extends RecyclerView.Adapter<MechanismSearchListAdapter.AdapterViewHodler> {
    private MechanismSearchActivity mActivity;
    private ArrayList<MechanismListBean.DataBean> mData;

    public ArrayList<MechanismListBean.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<MechanismListBean.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public MechanismSearchListAdapter(MechanismSearchActivity mechanismListActivity, ArrayList<MechanismListBean.DataBean> mData) {
        this.mActivity = mechanismListActivity;
        this.mData = mData;
    }

    @Override
    public AdapterViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_mechanism);
        return new AdapterViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterViewHodler holder, int position) {
        final MechanismListBean.DataBean dataBean = mData.get(position);
        holder.itemView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
        GlideUitl.loadImg(mActivity,dataBean.getLogoUrl(),holder.mechanismIv);
        holder.nameTv.setText(dataBean.getName());
        holder.addressTv.setText(dataBean.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean",dataBean);
                bundle.putString("articleId",mActivity.getArticleId());
                UIUtil.openActivity(mActivity,MechanismDetailActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    public class AdapterViewHodler extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView addressTv;
        private ImageView mechanismIv;
        private View itemView;

        public AdapterViewHodler(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mechanismIv = (ImageView) itemView.findViewById(R.id.iv_mechanism);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            addressTv = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }
}

