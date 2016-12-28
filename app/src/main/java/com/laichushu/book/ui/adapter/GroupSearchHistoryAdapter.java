package com.laichushu.book.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.db.Search_History;
import com.laichushu.book.db.Search_HistoryDao;
import com.laichushu.book.ui.activity.FindGroupSearchActivity;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * 王彤
 * Created by wt on 2016/12/28.
 */

public class GroupSearchHistoryAdapter extends BaseAdapter {
    private List<Search_History> list;
    private FindGroupSearchActivity mActivity;
    public GroupSearchHistoryAdapter(List<Search_History> list, FindGroupSearchActivity mActivity) {
        this.list = list;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
        final Search_History search_history = list.get(position);
        holder.historyTv.setText(search_history.getHistory());
        holder.delectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_HistoryDao dao = mActivity.getPresenter().getSearch_historyDao();
                dao.delete(search_history);
                list.remove(search_history);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public void setList(List<Search_History> list) {
        this.list = list;
    }

    class ViewHolder {

        private ImageView delectIv;
        private TextView historyTv;

        public ViewHolder(View itemView) {
            historyTv = (TextView) itemView.findViewById(R.id.tv_history);
            delectIv = (ImageView)itemView.findViewById(R.id.iv_delect);
        }
    }

}
