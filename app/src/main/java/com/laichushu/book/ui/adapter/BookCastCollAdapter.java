package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.mine.bookcast.BookcastPresener;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.ui.activity.MyBookCastActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/21.
 */

public class BookCastCollAdapter extends RecyclerView.Adapter<BookCastCollAdapter.ViewHolder> {
    private MyBookCastActivity context;
    private List<HomeHotModel.DataBean> dataBeen;
    private BookcastPresener bookcastPresener;
    private boolean isShow;

    public BookCastCollAdapter(MyBookCastActivity context, List<HomeHotModel.DataBean> dataBean, BookcastPresener bookcastPresener, boolean isShow) {
        this.context = context;
        this.dataBeen = dataBean;
        this.bookcastPresener = bookcastPresener;
        this.isShow = isShow;
    }

    @Override
    public BookCastCollAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_bookcast);
        return new BookCastCollAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int width=(UIUtil.getScreenWidth()/3)-24;
        RelativeLayout.LayoutParams linearParams = new RelativeLayout.LayoutParams(
                width,(width/3)*4
        );
        holder.ivImg.setLayoutParams(linearParams);
        GlideUitl.loadImg(context, dataBeen.get(position).getCoverUrl(),width,(width/3)*4, holder.ivImg);
        holder.tvItem.setText(dataBeen.get(position).getName());
        if (isShow) {
            holder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            holder.ivDelete.setVisibility(View.GONE);
        }
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转图书详情页
                bookcastPresener.loadBookDetailsByid(dataBeen.get(position).getSourceId());
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookcastPresener.loadDeleteCollectBookById(dataBeen.get(position).getCollectId(), position);
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

    public void deleteDataRefresh(int pos) {
        dataBeen.remove(pos);
        this.notifyDataSetChanged();
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout llItem;
        public final TextView tvItem;
        public final ImageView ivImg;
        public final ImageView ivDelete;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            llItem = (RelativeLayout) root.findViewById(R.id.rl_item);
            tvItem = (TextView) root.findViewById(R.id.tv_item);
            ivImg = (ImageView) root.findViewById(R.id.iv_img);
            ivDelete = (ImageView) root.findViewById(R.id.iv_deleteBook);
            this.root = root;
        }
    }
}
