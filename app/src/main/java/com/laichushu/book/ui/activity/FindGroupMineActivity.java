package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 小组 - 我的
 * Created by wangtong on 2016/12/28.
 */

public class FindGroupMineActivity extends MvpActivity2 implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView mineLv;
    private String[] itemName = {"我加入的小组", "我创建的小组", "我发表的话题", "我回复的话题", "我点赞的话题"};
    private TextView titleTv;
    private ImageView finishIv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * @return 成功页面
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findgroupmine);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        mineLv = (ListView) mSuccessView.findViewById(R.id.lv_mine);
        mineLv.setAdapter(new FindGroupMineAdapter());
        mineLv.setOnItemClickListener(this);
        finishIv.setOnClickListener(this);
        titleTv.setText("我的小组");
        return mSuccessView;
    }

    @Override
    protected void initData() {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 10);
    }

    private class FindGroupMineAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemName.length;
        }

        @Override
        public String getItem(int position) {
            return itemName[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = UIUtil.inflate(R.layout.item_find_group_mine);
            TextView nameTv = (TextView) convertView.findViewById(R.id.tv_itemname);
            ImageView inIv = (ImageView) convertView.findViewById(R.id.iv_in);
            TextView numberTv = (TextView) convertView.findViewById(R.id.tv_number);
            nameTv.setText(getItem(position));
            return convertView;
        }
    }

    /**
     * ListView条目点击
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putInt("type",0);
                UIUtil.openActivity(mActivity,FindGroupListActivity.class,bundle);
                break;
            case 1:
                bundle.putInt("type",1);
                UIUtil.openActivity(mActivity,FindGroupListActivity.class,bundle);
                break;
            case 2:
                bundle.putString("title","我发表的话题");
                UIUtil.openActivity(mActivity,FindGroupMineTopicListActivity.class,bundle);
                break;
            case 3:
                bundle.putString("title","我回复的话题");
                UIUtil.openActivity(mActivity,FindGroupMineTopicListActivity.class,bundle);
                break;
            case 4:
                bundle.putString("title","我收藏的话题");
                UIUtil.openActivity(mActivity,FindGroupMineTopicListActivity.class,bundle);
                break;
        }
    }

    /**
     * 点击时间
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }
}
