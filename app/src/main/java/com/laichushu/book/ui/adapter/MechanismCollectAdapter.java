package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.mechanism.mechanismcollect.MechanismCollectPresenter;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.ui.activity.MechanismCollectActivity;
import com.laichushu.book.ui.activity.MechanismDetailActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * Created by PCPC on 2017/1/16.
 */

public class MechanismCollectAdapter extends RecyclerView.Adapter<MechanismCollectAdapter.AdapterViewHodler> {
    private MechanismCollectActivity mActivity;
    private ArrayList<HomeHotModel.DataBean> mData;
    private MechanismCollectPresenter presenter;

    public ArrayList<HomeHotModel.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<HomeHotModel.DataBean> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    public MechanismCollectAdapter(MechanismCollectActivity mechanismListActivity, ArrayList<HomeHotModel.DataBean> mData, MechanismCollectPresenter presenter) {
        this.mActivity = mechanismListActivity;
        this.mData = mData;
        this.presenter = presenter;
    }

    @Override
    public MechanismCollectAdapter.AdapterViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_mechanism_collect);
        return new MechanismCollectAdapter.AdapterViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(MechanismCollectAdapter.AdapterViewHodler holder, int position) {
        final HomeHotModel.DataBean dataBean = mData.get(position);
        holder.itemView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
        GlideUitl.loadImg(mActivity, dataBean.getLogoUrl(), holder.mechanismIv);
        holder.nameTv.setText(dataBean.getPartyName());
        holder.addressTv.setText("出版社地址:" + dataBean.getAddress());
        String grade = "";
        if (null != dataBean.getGrade()) {
            switch (dataBean.getGrade()) {
                case "1":
                    grade = "一星级服务者";
                    break;
                case "2":
                    grade = "二星级服务者";
                    break;
                case "3":
                    grade = "三星级服务者";
                    break;
                case "4":
                    grade = "四星级服务者";
                    break;
                case "5":
                    grade = "五星级服务者";
                    break;
            }
        }
        holder.gradeTv.setText("出版社级别:" + grade);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadMechanismListData(ConstantValue.MECHANISM_TYPE,dataBean.getPartyId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class AdapterViewHodler extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView addressTv;
        private ImageView mechanismIv;
        private TextView gradeTv;
        private View itemView;

        public AdapterViewHodler(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mechanismIv = (ImageView) itemView.findViewById(R.id.iv_mechanism);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            gradeTv = (TextView) itemView.findViewById(R.id.tv_publishGrade);
            addressTv = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }
}
