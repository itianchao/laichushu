package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindServiceCooperateMode;
import com.laichushu.book.bean.netbean.FindServiceItemListModel;
import com.laichushu.book.mvp.mineservice.MineServicePresenter;
import com.laichushu.book.ui.activity.FindServerMainPageActivity;
import com.laichushu.book.ui.activity.MineAddServantActivity;
import com.laichushu.book.ui.activity.MineServicePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/31.
 */

public class MineAddServiceAdapter extends RecyclerView.Adapter<MineAddServiceAdapter.ViewHolder> {
    private MineAddServantActivity context;
    private List<FindServiceItemListModel.DataBean> dataBeen;

    public MineAddServiceAdapter(MineAddServantActivity context, List<FindServiceItemListModel.DataBean> dataBean) {
        this.context = context;
        this.dataBeen = dataBean;
    }

    @Override
    public MineAddServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_mine_service_content);
        return new MineAddServiceAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MineAddServiceAdapter.ViewHolder holder, final int position) {
        holder.nickname.setText(dataBeen.get(position).getName());
        holder.ivInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("详情");
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

    public void refreshAdapter(List<FindServiceItemListModel.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
        }
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout rlItem;
        public final TextView nickname;
        public final ImageView ivInto;

        public final View root;

        public ViewHolder(View root) {
            super(root);
            rlItem = (RelativeLayout) root.findViewById(R.id.rl_serviceName);

            nickname = (TextView) root.findViewById(R.id.tv_serviceName);
            ivInto = (ImageView) root.findViewById(R.id.iv_serviceName);

            this.root = root;
        }
    }
}

