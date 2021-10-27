package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.LuckFram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.TouzhuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.Happy10Adapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SureBetPopAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.bet_activity_adpter.BetChoosePlayAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetChoosePalyModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy10Model;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SureBetPopMeldol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CustomPopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LuckFarmBetActivity extends BaseActivity implements View.OnClickListener, CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener, Happy10Adapter.OnRecyclerViewItemClickListener {
    private TextView hourText;//倒计时小时数
    private TextView minutesText;//倒计时分钟数
    private TextView secondsText;//倒计时秒数
    private long countTime;//倒计时时间
    private TextView nowQishuText;//当前期数
    private TextView lastQiShuText;//上一期期数(用于显示在滚动开奖结果的上方,并且用于判断是否需要滚动(lastQiShuText与每10秒请求的开奖结果中第一个数据的lotteryqishu相同时,表示已经开奖,需要停止滚动))
    private String nowQishu;//当前期数
    private ImageView rotateImg;//余额刷新图片
    private boolean isWaitOpen = true;//是否在等待开奖(初始值必须为true,否则所有需要判断isWaitOpen判断的runnable都不会循环操作,导致开奖号码一直停在第一次赋值的时候)
    private int num = 1;//用于判断是否是第一次请求倒计时接口
    private LinearLayout showClassfyLinear;//点击弹出彩种分类pop
    private TextView rightMenu;//点击弹出右侧菜单pop
    private LinearLayout todayOpenResultLinear;//点击弹出今日开奖结果
    private LinearLayout choosePlayType;//点击弹出玩法类型pop
    private LinearLayout popTargetLinear;//玩法类型pop定位控件
    /*
   玩法类型pop
    */
    private PopupWindow choosePlayTypePop;
    private LinearLayout openResultLinear;
    private RecyclerView choosePlayRecy;
    private BetChoosePlayAdapter betChoosePlayAdapter;
    private ArrayList<BetChoosePalyModel> betChoosePalyModelArrayList = new ArrayList<>();
    private int popPosition = 0;
    //开奖结果滚动view
    private MyCornerTextView happy10NumTv1;
    private MyCornerTextView happy10NumTv3;
    private MyCornerTextView happy10NumTv2;
    private MyCornerTextView happy10NumTv4;
    private MyCornerTextView happy10NumTv5;
    private MyCornerTextView happy10NumTv6;
    private MyCornerTextView happy10NumTv7;
    private MyCornerTextView happy10NumTv8;

    //底部投注信息
    private TextView betNum;//已选中多少注
    private EditText amountEdit;//投注金额输入框
    private TextView betButton;//下注按钮
    private TextView randomButton;//机选按钮


    //两面页面下, 每一组的数据容器
    private ArrayList<Happy10Model> bothOne = new ArrayList<>();
    private ArrayList<Happy10Model> bothTwo = new ArrayList<>();
    private ArrayList<Happy10Model> bothThree = new ArrayList<>();
    private ArrayList<Happy10Model> bothFour = new ArrayList<>();
    private ArrayList<Happy10Model> bothFive = new ArrayList<>();
    private ArrayList<Happy10Model> bothSix = new ArrayList<>();
    private ArrayList<Happy10Model> bothSeven = new ArrayList<>();
    private ArrayList<Happy10Model> bothEight = new ArrayList<>();
    private ArrayList<ArrayList<Happy10Model>> bothAllList = new ArrayList<>();//两面下的所有数据容器
    //第一到第八球页面下的数据容器
    private ArrayList<Happy10Model> ballNumList = new ArrayList<>();
    //连码页面下的数据容器
    private ArrayList<Happy10Model> liamaList = new ArrayList<>();

    /*
   recycleView相关控件
    */
    private ArrayList<Happy10Model> selecterList = new ArrayList<>();//当前选中item容器
    private RecyclerView mRecy;
    private Happy10Adapter happy10Adapter;
    private ArrayList<Happy10Model> happy10ModelList = new ArrayList<>();

    /*
    下注清单pop
     */
    private PopupWindow sureBetPop;
    private TextView lotteryNameTv;//彩票名
    private TextView qishuTv;//期数
    private TextView betAmoumtTv;//投注金额(每注多少)
    private TextView allBetNumTv;//投注总数
    private TextView allBetAmountTv;//投注总金额
    private TextView sureButton;
    private TextView cancelButton;
    private RecyclerView sureBetRecy;
    private SureBetPopAdapter sureBetPopAdapter;
    private ArrayList<SureBetPopMeldol> sureBetPopMeldolArrayList = new ArrayList<>();

    private TextView memberMoneyText;//用户余额
    private TextView pcddTypeText;//选择栏的玩法名
    private ImageView chooseImg;//玩法选择pop弹出时,需要旋转的图片

    private TextView name;//彩票分类栏的彩票name

    private LinearLayout xingyongLinear;//信用官方玩法切换

    private PopupWindow CountDownEndPop;//倒计时结束时的pop
    private TextView lastQiShuTv;  //截止的期数
    private TextView newQiShuTv; //新的期数
    private TextView countDownEndSure;//倒计时结束pop的确定按钮

    private TextView actionBarBack;//返回键

    private long millionSeconds;//倒计时结束时间
    private long time;//服务器时间
    private long shijiancha;//服务器时间与本地时间差
    private String tqtime;//提前时间

    private Long user_id;
    private int type_id;
    private int game;
    private String typename;

    private ArrayList<MyCornerTextView> myCornerTextViewArrayLi = new ArrayList<>();
    private List<String> openResultList;//开奖结果num容器

    //    private PopupWindow fengpanPop;//封盘后需要弹出的pop
    private String[] bothTileList = {Utils.getString(R.string.第一球), Utils.getString(R.string.第二球), Utils.getString(R.string.第三球), Utils.getString(R.string.第四球), Utils.getString(R.string.第五球), Utils.getString(R.string.第六球), Utils.getString(R.string.第七球), Utils.getString(R.string.第八球)};//两面 页面下每一组的groupName
    private RadioButton lianmaOneBtn;
    private RadioButton lianmaTwoBtn;
    private RadioButton lianmaThreeBtn;
    private RadioButton lianmaFourBtn;
    private RadioButton lianmaFiveBtn;
    private RadioGroup lianmaGroup;
    private String lianmaType;//请求连码数据时,需要传的参数(在每次点击连码类型的时候,会将text设置为点击的type)
    private ArrayList<String> lianMaIdList = new ArrayList<>();

    private long jgTime;
    private String todayZJ;

    public static final int ReqTouzhu = 101;
    private TextView stopTv;
    private LinearLayout timeLinear;
    private LinearLayout timeLoadingLinear;
    private ConstraintLayout loadingLinear;
    private String isopenOffice;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private ArrayList<String> list = new ArrayList<>();
    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    private TextView is_stop_tv;
    private String isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy10_bet);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getIntentData();
        Utils.saveLotteryHistory(game, type_id);
        bindView();//绑定控件
        initChoosePlayTypePop();
        initAllPop();//投注记录 开奖结果 彩票分类等,相关初始化
//        initFenpangPop(); //封盘pop
        getCountTime();//请求倒计时
        initRecycle();//recycleView相关
        getMoney();//获取资金信息
        initSureBetPop();//投注清单pop
        initCountDownEndPop();//倒计时结束的pop
        requestRule("");//请求下注列表

    }

    private void getIntentData() {
        user_id = SharePreferencesUtil.getLong(LuckFarmBetActivity.this, "user_id", 0l);
         /*
        请求开奖结果,跳转投注记录等等需要的参数
         */
        Intent intent = getIntent();
        isStart = intent.getStringExtra("isStart");
        type_id = intent.getIntExtra("type_id", 0);
        game = intent.getIntExtra("game", 0);
        typename = intent.getStringExtra("typename");
        isopenOffice = intent.getStringExtra("isopenOffice");
        if (StringMyUtil.isEmptyString(isopenOffice)) {
            isopenOffice = "";
        }
        for (int i = 1; i < 10; i++) {
            list.add(i + "");
        }
    }

    @Override
    protected void init() {
    }

    /*
    封盘时的pop相关
     */
//    private void initFenpangPop() {
//        View view = LayoutInflater.from(this).inflate(R.layout.fengpang_popwindow,null);
//        fengpanPop=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
//        fengpanPop.setAnimationStyle(R.style.popAlphaanim0_5);
//        fengpanPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this,1f);
//            }
//        });
//    }
    /*
    recyeleView 布局相关配置
     */
    private void initRecycle() {
        mRecy.setAdapter(happy10Adapter);//设置spanSize之前要先设置适配器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 60);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //返回position对应的item所占的size
            @Override
            public int getSpanSize(int position) {
//                if(betChoosePalyModelArrayList.get(0).getStatus()==1){
                if (popPosition == 0) {
                    if (position == 0 || position == 11 || position == 22 || position == 33 || position == 44 || position == 53 || position == 62 || position == 71) {
                        return 60;
                    } else if (position == 45 || position == 46 || position == 47 || position == 48 || position == 49 || position == 50 || position == 51 || position == 52
                            || position == 54 || position == 55 || position == 56 || position == 57 || position == 58 || position == 59 || position == 60 || position == 61
                            || position == 63 || position == 64 || position == 65 || position == 66 || position == 67 || position == 68 || position == 69 || position == 70
                            || position == 72 || position == 73 || position == 74 || position == 75 || position == 76 || position == 77 || position == 78 || position == 79) {
                        return 15;
                    } else {
                        return 12;
                    }
//                }else if(betChoosePalyModelArrayList.get(1).getStatus()==1||betChoosePalyModelArrayList.get(2).getStatus()==1||betChoosePalyModelArrayList.get(3).getStatus()==1||betChoosePalyModelArrayList.get(4).getStatus()==1){
                } else if (popPosition == 1 || popPosition == 2 || popPosition == 3 || popPosition == 4) {
                    if (position == 0) {
                        return 60;
                    } else if (position == 21 || position == 22 || position == 23 || position == 24 || position == 25 || position == 26 || position == 27 || position == 28 || position == 29 || position == 30 || position == 31 || position == 32) {
                        return 15;
                    } else {
                        return 12;
                    }

                } else if (popPosition == 5 || popPosition == 6 || popPosition == 7 || popPosition == 8) {
                    if (position == 0) {
                        return 60;
                    } else if (position == 21 || position == 22 || position == 23 || position == 24 || position == 25 || position == 26 || position == 27 || position == 28 || position == 29 || position == 30 || position == 31 || position == 32) {
                        return 15;
                    } else if (position == 33 || position == 34 || position == 35) {
                        return 20;
                    } else {
                        return 12;
                    }
                } else {
                    if (position == 0) {
                        return 60;
                    } else {
                        return 12;
                    }
                }
            }


        });
        mRecy.setLayoutManager(gridLayoutManager);
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//添加间距
        happy10Adapter.setOnItemClickListener(this);
    }

    /*
    玩法类型pop
     */
    private void initChoosePlayTypePop() {
        choosePlayTypePop.setAnimationStyle(R.style.popAlphaanim0_5);
        choosePlayTypePop.setTouchable(true);//响应内部点击
        choosePlayTypePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = LuckFarmBetActivity.this.getWindow().getAttributes();
                if (!CountDownEndPop.isShowing() && !sureBetPop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this, 1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(LuckFarmBetActivity.this, R.anim.img_rotate_end_anim);
                ;
                chooseImg.startAnimation(loadAnimation);
            }

        });
    }

    /**
     * 请求投注列表数据
     *
     * @param lianmaType 用于连码界面设置为第一个item的ruleType和连码页面item的groupName(在连码界面每次点击玩法类型时传入)
     */
    private void requestRule(final String lianmaType) {
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + getGame() + type_id, "");
        loadingLinear.setVisibility(View.VISIBLE);
        if (StringMyUtil.isEmptyString(jsonStr)) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id", user_id);
            data.put("type_id", type_id);
            data.put("game", getGame());
            Utils.docking(data, RequestUtil.REQUEST_01farmall, -1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    SharePreferencesUtil.putString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + getGame() + type_id, content);
                    handRuleJson(content, lianmaType);
                }

                @Override
                public void failed(MessageHead messageHead) {
                    loadingLinear.setVisibility(View.GONE);
                }
            });
        } else {
            handRuleJson(jsonStr, lianmaType);
        }

    }

    private void handRuleJson(String content, String lianmaType) {
        loadingLinear.setVisibility(View.GONE);
        happy10ModelList.clear();
        for (int i = 0; i < bothAllList.size(); i++) {
            bothAllList.get(i).clear();
        }
        ballNumList.clear();
        liamaList.clear();
        JSONObject jsonObject1 = JSONObject.parseObject(content);
        JSONArray rulelistAll = jsonObject1.getJSONArray("RulelistAll");
        for (int i = 0; i < rulelistAll.size(); i++) {
            JSONObject jsonObject = rulelistAll.getJSONObject(i);
            String rulelist1 = jsonObject.getString("Rulelist");
            JSONArray rulelist = JSONObject.parseArray(rulelist1);
            Integer isboth = jsonObject.getInteger("isboth");
            Integer balled = jsonObject.getInteger("balled");
            Integer type = jsonObject.getInteger("type");
            if (isboth == 1) {//两面的数据
                for (int j = 0; j < bothTileList.length; j++) {
                    String title = bothTileList[j];
                    for (int k = 0; k < rulelist.size(); k++) {
                        JSONObject jsonObject2 = rulelist.getJSONObject(k);
                        String name = jsonObject2.getString("name");
                        BigDecimal odds = jsonObject2.getBigDecimal("odds");
                        String id = jsonObject2.getString("id");
                        String groupname = jsonObject2.getString("groupname");
                        if (title.equals(groupname)) {
                            Happy10Model happy8Model = new Happy10Model(name, odds + "", id, Utils.getString(R.string.两面) + "-" + groupname);
                            bothAllList.get(j).add(happy8Model);
                        }
                    }
                }
            } else if (isboth == 0 && type == 0 && (balled == 1 || balled == 2 || balled == 3 || balled == 4 || balled == 5 || balled == 6 || balled == 7 || balled == 8)) {//第1-8球的数据
                for (int j = 0; j < bothTileList.length; j++) {
                    String title = bothTileList[j];
                    for (int k = 0; k < rulelist.size(); k++) {
                        JSONObject jsonObject2 = rulelist.getJSONObject(k);
                        Integer group_id = jsonObject2.getInteger("group_id");
                        String name = jsonObject2.getString("name");
                        BigDecimal odds = jsonObject2.getBigDecimal("odds");
                        String id = jsonObject2.getString("id");
                        String groupname = jsonObject2.getString("groupname");
                        if (title.equals(groupname)) {
                            Happy10Model happy10Medol = new Happy10Model(name, odds + "", id, title + "-" + groupname);
                            ballNumList.add(happy10Medol);
                        }
                    }
                }
            } else if (isboth == 0 && balled == 8 && type == 2) {
                for (int k = 0; k < rulelist.size(); k++) {
                    JSONObject jsonObject2 = rulelist.getJSONObject(k);
//                                Integer group_id = jsonObject2.getInteger("id");
                    String typeName = jsonObject2.getString("name");
                    BigDecimal odds = jsonObject2.getBigDecimal("odds");
                    String name = "";
                    String id = jsonObject2.getString("id");
                    String groupname = jsonObject2.getString("groupname");
                    for (int j = 1; j < 21; j++) {
                        name = j + "";
                        Happy10Model happy8Model = new Happy10Model(name, odds + "", id, Utils.getString(R.string.连码) + "-" + lianmaType);
                        happy8Model.setLianmaId(j + "");
                        happy8Model.setLianmaType(typeName);
                        liamaList.add(happy8Model);
                    }
                }
            }
        }
        if (popPosition == 0) {
            Happy10Model happy8Medol = new Happy10Model(Utils.getString(R.string.第一球), "", "", Utils.getString(R.string.两面) + "-" + Utils.getString(R.string.第一球));
            happy10ModelList.add(happy8Medol);
            ArrayList<String> idList = new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            for (int i = 0; i < bothOne.size(); i++) {
                Happy10Model happy8Medol1 = bothOne.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.第二球), "", "", Utils.getString(R.string.两面) + "-" + Utils.getString(R.string.第二球)));
            for (int i = 0; i < bothTwo.size(); i++) {
                Happy10Model happy8Medol1 = bothTwo.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.第三球), "", "", Utils.getString(R.string.两面) + "-" + Utils.getString(R.string.第三球)));
            for (int i = 0; i < bothThree.size(); i++) {
                Happy10Model happy8Medol1 = bothThree.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);

            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.第四球),"","",getString(R.string.两面第四球)));
            for (int i = 0; i < bothFour.size(); i++) {
                Happy10Model happy8Medol1 = bothFour.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.第五球),"","",getString(R.string.两面第五球)));

            for (int i = 0; i < bothFive.size(); i++) {
                Happy10Model happy8Medol1 = bothFive.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.第六球),"","",getString(R.string.两面第六球)));

            for (int i = 0; i < bothSix.size(); i++) {
                Happy10Model happy8Medol1 = bothSix.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }

            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.第七球),"","",getString(R.string.两面第七球)));

            for (int i = 0; i < bothSeven.size(); i++) {
                Happy10Model happy8Medol1 = bothSeven.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.第八球),"","",getString(R.string.两面第八球)));

            for (int i = 0; i < bothEight.size(); i++) {
                Happy10Model happy8Medol1 = bothEight.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if (idList.contains(rule_id)) {
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
        } else if (popPosition == 1 || popPosition == 2 || popPosition == 3 || popPosition == 4 || popPosition == 5 || popPosition == 6 || popPosition == 7 || popPosition == 8) {

            ArrayList<String> idList = new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            String type = betChoosePalyModelArrayList.get(popPosition).getType();
            happy10ModelList.add(new Happy10Model(type, "", "", type + Utils.getString(R.string.横杠第一球)));
            for (int i = 0; i < ballNumList.size(); i++) {
                Happy10Model happy8Medol = ballNumList.get(i);
                String rule_id = happy8Medol.getRule_id();
                String groupname = happy8Medol.getGroupname();
                String betType = happy8Medol.getBetType();
                if (idList.contains(rule_id)) {
                    happy8Medol.setStatus(1);
                }
                if (groupname.equals(type + "-" + type)) {
                    happy10ModelList.add(happy8Medol);
                }
            }
        } else {

            ArrayList<String> idList = new ArrayList<>();

            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            happy10ModelList.add(new Happy10Model(lianmaType,"","",getString(R.string.连码横杠)+typename));
            for (int i = 0; i <liamaList.size() ; i++) {

                Happy10Model happy8Medol = liamaList.get(i);
                String rule_id = happy8Medol.getRule_id();
                String lianmaId = happy8Medol.getLianmaId();
                if (idList.contains(rule_id) && lianMaIdList.contains(lianmaId + rule_id)) {//连码页面下,所有item的id都一样,如果只用id判断切换时是否选中,会导致点击一次后,切换回来时,所有item都设置为选中,所有根据lianmaId和id一起判断
                    happy8Medol.setStatus(1);
                }
                if (lianmaType.equals(happy8Medol.getLianmaType())) {//点击 任选二 任选三...等,会将点击的textView传入,根据传入的值取出对应的数据
                    happy10ModelList.add(happy8Medol);
                }
            }

        }
        happy10Adapter.notifyDataSetChanged();
    }

    /*
    请求玩家资金信息
     */
    private void getMoney() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", user_id);
        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                BigDecimal amount = memberMoney.getBigDecimal("amount");
                memberMoneyText.setText(amount + "");
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    /*
    倒计时结束后弹出的pop相关设置
     */
    private void initCountDownEndPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.countdown_end_pop, null);
        lastQiShuTv = view.findViewById(R.id.last_qishu);
        newQiShuTv = view.findViewById(R.id.new_qihao);
        countDownEndSure = view.findViewById(R.id.countdown_pop_sure);
        countDownEndSure.setOnClickListener(this);
        CountDownEndPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        CountDownEndPop.setAnimationStyle(R.style.popAlphaanim0_5);
        CountDownEndPop.setTouchable(true);//响应内部点击
        CountDownEndPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (choosePlayTypePop.isShowing() && !customPopupWindow.menuPop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.sscTodayOpenResultPop.isShowing() && !customPopupWindow.gameRulePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this, 1f);
                }
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://请求倒计时接口结束后,再请求开奖结果数据(避免请求开奖结果数据根据期数判断是否开奖了时,会报空指针异常)
                    initOpenResult();//开奖结果数据处理
                    break;
                case 2:
                    getMoney();
                    break;
            }
        }
    };

    private void bindView() {
        is_stop_tv = findViewById(R.id.is_stop_tv);
        if (isStart.equals("0")) {
            is_stop_tv.setVisibility(View.VISIBLE);
        } else {
            is_stop_tv.setVisibility(View.GONE);
        }
        loadingLinear = findViewById(R.id.loading_linear);
        timeLinear = findViewById(R.id.time_linear);
        timeLoadingLinear = findViewById(R.id.time_load_linear);
        stopTv = findViewById(R.id.stop_textview);
        stopTv.setOnClickListener(this);
        actionBarBack = findViewById(R.id.bet_actionbar_back);
        actionBarBack.setOnClickListener(this);

        hourText = findViewById(R.id.hour);
        minutesText = findViewById(R.id.minutes);
        secondsText = findViewById(R.id.seconds);

        openResultLinear = findViewById(R.id.ssc_open_resule_linear);
        happy10NumTv1 = findViewById(R.id.num_one);
        happy10NumTv2 = findViewById(R.id.num_two);
        happy10NumTv3 = findViewById(R.id.num_three);
        happy10NumTv4 = findViewById(R.id.num_four);
        happy10NumTv5 = findViewById(R.id.num_five);
        happy10NumTv6 = findViewById(R.id.num_six);
        happy10NumTv7 = findViewById(R.id.num_seven);
        happy10NumTv8 = findViewById(R.id.num_eight);
        myCornerTextViewArrayLi.add(happy10NumTv1);
        myCornerTextViewArrayLi.add(happy10NumTv2);
        myCornerTextViewArrayLi.add(happy10NumTv3);
        myCornerTextViewArrayLi.add(happy10NumTv4);
        myCornerTextViewArrayLi.add(happy10NumTv5);
        myCornerTextViewArrayLi.add(happy10NumTv6);
        myCornerTextViewArrayLi.add(happy10NumTv7);
        myCornerTextViewArrayLi.add(happy10NumTv8);
        for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
            myCornerTextViewArrayLi.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayLi.get(i).setCornerSize(50);
        }
        betNum = findViewById(R.id.bet_num);
        amountEdit = findViewById(R.id.amount_edit);
        betButton = findViewById(R.id.bet_button);
        randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(this);
        betButton.setOnClickListener(this);

        rotateImg = findViewById(R.id.rotate_image);
        rotateImg.setOnClickListener(this);
        nowQishuText = findViewById(R.id.now_qishu);
        lastQiShuText = findViewById(R.id.last_qishu);
        showClassfyLinear = findViewById(R.id.show_classfy_linear);
        showClassfyLinear.setOnClickListener(this);
        rightMenu = findViewById(R.id.right_menu);
        rightMenu.setOnClickListener(this);
        todayOpenResultLinear = findViewById(R.id.today_open_result);
        todayOpenResultLinear.setOnClickListener(this);
        choosePlayType = findViewById(R.id.choose_play_type);
        choosePlayType.setOnClickListener(this);
        popTargetLinear = findViewById(R.id.choose_pop_target);

        mRecy = findViewById(R.id.happy10_bet_recycle);
        happy10Adapter = new Happy10Adapter(happy10ModelList, selecterList);

        name = findViewById(R.id.name);
        name.setText(typename);
        /*
        玩法选择poprecycleView相关
         */
        View view = LayoutInflater.from(this).inflate(R.layout.happy10_play_type_pop, null);
        choosePlayTypePop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        choosePlayRecy = view.findViewById(R.id.happy_10_choose_play_recy);
        betChoosePlayAdapter = new BetChoosePlayAdapter(betChoosePalyModelArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        choosePlayRecy.setLayoutManager(gridLayoutManager);
        choosePlayRecy.setAdapter(betChoosePlayAdapter);
        BetChoosePalyModel betChoosePalyModel = new BetChoosePalyModel(Utils.getString(R.string.两面));
        betChoosePalyModel.setStatus(1);
        betChoosePalyModelArrayList.add(betChoosePalyModel);
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第一球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第二球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第三球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第四球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第五球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第六球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第七球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.第八球)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.连码)));

        //将两面页面下的所有数据容器放到一个容器里(请求玩法列表的时候,需要根据下标取出容器,并存放数据(每次请求玩法列表的时候,清空))
        bothAllList.add(bothOne);
        bothAllList.add(bothTwo);
        bothAllList.add(bothThree);
        bothAllList.add(bothFour);
        bothAllList.add(bothFive);
        bothAllList.add(bothSix);
        bothAllList.add(bothSeven);
        bothAllList.add(bothEight);

        //连码页面下的按钮
        lianmaGroup = findViewById(R.id.lianma_radioGroup);
        lianmaOneBtn = findViewById(R.id.lianma_one);
        lianmaTwoBtn = findViewById(R.id.lianma_two);
        lianmaThreeBtn = findViewById(R.id.lianma_three);
        lianmaFourBtn = findViewById(R.id.lianma_four);
        lianmaFiveBtn = findViewById(R.id.lianma_five);
        lianmaOneBtn.setOnClickListener(this);
        lianmaTwoBtn.setOnClickListener(this);
        lianmaThreeBtn.setOnClickListener(this);
        lianmaFourBtn.setOnClickListener(this);
        lianmaFiveBtn.setOnClickListener(this);
        lianmaOneBtn.performClick();

        /**
         *玩法选择pop的点击事件
         */
        betChoosePlayAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == betChoosePalyModelArrayList.size() - 1) {//点击连码按钮,显示连码玩法选择的button,并清空选中容器
                    lianmaGroup.setVisibility(View.VISIBLE);
                    clearSelector();//取消选择
                    happy10Adapter.notifyDataSetChanged();
                } else {//点击除了 Utils.getString(R.string.连码)外的按钮,判断选中的容器中是否包含连码页面的item,如果有,说明是从连码界面跳转,同样清空选中的容器(直接clear,不用remove.
                    // 因为每次跳转到 连码 界面,其他页面的数据已经被清空了,即从 连码 界面跳转过来时,选中容器中只会有 连码 的item)
                    lianmaGroup.setVisibility(View.GONE);
                    for (int i = 0; i < selecterList.size(); i++) {
                        Happy10Model happy8Model = selecterList.get(i);
                        if (happy8Model.getGroupname().contains(Utils.getString(R.string.连码))) {//只要有一个medel的groupname包含Utils.getString(R.string.连码),直接清空选中的容器
//                            selecterList.remove(happy8Model);
                            clearSelector();
                            randomButton.setText(Utils.getString(R.string.机选));
                            happy10Adapter.notifyDataSetChanged();
                        }
                    }
                }
                //每次点击都将当前position传出(需要判断当前是选中哪种玩法时, 根据popPosition判断)
                popPosition = position;
                requestRule(lianmaType);
                choosePlayTypePop.dismiss();
                pcddTypeText.setText(betChoosePalyModelArrayList.get(popPosition).getType());
                if (mRecy != null) {
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
            }
        });

        xingyongLinear = findViewById(R.id.xingyong_play);
        if (isopenOffice.equals("0")) {
            xingyongLinear.setVisibility(View.INVISIBLE);
            xingyongLinear.setClickable(false);
        } else {
            xingyongLinear.setVisibility(View.VISIBLE);
            xingyongLinear.setClickable(true);
        }
        xingyongLinear.setOnClickListener(this);

        pcddTypeText = findViewById(R.id.pcdd_type);
        chooseImg = findViewById(R.id.choose_img);
//        playTypeText=findViewById(R.id.play_type_text);
        memberMoneyText = findViewById(R.id.member_money);




        /*
        开奖结果样式设置
         */

        for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
            myCornerTextViewArrayLi.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayLi.get(i).setCornerSize(50);
        }
    }

    /*
    开奖结果  右侧菜单相关
     */
    private void initAllPop() {
        //彩票分类pop相关设置
        customPopupWindow.initClassfyPop(this, this);
        //彩票分类pop数据请求 typename用于默认选中某个button
        customPopupWindow.initClassfyData(this, typename);
//        右侧菜单pop
        customPopupWindow.initMenuPop(this, this);
        //跳转 投注记录 typename:彩票名 game:彩票game  tyoe_id: 彩票type_id
        customPopupWindow.toBetNote(this, typename, game, type_id);
        //跳转 充值中心
        customPopupWindow.toInvestCenter(this);
        //跳转会员中心
        customPopupWindow.tovVipCenter(this);
        //跳转 开奖结果 type_id: 当前彩票的typ_id  lotteryClassId: 当前彩种分类的id
        customPopupWindow.toOpenResult(this, type_id, game);
        //game 彩票类型(用于判断需要跳转到哪种类型的游戏规则) typename: 彩票名: 用于设置规则说明中的彩票名
        customPopupWindow.initGameRule(this, game, typename, 0, false);
        //弹出游戏规则pop
        customPopupWindow.showGameRulePop(this, false);
        ;
        //开奖结果pop视图初始化
        customPopupWindow.initHappy10TodayResult(this, game, type_id, false);
        //跳转两面长龙
        customPopupWindow.toTwoChangLongAty(this, game, type_id);
        //跳转今日输赢
        customPopupWindow.toTodayWinLose(this, game, type_id);
        //跳转在线客服
        customPopupWindow.toOnlineKf(this);

        jgTime = customPopupWindow.getJgTIme(game, type_id);

    }

    /*
    runnable 循环操作
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (runnableTime != null) {
            handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime, 1000);
        if (isWaitOpen) {
            if (runnableRandom != null) {
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);
//            System.out.println("gundong  isWait");
        }
        if (isWaitOpen) {
            if (runnableRequestOpen != null) {
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, jgTime);
//            System.out.println(Utils.getString(R.string.请求接口10s  isWait));
        }
//        handler.postDelayed(runnableBoolen,5000);
        if (runnableZj != null) {
            handler.removeCallbacks(runnableZj);
        }
        handler.postDelayed(runnableZj, 10000);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        float alpha = lp.alpha;
        if (alpha == 1f) {
            CountDownEndPop.dismiss();
        }
    }

    /*
      是否中奖(中奖后要更新资金信息)
       */
    Runnable runnableZj = new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name", SharePreferencesUtil.getString(LuckFarmBetActivity.this, "nickname", ""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    todayZJ = jsonObject.getString("todayZJ");
                    if (todayZJ.equals("1")) {
                        Message message = Message.obtain();
                        message.what = 2;
                        handler.sendMessage(message);
                    }

                }

                @Override
                public void failed(MessageHead messageHead) {

                }
            });
            handler.postDelayed(runnableZj, 2000);
        }
    };

    /*
    请求开奖结果
     */
    private void getCountTime() {
        showLoadingLinear();
        HashMap<String, Object> data = new HashMap<>();
        String domain = SharePreferencesUtil.getString(LuckFarmBetActivity.this, "domain", "");
        data.put("type_id", type_id);
        data.put("source", 2);
        Utils.docking(data, RequestUtil.REQUEST_03farm, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                String stoptimestr = jsonObject.getString("stoptimestr");//倒计时结束时间
                nowQishu = jsonObject.getString("qishu");//当前期数
                if (StringMyUtil.isEmptyString(nowQishu)) {//期数为空,表示封盘
//                    fengpanPop.showAtLocation(actionBarBack, Gravity.CENTER,0,0);
                    stopTv.setVisibility(View.VISIBLE);
                    ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this, 0.7f);
                    nowQishuText.setText(Utils.getString(R.string.-- -- -- 期));
                    lastQiShuText.setText("-- -- -- " + Utils.getString(R.string.期开奖号码));
                    hourText.setText("--");
                    minutesText.setText("--");
                    secondsText.setText("--");
                    handler.removeCallbacks(runnableRandom);
                    handler.removeCallbacks(runnableTime);
                    handler.removeCallbacks(runnableRequestOpen);
                    openResultLinear.setVisibility(View.INVISIBLE);
                } else {
//                    if(fengpanPop.isShowing()){
//                        fengpanPop.dismiss();
//                    }
                    stopTv.setVisibility(View.GONE);
                    if (openResultLinear.getVisibility() != View.VISIBLE) {
                        openResultLinear.setVisibility(View.VISIBLE);
                    }
                    if (runnableRandom == null) {
                        handler.postDelayed(runnableRandom, 150);
                    }
                    if (runnableTime == null) {
                        handler.postDelayed(runnableTime, 1000);
                    }
                    if (runnableRequestOpen == null) {
                        handler.postDelayed(runnableRequestOpen, jgTime);
                    }
                    tqtime = jsonObject.getString("tqtime");//封盘时间(倒计时需要减去封盘时间)
                    nowQishuText.setText(nowQishu + Utils.getString(R.string. 期));
                    lastQiShuText.setText((Long.parseLong(nowQishu) - 1) + " " + Utils.getString(R.string.期开奖号码));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//倒计时结束时间
//                        time = Long.parseLong(Utils.getFileData("time"));//服务器时间
                        long nowTime = System.currentTimeMillis();//当前时间
//                        shijiancha = time- nowTime;//服务器时间和当地时间差
                        shijiancha = SharePreferencesUtil.getLong(LuckFarmBetActivity.this, "shijiancha", 0l);//服务器时间和当地时间差
                        countTime = millionSeconds + shijiancha - nowTime - (Long.parseLong(tqtime) * 1000);
//                        if(num==1){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                showTimeLInear();
                            }
                        }, 600);
//                        }
                        /*else {
                            timeLoadingLinear.setVisibility(View.GONE);
                            timeLinear.setVisibility(View.VISIBLE);
                        }*/
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (num == 1) {//第一次请求倒计时数据时,才发送消息通知请求开奖结果(因为请求一次后,期数不会为空,可以直接请求开奖结果)
                        Message message = Message.obtain();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                    num++;
                }

            }

            @Override
            public void failed(MessageHead messageHead) {
                showToast(messageHead.getInfo());
                showTimeLInear();
            }
        });
    }

    private void showLoadingLinear() {
        if (isStart.equals("0")) {
            timeLoadingLinear.setVisibility(View.GONE);
            timeLinear.setVisibility(View.GONE);
            is_stop_tv.setVisibility(View.VISIBLE);
        } else {
            timeLoadingLinear.setVisibility(View.VISIBLE);
            timeLinear.setVisibility(View.GONE);
            is_stop_tv.setVisibility(View.GONE);
        }
    }

    private void showTimeLInear() {
        if (isStart.equals("0")) {
            is_stop_tv.setVisibility(View.VISIBLE);
            timeLoadingLinear.setVisibility(View.GONE);
            timeLinear.setVisibility(View.GONE);
        } else {
            timeLinear.setVisibility(View.VISIBLE);
            timeLoadingLinear.setVisibility(View.GONE);
            is_stop_tv.setVisibility(View.GONE);
        }
    }

    /*
         请求开奖结果
     */
    private void initOpenResult() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id", type_id);
        data.put("pageNo", 1);
        data.put("pageSize", 20);
        data.put("flag", 1);
        Utils.docking(data, RequestUtil.REQUEST_04farm, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray farmLotterylist = jsonObject1.getJSONArray("farmLotterylist");
                if (farmLotterylist.size() == 0) {
                    showToast(getString(R.string.开奖结果获取失败));
                    return;
                }
                JSONObject jsonObject = farmLotterylist.getJSONObject(0);
                String lotteryqishu = jsonObject.getString("lotteryqishu");
                if (!StringMyUtil.isEmptyString(nowQishu)) {
                    if (Long.parseLong(lotteryqishu) == (Long.parseLong(nowQishu) - 1)) {
                        isWaitOpen = false;
                        String lotteryNo = jsonObject.getString("lotteryNo");
                        String[] split = lotteryNo.split(",");
                        for (int i = 0; i < split.length; i++) {
                            String s = split[i];
                            if (list.contains(s)) {
                                split[i] = "0" + s;
                            }
                        }
                        openResultList = Arrays.asList(split);
                        for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
                            myCornerTextViewArrayLi.get(i).setText(openResultList.get(i));
                        }
                    } else {
                        isWaitOpen = true;
                    }
                }
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stop_textview:
                showToast(Utils.getString(R.string.该彩种已封盘));
                break;
            case R.id.rotate_image:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                ;
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            case R.id.show_classfy_linear://点击弹出彩票分类pop
                customPopupWindow.showClassfyPop(showClassfyLinear, this);
                break;
            case R.id.xingyong_play:
                showToast(getString(R.string.暂无该游戏官方玩法));
                break;
            case R.id.right_menu:
                customPopupWindow.showMenuPop(rightMenu, this);
                break;
            case R.id.today_open_result:
                customPopupWindow.initfarmTodayResultData(this, todayOpenResultLinear, type_id);
//                customPopupWindow.showHappy8TodayResultPop(todayOpenResultLinear,this);
                break;


            //actionbar中 点击弹出玩法选择pop  并设置图片的动画
            case R.id.choose_play_type:
                choosePlayTypePop.showAsDropDown(popTargetLinear, Gravity.BOTTOM, 0, 0);
                ProgressDialogUtil.darkenBackground(this, 0.5f);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                ;
                loadAnimation.setFillAfter(true);//动画结束后,保持结束时的角度
                chooseImg.startAnimation(loadAnimation);
                break;
                /*
                连码页面下的玩法选择点击事件
                 */
            case R.id.lianma_one:
                requestRule(lianmaOneBtn.getText().toString());
                lianmaType = lianmaOneBtn.getText().toString();
                clearSelector();
                break;
            case R.id.lianma_two:
                requestRule(lianmaTwoBtn.getText().toString());
                lianmaType = lianmaTwoBtn.getText().toString();
                clearSelector();//清空选择容器
                break;
            case R.id.lianma_three:
                requestRule(lianmaThreeBtn.getText().toString());
                lianmaType = lianmaThreeBtn.getText().toString();
                clearSelector();
                break;
            case R.id.lianma_four:
                requestRule(lianmaFourBtn.getText().toString());
                lianmaType = lianmaFourBtn.getText().toString();
                clearSelector();
                break;
            case R.id.lianma_five:
                requestRule(lianmaFiveBtn.getText().toString());
                lianmaType = lianmaFiveBtn.getText().toString();
                clearSelector();
                break;


            case R.id.random_button:
                int max = 0;
                if (lianmaGroup.getVisibility() != View.VISIBLE) { //不是连码界面,随机选中一个
                    if (randomButton.getText().toString().equals(Utils.getString(R.string.机选))) {
                        xingYongRandom();
                    } else {
                        for (int i = 0; i < happy10ModelList.size(); i++) {
                            happy10ModelList.get(i).setStatus(0);
                        }
                        clearSelector();
                    }
                } else {

                    //选中任选二或者选中选二连选
                    if (lianmaOneBtn.isChecked() || lianmaTwoBtn.isChecked()) {
                        if (randomButton.getText().toString().equals(Utils.getString(R.string.机选))) {
                            randomBet(happy10ModelList, 2);
                            betNum.setText(1 + "");
                            randomButton.setText(Utils.getString(R.string.重置));
                        } else {
                            for (int i = 0; i < happy10ModelList.size(); i++) {
                                happy10ModelList.get(i).setStatus(0);
                            }
                            clearSelector();
                        }
                    }
                    //选中任选三
                    else if (lianmaThreeBtn.isChecked()) {//
                        if (randomButton.getText().toString().equals(Utils.getString(R.string.机选))) {
                            randomBet(happy10ModelList, 3);
                            betNum.setText(1 + "");
                            randomButton.setText(Utils.getString(R.string.重置));
                        } else {
                            for (int i = 0; i < happy10ModelList.size(); i++) {
                                happy10ModelList.get(i).setStatus(0);
                            }
                            clearSelector();
                        }
                    }
                    //选中任选四
                    else if (lianmaFourBtn.isChecked()) {//


                        if (randomButton.getText().toString().equals(Utils.getString(R.string.机选))) {
                            randomBet(happy10ModelList, 4);
                            betNum.setText(1 + "");
                            randomButton.setText(Utils.getString(R.string.重置));
                        } else {
                            for (int i = 0; i < happy10ModelList.size(); i++) {
                                happy10ModelList.get(i).setStatus(0);
                            }
                            clearSelector();
                        }
                    }
                    //选中任选五
                    else {//

                        if (randomButton.getText().toString().equals(Utils.getString(R.string.机选))) {
                            randomBet(happy10ModelList, 5);
                            betNum.setText(1 + "");
                            randomButton.setText(Utils.getString(R.string.重置));
                        } else {
                            for (int i = 0; i < happy10ModelList.size(); i++) {
                                happy10ModelList.get(i).setStatus(0);
                            }
                            clearSelector();
                        }
                    }
                }
                for (int i = 0; i < selecterList.size(); i++) {
                    String rule_id = selecterList.get(i).getRule_id();
                    for (int j = 0; j < happy10ModelList.size(); j++) {
                        Happy10Model happy10Model = happy10ModelList.get(j);
                        if (happy10Model.getRule_id().equals(rule_id)) {
                            if (j > max) {
                                max = j;
                            }
                        }
                    }
                }
                happy10Adapter.notifyDataSetChanged();
                mRecy.getLayoutManager().scrollToPosition(max);
                break;
            case R.id.bet_button:
                if (Utils.isFastClick()) {
                    return;
                }
                int size = selecterList.size();
                String editText = amountEdit.getText().toString();
                if (stopTv.getVisibility() == View.VISIBLE) {
                    showToast(Utils.getString(R.string.当前彩种已封盘));
                } else if (lianmaGroup.getVisibility() == View.VISIBLE) {
                    if (lianmaOneBtn.isChecked() || lianmaTwoBtn.isChecked()) {
                        if (size < 2) {
                            showToast(Utils.getString(R.string.请选择玩法));
                        } else if (StringMyUtil.isEmptyString(editText)) {
                            showToast(Utils.getString(R.string.请输入下注金额));
                        } else {
                            /*betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //投注清单pop的数据处理
                            lotteryNameTv.setText(name.getText().toString());//彩票名
                            qishuTv.setText(nowQishu);//投注期数
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this,0.5f);
                            hintKbTwo();//隐藏软键盘*/
                            chechBet(true);
                        }
                    } else if (lianmaThreeBtn.isChecked()) {
                        if (size < 3) {
                            showToast(Utils.getString(R.string.请选择玩法));
                        } else if (StringMyUtil.isEmptyString(editText)) {
                            showToast(Utils.getString(R.string.请输入下注金额));
                        } else {
                            /*betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //投注清单pop的数据处理
                            lotteryNameTv.setText(name.getText().toString());//彩票名
                            qishuTv.setText(nowQishu);//投注期数
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this,0.5f);
                            hintKbTwo();//隐藏软键盘*/
                            chechBet(true);
                        }
                    } else if (lianmaFourBtn.isChecked()) {
                        if (size < 4) {
                            showToast(Utils.getString(R.string.请选择玩法));
                        } else if (StringMyUtil.isEmptyString(editText)) {
                            showToast(Utils.getString(R.string.请输入下注金额));
                        } else {
                            /*betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //投注清单pop的数据处理
                            lotteryNameTv.setText(name.getText().toString());//彩票名
                            qishuTv.setText(nowQishu);//投注期数
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this,0.5f);
                            hintKbTwo();//隐藏软键盘*/
                            chechBet(true);
                        }
                    } else {
                        if (size < 5) {
                            showToast(Utils.getString(R.string.请选择玩法));
                        } else if (StringMyUtil.isEmptyString(editText)) {
                            showToast(Utils.getString(R.string.请输入下注金额));
                        } else {
                            /*betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //投注清单pop的数据处理
                            lotteryNameTv.setText(name.getText().toString());//彩票名
                            qishuTv.setText(nowQishu);//投注期数
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this,0.5f);
                            hintKbTwo();//隐藏软键盘*/
                            chechBet(true);
                        }
                    }
                } else {
                    if (size == 0) {
                        showToast(Utils.getString(R.string.请选择玩法));
                    } else if (StringMyUtil.isEmptyString(editText)) {
                        showToast(Utils.getString(R.string.请输入下注金额));
                    } else {
                        /*betButton.setClickable(false);
                        ArrayList<Happy10Model> list = new ArrayList<>();
                        initSureBetPopData(list); //投注清单pop的数据处理
                        lotteryNameTv.setText(name.getText().toString());//彩票名
                        qishuTv.setText(nowQishu);//投注期数
                        allBetNumTv.setText(size+"");
                        allBetAmountTv.setText((Long.parseLong(inputTv)*size)+"");
                        betAmoumtTv.setText(inputTv);
                        sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                        ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this,0.5f);
                        hintKbTwo();//隐藏软键盘*/
                        chechBet(false);
                    }

                }
                break;
            case R.id.sure_bet_cancel:
                sureBetPop.dismiss();
                break;
            case R.id.sure_bet_sure:
                sureButton.setClickable(false);
                if (lianmaGroup.getVisibility() == View.VISIBLE) {//连码投注
                    requestBet(selecterList.size(), amountEdit.getText().toString(), true);
                } else {//其他投注
                    requestBet(selecterList.size(), amountEdit.getText().toString(), false);
                }
                break;
            case R.id.countdown_pop_sure:
                CountDownEndPop.dismiss();
                break;
            case R.id.bet_actionbar_back:
                finish();
                break;
        }
    }

    private void chechBet(boolean isLianma) {
        List<TouzhuModel> touzhuList = new ArrayList<>();
        Collections.sort(selecterList);
        if (!isLianma) {
            for (int i = 0; i < selecterList.size(); i++) {
                TouzhuModel touzhuModel = new TouzhuModel();
                List<String> groupname = Arrays.asList(selecterList.get(i).getGroupname().split("-"));
                if (groupname.size() == 2) {
                    touzhuModel.setGroupname(groupname.get(1));
                } else if (groupname.size() == 3) {
                    touzhuModel.setGroupname(groupname.get(1) + "-" + groupname.get(2));
                } else {
                    touzhuModel.setGroupname(groupname.get(0));
                }
                touzhuModel.setName(selecterList.get(i).getBetType());
                touzhuModel.setId(String.valueOf(selecterList.get(i).getRule_id()));
                touzhuModel.setMoney(amountEdit.getText().toString());
                touzhuList.add(touzhuModel);
            }
        } else {
            TouzhuModel touzhuModel = new TouzhuModel();
            List<String> groupname = Arrays.asList(selecterList.get(0).getGroupname().split("-"));
            if (groupname.size() == 2) {
                touzhuModel.setGroupname(groupname.get(1));
            } else if (groupname.size() == 3) {
                touzhuModel.setGroupname(groupname.get(1) + "-" + groupname.get(2));
            } else {
                touzhuModel.setGroupname(groupname.get(0));
            }

            String name = "";
            for (int i = 0; i < selecterList.size(); i++) {
                name = name + selecterList.get(i).getBetType() + ",";
            }
            name = name.substring(0, name.length() - 1);
            touzhuModel.setName(name);
            touzhuModel.setId(String.valueOf(selecterList.get(0).getRule_id()));
            touzhuModel.setMoney(amountEdit.getText().toString());
            touzhuList.add(touzhuModel);
        }

        String ma = "";
        if (isLianma) {
            for (Happy10Model happy10Model : selecterList) {
                ma = ma + happy10Model.getBetType() + ",";
            }
            ma = ma.substring(0, ma.length() - 1);
        }

        Intent intent = new Intent();
        intent.setClass(LuckFarmBetActivity.this, TouzhuActivity.class);
        intent.putExtra("touzhuList", (Serializable) touzhuList);
        intent.putExtra("game", String.valueOf(game));
        intent.putExtra("type_id", String.valueOf(type_id));
        intent.putExtra("money", amountEdit.getText().toString());
        intent.putExtra("qishu", nowQishu);
        intent.putExtra("index", "");  //
        intent.putExtra("ma", ma);
        startActivityForResult(intent, ReqTouzhu);
    }

    //对投注成功后进行数据重置 更新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // 这里写信用投注成功后清除数据
            for (int i = 0; i < happy10ModelList.size(); i++) {
                happy10ModelList.get(i).setStatus(0);
            }
            selecterList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size + "");
            happy10Adapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.机选));
            getMoney();//投注后,更新资金信息
        }

    }

    private void xingYongRandom() {
        selecterList.clear();
        if (happy10ModelList.size() == 0) {
            return;
        }
        int random = new Random().nextInt(happy10ModelList.size());
        happy10ModelList.get(random).setStatus(1);
        selecterList.add(happy10ModelList.get(random));
        int size = selecterList.size();
        betNum.setText(size + "");
        randomButton.setText(Utils.getString(R.string.重置));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            if(betType.equals(Utils.getString(R.string.第一球))||betType.equals(Utils.getString(R.string.第二球))||betType.equals(Utils.getString(R.string.第三球))||betType.equals(Utils.getString(R.string.第四球))||betType.equals(Utils.getString(R.string.第五球))||betType.equals(Utils.getString(R.string.第六球))||betType.equals(Utils.getString(R.string.第七球))||betType.equals(Utils.getString(R.string.第八球))){

                xingYongRandom();
            } else {
                break;
            }
        }
    }

    /**
     * 机选(重置)按钮
     *
     * @param happy10MedolList
     * @param num              需要随机的次数
     */
    private void randomBet(ArrayList<Happy10Model> happy10MedolList, int num) {
        selecterList.clear();
        ArrayList<Integer> randomList = new ArrayList<>();
        for (int i = 1; i < happy10MedolList.size(); i++) {
            randomList.add(i);
        }
        for (int i = 0; i < num; i++) {
            int random = new Random().nextInt(randomList.size());
            Integer value1 = randomList.get(random);
            randomList.remove(random);
            happy10MedolList.get(value1).setStatus(1);
            selecterList.add(happy10MedolList.get(value1));
        }
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            if(betType.equals(Utils.getString(R.string.任选二))|| betType.equals(Utils.getString(R.string.任选二))|| betType.equals(Utils.getString(R.string.任选二))|| betType.equals(Utils.getString(R.string.任选二))|| betType.equals(getString(R.string.选二连组))){
                randomBet(happy10MedolList,num);
                break;
            } else {
                break;
            }
        }
    }

    /*
      清空已选择的容器
     */
    private void clearSelector() {
        selecterList.clear();
        int size = selecterList.size();
        amountEdit.setText("");
        betNum.setText(size + "");
        randomButton.setText(Utils.getString(R.string.机选));
    }

    /**
     * 下注详情pop的数据处理
     *
     * @param list
     */
    private void initSureBetPopData(ArrayList<Happy10Model> list) {
        //添加所有已选中的medol
        for (int i = 0; i < selecterList.size(); i++) {
            Happy10Model happy8Model = selecterList.get(i);
            String groupname = happy8Model.getGroupname();
            String betType = happy8Model.getBetType();
            if (!list.contains(happy8Model)) {
                happy8Model = new Happy10Model();
                happy8Model.setGroupname(groupname);
//                happy8Model.setBetType(betType);
                list.add(happy8Model);
            }
        }
        /*
        将groupName相同的ruleId用逗号拼接
         */
        for (int i = 0; i < list.size(); i++) {
            Happy10Model happy8Model = list.get(i);
            for (int j = 0; j < selecterList.size(); j++) {
                Happy10Model model = selecterList.get(j);
                if (happy8Model.getGroupname().equalsIgnoreCase(model.getGroupname())) {//groupName相同,则设置ruleId
                    String rule_id = model.getRule_id();
                    String betType = model.getBetType();
                    String rebate = model.getRebate();
                    happy8Model.setRule_id(happy8Model.getRule_id() + "," + rule_id);
                    String modelBetType = happy8Model.getBetType();
                    if (StringMyUtil.isEmptyString(modelBetType)) {
                        modelBetType = "";
                    }
                    happy8Model.setBetType(modelBetType + "," + betType);
                }
            }
        }
        sureBetPopMeldolArrayList.clear();

        //给显示下注详情的textView设置样式()
        for (int i = 0; i < list.size(); i++) {
            Happy10Model model1 = list.get(i);
            String groupname = model1.getGroupname();
            String betType = model1.getBetType();
            String substring = betType.substring(1, betType.length());
            String finalStr1 = groupname + ":" + "<font color=\"#FF0000\">" + substring + "</font>";
            sureBetPopMeldolArrayList.add(new SureBetPopMeldol(finalStr1));
        }
          /*
        选择排序,去重复(因为上面遍历sureBetPopMeldolArrayList,会添加多次相同的数据,选择排序后,相同的数据只保留一个)
         */
        for (int i = 0; i < sureBetPopMeldolArrayList.size() - 1; i++) {
            for (int j = i + 1; j < sureBetPopMeldolArrayList.size(); j++) {
                if (sureBetPopMeldolArrayList.get(i).equals(sureBetPopMeldolArrayList.get(j))) {
                    sureBetPopMeldolArrayList.remove(j);
                    j--;
                }
            }
        }
        sureBetPopAdapter.notifyDataSetChanged();
    }

    /**
     * 请求下注接口
     *
     * @param size     当前选中容器的size
     * @param editText 下注金额输入框的内容
     * @param isLianma 是否是连码下注(需要进行参数处理)
     */
    private void requestBet(int size, String editText, boolean isLianma) {
        HashMap<String, Object> data = new HashMap<>();
        //不是连码  拼接ruleId  拼接下注金额
        if (!isLianma) {
            String needString = "";
            for (int i = 0; i < size; i++) {
                Happy10Model happy8Medol = selecterList.get(i);
                String rule_id = happy8Medol.getRule_id();
                needString += rule_id + ",";
            }
            needString = needString.substring(0, needString.length() - 1);
            String amountStr = "";
            for (int i = 0; i < size; i++) {
                amountStr += editText + ",";
            }
            amountStr = amountStr.substring(0, amountStr.length() - 1);
            String token = SharePreferencesUtil.getString(LuckFarmBetActivity.this, "token", "");
            String domain = SharePreferencesUtil.getString(LuckFarmBetActivity.this, "domain", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long l = System.currentTimeMillis();
            Date date = new Date(l);
            String format = df.format(date);
            data.put("user_id", user_id);
            data.put("type_id", type_id);
            data.put("rule_id", needString);
            data.put("amount", amountStr);
            data.put("lotteryqishu", nowQishu);
            data.put("source", 2);
            data.put("curtime", format);//当前时间
            data.put("token", token);//用户token
        }
        //连码下注  拼接连码的选中type
        else {
            String rule_id = selecterList.get(0).getRule_id();
            String lianma = "";//拼接连码投注内容
            for (int i = 0; i < size; i++) {
                String betType = selecterList.get(i).getBetType();
                lianma += betType + ",";
            }
            lianma = lianma.substring(0, lianma.length() - 1);
            String amount = editText.toString();
            String token = SharePreferencesUtil.getString(LuckFarmBetActivity.this, "token", "");
            String domain = SharePreferencesUtil.getString(LuckFarmBetActivity.this, "domain", "");
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            long l = System.currentTimeMillis();
            Date date = new Date(l);
            String format = df.format(date);
            data.put("user_id", user_id);
            data.put("type_id", type_id);
            data.put("rule_id", rule_id);
            data.put("amount", amount);
            data.put("lotteryqishu", nowQishu);
            data.put("source", 2);
            data.put("curtime", format);//当前时间
            data.put("token", token);//用户token
            data.put("lianma", lianma);
        }

        Utils.docking(data, RequestUtil.REQUEST_02farm, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                //投注成功  清空选中容器  初始化投注相关控件的状态
                betButton.setClickable(true);
                sureButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
                for (int i = 0; i < happy10ModelList.size(); i++) {
                    happy10ModelList.get(i).setStatus(0);
                }
                selecterList.clear();
                int size = selecterList.size();
                amountEdit.setText("");
                betNum.setText(size + "");
                happy10Adapter.notifyDataSetChanged();
                sureBetPop.dismiss();
                randomButton.setText(Utils.getString(R.string.机选));
                getMoney();//投注后,更新资金信息
            }

            @Override
            public void failed(MessageHead messageHead) {
                betButton.setClickable(true);
                sureButton.setClickable(true);
                JSONObject headData = messageHead.getData();
                showToast(Utils.getString(R.string.下注失败) + "\n" + messageHead.getInfo());
                sureBetPop.dismiss();

            }
        });
    }

    /*
     倒计时处理相关
      */
    Runnable runnableTime = new Runnable() {
        @Override
        public void run() {
            if (countTime <= 0) {
                getCountTime();
            } else {
                countTime = millionSeconds + shijiancha - System.currentTimeMillis() - (Long.parseLong(tqtime) * 1000);
//                countTime = countTime - 1000;
                long hours = (countTime/* - days * (1000 * 60 * 60 * 24)*/) / (1000 * 60 * 60);
                long minutes = (countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = (countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
                hourText.setText(hours + "");
                minutesText.setText(minutes + "");
                secondsText.setText(seconds + "");
                if (hours == 0 || hours < 10) {
                    hourText.setText("0" + hours);
                } else {
                    hourText.setText("" + hours);
                }
                if (minutes == 0 || minutes < 10) {
                    minutesText.setText("0" + minutes);
                } else {

                    minutesText.setText("" + minutes);

                }
                if (seconds == 0 || seconds < 10) {

                    secondsText.setText("0" + seconds);
                } else {
                    secondsText.setText("" + seconds);
                }
                if (countTime <= 1) {
                    countTime = 0;
                    if (CountDownEndPop != null) {
                        lastQiShuTv.setText(nowQishu);
                        newQiShuTv.setText((Long.parseLong(nowQishu) + 1) + "");
                        if (!isFinishing() && CountDownEndPop != null && !CountDownEndPop.isShowing() && secondsText.getText().toString().equals("00")) {
                            CountDownEndPop.showAtLocation(minutesText, Gravity.CENTER, 0, 0);
                            ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this, 0.5f);
                        }
                    }
                    isWaitOpen = true;
                }
            }
            handler.postDelayed(runnableTime, 1000);

        }
    };

    /*
    开奖结果未更新前的动画
     */
    Runnable runnableRandom = new Runnable() {
        @Override
        public void run() {
            if (isWaitOpen) {
                int i = new Random().nextInt(9) + 1;
                for (int j = 0; j < myCornerTextViewArrayLi.size(); j++) {
                    myCornerTextViewArrayLi.get(j).setText(i + "");
                }
            } else {
                for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
                    myCornerTextViewArrayLi.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRandom, 150);
        }
    };

    /*
    未开奖时,循环请求开奖结果
     */
    Runnable runnableRequestOpen = new Runnable() {
        @Override
        public void run() {
            if (isWaitOpen) {
                initOpenResult();
            } else {
                for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
                    myCornerTextViewArrayLi.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRequestOpen, jgTime);
        }
    };

    /**
     * 彩票分类recycleView 点击事件回调
     *
     * @param view
     * @param position
     * @param lotteryClassModel
     */
    @Override
    public void onItemClick(View view, int position, LotteryClassModel lotteryClassModel) {
        int type_id = lotteryClassModel.getType_id();
        String isopenOffice = lotteryClassModel.getIsopenOffice();
        String isStart = lotteryClassModel.getIsStart();
        String typename = lotteryClassModel.getTypename();
        int game = lotteryClassModel.getGame();
        ToBetAtyUtils.toBetActivity(LuckFarmBetActivity.this, game, typename, type_id, isopenOffice, isStart);
        finish();
    }

    /**
     * 右侧菜单pop的点击事件回调
     *
     * @param view
     */
    @Override
    public void onMenuClicked(View view) {

    }

    /**
     * 投注规则recycleView点击事件回调
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        int size = selecterList.size();
        if (popPosition == betChoosePalyModelArrayList.size() - 1) {//点击了 连码按钮(给连码按钮下的item多加一个字段,切换时,根据此字段和id判断是否选中)
            String lianmaId = happy10ModelList.get(position).getLianmaId();
            String rule_id = happy10ModelList.get(position).getRule_id();
            if (lianMaIdList.contains(lianmaId + rule_id)) {
                lianMaIdList.remove(lianmaId + rule_id);
                ;
            } else {
                lianMaIdList.add(lianmaId + rule_id);
            }
        }
        //连码界面下  item点击事件的处理
        if (lianmaGroup.getVisibility() == View.VISIBLE) {
            if (lianmaOneBtn.isChecked() || lianmaTwoBtn.isChecked()) {
                if (size > 2) {
                    showToast(Utils.getString(R.string.不允许超出2个选项));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                } else if (size < 2) {
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.机选));
                } else {
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.重置));
                }
            } else if (lianmaThreeBtn.isChecked()) {
                if (size > 3) {
                    showToast(Utils.getString(R.string.不允许超出3个选项));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                } else if (size < 3) {
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.机选));
                } else {
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.重置));
                }
            } else if (lianmaFourBtn.isChecked()) {
                if (size > 4) {
                    showToast(Utils.getString(R.string.不允许超出4个选项));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                } else if (size < 4) {
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.机选));
                } else {
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.重置));
                }
            } else {
                if (size > 5) {
                    showToast(Utils.getString(R.string.不允许超出5个选项));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                } else if (size < 5) {
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.机选));
                } else {
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.重置));
                }
            }

        }
        //非连码界面下, item点击事件的处理
        else {
            betNum.setText(size + "");//当前选中了多少注,直接设置为seleterList的size(selecterList在每次点击选中item的时候会将当前的position对应的model保存,每次取消选中的时候,将对应的medol移除)
            if (size != 0) {
                randomButton.setText(Utils.getString(R.string.重置));
            } else {
                randomButton.setText(Utils.getString(R.string.机选));
            }
        }
    }

    /*
    投注清单pop
     */
    private void initSureBetPop() {
        sureBetPopAdapter = new SureBetPopAdapter(sureBetPopMeldolArrayList);
        View view = LayoutInflater.from(this).inflate(R.layout.sure_bet_pop, null);
        View footView = LayoutInflater.from(view.getContext()).inflate(R.layout.sure_bet_pop_recycle_foot, null);
        View headView = LayoutInflater.from(view.getContext()).inflate(R.layout.sure_bet_pop_recycle_head, null);
        sureBetPopAdapter.addHeaderView(headView);
        sureBetPopAdapter.addFooterView(footView);
        lotteryNameTv = headView.findViewById(R.id.lottery_name);
        qishuTv = headView.findViewById(R.id.lottery_qishu);
        betAmoumtTv = headView.findViewById(R.id.bet_amount);
        allBetNumTv = footView.findViewById(R.id.all_bet_num);
        allBetAmountTv = footView.findViewById(R.id.all_bet_amout);
        cancelButton = view.findViewById(R.id.sure_bet_cancel);
        sureButton = view.findViewById(R.id.sure_bet_sure);
        sureBetRecy = view.findViewById(R.id.sure_bet_pop_recycly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        sureBetRecy.setLayoutManager(linearLayoutManager);
        sureBetRecy.setAdapter(sureBetPopAdapter);
        cancelButton.setOnClickListener(this);
        sureButton.setOnClickListener(this);
        sureBetPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        sureBetPop.setAnimationStyle(R.style.pop_scale_animation);
        sureBetPop.setTouchable(true);//响应内部点击
        sureBetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing() || !choosePlayTypePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(LuckFarmBetActivity.this, 1f);
                }
                betButton.setClickable(true);
            }
        });

    }
        /*
        隐藏软键盘
         */

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacks(runnableBoolen);
/*        handler.removeCallbacks(runnableRandom);
        handler.removeCallbacks(runnableRequestOpen);
        handler.removeCallbacks(runnableTime);
        handler.removeCallbacks(runnableZj);*/
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private int getGame() {
        /*1》快3，
        2》时时彩，
        3》赛车，
        4》六合彩，
        5》蛋蛋，
        6》快8，
        7》农场，
        8》快乐10分，
        9》11选5*/
        return 7;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initAllPop();
        if (!CountDownEndPop.isShowing()) {
            ProgressDialogUtil.darkenBackground(this, 1f);
        }
//        handler.postDelayed(runnableRandom,150);
//        handler.postDelayed(runnableRequestOpen,jgTime);
//        handler.postDelayed(runnableTime,1000);
//        handler.postDelayed(runnableZj,10000);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateXian(HbGameClassModel hbGameClassModel) {
        customPopupWindow.selectorId(hbGameClassModel);
    }

    @Override
    public void onNetChange(boolean netWorkState) {
        if (!netWorkState) {
            showToast(Utils.getString(R.string.当前网络不可用,请检查网络));
            findViewById(R.id.no_network).setVisibility(View.VISIBLE);
            ProgressDialogUtil.show(this);
            handler.removeCallbacks(runnableRandom);
            handler.removeCallbacks(runnableRequestOpen);
            handler.removeCallbacks(runnableZj);
            handler.removeCallbacks(runnableTime);
        } else {
            findViewById(R.id.no_network).setVisibility(View.GONE);
            ProgressDialogUtil.stop(this);
            if (runnableRandom == null) {
                handler.postDelayed(runnableRandom, 150);
            }
            if (runnableRequestOpen == null) {
                handler.postDelayed(runnableRequestOpen, jgTime);
            }
            if (runnableZj == null) {
                handler.postDelayed(runnableZj, 10000);
            }
            if (runnableTime == null) {
                handler.postDelayed(runnableTime, 1000);
            }
        }
    }

}
