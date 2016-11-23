package com.laichushu.book.retrofit;

import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ActivityList_Paramet;
import com.laichushu.book.bean.netbean.ActivityResult_Paramet;
import com.laichushu.book.bean.netbean.ArticleBookList_Paramet;
import com.laichushu.book.bean.netbean.ArticleVote_Paramet;
import com.laichushu.book.bean.netbean.AuthorDetail_Paramet;
import com.laichushu.book.bean.netbean.AuthorWorks_Paramet;
import com.laichushu.book.bean.netbean.Balance_Paramet;
import com.laichushu.book.bean.netbean.BestLike_Paramet;
import com.laichushu.book.bean.netbean.BookList_Paramet;
import com.laichushu.book.bean.netbean.CollectList_Paramet;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.bean.netbean.Comment2_Paramet;
import com.laichushu.book.bean.netbean.Comment_Paramet;
import com.laichushu.book.bean.netbean.Complaint_Paramet;
import com.laichushu.book.bean.netbean.CreateNewDraft_Paramet;
import com.laichushu.book.bean.netbean.CreateSourceMaterialDir_Paramet;
import com.laichushu.book.bean.netbean.CreateSourceMaterial_Paramet;
import com.laichushu.book.bean.netbean.DeleteDraft_Paramet;
import com.laichushu.book.bean.netbean.DeleteNewBook_Paramet;
import com.laichushu.book.bean.netbean.DraftList_Paramet;
import com.laichushu.book.bean.netbean.EditDraft_Paramet;
import com.laichushu.book.bean.netbean.EditMaterialBook_Paramet;
import com.laichushu.book.bean.netbean.ForgetPwd_Paramet;
import com.laichushu.book.bean.netbean.HomeAllBook_Paramet;
import com.laichushu.book.bean.netbean.HomeCategroyListBook_Paramet;
import com.laichushu.book.bean.netbean.HomeHot_Paramet;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.bean.netbean.HomeSearch_Paramet;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserDy_parmet;
import com.laichushu.book.bean.netbean.HomeUserFocusMe_parmet;
import com.laichushu.book.bean.netbean.HomeUserInfor_paramet;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.bean.netbean.JoinActivity_Paramet;
import com.laichushu.book.bean.netbean.Jurisdiction_Paramet;
import com.laichushu.book.bean.netbean.Login_Paramet;
import com.laichushu.book.bean.netbean.MaterialContent_Paramet;
import com.laichushu.book.bean.netbean.MaterialList_Paramet;
import com.laichushu.book.bean.netbean.MyArticBooklist_paramet;
import com.laichushu.book.bean.netbean.MyHomeModel;
import com.laichushu.book.bean.netbean.PartList_Paramet;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.PersonalCentre_Parmet;
import com.laichushu.book.bean.netbean.PublishNewBook_Paramet;
import com.laichushu.book.bean.netbean.Purchase_Paramet;
import com.laichushu.book.bean.netbean.ReCommentList_Paramet;
import com.laichushu.book.bean.netbean.ReSavaComment_Paramet;
import com.laichushu.book.bean.netbean.RegistValid_Paramet;
import com.laichushu.book.bean.netbean.Regist_Paramet;
import com.laichushu.book.bean.netbean.RewardMoney_Paramet;
import com.laichushu.book.bean.netbean.SaveComment_Paramet;
import com.laichushu.book.bean.netbean.ScoreLike_Paramet;
import com.laichushu.book.bean.netbean.SourceMaterialDirList_Paramet;
import com.laichushu.book.bean.netbean.SourceMaterialList_Paramet;
import com.laichushu.book.bean.netbean.SubscribeArticle_Paramet;
import com.laichushu.book.bean.netbean.UpdatePersonalInfor_Parmet;
import com.laichushu.book.bean.netbean.UploadIdcardInfor_Parmet;
import com.laichushu.book.mvp.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.bookcast.BookCastModle;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.bookdetail.AuthorDetailModle;
import com.laichushu.book.mvp.bookdetail.SubscribeArticleModle;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.campaign.CampaignJoinModel;
import com.laichushu.book.mvp.campaign.CampaignModel;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.creatnewdraft.CreateNewDraftModle;
import com.laichushu.book.mvp.directories.BookMoudle;
import com.laichushu.book.mvp.directories.MaterialContentModel;
import com.laichushu.book.mvp.directories.MaterialListModel;
import com.laichushu.book.mvp.draftmodle.DraftModle;
import com.laichushu.book.mvp.forgetpwd.ForgetPwdModel;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.mvp.login.LoginModel;
import com.laichushu.book.mvp.part.PartModel;
import com.laichushu.book.mvp.regist.RegistModel;
import com.laichushu.book.mvp.regist2.RegistModel2;
import com.laichushu.book.mvp.sourcematerial.SourceMaterialModle;
import com.laichushu.book.mvp.sourcematerialdir.SourceMaterialDirModle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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
//    String API_SERVER_URL = "http://192.168.1.119:8082/book-app/";//施大勇1
//    String API_SERVER_URL = "http://192.168.1.129:8082/book-app/";//施大勇2
//    String API_SERVER_URL = "http://192.168.1.148:8082/book-app/";//施大勇3
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

    //获取素材列表
    @POST("material/list")
    Observable<PartModel> getMaterialList(@Body SourceMaterialList_Paramet paramet);

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

    //创建新书
    @Multipart
    @POST("article/save")
    Observable<RewardResult> createNewBook(@Part("file\"; filename=\"cover.jpg") RequestBody file, @PartMap Map<String, RequestBody> params);

    //本地图片转换
    @Multipart
    @POST("file/upload")
    Observable<CreateNewDraftModle> uploadImg(@Part("file\"; filename=\"img.jpg") RequestBody file, @PartMap Map<String, RequestBody> params);

    //新建草稿
    @POST("chapter/save")
    Observable<RewardResult> createNewDraft(@Body CreateNewDraft_Paramet paramet);

    //修改草稿保存
    @POST("chapter/edit")
    Observable<RewardResult> editDraft(@Body EditDraft_Paramet paramet);

    //书的章列表或者节列表
    @POST("chapter/list")
    Observable<DraftModle> getDraftList(@Body DraftList_Paramet paramet);

    //书的列表
    @POST("searchArticle/findArticleByAuthorId")
    Observable<HomeHotModel> getArticleBookList(@Body ArticleBookList_Paramet paramet);


    //投稿
    @POST("article/vote")
    Observable<RewardResult> articleVote(@Body ArticleVote_Paramet paramet);

    //发表
    @POST("article/issue")
    Observable<RewardResult> publishNewBook(@Body PublishNewBook_Paramet paramet);

    //删除新书
    @POST("article/delete")
    Observable<RewardResult> deleteNewBook(@Body DeleteNewBook_Paramet paramet);

    //收藏列表
    @POST("collect/list")
    Observable<HomeHotModel> getCollectList(@Body MyArticBooklist_paramet paramet);

    //浏览
    @POST("searchArticle/findArticleByAuthorId")
    Observable<HomeHotModel> getArticleBookListScan(@Body MyArticBooklist_paramet paramet);

    //个人主页用户信息
    @POST("userDetail/find")
    Observable<HomeUserResult> getHomeUserInforDetails(@Body HomeUserInfor_paramet paramet);

    //个人主页动态
    @POST("userDetail/findDy")
    Observable<HomeUseDyrResult> getHomeUserDyDetails(@Body HomeUserDy_parmet paramet);

    //个人主页关注我的
    @POST("perFocus/findMy")
    Observable<HomePersonFocusResult> getHomeUserFocusMeDetails(@Body HomeUserFocusMe_parmet paramet);

    //个人主页我关注的
    @POST("perFocus/findBe")
    Observable<HomePersonFocusResult> getHomeUserFocusBeDetails(@Body HomeUserFocusMe_parmet paramet);

    //删除草稿
    @POST("chapter/delete")
    Observable<RewardResult> deleteDraftBook(@Body DeleteDraft_Paramet paramet);

    //收藏列表
    @POST("collect/list")
    Observable<BookCastModle> getCollectList(@Body CollectList_Paramet paramet);

    //添加素材文件夹
    @POST("material/addfolder")
    Observable<RewardResult> createSourceMaterialDir(@Body CreateSourceMaterialDir_Paramet paramet);

    //添加素材
    @POST("material/addmat")
    Observable<RewardResult> createSourceMaterial(@Body CreateSourceMaterial_Paramet paramet);

    //获取素材文件夹列表
    @POST("material/list")
    Observable<SourceMaterialDirModle> getSourceMaterialDirList(@Body SourceMaterialDirList_Paramet paramet);

    //获取素材列表
    @POST("material/list")
    Observable<SourceMaterialModle> getSourceMaterialList(@Body SourceMaterialList_Paramet paramet);

    //获取素材列表
    @POST("material/edit")
    Observable<RewardResult> editMaterialBook(@Body EditMaterialBook_Paramet paramet);

}
