package com.laichushu.book.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.db.City_Id;
import com.laichushu.book.db.City_IdDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.mvp.findservicepage.FindServicePagePresenter;
import com.laichushu.book.mvp.findservicepage.FindServicePageView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.WheelView;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
/**
 *  发现 - 服务
 */
public class FindServicePageActivity extends MvpActivity2<FindServicePagePresenter> implements FindServicePageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mVideoRecyclerView, mMineRecyclerView;
    private CheckBox rbRanking, rbCity;

    private City_IdDao city_idDao;
    private List<City_Id> city_idList;
    private String curProCode = "01";
    @Override
    protected FindServicePagePresenter createPresenter() {
        return new FindServicePagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_find_service_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        mVideoRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_total_ranking);
        mMineRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_city);
        rbRanking = (CheckBox) inflate.findViewById(R.id.rb_total_ranking);
        rbCity = (CheckBox) inflate.findViewById(R.id.rb_city);
        mVideoRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_total_ranking);
        mMineRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_city);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("服务");

        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        city_idDao = daoSession.getCity_IdDao();
        city_idList = city_idDao.queryBuilder().build().list();

        ivBack.setOnClickListener(this);
        rbRanking.setOnClickListener(this);
        rbCity.setOnClickListener(this);

        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.rb_total_ranking:
                //全部排行
                rbCity.setChecked(false);
                if (!rbRanking.isChecked()) {
                    rbRanking.setChecked(true);
                }
                mvpPresenter.showRankingDialog(mActivity, rbRanking);
                break;
            case R.id.rb_city:
                //城市
                rbRanking.setChecked(false);
                if (!rbCity.isChecked()) {
                    rbCity.setChecked(true);
                }
                initAreaSelector();
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg.toString());
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }

    //选择城市
    public void initAreaSelector() {
        View customerView = UIUtil.inflate(R.layout.item_pop_find_city, null);
        final PopupWindow popupWindow = new PopupWindow(customerView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        WheelView wvProvince = ((WheelView) customerView.findViewById(R.id.wv_province));
        final WheelView wvCity = ((WheelView) customerView.findViewById(R.id.wv_city));
        TextView tvCancel = ((TextView) customerView.findViewById(R.id.tv_cancel));
        TextView tvSubmit = ((TextView) customerView.findViewById(R.id.tv_submit));
        wvProvince.setOffset(2);
        wvProvince.setSeletion(0);
        wvCity.setOffset(2);
        wvCity.setSeletion(0);
        wvProvince.setItems(getProvonce());
        wvCity.setItems(getCity("01"));
        final String[] curProvince = {"北京市"};
        wvProvince.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int position, String item) {
                curProvince[0] = item;
                wvCity.setItems(getCity(getProCodeByProvince(item)));
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交数据
                curProCode = getProCodeByProvince(curProvince[0]);
                popupWindow.dismiss();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        int xPos =  mActivity.getWindowManager().getDefaultDisplay().getWidth() / 4
                - rbCity.getWidth() / 4;
        int[] location = new int[2];
        rbCity.getLocationOnScreen(location);
        popupWindow.showAsDropDown(rbCity, xPos, 40);

    }

    /**
     * 获取所有的省份
     *
     * @return
     */
    private ArrayList<String> getProvonce() {
        ArrayList<String> proDate = new ArrayList<>();
        proDate.clear();
        for (int i = 0; i < city_idList.size(); i++) {
            if (!proDate.contains(city_idList.get(i).getProvince())) {
                proDate.add(city_idList.get(i).getProvince());
            }
        }
        return proDate;
    }

    /**
     * 通过省份名字获取省份代号
     *
     * @param province
     * @return
     */
    private String getProCodeByProvince(String province) {
        String result = null;
        for (int i = 0; i < city_idList.size(); i++) {
            if (province.equals(city_idList.get(i).getProvince())) {
                result = city_idList.get(i).getProCode();
            }
        }
        return result;
    }

    /**
     * 通过省份code获取省份名字
     *
     * @param proCode
     * @return
     */
    private String getProvinceByProcode(String proCode) {
        String result = null;
        for (int i = 0; i < city_idList.size(); i++) {
            if (proCode.equals(city_idList.get(i).getProCode())) {
                result = city_idList.get(i).getProvince();
            }
        }
        return result;
    }

    /**
     * @param proCode
     * @return 根据proCode查询城市
     */
    private ArrayList<String> getCity(String proCode) {
        ArrayList<String> cityDate = new ArrayList<>();
        cityDate.clear();
        for (int i = 0; i < city_idList.size(); i++) {
            if (proCode.equals(city_idList.get(i).getProCode())) {
                cityDate.add(city_idList.get(i).getCity());
            }
        }
        return cityDate;
    }
}

