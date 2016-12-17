package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.ui.activity.MyWalletDetailsActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/26.
 */

public class MyWalletRecordAdapter extends RecyclerView.Adapter<MyWalletRecordAdapter.ViewHolder> {
    private MyWalletDetailsActivity context;
    private List<WalletBalanceReward.DataBean> dataBeen;

    public MyWalletRecordAdapter(MyWalletDetailsActivity context, List<WalletBalanceReward.DataBean> dataBean) {
        this.context = context;
        this.dataBeen = dataBean;
    }

    @Override
    public MyWalletRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_wallet_record);
        return new MyWalletRecordAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvtransName.setText(dataBeen.get(position).getTradeName());
        holder.tvTransDetails.setText(dataBeen.get(position).getTradeMoney()+"元  ");
        holder.tvTransState.setText(dataBeen.get(position).getStatus());
        holder.tvTransData.setText(dataBeen.get(position).getTradeTime()+"  ");
//        holder.llItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        //跳转图书详情页
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("bean", dataBeen.get(position));
//                UIUtil.openActivity(context, BookDetailActivity.class, bundle);
//            }
//        });
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }

    public void refreshAdapter(List<WalletBalanceReward.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvtransName, tvTransDetails, tvTransState, tvTransData;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            tvtransName = (TextView) root.findViewById(R.id.tv_transName);
            tvTransDetails = (TextView) root.findViewById(R.id.tv_transDetails);
            tvTransState = (TextView) root.findViewById(R.id.tv_transState);
            tvTransData = (TextView) root.findViewById(R.id.tv_transDate);
            this.root = root;
        }
    }
}
