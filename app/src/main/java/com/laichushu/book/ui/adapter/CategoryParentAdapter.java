package com.laichushu.book.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.home.homecategory.CategoryModle;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 分类 parent Adapter
 * Created by wangtong on 2016/11/10.
 */

public class CategoryParentAdapter extends BaseAdapter {
    private ArrayList<CategoryModle.DataBean> mParentData;

    public CategoryParentAdapter(ArrayList<CategoryModle.DataBean> mParentData) {
        this.mParentData = mParentData;
    }

    @Override
    public int getCount() {
        return mParentData == null ? 0 : mParentData.size();
    }

    @Override
    public CategoryModle.DataBean getItem(int position) {
        return mParentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = UIUtil.inflate(R.layout.item_category_parent);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.parentTv.setText(mParentData.get(position).getName());
        if (mParentData.get(position).isPressd()) {
            holder.rlParent.setBackgroundColor(UIUtil.getColor(R.color.SpringGreen));
            holder.parentTv.setTextColor(UIUtil.getColor(R.color.white));
        } else {
            holder.rlParent.setBackgroundColor(UIUtil.getColor(R.color.frenchGrey));
            holder.parentTv.setTextColor(UIUtil.getColor(R.color.edit));
        }
        return convertView;
    }

    class ViewHolder {

        private TextView parentTv;
        private RelativeLayout rlParent;

        public ViewHolder(View itemView) {
            parentTv = (TextView) itemView.findViewById(R.id.tv_parent);
            rlParent = (RelativeLayout) itemView.findViewById(R.id.rl_parent);
        }
    }

    public ArrayList<CategoryModle.DataBean> getmParentData() {
        return mParentData;
    }

    public void setmParentData(ArrayList<CategoryModle.DataBean> mParentData) {
        this.mParentData = mParentData;
    }
}
