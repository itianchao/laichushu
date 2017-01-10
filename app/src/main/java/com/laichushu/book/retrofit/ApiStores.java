package com.laichushu.book.retrofit;


import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.HomeTitleBean;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.JsonBean.PreviewCoverBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.JsonBean.UrlResult;
import com.laichushu.book.bean.netbean.*;
import com.laichushu.book.bean.otherbean.CoverDirBean;
import com.laichushu.book.bean.otherbean.NoteListModle;
import com.laichushu.book.bean.otherbean.ScoreListModle;
import com.laichushu.book.bean.otherbean.SpeakListModle;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.entry.forgetpwd.ForgetPwdModel;
import com.laichushu.book.mvp.entry.login.LoginModel;
import com.laichushu.book.mvp.entry.regist.RegistModel;
import com.laichushu.book.mvp.entry.regist2.RegistModel2;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.mvp.find.coursera.videodetail.VideoDetailModle;
import com.laichushu.book.mvp.find.group.GroupListModle;
import com.laichushu.book.mvp.find.group.groupsearch.FindGroupModle;
import com.laichushu.book.mvp.find.group.member.FindGroupMemberModle;
import com.laichushu.book.mvp.find.mechanism.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.mvp.home.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.home.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.home.bookdetail.AuthorDetailModle;
import com.laichushu.book.mvp.home.bookdetail.BookDetailModle;
import com.laichushu.book.mvp.home.bookdetail.SubscribeArticleModle;
import com.laichushu.book.mvp.home.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.home.campaign.CampaignJoinModel;
import com.laichushu.book.mvp.home.campaign.CampaignModel;
import com.laichushu.book.mvp.home.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.home.homecategory.CategoryModle;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.mvp.home.homelist.HomeModel;
import com.laichushu.book.mvp.home.homesearch.HomeSearchModel;
import com.laichushu.book.mvp.home.part.PartModel;
import com.laichushu.book.mvp.mine.bookcast.BookCastModle;
import com.laichushu.book.mvp.msg.notice.NoticeModle;
import com.laichushu.book.mvp.msg.topic.topicdetail.TopicdetailModel;
import com.laichushu.book.mvp.write.coverdir.CoverDirModle;
import com.laichushu.book.mvp.write.creatnewdraft.CreateNewDraftModle;
import com.laichushu.book.mvp.write.directories.BookMoudle;
import com.laichushu.book.mvp.write.directories.MaterialContentModel;
import com.laichushu.book.mvp.write.directories.MaterialListModel;
import com.laichushu.book.mvp.write.draftmodle.DraftModle;
import com.laichushu.book.mvp.write.sourcematerial.SourceMaterialModle;
import com.laichushu.book.mvp.write.sourcematerialdir.SourceMaterialDirModle;
import com.laichushu.book.ui.activity.MineCourseList_Paramet;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 接口url
 * Created by wangtong
 * on 2016/10/11
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = ConstantValue.API_SERVER_URL4;

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
    //活动列表接口--同城
    @POST("searchArticle/findArticleByCity")
    Observable<HomeHotModel> getActivityListByCity(@Body ActivityListByCity_Paramet paramet);

    //活动结果接口
    @POST("activity/getActivityResult")
    Observable<CampaignModel> getActivityResult(@Body ActivityResult_Paramet paramet);

    //活动详情借口 活动通知--->活动详情
    @POST("activity/getActivityResultFromInfo")
    Observable<CampaignDetailsModel> getActivityDetailsResult(@Body ActivityResult_Paramet paramet);

    //参加活动
    @POST("activity/save")
    Observable<CampaignJoinModel> joinActivity(@Body JoinActivity_Paramet paramet);

    //发表投稿时获取作者作品
    @POST("searchArticle/findExpressArticleByAuthorId")
    Observable<AuthorWorksModle> getAuthorWorks(@Body AuthorWorks_Paramet paramet);

    //活动 获取作者作品
    @POST("searchArticle/findActivityArticleByAuthorId")
    Observable<AuthorWorksModle> getActivityAuthorWorks(@Body AuthorWorks_Paramet paramet);

    //获取作者作品 通过图书id查询图书详情
    @POST("searchArticle/findArticleByBookId")
    Observable<BookDetailsModle> getAuthorWorksByBookId(@Body AuthorWorksByBookId_Paramet paramet);

    //获取作者作品 通过图书id查询图书详情
    @POST("searchArticle/findArticleByBookId")
    Observable<HomeTitleBean> getBookById(@Body AuthorWorksByBookId_Paramet paramet);

    //获取作者作品 通过图书id查询图书详情
    @POST("searchArticle/findArticleByBookId")
    Observable<BookDetailModle> getBookById(@Body FindByBookId_Paramet paramet);

    //获取活动 通过活动Id查询活动详情
    @POST("activity/getActivityById")
    Observable<HomeTitleBean> getActivityById(@Body ActivityById_Paramet paramet);

    //获取活动 通过活动Id查询活动详情
    @POST("activity/getActivityById")
    Observable<CampaignModel> getActivityById(@Body ActivityDetail_Paramet paramet);

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

    //点赞 -- 废除
    @POST("scoreLike/save")
    Observable<RewardResult> saveScoreLike(@Body ScoreLike_Paramet paramet);

    //消息--删除
    @POST("information/delete")
    Observable<RewardResult> deleteMsgDetails(@Body DeleteMsg_Paramet paramet);

    //评论点赞
    @POST("like/save")
    Observable<RewardResult> saveScoreLike(@Body TopicDyLike_Paramet paramet);

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

    //图书-收藏列表
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

    //个人主页动态+机构话题列表
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

    //删除关注
    @POST("perFocus/delFocus")
    Observable<HomeFocusResult> getDelFocus(@Body ChangeFocusState_Paramet paramet);

    //个人主页发表动态话题
    @POST("topic/saveTopic")
    Observable<RewardResult> publishTopic(@Body PublishTopic_Paramet paramet);

    //机构--删除话题
    @POST("topic/deleteTopic")
    Observable<RewardResult> deleteTopicManageDetails(@Body DeleteTopic_Paramet paramet);

    //机构--修改资料
    @Multipart
    @POST("press/update")
    Observable<RewardResult> updateTopicDetails(@PartMap Map<String, RequestBody> params, @Part("file\"; filename=\"logoUrl.jpg") RequestBody file);

    //机构---修改机构信息--弃用
    @POST("topic/selectMyTopic")
    Observable<RewardResult> checkTopicList(@Body PublishTopic_Paramet paramet);

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
    Observable<WalletBalanceReward> getRechargeDetail(@Body WalletBalanceRecord_Paramet paramet);

    //我的钱包提现
    @POST("withdraw/apply")
    Observable<RewardResult> getWithdrawalsApplayDetails(@Body WithdrawalsApplay_Paramet paramet);

    //我的钱包充值
    @POST("recharge/recharge")
    Observable<RewardResult> getRechargeAppPayDetails(@Body RechargeAppPay_Paramet paramet);

    //我的服务者列表--合作
    @POST("servicer/findMyServerList")
    Observable<FindServiceCooperateMode> getFindMyServerListDetails(@Body FindMyServerList_Paramet paramet);

    //我的服务者--服务内容列表
    @POST("servicerItem/findServerItemList")
    Observable<FindServiceItemListModel> getFindServerItemListDetails(@Body FindServerItemList_Paramet paramet);

    //获取机构公告列表
    @POST("press/notices")
    Observable<NoticeModle> getNoticesList(@Body NoticesList_Paramet paramet);

    //机构--发表新话题
    @POST("press/update")
    Observable<RewardResult> updateTopicMsgDetails(@Body NoticesList_Paramet paramet);

    //机构发标公告
    @POST("notice/save")
    Observable<RewardResult> getNoticesSaveList(@Body NoticesSave_Paramet paramet);

    //删除机构公告
    @POST("notice/delete")
    Observable<RewardResult> dleteNoticesItem(@Body NoticesList_Paramet paramet);

    //获取机构话题
//    @POST("press/topics")
    @POST("dynamic/find")
    Observable<MechanismTopicListModel> getMechanismTopicList(@Body MechanismTopicList_Paramet paramet);

    //搜索机构
    @POST("party/list")
    Observable<MechanismListBean> getSearchMechanismList(@Body MechanismSearchList_Paramet paramet);

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

    //发现精选课程
    @POST("lesson/lessonList")
    Observable<FindLessonListResult> findLessonListDatails(@Body FindLessonList_Paramet paramet);

    //发现小组推荐
    @POST("team/findTopTeam")
    Observable<FindCourseCommResult> findCourseCommendationDatails();

    //热门搜索历史
    @POST("searchArticle/findHotArticle")
    Observable<HomeSearchModel> getHotSearch();

    //获取url下载接口
    @POST("chapter/download")
    Observable<UrlResult> downloadEpubFile(@Body DownloadEpubFilePermission_Paramet paramet);

    //发送验证码的接口为：
    @POST("msg/send")
    Observable<RewardResult> sendMsg(@Body SendMsg_Paramet paramet);

    //下载文件
//    @Streaming //大文件时要加不然会OOM
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    //发现界面--编辑
    @POST("editor/list")
    Observable<FindEditorListModel> getEditorListDatails(@Body FindEditorList_Paramet paramet);

    //发现编辑--个人主页
    @POST("editor/info")
    Observable<FindEditorInfoModel> getEditorInfoDatails(@Body FindEditorInfo_Paramet paramet);

    //发现服务--个人主页
    @POST("servicer/findServerInfo")
    Observable<FindServerInfoModel> getServiceInfoDatails(@Body FindEditorInfo_Paramet paramet);

    //编辑界面--编辑评论
    @POST("editor/commentSave")
    Observable<RewardResult> saveEditorComment(@Body EditorSaveComment_Paramet paramet);

    //发现编辑--查询所有评论
    @POST("editor/commentList")
    Observable<TopicdetailModel> getEditorCommentList(@Body FindEditorCommentList_Paramet paramet);

    //发现编辑--案列
    @POST("searchArticle/findArticleByCaseId")
    Observable<HomeHotModel> getEditorFindArticleByCaseId(@Body FindArticleByCaseId_Paramet paramet);

    //发现编辑--个人主页合作
    @POST("article/vote")
    Observable<RewardResult> getArticleVoteDetails(@Body FindArticleVote_Paramet paramet);

    //发现--服务列表
    @POST("servicer/findServerList")
    Observable<FindServiceInfoModel> getFindServerListDetails(@Body FindSertverList_Paramet paramet);

    //我的服务--成为服务者
    @Multipart
    @POST("servicer/saveServerInfo")
    Observable<RewardResult> SaveServerInfoDetails(@PartMap Map<String, RequestBody> params, @Part("visitingCardFile\"; filename=\"visiting.jpg") RequestBody file);

    //服务--收藏列表
    @POST("collect/list")
    Observable<FindServiceInfoModel> getCollectServiceDateList(@Body MyArticBooklist_paramet paramet);

    //服务--添加服务
    @POST("servicerItem/saveServeItem")
    Observable<RewardResult> getSaveServeItem(@Body SaveServeItem_paramet paramet);

    /**
     * 发现 - 小组
     */
    //已加入的小组列表
    @POST("team/myJoin")
    Observable<GroupListModle> getJoinGroupList(@Body JoinGroupList_Paramet paramet);

    //我创建的小组列表
    @POST("team/myCreate")
    Observable<GroupListModle> getMyCreateGroupList(@Body JoinGroupList_Paramet paramet);

    //创建小组
    @Multipart
    @POST("team/save")
    Observable<RewardResult> createNewGroup(@Part("file\"; filename=\"group.jpg") RequestBody file, @PartMap Map<String, RequestBody> params);

    //搜索小组
    @POST("team/all")
    Observable<FindGroupModle> searchGroupList(@Body SearchGroupList_Paramet paramet);

    //最新话题列表
    @POST("topic/findNewTopic")
    Observable<MechanismTopicListModel> getNewTopicList(@Body NewTopicList_Paramet paramet);

    //我发表的话题列表
    @POST("topic/findTopicList")
    Observable<MechanismTopicListModel> getMyPublishTopicList(@Body MyPublishTopicList_Paramet paramet);


    //我收藏的话题列表
    @POST("topic/findTopicByCollectId")
    Observable<MechanismTopicListModel> getMyCollectTopicList(@Body MyPublishTopicList_Paramet paramet);

    //我回复的话题列表
    @POST("topic/findTopicByCommentId")
    Observable<MechanismTopicListModel> getMyReplyTopicList(@Body MyPublishTopicList_Paramet paramet);

    //小组话题
    @POST("topic/findTopicList")
    Observable<MechanismTopicListModel> getGroupTopicList(@Body MyPublishTopicList_Paramet paramet);

    //推荐话题
    @POST("topic/findSuggestTopicList")
    Observable<MechanismTopicListModel> getGroupSuggestTopicList(@Body MyPublishTopicList_Paramet paramet);

    //获取小组成员列表
    @POST("team/memberList")
    Observable<FindGroupMemberModle> getGroupMemberList(@Body GroupApplyMemberList_Paramet paramet);

    //获取申请成员列表
    @POST("team/todoList")
    Observable<FindGroupMemberModle> getGroupApplyMemberList(@Body GroupApplyMemberList_Paramet paramet);

    //处理申请成员 同意or拒绝
    @POST("team/approve")
    Observable<RewardResult> getGroupApplyMemberResult(@Body GroupApplyMemberResult_Paramet paramet);

    //搜索的话题列表
    @POST("topic/findTopicList")
    Observable<MechanismTopicListModel> getSearchTopicList(@Body MyPublishTopicList_Paramet paramet);

    //删除小组成员 or 退出小组成员
    @POST("team/leaveTeam")
    Observable<RewardResult> deleteGroupMember(@Body DeleteGroupMember_Paramet paramet);

    //申请加入小组
    @POST("team/applyJoin")
    Observable<RewardResult> applyJoinGroup(@Body ApplyJoinGroupMember_Paramet paramet);

    //解散
    @POST("team/dismiss")
    Observable<RewardResult> dismissGroup(@Body DismissGroup_Paramet paramet);

    //话题置顶
    @POST("topic/updateTopicTopFlag")
    Observable<RewardResult> updateTopicTop(@Body UpdateTopicTop_Paramet paramet);

    //话题删除
    @POST("topic/deleteTopic")
    Observable<RewardResult> deleteTopic(@Body DeleteTopic_Paramet paramet);

    //话题设置推荐
    @POST("topic/updateTopicRecommended")
    Observable<RewardResult> updateTopicRecommended(@Body UpdateTopicRecommended_Paramet paramet);

    /***
     *  发现 - 课程
     */

    //课程视频列表接口 or 课程文档列表接口
    @POST("lesson/lessonList")
    Observable<CourseraModle> getLessonList(@Body LessonList_Paramet paramet);

    //课程详情
    @POST("lesson/lessonDetail")
    Observable<VideoDetailModle> getLessonDetail(@Body LessonDetail_Paramet paramet);

    //课程笔记列表
    @POST("lessonNote/list")
    Observable<NoteListModle> getLessonNoteList(@Body NoteList_Paramet paramet);

    //保存笔记 or 修改笔记
    @POST("lessonNote/save")
    Observable<RewardResult> getLessonNoteSave(@Body NoteSave_Paramet paramet);

    //删除笔记
    @POST("lessonNote/delete")
    Observable<RewardResult> getLessonNoteDelete(@Body NoteDelete_Paramet paramet);

    //课程讲义详情接口
    @POST("handOuts/handOutsList")
    Observable<SpeakListModle> getSpeakpdfList(@Body SpeakpdfDetail_Paramet paramet);

    //课程评分
    @POST("score/list")
    Observable<ScoreListModle> getCourseScoreList(@Body CourseScoreList_Paramet paramet);

    //课程评分保存
    @POST("score/save")
    Observable<RewardResult> courseScoreSave(@Body CourseScoreSave_Paramet paramet);

    //课程相关
    @POST("lesson/lessonAbout")
    Observable<CourseraModle> getLessonAboutList(@Body LessonAbout_Paramet paramet);

    //我的收藏、我的下载、我的浏览
    @POST("lesson/findMyLessonList")
    Observable<CourseraModle> getMineCourseList(@Body MineCourseList_Paramet paramet);
}
