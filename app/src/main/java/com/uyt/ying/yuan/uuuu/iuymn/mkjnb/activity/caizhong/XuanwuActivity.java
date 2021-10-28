
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.GfHeadRecy1Adapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.GfHeadRecy2Adapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RecyclerViewGridAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.XuanwuGfP2Adapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.XuanwuGfPAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.XuanwuParentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GfBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KJCountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MemberMoney;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NewplayModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.State;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.XuanwuGroup;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.XuanwuGroupBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.XuanwuLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.BetDialog;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.JiangjinDialog;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.AnimUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CustomPopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MoneyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyMathUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.NetworkUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqCount;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqMemberMoney;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqgetGroup;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqgetNewplay;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqlastLottery;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil.timeMode;

public class XuanwuActivity extends BaseActivity implements CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener {
    private String todayZJ;
    String type_id, isopenOffice, isStart, game, typename;
    int[] qius;//球点数
    List<Integer> kjList = new ArrayList<Integer>(); //开奖list  存放图片 资源id
    List<XuanwuGroupBean> list1 = new ArrayList<>();   //6类数据 和总数据
    List<XuanwuGroupBean> list2 = new ArrayList<>();
    List<XuanwuGroupBean> list3 = new ArrayList<>();
    List<XuanwuGroupBean> list4 = new ArrayList<>();
    List<XuanwuGroupBean> list5 = new ArrayList<>();
    List<XuanwuGroupBean> list6 = new ArrayList<>();
    List<XuanwuGroupBean> totalList = new ArrayList<>();
    XuanwuGroup xuanwuGroup;
    KJCountDown kjCountDown;
    MemberMoney memberMoney;
    // HashMap<String, Boolean> isClickMap = new LinkedHashMap<>();
    List<IsClickModel> isClickList = new ArrayList<>();
    int[][] B = new int[3][6];
    XuanwuParentAdapter xuanwuParentAdapter;
    Long endtime, localtime, sjcha, tqtime;  //截止时间 当前时间 服务器与本地时间差
    String qishu, lastqishu;
    Long timeCount = 0L;  //倒计时数
    XuanwuLottery xuanwuLottery;
    String lotteryqishu;
    boolean isWaitopen = true;
    TextView lastQiShuTv;
    TextView newQiShuTv;
    TextView countDownEndSure;
    @BindView(R.id.ll_caizhong_head1)
    LinearLayout ll_caizhong_head1;
    @BindView(R.id.caizhong_head1_iv)
    ImageView caizhong_head1_iv;
    @BindView(R.id.xuanwu_xy_recyclerview)
    RecyclerView xuanwu_xy_recyclerview;
    @BindView(R.id.caizhong_head1_back)
    TextView caizhong_head1_back;
    @BindView(R.id.caizhong_head1_title)
    TextView caizhong_head1_title;
    @BindView(R.id.ll_caizhong_head2_left_tv)
    TextView ll_caizhong_head2_left_tv;
    @BindView(R.id.k3_tab_num)
    TextView k3_tab_num;
    @BindView(R.id.k3_tab_money)
    EditText k3_tab_money;//金额
    @BindView(R.id.k3_tab_confirm)
    TextView k3_tab_confirm;  //下注
    @BindView(R.id.k3_tab_reset)
    TextView k3_tab_reset;  //重置
    @BindView(R.id.xuanwu_head3_tv1)
    TextView xuanwu_head3_tv1;  //当前期数
    @BindView(R.id.xuanwu_head3_tv2)
    TextView xuanwu_head3_tv2;  //倒计时
    @BindView(R.id.xuanwu_head3_tv3)
    TextView xuanwu_head3_tv3;
    @BindView(R.id.xuanwu_head3_iv1)
    ImageView xuanwu_head3_iv1;
    @BindView(R.id.xuanwu_head3_iv2)
    ImageView xuanwu_head3_iv2;
    @BindView(R.id.xuanwu_head3_iv3)
    ImageView xuanwu_head3_iv3;
    @BindView(R.id.xuanwu_head3_iv4)
    ImageView xuanwu_head3_iv4;
    @BindView(R.id.xuanwu_head3_iv5)
    ImageView xuanwu_head3_iv5;
    @BindView(R.id.ll1_xuanwu_head3)
    LinearLayout ll1_xuanwu_head3;
    @BindView(R.id.ll2_xuanwu_head3)
    LinearLayout ll2_xuanwu_head3;
    @BindView(R.id.ll_caizhong_head2_left)
    LinearLayout ll_caizhong_head2_left;
    @BindView(R.id.caizhong_head1_menu)
    TextView caizhong_head1_menu;
    @BindView(R.id.ll_caizhong_head2_right)
    LinearLayout ll_caizhong_head2_right;
    @BindView(R.id.ll_caizhong_head2_center)
    LinearLayout ll_caizhong_head2_center;
    @BindView(R.id.ll_caizhong_head2_center_tv)
    TextView ll_caizhong_head2_center_tv;
    @BindView(R.id.xuanwu_tab_main)
    LinearLayout xuanwu_tab_main;
    @BindView(R.id.ll_caizhong_head1_parent)
    LinearLayout ll_caizhong_head1_parent;
    @BindView(R.id.ll_reload)
    LinearLayout ll_reload;
    @BindView(R.id.tv_reload)
    TextView tv_reload;
    @BindView(R.id.ll_caizhong_head2_right_tv)
    TextView ll_caizhong_head2_right_tv;
    @BindView(R.id.ll_gf_xuanwu_recycler)
    LinearLayout ll_gf_xuanwu_recycler;
    @BindView(R.id.ll_xy_xuanwu_recycler)
    LinearLayout ll_xy_xuanwu_recycler;
    @BindView(R.id.ll_touzhu_xy)
    LinearLayout ll_touzhu_xy;
    @BindView(R.id.ll_touzhu_gf)
    LinearLayout ll_touzhu_gf;
    @BindView(R.id.xuanwu_gf_recyclerview)
    RecyclerView xuanwu_gf_recyclerview;
    @BindView(R.id.tv_touzhu_gf_times)
    EditText tv_touzhu_gf_times;
    @BindView(R.id.tv_touzhu_jixuan)
    TextView tv_touzhu_jixuan;
    @BindView(R.id.tv_touzhu_num)
    TextView tv_touzhu_num;
    @BindView(R.id.tv_touzhu_money)
    TextView tv_touzhu_money;
    @BindView(R.id.tv_touzhu_zjmoney)
    TextView tv_touzhu_zjmoney;
    @BindView(R.id.ll_xuanwu_gf_recyclerview2)
    LinearLayout ll_xuanwu_gf_recyclerview2;
    @BindView(R.id.xuanwu_gf_recyclerview2_tv1)
    TextView xuanwu_gf_recyclerview2_tv1;
    @BindView(R.id.xuanwu_gf_recyclerview2_tv2)
    TextView xuanwu_gf_recyclerview2_tv2;
    @BindView(R.id.xuanwu_gf_recyclerview2)
    RecyclerView xuanwu_gf_recyclerview2;
    @BindView(R.id.tv_touzhu_yuan)
    TextView tv_touzhu_yuan;
    @BindView(R.id.tv_touzhu_jiao)
    TextView tv_touzhu_jiao;
    @BindView(R.id.rotate_image)
    ImageView rotate_image;
    @BindView(R.id.tv_touzhu_gf_jian)
    TextView tv_touzhu_gf_jian;
    @BindView(R.id.tv_touzhu_gf_jia)
    TextView tv_touzhu_gf_jia;
    @BindView(R.id.touzhu_gf_confirm)
    TextView touzhu_gf_confirm;
    @BindView(R.id.xuanwu_time_load)
    GifImageView xuanwu_time_load;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;


    public static final int ReqTouzhu = 101;
    public static final String TITLE1[] = {Utils.getString(R.string.两面),Utils.getString(R.string.第一球) , Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球) };
//    public static final String TITLE1[] = {Utils.getString(R.string.两面), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};
    Animation rotateAnimation = AnimUtils.getAnimation(180);
    RecyclerView head1recyclerview;
    PopupWindow head1popupWindow;

    RecyclerView gfhead_pop_recy1, gfhead_pop_recy2;
    PopupWindow gfheadPop;
    GfHeadRecy2Adapter gfhead2Adapter;
    GfHeadRecy1Adapter gfhead1Adapter;

    PopupWindow CountDownEndPop;//倒计时结束时的pop
    boolean isOfficial = false;
    NewplayModel newplayModel;
    int index_xy = 0;
    int index_gf_x = 0;
    int index_gf_y = 0;
    double danwei = 1.00;
    List<String> gftitleList1 = new ArrayList<>();
    List<String> gftitleList2 = new ArrayList<>();
    XuanwuGfPAdapter xuanwuGfPAdapter;
    XuanwuGfP2Adapter xuanwuGfP2Adapter;
    @BindView(R.id.fenpan_tv)
    TextView fenpan_tv;
    long[][] A = new long[3][11];
    private long mLastClickTime = 0;
    public static final long TIME_INTERVAL = 1000L;
    List<GfBetModel> betModelList = new ArrayList<>();   //官方投注List


    List<NewplayModel.PlayRuleListOneBean> ruleoneList = new ArrayList<>();
    List<NewplayModel.PlayRuleListTwoBean> ruletwoList = new ArrayList<>();
    List<NewplayModel.PlayRuleListThreeBean> rulethreeList = new ArrayList<>();
    List<NewplayModel.PlayRuleListFourBean> rulefourList = new ArrayList<>();    //50个

    List<NewplayModel.PlayRuleListThreeBean> choicethreeList = new ArrayList<>();
    List<NewplayModel.PlayRuleListFourBean> choicefourList = new ArrayList<>(); //3个
    NewplayModel.PlayRuleListThreeBean playRuleListThreeBean = new NewplayModel.PlayRuleListThreeBean();

    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanwu);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        Utils. saveLotteryHistory(Integer.parseInt(game),Integer.parseInt(type_id));
        //    Utils.logE("thread name and Id 1:",""+Thread.currentThread().getName()+","+Thread.currentThread().getId());
        ReqCountdown();
        initData();

        clickView();
        initCountDownEndPop();//倒计时结束的pop
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!CountDownEndPop.isShowing()) {
            ProgressDialogUtil.darkenBackground(this, 1f);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("0".equals(isopenOffice)){
            ll_caizhong_head2_right.setVisibility(View.INVISIBLE);
        }else {
            ll_caizhong_head2_right.setVisibility(View.VISIBLE);
        }
        if (NetworkUtil.isNetworkConnected(this)) {
            initAllPop();
            xuanwu_tab_main.setVisibility(View.VISIBLE);
            ll_caizhong_head1_parent.setVisibility(View.VISIBLE);
            ll_reload.setVisibility(View.GONE);

            handler.postDelayed(runnableTime, 300);
            if (runnableRequestOpen != null) {
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, 0);
            if (runnableRandom != null) {
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);
            handler.postDelayed(runnableZj, 2000);
        } else {
            xuanwu_tab_main.setVisibility(View.GONE);
            ll_caizhong_head1_parent.setVisibility(View.GONE);
            ll_reload.setVisibility(View.VISIBLE);
            showToast(Utils.getString(R.string.网络不给力请检查网络));
        }
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        typename = intent.getStringExtra("typename");
        type_id = intent.getStringExtra("type_id");
        isopenOffice = intent.getStringExtra("isopenOffice");
        isStart = intent.getStringExtra("isStart");
        qius = Const.getXuanwuqiuArray(this);
    }

    //检查上个页面专递过来的参数
    private void checkPara() {
        if (StringMyUtil.isEmptyString(type_id)) {
            return;
        }
        if (StringMyUtil.isEmptyString(game)) {
            return;
        }
        if (StringMyUtil.isEmptyString(typename)) {
            return;
        }
    }

    private void initAllPop() {
        //彩票分类pop相关设置
        customPopupWindow.initClassfyPop(this, this);
        //彩票分类pop数据请求 typename用于默认选中某个button
        customPopupWindow.initClassfyData(this, typename);
//        右侧菜单pop
        customPopupWindow.initMenuPop(this, this);
        //跳转 投注记录 typename:彩票名 game:彩票game  tyoe_id: 彩票type_id
        //Context context, final String typename, final int game, final int type_id
        customPopupWindow.toBetNote(this, typename, Integer.parseInt(game), Integer.parseInt(type_id));
        //跳转 充值中心
        customPopupWindow.toInvestCenter(this);
        //跳转会员中心
        customPopupWindow.tovVipCenter(this);
        //跳转 开奖结果 type_id: 当前彩票的typ_id  lotteryClassId: 当前彩种分类的id
        customPopupWindow.toOpenResult(this, Integer.parseInt(type_id), Integer.parseInt(game));
        //game 彩票类型(用于判断需要跳转到哪种类型的游戏规则) typename: 彩票名: 用于设置规则说明中的彩票名
        customPopupWindow.initGameRule(this, Integer.parseInt(game), typename, 0,false);
        //弹出游戏规则pop
        customPopupWindow.showGameRulePop(this,false);
        ;
        customPopupWindow.initSscTodayResultPop(this, Integer.parseInt(game), Integer.parseInt(type_id),false);
        //跳转两面长龙
        customPopupWindow.toTwoChangLongAty(this, Integer.parseInt(game), Integer.parseInt(type_id));
        //跳转今日输赢
        customPopupWindow.toTodayWinLose(this, Integer.parseInt(game), Integer.parseInt(type_id));
        //跳转在线客服
        customPopupWindow.toOnlineKf(this);
    }

    private void initCountDownEndPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.countdown_end_pop, null);
        lastQiShuTv = view.findViewById(R.id.last_qishu);
        newQiShuTv = view.findViewById(R.id.new_qihao);
        countDownEndSure = view.findViewById(R.id.countdown_pop_sure);
        CountDownEndPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        CountDownEndPop.setAnimationStyle(R.style.popAlphaanim0_5);
        CountDownEndPop.setTouchable(true);//响应内部点击
        CountDownEndPop.setOnDismissListener(() -> ProgressDialogUtil.darkenBackground(XuanwuActivity.this, 1f));
        countDownEndSure.setOnClickListener(v -> CountDownEndPop.dismiss());
    }

    private void initData() {
        checkPara();
        loading_linear.setVisibility(View.VISIBLE);
        ll_caizhong_head2_left_tv.setText(typename);
        if (!isOfficial) {
            ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.信用玩法));
            ll_xy_xuanwu_recycler.setVisibility(View.VISIBLE);
            ll_touzhu_xy.setVisibility(View.VISIBLE);
            ll_gf_xuanwu_recycler.setVisibility(View.GONE);
            ll_touzhu_gf.setVisibility(View.GONE);

            ReqgetGroup(this, game, type_id, -1, new MyRequest.MyRequestListener() {
                @Override
                public void success(String content) {
                    loading_linear.setVisibility(View.GONE);
                    Utils.logE("content", content);
                    if (!StringMyUtil.isEmptyString(content)) {
                        Gson gson = new GsonBuilder().serializeNulls().create();
                        try {
                            Utils.logE("xuanwuGroup", "1");
                            xuanwuGroup = gson.fromJson(content, XuanwuGroup.class);
                            //        showtoast("" + xuanwuGroup.getRulelistAll().get(xuanwuGroup.getRulelistAll().size() - 1).getBalled());
                            Utils.logE("xuanwuGroup", "2");
                            if (xuanwuGroup != null) {
                                showcaizhong();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void failed(String content) {
                    //   showtoast(Utils.getString(R.string.请求数据错误));
                    loading_linear.setVisibility(View.GONE);
                }
            });
        } else {
            ll_xy_xuanwu_recycler.setVisibility(View.GONE);
            ll_touzhu_xy.setVisibility(View.GONE);
            ll_gf_xuanwu_recycler.setVisibility(View.VISIBLE);
            ll_touzhu_gf.setVisibility(View.VISIBLE);
            ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.官方玩法));
            ReqgetNewplay(this, game, type_id, -1, new MyRequest.MyRequestListener() {
                @Override
                public void success(String content) {
                    loading_linear.setVisibility(View.GONE);
                    Utils.logE("content", content);
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    if (!StringMyUtil.isEmptyString(content)) {
                        try {
                            Utils.logE("newplayModel", "1");
                            newplayModel = gson.fromJson(content, NewplayModel.class);
                            Utils.logE("newplayModel", "2");
                            if (newplayModel != null) {
                                ruleoneList = newplayModel.getPlayRuleListOne();
                                ruletwoList = newplayModel.getPlayRuleListTwo();
                                rulethreeList = newplayModel.getPlayRuleListThree();
                                rulefourList = newplayModel.getPlayRuleListFour();
                                Utils.logE("thread name and Id 2:", "" + Thread.currentThread().getName() + "," + Thread.currentThread().getId());
                                showcaizhong();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void failed(String content) {
                    loading_linear.setVisibility(View.GONE);
                    Utils.logE("content", content);
                }
            });
        }
        requestMoney();

    }

    //请求倒计时
    private void ReqCountdown() {
        xuanwu_head3_tv2.setVisibility(View.GONE);
        xuanwu_time_load.setVisibility(View.VISIBLE);
        ReqCount(this, game, type_id, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                if (!StringMyUtil.isEmptyString(content)) {
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    try {
                        kjCountDown = gson.fromJson(content, KJCountDown.class);
                        if (kjCountDown != null) {
                            if(StringMyUtil.isEmptyString(kjCountDown.getQishu())){
                                fenpan_tv.setVisibility(View.VISIBLE);
                                xuanwu_head3_tv1.setText("- - -");
                                xuanwu_head3_tv2.setText("- - - ");
                            }else {
                                //设置全局变量
                                fenpan_tv.setVisibility(View.GONE);
                                endtime = Long.parseLong(DateUtil.dateToStamp(kjCountDown.getStoptimestr()));
                                tqtime = (long) kjCountDown.getTqtime() * 1000;
                                sjcha = SharePreferencesUtil.getLong(XuanwuActivity.this, "shijiancha", 0l);//servertime - localtime;
                                qishu = kjCountDown.getQishu();
                                timeCount = 1L;
                                lastqishu = kjCountDown.getLastqishu();

                                    xuanwu_head3_tv1.setText(qishu + Utils.getString(R.string. 期));
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            xuanwu_head3_tv2.setVisibility(View.VISIBLE);
                                            xuanwu_time_load.setVisibility(View.GONE);
                                        }
                                    },600);

                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(String content) {
                xuanwu_head3_tv2.setVisibility(View.GONE);
                xuanwu_time_load.setVisibility(View.VISIBLE);
            }
        });
        if (isWaitopen) {
            //TODO 等待开奖 筛子摇起来/开奖结果求起来
            if (runnableRequestOpen != null) {
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, 0);
            if (runnableRandom != null) {
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);
//            Utils.logE("isWaitopen:", "" + isWaitopen + Utils.getString(R.string.,等待开奖 筛子摇起来/开奖结果求起来));
        }
    }

    private void initOpenResult() {
        ReqlastLottery(this, game, type_id, 1, 1, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                Utils.logE("jony111:", content);
                Gson gson = new GsonBuilder().serializeNulls().create();
                if (!StringMyUtil.isEmptyString(content)) {
                    try {
                        xuanwuLottery = gson.fromJson(content, XuanwuLottery.class);
                        Utils.logE("content", content);
                        lotteryqishu = xuanwuLottery.getXuanwuLotterylist().get(0).getLotteryqishu();
                        if (!StringMyUtil.isEmptyString(qishu)) {
                            //是否是当天最后一期
                            if (!StringMyUtil.isEmptyString(qishu) && !StringMyUtil.isEmptyString(lastqishu) && !StringMyUtil.isEmptyString(lotteryqishu) && xuanwuLottery != null) {
                                if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu.substring(lotteryqishu.length() - 2, lotteryqishu.length()))) {
                                    isWaitopen = false;
                                    xuanwu_head3_tv3.setText(lotteryqishu + " "+Utils.getString(R.string.期开奖号码));
                                    Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult1");
                                    //  updateRightLotteryNo();
                                    List<String> list = Arrays.asList(xuanwuLottery.getXuanwuLotterylist().get(0).getLotteryNo().split(","));
                                    kjList.clear();
                                    for (String bean : list) {
                                        kjList.add(qius[Integer.parseInt(bean) - 1]);
                                    }
                                    xuanwu_head3_iv1.setImageResource(kjList.get(0));
                                    xuanwu_head3_iv2.setImageResource(kjList.get(1));
                                    xuanwu_head3_iv3.setImageResource(kjList.get(2));
                                    xuanwu_head3_iv4.setImageResource(kjList.get(3));
                                    xuanwu_head3_iv5.setImageResource(kjList.get(4));

                                } else {
                                    if (Long.parseLong(qishu) - 1 == Long.parseLong(lotteryqishu)) {
                                        xuanwu_head3_tv3.setText(lotteryqishu + " "+Utils.getString(R.string.期开奖号码));
                                        Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult2");
                                        isWaitopen = false;

                                        List<String> list = Arrays.asList(xuanwuLottery.getXuanwuLotterylist().get(0).getLotteryNo().split(","));
                                        kjList.clear();
                                        for (String bean : list) {
                                            kjList.add(qius[Integer.parseInt(bean) - 1]);
                                        }
                                        xuanwu_head3_iv1.setImageResource(kjList.get(0));
                                        xuanwu_head3_iv2.setImageResource(kjList.get(1));
                                        xuanwu_head3_iv3.setImageResource(kjList.get(2));
                                        xuanwu_head3_iv4.setImageResource(kjList.get(3));
                                        xuanwu_head3_iv5.setImageResource(kjList.get(4));
                                    } else {
                                        xuanwu_head3_tv3.setText((Long.parseLong(lotteryqishu) + 1) + " "+Utils.getString(R.string.期开奖号码));  //筛子转动
                                        isWaitopen = true;
                                        Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult3");
                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(String content) {
                //    showtoast(Utils.getString(R.string.请求数据错误));
            }
        });
    }

    private void showcaizhong() {

        if (!isOfficial) {
            caizhong_head1_title.setText(TITLE1[index_xy]);
            List<String> datagGroupList = new ArrayList<>();
            List<XuanwuGroupBean> dataBeanList = new ArrayList<>();
            int row = 0;
            switch (index_xy) {
                case 0:
                    row = 24;
                    if (list1.size() == 0) {
                        if (xuanwuGroup != null) {
                            List<XuanwuGroup.RulelistAllBean> rulelistAllBeanList = xuanwuGroup.getRulelistAll();
                            //TODO 对六组数据进行处理 分类
//                            String[] title = {Utils.getString(R.string.总和), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};
                            String[] title = {Utils.getString(R.string.总和), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};
                            for (int i = 0; i < rulelistAllBeanList.size(); i++) {
                                if (rulelistAllBeanList.get(i).getIsboth() == 1  //两面
                                        && rulelistAllBeanList.get(i).getBalled() == 5
                                        && rulelistAllBeanList.get(i).getType() == 2) {
                                    try {
                                        String strJsonArry = rulelistAllBeanList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            XuanwuGroupBean bean = new XuanwuGroupBean();
                                            bean.setDgroupname(Utils.getString(R.string.两面));
                                            bean.setXgroupname(title[jsonObject.getIntValue("balled")]);
                                            bean.setBalled(jsonObject.getIntValue("balled"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLongValue("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            bean.setLastModifiedDate(jsonObject.getLongValue("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDoubleValue("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            list1.add(bean);
                                        }
                                        totalList.addAll(list1);
                                        for (XuanwuGroupBean bean : list1) {
                                            //isClickMap.put(String.valueOf(bean.getId()), false);
                                            StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    dataBeanList.addAll(list1);
                    datagGroupList.add(Utils.getString(R.string.总和));
                    datagGroupList.add(Utils.getString(R.string.第一球));
                    datagGroupList.add(Utils.getString(R.string.第二球));
                    datagGroupList.add(Utils.getString(R.string.第三球));
                    datagGroupList.add(Utils.getString(R.string.第四球));
                    datagGroupList.add(Utils.getString(R.string.第五球));
                    break;
                case 1:
                    row = 5;
                    if (list2.size() == 0) {
                        if (xuanwuGroup != null) {
                            List<XuanwuGroup.RulelistAllBean> rulelistAllBeanList = xuanwuGroup.getRulelistAll();
                            //对六组数据进行处理 分类
                            String[] title = {Utils.getString(R.string.总和), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};
                            for (int i = 0; i < rulelistAllBeanList.size(); i++) {
                                if (rulelistAllBeanList.get(i).getIsboth() == 0  //第一球
                                        && rulelistAllBeanList.get(i).getBalled() == 1
                                        && rulelistAllBeanList.get(i).getType() == 0) {
                                    try {
                                        String strJsonArry = rulelistAllBeanList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            XuanwuGroupBean bean = new XuanwuGroupBean();
                                            bean.setDgroupname(Utils.getString(R.string.第一球));
                                            bean.setXgroupname(title[jsonObject.getIntValue("balled")]);
                                            bean.setBalled(jsonObject.getIntValue("balled"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLongValue("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            bean.setLastModifiedDate(jsonObject.getLongValue("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDoubleValue("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            list2.add(bean);
                                        }
                                        totalList.addAll(list2);
                                        for (XuanwuGroupBean bean : list2) {
                                            //  isClickMap.put(String.valueOf(bean.getId()), false);
                                            StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                    }
                    dataBeanList.addAll(list2);
                    datagGroupList.add(Utils.getString(R.string.第一球));
                    break;
                case 2:
                    row = 5;
                    if (list3.size() == 0) {
                        if (xuanwuGroup != null) {
                            List<XuanwuGroup.RulelistAllBean> rulelistAllBeanList = xuanwuGroup.getRulelistAll();
                            //TODO 对六组数据进行处理 分类
                            String[] title = {Utils.getString(R.string.总和), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};

                            for (int i = 0; i < rulelistAllBeanList.size(); i++) {
                                if (rulelistAllBeanList.get(i).getIsboth() == 0  //第二球
                                        && rulelistAllBeanList.get(i).getBalled() == 2
                                        && rulelistAllBeanList.get(i).getType() == 0) {
                                    try {
                                        String strJsonArry = rulelistAllBeanList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            XuanwuGroupBean bean = new XuanwuGroupBean();
                                            bean.setDgroupname(Utils.getString(R.string.第二球));
                                            bean.setXgroupname(title[jsonObject.getIntValue("balled")]);
                                            bean.setBalled(jsonObject.getIntValue("balled"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLongValue("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            bean.setLastModifiedDate(jsonObject.getLongValue("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDoubleValue("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            list3.add(bean);
                                        }
                                        totalList.addAll(list3);
                                        for (XuanwuGroupBean bean : list3) {
                                            //  isClickMap.put(String.valueOf(bean.getId()), false);
                                            StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                    }
                    dataBeanList.addAll(list3);
                    datagGroupList.add(Utils.getString(R.string.第二球));
                    break;
                case 3:
                    row = 5;
                    if (list4.size() == 0) {
                        if (xuanwuGroup != null) {
                            List<XuanwuGroup.RulelistAllBean> rulelistAllBeanList = xuanwuGroup.getRulelistAll();
                            String[] title = {Utils.getString(R.string.总和), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};

                            for (int i = 0; i < rulelistAllBeanList.size(); i++) {
                                if (rulelistAllBeanList.get(i).getIsboth() == 0  //第三球
                                        && rulelistAllBeanList.get(i).getBalled() == 3
                                        && rulelistAllBeanList.get(i).getType() == 0) {
                                    try {
                                        String strJsonArry = rulelistAllBeanList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            XuanwuGroupBean bean = new XuanwuGroupBean();
                                            bean.setDgroupname(Utils.getString(R.string.第三球));
                                            bean.setXgroupname(title[jsonObject.getIntValue("balled")]);
                                            bean.setBalled(jsonObject.getIntValue("balled"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLongValue("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            bean.setLastModifiedDate(jsonObject.getLongValue("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDoubleValue("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            list4.add(bean);
                                        }
                                        totalList.addAll(list4);
                                        for (XuanwuGroupBean bean : list4) {
                                            //  isClickMap.put(String.valueOf(bean.getId()), false);
                                            StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    dataBeanList.addAll(list4);
                    datagGroupList.add(Utils.getString(R.string.第三球));
                    break;
                case 4:
                    row = 5;
                    if (list5.size() == 0) {
                        if (xuanwuGroup != null) {
                            List<XuanwuGroup.RulelistAllBean> rulelistAllBeanList = xuanwuGroup.getRulelistAll();
                            String[] title = {Utils.getString(R.string.总和), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};
                            for (int i = 0; i < rulelistAllBeanList.size(); i++) {
                                if (rulelistAllBeanList.get(i).getIsboth() == 0  //第四球
                                        && rulelistAllBeanList.get(i).getBalled() == 4
                                        && rulelistAllBeanList.get(i).getType() == 0) {
                                    try {
                                        String strJsonArry = rulelistAllBeanList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            XuanwuGroupBean bean = new XuanwuGroupBean();
                                            bean.setDgroupname( Utils.getString(R.string.第四球));
                                            bean.setXgroupname(title[jsonObject.getIntValue("balled")]);
                                            bean.setBalled(jsonObject.getIntValue("balled"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLongValue("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            bean.setLastModifiedDate(jsonObject.getLongValue("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDoubleValue("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            list5.add(bean);
                                        }
                                        totalList.addAll(list5);
                                        for (XuanwuGroupBean bean : list5) {
                                            //  isClickMap.put(String.valueOf(bean.getId()), false);
                                            StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    dataBeanList.addAll(list5);
                    datagGroupList.add( Utils.getString(R.string.第四球));
                    break;
                case 5:
                    row = 5;
                    if (list6.size() == 0) {
                        if (xuanwuGroup != null) {
                            List<XuanwuGroup.RulelistAllBean> rulelistAllBeanList = xuanwuGroup.getRulelistAll();
                            //TODO 对六组数据进行处理 分类
                            String[] title = {Utils.getString(R.string.总和), Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球)};

                            for (int i = 0; i < rulelistAllBeanList.size(); i++) {
                                if (rulelistAllBeanList.get(i).getIsboth() == 0  //第五球
                                        && rulelistAllBeanList.get(i).getBalled() == 5
                                        && rulelistAllBeanList.get(i).getType() == 0) {
                                    try {
                                        String strJsonArry = rulelistAllBeanList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            XuanwuGroupBean bean = new XuanwuGroupBean();
                                            bean.setDgroupname(Utils.getString(R.string.第五球));
                                            bean.setXgroupname(title[jsonObject.getIntValue("balled")]);
                                            bean.setBalled(jsonObject.getIntValue("balled"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLongValue("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            bean.setLastModifiedDate(jsonObject.getLongValue("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDoubleValue("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            list6.add(bean);
                                        }
                                        totalList.addAll(list6);
                                        //          isClickList.clear();
                                        for (XuanwuGroupBean bean : list6) {
                                            //  isClickMap.put(String.valueOf(bean.getId()), false);
                                            StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    dataBeanList.addAll(list6);
                    datagGroupList.add(Utils.getString(R.string.第五球));
                    break;
            }


            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xuanwu_xy_recyclerview.setLayoutManager(layoutManager);
            xuanwu_xy_recyclerview.setFocusableInTouchMode(false);
            xuanwuParentAdapter = new XuanwuParentAdapter(this, datagGroupList, dataBeanList, row, index_xy, isClickList);
            xuanwu_xy_recyclerview.setAdapter(xuanwuParentAdapter);
        } else {
            gftitleList1.clear();
            gftitleList2.clear();
            for (int i = 0; i < ruleoneList.size(); i++) {
                gftitleList1.add(ruleoneList.get(i).getName());
            }
            NewplayModel.PlayRuleListTwoBean playRuleListTwoBean = new NewplayModel.PlayRuleListTwoBean();
            for (int i = 0; i < ruletwoList.size(); i++) {
                if (ruleoneList.get(index_gf_x).getId() == ruletwoList.get(i).getParentId()) {
                    playRuleListTwoBean = ruletwoList.get(i);
                }
            }
            choicethreeList.clear();
            for (int i = 0; i < rulethreeList.size(); i++) {
                if (playRuleListTwoBean.getId() == rulethreeList.get(i).getParentId()) {
                    choicethreeList.add(rulethreeList.get(i));
                }
            }
            playRuleListThreeBean = choicethreeList.get(index_gf_y);

            choicefourList.clear();
            for (int i = 0; i < rulefourList.size(); i++) {
                if (rulefourList.get(i).getPlayRuleId() == choicethreeList.get(index_gf_y).getId()) {
                    choicefourList.add(rulefourList.get(i));
                }
            }

            for (NewplayModel.PlayRuleListThreeBean bean : choicethreeList) {
                gftitleList2.add(bean.getName());
            }
            caizhong_head1_title.setText("" + gftitleList1.get(index_gf_x) + gftitleList2.get(index_gf_y));

            //  isClickMap.clear();
            isClickList.clear();

            danwei = 1.00;

            if (index_gf_x == 4 && index_gf_y == 0) {

                for (int i = 0; i < choicefourList.size(); i++) {
                    // isClickMap.put(String.valueOf(choicefourList.get(i).getId()), false);
                    StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId()), false);
                }
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 6; j++) {
                        B[i][j] = 0;
                    }
                }

                String remark = playRuleListThreeBean.getRemark();
                remark = remark + Utils.getString(R.string. 奖金详情);
                SpannableString spannableString = new SpannableString(remark);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), remark.indexOf(" ") + 1, remark.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                xuanwu_gf_recyclerview2_tv1.setText(spannableString);
                xuanwu_gf_recyclerview2_tv2.setText(choicefourList.get(0).getName());
                double max_odds = choicefourList.get(0).getOdds();
                for (int i = 1; i < choicefourList.size(); i++) {
                    if (max_odds < choicefourList.get(i).getOdds()) {
                        max_odds = choicefourList.get(i).getOdds();
                    }
                }
                BigDecimal odds = new BigDecimal(String.valueOf(max_odds));
                BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
                BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
                totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));

                ll_xuanwu_gf_recyclerview2.setVisibility(View.VISIBLE);
                xuanwu_gf_recyclerview.setVisibility(View.GONE);
                GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                xuanwu_gf_recyclerview2.setLayoutManager(layoutManager);
                xuanwu_gf_recyclerview2.setFocusableInTouchMode(false);
                xuanwuGfP2Adapter = new XuanwuGfP2Adapter(this, B, choicefourList, isClickList);
                xuanwu_gf_recyclerview2.setAdapter(xuanwuGfP2Adapter);
            } else if (index_gf_x == 4 && index_gf_y == 1) {
                for (int i = 0; i < choicefourList.size(); i++) {
                    //   isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes())), false);
                    StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes())), false);
                }
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 6; j++) {
                        B[i][j] = 0;
                    }
                }

                double max_odds = choicefourList.get(0).getOdds();
                for (int i = 1; i < choicefourList.size(); i++) {
                    if (max_odds < choicefourList.get(i).getOdds()) {
                        max_odds = choicefourList.get(i).getOdds();
                    }
                }
                BigDecimal odds = new BigDecimal(String.valueOf(max_odds));
                BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
                BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
                totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));

                Utils.logE("isClickMap", "" + isClickList);
                ll_xuanwu_gf_recyclerview2.setVisibility(View.GONE);
                xuanwu_gf_recyclerview.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                xuanwu_gf_recyclerview.setLayoutManager(layoutManager);
                xuanwu_gf_recyclerview.setFocusableInTouchMode(false);
                xuanwuGfPAdapter = new XuanwuGfPAdapter(this, B, choicefourList, playRuleListThreeBean, isClickList, index_gf_x, index_gf_y);
                xuanwu_gf_recyclerview.setAdapter(xuanwuGfPAdapter);
            } else {
                ll_xuanwu_gf_recyclerview2.setVisibility(View.GONE);
                xuanwu_gf_recyclerview.setVisibility(View.VISIBLE);
                for (int i = 0; i < choicefourList.size(); i++) {
                    String[] arry = choicefourList.get(i).getCodes().split(",");
                    for (int j = 0; j < arry.length; j++) {
                        // isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(arry[j])), false);
                        StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(arry[j])), false);
                    }
                }
                for (int i = 0; i < choicefourList.size(); i++) {
                    for (int j = 0; j < 6; j++) {
                        B[i][j] = 0;
                    }
                }

                // 设置中奖金额
                BigDecimal odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
                BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
                BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
                totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                xuanwu_gf_recyclerview.setLayoutManager(layoutManager);
                xuanwu_gf_recyclerview.setFocusableInTouchMode(false);
                xuanwuGfPAdapter = new XuanwuGfPAdapter(this, B, choicefourList, playRuleListThreeBean, isClickList, index_gf_x, index_gf_y);
                xuanwu_gf_recyclerview.setAdapter(xuanwuGfPAdapter);
            }

            //TODO 2019-7-23 切换index_gf   设置下方数据
            tv_touzhu_gf_times.setText("1");
            tv_touzhu_jixuan.setText(Utils.getString(R.string.机选));
            tv_touzhu_num.setText("0");
            tv_touzhu_money.setText("0.00");
            //单位设置

            tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(this,R.drawable.shape_yuan_press));
            tv_touzhu_yuan.setTextColor(ContextCompat.getColor(this,R.color.white));
            tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(this,R.drawable.shape_jiao_unpress));
            tv_touzhu_jiao.setTextColor(ContextCompat.getColor(this,R.color.textgray));
        }

    }

    //TODO 2019-7-25 1级列表选中后 数据更新 和界面更新
    public void OnHeadRecy1Listener(int x) {
        index_gf_x = x;
        index_gf_y = 0;
        Utils.logE("index_gf_x", "" + index_gf_x);

        NewplayModel.PlayRuleListTwoBean playRuleListTwoBean = new NewplayModel.PlayRuleListTwoBean();
        for (int i = 0; i < ruletwoList.size(); i++) {
            if (ruleoneList.get(index_gf_x).getId() == ruletwoList.get(i).getParentId()) {
                playRuleListTwoBean = ruletwoList.get(i);
            }
        }

        gftitleList2.clear();
        for (int i = 0; i < rulethreeList.size(); i++) {
            if (playRuleListTwoBean.getId() == rulethreeList.get(i).getParentId()) {
                choicethreeList.add(rulethreeList.get(i));
                gftitleList2.add(rulethreeList.get(i).getName());
            }
        }

        int row = 0;
        if (index_gf_x == 5 || index_gf_x == 6) {
            row = 4;
        } else {
            row = 3;
        }
        gfhead2Adapter = new GfHeadRecy2Adapter(this, gftitleList2, index_gf_y);
        gfhead_pop_recy2.setAdapter(gfhead2Adapter);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, row);
        gridLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
        gfhead_pop_recy2.setLayoutManager(gridLayoutManager2);

        caizhong_head1_title.setText("" + gftitleList1.get(index_gf_x) + gftitleList2.get(index_gf_y));
        showcaizhong();
    }

    //TODO 2019-7-25 2级列表选中后 数据更新 和界面更新
    public void OnHeadRecy2Listener(int y) {
        index_gf_y = y;
        Utils.logE("index_gf_y", "" + index_gf_y);


        if (gfheadPop.isShowing()) {
            gfheadPop.dismiss();
        }
        showcaizhong();

    }

    public void OnGfListener(List<IsClickModel> isClickList, int[][] B) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 11; j++) {
                A[i][j] = 0;
            }
        }
        long total_num = 0;
        if (index_gf_x == 4 && index_gf_y == 0) {
            xuanwuGfP2Adapter.notifyDataSetChanged();

            for (IsClickModel isClickModel : isClickList) {
                if (isClickModel.getIsclick()) {
                    for (int j = 0; j < choicefourList.size(); j++) {
                        if (Integer.parseInt(/*entry.getKey()*/isClickModel.getId()) == choicefourList.get(j).getId()) {
                            A[0][j] = 1;
                        }
                    }
                }
            }

            total_num = StrUtil.isclickCal(isClickList);
        } else if (index_gf_x == 4 && index_gf_y == 1) {
            xuanwuGfPAdapter.notifyDataSetChanged();
            //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
            for (IsClickModel isClickModel : isClickList) {
                //  if (entry.getValue()) {
                if (isClickModel.getIsclick()) {
                    for (int j = 0; j < choicefourList.size(); j++) {
                        if (Integer.parseInt(isClickModel.getId()) == choicefourList.get(j).getId() * 100 + Integer.parseInt(choicefourList.get(j).getCodes())) {
                            A[0][j] = 1;
                        }
                    }
                }
            }

            total_num = StrUtil.isclickCal(isClickList);
        } else {
            xuanwuGfPAdapter.notifyDataSetChanged();
            // for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
            for (IsClickModel isClickModel : isClickList) {
                //  if (entry.getValue()) {
                if (isClickModel.getIsclick()) {
                    for (int i = 0; i < choicefourList.size(); i++) {
                        if (Integer.parseInt(isClickModel.getId()) / 100 == choicefourList.get(i).getId()) {
                            int j = Integer.parseInt(isClickModel.getId()) % 100 - 1;
                            A[i][j] = 1;
                        }
                    }
                }
            }

            if (index_gf_x == 0) {
                if (index_gf_y == 0) {
                    for (int i = 0; i < 11; i++) {
                        for (int j = 0; j < 11; j++) {
                            for (int k = 0; k < 11; k++) {
                                if (i != j && i != k && j != k) {
                                    total_num = total_num + A[0][i] * A[1][j] * A[2][k];
                                }
                            }
                        }
                    }
                }
                if (index_gf_y == 1) {
                    int num = 0;
                    num = StrUtil.isclickCal(isClickList);
                    total_num = StrUtil.comb(num, 3);

                }
                if (index_gf_y == 2) {
                    int danma_num = 0;
                    int tuoma_num = 0;
                    //   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel : isClickList) {
                        if (/*entry.getValue()*/isClickModel.getIsclick()) {
                            if (Integer.parseInt(isClickModel.getId()) / 100 == choicefourList.get(0).getId()) {
                                danma_num++;
                            }
                            if (Integer.parseInt(isClickModel.getId()) / 100 == choicefourList.get(1).getId()) {
                                tuoma_num++;
                            }
                        }
                    }
                    if (danma_num == 1 && tuoma_num >= 2) {
                        total_num = StrUtil.comb(tuoma_num, 2);
                    } else if (danma_num == 2 && tuoma_num >= 1) {
                        total_num = tuoma_num;
                    } else {
                        total_num = 0;
                    }

                }

            }
            if (index_gf_x == 1) {
                if (index_gf_y == 0) {
                    for (int i = 0; i < 11; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (i != j) {
                                total_num = total_num + A[0][i] * A[1][j];
                            }
                        }
                    }
                }
                if (index_gf_y == 1) {
                    int num = 0;
                    num = StrUtil.isclickCal(isClickList);
                    total_num = StrUtil.comb(num, 2);
                }
                if (index_gf_y == 2) {
                    int danma_num = 0;
                    int tuoma_num = 0;
                    //   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel : isClickList) {
                        if (isClickModel.getIsclick()) {
                            if (Integer.parseInt(isClickModel.getId()) / 100 == choicefourList.get(0).getId()) {
                                danma_num++;
                            }
                            if (Integer.parseInt(isClickModel.getId()) / 100 == choicefourList.get(1).getId()) {
                                tuoma_num++;
                            }
                        }
                    }
                    if (danma_num == 1) {
                        total_num = tuoma_num;
                    } else {
                        total_num = 0;
                    }
                }
            }
            if (index_gf_x == 2 || index_gf_x == 3) {

                total_num = StrUtil.isclickCal(isClickList);
            }
            if (index_gf_x == 5) {
                int num = 0;

                num = StrUtil.isclickCal(isClickList);
                if (index_gf_y == 0) {
                    total_num = num;
                }
                if (index_gf_y == 1) {
                    total_num = StrUtil.comb(num, 2);
                }
                if (index_gf_y == 2) {
                    total_num = StrUtil.comb(num, 3);
                }
                if (index_gf_y == 3) {
                    total_num = StrUtil.comb(num, 4);
                }
                if (index_gf_y == 4) {
                    total_num = StrUtil.comb(num, 5);
                }
                if (index_gf_y == 5) {
                    total_num = StrUtil.comb(num, 6);
                }
                if (index_gf_y == 6) {
                    total_num = StrUtil.comb(num, 7);
                }
                if (index_gf_y == 7) {
                    total_num = StrUtil.comb(num, 8);
                }
            }
            if (index_gf_x == 6) {
                int danma_num = 0;
                int tuoma_num = 0;
                //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                for (IsClickModel isClickModel : isClickList) {
                    if (isClickModel.getIsclick()) {
                        if (Integer.parseInt(isClickModel.getId()) / 100 == choicefourList.get(0).getId()) {
                            danma_num++;
                        }
                        if (Integer.parseInt(isClickModel.getId()) / 100 == choicefourList.get(1).getId()) {
                            tuoma_num++;
                        }
                    }
                }

                if (index_gf_y == 0) {
                    if (danma_num != 0) {
                        total_num = StrUtil.comb(tuoma_num, 2 - danma_num);
                    } else {
                        total_num = 0;
                    }
                }
                if (index_gf_y == 1) {
                    if (danma_num != 0) {
                        total_num = StrUtil.comb(tuoma_num, 3 - danma_num);
                    } else {
                        total_num = 0;
                    }
                }
                if (index_gf_y == 2) {
                    if (danma_num != 0) {
                        total_num = StrUtil.comb(tuoma_num, 4 - danma_num);
                    } else {
                        total_num = 0;
                    }
                }
                if (index_gf_y == 3) {
                    if (danma_num != 0) {
                        total_num = StrUtil.comb(tuoma_num, 5 - danma_num);
                    } else {
                        total_num = 0;
                    }
                }
                if (index_gf_y == 4) {
                    if (danma_num != 0) {
                        total_num = StrUtil.comb(tuoma_num, 6 - danma_num);
                    } else {
                        total_num = 0;
                    }
                }
                if (index_gf_y == 5) {
                    if (danma_num != 0) {
                        total_num = StrUtil.comb(tuoma_num, 7 - danma_num);
                    } else {
                        total_num = 0;
                    }
                }
                if (index_gf_y == 6) {
                    if (danma_num != 0) {
                        total_num = StrUtil.comb(tuoma_num, 8 - danma_num);
                    } else {
                        total_num = 0;
                    }
                }
            }
        }


        tv_touzhu_num.setText(String.valueOf(total_num));
        BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
        BigDecimal totalmoney = times.multiply(new BigDecimal(String.valueOf(total_num))).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
        totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
        tv_touzhu_money.setText(String.valueOf(totalmoney));
        if (total_num != 0) {
            tv_touzhu_jixuan.setText(Utils.getString(R.string.重置));
        } else {
            tv_touzhu_jixuan.setText(Utils.getString(R.string.机选));
        }
    }

    //   回调所选的按钮 更新下方已选注数    //在内层adapter   notifyDataSetChanged   后  isClickMap 在activity 和上层adapter自动更新
    public void OnClickListener(List<IsClickModel> isClickList) {
        if (!isOfficial) {
            int num = 0;

            num = StrUtil.isclickCal(isClickList);
            k3_tab_num.setText("" + num);
            if (num != 0) {
                k3_tab_reset.setText(Utils.getString(R.string.重置));
            } else {
                k3_tab_reset.setText(Utils.getString(R.string.机选));
            }
        }

    }

    @Override
    public void onItemClick(View view, int position, LotteryClassModel lotteryClassModel) {
        int type_id = lotteryClassModel.getType_id();
        String isopenOffice = lotteryClassModel.getIsopenOffice();
        String isStart = lotteryClassModel.getIsStart();
        String typename = lotteryClassModel.getTypename();
        int game = lotteryClassModel.getGame();
        ToBetAtyUtils.toBetActivity(XuanwuActivity.this, game, typename, type_id, isopenOffice, isStart);
        finish();
    }

    @Override
    public void onMenuClicked(View view) {
    }

    private void clickView() {
        fenpan_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Utils.getString(R.string.该彩种已封盘));
            }
        });
        ll_caizhong_head2_right.setOnClickListener(v -> {
            //TODO 2019-7-24  官方信用切换
            isOfficial = !isOfficial;
            if (isOfficial) {
                if (!isopenOffice.equals("0")){
                    ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.官方玩法));
                    ll_xy_xuanwu_recycler.setVisibility(View.GONE);
                    ll_touzhu_xy.setVisibility(View.GONE);
                    ll_gf_xuanwu_recycler.setVisibility(View.VISIBLE);
                    ll_touzhu_gf.setVisibility(View.VISIBLE);
                    if (newplayModel == null) {
                        initData();
                    } else {
                        showcaizhong();
                    }
                }else {
                    showToast(Utils.getString(R.string.暂无官方彩));
                }

            } else {
                ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.信用玩法));
                ll_xy_xuanwu_recycler.setVisibility(View.VISIBLE);
                ll_touzhu_xy.setVisibility(View.VISIBLE);
                ll_gf_xuanwu_recycler.setVisibility(View.GONE);
                ll_touzhu_gf.setVisibility(View.GONE);
                k3_tab_num.setText("0");
                k3_tab_money.setText("");
                k3_tab_reset.setText(Utils.getString(R.string.机选));
                if (xuanwuGroup == null) {
                    initData();
                } else {
                    isClickList.clear();
                    for (XuanwuGroupBean bean : totalList) {
                        StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                    }
                    showcaizhong();
                }
            }
        });
        ll_caizhong_head1.setOnClickListener(v -> {
            //点击旋转
            caizhong_head1_iv.startAnimation(rotateAnimation);
            caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_down);

            if (!isOfficial) {
                String str = caizhong_head1_title.getText().toString();
                int x = 0;
                for (int i = 0; i < TITLE1.length; i++) {
                    if (str.equals(TITLE1[i])) {
                        x = i;
                        break;
                    }
                }
                showhead1popup(x);
            } else {
                showgfheadpop();
            }

        });

        caizhong_head1_back.setOnClickListener(v -> onBackPressed());

        k3_tab_confirm.setOnClickListener(v -> {
            int count = 0;

            count = StrUtil.isclickCal(isClickList);
            if (count == 0) {
                showToast(Utils.getString(R.string.请选择玩法));
                return;
            }
            if (StringMyUtil.isEmptyString(k3_tab_money.getText().toString()) && !MoneyUtils.isMoney(k3_tab_money.getText().toString())) {
                showToast(Utils.getString(R.string.请输入金额));
                return;
            }
            List<TouzhuModel> touzhuList = new ArrayList<>();
            for (IsClickModel isClickModel : isClickList) {
                if (isClickModel.getIsclick()) {
                    for (XuanwuGroupBean bean : totalList) {
                        if (isClickModel.getId().equals(String.valueOf(bean.getId()))) {
                            TouzhuModel touzhuModel = new TouzhuModel();
                            touzhuModel.setGroupname(bean.getXgroupname());
                            touzhuModel.setName(bean.getName());
                            touzhuModel.setId(String.valueOf(bean.getId()));
                            touzhuModel.setMoney(k3_tab_money.getText().toString());
                            touzhuList.add(touzhuModel);
                        }
                    }
                }
            }
            Intent intent = new Intent();
            intent.setClass(XuanwuActivity.this, TouzhuActivity.class);
            intent.putExtra("touzhuList", (Serializable) touzhuList);
            intent.putExtra("isClickList", (Serializable) isClickList);
            intent.putExtra("game", game);
            intent.putExtra("type_id", type_id);
            intent.putExtra("money", k3_tab_money.getText().toString());
            intent.putExtra("qishu", kjCountDown.getQishu());
            intent.putExtra("index", "0");
            intent.putExtra("ma", "");
            startActivityForResult(intent, ReqTouzhu);
        });

        k3_tab_reset.setOnClickListener(v -> {
            if(totalList.size()==0){
                return;
            }
            if (k3_tab_reset.getText().toString().equals(Utils.getString(R.string.重置))) {
                k3_tab_reset.setText(Utils.getString(R.string.机选));
                isClickList.clear();
                for (XuanwuGroupBean bean : totalList) {
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                }
                xuanwuParentAdapter.notifyDataSetChanged();
                k3_tab_money.setText("");
                k3_tab_num.setText("0");
            } else {
                k3_tab_reset.setText(Utils.getString(R.string.重置));
                List<String> idList = new ArrayList<>();
                if (caizhong_head1_title.getText().toString().equals(getString(R.string.两面))) {
                    for (XuanwuGroupBean bean : list1) {
                        idList.add(String.valueOf(bean.getId()));
                    }
                } else if (caizhong_head1_title.getText().toString().equals(getString(R.string.第一球))) {
                    for (XuanwuGroupBean bean : list2) {
                        idList.add(String.valueOf(bean.getId()));
                    }
                } else if (caizhong_head1_title.getText().toString().equals(getString(R.string.第二球))) {
                    for (XuanwuGroupBean bean : list3) {
                        idList.add(String.valueOf(bean.getId()));
                    }
                } else if (caizhong_head1_title.getText().toString().equals(getString(R.string.第三球))) {
                    for (XuanwuGroupBean bean : list4) {
                        idList.add(String.valueOf(bean.getId()));
                    }
                } else if (caizhong_head1_title.getText().toString().equals(getString(R.string.第四球))) {
                    for (XuanwuGroupBean bean : list5) {
                        idList.add(String.valueOf(bean.getId()));
                    }
                } else if (caizhong_head1_title.getText().toString().equals(getString(R.string.第五球))) {
                    for (XuanwuGroupBean bean : list6) {
                        idList.add(String.valueOf(bean.getId()));
                    }
                }
                Random random = new Random();
                if(idList.size()==0){
                    return;
                }
                String id = idList.get(random.nextInt(idList.size()));
                for (IsClickModel isClickModel : isClickList) {
                    if (isClickModel.getId().equals(id)) {
                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                    }
                }
                if (index_xy == 0) {
                    int position = 0;
                    List<String> datagGroupList = new ArrayList<>();
                    datagGroupList.add(getString(R.string.总和));
                    datagGroupList.add(getString(R.string.第一球));
                    datagGroupList.add(getString(R.string.第二球));
                    datagGroupList.add(getString(R.string.第三球));
                    datagGroupList.add(getString(R.string.第四球));
                    datagGroupList.add(getString(R.string.第五球));
                    for (int i = 0; i < list1.size(); i++) {
                        if (id.equals(String.valueOf(list1.get(i).getId()))) {
                            for (int j = 0; j < datagGroupList.size(); j++) {
                                if (list1.get(i).getXgroupname().equals(datagGroupList.get(j))) {
                                    position = j;
                                }
                            }
                        }
                    }
                    xuanwu_xy_recyclerview.smoothScrollToPosition(position);
                }
                xuanwuParentAdapter.notifyDataSetChanged();
                k3_tab_num.setText("1");
            }
        });

            ll_caizhong_head2_center.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestMoney();
                    Animation animation = AnimationUtils.loadAnimation(XuanwuActivity.this, R.anim.rotate_anim);
                    rotate_image.startAnimation(animation);
                }
            });
        ll2_xuanwu_head3.setOnClickListener(v -> customPopupWindow.initSscTodayResultData(XuanwuActivity.this, Integer.parseInt(game), Integer.parseInt(type_id), ll2_xuanwu_head3));
        ll_caizhong_head2_left.setOnClickListener(v -> customPopupWindow.showClassfyPop(ll_caizhong_head2_left, XuanwuActivity.this));
        caizhong_head1_menu.setOnClickListener(v -> customPopupWindow.showMenuPop(caizhong_head1_menu, XuanwuActivity.this));
        k3_tab_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //如果EditText中的数据不为空，且长度大于指定的最大长度
                if (!TextUtils.isEmpty(s) && s.length() > 8) {
                    //删除指定长度之后的数据
                    s.delete(8, k3_tab_money.getSelectionEnd());
                }
            }
        });
        tv_reload.setOnClickListener(v -> {
            initData();
            initCountDownEndPop();//倒计时结束的pop
            ReqCountdown();  //倒计时
            onResume();
        });

        tv_touzhu_yuan.setOnClickListener(v -> {
            tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(this,R.drawable.shape_yuan_press));
            tv_touzhu_yuan.setTextColor(ContextCompat.getColor(this,R.color.white));
            tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(this,R.drawable.shape_jiao_unpress));
            tv_touzhu_jiao.setTextColor(ContextCompat.getColor(this,R.color.textgray));
            danwei = 1.00;
            BigDecimal odds;
            if (index_gf_x == 4) {
                double max_odds = choicefourList.get(0).getOdds();
                for (int i = 1; i < choicefourList.size(); i++) {
                    if (max_odds < choicefourList.get(i).getOdds()) {
                        max_odds = choicefourList.get(i).getOdds();
                    }
                }
                odds = new BigDecimal(String.valueOf(max_odds));

            } else {
                odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
            }

            BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
            BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
            totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));

            BigDecimal totalmoney = times.multiply(new BigDecimal(tv_touzhu_num.getText().toString())).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
            totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            tv_touzhu_money.setText(String.valueOf(totalmoney));
        });
        tv_touzhu_jiao.setOnClickListener(v -> {
            tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(XuanwuActivity.this,R.drawable.shape_yuan_unpress));
            tv_touzhu_yuan.setTextColor(ContextCompat.getColor(XuanwuActivity.this,R.color.textgray));
            tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(XuanwuActivity.this,R.drawable.shape_jiao_press));
            tv_touzhu_jiao.setTextColor(ContextCompat.getColor(XuanwuActivity.this,R.color.white));
            danwei = 0.10;
            BigDecimal odds;
            if (index_gf_x == 4) {
                double max_odds = choicefourList.get(0).getOdds();
                for (int i = 1; i < choicefourList.size(); i++) {
                    if (max_odds < choicefourList.get(i).getOdds()) {
                        max_odds = choicefourList.get(i).getOdds();
                    }
                }
                odds = new BigDecimal(String.valueOf(max_odds));

            } else {
                odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
            }

            BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
            BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
            totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));

            BigDecimal totalmoney = times.multiply(new BigDecimal(tv_touzhu_num.getText().toString())).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
            totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            tv_touzhu_money.setText(String.valueOf(totalmoney));
        });
        tv_touzhu_gf_jia.setOnClickListener(v -> {
            String StrTimes = tv_touzhu_gf_times.getText().toString();
            int times = Integer.parseInt(StrTimes);
            times++;
            if (times > 10000) {
                tv_touzhu_gf_times.setText("10000");
            }
            tv_touzhu_gf_times.setText(String.valueOf(times));
        });
        tv_touzhu_gf_jian.setOnClickListener(v -> {
            String StrTimes = tv_touzhu_gf_times.getText().toString();
            int times = Integer.parseInt(StrTimes);
            times--;
            tv_touzhu_gf_times.setText(String.valueOf(times));
            if (times < 1) {
                tv_touzhu_gf_times.setText("1");
            }
        });
        tv_touzhu_gf_times.setSelection(tv_touzhu_gf_times.getText().length());
        tv_touzhu_gf_times.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果EditText中的数据不为空，且长度大于指定的最大长度
                tv_touzhu_gf_times.setSelection(tv_touzhu_gf_times.getText().length());
                if (!TextUtils.isEmpty(s) && (Integer.parseInt(s.toString()) > 10000)) {
                    //删除指定长度之后的数据
                    showToast(getString(R.string.最高10000倍));
                    tv_touzhu_gf_times.setText("10000");
                }
                if (s.length() == 0 || TextUtils.isEmpty(s)) {
                    tv_touzhu_gf_times.setText("1");
                }
                //TODO 大数 精确计算
                BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
                BigDecimal totalmoney = times.multiply(new BigDecimal(tv_touzhu_num.getText().toString())).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
                totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                tv_touzhu_money.setText(String.valueOf(totalmoney));
                if (newplayModel != null) {
                    BigDecimal odds;
                    if (index_gf_x == 4) {
                        double max_odds = choicefourList.get(0).getOdds();
                        for (int i = 1; i < choicefourList.size(); i++) {
                            if (max_odds < choicefourList.get(i).getOdds()) {
                                max_odds = choicefourList.get(i).getOdds();
                            }
                        }
                        odds = new BigDecimal(String.valueOf(max_odds));
                    } else {
                        odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
                    }

                    BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
                    totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                    tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));
                }
            }
        });

        tv_touzhu_jixuan.setOnClickListener(v -> {
            if (tv_touzhu_jixuan.getText().toString().equals(Utils.getString(R.string.重置))) {
                tv_touzhu_jixuan.setText(Utils.getString(R.string.机选));
                tv_touzhu_gf_times.setText("1");
                tv_touzhu_num.setText("0");
                tv_touzhu_money.setText("0.00");
                danwei = 1.00;
                tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(XuanwuActivity.this,R.drawable.shape_yuan_press));
                tv_touzhu_yuan.setTextColor(ContextCompat.getColor(XuanwuActivity.this,R.color.white));
                tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(XuanwuActivity.this,R.drawable.shape_jiao_unpress));
                tv_touzhu_jiao.setTextColor(ContextCompat.getColor(XuanwuActivity.this,R.color.textgray));
                // isClickMap.clear();
                isClickList.clear();
                if (index_gf_x == 4 && index_gf_y == 0) {
                    for (int i = 0; i < choicefourList.size(); i++) {
                        //   isClickMap.put(String.valueOf(choicefourList.get(i).getId()), false);
                        StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId()), false);
                    }
                    for (int i = 0; i < 1; i++) {
                        for (int j = 0; j < 6; j++) {
                            B[i][j] = 0;
                        }
                    }
                    xuanwuGfP2Adapter.notifyDataSetChanged();
                } else if (index_gf_x == 4 && index_gf_y == 1) {
                    for (int i = 0; i < choicefourList.size(); i++) {
                        StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes())), false);
                    }
                    for (int i = 0; i < 1; i++) {
                        for (int j = 0; j < 6; j++) {
                            B[i][j] = 0;
                        }
                    }
                    xuanwuGfPAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < choicefourList.size(); i++) {
                        String[] arry = choicefourList.get(i).getCodes().split(",");
                        for (int j = 0; j < arry.length; j++) {
                            StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(arry[j])), false);
                        }
                    }
                    for (int i = 0; i < choicefourList.size(); i++) {
                        for (int j = 0; j < 6; j++) {
                            B[i][j] = 0;
                        }
                    }
                    xuanwuGfPAdapter.notifyDataSetChanged();
                }
            } else {
                tv_touzhu_jixuan.setText(Utils.getString(R.string.重置));
                tv_touzhu_gf_times.setText("1");
                tv_touzhu_num.setText("1");
                tv_touzhu_money.setText("2.00");
                danwei = 1.00;
                tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(XuanwuActivity.this,R.drawable.shape_yuan_press));
                tv_touzhu_yuan.setTextColor(ContextCompat.getColor(XuanwuActivity.this,R.color.white));
                tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(XuanwuActivity.this,R.drawable.shape_jiao_unpress));
                tv_touzhu_jiao.setTextColor(ContextCompat.getColor(XuanwuActivity.this,R.color.textgray));
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 11; j++) {
                        A[i][j] = 0;
                    }
                }
                if (index_gf_x == 4 && index_gf_y == 0) {
                    isClickList.clear();
                    for (int i = 0; i < choicefourList.size(); i++) {
                        StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId()), false);
                    }
                    List<Long> idList = new ArrayList<>();
                    for (int i = 1; i < choicefourList.size(); i++) {
                        idList.add(choicefourList.get(i).getId());
                    }
                    idList = MyMathUtils.getRandomNum(idList, 1);
                    StrUtil.isclickReplace(isClickList, String.valueOf(idList.get(0)), true);
                    for (int j = 0; j < choicefourList.size(); j++) {
                        if (idList.get(0) == choicefourList.get(j).getId()) {
                            A[0][j] = 1;
                        }
                    }
                    for (int i = 0; i < 1; i++) {
                        for (int j = 0; j < 6; j++) {
                            B[i][j] = 0;
                        }
                    }
                    xuanwuGfP2Adapter.notifyDataSetChanged();
                } else if (index_gf_x == 4 && index_gf_y == 1) {
                    for (int i = 0; i < choicefourList.size(); i++) {
                        //  isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes())), false);
                        StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes())), false);
                    }
                    List<Long> idList = new ArrayList<>();
                    for (int i = 1; i < choicefourList.size(); i++) {
                        idList.add(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes()));
                    }
                    idList = MyMathUtils.getRandomNum(idList, 1);
                    StrUtil.isclickReplace(isClickList, String.valueOf(idList.get(0)), true);

                    for (int j = 0; j < choicefourList.size(); j++) {
                        if (idList.get(0) == choicefourList.get(j).getId() * 100 + Integer.parseInt(choicefourList.get(j).getCodes()))
                            ;
                        A[0][j] = 1;
                    }
                    for (int i = 0; i < 1; i++) {
                        for (int j = 0; j < 6; j++) {
                            B[i][j] = 0;
                        }
                    }
                    xuanwuGfPAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < choicefourList.size(); i++) {
                        String[] arry = choicefourList.get(i).getCodes().split(",");
                        for (int j = 0; j < arry.length; j++) {
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(arry[j])), false);
                        }
                    }
                    for (int i = 0; i < choicefourList.size(); i++) {
                        for (int j = 0; j < 6; j++) {
                            B[i][j] = 0;
                        }
                    }

                    if (index_gf_x == 0) {
                        if (index_gf_y == 0) {
                            List<Long> idList = new ArrayList<>();
                            for (long i = 1; i < 12; i++) {
                                idList.add(i);
                            }
                            idList = MyMathUtils.getRandomNum(idList, choicefourList.size());  //TODO 此时idList.size == choicefourList.size
                            for (int i = 0; i < idList.size(); i++) {

                                for (IsClickModel isClickModel : isClickList) {
                                    if (isClickModel.getId().equals(String.valueOf(choicefourList.get(i).getId() * 100 + idList.get(i)))) {
                                        StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                        int j = (int) (idList.get(i) - 1);
                                        A[i][j] = 1;
                                    }
                                }
                            }
                            xuanwuGfPAdapter.notifyDataSetChanged();
                        }
                        if (index_gf_y == 1) {
                            List<Long> idList = new ArrayList<>();
                            for (long i = 1; i < 12; i++) {
                                idList.add(i);
                            }
                            idList = MyMathUtils.getRandomNum(idList, 3);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(0)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(1)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(2)), true);
                            A[0][(int) (idList.get(0) - 1)] = 1;
                            A[0][(int) (idList.get(1) - 1)] = 1;
                            A[0][(int) (idList.get(2) - 1)] = 1;
                            xuanwuGfPAdapter.notifyDataSetChanged();
                        }
                        if (index_gf_y == 2) {
                            List<Long> idList = new ArrayList<>();
                            for (long i = 1; i < 12; i++) {
                                idList.add(i);
                            }
                            idList = MyMathUtils.getRandomNum(idList, 3);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(0)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(1).getId() * 100 + idList.get(1)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(1).getId() * 100 + idList.get(2)), true);
                            A[0][(int) (idList.get(0) - 1)] = 1;
                            A[1][(int) (idList.get(1) - 1)] = 1;
                            A[1][(int) (idList.get(2) - 1)] = 1;
                            xuanwuGfPAdapter.notifyDataSetChanged();
                        }
                    }
                    if (index_gf_x == 1) {
                        if (index_gf_y == 0) {
                            List<Long> idList = new ArrayList<>();
                            for (long i = 1; i < 12; i++) {
                                idList.add(i);
                            }
                            idList = MyMathUtils.getRandomNum(idList, 2);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(0)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(1).getId() * 100 + idList.get(1)), true);
                            A[0][(int) (idList.get(0) - 1)] = 1;
                            A[1][(int) (idList.get(1) - 1)] = 1;
                            xuanwuGfPAdapter.notifyDataSetChanged();
                        }
                        if (index_gf_y == 1) {
                            List<Long> idList = new ArrayList<>();
                            for (long i = 1; i < 12; i++) {
                                idList.add(i);
                            }
                            idList = MyMathUtils.getRandomNum(idList, 2);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(0)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(1)), true);
                            A[0][(int) (idList.get(0) - 1)] = 1;
                            A[0][(int) (idList.get(1) - 1)] = 1;
                            xuanwuGfPAdapter.notifyDataSetChanged();
                        }
                        if (index_gf_y == 2) {
                            List<Long> idList = new ArrayList<>();
                            for (long i = 1; i < 12; i++) {
                                idList.add(i);
                            }
                            idList = MyMathUtils.getRandomNum(idList, 2);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(0)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(1).getId() * 100 + idList.get(1)), true);
                            A[0][(int) (idList.get(0) - 1)] = 1;
                            A[1][(int) (idList.get(1) - 1)] = 1;
                            xuanwuGfPAdapter.notifyDataSetChanged();
                        }
                    }
                    if (index_gf_x == 2) {
                        List<Long> idList = new ArrayList<>();
                        for (long i = 1; i < 12; i++) {
                            idList.add(i);
                        }
                        idList = MyMathUtils.getRandomNum(idList, 1);
                        StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(0)), true);
                        A[0][(int) (idList.get(0) - 1)] = 1;
                        xuanwuGfPAdapter.notifyDataSetChanged();
                    }
                    if (index_gf_x == 3) {
                        List<Long> idList = new ArrayList<>();
                        for (int i = 0; i < choicefourList.size(); i++) {
                            String[] arry = choicefourList.get(i).getCodes().split(",");
                            for (int j = 0; j < arry.length; j++) {
                                idList.add(choicefourList.get(i).getId() * 100 + Integer.parseInt(arry[j]));
                            }
                        }
                        idList = MyMathUtils.getRandomNum(idList, 1);
                        StrUtil.isclickReplace(isClickList, String.valueOf(idList.get(0)), true);
                        for (int i = 0; i < choicefourList.size(); i++) {
                            for (int j = 0; j < 11; j++) {
                                if (idList.get(0) == choicefourList.get(i).getId() * 100 + j + 1) {
                                    A[i][j] = 1;
                                }
                            }
                        }
                        xuanwuGfPAdapter.notifyDataSetChanged();
                    }

                    if (index_gf_x == 5) {

                        List<Long> idList = new ArrayList<>();
                        for (long i = 1; i < 12; i++) {
                            idList.add(i);
                        }
                        idList = MyMathUtils.getRandomNum(idList, index_gf_y + 1);
                        for (int i = 0; i < idList.size(); i++) {
                            // isClickMap.replace(String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(i)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(i)), true);
                            A[0][(int) (idList.get(i) - 1)] = 1;
                        }
                        xuanwuGfPAdapter.notifyDataSetChanged();
                    }
                    if (index_gf_x == 6) {
                        List<Long> idList = new ArrayList<>();
                        for (long i = 1; i < 12; i++) {
                            idList.add(i);
                        }
                        idList = MyMathUtils.getRandomNum(idList, index_gf_y + 2);
                        StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(0).getId() * 100 + idList.get(0)), true);
                        A[0][(int) (idList.get(0) - 1)] = 1;
                        for (int i = 1; i < idList.size(); i++) {
                            // isClickMap.replace(String.valueOf(choicefourList.get(1).getId() * 100 + idList.get(i)), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(choicefourList.get(1).getId() * 100 + idList.get(i)), true);
                            A[1][(int) (idList.get(i) - 1)] = 1;
                        }
                        xuanwuGfPAdapter.notifyDataSetChanged();
                    }
                }

            }
        });

        touzhu_gf_confirm.setOnClickListener(v -> {

                    long nowTime = System.currentTimeMillis();
                    if (nowTime - mLastClickTime > TIME_INTERVAL) {
                        if (Integer.parseInt(tv_touzhu_num.getText().toString()) < 1) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                        if (Double.parseDouble(tv_touzhu_money.getText().toString()) < 1) {
                            showToast(Utils.getString(R.string.投注)+Utils.getString(R.string.金额应大于1元));
                            return;
                        }
                        String groupname = typename + "-" + gftitleList1.get(index_gf_x) + gftitleList2.get(index_gf_y);
                        String danjia = String.valueOf(2 * danwei);
                        String totalnum = tv_touzhu_num.getText().toString();
                        String totalmoney = tv_touzhu_money.getText().toString();
                        String str = new String();

                        if (index_gf_x == 4) {
                            for (int i = 0; i < choicefourList.size(); i++) {
                                if (A[0][i] == 1) {
                                    str = str + "\n" + choicefourList.get(i).getName() + ":" + choicefourList.get(i).getCodes();
                                }

                            }
                        } else {
                            for (int i = 0; i < choicefourList.size(); i++) {
                                int m = 0;
                                for (int n = 0; n < 11; n++) {
                                    m = (int) (m + A[i][n]);
                                }
                                //TODO 2019-7-23  判断是否A1[i][]中是否有选中数据
                                if (m > 0) {
                                    str = str + "\n" + choicefourList.get(i).getName() + ":";
                                    for (int j = 0; j < 11; j++) {
                                        if (A[i][j] == 1) {
                                            str = str + String.valueOf(j + 1) + ",";
                                        }
                                    }
                                    str = str.substring(0, str.length() - 1);
                                }
                            }
                        }

                        final BetDialog betDialog = new BetDialog(XuanwuActivity.this);
                        betDialog.setGroupname(groupname + ":");
                        betDialog.setQishu(qishu);
                        betDialog.setDanjia(danjia);
                        betDialog.setContent(str);
                        betDialog.setTotalnum(totalnum);
                        betDialog.setTotalmoney(totalmoney);

                        betDialog.setYesOnclickListener(() -> {
                                    betDialog.dismiss();
                                    String ruleId = String.valueOf(playRuleListThreeBean.getId());
                                    String amount = tv_touzhu_gf_times.getText().toString();
                                    String amountType = "";
                                    if (danwei == 1) {
                                        amountType = "0";
                                    } else {
                                        amountType = "1";
                                    }

                                    if (index_gf_x == 4) {
                                        betModelList.clear();
                                        for (int i = 0; i < choicefourList.size(); i++) {
                                            if (A[0][i] == 1) {
                                                GfBetModel betModel = new GfBetModel();
                                                betModel.setName(choicefourList.get(i).getCodes());
                                                betModel.setRuleTwoId(choicefourList.get(i).getId());
                                                betModelList.add(betModel);
                                            }
                                        }
                                    } else {
                                        betModelList.clear();
                                        for (int i = 0; i < choicefourList.size(); i++) {
                                            GfBetModel betModel = new GfBetModel();
                                            String name = new String();
                                            for (int j = 0; j < 11; j++) {
                                                if (A[i][j] == 1) {
                                                    name = name + String.valueOf(j + 1) + ",";
                                                }
                                            }
                                            name = name.substring(0, name.length() - 1);
                                            betModel.setName(name);
                                            betModel.setRuleTwoId(choicefourList.get(i).getId());
                                            betModelList.add(betModel);
                                        }
                                    }
                                    HashMap<String, Object> betMap = new HashMap<>();

                                    betMap.put("amount", amount);
                                    betMap.put("amountType", amountType);
                                    betMap.put("ruleId", ruleId);
                                    betMap.put("nameList", betModelList);
                                    List<HashMap> betList = new ArrayList<>();
                                    betList.add(betMap);
                                    ProgressDialogUtil.show(XuanwuActivity.this, Utils.getString(R.string.下注中)+"...");
                                    MyRequest.ReqNewplayBet(XuanwuActivity.this, game, type_id, qishu, betList, 0, new MyRequest.MyRequestListener1() {
                                        @Override
                                        public void success1(String content) {
                                            ProgressDialogUtil.stop(XuanwuActivity.this);
                                            Gson gson = new GsonBuilder().serializeNulls().create();
                                            try {
                                                State state = gson.fromJson(content, State.class);
                                                if (!StringMyUtil.isEmptyString(state) && state.getStatus().equals("success")) {
                                                    showToast(Utils.getString(R.string.下注成功));
                                                    isClickList.clear();

                                                    if (index_gf_x == 4 && index_gf_y == 0) {
                                                        for (int i = 0; i < choicefourList.size(); i++) {
                                                            StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId()), false);
                                                        }
                                                        for (int i = 0; i < 1; i++) {
                                                            for (int j = 0; j < 6; j++) {
                                                                B[i][j] = 0;
                                                            }
                                                        }
                                                        xuanwuGfP2Adapter.notifyDataSetChanged();
                                                    } else if (index_gf_x == 4 && index_gf_y == 1) {
                                                        for (int i = 0; i < choicefourList.size(); i++) {
                                                            //   isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes())), false);
                                                            StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + Integer.parseInt(choicefourList.get(i).getCodes())), false);
                                                        }
                                                        for (int i = 0; i < 1; i++) {
                                                            for (int j = 0; j < 6; j++) {
                                                                B[i][j] = 0;
                                                            }
                                                        }
                                                        xuanwuGfPAdapter.notifyDataSetChanged();
                                                    } else {
                                                        for (int i = 0; i < choicefourList.size(); i++) {
                                                            for (int j = 1; j < 12; j++) {
                                                                //  isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                                                                StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                                                            }
                                                        }
                                                        for (int i = 0; i < choicefourList.size(); i++) {
                                                            for (int j = 0; j < 6; j++) {
                                                                B[i][j] = 0;
                                                            }
                                                        }
                                                        xuanwuGfPAdapter.notifyDataSetChanged();
                                                    }
                                                    tv_touzhu_gf_times.setText("1");
                                                    tv_touzhu_num.setText("0");
                                                    tv_touzhu_money.setText("0.00");
                                                    tv_touzhu_jixuan.setText(Utils.getString(R.string.机选));
                                                    requestMoney();
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void failed1(String failMessage) {
                                            ProgressDialogUtil.stop(XuanwuActivity.this);
                                            showToast(Utils.getString(R.string.下注失败)+"\n" +failMessage);
                                        }
                                    });
                                }
                        );
                        betDialog.setNoOnclickListener(() -> betDialog.dismiss());
                        betDialog.show();
                        betDialog.setCanceledOnTouchOutside(false); //外部不可点击
                    }
                }
        );
        xuanwu_gf_recyclerview2_tv1.setOnClickListener(v -> {
                JiangjinDialog jiangjinDialog = new JiangjinDialog(XuanwuActivity.this, choicefourList);
                jiangjinDialog.setYesOnclickListener(()-> jiangjinDialog.dismiss());
                jiangjinDialog.show();
                jiangjinDialog.setCanceledOnTouchOutside(true);
            }
        );
    }

    private void requestMoney() {
        ReqMemberMoney(XuanwuActivity.this, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                Utils.logE("content:", "" + content);
                 memberMoney = JSONObject.parseObject(content, MemberMoney.class);
                 ll_caizhong_head2_center_tv.setText(MoneyUtils.parseMoey(String.valueOf(XuanwuActivity.this.memberMoney.getMemberMoney().getAmount())));
            }

            @Override
            public void failed(String content) {
            }
        });
    }

    public void showhead1popup(int x) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.layout_head1popuprecycler, null, false);//引入弹窗布局
        head1recyclerview = vPopupWindow.findViewById(R.id.head1_recyclerview);
        head1popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置背景透明
        addBackground();
        View view = findViewById(R.id.rl_head1);
        head1popupWindow.showAsDropDown(view);
        loadgrideDate(x);
    }

    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        head1popupWindow.setOutsideTouchable(true);
        head1popupWindow.setOnDismissListener(() -> {
            caizhong_head1_iv.startAnimation(rotateAnimation);
            caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_up);
            lp.alpha = 1f;
            getWindow().setAttributes(lp);
        });
    }

    private void loadgrideDate(int x) {
        final List<String> titleList = new ArrayList<>();
        for (int i = 0; i < TITLE1.length; i++) {
            titleList.add(TITLE1[i]);
        }
        RecyclerViewGridAdapter recyclerViewGridAdapter = new RecyclerViewGridAdapter(this, titleList, x);
        head1recyclerview.setAdapter(recyclerViewGridAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        head1recyclerview.setLayoutManager(gridLayoutManager);
        recyclerViewGridAdapter.setOnItemClickListener(((view, position) -> {
            caizhong_head1_title.setText(titleList.get(position));
            if (head1popupWindow.isShowing()) {
                head1popupWindow.dismiss();
            }
            if (!isOfficial) {
                index_xy = position;
                showcaizhong();
            }
        }));
    }

    private void showgfheadpop() {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.layout_gfhead_pop, null, false);//引入弹窗布局
        gfhead_pop_recy1 = vPopupWindow.findViewById(R.id.gfhead_pop_recy1);
        gfhead_pop_recy2 = vPopupWindow.findViewById(R.id.gfhead_pop_recy2);
        gfheadPop = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        gfheadPop.setOutsideTouchable(true);
        gfheadPop.setOnDismissListener(() -> {
            caizhong_head1_iv.startAnimation(rotateAnimation);
            caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_up);
            lp.alpha = 1f;
            getWindow().setAttributes(lp);
        });

        View view = findViewById(R.id.rl_head1);
        gfheadPop.showAsDropDown(view);

        gfhead1Adapter = new GfHeadRecy1Adapter(this, gftitleList1, index_gf_x);
        gfhead_pop_recy1.setAdapter(gfhead1Adapter);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 4);
        gridLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);
        gfhead_pop_recy1.setLayoutManager(gridLayoutManager1);

        int row = 0;
        if (index_gf_x == 5 || index_gf_x == 6) {
            row = 4;
        } else {
            row = 3;
        }
        gfhead2Adapter = new GfHeadRecy2Adapter(this, gftitleList2, index_gf_y);
        gfhead_pop_recy2.setAdapter(gfhead2Adapter);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, row);
        gridLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
        gfhead_pop_recy2.setLayoutManager(gridLayoutManager2);

    }


    //对投注成功后进行数据重置 更新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            if (resultCode == 102) {
                isClickList.clear();
                for (XuanwuGroupBean bean : totalList) {
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                }
                xuanwuParentAdapter.notifyDataSetChanged();
                k3_tab_num.setText("" + 0);
                k3_tab_money.setText("");
                k3_tab_reset.setText(Utils.getString(R.string.机选));
                requestMoney();
            }
        }
    }

    Runnable runnableTime = new Runnable() {
        @Override
        public void run() {
            if (timeCount > 0&&kjCountDown!=null) {
                timeCount = (endtime - tqtime) - (System.currentTimeMillis() - sjcha);
                Utils.logE("timeCount:", "" + timeCount);
                int mHour = (int) ((timeCount / 1000) / (60 * 60));  //  对3600 取整  就是小时
                int mMin = (int) (((timeCount / 1000) % (60 * 60)) / 60);//  对3600 取余除以60 就是多出的分
                int mSecond = (int) ((timeCount / 1000) % 60); //  对60 取余  就是多出的秒
                String str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
                xuanwu_head3_tv2.setText(str_time);
                if (timeCount <= 1) {
                    timeCount = 0L;
                    xuanwu_head3_tv2.setText(Utils.getString(R.string.已结束));
                    if (CountDownEndPop != null) {
                        lastQiShuTv.setText(qishu);
                        newQiShuTv.setText((Long.parseLong(qishu) + 1) + "");
                        if (!isFinishing() && CountDownEndPop != null) {
                            CountDownEndPop.showAtLocation(ll1_xuanwu_head3, Gravity.CENTER, 0, 0);
                        }
                        ProgressDialogUtil.darkenBackground(XuanwuActivity.this, 0.5f);
                    }
                    isWaitopen = true;
                    ReqCountdown();
                    Utils.logE("isWaitopen:", "" + isWaitopen + ",runnableTime");
                }
            }
            handler.postDelayed(runnableTime, 300);
        }
    };

    Runnable runnableRandom = new Runnable() {
        @Override
        public void run() {
            if (isWaitopen) {
                if (!StringMyUtil.isEmptyString(qishu)) {
                    long waitOpenQiShu = Long.parseLong(qishu) - 1;
                    xuanwu_head3_tv3.setText(waitOpenQiShu + " "+Utils.getString(R.string.期开奖号码));
                    List<String> list = new ArrayList<String>();
                    kjList.clear();
                    Random random = new Random();
                    kjList.add(qius[random.nextInt(10)]);
                    xuanwu_head3_iv1.setImageResource(kjList.get(0));
                    xuanwu_head3_iv2.setImageResource(kjList.get(0));
                    xuanwu_head3_iv3.setImageResource(kjList.get(0));
                    xuanwu_head3_iv4.setImageResource(kjList.get(0));
                    xuanwu_head3_iv5.setImageResource(kjList.get(0));
                }
                handler.postDelayed(runnableRandom, 150);
            } else {
                handler.removeCallbacks(runnableRandom);
            }
        }
    };

    Runnable runnableRequestOpen = new Runnable() {
        @Override
        public void run() {
            if (isWaitopen) {
                //TODO 开奖中
                initOpenResult();
                handler.postDelayed(runnableRequestOpen, 8000);
            } else {
                handler.removeCallbacks(runnableRequestOpen);
            }
        }

    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    showcaizhong();
                    break;

                case 2:
                    requestMoney();
                    break;
            }
        }
    };

    /*
      是否中奖(中奖后要更新资金信息)
    */
    Runnable runnableZj = new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name", SharePreferencesUtil.getString(XuanwuActivity.this, "nickname", ""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)   {
                    try {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        todayZJ = jsonObject.getString("todayZJ");
                        if (todayZJ.equals("1")) {
                            Message message = Message.obtain();
                            message.what = 2;
                            handler.sendMessage(message);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                @Override
                public void failed(MessageHead messageHead) {
                }
            });
            handler.postDelayed(runnableZj, 2000);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnableRandom);
        handler.removeCallbacks(runnableRequestOpen);
        handler.removeCallbacks(runnableTime);
        handler.removeCallbacks(runnableZj);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
      /*handler.removeCallbacks(runnableRandom);
        handler.removeCallbacks(runnableRequestOpen);
        handler.removeCallbacks(runnableTime);
        handler.removeCallbacks(runnableZj);*/
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        customPopupWindow.selectorId(hbGameClassModel);
    }
}
