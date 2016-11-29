package com.laichushu.book.mvp.homepage;

import com.laichushu.book.bean.netbean.ChangeFocusState_Paramet;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusBe_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusMe_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusState_Paramet;
import com.laichushu.book.bean.netbean.ScoreLike_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

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

    private HomeUserDy_parmet paramet = new HomeUserDy_parmet(userId, pageSize, pageNo,"");

    public HomeUserDy_parmet getParamet2() {
        return paramet;
    }


    //初始化构造
    public HomePagePresener(HomePageView view) {
        attachView(view);
        mActivity = (PersonalHomePageActivity) view;
    }

    public void LoadData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getHomeUserDyDetails(paramet), new ApiCallback<HomeUseDyrResult>() {
            @Override
            public void onSuccess(HomeUseDyrResult model) {
                mvpView.getDyDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
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
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

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
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public ChangeFocusState_Paramet getAddFocus() {
        return addFocus;
    }

    public void setAddFocus(ChangeFocusState_Paramet addFocus) {
        this.addFocus = addFocus;
    }

    private ChangeFocusState_Paramet addFocus = new ChangeFocusState_Paramet(userId,"");
//添加关注
    public void loadAddFocus(String userId,final boolean flg){
        addFocus.setUserId(userId);
        LoggerUtil.toJson(addFocus);
                    addSubscription(apiStores.getAddFocus(addFocus), new ApiCallback<HomeFocusResult>() {
                        @Override
                        public void onSuccess(HomeFocusResult model) {
                            mvpView.getFocusBeStatus(model,flg);
                        }

                        @Override
                        public void onFailure(int code, String msg) {
                            mvpView.getDataFail("code+" + code + "/msg:" + msg);
                        }

                        @Override
                        public void onFinish() {

                        }
        });
    }

    public ChangeFocusState_Paramet getDelFocus() {
        return delFocus;
    }

    public void setDelFocus(ChangeFocusState_Paramet delFocus) {
        this.delFocus = delFocus;
    }

    private ChangeFocusState_Paramet delFocus = new ChangeFocusState_Paramet(userId,"");
    //取消关注
    public void loadDelFocus(String userId,final boolean isFocus){
        delFocus.setUserId(userId);
        LoggerUtil.toJson(delFocus);
        addSubscription(apiStores.getDelFocus(delFocus), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusMeStatus(model,isFocus);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }



    //点赞
//    private ScoreLike_Paramet scoreLike =new ScoreLike_Paramet("",userId,"5");
//    public void loadLikeUp(String sourUserId){
//        scoreLike.setSourceId(sourUserId);
//        LoggerUtil.toJson(collect);
//        addSubscription(apiStores.collectSave(collect), new ApiCallback<HomeFocusResult>() {
//            @Override
//            public void onSuccess(HomeFocusResult model) {
//                mvpView.getFocusMeStatus(model,isFocus);
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                mvpView.getDataFail("code+" + code + "/msg:" + msg);
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//    }
    //查看话题详情
    private CollectSave_Paramet collect =new CollectSave_Paramet(userId,"","0","5");
    public void loadLikeUp(String targetId){
        collect.setTargetId(targetId);
        LoggerUtil.toJson(collect);
        addSubscription(apiStores.collectSave(collect), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusMeStatus(model,false);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
