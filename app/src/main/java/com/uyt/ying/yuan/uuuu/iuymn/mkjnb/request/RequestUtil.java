package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;

public class RequestUtil {

    public static String REQUEST_10000 = "/wx/getMemberByUnionid.json";

    public static String REQUEST_10001 = "/wx/wxMember.json";
    //获取余额宝信息
    public static String YUEBAO_INFO="/recharge/getYueBaoInfo";
    //转入余额宝
    public static String IN_YUEBAO="/recharge/transferToYueBao";
    //转出余额宝
    public static String OUT_YUEBAO="/recharge/transferOutYueBao";
    //余额宝明细
    public static String YUEBAO_MORE="/recharge/getMemberYueBaoRecordList";
    public static String APP_VERSION = "app/appVersion";
    public static String CHATROOM_USERINFO = "/chatRoom/onlinePersonInfo";
    public static String CHATROOM_ONLINE = "/chatRoom/onlinePeople";
    /*
我的反馈
 pageNo 页数
 pageSize 每页的数据个数
 orderField  createdDate按反馈时间排序 (不传默认按反馈id排序)
 */
    public static String MINE_FEEDBACK="liveBroadCast/feedBackList";
    /*
      上传图片
      img_url
       */
    public static String UPLOAD_IMAGE="uploadWS/upload";
    /*
提交意见反馈
labelId 问题分类id
content 意见反馈文字内容
photoUrl 意见反馈的图片url
 */
    public static String COMMIT_PROBLEM="liveBroadCast/feedBack";
    public static String VERSION_UPLOAD="appVersion/get/1";
    /*
        聊天室图片消息
     */
    public static  String UPLOADIMAGE="/uploadWS/upload";
    public static  String UPLOAD_FILE="/uploadWS/uploadApp";
    /**
     * 获取聊天室信息
     */
    public static String REQUEST_Chat1 = "rongcloud/getChatroomInfo";
    /*
    获取聊天室列表
     */
    public static String CHATROOMLIST="/rongcloud/getChatroomList";
    /*
     获取聊天室昨日高手榜
     */
    public static String CHATROOMRANKLIST="/rongcloud/yestodayRankList";
    /**
     * 获取长龙助手
     */
    public static String REQUEST_asscl = "navigate/getDragon.json";
    /**
     * 昨日高手
     */
    public static String REQUEST_bonus01 = "/rongcloud/yestodayRankList";
    /*
    我遇到的问题(全部问题反馈)
     */
    public static String ALL_PROBLEM="opinionLabel/getOpinionLabel";
    /**
     * 计划列表
     */
    public static String REQUEST_planB = "/game/getLotteryPlanLst";

    /**
     * 聊天室发红包
     */
    public static String REQUEST_Chat2 = "/rongcloud/sendRed";

    /**
     * 聊天室发红包
     */
    public static String LIVE_SEND_RED = "rongcloud/sendRed";

    /**
     * 领取红包
     */
    public static String REQUEST_Chat3 = "/rongcloud/openRed";

    /**
     * 开红包
     * user_id
     * redId
     */
    public static String OPEN_RED = "rongcloud/openRed";

    /**
     * 关注
     */
    public static String REQUEST_Chat4 = "/rongcloud/follow";

    /**
     * 昨日高手榜
     */
    public static String REQUEST_Chat5 = "/rongcloud/yestodayRankList";

    /**
     * 红包领取列表
     */
    public static String REQUEST_Chat6 = "/rongcloud/redInfo";

    /**
     * 商城订单调起微信支付参数
     */
    public static String REQUEST_10002 = "/wx/swiftWxPay.json";

    /**
     * 获取微信基本参数
     */
    public static String REQUEST_10004 = "/url/fronturl.json";

    /**
     * 获取微信基本参数
     */
    public static String REQUEST_10005 = "url/url.json";

    /**
     * 获取用户登录信息
     */
    public static String REQUEST_13r = "member/aboutPerson";
    /**
     * 查询用户是否中奖
     */
    public static String REQUEST_zj_tb = "/member/getTodayZJCache.json";
    /**
     * 新玩法
     */
    public static String REQUEST_01new = "/newPlay/getRule.json";
    /**
     * 新下注
     */
    public static String REQUEST_02new = "/newPlay/bet.json";

    /**
     * 江苏骰宝下注
     */
    public static String REQUEST_01tb ="/game/bet.json";
    /**
     * 江苏骰宝游戏组列表
     */
    public static String REQUEST_02tb="/game/getgroup";
    /**
     * 江苏骰宝二级游戏组列表
     */
    public static String REQUEST_03tb="/game/getgroupTwo.json";

    /**
     * 江苏骰宝游戏倒计时
     */
    public static String REQUEST_04tb ="/game/countdown.json";


    /**
     * 会员注册
     */
    public static String REQUEST_06tb = "member/register.json";
    /**
     * 会员登录
     */
    public static String REQUEST_07tb = "/member/login.json";
    /**
     * 下注订单
     */
    public static String REQUEST_08tb = "/game/betOrder.json";
    /**
     * 个人信息与金额
     */
    public static String REQUEST_09tb = "/member/aboutPerson.json";
    /**
     * 修改用户信息
     */
    public static String REQUEST_10tb = "/member/updatePerson.json";
    /**
     * 余额获得记录
     */
    public static String REQUEST_11tb = "/record/balanceGain.json";

    /**
     * 余额消费记录
     */
    public static String REQUEST_12tb = "/record/balanceConsume.json";
    /**
     * 历史报表
     */
    public static String REQUEST_13tb = "/game/getTypeList.json";

    //时时彩
    /**
     * 时时彩游戏组列表
     */
    public static String REQUEST_01ssc = "/sscai/getgroupTwo.json";
    /**
     * 时时彩游戏下注
     */
    public static String REQUEST_02ssc = "sscai/bet.json";
    /**
     * 时时彩倒计时
     */
    public static String REQUEST_03ssc = "/sscai/countdown.json";
    /**
     * 时时彩游戏类型（历史注单)
     */
    public static String REQUEST_04ssc = "/sscai/getTypeList.json";
    /**
     * 时时彩近期开奖
     */
    public static String REQUEST_05ssc = "/sscai/lastLottery.json";
    /**
     * 时时彩长龙榜
     */
    public static String REQUEST_06ssc = "/sscai/specialLottery.json";
    /**
     * 时时彩开奖统计
     */
    public static String REQUEST_07ssc = "/sscai/countLottery.json";

    //北京赛车(pk10)

    /**
     * 赛车获取二级组列表
     */
    public static String REQUEST_01sc = "/race/getgroupTwo.json";
    /**
     * 赛车下注
     */
    public static String REQUEST_02sc = "race/bet.json";
    /**
     * 赛车游戏倒计时
     */
    public static String REQUEST_03sc = "/race/countdown.json";
    /**
     * 赛车游戏类型列表（历史注单）
     */
    public static String REQUEST_04sc = "/race/getTypeList.json";
    /**
     * 赛车近期开奖
     */
    public static String REQUEST_05sc = "/race/lastLottery.json";
    /**
     * 赛车长龙榜
     */
    public static String REQUEST_06sc = "/race/specialLottery.json";
    /**
     * 赛车开奖统计
     */
    public static String REQUEST_07sc = "/race/countLottery.json";
    /**
     * 赛车获取二级组列表
     */
    public static String REQUEST_01race = "/race/getgroupAll";

    /**
     * 导航栏
     */
    public static String REQUEST_01dh = "/member/balanceRecordTypeNameList";
    public static String REQUEST_02dh = "/navigate/getHall";

    /**
     * 获取所有玩法
     */
    public static String REQUEST_03dh = "/newPlay/getRuleAll.json";

    /**
     * 导航栏
     */
    public static String REQUEST_01dhnew = "navigate/navigateList";

    /**
     * 导航栏大类
     */
    public static String REQUEST_01dhnewOne = "/navigate/navigateOneList.json";

    /**
     * 导航栏小类
     */
    public static String REQUEST_01dhnewTwo = "/navigate/navigateTwoList.json";
    /**
     * 代理域名
     */
    public static String REQUEST_01dl = "/agent/accountByshareCode.json";


    //六合彩
    /**
     * 六合彩分组
     */
    public static String REQUEST_01lhc = "/marksix/getgroup.json";
    /**
     * 六合彩二级分组
     */
    public static String REQUEST_02lhc = "/marksix/getgroupTwo.json";
    /**
     * 六合彩优化
     */
    public static String REQUEST_06lhc = "/marksix/getgroupAll";
    /**
     * 六合彩下注
     */
    public static String REQUEST_03lhc = "marksix/bet.json";
    /**
     * 六合彩倒计时
     */
    public static String REQUEST_04lhc = "/marksix/countdown.json";
    /**
     * 六合彩开奖结果
     */
    public static String REQUEST_05lhc = "/marksix/lastLottery.json";
    //PC蛋蛋
    /**
     * PC蛋蛋一级组列表
     */
    public static String REQUEST_01dd = "/dan/getgroup";
    /**
     * PC蛋蛋二级组列表
     */
    public static String REQUEST_02dd = "/dan/getgroupTwo.json";
    /**
     * PC蛋蛋下注
     */
    public static String REQUEST_03dd = "dan/bet.json";
    /**
     * PC蛋蛋倒计时
     */
    public static String REQUEST_04dd = "/dan/countdown.json";
    /**
     * PC蛋蛋近期开奖
     */
    public static String REQUEST_05dd = "/dan/lastLottery.json";
    //北京快乐8
    /**
     * 北京快乐8组列表
     */
    public static String REQUEST_01ha = "/happy/getgroupTwo";

    /**
     * 北京快乐8组列表
     */
    public static String REQUEST_01hapc = "/happy/getgroupAll";

    /**
     * 北京快乐8组列表all
     */
    public static String REQUEST_01haall = "/happy/getgroupAll";
    /**
     * 北京快乐8下注
     */
    public static String REQUEST_02ha = "happy/bet.json";
    /**
     * 北京快乐8倒计时
     */
    public static String REQUEST_03ha = "/happy/countdown.json";
    /**
     * 北京快乐8开奖结果
     */
    public static String REQUEST_04ha = "/happy/lastLottery.json";
    /**
     * 北京快乐8开奖统计
     */
    public static String REQUEST_05ha = "/happy/countLottery.json";
    //幸运农场
    /**
     * 幸运农场组列表
     */
    public static String REQUEST_01farm = "/farm/getgroupTwo.json";


    /**
     * 幸运农场组列表
     */
    public static String REQUEST_01farmall = "/farm/getgroupAll";

    /**
     * 广东快乐十分
     */
    public static String REQUEST_01gdhappy = "/happyten/getgroupAll";
    /**
     * 幸运农场下注
     */
    public static String REQUEST_02farm = "farm/bet.json";
    /**
     * 幸运农场倒计时
     */
    public static String REQUEST_03farm = "/farm/countdown.json";
    /**
     * 幸运农场开奖结果
     */
    public static String REQUEST_04farm = "/farm/lastLottery.json";

    /**
     * 幸运农场长龙榜
     */
    public static String REQUEST_05farm = "/farm/specialLottery.json";
    /**
     * 幸运农场开奖统计
     */
    public static String REQUEST_06farm = "/farm/countLottery.json";
    //广东快乐十分
    /**
     * 广东快乐十分组列表
     */
    public static String REQUEST_01happyten = "/happyten/getgroupTwo.json";
    /**
     * 广东快乐十分下注
     */
    public static String REQUEST_02happyten = "happyten/bet.json";
    /**
     * 广东快乐十分倒计时
     */
    public static String REQUEST_03happyten = "/happyten/countdown.json";
    /**
     * 广东快乐十分开奖结果
     */
    public static String REQUEST_04happyten = "/happyten/lastLottery.json";
    /**
     * 广东快乐十分长龙榜
     */
    public static String REQUEST_05happyten = "/happyten/specialLottery.json";
    /**
     * 广东快乐十分开奖统计
     */
    public static String REQUEST_06happyten = "/happyten/countLottery.json";
    //广东快乐十分
    /**
     * 广东11选5组列表
     */
    public static String REQUEST_01xuanwu = "/xuanwu/getgroupTwo.json";
    /**
     * 广东11选5下注
     */
    public static String REQUEST_02xuanwu = "xuanwu/bet.json";
    /**
     * 广东11选5倒计时
     */
    public static String REQUEST_03xuanwu = "/xuanwu/countdown.json";
    /**
     * 广东11选5开奖结果
     */
    public static String REQUEST_04xuanwu = "/xuanwu/lastLottery.json";
    /**
     * 广东11选5长龙榜
     */
    public static String REQUEST_05xuanwu = "/xuanwu/specialLottery.json";
    /**
     * 广东11选5开奖统计
     */
    public static String REQUEST_06xuanwu = "/xuanwu/countLottery.json";
    //个人中心
    /**
     * 获取用户资金信息
     */
    public static String REQUEST_06rzq = "member/memberMoney.json";
    /**
     * 获取用户等级信息
     */
    public static String REQUEST_07rzq = "/member/memberGrade.json";
    /**
     * 获取用户等级机制
     */
    public static String REQUEST_08rzq = "member/promotionMechanism.json";
    /**
     * 获取用户头像信息列表
     */
    public static String REQUEST_09rzq = "/member/memberHeadPortrait.json";
    /**
     * 用户信息修改
     */
    public static String REQUEST_10rzq = "member/updatePerson.json";



    /**
     * 用户私信列表
     */
    public static String REQUEST_02me = "/extension/letterList.json";

    /**
     * 用户私信详情
     */
    public static String REQUEST_03me = "/extension/letterDetail.json";

    /**
     * 公告详情
     */
    public static String REQUEST_04me = "/extension/noticeInfoDetail.json";

    /**
     * 积分
     */
    public static String REQUEST_05me = "/member/pointList.json";

    /**
     * 今日盈亏
     */
    public static String REQUEST_06me = "member/todayProfitLoss.json";

    /**
     * 是否有该账号的用户
     */
    public static String REQUEST_07me = "/member/isHaveUser.json";

    /**
     * 验证安全密码是否正确
     */
    public static String REQUEST_08me = "member/validatePayPassword.json";

    /**
     * 发送验证码
     */
    public static String REQUEST_11rzq = "/sms/sendSms.json";
    /**
     * 订单详情
     */
    public static String REQUEST_13rzq = "/game/orderDetail.json";

    /**
     * 佣金提现
     */
    public static String REQUEST_12rzq = "member/commissionDraw.json";

    /**
     * 代理报表
     */
    public static String REQUEST_14rzq = "member/agentReport.json";

    /**
     * 代理下级会员管理
     */
    public static String REQUEST_15rzq = "/member/childManagement.json";

    /**
     * 代理下级提现列表
     */
    public static String REQUEST_16rzq = "/member/childDrawRecordList.json";

    /**
     * 代理下级所有类型
     */
    public static String REQUEST_21rzq = "/member/childBalanceRecordList.json";

    /**
     * 代理下级充值列表
     */
    public static String REQUEST_17rzq = "/member/childRechargeList.json";

    /**
     * 代理下级投注订单
     */
    public static String REQUEST_18rzq = "/game/childBetOrder.json";
    /**
     * 个人信息查询
     */
    public static String REQUEST_19rzq = "/member/loginInfo.json";

    /**
     * 用户余额入和出列表
     */
    public static String REQUEST_20rzq = "/member/balanceRecordList.json";

    /**
     * 银行信息列表
     */
    public static String REQUEST_22rzq = "recharge/bankInfoList.json";

    /**
     * 撤销订单
     */
    public static String REQUEST_23rzq = "/game/cancelOrder.json";
    /**
     * 佣金明细
     */
    public static String REQUEST_24rzq = "/member/childCommissionList.json";

    /**
     * 用户私信未读条数
     */
    public static String REQUEST_27rzq = "/extension/letterUnreadNum.json";

    /**
     * 提示文字
     */
    public static String REQUEST_28rzq = "prompt/promptWords.json";


    /**
     * 开奖采集
     */
    public static String REQUEST_01cj = "/gather/gatherDan.json";//pc蛋蛋
    public static String REQUEST_02cj = "/gather/gatherFarm.json";//农场
    public static String REQUEST_03cj = "/gather/gatherGame.json";//快3
    public static String REQUEST_04cj = "/gather/gatherHappy8.json";//北京快乐8
    public static String REQUEST_05cj = "/gather/gatherHappyten.json";//广东快乐十分
    public static String REQUEST_06cj = "/gather/gatherMarksix.json";//六合彩
    public static String REQUEST_07cj = "/gather/gatherRace.json";//赛车
    public static String REQUEST_08cj = "/gather/gatherSScai.json";//时时彩
    public static String REQUEST_09cj = "/gather/gatherXuanwu.json";//广东十一选五

    /**
     * 代理获取下级账号姓名
     */
    public static String REQUEST_25rzq = "/member/childRealName.json";

    /**
     * 代理获取下级账号姓名
     */
    public static String REQUEST_26rzq = "/recharge/agentRechargeChild.json";

    //王者江苏骰宝

    /**
     * 快3一级列表
     */
    public static String REQUEST_4r = "/game/getgroup";

    /**
     * 快3二级列表
     */
    public static String REQUEST_5r = "/game/getgroupTwo";


    /**
     * 快3骰宝游戏倒计时
     */
    public static String REQUEST_6r = "/game/countdown";



    /**
     * 快3骰宝游戏下注
     */
    public static String REQUEST_7r = "game/bet";


    /**
     * 快3骰宝游戏期数
     */
    public static String REQUEST_8r = "/game/lastLottery";

    /**
     * 查询已创建的邀请码
     * user_id
     * pageNo
     * pageSize
     * isagent
     */
    public static String REQUEST_FINDCODE = "member/findCode";

    /**
     * 下级报表
     */
    public static String REQUEST_REPORT_LIST = "member/getTeamReportList";

    /**
     * 创建下级开户
     */
    public static String REQUEST_cr11 = "member/createSubordinate";

    /**
     * 王者注册
     */
    public static String REQUEST_9r = "/member/register";

    /**
     * 验证用户名
     */
    public static String REQUEST_vaie = "/member/validateName";

    /**
     * 王者注册获取代理名
     */
    public static String REQUEST_31s = "/agent/accountByShareCode";

    /**
     * 通过域名获取代理账号
     */
    public static String REQUEST_32s = "/agent/accountByUrl";

    /**
     * 王者登录
     */
    public static String REQUEST_10r = "member/login";


    /**
     * 下注订单
     */
    public static String REQUEST_11r = "/game/betOrder";

    /**
     * 下注订单  单次
     */
    public static String REQUEST_11rdanci = "/game/orderDetailOne";


    /**
     * 余额获得记录
     */
    public static String REQUEST_12r = "/record/balanceGain";

    /**
     * 登录密码修改
     */
    public static String REQUEST_14r = "/member/updatePerson";

    /**
     * 余额消费记录
     */
    public static String REQUEST_15r = "/record/balanceConsume";

    /**
     * 即时注单
     */
    public static String REQUEST_16r = "/game/getTypeList";



    //王者重庆时时彩

    /**
     * 二级组列表
     */
    public static String REQUEST_17r = "/sscai/getgroupTwoWX";

    /**
     * 时时彩游戏倒计时
     */
    public static String REQUEST_18r = "/sscai/countdown";

    /**
     * 近期开奖
     */
    public static String REQUEST_19r = "/sscai/lastLottery";


    /**
     * 近期开奖
     */
    public static String REQUEST_20r = "/sscai/bet";


    /**
     * 时时彩历史注单
     */
    public static String REQUEST_21r = "/sscai/getTypeList";


    /**
     * 时时彩两面长龙
     */
    public static String REQUEST_22r = "/sscai/specialLottery";


    /**
     * 时时彩两面长龙
     */
    public static String REQUEST_23r = "/sscai/countLottery";



    //北京赛车(PK10)


    /**
     * 北京赛车二级列表
     */
    public static String REQUEST_24r = "/race/getgroupTwo";

    /**
     * 北京赛车倒计时
     */
    public static String   REQUEST_25r = "/race/countdown";

    /**
     * 北京赛车近期开奖
     */
    public static String REQUEST_26r = "/race/lastLottery";

    /**
     * 北京赛车下注
     */
    public static String REQUEST_27r = "/race/bet";

    /**
     * 北京赛车(PK10)历史注单
     */
    public static String REQUEST_28r = "/race/getTypeList";

    /**
     * 北京赛车(PK10)两面长龙
     */
    public static String REQUEST_29r = "/race/specialLottery";

    /**
     * 北京赛车(PK10)路珠
     */
    public static String REQUEST_30r = "/race/countLottery";


    /**
     * 公告列表
     */
    public static String REQUEST_33r = "extension/noticeInfoList";



    //香港六合彩
    /**
     * 香港六合彩一级列表
     */
    public static String REQUEST_34r = "/marksix/getgroup";
    /**
     * 快3二级列表
     */
    public static String REQUEST_35r = "/marksix/getgroupTwo";

    /**
     * 香港六合彩倒计时
     */
    public static String REQUEST_36r = "/marksix/countdown";

    /**
     * 近期开奖
     */
    public static String REQUEST_37r = "/marksix/lastLottery";
    /**
     * 香港六合彩下注
     */
    public static String REQUEST_38r = "/marksix/bet";

    /*-------------充值---------------*/
    /**
     *汇款银行列表
     */
    public static String REQUEST_wt1 = "recharge/bankList";

    /**
     *汇款账号信息
     */
    public static String REQUEST_wt2 = "/recharge/remittanceAccount";


    /**
     *创建充值订单
     */
    public static String CREATE_RECHARGE_OLDER = "recharge/createRemittance";
    /**
     *提现
     */
    public static String REQUEST_wt4 = "member/balanceDraw";

    /**
     *绑定银行卡
     */
    public static String REQUEST_wt5 = "member/bindBankCard";


    /**
     *用户充值列表
     */
    public static String REQUEST_wt6 = "member/rechargeList";

    /**
     *用提现列表
     */
    public static String REQUEST_wt7 = "/member/drawRecordList";

    /**
     * 用户等级机制
     */
    public static String REQUEST_zz1 = "/member/promotionMechanism";

    /**
     *每日加奖
     */
    public static String REQUEST_zz2 = "/member/codeAmountMechanism";

    /**
     * 公告列表
     */
    public static String REQUEST_l1 = "/extension/noticeInfoList";

    /**
     * 系统参数
     */
    public static String REQUEST_l2 = "sys/sysParameter";

    /**
     * 是否有注册红包
     */
    public static String REQUEST_l4 = "/member/isLoginRed";

    /**
     * 用户注册红包列表
     */
    public static String REQUEST_l5 = "/member/loginRedList";

    /**
     * 领取注册红包
     */
    public static String REQUEST_l6 = "/member/drawLoginRed";


    /**
     * 是否有活动红包
     */
    public static String REQUEST_l7 = "/member/isActivityRed";

    /**
     * 用户活动红包列表
     */
    public static String REQUEST_l8 = "/member/activityRedList";

    /**
     * 领取活动红包
     */
    public static String REQUEST_l9 = "/member/drawActivityRed";


    /**
     * 是否有生日红包
     */
    public static String REQUEST_l10 = "/member/isBirthdayRed";

    /**
     * 用户生日红包列表
     */
    public static String REQUEST_l11 = "/member/birthdayRedList";

    /**
     * 领取生日红包
     */
    public static String REQUEST_l12 = "/member/drawBirthdayRed";
    /**
     * 发现-中奖信息
     */
    public static String REQUEST_zz3 = "/find/lotteryList";

    /**
     * 中奖信息个人信息
     */
    public static String REQUEST_zz4 = "/find/lotteryDetail";

    /**
     * 昨日盈利
     */
    public static String REQUEST_zz5 = "/find/yesterdayProfitList";

    /**
     * 领取晋级奖励
     */
    public static String REQUEST_zz6 = "/member/drawPromotionAward";

    /**
     * 领取每日加奖
     */
    public static String REQUEST_zz7 = "/member/drawCodeAmountAward";

    /**
     * 领取每日加奖
     */
    public static String REQUEST_l13 = "/member/infoShow";

    /**
     * 服务体验
     */
    public static String REQUEST_l14 = "/recharge/serviceExperience";

    /**
     * 帮助中心
     */
    public static String REQUEST_hp01 = "/prompt/promptHelp";

    /**
     * 邀请人
     */
    public static String REQUEST_yqr = "/member/getReferNameByDomain";


    /**
     * 时时彩玩法列表 (pc-时时列表接口优化)
     */
    public static String REQUEST_wt8 = "/sscai/getgroupTwoAll.json";


    /**
     * 时时彩玩法列表 (moblie-时时列表接口优化)
     */
    public static String REQUEST_wt9 = "/sscai/getgroupTwoWXAll";


    /**
     * 11选5玩法列表 (moblie-11选5列表接口优化)
     */
    public static String REQUEST_wt10 = "/xuanwu/getgroupAll";


    /**
     * 时时彩官方玩法列表
     */
    public static String REQUEST_wt11 = "/newPlay/getRule.json";




    /**
     * 修改邀请码信息
     */
    public static String REQUEST_wt30 = "/member/updateCode.json";




    /**
     * 手机端下级报表
     */
    public static String REQUEST_wt31 = "/member/getMobileTeamList.json";



    /**
     * 手机端下级报表
     */
    public static String REQUEST_wt32 = "/member/agentReportSimple.json";



    //-------------------------------------------------电影接口-------------------------------------------------
	    /*
    首页点击专题跳转全部频道
    id  专题id
    selectType  排序规则  1 综合  2最多播放  3最近更新  4 最多播放
    page  分页
    pageSize  数据个数
    expandId  拓展分类id  (可不传)
      */

    public static String TEST  = "movie/newMoviesWithCategory";
    public static String ALL_CHANNEL="classification/getClassificationInfo";

    /*
    首页视频
     */
    public static String HOME_MOVIE="movie/newMoviesWithCategory";

    /*
    系统参数
     */
    public static String MOVIE_SYS_PARAMETER="sysParameter/1";


    /*
    搜索电影
    keyWord 搜索内容
    page  分页页数
    limit 每页的个数(后台简直无情,又换字段..)
     */
    public static String SEARCH_RESULT="movie/selectOverallMovie";
    /*
    收藏
    movieId
     */
    public static String COLLECT_MOVIE="movieLog/collection";
    /*
    发现
     */
    public static String FIND="movie/movieFind";

    /*
     我的最爱
     pageNo 分页
     pageSize
      */
    public static String MINE_FAVORATE="movieLog/like/list";


	/*
	礼物列表
	 */

    public static String GIFT_LIST ="liveBroadCast/giftList";

    /*
    直播间列表
    keyWord 搜索条件(搜索时需要传)
     */
    public static String LIVEROOM_LIST="liveBroadCast/anchorMemberRoomList";

    public static String SEND_GIFT="liveBroadCast/giveGift";
    /**
     * 活动榜单数据
     */
    public static String HB_PARAMETER="rongcloudHB/rongcloudHBParameter";
    /**
     * 趣约红包 天降红包  专享红包用户在该活动状态
     */
    public static String HB_ACTIVITY_DETAIL="rongcloudHB/userActivityDetail";
    /**
     * 开趣约红包
     */
    public static String HB_OPEN_QUYUE_HB="rongcloudHB/openQuYueHB";
    /**
     * 开专享红包
     */
    public static String HB_OPEN_ZX_HB="rongcloudHB/openZxHB";


    /**
     * 开天降红包
     */
    public static String HB_OPEN_TJ_GHB="liveBroadCast/getHeavenRedPacket";

    /**
     * 天降红包活动预告
     */
    public static String TJ_YUGAO="liveBroadCast/heavenRedPacketTeaser";

    /**
     * 关注列表
     * pageNo
     * pageSize
     * user_id
     */
    public static String MINE_FOLLOW = "liveBroadCast/anchorFollowList";

    /**
     * 关注主播
     * user_id
     * anchorMemberId
     */

    public static String FOLLOW = "liveBroadCast/anchorFollow";

    /**
     * 取消关注主播
     * user_id
     * anchorMemberId
     */
    public static String CANCEL_FOLLOW = "liveBroadCast/anchorCancelFollow";

    /**
     *phone 手机号
     * type /0注册;1忘记密码;2登录;3修改手机/绑定手机
     *
     */
    public static String SEND_PHONE_CODE="sms/sendSms";
    /**
     * 积分详情
     * user_id 用户id（必传） type类型（必传1得到2消费）pageNo （必传）pageSize（必传）
     */
    public static String INTEGRAL_DETAILS="liveBroadCast/memberIntegralDetail";
    /**
     * 上传图片
     */
    public static String UPLOAD_IMG="uploadWS/upload";

    /**
     *推广收益明细
     * user_id
     */
    public static String MAKE_MONEY_RULE="liveBroadCast/yqRedIncome";

    /**
     * 举报主播
     * user_id
     *anchorMemberId
     * content
     */
    public static String REPORT_ANCHOR="liveBroadCast/anchorReport";

    //
    //邀请红包剩余可领取次数
    public static String INVITE_RED_COUNT="rongcloudHB/inviteRedCount";

    //专享红包信息
    public static String ZX_RED_INFO="rongcloudHB/isZXStandard";


    //棋牌游戏列表
    public static String CHESS_LIST="chess/getChessClassList";

    /**
     *     获取棋牌游戏地址
     *     user_id 用户id
     *     game 游戏大类
     *     id 游戏列表的id
     */

    public static String PLAY_CHESS="chess/playChessGame";

    /**
     * 棋牌游戏余额
     * user_id
     */
    public static String CHESS_BALANCE="chess/getChessMemberMoney";

    /**
     * 一键回收所有余额
     * user_id
     */
    public static String RECYCLER_BALANCE="chess/oneClickRecycling";

    /**
     * 余额自动转换
     * user_id
     */
    public static String AUTO_CHANGE_QUOTA="chess/autoConvert";

    /**
     * 开元充值
     * user_id
     * game
     * price
     */
    public static String CHESS_RECHARGE="chess/recharge";

    /**
     * 开元提现
     * user_id
     * game
     * price
     */
    public static String CHESS_WITHDRAW="chess/withdrawal"  ;


    /**
     * 游戏报表
     * user_id 用户id
     * game 空用户游戏报表;0用户彩票游戏报表;50用户棋牌游戏报表
     * startDate  开始时间(format格式:2020-04-26)
     * endDate   结束时间 (format格式:2020-04-26)
     */
    public static String GAME_REPORT="chess/gameReport";

    /**
     * 棋牌投注记录
     *  user_id
     *  game
     *  startDate(如:2020-04-26 23:59:59)
     *  endDate
     *  pageNo
     *  pageSize
     */
    public static String CHESS_BET_LIST="chess/touZhuListByGame";


    /**
     * 购彩大厅左侧菜单栏列表
     */
    public static String CHESS_GAME_LIST="chess/getChessGameList";

    /**
     *检验用户名是否存在
     * nickname
     */
    public static  String  CHECK_ACCOUNT="member/validateName";

    /**
     * nickname
     * phone
     * phoneSMSCode
     * password
     * 只传nickname获取手机号
     * 只传nickname+phone 获取短信验证码
     * 全部传修改密码
     */
    public static String FORGET_PASSWORD="member/updatePassword";

    public static String FORGET_PASSWORD2="member/updatePassword2";

    /**
     * 获取主播名片
     * user_id 用户id
     * anchorAccount 主播账号
     */
    public static String ANCHOR_BUSINESS="liveBroadCast/openAnchorBusinessCard";

    /**
     * 后台域名列表
     */
    public static String BASE_URL_LIST="app/appRequestDomains";

    /**
     * 版本信息
     */
    public static String GET_VERSION="navigate/getGameClassVersion";

    /**
     直播间信息(刷新播放地址)
     */
    public static String MANAGE_TYPE="liveBroadCast/getLiveRoomByAccount";
    /**
     * 禁言列表
     */
    public static String FORBIDDEM_LIST="rongcloud/chatRoomGagList";
    /**
     * 房管解除禁言
     */
    public static String CANCEL_FORBIDDEM="rongcloud/chatRoomGagDel";
    /**
     * 房管禁言用户
     */
    public static String FORBIDDEN_USER="rongcloud/chatRoomGagAdd";
    /**
     * 直播tab分类
     */
    public static String LIVE_CLASSFY="liveBroadCast/liveCategoryList";

    /**
     * 主播礼物排行
     */
    public static String GIFT_RANK ="liveBroadCast/getUserGiftAmountByAnchorAccount";

    /**
     * 全局排行榜
     */
    public static String ALL_RANK_LIST="liveBroadCast/rankingHb";
    /**
     * 付费
     */
    public static String TOLL_ANCHOR="liveBroadCast/anchorSubscribe";

    /**
     * 切换自动收费
     */
    public static String CHANGE_AUTO_TOLL="liveBroadCast/changeAutoAnchorSubscribe";

    /**
     * 非无限代理 代理中心
     */
    public static String AGENT_CENTER="member/agentReportSimple";

    /**
     * 自己封禁自己
     */
    public static String FORBIDDEN_SELF="rongcloud/blockSelfAdd";

    /**
     * 观看直播时长
     */
    public static String WATCH_MINUTES="liveBroadCast/liveWatchMinutes";

    /**
     * 充值列表
     */
    public static String RECHARGE_LIST="recharge/bankList";

    /**
     * 注册前校验
     *phone
     *refername
     *
     * deviceInfo(只传deviceInfo 获取邀请码)
     */
    public static String CHECK_REGISTER="member/registerCheck";

    /**
     * 超管拉黑主播或用户
     * user_id 用户(超管)id
     * killedUserId 拉黑/取消拉黑的用户id(可为空)
     * killedAnchorAccount 拉黑/取消拉黑的主播(可为空)
     * maintainStatus 0取消拉黑；1拉黑
     */
    public static String BLOCK_USER="rongcloud/memberKillMember";

    /**
     * 拉黑列表
     */
    public static String BLOCK_LIST="rongcloud/memberKillMemberList";

    /**
     * USDT 汇率
     */
    public static String USDT_RATE="member/drawUsdtRate";
    /**
     * 绑定USDT账号
     */
    public static String BIND_USDT="member/bindBankCard";

    /**
     * 转盘抽奖次数
     */
    public static String TURNTABLE_COUNT="memberPoint/getYqzpLastNum";

    /**
     *  我的装备
     */
    public static String MINE_EQUIPMENT="memberPoint/memberMedal";

    /**
     * 穿戴装备
     * status 0未使用/取消穿戴；1使用中/穿戴
     * medalInfoId 勋章id
     */
    public static String USE_MOUNT="memberPoint/handleMemberMedalStatus";

    /**
     * 钻石购买装备
     */
    public static String BUY_MOUNT="memberPoint/buyMedal";

    /**
     * 直播间领钻石
     */
    public static String TAKE_DIAMOND="memberPoint/gainZhuanShiConditions";

    /**
     * 使用中的装备
     */
    public static  String USING_EQUIPMENT="memberPoint/memberMedalInUse";

    /**
     * 用户等级图片列表
     */
    public static String LEVEL_LIST="memberPoint/sysGradeList";

    /**
     * 转盘抽奖
     */
    public static String REQUEST_TURNTABLE="memberPoint/openYqzp";

    /**
     * 签到列表
     */
    public static String SIGN_IN_LIST="memberPoint/signInList";

    /**
     * 签到
     */
    public static String SIGN_IN="memberPoint/signIn";

    /**
     *轮播图数据
     */
    public static String BANNER_DATA="extension/noticeInfoList.json";

    /**
     * 游客登录
     */
    public static String VISITOR_LOGIN="member/visitorLogin";

    /**
     * 游客登录按钮是否显示 (和系统参数中的VISITOR_LOGIN_CLOSE配合判断)
     */
    public static String VISITOR_SHOW="member/validateName";

    /**
     * 游客认证
     */
    public static String VISITOR_BIND="member/visitorBindPhone";
}
