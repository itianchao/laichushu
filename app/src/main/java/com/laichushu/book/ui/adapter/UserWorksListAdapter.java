package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.mine.userhomepage.UserHomePagePresener;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/25.
 */

public class UserWorksListAdapter extends RecyclerView.Adapter<UserWorksListAdapter.ViewHolder> {
    private UserHomePageActivity context;
    private List<HomeHotModel.DataBean> dataBeen;
    private UserHomePagePresener userHomePagePresener;
    public UserWorksListAdapter(UserHomePageActivity context, List<HomeHotModel.DataBean> dataBean,UserHomePagePresener userHomePagePresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.userHomePagePresener=userHomePagePresener;
    }

    @Override
    public UserWorksListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_bookcast);
        return new UserWorksListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserWorksListAdapter.ViewHolder holder, final int position) {
        int width=(UIUtil.getScreenWidth()/3)-24;
        RelativeLayout.LayoutParams linearParams = new RelativeLayout.LayoutParams(
                width,(width/3)*4
        );
        holder.ivImg.setLayoutParams(linearParams);
        GlideUitl.loadImg(context, dataBeen.get(position).getCoverUrl(),width,(width/3)*4, holder.ivImg);
        holder.tvItem.setText(dataBeen.get(position).getArticleName());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转图书详情页
            userHomePagePresener.loadBookDetailsByid(dataBeen.get(position).getArticleId());
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
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout llItem;
        public final TextView tvItem;
        public final ImageView ivImg;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            llItem = (RelativeLayout) root.findViewById(R.id.ll_item);
            tvItem = (TextView) root.findViewById(R.id.tv_item);
            ivImg = (ImageView) root.findViewById(R.id.iv_img);
            this.root = root;
        }
    }
}
