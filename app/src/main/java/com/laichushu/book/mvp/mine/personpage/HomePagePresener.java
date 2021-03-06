package com.laichushu.book.mvp.mine.personpage;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ChangeFocusState_Paramet;
import com.laichushu.book.bean.netbean.CollectSaveDate_Paramet;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusBe_parmet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomePagePresener extends BasePresenter<HomePageView> {
    private PersonalHomePageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    public HomeUserDy_parmet getParamet() {
        return paramet;
    }

    public void setParamet(HomeUserDy_parmet paramet) {
        this.paramet = paramet;
    }

    private HomeUserDy_parmet paramet = new HomeUserDy_parmet(userId, "", pageSize, pageNo, "");

    public HomeUserDy_parmet getParamet2() {
        return paramet;
    }


    //初始化构造
    public HomePagePresener(HomePageView view) {
        attachView(view);
        mActivity = (PersonalHomePageActivity) view;
    }

    /**
     * 个人主页--动态
     */
    public void LoadData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getHomeUserDyDetails(paramet), new ApiCallback<HomeUseDyrResult>() {
            @Override
            public void onSuccess(HomeUseDyrResult model) {
                mvpView.getDyDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,2);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void setParamet2(HomeUserFocusBe_parmet paramet2) {
        this.paramet2 = paramet2;
    }

    //关注我的
    private HomeUserFocusBe_parmet paramet2 = new HomeUserFocusBe_parmet(userId, pageNo, pageSize);

    public void LoadFocusMeData() {
        LoggerUtil.toJson(paramet2);
        addSubscription(apiStores.getHomeUserFocusMeDetails(paramet2), new ApiCallback<HomePersonFocusResult>() {
            @Override
            public void onSuccess(HomePersonFocusResult model) {
                mvpView.getFocusMeDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,3);
            }

            @Override
            public void onFinish() {
mvpView.dismissDialog();
            }
        });
    }

    public HomeUserFocusBe_parmet getParamet3() {
        return paramet3;
    }

    public void setParamet3(HomeUserFocusBe_parmet paramet3) {
        this.paramet3 = paramet3;
    }

    //我关注的
    private HomeUserFocusBe_parmet paramet3 = new HomeUserFocusBe_parmet(userId, pageNo, pageSize);

    public void LoadFocusBeData() {
        LoggerUtil.toJson(paramet3);
        addSubscription(apiStores.getHomeUserFocusBeDetails(paramet3), new ApiCallback<HomePersonFocusResult>() {
            @Override
            public void onSuccess(HomePersonFocusResult model) {
                mvpView.getFocusBeDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,4);
            }

            @Override
            public void onFinish() {
mvpView.dismissDialog();
            }
        });
    }

    public ChangeFocusState_Paramet getAddFocus() {
        return addFocus;
    }

    public void setAddFocus(ChangeFocusState_Paramet addFocus) {
        this.addFocus = addFocus;
    }

    private ChangeFocusState_Paramet addFocus = new ChangeFocusState_Paramet(userId, "");

    //添加关注
    public void loadAddFocus(String userId, final boolean flg, final HomePersonFocusResult.DataBean dataBean, final int position, final int type) {
        addFocus.setUserId(userId);
        LoggerUtil.toJson(addFocus);
        mvpView.showDialog();
        addSubscription(apiStores.getAddFocus(addFocus), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusBeStatus(model, flg, dataBean, position, type);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,5);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }

    public ChangeFocusState_Paramet getDelFocus() {
        return delFocus;
    }

    public void setDelFocus(ChangeFocusState_Paramet delFocus) {
        this.delFocus = delFocus;
    }

    private ChangeFocusState_Paramet delFocus = new ChangeFocusState_Paramet(userId, "");

    //取消关注
    public void loadDelFocus(String userId, final boolean isFocus, final HomePersonFocusResult.DataBean dataBean, final int position, final int type) {
        delFocus.setUserId(userId);
        LoggerUtil.toJson(delFocus);
        mvpView.showDialog();
        addSubscription(apiStores.getDelFocus(delFocus), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusMeStatus(model, isFocus, dataBean, position, type);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,6);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }


    /**
     * 话题收藏
     *
     * @param sourceId
     * @param sourceType
     * @param type
     */
    public void loadCollectSaveDate(String sourceId, String sourceType, final String type, final HomeUseDyrResult.DataBean dataBean, final int position) {
        CollectSaveDate_Paramet collectSave = new CollectSaveDate_Paramet(userId, sourceId, sourceType, type);
        LoggerUtil.toJson(collectSave);
        mvpView.showDialog();
        addSubscription(apiStores.collectSaveData(collectSave), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSaveCollectSuccess(model, type, dataBean, position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,7);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }

    //查看话题详情
    private CollectSave_Paramet collect = new CollectSave_Paramet(userId, "", "0", "5");

    public void loadLikeUp(String targetId) {
        collect.setSourceId(targetId);
        LoggerUtil.toJson(collect);
        addSubscription(apiStores.collectSave(collect), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusMeStatus(model, false, null, 0, 0);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,8);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
