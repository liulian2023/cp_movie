package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateSingleEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.HomeTwoFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.BannerWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ChessTxWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.GameReportActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MineMessageActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter.BannerViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter.HomeClassFyAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter.LiveListAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.AppUpdateModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.CollectEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.UpdateGiftAmountModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BannerData;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeAtyEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeGridView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeLiveDataSuccessEven;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.HomeAtyPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.serevice.DownloadService;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SaveChessLotteryUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ScreenUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.VersionUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class HotFragment extends BaseFragment implements View.OnClickListener {
    public final String TAG = getClass().getSimpleName();
    private BannerViewPager<BannerData.ExtensionNoticeInfoListBean, BannerViewHolder> mBannerViewPager;   //?????????
    private LinearLayout bannerLinear; //?????????linear(????????????????????????,??????????????????banner ????????????banner????????????,?????????????????????)
    private List<BannerData.ExtensionNoticeInfoListBean> bannerDataList=new ArrayList<>();   //???????????????
    private MarqueeView paomaTv;//?????????
    private ArrayList<String> paomaStrDataList = new ArrayList<>(); //?????????????????????
    private ArrayList<HomeAtyEntity.ExtensionNoticeInfoListBean> noticeModelArrayList =new ArrayList<>();    //???????????????????????????

    //????????????????????????
    private static final int SHOW_UPLOAD_POP=1212;
    /*
   ??????recycleView
   */
    private RecyclerView hotLotteryRecy;
    private HomeClassFyAdapter hotLotteryAdapter;
    private ArrayList<HomeGridView> hotLotteryList = new ArrayList<>();
    //??????????????????(?????????????????????????????????,???????????????,???????????????????????????,????????????????????????,???????????????????????????tab???,??????????????????)
    private String  navigateList;
    //????????????recycleView
    private RecyclerView liveRecycle;
    private LiveListAdapter liveListAdapter;
    private ArrayList<LiveEntity.AnchorMemberRoomListBean> liveBeanList = new ArrayList<>();
    private Long noticeId;
    private PopupWindow messageNumPopWindow;//????????????pop
    private boolean isDestroy=false;//???????????????????????????
    private int homeCount;//mainActivity???????????????homeTab?????????homeCount???1(???????????????????????????showAty???????????????????????????pop)
    private boolean showAtyPop=false;//???????????????????????????
    private boolean isLogin;
    private List<String> atyIdList=new ArrayList<>();//??????????????????id
    private List<String> birthdayIdList=new ArrayList<>();
    private List<String> regiesterIdList=new ArrayList<>();
    private String showPop;//?????????????????????????????????????????????
    private boolean isCreate=false;
    private boolean fromLogin;//?????????????????????????????????????????????homeTab??????????????????,?????????????????????,??????????????????tab?????????
    private String atyPopJson;//???????????????json??????,???????????????????????????????????????,????????????????????????pop
    private boolean isJsonChange;//??????atyPopJson???,?????????boolean???
    private LinearLayout moreNoticeLienar;
    private View view;
    private LinearLayout toMessageLinear;  //????????????pop????????????????????????
    SaveChessLotteryUtil saveChessLotteryUtil =  new SaveChessLotteryUtil();
    private ImageView lotteryMoreIv;//????????????????????????
    private TextView lotteryMoreTv;
    private ImageView movieMoreIv;//??????????????????
    private TextView movieMoreTv;
    private SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private int hotTabCount;
    private ImageView make_money_iv;
    private ConstraintLayout loadingLayout;
    private LinearLayout nodataLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
//    private TextView buttomTipTv;

    public MyHandler handler;
    private boolean shouidUpdate=true;
    private String downloadUrl;

    //??????app????????????
    private long localVersionCode;
    private String localVersionName;


    private DownloadService downloadService ;
    //    private DownloadService.DownloadBinder downloadBinder;
    private DownloadService.DownloadBinder downloadBinder;
    private SmartRefreshLayout refreshLayout;

    private boolean hotRecycleSuccess;
    private boolean bannerSuccess;
    private boolean paomaSuccess;
    private boolean liveSuccese;
    private boolean chessSuccess;
    private String chessData;

    private String downloadApkPath;
    public static int REQUEST_ATY_RED =1;
    private static int REQUEST_BIRTHDAY_RED =2;
    private static int REQUEST_REGISTER_RED =3;
    private static int REQUEST_UNREAD_MESSAGE =4;

    private static int REQUEST_ATY_POP=7;
    private static int RQUEST_LIVE_DATA=10;
    private static int REQUEST_PARAMETER=11;
    private static int REQUEST_HOT_LOTTERY =12;
    private static String INSTALL_APP=Utils.getString(R.string.????????????);
    private static String DOWNING=Utils.getString(R.string.????????????);
    private LinearLayout noticeMoreLinear;

    private int pageNo=1;
    private int pageSize = 16;

    private enum SuccessType{
        PAOMA,
        LIVE,
        BANNER,
        HOT,
        CHESS;
    }
    SuccessType successType;
    HomeAtyPop homeAtyPop;
    HomeAtyPop noticePop;
    private ArrayList<HomeAtyEntity.ExtensionNoticeInfoListBean> atyPopDataList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//??????eventBus
        }
        localVersionCode= VersionUtils.getAppVersionCode(MyApplication.getInstance());
        localVersionName= VersionUtils.getAppVersionName(MyApplication.getInstance());
        handler=new MyHandler(new WeakReference<>(this));
        initData();
        bindView(view);
        requestBannerData();
        initLiveRecycle();
        initRefresh();
        //????????????????????????(?????????????????????)
        requestChessData(false);//??????gridView??????
        initPaoMa();//???????????????????????????
        if(showAtyPop&&homeCount==1&&hotTabCount==1){
            handler.sendEmptyMessage(REQUEST_ATY_POP);
        }
        return view;
    }

    private void initRefresh() {
//        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                requestChessData(true);//??????gridView??????(?????????????????????????????????????????????)
                requestBannerData();//?????????
                initPaoMa();//???????????????????????????
            }
        });
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                requestLiveData(true,false,true);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy=true;
        ProgressDialogUtil.stop(getActivity());
        if(mBannerViewPager!=null){
            mBannerViewPager.stopLoop();
        }
        EventBus.getDefault().unregister(this);//??????eventBus??????
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
    @Override
    public void onResume() {

        if (mBannerViewPager != null){
            mBannerViewPager.startLoop();
        }

        super.onResume();
    }

    private void requestAppUpdate() {
/*        if(showAtyPop&&homeCount==1&&hotTabCount==1){
            getAppUpload();
        }else {*/

//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(shouidUpdate){
            shouidUpdate=false;
            return;
        }
//        requestLiveData(true);
    }
    @Override
    public void onStop() {
        super.onStop();
        ProgressDialogUtil.stop(getActivity());
        if (mBannerViewPager != null){
            mBannerViewPager.stopLoop();
        }
    }

    private void requestHBParameter() {
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.HB_PARAMETER, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                RongcloudHBParameter hbParameter = JSONObject.parseObject(result, RongcloudHBParameter.class);
                RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
                int zxHBSwitch = rongcloudHBParameter.getZxHBSwitch();
//                if(zxHBSwitch==1){
                String zxHBGameClassIds = rongcloudHBParameter.getZxHBGameClassIds();
                HbGameClassModel instance = HbGameClassModel.getInstance();
                instance.setHbParameter(hbParameter);
                instance.setGameIdListStr(zxHBSwitch==1?zxHBGameClassIds:"");
                List<String> idList = Arrays.asList(zxHBGameClassIds.split(","));
/*                if(StringMyUtil.isNotEmpty(navigateList)){
                    JSONArray gameClassList = JSONObject.parseObject(navigateList).getJSONArray("gameClassList");
                    String type_id_list="";
                    String game_list="";
                    for (int i = 0; i < gameClassList.size(); i++) {
                        JSONObject jsonObject = gameClassList.getJSONObject(i);
                        String lottery_id = jsonObject.getString("id");
                        if(idList.contains(lottery_id)){
                            String type_id = jsonObject.getString("type_id");
                            String game = jsonObject.getString("game");
                            type_id_list=type_id_list+type_id+",";
                            game_list=game_list+game+",";
                        }
                    }
                    if(StringMyUtil.isNotEmpty(game_list)){
                        instance.setGamelist(game_list.substring(0,game_list.length()-1));
                        instance.setTyptIdList(type_id_list.substring(0,type_id_list.length()-1));
                    }

                }*/
                EventBus.getDefault().postSticky(instance);
//                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateCollect(CollectEvenModel collectEvenModel){
/*        int movieId = collectEvenModel.getMovieId();
        boolean collect = collectEvenModel.isCollect();
        for (int i = 0; i < liveBeanList.size(); i++) {
            HomeMovieModel homeMovieModel = liveBeanList.get(i);
            if(movieId==homeMovieModel.getMovieId()){
                if(collect){
                    homeMovieModel.setIsCollection(1);
                }else {
                    homeMovieModel.setIsCollection(0);
                }
            }
        }
        homePopRecycleAdapter.notifyDataSetChanged();*/
    }

    /**
     * ????????????????????????
     * @param followEvenModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateIsFollow(FollowEvenModel followEvenModel){
        for (int i = 0; i < liveBeanList.size(); i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = liveBeanList.get(i);
            if((anchorMemberRoomListBean.getAnchorMemberId()+"").equals(followEvenModel.getId())){
                if(followEvenModel.isFollow()){
                    anchorMemberRoomListBean.setIsFollow(1);
                }else {
                    anchorMemberRoomListBean.setIsFollow(0);
                }
                break;
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void appUpdate(AppUpdateModel appUpdateModel){
        boolean update = appUpdateModel.isUpdate();
        if(update){
//            requestAppUpdate();
            handler.sendEmptyMessage(REQUEST_ATY_POP);
        }
    }
    /**
     * ????????????????????????
     * @param updateGiftAmountModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateGiftAmount(UpdateGiftAmountModel updateGiftAmountModel){
        for (int i = 0; i < liveBeanList.size(); i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = liveBeanList.get(i);
            if((anchorMemberRoomListBean.getAnchorMemberId()+"").equals(updateGiftAmountModel.getId())){
                anchorMemberRoomListBean.setGiftAmount(updateGiftAmountModel.getGiftAmount()+"");
                break;
            }
        }
    }

    /**
     * ?????????????????????
     * @param hbGameClassModel  ?????????????????????idList???model
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        int quYueHBSwitch = hbGameClassModel.getHbParameter().getRongcloudHBParameter().getQuYueHBSwitch();
        if(quYueHBSwitch==1){
            if(make_money_iv!=null){
                make_money_iv.setVisibility(View.VISIBLE);
            }
        }else {
            if(make_money_iv!=null){
                make_money_iv.setVisibility(View.GONE);
            }
        }
        selectorId(hbGameClassModel);
    }


    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        for (int i = 0; i < hotLotteryList.size(); i++) {
            HomeGridView homeGridView = hotLotteryList.get(i);
            for (int j = 0; j < idList.size(); j++) {
                if((homeGridView.getId()+"").equals(idList.get(j))){
                    homeGridView.setXian(true);
                    break;
                }
            }
        }
        if(null!=hotLotteryAdapter){
            hotLotteryAdapter.notifyDataSetChanged();
        }
    }

    public static HotFragment newInstance(int position){
        Bundle bundle = new Bundle();
        HotFragment hotFragment = new HotFragment();
        bundle.putInt("position",position);
        hotFragment.setArguments(bundle);
        return hotFragment;
    }
    private void bindView(View view) {
        refreshLayout=view.findViewById(R.id.hot_refresh);
        liveRecycle =view.findViewById(R.id.home_movie_recycle);
        make_money_iv=view.findViewById(R.id.make_money_iv);
        make_money_iv.setOnClickListener(this);
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestLiveData(true,false,false);
    }

    static class MyHandler extends  Handler{
        WeakReference<HotFragment> hotFragmentWeakReference;

        public MyHandler(WeakReference<HotFragment> hotFragmentWeakReference) {
            this.hotFragmentWeakReference = hotFragmentWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HotFragment hotFragment = hotFragmentWeakReference.get();
            if(null!=hotFragment){
                if(!hotFragment.isDestroy){//????????????????????????????????????pop,??????bug
                    if(msg.what== REQUEST_ATY_RED){//????????????pop dismiss???,??????????????????pop
//                    initAtyGift();//????????????????????????
                        hotFragment. getAtyGift();//?????????????????????
                    }
                    if(msg.what==REQUEST_BIRTHDAY_RED){
//                    initBirthdayGift();//????????????????????????
                        hotFragment. getBirthdayGift();//?????????????????????
                    }
                    if(msg.what==REQUEST_REGISTER_RED){
                        hotFragment.   getRegisterGift();
                    }
                    if(msg.what==REQUEST_UNREAD_MESSAGE){
                        hotFragment.  initMessagePop();//????????????pop????????????
                        hotFragment.  getMessageNum();//?????????????????????
                    }
                    if(msg.what==RQUEST_LIVE_DATA){
                        hotFragment.requestLiveData((Boolean) msg.obj,true,false);
                    }
                    if(msg.what == REQUEST_HOT_LOTTERY){
                        hotFragment.requestHotLotteryData((Boolean) msg.obj);
                    }
                    if(msg.what==REQUEST_ATY_POP){
                        hotFragment.getAtyPop();
                    }
                    if(msg.what==SHOW_UPLOAD_POP){
                    }
                    if(msg.what==REQUEST_PARAMETER){
                        hotFragment. requestHBParameter();
                    }
                }
            }
        }


    }

    /**
     * ????????????????????????(?????????????????????)
     * @param uploadCollection ?????????????????????????????????????????????()
     */
    private void requestChessData(boolean uploadCollection) {
        if(StringMyUtil.isNotEmpty(chessData)){
            //??????????????????
            addChessData(chessData,uploadCollection);
        }
        /*
        ???????????????????????????
         */
        HashMap<String, Object> data = new HashMap<>();
        data.put("loginType",1);
        HttpApiUtils.CPnormalRequest(getActivity(),this, RequestUtil.CHESS_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                if(StringMyUtil.isEmptyString(chessData)){
                    //???????????????????????????????????????????????????????????????
                    addChessData(result, uploadCollection);
                }
                chessData=result;
                SharePreferencesUtil.putString(getContext(),CommonStr.CHESS_DATA,result);
            }

            @Override
            public void onFailed(String msg) {
                refreshLayout.finishRefresh(false);
            }
        });
    }

    /**
     * ????????????????????????????????????
     * @param result
     * @param uploadCollection
     */
    private void addChessData(String result, boolean uploadCollection) {
        initRefreshStatus(SuccessType.CHESS);
        hotLotteryList.clear();
        //????????????item
        HomeGridView.ChessEntity chessEntity = JSONObject.parseObject(result, HomeGridView.ChessEntity.class);
        List<HomeGridView.ChessEntity.DataBean> dataBeanList = chessEntity.getData();
        for (int i = 0; i < dataBeanList.size(); i++) {
            HomeGridView.ChessEntity.DataBean bean = dataBeanList.get(i);
            int isHot = bean.getIsHot();
            //????????????????????????????????????
            if(isHot==1){
                if(hotLotteryList.size()==4){
                    break;
                }
                HomeGridView homeGridView = new HomeGridView();
                homeGridView.setChessDataBean(bean);
                hotLotteryList.add(homeGridView);
            }
        }
        Message message = Message.obtain();
        message.obj=uploadCollection;
        message.what=REQUEST_HOT_LOTTERY;
        handler.sendMessage(message);//?????????????????????
    }
    /**
     * ??????????????????(???????????????????????????)
     * @param uploadCollection
     */
    private void requestHotLotteryData(boolean uploadCollection) {
        if(!StringMyUtil.isEmptyString(navigateList)){
            addHotLotteryData(navigateList);
            initRefreshStatus(SuccessType.HOT);
            Message message = Message.obtain();
            message.obj=uploadCollection;
            message.what=RQUEST_LIVE_DATA;
            handler.sendMessage(message);//navigateList?????????????????????????????????
            handler.sendEmptyMessage(REQUEST_PARAMETER);//????????????????????????,??????????????????????????????(????????????game???type_id???????????????id)
        }
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_01dhnew, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                if(StringMyUtil.isEmptyString(navigateList)){
                    addHotLotteryData(result);
                    initRefreshStatus(SuccessType.HOT);
                    Message message = Message.obtain();
                    message.obj=uploadCollection;
                    message.what=RQUEST_LIVE_DATA;
                    handler.sendMessage(message);
                    handler.sendEmptyMessage(REQUEST_PARAMETER);
                }
                navigateList=result;
                SharePreferencesUtil.putString(getContext(),"navigateList",result);

                //???????????????????????????
                NavigateSingleEntity.newInstance().setNavigateEntity(JSONObject.parseObject(result, NavigateEntity.class));
            }

            @Override
            public void onFailed(String msg) {
                refreshLayout.finishRefresh(false);
            }
        });
    }



    private void addHotLotteryData(String content) {
        //????????????
        JSONObject jsonObject = JSONObject.parseObject(content);//??????json
        JSONArray jsonArray = jsonObject.getJSONArray("gameClassList");
        String imaUrl = SharePreferencesUtil.getString(getActivity(), "FirstImageUrl", "");
        int size = jsonArray.size();
//        ArrayList<HomeGridView> hotLotteryList = new ArrayList<>();
        for (int i = 0; i < /*(size>=8?8:size)*/size; i++) {
            JSONObject jsonObjectHot = jsonArray.getJSONObject(i);
            String isStart = jsonObjectHot.getString("isStart");//??????????????????????????? 0??????????????? 1?????????
            //??????????????????
            if(jsonObjectHot.getString("isHot").equals("1")/*&&isStart.equals("1")*/){
                if(hotLotteryList.size()==8){
                    break;
                }
                String image = jsonObjectHot.getString("image");
                String count = jsonObjectHot.getString("remark");//???????????????
        /*
        ?????????????????????????????????
         */
                Integer game = jsonObjectHot.getInteger("game");//??????
                String typename = jsonObjectHot.getString("typename");//??????
                Integer type_id = jsonObjectHot.getInteger("type_id");//typeid

                String isopenOffice = jsonObjectHot.getString("isopenOffice");//??????????????????????????? 0?????????
                String id = jsonObjectHot.getString("id");//??????????????????????????????(???????????? ????????? ?????????)
                String finalImgUrl = imaUrl + image;
                HomeGridView homeGridView = new HomeGridView(finalImgUrl, typename, count, type_id, isStart, isopenOffice, game);
                homeGridView.setId(id);
                hotLotteryList.add(homeGridView);
                //isStart: 0 ????????????  1 ????????????   ?????????????????????????????????

            }
        }
//                hotLotteryList.add(new HomeGridView(CommonStr.ALL_LOTTERY, CommonStr.ALL_LOTTERY, " ", 0, ",", "", 0));
        SharePreferencesUtil.putString(getContext(),"hotLottery", JSON.toJSONString(hotLotteryList));
        hotLotteryAdapter.notifyDataSetChanged();
        HbGameClassModel instance = HbGameClassModel.getInstance();
        if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
            selectorId(instance);
        }
    }
    /*
    ??????????????????
     */
    private void initLiveRecycle() {
        liveListAdapter =new LiveListAdapter(this);
        liveRecycle.setLayoutManager(new GridLayoutManager(getContext(),2));
        liveRecycle.setAdapter(liveListAdapter);
        liveListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(!LoginIntentUtil.isLogin(getContext())){
                    LoginIntentUtil.toLogin(getActivity());
                }else {
                    int curPage = ((position+1) + pageSize-1)/pageSize;
                    RouteUtils.start2LiveActivity(getContext(),liveBeanList.get(position),"",CommonStr.AREA_DEFAULT_VALUE,curPage);
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), LiveTestActivity.class);
//                    startActivity(intent);
                }
            }
        });

        View headView = LayoutInflater.from(getContext()).inflate(R.layout.hot_head_layout,null);
        liveListAdapter.addHeaderView(headView);
        bindHeadView(headView);
        initHotRecycle();
    }

    private void bindHeadView(View view) {
        noticeMoreLinear =view.findViewById(R.id.notice_more_linear);
        noticeMoreLinear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(getContext(), GameReportActivity.class));
//                OpenInviteRedActivity.startAty(getActivity(),5,"1");
                return false;
            }
        });
        mBannerViewPager=view.findViewById(R.id.hot_fragment_banner);
        bannerLinear=view.findViewById(R.id.hot_fragment_banner_linear);
        int mScreenWidth = ScreenUtils.getWight(_mActivity);
        bannerLinear.getLayoutParams().height = (mScreenWidth - ScreenUtils.dip2px(_mActivity, 30f)) * 240/700;
        hotLotteryRecy =view.findViewById(R.id.home_classfy_recycle);
        paomaTv=view.findViewById(R.id.marquee_text);
        lotteryMoreIv=view.findViewById(R.id.lottery_more_iv);
        lotteryMoreTv=view.findViewById(R.id.lottery_more_tv);
        movieMoreIv=view.findViewById(R.id.movie_more_iv);
        movieMoreTv=view.findViewById(R.id.movie_more_tv);
        loadingLayout=view.findViewById(R.id.loading_linear);
        nodataLinear=view.findViewById(R.id.nodata_linear);
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        lotteryMoreIv.setOnClickListener(this);
        lotteryMoreTv.setOnClickListener(this);
        movieMoreIv.setOnClickListener(this);
        movieMoreTv.setOnClickListener(this);
        noticeMoreLinear.setOnClickListener(this);

        paomaTv.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
//                NoticeModel noticeModel = noticeModelArrayList.get(position);
               /* if(!IntentUtil.isLogin(getActivity())){
                    IntentUtil.toLogin(getActivity());
                }else{
                    NoticeModel noticeModel = noticeModelArrayList.get(position);
                    Intent intent = new Intent(getContext(), NoticeMessageDetailsActivity.class);
                    intent.putExtra("title", noticeModel.getTitle());
                    intent.putExtra("content", noticeModel.getWenContent());
                    intent.putExtra("time", noticeModel.getTime());
                    intent.putExtra("id",noticeModel.getId());
                    startActivity(intent);
                }*/
                initNoticePop();
            }
        });
    }

    /**
     * ??????????????????
     * @param uploadCollection ????????????????????????????????????
     */
    private void requestLiveData(boolean uploadCollection,boolean isRefresh,boolean isLoadMore) {
        if(liveBeanList.size()!=0&&!uploadCollection){
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        if(isRefresh){
            data.put("pageNo",1);
        }else {
            data.put("pageNo",pageNo);
        }
        data.put("pageSize",pageSize);
        HttpApiUtils.CPnormalRequest(getActivity(),this, RequestUtil.LIVEROOM_LIST, data,new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                if(isRefresh){
                    pageNo=1;
                    initRefreshStatus(SuccessType.LIVE);
                }
                JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//??????json
                JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
                if(!isLoadMore){
                    liveBeanList.clear();
                }else {
                    refreshLayout.finishLoadMore(true);
                }
                LiveEntity LiveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.AnchorMemberRoomListBean> anchorMemberRoomList = LiveEntity.getAnchorMemberRoomList();
                EventBus.getDefault().post(new HomeLiveDataSuccessEven(true));
                /**
                 * ??????????????????????????????????????????????????????????????? {"message":"success","status":"success"} ???????????????, ????????????
                 */
                if(anchorMemberRoomList==null){
                    nodataLinear.setVisibility(View.VISIBLE);
                }else {
                    nodataLinear.setVisibility(View.GONE);
                    int size = anchorMemberRoomList.size();
                    if(size==0){
                        if(!isLoadMore){
                            nodataLinear.setVisibility(View.VISIBLE);
//                            buttomTipTv.setVisibility(View.GONE);
                        }else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                    }else {
                        nodataLinear.setVisibility(View.GONE);
//                        buttomTipTv.setVisibility(View.VISIBLE);
                        if(isLoadMore){
                            refreshLayout.finishLoadMore(true);
                        }
                    }

/*                for (int i = 0; i < anchorMemberRoomList.size(); i++) {
                    LiveEntity.AnchorMemberRoomListBean memberRoomListBean = anchorMemberRoomList.get(i);
                    if(!liveBeanList.contains(memberRoomListBean)){
                        liveBeanList.add(memberRoomListBean);
                    }
                }*/
                    for (int i = 0; i < size; i++) {
                        LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = anchorMemberRoomList.get(i);
                        long cpId = anchorMemberRoomListBean.getCpId();
                        for (int j = 0; j < lotteryArray.size(); j++) {
                            JSONObject object = lotteryArray.getJSONObject(j);
                            long lotteryId = object.getLong("id");
                            if(lotteryId==cpId){
                                String typename = object.getString("typename");
                                String game = object.getString("game");
                                anchorMemberRoomListBean.setGame(game);
                                anchorMemberRoomListBean.setLotteryName(typename);
                                if(!liveBeanList.contains(anchorMemberRoomListBean)){
                                    liveBeanList.add(anchorMemberRoomListBean);
                                }
                                break;
                            }
                        }
                    }
                    liveListAdapter.setList(liveBeanList);
                }

            }
            @Override
            public void onFailed(String msg) {
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
                if(isLoadMore){
                    refreshLayout.finishLoadMore(false);
                    pageNo--;

                }
            }

        });
    }
    /*
    intent  sharePreferencesUtil Argument ????????????
     */
    private void initData() {

        atyPopJson=SharePreferencesUtil.getString(getContext(),"atyPopJson","");
        navigateList=SharePreferencesUtil.getString(getContext(),"navigateList","");
        chessData = SharePreferencesUtil.getString(getContext(), CommonStr.CHESS_DATA, "");
        showPop=SharePreferencesUtil.getString(getContext(),"showPop","");
        homeCount = SharePreferencesUtil.getInt(getActivity(), "HomeCount", 0);
        HomeTwoFragment homeTwoFragment= (HomeTwoFragment) getParentFragment();
        hotTabCount=homeTwoFragment.hotTabCount;
        showAtyPop = getActivity().getIntent().getBooleanExtra("showAtyPop",false);//??????mainActivyty??????????????????????????????pop?????????
        Bundle bundle = getArguments();
        if(bundle!=null){
            fromLogin=bundle.getBoolean("fromLogin");
        }
    }
    /*
    ??????recycleView
     */
    private void initHotRecycle() {
        hotLotteryRecy.setHasFixedSize(true);
        hotLotteryRecy.setNestedScrollingEnabled(false);
        hotLotteryAdapter = new HomeClassFyAdapter(hotLotteryList,this);
        hotLotteryRecy.setLayoutManager(new GridLayoutManager(getContext(),4));
        hotLotteryRecy.setAdapter(hotLotteryAdapter);
        hotLotteryAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(!LoginIntentUtil.isLogin(getContext())){
                    LoginIntentUtil.toLogin(getActivity());
                }else {
                    HomeGridView hotLotteryModel = hotLotteryList.get(position);
                    HomeGridView.ChessEntity.DataBean chessDataBean = hotLotteryModel.getChessDataBean();
                    if(chessDataBean ==null){
                        if(hotLotteryModel.getLottoeryName().equals(CommonStr.ALL_LOTTERY)){
                            MainActivity mainActivity  = (MainActivity) getActivity();
                            HomeTwoFragment homeTwoFragment = (HomeTwoFragment) getParentFragment();
                            if(null!=mainActivity){
                                mainActivity.tabBuy.performClick();
                            }
                        }else {
                            int typeId = hotLotteryModel.getTypeId();
//                           intent.putExtra("type_id", typeId);
                            String isopenOffice = hotLotteryModel.getIsopenOffice();
//                           intent.putExtra("isopenOffice", isopenOffice);
                            String isStart = hotLotteryModel.getIsStart();
//                           intent.putExtra("isStart", isStart);
                            int game = hotLotteryModel.getGame();
//                           intent.putExtra("game", game);
                            String lottoeryName = hotLotteryModel.getLottoeryName();
//                           intent.putExtra("typename", lottoeryName);
//                           startActivity(intent);
                            ToBetAtyUtils.toBetActivity(getActivity(),game,lottoeryName,typeId,isopenOffice,isStart);
                        }
                    }else {
                        String jsonString = JSONObject.toJSONString(chessDataBean);
                        saveChessLotteryUtil.saveChessLotteryHistory(jsonString);
                        String id = chessDataBean.getId();
                        int game = chessDataBean.getGame();
                        HashMap<String, Object> data = new HashMap<>();
                        data.put("game",game);
                        data.put("id",id);
                        data.put("loginType",1);
                        HttpApiUtils.CpRequest(getActivity(), HotFragment.this,RequestUtil.PLAY_CHESS, data, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                String url = jsonObject.getString("url");
                                ChessTxWebViewActivity.startAty(getContext(),url,game,id);
                            }

                            @Override
                            public void onFailed(String msg) {

                            }
                        });
                    }
                }
            }
        });
    }
    /*
    ???????????????
     */
    private void requestBannerData() {
        HashMap<String,Object >  dataBanner = new HashMap<>();
        dataBanner.put("pageNo", "1");
        dataBanner.put("pageSize", "10");
        dataBanner.put("type", "1");
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.BANNER_DATA, dataBanner, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                initRefreshStatus(SuccessType.BANNER);
                bannerDataList.clear();
                BannerData parseObject = JSONObject.parseObject(result, BannerData.class);
                List<BannerData.ExtensionNoticeInfoListBean> extensionNoticeInfoList = parseObject.getExtensionNoticeInfoList();
                bannerDataList.addAll(extensionNoticeInfoList);
                mBannerViewPager
                        .setIndicatorVisibility(View.VISIBLE)
                        //??????????????????
                        .setInterval(2000)
                        //??????????????????
                        .setCanLoop(true)
                        //????????????????????????
                        .setAutoPlay(true)
                        //????????????
                        .setRoundCorner(7)
                        //???????????????
                        .setIndicatorColor(ContextCompat.getColor(getContext(),R.color.white),ContextCompat.getColor( getContext(),R.color.black))
                        .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                        //???????????????
                        .setIndicatorGravity(IndicatorGravity.END)
                        //????????????????????????/??????
                        .setIndicatorWidth(10)
                        //?????????????????????( DASH:??????  CIRCLE:??????)
                        .setIndicatorStyle(IndicatorStyle.CIRCLE)
                        //??????????????????
                        .setScrollDuration(1000)
                        //??????banner??????,???????????????????????????
                        .setHolderCreator(BannerViewHolder::new)
                        //????????????
                        .setOnPageClickListener(position -> {
                            /**
                             *  ???????????? (???link??????link ??????link??????content,??????????????????????????????)
                             */
                            if(LoginIntentUtil.isLogin(getContext())){
                                BannerData.ExtensionNoticeInfoListBean extensionNoticeInfoListBean = bannerDataList.get(position);
                                String theme = extensionNoticeInfoListBean.getTheme();
                                String link = extensionNoticeInfoListBean.getLink();
                                if(StringMyUtil.isNotEmpty(link)){
                                    BannerWebViewActivity.startActivity(getContext(),link,theme);
                                }else {
                                    String webViewContent = extensionNoticeInfoListBean.getContent();
                                    if(StringMyUtil.isNotEmpty(webViewContent)){
                                        BannerWebViewActivity.startActivity(getContext(),webViewContent,theme);
                                    }else {
                                        Fragment fragment =  getParentFragment();
                                        if(fragment instanceof  HomeTwoFragment && fragment!=null){
                                            HomeTwoFragment homeTwoFragment = (HomeTwoFragment) fragment;
                                            homeTwoFragment.getmViewPager().setCurrentItem(1);
                                        }
                                    }

                                }

                            }else{
                                LoginIntentUtil.toLogin(getActivity());
                            }


                        })
                        //???????????????
                        .create(bannerDataList);
            }

            @Override
            public void onFailed(String msg) {
                refreshLayout.finishRefresh(false);
            }
        });

    }

    private void initRefreshStatus(SuccessType successType) {
        switch (successType){
            case HOT:
                hotRecycleSuccess=true;
                break;
            case LIVE:
                liveSuccese=true;
                break;
            case PAOMA:
                paomaSuccess = true;
                break;
            case BANNER:
                bannerSuccess=true;
                break;
            case CHESS:
                chessSuccess=true;
                break;
        }
        if(hotRecycleSuccess&&paomaSuccess&&liveSuccese&&bannerSuccess&&chessSuccess){
            refreshLayout.finishRefresh(true);
            hotRecycleSuccess=false;
            paomaSuccess=false;
            liveSuccese=false;
            bannerSuccess=false;
            chessSuccess=false;
        }
    }

    /*
    ????????????pop
     */
    private void initMessagePop() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//??????LayoutInflater??????
        View inflate = inflater.inflate(R.layout.home_message_num_pop, null);//?????????????????????
        messageNumPopWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//?????????popupWindow
        messageNumPopWindow.setAnimationStyle(R.style.popupAnimation);//??????????????????
//        ????????????  ??????pop
        inflate.findViewById(R.id.message_dismiss).setOnClickListener(v->messageNumPopWindow.dismiss());

        //??????????????????,?????????????????????
        toMessageLinear=inflate.findViewById(R.id.to_message_aty);
        toMessageLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPoppu(view);
                try {
                    Intent intent = new Intent(getActivity(), MineMessageActivity.class);
                    intent.putExtra("toMessage",true);//????????????????????????toMessage????????????????????????
                    startActivity(intent);
                    messageNumPopWindow.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        //????????????,??????????????????
        messageNumPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
                Message obtain = Message.obtain();
                obtain.what=5;
                handler.sendMessage(obtain);
                //           initTabClick();
            }
        });
    }
    /*
    ???????????????
     */
    private void getMessageNum() {
        Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
        if(user_id==0){
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_27rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                Long letterUnreadNum = jsonObject.getLong("letterUnreadNum");
                SharePreferencesUtil.putLong(getActivity(),"letterUnreadNum",letterUnreadNum);
                if(letterUnreadNum!=0l){
                    messageNumPopWindow.showAtLocation(view, Gravity.CENTER, 0, 0); // ??????????????????
                    ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                    //    contralTabClick();
                }else{
                    Message message = Message.obtain();
                    message.what=5;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    /*
    ?????????????????????
     */
    private void getBirthdayGift() {
        Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
        if(user_id==0){
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_l10, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                Integer isRed = jsonObject.getInteger("isRed");
                String   birthdayGiftId = jsonObject.getString("redIdStr");
                if(isRed==1){
                    //???????????????,??????pop
                    if(!StringMyUtil.isEmptyString(birthdayGiftId)){
                        String[]  split = birthdayGiftId.split(",");
                        List<String> strings = Arrays.asList(split);
                        birthdayIdList = new ArrayList(strings);
                    }
                    for (int j = 0; j < birthdayIdList.size(); j++) {
                        initBirthdayGift(birthdayIdList.get(j));
                    }

                } else{
                    //??????????????????,?????????????????????pop
                    Message message = Message.obtain();
                    message.what=REQUEST_REGISTER_RED;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    /*
    ??????????????????pop?????????
     */
    private void getAtyPop() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",15);
        data.put("type",3);//0.logo  1?????????  2?????? 3????????????  4????????????  7:??????????????????
        Utils.docking(data, RequestUtil.REQUEST_33r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {

                if(atyPopJson.equals(content)||StringMyUtil.isEmptyString(atyPopJson)){
                    isJsonChange=false;
                }else {
                    isJsonChange=true;
                }
                SharePreferencesUtil.putString(getContext(),"atyPopJson",content);
                if(atyPopDataList!=null){
                    atyPopDataList.clear();
                }
                HomeAtyEntity homeAtyEntity = JSONArray.parseObject(content, HomeAtyEntity.class);
                List<HomeAtyEntity.ExtensionNoticeInfoListBean> extensionNoticeInfoList = homeAtyEntity.getExtensionNoticeInfoList();
                atyPopDataList.addAll(extensionNoticeInfoList);
                noticeModelArrayList.addAll(extensionNoticeInfoList);

                initHomeAtyPop();//????????????????????????
                if(homeAtyPop!=null){
                    homeAtyPop.getHomeAtyRecyclerAdapter().notifyDataSetChanged();
                }
//                homePopRecycleAdapter.notifyDataSetChanged();
                    /*showAtyPop ????????????????????????????????????
                     homeCount mainActivity??? ??????Utils.getString(R.string.??????)tab???????????????
                     showPop??????????????????????????????????????????pop???????????????
                     fromLogin ????????????????????????,??????????????????homeTab????????????
                     isJsonChange ???????????????????????????
                     */
                // ????????????????????????,????????????????????????tab????????????,??????????????????tab?????????,?????????????????????Utils.getString(R.string.??????)tab????????????pop (???tab?????????,?????????count++,??????homeCount???????????????)
                if(showAtyPop&&homeCount==1&&showPop.equals("0")&&hotTabCount==1||fromLogin&&showPop.equals("0")||isJsonChange&&showPop.equals("0")){//??????????????????pop,???????????????????????????,???????????????????????????tab???????????????pop,???????????????????????????,??????????????????????????????
                    showAtyPop(view);
                    //       contralTabClick();

                }else{
                    Message message = Message.obtain();
                    message.what= REQUEST_ATY_RED;//????????????pop??????????????????pop
                    handler.sendMessage(message);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {

                String pcLogo = SharePreferencesUtil.getString(getActivity(), "pcLogo", "");
                String firstImgurl = Utils.getFirstImgurl(getActivity());//????????????
                String url = firstImgurl + pcLogo;
            }
        });
    }
    /**
     ????????????pop????????????
     */
    private void initHomeAtyPop() {
        if(homeAtyPop==null&&getContext()!=null){
            homeAtyPop = new HomeAtyPop(getContext(),true,this,atyPopDataList);
            homeAtyPop.setmOnDissmissListener(new BasePopupWindow2.OnDissmissListener() {
                @Override
                public void onDismiss() {
                    Message message = Message.obtain();
                    message.what=REQUEST_ATY_RED;//????????????pop???????????????pop
                    handler.sendMessage(message);
                }
            });
        }

    }

    /**
     ????????????pop????????????
     */
    private void initNoticePop() {
        noticePop = new HomeAtyPop(getContext(),true,this,noticeModelArrayList);
        noticePop.getHomeAtyRecyclerAdapter().notifyDataSetChanged();
        noticePop.showAtLocation(view,Gravity.CENTER,0,0);
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
    }
    /*
    ??????????????????pop(????????????)
     */
    private void showAtyPop(final View view) {
        hotLotteryRecy.postDelayed(new Runnable() {
            @Override
            public void run() {
   /*             if(!getActivity().isFinishing()&&!isDestroy) {
                    atyPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // ??????????????????
                }*/
                if(atyPopDataList.size()!=0&&homeAtyPop!=null&&!homeAtyPop.isShowing()){
                    homeAtyPop.showAtLocation(view,Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
                }else {
                    Message message = Message.obtain();
                    message.what= REQUEST_ATY_RED;//????????????????????????????????????????????????
                    handler.sendMessage(message);
                }
            }
        },300);
    }
    /*
    ?????????????????????
    */
    private void getAtyGift() {
        Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
        if(user_id==0){
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_l7, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {

                JSONObject jsonObject = JSONObject.parseObject(content);
                Integer isRed = jsonObject.getInteger("isRed");
                String AtyGiftId = jsonObject.getString("redIdStr");

                if(isRed==1){
                    if(!StringMyUtil.isEmptyString(AtyGiftId)){
                        String[]  split = AtyGiftId.split(",");
                        List<String> strings = Arrays.asList(split);
                        atyIdList = new ArrayList(strings);
                    }
                    for (int j = 0; j < atyIdList.size(); j++) {
                        initAtyGift(atyIdList.get(j));
                    }
                }else{
                    Message message = Message.obtain();
                    message.what=REQUEST_BIRTHDAY_RED;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    /*
    ????????????pop????????????
     */
    private void initAtyGift(final String redId) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//??????LayoutInflater??????
        View inflate = inflater.inflate(R.layout.aty_gift_pop, null);//?????????????????????
        final TextView atyGiftPrice=inflate.findViewById(R.id.aty_gift_price);
        final LinearLayout  atyGiftLinear= inflate.findViewById(R.id.aty_gift_linear);
        atyGiftLinear.setBackgroundResource(R.drawable.hdhb_2);
        atyGiftPrice.setVisibility(View.GONE);
        PopupWindow atyGiftPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//?????????popupWindow
        atyGiftPopupWindow.setAnimationStyle(R.style.popupAnimation);//??????????????????
        atyGiftPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // ??????????????????
        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
        //     contralTabClick();//??????pop????????????,????????????tab,??????pop?????????????????????,???dismiss???,??????????????????????????????
        //????????????,??????????????????
        atyGiftPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
                atyIdList.remove(redId);
                if(atyIdList.size()==0){
                    Message obtain = Message.obtain();
                    obtain.what=2;
                    handler.sendMessage(obtain);
                    //           initTabClick();
                }

            }
        });

        atyGiftLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
                HashMap<String, Object> data = new HashMap<>();
                data.put("red_id",redId);
                data.put("user_id",user_id);
                Utils.docking(data, RequestUtil.REQUEST_l9, 0, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content) {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        String message = jsonObject.getString("message");
                        if(Utils.getString(R.string.??????????????????).equals(message)){
                            BigDecimal redPrice = jsonObject.getBigDecimal("redPrice");
                            atyGiftLinear.setBackgroundResource(R.drawable.ylhdhb_2);
                            atyGiftPrice.setText(redPrice+"");
                            atyGiftPrice.setVisibility(View.VISIBLE);
                            showToast(message);
                        }
                        else{
                            atyGiftPrice.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void failed(MessageHead messageHead) {
                        showToast(messageHead.getInfo());
                    }
                });
            }
        });
    }
    /*
   ?????????????????????
    */
    private void getRegisterGift() {
        Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
        if(user_id==0){
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_l4, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                Integer isRed = jsonObject.getInteger("isRed");
//                String AtyGiftId = jsonObject.getString("redIdStr");

                if(isRed==1){
//                    if(!StringMyUtil.isEmptyString(AtyGiftId)){
//                        String[]  split = AtyGiftId.split(",");
//                        List<String> strings = Arrays.asList(split);
//                        regiesterIdList = new ArrayList(strings);
//                    }
//                    for (int j = 0; j < regiesterIdList.size(); j++) {
                    HashMap<String, Object> data1 = new HashMap<>();
                    data1.put("user_id",user_id);
                    data1.put("pageNo",1);
                    data1.put("pageSize",100);
                    Utils.docking(data1, RequestUtil.REQUEST_l5, 0, new DockingUtil.ILoaderListener() {
                        @Override
                        public void success(String content) {
                            initRegisterGift("");
                        }

                        @Override
                        public void failed(MessageHead messageHead) {
                            Utils.logE(TAG,"failed:??????????????????????????????");
                            Message message = Message.obtain();
                            message.what=REQUEST_UNREAD_MESSAGE;
                            handler.sendMessage(message);
                        }
                    });

//                    }
                }else{
                    Message message = Message.obtain();
                    message.what=REQUEST_UNREAD_MESSAGE;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    /*
    ??????????????????pop????????????
     */
    private void initRegisterGift(final String redId) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//??????LayoutInflater??????
        View inflate = inflater.inflate(R.layout.register_gift_pop, null);//?????????????????????
        final TextView atyGiftPrice=inflate.findViewById(R.id.register_gift_price);
        final LinearLayout  atyGiftLinear= inflate.findViewById(R.id.register_gift_linear);
        atyGiftLinear.setBackgroundResource(R.drawable.register_gift);
        atyGiftPrice.setVisibility(View.GONE);
        PopupWindow atyGiftPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//?????????popupWindow
        atyGiftPopupWindow.setAnimationStyle(R.style.popupAnimation);//??????????????????
        atyGiftPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // ??????????????????
        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
        //     contralTabClick();//??????pop????????????,????????????tab,??????pop?????????????????????,???dismiss???,??????????????????????????????
        //????????????,??????????????????
        atyGiftPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
                regiesterIdList.remove(redId);
                if(regiesterIdList.size()==0){
                    Message obtain = Message.obtain();
                    obtain.what=4;
                    handler.sendMessage(obtain);
                    //        initTabClick();
                }

            }
        });

        atyGiftLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
                HashMap<String, Object> data = new HashMap<>();
                data.put("red_id",redId);
                data.put("user_id",user_id);
                Utils.docking(data, RequestUtil.REQUEST_l6, 0, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content) {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        String message = jsonObject.getString("message");
                        if(Utils.getString(R.string.??????????????????).equals(message)){
                            BigDecimal redPrice = jsonObject.getBigDecimal("redPrice");
                            atyGiftLinear.setBackgroundResource(R.drawable.register_gift2);
                            atyGiftPrice.setText(redPrice+"");
                            atyGiftPrice.setVisibility(View.VISIBLE);
                            showToast(message);
                        }
                        else{
                            atyGiftPrice.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void failed(MessageHead messageHead) {
                        showToast(messageHead.getInfo());
                    }
                });
            }
        });
    }
    /*
    ????????????tab????????????,?????????????????????pop??????disMiss???,?????????????????????tab?????????????????????????????????pop disMiss???????????????????????????
     */
    private void contralTabClick() {
        if(!isDestroy){
            LinearLayout home = getActivity().findViewById(R.id.tab_home_two);
            LinearLayout shopping = getActivity().findViewById(R.id.tab_buy);
            LinearLayout aty = getActivity().findViewById(R.id.tab_aty);
            LinearLayout mine = getActivity().findViewById(R.id.tab_mine);
            home.setClickable(false);
            shopping.setClickable(false);
            aty.setClickable(false);
            mine.setClickable(false);
        }

    }
    /*
    ????????????pop????????????
     */
    private void initBirthdayGift(final String redId) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//??????LayoutInflater??????
        View inflate = inflater.inflate(R.layout.birthday_gift_pop, null);//?????????????????????
        final LinearLayout birthdayLinear=inflate.findViewById(R.id.birthday_gift_linear);
        final TextView   birthdayPrice=inflate.findViewById(R.id.birthday_gift_price);
        birthdayLinear.setBackgroundResource(R.drawable.srhb_2);
        birthdayPrice.setVisibility(View.GONE);
        PopupWindow   BirthdayGiftPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//?????????popupWindow
        BirthdayGiftPopupWindow.setAnimationStyle(R.style.popupAnimation);//??????????????????
        BirthdayGiftPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // ??????????????????
        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
        //    contralTabClick();
        //????????????,??????????????????
        BirthdayGiftPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
                birthdayIdList.remove(redId);
                if(birthdayIdList.size()==0){
                    Message obtain = Message.obtain();
                    obtain.what=REQUEST_REGISTER_RED;
                    handler.sendMessage(obtain);
                    //          initTabClick();
                }

            }
        });
        birthdayLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
                HashMap<String, Object> data = new HashMap<>();
                data.put("red_id",redId);
                data.put("user_id",user_id);
                Utils.docking(data, RequestUtil.REQUEST_l12, 0, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content) {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        String message = jsonObject.getString("message");
                        if(Utils.getString(R.string.??????????????????).equals(message)){
                            BigDecimal redPrice = jsonObject.getBigDecimal("redPrice");
                            birthdayLinear.setBackgroundResource(R.drawable.ylsrhb_2);
                            birthdayPrice.setText(redPrice+"");
                            birthdayPrice.setVisibility(View.VISIBLE);
                            showToast(message);
                        }
                        else{
                            birthdayPrice.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void failed(MessageHead messageHead) {
                        showToast(messageHead.getInfo());
                    }
                });
            }
        });
    }
    /*
    ?????????????????????
     */
    private void initPaoMa() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",15);
        data.put("type",3);//0.logo  1?????????  2?????? 3????????????  4????????????
        Utils.docking(data, RequestUtil.REQUEST_33r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                initRefreshStatus(SuccessType.PAOMA);
                paomaStrDataList.clear();

                HomeAtyEntity homeAtyEntity = JSONArray.parseObject(content, HomeAtyEntity.class);
                List<HomeAtyEntity.ExtensionNoticeInfoListBean> extensionNoticeInfoList = homeAtyEntity.getExtensionNoticeInfoList();
                for (int i = 0; i < extensionNoticeInfoList.size(); i++) {
//                    noticeModelArrayList.add(extensionNoticeInfoList.get(i));
                    paomaStrDataList.add(extensionNoticeInfoList.get(i).getTheme());
                }

                paomaTv.startWithList(paomaStrDataList, R.anim.anim_bottom_in, R.anim.anim_top_out);
            }

            @Override
            public void failed(MessageHead messageHead) {
                refreshLayout.finishRefresh(false);
            }
        });
    }
    @Override
    public void onClick(View v) {
        MainActivity mainActivity  = (MainActivity) getActivity();
        HomeTwoFragment homeTwoFragment = (HomeTwoFragment) getParentFragment();
        switch (v.getId()){
            case R.id.lottery_more_iv:
            case R.id.lottery_more_tv:
   /*            if(homeTwoFragment!=null){
                   homeTwoFragment.getmViewPager().setCurrentItem(1);
               }*/
                mainActivity.tabAtyClick();
                break;
            case R.id.movie_more_tv:
            case R.id.movie_more_iv:
                mainActivity.tabBuyClick(true);
//                mainActivity.transaction.commit();
//               homeTwoFragment.mViewPager.setCurrentItem(1);
                break;

            case R.id.notice_more_linear:
                startActivity(new Intent(getContext(),MineMessageActivity.class));
                break;
            case R.id.make_money_iv:
//                startActivity(new Intent(getContext(),InviteAndMakeMoneyActivity.class));
                if(LoginIntentUtil.isLogin(getContext())){
                    InviteAndMakeMoneyActivity.startAty(getContext(),"");
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }
                break;
            default:
                break;
        }
    }

}
