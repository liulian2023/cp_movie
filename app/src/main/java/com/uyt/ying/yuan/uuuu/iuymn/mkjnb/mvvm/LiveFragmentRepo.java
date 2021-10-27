package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.net.common.BaseStringObserver;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.PackType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BaseCpEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KJCountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LastLotteryEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MemberMoney;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RefreshUrlEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserTitleEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ZjEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ActivityRankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ChatUserEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.HBStatusEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.PackEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DeviceUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SystemUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TimerUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.VersionUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum.MARKSIX;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum.valueOf;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils.getApiHost;

public class LiveFragmentRepo extends BaseRepository {
    /**
     * 请求礼物数据
     */
/*    public void getGiftData(CallBack callBack) {
       *//* GiftEntity giftEntity = new GiftEntity();
        List<GiftEntity.DataBean> giftList = new ArrayList<>();
        for (int i =0;i<32;i++){
            GiftEntity.DataBean bean = new GiftEntity.DataBean();
            if (i==2){
                bean.setGz(true);
            }
            bean.setName(Utils.getString(R.string.爱心飞吻));
            bean.setPrice(i);
            bean.setImage_url("http://www.gx8899.com/uploads/allimg/190526/3-1Z52611122D62.jpg");
            giftList.add(bean);
        }
        giftEntity.setData(giftList);
        callBack.Success(giftEntity);*//*
        Observable<Response<ResponseBody>> observable = HttpApiImpl.getInstance1()
                .getGiftList();
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                Utils.initEntity(result, GiftEntity.class, callBack);
            }

            @Override
            public void onFail(String msg) {
                callBack.Faild(msg);
            }
        });
//        HttpApiImpl.getInstance1().getGiftWithCache()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Reply<Response<ResponseBody>>>() {
//                    @Override
//                    public void accept(Reply<Response<ResponseBody>> responseReply) throws Exception {
//                        LogUtils.e(responseReply.toString());
//                    }
//                });
    }*/

    /**
     * 请求活动榜单数据
     *
     * @param callBack
     */
    public void getActivityRankData(CallBack callBack) {
        // 趣约红包 天降红包  专享红包
        Observable<Response<ResponseBody>> observable = HttpApiImpl.getInstance1()
                .getActivityRank();
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {

            @Override
            public void onSuccess(String result, Headers headers) {
                LogUtils.e(result);
                String dataResult = CPDataParseUtils.ParseHeaderResult(result, headers);
                RongcloudHBParameter hbParameter = JSONObject.parseObject(dataResult, RongcloudHBParameter.class);
                ActivityRankEntity rankEntity = new ActivityRankEntity();
                List<ActivityRankEntity.DataBean> rankList = new ArrayList<>();
                RongcloudHBParameter.RongcloudHBParameterBean parameter = hbParameter.getRongcloudHBParameter();
                //switch == 1  开关打开
                int quYueHBSwitch = parameter.getQuYueHBSwitch();
                int tjHBSwitch = parameter.getTjHBSwitch();
                int zxHBSwitch = parameter.getZxHBSwitch();

                if (quYueHBSwitch == 1) {
                    //趣约
                    ActivityRankEntity.DataBean dataBean = new ActivityRankEntity.DataBean();
                    dataBean.setSwitch(1);
                    dataBean.setImageResId(R.drawable.ic_activity_qy);
                    dataBean.setActivityType(PackType.QY.getValue());
                    dataBean.setContent(hbParameter);
                    rankList.add(dataBean);
                }
                if (tjHBSwitch == 1) {
                    //天降
                    ActivityRankEntity.DataBean dataBean = new ActivityRankEntity.DataBean();
                    dataBean.setSwitch(1);
                    dataBean.setImageResId(R.drawable.ic_activity_tj);
                    dataBean.setActivityType(PackType.TJ.getValue());
                    dataBean.setContent(hbParameter);
                    rankList.add(dataBean);
                }
                if (zxHBSwitch == 1) {
                    //专享
                    ActivityRankEntity.DataBean dataBean = new ActivityRankEntity.DataBean();
                    dataBean.setSwitch(1);
                    dataBean = new ActivityRankEntity.DataBean();
                    dataBean.setImageResId(R.drawable.ic_activity_zx);
                    dataBean.setActivityType(PackType.ZX.getValue());
                    dataBean.setContent(hbParameter);
                    rankList.add(dataBean);
                }
                rankEntity.setData(rankList);
                callBack.Success(rankEntity);
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
                callBack.Faild(msg);
            }
        });


//        Utils.docking(new HashMap<>(), RequestUtil.HB_PARAMETER, 0, new DockingUtil.ILoaderListener() {
//            @Override
//            public void success(String content) {
//                RongcloudHBParameter hbParameter = JSONObject.parseObject(content, RongcloudHBParameter.class);
//                LogUtils.e("getActivityRankData>onSuccess " + content);
//                ActivityRankEntity rankEntity = new ActivityRankEntity();
//                List<ActivityRankEntity.DataBean> rankList = new ArrayList<>();
//                RongcloudHBParameter.RongcloudHBParameterBean parameter = hbParameter.getRongcloudHBParameter();
//                int quYueHBSwitch = parameter.getQuYueHBSwitch();
//                int tjHBSwitch = parameter.getTjHBSwitch();
//                int zxHBSwitch = parameter.getZxHBSwitch();
//                ActivityRankEntity.DataBean dataBean = new ActivityRankEntity.DataBean();
//                if (quYueHBSwitch == 0) {
//                    //趣约
//                    dataBean.setImageResId(R.drawable.ic_activity_qy);
//                    dataBean.setActivityType(1);
//                    dataBean.setContent(hbParameter);
//                    rankList.add(dataBean);
//                }
//                if (tjHBSwitch == 0) {
//                    //天降
//                    dataBean = new ActivityRankEntity.DataBean();
//                    dataBean.setImageResId(R.drawable.ic_activity_tj);
//                    dataBean.setActivityType(2);
//                    dataBean.setContent(hbParameter);
//                    rankList.add(dataBean);
//                }
//                if (zxHBSwitch == 0) {
//                    //专享
//                    dataBean = new ActivityRankEntity.DataBean();
//                    dataBean.setImageResId(R.drawable.ic_activity_zx);
//                    dataBean.setActivityType(3);
//                    dataBean.setContent(hbParameter);
//                    rankList.add(dataBean);
//                }
//
//
//                rankEntity.setData(rankList);
//                Utils.setRongcloudHBParameter(hbParameter);
//                callBack.Success(rankEntity);
//            }
//
//            @Override
//            public void failed(MessageHead messageHead) {
//                LogUtils.e(messageHead.getInfo());
//                callBack.Faild(messageHead.getInfo());
//            }
//        });

    }

    public void getHbStatus(PackType packType,CallBack callBack){
        Observable<Response<ResponseBody>> observable = HttpApiImpl.getInstance1()
                .getHbStatus(packType.getValue());
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String dataResult = CPDataParseUtils.ParseHeaderResult(result,headers);
                Gson gson = new GsonBuilder().serializeNulls().create();
                HBStatusEntity hbStatusEntity = gson.fromJson(dataResult,HBStatusEntity.class);
                callBack.Success(hbStatusEntity);
            }

            @Override
            public void onFail(String msg) {
                callBack.Faild(msg);
            }
        });
    }

    /**
     * 请求 红包信息
     *
     * @param packId
     * @param userId
     * @param callBack
     */
    public void getPackInfo(int packId, int userId, CallBack callBack) {
        PackEntity packEntity = new PackEntity();
        PackEntity.DataBean dataBean = new PackEntity.DataBean();
        dataBean.setPackId(packId);
        dataBean.setLock(true);
        dataBean.setMoney("123456");
        dataBean.setMsg("xxoo");
        packEntity.setData(dataBean);

        callBack.Success(packEntity);
    }

    /**
     * 获取聊天用户信息
     *
     * @param callBack
     */
    public void getChatUserInfo(CallBack callBack) {
//        ChatUserEntity chatUserEntity = new ChatUserEntity();
//        List<ChatUserEntity.DataBean> dataList = new ArrayList<>();
//        //头像url数据(逗号拼接的)
//        String str = SharePreferencesUtil.getString(MyApplication.getInstance(), "titleImageList", "");
//        String[] split = (str + "").split(",");
//        Random random = new Random();
//        int length = split.length > 4 ? 4 : split.length;
//        for (int i = 0; i < length; i++) {
//            ChatUserEntity.DataBean bean = new ChatUserEntity.DataBean();
//            bean.setSex(Utils.getString(R.string.男));
//            bean.setName(Utils.getString(R.string.任我行));
//            bean.setAge(4);
//            if (split != null && i < length) {
//                String avatar = split[random.nextInt(split.length)];
//                bean.setAvatar(avatar);
//            }
//            dataList.add(bean);
//        }
//        chatUserEntity.setData(dataList);
//        chatUserEntity.setTotal(30020);
//        callBack.Success(chatUserEntity);
        UserTitleEntity userTitleEntity = UserTitleEntity.getInstance();
        String jsonStr = userTitleEntity.getJsonStr();
        if(StringMyUtil.isEmptyString(jsonStr)){
//            Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
            Observable<Response<ResponseBody>> observable = new HttpApiImpl(getApiHost())
                    .getChatUserInfo();
            addSubscription(observable, new BaseStringObserver<ResponseBody>() {

                @Override
                public void onSuccess(String result, Headers headers) {
                    LogUtils.e("onSuccess " + result);
                    //   Utils.initEntity(result, KJCountDown.class, callBack);
                    String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    ChatUserEntity chatUserEntity = gson.fromJson(resultData,ChatUserEntity.class);
                    callBack.Success(chatUserEntity);
                    userTitleEntity.setJsonStr(result);
                }

                @Override
                public void onFail(String msg) {
                    callBack.Faild(msg);
                }
            });
        }else {
            String resultData = CPDataParseUtils.ParseHeaderResult(jsonStr, null);
            Gson gson = new GsonBuilder().serializeNulls().create();
            ChatUserEntity chatUserEntity = gson.fromJson(resultData,ChatUserEntity.class);
            callBack.Success(chatUserEntity);
        }

    }

    /**
     * 倒计时请求
     *
     * @param game     彩种
     * @param typeId
     * @param callBack
     */
    public void getCountDown(int game, int typeId, CallBack callBack) {
        Context mContext = MyApplication.getInstance();
        String countdown_url = "";
        Resources res = mContext.getResources();
        String[] countdowns = res.getStringArray(R.array.countdown_cpmovie);
        if (game > 0 && game <= countdowns.length) {
            countdown_url = countdowns[game - 1];
        }


//        Observable<Response<ResponseBody>> countDownObservable = new HttpApiImpl(API_HOST1)
        Observable<Response<ResponseBody>> countDownObservable = new HttpApiImpl( getApiHost())
                .getCountDown(countdown_url, typeId);
        addSubscription(countDownObservable, new BaseStringObserver<ResponseBody>() {

            @Override
            public void onSuccess(String result, Headers headers) {
                LogUtils.e("onSuccess " + result);
                //   Utils.initEntity(result, KJCountDown.class, callBack);
                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                Gson gson = new GsonBuilder().serializeNulls().create();
                KJCountDown kjCountDown = gson.fromJson(resultData, KJCountDown.class);
                callBack.Success(kjCountDown);
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
                callBack.Faild(msg);
            }
        });
    }


    public void getLastLottery(int game, int typeId, CallBack callBack) {
        Context mContext = MyApplication.getInstance();
        String lastLottery_url = "";
        Resources res = mContext.getResources();
        String[] lastLotterys = res.getStringArray(R.array.lastLottery_cpmovie);
        if (game > 0 && game <= lastLotterys.length) {
            lastLottery_url = lastLotterys[game - 1];
        }
//        Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
        Observable<Response<ResponseBody>> observable = new HttpApiImpl(getApiHost())
                .getLastLottery(lastLottery_url, typeId);
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {

                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);

                JSONArray jsonArray = null;
                /**
                 * 对彩种进行判断
                 */
                switch (valueOf(game)) {
                    case KUAISAN:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("gameLotterylist"));
                        break;
                    case SSC:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("sscaiLotterylist"));
                        break;
                    case RACE:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("raceLotterylist"));
                        break;
                    case MARKSIX:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("marksixLotterylist"));
                        break;
                    case DANDAN:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("danLotterylist"));
                        break;
                    case HAPPY8:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("happyLotterylist"));
                        break;
                    case LUCKFARM:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("farmLotterylist"));
                        break;
                    case HAPPY10:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("happytenLotterylist"));
                        break;
                    case XUANWU:
                        jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("xuanwuLotterylist"));
                        break;
                }
                if (jsonArray != null && jsonArray.size() > 0) {
                    JSONObject lotteryJsonObject = jsonArray.getJSONObject(0);
                    String lotteryNo = lotteryJsonObject.getString("lotteryNo");
                    String lotteryqishu = lotteryJsonObject.getString("lotteryqishu");
                    List<String> NoList = Arrays.asList(lotteryNo.split(","));
                    List<String> AnimalList = null;
                    List<String> ColorList = null;
                    /**
                     * 六合彩特有的 生肖AnimalList 颜色ColorList
                     */
                    if (valueOf(game) == MARKSIX) {
                        AnimalList = Arrays.asList(lotteryJsonObject.getString("animal").split(","));
                        ColorList = Arrays.asList(lotteryJsonObject.getString("color").split(","));
                    }
                    /**
                     * entity 赋值
                     */
                    LastLotteryEntity lastLotteryEntity = new LastLotteryEntity();
                    lastLotteryEntity.setGame(game);
                    lastLotteryEntity.setLotteryNo(lotteryNo);
                    lastLotteryEntity.setLotteryqishu(lotteryqishu);
                    lastLotteryEntity.setNoList(NoList);
                    lastLotteryEntity.setAnimalList(AnimalList);
                    lastLotteryEntity.setColorList(ColorList);
                    callBack.Success(lastLotteryEntity);
                }
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
                callBack.Faild(msg);
            }
        });

    }


    /**
     * 请求彩种数据
     */
    public void getGameRuleData(int game, int type_id, CallBack callBack) {
/*        Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
                .getGameRuleData(game, type_id);
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                GameRuleContext gameRuleContext = new GameRuleContext();
                Gson gson = new GsonBuilder().serializeNulls().create();
                BaseCpEntity baseCpEntity = gson.fromJson(resultData, BaseCpEntity.class);
                if (baseCpEntity.getStatus().equals("success")) {
                    BetPopAllModel betPopAllModel = gameRuleContext.parseBetPopAllData(resultData, game);
                    //默认请求的数据tab 0 选中
                    betPopAllModel.getBetPopTabModelList().get(0).setSelect(true);
                    callBack.Success(betPopAllModel);
                } else {
                    callBack.Faild(baseCpEntity.getMessage());
                }
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
            }
        });*/


        Context mContext = MyApplication.getInstance();
        String req_url = "";
        Resources res = mContext.getResources();
        String[] gameRules = res.getStringArray(R.array.getgroup);
        if (game <= gameRules.length) {
            req_url = gameRules[game - 1];
        }
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + game+type_id, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> dataMap = new HashMap<>();
            dataMap.put("game", game);
            dataMap.put("type_id", type_id);
            putCommonParams(dataMap);
//        Map<String,Object> aaa = JsonUtil.getDefReqMap(dataMap);
            Utils.docking(dataMap, req_url, -1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LOTTERY_RULE +  game+type_id,content);
                    handRuleJson(content, game, callBack);
                }

                @Override
                public void failed(MessageHead messageHead) {
                    LogUtils.e(messageHead.getInfo());
                }
            });
        }else {
            handRuleJson(jsonStr, game, callBack);
        }


    }

    private void handRuleJson(String content, int game, CallBack callBack) {
        GameRuleContext gameRuleContext = new GameRuleContext();
        Gson gson = new GsonBuilder().serializeNulls().create();
        BaseCpEntity baseCpEntity = gson.fromJson(content, BaseCpEntity.class);
        if (baseCpEntity.getStatus().equals("success")) {
            BetPopAllModel betPopAllModel = gameRuleContext.parseBetPopAllData(content, game);
            //默认请求的数据tab 0 选中
            betPopAllModel.getBetPopTabModelList().get(0).setSelect(true);
            callBack.Success(betPopAllModel);
        } else {
            callBack.Faild(baseCpEntity.getMessage());
        }
    }

    /**
     * 请求用户资金
     */
    public void getAmount(CallBack callBack) {
//        Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
        Observable<Response<ResponseBody>> observable = new HttpApiImpl(getApiHost())
                .getAmount();
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                Gson gson = new GsonBuilder().serializeNulls().create();
                MemberMoney memberMoney = gson.fromJson(resultData, MemberMoney.class);
                callBack.Success(memberMoney);
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
                callBack.Success(msg);
            }
        });
    }
    /**
     * 请求用户资金
     */
    public void getIsZj(CallBack callBack) {
//        Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
        Observable<Response<ResponseBody>> observable = new HttpApiImpl(getApiHost())
                .getIsZj();
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                Gson gson = new GsonBuilder().serializeNulls().create();
                ZjEntity zjEntity = gson.fromJson(resultData, ZjEntity.class);
                callBack.Success(zjEntity);
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
                callBack.Success(msg);
            }
        });
    }
    /**
     * 请求投注接口
     */
    public void getBet(int game, HashMap reqMap, Activity activity, Fragment fragment, CallBack callBack) {
//        Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
    /*    Observable<Response<ResponseBody>> observable = new HttpApiImpl(getApiHost())
                .getBet(game, reqMap,activity,fragment);
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {

            @Override
            public void onSuccess(String result, Headers headers) {
                LogUtils.e(result);
                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                LogUtils.e("getBet:" + resultData);
                Gson gson = new GsonBuilder().serializeNulls().create();
                BaseCpEntity baseCpEntity = gson.fromJson(resultData, BaseCpEntity.class);
                callBack.Success(baseCpEntity);
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
                callBack.Faild(msg);
            }
        });*/

        String curtime = TimerUtils.getTimeStyleTwo();
        reqMap.put("curtime", curtime);

        String bet_url = "";
        Resources res = MyApplication.getInstance().getResources();
        String[] gameRules = res.getStringArray(R.array.cpmovie_touzhu);
        if (game <= gameRules.length) {
            bet_url = gameRules[game - 1];
        }
        HttpApiUtils.CpRequest(activity, fragment, bet_url, reqMap, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                BaseCpEntity baseCpEntity = gson.fromJson(result, BaseCpEntity.class);
                callBack.Success(baseCpEntity);
            }

            @Override
            public void onFailed(String msg) {
                callBack.Faild(msg);
            }
        });
    }

    /**
     * 刷新直播间地址
     */
    public void RefreshRoomUrl(String anchorAccount, String remoteLiveManagementId,String liveRoomId,CallBack callBack) {
//        Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
        Observable<Response<ResponseBody>> observable = new HttpApiImpl(getApiHost())
                .RefreshRoomUrl(anchorAccount,remoteLiveManagementId,liveRoomId);
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                LogUtils.e(result);
                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                RefreshUrlEntity refreshUrlEntity = JSONObject.parseObject(resultData, RefreshUrlEntity.class);
                if (refreshUrlEntity == null) {
                    /**
                     * 返回数据为空   手动设置主播下播
                     */
                    refreshUrlEntity = new RefreshUrlEntity();
                    refreshUrlEntity.setIsLiving(0);
                    refreshUrlEntity.setStatus("success");
                }
                if (refreshUrlEntity.getStatus().equals("success")) {
                    callBack.Success(refreshUrlEntity);
                } else {
                    callBack.Faild(refreshUrlEntity.getMessage());
                }
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
                callBack.Faild(msg);
            }
        });
    }


    /**
     * 请求NavigateList
     *
     * @param callBack
     */
    public void getNavigateList(CallBack callBack) {
//        Observable<Response<ResponseBody>> observable = new HttpApiImpl(API_HOST1)
        Observable<Response<ResponseBody>> observable = new HttpApiImpl(getApiHost())
                .getNavigateList();
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String dataResult = CPDataParseUtils.ParseHeaderResult(result, headers);
                if (!TextUtils.isEmpty(dataResult)) {
                    callBack.Success(dataResult);
                } else {
                    callBack.Faild(Utils.getString(R.string.初始化失败));
                }

            }

            @Override
            public void onFail(String msg) {
                callBack.Faild(msg);
            }
        });
    }

    private  void putCommonParams(HashMap<String, Object> data) {
//        String domain = SharePreferencesUtil.getString(MyApplication.getInstance(), "domain", "");
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        String domain = sp.getNewBaseUrl();
        domain = StringMyUtil.isEmptyString(domain)? BuildConfig.API_HOST1:domain;
        String token = SharePreferencesUtil.getString(MyApplication.getInstance(), "token", "");
        Long user_id1 = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
        if(!data.containsKey("loginType")){
            data.put("loginType",1);
        }

        if (!StringMyUtil.isEmptyString(domain) && !data.containsKey("domain")) {
            data.put("domain", domain);
        }
        data.put("source", 2);
        if (!StringMyUtil.isEmptyString(token) && !data.containsKey("token")) {
            data.put("token", token);
            data.put("tokenInfo", token);
        }
        if (user_id1 != 0l) {
            data.put("user_id", user_id1);
        }
        if(!data.containsKey("deviceNumber")){
            data.put("deviceNumber", AESUtil.encrypt(DeviceUtils.getUniqueId(MyApplication.getInstance())));//设备号
        }
        if(!data.containsKey("appVersionName")){
            data.put("appVersionName", VersionUtils.getAppVersionName(MyApplication.getInstance()));
        }
        if(!data.containsKey("appVersionName")){
            data.put("appVersionName", SystemUtil.getSystemVersion());
        }
        if(!data.containsKey("mobileBrandModels")) {
            data.put("mobileBrandModels", SystemUtil.getSystemModel());
        }
        if(!data.containsKey("mobileSystemVersion")){
            data.put("mobileSystemVersion", SystemUtil.getSystemVersion());
        }
        if(!data.containsKey("mobileBrand")){
            data.put("mobileBrand",SystemUtil.getDeviceBrand());
        }
    }


}
