package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomePageDynamicAdapter extends RecyclerView.Adapter<HomePageDynamicAdapter.ViewHolder> {
    private PersonalHomePageActivity context;
    private List<HomeUseDyrResult.DataBean> dataBeen;

    public HomePageDynamicAdapter(PersonalHomePageActivity context, List<HomeUseDyrResult.DataBean> dataBean) {
        this.context = context;
        this.dataBeen = dataBean;
    }

    @Override
    public HomePageDynamicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_homepage_dynemic);
        return new HomePageDynamicAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideUitl.loadRandImg(context, null, holder.ivImg);
        holder.tvShopName.setText(dataBeen.get(position).getTitle());
        holder.tvTime.setText(dataBeen.get(position).getCreateDate());
        holder.tvTitle.setText(dataBeen.get(position).getTitle());
        holder.tvTitleContent.setText(dataBeen.get(position).getContent());
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

    public void refreshAdapter(List<HomeUseDyrResult.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvShopName,tvTime,tvTitle,tvTitleContent,tvMsg,tvFocus,tvScan;
        public final ImageView ivImg;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            tvShopName = (TextView) root.findViewById(R.id.tv_dyName);
            tvTime = (TextView) root.findViewById(R.id.tv_dyTime);
            tvTitle = (TextView) root.findViewById(R.id.tv_dyTitleName);
            tvTitleContent = (TextView) root.findViewById(R.id.tv_dyTitleContent);
            tvMsg = (TextView) root.findViewById(R.id.tv_msg);
            tvFocus = (TextView) root.findViewById(R.id.tv_focus);
            tvScan = (TextView) root.findViewById(R.id.tv_scan);
            ivImg = (ImageView) root.findViewById(R.id.iv_dyHead);
            this.root = root;
        }
    }
}