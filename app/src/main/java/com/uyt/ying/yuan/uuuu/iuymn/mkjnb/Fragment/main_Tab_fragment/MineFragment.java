package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.VisitorSafeCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.AgentCenter2Activity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.AgentCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.BannerWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.OnLineKeFuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.AboutUsActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.FeedBackActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.HelpGuideActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MineFollowActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MineIntegralActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MineMessageActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.MyEquipmentActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.TodayWinOrLoseActvity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys.UserInfoActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.GameReportActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineDealActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.QuotaChangeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.WantToWithdrawActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter.SafeCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.yueBao_activity.YueBaoActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.MineRecyclerAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter.BannerViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BannerData;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.SignInPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ClearCache;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ScreenUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import org.greenrobot.eventbus.EventBus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Headers;


public class MineFragment extends BaseFragment implements View.OnClickListener {
    private Unbinder unbinder;
    @BindView(R.id.mine_fragment_recycler)
    RecyclerView mine_fragment_recycler;
    MineRecyclerAdapter mineRecyclerAdapter;
    ArrayList<MineInfoEntity> mineInfoEntityArrayList = new ArrayList<>();
    private int status;
    private Long userId;
    private int isagent;
    private Long letterUnreadNum;
    private String phone;
//    private String bankCard;
    private boolean isCreat = true;
    private boolean isDestroy = false;
    private BannerViewPager<BannerData.ExtensionNoticeInfoListBean, BannerViewHolder> mBannerViewPager;   //轮播图
    private List<BannerData.ExtensionNoticeInfoListBean> bannerDataList=new ArrayList<>();   //轮播图数据
    LinearLayout mine_fragment_banner_linear;
    ImageView mine_title_iv;
    TextView name_tv;
    TextView username_tv;
    TextView copy_name_tv;
    TextView amount_tv;
    TextView commission_tv;
    ImageView mine_level_iv;
    ImageView kefu_iv;
    LinearLayout to_invest_linear;
    LinearLayout to_with_draw_linear;
    LinearLayout to_quota_change_linear;
    LinearLayout to_deal_note_linear;
    LinearLayout to_game_report_linear;
    ImageView mall_centert_iv;
    ImageView sign_in_center_iv;
    ConstraintLayout user_info_constrainLayout;
    Button exit_login_btn;
    public  TextView version_name_tv;
    private List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> memberBankInfoVoList;
    private CommonTipPop commonTipPop;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        status = SharePreferencesUtil.getInt(getContext(), "yueBaoStatus", 0);
//        ActionBarUtil.initMainActionbar(getActivity(),view.findViewById(R.id.mine_actionbar_linear),R.color.action_bar_color);
        userId = SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        isagent= SharePreferencesUtil.getInt(getContext(),"isagent",0);
        initRecycler();
        getMessageNum();//获取私信数,在我的消息中显示消息条数
        initRecycleData();//添加recycleView item
        getYueBaoStatus();//获取余额宝状态,为开启时,添加余额宝item
        getInfo(true);//获取个人信息 资金信息
        requestBannerData();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
//        getMoney();
        if(!isCreat){
            letterUnreadNum = SharePreferencesUtil.getLong(getContext(), "letterUnreadNum", 0L);//私信数
            initRecycleData();//点击信息或公告返回后,需要更新消息数
            mineRecyclerAdapter.notifyDataSetChanged();
            updateMoney();
            updatePerson();
            Utils.RequestUsingEquipment(getActivity(),this);
        }
        isCreat=false;
    }



    @Override
    public void onDestroy() {
        isDestroy=true;
        super.onDestroy();
    }
    /*
    轮播图数据
     */
    private void requestBannerData() {
        HashMap<String,Object >  dataBanner = new HashMap<>();
        dataBanner.put("pageNo", "1");
        dataBanner.put("pageSize", "10");
        dataBanner.put("type", "9");
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.BANNER_DATA, dataBanner, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                bannerDataList.clear();
                BannerData parseObject = JSONObject.parseObject(result, BannerData.class);
                List<BannerData.ExtensionNoticeInfoListBean> extensionNoticeInfoList = parseObject.getExtensionNoticeInfoList();
                if(extensionNoticeInfoList.size()==0){
                    mine_fragment_banner_linear.setVisibility(View.GONE);
                }else {
                    mine_fragment_banner_linear.setVisibility(View.VISIBLE);
                }
                bannerDataList.addAll(extensionNoticeInfoList);
                mBannerViewPager
                        .setIndicatorVisibility(View.VISIBLE)
                        //轮播切换时间
                        .setInterval(2000)
                        //是否开启循环
                        .setCanLoop(true)
                        //是否开启自动轮播
                        .setAutoPlay(true)
                        //设置圆角
                        .setRoundCorner(7)
                        //指示器颜色
                        .setIndicatorColor(ContextCompat.getColor(getContext(),R.color.white), ContextCompat.getColor(getContext(),R.color.black))
                        .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                        //指示器位置
                        .setIndicatorGravity(IndicatorGravity.END)
                        //设置指示器的宽度/直径
                        .setIndicatorWidth(10)
                        //设置指示器样式( DASH:矩形  CIRCLE:圆点)
                        .setIndicatorStyle(IndicatorStyle.CIRCLE)
                        //页面滚动时间
                        .setScrollDuration(1000)
                        //绑定banner视图,以及数据的加载方式
                        .setHolderCreator(BannerViewHolder::new)
                        //点击事件
                        .setOnPageClickListener(position -> {
                            // BannerData bannerData = bannerDataList.get(position);
                            //页面跳转
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
                                    }
                                }

                            }else{
                                LoginIntentUtil.toLogin(getActivity());
                            }


                        })
                        //绑定数据源
                        .create(bannerDataList);
            }

            @Override
            public void onFailed(String msg) {

            }
        });

    }

    private void updatePerson() {
        /*
        用户信息相关
         */
        HashMap<String, Object> aboutPerson = new HashMap<>();//REQUEST_06rzq
        long userId = SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        aboutPerson.put("user_id",userId);
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_13r, aboutPerson, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                if(!isDestroy){
                    SharePreferencesUtil.putString(getContext(),"userInfo",result);//储存用户信息
                    JSONObject jsonObject = JSONObject.parseObject(result);

                    JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                /*
                用户头像
                 */
                    String image = memberInfo.getString("image");
                    String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                    String finalImgUrl="";
                    if(!StringMyUtil.isEmptyString(image)){//新注册的账号没有image字段,需要给一个默认头像
                        image =image.replace(firstImageUrl,"");
                        finalImgUrl = firstImageUrl+image;
                        SharePreferencesUtil.putString(getContext(),"oldUserImage",finalImgUrl);
                    }else{//默认头像
//                    finalImgUrl="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1562391789&di=85d934dfaf6e65bad4738912e6a174d3&src=http://hbimg.b0.upaiyun.com/69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658";
                        finalImgUrl=Utils.getString(R.string.沒有头像);
                    }

                  /*
                  用户名
                  */
                    String nickname = memberInfo.getString("nickname");
                    username_tv.setText(nickname);
                    SharePreferencesUtil.putString(getContext(),"nickname",nickname);
                  /*
                用户等级
                 */
                    Integer pointGrade = memberInfo.getInteger(CommonStr.GRADE);
                    SharePreferencesUtil.putInt(getContext(),CommonStr.GRADE,pointGrade+1);
//                    vipNum.setText("VIP"+(pointGrade+1));
                    GlideLoadViewUtil.FLoadNormalView(MineFragment.this,Utils.checkImageUrl(Utils.getLevelUrl(pointGrade+1)),mine_level_iv);
                /*
                用户昵称
                 */
                    String userNickName = memberInfo.getString("userNickName");
                    SharePreferencesUtil.putString(getContext(),"userNickName",userNickName);
                    name_tv.setText(userNickName);
                    //昵称修改次数
                    Integer userNickNameNums = memberInfo.getInteger("userNickNameNums");//昵称可修改次数
                    Integer userNickNameUseNum = memberInfo.getInteger("userNickNameUseNum");//昵称已经修改次数
                    SharePreferencesUtil.putInt(MyApplication.getInstance(),"nickNameCount",userNickNameNums-userNickNameUseNum);
                /*
                手机号
                用来判断有没有绑定手机
                 */
                    phone = memberInfo.getString("phone");
                    SharePreferencesUtil.putString(getContext(),"phone",phone);

//                /*
//                安全密码
//                用来判断有没有设置安全密码
//                 */
                    String paypassword = memberInfo.getString("paypassword");
                    SharePreferencesUtil.putString(getContext(),"havaPaypassword",paypassword);
                /*
                银行卡号 和银行名称
                用于余额提现界面和银行卡管理的显示
                 */
//                    bankCard = memberInfo.getString("bankCard");//银行卡号
                    UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                    memberBankInfoVoList = userInfoEntity.getMemberInfo().getMemberBankInfoVoList();//银行卡列表


                    String bankName = memberInfo.getString("bankName");//银行名称
//                    SharePreferencesUtil.putString(getContext(),"bankCard",memberBankInfoVoList);
                    SharePreferencesUtil.putString(getContext(),"bankName",bankName);
                    /*
                    是否是测试会员  0 否 1是
                     */
                    String isTest = memberInfo.getString("isTest");
                    SharePreferencesUtil.putString(getContext(),"isTest",isTest);

                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private void updateMoney() {
    /*
    用户资金明细相关
     */
        HashMap<String, Object> dataMoney = new HashMap<>();//REQUEST_06rzq
        long user_id = SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        dataMoney.put("user_id",user_id);
        Utils.docking(dataMoney, RequestUtil.REQUEST_06rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                SharePreferencesUtil.putString(getContext(),"moneyString",content);
                JSONObject jsonObject = JSONObject.parseObject(content);

                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                /*
                用户资金
                 */
                String amount = memberMoney.getString("amount");
                amount_tv.setText(amount);
                /*
                用户佣金
                 */
                BigDecimal commission = memberMoney.getBigDecimal("commission");
                commission_tv.setText(commission+"");
                /*
                成长积分
                 */
                Long point = memberMoney.getLong("point");
                SharePreferencesUtil.putLong(getContext(),"point",point);
            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    private void getYueBaoStatus() {
        //      String appName = SharePreferencesUtil.getString(getContext(), "appName", "");
//        if(appName.equals(Utils.getString(R.string.迅博彩票))){
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",userId);
        Utils.docking(data, RequestUtil.YUEBAO_INFO, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONObject settingVo = jsonObject1.getJSONObject("settingVo");
                //0关闭;1禁止转入;2开启
                int  status= settingVo.getInteger("status");
                SharePreferencesUtil.putInt(getContext(),"yueBaoStatus",status);
                boolean haveYueBao=false;

                for (int i = 0; i < mineInfoEntityArrayList.size(); i++) {
                    if(mineInfoEntityArrayList.get(i).getRemark().equals(Utils.getString(R.string.余额宝))){
                        haveYueBao=true;
                    }
                }
                //余额宝未关闭,且列表中没有余额宝item,添加item
                if(status!=0){
                    if(!haveYueBao){
                        mineInfoEntityArrayList.add(1,new MineInfoEntity(Utils.getString(R.string.余额宝),R.drawable.yeb_icon,0L));
                        mineRecyclerAdapter.notifyDataSetChanged();
                    }
                }
                //余额宝关闭且列表中有余额宝item,移除
                else {
                    if(haveYueBao){
                        mineInfoEntityArrayList.remove(1);
                        mineRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    private void getMessageNum() {
        letterUnreadNum = SharePreferencesUtil.getLong(getContext(), "letterUnreadNum", 0L);//私信数
    }

    private void initRecycleData() {

        mineInfoEntityArrayList.clear();
            addCommonItem(status);
            int size = mineInfoEntityArrayList.size();
            MineInfoEntity mineInfo = new MineInfoEntity(Utils.getString(R.string.我的关注), R.drawable.wdgz_icon,  0L);
            mineInfoEntityArrayList.add(size-3,mineInfo);

    }

    private void requestHBParameter() {
        Utils.docking(new HashMap<>(), RequestUtil.HB_PARAMETER, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                RongcloudHBParameter hbParameter = JSONObject.parseObject(content, RongcloudHBParameter.class);
                RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
                int zxHBSwitch = rongcloudHBParameter.getZxHBSwitch();
//                if(zxHBSwitch==1){
                String zxHBGameClassIds = rongcloudHBParameter.getZxHBGameClassIds();
                HbGameClassModel instance = HbGameClassModel.getInstance();
                instance.setHbParameter(hbParameter);
                instance.setGameIdListStr(zxHBSwitch==1?zxHBGameClassIds:"");
                EventBus.getDefault().postSticky(instance);
                addItem(status, hbParameter);
//                }
            }
            @Override
            public void failed(MessageHead messageHead) {
                addItem(status, null);
            }
        });

    }

    private void addCommonItem(int status) {
        mineInfoEntityArrayList.clear();
        RongcloudHBParameter hbParameter = HbGameClassModel.getInstance().getHbParameter();
        if(hbParameter==null){
            requestHBParameter();
        }
        addItem(status, hbParameter);
    }

    private void addItem(int status, RongcloudHBParameter hbParameter) {

        int quYueHBSwitch =hbParameter==null?0: hbParameter.getRongcloudHBParameter().getQuYueHBSwitch();
        if(isagent==0){
            if(quYueHBSwitch==1){
                mineInfoEntityArrayList.add(new MineInfoEntity(getString(R.string.我要赚钱),R.drawable.wyzq_icon,0L));
            }
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.个人信息),R.drawable.g,0L));
            //余额宝没关闭,添加银行卡
            if(status!=0){
                mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.余额宝),R.drawable.yeb_icon,0L));
            }
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.安全中心),R.drawable.aqzx,0L));
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.今日盈亏),R.drawable.yinkui,0L));
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.我的积分),R.drawable.wdjf_icon,0L));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.我的消息),R.drawable.wdxx_icon,letterUnreadNum));
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.我的装备),R.drawable.wdxx_icon,letterUnreadNum));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.意见反馈),R.drawable.yjfk_icon,letterUnreadNum));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.帮助指南),R.drawable.bzzn_icon,0L));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.关于我们),R.drawable.gzwm_icon,0L));

        }else {
            if(quYueHBSwitch==1){
                mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.我要赚钱),R.drawable.wyzq_icon,0L));
            }
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.个人信息),R.drawable.mine_mine,0L));
            if(status!=0){
                mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.余额宝),R.drawable.yeb_icon,0L));
            }
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.安全中心),R.drawable.aqzx,0L));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.代理中心),R.drawable.dlzx_icon,0L));
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.今日盈亏),R.drawable.yinkui,0L));
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.我的积分),R.drawable.wdjf_icon,0L));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.我的消息),R.drawable.wdxx_icon,letterUnreadNum));
//            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.我的装备),R.drawable.wdxx_icon,letterUnreadNum));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.意见反馈),R.drawable.yjfk_icon,letterUnreadNum));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.帮助指南),R.drawable.bzzn_icon,0L));
            mineInfoEntityArrayList.add(new MineInfoEntity(Utils.getString(R.string.关于我们),R.drawable.gzwm_icon,0L));
        }
    }

    private void getInfo(boolean isOnCreate) {
        //用户资金信息
        getMoney();
        //用户信息
        getPerson(isOnCreate);
    }
    /*
    个人信息activity中修改头像成功,需要更新头像
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            getPerson(false);
        }
    }

    private void getPerson(boolean isOnCreate) {
    /*
        用户信息相关
         */
        HashMap<String, Object> aboutPerson = new HashMap<>();//REQUEST_06rzq

        aboutPerson.put("user_id",userId);
        if(isOnCreate){
//            aboutPerson.put("page",1);
        }
        Utils.docking(aboutPerson, RequestUtil.REQUEST_13r,1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                if(!isDestroy){
                    SharePreferencesUtil.putString(getContext(),"userInfo",content);//储存用户信息
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                /*
                用户头像
                 */
                    String image = memberInfo.getString("image");
                    String firstImageUrl = SharePreferencesUtil.getString(getContext(), "FirstImageUrl", "");
                    String finalImgUrl="";
                    if(!StringMyUtil.isEmptyString(image)){
                        image =image.replace(firstImageUrl,"");
                        finalImgUrl = firstImageUrl+image;
                        SharePreferencesUtil.putString(getContext(),"url",image);
                    }else{//新注册的账号没有image字段,需要给一个默认头像
//                    finalImgUrl="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1562391789&di=85d934dfaf6e65bad4738912e6a174d3&src=http://hbimg.b0.upaiyun.com/69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658";
                        finalImgUrl=Utils.getString(R.string.沒有头像);
                    }

                    Glide.with(getContext())
                            .load(finalImgUrl)
                            .error(R.drawable.system_default_title)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(mine_title_iv);
                  /*
                  用户名
                  */
                    String nickname = memberInfo.getString("nickname");
                    username_tv.setText(nickname);

                    SharePreferencesUtil.putString(getContext(),"nickname",nickname);
                  /*
                用户等级
                 */
                    Integer pointGrade = memberInfo.getInteger(CommonStr.GRADE);
                    SharePreferencesUtil.putInt(getContext(),CommonStr.GRADE,pointGrade+1);
                    GlideLoadViewUtil.FLoadNormalView(MineFragment.this,Utils.checkImageUrl(Utils.getLevelUrl(pointGrade+1)),mine_level_iv);
//                    vipNum.setImageResource(Utils.getLevelDrawble(pointGrade+1));
                 /*
                用户昵称
                 */
                    String userNickName = memberInfo.getString("userNickName");
                    SharePreferencesUtil.putString(getContext(),"userNickName",userNickName);
                    name_tv.setText(userNickName);

                /*
                手机号
                用来判断有没有绑定手机
                 */
                    phone = memberInfo.getString("phone");
                    SharePreferencesUtil.putString(getContext(),"phone",phone);

//                /*
//                安全密码
//                用来判断有没有设置安全密码
//                 */
                    String paypassword = memberInfo.getString("paypassword");
                    SharePreferencesUtil.putString(getContext(),"havaPaypassword",paypassword);
                /*
                银行卡号 和银行名称
                用于余额提现界面和银行卡管理的显示
                 */
                    UserInfoEntity userInfoEntity = JSONObject.parseObject(content, UserInfoEntity.class);
                    memberBankInfoVoList = userInfoEntity.getMemberInfo().getMemberBankInfoVoList();//银行卡列表
//                    bankCard = memberInfo.getString("bankCard");//银行卡号
                    String bankName = memberInfo.getString("bankName");//银行名称
//                    SharePreferencesUtil.putString(getContext(),"bankCard",bankCard);
                    SharePreferencesUtil.putString(getContext(),"bankName",bankName);
                    /*
                    是否是测试会员  0 否 1是
                     */
                    String isTest = memberInfo.getString("isTest");
                    SharePreferencesUtil.putString(getContext(),"isTest",isTest);

                }

            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }

    private void getMoney() {
    /*
    用户资金明细相关
     */
        HashMap<String, Object> dataMoney = new HashMap<>();//REQUEST_06rzq
        long user_id = SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        dataMoney.put("user_id",user_id);
        Utils.docking(dataMoney, RequestUtil.REQUEST_06rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                SharePreferencesUtil.putString(getContext(),"moneyString",content);
                JSONObject jsonObject = JSONObject.parseObject(content);

                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                /*
                用户资金
                 */
                String amount = memberMoney.getString("amount");
                amount_tv.setText(amount+Utils.getString(R.string.元));
                /*
                用户佣金
                 */
                BigDecimal commission = memberMoney.getBigDecimal("commission");
                if(commission_tv!=null){
                    commission_tv.setText(commission.toString()+Utils.getString(R.string.元));
                }
                SharePreferencesUtil.putString(getContext(),CommonStr.COMMISION,commission+"");
                /*
                成长积分
                 */
                Long point = memberMoney.getLong("point");
                SharePreferencesUtil.putLong(getContext(),"point",point);
            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    private void initRecycler() {
        mineRecyclerAdapter = new MineRecyclerAdapter(R.layout.mine_recycler_item_layout,mineInfoEntityArrayList);
        mine_fragment_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mine_fragment_recycler.setAdapter(mineRecyclerAdapter);
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.mine_fragment_head,null);
        bindHeadView(headView);
        mineRecyclerAdapter.addHeaderView(headView);
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.mine_recycleview_foot,null);
        bindFootView(footView);
        mineRecyclerAdapter.addFooterView(footView);
        mineRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                MineInfoEntity mineInfoEntity = mineInfoEntityArrayList.get(position);
                String remark = mineInfoEntity.getRemark();
                String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");//0是测试用户 1是正式用户 -1 游客
                switch (remark){
                    case  Utils.getString(R.string.个人信息):
                        //跳转个人信息后,如果用户更换的头像,返回时需要刷新头像
                        Intent intent1 = new Intent(getContext(), UserInfoActivity.class);
                        startActivityForResult(intent1,1);
                        //  startActivity(new Intent(getContext(), K3Activity.class));
                        break;
                    case Utils.getString(R.string.余额宝):
                        YueBaoActivity.actionStart(getContext(),SharePreferencesUtil.getInt(getContext(),"yueBaoStatus",0));
                        break;
                    case Utils.getString(R.string.安全中心)://点击跳转安全中心
                        if(isTest.equals("-1")){
                            startActivity(new Intent(getContext(), VisitorSafeCenterActivity.class));
                        }else if(Utils.passwordIsEmpty()){
                            RouteUtils.skipVisitorSafeCenter(getContext());
                        }else {
                            startActivity(new Intent(getContext(), SafeCenterActivity.class));
                        }
                        break;
                    case Utils.getString(R.string.代理中心)://点击跳转代理中心
                        if(SharePreferencesUtil.getString(getContext(),"isInfiniteAgent","").equals("0")){//无限代理
                            startActivity(new Intent(getContext(), AgentCenterActivity.class));
                        }else {//非无限代理
                            startActivity(new Intent(getContext(), AgentCenter2Activity.class));
                        }
                        break;
                    case Utils.getString(R.string.今日盈亏)://点击跳转今日盈亏
                        startActivity(new Intent(getContext(), TodayWinOrLoseActvity.class));
                        break;
                    case Utils.getString(R.string.我的积分)://点击跳转我的积分
                        startActivity(new Intent(getContext(), MineIntegralActivity.class));
                        break;
                    case Utils.getString(R.string.我的消息)://点击跳转我的消息
                        Intent intent = new Intent(getContext(), MineMessageActivity.class);
                        //有未读消息时跳转,选中消息页面(默认选中消息)
                        if(letterUnreadNum!=0){
                            intent.putExtra("toMessage",true);
                        }
                        startActivity(intent);
                        break;
                    case Utils.getString(R.string.帮助指南)://点击跳转帮助指南
                        startActivity(new Intent(getContext(), HelpGuideActivity.class));
                        break;
                    case Utils.getString(R.string.关于我们)://点击跳转关于我们
                        startActivity(new Intent(getContext(), AboutUsActivity.class));
                        break;
                    case Utils.getString(R.string.我的收藏):
                        //       startActivity(new Intent(getContext(), CollectActivity.class));
                        break;
                    case Utils.getString(R.string.我的关注):
                        startActivity(new Intent(getContext(), MineFollowActivity.class));
                        break;
                    case Utils.getString(R.string.意见反馈):
                        startActivity(new Intent(getContext(), FeedBackActivity.class));
                        break;
                    case Utils.getString(R.string.我要赚钱):
//                        startActivity(new Intent(getContext(),InviteAndMakeMoneyActivity.class));
                        InviteAndMakeMoneyActivity.startAty(getContext(),"");
                        break;
                    case Utils.getString(R.string.我的装备):
                        startActivity(new Intent(getContext(), MyEquipmentActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private void bindFootView(View footView) {
        exit_login_btn = footView.findViewById(R.id.exit_login_btn);
        version_name_tv = footView.findViewById(R.id.version_name_tv);
        exit_login_btn.setOnClickListener(this);
        StringBuffer sbf = new StringBuffer();
        sbf.append(Utils.getString(R.string.版本号:));
        if (BuildConfig.DEBUG){
            sbf.append(Utils.getString(R.string.测试版 ));
        }
        sbf.append(BuildConfig.VERSION_NAME);
        version_name_tv.setText(sbf.toString());
    }

    private void bindHeadView(View headView) {
        mine_fragment_banner_linear = headView.findViewById(R.id.mine_fragment_banner_linear);
        mine_fragment_banner_linear.getLayoutParams().height = (ScreenUtils.getWight(_mActivity) - ScreenUtils.dip2px(_mActivity, 30f)) * 240/ 700;
        mBannerViewPager = headView.findViewById(R.id.mine_fragment_banner);
        mine_title_iv = headView.findViewById(R.id.mine_title_iv);
        name_tv = headView.findViewById(R.id.name_tv);
        username_tv = headView.findViewById(R.id.username_tv);
        copy_name_tv = headView.findViewById(R.id.copy_name_tv);
        amount_tv = headView.findViewById(R.id.amount_tv);
        commission_tv = headView.findViewById(R.id.commission_tv);
        mine_level_iv = headView.findViewById(R.id.mine_level_iv);
        kefu_iv = headView.findViewById(R.id.kefu_iv);
        to_invest_linear = headView.findViewById(R.id.to_invest_linear);
        to_with_draw_linear = headView.findViewById(R.id.to_with_draw_linear);
        to_quota_change_linear = headView.findViewById(R.id.to_quota_change_linear);
        to_deal_note_linear = headView.findViewById(R.id.to_deal_note_linear);
        to_game_report_linear = headView.findViewById(R.id.to_game_report_linear);
        mall_centert_iv = headView.findViewById(R.id.mall_centert_iv);
        sign_in_center_iv = headView.findViewById(R.id.sign_in_center_iv);
        user_info_constrainLayout = headView.findViewById(R.id.user_info_constrainLayout);
        user_info_constrainLayout.setOnClickListener(this);
        sign_in_center_iv.setOnClickListener(this);
        mall_centert_iv.setOnClickListener(this);
        to_game_report_linear.setOnClickListener(this);
        to_deal_note_linear.setOnClickListener(this);
        to_quota_change_linear.setOnClickListener(this);
        to_with_draw_linear.setOnClickListener(this);
        to_invest_linear.setOnClickListener(this);
        copy_name_tv.setOnClickListener(this);
        kefu_iv.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    @Override
    public void onClick(View v) {
        String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(),"isTest","");
        switch (v.getId()){
            case  R.id.exit_login_btn:
                AlertDialog isExit = new AlertDialog.Builder(getContext()).create();
                isExit.setTitle(Utils.getString(R.string.退出登录));
                isExit.setMessage(Utils.getString(R.string.确定退出登录?));
                isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClearCache.clearCache(getContext());
                        LoginIntentUtil.toLogin(getActivity());
                    }
                });
                isExit.show();
                break;
            case R.id.user_info_constrainLayout:
                startActivityForResult(new Intent(getContext(),UserInfoActivity.class),0);
                break;
            case R.id.sign_in_center_iv:
                // 签到
                SignInPop signInPop = new SignInPop(getContext(), true,MineFragment.this);
                signInPop.showAtLocation(sign_in_center_iv, Gravity.CENTER,0,0);
                ProgressDialogUtil.darkenBackground(getActivity(),0.6f);
                break;
            case R.id.mall_centert_iv:
                startActivity(new Intent(getContext(),MyEquipmentActivity.class));
                break;
            case R.id.to_game_report_linear:
                startActivity(new Intent(getContext(), GameReportActivity.class));
                break;
            case R.id.to_deal_note_linear:
                //交易记录
               startActivity(new Intent(getContext(), MineDealActivity.class));
                break;
            case R.id.to_quota_change_linear:
                //额度转换
                QuotaChangeActivity.startAty(getContext());
                break;
            case R.id.to_with_draw_linear:
//                if(StringMyUtil.isEmptyString(bankCard)){
                if(isTest.equals("-1")){
                    //游客点击
                   Utils.initSkipVisitorSafeCenterPop(getContext(),getActivity());
                }else {
                    if(memberBankInfoVoList==null||memberBankInfoVoList.size()==0){
                        if(Utils.passwordIsEmpty()){
                            initDialog(false);
                        }else {
                            initDialog(true);
                        }
                    }else{
                        startActivity(new Intent(getContext(), WantToWithdrawActivity.class));//点击跳转我要提现
                    }
                }

                break;
            case R.id.to_invest_linear:
                if(isTest.equals("-1")){
                    //游客点击
                   Utils. initSkipVisitorSafeCenterPop(getContext(),getActivity());
                }else {
                    startActivity(new Intent(getContext(), RechargeActivity.class));//点击跳转我要充值
                }
                break;
            case R.id.copy_name_tv:
                Utils.copyStr("username",username_tv.getText().toString());
                break;
            case R.id.kefu_iv:
                Intent intent = new Intent(getContext(), OnLineKeFuActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initDialog(boolean skip2SafeCenter) {
        String content;
        if(skip2SafeCenter){
            content = Utils.getString(R.string.提现前需先绑定银行卡，是否前往绑定);
        }else {
            content=Utils.getString(R.string.请先完成安全认证);
        }
        AlertDialog bindPhone = new AlertDialog.Builder(getContext()).create();
        bindPhone.setTitle(Utils.getString(R.string.温馨提示));
        bindPhone.setMessage(content);
        bindPhone.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        bindPhone.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //木有密码是使用的一键登录.需要跳转认证页面
                if(skip2SafeCenter){
                    startActivity(new Intent(getContext(), SafeCenterActivity.class));
                }else {
                    RouteUtils.skipVisitorSafeCenter(getContext());
                }
            }
        });
        bindPhone.show();
    }
}