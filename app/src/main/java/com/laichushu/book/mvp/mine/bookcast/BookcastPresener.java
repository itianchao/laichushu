package com.laichushu.book.mvp.mine.bookcast;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AuthorWorksByBookId_Paramet;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.DeleteBookByBookId_Paramet;
import com.laichushu.book.bean.netbean.MyArticBooklist_paramet;
import com.laichushu.book.bean.netbean.MyBrowseList_paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MyBookCastActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/21.
 */

public class BookcastPresener extends BasePresenter<BookcastView> {
    private MyBookCastActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE4;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    public MyArticBooklist_paramet getParamet() {
        return paramet;
    }

    public void setParamet(MyArticBooklist_paramet paramet) {
        this.paramet = paramet;
    }

    private MyArticBooklist_paramet paramet = new MyArticBooklist_paramet(userId, pageNo, pageSize,"",ConstantValue.BOOKCOMMENTTYPE);

    public MyArticBooklist_paramet getParamet2() {
        return paramet2;
    }

    public void setParamet2(MyArticBooklist_paramet paramet2) {
        this.paramet2 = paramet2;
    }

    private MyArticBooklist_paramet paramet2 = new MyArticBooklist_paramet(userId, pageNo, pageSize,"1",ConstantValue.BOOKCOMMENTTYPE);
    private int state = 1;
    private int type=1;
    //初始化构造
    public BookcastPresener(BookcastView view) {
        attachView(view);
        mActivity = (MyBookCastActivity) view;
    }
//旧的浏览接口
    public void LoadData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getArticleBookListScan(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getDataSuccess(model);
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
    //新的浏览
    public void loadBrowserListData(String type) {
        MyBrowseList_paramet browseList_paramet =new MyBrowseList_paramet(userId,type);
        LoggerUtil.toJson(browseList_paramet);
        addSubscription(apiStores.getMyBrowseBookListScan(browseList_paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getDataSuccess(model);
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
    public void LoadCollectionData() {
        LoggerUtil.toJson(paramet2);
        addSubscription(apiStores.getCollectList(paramet2), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getCollectionDataSuccess(model);
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

    /**
     * 获取图书详情
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
                mvpView.getDataFail("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
 /**
     * 删除收藏图书
     * @param collectId
     */
    public void loadDeleteCollectBookById(String collectId, final int position) {
        mvpView.showDialog();
        DeleteBookByBookId_Paramet deleteParamet = new DeleteBookByBookId_Paramet(collectId,null);
        LoggerUtil.e("获取图书详情");
        addSubscription(apiStores.getDeleteCollectBookByBookId(deleteParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDeleteBookByIdDataSuccess(model,position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }/**
     * 删除浏览图书
     * @param browseId
     */
    public void loadDeleteBrowseBookById(String browseId, final int position) {
        mvpView.showDialog();
        DeleteBookByBookId_Paramet deleteParamet = new DeleteBookByBookId_Paramet(null,browseId);
        LoggerUtil.e("获取图书详情");
        addSubscription(apiStores.getDeleteBrowseBookByBookId(deleteParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getDeleteBookByIdDataSuccess(model,position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }

}
