package com.sofacity.laichushu.retrofit;

import com.sofacity.laichushu.bean.netbean.ActivityList_Paramet;
import com.sofacity.laichushu.bean.netbean.ActivityResult_Paramet;
import com.sofacity.laichushu.bean.netbean.AuthorDetail_Paramet;
import com.sofacity.laichushu.bean.netbean.AuthorWorks_Paramet;
import com.sofacity.laichushu.bean.netbean.BestLike_Paramet;
import com.sofacity.laichushu.bean.netbean.Comment_Paramet;
import com.sofacity.laichushu.bean.netbean.ForgetPwd_Paramet;
import com.sofacity.laichushu.bean.netbean.HomeAllBook_Paramet;
import com.sofacity.laichushu.bean.netbean.HomeHot_Paramet;
import com.sofacity.laichushu.bean.netbean.HomeSearch_Paramet;
import com.sofacity.laichushu.bean.netbean.JoinActivity_Paramet;
import com.sofacity.laichushu.bean.netbean.Login_Paramet;
import com.sofacity.laichushu.bean.netbean.MaterialContent_Paramet;
import com.sofacity.laichushu.bean.netbean.MaterialList_Paramet;
import com.sofacity.laichushu.bean.netbean.ReCommentList_Paramet;
import com.sofacity.laichushu.bean.netbean.ReSavaComment_Paramet;
import com.sofacity.laichushu.bean.netbean.RegistValid_Paramet;
import com.sofacity.laichushu.bean.netbean.Regist_Paramet;
import com.sofacity.laichushu.bean.netbean.SaveComment_Paramet;
import com.sofacity.laichushu.bean.netbean.SubscribeArticle_Paramet;
import com.sofacity.laichushu.mvp.campaign.AuthorWorksModle;
import com.sofacity.laichushu.mvp.campaign.CampaignJoinModel;
import com.sofacity.laichushu.mvp.campaign.CampaignModel;
import com.sofacity.laichushu.mvp.allcomment.SendCommentMoudle;
import com.sofacity.laichushu.mvp.bookdetail.ArticleCommentModle;
import com.sofacity.laichushu.mvp.bookdetail.AuthorDetailModle;
import com.sofacity.laichushu.mvp.bookdetail.SubscribeArticleModle;
import com.sofacity.laichushu.mvp.commentdetail.CommentDetailModle;
import com.sofacity.laichushu.mvp.directories.MaterialContentModel;
import com.sofacity.laichushu.mvp.directories.MaterialListModel;
import com.sofacity.laichushu.mvp.forgetpwd.ForgetPwdModel;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.mvp.home.HomeModel;
import com.sofacity.laichushu.mvp.login.LoginModel;
import com.sofacity.laichushu.mvp.regist.RegistModel;
import com.sofacity.laichushu.mvp.regist2.RegistModel2;

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
    String API_SERVER_URL = "http://192.168.147.117:8082/book-app/";//张峰
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
    //首页搜索接口
    @POST("searchArticle/searchByName")
    Observable<HomeHotModel> homeSearch(@Body HomeSearch_Paramet paramet);
    //作者详情接口
    @POST("author/list")
    Observable<AuthorDetailModle> authorDetail(@Body AuthorDetail_Paramet paramet);
    //图书详情-喜欢本书的也喜欢接口
    @POST("searchArticle/bestLikeSuggest")
    Observable<HomeHotModel> bestLikeSuggest (@Body BestLike_Paramet paramet);
    //图书订阅接口
    @POST("subscribeArticle/save")
    Observable<SubscribeArticleModle> subscribeArticle (@Body SubscribeArticle_Paramet paramet);
    //打赏账户查询余额接口
    @POST("reward/getMoney")
    Observable balance();
    //图书打赏接口
    @POST("reward/save")
    Observable rewardMoney();
    //图书评论查询接口
    @POST("articleScore/list")
    Observable<ArticleCommentModle> articleComment(@Body Comment_Paramet paramet);
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

}
