/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.MarksixParentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RecyclerViewGridAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KJCountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MarksixBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MarksixGroup;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MarksixLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MemberMoney;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.AnimUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CustomPopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MoneyUtils;
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
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqlastLottery;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil.timeMode;

public class MarksixActivity extends BaseActivity implements CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener {

    String type_id, isopenOffice, isStart, game, typename;
    MarksixGroup marksixGroup;
    KJCountDown kjCountDown;
    MarksixLottery marksixLottery;
    MemberMoney memberMoney;
    List<MarksixGroup.MarksixRulelistBean> marksixRulelist = new ArrayList<>();
    List<MarksixGroup.ClassifylistallBean> classifylistall = new ArrayList<>();
    List<MarksixGroup.GrouplistBean> grouplist = new ArrayList<>();

    List<MarksixBean> dataBeanList = new ArrayList<>();  //每个group的全部数据
    List<String> datagGroupList = new ArrayList<>();  //小组名称
    List<MarksixBean> smallDataBeanList;   //有tab的时候所选tab的数据
    List<String> TitleList;
    List<String> TabNameList = new ArrayList<>();
    int INDEX = 0;
    MarksixParentAdapter marksixParentAdapter;
    //   HashMap<String, Boolean> isClickMap = new LinkedHashMap<>();
    List<IsClickModel> isClickList = new ArrayList<>();

    Long endtime, localtime, sjcha, tqtime; //截止时间 当前时间 服务器与本地时间差
    String qishu, lastqishu;
    String lotteryqishu;
    Long timeCount = 0L;  //倒计时数
    boolean isWaitopen = true;
    List<Integer> kjList = new ArrayList<Integer>(); //开奖list
    TextView lastQiShuTv;
    TextView newQiShuTv;
    TextView countDownEndSure;
    PopupWindow CountDownEndPop;//倒计时结束时的pop
    Animation rotateAnimation = AnimUtils.getAnimation(180);
    RecyclerView head1recyclerview;
    PopupWindow head1popupWindow;

    public static final int ReqTouzhu = 101;
    long ZXBZ_ID;//自选不中id
    long LM_ID;
    private String todayZJ;
    @BindView(R.id.caizhong_head1_back)
    TextView caizhong_head1_back;
    @BindView(R.id.ll_caizhong_head1)
    LinearLayout ll_caizhong_head1;
    @BindView(R.id.caizhong_head1_iv)
    ImageView caizhong_head1_iv;
    @BindView(R.id.caizhong_head1_title)
    TextView caizhong_head1_title;  //标题
    @BindView(R.id.ll_caizhong_head2_left)
    LinearLayout ll_caizhong_head2_left;
    @BindView(R.id.ll_caizhong_head2_left_tv)
    TextView ll_caizhong_head2_left_tv;
    @BindView(R.id.marksix_tab)
    TabLayout marksix_tab;   //tablayout
    @BindView(R.id.marksix_recyclerview)
    RecyclerView marksix_recyclerview;
    @BindView(R.id.k3_tab_num)    //已选注数
            TextView k3_tab_num;
    @BindView(R.id.k3_tab_money)
    EditText k3_tab_money;
    @BindView(R.id.rotate_image)
    ImageView rotate_image;
    @BindView(R.id.k3_tab_confirm)
    TextView k3_tab_confirm;
    @BindView(R.id.k3_tab_reset)
    TextView k3_tab_reset;
    @BindView(R.id.marksix_head3_tv1)//当前期数
            TextView marksix_head3_tv1;
    @BindView(R.id.marksix_head3_tv3)  //倒计时textview
            TextView marksix_head3_tv3;
    @BindView(R.id.marksix_head3_tv4)//上期开奖号码
            TextView marksix_head3_tv4;
    @BindView(R.id.marksix_no_tv1)   //开奖号码
            TextView marksix_no_tv1;
    @BindView(R.id.marksix_no_tv2)   //开奖号码
            TextView marksix_no_tv2;
    @BindView(R.id.marksix_no_tv3)   //开奖号码
            TextView marksix_no_tv3;
    @BindView(R.id.marksix_no_tv4)   //开奖号码
            TextView marksix_no_tv4;
    @BindView(R.id.marksix_no_tv5)   //开奖号码
            TextView marksix_no_tv5;
    @BindView(R.id.marksix_no_tv6)   //开奖号码
            TextView marksix_no_tv6;
    @BindView(R.id.marksix_no_tv7)   //开奖号码
            TextView marksix_no_tv7;
    @BindView(R.id.marksix_sx_tv1)   //开奖号码
            TextView marksix_sx_tv1;
    @BindView(R.id.marksix_sx_tv2)   //开奖号码
            TextView marksix_sx_tv2;
    @BindView(R.id.marksix_sx_tv3)   //开奖号码
            TextView marksix_sx_tv3;
    @BindView(R.id.marksix_sx_tv4)   //开奖号码
            TextView marksix_sx_tv4;
    @BindView(R.id.marksix_sx_tv5)   //开奖号码
            TextView marksix_sx_tv5;
    @BindView(R.id.marksix_sx_tv6)   //开奖号码
            TextView marksix_sx_tv6;
    @BindView(R.id.marksix_sx_tv7)   //开奖号码
            TextView marksix_sx_tv7;
    @BindView(R.id.ll1_marksix_head3)
    LinearLayout ll1_marksix_head3;
    @BindView(R.id.ll_reload)
    LinearLayout ll_reload;
    @BindView(R.id.tv_reload)
    TextView tv_reload;
    @BindView(R.id.ll_caizhong_head1_parent)
    LinearLayout ll_caizhong_head1_parent;

    @BindView(R.id.caizhong_head1_menu)
    TextView caizhong_head1_menu;
    @BindView(R.id.ll2_marksix_head3)
    LinearLayout ll2_marksix_head3;
    @BindView(R.id.ll_caizhong_head2_right)
    LinearLayout ll_caizhong_head2_right;
    @BindView(R.id.ll_caizhong_head2_center)
    LinearLayout ll_caizhong_head2_center;
    @BindView(R.id.ll_caizhong_head2_center_tv)
    TextView ll_caizhong_head2_center_tv;
    @BindView(R.id.marksix_tab_main)
    LinearLayout marksix_tab_main;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.six_load_img)
    GifImageView six_load_img;
    @BindView(R.id.fenpan_tv)
    TextView fenpan_tv;
    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_marksix);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        Utils. saveLotteryHistory(Integer.parseInt(game),Integer.parseInt(type_id));
        initData();
        ReqCountdown();
        clickView();
        initCountDownEndPop();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initAllPop();
        if ("0".equals(isopenOffice)){
            ll_caizhong_head2_right.setVisibility(View.INVISIBLE);
        }else {
            ll_caizhong_head2_right.setVisibility(View.VISIBLE);
        }
        ll_caizhong_head2_right.setVisibility(View.INVISIBLE);
        if (NetworkUtil.isNetworkConnected(this)) {
            marksix_tab_main.setVisibility(View.VISIBLE);
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
            marksix_tab_main.setVisibility(View.GONE);
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

    private void initData() {
        checkPara();
        loading_linear.setVisibility(View.VISIBLE);
        ReqgetGroup(this, game, type_id, -1, new MyRequest.MyRequestListener() {

            @Override
            public void success(String content) {
                loading_linear.setVisibility(View.GONE);
                Utils.logE("content", content);
                if (!StringMyUtil.isEmptyString(content)) {
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    try {
                        marksixGroup = gson.fromJson(content, MarksixGroup.class);
                        if (marksixGroup!=null){
                            classifylistall = marksixGroup.getClassifylistall();
                            grouplist = marksixGroup.getGrouplist();
                            marksixRulelist = marksixGroup.getMarksixRulelist();
                            Utils.logE("marksixGroup", "" + marksixGroup);
                            TitleList = new ArrayList<>();
                            if (!StringMyUtil.isEmptyString(marksixGroup)) {
                                for (MarksixGroup.GrouplistBean grouplistBean : marksixGroup.getGrouplist()) {
                                    TitleList.add(grouplistBean.getGroupname());
                                }
                            }
                            caizhong_head1_title.setText(TitleList.get(0).substring(0, 2));
                            ll_caizhong_head2_left_tv.setText(typename);
                            shouwView(0);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            @Override
            public void failed(String content) {
                loading_linear.setVisibility(View.GONE);
            }
        });
        requestMoney();
    }

    /*
跳转各种pop,开奖结果 等
 */
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
        customPopupWindow.initMarksixTodayResultPop(this, Integer.parseInt(game), Integer.parseInt(type_id),false);
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
        CountDownEndPop.setOnDismissListener(() -> ProgressDialogUtil.darkenBackground(MarksixActivity.this, 1f));
        countDownEndSure.setOnClickListener(v ->  CountDownEndPop.dismiss());
    }

    //请求倒计时
    private void ReqCountdown() {
        six_load_img.setVisibility(View.VISIBLE);
        marksix_head3_tv3.setVisibility(View.GONE);
        ReqCount(this, game, type_id, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                if (!StringMyUtil.isEmptyString(content)) {
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    try {
                        kjCountDown = gson.fromJson(content, KJCountDown.class);
                        if (kjCountDown!=null){
                            if(StringMyUtil.isEmptyString(kjCountDown.getQishu())){
                                fenpan_tv.setVisibility(View.VISIBLE);
                                marksix_head3_tv1.setText("- - -");
                                marksix_head3_tv3.setText("- - -");
                            }else {
                                fenpan_tv.setVisibility(View.GONE);
                                endtime = Long.parseLong(DateUtil.dateToStamp(kjCountDown.getStoptimestr()));
                                //   localtime = System.currentTimeMillis();
                                tqtime = (long) kjCountDown.getTqtime() * 1000;
                                sjcha = SharePreferencesUtil.getLong(MarksixActivity.this, "shijiancha", 0l);//servertime - localtime;
                                qishu = kjCountDown.getQishu();
                                timeCount = 1L;
                                lastqishu = kjCountDown.getLastqishu();
                                marksix_head3_tv1.setText(qishu + Utils.getString(R.string. 期));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        six_load_img.setVisibility(View.GONE);
                                        marksix_head3_tv3.setVisibility(View.VISIBLE);
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
                six_load_img.setVisibility(View.VISIBLE);
                marksix_head3_tv3.setVisibility(View.GONE);
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
                // Gson gson = new Gson();
                Gson gson = new GsonBuilder().serializeNulls().create();
                if (!StringMyUtil.isEmptyString(content)) {
                    try {
                        marksixLottery = gson.fromJson(content, MarksixLottery.class);
                        if (marksixLottery!=null){
                            Utils.logE("content", content);
                            lotteryqishu = marksixLottery.getMarksixLotterylist().get(0).getLotteryqishu();
                            if (!StringMyUtil.isEmptyString(qishu)) {
                                //是否是当天最后一期
                                if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu.substring(lotteryqishu.length() - 2, lotteryqishu.length()))) {
                                    isWaitopen = false;
                                    marksix_head3_tv4.setText(lotteryqishu + " "+Utils.getString(R.string.期开奖号码));
                                    Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult1");
                                    //  updateRightLotteryNo();
                                    List<String> list = new ArrayList<String>();
                                    list = Arrays.asList(marksixLottery.getMarksixLotterylist().get(0).getLotteryNo().split(","));
                                    List<String> bkg_list = new ArrayList<>();
                                    List<String> sxList = new ArrayList<>();
                                    sxList = Arrays.asList(marksixLottery.getMarksixLotterylist().get(0).getAnimal().split(","));

                                    bkg_list = Arrays.asList(marksixLottery.getMarksixLotterylist().get(0).getColor().split(","));
                                    kjList.clear();
                                    for (String bean : list) {
                                        kjList.add(Integer.parseInt(bean));
                                    }
                                    List<Integer> bkgcolor_list = new ArrayList<>();
                                    for (String bean : bkg_list) {
                                        switch (bean) {
                                            case "red":
                                                bkgcolor_list.add(R.drawable.shape_circle_red);
                                                break;
                                            case "blue":
                                                bkgcolor_list.add(R.drawable.shape_circle_blue);
                                                break;
                                            case "green":
                                                bkgcolor_list.add(R.drawable.shape_circle_green);
                                                break;
                                        }
                                    }
                                    marksix_no_tv1.setText(getNum(String.valueOf(kjList.get(0))));
                                    marksix_no_tv1.setBackground(getResources().getDrawable(bkgcolor_list.get(0)));
                                    marksix_no_tv2.setText(getNum(String.valueOf(kjList.get(1))));
                                    marksix_no_tv2.setBackground(getResources().getDrawable(bkgcolor_list.get(1)));
                                    marksix_no_tv3.setText(getNum(String.valueOf(kjList.get(2))));
                                    marksix_no_tv3.setBackground(getResources().getDrawable(bkgcolor_list.get(2)));
                                    marksix_no_tv4.setText(getNum(String.valueOf(kjList.get(3))));
                                    marksix_no_tv4.setBackground(getResources().getDrawable(bkgcolor_list.get(3)));
                                    marksix_no_tv5.setText(getNum(String.valueOf(kjList.get(4))));
                                    marksix_no_tv5.setBackground(getResources().getDrawable(bkgcolor_list.get(4)));
                                    marksix_no_tv6.setText(getNum(String.valueOf(kjList.get(5))));
                                    marksix_no_tv6.setBackground(getResources().getDrawable(bkgcolor_list.get(5)));
                                    marksix_no_tv7.setText(getNum(String.valueOf(kjList.get(6))));
                                    marksix_no_tv7.setBackground(getResources().getDrawable(bkgcolor_list.get(6)));

                                    marksix_sx_tv1.setText(sxList.get(0));
                                    marksix_sx_tv2.setText(sxList.get(1));
                                    marksix_sx_tv3.setText(sxList.get(2));
                                    marksix_sx_tv4.setText(sxList.get(3));
                                    marksix_sx_tv5.setText(sxList.get(4));
                                    marksix_sx_tv6.setText(sxList.get(5));
                                    marksix_sx_tv7.setText(sxList.get(6));
                                } else {
                                    if (Long.parseLong(qishu) - 1 == Long.parseLong(lotteryqishu)) {
                                        marksix_head3_tv4.setText(lotteryqishu + " "+Utils.getString(R.string.期开奖号码));
                                        Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult2");
                                        isWaitopen = false;
                                        //    updateRightLotteryNo();
                                        List<String> list = new ArrayList<String>();
                                        list = Arrays.asList(marksixLottery.getMarksixLotterylist().get(0).getLotteryNo().split(","));
                                        List<String> bkg_list = new ArrayList<>();
                                        bkg_list = Arrays.asList(marksixLottery.getMarksixLotterylist().get(0).getColor().split(","));
                                        List<String> sxList = new ArrayList<>();
                                        sxList = Arrays.asList(marksixLottery.getMarksixLotterylist().get(0).getAnimal().split(","));
                                        kjList.clear();
                                        for (String bean : list) {
                                            kjList.add(Integer.parseInt(bean));
                                        }
                                        List<Integer> bkgcolor_list = new ArrayList<>();

                                        for (String bean : bkg_list) {
                                            switch (bean) {
                                                case "red":
                                                    bkgcolor_list.add(R.drawable.shape_circle_red);
                                                    break;
                                                case "blue":
                                                    bkgcolor_list.add(R.drawable.shape_circle_blue);
                                                    break;
                                                case "green":
                                                    bkgcolor_list.add(R.drawable.shape_circle_green);
                                                    break;
                                            }

                                        }
                                        marksix_no_tv1.setText(getNum(String.valueOf(kjList.get(0))));
                                        marksix_no_tv1.setBackground(getResources().getDrawable(bkgcolor_list.get(0)));
                                        marksix_no_tv2.setText(getNum(String.valueOf(kjList.get(1))));
                                        marksix_no_tv2.setBackground(getResources().getDrawable(bkgcolor_list.get(1)));
                                        marksix_no_tv3.setText(getNum(String.valueOf(kjList.get(2))));
                                        marksix_no_tv3.setBackground(getResources().getDrawable(bkgcolor_list.get(2)));
                                        marksix_no_tv4.setText(getNum(String.valueOf(kjList.get(3))));
                                        marksix_no_tv4.setBackground(getResources().getDrawable(bkgcolor_list.get(3)));
                                        marksix_no_tv5.setText(getNum(String.valueOf(kjList.get(4))));
                                        marksix_no_tv5.setBackground(getResources().getDrawable(bkgcolor_list.get(4)));
                                        marksix_no_tv6.setText(getNum(String.valueOf(kjList.get(5))));
                                        marksix_no_tv6.setBackground(getResources().getDrawable(bkgcolor_list.get(5)));
                                        marksix_no_tv7.setText(getNum(String.valueOf(kjList.get(6))));
                                        marksix_no_tv7.setBackground(getResources().getDrawable(bkgcolor_list.get(6)));

                                        marksix_sx_tv1.setText(sxList.get(0));
                                        marksix_sx_tv2.setText(sxList.get(1));
                                        marksix_sx_tv3.setText(sxList.get(2));
                                        marksix_sx_tv4.setText(sxList.get(3));
                                        marksix_sx_tv5.setText(sxList.get(4));
                                        marksix_sx_tv6.setText(sxList.get(5));
                                        marksix_sx_tv7.setText(sxList.get(6));

                                    } else {
                                        marksix_head3_tv4.setText((Long.parseLong(lotteryqishu) + 1) + " "+Utils.getString(R.string.期开奖号码));  //筛子转动
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
            }
        });


    }

    private void shouwView(int x) {
        INDEX = x;
        int row = 0;
        k3_tab_num.setText("0");
        k3_tab_money.setText("");
        k3_tab_reset.setText(Utils.getString(R.string.机选));

        if (INDEX == 0 || INDEX == 4) {
            smallDataBeanList = new ArrayList<>();
            isClickList.clear();
            dataBeanList.clear();
            smallDataBeanList.clear();
            datagGroupList.clear();
            marksix_tab.setVisibility(View.GONE);
            if (INDEX == 0) {
                row = 2 * 4 * 6;
                datagGroupList.add(getString(R.string.特码B));
                datagGroupList.add("");
            } else if (INDEX == 4) {
                row = 6 * 4;
                datagGroupList.add(getString(R.string.正码));
                datagGroupList.add(getString(R.string.两面));
            }

            for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id() && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                    MarksixBean marksixBean = new MarksixBean();
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                    marksixBean.setDgroupname(bean.getGroupname());
                    if (INDEX == 0) {
                        if (StringMyUtil.isEmptyString(bean.getClassify())) {
                            marksixBean.setXgroupname("");
                        } else {
                            marksixBean.setXgroupname(bean.getClassify());
                        }
                    } else if (INDEX == 4) {
                        if (StrUtil.isNumer(bean.getName())) {
                            marksixBean.setXgroupname(getString(R.string.正码));
                        } else {
                            marksixBean.setXgroupname(getString(R.string.两面));
                        }
                    }

                    marksixBean.setCode(bean.getCode());
                    marksixBean.setCreatedDate(bean.getCreatedDate());
                    marksixBean.setCreatedUser(bean.getCreatedUser());
                    marksixBean.setGroup_id(bean.getGroup_id());
                    marksixBean.setGroupname(bean.getGroupname());
                    marksixBean.setId(bean.getId());
                    marksixBean.setIsDelete(bean.getIsDelete());
                    marksixBean.setModel_id(bean.getModel_id());
                    marksixBean.setName(bean.getName());
                    marksixBean.setOdds(bean.getOdds());
                    marksixBean.setOrderNo(bean.getOrderNo());
                    marksixBean.setType_id(bean.getType_id());
                    marksixBean.setAnimal(bean.getAnimal());
                    marksixBean.setClassify(bean.getClassify());
                    marksixBean.setColor(bean.getColor());
                    marksixBean.setRemark(bean.getRemark());
                    marksixBean.setOdds2(bean.getOdds2());
                    dataBeanList.add(marksixBean);
                }
            }
            smallDataBeanList.addAll(dataBeanList);

        } else if (INDEX == 1 || INDEX == 2) {
            smallDataBeanList = new ArrayList<>();
            // isClickMap.clear();
            isClickList.clear();
            dataBeanList.clear();
            smallDataBeanList.clear();
            datagGroupList.clear();
            marksix_tab.setVisibility(View.GONE);
            row = 4;
            datagGroupList.add(grouplist.get(INDEX).getGroupname());
            for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id() && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                    MarksixBean marksixBean = new MarksixBean();
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                    marksixBean.setDgroupname(bean.getGroupname());
                    marksixBean.setXgroupname(bean.getGroupname());
                    marksixBean.setCode(bean.getCode());
                    marksixBean.setCreatedDate(bean.getCreatedDate());
                    marksixBean.setCreatedUser(bean.getCreatedUser());
                    marksixBean.setGroup_id(bean.getGroup_id());
                    marksixBean.setGroupname(bean.getGroupname());
                    marksixBean.setId(bean.getId());
                    marksixBean.setIsDelete(bean.getIsDelete());
                    marksixBean.setModel_id(bean.getModel_id());
                    marksixBean.setName(bean.getName());
                    marksixBean.setOdds(bean.getOdds());
                    marksixBean.setOrderNo(bean.getOrderNo());
                    marksixBean.setType_id(bean.getType_id());
                    marksixBean.setAnimal(bean.getAnimal());
                    marksixBean.setClassify(bean.getClassify());
                    marksixBean.setColor(bean.getColor());
                    marksixBean.setRemark(bean.getRemark());
                    marksixBean.setOdds2(bean.getOdds2());
                    dataBeanList.add(marksixBean);
                }
            }
            smallDataBeanList.addAll(dataBeanList);

        } else if (INDEX == 3 || INDEX == 6) {
            smallDataBeanList = new ArrayList<>();
            isClickList.clear();
            dataBeanList.clear();
            smallDataBeanList.clear();
            datagGroupList.clear();
            marksix_tab.setVisibility(View.GONE);
            if (INDEX == 3) {
                row = 3;
            } else if (INDEX == 6) {
                row = 2 * 3 * 4;
            }
            for (int i = 0; i < classifylistall.size(); i++) {   //分小组
                if (grouplist.get(INDEX).getGroup_id() == Integer.parseInt(classifylistall.get(i).getGroup_id())) {
                    for (int k = 0; k < classifylistall.get(i).getClassifylist().size(); k++) {
                        datagGroupList.add(classifylistall.get(i).getClassifylist().get(k).getClassify());
                    }
                }
            }
            if (INDEX == 3 && datagGroupList.size() == 0) {
                datagGroupList.add(getString(R.string.色波));
            }
            for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id() && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                    MarksixBean marksixBean = new MarksixBean();
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                    marksixBean.setDgroupname(bean.getGroupname());
                    if (!StringMyUtil.isEmptyString(bean.getClassify())) {
                        marksixBean.setXgroupname(bean.getClassify());   //设置小组名
                    } else {
                        if (INDEX == 3) {
                            marksixBean.setXgroupname(bean.getGroupname());   //设置小组名
                        }
                    }
                    marksixBean.setCode(bean.getCode());
                    marksixBean.setCreatedDate(bean.getCreatedDate());
                    marksixBean.setCreatedUser(bean.getCreatedUser());
                    marksixBean.setGroup_id(bean.getGroup_id());
                    marksixBean.setGroupname(bean.getGroupname());
                    marksixBean.setId(bean.getId());
                    marksixBean.setIsDelete(bean.getIsDelete());
                    marksixBean.setModel_id(bean.getModel_id());
                    marksixBean.setName(bean.getName());
                    marksixBean.setOdds(bean.getOdds());
                    marksixBean.setOrderNo(bean.getOrderNo());
                    marksixBean.setType_id(bean.getType_id());
                    marksixBean.setAnimal(bean.getAnimal());
                    marksixBean.setClassify(bean.getClassify());
                    marksixBean.setColor(bean.getColor());
                    marksixBean.setRemark(bean.getRemark());
                    marksixBean.setOdds2(bean.getOdds2());
                    dataBeanList.add(marksixBean);
                }
            }
            smallDataBeanList.addAll(dataBeanList);
        } else if (INDEX == 5 || INDEX == 7 || INDEX == 9) {
            smallDataBeanList = new ArrayList<>();
            isClickList.clear();
            dataBeanList.clear();
            smallDataBeanList.clear();
            datagGroupList.clear();
            TabNameList.clear();
            marksix_tab.removeAllTabs();
            marksix_tab.setVisibility(View.VISIBLE);
            if (INDEX == 5) {
                row = 1 * 3 * 6;
                marksix_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
            } else if (INDEX == 7) {
                row = 2 * 4;
                marksix_tab.setTabMode(TabLayout.MODE_FIXED);
            } else if (INDEX == 9) {
                row = 2 * 4;
                marksix_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
            }

            TabNameList.clear();
            for (int i = 0; i < classifylistall.size(); i++) {   //分小组
                if (grouplist.get(INDEX).getGroup_id() == Integer.parseInt(classifylistall.get(i).getGroup_id())) {
                    for (int k = 0; k < classifylistall.get(i).getClassifylist().size(); k++) {
                        datagGroupList.add(classifylistall.get(i).getClassifylist().get(k).getClassify());
                        TabNameList.add(classifylistall.get(i).getClassifylist().get(k).getClassify());//获取tabname
                    }
                }
            }
            if (INDEX == 9) {
                for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                    if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id()
                            && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())
                            && !StringMyUtil.isEmptyString(bean.getClassify())) {

                        MarksixBean marksixBean = new MarksixBean();
                        StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                        marksixBean.setDgroupname(bean.getGroupname());
                        marksixBean.setXgroupname(bean.getClassify());   //设置小组名
                        marksixBean.setCode(bean.getCode());
                        marksixBean.setCreatedDate(bean.getCreatedDate());
                        marksixBean.setCreatedUser(bean.getCreatedUser());
                        marksixBean.setGroup_id(bean.getGroup_id());
                        marksixBean.setGroupname(bean.getGroupname());
                        marksixBean.setId(bean.getId());
                        marksixBean.setIsDelete(bean.getIsDelete());
                        marksixBean.setModel_id(bean.getModel_id());
                        marksixBean.setName(bean.getName());
                        marksixBean.setOdds(bean.getOdds());
                        marksixBean.setOrderNo(bean.getOrderNo());
                        marksixBean.setType_id(bean.getType_id());
                        marksixBean.setAnimal(bean.getAnimal());
                        marksixBean.setClassify(bean.getClassify());
                        marksixBean.setColor(bean.getColor());
                        marksixBean.setRemark(bean.getRemark());
                        marksixBean.setOdds2(bean.getOdds2());
                        dataBeanList.add(marksixBean);

                    }
                }
            } else {
                for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                    if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id()
                            && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                        MarksixBean marksixBean = new MarksixBean();
                        StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                        marksixBean.setDgroupname(bean.getGroupname());
                        marksixBean.setXgroupname(bean.getClassify());   //设置小组名
                        marksixBean.setCode(bean.getCode());
                        marksixBean.setCreatedDate(bean.getCreatedDate());
                        marksixBean.setCreatedUser(bean.getCreatedUser());
                        marksixBean.setGroup_id(bean.getGroup_id());
                        marksixBean.setGroupname(bean.getGroupname());
                        marksixBean.setId(bean.getId());
                        marksixBean.setIsDelete(bean.getIsDelete());
                        marksixBean.setModel_id(bean.getModel_id());
                        marksixBean.setName(bean.getName());
                        marksixBean.setOdds(bean.getOdds());
                        marksixBean.setOrderNo(bean.getOrderNo());
                        marksixBean.setType_id(bean.getType_id());
                        marksixBean.setAnimal(bean.getAnimal());
                        marksixBean.setClassify(bean.getClassify());
                        marksixBean.setColor(bean.getColor());
                        marksixBean.setRemark(bean.getRemark());
                        marksixBean.setOdds2(bean.getOdds2());
                        dataBeanList.add(marksixBean);
                    }
                }
            }

            marksix_tab.removeAllTabs();
            for (String str : TabNameList) {
                marksix_tab.addTab(marksix_tab.newTab().setText(str));  //tab设置名称
            }
            //TODO 默认选择正一特的数据
            for (MarksixBean bean : dataBeanList) {
                if (bean.getXgroupname().equals(TabNameList.get(0))) {
                    if (INDEX == 5 && smallDataBeanList.size() < 58) {

                        smallDataBeanList.add(bean);
                    } else if ((INDEX == 7 || INDEX == 9) && smallDataBeanList.size() < 12) {
                        smallDataBeanList.add(bean);
                    }
                }
            }
            //TODO 如果不全为数字  设置组名为 两面
            if (INDEX == 5) {
                for (MarksixBean bean : smallDataBeanList) {
                    if (!StrUtil.isNumer(bean.getName())) {
                        bean.setXgroupname(getString(R.string.两面));
                    }
                }
            }

            //分两组   正**码   两面
            datagGroupList.clear();
            datagGroupList.add(TabNameList.get(0));
            if (INDEX == 5) {
                datagGroupList.add(getString(R.string.两面));
            }

            Utils.logE("smallDataBeanList", "" + smallDataBeanList);
        } else if (INDEX == 8) {
            smallDataBeanList = new ArrayList<>();
            //   isClickMap.clear();
            isClickList.clear();
            dataBeanList.clear();
            smallDataBeanList.clear();
            datagGroupList.clear();
            marksix_tab.setVisibility(View.GONE);
            row = 6;
            datagGroupList.add(grouplist.get(INDEX).getGroupname());

            List<MarksixGroup.MarksixRulelistBean> teBeanList = new ArrayList<>();  //特码B的数据
            for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                if (getString(R.string.特码B).equals(bean.getClassify())) {
                    teBeanList.add(bean);
                }
            }

            for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id() && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                    MarksixBean marksixBean = new MarksixBean();
                    marksixBean.setDgroupname(bean.getGroupname());
                    marksixBean.setXgroupname(bean.getGroupname());
                    marksixBean.setCode(bean.getCode());
                    marksixBean.setCreatedDate(bean.getCreatedDate());
                    marksixBean.setCreatedUser(bean.getCreatedUser());
                    marksixBean.setGroup_id(bean.getGroup_id());
                    marksixBean.setGroupname(bean.getGroupname());
                    marksixBean.setId(bean.getId());
                    marksixBean.setIsDelete(bean.getIsDelete());
                    marksixBean.setModel_id(bean.getModel_id());
                    marksixBean.setName(bean.getName());
                    marksixBean.setOdds(bean.getOdds());
                    marksixBean.setOrderNo(bean.getOrderNo());
                    marksixBean.setType_id(bean.getType_id());
                    marksixBean.setAnimal(bean.getAnimal());
                    marksixBean.setClassify(bean.getClassify());
                    marksixBean.setColor(bean.getColor());
                    marksixBean.setRemark(bean.getRemark());
                    marksixBean.setOdds2(bean.getOdds2());
                    dataBeanList.add(marksixBean);
                }
            }
            Utils.logE("dataBeanList", "" + dataBeanList);
            // smallDataBeanList.addAll(dataBeanList);
            MarksixBean baseBean = dataBeanList.get(0);  //从获取的这一个自选不中来生成需要的数据
            ZXBZ_ID = baseBean.getId();
            dataBeanList.clear();
            //TODO 清空dataBeanList  重新赋值 1-49
            for (int i = 0; i < 49; i++) {
                MarksixBean marksixBean = new MarksixBean();
                StrUtil.isclickAdd(isClickList, String.valueOf(baseBean.getId() * 10 + i), false);
                marksixBean.setDgroupname(getString(R.string.自选不中));
                marksixBean.setXgroupname(getString(R.string.自选不中));
                marksixBean.setCode(baseBean.getCode());
                marksixBean.setCreatedDate(baseBean.getCreatedDate());
                marksixBean.setCreatedUser(baseBean.getCreatedUser());
                marksixBean.setGroup_id(baseBean.getGroup_id());
                marksixBean.setGroupname(baseBean.getGroupname());
                marksixBean.setId(baseBean.getId() * 10 + i);  //重新分配1-49个按钮的id
                marksixBean.setIsDelete(baseBean.getIsDelete());
                marksixBean.setModel_id(baseBean.getModel_id());
                if (i < 9) {
                    marksixBean.setName("0" + String.valueOf(i + 1));
                } else {
                    marksixBean.setName(String.valueOf(i + 1));
                }
                marksixBean.setOdds(baseBean.getOdds());
                marksixBean.setOrderNo(baseBean.getOrderNo());
                marksixBean.setType_id(baseBean.getType_id());
                marksixBean.setAnimal(baseBean.getAnimal());
                marksixBean.setClassify(baseBean.getClassify());
                marksixBean.setColor(teBeanList.get(i).getColor());  //从特码B的颜色复制过来
                marksixBean.setRemark(baseBean.getRemark());
                marksixBean.setOdds2(baseBean.getOdds2());
                dataBeanList.add(marksixBean);
            }

            smallDataBeanList.addAll(dataBeanList);
        } else if (INDEX == 10) {
            smallDataBeanList = new ArrayList<>();
            isClickList.clear();
            dataBeanList.clear();
            smallDataBeanList.clear();
            TabNameList.clear();
            marksix_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
            datagGroupList.clear();
            marksix_tab.setVisibility(View.VISIBLE);
            row = 6;
            List<MarksixGroup.MarksixRulelistBean> teBeanList = new ArrayList<>();  //特码B的数据 用于颜色之类区分
            for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                if (getString(R.string.特码B).equals(bean.getClassify())) {
                    teBeanList.add(bean);
                }
            }

            for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id()
                        && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                    MarksixBean marksixBean = new MarksixBean();
                    marksixBean.setDgroupname(bean.getGroupname());
                    marksixBean.setXgroupname(bean.getName());
                    marksixBean.setCode(bean.getCode());
                    marksixBean.setCreatedDate(bean.getCreatedDate());
                    marksixBean.setCreatedUser(bean.getCreatedUser());
                    marksixBean.setGroup_id(bean.getGroup_id());
                    marksixBean.setGroupname(bean.getGroupname());
                    marksixBean.setId(bean.getId());
                    marksixBean.setIsDelete(bean.getIsDelete());
                    marksixBean.setModel_id(bean.getModel_id());
                    marksixBean.setName(bean.getName());
                    marksixBean.setOdds(bean.getOdds());
                    marksixBean.setOrderNo(bean.getOrderNo());
                    marksixBean.setType_id(bean.getType_id());
                    marksixBean.setAnimal(bean.getAnimal());
                    marksixBean.setClassify(bean.getClassify());
                    marksixBean.setColor(bean.getColor());
                    marksixBean.setRemark(bean.getRemark());
                    marksixBean.setOdds2(bean.getOdds2());
                    dataBeanList.add(marksixBean);
                }
                Utils.logE("dataBeanList", "" + dataBeanList);
            }
            MarksixBean baseBean = new MarksixBean();
            if (dataBeanList.size() < 7) {
                //从获取的这一个第一个连码来生成需要的数据
                baseBean = dataBeanList.get(0);
                LM_ID = baseBean.getId();
            }

            datagGroupList.add(dataBeanList.get(0).getXgroupname());
            for (MarksixBean bean : dataBeanList) {
                TabNameList.add(bean.getXgroupname());
            }
            marksix_tab.removeAllTabs();
            for (String str : TabNameList) {
                marksix_tab.addTab(marksix_tab.newTab().setText(str));  //tab设置名称
            }

            Utils.logE("dataBeanList", "" + dataBeanList);
            List<MarksixBean> marksixBeansList = new ArrayList<>();
            smallDataBeanList.clear();  // 下面2句很重要  不知为什么tablayout 会加载2次数据  所以在此要对数据进行清空
            //   isClickMap.clear();
            isClickList.clear();
            for (int i = 0; i < 49; i++) {
                MarksixBean marksixBean = new MarksixBean();
                StrUtil.isclickAdd(isClickList, String.valueOf(baseBean.getId() * 10 + i), false);
                marksixBean.setDgroupname(grouplist.get(INDEX).getGroupname());
                marksixBean.setXgroupname(datagGroupList.get(0));
                marksixBean.setCode(baseBean.getCode());
                marksixBean.setCreatedDate(baseBean.getCreatedDate());
                marksixBean.setCreatedUser(baseBean.getCreatedUser());
                marksixBean.setGroup_id(baseBean.getGroup_id());
                marksixBean.setGroupname(baseBean.getGroupname());
                marksixBean.setId(baseBean.getId() * 10 + i);  //重新分配1-49个按钮的id
                marksixBean.setIsDelete(baseBean.getIsDelete());
                marksixBean.setModel_id(baseBean.getModel_id());
                if (i < 9) {
                    marksixBean.setName("0" + String.valueOf(i + 1));
                } else {
                    marksixBean.setName(String.valueOf(i + 1));
                }
                marksixBean.setOdds(baseBean.getOdds());
                marksixBean.setOrderNo(baseBean.getOrderNo());
                marksixBean.setType_id(baseBean.getType_id());
                marksixBean.setAnimal(baseBean.getAnimal());
                marksixBean.setClassify(baseBean.getClassify());
                marksixBean.setColor(teBeanList.get(i).getColor());  //从特码B的颜色复制过来
                marksixBean.setRemark(baseBean.getRemark());
                marksixBean.setOdds2(baseBean.getOdds2());
                marksixBeansList.add(marksixBean);
            }
            smallDataBeanList.addAll(marksixBeansList);

        }

        isClickList.clear();
        for (int i =0;i<smallDataBeanList.size();i++){
            StrUtil.isclickAdd(isClickList, String.valueOf(smallDataBeanList.get(i).getId()), false);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        marksix_recyclerview.setFocusableInTouchMode(false);
        marksix_recyclerview.setLayoutManager(layoutManager);
        marksixParentAdapter = new MarksixParentAdapter(this, datagGroupList, smallDataBeanList, row, INDEX, isClickList);
        marksix_recyclerview.setAdapter(marksixParentAdapter);
    }


    public void OnClickListener(/*HashMap<String, Boolean> isClickMap1*/List<IsClickModel> isClickList) {
        int num = StrUtil.isclickCal(isClickList);
        if (num != 0) {
            k3_tab_reset.setText(Utils.getString(R.string.重置));
        } else {
            k3_tab_reset.setText(Utils.getString(R.string.机选));
        }

        if (INDEX == 8) {
            if (num < 5) {
                k3_tab_num.setText("0");
            } else {
                k3_tab_num.setText("1");
            }
        } else if (INDEX == 9) {
            long totalnum = 0;
            if (getString(R.string.二连肖).equals(datagGroupList.get(0))) {
                if (num < 2) {
                    totalnum = 0;
                } else {
                    totalnum = StrUtil.comb(num, 2);
                }
            } else if (getString(R.string.三连肖).equals(datagGroupList.get(0))) {
                if (num < 3) {
                    totalnum = 0;
                } else {
                    totalnum = StrUtil.comb(num, 3);
                }
            } else if (getString(R.string.四连肖).equals(datagGroupList.get(0))) {
                if (num < 4) {
                    totalnum = 0;
                } else {
                    totalnum = StrUtil.comb(num, 4);
                }
            } else if (getString(R.string.五连肖).equals(datagGroupList.get(0))) {
                if (num < 5) {
                    totalnum = 0;
                } else {
                    totalnum = StrUtil.comb(num, 5);
                }
            } else if (getString(R.string.二连尾).equals(datagGroupList.get(0))) {
                if (num < 2) {
                    totalnum = 0;
                } else {
                    totalnum = StrUtil.comb(num, 2);
                }
            } else if (getString(R.string.三连尾).equals(datagGroupList.get(0))) {
                if (num < 3) {
                    totalnum = 0;
                } else {
                    totalnum = StrUtil.comb(num, 3);
                }
            } else if (getString(R.string.四连尾).equals(datagGroupList.get(0))) {
                if (num < 2) {
                    totalnum = 4;
                } else {
                    totalnum = StrUtil.comb(num, 4);
                }
            } else if (getString(R.string.五连尾).equals(datagGroupList.get(0))) {
                if (num < 5) {
                    totalnum = 0;
                } else {
                    totalnum = StrUtil.comb(num, 5);
                }
            }
            k3_tab_num.setText("" + totalnum);
        } else if (INDEX == 10) {
            if (getString(R.string.三中二).equals(datagGroupList.get(0))) {
                if (num < 3) {
                    k3_tab_num.setText("0");
                } else {
                    k3_tab_num.setText("1");
                }
            } else if (getString(R.string.三全中).equals(datagGroupList.get(0))) {
                if (num < 3) {
                    k3_tab_num.setText("0");
                } else {
                    k3_tab_num.setText("1");
                }
            } else if (getString(R.string.二全中).equals(datagGroupList.get(0))) {
                if (num < 2) {
                    k3_tab_num.setText("0");
                } else {
                    k3_tab_num.setText("1");
                }
            } else if (getString(R.string.二中特).equals(datagGroupList.get(0))) {
                if (num < 2) {
                    k3_tab_num.setText("0");
                } else {
                    k3_tab_num.setText("1");
                }
            } else if (getString(R.string.特串).equals(datagGroupList.get(0))) {
                if (num < 2) {
                    k3_tab_num.setText("0");
                } else {
                    k3_tab_num.setText("1");
                }
            } else if (getString(R.string.四全中).equals(datagGroupList.get(0))) {
                if (num < 4) {
                    k3_tab_num.setText("0");
                } else {
                    k3_tab_num.setText("1");
                }
            }
        } else {
            k3_tab_num.setText("" + num);
        }


    }

    public void showhead1popup(int x) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.layout_head1popuprecycler, null, false);//引入弹窗布局
        head1recyclerview = vPopupWindow.findViewById(R.id.head1_recyclerview);
        head1popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT, true);
        addBackground();
        View view = findViewById(R.id.rl_head1);
        head1popupWindow.showAsDropDown(view);
        loadgrideDate(x);
    }
    private void addBackground() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;//调节透明度
        getWindow().setAttributes(lp);
        head1popupWindow.setOutsideTouchable(true);
        head1popupWindow.setOnDismissListener(() -> {
                    //消失旋转
                    caizhong_head1_iv.startAnimation(rotateAnimation);
                    caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_up);
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
        );
    }

    private void loadgrideDate(int x) {
        final List<String> titleList = new ArrayList<String>();
        titleList.add(getString(R.string.特码));
        titleList.add(getString(R.string.特肖));
        titleList.add(getString(R.string.两面));
        titleList.add(getString(R.string.色波));
        titleList.add(getString(R.string.正码));
        titleList.add(getString(R.string.正特码));
        titleList.add(getString(R.string.正码1到6));
        titleList.add(getString(R.string.平特));
        titleList.add(getString(R.string.自选不中));
        titleList.add(getString(R.string.连肖连尾));
        titleList.add(getString(R.string.连码));

        RecyclerViewGridAdapter recyclerViewGridAdapter = new RecyclerViewGridAdapter(this, titleList, x);
        head1recyclerview.setAdapter(recyclerViewGridAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        head1recyclerview.setLayoutManager(gridLayoutManager);

        recyclerViewGridAdapter.setOnItemClickListener((View view,int position) ->{
            caizhong_head1_title.setText(titleList.get(position));
            if (head1popupWindow.isShowing()) {
                head1popupWindow.dismiss();
            }
            try {
                if (marksixGroup!=null){
                    shouwView(position);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }


    private void clickView() {
        fenpan_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Utils.getString(R.string.该彩种已封盘));
            }
        });
        caizhong_head1_back.setOnClickListener(v -> onBackPressed());
        //TODO 点击重新加载 2019-7-17
        tv_reload.setOnClickListener(v -> {
            initData();
            initCountDownEndPop();//倒计时结束的pop
            ReqCountdown();  //倒计时
            onResume();

        });

        ll_caizhong_head1.setOnClickListener(v -> {
            caizhong_head1_iv.startAnimation(rotateAnimation);
            caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_down);
            List<String> list = new ArrayList<>();
            list.add(getString(R.string.特码));
            list.add(getString(R.string.特肖));
            list.add(getString(R.string.两面));
            list.add(getString(R.string.色波));
            list.add(getString(R.string.正码));
            list.add(getString(R.string.正特码));
            list.add(getString(R.string.正码1到6));
            list.add(getString(R.string.平特));
            list.add(getString(R.string.自选不中));
            list.add(getString(R.string.连肖连尾));
            list.add(getString(R.string.连码));
            String str = caizhong_head1_title.getText().toString();
            int x = 0;
            for (int i = 0; i < list.size(); i++) {
                if (str.equals(list.get(i))) {
                    x = i;
                    break;
                }
            }
            showhead1popup(x);
        });
        //选项卡切换
        marksix_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (INDEX == 5 || INDEX == 7 || INDEX == 9 || INDEX == 10) {
                    //分两组   正**码   两面
                    List<MarksixBean> smallDataBeanList1 = new ArrayList<>();
                    isClickList.clear();
                    datagGroupList.clear();
                    smallDataBeanList1.clear();
                    dataBeanList.clear();

                    datagGroupList.add(TabNameList.get(marksix_tab.getSelectedTabPosition()));
                    if (INDEX == 5) {
                        datagGroupList.add(getString(R.string.两面));
                    }
                    if (INDEX == 10) {

                        List<MarksixGroup.MarksixRulelistBean> teBeanList = new ArrayList<>();  //特码B的数据 用于颜色之类区分
                        for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                            if (getString(R.string.特码B).equals(bean.getClassify())) {
                                teBeanList.add(bean);
                            }
                        }
                        Utils.logE("dataBeanList", "" + dataBeanList);

                        for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                            if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id()
                                    && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                                MarksixBean marksixBean = new MarksixBean();

                                marksixBean.setDgroupname(bean.getGroupname());
                                marksixBean.setXgroupname(bean.getName());
                                marksixBean.setCode(bean.getCode());
                                marksixBean.setCreatedDate(bean.getCreatedDate());
                                marksixBean.setCreatedUser(bean.getCreatedUser());
                                marksixBean.setGroup_id(bean.getGroup_id());
                                marksixBean.setGroupname(bean.getGroupname());
                                marksixBean.setId(bean.getId());
                                marksixBean.setIsDelete(bean.getIsDelete());
                                marksixBean.setModel_id(bean.getModel_id());
                                marksixBean.setName(bean.getName());
                                marksixBean.setOdds(bean.getOdds());
                                marksixBean.setOrderNo(bean.getOrderNo());
                                marksixBean.setType_id(bean.getType_id());
                                marksixBean.setAnimal(bean.getAnimal());
                                marksixBean.setClassify(bean.getClassify());
                                marksixBean.setColor(bean.getColor());
                                marksixBean.setRemark(bean.getRemark());
                                marksixBean.setOdds2(bean.getOdds2());

                                dataBeanList.add(marksixBean);
                            }
                        }
                        MarksixBean baseBean = new MarksixBean();
                        if (dataBeanList.size() < 7) {
                            baseBean = dataBeanList.get(marksix_tab.getSelectedTabPosition());  //从获取的这一个第一个连码来生成需要的数据
                            LM_ID = baseBean.getId();
                        }


                        //TODO 清空dataBeanList  重新赋值 1-49
                        dataBeanList.clear();
                        //   isClickMap.clear();
                        isClickList.clear();
                        for (int i = 0; i < 49; i++) {
                            MarksixBean marksixBean = new MarksixBean();
                            //    isClickMap.put(String.valueOf(baseBean.getId() * 10 + i), false);
                            StrUtil.isclickAdd(isClickList, String.valueOf(baseBean.getId() * 10 + i), false);

                            marksixBean.setDgroupname(grouplist.get(INDEX).getGroupname());
                            marksixBean.setXgroupname(TabNameList.get(marksix_tab.getSelectedTabPosition()));
                            marksixBean.setCode(baseBean.getCode());
                            marksixBean.setCreatedDate(baseBean.getCreatedDate());
                            marksixBean.setCreatedUser(baseBean.getCreatedUser());
                            marksixBean.setGroup_id(baseBean.getGroup_id());
                            marksixBean.setGroupname(baseBean.getGroupname());
                            marksixBean.setId(baseBean.getId() * 10 + i);  //重新分配1-49个按钮的id
                            marksixBean.setIsDelete(baseBean.getIsDelete());
                            marksixBean.setModel_id(baseBean.getModel_id());
                            if (i < 9) {
                                marksixBean.setName("0" + String.valueOf(i + 1));
                            } else {
                                marksixBean.setName(String.valueOf(i + 1));
                            }
                            marksixBean.setOdds(baseBean.getOdds());
                            marksixBean.setOrderNo(baseBean.getOrderNo());
                            marksixBean.setType_id(baseBean.getType_id());
                            marksixBean.setAnimal(baseBean.getAnimal());
                            marksixBean.setClassify(baseBean.getClassify());
                            marksixBean.setColor(teBeanList.get(i).getColor());  //从特码B的颜色复制过来
                            marksixBean.setRemark(baseBean.getRemark());
                            marksixBean.setOdds2(baseBean.getOdds2());

                            dataBeanList.add(marksixBean);
                        }
                        smallDataBeanList.clear();
                        smallDataBeanList.addAll(dataBeanList);
                    } else if (INDEX == 9) {
                        for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                            if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id()
                                    && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())
                                    && !StringMyUtil.isEmptyString(bean.getClassify())) {
                                MarksixBean marksixBean = new MarksixBean();
                                //  isClickMap.put(String.valueOf(bean.getId()), false);
                                StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                marksixBean.setDgroupname(bean.getGroupname());
                                marksixBean.setXgroupname(bean.getClassify());   //设置小组名
                                marksixBean.setCode(bean.getCode());
                                marksixBean.setCreatedDate(bean.getCreatedDate());
                                marksixBean.setCreatedUser(bean.getCreatedUser());
                                marksixBean.setGroup_id(bean.getGroup_id());
                                marksixBean.setGroupname(bean.getGroupname());
                                marksixBean.setId(bean.getId());
                                marksixBean.setIsDelete(bean.getIsDelete());
                                marksixBean.setModel_id(bean.getModel_id());
                                marksixBean.setName(bean.getName());
                                marksixBean.setOdds(bean.getOdds());
                                marksixBean.setOrderNo(bean.getOrderNo());
                                marksixBean.setType_id(bean.getType_id());
                                marksixBean.setAnimal(bean.getAnimal());
                                marksixBean.setClassify(bean.getClassify());
                                marksixBean.setColor(bean.getColor());
                                marksixBean.setRemark(bean.getRemark());
                                marksixBean.setOdds2(bean.getOdds2());

                                dataBeanList.add(marksixBean);
                            }
                        }
                    } else if (INDEX == 5 || INDEX == 7) {
                        for (MarksixGroup.MarksixRulelistBean bean : marksixRulelist) {
                            if (grouplist.get(INDEX).getGroup_id() == bean.getGroup_id()
                                    && grouplist.get(INDEX).getGroupname().equals(bean.getGroupname())) {
                                MarksixBean marksixBean = new MarksixBean();
                                // isClickMap.put(String.valueOf(bean.getId()), false);
                                StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                marksixBean.setDgroupname(bean.getGroupname());
                                marksixBean.setXgroupname(bean.getClassify());   //设置小组名
                                marksixBean.setCode(bean.getCode());
                                marksixBean.setCreatedDate(bean.getCreatedDate());
                                marksixBean.setCreatedUser(bean.getCreatedUser());
                                marksixBean.setGroup_id(bean.getGroup_id());
                                marksixBean.setGroupname(bean.getGroupname());
                                marksixBean.setId(bean.getId());
                                marksixBean.setIsDelete(bean.getIsDelete());
                                marksixBean.setModel_id(bean.getModel_id());
                                marksixBean.setName(bean.getName());
                                marksixBean.setOdds(bean.getOdds());
                                marksixBean.setOrderNo(bean.getOrderNo());
                                marksixBean.setType_id(bean.getType_id());
                                marksixBean.setAnimal(bean.getAnimal());
                                marksixBean.setClassify(bean.getClassify());
                                marksixBean.setColor(bean.getColor());
                                marksixBean.setRemark(bean.getRemark());
                                marksixBean.setOdds2(bean.getOdds2());

                                dataBeanList.add(marksixBean);
                            }
                        }
                    }


                    if (INDEX != 10) {
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(TabNameList.get(marksix_tab.getSelectedTabPosition()))) {
                                smallDataBeanList1.add(bean);
                                StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                            }
                        }
                        smallDataBeanList.clear();
                        smallDataBeanList.addAll(smallDataBeanList1);
                    }
                    //TODO 如果不全为数字  设置组名为 两面
                    if (INDEX == 5) {
                        for (MarksixBean bean : smallDataBeanList1) {
                            if (!StrUtil.isNumer(bean.getName())) {
                                bean.setXgroupname(getString(R.string.两面));
                            }
                        }
                    }


                    //分两组   正**码   两面
                    datagGroupList.clear();
                    datagGroupList.add(TabNameList.get(marksix_tab.getSelectedTabPosition()));
                    if (INDEX == 5) {
                        datagGroupList.add(getString(R.string.两面));
                    }

                    isClickList.clear();
                    for (int i =0;i<smallDataBeanList.size();i++){
                        StrUtil.isclickAdd(isClickList, String.valueOf(smallDataBeanList.get(i).getId()), false);
                    }

                    marksixParentAdapter.notifyDataSetChanged();
                    k3_tab_num.setText("" + 0);
                    k3_tab_money.setText("");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        k3_tab_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                count = StrUtil.isclickCal(isClickList);

                if (INDEX == 8) {
                    if (count < 5) {
                        showToast(Utils.getString(R.string.请选择玩法));
                        return;
                    }
                } else if (INDEX == 9) {
                    if (getString(R.string.二连肖).equals(datagGroupList.get(0))) {
                        if (count < 2) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.三连肖).equals(datagGroupList.get(0))) {
                        if (count < 3) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.四连肖).equals(datagGroupList.get(0))) {
                        if (count < 4) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.五连肖).equals(datagGroupList.get(0))) {
                        if (count < 5) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.二连尾).equals(datagGroupList.get(0))) {
                        if (count < 2) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.三连尾).equals(datagGroupList.get(0))) {
                        if (count < 3) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.四连尾).equals(datagGroupList.get(0))) {
                        if (count < 4) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.五连尾).equals(datagGroupList.get(0))) {
                        if (count < 5) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    }
                } else if (INDEX == 10) {
                    if (getString(R.string.三中二).equals(datagGroupList.get(0))) {
                        if (count < 3) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.三全中).equals(datagGroupList.get(0))) {
                        if (count < 3) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.二全中).equals(datagGroupList.get(0))) {
                        if (count < 2) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.二中特).equals(datagGroupList.get(0))) {
                        if (count < 2) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.特串).equals(datagGroupList.get(0))) {
                        if (count < 2) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    } else if (getString(R.string.四全中).equals(datagGroupList.get(0))) {
                        if (count < 4) {
                            showToast(Utils.getString(R.string.请选择玩法));
                            return;
                        }
                    }
                } else {
                    if (count == 0) {
                        showToast(Utils.getString(R.string.请选择玩法));
                        return;
                    }
                }
                if (StringMyUtil.isEmptyString(k3_tab_money.getText().toString()) || !MoneyUtils.isMoney(k3_tab_money.getText().toString())) {
                    showToast(Utils.getString(R.string.请输入金额));
                    return;
                }
                List<TouzhuModel> touzhuList = new ArrayList<>();
                String ma = "";
                if (INDEX == 8) {

                    for (IsClickModel isClickModel : isClickList) {

                        if (isClickModel.getIsclick()) {
                            for (MarksixBean bean : dataBeanList) {
                                if (isClickModel.getId().equals(String.valueOf(bean.getId()))) {
                                    ma = ma + bean.getName() + ",";
                                }
                            }
                        }
                    }
                    ma = ma.substring(0, ma.length() - 1);
                    TouzhuModel touzhuModel = new TouzhuModel();
                    touzhuModel.setGroupname(getString(R.string.自选不中自选不中));
                    touzhuModel.setName(ma);
                    touzhuModel.setId(String.valueOf(ZXBZ_ID));
                    touzhuModel.setMoney(k3_tab_money.getText().toString());

                    touzhuList.add(touzhuModel);

                } else if (INDEX == 9) {
                    int num = 0;
                    List<MarksixBean> choicesList = new ArrayList<>();
                    List<MarksixBean> totalList = new ArrayList<>();

                    for (IsClickModel isClickModel : isClickList) {

                        if (isClickModel.getIsclick()) {
                            for (MarksixBean bean : dataBeanList) {
                                if (isClickModel.getId().equals(String.valueOf(bean.getId()))) {
                                    choicesList.add(bean);
                                    num++;
                                }
                            }
                        }
                    }
                    String[] TotalArry = new String[num];
                    for (int i = 0; i < TotalArry.length; i++) {
                        TotalArry[i] = choicesList.get(i).getName();
                    }
                    Utils.logE("TotalArry", "" + TotalArry);
                    ma = "";
                    if (getString(R.string.二连肖).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 2);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.二连肖))) {
                                totalList.add(bean);
                            }
                        }
                    } else if (getString(R.string.三连肖).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 3);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.三连肖))) {
                                totalList.add(bean);
                            }
                        }
                    } else if (getString(R.string.四连肖).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 4);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.四连肖))) {
                                totalList.add(bean);
                            }
                        }
                    } else if (getString(R.string.五连肖).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 5);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.五连肖))) {
                                totalList.add(bean);
                            }
                        }
                    } else if (getString(R.string.二连尾).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 2);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.二连尾))) {
                                totalList.add(bean);
                            }
                        }
                    } else if (getString(R.string.三连尾).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 3);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.三连尾))) {
                                totalList.add(bean);
                            }
                        }
                    } else if (getString(R.string.四连尾).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 4);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.四连尾))) {
                                totalList.add(bean);
                            }
                        }
                    } else if (getString(R.string.五连尾).equals(datagGroupList.get(0))) {
                        ma = StrUtil.combinationSelect(TotalArry, 5);
                        for (MarksixBean bean : dataBeanList) {
                            if (bean.getXgroupname().equals(getString(R.string.五连尾))) {
                                totalList.add(bean);
                            }
                        }
                    }
                    ma = ma.substring(0, ma.length() - 1);
                    String[] Arry_N = ma.split("-");
                    for (int i = 0; i < Arry_N.length; i++) {
                        TouzhuModel touzhuModel = new TouzhuModel();
                        touzhuModel.setGroupname(getString(R.string.括号连肖连尾) + datagGroupList.get(0));
                        touzhuModel.setName(Arry_N[i]);
                        String[] bijiaoArry = Arry_N[i].split(",");

                        List<MarksixBean> choiceList = new ArrayList<>();
                        for (int k = 0; k < totalList.size(); k++) {
                            for (int m = 0; m < bijiaoArry.length; m++) {
                                if (bijiaoArry[m].equals(totalList.get(k).getName())) {
                                    choiceList.add(totalList.get(k));
                                }
                            }

                        }
                        //从所选的item中比较赔率
                        long id = 0;
                        for (int j = 0; j < choiceList.size() - 1; j++) {
                            if (datagGroupList.get(0).contains(getString(R.string.连肖))) {

                                if (choiceList.get(j).getOdds() < choiceList.get(j + 1).getOdds()) {
                                    id = choiceList.get(j).getId();
                                } else {
                                    id = choiceList.get(j).getId();
                                }
                            } else {
                                if (choiceList.get(j).getOdds() > choiceList.get(j + 1).getOdds()) {
                                    id = choiceList.get(j).getId();
                                } else {
                                    id = choiceList.get(j).getId();
                                }
                            }
                        }
                        touzhuModel.setId(String.valueOf(id));
                        touzhuModel.setMoney(k3_tab_money.getText().toString());

                        touzhuList.add(touzhuModel);
                    }

                } else if (INDEX == 10) {
                    for (IsClickModel isClickModel : isClickList) {
                        if (isClickModel.getIsclick()) {
                            for (MarksixBean bean : smallDataBeanList) {
                                if (isClickModel.getId().equals(String.valueOf(bean.getId()))) {
                                    ma = ma + bean.getName() + ",";
                                }
                            }
                        }
                    }
                    ma = ma.substring(0, ma.length() - 1);
                    TouzhuModel touzhuModel = new TouzhuModel();
                    touzhuModel.setGroupname("[" + smallDataBeanList.get(0).getDgroupname() + "]" + smallDataBeanList.get(0).getXgroupname());
                    touzhuModel.setName(ma);
                    touzhuModel.setId(String.valueOf(LM_ID));
                    touzhuModel.setMoney(k3_tab_money.getText().toString());

                    touzhuList.add(touzhuModel);
                } else {

                    for (IsClickModel isClickModel : isClickList) {
                        if (isClickModel.getIsclick()) {
                            for (MarksixBean bean : dataBeanList) {
                                if (isClickModel.getId().equals(String.valueOf(bean.getId()))) {
                                    TouzhuModel touzhuModel = new TouzhuModel();
                                    touzhuModel.setGroupname("[" + bean.getDgroupname() + "]" + bean.getXgroupname());
                                    touzhuModel.setName(bean.getName());
                                    touzhuModel.setId(String.valueOf(bean.getId()));
                                    touzhuModel.setMoney(k3_tab_money.getText().toString());
                                    touzhuList.add(touzhuModel);
                                }
                            }
                        }
                    }
                }
                if(kjCountDown ==null){
                    showToast(Utils.getString(R.string.数据读取错误请重试));
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(MarksixActivity.this, TouzhuActivity.class);


                intent.putExtra("touzhuList", (Serializable) touzhuList);
                intent.putExtra("isClickList", (Serializable) isClickList);
                intent.putExtra("game", game);

                intent.putExtra("type_id", type_id);
                intent.putExtra("money", k3_tab_money.getText().toString());
                intent.putExtra("qishu", kjCountDown.getQishu());
                intent.putExtra("index", String.valueOf(INDEX));
                intent.putExtra("ma", ma);

                startActivityForResult(intent, ReqTouzhu);
            }
        });

        k3_tab_reset.setOnClickListener(v -> {
            if(dataBeanList.size()==0){
                return;
            }
            if (k3_tab_reset.getText().toString().equals(Utils.getString(R.string.重置))) {
                k3_tab_reset.setText(Utils.getString(R.string.机选));
                //  isClickMap.clear();
                isClickList.clear();
                for (MarksixBean bean : dataBeanList) {
                    //    isClickMap.put(String.valueOf(bean.getId()), false);
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                }
                marksixParentAdapter.notifyDataSetChanged();
                k3_tab_money.setText("");
                k3_tab_num.setText("0");
            } else {
                k3_tab_reset.setText(Utils.getString(R.string.重置));
                List<String> idList = new ArrayList<>();
                if(smallDataBeanList==null){
                    smallDataBeanList = new ArrayList<>();
                }
                for (MarksixBean bean : smallDataBeanList) {
                    idList.add(String.valueOf(bean.getId()));
                }
                Random random = new Random();
                List<String> idlist1 = new ArrayList<>();
                if (INDEX == 8) {
                    int[] arry = StrUtil.randomCommon(0, idList.size(), 5);
                    for (int i = 0; i < arry.length; i++) {
                        idlist1.add(idList.get(arry[i]));
                    }
                    for (IsClickModel isClickModel : isClickList) {

                        for (String id : idlist1) {
                            if (isClickModel.getId().equals(id)) {
                                StrUtil.isclickReplace(isClickList, id, true);
                            }
                        }

                    }
                    marksixParentAdapter.notifyDataSetChanged();
                    k3_tab_num.setText("1");
                } else if (INDEX == 9) {
                    if (getString(R.string.二连肖).equals(datagGroupList.get(0))) {
                        int[] arry1 = StrUtil.randomCommon(0, idList.size(), 2);
                        for (int i = 0; i < arry1.length; i++) {
                            idlist1.add(idList.get(arry1[i]));
                        }

                        for (IsClickModel isClickModel : isClickList) {

                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.三连肖).equals(datagGroupList.get(0))) {
                        int[] arry2 = StrUtil.randomCommon(0, idList.size(), 3);
                        for (int i = 0; i < arry2.length; i++) {
                            idlist1.add(idList.get(arry2[i]));
                        }

                        for (IsClickModel isClickModel : isClickList) {
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.四连肖).equals(datagGroupList.get(0))) {
                        int[] arry3 = StrUtil.randomCommon(0, idList.size(), 4);
                        for (int i = 0; i < arry3.length; i++) {
                            idlist1.add(idList.get(arry3[i]));
                        }

                        for (IsClickModel isClickModel : isClickList) {
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.五连肖).equals(datagGroupList.get(0))) {
                        int[] arry4 = StrUtil.randomCommon(0, idList.size(), 5);
                        for (int i = 0; i < arry4.length; i++) {
                            idlist1.add(idList.get(arry4[i]));
                        }
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (/*entry.getKey()*/isClickModel.getId().equals(id)) {
                                    // isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.二连尾).equals(datagGroupList.get(0))) {
                        int[] arry5 = StrUtil.randomCommon(0, idList.size(), 2);
                        for (int i = 0; i < arry5.length; i++) {
                            idlist1.add(idList.get(arry5[i]));
                        }
                        //   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (/*entry.getKey()*/isClickModel.getId().equals(id)) {
                                    //   isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.三连尾).equals(datagGroupList.get(0))) {
                        int[] arry6 = StrUtil.randomCommon(0, idList.size(), 3);
                        for (int i = 0; i < arry6.length; i++) {
                            idlist1.add(idList.get(arry6[i]));
                        }
                        //   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (/*entry.getKey()*/isClickModel.getId().equals(id)) {
                                    //  isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.四连尾).equals(datagGroupList.get(0))) {
                        int[] arry7 = StrUtil.randomCommon(0, idList.size(), 2);
                        for (int i = 0; i < arry7.length; i++) {
                            idlist1.add(idList.get(arry7[i]));
                        }
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (/*entry.getKey()*/isClickModel.getId().equals(id)) {
                                    //   isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.五连尾).equals(datagGroupList.get(0))) {
                        int[] arry8 = StrUtil.randomCommon(0, idList.size(), 2);
                        for (int i = 0; i < arry8.length; i++) {
                            idlist1.add(idList.get(arry8[i]));
                        }
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (/*entry.getKey()*/isClickModel.getId().equals(id)) {
                                    //   isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    }
                    //  k3_tab_num.setText("" + totalnum);
                } else if (INDEX == 10) {
                    if (getString(R.string.三中二).equals(datagGroupList.get(0))) {
                        int[] arry1 = StrUtil.randomCommon(0, idList.size(), 3);
                        for (int i = 0; i < arry1.length; i++) {
                            idlist1.add(idList.get(arry1[i]));
                        }
                        //    for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    //   isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.三全中).equals(datagGroupList.get(0))) {
                        int[] arry2 = StrUtil.randomCommon(0, idList.size(), 3);
                        for (int i = 0; i < arry2.length; i++) {
                            idlist1.add(idList.get(arry2[i]));
                        }
                        // for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    // isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.二全中).equals(datagGroupList.get(0))) {
                        int[] arry3 = StrUtil.randomCommon(0, idList.size(), 2);
                        for (int i = 0; i < arry3.length; i++) {
                            idlist1.add(idList.get(arry3[i]));
                        }
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    //  isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.二中特).equals(datagGroupList.get(0))) {
                        int[] arry4 = StrUtil.randomCommon(0, idList.size(), 2);
                        for (int i = 0; i < arry4.length; i++) {
                            idlist1.add(idList.get(arry4[i]));
                        }
                        //     for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    //   isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.特串).equals(datagGroupList.get(0))) {
                        int[] arry5 = StrUtil.randomCommon(0, idList.size(), 2);
                        for (int i = 0; i < arry5.length; i++) {
                            idlist1.add(idList.get(arry5[i]));
                        }
                        //   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    //    isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    } else if (getString(R.string.四全中).equals(datagGroupList.get(0))) {
                        int[] arry6 = StrUtil.randomCommon(0, idList.size(), 4);
                        for (int i = 0; i < arry6.length; i++) {
                            idlist1.add(idList.get(arry6[i]));
                        }
                        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                        for (IsClickModel isClickModel : isClickList) {
                            // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            for (String id : idlist1) {
                                if (isClickModel.getId().equals(id)) {
                                    //   isClickMap.replace(entry.getKey(),true);
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }

                        }
                        marksixParentAdapter.notifyDataSetChanged();
                        k3_tab_num.setText("1");
                    }
                } else {
                    if(idList.size()==0){
                        return;
                    }
                    String id = idList.get(random.nextInt(idList.size()));
                    //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                    for (IsClickModel isClickModel : isClickList) {
                        // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                        if (isClickModel.getId().equals(id)) {
                            // isClickMap.replace(entry.getKey(),true);
                            StrUtil.isclickReplace(isClickList, id, true);
                        }
                    }
                    marksixParentAdapter.notifyDataSetChanged();
                    k3_tab_num.setText("1");
                }

            }
        });
        ll_caizhong_head2_left.setOnClickListener(v -> customPopupWindow.showClassfyPop(ll_caizhong_head2_left, MarksixActivity.this));
        ll2_marksix_head3.setOnClickListener(v -> customPopupWindow.initMarksixTodayResultData(MarksixActivity.this, Integer.parseInt(game), Integer.parseInt(type_id), ll2_marksix_head3));
        caizhong_head1_menu.setOnClickListener(v -> customPopupWindow.showMenuPop(caizhong_head1_menu, MarksixActivity.this));
        ll_caizhong_head2_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMoney();
                Animation animation = AnimationUtils.loadAnimation(MarksixActivity.this, R.anim.rotate_anim);
                rotate_image.startAnimation(animation);
            }
        });


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

    }

    private void requestMoney() {
        ReqMemberMoney(MarksixActivity.this, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                memberMoney = JSONObject.parseObject(content, MemberMoney.class);
                ll_caizhong_head2_center_tv.setText(MoneyUtils.parseMoey(String.valueOf(MarksixActivity.this.memberMoney.getMemberMoney().getAmount())));
            }
            @Override
            public void failed(String content) {
            }
        });
    }

    @Override
    public void onItemClick(View view, int position, LotteryClassModel lotteryClassModel) {
        int type_id = lotteryClassModel.getType_id();
        String isopenOffice = lotteryClassModel.getIsopenOffice();
        String isStart = lotteryClassModel.getIsStart();
        String typename = lotteryClassModel.getTypename();
        int game = lotteryClassModel.getGame();
        ToBetAtyUtils.toBetActivity(MarksixActivity.this, game, typename, type_id, isopenOffice, isStart);
        finish();
    }
    @Override
    public void onMenuClicked(View view) {

    }

    //对投注成功后进行数据重置 更新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            if (resultCode == 102) {
                isClickList.clear();
                for (MarksixBean bean : dataBeanList) {
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                }
                marksixParentAdapter.notifyDataSetChanged();
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
                timeCount = (endtime - tqtime) - (System.currentTimeMillis()-sjcha) ;
                Utils.logE("timeCount:", "" + timeCount);
                int mHour = (int) ((timeCount / 1000) / (60 * 60));  //  对3600 取整  就是小时
                int mMin = (int) (((timeCount / 1000) % (60 * 60)) / 60);//  对3600 取余除以60 就是多出的分
                int mSecond = (int) ((timeCount / 1000) % 60); //  对60 取余  就是多出的秒
                String str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
                marksix_head3_tv3.setText(str_time);
                if (timeCount <= 1) {
                    timeCount = 0L;
                    marksix_head3_tv3.setText(Utils.getString(R.string.已结束));
                    if (CountDownEndPop != null) {
                        lastQiShuTv.setText(qishu);
                        newQiShuTv.setText((Long.parseLong(qishu) + 1) + "");
                        if (!isFinishing() && CountDownEndPop != null) {
                            CountDownEndPop.showAtLocation(ll1_marksix_head3, Gravity.CENTER, 0, 0);
                        }
                        ProgressDialogUtil.darkenBackground(MarksixActivity.this, 0.5f);
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
                    marksix_head3_tv4.setText(waitOpenQiShu + " "+Utils.getString(R.string.期开奖号码));
                    List<String> list = new ArrayList<String>();
                    kjList.clear();
                    Random random = new Random();
                    for (int i = 0; i < 7; i++) {
                        kjList.add(random.nextInt(49));
                    }
                    marksix_no_tv1.setText(String.valueOf(kjList.get(0)));
                    marksix_no_tv1.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                    marksix_no_tv2.setText(String.valueOf(kjList.get(1)));
                    marksix_no_tv2.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                    marksix_no_tv3.setText(String.valueOf(kjList.get(2)));
                    marksix_no_tv3.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                    marksix_no_tv4.setText(String.valueOf(kjList.get(3)));
                    marksix_no_tv4.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                    marksix_no_tv5.setText(String.valueOf(kjList.get(4)));
                    marksix_no_tv5.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                    marksix_no_tv6.setText(String.valueOf(kjList.get(5)));
                    marksix_no_tv6.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                    marksix_no_tv7.setText(String.valueOf(kjList.get(6)));
                    marksix_no_tv7.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));

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
                case 2:
                    requestMoney();
                    break;
            }
        }
    };

    Runnable runnableZj = new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name", SharePreferencesUtil.getString(MarksixActivity.this, "nickname", ""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
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
/*        handler.removeCallbacks(runnableRandom);
        handler.removeCallbacks(runnableRequestOpen);
        handler.removeCallbacks(runnableTime);
        handler.removeCallbacks(runnableZj);*/
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!CountDownEndPop.isShowing()) {
            ProgressDialogUtil.darkenBackground(this, 1f);
        }
        handler.post(runnableRandom);
        handler.post(runnableRequestOpen);
        handler.post(runnableTime);
        handler.post(runnableZj);
    }

    /*@Override
    public void onNetChange(boolean netWorkState) {
        super.onNetChange(netWorkState);
        if (netWorkState) {
            marksix_tab_main.setVisibility(View.VISIBLE);
            if (runnableRequestOpen != null) {
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, 0);
            if (runnableRandom != null) {
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);
            handler.postDelayed(runnableTime, 1000);
            handler.postDelayed(runnableZj, 2000);
        } else {
            marksix_tab_main.setVisibility(View.GONE);
            handler.removeCallbacks(runnableRandom);
            handler.removeCallbacks(runnableRequestOpen);
            handler.removeCallbacks(runnableTime);
            handler.removeCallbacks(runnableZj);
        }
    }*/
    private String getNum(String num){
        String s = "";
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        if(list.contains(num)){
            s+="0"+num;
        }else {
            s=num;
        }
        return s;
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        customPopupWindow.selectorId(hbGameClassModel);
    }
}
