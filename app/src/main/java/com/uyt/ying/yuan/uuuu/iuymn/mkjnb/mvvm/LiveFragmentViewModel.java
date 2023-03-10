package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveShareBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.CPEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveLotteryEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.OpenNoMulBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.PackType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BaseCpEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinContentModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GiftNumEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KJCountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LastLotteryEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryListItem;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManagerTypeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MemberMoney;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RefreshUrlEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ZjEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ActivityRankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ChatUserEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.GiftEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.HBStatusEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.PackEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.JsonUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget.CusBetJoinOldPop;
import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.droidsonroids.gif.GifImageView;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil.timeMode;


public class LiveFragmentViewModel extends BaseViewModel<LiveFragmentRepo> {

    private String TAG = "LiveFragmentViewModel";

    public LiveFragmentViewModel(@NonNull Application application) {
        super(application);
        EventBus.getDefault().register(this);
    }

    //??????????????????????????? ??????1 ??????2 ?????????3 ??????4
    public MutableLiveData<Long> CPID;
    public MutableLiveData<Integer> requestFailCount;
    private MutableLiveData<String> ROOMID;
    private MutableLiveData<CPEntity> cpLiveData;
    private MutableLiveData<RefreshUrlEntity> refreshRoomLiveData;
    private MutableLiveData<ManagerTypeEntity> anchorManageData;
    private MutableLiveData<LiveLotteryEntity> liveLotteryData;
    private MutableLiveData<Boolean> IsShowLiveData;
    private MutableLiveData<String> countDownLiveData;
    private MutableLiveData<List<ChatUserEntity.MemberHeadPortraitListBean>> chatUserLiveData;
    private MutableLiveData<PackEntity> packLiveData;
    private MutableLiveData<ActivityRankEntity> rankLiveData;
    private MutableLiveData<HBStatusEntity> hbStatusLiveData;
    private MutableLiveData<List<GiftEntity.GiftListsBean>> giftLiveData;
    private MutableLiveData<List<GiftNumEntity.GiftNumListBean>> giftNumLiveData;

    /**
     * CusLotteryListPop ??????????????? ??????
     */
    private MutableLiveData<List<LotteryListItem>> playLotteryLiveData;
    private MutableLiveData<HbGameClassModel> hbGameLiveData;
    /**
     * betPop ????????? livedata  ??????
     */
    private MutableLiveData<BetPopAllModel> betPopAllLiveData;
    private MutableLiveData<MemberMoney> amountLiveData;
    private Disposable Amount_disposable, dismiss_disable;
    /**
     * betJoin ????????? livedata ????????????  ???????????????
     */
    private MutableLiveData<BetJoinAllModel> betJoinAllLiveData;
    private SharedPreferenceHelperImpl mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();

    private LiveLotteryEntity liveLotteryEntity = new LiveLotteryEntity();

    private long endtime, tqtime, sjcha, timeCount;
    private String lastqishu;//????????????
    private String qishu;//??????
    private int game;
    int typeId;
    private String typename;
    //????????????
    private boolean isWaitopen = true;
    private NavigateEntity.GameClassListBean gameClassListBean;
    private Context mContext;
    private boolean countDownStatus =true;
    private boolean isShowingAmount=false;
    public int countDownFailCount=0;
    /**
     * ?????????CPID ???????????????game typeId
     *
     * @param cpId
     */
    public void initCpId(long cpId, String roomId) {
        if (CPID == null) {
            CPID = new MutableLiveData<>();
        }
        if (ROOMID == null) {
            ROOMID = new MutableLiveData<>();
        }
        CPID.postValue(cpId);
        ROOMID.postValue(roomId);
        isWaitopen = true;
        liveLotteryData = getOpenLottery();
        cpLiveData = getCpLiveData();
        mContext = MyApplication.getInstance();
        String navigateList = SharePreferencesUtil.getString(mContext, "navigateList", "");
        Gson gson = new GsonBuilder().serializeNulls().create();
        NavigateEntity navigateEntity = gson.fromJson(navigateList, NavigateEntity.class);
        if (TextUtils.isEmpty(navigateList) || navigateEntity.getGameClassList() == null) {
            mRepository.getNavigateList(new CallBack() {
                @Override
                public void Success(Object object) {
                    SharePreferencesUtil.putString(MyApplication.getInstance(), "navigateList", (String) object);
                    NavigateEntity navigateEntity = gson.fromJson((String) object, NavigateEntity.class);
                    if (navigateEntity.getGameClassList() != null) {
                        parseNaviData(cpId, navigateEntity);
                    }
                }

                @Override
                public void Faild(String msg) {
                    ToastUtil.showToast(Utils.getString(R.string.?????????????????????????????????????????????????????????));
                }
            });
        } else {
            parseNaviData(cpId, navigateEntity);
        }

    }

    public MutableLiveData<Boolean> getIsShowLiveData() {
        if (IsShowLiveData == null) {
            IsShowLiveData = new MutableLiveData<>();
        }
        return IsShowLiveData;
    }
    private void  selectorId(long id) {
        HbGameClassModel hbGameClassModel = HbGameClassModel.getInstance();
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        for (int i = 0; i < idList.size(); i++) {
            if(idList.get(i).equals(id+"")){
                liveLotteryEntity.setXian(true);
                break;
            }
        }
    }
    /**
     * ????????????????????????  ?????????CpId ?????? ??????????????????
     *
     * @param cpId
     * @param navigateEntity
     */
    private void parseNaviData(long cpId, NavigateEntity navigateEntity) {
        for (int i = 0; i < navigateEntity.getGameClassList().size(); i++) {
            if (cpId == navigateEntity.getGameClassList().get(i).getId()) {
                game = navigateEntity.getGameClassList().get(i).getGame();
                typeId = navigateEntity.getGameClassList().get(i).getType_id();
                typename = navigateEntity.getGameClassList().get(i).getTypename();
                String image_url = navigateEntity.getGameClassList().get(i).getImage();
                gameClassListBean = navigateEntity.getGameClassList().get(i);
                liveLotteryEntity.setCpId(cpId);
                selectorId(cpId);
                liveLotteryEntity.setGame(game);
                liveLotteryEntity.setTypeId(typeId);
                liveLotteryEntity.setTypeName(typename);
                liveLotteryEntity.setImage_url(image_url);
                liveLotteryData.postValue(liveLotteryEntity);
                /**
                 * cpEntity ?????? ??????
                 */
                CPEntity cpEntity = cpLiveData.getValue();
                if (cpEntity == null) {
                    cpEntity = new CPEntity();
                }
                cpEntity.setGame(game);
                cpEntity.setTypeId(typeId);
                cpEntity.setTypeName(typename);
                cpLiveData.postValue(cpEntity);
            }
        }
        if (timerRunnable != null) {
            handler.removeCallbacks(timerRunnable);
        }
        handler.postDelayed(timerRunnable, 0);
        reqCountDownData(null);
        reqBetPopData();
        isZjTimer();
//        getAmount();
    }

    /**
     * ??????CpLiveData
     *
     * @return
     */
    public MutableLiveData<CPEntity> getCpLiveData() {
        if (cpLiveData == null) {
            cpLiveData = new MutableLiveData<>();
        }
        return cpLiveData;
    }
    public MutableLiveData<Integer> getRequestFailCount() {
        if (requestFailCount == null) {
            requestFailCount = new MutableLiveData<>();
        }
        return requestFailCount;
    }

    /**
     * ??????refreshroomUrl  livedata
     *
     * @return
     */
    public MutableLiveData<RefreshUrlEntity> getRefreshRoomLiveData() {
        if (refreshRoomLiveData == null) {
            refreshRoomLiveData = new MutableLiveData<>();
        }
        return refreshRoomLiveData;
    }    /**
     * ???????????????????????????
     *
     * @return
     */
    public MutableLiveData<ManagerTypeEntity> getAnchorManageData() {
        if (anchorManageData == null) {
            anchorManageData = new MutableLiveData<>();
        }
        return anchorManageData;
    }
    /**
     * ???????????? ???????????? LotteryPop ???????????? ????????? sp????????? ??????
     *
     * @return
     */
    public MutableLiveData<List<LotteryListItem>> getLotteryPopData() {
        if (playLotteryLiveData == null) {
            playLotteryLiveData = new MutableLiveData<>();
            Context mContext = MyApplication.getInstance();
            List<LotteryListItem> playLotteryList = new ArrayList<>();
            String navigateList = SharePreferencesUtil.getString(mContext, "navigateList", "");
            Gson gson = new GsonBuilder().serializeNulls().create();
            NavigateEntity navigateEntity = gson.fromJson(navigateList, NavigateEntity.class);
            boolean haveLiveCp = false;
            for (int i = 0; i < navigateEntity.getGameClassList().size(); i++) {
                NavigateEntity.GameClassListBean bean = navigateEntity.getGameClassList().get(i);
//                if (bean.getIsHot() == 1) {
                    String finalImgUrl = Utils.CPImageUrlCheck(mContext, bean.getImage());
                    String count = bean.getRemark();//???????????????
                    int lotteryGame = bean.getGame();//????????????
                    String lotteryTypeName = bean.getTypename();//????????????
                    int lotteryTypeId = bean.getType_id();//??????typeid
                    String isStart = "" + bean.getIsStart();//??????????????????????????? 0??????????????? 1?????????
                    String isopenOffice = "" + bean.getIsopenOffice();//??????????????????????????? 0?????????
                    long id = bean.getId();//??????????????????????????????(???????????? ????????? ?????????)
                    Integer iscustom = bean.getIscustom();
                    LotteryListItem lotteryListItem = new LotteryListItem();

                    lotteryListItem.setCpId(id);
                    lotteryListItem.setGame(lotteryGame);
                    lotteryListItem.setType_id(lotteryTypeId);
                    lotteryListItem.setName(lotteryTypeName);
                    lotteryListItem.setImgUrl(finalImgUrl);
                    lotteryListItem.setLive(false);
 /*                   if (playLotteryList.size() < 7) {
                        if (CPID.getValue() == id) {
                            lotteryListItem.setLive(true);
                            playLotteryList.add(0, lotteryListItem);
                        } else {
                            playLotteryList.add(lotteryListItem);
                        }
                    }*/
                            if(CPID.getValue() == id){
                                //???????????????,???????????????cp????????????7??????????????????,???????????????????????????cp,????????????,??????????????????????????????cp
                                 if(playLotteryList.size()>=7){
                                    playLotteryList.remove(playLotteryList.size()-1);
                                }
                                 if(playLotteryList.size()!=0){

                                 }
                                lotteryListItem.setLive(true);
                                playLotteryList.add(0, lotteryListItem);
                                haveLiveCp=true;
                            }else {
                                //??????????????????cp
                                if(bean.getIsHot()==1){
                                    if(playLotteryList.size()<7){
                                        playLotteryList.add(lotteryListItem);
                                    }
                                }
                            }
                            //?????????????????????cp??????position???0?????????,????????????7?????????
                            if(playLotteryList.size()==7&&haveLiveCp==true){
                                break;
                            }
//                }
            }
            LotteryListItem lotteryListItem = new LotteryListItem();
            lotteryListItem.setCpId(GameTypeEnum.LIVESHOP.getValue());
            lotteryListItem.setGame(GameTypeEnum.LIVESHOP.getValue());
            lotteryListItem.setName(Utils.getString(R.string.????????????));
            lotteryListItem.setLive(false);
            playLotteryList.add(lotteryListItem);
            /**
             * ????????????????????????
             */
            if(hbGameLiveData!=null){
                String idsStr = hbGameLiveData.getValue().getGameIdListStr();
                if (!TextUtils.isEmpty(idsStr)) {
                    List<String> idList = Arrays.asList(idsStr.split(","));
                    for (LotteryListItem item : playLotteryList) {
                        for (String s : idList) {
                            if (s.equals(item.getCpId() + "")) {
                                item.setXian(true);
                                break;
                            }else {
                                item.setXian(false);
                            }
                        }
                    }
                }
            }

            playLotteryLiveData.postValue(playLotteryList);
        }
        return playLotteryLiveData;
    }

    /**
     * ?????? ???????????? LotteryPop By Position
     */
    public void setLotteryPopDataByPosition(int position) {
        MutableLiveData<List<LotteryListItem>> listMutableLiveData =  new MutableLiveData<>();
        List<LotteryListItem> playLotteryList = getLotteryPopData().getValue();
        for (int i = 0; i < playLotteryList.size(); i++) {
            LotteryListItem lotteryListItem = playLotteryList.get(i);
            lotteryListItem.setLive(false);
            if (i == position) {
                playLotteryList.remove(lotteryListItem);
                lotteryListItem.setLive(true);
                playLotteryList.add(0, lotteryListItem);
            }
        }
        playLotteryLiveData.postValue(playLotteryList);
    }

    /**
     * ???????????????????????????
     *
     * @param hbGameClassModel ?????????????????????idList???model
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateXian(HbGameClassModel hbGameClassModel) {
//        LogUtils.e("" + hbGameClassModel);
        if (hbGameLiveData == null) {
            hbGameLiveData = new MutableLiveData<>();
        }
        hbGameLiveData.postValue(hbGameClassModel);

    }

    /**
     * ??????????????????
     */
    public MutableLiveData<LiveLotteryEntity> getOpenLottery() {
        if (liveLotteryData == null) {
            liveLotteryData = new MutableLiveData<>();
        }
        return liveLotteryData;
    }

    public MutableLiveData<String> getCountDownLiveData() {
        if (countDownLiveData == null) {
            countDownLiveData = new MutableLiveData<>();
        }
        return countDownLiveData;
    }

    /**
     * ??????????????????LiveData
     */
    public MutableLiveData<List<ChatUserEntity.MemberHeadPortraitListBean>> getChatUserLiveData() {
        if (chatUserLiveData == null) {
            chatUserLiveData = new MutableLiveData<>();
        }
        return chatUserLiveData;
    }

    /**
     * ????????????LiveData
     */
    public MutableLiveData<PackEntity> getPackLiveData() {
        if (packLiveData == null) {
            packLiveData = new MutableLiveData<>();
        }
        return packLiveData;
    }

    /**
     * ??????????????????
     */
    public MutableLiveData<HBStatusEntity> getHbStatusLiveData() {
        if (hbStatusLiveData == null) {
            hbStatusLiveData = new MutableLiveData<>();
        }
        return hbStatusLiveData;
    }

    /**
     * ?????????????????????
     *
     * @return
     */
    public MutableLiveData<ActivityRankEntity> getRankLiveData() {
        if (rankLiveData == null) {
            rankLiveData = new MutableLiveData<>();
        }
        return rankLiveData;
    }

    /**
     * ??????????????????
     */
    public MutableLiveData<List<GiftEntity.GiftListsBean>> getGiftLiveData() {
        if (giftLiveData == null) {
            giftLiveData = new MutableLiveData<>();
        }
        return giftLiveData;
    }

    /**
     * ???????????????????????? ????????????
     */
    public MutableLiveData<List<GiftNumEntity.GiftNumListBean>> getGiftNumLiveData() {
        if (giftNumLiveData == null) {
            giftNumLiveData = new MutableLiveData<>();
            Gson gson = new GsonBuilder().serializeNulls().create();
            GiftNumEntity giftNumEntity = gson.fromJson(JsonUtil.getJson(MyApplication.getInstance(), "giftnum.json"), GiftNumEntity.class);
            giftNumLiveData.postValue(giftNumEntity.getGiftNumList());
        }
        return giftNumLiveData;
    }

    /**
     * ???????????????????????? by positin
     */
    public void selectGiftNumByPosition(int position) {
        giftNumLiveData = getGiftNumLiveData();
        List<GiftNumEntity.GiftNumListBean> gifNumList = giftNumLiveData.getValue();
        for (int i = 0; i < gifNumList.size(); i++) {
            gifNumList.get(i).setIsSelect(false);
            if (i == position) {
                gifNumList.get(i).setIsSelect(true);
            }
        }
        giftNumLiveData.postValue(gifNumList);
    }


    /**
     * ????????????livedata
     *
     * @return
     */
    public MutableLiveData<MemberMoney> getAmountLiveData() {
        if (amountLiveData == null) {
            amountLiveData = new MutableLiveData<>();
        }
        return amountLiveData;
    }

    /**
     * ??????betPop??????
     *
     * @return
     */
    public MutableLiveData<BetPopAllModel> getBetPopAllData() {
        if (betPopAllLiveData == null) {
            betPopAllLiveData = new MutableLiveData<>();
        }
        return betPopAllLiveData;
    }

    /**
     * ?????? betJoin ???livedata
     */

    public MutableLiveData<BetJoinAllModel> getBetJoinAllLiveData() {
        if (betJoinAllLiveData == null) {
            betJoinAllLiveData = new MutableLiveData<>();
            Gson gson = new GsonBuilder().serializeNulls().create();
            String str_result = mSharedPreferenceHelperImpl.getChouma();
            if (TextUtils.isEmpty(str_result)) {
                str_result = JsonUtil.getJson(MyApplication.getInstance(), "betjoin.json");
            }
            BetJoinAllModel betJoinAllModel = gson.fromJson(str_result, BetJoinAllModel.class);
            for (int i = 0; i < betJoinAllModel.getBetJoinMaModelList().size(); i++) {
                boolean current = betJoinAllModel.getBetJoinMaModelList().get(i).isCurrent();
                if(current){
                    Utils.logE(TAG, "????????????rulePop??????????????? :" +betJoinAllModel.getBetJoinMaModelList().get(i).getDanjia() );
                }
            }
            betJoinAllLiveData.postValue(betJoinAllModel);
        }
        return betJoinAllLiveData;
    }

    /**
     * ????????????????????????
     */
    public void reqChatUsereInfo() {
        mRepository.getChatUserInfo(new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof ChatUserEntity) {
                    ChatUserEntity chatUserEntity = (ChatUserEntity) object;
                    chatUserLiveData.postValue(chatUserEntity.getMemberHeadPortraitList());
                }
            }

            @Override
            public void Faild(String msg) {
                LogUtils.e(msg);
                ToastUtil.showToast(msg);
            }
        });
    }

    /**
     * ????????????Id ??????????????????
     */
    public void reqPackInfo(int packId, int userId) {
        mRepository.getPackInfo(packId, userId, new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof PackEntity) {
                    PackEntity packList = (PackEntity) object;

                }
            }

            @Override
            public void Faild(String msg) {
                LogUtils.e(msg);
                ToastUtil.showToast(msg);
            }
        });

    }

    /**
     * // ???????????? ????????????  ????????????
     */
    public void reqActivityRankData() {
        rankLiveData = getRankLiveData();
        mRepository.getActivityRankData(new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof ActivityRankEntity) {
                    ActivityRankEntity activityRankEntity = (ActivityRankEntity) object;
                    rankLiveData.postValue(activityRankEntity);
                }
            }

            @Override
            public void Faild(String msg) {
                LogUtils.e(msg);
                ToastUtil.showToast(msg);
            }
        });
    }

    public void reqHbStatus(PackType packType) {
        mRepository.getHbStatus(packType, new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof HBStatusEntity) {
                    HBStatusEntity hbStatusEntity = (HBStatusEntity) object;
                    hbStatusLiveData.postValue(hbStatusEntity);
                }
            }

            @Override
            public void Faild(String msg) {
                ToastUtil.showToast(msg);
            }
        });
    }


    /**
     * ??????????????????
     */
 /*   public void reqGiftData() {
        if (giftLiveData == null) {
            giftLiveData = new MutableLiveData<>();
        }
        mRepository.getGiftData(new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof GiftEntity) {
                    GiftEntity giftEntity = (GiftEntity) object;
                    giftLiveData.postValue(giftEntity.getGiftLists());
                }
            }


            @Override
            public void Faild(String msg) {
                LogUtils.e(msg);
                ToastUtils.showToast(msg);
            }
        });
    }*/



    /**
     * ?????????????????????
     */
    public void reqCountDownData(GifImageView gifImageView) {
        countDownStatus=false;
        Context mContext = MyApplication.getInstance();
        int game = this.game;
        int typeId = this.typeId;
        if (game == 0) {
            return;
        }
        //?????????????????????????????????????????????????????????
        if(handler!=null&&countReqRunnable!=null){
            handler.removeCallbacks(countReqRunnable);
        }
        if(gifImageView!=null){
            gifImageView.setVisibility(View.VISIBLE);
        }
        mRepository.getCountDown(game, typeId, new CallBack() {
            @Override
            public void Success(Object object) {
                if(gifImageView!=null){
                    gifImageView.setVisibility(View.GONE);
                }
                countDownFailCount=0;
                requestFailCount.postValue(countDownFailCount);
                if(handler!=null&&countReqRunnable!=null){
                    handler.postDelayed(countReqRunnable,3000);
                }
                if (object instanceof KJCountDown) {
                    KJCountDown kjCountDown = (KJCountDown) object;
                    String str_stop = kjCountDown.getStoptimestr();
                    if (!TextUtils.isEmpty(str_stop)) {
                        try {
                            endtime = Long.parseLong(DateUtil.dateToStamp(str_stop));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    qishu = kjCountDown.getQishu();
                    lastqishu = kjCountDown.getLastqishu();
                    tqtime = (long) kjCountDown.getTqtime() * 1000;
                    sjcha = SharePreferencesUtil.getLong(mContext, "shijiancha", 0l);
                    timeCount = (endtime - tqtime) - (System.currentTimeMillis() - sjcha);
//                    LogUtils.e("timeCount:" + timeCount);
                    /**
                     * ????????????????????? ????????????????????????
                     */
                    LiveLotteryEntity liveLotteryEntity = getOpenLottery().getValue();
                    liveLotteryEntity.setCurrentQishu(qishu+Utils.getString(R.string.???));
                    liveLotteryData.postValue(liveLotteryEntity);
                    countDownStatus=true;
                }
            }

            @Override
            public void Faild(String msg) {
                if(gifImageView!=null){
                    gifImageView.setVisibility(View.GONE);
                }
                countDownFailCount++;
                requestFailCount.postValue(countDownFailCount);
                if(handler!=null&&countReqRunnable!=null) {
                    handler.postDelayed(countReqRunnable, 3000);
                }
                ToastUtil.showToast(msg);
                countDownStatus=true;

            }
        });
        if (isWaitopen) {
            if (openRunnable != null) {
                handler.removeCallbacks(openRunnable);
            }
            handler.postDelayed(openRunnable, 1000);
        } else {
            handler.removeCallbacks(openRunnable);
        }
    }


    /**
     * ????????????????????????
     * game 1 ?????? 3?????? 4 ????????? ??????
     */
    public void reqLastLottery() {
        Context mContext = MyApplication.getInstance();
        int game = this.game;
        int typeId = this.typeId;
        int[] shaizis = Const.getShaziArray(mContext);
        if(handler!=null&&openRunnable!=null){
            handler.removeCallbacks(openRunnable);
        }
        mRepository.getLastLottery(game, typeId, new CallBack() {
            @Override
            public void Success(Object object) {
                countDownFailCount=0;
                requestFailCount.postValue(countDownFailCount);
                if(handler!=null&&openRunnable!=null){
                    handler.postDelayed(openRunnable,5000);
                }
                if (object instanceof LastLotteryEntity) {
                    LastLotteryEntity lastLotteryEntity = (LastLotteryEntity) object;
                    List<OpenNoMulBean> mOpenNoMulList = new ArrayList<>();
                    if (game == GameTypeEnum.KUAISAN.getValue()) {
                        for (String str : lastLotteryEntity.getNoList()) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(String.valueOf(shaizis[Integer.parseInt(str) - 1]));
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(1);
                            mOpenNoMulList.add(openNoMulBean);
                        }
                    } else if (game == GameTypeEnum.RACE.getValue()) {
                        for (String str : lastLotteryEntity.getNoList()) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(str);
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(2);
                            mOpenNoMulList.add(openNoMulBean);
                        }
                    } else if (game == GameTypeEnum.MARKSIX.getValue()) {
                        for (int i = 0; i < lastLotteryEntity.getNoList().size(); i++) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(lastLotteryEntity.getNoList().get(i));
                            openNoMulBean.setAnimal(lastLotteryEntity.getAnimalList().get(i));
                            openNoMulBean.setColor(lastLotteryEntity.getColorList().get(i));
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(3);
                            mOpenNoMulList.add(openNoMulBean);
                        }
                        //??????+???
                        OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                        openNoMulBean.setName("+");
                        openNoMulBean.setGame(game);
                        openNoMulBean.setItemType(3);
                        mOpenNoMulList.add(6,openNoMulBean);
                    } else if(game == GameTypeEnum.DANDAN.getValue()){
                        int heZhi=0;
                        for (int i = 0; i < lastLotteryEntity.getNoList().size(); i++) {
                            String name = lastLotteryEntity.getNoList().get(i);
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(name);
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(5);
                            mOpenNoMulList.add(openNoMulBean);
                            heZhi+=Integer.parseInt(name);
                        }
                        OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                        openNoMulBean.setName("+");
                        openNoMulBean.setGame(game);
                        openNoMulBean.setItemType(5);
                        mOpenNoMulList.add(1,openNoMulBean);
                        mOpenNoMulList.add(3,openNoMulBean);

                        OpenNoMulBean openNoMulBean2 = new OpenNoMulBean();
                        openNoMulBean2.setName("=");
                        openNoMulBean2.setGame(game);
                        openNoMulBean2.setItemType(5);
                        mOpenNoMulList.add(5,openNoMulBean2);

                        OpenNoMulBean openNoMulBeanHeZhi = new OpenNoMulBean();
                        openNoMulBeanHeZhi.setName(heZhi+"");
                        openNoMulBeanHeZhi.setGame(game);
                        openNoMulBeanHeZhi.setItemType(5);
                        mOpenNoMulList.add(6,openNoMulBeanHeZhi);

                    } else {
                        for (String str : lastLotteryEntity.getNoList()) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(str);
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(4);
                            mOpenNoMulList.add(openNoMulBean);
                        }

                    }

                    /**
                     * ???????????????????????????  ???????????????   10??????   ????????????????????????
                     */
                    if (liveLotteryEntity.getOpenMulNoList()!=null)
                    {
                        for (int i =0;i<liveLotteryEntity.getOpenMulNoList().size();i++){
                            for (int j=0;j<mOpenNoMulList.size();j++){
                                if (!liveLotteryEntity.getOpenMulNoList().get(i).getName().equals(mOpenNoMulList.get(j))){
//                                    LogUtils.e(Utils.getString(R.string.??????????????????));
                                    Boolean isShow = getIsShowLiveData().getValue();
                                    isShow = false;
                                    IsShowLiveData.postValue(isShow);
                                    if (dismiss_disable != null && !dismiss_disable.isDisposed()) {
                                        dismiss_disable.dispose();
                                    }
                                    dismiss_disable = Flowable.interval(1, TimeUnit.SECONDS)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .doOnNext(aLong -> {
                                                if (aLong==10){
//                                                    LogUtils.e(Utils.getString(R.string.???????????????????????????)+aLong);
                                                    Boolean b = getIsShowLiveData().getValue();
                                                    b = false;
                                                    IsShowLiveData.postValue(b);
                                                }
                                            })
                                            .subscribe();
                                }
                            }
                        }

                    }

                    liveLotteryEntity.setOpenMulNoList(mOpenNoMulList);
                    liveLotteryEntity.setOpenQishu(lastLotteryEntity.getLotteryqishu() + Utils.getString(R.string.?????????));
                    liveLotteryData.postValue(liveLotteryEntity);

                    /**
                     * ??????????????????????????????
                     */
                    if (!StringMyUtil.isEmptyString(lastqishu)) {
                        String lotteryqishu = lastLotteryEntity.getLotteryqishu();
                        if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu.substring(lotteryqishu.length() - 2))) {
                            isWaitopen = false;
                            LogUtils.e("????????????");
                        } else {
                            if(StringMyUtil.isNotEmpty(qishu)){
                                if (Long.parseLong(qishu) - 1 == Long.parseLong(lotteryqishu)) {
                                    isWaitopen = false;
                                } else {
                                    isWaitopen = true;
                                }
                            }else {
                                isWaitopen = true;
                            }
                        }
                    }
                }
            }

            @Override
            public void Faild(String msg) {
                countDownFailCount++;
                requestFailCount.postValue(countDownFailCount);
                if(handler!=null&&openRunnable!=null){
                    handler.postDelayed(openRunnable,5000);
                }
                LogUtils.e(msg);
                ToastUtil.showToast(msg);
            }
        });
    }


    Handler handler = new Handler();
    /**
     * timer?????????
     */
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            /**
             * ??????timeCount<=0 ??????0ms ???????????? ?????????3???????????????
             */

            if (timeCount <= 1000) {
                isWaitopen = true;
                if (countReqRunnable != null) {
                    handler.removeCallbacks(countReqRunnable);
                }
                if(countDownStatus){
                    handler.postDelayed(countReqRunnable, 0);
                }
            } else {
                /**
                 * ??????timeCount>0   countReqRunnable?????????  ??????countReqRunnable
                 */
                if (countReqRunnable != null) {
                    handler.removeCallbacks(countReqRunnable);
                }
                timeCount = (endtime - tqtime) - (System.currentTimeMillis() - sjcha);
                int mHour = (int) ((timeCount / 1000) / (60 * 60));  //  ???3600 ??????  ????????????
                int mMin = (int) (((timeCount / 1000) % (60 * 60)) / 60);//  ???3600 ????????????60 ??????????????????
                int mSecond = (int) ((timeCount / 1000) % 60); //  ???60 ??????  ??????????????????
                String str_time;
                if (mHour == 0) {
                    str_time = timeMode(mMin) + ":" + timeMode(mSecond);
                } else {
                    str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
                }
                //  str_countdown.postValue(str_time);
                //      liveLotteryEntity.setCurrCountTime(str_time);
                if(timeCount<=1000){
                    str_time=Utils.getString(R.string.?????????);
                }
                countDownLiveData.postValue(str_time);

            }
            handler.postDelayed(timerRunnable, 1000);
        }
    };
    /**
     * ???????????????  ??????5?????????????????????
     */
    Runnable openRunnable = new Runnable() {
        @Override
        public void run() {
            if (isWaitopen&&countDownFailCount<=6) {
                reqLastLottery();
            }
            handler.postDelayed(openRunnable, 5000);
        }
    };
    /**
     * ???????????????0???????????????3?????????????????????   ?????? ????????????????????????0?????????????????????
     */
    Runnable countReqRunnable = new Runnable() {
        @Override
        public void run() {
            if(countDownFailCount>6){
                return;
            }
            reqCountDownData(null);
            handler.postDelayed(countReqRunnable, 3000);
        }
    };

    /**
     * ???????????????????????? betPop
     */
    public void reqBetPopData() {
        int game = this.game;
        int typeId = this.typeId;
        betPopAllLiveData = getBetPopAllData();
        mRepository.getGameRuleData(game, typeId, new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof BetPopAllModel) {
                    BetPopAllModel betPopAllModel = (BetPopAllModel) object;
                    /**
                     * ???????????????????????????????????????  ??? tab?????????????????????????????????
                     */
                    betPopAllModel.getBetPopTabModelList().get(0).setSelect(true);
                    betPopAllLiveData.postValue(betPopAllModel);
                }
            }

            @Override
            public void Faild(String msg) {
                LogUtils.e(msg);
                ToastUtil.showToast(msg);
            }
        });
    }

    /**
     * ??????tab ???????????? ??????tab   ????????????child?????? ????????????
     *
     * @param position
     */
    public void selectTabPosition(int position) {
        BetPopAllModel betPopAllModel = betPopAllLiveData.getValue();
        List<BetPopAllModel.BetPopTabModel> tabList = betPopAllModel.getBetPopTabModelList();
        List<BetPopAllModel.BetPopChildModel> childList = betPopAllModel.getBetPopChildModelList();
        for (int i = 0; i < tabList.size(); i++) {
            tabList.get(i).setSelect(false);
            if (position == i) {
                tabList.get(i).setSelect(true);
            }
        }
        for (int j = 0; j < childList.size(); j++) {
            childList.get(j).setSelect(false);
        }
        betPopAllLiveData.postValue(betPopAllModel);
    }

    /**
     * ?????? child ???????????? betPopAllLiveData
     */
    public void selectChildPosition(int position) {
        /**
         * index ?????????  ??? tab???????????????child??????????????????
         */
        int index = 0;
        BetPopAllModel betPopAllModel = betPopAllLiveData.getValue();
        List<BetPopAllModel.BetPopTabModel> tabList = betPopAllModel.getBetPopTabModelList();
        List<BetPopAllModel.BetPopChildModel> childList = betPopAllModel.getBetPopChildModelList();
        for (int i = 0; i < tabList.size(); i++) {
            if (tabList.get(i).isSelect()) {
                for (int j = 0; j < childList.size(); j++) {
                    if (tabList.get(i).getId() == childList.get(j).getParentId()) {
                        //???????????? ????????????  ?????? ???????????????  ????????? ????????????
                        if (index == position) {
                            if (childList.get(j).isSelect()) {
                                childList.get(j).setSelect(false);
                            } else {
                                childList.get(j).setSelect(true);
                            }
                            break;
                        }
                        index++;
                    }
                }
            }
        }
        betPopAllLiveData.postValue(betPopAllModel);
    }

    /**
     * ????????????child?????? betPopAllLiveData
     */
    public void deleteByChildId(long id) {
        BetPopAllModel betPopAllModel = betPopAllLiveData.getValue();
        List<BetPopAllModel.BetPopTabModel> tabList = betPopAllModel.getBetPopTabModelList();
        List<BetPopAllModel.BetPopChildModel> childList = betPopAllModel.getBetPopChildModelList();

        for (int i = 0; i < tabList.size(); i++) {
            if (tabList.get(i).isSelect()) {
                for (int j = 0; j < childList.size(); j++) {

                    if (childList.get(j).getId() == id) {
                        childList.get(j).setSelect(false);
                    }
                }
            }
        }
        betPopAllLiveData.postValue(betPopAllModel);
    }

    /**
     * ??????????????????
     */
    public void selectTimesByPosition(int position) {
        BetJoinAllModel betJoinAllModel = betJoinAllLiveData.getValue();
        List<BetJoinAllModel.BetJoinTimeModel> betJoinTimeModelList = betJoinAllModel.getBetJoinTimeModelList();

        for (int i = 0; i < betJoinTimeModelList.size(); i++) {
            betJoinTimeModelList.get(i).setSelect(false);
            if (i == position) {
                betJoinTimeModelList.get(i).setSelect(true);
            }
        }
        betJoinAllModel.setBetJoinTimeModelList(betJoinTimeModelList);
        betJoinAllLiveData.postValue(betJoinAllModel);
    }

    /**
     * ?????????????????? by  position
     *
     * @param position
     */
    public void selectMaByPosition(int position) {
        BetJoinAllModel betJoinAllModel = betJoinAllLiveData.getValue();
        List<BetJoinAllModel.BetJoinMaModel> betJoinMaModelList = betJoinAllModel.getBetJoinMaModelList();
        for (int i = 0; i < betJoinMaModelList.size(); i++) {
            betJoinMaModelList.get(i).setSelect(false);
            if (i == position) {
                betJoinMaModelList.get(i).setSelect(true);
            }
        }
        betJoinAllModel.setBetJoinMaModelList(betJoinMaModelList);
        betJoinAllLiveData.postValue(betJoinAllModel);
    }

    /**
     * ????????????isCurrent ????????????????????????  ??????
     * ??? isselect  ???true?????????  ??????  is Current
     * ????????? sp   ???????????????  1  ????????????
     */
    public void selectCurrentMa() {
        BetJoinAllModel betJoinAllModel = betJoinAllLiveData.getValue();
        List<BetJoinAllModel.BetJoinMaModel> betJoinMaModelList = betJoinAllModel.getBetJoinMaModelList();
        List<BetJoinAllModel.BetJoinTimeModel> betJoinTimeModelList = betJoinAllModel.getBetJoinTimeModelList();
        for (int i = 0; i < betJoinMaModelList.size(); i++) {
            betJoinMaModelList.get(i).setCurrent(false);
            if (betJoinMaModelList.get(i).isSelect()) {
                betJoinMaModelList.get(i).setCurrent(true);
                Utils.logE(TAG, "????????????????????????: "+betJoinMaModelList.get(i).getDanjia() );
            }
        }
        betJoinAllModel.setBetJoinMaModelList(betJoinMaModelList);
        betJoinAllLiveData.postValue(betJoinAllModel);

        BetPopAllModel betPopAllModel = betPopAllLiveData.getValue();
        if(betPopAllModel!=null){
            List<BetPopAllModel.BetPopChildModel> betPopChildModelList = betPopAllModel.getBetPopChildModelList();
            for (int i = 0; i < betPopChildModelList.size(); i++) {
                betPopChildModelList.get(i).setRubbish("???????????????,????????????betPopAllLiveData??????,??????????????????????????????????????????,???????????????????????????????????????betPopAllLiveData??????????????????"+System.currentTimeMillis());
            }
            betPopAllLiveData.postValue(betPopAllModel);;
        }


        /**
         * ?????????sp??????  ????????????????????????
         */
        for (int i = 0; i < betJoinTimeModelList.size(); i++) {
            betJoinTimeModelList.get(i).setSelect(false);
            if (i == 0) {
                betJoinTimeModelList.get(i).setSelect(true);
            }
        }
        betJoinAllModel.setBetJoinTimeModelList(betJoinTimeModelList);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String str = gson.toJson(betJoinAllModel, BetJoinAllModel.class);
        mSharedPreferenceHelperImpl.setChoumaData(str);
    }

    /**
     * ?????????????????????
     */
    public void setCusMa(int value) {
        BetJoinAllModel betJoinAllModel = betJoinAllLiveData.getValue();
        List<BetJoinAllModel.BetJoinMaModel> betJoinMaModelList = betJoinAllModel.getBetJoinMaModelList();
        betJoinMaModelList.get(betJoinMaModelList.size() - 1).setDanjia(value);
        betJoinAllModel.setBetJoinMaModelList(betJoinMaModelList);
        betJoinAllLiveData.postValue(betJoinAllModel);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String str = gson.toJson(betJoinAllModel, BetJoinAllModel.class);
        mSharedPreferenceHelperImpl.setChoumaData(str);
    }

    public void setAnchorAccount(){}


    /**
     * ????????????
     */

    public void reqBet2(List<BetJoinContentModel> list, Activity activity, Fragment fragment, LiveEntity.AnchorMemberRoomListBean mLiveData, CusBetJoinOldPop cusBetJoinOldPop) {
        HashMap<String, Object> reqMap = new HashMap();
        int game = this.game;
        /**
         * ??????  ?????? ??????  ?????????   childList X ??????
         */
        BetPopAllModel betPopAllModel = betPopAllLiveData.getValue();
        List<BetPopAllModel.BetPopChildModel> selectList = betPopAllModel.getBetPopChildModelList();

        List<String> ruleidList = new ArrayList<>();
        List<String> amountList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        String groupName = "";


        int messageAmount=0;
        for (int j = 0; j < list.size(); j++) {
            ruleidList.add("" + list.get(j).getRuleId());
            amountList.add("" + list.get(j).getDanjia()*Integer.parseInt(list.get(j).getMultiple()));
            messageAmount+=list.get(j).getDanjia()*Integer.parseInt(list.get(j).getMultiple());
            nameList.add("" + list.get(j).getName());
            groupName = list.get(j).getType();//??????(????????????tab)
        }

        String rule_id = Joiner.on(",").join(ruleidList);  //??????guava???Joiner???????????????
        String amount = Joiner.on(",").join(amountList);
        String name = Joiner.on(",").join(nameList);

        int type_id = this.typeId;
        String lotteryqishu = this.qishu;
        reqMap.put("lotteryqishu", lotteryqishu);
        reqMap.put("type_id", type_id);
        reqMap.put("amount", amount);
        reqMap.put("rule_id", rule_id);
        reqMap.put("anchorAccount", mLiveData.getAnchorAccount());
        reqMap.put("remoteLiveManagementId", mLiveData.getRemoteLiveManagementId());
        /**
         * ?????????????????????????????????
         */
        String finalGroupName = groupName;
        int finalMessageAmount = messageAmount;
        mRepository.getBet(game, reqMap, activity,fragment,new CallBack() {
            @Override
            public void Success(Object object) {
                cusBetJoinOldPop.dismiss();
                if (object instanceof BaseCpEntity) {
                    BaseCpEntity baseCpEntity = (BaseCpEntity) object;
                    ToastUtil.showToast(baseCpEntity.getMessage());
                    LogUtils.e(baseCpEntity.getMessage());
                    if (baseCpEntity.getStatus().equals("success")) {
                        for (BetPopAllModel.BetPopChildModel bean : selectList) {
                            bean.setSelect(false);
                        }
                        Long user_id = SharePreferencesUtil.getLong(mContext, "user_id", 0l);
                        String userNickName = SharePreferencesUtil.getString(mContext, "userNickName", "");
                        int pointGrade = SharePreferencesUtil.getInt(mContext, CommonStr.GRADE, 1);
                        String type_id = gameClassListBean.getType_id() + "";
                        String game = gameClassListBean.getGame() + "";
                        String typename = gameClassListBean.getTypename();
                        LiveShareBetMessage liveShareBetMessage = new LiveShareBetMessage(userNickName, pointGrade + "", type_id, rule_id, lotteryqishu,
                                game, typename, finalMessageAmount +"", finalGroupName, name, user_id + "");
                        liveShareBetMessage.setUserInfoJson(RongLibUtils.setUserInfo(mContext,anchorManageData.getValue()));
                        RongLibUtils.sendMessage(ROOMID.getValue(), liveShareBetMessage);
                        betPopAllLiveData.postValue(betPopAllModel);
                        mRepository.postData("betStatus", "success");
                    }
                }
            }

            @Override
            public void Faild(String msg) {
                mRepository.postData("bet", "faild");
                ToastUtil.showToast(msg);
                LogUtils.e(msg);
            }
        });
    }

    /**
     * ?????????????????????
     */
    public void isZjTimer() {
        if (Amount_disposable != null) {
            Amount_disposable.dispose();
            Amount_disposable = null;
        }
        Amount_disposable = Flowable.interval(6, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    if(isShowingAmount){
                        requestIsZj();
                        Utils.logE(TAG, "isZjTimer: ??????????????????,??????????????????,????????????");
                    }else {
                        Utils.logE(TAG, "isZjTimer: ??????????????????,????????????????????? return)");
                    }
                })
                // .doOnComplete(()->Amount_disposable.dispose())
                .subscribe();
    }
    /**
     * ??????????????????
     */
    public void requestIsZj() {
//        amountLiveData = getAmountLiveData();
        mRepository.getIsZj(new CallBack() {
            @Override
            public void Success(Object object) {
                if(object instanceof ZjEntity){
                   ZjEntity zjEntity = (ZjEntity) object;
//                    Utils.logE(TAG, Utils.getString(R.string.??????????????????  ) );
                    String todayZJ = zjEntity.getTodayZJ();
                    if(todayZJ.equals("1")){
                        getAmount();
                    }
                }


            }

            @Override
            public void Faild(String msg) {
                ToastUtil.showToast(msg);
            }
        });
    }

    public void getAmount() {
        amountLiveData = getAmountLiveData();
        mRepository.getAmount(new CallBack() {
            @Override
            public void Success(Object o) {
                if (o instanceof MemberMoney) {
                    MemberMoney memberMoney = (MemberMoney) o;
                    if (memberMoney != null) {
                        amountLiveData.postValue(memberMoney);
                    }
                }
            }

            @Override
            public void Faild(String msg) {

            }
        });
    }

    public void requestMoney(){

    }
    /**
     * ?????????????????????url
     */
    public void RefreshRoomUrl(String anchorAccount,String remoteLiveManagementId,String liveRoomId) {
        mRepository.RefreshRoomUrl(anchorAccount,remoteLiveManagementId,liveRoomId, new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof RefreshUrlEntity) {
                    RefreshUrlEntity refreshUrlEntity = (RefreshUrlEntity) object;
                    refreshRoomLiveData.postValue(refreshUrlEntity);
                }
            }

            @Override
            public void Faild(String msg) {
                ToastUtil.showToast(msg);
            }
        });
    }



    /**
     * ??????????????????
     */
//    public void loadAnimation(LiveFragment liveFragment) {
//        SVGAParser svgaParser = new SVGAParser(liveFragment.getContext());
//        SVGAImageView svgaImageView = liveFragment.svgaImageView;
//        svgaImageView.setLoops(1);//???????????????????????????????????????????????????????????????????????????
//        svgaParser.parse("tm7s00.svga", new SVGAParser.ParseCompletion() {
//            @Override
//            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
//                svgaImageView.setVideoItem(svgaVideoEntity);
//                svgaImageView.stepToFrame(0, true);
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });
//        svgaImageView.setCallback(new SVGACallback() {
//            @Override
//            public void onPause() {
//                Utils.logE(TAG, Utils.getString(R.string.onFinished: ??????????????????));
//            }
//
//            @Override
//            public void onFinished() {
//                Utils.logE(TAG, Utils.getString(R.string.onFinished: ??????????????????));
//            }
//
//            @Override
//            public void onRepeat() {
//                Utils.logE(TAG, Utils.getString(R.string.onFinished: ????????????????????????));
//            }
//
//            @Override
//            public void onStep(int i, double v) {
//
//            }
//        });
//    }

    /**
     * ??????????????????  ?????????????????????
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (timerRunnable != null) {
            handler.removeCallbacks(timerRunnable);
        }
        if (openRunnable != null) {
            handler.removeCallbacks(openRunnable);
        }
        if (timerRunnable != null) {
            handler.removeCallbacks(timerRunnable);
        }
        handler.removeCallbacksAndMessages(null);

        if (Amount_disposable != null) {
            Amount_disposable.dispose();
            Amount_disposable = null;
        }
        if (dismiss_disable != null) {
            dismiss_disable.dispose();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public boolean isShowingAmount() {
        return isShowingAmount;
    }

    public void setShowingAmount(boolean showingAmount) {
        isShowingAmount = showingAmount;
    }
}
