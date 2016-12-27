package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.activity.FindGroupMainActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 小组主页 - 小组推荐
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupRecommenAdapter extends RecyclerView.Adapter<FindGroupRecommenAdapter.FindGroupRecommenViewHolder> {
    private FindGroupMainActivity mActivity;

    public FindGroupRecommenAdapter(FindGroupMainActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public FindGroupRecommenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_group_recommen2);
        return new FindGroupRecommenViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindGroupRecommenViewHolder holder, int position) {
        holder.groupRecommenNameTv.setText("");
        GlideUitl.loadImg(mActivity,"",holder.groupRecommenIv);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class FindGroupRecommenViewHolder extends RecyclerView.ViewHolder {

        private ImageView groupRecommenIv;
        private TextView groupRecommenNameTv;

        private FindGroupRecommenViewHolder(View itemView) {
            super(itemView);
            groupRecommenIv = (ImageView) itemView.findViewById(R.id.iv_group_recommen);
            groupRecommenNameTv = (TextView) itemView.findViewById(R.id.tv_group_recommen_name);
        }
    }
}
