package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindLessonListResult;
import com.laichushu.book.mvp.findfragment.FindPresenter;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/19.
 */

public class ClassRecycleAdapter extends RecyclerView.Adapter<ClassRecycleAdapter.ViewHolder> {
    private Activity context;
    private List<FindLessonListResult.DataBean.LessonListBean> dataBeen;
    private FindPresenter bookcastPresener;

    public ClassRecycleAdapter(Activity context, List<FindLessonListResult.DataBean.LessonListBean> dataBean, FindPresenter bookcastPresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.bookcastPresener = bookcastPresener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_class_find);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideUitl.loadImg(context, dataBeen.get(position).getThumbUrl(), holder.ivImg);
        holder.tvItem.setText(dataBeen.get(position).getName());
        holder.tvPlayNum.setText(dataBeen.get(position).getClickNum()+"");
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转图书详情页
//                bookcastPresener.loadBookDetailsByid(dataBeen.get(position).getArticleId());
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

    public void refreshAdapter(List<FindLessonListResult.DataBean.LessonListBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout llItem;
        public final TextView tvItem;
        public final TextView tvPlayNum;
        public final ImageView ivImg;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            llItem = (LinearLayout) root.findViewById(R.id.ll_class_item);
            tvItem = (TextView) root.findViewById(R.id.tv_class_name);
            tvPlayNum = (TextView) root.findViewById(R.id.tv_class_playNum);
            ivImg = (ImageView) root.findViewById(R.id.iv_class_img);
            this.root = root;
        }
    }
}
