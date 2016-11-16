package com.laichushu.book.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.laichushu.book.ui.activity.DirectoriesActivity;
import com.laichushu.book.R;
import com.laichushu.book.mvp.directories.BookMoudle;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 目录
 * Created by wangtong on 2016/11/8.
 */

public class BookListAdapter extends BaseAdapter {
    private DirectoriesActivity mActivity;
    private ArrayList<BookMoudle.DataBean> mBookdata;

    public BookListAdapter(DirectoriesActivity directoriesActivity, ArrayList<BookMoudle.DataBean> mBookdata) {
        mActivity = directoriesActivity;
        this.mBookdata = mBookdata;
    }

    @Override
    public int getCount() {
        return mBookdata == null ? 0 : mBookdata.size();
    }

    @Override
    public BookMoudle.DataBean getItem(int position) {
        return mBookdata.get(position);
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
        String name = getItem(position).getName();
        holder.titleTv.setText(name);
        return convertView;
    }

    public void setmBookdata(ArrayList<BookMoudle.DataBean> mBookdata) {
        this.mBookdata = mBookdata;
    }

    private class ViewHolder {
        private TextView titleTv;
        public ViewHolder(View itemView) {
            titleTv = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }
}
