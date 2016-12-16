package com.laichushu.book.retrofit;

import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.JsonBean.PreviewCoverBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.JsonBean.UrlResult;
import com.laichushu.book.bean.netbean.*;
import com.laichushu.book.bean.otherbean.CoverDirBean;
import com.laichushu.book.mvp.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.bookcast.BookCastModle;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.bookdetail.AuthorDetailModle;
import com.laichushu.book.mvp.bookdetail.SubscribeArticleModle;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.campaign.CampaignJoinModel;
import com.laichushu.book.mvp.campaign.CampaignModel;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.coverdir.CoverDirModle;
import com.laichushu.book.mvp.creatnewdraft.CreateNewDraftModle;
import com.laichushu.book.mvp.directories.BookMoudle;
import com.laichushu.book.mvp.directories.MaterialContentModel;
import com.laichushu.book.mvp.directories.MaterialListModel;
import com.laichushu.book.mvp.draftmodle.DraftModle;
import com.laichushu.book.mvp.forgetpwd.ForgetPwdModel;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.mvp.homesearch.HomeSearchModel;
import com.laichushu.book.mvp.login.LoginModel;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.mvp.notice.NoticeModle;
import com.laichushu.book.mvp.part.PartModel;
import com.laichushu.book.mvp.regist.RegistModel;
import com.laichushu.book.mvp.regist2.RegistModel2;
import com.laichushu.book.mvp.sourcematerial.SourceMaterialModle;
import com.laichushu.book.mvp.sourcematerialdir.SourceMaterialDirModle;
import com.laichushu.book.mvp.topicdetail.TopicdetailModel;

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
//    String API_SERVER_URL = "http://192.168.191.1:8082/book-app/";
//    String API_SERVER_URL = "http://192.168.1.103:8082/book-app/";//张峰
//    String API_SERVER_URL = "http://192.168.1.119:8082/book-app/";//施大勇1
//    String API_SERVER_URL = "http://192.168.1.129:8082/book-app/";//施大勇2
//    String API_SERVER_URL = "http://192.168.1.148:8082/book-app/";//施大勇3
//    String API_SERVER_URL = "http://192.168.0.123:8082/book-app/";//施大勇4
    String API_SERVER_URL = "http://192.168.1.150:8082/book-app/";//施大勇5
//    String API_SERVER_URL = "http://192.168.1.130:8082/book-app/";//施大勇6
//    String API_SERVER_URL = "http://192.168.147.101:8082/book-app/";//张永生
//      String API_SERVER_URL = "http://192.168.1.122:8082/book-app/";//李红江

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

    //home分类查询图书
    @POST("searchArticle/findArticleByCategoryId")
    Observable<HomeHotModel> gethomeCategroyData(@Body HomeCategroyListBook_Paramet paramet);

    //首页和创建新书分类接口
    @POST("category/findCategoryList")
    Observable<CategoryModle> getCategoryList(@Body HomeCategory_Paramet paramet);

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
    Observable<ArticleCommentModle> articleComment(@Body TopicDetailCommentList_Paramet paramet);

    //图书评论保存接口
    @POST("articleScore/save")
    Observable<SendCommentMoudle> saveComment(@Body SaveComment_Paramet paramet);

    //回复评论接口
    @POST("comment/list")
    Observable<CommentDetailModle> CommentList(@Body ReCommentList_Paramet paramet);

    //保存回复评论接口
    @POST("comment/save")
    Observable<CommentDetailModle> saveComment(@Body ReSavaComment_Paramet paramet);

    //活动列表接口
    @POST("activity/list")
    Observable<HomeHotModel> activityList(@Body ActivityList_Paramet paramet);

    //活动结果接口
    @POST("activity/getActivityResult")
    Observable<CampaignModel> getActivityResult(@Body ActivityResult_Paramet paramet);

    //活动详情借口 活动通知--->活动详情
    @POST("activity/getActivityResultFromInfo")
    Observable<CampaignDetailsModel> getActivityDetailsResult(@Body ActivityResult_Paramet paramet);

    //参加活动
    @POST("activity/save")
    Observable<CampaignJoinModel> joinActivity(@Body JoinActivity_Paramet paramet);

    //获取作者作品
    @POST("searchArticle/findExpressArticleByAuthorId")
    Observable<AuthorWorksModle> getAuthorWorks(@Body AuthorWorks_Paramet paramet);

    //获取作者作品 通过图书id查询图书详情
    @POST("searchArticle/findArticleByBookId")
    Observable<BookDetailsModle> getAuthorWorksByBookId(@Body AuthorWorksByBookId_Paramet paramet);

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

    //评论点赞
    @POST("like/save")
    Observable<RewardResult> saveTopicDyLike(@Body TopicDyLike_Paramet paramet);

    //取消赞
    @POST("scoreLike/delete")
    Observable<RewardResult> deleteScoreLike(@Body ScoreLike_Paramet paramet);

    //收藏
    @POST("collect/save")
    Observable<RewardResult> collectSave(@Body CollectSave_Paramet paramet);

    //话题动态收藏
    @POST("collect/save")
    Observable<RewardResult> collectSaveData(@Body CollectSaveDate_Paramet paramet);

    //阅读
    @POST("chapter/list")
    Observable<RewardResult> getJurisdiction(@Body Jurisdiction_Paramet paramet);

    //个人中心
    @POST("perHome/find")
    Observable<PersonalCentreResult> getPersonalDetails(@Body PersonalCentre_Parmet paramet);

    //个人主页更新用户详情  @Body UpdatePersonalInfor_Parmet paramet
    @Multipart
    @POST("perHome/edit")
    Observable<PersonInfoResultReward> getUpdateDetails(@PartMap Map<String, RequestBody> params, @Part("photoFile\"; filename=\"head.jpg") RequestBody file);

    //提交个人信息审核@Body UploadIdcardInfor_Parmet paramet
    @Multipart
    @POST("perHome/atte")
    Observable<RewardResult> getUploadInfor(@PartMap Map<String, RequestBody> params, @Part("idCardFront\"; filename=\"front.jpg") RequestBody front, @Part("idCardOppsite\"; filename=\"oppsite.jpg") RequestBody oppsite);

    //创建新书 从相册
    @Multipart
    @POST("article/save")
    Observable<RewardResult> createNewBook(@Part("file\"; filename=\"cover.jpg") RequestBody file, @PartMap Map<String, RequestBody> params);

    //创建新书 从模版
    @Multipart
    @POST("article/save")
    Observable<RewardResult> createNewBook(@PartMap Map<String, RequestBody> params);

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

    //书的列表 <============>用户书的列表 userId传targetId
    @POST("searchArticle/findArticleByAuthorId")
    Observable<HomeHotModel> getArticleBookList(@Body ArticleBookList_Paramet paramet);

    //投稿
    @POST("article/vote")
    Observable<RewardResult> articleVote(@Body ArticleVote_Paramet paramet);

    //发表
    @POST("article/publish")
    Observable<RewardResult> publishNewBook(@Body PublishNewBook_Paramet paramet);

    //删除新书
    @POST("article/delete")
    Observable<RewardResult> deleteNewBook(@Body DeleteNewBook_Paramet paramet);

    //收藏列表
    @POST("collect/list")
    Observable<HomeHotModel> getCollectList(@Body MyArticBooklist_paramet paramet);
    //修改签约状态
    @POST("party/findPartyEditor")
    Observable<SignStateResult> getSignStateDetails(@Body MyArticBooklist_paramet paramet);
    //修改签约编辑
    @POST("article/signEditor")
    Observable<RewardResult> getSignEditorDetails(@Body MySignEditor_paramet paramet);

    //浏览
    @POST("searchArticle/findArticleByAuthorId")
    Observable<HomeHotModel> getArticleBookListScan(@Body MyArticBooklist_paramet paramet);

    //新浏览
    @POST("browse/list")
    Observable<HomeHotModel> getMyBrowseBookListScan(@Body MyBrowseList_paramet paramet);

    //个人主页用户信息   userId
    @POST("userDetail/findUserInfo")
    Observable<HomeUserResult> getHomeUserInforDetails(@Body HomeInfo_paramet paramet);

    //用户主页信息   userID,target Id
    @POST("userDetail/findUserInfo")
    Observable<HomeUserResult> getUserInforDetails(@Body HomeUserInfor_paramet paramet);

    //个人主页动态
//    @POST("userDetail/findDy")
    @POST("dynamic/find")
    Observable<HomeUseDyrResult> getHomeUserDyDetails(@Body HomeUserDy_parmet paramet);

    //用户主页动态
    @POST("userDetail/findDy")
//    @POST("dynamic/find")
    Observable<HomeUseDyrResult> getUserHomeDyDetails(@Body HomeUserDy_parmet paramet);

    //个人主页关注我的
    @POST("perFocus/findMyBeFocusedInfos")
    Observable<HomePersonFocusResult> getHomeUserFocusMeDetails(@Body HomeUserFocusBe_parmet paramet);

    //用户主页关注他的
    @POST("perFocus/findHisBeFocusedInfos")
    Observable<HomePersonFocusResult> getUserFocusMeDetails(@Body UserFocusBe_parmet paramet);

    //意见反馈
    @POST("feedback/save")
    Observable<RewardResult> feedBackDetails(@Body FeedBack_parmet paramet);

    //个人主页我关注的
    @POST("perFocus/findMyFocusInfos")
    Observable<HomePersonFocusResult> getHomeUserFocusBeDetails(@Body HomeUserFocusBe_parmet paramet);

    //用户主页他关注的
    @POST("perFocus/findHisFocusInfos")
    Observable<HomePersonFocusResult> getUserFocusBeDetails(@Body UserFocusBe_parmet paramet);

    //个人主页更新“关注我的”信息关注状态
    @POST("perFocus/updateBe")
    Observable<HomeFocusResult> getHomeUserFocusMeStatuss(@Body HomeUserFocusState_Paramet paramet);

    //个人主页更新“我关注的”信息关注状态
    @POST("userDetail/updateHis")
    Observable<HomeFocusResult> getHomeUserFocusBeStatuss(@Body HomeUserFocusState_Paramet paramet);

    //添加关注
    @POST("perFocus/addFocus")
    Observable<HomeFocusResult> getAddFocus(@Body ChangeFocusState_Paramet paramet);

    //添加关注
    @POST("perFocus/delFocus")
    Observable<HomeFocusResult> getDelFocus(@Body ChangeFocusState_Paramet paramet);

    //个人主页发表动态话题
    @POST("topic/saveTopic")
    Observable<RewardResult> publishTopic(@Body PublishTopic_Paramet paramet);

    //个人主页等级分类详情
    @POST("gradeDetail/find")
    Observable<GradeRemarksResult> gradeDetails(@Body GradeDetails_Paramet paramet);

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

    //获取素材列表
    @POST("material/delete")
    Observable<RewardResult> deleteMaterial(@Body DeleteMaterial_Paramet paramet);

    //获取模版列表
    @POST("cover/list")
    Observable<CoverDirModle> getCoverList(@Body CoverDir_Paramet paramet);

    //获取预览模版
    @POST("cover/make")
    Observable<PreviewCoverBean> makeCover(@Body CoverMake_Paramet paramet);

    //素材重命名
    @POST("material/editCatalog")
    Observable<RewardResult> materialRename(@Body MaterialRename_Paramet paramet);

    //草稿重命名
    @POST("chapter/editCatalog")
    Observable<RewardResult> chapterRename(@Body ChapterRename_Paramet paramet);

    //获取模板类型
    @POST("cover/mouldList")
    Observable<CoverDirBean> getCoverModleList(@Body CoverModleList_Paramet paramet);

    //获取机构
    @POST("party/list")
    Observable<MechanismListBean> getMechanismList(@Body MechanismList_Paramet paramet);

    //我的钱包基本信息+交易记录
    @POST("wallet/find")
    Observable<WalletBalanceReward> getBalanceRecordDetails(@Body WalletBalanceRecord_Paramet paramet);

    //我的钱包充值
    @POST("wallet/find")
    Observable<WalletBalanceReward> getRechargeDetails(@Body WalletBalanceRecord_Paramet paramet);

    //我的钱包提现
    @POST("withdraw/apply")
    Observable<RewardResult> getWithdrawalsApplayDetails(@Body WithdrawalsApplay_Paramet paramet);

    //获取机构公告列表
    @POST("press/notices")
    Observable<NoticeModle> getNoticesList(@Body NoticesList_Paramet paramet);

    //获取机构话题
    @POST("press/topics")
    Observable<MechanismTopicListModel> getMechanismTopicList(@Body MechanismTopicList_Paramet paramet);

    //给机构发消息
    @POST("party/sendMsgToParty")
    Observable<RewardResult> sendMsgToParty(@Body SendMsgToParty_Paramet paramet);

    //话题详情评论列表
    @POST("comment/list")
    Observable<TopicdetailModel> topicDetailCommentList(@Body TopicDetailCommentList_Paramet paramet);

    //话题详情保存评论
    @POST("comment/save")
    Observable<RewardResult> topicDetailCommentSave(@Body TopicDetailCommentSave_Paramet paramet);
    //活动详情发送私信
    @POST("perInformation/addPerMsgInfo")
    Observable<RewardResult> appActPerMsgInfoDetails(@Body AddActPerMsgInfo_Paramet paramet);

    //消息界面 回复消息
    @POST("information/sendMsg")
    Observable<RewardResult> msgSendMsgDetails(@Body SendMsgDetails_Paramet paramet);

    //消息页面--评论
    @POST("information/list")
    Observable<MessageCommentResult> messageCommentDetails(@Body MessageComment_Paramet paramet);

    //消息页面-根据图书id查找图书详情
    @POST("myArticle/find")
    Observable<HomeHotModel> getBookDetailsById(@Body BookDetails_Paramet paramet);

    //消息界面-查询私信列表
    @POST("perInformation/findList")
    Observable<MessageCommentResult> getPerInformation(@Body PerInformation_Paramet paramet);

    //消息界面-查询私信详情
    @POST("perInformation/findDetailList")
    Observable<PerMsgInfoReward> getPerInformationDetails(@Body PerInformationDetails_Paramet paramet);

    //消息界面-细心详情回复
    @POST("perInformation/addPerDetailInfo")
    Observable<RewardResult> getPerDetails(@Body AddPerDetails_Paramet paramet);

    //私信界面-删除私信
    @POST("perInformation/delPerInfo")
    Observable<RewardResult> getDelPerInfoDetails(@Body DelPerInfo_Paramet paramet);

    //发送私信
    @POST("perInformation/addPerMsgInfo")
    Observable<RewardResult> getAddPerMsgInfDetails(@Body AddPerMsgInfo_Paramet paramet);

    //修改图书权限
    @POST("article/set")
    Observable<RewardResult> getUpdateBookPermissionDetails(@Body UpdateBookPermission_Paramet paramet);

    //修改素材权限
    @POST("article/updateMaterialPermission")
    Observable<RewardResult> getUpdateMaterialPermissionDetails(@Body UpdateMaterialPermission_Paramet paramet);

    //热门搜索历史
    @POST("searchArticle/findHotArticle")
    Observable<HomeSearchModel> getHotSearch();

    //获取url下载接口
    @POST("chapter/download")
    Observable<UrlResult> downloadEpubFile(@Body DownloadEpubFilePermission_Paramet paramet);

    //发送验证码的接口为：
    @POST("msg/send")
    Observable<RewardResult> sendMsg(@Body SendMsg_Paramet paramet);
}
