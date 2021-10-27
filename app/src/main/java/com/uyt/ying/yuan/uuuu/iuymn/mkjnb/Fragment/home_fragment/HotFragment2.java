package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.BannerWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ChessTxWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter.LiveCenterListAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeTopGameEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveClassfyEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateSingleEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.HomeTwoFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MineMessageActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.HotTopRecyclerAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter.LiveListAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.AppUpdateModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.CollectEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.UpdateGiftAmountModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeAtyEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeLiveDataSuccessEven;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HotTopEntity;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.VersionUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Headers;

public class HotFragment2 extends BaseFragment implements View.OnClickListener {
    public final String TAG = getClass().getSimpleName();
    private MarqueeView paomaTv;//跑马灯
    private ArrayList<String> paomaStrDataList = new ArrayList<>(); //跑马灯显示数据
    private ArrayList<HomeAtyEntity.ExtensionNoticeInfoListBean> noticeModelArrayList =new ArrayList<>();    //跑马灯点击跳转数据

    /*
   分类recycleView
   */
    private RecyclerView hot_top_live_recycler;
    private LiveListAdapter liveTopListAdapter;
    private ArrayList<LiveEntity.AnchorMemberRoomListBean> liveTopBeanList = new ArrayList<>();


    private LinearLayout hot_center_linear;
    private RecyclerView hot_center_live_recycler;
    private LiveCenterListAdapter liveCenterListAdapter;
    private ArrayList<LiveEntity.AnchorMemberRoomListBean> liveCenterBeanList = new ArrayList<>();

    //分类缓存数据(购彩大厅和此页面会存入,如果有缓存,直接先取缓存的数据,然后覆盖旧的数据,避免购彩大厅中切换tab时,频繁请求接口)
    private String  navigateList;
    //视频列表recycleView
    private LiveListAdapter liveListAdapter;
    private ArrayList<LiveEntity.AnchorMemberRoomListBean> liveBeanList = new ArrayList<>();
    private Long noticeId;
    private PopupWindow messageNumPopWindow;//未读消息pop
    private boolean isDestroy=false;//当前页面是否被销毁
    private int homeCount;//mainActivity中每次点击homeTab都是使homeCount加1(配合引导页面传入的showAty判断时候要弹出活动pop)
    private boolean showAtyPop=false;//是否要显示活动弹窗
    private boolean isLogin;
    private List<String> atyIdList=new ArrayList<>();//活动弹窗中的id
    private List<String> birthdayIdList=new ArrayList<>();
    private List<String> regiesterIdList=new ArrayList<>();
    private String showPop;//后台控制是否弹出活动公告的字段
    private boolean isCreate=false;
    private boolean fromLogin;//登录成功后回到首页且第一次点击homeTab才会传入的值,避免登录成功后,每次点击首页tab都弹出
    private String atyPopJson;//活动公告的json数据,用于判断公告内容是否有变化,内容改变需要弹出pop
    private boolean isJsonChange;//比较atyPopJson后,设置的boolean值
    private LinearLayout moreNoticeLienar;
    private View view;
    private LinearLayout toMessageLinear;  //消息弹窗pop中的查看消息按钮
    SaveChessLotteryUtil saveChessLotteryUtil =  new SaveChessLotteryUtil();
    private SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private int hotTabCount;

    public MyHandler handler;
    private boolean shouidUpdate=true;
    private String downloadUrl;

    //当前app的版本号
    private long localVersionCode;
    private String localVersionName;


    private DownloadService downloadService ;
    //    private DownloadService.DownloadBinder downloadBinder;
    private DownloadService.DownloadBinder downloadBinder;

    private boolean navigateSuccess;
    private boolean bannerSuccess;
    private boolean paomaSuccess;
    private boolean centerSuccess;
    private boolean liveSuccese;

    private String downloadApkPath;
    public static int REQUEST_ATY_RED =1;
    private static int REQUEST_BIRTHDAY_RED =2;
    private static int REQUEST_REGISTER_RED =3;
    private static int REQUEST_UNREAD_MESSAGE =4;

    private static int REQUEST_ATY_POP=7;
    private static int REQUEST_LIVE_DATA =10;
    private static int REQUEST_PARAMETER=11;
    private static int REQUEST_CENTER_LIVE_DATA=12;
    private static String INSTALL_APP=Utils.getString(R.string.点击安装);
    private static String DOWNING=Utils.getString(R.string.正在下载...);

    private int pageNo=1;
    private int pageSize = 16;
    private Unbinder mUnbinder;
    private String recommendStr;
    private TextView hot_center_tv;

    private enum SuccessType{
        PAOMA,
        LIVE,
        CENTER,
        BANNER,
        NAVIGATE;
    }
    HomeAtyPop homeAtyPop;
    HomeAtyPop noticePop;
    @BindView(R.id.home_movie_recycle)
    RecyclerView home_movie_recycle;
    @BindView(R.id.hot_refresh)
    SmartRefreshLayout hot_refresh;
    @BindView(R.id.hot_top_recycler)
    RecyclerView hot_top_recycler;
    HotTopRecyclerAdapter hotTopRecyclerAdapter;
    ArrayList<HotTopEntity.ExtensionNoticeInfoListBean>hotTopEntityArrayList = new ArrayList<>();
    private ArrayList<HomeAtyEntity.ExtensionNoticeInfoListBean> atyPopDataList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot2, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//注册eventBus
        }
        localVersionCode= VersionUtils.getAppVersionCode(MyApplication.getInstance());
        localVersionName= VersionUtils.getAppVersionName(MyApplication.getInstance());
        handler=new MyHandler(new WeakReference<>(this));
        initData();
        initTopRecycler();
        initLiveRecycle();
        initRefresh();
        //请求彩种列表(用于筛选直播间彩种,成功后请求直播间数据)
        requestNavigateData(false);
        initPaoMa();//跑马灯文字信息获取
        if(showAtyPop&&homeCount==1&&hotTabCount==1){
            handler.sendEmptyMessage(REQUEST_ATY_POP);
        }
        return view;
    }

    private void initTopRecycler() {
        hotTopRecyclerAdapter  =new HotTopRecyclerAdapter(R.layout.hot_top_recycler_item,hotTopEntityArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        hot_top_recycler.setLayoutManager(layoutManager);
        hot_top_recycler.setAdapter(hotTopRecyclerAdapter);
        requestTopRecyclerData();
        hotTopRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                HotTopEntity.ExtensionNoticeInfoListBean extensionNoticeInfoListBean = hotTopEntityArrayList.get(position);
                int liveShowPage = extensionNoticeInfoListBean.getLiveShowPage();
                if(liveShowPage == 1){
                    /**
                     * 棋牌/彩票游戏
                     */
                    if(LoginIntentUtil.isLogin(getContext())){
                        String content = extensionNoticeInfoListBean.getContent();
                        HomeTopGameEntity homeTopGameEntity = JSONObject.parseObject(content, HomeTopGameEntity.class);
                        int game = homeTopGameEntity.getGame();
                        if(game >= 50){
                            /**
                             * 跳转棋牌
                             */
                            requestChessGame(homeTopGameEntity, game);
                        }else {
                            /**
                             * 跳转cp
                             */
                            ToBetAtyUtils.toBetActivity(getContext(),game,homeTopGameEntity.getTypename(),Integer.parseInt(homeTopGameEntity.getType_id()),"0",homeTopGameEntity.getIsStart()+"");
                        }
                    }else {
                        LoginIntentUtil.toLogin(getActivity());
                    }
                }else if(liveShowPage == 2){
                    /**
                     * 外链
                     */
                    BannerWebViewActivity.startActivity(getContext(),extensionNoticeInfoListBean.getLink(),extensionNoticeInfoListBean.getTheme());
                }else if(liveShowPage == 3){
                    /**
                     * 推广赚钱
                     */
                    if(LoginIntentUtil.isLogin(getContext())){
                        InviteAndMakeMoneyActivity.startAty(getContext(),"");
                    }else {
                        LoginIntentUtil.toLogin(getActivity());
                    }
                }else {
                    showToast(Utils.getString(R.string.未知类型, 无法跳转));
                }

            }
        });

    }

    private void requestChessGame(HomeTopGameEntity homeTopGameEntity, int game) {
        long id = homeTopGameEntity.getId();
        HashMap<String, Object> data = new HashMap<>();
        data.put("game",game);
        data.put("id",id);
        data.put("loginType",1);
        HttpApiUtils.CpRequest(getActivity(), HotFragment2.this, RequestUtil.PLAY_CHESS, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String url = jsonObject.getString("url");
                ChessTxWebViewActivity.startAty(getContext(),url,game,id+"");
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private void requestTopRecyclerData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo", 1);
        data.put("pageSize", 15);
        data.put("type", 11);//0.logo  1轮播图  2消息 3弹出公告  4优惠活动  7:聊天室群公告 11 首页顶部分类
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_33r, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                hotTopEntityArrayList.clear();
                HotTopEntity hotTopEntity = JSONObject.parseObject(result, HotTopEntity.class);
                hotTopEntityArrayList.addAll(hotTopEntity.getExtensionNoticeInfoList());
                hotTopRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void initRefresh() {
//        refreshLayout.setEnableLoadMore(false);
        hot_refresh.setRefreshHeader(new ClassicsHeader(getContext()));
        hot_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestTopRecyclerData();//顶部分类
                refreshLayout.resetNoMoreData();
                initPaoMa();//跑马灯文字信息获取
                requestNavigateData(true);
            }
        });
        hot_refresh.setRefreshFooter(new ClassicsFooter(getContext()));
        hot_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
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
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        ProgressDialogUtil.stop(getActivity());

        EventBus.getDefault().unregister(this);//取消eventBus注册
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
    @Override
    public void onResume() {



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

    }

    private void requestHBParameter() {
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.HB_PARAMETER, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                RongcloudHBParameter hbParameter = JSONObject.parseObject(result, RongcloudHBParameter.class);
                RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
                int zxHBSwitch = rongcloudHBParameter.getZxHBSwitch();
                String zxHBGameClassIds = rongcloudHBParameter.getZxHBGameClassIds();
                HbGameClassModel instance = HbGameClassModel.getInstance();
                instance.setHbParameter(hbParameter);
                instance.setGameIdListStr(zxHBSwitch==1?zxHBGameClassIds:"");
                EventBus.getDefault().postSticky(instance);

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
     * 更新主播收藏状态
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
     * 更新主播礼物金额
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
     * 更新是否是限定
     * @param hbGameClassModel  包含限定彩种的idList的model
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        int quYueHBSwitch = hbGameClassModel.getHbParameter().getRongcloudHBParameter().getQuYueHBSwitch();
/*        if(quYueHBSwitch==1){
            if(make_money_iv!=null){
                make_money_iv.setVisibility(View.VISIBLE);
            }
        }else {
            if(make_money_iv!=null){
                make_money_iv.setVisibility(View.GONE);
            }
        }*/
    }



    public static HotFragment2 newInstance(int position){
        Bundle bundle = new Bundle();
        HotFragment2 hotFragment = new HotFragment2();
        bundle.putInt("position",position);
        hotFragment.setArguments(bundle);
        return hotFragment;
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestLiveData(true,false,false);
    }

    static class MyHandler extends  Handler{
        WeakReference<HotFragment2> hotFragmentWeakReference;

        public MyHandler(WeakReference<HotFragment2> hotFragmentWeakReference) {
            this.hotFragmentWeakReference = hotFragmentWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HotFragment2 hotFragment = hotFragmentWeakReference.get();
            if(null!=hotFragment){
                if(!hotFragment.isDestroy){//当前页面没有被销毁才调用pop,否则bug
                    if(msg.what== REQUEST_ATY_RED){//活动公告pop dismiss后,显示活动红包pop
//                    initAtyGift();//活动红包相关配置
                        hotFragment. getAtyGift();//是否有活动红包
                    }
                    if(msg.what==REQUEST_BIRTHDAY_RED){
//                    initBirthdayGift();//生日红包相关设置
                        hotFragment. getBirthdayGift();//是否有生日红包
                    }
                    if(msg.what==REQUEST_REGISTER_RED){
                        hotFragment.   getRegisterGift();
                    }
                    if(msg.what==REQUEST_UNREAD_MESSAGE){
                        hotFragment.  initMessagePop();//未读消息pop相关配置
                        hotFragment.  getMessageNum();//是否有未读消息
                    }
                    if(msg.what== REQUEST_LIVE_DATA){
                        hotFragment.requestLiveData((Boolean) msg.obj,true,false);
                    }
                    if(msg.what ==REQUEST_CENTER_LIVE_DATA){
                        hotFragment.requestCenterLiveData();
                    }
                    if(msg.what==REQUEST_ATY_POP){
                        hotFragment.getAtyPop();
                    }
                    if(msg.what==REQUEST_PARAMETER){
                        hotFragment. requestHBParameter();
                    }
                }
            }
        }


    }



    /**
     * 请求热门数据(成功后请求直播数据)
     * @param uploadCollection
     */
    private void requestNavigateData(boolean uploadCollection) {
        if(StringMyUtil.isNotEmpty(navigateList)){
            initRefreshStatus(SuccessType.NAVIGATE);
            Message message = Message.obtain();
            message.obj=uploadCollection;
            message.what= REQUEST_LIVE_DATA;
            handler.sendMessage(message);//navigateList不为空直接请求电影数据
            handler.sendEmptyMessage(REQUEST_PARAMETER);//请求到彩种列表后,请求邀请红包限定玩法(需要根据game和type_id筛选出彩种id)
            handler.sendEmptyMessage(REQUEST_CENTER_LIVE_DATA);//请求横向直播间数据
        }
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_01dhnew, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                if(StringMyUtil.isEmptyString(navigateList)){
                    initRefreshStatus(SuccessType.NAVIGATE);
                    Message message = Message.obtain();
                    message.obj=uploadCollection;
                    message.what= REQUEST_LIVE_DATA;
                    handler.sendMessage(message);
                    handler.sendEmptyMessage(REQUEST_PARAMETER);
                    handler.sendEmptyMessage(REQUEST_CENTER_LIVE_DATA);//请求横向直播间数据

                }
                navigateList=result;
                SharePreferencesUtil.putString(getContext(),"navigateList",result);

                //处理直播间投注数据
                NavigateSingleEntity.newInstance().setNavigateEntity(JSONObject.parseObject(result, NavigateEntity.class));
            }

            @Override
            public void onFailed(String msg) {
                hot_refresh.finishRefresh(false);
            }
        });
    }




    /*
    视频列表数据
     */
    private void initLiveRecycle() {
        liveListAdapter =new LiveListAdapter(this);
        home_movie_recycle.setLayoutManager(new GridLayoutManager(getContext(),2));
        home_movie_recycle.setAdapter(liveListAdapter);
        liveListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(!LoginIntentUtil.isLogin(getContext())){
                    LoginIntentUtil.toLogin(getActivity());
                }else {
                    int curPage = ((position+1) + pageSize-1)/pageSize;
                    RouteUtils.start2LiveActivity(getContext(),liveBeanList.get(position),"",CommonStr.AREA_DEFAULT_VALUE,curPage);

                }
            }
        });
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.hot2_head_layout,null);
        liveListAdapter.addHeaderView(headView);
        bindHeadView(headView);
        initLiveTopRecycler();
        initLiveCenterRecycler();
    }



    private void bindHeadView(View view) {
        hot_center_tv =view.findViewById(R.id.hot_center_tv);
        hot_center_linear =view.findViewById(R.id.hot_center_linear);
        hot_center_live_recycler =view.findViewById(R.id.hot_center_live_recycler);
        hot_top_live_recycler =view.findViewById(R.id.hot_top_live_recycler);
        paomaTv=view.findViewById(R.id.marquee_text);
        paomaTv.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                initNoticePop();
            }
        });
        if(StringMyUtil.isNotEmpty(recommendStr) && Utils.isJson(recommendStr)){
            LiveClassfyEntity.CategoryListBean categoryListBean = JSONObject.parseObject(recommendStr, LiveClassfyEntity.CategoryListBean.class);
            hot_center_tv.setText(categoryListBean.getName());
        }
    }

    /**
     * 请求中间横向的直播间数据
     */
    private void requestCenterLiveData() {
        HashMap<String, Object> data = new HashMap<>();
        if(StringMyUtil.isNotEmpty(recommendStr) && Utils.isJson(recommendStr)){
            LiveClassfyEntity.CategoryListBean categoryListBean = JSONObject.parseObject(recommendStr, LiveClassfyEntity.CategoryListBean.class);
            data.put("categoryId", categoryListBean.getCategoryId());
        }
        data.put("pageNo",1);
        data.put("pageSize",pageSize);

        HttpApiUtils.CPnormalRequest(getActivity(),this, RequestUtil.LIVEROOM_LIST, data,new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                initRefreshStatus(SuccessType.CENTER);
                liveCenterBeanList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//解析json
                JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
                LiveEntity LiveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.AnchorMemberRoomListBean> anchorMemberRoomList = LiveEntity.getAnchorMemberRoomList();
                EventBus.getDefault().post(new HomeLiveDataSuccessEven(true));

                /**
                 * 后台每次添加一条新视频数据第一次请求会返回 {"message":"success","status":"success"} 这里做判断, 避免崩溃
                 */
                if(anchorMemberRoomList!=null){
                    int size = anchorMemberRoomList.size();
                    if(size!=0){
                        hot_center_linear.setVisibility(View.VISIBLE);
                    }else {
                        hot_center_linear.setVisibility(View.GONE);
                    }

                    handlerLiveData(lotteryArray, anchorMemberRoomList, size, 0, liveCenterBeanList);
                    liveCenterListAdapter.setList(liveCenterBeanList);
                }else {
                    hot_center_linear.setVisibility(View.GONE);
                }



            }
            @Override
            public void onFailed(String msg) {
            }

        });
    }
    /**
     * 请求直播数据
     * @param uploadCollection 是否需要更新视频收藏状态
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
                JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//解析json
                JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
                if(!isLoadMore){
                    liveBeanList.clear();
                    liveTopBeanList.clear();
                }else {
                    hot_refresh.finishLoadMore(true);
                }
                LiveEntity LiveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.AnchorMemberRoomListBean> anchorMemberRoomList = LiveEntity.getAnchorMemberRoomList();
                EventBus.getDefault().post(new HomeLiveDataSuccessEven(true));
                /**
                 * 后台每次添加一条新视频数据第一次请求会返回 {"message":"success","status":"success"} 这里做判断, 避免崩溃
                 */
                if(anchorMemberRoomList!=null){
                    int size = anchorMemberRoomList.size();
                    if(size==0){
                        if(isLoadMore){
                            hot_refresh.finishLoadMoreWithNoMoreData();
                        }
                    }else {
                        if(isLoadMore){
                            hot_refresh.finishLoadMore(true);
                        }
                    }
                    /**
                     * 处理直播间数据
                     */
                    if(pageNo==1){
                        if(size<=4){
                            /**
                             * pageNo==1 size<=4时 所有直播间都放在上面
                             */
                            liveTopBeanList.clear();
                            handlerLiveData(lotteryArray, anchorMemberRoomList, size, 0, liveTopBeanList);
                        }else {
                            /**
                             * pageNo==1 size>4时 前4个直播间放上面, 剩下的在添加
                             */
                            handlerLiveData(lotteryArray, anchorMemberRoomList, 4, 0, liveTopBeanList);
                            handlerLiveData(lotteryArray, anchorMemberRoomList, size, 4, liveBeanList);
                        }
                        liveListAdapter.setList(liveBeanList);
                        liveTopListAdapter.setList(liveTopBeanList);
                    }else {
                        handlerLiveData(lotteryArray, anchorMemberRoomList, size, 0, liveBeanList);
                        liveListAdapter.setList(liveBeanList);
                    }
                }
            }
            @Override
            public void onFailed(String msg) {
                if(isRefresh){
                    hot_refresh.finishRefresh(false);
                }
                if(isLoadMore){
                    hot_refresh.finishLoadMore(false);
                    pageNo--;

                }
            }

        });
    }

    private void handlerLiveData(JSONArray lotteryArray, List<LiveEntity.AnchorMemberRoomListBean> anchorMemberRoomList, int size, int startIndex, ArrayList<LiveEntity.AnchorMemberRoomListBean> liveTopBeanList) {
        for (int i = startIndex; i < size; i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = anchorMemberRoomList.get(i);
            long cpId = anchorMemberRoomListBean.getCpId();
            for (int j = 0; j < lotteryArray.size(); j++) {
                JSONObject object = lotteryArray.getJSONObject(j);
                long lotteryId = object.getLong("id");
                if (lotteryId == cpId) {
                    String typename = object.getString("typename");
                    String game = object.getString("game");
                    anchorMemberRoomListBean.setGame(game);
                    anchorMemberRoomListBean.setLotteryName(typename);
                    if (!liveTopBeanList.contains(anchorMemberRoomListBean)) {
                        liveTopBeanList.add(anchorMemberRoomListBean);
                    }
                    break;
                }
            }
        }
    }

    /*
    intent  sharePreferencesUtil Argument 数据获取
     */
    private void initData() {
        recommendStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.HOME_RECOMMEND, "");
        atyPopJson=SharePreferencesUtil.getString(getContext(),"atyPopJson","");
        navigateList=SharePreferencesUtil.getString(getContext(),"navigateList","");
        showPop=SharePreferencesUtil.getString(getContext(),"showPop","");
        homeCount = SharePreferencesUtil.getInt(getActivity(), "HomeCount", 0);
        HomeTwoFragment homeTwoFragment= (HomeTwoFragment) getParentFragment();
        hotTabCount=homeTwoFragment.hotTabCount;
        showAtyPop = getActivity().getIntent().getBooleanExtra("showAtyPop",false);//得到mainActivyty中传递的是否显示活动pop的参数
        Bundle bundle = getArguments();
        if(bundle!=null){
            fromLogin=bundle.getBoolean("fromLogin");
        }
    }

    private void initLiveCenterRecycler() {
        hot_center_live_recycler.setHasFixedSize(true);
        hot_center_live_recycler.setNestedScrollingEnabled(false);
        liveCenterListAdapter = new LiveCenterListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        hot_center_live_recycler.setLayoutManager(linearLayoutManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//        hot_center_live_recycler.setLayoutManager(gridLayoutManager);
        hot_center_live_recycler.setAdapter(liveCenterListAdapter);
        liveCenterListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Object o = liveCenterBeanList.get(position);
                if(o instanceof LiveEntity.AnchorMemberRoomListBean){
                    if(!LoginIntentUtil.isLogin(getContext())){
                        LoginIntentUtil.toLogin(getActivity());
                    }else {
                        RouteUtils.start2LiveActivity(getContext(), (LiveEntity.AnchorMemberRoomListBean) o,"",CommonStr.AREA_DEFAULT_VALUE,1);
                    }
                }

            }
        });

    }

    /**
     * 顶部4个直播间列表
     * */
    private void initLiveTopRecycler() {
        hot_top_live_recycler.setHasFixedSize(true);
        hot_top_live_recycler.setNestedScrollingEnabled(false);
        liveTopListAdapter = new LiveListAdapter(this);
        hot_top_live_recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        hot_top_live_recycler.setAdapter(liveTopListAdapter);
        liveTopListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(!LoginIntentUtil.isLogin(getContext())){
                    LoginIntentUtil.toLogin(getActivity());
                }else {
                    RouteUtils.start2LiveActivity(getContext(),liveTopBeanList.get(position),"",CommonStr.AREA_DEFAULT_VALUE,1);
                }
            }
        });
    }


    private void initRefreshStatus(SuccessType successType) {
        switch (successType){
            case NAVIGATE:
                navigateSuccess =true;
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
            case CENTER:
                centerSuccess =true;
                break;
        }
        if(navigateSuccess &&paomaSuccess&&liveSuccese&&centerSuccess/*&&bannerSuccess*/){
            hot_refresh.finishRefresh(true);
            navigateSuccess =false;
            paomaSuccess=false;
            liveSuccese=false;
            bannerSuccess=false;
            centerSuccess=false;
        }
    }

    /*
    未读消息pop
     */
    private void initMessagePop() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//获得LayoutInflater对象
        View inflate = inflater.inflate(R.layout.home_message_num_pop, null);//读取布局管理器
        messageNumPopWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        messageNumPopWindow.setAnimationStyle(R.style.popupAnimation);//设置进出动画
//        点击图片  弹回pop
        inflate.findViewById(R.id.message_dismiss).setOnClickListener(v->messageNumPopWindow.dismiss());

        //点击查看消息,跳转到我的消息
        toMessageLinear=inflate.findViewById(R.id.to_message_aty);
        toMessageLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPoppu(view);
                try {
                    Intent intent = new Intent(getActivity(), MineMessageActivity.class);
                    intent.putExtra("toMessage",true);//我的消息界面通过toMessage判断切换哪个界面
                    startActivity(intent);
                    messageNumPopWindow.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        //弹窗消失,恢复背景亮度
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
    是否有私信
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
                    messageNumPopWindow.showAtLocation(view, Gravity.CENTER, 0, 0); // 显示弹出窗口
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
    是否有活动红包
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
                    //有生日红包,显示pop
                    if(!StringMyUtil.isEmptyString(birthdayGiftId)){
                        String[]  split = birthdayGiftId.split(",");
                        List<String> strings = Arrays.asList(split);
                        birthdayIdList = new ArrayList(strings);
                    }
                    for (int j = 0; j < birthdayIdList.size(); j++) {
                        initBirthdayGift(birthdayIdList.get(j));
                    }

                } else{
                    //没有生日红包,通知显示下一个pop
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
    得到活动公告pop的数据
     */
    private void getAtyPop() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",15);
        data.put("type",3);//0.logo  1轮播图  2消息 3弹出公告  4优惠活动  7:聊天室群公告
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

                initHomeAtyPop();//活动弹窗相关配置
                if(homeAtyPop!=null){
                    homeAtyPop.getHomeAtyRecyclerAdapter().notifyDataSetChanged();
                }
//                homePopRecycleAdapter.notifyDataSetChanged();
                    /*showAtyPop 判断是否是开屏图跳转而来
                     homeCount mainActivity中 底部Utils.getString(R.string.首页)tab点击的次数
                     showPop系统参数中获取的是否需要弹出pop的判断参数
                     fromLogin 是否是登录成功后,且第一次点击homeTab跳转而来
                     isJsonChange 活动内容是否有变化
                     */
                // 第一次进入页面时,用户没有点击底部tab切换页面,而是点击顶部tab切换时,只有第一次选中Utils.getString(R.string.热门)tab时才弹出pop (在tab选中时,将变量count++,参考homeCount的处理方式)
                if(showAtyPop&&homeCount==1&&showPop.equals("0")&&hotTabCount==1||fromLogin&&showPop.equals("0")||isJsonChange&&showPop.equals("0")){//后台开启弹出pop,从开屏页面跳转而来,且是第一次点击首页tab才弹出活动pop,或者公告内容有变化,或者登录成功跳转而来
                    showAtyPop(view);
                    //       contralTabClick();

                }else{
                    Message message = Message.obtain();
                    message.what= REQUEST_ATY_RED;//弹回当前pop才显示下一个pop
                    handler.sendMessage(message);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {

                String pcLogo = SharePreferencesUtil.getString(getActivity(), "pcLogo", "");
                String firstImgurl = Utils.getFirstImgurl(getActivity());//图片前缀
                String url = firstImgurl + pcLogo;
            }
        });
    }
    /**
     活动公告pop相关配置
     */
    private void initHomeAtyPop() {
        if(homeAtyPop==null&&getContext()!=null){
            homeAtyPop = new HomeAtyPop(getContext(),true,this,atyPopDataList);
            homeAtyPop.setmOnDissmissListener(new BasePopupWindow2.OnDissmissListener() {
                @Override
                public void onDismiss() {
                    Message message = Message.obtain();
                    message.what=REQUEST_ATY_RED;//弹回当前pop才显示另一pop
                    handler.sendMessage(message);
                }
            });
        }

    }

    /**
     活动公告pop相关配置
     */
    private void initNoticePop() {
        noticePop = new HomeAtyPop(getContext(),true,this,noticeModelArrayList);
        noticePop.getHomeAtyRecyclerAdapter().notifyDataSetChanged();
        noticePop.showAtLocation(view,Gravity.CENTER,0,0);
        ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
    }
    /*
    弹出活动公告pop(延时弹出)
     */
    private void showAtyPop(final View view) {
        hot_top_live_recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
   /*             if(!getActivity().isFinishing()&&!isDestroy) {
                    atyPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // 显示弹出窗口
                }*/
                if(atyPopDataList.size()!=0&&homeAtyPop!=null&&!homeAtyPop.isShowing()){
                    homeAtyPop.showAtLocation(view,Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(getActivity(),0.7f);
                }else {
                    Message message = Message.obtain();
                    message.what= REQUEST_ATY_RED;//不用弹出公告弹窗直接请求活动红包
                    handler.sendMessage(message);
                }
            }
        },300);
    }
    /*
    是否有活动红包
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
    活动红包pop相关配置
     */
    private void initAtyGift(final String redId) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//获得LayoutInflater对象
        View inflate = inflater.inflate(R.layout.aty_gift_pop, null);//读取布局管理器
        final TextView atyGiftPrice=inflate.findViewById(R.id.aty_gift_price);
        final LinearLayout  atyGiftLinear= inflate.findViewById(R.id.aty_gift_linear);
        atyGiftLinear.setBackgroundResource(R.drawable.hdhb_2);
        atyGiftPrice.setVisibility(View.GONE);
        PopupWindow atyGiftPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        atyGiftPopupWindow.setAnimationStyle(R.style.popupAnimation);//设置进出动画
        atyGiftPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // 显示弹出窗口
        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
        //     contralTabClick();//避免pop未消失时,点击底部tab,导致pop出现在其他界面,且dismiss后,将其他界面的背景变暗
        //弹窗消失,恢复背景亮度
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
                        if(Utils.getString(R.string.红包领取成功).equals(message)){
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
   是否有注册红包
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
                            Utils.logE(TAG,Utils.getString(R.string.failed:获取注册红包列表失败));
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
    新人注册红包pop相关配置
     */
    private void initRegisterGift(final String redId) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//获得LayoutInflater对象
        View inflate = inflater.inflate(R.layout.register_gift_pop, null);//读取布局管理器
        final TextView atyGiftPrice=inflate.findViewById(R.id.register_gift_price);
        final LinearLayout  atyGiftLinear= inflate.findViewById(R.id.register_gift_linear);
        atyGiftLinear.setBackgroundResource(R.drawable.register_gift);
        atyGiftPrice.setVisibility(View.GONE);
        PopupWindow atyGiftPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        atyGiftPopupWindow.setAnimationStyle(R.style.popupAnimation);//设置进出动画
        atyGiftPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // 显示弹出窗口
        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
        //     contralTabClick();//避免pop未消失时,点击底部tab,导致pop出现在其他界面,且dismiss后,将其他界面的背景变暗
        //弹窗消失,恢复背景亮度
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
                        if(Utils.getString(R.string.红包领取成功).equals(message)){
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
    控制底部tab不可点击,避免在首页还有pop没有disMiss时,用户点击底部的tab切换页面并在其他页面将pop disMiss时屏幕亮度不会回复
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
    生日红包pop相关设置
     */
    private void initBirthdayGift(final String redId) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());//获得LayoutInflater对象
        View inflate = inflater.inflate(R.layout.birthday_gift_pop, null);//读取布局管理器
        final LinearLayout birthdayLinear=inflate.findViewById(R.id.birthday_gift_linear);
        final TextView   birthdayPrice=inflate.findViewById(R.id.birthday_gift_price);
        birthdayLinear.setBackgroundResource(R.drawable.srhb_2);
        birthdayPrice.setVisibility(View.GONE);
        PopupWindow   BirthdayGiftPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        BirthdayGiftPopupWindow.setAnimationStyle(R.style.popupAnimation);//设置进出动画
        BirthdayGiftPopupWindow.showAtLocation(view,Gravity.CENTER, 0, 0); // 显示弹出窗口
        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
        //    contralTabClick();
        //弹窗消失,恢复背景亮度
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
                        if(Utils.getString(R.string.红包领取成功).equals(message)){
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
    请求跑马灯内容
     */
    private void initPaoMa() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",15);
        data.put("type",3);//0.logo  1轮播图  2消息 3弹出公告  4优惠活动
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
                hot_refresh.finishRefresh(false);
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
