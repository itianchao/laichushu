package com.sofacity.laichushu.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.sofacity.laichushu.R;

import java.util.List;

public class TypePopWindow extends PopupWindow {
    private View mConentView;                // PopupWindow视图
    private Activity mActivity;
    private Context mContext;
    private SpinerListAdapter mAdapter;
    private IListItemClickListener mListItemClickListener;
    private List<String> mDatas;

    public TypePopWindow(Context context, List<String> datas) {
        super(context);
        this.mActivity = (Activity) context;
        this.mContext = context;
        this.mDatas = datas;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConentView = inflater.inflate(R.layout.pop_window_list_layout, null);
        this.setContentView(mConentView);
        initPopWindow();
        initView();
    }

    @SuppressWarnings("deprecation")
    private void initPopWindow() {
        // 设置SelectPicPopupWindow弹出窗体的宽
        // int width =
        // mActivity.getWindowManager().getDefaultDisplay().getWidth();
        // this.setWidth(width / 2 + 50);
        // this.setWidth(width - 100);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(android.R.style.Animation_Dialog);
        this.setAnimationStyle(R.style.add_popwin_anim_style);// 自定义动画
        // 幕背景变暗
        // setBackgroundAlpha(0.5f);
    }

    private void initView() {
        ListView mListView = (ListView) mConentView.findViewById(R.id.lv_popwin);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapter = new SpinerListAdapter();
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListItemClickListener.clickItem(position);
                TypePopWindow.this.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }

    class SpinerListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null) {
                return mDatas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_poplist, null);
                holder = new ViewHolder();
                holder.tv = (TextView) convertView.findViewById(R.id.tv_type);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv.setText(mDatas.get(position));
            return convertView;
        }
    }

    static class ViewHolder {
        TextView tv;
    }

    public interface IListItemClickListener {
        void clickItem(int position);
    }

    public void setListItemClickListener(IListItemClickListener listener) {
        this.mListItemClickListener = listener;
    }

}
