package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.annmanage.AnnManagerPresenter;
import com.laichushu.book.mvp.notice.NoticeModle;
import com.laichushu.book.ui.activity.AnnounMangeActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/20.
 */

public class AnnManageAdapter extends RecyclerView.Adapter<AnnManageAdapter.ViewHolder> {
    private AnnounMangeActivity context;
    private List<NoticeModle.DataBean> dataBeen;
    private AnnManagerPresenter bookcastPresener;

    public AnnManageAdapter(AnnounMangeActivity context, List<NoticeModle.DataBean> dataBean, AnnManagerPresenter bookcastPresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.bookcastPresener = bookcastPresener;
    }

    @Override
    public AnnManageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_ann_manage);
        return new AnnManageAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnnManageAdapter.ViewHolder holder, final int position) {
        holder.tvTime.setText(dataBeen.get(position).getReleaseTime());
        holder.tvContent.setText(dataBeen.get(position).getContent());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                删除该公告
                bookcastPresener.loadDelAnnManageDate(dataBeen.get(position).getId());
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

    public void refreshAdapter(List<NoticeModle.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
        }
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout llItem;
        public final TextView tvTime;
        public final TextView tvContent;
        public final ImageView ivDelete;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            llItem = (LinearLayout) root.findViewById(R.id.ll_item);
            tvTime = (TextView) root.findViewById(R.id.tv_annTime);
            tvContent = (TextView) root.findViewById(R.id.tv_annContent);
            ivDelete = (ImageView) root.findViewById(R.id.iv_annDelete);
            this.root = root;
        }
    }
}
