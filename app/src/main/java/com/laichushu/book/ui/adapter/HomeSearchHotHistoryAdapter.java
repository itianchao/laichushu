package com.laichushu.book.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.home.homesearch.HomeSearchModel;
import com.laichushu.book.ui.activity.HomeSearchActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * 王彤
 * Created by wt on 2016/11/2.
 */

public class HomeSearchHotHistoryAdapter extends BaseAdapter {
    private List<HomeSearchModel.DataBean> mData;
    private HomeSearchActivity mActivity;

    public List<HomeSearchModel.DataBean> getmData() {
        return mData;
    }

    public void setmData(List<HomeSearchModel.DataBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public HomeSearchHotHistoryAdapter(List<HomeSearchModel.DataBean> mData, HomeSearchActivity mActivity) {
        this.mData = mData;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = UIUtil.inflate(R.layout.item_history);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        String name = mData.get(position).getArticleName();
        holder.historyTv.setText(name);
        holder.delectIv.setVisibility(View.INVISIBLE);
        switch(position){
            case 0:
                GlideUitl.loadImg(mActivity,R.drawable.icon_frist,holder.searchIv);
                break;
            case 1:
                GlideUitl.loadImg(mActivity,R.drawable.icon_second,holder.searchIv);
                break;
            case 2:
                GlideUitl.loadImg(mActivity,R.drawable.icon_thrid,holder.searchIv);
                break;
            case 3:
                GlideUitl.loadImg(mActivity,R.drawable.icon_fourth,holder.searchIv);
                break;
            case 4:
                GlideUitl.loadImg(mActivity,R.drawable.icon_fifth,holder.searchIv);
                break;
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView delectIv;
        private ImageView searchIv;
        private TextView historyTv;

        public ViewHolder(View itemView) {
            historyTv = (TextView) itemView.findViewById(R.id.tv_history);
            delectIv = (ImageView)itemView.findViewById(R.id.iv_delect);
            searchIv = (ImageView)itemView.findViewById(R.id.iv_search);
        }
    }
}
