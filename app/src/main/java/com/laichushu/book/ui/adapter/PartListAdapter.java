package com.laichushu.book.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.home.part.PartModel;
import com.laichushu.book.ui.activity.PartActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 节
 * Created by wangtong on 2016/11/8.
 */

public class PartListAdapter extends BaseAdapter {
    private PartActivity mActivity;
    private ArrayList<PartModel.DataBean> mPartdata;

    public PartListAdapter(PartActivity partActivity, ArrayList<PartModel.DataBean> mPartdata) {
        mActivity = partActivity;
        this.mPartdata = mPartdata;
    }

    @Override
    public int getCount() {
        return mPartdata == null ? 0 : mPartdata.size();
    }

    @Override
    public PartModel.DataBean getItem(int position) {
        return mPartdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = UIUtil.inflate(R.layout.item_booklist);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        String name = mPartdata.get(position).getName();
        holder.titleTv.setText(name);
        return convertView;
    }

    public void setmPartdata(ArrayList<PartModel.DataBean> mPartdata) {
        this.mPartdata = mPartdata;
    }

    private class ViewHolder {
        private TextView titleTv;
        public ViewHolder(View itemView) {
            titleTv = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }
}
