/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api;


import com.uyt.ying.rxhttp.net.model.UserEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ChanelEntity;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * created  by xxxx on 2019/11/14.
 */
public interface IHttpApi {


    @GET("movie/newMoviesWithCategory")
    Observable<Response<ResponseBody>> test();
    //首页频道
    @GET("classification/getClassificationInfo")
    Observable<Response<ResponseBody>> allChannel(@QueryMap Map<String, Object> data);

    //用户登录
    @POST("pay/apiMember/login")
    Observable<UserEntity> PostLogin(@Body RequestBody requestBody);

//    //首页频道
    @GET("movie/newMoviesWithCategory")
    Observable<Response<ResponseBody>> homeMovie(@QueryMap Map<String, Object> data);

    //系统参数
    //
    @GET("sysParameter/1")
    Observable<Response<ResponseBody>> movieSysParameter(@QueryMap Map<String, Object> data);

    //搜索结果
    @GET("movie/selectOverallMovie")
    Observable<Response<ResponseBody>> searchResult(@QueryMap Map<String, Object> data);

//    //获取liveroomList视频列表
//    @GET("movie/newMoviesWithCategoryxxx")
//    Observable<LiveRoomEntity> getShipinList(@QueryMap Map<String, Object> data);

    //房间列表
    @POST("{url}")
    Observable<Response<ResponseBody>> getRoomList(@Path(value = "url",encoded = true)String url,@QueryMap Map<String, Object> data);

    @POST("navigate/navigateList.json")
    Observable<Response<ResponseBody>> getNavigateList(@QueryMap Map<String, Object> data);
    //频道列表
    @GET("classification/getClassificationInfo")
    Observable<ChanelEntity> getChannelList(@QueryMap Map<String, Object> data);

    //倒计时
    @POST("{url}")
    Observable<Response<ResponseBody>>  getCountDown(@Path(value = "url",encoded = true)String url,@QueryMap Map<String, Object> data);

    //上期开奖结果
    @POST("{url}")
    Observable<Response<ResponseBody>>  getLastLottery(@Path(value = "url",encoded = true)String url,@QueryMap Map<String, Object> data);

    //游戏规则
    @POST("{url}")
    Observable<Response<ResponseBody>>  getGameRuleData(@Path(value = "url",encoded = true)String url,@QueryMap Map<String, Object> data);

    //投注
    @POST("{url}")
    Observable<Response<ResponseBody>>  getBet(@Path(value = "url",encoded = true)String url,@QueryMap Map<String, Object> data);

    //刷新直播间地址 by 主播account
    @POST("liveBroadCast/getLiveRoomByAccount")
    Observable<Response<ResponseBody>> RefreshRoomUrl(@Body RequestBody requestBody);

    //收藏
    @GET("movieLog/collection")
    Observable<Response<ResponseBody>> collectMovie(@QueryMap Map<String, Object> data);
        //发现
    @GET("movie/movieFind")
    Observable<Response<ResponseBody>> getFindHomeData(@QueryMap Map<String, Object> data);

    //我的最爱
    @POST("movieLog/like/list")
    Observable<Response<ResponseBody>> mineFavorate(@Body RequestBody requestBody);

    //版本更新
    @GET("appVersion/get/1")
    Observable<Response<ResponseBody>> versitionupload(@QueryMap Map<String, Object> data);

    //礼物列表
/*    @POST("liveBroadCast/giftList")
    Observable<Response<ResponseBody>> getGiftlist(@QueryMap Map<String, Object> data);*/

    //礼物列表
    @POST("liveBroadCast/giftList")
    Observable<Response<ResponseBody>> giftList(@Body RequestBody requestBody);

    //赠送礼物
    @POST("{url}")
    Observable<Response<ResponseBody>> sendGift(@Path(value = "url",encoded = true)String url,@QueryMap Map<String, Object> data);

    //赠送礼物
    @POST("liveBroadCast/giveGift")
    Observable<Response<ResponseBody>> getSendGift(@Body RequestBody requestBody);



    //红包状态
    @POST("rongcloudHB/userActivityDetail")
    Observable<Response<ResponseBody>> getHbStatus(@QueryMap Map<String, Object> data);



    @POST("member/memberMoney.json")
    Observable<Response<ResponseBody>> getAmount(@Body RequestBody requestBody);

    @POST("member/getTodayZJCache.json")
    Observable<Response<ResponseBody>> requestIsZj(@QueryMap Map<String, Object> data);
    //发红包
    @POST("rongcloud/sendRed")
    Observable<Response<ResponseBody>> sendRed(@Body RequestBody requestBody);

    //开普通红包
    @POST("rongcloud/openRed")
    Observable<Response<ResponseBody>> openRed(@Body RequestBody requestBody);

    //开趣约红包
    @POST("rongcloudHB/openQuYueHB")
    Observable<Response<ResponseBody>> openQyRed(@Body RequestBody requestBody);

    //开专享红包
    @POST("rongcloudHB/openZxHB")
    Observable<Response<ResponseBody>> openZxRed(@Body RequestBody requestBody);

    //天降红包活动预告
    @POST("liveBroadCast/heavenRedPacketTeaser")
    Observable<Response<ResponseBody>> tj_yuGao(@Body RequestBody requestBody);

    //开天降红包
    @POST("liveBroadCast/getHeavenRedPacket")
    Observable<Response<ResponseBody>> openTjHb(@Body RequestBody requestBody);

    //直播列表
    @POST("liveBroadCast/anchorMemberRoomList")
    Observable<Response<ResponseBody>> liveList(@Body RequestBody requestBody);



    //关注列表
    @POST("liveBroadCast/anchorFollowList")
    Observable<Response<ResponseBody>> mineFollowList(@Body RequestBody requestBody);

    //关注主播
    @POST("liveBroadCast/anchorFollow")
    Observable<Response<ResponseBody>> requestFollow(@Body RequestBody requestBody);

    //  取消关注主播
    @POST("liveBroadCast/anchorCancelFollow")
    Observable<Response<ResponseBody>> cancelFollow(@Body RequestBody requestBody);

    //  提交意见反馈
    @POST("liveBroadCast/feedBack")
    Observable<Response<ResponseBody>> feedBack(@Body RequestBody requestBody);

    //我的反馈列表
    @POST("liveBroadCast/feedBackList")
    Observable<Response<ResponseBody>> feedBackList(@Body RequestBody requestBody);

    //发送手机验证码
    @POST("sms/sendSms")
    Observable<Response<ResponseBody>> sendPhoneCode(@Body RequestBody requestBody);

    //邀请码列表
    @POST("member/findCode")
    Observable<Response<ResponseBody>> getInviteCodeList(@Body RequestBody requestBody);

    //自身返点信息
    @POST("member/aboutPerson")
    Observable<Response<ResponseBody>> aboutPerson(@Body RequestBody requestBody);


    //创建下级
    @POST("member/createSubordinate")
    Observable<Response<ResponseBody>> kaihu(@Body RequestBody requestBody);

    //积分详情
    @POST("liveBroadCast/memberIntegralDetail")
    Observable<Response<ResponseBody>> integralDetails(@Body RequestBody requestBody);

    //上传图片
    @POST("uploadWS/upload")
    Observable<Response<ResponseBody>> uploadImg(@Body RequestBody requestBody);
    //邀请红包推广收益
    @POST("liveBroadCast/yqRedIncome")
    Observable<Response<ResponseBody>> redRule(@Body RequestBody requestBody);

    @POST("member/memberHeadPortrait")
    Observable<Response<ResponseBody>> getChatUserInfo(@QueryMap Map<String, Object> data);

    //举报主播
    @POST("liveBroadCast/anchorReport")
    Observable<Response<ResponseBody>> reportAnchor(@Body RequestBody requestBody);
    //红包活动参数
    @POST("rongcloudHB/rongcloudHBParameter")
    Observable<Response<ResponseBody>> rongcloudHBParameter(@Body RequestBody requestBody);

    //
    //邀请红包数量
    @POST("rongcloudHB/inviteRedCount")
    Observable<Response<ResponseBody>> inviteRedCount(@Body RequestBody requestBody);
    //版本更新
    @POST("app/appVersion")
    Observable<Response<ResponseBody>> appUpdate(@Body RequestBody requestBody);
    //专享红包信息
    @POST("rongcloudHB/isZXStandard")
    Observable<Response<ResponseBody>> zxRedInfo(@Body RequestBody requestBody);

    //棋牌列表
    @POST("chess/getChessClassList")
    Observable<Response<ResponseBody>> chessList(@Body RequestBody requestBody);

    //棋牌游戏地址
    @POST("chess/playChessGame")
    Observable<Response<ResponseBody>> playChess(@Body RequestBody requestBody);

    //棋牌游戏余额
    @POST("chess/getChessMemberMoney")
    Observable<Response<ResponseBody>> chessBalance(@Body RequestBody requestBody);

    //一键回收所有余额
    @POST("chess/oneClickRecycling")
    Observable<Response<ResponseBody>> recycleBalance(@Body RequestBody requestBody);

    //余额自动转换
    @POST("chess/autoConvert")
    Observable<Response<ResponseBody>> autoChahge(@Body RequestBody requestBody);

    //开元充值
    @POST("chess/recharge")
    Observable<Response<ResponseBody>> chessRecharge(@Body RequestBody requestBody);

    //开元提现
    @POST("chess/withdrawal")
    Observable<Response<ResponseBody>> chessWithDraw(@Body RequestBody requestBody);

    //游戏报表
    @POST("chess/gameReport")
    Observable<Response<ResponseBody>> gameReport(@Body RequestBody requestBody);

    @POST("member/todayProfitLoss.json")
    Observable<Response<ResponseBody>> todayWinOrLose(@Body RequestBody requestBody);

    @POST("chess/touZhuListByGame")
    Observable<Response<ResponseBody>> chessBetList(@Body RequestBody requestBody);
    //购彩大厅左右菜单栏
    @POST("chess/getChessGameList")
    Observable<Response<ResponseBody>> chessGameList(@Body RequestBody requestBody);

    //检验用户名是否存在
    @POST("member/validateName")
    Observable<Response<ResponseBody>>checkAccount(@Body RequestBody requestBody);

    //修改=密码
    @POST("member/updatePassword")
    Observable<Response<ResponseBody>>forgetPassword(@Body RequestBody requestBody);
    //获取下载地址
    @POST("url/url.json")
    Observable<Response<ResponseBody>>downloadUrl(@Body RequestBody requestBody);

    //获取主播名片
    @POST("liveBroadCast/openAnchorBusinessCard")
    Observable<Response<ResponseBody>>ahchorbusiness(@Body RequestBody requestBody);

    //后台域名列表
    @POST("app/appRequestDomains")
    Observable<Response<ResponseBody>>baseUrlList(@Body RequestBody requestBody);

    @POST("sys/sysParameter")
    Observable<Response<ResponseBody>>sysParameter(@Body RequestBody requestBody);

    @POST("navigate/getGameClassVersion")
    Observable<Response<ResponseBody>>gameClassVersion(@Body RequestBody requestBody);

    /**
     * 用户等级
     * @param requestBody
     * @return
     */
    @POST("member/promotionMechanism.json")
    Observable<Response<ResponseBody>>level(@Body RequestBody requestBody);

    /**
     * 禁言列表
     */
    @POST("rongcloud/chatRoomGagList")
    Observable<Response<ResponseBody>>forbiddenList(@Body RequestBody requestBody);

    /**
     * 房管解除禁言
     */
    @POST("rongcloud/chatRoomGagDel")
    Observable<Response<ResponseBody>>cancelForbidden(@Body RequestBody requestBody);

    /**
     * 房管禁言用户
     */
    @POST("rongcloud/chatRoomGagAdd")
    Observable<Response<ResponseBody>>forbiddenUser(@Body RequestBody requestBody);

    /**
     *直播tab分类
     */
    @POST("liveBroadCast/liveCategoryList")
    Observable<Response<ResponseBody>>livrClass(@Body RequestBody requestBody);

    /**
     *礼物榜
     */
    @POST("liveBroadCast/getUserGiftAmountByAnchorAccount")
    Observable<Response<ResponseBody>>giftRank(@Body RequestBody requestBody);

    /**
     *全局排行榜
     */
    @POST("liveBroadCast/rankingHb")
    Observable<Response<ResponseBody>>rankList(@Body RequestBody requestBody);

    //昨日各种xx包排行榜
    @POST("liveBroadCast/rankingHb")
    Observable<Response<ResponseBody>> getYestodayList(@Body RequestBody requestBody);

    //付费
    @POST("liveBroadCast/anchorSubscribe")
    Observable<Response<ResponseBody>> tollAnchor(@Body RequestBody requestBody);


    @POST("liveBroadCast/changeAutoAnchorSubscribe")
    Observable<Response<ResponseBody>> changeAutoToll(@Body RequestBody requestBody);

    @POST("member/agentReportSimple")
    Observable<Response<ResponseBody>> agentCenter(@Body RequestBody requestBody);

    @POST("rongcloud/blockSelfAdd")
    Observable<Response<ResponseBody>> forbiddenSelf(@Body RequestBody requestBody);

    @POST("rongcloud/getChatroomInfo")
    Observable<Response<ResponseBody>> getChatRoomInfo(@Body RequestBody requestBody);

    @POST("member/register.json")
    Observable<Response<ResponseBody>> register(@Body RequestBody requestBody);

    @POST("liveBroadCast/liveWatchMinutes")
    Observable<Response<ResponseBody>> watchMinutes(@Body RequestBody requestBody);

    //充值列表
    @POST("recharge/bankList")
    Observable<Response<ResponseBody>> rechargeList(@Body RequestBody requestBody);
    //创建充值订单
    @POST("recharge/createRemittance")
    Observable<Response<ResponseBody>> createRechargeOlder(@Body RequestBody requestBody);

    @POST("member/updatePassword2")
    Observable<Response<ResponseBody>> findPassword2(@Body RequestBody requestBody);

    @POST("extension/noticeInfoList")
    Observable<Response<ResponseBody>> rechargeNotice(@Body RequestBody requestBody);

    @POST("member/login")
    Observable<Response<ResponseBody>> login(@Body RequestBody requestBody);

    @POST("prompt/promptWords.json")
    Observable<Response<ResponseBody>> promptWords(@Body RequestBody requestBody);

    @POST("member/registerCheck")
    Observable<Response<ResponseBody>> checkRegister(@Body RequestBody requestBody);

    @POST("rongcloud/memberKillMember")
    Observable<Response<ResponseBody>> blockUser(@Body RequestBody requestBody);

    @POST("rongcloud/memberKillMemberList")
    Observable<Response<ResponseBody>> blockList(@Body RequestBody requestBody);

    @POST("member/drawUsdtRate")
    Observable<Response<ResponseBody>> USDTRate(@Body RequestBody requestBody);

    @POST("member/bindBankCard")
    Observable<Response<ResponseBody>> bindUSDT(@Body RequestBody requestBody);


    @POST("memberPoint/getYqzpLastNum")
    Observable<Response<ResponseBody>> turntableCount(@Body RequestBody requestBody);

    @POST("memberPoint/memberMedal")
    Observable<Response<ResponseBody>> mineEquipment(@Body RequestBody requestBody);


    @POST("memberPoint/handleMemberMedalStatus")
    Observable<Response<ResponseBody>> useMount(@Body RequestBody requestBody);

    @POST("memberPoint/buyMedal")
    Observable<Response<ResponseBody>> buyMount(@Body RequestBody requestBody);


    @POST("memberPoint/gainZhuanShiConditions")
    Observable<Response<ResponseBody>> takeDiamond(@Body RequestBody requestBody);

    @POST("memberPoint/memberMedalInUse")
    Observable<Response<ResponseBody>> usingEquipment(@Body RequestBody requestBody);

    @POST("memberPoint/sysGradeList")
    Observable<Response<ResponseBody>> levelList(@Body RequestBody requestBody);

    @POST("memberPoint/openYqzp")
    Observable<Response<ResponseBody>> requestTurntable(@Body RequestBody requestBody);

    @POST("memberPoint/signInList")
    Observable<Response<ResponseBody>> signInList(@Body RequestBody requestBody);


    @POST("memberPoint/signIn")
    Observable<Response<ResponseBody>> signIn(@Body RequestBody requestBody);

    @POST("member/bindBankCard")
    Observable<Response<ResponseBody>> bindBankCard(@Body RequestBody requestBody);

    @POST("member/balanceDraw")
    Observable<Response<ResponseBody>> withDraw(@Body RequestBody requestBody);

    @POST("member/commissionDraw.json")
    Observable<Response<ResponseBody>> commissionDraw(@Body RequestBody requestBody);

    @POST("extension/noticeInfoList.json")
    Observable<Response<ResponseBody>> bannerData(@Body RequestBody requestBody);

    @POST("member/updatePerson.json")
    Observable<Response<ResponseBody>> modifyNickname(@Body RequestBody requestBody);

    @POST("game/bet")
    Observable<Response<ResponseBody>> gameBet(@Body RequestBody requestBody);

    @POST("sscai/bet")
    Observable<Response<ResponseBody>> sscBet(@Body RequestBody requestBody);

    @POST("race/bet")
    Observable<Response<ResponseBody>> raceBet(@Body RequestBody requestBody);

    @POST("marksix/bet")
    Observable<Response<ResponseBody>> markSixBet(@Body RequestBody requestBody);

    @POST("dan/bet")
    Observable<Response<ResponseBody>> danBet(@Body RequestBody requestBody);

    @POST("happy/bet")
    Observable<Response<ResponseBody>> happyBet(@Body RequestBody requestBody);

    @POST("farm/bet")
    Observable<Response<ResponseBody>> farmBet(@Body RequestBody requestBody);

    @POST("happyten/bet")
    Observable<Response<ResponseBody>> happyTenBet(@Body RequestBody requestBody);

    @POST("xuanwu/bet")
    Observable<Response<ResponseBody>> xuanwuTenBet(@Body RequestBody requestBody);

    @POST("member/agentReport.json")
    Observable<Response<ResponseBody>> agentReport(@Body RequestBody requestBody);

    @POST("navigate/navigateList")
    Observable<Response<ResponseBody>> navigateList(@Body RequestBody requestBody);


    @POST("member/getTeamReportList")
    Observable<Response<ResponseBody>> getTeamReportList(@Body RequestBody requestBody);
    //

    @POST("navigate/getDragon.json")
    Observable<Response<ResponseBody>> getDragon(@Body RequestBody requestBody);

    @POST("recharge/bankInfoList.json")
    Observable<Response<ResponseBody>> bankInfoList(@Body RequestBody requestBody);

    @POST("member/visitorLogin")
    Observable<Response<ResponseBody>> visitorLogin(@Body RequestBody requestBody);


    @POST("member/validateName")
    Observable<Response<ResponseBody>> visitorShow(@Body RequestBody requestBody);

    @POST("member/visitorBindPhone")
    Observable<Response<ResponseBody>> visitorBind(@Body RequestBody requestBody);


    @POST("member/validatePayPassword.json")
    Observable<Response<ResponseBody>> checkPassword(@Body RequestBody requestBody);

    @GET("member/test")
    Observable<Response<ResponseBody>> pingTest(@QueryMap Map<String, Object> data);




}
