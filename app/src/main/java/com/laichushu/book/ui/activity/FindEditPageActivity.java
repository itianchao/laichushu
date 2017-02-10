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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindEditorListModel;
import com.laichushu.book.db.City_Id;
import com.laichushu.book.db.City_IdDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.mvp.find.eidt.findeditpage.FindEditPagePresenter;
import com.laichushu.book.mvp.find.eidt.findeditpage.FindEditPageView;
import com.laichushu.book.ui.adapter.TotalRanKingAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.WheelView;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FindEditPageActivity extends MvpActivity2<FindEditPagePresenter> implements FindEditPageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private View inflate;
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mEditorRecyclerView;
    private TotalRanKingAdapter rangeAdapter;
    private RadioGroup rgTitle;
    private CheckBox rbRanking, rbCity;
    private List<FindEditorListModel.DataBean> editorDate = new ArrayList<>();
    private String orderBy = "1";

    private City_IdDao city_idDao;
    private List<City_Id> city_idList;
    private String curProCode = "";
    private String curCityCode = "";
    private int PAGE_NO = 1;

    public String getCurProCode() {
        return curProCode;
    }

    public void setCurProCode(String curProCode) {
        this.curProCode = curProCode;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    protected FindEditPagePresenter createPresenter() {
        return new FindEditPagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        inflate = UIUtil.inflate(R.layout.activity_find_edit_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        rgTitle = (RadioGroup) inflate.findViewById(R.id.rg_find);
        rbRanking = (CheckBox) inflate.findViewById(R.id.rb_total_ranking);
        rbCity = (CheckBox) inflate.findViewById(R.id.rb_city);
        mEditorRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_total_ranking);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("编辑");
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        city_idDao = daoSession.getCity_IdDao();
        city_idList = city_idDao.queryBuilder().build().list();

        ivBack.setOnClickListener(this);
        rbRanking.setOnClickListener(this);
        rbCity.setOnClickListener(this);


        //初始化mRecyclerView 动态
        mEditorRecyclerView.setGridLayout(1);
        mEditorRecyclerView.setFooterViewText("加载中");
        rangeAdapter = new TotalRanKingAdapter(this, editorDate, mvpPresenter);
        mEditorRecyclerView.setAdapter(rangeAdapter);
        mEditorRecyclerView.setOnPullLoadMoreListener(this);

        mvpPresenter.loadEditorListData(curProCode, orderBy);
    }
    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("编辑");
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
                mvpPresenter.showRankingDialog(mActivity, rbRanking, getCurProCode());
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
    public void getEditorListDataSuccess(FindEditorListModel model, String cityId, String orderByData) {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEditorRecyclerView.setPullLoadMoreCompleted();
            }
        }, 300);
        if (editorDate.size() > 0)
            editorDate.clear();
        setCurProCode(cityId);
        setOrderBy(orderByData);
        if (model.isSuccess()) {
            if (null != model.getData() && !model.getData().isEmpty()) {
                editorDate = model.getData();
                PAGE_NO++;
            } else {
                ToastUtil.showToast(mActivity.getString(R.string.errMsg_empty));
            }
            rangeAdapter.refreshAdapter(editorDate);
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast(model.getErrMsg());
            ErrorReloadData(1);
        }

    }

    @Override
    public void onRefresh() {
        PAGE_NO = 1;
        mvpPresenter.getEditorList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadEditorListData(curProCode, orderBy + "");
    }

    @Override
    public void onLoadMore() {
        mvpPresenter.getEditorList_paramet().setPageNo(PAGE_NO + "");
        mvpPresenter.loadEditorListData(curProCode, orderBy + "");
    }


    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void getDataFail(String msg, int flg) {
        LoggerUtil.toJson(msg);
        dismissDialog();
        ErrorReloadData(flg);
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
        curProCode = "01";
        curCityCode = "01";
        wvProvince.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int position, String item) {
                curProCode = getProCodeByProvince(item);
                wvCity.setItems(getCity(getProCodeByProvince(item)));
            }
        });
        wvCity.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int position, String item) {
                curCityCode = getCodeByCity(item);
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
                showDialog();
                mvpPresenter.loadEditorListData(curProCode + curCityCode, orderBy);
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

    //通过城市名字获取城市code
    private String getCodeByCity(String cityName) {
        String result = null;
        for (int i = 0; i < city_idList.size(); i++) {
            if (city_idList.get(i).getCity().equals(cityName)) {
                result = city_idList.get(i).getCityCode();
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

    public void ErrorReloadData(final int flg) {
        if (flg == 1)
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                if (flg == 1) {
                    mvpPresenter.loadEditorListData(curProCode, orderBy);
                }

            }
        });
    }
}
