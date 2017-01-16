package com.laichushu.book.ui.activity;

import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindServiceInfoModel;
import com.laichushu.book.db.City_Id;
import com.laichushu.book.db.City_IdDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.mvp.find.service.findservicepage.FindServicePagePresenter;
import com.laichushu.book.mvp.find.service.findservicepage.FindServicePageView;
import com.laichushu.book.ui.adapter.FindServicePageAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.WheelView;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现 - 服务
 */
public class FindServicePageActivity extends MvpActivity2<FindServicePagePresenter> implements FindServicePageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mServerRecyclerView;
    private CheckBox rbRanking, rbServiceType, rbCity;
    private FindServicePageAdapter serverAdapter;
    private List<FindServiceInfoModel.DataBean> serverDate = new ArrayList<>();

    private City_IdDao city_idDao;
    private List<City_Id> city_idList;
    private String orderBy = "1";
    private String curProCode = "";
    private String curCityCode = "";
    private String curServiceType = null;
    private int PAGE_NO = 1;

    @Override
    protected FindServicePagePresenter createPresenter() {
        return new FindServicePagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_find_service_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        mServerRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_total_ranking);
        rbRanking = (CheckBox) inflate.findViewById(R.id.rb_total_ranking);
        rbServiceType = (CheckBox) inflate.findViewById(R.id.rb_serviceType);
        rbCity = (CheckBox) inflate.findViewById(R.id.rb_city);
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
        rbServiceType.setOnClickListener(this);
        rbCity.setOnClickListener(this);

        rbRanking.setOnClickListener(this);
        rbCity.setOnClickListener(this);


        //初始化mRecyclerView 动态
        mServerRecyclerView.setGridLayout(1);
        mServerRecyclerView.setFooterViewText("加载中");
        serverAdapter = new FindServicePageAdapter(this, serverDate, mvpPresenter);
        mServerRecyclerView.setAdapter(serverAdapter);
        mServerRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.loadServerListData(curProCode, curServiceType, orderBy);
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
                rbServiceType.setChecked(false);
                if (!rbRanking.isChecked()) {
                    rbRanking.setChecked(true);
                }
                mvpPresenter.showRankingDialog(mActivity, rbRanking, curProCode, curServiceType);
                break;
            case R.id.rb_serviceType:
                //服务类型
                rbRanking.setChecked(false);
                rbCity.setChecked(false);
                if (!rbServiceType.isChecked())
                    rbServiceType.setChecked(true);
                mvpPresenter.showServiceTypeDialog(mActivity, rbServiceType, curProCode, orderBy);
                break;
            case R.id.rb_city:
                //城市
                rbRanking.setChecked(false);
                rbServiceType.setChecked(false);
                if (!rbCity.isChecked()) {
                    rbCity.setChecked(true);
                }
                initAreaSelector();
                break;
        }
    }

    @Override
    public void getServercerListDataSuccess(FindServiceInfoModel model, String serviceType, String orderBy) {
        if (serverDate.size() > 0)
            serverDate.clear();
        this.orderBy = orderBy;
        this.curServiceType = serviceType;
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mServerRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            serverDate = model.getData();
            if (null != model.getData() && !model.getData().isEmpty()) {
                PAGE_NO++;
            } else {
                ToastUtil.showToast("暂无数据");
            }
            serverAdapter.refreshAdapter(serverDate);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }


    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        mvpPresenter.getSertverList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadServerListData(curProCode, curServiceType, orderBy + "");
    }

    @Override
    public void onLoadMore() {
        PAGE_NO = 1;
        mvpPresenter.getSertverList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadServerListData(curProCode, curServiceType, orderBy + "");
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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity, R.style.DialogStyle);
        final AlertDialog alertDialog = dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View customerView = inflater.inflate(R.layout.item_pop_myview, null);

        WheelView wvProvince = ((WheelView) customerView.findViewById(R.id.wv_province));
        final WheelView wvCity = ((WheelView) customerView.findViewById(R.id.wv_city));
        TextView tvCancel = ((TextView) customerView.findViewById(R.id.tv_cancel));
        TextView tvSubmit = ((TextView) customerView.findViewById(R.id.tv_submit));
        wvProvince.setSelectTextColor("#969696");
        wvProvince.setLineColor("#969696");
        wvCity.setSelectTextColor("#969696");
        wvCity.setLineColor("#969696");
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
                alertDialog.dismiss();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交数据
                curProCode = getProCodeByProvince(curProvince[0]);
                mvpPresenter.loadServerListData(curProCode + curCityCode, curServiceType, orderBy);
                curProCode = curProCode + curCityCode;
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(customerView);
        alertDialog.show();
        WindowManager m = getWindowManager();
        Display display = m.getDefaultDisplay();  //为获取屏幕宽、高
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.getWindow().setLayout(display.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setWindowAnimations(R.style.timepopwindow_anim_style);
        alertDialog.show();

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

