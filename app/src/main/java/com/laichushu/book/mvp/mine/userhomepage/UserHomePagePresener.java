package com.laichushu.book.mvp.mine.userhomepage;

import android.text.TextUtils;
import android.widget.EditText;

import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AddPerMsgInfo_Paramet;
import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.bean.netbean.AuthorWorksByBookId_Paramet;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.ChangeFocusState_Paramet;
import com.laichushu.book.bean.netbean.CollectSaveDate_Paramet;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserInfor_paramet;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.bean.netbean.TopicDyLike_Paramet;
import com.laichushu.book.bean.netbean.UserFocusBe_parmet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;

/**
 * Created by PCPC on 2016/11/25.
 */

public class UserHomePagePresener extends BasePresenter<UserHomePageView> {
    private UserHomePageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public UserHomePagePresener(UserHomePageView view) {
        attachView(view);
        mActivity = (UserHomePageActivity) view;
    }


    public HomeUserInfor_paramet getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HomeUserInfor_paramet headInfo) {
        this.headInfo = headInfo;
    }

    //初始化用户界面用户信息
    HomeUserInfor_paramet headInfo = new HomeUserInfor_paramet(SharePrefManager.getUserId(), "");

    public void getUserHeadDate(String userId) {
        headInfo.setUserId(userId);
        LoggerUtil.toJson(headInfo);
        addSubscription(apiStores.getUserInforDetails(headInfo), new ApiCallback<HomeUserResult>() {

            @Override
            public void onSuccess(HomeUserResult model) {
                mvpView.getUserHeadDateSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail1("code+" + code + "/msg:" + msg,1);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public HomeUserDy_parmet getParamet() {
        return paramet;
    }

    public void setParamet(HomeUserDy_parmet paramet) {
        this.paramet = paramet;
    }

    //获取用户动态详情
    private HomeUserDy_parmet paramet = new HomeUserDy_parmet("", "", pageSize, pageNo, userId);

    public void getUserDynmicDate(String id) {
        getParamet().setUserId(id);
        addSubscription(apiStores.getUserHomeDyDetails(paramet), new ApiCallback<HomeUseDyrResult>() {

            @Override
            public void onSuccess(HomeUseDyrResult model) {
                mvpView.getUserHomeDyDateSuccess(model);
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

    public ArticleBookList_Paramet getBookList() {
        return bookList;
    }

    public void setBookList(ArticleBookList_Paramet bookList) {
        this.bookList = bookList;
    }

    //获取用户作品列表 userId==targetId
    ArticleBookList_Paramet bookList = new ArticleBookList_Paramet(userId, pageNo, pageSize, "");

    public void getUserBookListDate(String userId) {
        bookList.setAuthorId(userId);
        addSubscription(apiStores.getArticleBookList(bookList), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getUserHomeBookListSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,3);
            }

            @Override
            public void onFinish() {

            }
        });
    }


    public UserFocusBe_parmet getFocusMe_parmet() {
        return focusMe_parmet;
    }

    public void setFocusMe_parmet(UserFocusBe_parmet focusMe_parmet) {
        this.focusMe_parmet = focusMe_parmet;
    }

    //用户 他关注的
    UserFocusBe_parmet focusMe_parmet = new UserFocusBe_parmet(SharePrefManager.getUserId(), "", pageNo, pageSize);

    public void getUserHeFocusDate(String id) {
        focusMe_parmet.setUserId(id);
        addSubscription(apiStores.getUserFocusBeDetails(focusMe_parmet), new ApiCallback<HomePersonFocusResult>() {

            @Override
            public void onSuccess(HomePersonFocusResult model) {
                mvpView.getUserHomeHeFocusSuccess(model);
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


    public UserFocusBe_parmet getFocusBe_parmet() {
        return focusBe_parmet;
    }

    public void setFocusBe_parmet(UserFocusBe_parmet focusBe_parmet) {
        this.focusBe_parmet = focusBe_parmet;
    }

    //用户界面 关注他的
    UserFocusBe_parmet focusBe_parmet = new UserFocusBe_parmet(SharePrefManager.getUserId(), "", pageNo, pageSize);

    public void getUserFocusHeDate(String userId) {
        focusBe_parmet.setUserId(userId);
        addSubscription(apiStores.getUserFocusMeDetails(focusBe_parmet), new ApiCallback<HomePersonFocusResult>() {

            @Override
            public void onSuccess(HomePersonFocusResult model) {
                mvpView.getUserHomeFocusHeSuccess(model);
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

    public ChangeFocusState_Paramet getAddFocus() {
        return addFocus;
    }

    public void setAddFocus(ChangeFocusState_Paramet addFocus) {
        this.addFocus = addFocus;
    }

    private ChangeFocusState_Paramet addFocus = new ChangeFocusState_Paramet(userId, "");

    //添加关注
    public void loadAddFocus(String userId, final boolean flg,final HomePersonFocusResult.DataBean dataBean, final int position, final int type) {
        mvpView.showDialog();
        LoggerUtil.toJson(addFocus);
        addFocus.setUserId(userId);
        addSubscription(apiStores.getAddFocus(addFocus), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusBeStatus(model, flg,dataBean, position, type);
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

    public ChangeFocusState_Paramet getDelFocus() {
        return delFocus;
    }

    public void setDelFocus(ChangeFocusState_Paramet delFocus) {
        this.delFocus = delFocus;
    }

    private ChangeFocusState_Paramet delFocus = new ChangeFocusState_Paramet(userId, "");

    //取消关注
    public void loadDelFocus(String userId, final boolean isFocus, final HomePersonFocusResult.DataBean dataBean, final int position, final int type) {
        LoggerUtil.toJson(delFocus);
        delFocus.setUserId(userId);
        mvpView.showDialog();
        addSubscription(apiStores.getDelFocus(delFocus), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getFocusMeStatus(model, isFocus,dataBean, position, type);
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

// 动态话题点赞

    public void loadLikeUpDate(String sourceId, String sourceType, final String type) {
        TopicDyLike_Paramet scoreLike = new TopicDyLike_Paramet(sourceId, sourceType, type, userId);
        LoggerUtil.toJson(scoreLike);
        addSubscription(apiStores.saveTopicDyLike(scoreLike), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {

//                mvpView.getLikeUpSuccess(model,type);
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

    //话题收藏
    public void loadCollectSaveDate(String sourceId, String sourceType, final String type, final HomeUseDyrResult.DataBean dataBean, final int position) {
        CollectSaveDate_Paramet collectSave = new CollectSaveDate_Paramet(userId, sourceId, sourceType, type);
        LoggerUtil.toJson(collectSave);
        mvpView.showDialog();
        addSubscription(apiStores.collectSaveData(collectSave), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSaveCollectSuccess(model, type,dataBean,position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,9);
            }

            @Override
            public void onFinish() {
mvpView.dismissDialog();
            }
        });
    }

    //发送私信
    public void loadAddPerInfoDate(String accepterId, final EditText sendmsgEv) {
        String msg = sendmsgEv.getText().toString().trim();
        if (TextUtils.isEmpty(msg)) {
            sendmsgEv.startAnimation(ShakeAnim.shakeAnimation(3));
            return;
        }
        AddPerMsgInfo_Paramet addPerInfoMsg = new AddPerMsgInfo_Paramet(msg, accepterId, userId);
        LoggerUtil.toJson(addPerInfoMsg);
        addSubscription(apiStores.getAddPerMsgInfDetails(addPerInfoMsg), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getAddPerInfoSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg,10);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取图书详情
     *
     * @param articleId
     */
    public void loadBookDetailsByid(String articleId) {
        mvpView.showDialog();
        AuthorWorksByBookId_Paramet bookParamet = new AuthorWorksByBookId_Paramet(userId, articleId);
        LoggerUtil.e("获取图书详情");
        addSubscription(apiStores.getAuthorWorksByBookId(bookParamet), new ApiCallback<BookDetailsModle>() {
            @Override
            public void onSuccess(BookDetailsModle model) {
                mvpView.getBookDetailsByIdDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code" + code + "msg:" + msg,11);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
}