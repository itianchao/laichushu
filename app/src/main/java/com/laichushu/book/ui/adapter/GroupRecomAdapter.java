package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.FindPresenter;
import com.laichushu.book.mvp.find.group.GroupListModle;
import com.laichushu.book.ui.activity.FindGroupDetailActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/19.
 */

public class GroupRecomAdapter extends RecyclerView.Adapter<GroupRecomAdapter.ViewHolder> {
    private Activity mActivity;
    private List<GroupListModle.DataBean> dataBeen;
    private FindPresenter findPresenter;

    public GroupRecomAdapter(Activity mActivity, List<GroupListModle.DataBean> dataBean, FindPresenter findPresenter) {
        this.mActivity = mActivity;
        this.dataBeen = dataBean;
        this.findPresenter = findPresenter;
    }

    @Override
    public GroupRecomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_course_child);
        return new GroupRecomAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupRecomAdapter.ViewHolder holder, final int position) {
        final GroupListModle.DataBean dataBean = dataBeen.get(position);
        GlideUitl.loadCornersImg(mActivity, 60, dataBean.getPhoto(), holder.hot1Iv);
        holder.tvNAme.setText(dataBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean",dataBean);
                bundle.putInt("position",position);
                UIUtil.openActivity(mActivity,FindGroupDetailActivity.class,bundle);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void refreshAdapter(List<GroupListModle.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView hot1Iv;
        private TextView tvNAme;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            hot1Iv = (ImageView) root.findViewById(R.id.iv_hot1);
            tvNAme = (TextView) root.findViewById(R.id.tv_name1);
            this.root = root;
        }
    }
}
