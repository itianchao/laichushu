package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindEditorListModel;
import com.laichushu.book.bean.netbean.FindServiceInfoModel;
import com.laichushu.book.mvp.findservicepage.FindServicePagePresenter;
import com.laichushu.book.ui.activity.FindServerMainPageActivity;
import com.laichushu.book.ui.activity.FindServicePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/29.
 */

public class FindServicePageAdapter extends RecyclerView.Adapter<FindServicePageAdapter.ViewHolder> {
    private FindServicePageActivity context;
    private List<FindServiceInfoModel.DataBean> dataBeen;
    private FindServicePagePresenter findEditPagePresenter;

    public FindServicePageAdapter(FindServicePageActivity context, List<FindServiceInfoModel.DataBean> dataBean, FindServicePagePresenter findEditPagePresenter) {
        this.context = context;
        this.dataBeen = dataBean;
        this.findEditPagePresenter = findEditPagePresenter;
    }

    @Override
    public FindServicePageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_service);
        return new FindServicePageAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindServicePageAdapter.ViewHolder holder, final int position) {
        GlideUitl.loadRandImg(context, dataBeen.get(position).getPhoto(), holder.ivImg);
        switch (dataBeen.get(position).getServiceType()) {
            case 1:
                holder.rbNum.setRating(5);
                break;
            case 2:
                holder.rbNum.setRating(4);
                break;
            case 3:
                holder.rbNum.setRating(3);
                break;
        }
        holder.tvRealName.setText(dataBeen.get(position).getName());
        holder.tvDeails.setText(dataBeen.get(position).getServiceIntroduce());
        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转服务主页
                Bundle bundle = new Bundle();
                bundle.putSerializable("userId", dataBeen.get(position).getUserId());
                UIUtil.openActivity(context, FindServerMainPageActivity.class, bundle);
            }
        });
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }

    public void refreshAdapter(List<FindServiceInfoModel.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
        }
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout rlItem;
        public final TextView tvRealName, tvDeails;
        public final ImageView ivImg;
        public final RatingBar rbNum;

        public final View root;

        public ViewHolder(View root) {
            super(root);
            rlItem = (RelativeLayout) root.findViewById(R.id.rl_item);
            ivImg = (ImageView) root.findViewById(R.id.iv_userHeadImg);
            tvRealName = (TextView) root.findViewById(R.id.tv_realName);
            tvDeails = (TextView) root.findViewById(R.id.tv_details);
            rbNum = (RatingBar) root.findViewById(R.id.rb_details);
            this.root = root;
        }
    }
}
