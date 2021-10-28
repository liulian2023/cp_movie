package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
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

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.K3GfHeadAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.K3OfficialAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.K3RecyclerAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.RecyclerViewGridAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GetGroup;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.K3GfHeadModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KJCountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LastLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MemberMoney;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TimerUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.lang.ref.WeakReference;
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
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqlastLottery;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil.timeMode;

public class K3Activity extends BaseActivity implements CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener{


    public static final int ReqTouzhu = 101;
    String type_id, isopenOffice, isStart, game, typename;

    KJCountDown kjCountDown;
    GetGroup getGroup;
    LastLottery lastLottery;
    MemberMoney memberMoney;
    private String todayZJ;//是否中奖(1 中奖 0未中)


    @BindView(R.id.caizhong_head1_back)
    TextView caizhong_head1_back;
    @BindView(R.id.ll_caizhong_head1)
    LinearLayout ll_caizhong_head1;
    @BindView(R.id.caizhong_head1_title)
    TextView caizhong_head1_title;
    @BindView(R.id.caizhong_head1_iv)
    ImageView caizhong_head1_iv;
    @BindView(R.id.k3_xy_recyclerview)
    RecyclerView k3_xy_recyclerview; //下面布局recyclerview
    @BindView(R.id.k3_gf_recyclerview)
    RecyclerView k3_gf_recyclerview; //下面布局recyclerview
    @BindView(R.id.k3_center_title)
    TextView k3_center_title;
    @BindView(R.id.k3_tab_num)
    TextView k3_tab_num;  //注数
    @BindView(R.id.k3_tab_money)
    EditText k3_tab_money;//金额
    @BindView(R.id.k3_tab_confirm)
    TextView k3_tab_confirm;  //下注
    @BindView(R.id.k3_tab_reset)
    TextView k3_tab_reset;  //重置
    @BindView(R.id.ll_caizhong_head2_left)
    LinearLayout ll_caizhong_head2_left;  //点击弹出彩种分类pop
    @BindView(R.id.ll_caizhong_head2_left_tv)
    TextView ll_caizhong_head2_left_tv;
    @BindView(R.id.k3_head3_tv1)  //本期开奖
    TextView k3_head3_tv1;
    @BindView(R.id.k3_head3_tv2)   //倒计时
    TextView k3_head3_tv2;
    @BindView(R.id.time_load_img)
    GifImageView time_load_img;
    @BindView(R.id.k3_head3_tv3) //上期开奖
    TextView k3_head3_tv3;
    @BindView(R.id.k3_head3_iv1)
    ImageView k3_head3_iv1; //筛子1
    @BindView(R.id.k3_head3_iv2)
    ImageView k3_head3_iv2; //筛子2
    @BindView(R.id.k3_head3_iv3)
    ImageView k3_head3_iv3; //筛子3
    @BindView(R.id.ll2_k3_head3)  //上期开奖父容器
    LinearLayout ll2_k3_head3;

    TextView lastQiShuTv;
    TextView newQiShuTv;
    TextView countDownEndSure;
    @BindView(R.id.ll1_k3_head3)
    LinearLayout ll1_k3_head3;
    @BindView(R.id.caizhong_head1_menu)
    TextView caizhong_head1_menu;
    @BindView(R.id.ll_caizhong_head2_center)
    LinearLayout ll_caizhong_head2_center;
    @BindView(R.id.ll_caizhong_head2_center_tv)
    TextView ll_caizhong_head2_center_tv;
    @BindView(R.id.ll_caizhong_head2_right)
    LinearLayout ll_caizhong_head2_right;
    @BindView(R.id.ll_caizhong_head2_right_tv)
    TextView ll_caizhong_head2_right_tv;
    @BindView(R.id.rotate_image)
    ImageView rotate_image;

    @BindView(R.id.k3_tab_main)
    LinearLayout k3_tab_main;
    @BindView(R.id.ll_k3_xy)
    LinearLayout ll_k3_xy;
    @BindView(R.id.ll_k3_gf)
    LinearLayout ll_k3_gf;
    @BindView(R.id.ll_reload)
    LinearLayout ll_reload;
    @BindView(R.id.tv_reload)
    TextView tv_reload;
    @BindView(R.id.k3_gf_title)
    TextView k3_gf_title;
    @BindView(R.id.ll_caizhong_head1_parent)
    LinearLayout ll_caizhong_head1_parent;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.fenpan_tv)
    TextView fenpan_tv;
    K3RecyclerAdapter k3RecyclerAdapter;   //信用adapter
    K3OfficialAdapter k3OfficialAdapter;   //官方adapter
    RecyclerView head1recyclerview;
    PopupWindow head1popupWindow;
    private PopupWindow CountDownEndPop;//倒计时结束时的pop
    RecyclerViewGridAdapter xy_headAdapter;
    K3GfHeadAdapter gf_headAdapter;

    List<Integer> kjList = new ArrayList<Integer>(); //开奖list  存放图片 资源id
    int[] shaizis;//筛子点数
    //  LinkedHashMap<String, Boolean> isClickMap = new LinkedHashMap<>();
    List<IsClickModel> isClickList = new ArrayList<>();
    Long endtime, localtime, sjcha, tqtime;  //截止时间 当前时间 服务器与本地时间差
    String qishu, lastqishu, lotteryqishu;
    Long timeCount = 0L;  //倒计时数
    boolean isWaitopen = true;
    // long num = 1;
    List<GetGroup.GameruleBean> widgetList = new ArrayList<GetGroup.GameruleBean>(); //如10个控件信息
    List<String[]> widgetcontentList = new ArrayList<String[]>();
    List<GetGroup.GameruleTwoBean> widgetListTwo = new ArrayList<>();
    List<GetGroup.GameruleTwoBean> officialList = new ArrayList<>();


    Animation rotateAnimation = AnimUtils.getAnimation(180);

    boolean isOfficial = false;
    List<GetGroup.GameruleBean> gameruleList = new ArrayList<>();
    List<GetGroup.GroupBean> groupList = new ArrayList<>();
    List<GetGroup.GameruleTwoBean> gameRulelisttwo = new ArrayList<>();
    List<GetGroup.GroupTwoBean> grouplisttwo = new ArrayList<>();
    List<String> xy_popheadList = new ArrayList<>();
    List<K3GfHeadModel> gf_popheadList = new ArrayList<>();

    int index_xy = 0;
    int index_gf = 0;

    String gf_ma = "";
    String gf_rule_id = "";
    String gf_amount = "";


    private long mLastClickTime = 0;
    public static final long TIME_INTERVAL = 1000L;

    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k3);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        Utils. saveLotteryHistory(Integer.parseInt(game),Integer.parseInt(type_id));
        mycountdown();  //倒计时
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
            k3_tab_main.setVisibility(View.VISIBLE);
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
            initAllPop();
        } else {
            k3_tab_main.setVisibility(View.GONE);
            ll_caizhong_head1_parent.setVisibility(View.GONE);
            ll_reload.setVisibility(View.VISIBLE);
            showToast(Utils.getString(R.string.网络不给力请检查网络));
        }
    }

    @Override  //父类初始化
    protected void init() {
        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        typename = intent.getStringExtra("typename");
        type_id = intent.getStringExtra("type_id");
        isopenOffice = intent.getStringExtra("isopenOffice");  // 0 没有开官方彩
        isStart = intent.getStringExtra("isStart");
        shaizis = Const.getShaziArray(this);
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

    //显示倒计时
    private void mycountdown() {
        time_load_img.setVisibility(View.VISIBLE);
        k3_head3_tv2.setVisibility(View.GONE);
        ReqCount(this, game, type_id, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                if (!StringMyUtil.isEmptyString(content)) {
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    try {
                        kjCountDown = gson.fromJson(content, KJCountDown.class);
                        //设置全局变量
                        if (kjCountDown != null) {
                            if(StringMyUtil.isEmptyString(kjCountDown.getQishu())){
                                fenpan_tv.setVisibility(View.VISIBLE);
                                k3_head3_tv1.setText("- - -");
                                k3_head3_tv2.setText("- - -");
                            }else {
                                fenpan_tv.setVisibility(View.GONE);
                                endtime = Long.parseLong(DateUtil.dateToStamp(kjCountDown.getStoptimestr()));
                                tqtime = (long) kjCountDown.getTqtime() * 1000;
                                sjcha = SharePreferencesUtil.getLong(K3Activity.this, "shijiancha", 0l);//servertime - localtime;
                                qishu = kjCountDown.getQishu();
                                timeCount = 1L;
                                lastqishu = kjCountDown.getLastqishu();
                                if (StringMyUtil.isEmptyString(qishu)) {
                                    k3_head3_tv1.setText("---");
                                    k3_head3_tv2.setVisibility(View.INVISIBLE);
                                } else {
                                    k3_head3_tv1.setText(qishu + Utils.getString(R.string. 期));
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            time_load_img.setVisibility(View.GONE);
                                            k3_head3_tv2.setVisibility(View.VISIBLE);
                                        }
                                    },600);

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
                //     showtoast(Utils.getString(R.string.请求数据错误));
                time_load_img.setVisibility(View.VISIBLE);
                k3_head3_tv2.setVisibility(View.GONE);
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
        }
    }

    //获取数据接口数据 getgroup
    private void initData() {
        loading_linear.setVisibility(View.VISIBLE);
        checkPara();
        ReqgetGroup(this, game, type_id, -1, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                loading_linear.setVisibility(View.GONE);
                Gson gson = new GsonBuilder().serializeNulls().create();
                if (!StringMyUtil.isEmptyString(content)) {
                    try {
                        getGroup = gson.fromJson(content, GetGroup.class);
                        if (getGroup != null) {
                            ll_reload.setVisibility(View.GONE);
                            k3_tab_main.setVisibility(View.VISIBLE);
                            gameruleList.clear();
                            gameruleList = getGroup.getGameRulelist();
                            groupList.clear();
                            groupList = getGroup.getGrouplist();
                            gameRulelisttwo.clear();
                            gameRulelisttwo = getGroup.getGameRulelisttwo();
                            grouplisttwo.clear();
                            grouplisttwo = getGroup.getGrouplisttwo();

                            //初始化左方和上方的title
                            ll_caizhong_head2_left_tv.setText(typename);
                            if (!isOfficial) {
                                caizhong_head1_title.setText(groupList.get(index_xy).getGroupname());
                            } else {
                                caizhong_head1_title.setText(grouplisttwo.get(index_gf).getGroupname());
                            }
                            // 默认第一次  加载下方列表
                            if (!isOfficial) {
                                ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.信用玩法));
                                showcaizhong(index_xy, false);
                                ll_k3_gf.setVisibility(View.GONE);
                                ll_k3_xy.setVisibility(View.VISIBLE);
                            } else {
                                ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.官方玩法));
                                showcaizhong(index_gf, false);
                                ll_k3_gf.setVisibility(View.VISIBLE);
                                ll_k3_xy.setVisibility(View.GONE);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(String content) {
                //     showtoast(Utils.getString(R.string.请求数据错误));
                loading_linear.setVisibility(View.GONE);
            }
        });
        requestMoney();
    }

    private void initOpenResult() {
        ReqlastLottery(this, game, type_id, 1, 1, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                if (!StringMyUtil.isEmptyString(content)) {
                    try {
                        lastLottery = gson.fromJson(content, LastLottery.class);
                        if (lastLottery != null) {
                            lotteryqishu = lastLottery.getGameLotterylist().get(0).getLotteryqishu();
                        }
                        if (!StringMyUtil.isEmptyString(qishu) && !StringMyUtil.isEmptyString(lastqishu) && !StringMyUtil.isEmptyString(lotteryqishu) && lastLottery != null) {
                            //是否是当天最后一期
                            if (Long.parseLong(lastqishu) == Long.parseLong(lotteryqishu.substring(lotteryqishu.length() - 2, lotteryqishu.length()))) {
                                isWaitopen = false;
                                k3_head3_tv3.setText(lotteryqishu + " "+Utils.getString(R.string.期开奖号码));
                                //  updateRightLotteryNo();
                                List<String> list = Arrays.asList(lastLottery.getGameLotterylist().get(0).getLotteryNo().split(","));
                                kjList.clear();
                                for (String bean : list) {
                                    kjList.add(shaizis[Integer.parseInt(bean) - 1]);
                                }
                                k3_head3_iv1.setImageResource(kjList.get(0));
                                k3_head3_iv2.setImageResource(kjList.get(1));
                                k3_head3_iv3.setImageResource(kjList.get(2));
                            } else {
                                if (Long.parseLong(qishu) - 1 == Long.parseLong(lotteryqishu)) {
                                    k3_head3_tv3.setText(lotteryqishu + " "+Utils.getString(R.string.期开奖号码));
                                    Utils.logE("isWaitopen:", "" + isWaitopen + ",initOpenResult2");
                                    isWaitopen = false;
                                    List<String> list = Arrays.asList(lastLottery.getGameLotterylist().get(0).getLotteryNo().split(","));
                                    kjList.clear();
                                    for (String bean : list) {
                                        kjList.add(shaizis[Integer.parseInt(bean) - 1]);
                                    }
                                    k3_head3_iv1.setImageResource(kjList.get(0));
                                    k3_head3_iv2.setImageResource(kjList.get(1));
                                    k3_head3_iv3.setImageResource(kjList.get(2));
                                } else {
                                    k3_head3_tv3.setText((Long.parseLong(lotteryqishu) + 1) + " "+Utils.getString(R.string.期开奖号码));  //筛子转动
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
                //    showtoast(Utils.getString(R.string.请求数据错误));
            }
        });
    }

    //显示下方的自定义recyclerview
    private void showcaizhong(int x, boolean isChoice) {
        String group_id = "", groupname = "";
        if (!isOfficial) {
            group_id = String.valueOf(groupList.get(x).getGroup_id());
            groupname = groupList.get(x).getGroupname();
            k3_center_title.setText(groupname);
        } else {
            group_id = String.valueOf(grouplisttwo.get(x).getGroup_id());
        }
        //gridRecycler num 的控制
        int column = 0;
        String gf_titleStr = "";
        BigDecimal odds_setscale = new BigDecimal(String.valueOf(grouplisttwo.get(x).getOdds()));
//        BigDecimal odds_setscale = odds.setScale(2, BigDecimal.ROUND_HALF_UP);
        switch (group_id) {
            case "1":
                column = 2;
                break;
            case "2":
                column = 2;
                break;
            case "3":
                column = 5;
                break;
            case "4":
                column = 5;
                break;
            case "5":
                column = 2;
                break;
            case "6":
                column = 4;
                gf_titleStr = getString(R.string.猜3个开奖号相加的和) + odds_setscale + "倍";
                break;
            case "7":
                column = 1;
                gf_titleStr = getString(R.string.对所有相同的三个号码) + odds_setscale + "倍";
                break;
            case "8":
                column = 3;
                gf_titleStr = getString(R.string.对相同的三个号码) + odds_setscale + "倍";
                break;
            case "9":
                column = 1;
                gf_titleStr = getString(R.string.对所有的3个相连号码) + odds_setscale + "倍";
                break;
            case "10":
                column = 3;
                gf_titleStr = getString(R.string.所选号码与开奖号码的) + odds_setscale + "倍";
                break;
            case "11":
                column = 3;
                gf_titleStr = getString(R.string.选号与奖号) + odds_setscale + "倍";
                break;
            case "12":
                column = 6;
                gf_titleStr = getString(R.string.选择1对相同号码和1个不同号码投注) + odds_setscale + "倍";
                break;
            case "13":
                column = 3;
                gf_titleStr = getString(R.string.所选号码与开奖号码任意2个号码相同) + odds_setscale + "倍";
                break;
            default:
                column = 2;
                break;
        }
        if (isOfficial) {
            //TODO 2种方式显示特定textview
            SpannableString spannableString = new SpannableString(gf_titleStr);
            if (gf_titleStr.contains("。")) {
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), gf_titleStr.lastIndexOf("。") + 1, gf_titleStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            k3_gf_title.setText(spannableString);
            //  String content = "<font color=\"#FF0000\">" + gf_titleStr + "</font>";
            //  k3_gf_title.setText(Html.fromHtml(content));
        }
        //TODO 信用recyclerview列表展示
        if (!isOfficial) {
            widgetList.clear();
            for (GetGroup.GameruleBean bean : gameruleList) {
                if (String.valueOf(bean.getGroup_id()).equals(group_id)) {
                    widgetList.add(bean);
                }
            }
            List<String> list2 = new ArrayList<>();
            list2.clear();
            for (int i = 0; i < widgetList.size(); i++) {
                list2.add(widgetList.get(i).getName());
            }
            // List<String[]> list3 = new ArrayList<String[]>();
            widgetcontentList.clear();
            for (String s : list2) {
                widgetcontentList.add(s.split("_"));
            }
            if (!isChoice) {
                //  isClickMap.clear();
                isClickList.clear();
                for (int i = 0; i < gameruleList.size(); i++) {
                    //   isClickMap.put(String.valueOf(gameruleList.get(i).getId()), false);
                    IsClickModel isClickModel = new IsClickModel();
                    isClickModel.setId(String.valueOf(gameruleList.get(i).getId()));
                    isClickModel.setIsclick(false);
                    isClickList.add(isClickModel);

                }
            }
            k3RecyclerAdapter = new K3RecyclerAdapter(this, widgetList, widgetcontentList, shaizis, isClickList);
            k3_xy_recyclerview.setAdapter(k3RecyclerAdapter);
            //布局管理器所需参数，上下文
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, column);
            //B 通过布局管理器，可以控制条目排列顺序  true：反向显示  false：正常显示(默认)
            gridLayoutManager.setReverseLayout(false);
            k3_xy_recyclerview.setLayoutManager(gridLayoutManager);
            //TODO 2019-7-18 信用Recycler回调
            k3RecyclerAdapter.setOnItemClickListener((View view, int position, List<IsClickModel> isClickList) -> {
                int num = 0;
                for (int i = 0; i < isClickList.size(); i++) {
                    if (StrUtil.isclick(isClickList, isClickList.get(i).getId())) {
                        num++;
                    }
                }
                k3_tab_num.setText("" + num);
                if (num != 0) {
                    k3_tab_reset.setText(Utils.getString(R.string.重置));
                } else {
                    k3_tab_reset.setText(Utils.getString(R.string.机选));
                }
            });
        }
        //TODO 官方recyclerview列表显示2019-7-18
        else {
            widgetListTwo.clear();
            for (GetGroup.GameruleTwoBean bean : gameRulelisttwo) {
                if (String.valueOf(bean.getGroup_id()).equals(group_id)) {
                    widgetListTwo.add(bean);
                }
            }
            if (group_id.equals("6")
                    || group_id.equals("7")
                    || group_id.equals("9")) {
                officialList.clear();
                officialList.addAll(widgetListTwo);
            }
            if (group_id.equals("8")
                    || group_id.equals("10")
                    || group_id.equals("11")
                    || group_id.equals("12")
                    || group_id.equals("13")) {
                officialList.clear();
                String[] arry = widgetListTwo.get(0).getRemark().split(",");
                for (int i = 0; i < arry.length; i++) {
                    GetGroup.GameruleTwoBean bean = new GetGroup.GameruleTwoBean();
                    bean.setCode(widgetListTwo.get(0).getCode());
                    bean.setCreatedDate(widgetListTwo.get(0).getCreatedDate());
                    bean.setGroup_id(widgetListTwo.get(0).getGroup_id());
                    bean.setGroupname(widgetListTwo.get(0).getGroupname());
                    bean.setId(widgetListTwo.get(0).getId() * 100 + i);
                    bean.setIsDelete(widgetListTwo.get(0).getIsDelete());
                    bean.setModel_id(widgetListTwo.get(0).getModel_id());
                    //三同号单选
                    if (group_id.equals("8")) {
                        bean.setName(String.valueOf(i + 1) + String.valueOf(i + 1) + String.valueOf(i + 1));
                    }
                    //三不同号
                    if (group_id.equals("10")) {
                        bean.setName(String.valueOf(i + 1));
                    }
                    //二同号复选
                    if (group_id.equals("11")) {
                        bean.setName(String.valueOf(i + 1) + String.valueOf(i + 1));
                    }
                    //二同号单选
                    if (group_id.equals("12")) {
                        if (i < 6) {
                            bean.setName(String.valueOf(i + 1) + String.valueOf(i + 1));
                        } else {
                            bean.setName(String.valueOf(i + 1 - 6));
                        }
                    }
                    //二不同号
                    if (group_id.equals("13")) {
                        bean.setName(String.valueOf(i + 1));
                    }
                    bean.setOdds(widgetListTwo.get(0).getOdds());
                    bean.setPlay(widgetListTwo.get(0).getPlay());
                    bean.setRemark(arry[i]);
                    bean.setType_id(widgetListTwo.get(0).getType_id());

                    officialList.add(bean);
                }
            }
            isClickList.clear();
            for (int i = 0; i < officialList.size(); i++) {
                StrUtil.isclickAdd(isClickList, String.valueOf(officialList.get(i).getId()), false);
            }
            k3OfficialAdapter = new K3OfficialAdapter(this, officialList, x, isClickList);
            k3_gf_recyclerview.setAdapter(k3OfficialAdapter);
            //布局管理器所需参数，上下文
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, column);
            //B 通过布局管理器，可以控制条目排列顺序  true：反向显示  false：正常显示(默认)
            gridLayoutManager.setReverseLayout(false);
            k3_gf_recyclerview.setLayoutManager(gridLayoutManager);
        }
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
        //跳转两面长龙
        customPopupWindow.toTwoChangLongAty(this, Integer.parseInt(game), Integer.parseInt(type_id));
        //跳转今日输赢
        customPopupWindow.toTodayWinLose(this, Integer.parseInt(game), Integer.parseInt(type_id));
        customPopupWindow.initKuaiSanTodayResult(new WeakReference<>(K3Activity.this), Integer.parseInt(type_id),false);
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
        CountDownEndPop.setOnDismissListener(() -> ProgressDialogUtil.darkenBackground(K3Activity.this, 1f));
        countDownEndSure.setOnClickListener(v -> CountDownEndPop.dismiss());
    }

    //界面控件点击
    private void clickView() {
        fenpan_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Utils.getString(R.string.该彩种已封盘));
            }

        });
        //TODO 2019-7-18 信用官方切换
        ll_caizhong_head2_right.setOnClickListener(v -> {
            isOfficial = !isOfficial;
            if(getGroup==null){
                return;
            }
            if (isOfficial) {
                if (!"0".equals(isopenOffice)){
                    ll_k3_gf.setVisibility(View.VISIBLE);
                    ll_k3_xy.setVisibility(View.GONE);
                    gameRulelisttwo = getGroup.getGameRulelisttwo();
                    grouplisttwo = getGroup.getGrouplisttwo();
                    ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.官方玩法));
                    caizhong_head1_title.setText(grouplisttwo.get(index_gf).getGroupname());
                    showcaizhong(index_gf, false);
                }else {
                    showToast(Utils.getString(R.string.暂无官方彩));
                }

            } else {
                ll_k3_gf.setVisibility(View.GONE);
                ll_k3_xy.setVisibility(View.VISIBLE);
                ll_caizhong_head2_right_tv.setText(Utils.getString(R.string.信用玩法));
                caizhong_head1_title.setText(groupList.get(index_xy).getGroupname());
                showcaizhong(index_gf, false);
            }
            Utils.logE("isOfficial", "" + isOfficial);
        });
        //返回按钮
        caizhong_head1_back.setOnClickListener(v -> onBackPressed());
        //TODO 顶部headpop切换
        ll_caizhong_head1.setOnClickListener(v -> {
            if (getGroup != null) {
                caizhong_head1_iv.startAnimation(rotateAnimation);
                caizhong_head1_iv.setImageResource(R.mipmap.icon_spinner_down);

                List<String> list = new ArrayList<>();
                int x = 0;
                if (!isOfficial) {
                    for (int i = 0; i < groupList.size(); i++) {
                        list.add(groupList.get(i).getGroupname());
                    }
                    String str = caizhong_head1_title.getText().toString();

                    for (int i = 0; i < list.size(); i++) {
                        if (str.equals(list.get(i))) {
                            x = i;
                            break;
                        }
                    }
                } else { //TODO 官方顶部headpop切换
                    for (int i = 0; i < grouplisttwo.size(); i++) {
                        list.add(grouplisttwo.get(i).getGroupname());
                    }
                    String str = caizhong_head1_title.getText().toString();

                    for (int i = 0; i < list.size(); i++) {
                        if (str.equals(list.get(i))) {
                            x = i;
                            break;
                        }
                    }
                }
                showhead1popup(x);
            }
        });
        //投注按钮
        k3_tab_confirm.setOnClickListener(v -> {
                //TODO 2019-7-20 信用投注
                if (!isOfficial) {
                    int count = 0;
                    count = StrUtil.isclickCal(isClickList);
                    if (count == 0) {
                        showToast(Utils.getString(R.string.请选择玩法));
                        return;
                    }
                    if (StringMyUtil.isEmptyString(k3_tab_money.getText().toString()) || !MoneyUtils.isMoney(k3_tab_money.getText().toString())) {
                        showToast(Utils.getString(R.string.请输入金额));
                        return;
                    }
                    List<TouzhuModel> touzhuList = new ArrayList<>();
                    for (int i = 0; i < isClickList.size(); i++) {
                        if (isClickList.get(i).getIsclick()) {
                            for (GetGroup.GameruleBean bean : getGroup.getGameRulelist()) {
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
                    intent.setClass(K3Activity.this, TouzhuActivity.class);
                    intent.putExtra("touzhuList", (Serializable) touzhuList);
                    intent.putExtra("isClickList", (Serializable) isClickList);
                    intent.putExtra("game", game);
                    intent.putExtra("type_id", type_id);
                    intent.putExtra("money", k3_tab_money.getText().toString());
                    intent.putExtra("qishu", qishu);
                    intent.putExtra("index", "");
                    intent.putExtra("ma", "");
                    startActivityForResult(intent, ReqTouzhu);
                }//TODO 2019-7-20 官方投注
                else {
                    long nowTime = System.currentTimeMillis();
                    if (nowTime - mLastClickTime > TIME_INTERVAL) {
                        int count = 0;

                        count = StrUtil.isclickCal(isClickList);
                        // 0 1 2 3 5至少选1个   4至少选3个（组合）  6至少选2个（上下各选1个） 7 至少选2个（组合）
                        if (index_gf == 0 || index_gf == 1 || index_gf == 2 || index_gf == 3 || index_gf == 5) {
                            if (count == 0) {
                                showToast(Utils.getString(R.string.请选择玩法));
                                return;
                            }

                        }
                        if (index_gf == 4) {
                            if (count < 3) {
                                showToast(Utils.getString(R.string.请选择玩法));
                                return;
                            }
                        }
                        if (index_gf == 6 || index_gf == 7) {
                            if (count < 2) {
                                showToast(Utils.getString(R.string.请选择玩法));
                                return;
                            }
                        }
                        if (StringMyUtil.isEmptyString(k3_tab_money.getText().toString()) || !MoneyUtils.isMoney(k3_tab_money.getText().toString())) {
                            showToast(Utils.getString(R.string.请输入金额));
                            return;
                        }

                        gf_ma = "";
                        gf_amount = "";
                        gf_rule_id = "";
                        if (index_gf == 0) {
                            //TODO 2019-7-20
                          /*  for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                                if (entry.getValue()) {*/
                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getIsclick()) {
                                    for (GetGroup.GameruleTwoBean bean : officialList) {
                                        if (/*entry.getKey()*/isClickList.get(i).getId().equals(String.valueOf(bean.getId()))) {
                                            gf_rule_id = gf_rule_id + bean.getId() + ",";
                                            gf_amount = gf_amount + k3_tab_money.getText().toString() + ",";
                                        }
                                    }
                                }
                            }
                            gf_rule_id = gf_rule_id.substring(0, gf_rule_id.length() - 1);
                            gf_amount = gf_amount.substring(0, gf_amount.length() - 1);
                        }
                        if (index_gf == 1) {
                            gf_ma = "wu";
                            gf_rule_id = "" + widgetListTwo.get(0).getId();
                            gf_amount = k3_tab_money.getText().toString();
                        }
                        if (index_gf == 2) {
                            gf_rule_id = "" + widgetListTwo.get(0).getId();
                            gf_amount = k3_tab_money.getText().toString();
                         /*   for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                                if (entry.getValue()) {*/
                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getIsclick()) {
                                    for (GetGroup.GameruleTwoBean bean : officialList) {
                                        if (/*entry.getKey()*/isClickList.get(i).getId().equals(String.valueOf(bean.getId()))) {
                                            gf_ma = gf_ma + bean.getRemark() + ",";
                                        }
                                    }
                                }
                            }
                            gf_ma = gf_ma.substring(0, gf_ma.length() - 1);
                        }
                        if (index_gf == 3) {
                            gf_ma = "wu";
                            gf_rule_id = "" + widgetListTwo.get(0).getId();
                            gf_amount = k3_tab_money.getText().toString();
                        }
                        if (index_gf == 4) {
                            gf_rule_id = "" + widgetListTwo.get(0).getId();
                            gf_amount = k3_tab_money.getText().toString();
                        /*    for (Map.Entry<String, Boolean> entry : isClickMap.entrySet()) {
                                if (entry.getValue()) {*/
                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getIsclick()) {
                                    for (GetGroup.GameruleTwoBean bean : officialList) {
                                        if (/*entry.getKey()*/isClickList.get(i).getId().equals(String.valueOf(bean.getId()))) {
                                            gf_ma = gf_ma + bean.getRemark() + ",";
                                        }
                                    }
                                }
                            }
                            gf_ma = gf_ma.substring(0, gf_ma.length() - 1);
                        }
                        if (index_gf == 5) {
                            gf_rule_id = "" + widgetListTwo.get(0).getId();
                            gf_amount = k3_tab_money.getText().toString();
                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getIsclick()) {
                                    for (GetGroup.GameruleTwoBean bean : officialList) {
                                        if (/*entry.getKey()*/isClickList.get(i).getId().equals(String.valueOf(bean.getId()))) {
                                            gf_ma = gf_ma + bean.getRemark() + ",";
                                        }
                                    }
                                }
                            }
                            gf_ma = gf_ma.substring(0, gf_ma.length() - 1);
                        }
                        if (index_gf == 6) {
                            gf_rule_id = "" + widgetListTwo.get(0).getId();
                            gf_amount = k3_tab_money.getText().toString();
                            for (int j = 0; j < isClickList.size(); j++) {
                                if (isClickList.get(j).getIsclick()) {
                                    for (int i = 0; i < officialList.size() / 2; i++) {
                                        if (isClickList.get(j).getId().equals(String.valueOf(officialList.get(i).getId()))) {
                                            gf_ma = gf_ma + officialList.get(i).getRemark() + ",";
                                        }
                                    }
                                }
                            }
                            gf_ma = gf_ma.substring(0, gf_ma.length() - 1) + ";";
                            for (int j = 0; j < isClickList.size(); j++) {
                                if (isClickList.get(j).getIsclick()) {
                                    for (int i = officialList.size() / 2; i < officialList.size(); i++) {
                                        if (/*entry.getKey()*/isClickList.get(j).getId().equals(String.valueOf(officialList.get(i).getId()))) {
                                            gf_ma = gf_ma + officialList.get(i).getRemark() + ",";
                                        }
                                    }
                                }
                            }
                            gf_ma = gf_ma.substring(0, gf_ma.length() - 1);
                        }
                        if (index_gf == 7) {
                            gf_rule_id = "" + widgetListTwo.get(0).getId();
                            gf_amount = k3_tab_money.getText().toString();
                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getIsclick()) {
                                    for (GetGroup.GameruleTwoBean bean : officialList) {
                                        if (/*entry.getKey()*/isClickList.get(i).getId().equals(String.valueOf(bean.getId()))) {
                                            gf_ma = gf_ma + bean.getRemark() + ",";
                                        }
                                    }
                                }
                            }
                            gf_ma = gf_ma.substring(0, gf_ma.length() - 1);
                        }
                        //qishu;
                        final String betTime = TimerUtils.getTimeStyleOne();   //2019-07-10 13:44:16
                        String groupname = grouplisttwo.get(index_gf).getGroupname();
                        String danjia = k3_tab_money.getText().toString();
                        String content = "";
                        String totalnum = k3_tab_num.getText().toString();
                        String totalmoney = String.valueOf(Integer.parseInt(totalnum) * Integer.parseInt(danjia));
                        for (int i = 0; i < isClickList.size(); i++) {
                            if (isClickList.get(i).getIsclick()) {
                                for (GetGroup.GameruleTwoBean bean : officialList) {
                                    if (/*entry.getKey()*/isClickList.get(i).getId().equals(String.valueOf(bean.getId()))) {
                                        content = content + bean.getName() + " ";
                                    }
                                }
                            }
                        }
                        final BetDialog betDialog = new BetDialog(K3Activity.this);
                        betDialog.setGroupname(groupname + ":");
                        betDialog.setQishu(qishu);
                        betDialog.setDanjia(danjia);
                        betDialog.setContent(content);
                        betDialog.setTotalnum(totalnum);
                        betDialog.setTotalmoney(totalmoney);
                        betDialog.setYesOnclickListener(()->{
                                betDialog.dismiss();
                                MyRequest.ReqTouzhu(K3Activity.this, game, type_id, betTime, qishu, gf_amount, gf_rule_id, String.valueOf(index_gf), gf_ma, 0, new MyRequest.MyRequestListener1() {
                                    @Override
                                    public void success1(String content) {
                                        Gson gson = new GsonBuilder().serializeNulls().create();
                                        try {
                                            State state = gson.fromJson(content, State.class);
                                            if (state.getStatus().equals("success")) {
                                                showToast(Utils.getString(R.string.下注成功));
                                                /*isClickMap.clear();*/
                                                isClickList.clear();
                                                for (GetGroup.GameruleTwoBean bean : officialList) {
                                                    //    isClickMap.put(String.valueOf(bean.getId()), false);
                                                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                                                }

                                                k3OfficialAdapter.notifyDataSetChanged();
                                                k3_tab_num.setText("" + 0);
                                                k3_tab_money.setText("");
                                                k3_tab_reset.setText(Utils.getString(R.string.机选));
                                                requestMoney();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    @Override
                                    public void failed1(String failMessage) {
                                        showToast(Utils.getString(R.string.下注失败)+"\n" + failMessage);
                                    }
                                });
                        });
                        betDialog.setNoOnclickListener(()-> betDialog.dismiss());
                        betDialog.show();
                        betDialog.setCanceledOnTouchOutside(false); //外部不可点击

                    }
                }
        });
        //重置按钮
        k3_tab_reset.setOnClickListener(v -> {
            if(getGroup==null){
                return;
            }
                //TODO 2017-19信用重置 机选
                if (!isOfficial) {
                    if (k3_tab_reset.getText().toString().equals(Utils.getString(R.string.重置))) {
                        k3_tab_reset.setText(Utils.getString(R.string.机选));
                        isClickList.clear();
                        if(getGroup==null){
                            return;
                        }
                        for (GetGroup.GameruleBean bean : getGroup.getGameRulelist()) {
                            StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                        }
                        k3RecyclerAdapter.notifyDataSetChanged();
                        k3_tab_money.setText("");
                        k3_tab_num.setText("0");
                    } else {
                        k3_tab_reset.setText(Utils.getString(R.string.重置));
                        Utils.logE("widgetList", "" + widgetList);
                        List<String> idList = new ArrayList<>();
                        for (GetGroup.GameruleBean bean : widgetList) {
                            idList.add(String.valueOf(bean.getId()));
                        }
                        Random random = new Random();
                        if(idList.size()>0){
                            String id = idList.get(random.nextInt(idList.size()));
                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getId().equals(id)) {
                                    StrUtil.isclickReplace(isClickList, id, true);
                                }
                            }
                            k3RecyclerAdapter.notifyDataSetChanged();
                            k3_tab_num.setText("1");
                        }
                    }
                } //TODO 2017-19官方重置 机选
                else {
                    if (k3_tab_reset.getText().toString().equals(Utils.getString(R.string.重置))) {
                        k3_tab_reset.setText(Utils.getString(R.string.机选));
                        // isClickMap.clear();
                        isClickList.clear();
                        for (int i = 0; i < officialList.size(); i++) {
                            //  isClickMap.put(String.valueOf(officialList.get(i).getId()), false);
                            StrUtil.isclickAdd(isClickList, String.valueOf(officialList.get(i).getId()), false);
                        }
                        k3OfficialAdapter.notifyDataSetChanged();
                        k3_tab_money.setText("");
                        k3_tab_num.setText("0");

                    } else {
                        isClickList.clear();
                        for (int i = 0; i < officialList.size(); i++) {
                            StrUtil.isclickAdd(isClickList, String.valueOf(officialList.get(i).getId()), false);
                        }
                        List<Long> idList = new ArrayList<>();
                        for (GetGroup.GameruleTwoBean bean : officialList) {
                            idList.add(bean.getId());
                        }
                        Random random = new Random();
                        if (index_gf == 0
                                || index_gf == 1
                                || index_gf == 2
                                || index_gf == 3
                                || index_gf == 5) {
                            if(idList.size()==0){
                                return;
                            }
                            long id = idList.get(random.nextInt(idList.size()));
                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getId().equals(String.valueOf(id))) ;
                                {
                                    StrUtil.isclickReplace(isClickList, String.valueOf(id), true);
                                }
                            }
                        }
                        if (index_gf == 4) {
                            idList = MyMathUtils.getRandomNum(idList, 3);
                            //    int id = idList.get(random.nextInt(idList.size()));
                            for (int i = 0; i < idList.size(); i++) {
                                for (int j = 0; j < isClickList.size(); j++) {
                                    if (isClickList.get(j).getId().equals(String.valueOf(idList.get(i)))) {
                                        StrUtil.isclickReplace(isClickList, isClickList.get(j).getId(), true);
                                    }
                                }
                            }

                        }
                        if (index_gf == 6) {
                            List<String> idXiaoList = new ArrayList<>();
                            List<String> idDaList = new ArrayList<>();
                            for (int i = 0; i < 6; i++) {
                                idXiaoList.add(String.valueOf(officialList.get(i).getId()));
                            }
                            String id = idXiaoList.get(random.nextInt(idXiaoList.size()));

                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getId().equals(id)) {
                                    StrUtil.isclickReplace(isClickList, isClickList.get(i).getId(), true);
                                }
                            }
                            for (int i = 6; i < 12; i++) {
                                idDaList.add(String.valueOf(officialList.get(i).getId()));
                            }
                            String id1 = idDaList.get(random.nextInt(idDaList.size()));

                            for (int i = 0; i < isClickList.size(); i++) {
                                if (isClickList.get(i).getId().equals(id1)) {
                                    StrUtil.isclickReplace(isClickList, isClickList.get(i).getId(), true);
                                }
                            }
                        }
                        if (index_gf == 7) {
                            idList = MyMathUtils.getRandomNum(idList, 2);
                            //    int id = idList.get(random.nextInt(idList.size()));
                            for (int i = 0; i < idList.size(); i++) {
                                for (int j = 0; j < isClickList.size(); j++) {
                                    if (isClickList.get(j).getId().equals(String.valueOf(idList.get(i)))) {
                                        StrUtil.isclickReplace(isClickList, isClickList.get(i).getId(), true);
                                    }
                                }
                            }
                        }
                        k3OfficialAdapter.notifyDataSetChanged();
                        k3_tab_reset.setText(Utils.getString(R.string.重置));
                        k3_tab_num.setText("1");
                    }
                }
        });

        ll_caizhong_head2_left.setOnClickListener(v -> customPopupWindow.showClassfyPop(ll_caizhong_head2_left, K3Activity.this));
        ll2_k3_head3.setOnClickListener(v -> customPopupWindow.initKuaiSanTodayResultData(K3Activity.this, ll2_k3_head3, Integer.parseInt(type_id)));
        caizhong_head1_menu.setOnClickListener(v -> customPopupWindow.showMenuPop(caizhong_head1_menu, K3Activity.this));
        ll_caizhong_head2_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMoney();
                Animation animation = AnimationUtils.loadAnimation(K3Activity.this, R.anim.rotate_anim);
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
        //TODO 点击重新加载 2019-7-17
        tv_reload.setOnClickListener(v -> {
            initData();
            initCountDownEndPop();//倒计时结束的pop
            mycountdown();  //倒计时
            onResume();
        });
/*        rotate_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMoney();
            }
        });*/
    }

    private void requestMoney() {
        ReqMemberMoney(K3Activity.this, 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                memberMoney = JSONObject.parseObject(content, MemberMoney.class);
                ll_caizhong_head2_center_tv.setText(MoneyUtils.parseMoey(String.valueOf(K3Activity.this.memberMoney.getMemberMoney().getAmount())));
            }

            @Override
            public void failed(String content) {
            }
        });
    }

    //TODO 2019-7-18 官方Recycler回调
    public void OnClickListener(List<IsClickModel> isClickList) {
        int num = 0;
        num = StrUtil.isclickCal(isClickList);
        if (num != 0) {
            k3_tab_reset.setText(Utils.getString(R.string.重置));
        } else {
            k3_tab_reset.setText(Utils.getString(R.string.机选));
        }
        if (index_gf == 4) {
            long totalnum = 0;
            if (num < 3) {
                totalnum = 0;
            } else {
                totalnum = StrUtil.comb(num, 3);
            }
            k3_tab_num.setText("" + totalnum);
        } else if (index_gf == 6) {
            int x = 0, y = 0;//二同号上下所选数
            //TODO 2019-7-19   判断选取的id 是否大于widgetListTwo.get(0).getId() * 100 + 5  《=  x++  > y++
            for (int i = 0; i < isClickList.size(); i++) {
                if (isClickList.get(i).getIsclick()) {
                    if (Integer.parseInt(isClickList.get(i).getId()) <= widgetListTwo.get(0).getId() * 100 + 5) {
                        x++;
                    } else {
                        y++;
                    }
                }
            }

            k3_tab_num.setText("" + x * y);

        } else if (index_gf == 7) {
            long totalnum = 0;
            if (num < 2) {
                totalnum = 0;
            } else {
                totalnum = StrUtil.comb(num, 2);
            }
            k3_tab_num.setText("" + totalnum);
        } else {
            k3_tab_num.setText("" + num);
        }


    }

    //对投注成功后进行数据重置 更新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (requestCode == ReqTouzhu) {
            //    int three = data.getIntExtra("three", 0);
            //设置结果显示框的显示数值
            if (resultCode == 102) {
                /* isClickMap.clear();*/
                isClickList.clear();
                for (GetGroup.GameruleBean bean : getGroup.getGameRulelist()) {
                    //  isClickMap.put(String.valueOf(bean.getId()), false);
                    StrUtil.isclickAdd(isClickList, String.valueOf(bean.getId()), false);
                }
                k3RecyclerAdapter.notifyDataSetChanged();
                k3_tab_num.setText("" + 0);
                k3_tab_money.setText("");
                k3_tab_reset.setText(Utils.getString(R.string.机选));
                requestMoney();
            }
        }
    }

    //TODO 2017-7-18 顶部popwindow
    public void showhead1popup(int x) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.layout_head1popuprecycler, null, false);//引入弹窗布局
        head1recyclerview = vPopupWindow.findViewById(R.id.head1_recyclerview);
        head1popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT, true);
        addBackground();
        View view = findViewById(R.id.rl_head1);
        head1popupWindow.showAsDropDown(view);
        loadgrideDate();
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
    private void loadgrideDate() {
        if (!isOfficial) {
            xy_popheadList.clear();
            for (int i = 0; i < getGroup.getGrouplist().size(); i++) {
                xy_popheadList.add(getGroup.getGrouplist().get(i).getGroupname());
            }
            if (xy_headAdapter == null) {
                xy_headAdapter = new RecyclerViewGridAdapter(this, xy_popheadList, index_xy);
                head1recyclerview.setAdapter(xy_headAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                head1recyclerview.setLayoutManager(gridLayoutManager);
            } else {
                head1recyclerview.setAdapter(xy_headAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                head1recyclerview.setLayoutManager(gridLayoutManager);
            }
            //TODO 2017-8-18 顶部信用headpopwindow 回调
            if (xy_headAdapter != null) {
                xy_headAdapter.setOnItemClickListener((View view, int position) -> {
                    caizhong_head1_title.setText(xy_popheadList.get(position));
                    showcaizhong(position, true);
                    index_xy = position;
                    if (head1popupWindow.isShowing()) {
                        head1popupWindow.dismiss();
                    }
                });
            }
        } else {
            gf_popheadList.clear();
            List<GetGroup.GameruleTwoBean> curList = new ArrayList<>();
            for (int i = 0; i < grouplisttwo.size(); i++) {
                K3GfHeadModel bean = new K3GfHeadModel();
                bean.setTitle(grouplisttwo.get(i).getGroupname());
                curList.clear();
                for (GetGroup.GameruleTwoBean bean1 : gameRulelisttwo) {
                    if (bean1.getGroup_id() == grouplisttwo.get(i).getGroup_id()) {
                        curList.add(bean1);
                    }
                }
                bean.setOdds(getgfHeadOdds(curList));
                switch (i) {
                    case 0:
                        bean.setArr("1,2,3");
                        break;
                    case 1:
                        bean.setArr("1,1,1");
                        break;
                    case 2:
                        bean.setArr("1,1,1");
                        break;
                    case 3:
                        bean.setArr("1,2,3");
                        break;
                    case 4:
                        bean.setArr("2,3,5");
                        break;
                    case 5:
                        bean.setArr("1,1,3");
                        break;
                    case 6:
                        bean.setArr("1,1,3");
                        break;
                    case 7:
                        bean.setArr("1,4,4");
                        break;
                }
                gf_popheadList.add(bean);
            } //TODO 2019-7-19 官方顶部popwindow recycler 初始化完毕

            if (gf_headAdapter == null) {
                gf_headAdapter = new K3GfHeadAdapter(this, gf_popheadList, index_gf);
                head1recyclerview.setAdapter(gf_headAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                head1recyclerview.setLayoutManager(gridLayoutManager);
            } else {
                head1recyclerview.setAdapter(gf_headAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                head1recyclerview.setLayoutManager(gridLayoutManager);
            }
            //TODO 2017-8-19 顶部官方headpopwindow 回调
            if (gf_headAdapter != null) {
                gf_headAdapter.setOnItemClickListener(((view, position) -> {
                    caizhong_head1_title.setText(gf_popheadList.get(position).getTitle());
                    showcaizhong(position, true);
                    index_gf = position;
                    if (head1popupWindow.isShowing()) {
                        head1popupWindow.dismiss();
                    }
                }));
            }
        }
    }

    private String getgfHeadOdds(List<GetGroup.GameruleTwoBean> list) {
        boolean isEqual = true;
        String result = "";
        if (list.size() > 1) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (!String.valueOf(list.get(i).getOdds()).equals(String.valueOf(list.get(i + 1).getOdds()))) {
                    isEqual = false;
                    break;
                }
            }
            if (!isEqual) {
                double min_int = list.get(0).getOdds();
                double max_int = list.get(0).getOdds();
                for (int i = 0; i < list.size(); i++) {
                    if (min_int > list.get(i).getOdds()) {
                        min_int = list.get(i).getOdds();
                    }
                    if (max_int < list.get(i).getOdds()) {
                        max_int = list.get(i).getOdds();
                    }
                }
                result = String.valueOf(min_int) + "-" + String.valueOf(max_int) + Utils.getString(R.string.倍);
            } else {
                result = String.valueOf(list.get(0).getOdds()) + Utils.getString(R.string.倍);
            }
        } else {
            result = String.valueOf(list.get(0).getOdds()) + Utils.getString(R.string.倍);
        }
        return result;
    }


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

    Runnable runnableTime = new Runnable() {
        @Override
        public void run() {
//            if (getGroup != null) {
                if (timeCount > 0&&kjCountDown!=null) {
                    timeCount = (endtime - tqtime) - (System.currentTimeMillis() - sjcha);
                    Utils.logE("timeCount:", "" + timeCount);
                    int mHour = (int) ((timeCount / 1000) / (60 * 60));  //  对3600 取整  就是小时
                    int mMin = (int) (((timeCount / 1000) % (60 * 60)) / 60);//  对3600 取余除以60 就是多出的分
                    int mSecond = (int) ((timeCount / 1000) % 60); //  对60 取余  就是多出的秒
                    String str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
                    k3_head3_tv2.setText(str_time);
                    //   timeCount--;
                    if (timeCount <= 1) {
                   //     timeCount = 0L;
                //        k3_head3_tv2.setText(Utils.getString(R.string.已结束));
                        if (CountDownEndPop != null) {
                            lastQiShuTv.setText(qishu);
                            newQiShuTv.setText((Long.parseLong(qishu) + 1) + "");
                            if (!isFinishing() && CountDownEndPop != null) {
                                CountDownEndPop.showAtLocation(ll1_k3_head3, Gravity.CENTER, 0, 0);
                            }
                            ProgressDialogUtil.darkenBackground(K3Activity.this, 0.5f);
                        }
                        isWaitopen = true;
                        mycountdown();
                        Utils.logE("isWaitopen:", "" + isWaitopen + ",runnableTime");
                    }
                }
                handler.postDelayed(runnableTime, 300);
//            }
        }
    };

    Runnable runnableRandom = new Runnable() {
        @Override
        public void run() {
            if (isWaitopen) {
                if (!StringMyUtil.isEmptyString(qishu)) {
                    long waitOpenQiShu = Long.parseLong(qishu) - 1;
                    k3_head3_tv3.setText(waitOpenQiShu + " "+Utils.getString(R.string.期开奖号码));
                    List<String> list = new ArrayList<String>();
                    kjList.clear();
                    Random random = new Random();

                    kjList.add(shaizis[random.nextInt(6)]);

                    //   kjRecyclerAdapter.notifyDataSetChanged();
                    k3_head3_iv1.setImageResource(kjList.get(0));
                    k3_head3_iv2.setImageResource(kjList.get(0));
                    k3_head3_iv3.setImageResource(kjList.get(0));


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
                //TODO 开奖中   2019-7-17
                if (getGroup != null) {
                    initOpenResult();
                }
                handler.postDelayed(runnableRequestOpen, 8000);
            } else {
                handler.removeCallbacks(runnableRequestOpen);
            }
        }

    };

    Runnable runnableZj = new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name", SharePreferencesUtil.getString(K3Activity.this, "nickname", ""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content){
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
            handler.postDelayed(runnableZj, 5000);
        }
    };

    @Override
    public void onItemClick(View view, int position, LotteryClassModel lotteryClassModel) {
        int type_id = lotteryClassModel.getType_id();
        String isopenOffice = lotteryClassModel.getIsopenOffice();
        String isStart = lotteryClassModel.getIsStart();
        String typename = lotteryClassModel.getTypename();
        int game = lotteryClassModel.getGame();

        ToBetAtyUtils.toBetActivity(K3Activity.this, game, typename, type_id, isopenOffice, isStart);
    finish();
    }

    @Override
    public void onMenuClicked(View view) {
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
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnableRandom);
        handler.removeCallbacks(runnableRequestOpen);
        handler.removeCallbacks(runnableTime);
        handler.removeCallbacks(runnableZj);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        customPopupWindow.selectorId(hbGameClassModel);
    }
}
