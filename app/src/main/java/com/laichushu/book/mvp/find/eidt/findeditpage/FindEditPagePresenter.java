package com.laichushu.book.mvp.find.eidt.findeditpage;

import android.app.Activity;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.laichushu.book.bean.netbean.FindEditorListModel;
import com.laichushu.book.bean.netbean.FindEditorList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindEditPageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.widget.TypePopWindow;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2016/12/23.
 */

public class FindEditPagePresenter extends BasePresenter<FindEditPageView> {
    private FindEditPageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public FindEditPagePresenter(FindEditPageView view) {
        attachView(view);
        mActivity = (FindEditPageActivity) view;
    }

    /**
     * 全部排行
     *
     * @param mActicity
     * @param v
     */
    public void showRankingDialog(final Activity mActicity, CheckBox v, final String curProcode) {
        List<String> mlist = new ArrayList<>();
        mlist.clear();
        mlist.add("工作年限");
        mlist.add("合作人数");
        mlist.add("评价人数");
        mlist.add("评价分数");
        TypePopWindow popWindow = new TypePopWindow(mActivity, mlist);
        popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
            @Override
            public void clickItem(int position) {
                mvpView.showDialog();
                if (position == 0) {
                    loadEditorListData(curProcode, "");
                } else {
                    loadEditorListData(curProcode, position + "");
                }


            }
        });
        popWindow.setWidth(v.getWidth());
        popWindow.setHeight(UIUtil.dip2px(40) * mlist.size());
        popWindow.showAsDropDown(v);
    }

    public FindEditorList_Paramet getEditorList_paramet() {
        return editorList_paramet;
    }

    public void setEditorList_paramet(FindEditorList_Paramet editorList_paramet) {
        this.editorList_paramet = editorList_paramet;
    }

    //获取编辑数据
    FindEditorList_Paramet editorList_paramet = new FindEditorList_Paramet("173", "", "", pageNo, pageSize);

    /**
     * orderBy
     * 1:合作人数，2:评分人数，3:总分，空为工作年限。
     *
     * @param cityId
     * @param orderBy
     */
    public void loadEditorListData(final String cityId, final String orderBy) {
        getEditorList_paramet().setCityId(cityId);
        getEditorList_paramet().setOrderBy(orderBy);
        Logger.e("参加活动");
        Logger.json(new Gson().toJson(editorList_paramet));
        addSubscription(apiStores.getEditorListDatails(editorList_paramet), new ApiCallback<FindEditorListModel>() {
            @Override
            public void onSuccess(FindEditorListModel model) {
                mvpView.getEditorListDataSuccess(model, cityId, orderBy);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,1);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
}
