package com.laichushu.book.mvp.findservicepage;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FindEditorListModel;
import com.laichushu.book.bean.netbean.FindSertverList_Paramet;
import com.laichushu.book.bean.netbean.FindServiceInfoModel;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.findeditpage.FindEditPageView;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindEditPageActivity;
import com.laichushu.book.ui.activity.FindServicePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2016/12/26.
 */

public class FindServicePagePresenter extends BasePresenter<FindServicePageView> {
    private FindServicePageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public FindServicePagePresenter(FindServicePageView view) {
        attachView(view);
        mActivity = (FindServicePageActivity) view;
    }

    /**
     * 全部排行
     *
     * @param mActicity
     * @param v
     */
    public void showRankingDialog(final Activity mActicity, CheckBox v, final String curProCode, final String serviceType) {
        View customerView = UIUtil.inflate(R.layout.dialog_mechanis_manage_item);
        final PopupWindow popupWindow = new PopupWindow(customerView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ListView listView = (ListView) customerView.findViewById(R.id.lv_item);
        List<String> data = new ArrayList<>();
        data.clear();
        data.add("综合排行");
        data.add("合作最多");
        data.add("评分最高");
        ArrayAdapter adapter = new ArrayAdapter(mActicity, R.layout.spiner_item_layout, data);
        listView.setAdapter(adapter);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        int xPos = mActicity.getWindowManager().getDefaultDisplay().getWidth() / 2
               -popupWindow.getWidth()/2;
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAsDropDown(v, Gravity.CENTER_HORIZONTAL, 0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadServerListData(curProCode,serviceType,(position+1)+"");
                if (null != popupWindow)
                    popupWindow.dismiss();
            }
        });

    }

    /**
     * 服务类型
     *
     * @param mActicity
     * @param v
     */
    public void showServiceTypeDialog(final Activity mActicity, CheckBox v, final String curProCode, final String orderBy) {
        View customerView = UIUtil.inflate(R.layout.dialog_mechanis_manage_item);
        final PopupWindow popupWindow = new PopupWindow(customerView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ListView listView = (ListView) customerView.findViewById(R.id.lv_item);
        List<String> data = new ArrayList<>();
        data.clear();
        data.add("代笔");
        data.add("设计");
        ArrayAdapter adapter = new ArrayAdapter(mActicity, R.layout.spiner_item_layout, data);
        listView.setAdapter(adapter);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        int xPos = mActicity.getWindowManager().getDefaultDisplay().getWidth() / 4
                - v.getWidth() / 4;
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAsDropDown(v,Gravity.CENTER_HORIZONTAL,0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadServerListData(curProCode,(position+1)+"",orderBy);
                if (null != popupWindow) {
                    popupWindow.dismiss();
                }

            }
        });

    }

    public FindSertverList_Paramet getSertverList_paramet() {
        return sertverList_paramet;
    }

    public void setSertverList_paramet(FindSertverList_Paramet sertverList_paramet) {
        this.sertverList_paramet = sertverList_paramet;
    }

    // 查询列表
    FindSertverList_Paramet sertverList_paramet = new FindSertverList_Paramet(userId, "", "", "", pageNo, pageSize);

    public void loadServerListData(String cityId, final String serviceType, final String orderBy) {
        getSertverList_paramet().setCityId(cityId);
        getSertverList_paramet().setServiceType(serviceType);
        getSertverList_paramet().setOrderBy(orderBy);
        Logger.e("查询服务");
        Logger.json(new Gson().toJson(sertverList_paramet));
        addSubscription(apiStores.getFindServerListDetails(sertverList_paramet), new ApiCallback<FindServiceInfoModel>() {
            @Override
            public void onSuccess(FindServiceInfoModel model) {
                mvpView.getServercerListDataSuccess(model,serviceType, orderBy);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
}

