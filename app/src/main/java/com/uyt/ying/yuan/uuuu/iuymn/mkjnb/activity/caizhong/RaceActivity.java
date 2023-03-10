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

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RaceOfficialPAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RaceParentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RecyclerViewGridAdapter;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceGroup;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceGroupBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.State;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.BetDialog;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqCount;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqMemberMoney;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqgetGroup;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqgetNewplay;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqlastLottery;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil.timeMode;

public class RaceActivity extends BaseActivity implements CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener {
    public static final int ReqTouzhu = 101;
    private String todayZJ;
    String type_id, isopenOffice, isStart, game, typename;
    RaceGroup raceGroup;
    NewplayModel newplayModel;
    KJCountDown kjCountDown;
    MemberMoney memberMoney;
    private PopupWindow CountDownEndPop;//?????????????????????pop

    List<RaceGroupBean> raceGroupBeanList1 = new ArrayList<>(), raceGroupBeanList2 = new ArrayList<>(), raceGroupBeanList3 = new ArrayList<>(), raceGroupBeanAllList  = new ArrayList<>();
    //   HashMap<String, Boolean> isClickMap = new LinkedHashMap<>();
    List<IsClickModel> isClickList = new ArrayList<>();
    //  List<IsClickModel> BList = new ArrayList<>();
    int[][] B = new int[10][6];

    RaceParentAdapter raceParentAdapter;

    Animation rotateAnimation = AnimUtils.getAnimation(180);
    RecyclerView head1recyclerview;
    PopupWindow head1popupWindow;

    Long endtime, localtime, sjcha, tqtime;  //???????????? ???????????? ???????????????????????????
    String qishu, lastqishu;
    Long timeCount = 0L;  //????????????
    boolean isWaitopen = true;
    //  long num = 1;

    RaceLottery raceLottery;
    String lotteryqishu;

    @BindView(R.id.caizhong_head1_back)
    TextView caizhong_head1_back;
    @BindView(R.id.caizhong_head1_title)
    TextView caizhong_head1_title;
    @BindView(R.id.xy_race_recyclerview)
    RecyclerView xy_race_recyclerview;
    @BindView(R.id.ll_caizhong_head1)
    LinearLayout ll_caizhong_head1;
    @BindView(R.id.caizhong_head1_iv)
    ImageView caizhong_head1_iv;
    @BindView(R.id.ll2_race_head3)
    LinearLayout ll2_race_head3;
    @BindView(R.id.k3_tab_num)
    TextView k3_tab_num;
    @BindView(R.id.k3_tab_money)
    EditText k3_tab_money;//??????
    @BindView(R.id.k3_tab_confirm)
    TextView k3_tab_confirm;  //??????
    @BindView(R.id.k3_tab_reset)
    TextView k3_tab_reset;  //??????

    TextView lastQiShuTv;
    TextView newQiShuTv;
    TextView countDownEndSure;
    @BindView(R.id.ll1_race_head3)
    LinearLayout ll1_race_head3;
    @BindView(R.id.ll_caizhong_head2_left)
    LinearLayout ll_caizhong_head2_left;
    @BindView(R.id.ll_caizhong_head2_left_tv)
    TextView ll_caizhong_head2_left_tv;
    @BindView(R.id.race_head3_tv1)
    TextView race_head3_tv1;  //????????????
    @BindView(R.id.race_head3_tv2)
    TextView race_head3_tv2;  //?????????
    @BindView(R.id.race_head3_tv3)
    TextView race_head3_tv3;
    @BindView(R.id.race_head3_iv1)
    ImageView race_head3_iv1;
    @BindView(R.id.race_head3_iv2)
    ImageView race_head3_iv2;
    @BindView(R.id.race_head3_iv3)
    ImageView race_head3_iv3;
    @BindView(R.id.race_head3_iv4)
    ImageView race_head3_iv4;
    @BindView(R.id.race_head3_iv5)
    ImageView race_head3_iv5;
    @BindView(R.id.race_head3_iv6)
    ImageView race_head3_iv6;
    @BindView(R.id.race_head3_iv7)
    ImageView race_head3_iv7;
    @BindView(R.id.race_head3_iv8)
    ImageView race_head3_iv8;
    @BindView(R.id.race_head3_iv9)
    ImageView race_head3_iv9;
    @BindView(R.id.race_head3_iv10)
    ImageView race_head3_iv10;
    @BindView(R.id.caizhong_head1_menu)
    TextView caizhong_head1_menu;
    @BindView(R.id.ll_caizhong_head2_right)
    LinearLayout ll_caizhong_head2_right;
    @BindView(R.id.ll_caizhong_head2_center)
    LinearLayout ll_caizhong_head2_center;
    @BindView(R.id.ll_caizhong_head2_center_tv)
    TextView ll_caizhong_head2_center_tv;
    @BindView(R.id.race_tab_main)
    LinearLayout race_tab_main;
    @BindView(R.id.ll_caizhong_head1_parent)
    LinearLayout ll_caizhong_head1_parent;
    @BindView(R.id.ll_reload)
    LinearLayout ll_reload;
    @BindView(R.id.tv_reload)
    TextView tv_reload;
    @BindView(R.id.rotate_image)
    ImageView rotate_image;
    @BindView(R.id.ll_caizhong_head2_right_tv)
    TextView ll_caizhong_head2_right_tv;
    @BindView(R.id.ll_xy_race_recycler)
    LinearLayout ll_xy_race_recycler;
    @BindView(R.id.ll_gf_race_recycler)
    LinearLayout ll_gf_race_recycler;
    @BindView(R.id.ll_touzhu_xy)
    LinearLayout ll_touzhu_xy;
    @BindView(R.id.ll_touzhu_gf)
    LinearLayout ll_touzhu_gf;
    @BindView(R.id.gf_race_recyclerview)
    RecyclerView gf_race_recyclerview;
    @BindView(R.id.tv_touzhu_yuan)
    TextView tv_touzhu_yuan;
    @BindView(R.id.tv_touzhu_jiao)
    TextView tv_touzhu_jiao;
    @BindView(R.id.tv_touzhu_gf_jian)
    TextView tv_touzhu_gf_jian;
    @BindView(R.id.tv_touzhu_gf_jia)
    TextView tv_touzhu_gf_jia;
    @BindView(R.id.tv_touzhu_gf_times)
    EditText tv_touzhu_gf_times;
    @BindView(R.id.tv_touzhu_zjmoney)
    TextView tv_touzhu_zjmoney;
    @BindView(R.id.tv_touzhu_jixuan)
    TextView tv_touzhu_jixuan;
    @BindView(R.id.tv_touzhu_num)
    TextView tv_touzhu_num;
    @BindView(R.id.tv_touzhu_money)
    TextView tv_touzhu_money;
    @BindView(R.id.touzhu_gf_confirm)
    TextView touzhu_gf_confirm;
    @BindView(R.id.race_time_loading_img)
    ImageView race_time_loading_img;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.fenpan_tv)
    TextView fenpan_tv;

    List<Integer> kjList = new ArrayList<>(); //??????list  ???????????? ??????id
    List<String> titleList = new ArrayList<>();
    int[] qius;//?????????

    boolean isOfficial = false;
    int index_xy = 0;
    int index_gf = 0;
    double danwei = 1.00;
    RecyclerViewGridAdapter recyclerViewGridAdapter;
    List<NewplayModel.PlayRuleListOneBean> ruleoneList = new ArrayList<>();
    List<NewplayModel.PlayRuleListTwoBean> ruletwoList = new ArrayList<>();
    List<NewplayModel.PlayRuleListThreeBean> rulethreeList = new ArrayList<>();
    List<NewplayModel.PlayRuleListFourBean> rulefourList = new ArrayList<>();    //25???
    List<NewplayModel.PlayRuleListFourBean> choicefourList = new ArrayList<>(); //10???
    NewplayModel.PlayRuleListThreeBean playRuleListThreeBean;

    List<GfBetModel> betModelList = new ArrayList<>();   //????????????List
    long[][] A1 = new long[10][10]; //????????? 0 1 ?????? ????????????

    private long mLastClickTime = 0;
    public static final long TIME_INTERVAL = 1000L;


    RaceOfficialPAdapter raceOfficialPAdapter;
    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_race);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        Utils. saveLotteryHistory(Integer.parseInt(game),Integer.parseInt(type_id));
        ReqCountdown();
        initData();
        clickView();
        initCountDownEndPop();
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
            race_tab_main.setVisibility(View.VISIBLE);
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
            race_tab_main.setVisibility(View.GONE);
            ll_caizhong_head1_parent.setVisibility(View.GONE);
            ll_reload.setVisibility(View.VISIBLE);
            showToast(Utils.getString(R.string.??????????????????????????????));
        }
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        typename = intent.getStringExtra("typename");
        type_id = intent.getStringExtra("type_id");
        isopenOffice = intent.getStringExtra("isopenOffice");
        isopenOffice = StringMyUtil.isEmptyString(isopenOffice)?"1":isopenOffice;
        isStart = intent.getStringExtra("isStart");
        qius = Const.getRaceqiuArray(this);
    }

    //???????????????????????????????????????
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

    //???????????????????????? getgroup
    private void initData() {
        checkPara();
        loading_linear.setVisibility(View.VISIBLE);
        ll_caizhong_head2_left_tv.setText(typename);
        if (!isOfficial) {  //TODO ???????????????????????????
            ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.????????????));
            ll_xy_race_recycler.setVisibility(View.VISIBLE);
            ll_touzhu_xy.setVisibility(View.VISIBLE);
            ll_gf_race_recycler.setVisibility(View.GONE);
            ll_touzhu_gf.setVisibility(View.GONE);
            ReqgetGroup(this, game, type_id, -1, new MyRequest.MyRequestListener() {
                @Override
                public void success(String content) {
                    loading_linear.setVisibility(View.GONE);
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    if (!StringMyUtil.isEmptyString(content)) {
                        try {
                            Utils.logE("raceGroup", "1");
                            raceGroup = gson.fromJson(content, RaceGroup.class);
                            Utils.logE("raceGroup", "2");

                            if (raceGroup != null) {
                                List<RaceGroup.RulelistAllBean> RulelistAllList;
                                RulelistAllList = raceGroup.getRulelistAll();
                                Collections.reverse(RulelistAllList);
                                raceGroupBeanList1.clear();
                                for (int i = 0; i < RulelistAllList.size(); i++) {
                                    if (RulelistAllList.get(i).getIsboth() == 1 && RulelistAllList.get(i).getIsGY() == 1 && RulelistAllList.get(i).getType() == 2) { //??????
                                        String strJsonArry = RulelistAllList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            RaceGroupBean bean = new RaceGroupBean();
                                            bean.setDgroupname(Utils.getString(R.string.??????));
                                            bean.setXgroupname(jsonObject.getString("groupname"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLong("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsGY(jsonObject.getIntValue("isGY"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            //         bean.setLastModifiedDate(jsonObject.getLong("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDouble("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            raceGroupBeanList1.add(bean);
                                        }
                                        //???raceGroupBeanList2????????????
                                        List<RaceGroupBean> list1 = new ArrayList<>(); //?????????  ??????4???
                                        List<RaceGroupBean> list2 = new ArrayList<>();  //?????????  1-17???
                                        for (int x = 50; x < 54; x++) {
                                            list1.add(raceGroupBeanList1.get(x));
                                        }
                                        for (int x = 0; x < 50; x++) {
                                            list2.add(raceGroupBeanList1.get(x));
                                        }
                                        raceGroupBeanList1.clear();
                                        raceGroupBeanList1.addAll(list1);
                                        raceGroupBeanList1.addAll(list2);
                                    } else if (RulelistAllList.get(i).getIsboth() == 0 && RulelistAllList.get(i).getIsGY() == 1 && RulelistAllList.get(i).getType() == 2) { //?????????
                                        raceGroupBeanList2 .clear();
                                        String strJsonArry = RulelistAllList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            RaceGroupBean bean = new RaceGroupBean();
                                            bean.setDgroupname(getString(R.string.?????????));
                                            bean.setXgroupname(getString(R.string.???????????????));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLong("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsGY(jsonObject.getIntValue("isGY"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDouble("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));
                                            raceGroupBeanList2.add(bean);
                                        }
                                        //???raceGroupBeanList2????????????
                                        List<RaceGroupBean> list1 = new ArrayList<>(); //?????????  ??????4???
                                        List<RaceGroupBean> list2 = new ArrayList<>();  //?????????  1-17???
                                        for (int x = 17; x < 21; x++) {
                                            list1.add(raceGroupBeanList2.get(x));
                                        }
                                        for (int x = 0; x < 17; x++) {
                                            list2.add(raceGroupBeanList2.get(x));
                                        }
                                        raceGroupBeanList2.clear();
                                        raceGroupBeanList2.addAll(list1);
                                        raceGroupBeanList2.addAll(list2);
                                        //           Utils.logE("raceGroupBeanList2", "" + raceGroupBeanList2);
                                    } else if (RulelistAllList.get(i).getIsboth() == 0 && RulelistAllList.get(i).getIsGY() == 0 && RulelistAllList.get(i).getType() == 0) { //?????????
                                        raceGroupBeanList3.clear();
                                        String strJsonArry = RulelistAllList.get(i).getRulelist();
                                        JSONArray jsonArray = JSON.parseArray(strJsonArry);
                                        for (int j = 0; j < jsonArray.size(); j++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);
                                            RaceGroupBean bean = new RaceGroupBean();

                                            bean.setDgroupname(Utils.getString(R.string.??????10???));
                                            bean.setXgroupname(jsonObject.getString("groupname"));
                                            bean.setCode(jsonObject.getString("code"));
                                            bean.setCreatedDate(jsonObject.getLong("createdDate"));
                                            bean.setCreatedUser(jsonObject.getString("createdUser"));
                                            bean.setGroup_id(jsonObject.getIntValue("group_id"));
                                            bean.setGroupname(jsonObject.getString("groupname"));
                                            bean.setId(jsonObject.getIntValue("id"));
                                            bean.setIsDelete(jsonObject.getIntValue("isDelete"));
                                            bean.setIsGY(jsonObject.getIntValue("isGY"));
                                            bean.setIsboth(jsonObject.getIntValue("isboth"));
                                            //            bean.setLastModifiedDate(jsonObject.getLong("lastModifiedDate"));
                                            bean.setModel_id(jsonObject.getIntValue("model_id"));
                                            bean.setName(jsonObject.getString("name"));
                                            bean.setOdds(jsonObject.getDouble("odds"));
                                            bean.setType_id(jsonObject.getIntValue("type_id"));

                                            raceGroupBeanList3.add(bean);
                                        }
                                    }
                                }
                                //?????????????????????4??????newlist
                                List<RaceGroupBean> newlist = new ArrayList<>();
                                for (int i = 4; i < raceGroupBeanList2.size(); i++) {
                                    newlist.add(raceGroupBeanList2.get(i));
                                }
                                raceGroupBeanAllList.clear();
                                raceGroupBeanAllList.addAll(raceGroupBeanList1);
                                raceGroupBeanAllList.addAll(newlist);
                                raceGroupBeanAllList.addAll(raceGroupBeanList3);  //??????171??????
                                //???????????????  ?????? ??? ??????  ?????????  ??????10???
                                //     caizhong_head1_title.setText(Utils.getString(R.string.??????));
                                //  isClickMap.clear();
                                isClickList.clear();
                                for (int i = 0; i < raceGroupBeanAllList.size(); i++) {
                                    //    isClickMap.put(String.valueOf(raceGroupBeanAllList.get(i).getId()), false);
                                    StrUtil.isclickAdd(isClickList, String.valueOf(raceGroupBeanAllList.get(i).getId()), false);
                                }
                                showcaizhong();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void failed(String content) {
                    //      showtoast(Utils.getString(R.string.??????????????????));
                    loading_linear.setVisibility(View.GONE);
                }
            });
        } else {
            // TODO ???????????????????????????
                ll_xy_race_recycler.setVisibility(View.GONE);
                ll_touzhu_xy.setVisibility(View.GONE);
                ll_gf_race_recycler.setVisibility(View.VISIBLE);
                ll_touzhu_gf.setVisibility(View.VISIBLE);
                ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.????????????));
                ReqgetNewplay(this, game, type_id, -1, new MyRequest.MyRequestListener() {
                    @Override
                    public void success(String content) {
                        loading_linear.setVisibility(View.GONE);

                        Utils.logE("content", content);
                        Gson gson = new GsonBuilder().serializeNulls().create();
                        if (!StringMyUtil.isEmptyString(content)) {
                            try {
                                newplayModel = gson.fromJson(content, NewplayModel.class);
                                if (newplayModel != null) {
                                    ruleoneList = newplayModel.getPlayRuleListOne();
                                    ruletwoList = newplayModel.getPlayRuleListTwo();
                                    rulethreeList = newplayModel.getPlayRuleListThree();
                                    rulefourList = newplayModel.getPlayRuleListFour();
                                    showcaizhong();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void failed(String content) {
                        Utils.logE("content", content);
                        loading_linear.setVisibility(View.GONE);
                    }
                });

        }

        requestMoney();
    }

    //???????????????
    private void ReqCountdown() {
        race_head3_tv2.setVisibility(View.GONE);
        race_time_loading_img.setVisibility(View.VISIBLE);
        ReqCount(this, game, type_id, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                if (!StringMyUtil.isEmptyString(content)) {
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    try {
                        kjCountDown = gson.fromJson(content, KJCountDown.class);
                        if (kjCountDown != null) {
                            //??????????????????
                            qishu = kjCountDown.getQishu();
                            if(StringMyUtil.isEmptyString(qishu)){
                                race_head3_tv1.setText("- - - ");
                                race_head3_tv2.setText("- - - ");
                                fenpan_tv.setVisibility(View.VISIBLE);
                            }else {
                                fenpan_tv.setVisibility(View.GONE);
                                endtime = Long.parseLong(DateUtil.dateToStamp(kjCountDown.getStoptimestr()));
                                //     localtime = System.currentTimeMillis();
                                if (!StringMyUtil.isEmptyString(kjCountDown.getTqtime())) {
                                    tqtime = (long) kjCountDown.getTqtime() * 1000;
                                }
                                sjcha = SharePreferencesUtil.getLong(RaceActivity.this, "shijiancha", 0l);
                                timeCount = 1L;
                                lastqishu = kjCountDown.getLastqishu();
                                race_head3_tv1.setText(qishu + Utils.getString(R.string. ???));
                                handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            race_head3_tv2.setVisibility(View.VISIBLE);
                                            race_time_loading_img.setVisibility(View.GONE);
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
                race_head3_tv2.setVisibility(View.GONE);
                race_time_loading_img.setVisibility(View.VISIBLE);
            }
        });
        if (isWaitopen) {
            //TODO ???????????? ???????????????/?????????????????????
            if (runnableRequestOpen != null) {
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, 0);
            if (runnableRandom != null) {
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);
//            Utils.logE("isWaitopen:", "" + isWaitopen + Utils.getString(R.string.,???????????? ???????????????/?????????????????????));
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
                        raceLottery = gson.fromJson(content, RaceLottery.class);
                        Utils.logE("content", content);
                        if (raceLottery != null) {
                            lotteryqishu = raceLottery.getRaceLotterylist().get(0).getLotteryqishu();
                        }
                        if (!StringMyUtil.isEmptyString(qishu) && !StringMyUtil.isEmptyString(lastqishu) && !StringMyUtil.isEmptyString(lotteryqishu) && raceLottery != null) {
                            //???????????????????????????
                            if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu.substring(lotteryqishu.length() - 2, lotteryqishu.length()))) {
                                isWaitopen = false;
                                race_head3_tv3.setText(lotteryqishu + " "+Utils.getString(R.string.???????????????));
                                Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult1");
                                //  updateRightLotteryNo();
                                List<String> list = new ArrayList<String>();
                                list = Arrays.asList(raceLottery.getRaceLotterylist().get(0).getLotteryNo().split(","));
                                kjList.clear();
                                for (String bean : list) {
                                    kjList.add(qius[Integer.parseInt(bean) - 1]);
                                }
                                race_head3_iv1.setImageResource(kjList.get(0));
                                race_head3_iv2.setImageResource(kjList.get(1));
                                race_head3_iv3.setImageResource(kjList.get(2));
                                race_head3_iv4.setImageResource(kjList.get(3));
                                race_head3_iv5.setImageResource(kjList.get(4));
                                race_head3_iv6.setImageResource(kjList.get(5));
                                race_head3_iv7.setImageResource(kjList.get(6));
                                race_head3_iv8.setImageResource(kjList.get(7));
                                race_head3_iv9.setImageResource(kjList.get(8));
                                race_head3_iv10.setImageResource(kjList.get(9));
                            } else {
                                if (Long.parseLong(qishu) - 1 == Long.parseLong(lotteryqishu)) {
                                    race_head3_tv3.setText(lotteryqishu + " "+Utils.getString(R.string.???????????????));
                                    Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult2");
                                    isWaitopen = false;
                                    //    updateRightLotteryNo();
                                    List<String> list = Arrays.asList(raceLottery.getRaceLotterylist().get(0).getLotteryNo().split(","));
                                    kjList.clear();
                                    for (String bean : list) {
                                        kjList.add(qius[Integer.parseInt(bean) - 1]);
                                    }
                                    race_head3_iv1.setImageResource(kjList.get(0));
                                    race_head3_iv2.setImageResource(kjList.get(1));
                                    race_head3_iv3.setImageResource(kjList.get(2));
                                    race_head3_iv4.setImageResource(kjList.get(3));
                                    race_head3_iv5.setImageResource(kjList.get(4));
                                    race_head3_iv6.setImageResource(kjList.get(5));
                                    race_head3_iv7.setImageResource(kjList.get(6));
                                    race_head3_iv8.setImageResource(kjList.get(7));
                                    race_head3_iv9.setImageResource(kjList.get(8));
                                    race_head3_iv10.setImageResource(kjList.get(9));
                                } else {
                                    race_head3_tv3.setText((Long.parseLong(lotteryqishu) + 1) + " "+Utils.getString(R.string.???????????????));  //????????????
                                    isWaitopen = true;
                                    Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult3");
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
                //    showtoast(Utils.getString(R.string.??????????????????));
            }
        });
    }

    // ????????????pop,???????????? ???
    private void initAllPop() {
        //????????????pop????????????
        customPopupWindow.initClassfyPop(this, this);
        //????????????pop???????????? typename????????????????????????button
        customPopupWindow.initClassfyData(this, typename);
//        ????????????pop
        customPopupWindow.initMenuPop(this, this);
        //?????? ???????????? typename:????????? game:??????game  tyoe_id: ??????type_id
        //Context context, final String typename, final int game, final int type_id
        customPopupWindow.toBetNote(this, typename, Integer.parseInt(game), Integer.parseInt(type_id));
        //?????? ????????????
        customPopupWindow.toInvestCenter(this);
        //??????????????????
        customPopupWindow.tovVipCenter(this);
        //?????? ???????????? type_id: ???????????????typ_id  lotteryClassId: ?????????????????????id
        customPopupWindow.toOpenResult(this, Integer.parseInt(type_id), Integer.parseInt(game));
        //game ????????????(??????????????????????????????????????????????????????) typename: ?????????: ???????????????????????????????????????
        customPopupWindow.initGameRule(this, Integer.parseInt(game), typename, 0,false);
        //??????????????????pop
        customPopupWindow.showGameRulePop(this,false);
        ;
        customPopupWindow.initRaceTodayResultPop(this, Integer.parseInt(game), Integer.parseInt(type_id),false);
        //??????????????????
        customPopupWindow.toTwoChangLongAty(this, Integer.parseInt(game), Integer.parseInt(type_id));
        //??????????????????
        customPopupWindow.toTodayWinLose(this, Integer.parseInt(game), Integer.parseInt(type_id));
        //??????????????????
        customPopupWindow.toOnlineKf(this);
    }

    private void initCountDownEndPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.countdown_end_pop, null);
        lastQiShuTv = view.findViewById(R.id.last_qishu);
        newQiShuTv = view.findViewById(R.id.new_qihao);
        countDownEndSure = view.findViewById(R.id.countdown_pop_sure);
        CountDownEndPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        CountDownEndPop.setAnimationStyle(R.style.popAlphaanim0_5);
        CountDownEndPop.setTouchable(true);//??????????????????
        CountDownEndPop.setOnDismissListener(() -> ProgressDialogUtil.darkenBackground(RaceActivity.this, 1f));
        countDownEndSure.setOnClickListener(v -> CountDownEndPop.dismiss());
    }

    private void clickView() {
        caizhong_head1_back.setOnClickListener(v -> onBackPressed());

        ll_caizhong_head1.setOnClickListener(v -> {
            //????????????
            caizhong_head1_iv.startAnimation(rotateAnimation);
            caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_down);
            int x = 0;
            List<String> list = new ArrayList<String>();
            if (!isOfficial) {  //TODO ???????????? popwindow
                list.add(getString(R.string.??????));
                list.add(getString(R.string.?????????));
                list.add(Utils.getString(R.string.??????10???));
            } else {
                //TODO ???????????? popwindow
                try {
                    if (newplayModel!=null){
                        for (int i = 0; i < newplayModel.getPlayRuleListOne().size(); i++) {
                            list.add(newplayModel.getPlayRuleListOne().get(i).getName());
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            String str = caizhong_head1_title.getText().toString();
            for (int i = 0; i < list.size(); i++) {
                if (str.equals(list.get(i))) {
                    x = i;
                    break;
                }
            }
            if (isOfficial){
                if (newplayModel!=null){
                    showhead1popup(x);
                }
            }else {
                if (raceGroup!=null){
                    showhead1popup(x);
                }
            }

        });

        k3_tab_confirm.setOnClickListener(v -> {
            int count = 0;
            count = StrUtil.isclickCal(isClickList);
            if (count == 0) {
                showToast(Utils.getString(R.string.???????????????));
                return;
            }
            if (StringMyUtil.isEmptyString(k3_tab_money.getText().toString()) && !MoneyUtils.isMoney(k3_tab_money.getText().toString())) {
                showToast(Utils.getString(R.string.???????????????));
                return;
            }
            List<TouzhuModel> touzhuList = new ArrayList<>();
            for (int i = 0; i < isClickList.size(); i++) {
                if (isClickList.get(i).getIsclick()) {
                    for (RaceGroupBean bean : raceGroupBeanAllList) {
                        if (isClickList.get(i).getId().equals(String.valueOf(bean.getId()))) {
                            TouzhuModel touzhuModel = new TouzhuModel();
                            touzhuModel.setGroupname(bean.getGroupname());
                            touzhuModel.setName(bean.getName());
                            touzhuModel.setId(String.valueOf(bean.getId()));
                            touzhuModel.setMoney(k3_tab_money.getText().toString());
                            touzhuList.add(touzhuModel);
                        }
                    }
                }
            }
            Intent intent = new Intent();
            intent.setClass(RaceActivity.this, TouzhuActivity.class);
            intent.putExtra("touzhuList", (Serializable) touzhuList);
            intent.putExtra("isClickList", (Serializable) isClickList);
            intent.putExtra("game", game);
            intent.putExtra("type_id", type_id);
            intent.putExtra("money", k3_tab_money.getText().toString());
            intent.putExtra("qishu", qishu);
            intent.putExtra("index", "");
            intent.putExtra("ma", "");
            startActivityForResult(intent, ReqTouzhu);

        });

        k3_tab_reset.setOnClickListener(v -> {
            if(raceGroupBeanAllList.size()==0){
                return;
            }
            if (!isOfficial) {  //TODO 2019-7-20 ???????????? ??????
                if (k3_tab_reset.getText().toString().equals(Utils.getString(R.string.??????))) {
                    k3_tab_reset.setText(R.string.??????);
                    //  isClickMap.clear();
                    isClickList.clear();
                    for (RaceGroupBean bean : raceGroupBeanAllList) {
                        //   isClickMap.put(String.valueOf(bean.getId()), false);
                        StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                    }
                    raceParentAdapter.notifyDataSetChanged();
                    k3_tab_money.setText("");
                    k3_tab_num.setText("0");
                } else {
                    k3_tab_reset.setText(Utils.getString(R.string.??????));
                    List<String> idList = new ArrayList<>();
                    if (caizhong_head1_title.getText().toString().equals(getString(R.string.??????))) {
                        for (RaceGroupBean bean : raceGroupBeanList1) {
                            idList.add(String.valueOf(bean.getId()));
                        }
                    } else if (caizhong_head1_title.getText().toString().equals(getString(R.string.?????????))) {
                        for (RaceGroupBean bean : raceGroupBeanList2) {
                            idList.add(String.valueOf(bean.getId()));
                        }
                    } else {
                        if(raceGroupBeanList3==null){
                            return;
                        }
                        for (RaceGroupBean bean : raceGroupBeanList3) {
                            idList.add(String.valueOf(bean.getId()));
                        }
                    }
                    Random random = new Random();
                    if(idList.size()==0){
                        return;
                    }
                    String id = idList.get(random.nextInt(idList.size()));
                    for (int i = 0; i < isClickList.size(); i++) {
                        if (id.equals(isClickList.get(i).getId())) {
                            StrUtil.isclickReplace(isClickList, isClickList.get(i).getId(), true);
                        }
                    }
                    int position = 0;
                    if (index_xy == 0) {
                        List<String> positionList = new ArrayList<>();
                        //{Utils.getString(R.string.?????????),Utils.getString(R.string.??????),Utils.getString(R.string.??????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????)};
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.??????));
                        positionList.add(getString(R.string.??????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        for (int i = 0; i < raceGroupBeanList1.size(); i++) {
                            if (id.equals(String.valueOf(raceGroupBeanList1.get(i).getId()))) {
                                for (int j = 0; j < positionList.size(); j++) {
                                    if (positionList.get(j).equals(raceGroupBeanList1.get(i).getXgroupname())) {
                                        position = j;
                                    }
                                }
                            }
                        }
                    }
                    if (index_xy == 1) {
                        List<String> positionList = new ArrayList<>();
                        positionList.add(getString(R.string.???????????????));  //????????????
                        for (int i = 0; i < raceGroupBeanList2.size(); i++) {
                            if (id.equals(String.valueOf(raceGroupBeanList2.get(i).getId()))) {
                                for (int j = 0; j < positionList.size(); j++) {
                                    if (positionList.get(j).equals(raceGroupBeanList2.get(i).getXgroupname())) {
                                        position = j;
                                    }
                                }
                            }
                        }
                    }
                    if (index_xy == 2) {
                        List<String> positionList = new ArrayList<>();
                        //{Utils.getString(R.string.?????????),Utils.getString(R.string.??????),Utils.getString(R.string.??????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????)};
                        positionList.add(getString(R.string.??????));
                        positionList.add(getString(R.string.??????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        positionList.add(getString(R.string.?????????));
                        for (int i = 0; i < raceGroupBeanList3.size(); i++) {
                            if (id.equals(String.valueOf(raceGroupBeanList3.get(i).getId()))) {
                                for (int j = 0; j < positionList.size(); j++) {
                                    if (positionList.get(j).equals(raceGroupBeanList3.get(i).getXgroupname())) {
                                        position = j;
                                    }
                                }
                            }
                        }
                    }
                    xy_race_recyclerview.smoothScrollToPosition(position);
                    raceParentAdapter.notifyDataSetChanged();
                    k3_tab_num.setText("1");
                }
            }
        });

        ll_caizhong_head2_left.setOnClickListener(v -> customPopupWindow.showClassfyPop(ll_caizhong_head2_left, RaceActivity.this));
        ll2_race_head3.setOnClickListener(v -> customPopupWindow.initRaceTodayResultData(RaceActivity.this, Integer.parseInt(game), Integer.parseInt(type_id), ll2_race_head3));
        caizhong_head1_menu.setOnClickListener(v -> customPopupWindow.showMenuPop(caizhong_head1_menu, RaceActivity.this));
        ll_caizhong_head2_right.setOnClickListener(v -> {
            isOfficial = !isOfficial;
            if (isOfficial) {
                if (!isopenOffice.equals("0")){
                    ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.????????????));
                    ll_xy_race_recycler.setVisibility(View.GONE);
                    ll_touzhu_xy.setVisibility(View.GONE);
                    ll_gf_race_recycler.setVisibility(View.VISIBLE);
                    ll_touzhu_gf.setVisibility(View.VISIBLE);
                    if (newplayModel == null) {
                        initData();
                    } else {
                        showcaizhong();
                    }
                }else {
                    showToast(Utils.getString(R.string.???????????????));
                }

            } else {
                ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.????????????));
                ll_xy_race_recycler.setVisibility(View.VISIBLE);
                ll_touzhu_xy.setVisibility(View.VISIBLE);
                ll_gf_race_recycler.setVisibility(View.GONE);
                ll_touzhu_gf.setVisibility(View.GONE);

                if (raceGroup == null) {
                    initData();
                } else {
                    isClickList.clear();
                    for (int i = 0; i < raceGroupBeanAllList.size(); i++) {
                        StrUtil.isclickAdd(isClickList, String.valueOf(raceGroupBeanAllList.get(i).getId()), false);
                    }
                    showcaizhong();
                }

            }
            Utils.logE("isOfficial", "" + isOfficial);

        });
        ll_caizhong_head2_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMoney();
                Animation animation = AnimationUtils.loadAnimation(RaceActivity.this, R.anim.rotate_anim);
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
                if (!TextUtils.isEmpty(s) && s.length() > 8) {
                    //?????????????????????????????????
                    s.delete(8, k3_tab_money.getSelectionEnd());
                }
            }
        });
        //TODO ?????????????????? 2019-7-17
        tv_reload.setOnClickListener(v -> {
            initData();
            initCountDownEndPop();//??????????????????pop
            ReqCountdown();  //?????????
            onResume();
        });
        tv_touzhu_yuan.setOnClickListener(v -> {
            tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_yuan_press));
            tv_touzhu_yuan.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.white));
            tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_jiao_unpress));
            tv_touzhu_jiao.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.textgray));
            danwei = 1.00;
            BigDecimal odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
            BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
            BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
            totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));
            BigDecimal totalmoney = times.multiply(new BigDecimal(tv_touzhu_num.getText().toString())).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
            totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            tv_touzhu_money.setText(String.valueOf(totalmoney));
        });
        tv_touzhu_jiao.setOnClickListener(v -> {
                    tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_yuan_unpress));
                    tv_touzhu_yuan.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.textgray));
                    tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_jiao_press));
                    tv_touzhu_jiao.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.white));
                    danwei = 0.10;
                    BigDecimal odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
                    BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
                    BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
                    totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                    tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));

                    BigDecimal totalmoney = times.multiply(new BigDecimal(tv_touzhu_num.getText().toString())).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
                    totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                    tv_touzhu_money.setText(String.valueOf(totalmoney));
                }
        );
        tv_touzhu_gf_jian.setOnClickListener(v -> {
                    String StrTimes = tv_touzhu_gf_times.getText().toString();
                    int times = Integer.parseInt(StrTimes);
                    times--;
                    tv_touzhu_gf_times.setText(String.valueOf(times));
                    if (times < 1) {
                        tv_touzhu_gf_times.setText("1");
                    }
                }
        );
        tv_touzhu_gf_jia.setOnClickListener(v -> {
            String StrTimes = tv_touzhu_gf_times.getText().toString();
            int times = Integer.parseInt(StrTimes);
            times++;
            if (times > 10000) {
                tv_touzhu_gf_times.setText("10000");
            }
            tv_touzhu_gf_times.setText(String.valueOf(times));
        });
        tv_touzhu_gf_times.setSelection(tv_touzhu_gf_times.getText().toString().length());
        tv_touzhu_gf_times.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //??????EditText????????????????????????????????????????????????????????????
                tv_touzhu_gf_times.setSelection(tv_touzhu_gf_times.getText().length());
                if (!TextUtils.isEmpty(s) && (Integer.parseInt(s.toString()) > 10000)) {
                    //?????????????????????????????????
                    showToast(getString(R.string.??????10000???));
                    tv_touzhu_gf_times.setText("10000");
                    //    s.delete(4, tv_touzhu_gf_times.getSelectionEnd());
                }
                if (s.length() == 0 || TextUtils.isEmpty(s)) {
                    tv_touzhu_gf_times.setText("1");
                }
                //TODO ?????? ????????????
                BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
                BigDecimal totalmoney = times.multiply(new BigDecimal(tv_touzhu_num.getText().toString())).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
                totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                tv_touzhu_money.setText(String.valueOf(totalmoney));
                if (playRuleListThreeBean != null) {
                    BigDecimal odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
                    BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
                    totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
                    tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));
                }
            }
        });

        tv_touzhu_jixuan.setOnClickListener(v -> {
            if (tv_touzhu_jixuan.getText().toString().equals(Utils.getString(R.string.??????))) {
                tv_touzhu_jixuan.setText(Utils.getString(R.string.??????));
                tv_touzhu_gf_times.setText("1");
                tv_touzhu_num.setText("0");
                tv_touzhu_money.setText("0.00");
                danwei = 1.00;
                tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_yuan_press));
                tv_touzhu_yuan.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.white));
                tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_jiao_unpress));
                tv_touzhu_jiao.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.textgray));

                // isClickMap.clear();
                for (int i = 0; i < choicefourList.size(); i++) {
                    for (int j = 0; j < 6; j++) {
                        B[i][j] = 0;
                    }
                }
                isClickList.clear();
                for (int i = 0; i < choicefourList.size(); i++) {
                    for (int j = 1; j < 11; j++) {
                        //  isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                        StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                    }
                }

                raceOfficialPAdapter.notifyDataSetChanged();
            } else {
                tv_touzhu_jixuan.setText(Utils.getString(R.string.??????));
                tv_touzhu_gf_times.setText("1");
                tv_touzhu_num.setText("1");
                tv_touzhu_money.setText("2.00");
                danwei = 1.00;
                tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_yuan_press));
                tv_touzhu_yuan.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.white));
                tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_jiao_unpress));
                tv_touzhu_jiao.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.textgray));

                //  isClickMap.clear();
                for (int i = 0; i < choicefourList.size(); i++) {
                    for (int j = 0; j < 6; j++) {
                        B[i][j] = 0;
                    }
                }
                isClickList.clear();
                for (int i = 0; i < choicefourList.size(); i++) {
                    for (int j = 1; j < 11; j++) {
                        //  isClickMap.put(String.valueOf(choicefourList.get(i).getId()*100+j),false);
                        StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                    }
                }
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        A1[i][j] = 0;
                    }
                }
                if (index_gf == 0) {
                    List<Long> idList = new ArrayList<>();
                    for (int i = 0; i < choicefourList.size(); i++) {
                        for (int j = 1; j < 11; j++) {
                            idList.add(choicefourList.get(i).getId() * 100 + j);
                        }
                    }
                    Random random = new Random();
                    long id = idList.get(random.nextInt(idList.size()));
                    int position = 0;
                    for (int i = 0; i < choicefourList.size(); i++) {
                        if (id / 100 == choicefourList.get(i).getId()) {
                            position = i;
                        }
                    }
                    for (IsClickModel isClickModel : isClickList) {
                        if (isClickModel.getId().equals(String.valueOf(id))) {
                            //   isClickMap.replace(entry.getKey(), true);
                            StrUtil.isclickReplace(isClickList, String.valueOf(id), true);
                            for (int i = 0; i < choicefourList.size(); i++) {
                                if (id / 100 == choicefourList.get(i).getId()) {
                                    //  int j = Integer.parseInt(entry.getKey()) % 100 - 1;
                                    int j = (int) (id % 100 - 1);
                                    A1[i][j] = 1;
                                }
                            }
                        }
                    }
                    gf_race_recyclerview.smoothScrollToPosition(position);
                    raceOfficialPAdapter.notifyDataSetChanged();
                } else {
                    List<Long> idList = new ArrayList<>();
                    for (long i = 1; i < 11; i++) {
                        idList.add(i);
                    }
                    idList = MyMathUtils.getRandomNum(idList, choicefourList.size());
                    for (int i = 0; i < idList.size(); i++) {

                        for (IsClickModel isClickModel : isClickList) {
                            if (isClickModel.getId().equals(String.valueOf(choicefourList.get(i).getId() * 100 + idList.get(i)))) {
                                StrUtil.isclickReplace(isClickList, isClickModel.getId(), true);
                                int j = (int) (idList.get(i) - 1);
                                A1[i][j] = 1;
                            }
                        }
                    }
                    raceOfficialPAdapter.notifyDataSetChanged();
                }
            }
        });


        touzhu_gf_confirm.setOnClickListener(v -> {
            long nowTime = System.currentTimeMillis();
            if (nowTime - mLastClickTime > TIME_INTERVAL) {
                if (Integer.parseInt(tv_touzhu_num.getText().toString()) < 1) {
                    showToast(Utils.getString(R.string.???????????????));
                    return;
                }

                if (Double.parseDouble(tv_touzhu_money.getText().toString()) < 1) {
                    showToast(Utils.getString(R.string.??????)+Utils.getString(R.string.???????????????1???));
                    return;
                }

                String groupname = typename + "-" + titleList.get(index_gf);
                String danjia = String.valueOf(2 * danwei);
                String totalnum = tv_touzhu_num.getText().toString();
                String totalmoney = tv_touzhu_money.getText().toString();
                String str = new String();

                for (int i = 0; i < choicefourList.size(); i++) {
                    int m = 0;
                    for (int n = 0; n < 10; n++) {
                        m = (int) (m + A1[i][n]);
                    }
                    //TODO 2019-7-23  ????????????A1[i][]????????????????????????
                    if (m > 0) {
                        str = str + "\n" + choicefourList.get(i).getName() + ":";
                        for (int j = 0; j < 10; j++) {
                            if (A1[i][j] == 1) {
                                str = str + String.valueOf(j + 1) + ",";
                            }
                        }
                        str = str.substring(0, str.length() - 1);
                    }

                }


                final BetDialog betDialog = new BetDialog(RaceActivity.this);
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
                    if (index_gf == 0) {
                        betModelList.clear();
                        for (int i = 0; i < 10; i++) {
                            int m = 0;
                            for (int n = 0; n < 10; n++) {
                                m = (int) (m + A1[i][n]);
                            }
                            if (m > 0) {
                                GfBetModel betModel = new GfBetModel();
                                String name = new String();
                                for (int j = 0; j < 10; j++) {
                                    if (A1[i][j] == 1) {
                                        name = name + String.valueOf(j + 1) + ",";
                                    }
                                }
                                name = name.substring(0, name.length() - 1);
                                betModel.setName(name);
                                betModel.setRuleTwoId(choicefourList.get(i).getId());
                                betModelList.add(betModel);
                            }
                        }
                    } else {
                        betModelList.clear();
                        for (int i = 0; i < choicefourList.size(); i++) {
                            GfBetModel betModel = new GfBetModel();
                            String name = new String();
                            for (int j = 0; j < 10; j++) {
                                if (A1[i][j] == 1) {
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
                    ProgressDialogUtil.show(RaceActivity.this, Utils.getString(R.string.?????????)+"...");
                    MyRequest.ReqNewplayBet(RaceActivity.this, game, type_id, qishu, betList, 0, new MyRequest.MyRequestListener1() {
                        @Override
                        public void success1(String content) {
                            Utils.logE(getString(R.string.??????????????????), content);
                            ProgressDialogUtil.stop(RaceActivity.this);
                            Gson gson = new GsonBuilder().serializeNulls().create();
                            try {
                                State state = gson.fromJson(content, State.class);
                                if (!StringMyUtil.isEmptyString(state) && state.getStatus().equals("success")) {
                                    showToast(Utils.getString(R.string.????????????));
                                    //  isClickMap.clear();
                                    isClickList.clear();
                                    for (int i = 0; i < choicefourList.size(); i++) {
                                        for (int j = 1; j < 11; j++) {
                                            //   isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                                            StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                                        }
                                    }
                                    for (int i = 0; i < choicefourList.size(); i++) {
                                        for (int j = 0; j < 6; j++) {
                                            B[i][j] = 0;
                                        }

                                    }
                                    raceOfficialPAdapter.notifyDataSetChanged();
                                    tv_touzhu_gf_times.setText("1");
                                    tv_touzhu_num.setText("0");
                                    tv_touzhu_money.setText("0.00");
                                    tv_touzhu_jixuan.setText(Utils.getString(R.string.??????));
                                    requestMoney();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void failed1(String failMessage) {
                            ProgressDialogUtil.stop(RaceActivity.this);
                            showToast(Utils.getString(R.string.????????????)+"\n" + failMessage);
                        }
                    });
                });
                betDialog.setNoOnclickListener(() -> betDialog.dismiss());
                betDialog.show();
                betDialog.setCanceledOnTouchOutside(false); //??????????????????
            }
        });
        fenpan_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Utils.getString(R.string.??????????????????));
            }
        });

    }

    private void requestMoney() {
        ReqMemberMoney(RaceActivity.this, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                Utils.logE("content:", "" + content);
                 memberMoney = JSONObject.parseObject(content, MemberMoney.class);
                ll_caizhong_head2_center_tv.setText(MoneyUtils.parseMoey(String.valueOf(RaceActivity.this.memberMoney.getMemberMoney().getAmount())));
            }

            @Override
            public void failed(String content) {
            }
        });
    }

    public void showhead1popup(int x) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.layout_head1popuprecycler, null, false);//??????????????????
        head1recyclerview = vPopupWindow.findViewById(R.id.head1_recyclerview);
        head1popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //??????????????????
        addBackground();
        View view = findViewById(R.id.rl_head1);
        head1popupWindow.showAsDropDown(view);
        loadgrideDate(x);
    }

    private void addBackground() {
        // ????????????????????????
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;//???????????????
        getWindow().setAttributes(lp);
        //dismiss???????????????
        head1popupWindow.setOutsideTouchable(true);
        head1popupWindow.setOnDismissListener(() -> {
            //????????????
            caizhong_head1_iv.startAnimation(rotateAnimation);
            caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_up);
            lp.alpha = 1f;
            getWindow().setAttributes(lp);
        });
    }

    private void loadgrideDate(int x) {
        if (!isOfficial) {
            titleList.clear();
            titleList.add(getString(R.string.??????));
            titleList.add(getString(R.string.?????????));
            titleList.add(Utils.getString(R.string.??????10???));
        } else {
            //TODO ??????
            titleList.clear();
            for (NewplayModel.PlayRuleListOneBean bean : ruleoneList) {
                titleList.add(bean.getName());
            }
        }


        recyclerViewGridAdapter = new RecyclerViewGridAdapter(this, titleList, x);
        head1recyclerview.setAdapter(recyclerViewGridAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        head1recyclerview.setLayoutManager(gridLayoutManager);

        recyclerViewGridAdapter.setOnItemClickListener((View view, int position) -> {
            caizhong_head1_title.setText(titleList.get(position));
            if (head1popupWindow.isShowing()) {
                head1popupWindow.dismiss();
            }
            if (!isOfficial) {
                index_xy = position;
            } else {
                index_gf = position;
            }
            showcaizhong();

        });
    }

    public void OnGfListener(List<IsClickModel> isClickList, int[][] B) {

        //TODO 2019-7-23  ?????? ??????????????????
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                A1[i][j] = 0;
            }
        }
        //  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
        //   if (entry.getValue()) {
        for (IsClickModel isClickModel : isClickList) {
            if (isClickModel.getIsclick()) {
                for (int i = 0; i < choicefourList.size(); i++) {
                    if (Integer.parseInt(/*entry.getKey()*/isClickModel.getId()) / 100 == choicefourList.get(i).getId()) {
                        int j = Integer.parseInt(/*entry.getKey()*/isClickModel.getId()) % 100 - 1;
                        A1[i][j] = 1;
                    }
                }
            }
        }
        int num = 0;
        if (index_gf == 0) {
            for (int i = 0; i < 10; i++) {
                int m = 0;
                for (int n = 0; n < 10; n++) {
                    m = (int) (m + A1[i][n]);
                }
                if (m > 0) {
                    for (int j = 0; j < 10; j++) {
                        if (A1[i][j] == 1) {
                            num++;
                        }
                    }
                }
            }
        }
        if (index_gf == 1) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 10; k++) {
                        for (int l = 0; l < 10; l++) {
                            for (int m = 0; m < 10; m++) {
                                if (i != j && i != k && i != l && i != m && j != k && j != l && j != m && k != l && k != m && l != m) {
                                    num = (int) (num + A1[0][i] * A1[1][j] * A1[2][k] * A1[3][l] * A1[4][m]);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (index_gf == 2) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 10; k++) {
                        for (int l = 0; l < 10; l++) {
                            if (i != j && i != k && i != l && j != k && j != l && k != l) {
                                num = (int) (num + A1[0][i] * A1[1][j] * A1[2][k] * A1[3][l]);
                            }
                        }
                    }
                }
            }
        }
        if (index_gf == 3) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 10; k++) {
                        if (i != j && i != k && j != k) {
                            num = (int) (num + A1[0][i] * A1[1][j] * A1[2][k]);
                        }
                    }
                }
            }
        }
        if (index_gf == 4) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i != j) {
                        num = (int) (num + A1[0][i] * A1[1][j]);
                    }
                }
            }
        }
        if (index_gf == 5) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (A1[i][j] == 1) {
                        num++;
                    }
                }
            }
        }
        //   raceOfficialPAdapter.notifyDataSetChanged();
        //TODO 2019-7-24

        tv_touzhu_num.setText("" + num);
        BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
        BigDecimal totalmoney = times.multiply(new BigDecimal(String.valueOf(num))).multiply(new BigDecimal(danwei)).multiply(new BigDecimal(String.valueOf(2)));
        totalmoney = totalmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
        tv_touzhu_money.setText(String.valueOf(totalmoney));
        if (num != 0) {
            tv_touzhu_jixuan.setText(Utils.getString(R.string.??????));
        } else {
            tv_touzhu_jixuan.setText(Utils.getString(R.string.??????));
        }
        raceOfficialPAdapter.notifyDataSetChanged();

    }

    //   ????????????????????? ????????????????????????    //?????????adapter   notifyDataSetChanged   ???  isClickMap ???activity ?????????adapter????????????
    public void OnClickListener(List<IsClickModel> isClickList) {

        if (!isOfficial) {
            //TODO 2019-7-23  ?????? ??????????????????
            int num = 0;
            num = StrUtil.isclickCal(isClickList);
            k3_tab_num.setText("" + num);
            if (num != 0) {
                k3_tab_reset.setText(Utils.getString(R.string.??????));
            } else {
                k3_tab_reset.setText(Utils.getString(R.string.??????));
            }
        } //TODO ????????????????????????


    }

    //TODO ????????????  ?????? 3????????? ????????? ??????10???  ??????6???
    private void showcaizhong() {
        if (!isOfficial) {
            titleList.clear();
            titleList.add(getString(R.string.??????));
            titleList.add(getString(R.string.?????????));
            titleList.add(Utils.getString(R.string.??????10???));
            caizhong_head1_title.setText(titleList.get(index_xy));

            if (index_xy == 0) {
                List<String> data2 = new ArrayList<>();
                //{Utils.getString(R.string.?????????),Utils.getString(R.string.??????),Utils.getString(R.string.??????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????)};
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.??????));
                data2.add(getString(R.string.??????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                xy_race_recyclerview.setLayoutManager(layoutManager);
                xy_race_recyclerview.setFocusableInTouchMode(false);
                raceParentAdapter = new RaceParentAdapter(this, data2, raceGroupBeanList1, 24, true, isClickList);
                xy_race_recyclerview.setAdapter(raceParentAdapter);
            }
            if (index_xy == 1) {
                List<String> data2 = new ArrayList<>();
                //{Utils.getString(R.string.??????????????????)};
                data2.add(getString(R.string.???????????????));  //????????????
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                xy_race_recyclerview.setLayoutManager(layoutManager);
                xy_race_recyclerview.setFocusableInTouchMode(false);
                raceParentAdapter = new RaceParentAdapter(this, data2, raceGroupBeanList2, 24, true, isClickList);
                xy_race_recyclerview.setAdapter(raceParentAdapter);
            }
            if (index_xy == 2) {
                List<String> data2 = new ArrayList<>();
                //{Utils.getString(R.string.??????),Utils.getString(R.string.??????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????)};
                data2.add(getString(R.string.??????));
                data2.add(getString(R.string.??????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));
                data2.add(getString(R.string.?????????));

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                xy_race_recyclerview.setLayoutManager(layoutManager);
                xy_race_recyclerview.setFocusableInTouchMode(false);
                raceParentAdapter = new RaceParentAdapter(this, data2, raceGroupBeanList3, 5, false, isClickList);
                xy_race_recyclerview.setAdapter(raceParentAdapter);
            }
        } else {
            titleList.clear();
            for (int i = 0; i < ruleoneList.size(); i++) {
                titleList.add(ruleoneList.get(i).getName());
            }
            caizhong_head1_title.setText(titleList.get(index_gf));
            NewplayModel.PlayRuleListOneBean playRuleListOneBean = ruleoneList.get(index_gf);
            NewplayModel.PlayRuleListTwoBean playRuleListTwoBean = new NewplayModel.PlayRuleListTwoBean();
            playRuleListThreeBean = new NewplayModel.PlayRuleListThreeBean();
            for (int i = 0; i < ruletwoList.size(); i++) {
                if (ruletwoList.get(i).getParentId() == playRuleListOneBean.getId()) {
                    playRuleListTwoBean = ruletwoList.get(i);
                }
            }
            for (int i = 0; i < rulethreeList.size(); i++) {
                if (rulethreeList.get(i).getParentId() == playRuleListTwoBean.getId()) {
                    playRuleListThreeBean = rulethreeList.get(i);
                }
            }
            choicefourList.clear();
            for (int i = 0; i < rulefourList.size(); i++) {
                if (rulefourList.get(i).getPlayRuleId() == playRuleListThreeBean.getId()) {
                    choicefourList.add(rulefourList.get(i));
                }
            }
            //  isClickMap.clear();
            isClickList.clear();
            for (int i = 0; i < choicefourList.size(); i++) {
                for (int j = 1; j < 11; j++) {
                    //     isClickMap.put(String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                    StrUtil.isclickAdd(isClickList, String.valueOf(choicefourList.get(i).getId() * 100 + j), false);
                }
            }


            for (int i = 0; i < choicefourList.size(); i++) {
                for (int j = 0; j < 6; j++) {
                    B[i][j] = 0;
                }

            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            gf_race_recyclerview.setLayoutManager(layoutManager);
            gf_race_recyclerview.setFocusableInTouchMode(false);
            raceOfficialPAdapter = new RaceOfficialPAdapter(this, B, choicefourList, playRuleListThreeBean, isClickList);
            gf_race_recyclerview.setAdapter(raceOfficialPAdapter);

            //TODO 2019-7-23 ??????index_gf   ??????????????????
            tv_touzhu_gf_times.setText("1");
            tv_touzhu_jixuan.setText(Utils.getString(R.string.??????));
            tv_touzhu_num.setText("0");
            tv_touzhu_money.setText("0.00");
            //????????????
            danwei = 1.00;
            tv_touzhu_yuan.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_yuan_press));
            tv_touzhu_yuan.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.white));
            tv_touzhu_jiao.setBackground(ContextCompat.getDrawable(RaceActivity.this,R.drawable.shape_jiao_unpress));
            tv_touzhu_jiao.setTextColor(ContextCompat.getColor(RaceActivity.this,R.color.textgray));
            // ??????????????????
            BigDecimal odds = new BigDecimal(String.valueOf(playRuleListThreeBean.getOdds()));
            BigDecimal times = new BigDecimal(tv_touzhu_gf_times.getText().toString());
            BigDecimal totalZjmoney = odds.multiply(times).multiply(new BigDecimal(danwei));
            totalZjmoney = totalZjmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
            tv_touzhu_zjmoney.setText(String.valueOf(totalZjmoney));
        }
    }

    //???????????????????????????????????? ????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK?????????????????????activity?????????????????????????????????Standard activity result:
        // operation succeeded. ????????????-1
        if (requestCode == ReqTouzhu) {
            //    int three = data.getIntExtra("three", 0);
            //????????????????????????????????????
            if (resultCode == 102) {
                // isClickMap.clear();
                isClickList.clear();
                for (RaceGroupBean bean : raceGroupBeanAllList) {
                    //   isClickMap.put(String.valueOf(bean.getId()), false);
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                }
                raceParentAdapter.notifyDataSetChanged();
                k3_tab_num.setText("" + 0);
                k3_tab_money.setText("");
                k3_tab_reset.setText(Utils.getString(R.string.??????));
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
                int mHour = (int) ((timeCount / 1000) / (60 * 60));  //  ???3600 ??????  ????????????
                int mMin = (int) (((timeCount / 1000) % (60 * 60)) / 60);//  ???3600 ????????????60 ??????????????????
                int mSecond = (int) ((timeCount / 1000) % 60); //  ???60 ??????  ??????????????????
                String str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
                race_head3_tv2.setText(str_time);
                //   timeCount--;
                if (timeCount <= 1) {
                    timeCount = 0L;
                    race_head3_tv2.setText(Utils.getString(R.string.?????????));
                    if (CountDownEndPop != null) {
                        lastQiShuTv.setText(qishu);
                        newQiShuTv.setText((Long.parseLong(qishu) + 1) + "");
                        if (!isFinishing() && CountDownEndPop != null) {
                            CountDownEndPop.showAtLocation(ll1_race_head3, Gravity.CENTER, 0, 0);
                        }
                        ProgressDialogUtil.darkenBackground(RaceActivity.this, 0.5f);
                    }
                    isWaitopen = true;
                    ReqCountdown();
                    Utils.logE("isWaitopen:", "" + isWaitopen + ",runnableTime");
                }
            }
            handler.postDelayed(runnableTime, 300);
        }
    };

    //????????????(??????????????????????????????)
    Runnable runnableZj = new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name", SharePreferencesUtil.getString(RaceActivity.this, "nickname", ""));
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
                    } catch (Exception e) {
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
    Runnable runnableRandom = new Runnable() {
        @Override
        public void run() {
            if (isWaitopen) {
                if (!StringMyUtil.isEmptyString(qishu)) {
                    long waitOpenQiShu = Long.parseLong(qishu) - 1;
                    race_head3_tv3.setText(waitOpenQiShu + " "+Utils.getString(R.string.???????????????));
                    List<String> list = new ArrayList<String>();
                    kjList.clear();
                    Random random = new Random();
                    kjList.add(qius[random.nextInt(10)]);
                    //   kjRecyclerAdapter.notifyDataSetChanged();
                    race_head3_iv1.setImageResource(kjList.get(0));
                    race_head3_iv2.setImageResource(kjList.get(0));
                    race_head3_iv3.setImageResource(kjList.get(0));
                    race_head3_iv4.setImageResource(kjList.get(0));
                    race_head3_iv5.setImageResource(kjList.get(0));
                    race_head3_iv6.setImageResource(kjList.get(0));
                    race_head3_iv7.setImageResource(kjList.get(0));
                    race_head3_iv8.setImageResource(kjList.get(0));
                    race_head3_iv9.setImageResource(kjList.get(0));
                    race_head3_iv10.setImageResource(kjList.get(0));
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
                //TODO ?????????
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

    @Override
    public void onItemClick(View view, int position, LotteryClassModel lotteryClassModel) {
        int type_id = lotteryClassModel.getType_id();
        String isopenOffice = lotteryClassModel.getIsopenOffice();
        String isStart = lotteryClassModel.getIsStart();
        String typename = lotteryClassModel.getTypename();
        int game = lotteryClassModel.getGame();

        ToBetAtyUtils.toBetActivity(RaceActivity.this, game, typename, type_id, isopenOffice, isStart);
        finish();
    }

    @Override
    public void onMenuClicked(View view) {
    }

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
        //??????????????????
        //   timetv.stopTime();
        super.onDestroy();
/*        handler.removeCallbacks(runnableRandom);
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
