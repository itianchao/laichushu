package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.mineservice.MineServicePresenter;
import com.laichushu.book.ui.activity.MineServicePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * 我的服务--首页
 * Created by PCPC on 2016/12/30.
 */

public class MineServiceCollectAdapter extends RecyclerView.Adapter<MineServiceCollectAdapter.ViewHolder> {
    private MineServicePageActivity context;
    private List<HomeHotModel.DataBean> dataBeen;
    private MineServicePresenter bookcastPresener;
    public MineServiceCollectAdapter(MineServicePageActivity context, List<HomeHotModel.DataBean> dataBean,MineServicePresenter bookcastPresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.bookcastPresener=bookcastPresener;
    }

    @Override
    public MineServiceCollectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_bookcast);
        return new MineServiceCollectAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MineServiceCollectAdapter.ViewHolder holder, final int position) {
        GlideUitl.loadImg(context, dataBeen.get(position).getCoverUrl(), holder.ivImg);
        holder.tvItem.setText(dataBeen.get(position).getName());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转图书详情页
                bookcastPresener.loadBookDetailsByid(dataBeen.get(position).getSourceId());
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

    public void refreshAdapter(List<HomeHotModel.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout llItem;
        public final TextView tvItem;
        public final ImageView ivImg;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            llItem = (LinearLayout) root.findViewById(R.id.ll_item);
            tvItem = (TextView) root.findViewById(R.id.tv_item);
            ivImg = (ImageView) root.findViewById(R.id.iv_img);
            this.root = root;
        }
    }
}
