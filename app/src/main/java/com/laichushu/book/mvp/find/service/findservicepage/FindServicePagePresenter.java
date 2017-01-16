package com.laichushu.book.mvp.find.service.findservicepage;

import android.app.Activity;
import android.widget.CheckBox;
import com.google.gson.Gson;
import com.laichushu.book.bean.netbean.FindSertverList_Paramet;
import com.laichushu.book.bean.netbean.FindServiceInfoModel;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindServicePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.widget.TypePopWindow;
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
        List<String> mlist = new ArrayList<>();
        mlist.clear();
        mlist.add("综合排行");
        mlist.add("合作最多");
        mlist.add("评分最高");
        TypePopWindow popWindow = new TypePopWindow(mActivity, mlist);
        popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
            @Override
            public void clickItem(int position) {
                loadServerListData(curProCode, serviceType, (position + 1) + "");
            }
        });
        popWindow.setWidth(v.getWidth());
        popWindow.setHeight(UIUtil.dip2px(40) * mlist.size());
        popWindow.showAsDropDown(v);
    }

    /**
     * 服务类型
     *
     * @param mActicity
     * @param v
     */
    public void showServiceTypeDialog(final Activity mActicity, CheckBox v, final String curProCode, final String orderBy) {
        List<String> mlist = new ArrayList<>();
        mlist.clear();
        mlist.add("代笔");
        mlist.add("设计");
        TypePopWindow popWindow = new TypePopWindow(mActivity, mlist);
        popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
            @Override
            public void clickItem(int position) {
                loadServerListData(curProCode, (position + 1) + "", orderBy);
            }
        });
        popWindow.setWidth(v.getWidth());
        popWindow.setHeight(UIUtil.dip2px(40) * mlist.size());
        popWindow.showAsDropDown(v);
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
                mvpView.getServercerListDataSuccess(model, serviceType, orderBy);
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

