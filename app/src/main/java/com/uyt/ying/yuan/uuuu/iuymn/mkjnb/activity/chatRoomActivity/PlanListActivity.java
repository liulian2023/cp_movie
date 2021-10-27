/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.PlanListAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.PlanListPopRecyAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.RongYunMessage.RongShareBetMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KJCountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LastLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryPlanList;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MemberMoney;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PlanListModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscLastLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.ChoiceDialog;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.ConfirmDialog;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.AnimUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StrUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TimerUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqCount;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqMemberMoney;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqlastLottery;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil.REQUEST_planB;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil.timeMode;

public class PlanListActivity extends BaseActivity {

    public static  final String TAG = PlanListActivity.class.getSimpleName();
    int GAME = 0;
    int type_id = 0;
    int x = 0;  // 下拉列表 默认值
    String chatRoomId;
    Long user_id;


    int index = 0;  //tablayout 选项
    PopupWindow more_popupwindow, xiala_popupwindow;
    TextView popmore_race, popmore_ssc, popmore_k3;
    MemberMoney memberMoney;
    PlanListModel planListModel;
    LotteryPlanList lotteryPlanList;
    KJCountDown kjCountDown;
    LastLottery lastLottery;
    RaceLottery raceLottery;
    SscLastLottery sscLastLottery;

    @BindView(R.id.planlist_more)
    ImageView planlist_more;
    @BindView(R.id.planlist_tab)
    TabLayout planlist_tab;
    @BindView(R.id.planlist_recyclerview)
    RecyclerView planlist_recyclerview;
    @BindView(R.id.planlist_balance)
    TextView planlist_balance;
    @BindView(R.id.planlist_head2_iv)
    ImageView planlist_head2_iv;
    @BindView(R.id.planlist_head2_title)
    TextView planlist_head2_title;
    @BindView(R.id.planlist_qishu)
    TextView planlist_qishu;
    @BindView(R.id.planlist_countdown)
    TextView planlist_countdown;
    @BindView(R.id.planlist_head2_tv1)
    TextView planlist_head2_tv1;
    @BindView(R.id.planlist_head2_tv2)
    TextView planlist_head2_tv2;
    @BindView(R.id.planlist_head2_tv3)
    TextView planlist_head2_tv3;
    @BindView(R.id.planlist_openresult1_tv1)
    TextView planlist_openresult1_tv1;
    @BindView(R.id.planlist_openresult1_tv2)
    TextView planlist_openresult1_tv2;
    @BindView(R.id.planlist_openresult2_tv1)
    TextView planlist_openresult2_tv1;
    @BindView(R.id.planlist_openresult2_tv2)
    TextView planlist_openresult2_tv2;
    @BindView(R.id.planlist_openresult3_tv1)
    TextView planlist_openresult3_tv1;
    @BindView(R.id.planlist_openresult3_tv2)
    TextView planlist_openresult3_tv2;
    @BindView(R.id.ll_planlist_openresult1)
    LinearLayout ll_planlist_openresult1;
    @BindView(R.id.ll_planlist_openresult2)
    LinearLayout ll_planlist_openresult2;
    @BindView(R.id.ll_planlist_openresult3)
    LinearLayout ll_planlist_openresult3;
    @BindView(R.id.planlist_betmoney)
    EditText planlist_betmoney;
    @BindView(R.id.planlist_choickmoney)
    TextView planlist_choickmoney;
    @BindView(R.id.planlist_clear)
    LinearLayout planlist_clear;
    @BindView(R.id.planlist_num)
    TextView planlist_num;
    @BindView(R.id.planlist_totalmoney)
    TextView planlist_totalmoney;
    @BindView(R.id.ll__planlist_dx)
    LinearLayout ll__planlist_dx;
    @BindView(R.id.tv_planlist_dx)
    TextView tv_planlist_dx;
    @BindView(R.id.planlist_dx_iv)
    ImageView planlist_dx_iv;
    @BindView(R.id.ll_confirm)
    LinearLayout ll_confirm;
    @BindView(R.id.planlist_back)
    TextView planlist_back;
//    @BindView(R.id.loading_linear)
//    ConstraintLayout loading_linear;

    Long timeCount = 0L;  //倒计时数
    Long endtime, tqtime,  sjcha;
    String qishu, lastqishu;
    List<String> tabTitles = new ArrayList<>();
    List<PlanListModel.GameClassListBean> GameClassList = new ArrayList<>();
    List<LotteryPlanList.ResultBean> resultList = new ArrayList<>();
    List<String> gameruleList = new ArrayList<>();
  //  HashMap<String, Boolean> hashMaps = new LinkedHashMap<>();
    List<IsClickModel> isClickList = new ArrayList<>();
    PlanListAdapter planListAdapter;
    RecyclerView popupwindow_planlist_recycler;
    Animation rotateAnimation = AnimUtils.getAnimation(180);

    boolean isWaitopen = true;

    long rule_id = 0;
    double rate = 10;
    String betName ;
    String betGroupName;
    String typename;
    String isForbidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planlist);
        ButterKnife.bind(this);
        chatRoomId=getIntent().getStringExtra("chatRoomId");
        user_id=SharePreferencesUtil.getLong(PlanListActivity.this,"user_id",0l);
        isForbidden=getIntent().getStringExtra("isForbidden");
        GAME = 3;

        initMorePop();
        initData();
        clickView();
     //   getCountdown();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (runnableTime != null) {
            handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime, 300);
        if (runnableRequestOpen != null) {
            handler.removeCallbacks(runnableRequestOpen);
        }
        handler.postDelayed(runnableRequestOpen, 0);
    }

    @Override
    protected void init() {

    }

    private void initData() {
//        loading_linear.setVisibility(View.VISIBLE);
        Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, -1, new DockingUtil.ILoaderListener() {//彩票分类
            @Override
            public void success(String content) {
//                loading_linear.setVisibility(View.GONE);
                Gson gson = new Gson();
                try {
                    planListModel = gson.fromJson(content, PlanListModel.class);
                    GetTabData(GAME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
//                loading_linear.setVisibility(View.GONE);
            }
        });

    }


    private void clickView() {

        planlist_back.setOnClickListener(v -> onBackPressed());
        planlist_more.setOnClickListener(v -> ShowMorepop());
        popmore_race.setOnClickListener(v -> {
            GAME = 3;
            GetTabData(GAME);
            more_popupwindow.dismiss();
        });
        popmore_ssc.setOnClickListener(v -> {
            GAME = 2;
            GetTabData(GAME);
            more_popupwindow.dismiss();
        });
        popmore_k3.setOnClickListener(v -> {
            GAME = 1;
            GetTabData(GAME);
            more_popupwindow.dismiss();
        });
        planlist_balance.setOnClickListener(v -> {
            if (planlist_balance.getText().toString().contains("￥")) {
                planlist_balance.setText(Utils.getString(R.string.余额));
            } else {
                ReqMemberMoney(PlanListActivity.this, 0, new MyRequest.MyRequestListener() {
                    @Override
                    public void success(String content) {
                        Gson gson = new Gson();
                        memberMoney = gson.fromJson(content, MemberMoney.class);
                        if (!StringMyUtil.isEmptyString(memberMoney.getMemberMoney().getAmount())) {
                            planlist_balance.setText("￥" + String.valueOf(memberMoney.getMemberMoney().getAmount()));
                        }
                    }

                    @Override
                    public void failed(String content) {
                    }
                });
            }
        });

        planlist_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                index = tab.getPosition();
                setTabView(index);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        ll_planlist_openresult1.setOnClickListener(v -> {
            ll_planlist_openresult1.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkgred_onpress));
            planlist_openresult1_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
            planlist_openresult1_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
            ll_planlist_openresult2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult2_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult2_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
            ll_planlist_openresult3.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult3_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult3_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));

            for (int i = 0; i < lotteryPlanList.getGroupNameItems().get(x).getGameRules().size(); i++) {
                if (planlist_openresult1_tv1.getText().toString().equals(lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getName())) {
                    rule_id = lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getId();
                }
            }

            if (!StringMyUtil.isEmptyString(planlist_betmoney.getText().toString())) {
                BigDecimal betmoney = new BigDecimal(planlist_betmoney.getText().toString());
                BigDecimal odds = new BigDecimal(planlist_openresult1_tv2.getText().toString());
                BigDecimal choickmoney = betmoney.multiply(odds);
                planlist_choickmoney.setText(String.valueOf(choickmoney));
            }
            planlist_num.setText("1");
        });
        ll_planlist_openresult2.setOnClickListener(v -> {
            ll_planlist_openresult1.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult1_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult1_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
            ll_planlist_openresult2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkgred_onpress));
            planlist_openresult2_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
            planlist_openresult2_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
            ll_planlist_openresult3.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult3_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult3_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
            for (int i = 0; i < lotteryPlanList.getGroupNameItems().get(x).getGameRules().size(); i++) {
                if (planlist_openresult2_tv1.getText().toString().equals(lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getName())) {
                    rule_id = lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getId();
                }
            }

            if (!StringMyUtil.isEmptyString(planlist_betmoney.getText().toString())) {
                BigDecimal betmoney = new BigDecimal(planlist_betmoney.getText().toString());
                BigDecimal odds = new BigDecimal(planlist_openresult2_tv2.getText().toString());
                BigDecimal choickmoney = betmoney.multiply(odds);
                planlist_choickmoney.setText(String.valueOf(choickmoney));
            }
            planlist_num.setText("1");
        });
        ll_planlist_openresult3.setOnClickListener(v -> {
            ll_planlist_openresult1.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult1_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult1_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
            ll_planlist_openresult2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult2_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult2_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
            ll_planlist_openresult3.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkgred_onpress));
            planlist_openresult3_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
            planlist_openresult3_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
            for (int i = 0; i < lotteryPlanList.getGroupNameItems().get(x).getGameRules().size(); i++) {
                if (planlist_openresult3_tv1.getText().toString().equals(lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getName())) {
                    rule_id = lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getId();
                }
            }
            if (!StringMyUtil.isEmptyString(planlist_betmoney.getText().toString())) {
                BigDecimal betmoney = new BigDecimal(planlist_betmoney.getText().toString());
                BigDecimal odds = new BigDecimal(planlist_openresult3_tv2.getText().toString());
                BigDecimal choickmoney = betmoney.multiply(odds);
                planlist_choickmoney.setText(String.valueOf(choickmoney));
            }
            planlist_num.setText("1");
        });


        if (planlist_betmoney.getTag() instanceof TextWatcher) {
            planlist_betmoney.removeTextChangedListener((TextWatcher) planlist_betmoney.getTag());
        }
        TextWatcher textWatcher = new TextWatcher() {
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
                    s.delete(8, planlist_betmoney.getSelectionEnd());
                }
                if (!StringMyUtil.isEmptyString(s.toString())) {
                    planlist_totalmoney.setText(s.toString());
                    BigDecimal betmoney = new BigDecimal(s.toString());
                    for (int i = 0; i < lotteryPlanList.getGroupNameItems().get(x).getGameRules().size(); i++) {
                        if (rule_id == lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getId()) {
                            BigDecimal odds = new BigDecimal(String.valueOf(lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getOdds()));
                            odds = getOdds(odds, rate);
                            BigDecimal choickmoney = betmoney.multiply(odds);
                            planlist_choickmoney.setText(String.valueOf(choickmoney));

                        }
                    }

                } else {
                    planlist_choickmoney.setText("0");
                    planlist_totalmoney.setText("0");
                }


            }
        };
        planlist_betmoney.addTextChangedListener(textWatcher);
        planlist_betmoney.setTag(textWatcher);

        //清空
        planlist_clear.setOnClickListener(v -> {
            planlist_betmoney.setText("");
            planlist_num.setText("0");
            planlist_totalmoney.setText("0");
            rule_id = 0;

            ll_planlist_openresult1.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult1_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult1_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
            ll_planlist_openresult2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult2_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult2_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
            ll_planlist_openresult3.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
            planlist_openresult3_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
            planlist_openresult3_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
        });


        ll__planlist_dx.setOnClickListener(v -> {
            planlist_dx_iv.startAnimation(rotateAnimation);
            planlist_dx_iv.setImageResource(R.drawable.sanjiao1);
            showPopupWindow();
        });

        ll_confirm.setOnClickListener(v -> {
            if (rule_id == 0) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(PlanListActivity.this);
                confirmDialog.setTitle(Utils.getString(R.string.温馨提示));
                confirmDialog.setMessage(getString(R.string.请选择号码));
                confirmDialog.setYesOnclickListener(Utils.getString(R.string.我知道了), new ConfirmDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.show();
                confirmDialog.setCanceledOnTouchOutside(false); //外部不可点击

            } else if (StringMyUtil.isEmptyString(planlist_betmoney.getText().toString()) || planlist_betmoney.getText().toString().equals("0")) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(PlanListActivity.this);
                confirmDialog.setTitle(Utils.getString(R.string.温馨提示));
                confirmDialog.setMessage(Utils.getString(R.string.请填写金额));
                confirmDialog.setYesOnclickListener(Utils.getString(R.string.我知道了), new ConfirmDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        confirmDialog.dismiss();

                    }
                });
                confirmDialog.show();
                confirmDialog.setCanceledOnTouchOutside(false); //外部不可点击

            } else {
                final ChoiceDialog choiceDialog = new ChoiceDialog(PlanListActivity.this);
                final String curtime = TimerUtils.getTimeStyleTwo();
                final String amount = planlist_betmoney.getText().toString();
                String strName = "";

                for (int i = 0; i < lotteryPlanList.getGroupNameItems().get(x).getGameRules().size(); i++) {
                    if (rule_id == lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getId()) {
                        strName = lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getName();
                        betName = lotteryPlanList.getGroupNameItems().get(x).getGameRules().get(i).getName();
                    }
                }
                String content = tabTitles.get(index) + ":" + qishu + "\nUtils.getString(R.string. + getString(R.string.投注金额冒号) + amount + )\n" + getString(R.string.投注内容冒号) + strName;
                choiceDialog.setTitle(getString(R.string.投注确认));
                choiceDialog.setMessage(content);
                choiceDialog.setYesOnclickListener(Utils.getString(R.string.确认), ()->{
                        choiceDialog.dismiss();
                        MyRequest.ReqTouzhu(PlanListActivity.this, String.valueOf(GAME), String.valueOf(type_id), curtime, qishu, amount, String.valueOf(rule_id), "0", "", 0, new MyRequest.MyRequestListener1() {

                            @Override
                            public void success1(String content) {
                                showToast(getString(R.string.投注成功));
                                planlist_betmoney.setText("");
                                planlist_num.setText("0");
                                planlist_totalmoney.setText("0");
                                long fx_rule_id = rule_id;
                                rule_id = 0;

                                ll_planlist_openresult1.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
                                planlist_openresult1_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
                                planlist_openresult1_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
                                ll_planlist_openresult2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
                                planlist_openresult2_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
                                planlist_openresult2_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
                                ll_planlist_openresult3.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
                                planlist_openresult3_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
                                planlist_openresult3_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.white));
                                if(isForbidden.equals("0")){//未开启禁言
                                    //投注成功,自动分享到聊天室
                                    String chatRoomAmount = SharePreferencesUtil.getString(PlanListActivity.this, "chatRoomAmount", "");
                                    if(Long.parseLong(amount)>Long.parseLong(chatRoomAmount)){
                                        //发送消息
                                        String touxiang=SharePreferencesUtil.getString(PlanListActivity.this,"oldUserImage","");
                                        String userNickName=SharePreferencesUtil.getString(PlanListActivity.this,"userNickName","");
                                        int pointGrade=SharePreferencesUtil.getInt(PlanListActivity.this, CommonStr.GRADE,1);
                                        RongShareBetMessage rongShareBetMessage = null;
                                        rongShareBetMessage = new RongShareBetMessage(userNickName,pointGrade+"",touxiang,
                                                type_id+"",fx_rule_id+"",qishu,GAME+"",typename,amount,betName,betGroupName,"","",user_id+"");
                                        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.CHATROOM, chatRoomId, rongShareBetMessage, null, null, new IRongCallback.ISendMessageCallback() {
                                            @Override
                                            public void onAttached(io.rong.imlib.model.Message message) {
                                            }
                                            @Override
                                            public void onSuccess(io.rong.imlib.model.Message message) {
                                                //分享成功,弹出是否进入聊天室pop
//                                                showtoast(Utils.getString(R.string.分享成功));
                                                Utils.logE("PlanListActivity", getString(R.string.计划列表自动分享成功) );
                                            }

                                            @Override
                                            public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {

                                                int value = errorCode.getValue( );
                                                Utils.logE("PlanListActivity", getString(R.string.计划列表自动分享失败) );
                                                int messageId = message.getMessageId();
                                                int[] deleteId={messageId};
                                                //禁言分享失败,删除此条消息(分享失败时,从融云服务器获取50条消息记录时,失败的消息也会加载)
                                                RongIMClient.getInstance().deleteMessages(deleteId, new RongIMClient.ResultCallback<Boolean>() {
                                                    @Override
                                                    public void onSuccess(Boolean aBoolean) {

                                                    }

                                                    @Override
                                                    public void onError(RongIMClient.ErrorCode errorCode) {

                                                    }
                                                });
//                                            if(value==23408){
//                                                showtoast(Utils.getString(R.string.您已被禁言));
//                                            }else {
//                                                showtoast(Utils.getString(R.string.分享失败));
//                                            }
                                            }
                                        });
                                    }
                                }

                            }

                            @Override
                            public void failed1(String  failMessage) {
                                    showToast(failMessage);

                            }
                        });


                });
                choiceDialog.setNoOnclickListener(Utils.getString(R.string.取消), () -> choiceDialog.dismiss());
                choiceDialog.show();
                choiceDialog.setCanceledOnTouchOutside(false); //外部不可点击
            }
        });


    }

    private void initMorePop() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.pop_planlist_more, null, false);//引入弹窗布局
        popmore_race = contentView.findViewById(R.id.planlist_popmore_race);
        popmore_ssc = contentView.findViewById(R.id.planlist_popmore_ssc);
        popmore_k3 = contentView.findViewById(R.id.planlist_popmore_k3);
        more_popupwindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
    }

    private void ShowMorepop() {
        more_popupwindow.setAnimationStyle(R.style.popupAnimation);//设置进出动画
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;//调节透明度
        getWindow().setAttributes(lp);
        more_popupwindow.setOutsideTouchable(true);
        more_popupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);//恢复背景亮度
            }
        });
        View view = findViewById(R.id.rl_planlist_head);
        more_popupwindow.showAsDropDown(view);

    }

    public void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;
        if(bgcolor==1f){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        getWindow().setAttributes(lp);
    }

    private void GetTabData(int game) {
        tabTitles.clear();
        GameClassList.clear();
        planlist_tab.removeAllTabs();
        planlist_tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < planListModel.getGameClassList().size(); i++) {
            if (game == planListModel.getGameClassList().get(i).getGame()) {
                GameClassList.add(planListModel.getGameClassList().get(i));
                tabTitles.add(planListModel.getGameClassList().get(i).getTypename());
            }
        }

        for (String str : tabTitles) {
            planlist_tab.addTab(planlist_tab.newTab().setText(str));  //tab设置名称
        }

    }

    private void setTabView(int position) {

        Glide.with(this)
                .load(Utils.getFirstImgurl(getApplicationContext()) + GameClassList.get(position).getImage())
                .into(planlist_head2_iv);
        planlist_head2_title.setText(GameClassList.get(position).getTypename());
        typename = GameClassList.get(position).getTypename();
        type_id = GameClassList.get(position).getType_id();


        planlist_betmoney.setText("");
        planlist_num.setText("0");
        planlist_totalmoney.setText("0");
        rule_id = 0;

        ll_planlist_openresult1.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
        planlist_openresult1_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
        planlist_openresult1_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
        ll_planlist_openresult2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
        planlist_openresult2_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
        planlist_openresult2_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
        ll_planlist_openresult3.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_no_enable));
        planlist_openresult3_tv1.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.action_bar_color));
        planlist_openresult3_tv2.setTextColor(ContextCompat.getColor(PlanListActivity.this,R.color.textgray));
        //TODO 选取popupwindow 和 切换tablayout后 获取倒计时 上期开奖结果   PlanList
        getCountdown();
        x = 0;

    }

    //显示倒计时
    private void getCountdown() {
        ReqCount(this, String.valueOf(GAME), String.valueOf(type_id), 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                if (!StringMyUtil.isEmptyString(content)) {
                    try {
                        Gson gson = new Gson();
                        kjCountDown = gson.fromJson(content, KJCountDown.class);
                        Utils.logE("kjCountDown:", "" + kjCountDown.getMessage());
                        //设置全局变量
                        if (kjCountDown != null) {
                            endtime = Long.parseLong(DateUtil.dateToStamp(kjCountDown.getStoptimestr()));
                            sjcha = SharePreferencesUtil.getLong(PlanListActivity.this, "shijiancha", 0l);//servertime - localtime;
                            tqtime = (long) kjCountDown.getTqtime() * 1000;
                            qishu = kjCountDown.getQishu();
                            lastqishu = kjCountDown.getLastqishu();
                            timeCount = 1L;
                            if (StringMyUtil.isEmptyString(qishu)) {
                                planlist_qishu.setVisibility(View.INVISIBLE);
                            } else {
                                planlist_qishu.setText(qishu + Utils.getString(R.string. 期));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(String content) {
                Utils.logE(TAG,content);
            }
        });
        if (isWaitopen) {
            if (runnableRequestOpen != null) {
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, 0);
        }

    }

    //上期开奖结果
    private void getLastLottery() {
        ReqlastLottery(this, String.valueOf(GAME), String.valueOf(type_id), 1, 1, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                Utils.logE("PLANLIST:", content);
                Gson gson = new Gson();
                switch (GAME) {
                    case 3:
                        try {
                            raceLottery = gson.fromJson(content, RaceLottery.class);
                            if (raceLottery != null) {
                                if (tv_planlist_dx.getText().toString().contains(getString(R.string.大小))) {
                                    planlist_head2_tv2.setText(raceLottery.getRaceLotterylist().get(0).getMarkdx());
                                    if (raceLottery.getRaceLotterylist().get(0).getMarkdx().equals(getString(R.string.大))) {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_blue));
                                    } else {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_orange));
                                    }
                                } else {
                                    planlist_head2_tv2.setText(raceLottery.getRaceLotterylist().get(0).getMarkds());
                                    if (raceLottery.getRaceLotterylist().get(0).getMarkds().equals(getString(R.string.双))) {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_blue));
                                    } else {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_orange));
                                    }
                                }
                                planlist_head2_tv3.setText(Utils.getString(R.string.四期));
                                String lotteryqishu = raceLottery.getRaceLotterylist().get(0).getLotteryqishu();
                                if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu.substring(lotteryqishu.length() - 2, lotteryqishu.length()))) {
                                    isWaitopen = false;
                                } else if (Long.parseLong(lotteryqishu + 1) == Long.parseLong(qishu)) {
                                    isWaitopen = false;
                                } else {
                                    isWaitopen = true;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            sscLastLottery = gson.fromJson(content, SscLastLottery.class);
                            if (sscLastLottery != null) {
                                planlist_head2_tv2.setText(sscLastLottery.getSscaiLotterylist().get(0).getMarklh());
                                planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_orange));
                                String lotteryqishu1 = sscLastLottery.getSscaiLotterylist().get(0).getLotteryqishu();
                                if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu1.substring(lotteryqishu1.length() - 2, lotteryqishu1.length()))) {
                                    isWaitopen = false;
                                } else if (Long.parseLong(lotteryqishu1 + 1) == Long.parseLong(qishu)) {
                                    isWaitopen = false;
                                } else {
                                    isWaitopen = true;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            lastLottery = gson.fromJson(content, LastLottery.class);
                            if (lastLottery != null) {
                                if (tv_planlist_dx.getText().toString().contains(getString(R.string.大小))) {
                                    planlist_head2_tv2.setText(lastLottery.getGameLotterylist().get(0).getRemark());
                                    if (lastLottery.getGameLotterylist().get(0).getRemark().equals(getString(R.string.大))) {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_blue));
                                    } else {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_orange));
                                    }
                                } else {
                                    planlist_head2_tv2.setText(lastLottery.getGameLotterylist().get(0).getDs());
                                    if (lastLottery.getGameLotterylist().get(0).getDs().equals(getString(R.string.双))) {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_blue));
                                    } else {
                                        planlist_head2_tv2.setBackground(ContextCompat.getDrawable(PlanListActivity.this,R.drawable.shape_bkg_orange));
                                    }
                                }
                                String lotteryqishu2 = lastLottery.getGameLotterylist().get(0).getLotteryqishu();
                                if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu2.substring(lotteryqishu2.length() - 2, lotteryqishu2.length()))) {
                                    isWaitopen = false;
                                } else if (Long.parseLong(lotteryqishu2 + 1) == Long.parseLong(qishu)) {
                                    isWaitopen = false;
                                } else {
                                    isWaitopen = true;
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                }
            }
            @Override
            public void failed(String content) {
            }
        });
    }

    private void getPlanList(final int position) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("game", GAME);
        data.put("type_id", type_id);
        data.put("position", position);

        Utils.docking(data, REQUEST_planB, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                Utils.logE("content", content);
                Gson gson = new Gson();
                try {
                    lotteryPlanList = gson.fromJson(content, LotteryPlanList.class);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Utils.logE("lotteryPlanList", "" + lotteryPlanList);
                switch (GAME) {
                    case 3:
                        rate = Double.valueOf(SharePreferencesUtil.getString(PlanListActivity.this, "3", "10"));
                        break;
                    case 2:
                        rate = Double.valueOf(SharePreferencesUtil.getString(PlanListActivity.this, "2", "10"));
                        break;
                    case 1:
                        rate = Double.valueOf(SharePreferencesUtil.getString(PlanListActivity.this, "1", "10"));
                        break;
                }
                if (GAME==2){//时时彩
                    betGroupName = lotteryPlanList.getGroupNameItems().get(position).getTitle();
                    planlist_head2_tv1.setText(betGroupName);

                }else {
                    betGroupName = lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(0).getGroupname();
                    planlist_head2_tv1.setText(betGroupName);
                }

                if (lotteryPlanList.getGroupNameItems().get(position).getGameRules().size() == 2) {
                    ll_planlist_openresult3.setVisibility(View.GONE);
                    planlist_openresult1_tv1.setText(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(0).getName());
                    String odds = String.valueOf(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(0).getOdds());
                    odds = String.valueOf(getOdds(new BigDecimal(odds), rate));
                    planlist_openresult1_tv2.setText(odds);
                    planlist_openresult2_tv1.setText(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(1).getName());
                    String odds1 = String.valueOf(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(1).getOdds());
                    odds1 = String.valueOf(getOdds(new BigDecimal(odds1), rate));
                    planlist_openresult2_tv2.setText(odds1);
                } else {
                    ll_planlist_openresult3.setVisibility(View.VISIBLE);
                    planlist_openresult1_tv1.setText(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(0).getName());
                    String odds = String.valueOf(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(0).getOdds());
                    odds = String.valueOf(getOdds(new BigDecimal(odds), rate));
                    planlist_openresult1_tv2.setText(odds);
                    planlist_openresult2_tv1.setText(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(1).getName());
                    String odds1 = String.valueOf(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(1).getOdds());
                    odds1 = String.valueOf(getOdds(new BigDecimal(odds1), rate));
                    planlist_openresult2_tv2.setText(odds1);
                    planlist_openresult3_tv1.setText(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(2).getName());
                    String odds2 = String.valueOf(lotteryPlanList.getGroupNameItems().get(position).getGameRules().get(2).getOdds());
                    odds2 = String.valueOf(getOdds(new BigDecimal(odds2), rate));
                    planlist_openresult3_tv2.setText(odds2);
                }

                gameruleList.clear();
              //  hashMaps.clear();
                isClickList.clear();
                for (int i = 0; i < lotteryPlanList.getGroupNameItems().size(); i++) {
                    gameruleList.add(lotteryPlanList.getGroupNameItems().get(i).getTitle());
                    if (i == position) {
                    //    hashMaps.put(lotteryPlanList.getGroupNameItems().get(i).getTitle(), true);
                        IsClickModel isClickModel = new IsClickModel();
                        isClickModel.setId(lotteryPlanList.getGroupNameItems().get(i).getTitle());
                        isClickModel.setIsclick(true);
                        isClickList.add(isClickModel);

                    } else {
                     //   hashMaps.put(lotteryPlanList.getGroupNameItems().get(i).getTitle(), false);
                        IsClickModel isClickModel = new IsClickModel();
                        isClickModel.setId(lotteryPlanList.getGroupNameItems().get(i).getTitle());
                        isClickModel.setIsclick(false);
                        isClickList.add(isClickModel);
                    }

                }

                tv_planlist_dx.setText(gameruleList.get(position));

                resultList.clear();
                List<LotteryPlanList.ResultBean> list = lotteryPlanList.getResult();
                resultList.addAll(list);

                if (planListAdapter == null) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(PlanListActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    planlist_recyclerview.setLayoutManager(layoutManager);
                    planlist_recyclerview.setFocusableInTouchMode(false);
                    planListAdapter = new PlanListAdapter(PlanListActivity.this, resultList);
                    planlist_recyclerview.setAdapter(planListAdapter);
                } else {
                    planListAdapter.notifyDataSetChanged();
                }

                //TODO planlist 获取成功后根据x 大小or单双 获取上期开奖结果
                getLastLottery();

            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    //显示下拉列表
    private void showPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.popupwindow_planlist, null, false);//引入弹窗布局
        popupwindow_planlist_recycler = contentView.findViewById(R.id.popupwindow_planlist_recycler);
        xiala_popupwindow = new PopupWindow(contentView, ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT, true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        xiala_popupwindow.setOutsideTouchable(true);
        xiala_popupwindow.setOnDismissListener(()-> {
            planlist_dx_iv.startAnimation(rotateAnimation);
            planlist_dx_iv.setImageResource(R.drawable.sanjiao2);
            lp.alpha = 1f;
            getWindow().setAttributes(lp);
        });
        View view = findViewById(R.id.ll__planlist_dx);
        xiala_popupwindow.showAsDropDown(view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PlanListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popupwindow_planlist_recycler.setLayoutManager(layoutManager);
        PlanListPopRecyAdapter planListPopRecyAdapter = new PlanListPopRecyAdapter(this, gameruleList, isClickList);
        popupwindow_planlist_recycler.setAdapter(planListPopRecyAdapter);


    }

    //下拉回调方法  更新界面
    public void OnClickListener(List<IsClickModel> isClickList) {

        for (int i=0;i<isClickList.size();i++){
            if (StrUtil.isclick(isClickList,isClickList.get(i).getId())){
                tv_planlist_dx.setText(isClickList.get(i).getId());
                if (xiala_popupwindow.isShowing()) {
                    planlist_dx_iv.startAnimation(rotateAnimation);
                    planlist_dx_iv.setImageResource(R.drawable.sanjiao2);
                    xiala_popupwindow.dismiss();
                }
            }
        }
        for (int i = 0; i < gameruleList.size(); i++) {
            if (tv_planlist_dx.getText().toString().equals(gameruleList.get(i))) {
                x = i;
            }
        }
        getPlanList(x);
        getLastLottery();
    }

    private BigDecimal getOdds(BigDecimal odds, double rate) {
        odds = odds.subtract(new BigDecimal((10 - rate) / 100.0).multiply(odds).setScale(3, BigDecimal.ROUND_DOWN));
        //   2-((10-9)/100*2)=1.98
        return odds;
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
                planlist_countdown.setText(str_time);
                //   timeCount--;
                if (timeCount <= 1) {
                    timeCount = 0L;

                    getCountdown();
                    getPlanList(x);

                }

            }
            handler.postDelayed(runnableTime, 300);
        }
    };

    Runnable runnableRequestOpen = new Runnable() {
        @Override
        public void run() {
            if (isWaitopen) {
                //TODO 开奖中
                getPlanList(x);
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
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (runnableTime != null) {
            handler.removeCallbacks(runnableTime);
        }
        if (runnableRequestOpen != null) {
            handler.removeCallbacks(runnableRequestOpen);
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
