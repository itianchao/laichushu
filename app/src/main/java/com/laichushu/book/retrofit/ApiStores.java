package com.laichushu.book.retrofit;

import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.netbean.ActivityResult_Paramet;
import com.laichushu.book.bean.netbean.AuthorDetail_Paramet;
import com.laichushu.book.bean.netbean.AuthorWorks_Paramet;
import com.laichushu.book.bean.netbean.Balance_Paramet;
import com.laichushu.book.bean.netbean.BestLike_Paramet;
import com.laichushu.book.bean.netbean.BookList_Paramet;
import com.laichushu.book.bean.netbean.Comment2_Paramet;
import com.laichushu.book.bean.netbean.Comment_Paramet;
import com.laichushu.book.bean.netbean.Complaint_Paramet;
import com.laichushu.book.bean.netbean.ForgetPwd_Paramet;
import com.laichushu.book.bean.netbean.HomeAllBook_Paramet;
import com.laichushu.book.bean.netbean.HomeCategroyListBook_Paramet;
import com.laichushu.book.bean.netbean.HomeHot_Paramet;
import com.laichushu.book.bean.netbean.HomeSearch_Paramet;
import com.laichushu.book.bean.netbean.JoinActivity_Paramet;
import com.laichushu.book.bean.netbean.Jurisdiction_Paramet;
import com.laichushu.book.bean.netbean.Login_Paramet;
import com.laichushu.book.bean.netbean.MaterialContent_Paramet;
import com.laichushu.book.bean.netbean.MaterialList_Paramet;
import com.laichushu.book.bean.netbean.PartList_Paramet;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.PersonalCentre_Parmet;
import com.laichushu.book.bean.netbean.ReCommentList_Paramet;
import com.laichushu.book.bean.netbean.ReSavaComment_Paramet;
import com.laichushu.book.bean.netbean.RegistValid_Paramet;
import com.laichushu.book.bean.netbean.Regist_Paramet;
import com.laichushu.book.bean.netbean.RewardMoney_Paramet;
import com.laichushu.book.bean.netbean.ScoreLike_Paramet;
import com.laichushu.book.bean.netbean.SubscribeArticle_Paramet;
import com.laichushu.book.bean.netbean.UpdatePersonalInfor_Parmet;
import com.laichushu.book.bean.netbean.UploadIdcardInfor_Parmet;
import com.laichushu.book.mvp.Campaign.AuthorWorksModle;
import com.laichushu.book.mvp.Campaign.CampaignJoinModel;
import com.laichushu.book.mvp.Campaign.CampaignModel;
import com.laichushu.book.mvp.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.bookdetail.AuthorDetailModle;
import com.laichushu.book.mvp.bookdetail.SubscribeArticleModle;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.directories.BookMoudle;
import com.laichushu.book.mvp.directories.MaterialContentModel;
import com.laichushu.book.mvp.directories.MaterialListModel;
import com.laichushu.book.mvp.forgetpwd.ForgetPwdModel;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.mvp.login.LoginModel;
import com.laichushu.book.mvp.part.PartModel;
import com.laichushu.book.mvp.regist.RegistModel;
import com.laichushu.book.mvp.regist2.RegistModel2;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ActivityList_Paramet;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.bean.netbean.Purchase_Paramet;
import com.laichushu.book.bean.netbean.SaveComment_Paramet;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 接口url
 * Created by wangtong
 * on 2016/10/11
 */
public interface ApiStores {
    //baseUrl
//    String API_SERVER_URL = "http://60.205.141.21:8099/";
    String API_SERVER_URL = "http://192.168.1.103:8082/book-app/";//张峰
//    String API_SERVER_URL = "http://192.168.1.119:8082/book-app/";//施大勇
//    String API_SERVER_URL = "http://192.168.147.101:8082/book-app/";//张永生

    //登录接口
    @POST("login/do")
    Observable<LoginModel> loginLoadData(@Body Login_Paramet paramet);

    //校验手机号和验证码接口
    @POST("register/valid")
    Observable<RegistModel> registCode(@Body RegistValid_Paramet paramet);

    //注册接口
    @POST("register/do")
    Observable<RegistModel2> registData(@Body Regist_Paramet paramet);

    //重置密码接口
    @POST("user/resetPwd")
    Observable<ForgetPwdModel> forgetPwdData(@Body ForgetPwd_Paramet paramet);

    //home轮播图接口
    @POST("carousel/list")
    Observable<HomeModel> homeCarouselData();

    //home最热接口
    @POST("searchArticle/suggest")
    Observable<HomeHotModel> homeHotData(@Body HomeHot_Paramet paramet);

    //home全部图书接口
    @POST("searchArticle/list")
    Observable<HomeHotModel> homeAllData(@Body HomeAllBook_Paramet paramet);

    //home分类
    @POST("searchArticle/findArticleByCategoryId")
    Observable<HomeHotModel> gethomeCategroyData(@Body HomeCategroyListBook_Paramet paramet);

    //首页分类接口
    @POST("category/findCategoryList")
    Observable<CategoryModle> getCategoryList();

    //首页搜索接口
    @POST("searchArticle/searchByName")
    Observable<HomeHotModel> homeSearch(@Body HomeSearch_Paramet paramet);

    //作者详情接口
    @POST("author/list")
    Observable<AuthorDetailModle> authorDetail(@Body AuthorDetail_Paramet paramet);

    //图书详情-喜欢本书的也喜欢接口
    @POST("searchArticle/bestLikeSuggest")
    Observable<HomeHotModel> bestLikeSuggest(@Body BestLike_Paramet paramet);

    //图书订阅 or 取消订阅 接口
    @POST("subscribeArticle/save")
    Observable<SubscribeArticleModle> subscribeArticle(@Body SubscribeArticle_Paramet paramet);

    //图书普通or大咖评论查询接口
    @POST("articleScore/list")
    Observable<ArticleCommentModle> articleComment(@Body Comment_Paramet paramet);

    //图书全部评论查询接口
    @POST("articleScore/list")
    Observable<ArticleCommentModle> articleComment(@Body Comment2_Paramet paramet);

    //图书评论保存接口
    @POST("articleScore/save")
    Observable<SendCommentMoudle> saveComment(@Body SaveComment_Paramet paramet);

    //回复评论接口
    @POST("articleComment/list")
    Observable<CommentDetailModle> CommentList(@Body ReCommentList_Paramet paramet);

    //保存回复评论接口
    @POST("articleComment/save")
    Observable<CommentDetailModle> saveComment(@Body ReSavaComment_Paramet paramet);

    //活动列表接口
    @POST("activity/list")
    Observable<HomeHotModel> activityList(@Body ActivityList_Paramet paramet);

    //活动结果接口
    @POST("activity/getActivityResult")
    Observable<CampaignModel> getActivityResult(@Body ActivityResult_Paramet paramet);

    //参加活动
    @POST("activity/save")
    Observable<CampaignJoinModel> joinActivity(@Body JoinActivity_Paramet paramet);

    //获取作者作品
    @POST("searchArticle/findArticleByAuthorId")
    Observable<AuthorWorksModle> getAuthorWorks(@Body AuthorWorks_Paramet paramet);

    //获取素材列表接口
    @POST("material/list")
    Observable<MaterialListModel> getMaterialList(@Body MaterialList_Paramet paramet);

    //获取素材内容接口
    @POST("readMaterial/list")
    Observable<MaterialContentModel> getMaterialContent(@Body MaterialContent_Paramet paramet);

    //获取目录接口
    @POST("chapter/list")
    Observable<BookMoudle> getBookList(@Body BookList_Paramet paramet);

    //获取目录接口
    @POST("chapter/list")
    Observable<PartModel> getPartList(@Body PartList_Paramet paramet);

    //查询余额接口
    @POST("reward/getMoney")
    Observable<BalanceBean> getBalance(@Body Balance_Paramet paramet);

    //图书打赏接口
    @POST("reward/save")
    Observable<RewardResult> rewardMoney(@Body RewardMoney_Paramet paramet);

    //购买图书接口
    @POST("purchase/save")
    Observable<RewardResult> payBook(@Body Purchase_Paramet paramet);

    //举报
    @POST("complaint/save")
    Observable<RewardResult> complaint(@Body Complaint_Paramet paramet);

    //点赞
    @POST("scoreLike/save")
    Observable<RewardResult> saveScoreLike(@Body ScoreLike_Paramet paramet);

    //取消赞
    @POST("scoreLike/delete")
    Observable<RewardResult> deleteScoreLike(@Body ScoreLike_Paramet paramet);

    //收藏
    @POST("collect/save ")
    Observable<RewardResult> collectSave(@Body CollectSave_Paramet paramet);

    //阅读
    @POST("chapter/list")
    Observable<RewardResult> getJurisdiction(@Body Jurisdiction_Paramet paramet);

    //个人中心
    @POST("perHome/find")
    Observable<PersonalCentreResult> getPersonalDetails(@Body PersonalCentre_Parmet paramet);

    //个人主页更新用户详情
    @POST("perHome/edit")
    Observable<RewardResult> getUpdateDetails(@Body UpdatePersonalInfor_Parmet paramet);
    //提交个人信息审核
    @POST("perHome/atte")
    Observable<RewardResult> getUploadInfor(@Body UploadIdcardInfor_Parmet paramet);
}
