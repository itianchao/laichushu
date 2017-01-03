package com.laichushu.book.mvp.findeditpage;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.netbean.FindEditorListModel;
import com.laichushu.book.bean.netbean.FindEditorList_Paramet;
import com.laichushu.book.bean.netbean.JoinActivity_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.campaign.CampaignJoinModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.AnnounMangeActivity;
import com.laichushu.book.ui.activity.FindEditPageActivity;
import com.laichushu.book.ui.activity.ModifyMechanismInfoActivity;
import com.laichushu.book.ui.activity.TopicManageActivity;
import com.laichushu.book.ui.base.BasePresenter;
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
    public void showRankingDialog(final Activity mActicity,CheckBox v, final String curProcode) {
        View customerView = UIUtil.inflate(R.layout.dialog_mechanis_manage_item);
        final PopupWindow popupWindow = new PopupWindow(customerView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ListView listView = (ListView) customerView.findViewById(R.id.lv_item);
        List<String> data = new ArrayList<>();
        data.clear();
        data.add("合作人数");
        data.add("评分人数");
        data.add("总分");
        ArrayAdapter adapter = new ArrayAdapter(mActicity, R.layout.spiner_item_layout, data);
        listView.setAdapter(adapter);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        int xPos = mActivity.getWindowManager().getDefaultDisplay().getWidth() / 2-v.getWidth()/2;
        popupWindow.showAsDropDown(v,xPos,0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadEditorListData(curProcode, (position + 1) + "");
                if (null != popupWindow) {
                    popupWindow.dismiss();
                }

            }
        });

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
    public void loadEditorListData(String cityId, final String orderBy) {
        getEditorList_paramet().setCityId(cityId);
        getEditorList_paramet().setOrderBy(orderBy);
        Logger.e("参加活动");
        Logger.json(new Gson().toJson(editorList_paramet));
        addSubscription(apiStores.getEditorListDatails(editorList_paramet), new ApiCallback<FindEditorListModel>() {
            @Override
            public void onSuccess(FindEditorListModel model) {
                mvpView.getEditorListDataSuccess(model,orderBy);
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
