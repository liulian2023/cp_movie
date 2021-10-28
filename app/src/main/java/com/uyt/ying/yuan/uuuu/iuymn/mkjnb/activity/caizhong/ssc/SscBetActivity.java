package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.ssc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
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
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.TouzhuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.GfSureBetPopAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscOffcialChooseTwoAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscOfficialAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SscOfficialChooseAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.SureBetPopAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GfSureBetPopMeldol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfficialChooseTwoMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscOfiicialBetMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SureBetPopMeldol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.BetNumUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CustomPopupWindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.NoAlphaProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import pl.droidsonroids.gif.GifImageView;

public class SscBetActivity extends BaseActivity implements View.OnClickListener,CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener, TextWatcher, SscOfficialAdapter.onItemFourClickListener, SscOfficialAdapter.onItemOneClickListener, SscOfficialAdapter.OnQuickClickLintener, SscAdapter.OnRecyclerViewItemClickListener {
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
    private RadioButton liangmianBtn;//两面button
    private RadioButton oneToFiveBtn;//1-5球button
    private RadioButton qianzhonghouBtn;//前后和button
    private LinearLayout openResultLinear;
    private MyCornerTextView sscNumTv1;
    private MyCornerTextView sscNumTv2;
    private MyCornerTextView sscNumTv3;
    private MyCornerTextView sscNumTv4;
    private MyCornerTextView sscNumTv5;

    private TextView betNum;//已选中多少注
    private EditText amountEdit;//投注金额输入框
    private TextView betButton;//下注按钮
    private TextView randomButton;//机选按钮

    private ArrayList<SscModel> zongheList = new ArrayList<>();
    private ArrayList<SscModel> zongheOneList = new ArrayList<>();
    private ArrayList<SscModel> zongheTwoList = new ArrayList<>();
    private ArrayList<SscModel> zongheThreeList = new ArrayList<>();
    private ArrayList<SscModel> zongheFourList = new ArrayList<>();
    private ArrayList<SscModel> zongheFiveList = new ArrayList<>();
    private ArrayList<SscModel> diyiqiuList = new ArrayList<>();
    private ArrayList<SscModel> dierqiuList = new ArrayList<>();
    private ArrayList<SscModel> disanqiuList = new ArrayList<>();
    private ArrayList<SscModel> disiqiuList = new ArrayList<>();
    private ArrayList<SscModel> diwuqiuList = new ArrayList<>();
    private ArrayList<SscModel> qiansanList = new ArrayList<>();
    private ArrayList<SscModel> zhongsanList = new ArrayList<>();
    private ArrayList<SscModel> housanList = new ArrayList<>();
    private ArrayList<ArrayList<SscModel>> allMoDelList = new ArrayList<>();

    private ArrayList<SscModel> diyiqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> dierqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> disanqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> disiqiuSelectList = new ArrayList<>();
    private ArrayList<SscModel> diwuqiuSelectList = new ArrayList<>();
    /*
   recycleView相关控件
    */
    private ArrayList<SscModel> selecterList = new ArrayList<>();//当前选中item容器
    private RecyclerView mRecy;
    private SscAdapter sscAdapter;
    private ArrayList<SscModel> sscModelList = new ArrayList<>();

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
    private TextView pcddTypeTextGf;//选择栏的玩法名
    private ImageView chooseImgGf;//玩法选择pop弹出时,需要旋转的图片
    private ImageView xingYongIma;

    private TextView name;//彩票分类栏的彩票name

    private LinearLayout xingyongLinear;//信用官方玩法切换
    private TextView xingyongTv;
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
    private String isopenOffice;
    private String isStart = "1";

    private String typename;

    private ArrayList<MyCornerTextView> myCornerTextViewArrayList = new ArrayList<>();
    private List<String> openResultList;
    private long jgTime;
//    private PopupWindow fengpanPop;

    private String todayZJ;//是否中奖 1 中奖  0未中

    private int sscaiCount;//1-5球投注限制

    private JSONArray playRuleListFour;


    //    ---------------------------------------------------------------官方相关---------------------------------------------------------------------------
       /*
    玩法类型pop(官方)
     */
    private PopupWindow choosePlayTypePopGf;//玩法选择pop
    private LinearLayout choosePlayTypeGf;//点击弹出玩法类型pop(官方)
    private ArrayList<SscOfficialChooseMedol> sscListOne = new ArrayList<>();//一级列表数据
    private ArrayList<SscOfficialChooseTwoMedol> sscListTwo = new ArrayList<>();//筛选后的二级列表数据(传入适配器的数据)
    private ArrayList<SscOfficialChooseMedol> sscListTwoAll = new ArrayList<>();//二级列表的所有数据
    //    private ArrayList<SscOfficialChooseMedol> sscListThree=new ArrayList<>();
    private ArrayList<SscOfficialChooseMedol> sscListThreeAll = new ArrayList<>();//三级列表的所有数据
    private SscOfficialChooseAdapter sscOfficialChooseAdapter;//一级列表适配器
    private SscOffcialChooseTwoAdapter sscOffcialChooseTwoAdapter;//二级列表适配器(适配器里同时处理三级列表适配器的数据处理)
    //    private SscOffcialChooseThreeAdapter sscOffcialChooseThreeAdapter;
    private RecyclerView recyTwo;//二级recycle
    private RecyclerView recyOne;//一级recycle
    private String typeOneName;//选择玩法后一级列表的name
    private String typeThreeName;//选择玩法后三级列表的name(和typeOneName组合后,显示在actionbar中间)
    private LinearLayout gfRecycleLinear;
    private LinearLayout gfBottomLinear;
    private LinearLayout gfAmountLinear;
    private LinearLayout RecycleLinear;
    private LinearLayout BottomLinear;
    private TextView betShuoMingTv;//倒计时下方的玩法说明


    /*
    官方玩法主列表recycleView(四级列表)
     */
    private RecyclerView officialbetRecy;
    private SscOfficialAdapter sscOfficialAdapter;
    private ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedolArrayList = new ArrayList<>();
    private ArrayList<SscOfiicialBetMedol> sscofficialFourModelList = new ArrayList<>();
    private ArrayList<SscOfiicialBetMedol> officialSelectorList = new ArrayList<>();//选中medol容器

    private String threeId;//三级列表的id,在每次点击三级列表和一级列表时赋值

    private TextView jianTv;
    private EditText officialAmountEt;
    private TextView jiaTv;
    private TextView maxAmountTv;
    private RadioButton yuanBtn;
    private RadioButton jiaoBtn;

    private TextView jixuanGfTv;
    private TextView betNumGfTv;
    private TextView betAmountGfTv;
    private TextView sureBetGf;

    /*
    官方玩法投注清单pop
     */
    private PopupWindow gfSureBetPop;
    private TextView gfLotteryNameTv;//彩票名
    private TextView gfQishuTv;//期数
    private TextView gfBetAmoumtTv;//投注金额(每注多少)
    private TextView gfAllBetNumTv;//投注总数
    private TextView gfAllBetAmountTv;//投注总金额
    private TextView gfSureButton;
    private TextView gfCancelButton;
    private RecyclerView gfSureBetRecy;
    private GfSureBetPopAdapter gfSureBetPopAdapter;
    private ArrayList<GfSureBetPopMeldol> gfSureBetPopMeldolArrayList = new ArrayList<>();

    private long officialAmonnt = 1;//点击加减,输入框的值
    private String odds; //赔率(最高可中)
    private String remark;//玩法列表上方的玩法说明
    private int allBetNum = 0;//总注数(用于判断投注是否正确,allBetnum为0:投注不合法)
    private String allBetAmountStr;//总投注金额(底部显示  投注清单显示)

    private boolean isCrate = true;
    private boolean isZero = false;//用于判断第一次进入时,请求到的倒计时是否为0,为0需要重新请求一次倒计时,否则runnableTime不会执行,导致倒计时一直没有数据
    public static final int ReqTouzhu = 101;
    private TextView stopTv;
    private LinearLayout timeLinear;
    private LinearLayout timeLoadLinear;
    private ConstraintLayout loadingLinear;

   private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
   private TextView is_stop_tv;

    private GifImageView count_down_fail_loading_iv;
    private int failCount=0;
    private PublishSubject failCountObservable =PublishSubject.create();
    private TextView fail_refresh_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssc_bet);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        getIntentData();
        Utils.saveLotteryHistory(game,type_id);
        bindView();//绑定控件
        initChoosePlayTypePop();
        initAllPop();//投注记录 开奖结果 彩票分类等,相关初始化
//        initFenpangPop(); //封盘pop
        getCountTime(null);//请求倒计时
        initRecycle();//recycleView相关
        getMoney();//获取资金信息
        initSureBetPop();//投注清单pop
        initGfSureBetPop();
        initCountDownEndPop();//倒计时结束的pop
        observableFailCount();//订阅倒计时失败次数
    }

        private void getIntentData() {
            user_id = SharePreferencesUtil.getLong(SscBetActivity.this, "user_id", 0l);
            sscaiCount = SharePreferencesUtil.getInt(SscBetActivity.this, "sscaiCount", 8);
         /*
        请求开奖结果,跳转投注记录等等需要的参数
         */
            Intent intent = getIntent();
            type_id = intent.getIntExtra("type_id", 0);
            game = intent.getIntExtra("game", 0);
            typename = intent.getStringExtra("typename");
            isopenOffice = intent.getStringExtra("isopenOffice");//0 没有官方彩
            if(StringMyUtil.isEmptyString(isopenOffice)){
                isopenOffice="";
            }
            isStart = intent.getStringExtra("isStart");
            isStart = StringMyUtil.isEmptyString(isStart)?"1":isStart;
        }

        @Override
    protected void init() {

    }
    private void observableFailCount() {
        failCountObservable
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer integer) {
                        if(integer>6){
                            if(fail_refresh_tv.getVisibility()!=View.VISIBLE){
                                fail_refresh_tv.setVisibility(View.VISIBLE);
                            }
                        }else {
                            if(fail_refresh_tv.getVisibility()!=View.GONE){
                                fail_refresh_tv.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*
    信用玩法列表相关初始化
     */
    private void initRecycle() {
        mRecy.setAdapter(sscAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 60);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //返回position对应的item所占的size
            @Override
            public int getSpanSize(int position) {
                if (liangmianBtn.isChecked()) {
                    if (position == 0 || position == 8 || position == 13 || position == 18 || position == 23 || position == 28) {
                        return 60;
                    } else if (position == 5 || position == 6 || position == 7) {
                        return 20;
                    } else {
                        return 15;
                    }
                } else if (oneToFiveBtn.isChecked()) {
                    if (position == 0 || position == 11 || position == 22 || position == 33 || position == 44) {
                        return 60;
                    } else {
                        return 12;
                    }
                } else {
                    if (position == 0 || position == 6 || position == 12) {
                        return 60;
                    } else {
                        return 12;
                    }
                }
            }


        });
        mRecy.setLayoutManager(gridLayoutManager);
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//添加间距
        sscAdapter.setOnItemClickListener(this);
    }

    /*
    信用玩法类型pop
     */
    private void initChoosePlayTypePop() {
        choosePlayTypePop.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePop.setTouchable(true);//响应内部点击
        choosePlayTypePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = SscBetActivity.this.getWindow().getAttributes();
                if (!CountDownEndPop.isShowing() && !sureBetPop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(SscBetActivity.this, R.anim.img_rotate_end_anim);
                ;
                chooseImg.startAnimation(loadAnimation);
            }

        });

    }

    /*
    请求信用玩法投注列表
     */
    private void requestRule() {
        loadingLinear.setVisibility(View.VISIBLE);
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + getGame()+type_id, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id", user_id);
            data.put("type_id", type_id);
            data.put("game", getGame());
            Utils.docking(data, RequestUtil.REQUEST_wt9, 1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LOTTERY_RULE + getGame()+type_id,content);
                    handRuleJson(content);
                }

                @Override
                public void failed(MessageHead messageHead) {
                    loadingLinear.setVisibility(View.GONE);
                }
            });
        }else {
            handRuleJson(jsonStr);
        }

    }

        private void handRuleJson(String content) {
            loadingLinear.setVisibility(View.GONE);
            sscModelList.clear();
//                selecterList.clear();
            zongheList.clear();
            for (int i = 0; i < allMoDelList.size(); i++) {
                allMoDelList.get(i).clear();
            }
            JSONObject jsonObject1 = JSONObject.parseObject(content);
            JSONArray rulelistAll = jsonObject1.getJSONArray("RulelistAll");
            for (int i = 0; i < rulelistAll.size(); i++) {
                JSONObject jsonObject = rulelistAll.getJSONObject(i);
                JSONArray rulelist = jsonObject.getJSONArray("Rulelist");
                Integer isboth = jsonObject.getInteger("isboth");
                Integer isball = jsonObject.getInteger("isball");
                for (int j = 0; j < rulelist.size(); j++) {
                    JSONObject jsonObject2 = rulelist.getJSONObject(j);
                    Integer group_id = jsonObject2.getInteger("group_id");
                    String name = jsonObject2.getString("name");
                    BigDecimal odds = jsonObject2.getBigDecimal("odds");
                    String id = jsonObject2.getString("id");
                    String groupname = jsonObject2.getString("groupname");
                    Integer balled = jsonObject2.getInteger("balled");
                    if (isboth == 0 && isball == 0) {//前中后的数据
                        if (group_id == 3) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.前中后)+"-" + groupname);
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                            qiansanList.add(sscMedol);
                        } else if (group_id == 4) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.大小单双)+"-" + groupname);
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                            zhongsanList.add(sscMedol);
                        } else if (group_id == 5) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.大小单双)+"-" + groupname);
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                            housanList.add(sscMedol);
                        }

                    } else if (isball == 1 && isboth == 0) {//1-5球的数据
                        if (group_id == 2) {
                            SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.一到五球)+"-" + groupname);
                            if (balled == 1) {
                                diyiqiuList.add(sscMedol);
                            } else if (balled == 2) {
                                dierqiuList.add(sscMedol);
                            } else if (balled == 3) {
                                disanqiuList.add(sscMedol);
                            } else if (balled == 4) {
                                disiqiuList.add(sscMedol);
                            } else {
                                diwuqiuList.add(sscMedol);
                            }
                            ArrayList<String> idList = new ArrayList<>();
                            for (int a = 0; a < selecterList.size(); a++) {
                                String rule_id = selecterList.get(a).getRule_id();
                                idList.add(rule_id);
                            }
                            if (idList.contains(id)) {
                                sscMedol.setStatus(1);
                            }
                        }
                    } else if (isball == 1 && isboth == 1) {
                        SscModel sscMedol = new SscModel(name, odds + "", id, Utils.getString(R.string.两面)+"-" + groupname);
                        if (balled == 0) {
                            zongheList.add(sscMedol);
                        } else if (balled == 1) {
                            zongheOneList.add(sscMedol);
                        } else if (balled == 2) {
                            zongheTwoList.add(sscMedol);
                        } else if (balled == 3) {
                            zongheThreeList.add(sscMedol);
                        } else if (balled == 4) {
                            zongheFourList.add(sscMedol);
                        } else {
                            zongheFiveList.add(sscMedol);
                        }
                        ArrayList<String> idList = new ArrayList<>();
                        for (int a = 0; a < selecterList.size(); a++) {
                            String rule_id = selecterList.get(a).getRule_id();
                            idList.add(rule_id);
                        }
                        if (idList.contains(id)) {
                            sscMedol.setStatus(1);
                        }
                    }
                }
            }

            if (liangmianBtn.isChecked()) {
                SscModel sscMedol = new SscModel(Utils.getString(R.string.总和龙虎和), "", "", Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.总和龙虎和));
                sscModelList.add(sscMedol);
                ArrayList<String> idList = new ArrayList<>();
                for (int a = 0; a < selecterList.size(); a++) {
                    String rule_id = selecterList.get(a).getRule_id();
                    idList.add(rule_id);
                }
                for (int i = 0; i < zongheList.size(); i++) {
                    SscModel sscMedol1 = zongheList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第一球), "", "", Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.第一球)));
                for (int i = 0; i < zongheOneList.size(); i++) {
                    SscModel sscMedol1 = zongheOneList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);

                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第二球), "", "", Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.第二球)));
                for (int i = 0; i < zongheTwoList.size(); i++) {
                    SscModel sscMedol1 = zongheTwoList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);

                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第三球), "", "", Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.第三球)));
                for (int i = 0; i < zongheThreeList.size(); i++) {
                    SscModel sscMedol1 = zongheThreeList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第四球), "", "", Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.第四球)));
                for (int i = 0; i < zongheFourList.size(); i++) {
                    SscModel sscMedol1 = zongheFourList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第五球), "", "", Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.第五球)));
                for (int i = 0; i < zongheFiveList.size(); i++) {
                    SscModel sscMedol1 = zongheFiveList.get(i);
                    String rule_id = sscMedol1.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol1.setStatus(1);
                    }
                    sscModelList.add(sscMedol1);
                }
            } else if (oneToFiveBtn.isChecked()) {
                ArrayList<String> idList = new ArrayList<>();
                for (int a = 0; a < selecterList.size(); a++) {
                    String rule_id = selecterList.get(a).getRule_id();
                    idList.add(rule_id);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第一球), "", "", Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第一球)));
                for (int i = 0; i < diyiqiuList.size(); i++) {
                    SscModel sscMedol = diyiqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第二球), "", "", Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第二球)));
                for (int i = 0; i < dierqiuList.size(); i++) {
                    SscModel sscMedol = dierqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第三球), "", "", Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第三球)));
                for (int i = 0; i < disanqiuList.size(); i++) {
                    SscModel sscMedol = disanqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第四球), "", "", Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第四球)));
                for (int i = 0; i < disiqiuList.size(); i++) {
                    SscModel sscMedol = disiqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.第五球), "", "", Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第五球)));
                for (int i = 0; i < diwuqiuList.size(); i++) {
                    SscModel sscMedol = diwuqiuList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
            } else {
                ArrayList<String> idList = new ArrayList<>();
                for (int a = 0; a < selecterList.size(); a++) {
                    String rule_id = selecterList.get(a).getRule_id();
                    idList.add(rule_id);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.前三), "", "", Utils.getString(R.string.前中后前三)));
                for (int i = 0; i < qiansanList.size(); i++) {
                    SscModel sscMedol = qiansanList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.中三), "", "", Utils.getString(R.string.前中后中三)));
                for (int i = 0; i < zhongsanList.size(); i++) {
                    SscModel sscMedol = zhongsanList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
                sscModelList.add(new SscModel(Utils.getString(R.string.后三), "", "",Utils.getString(R.string.前中后后三)));
                for (int i = 0; i < housanList.size(); i++) {
                    SscModel sscMedol = housanList.get(i);
                    String rule_id = sscMedol.getRule_id();
                    if (idList.contains(rule_id)) {
                        sscMedol.setStatus(1);
                    }
                    sscModelList.add(sscMedol);
                }
            }
            sscAdapter.notifyDataSetChanged();
        }

        /*
        获取用户资金信息
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
    倒计时结束pop相关初始化
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
                if (!gfSureBetPop.isShowing() && !sureBetPop.isShowing() && !choosePlayTypePop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing() && !customPopupWindow.sscTodayOpenResultPop.isShowing() && !choosePlayTypePopGf.isShowing()&&!customPopupWindow.gameRulePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
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
        is_stop_tv=findViewById(R.id.is_stop_tv);
        if(isStart.equals("0")){
            is_stop_tv.setVisibility(View.VISIBLE);
        }else {
            is_stop_tv.setVisibility(View.GONE);
        }
        count_down_fail_loading_iv=findViewById(R.id.count_down_fail_loading_iv);
        fail_refresh_tv=findViewById(R.id.fail_refresh_tv);
        fail_refresh_tv.setOnClickListener(this);

        loadingLinear=findViewById(R.id.loading_linear);
        timeLinear=findViewById(R.id.time_linear);
        timeLoadLinear=findViewById(R.id.time_load_linear);
        stopTv=findViewById(R.id.stop_textview);
        stopTv.setOnClickListener(this);
        actionBarBack = findViewById(R.id.bet_actionbar_back);
        actionBarBack.setOnClickListener(this);
        xingYongIma = findViewById(R.id.xingyong_image);
        hourText = findViewById(R.id.hour);
        minutesText = findViewById(R.id.minutes);
        secondsText = findViewById(R.id.seconds);

        openResultLinear = findViewById(R.id.ssc_open_resule_linear);
        sscNumTv1 = findViewById(R.id.num_one);
        sscNumTv2 = findViewById(R.id.num_two);
        sscNumTv3 = findViewById(R.id.num_three);
        sscNumTv4 = findViewById(R.id.num_four);
        sscNumTv5 = findViewById(R.id.num_five);
        myCornerTextViewArrayList.add(sscNumTv1);
        myCornerTextViewArrayList.add(sscNumTv2);
        myCornerTextViewArrayList.add(sscNumTv3);
        myCornerTextViewArrayList.add(sscNumTv4);
        myCornerTextViewArrayList.add(sscNumTv5);
        for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
            myCornerTextViewArrayList.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayList.get(i).setCornerSize(50);
        }
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

        choosePlayTypeGf = findViewById(R.id.choose_play_type_gf);
        choosePlayTypeGf.setOnClickListener(this);
        //官方玩法底部控件
        jianTv = findViewById(R.id.jian);
        officialAmountEt = findViewById(R.id.ssc_official_amount_edit);
        officialAmountEt.setText(officialAmonnt + "");
        officialAmountEt.setSelection(officialAmountEt.getText().length());//将光标移动到末尾
        jiaTv = findViewById(R.id.jia);
        jianTv.setOnClickListener(this);
        jiaTv.setOnClickListener(this);
        officialAmountEt.addTextChangedListener(this);
        maxAmountTv = findViewById(R.id.max_amount);
        yuanBtn = findViewById(R.id.yuan);
        jiaoBtn = findViewById(R.id.jiao);
        yuanBtn.setOnClickListener(this);
        jiaoBtn.setOnClickListener(this);
        yuanBtn.performClick();
        jixuanGfTv = findViewById(R.id.official_jixuan_tv);
        betNumGfTv = findViewById(R.id.bet_num_gf);
        betAmountGfTv = findViewById(R.id.amount_num_gf);
        sureBetGf = findViewById(R.id.sure_bet_gf);
        sureBetGf.setOnClickListener(this);
        jixuanGfTv.setOnClickListener(this);


        gfRecycleLinear = findViewById(R.id.ssc_gf_recycle_linear);
        gfBottomLinear = findViewById(R.id.ssc_gf_bottom_linear);

        betShuoMingTv = findViewById(R.id.bet_shuoming);

        RecycleLinear = findViewById(R.id.ssc_normal_recycle_linear);
        BottomLinear = findViewById(R.id.ssc_normal_bottom_linear);
        gfAmountLinear = findViewById(R.id.gf_ssc_amount_linear);

        mRecy = findViewById(R.id.happy_bet_recycle);
        sscAdapter = new SscAdapter(sscModelList, selecterList);

        name = findViewById(R.id.name);
        name.setText(typename);

        View view = LayoutInflater.from(this).inflate(R.layout.pcdandan_play_type_pop, null);
        choosePlayTypePop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        xingyongLinear = findViewById(R.id.xingyong_play);
        if(isopenOffice.equals("0")){
            xingyongLinear.setVisibility(View.INVISIBLE);
            xingyongLinear.setClickable(false);
        }else {
            xingyongLinear.setVisibility(View.VISIBLE);
            xingyongLinear.setClickable(true);
        }
        xingyongLinear.setOnClickListener(this);
        xingyongTv = findViewById(R.id.ll_caizhong_head2_right_tv);
        pcddTypeText = findViewById(R.id.pcdd_type);
        chooseImg = findViewById(R.id.choose_img);
        pcddTypeTextGf = findViewById(R.id.pcdd_type_gf);
        chooseImgGf = findViewById(R.id.choose_img_gf);
//        playTypeText=findViewById(R.id.play_type_text);
        memberMoneyText = findViewById(R.id.member_money);

        betNum = findViewById(R.id.bet_num);
        amountEdit = findViewById(R.id.amount_edit);
        betButton = findViewById(R.id.bet_button);
        randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(this);
        betButton.setOnClickListener(this);
        liangmianBtn = view.findViewById(R.id.happy8_liangmian);
        liangmianBtn.setText(Utils.getString(R.string.两面));
        liangmianBtn.setOnClickListener(this);
        oneToFiveBtn = view.findViewById(R.id.happy8_wuxing);
        oneToFiveBtn.setText(Utils.getString(R.string.一到五球));
        oneToFiveBtn.setOnClickListener(this);
        qianzhonghouBtn = view.findViewById(R.id.happy8_zhengma);
        qianzhonghouBtn.setText(getString(R.string.前中后));
        qianzhonghouBtn.setOnClickListener(this);
        liangmianBtn.performClick();
        /*
        开奖结果样式设置
         */
        for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
            myCornerTextViewArrayList.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayList.get(i).setCornerSize(50);
        }
        allMoDelList.add(zongheList);
        allMoDelList.add(zongheOneList);
        allMoDelList.add(zongheTwoList);
        allMoDelList.add(zongheThreeList);
        allMoDelList.add(zongheFourList);
        allMoDelList.add(zongheFiveList);
        allMoDelList.add(diyiqiuList);
        allMoDelList.add(dierqiuList);
        allMoDelList.add(disanqiuList);
        allMoDelList.add(disiqiuList);
        allMoDelList.add(diwuqiuList);
        allMoDelList.add(qiansanList);
        allMoDelList.add(zhongsanList);
        allMoDelList.add(housanList);
    }

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
        customPopupWindow.initGameRule(this, game, typename, 0,false);
        //弹出游戏规则pop
        customPopupWindow.showGameRulePop(this,false);
        ;
        //开奖结果pop视图初始化
        customPopupWindow.initSscTodayResultPop(this, game, type_id,false);
        //跳转两面长龙
        customPopupWindow.toTwoChangLongAty(this, game, type_id);
        //跳转今日输赢
        customPopupWindow.toTodayWinLose(this, game, type_id);
        //跳转在线客服
        customPopupWindow.toOnlineKf(this);
//        jgTime = customPopupWindow.getJgTIme(game, type_id);
        //请求开奖间隔时间
        jgTime = customPopupWindow.getJgTIme(game, type_id);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //官方玩法列表初始化  (第一至第四列表)
        if (isCrate) {
            initOfficialRecycle();
            initGfChooseTypePop();
        }
        if(runnableTime!=null){
        handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime, 300);
        isCrate = false;
        if (isWaitOpen) {
            if(runnableRandom!=null){
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);
        }
        if (isWaitOpen) {
            if(runnableRequestOpen!=null){
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, jgTime);
        }
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        float alpha = lp.alpha;
        if (alpha == 1f) {
            CountDownEndPop.dismiss();
        }
        if(runnableZj!=null){
            handler.removeCallbacks(runnableZj);
        }
        handler.postDelayed(runnableZj, 2000);
    }
    /*
    四级列表recycleVIew,适配器相关
     */
    private void initOfficialRecycle() {
        officialbetRecy = findViewById(R.id.happy_bet_gf_recycle);
        sscOfficialAdapter = new SscOfficialAdapter(this, sscOfiicialBetMedolArrayList, officialSelectorList, sscofficialFourModelList);
        officialbetRecy.setLayoutManager(new LinearLayoutManager(this));
        officialbetRecy.setAdapter(sscOfficialAdapter);
        //四级列表itemTwo点击事件
        sscOfficialAdapter.setOnItemFourClickListener(this);
        //四级列表itemOne点击事件
        sscOfficialAdapter.setOnItemOneClickListener(this);
        sscOfficialAdapter.setOnQuickClickLintener(this);
    }

    /*
    官方玩法类型选择pop
     */
    private void initGfChooseTypePop() {
        // -------------------------------------------------------一级列表-----------------------------------------------------------------
        View view = LayoutInflater.from(this).inflate(R.layout.ssc__official_choose_popwindow, null);
        recyOne = view.findViewById(R.id.ssc_official_chooose_recycle);
        sscOfficialChooseAdapter = new SscOfficialChooseAdapter(sscListOne);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recyOne.setLayoutManager(gridLayoutManager);
        recyOne.setAdapter(sscOfficialChooseAdapter);
        choosePlayTypePopGf = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        choosePlayTypePopGf.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePopGf.setTouchable(true);//响应内部点击
        choosePlayTypePopGf.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing() && !sureBetPop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(SscBetActivity.this, R.anim.img_rotate_end_anim);
                ;
                chooseImgGf.startAnimation(loadAnimation);

            }
        });
        choosePlayTypeGf.setOnClickListener(this);


        //官方玩法选择pop recycleView点击事件(一级列表)
        sscOfficialChooseAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemClick(position);

            }
        });

        // -------------------------------------------------------二级列表-----------------------------------------------------------------

        recyTwo = view.findViewById(R.id.ssc_official_two_recycle);
        sscOffcialChooseTwoAdapter = new SscOffcialChooseTwoAdapter(SscBetActivity.this, sscListTwo,/*sscListThree,*/sscListThreeAll);
        recyTwo.setLayoutManager(new LinearLayoutManager(SscBetActivity.this));
        recyTwo.setAdapter(sscOffcialChooseTwoAdapter);
        getGfPlayType();
        sscOffcialChooseTwoAdapter.setOnThreeItemClick(new SscOffcialChooseTwoAdapter.OnThreeItemClick() {
            @Override
            public void threeClick(View view, ArrayList<SscOfficialChooseMedol> threeList, int position) {
                SscOfficialChooseMedol sscOfficialChooseMedol = threeList.get(position);
                threeItemClick(sscOfficialChooseMedol);
            }
        });


    }

    /*
    三级列表点击处理
     */
    public void threeItemClick(SscOfficialChooseMedol sscOfficialChooseMedol) {
        typeThreeName = sscOfficialChooseMedol.getName();
        pcddTypeTextGf.setText(typeOneName + typeThreeName);
        String remake = sscOfficialChooseMedol.getRemake();
        remark = remake.substring(0, remake.indexOf(";"));
        if (yuanBtn.isChecked()) {
            odds = sscOfficialChooseMedol.getOdds();
        } else {
            odds = new BigDecimal(sscOfficialChooseMedol.getOdds()).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP) + "";
        }
        officialAmonnt = 1;
        officialAmountEt.setText("1");
        String remarkStr = remark + Utils.getString(R.string.分号最高奖金) + "<font color=\"#FF0000\">" + odds + "</font>" + Utils.getString(R.string.元);
        betShuoMingTv.setText(Html.fromHtml(remarkStr));
        maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.元)));
        threeId = sscOfficialChooseMedol.getId();
        initAllStatus();
        getPlayRuleListFour();
        if (choosePlayTypePopGf != null && choosePlayTypePopGf.isShowing()) {
            choosePlayTypePopGf.dismiss();
        }
    }

    /*
  上方玩法选择pop点击事件(一级列表)
 */
    public void itemClick(int position) {
        if(sscListOne.size()!=0){
            if (officialSelectorList != null && officialSelectorList.size() != 0) {
                officialSelectorList.clear();
            }
            SscOfficialChooseMedol sscOfficialChooseMedol1 = sscListOne.get(position);
            String id = sscOfficialChooseMedol1.getId();
            sscListTwo.clear();
            typeOneName = sscOfficialChooseMedol1.getName();
            pcddTypeTextGf.setText(typeOneName + getString(R.string.复式));
//        sscListThree.clear();
            for (int i = 0; i < sscListTwoAll.size(); i++) {
                String parentId = sscListTwoAll.get(i).getParentId();
                if (parentId.equals(id)) {
                    SscOfficialChooseMedol sscOfficialChooseMedol = sscListTwoAll.get(i);
                    sscListTwo.add(new SscOfficialChooseTwoMedol(sscOfficialChooseMedol.getId(), sscOfficialChooseMedol.getName(), sscOfficialChooseMedol.getOdds(), sscOfficialChooseMedol.getParentId()));
                }
            }
            sscOffcialChooseTwoAdapter.notifyDataSetChanged();

            SscOfficialChooseTwoMedol sscOfficialChooseTwoMedol = sscListTwo.get(0);
            String idTwo = sscOfficialChooseTwoMedol.getId();
            ArrayList<SscOfficialChooseMedol> sscOfficialChooseMedolArrayList = sscOfficialChooseTwoMedol.getSscOfficialChooseMedolArrayList();
            for (int j = 0; j < sscListThreeAll.size(); j++) {
                SscOfficialChooseMedol sscOfficialChooseMedol = sscListThreeAll.get(j);
                if (sscOfficialChooseMedol.getParentId().equals(idTwo)) {
                    //根据parentid取出model,并添加到二级列表medol的变量sscOfficialChooseMedolArrayList中
                    if (!sscOfficialChooseMedolArrayList.contains(sscOfficialChooseMedol)) {
                        sscOfficialChooseMedolArrayList.add(sscOfficialChooseMedol);
                    }
                    sscOfficialChooseTwoMedol.setSscOfficialChooseMedolArrayList(sscOfficialChooseMedolArrayList);
                }
            }

            if (sscOfficialChooseMedolArrayList.size() != 0) {
                threeId = sscOfficialChooseMedolArrayList.get(0).getId();
                typeThreeName = sscOfficialChooseMedolArrayList.get(0).getName();
                pcddTypeTextGf.setText(typeOneName + typeThreeName);
                officialAmonnt = 1;
                officialAmountEt.setText("1");
                remark = sscOfficialChooseMedolArrayList.get(0).getRemake();
                remark = remark.substring(0, remark.indexOf(";"));
                if (yuanBtn.isChecked()) {
                    odds = sscOfficialChooseMedolArrayList.get(0).getOdds();
                } else {
                    odds = new BigDecimal(sscOfficialChooseMedolArrayList.get(0).getOdds()).divide(new BigDecimal("10")).setScale(2, RoundingMode.HALF_UP) + "";
                }

                String remarkStr = remark + Utils.getString(R.string.分号最高奖金) + "<font color=\"#FF0000\">" + odds + "</font>" + Utils.getString(R.string.元);
                betShuoMingTv.setText(Html.fromHtml(remarkStr));
                maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.元)));

                allBetAmountStr = "0";
                allBetNum = 0;
                betAmountGfTv.setText("0");
                betNumGfTv.setText("0");
                if (yuanBtn != null) {
                    yuanBtn.performClick();
                }
            }
            getPlayRuleListFour();
        }


    }

    /*
    请求官方玩法 choosePop数据
     */
    private void getGfPlayType() {
        //REQUEST_wt11
        HashMap<String, Object> data = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        String token = SharePreferencesUtil.getString(this, "token", "");
        String domain = SharePreferencesUtil.getString(this, "domain", "");
        data.put("user_id", user_id);
        data.put("game", game);
        data.put("typeId", type_id);
        data.put("source", 2);
        data.put("token", token);
        Utils.docking(data, RequestUtil.REQUEST_wt11, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                //一级列表
                JSONArray playRuleListOne = jsonObject1.getJSONArray("playRuleListOne");
                for (int i = 0; i < playRuleListOne.size(); i++) {
                    JSONObject jsonObject = playRuleListOne.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String id = jsonObject.getString("id");
                    BigDecimal odds = jsonObject.getBigDecimal("odds");
                    String parentId = jsonObject.getString("parentId");
                    SscOfficialChooseMedol sscOfficialChooseMedol = new SscOfficialChooseMedol(id, name, odds + "", parentId);
                    sscListOne.add(sscOfficialChooseMedol);
                    if (i == 0) {
                        sscOfficialChooseMedol.setStatus(1);
//                        pcddTypeTextGf.setText(name);
                        typeOneName = name;
                    }

                }
                //二级列表
                JSONArray playRuleListTwo = jsonObject1.getJSONArray("playRuleListTwo");
                for (int i = 0; i < playRuleListTwo.size(); i++) {
                    JSONObject jsonObject = playRuleListTwo.getJSONObject(i);
                    String parentId = jsonObject.getString("parentId");//上级id
                    String id = jsonObject.getString("id");//自身id
                    String name = jsonObject.getString("name");
                    BigDecimal odds = jsonObject.getBigDecimal("odds");
                    SscOfficialChooseMedol sscOfficialChooseMedol = new SscOfficialChooseMedol(id, name, odds + "", parentId);
                    sscListTwoAll.add(sscOfficialChooseMedol);
                }

                //三级列表
                JSONArray playRuleListThree = jsonObject1.getJSONArray("playRuleListThree");
                for (int i = 0; i < playRuleListThree.size(); i++) {
                    JSONObject jsonObject = playRuleListThree.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String remark = jsonObject.getString("remark");
                    String id = jsonObject.getString("id");
                    String parentId = jsonObject.getString("parentId");
                    BigDecimal odds = jsonObject.getBigDecimal("odds").setScale(2, RoundingMode.HALF_UP);
                    SscOfficialChooseMedol sscOfficialChooseChildMedol = new SscOfficialChooseMedol(id, name, odds + "", parentId);
                    sscOfficialChooseChildMedol.setRemake(remark);
                    sscListThreeAll.add(sscOfficialChooseChildMedol);
                    if (i == 0) {
                        typeThreeName = name;
                    }
                }
                itemClick(0);//手动实现第一个item的点击效果
                sscOfficialChooseAdapter.notifyDataSetChanged();
                sscOffcialChooseTwoAdapter.notifyDataSetChanged();
//                sscOffcialChooseThreeAdapter.notifyDataSetChanged();
                pcddTypeTextGf.setText(typeOneName + typeThreeName);
                playRuleListFour = jsonObject1.getJSONArray("playRuleListFour");
                getPlayRuleListFour();
            }

            @Override
            public void failed(MessageHead messageHead) {
                String info = messageHead.getInfo();
            }
        });
    }

    /*
    四级列表的数据处理
     */
    public void getPlayRuleListFour() {
        sscOfiicialBetMedolArrayList.clear();
        sscofficialFourModelList.clear();
        if (playRuleListFour != null && playRuleListFour.size() != 0) {
            for (int i = 0; i < playRuleListFour.size(); i++) {
                JSONObject jsonObject = playRuleListFour.getJSONObject(i);
                String codes = jsonObject.getString("codes");
                String id = jsonObject.getString("id");
                String isQuick = jsonObject.getString("isQuick");
                String name = jsonObject.getString("name");
                String playRuleId = jsonObject.getString("playRuleId");
                if (playRuleId.equals(threeId)) {
                    SscOfiicialBetMedol sscOfiicialBetMedol = new SscOfiicialBetMedol(codes, id, isQuick, name, playRuleId);
                    if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {//四级列表前5个item属于大小单双,需要单独的布局
                        sscOfiicialBetMedol.setTypeOneName(Utils.getString(R.string.大小单双));
                    }
                    sscOfiicialBetMedolArrayList.add(sscOfiicialBetMedol);
                }
                sscOfficialAdapter.notifyDataSetChanged();
            }
        }

        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            String id = sscOfiicialBetMedol.getId();
            String isQuick = sscOfiicialBetMedol.getIsQuick();
            String playRuleId = sscOfiicialBetMedol.getPlayRuleId();
            String name = sscOfiicialBetMedol.getName();
            String codes = sscOfiicialBetMedol.getCodes();
            String[] split = codes.split(",");
            List<String> strings = Arrays.asList(split);
            SscOfiicialBetMedol ofiicialBetMedol = null;
            for (int j = 0; j < strings.size(); j++) {
                String code = strings.get(j);
                ofiicialBetMedol = new SscOfiicialBetMedol(code, id, isQuick, name, playRuleId);
//                if(seletedMedolMap.size()!=0&&seletedMedolMap.get(id+code)!=null){
//                    if((ofiicialBetMedol.getId()+ofiicialBetMedol.getCodes()).equals((seletedMedolMap.get(id+code).getId()+seletedMedolMap.get(id+code).getCodes()))){
//                        ofiicialBetMedol.setStatus(1);
//                    }
//                }
                sscofficialFourModelList.add(ofiicialBetMedol);
            }

        }
    }

    /*
    请求倒计时
     */
    private void getCountTime(GifImageView count_down_fail_loading_iv) {
        if(count_down_fail_loading_iv!=null){
            count_down_fail_loading_iv.setVisibility(View.VISIBLE);
        }
        if(runnableTime!=null){
            handler.removeCallbacks(runnableTime);
        }
        showLoadingLinear();
        HashMap<String, Object> data = new HashMap<>();
        String domain = SharePreferencesUtil.getString(SscBetActivity.this, "domain", "");
        data.put("type_id", type_id);
        data.put("source", 2);
        Utils.docking(data, RequestUtil.REQUEST_18r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content){
                if(count_down_fail_loading_iv!=null){
                    count_down_fail_loading_iv.setVisibility(View.GONE);
                }
                if(runnableTime!=null){
                    handler.post(runnableTime);
                }
                failCount = 0;
                failCountObservable.onNext(failCount);
                JSONObject jsonObject = JSONObject.parseObject(content);
                String stoptimestr = jsonObject.getString("stoptimestr");//倒计时结束时间
                nowQishu = jsonObject.getString("qishu");//当前期数
                if (StringMyUtil.isEmptyString(nowQishu)) {//期数为空,表示封盘
//                    fengpanPop.showAtLocation(actionBarBack, Gravity.CENTER, 0, 0);
                    stopTv.setVisibility(View.VISIBLE);
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 0.7f);
                    nowQishuText.setText(Utils.getString(R.string.期));
                    lastQiShuText.setText("-- -- -- "+Utils.getString(R.string.期开奖号码));
                    hourText.setText("--");
                    minutesText.setText("--");
                    secondsText.setText("--");
                    handler.removeCallbacks(runnableRandom);
                    handler.removeCallbacks(runnableRequestOpen);
                    handler.removeCallbacks(runnableTime);
                    openResultLinear.setVisibility(View.INVISIBLE);
                } else {
                    stopTv.setVisibility(View.GONE);
                    if (openResultLinear.getVisibility() != View.VISIBLE) {
                        openResultLinear.setVisibility(View.VISIBLE);
                    }
                    if(runnableRandom==null){
                        handler.postDelayed(runnableRandom,150);
                    }
                    if(runnableRequestOpen==null){
                        handler.postDelayed(runnableRequestOpen,jgTime);
                    }
                    if(runnableTime==null){
                        handler.postDelayed(runnableTime,300);
                    }
                    tqtime = jsonObject.getString("tqtime");//封盘时间(倒计时需要减去封盘时间)
                    nowQishuText.setText(nowQishu + Utils.getString(R.string. 期));
                    lastQiShuText.setText((Long.parseLong(nowQishu) - 1) + " "+Utils.getString(R.string.期开奖号码));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//倒计时结束时间
//                        time = Long.parseLong(Utils.getFileData("time"));//服务器时间
                        long nowTime = System.currentTimeMillis();//当前时间
//                        shijiancha = time- nowTime;//服务器时间和当地时间差
                        shijiancha = SharePreferencesUtil.getLong(SscBetActivity.this, "shijiancha", 0l);//服务器时间和当地时间差

                        countTime = millionSeconds + shijiancha - nowTime - (Long.parseLong(tqtime) * 1000);
//                        if(num==1){
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showTimeLInear();

                                }
                            },600);
//                        }
                    /*else {
                            timeLoadLinear.setVisibility(View.GONE);
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
                if(count_down_fail_loading_iv!=null){
                    count_down_fail_loading_iv.setVisibility(View.GONE);
                }
                failCount ++;
                failCountObservable.onNext(failCount);
                showToast(messageHead.getInfo());
                showLoadingLinear();
                if(runnableTime!=null){
                    handler.post(runnableTime);
                }

            }
        });
    }

        private void showLoadingLinear() {
            if (isStart.equals("0")) {
                timeLoadLinear.setVisibility(View.GONE);
                timeLinear.setVisibility(View.GONE);
                is_stop_tv.setVisibility(View.VISIBLE);
            } else {
                timeLoadLinear.setVisibility(View.VISIBLE);
                timeLinear.setVisibility(View.GONE);
                is_stop_tv.setVisibility(View.GONE);
            }
        }

        private void showTimeLInear() {
            if(isStart.equals("0")){
                is_stop_tv.setVisibility(View.VISIBLE);
                timeLoadLinear.setVisibility(View.GONE);
                timeLinear.setVisibility(View.GONE);
            }else {
                timeLinear.setVisibility(View.VISIBLE);
                timeLoadLinear.setVisibility(View.GONE);
                is_stop_tv.setVisibility(View.GONE);
            }
        }

        /*
        请求开奖结果
         */
    private void initOpenResult() {
        if(runnableRequestOpen!=null){
            handler.removeCallbacks(runnableRequestOpen);
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id", type_id);
        data.put("pageNo", 1);
        data.put("pageSize", 20);
        data.put("flag", 1);
        Utils.docking(data, RequestUtil.REQUEST_19r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                failCount=0;
                failCountObservable.onNext(failCount);
                if(runnableRequestOpen!=null){
                    handler.post(runnableRequestOpen);
                }
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("sscaiLotterylist");
                if(happytenLotterylist.size()==0){
                    showToast(getString(R.string.开奖结果获取失败));
                    return;
                }
                    JSONObject jsonObject = happytenLotterylist.getJSONObject(0);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");
                    if (!StringMyUtil.isEmptyString(nowQishu)) {
                        if (Long.parseLong(lotteryqishu) == (Long.parseLong(nowQishu) - 1)) {
                            isWaitOpen = false;
                            String lotteryNo = jsonObject.getString("lotteryNo");
                            String[] split = lotteryNo.split(",");
                            openResultList = Arrays.asList(split);
                            for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
                                myCornerTextViewArrayList.get(i).setText(openResultList.get(i));
                            }
                        } else {
                            isWaitOpen = true;
                        }
                    }


            }

            @Override
            public void failed(MessageHead messageHead) {
                failCount++;
                failCountObservable.onNext(failCount);
                if(runnableRequestOpen!=null){
                    handler.post(runnableRequestOpen);
                }
            }
        });
    }

    /*
    点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stop_textview:
                showToast(Utils.getString(R.string.该彩种已封盘));
                break;
            case R.id.official_jixuan_tv:
                if (jixuanGfTv.getText().equals(getString(R.string.重置))) {
                    initAllStatus();
                    jixuanGfTv.setText(getString(R.string.机选));
                    officialbetRecy.getLayoutManager().scrollToPosition(0);
                } else {
                    int max = 0;//随机item的最大position(四级列表父列表sscOfiicialBetMedolArrayList的position)
                    officialSelectorList.clear();
                    for (int i = 0; i < sscofficialFourModelList.size(); i++) {
                        sscofficialFourModelList.get(i).setStatus(0);
                    }
                    sscOfficialAdapter.notifyDataSetChanged();
                    if (typeOneName.equals(getString(R.string.大小单双))) {
                        max = randomOneItem(1);
                        allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                    } else if (typeOneName.equals(getString(R.string.一星))) {
                        max = randomOneItem(1);
                        allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                    } else if (typeOneName.equals(getString(R.string.前二)) || typeOneName.equals(getString(R.string.后二))) {
                        if (typeThreeName.equals(getString(R.string.直选复式))) {
//                            max = randomMoreItemOneNum();
                            randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.直选和值))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 2);
                        } else if (typeThreeName.equals(getString(R.string.组选复式))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        } else if (typeThreeName.equals(getString(R.string.组选和值))) {
                            max = randomOneItem(1);
                            allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 2);
                        } else if (typeThreeName.equals(getString(R.string.组选包胆))) {
                            max = randomOneItem(1);
                            allBetNum = officialSelectorList.size() * 9;
                        }
                    } else if (typeOneName.equals(getString(R.string.前三)) || typeOneName.equals(getString(R.string.中三)) || typeOneName.equals(getString(R.string.后三))) {
                        if (typeThreeName.equals(getString(R.string.复式))) {
                            randomMoreItemOneNum();
//                            max =  randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.直选和值))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 3);
                        } else if (typeThreeName.equals(getString(R.string.组选和值))) {
                            max = randomOneItem(1);
                            allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 3);
                        } else if (typeThreeName.equals(getString(R.string.组三))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                            allBetNum = allBetNum * 2;
                        } else if (typeThreeName.equals(getString(R.string.组六))) {
                            max = randomOneItem(3);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
                        } else if (typeThreeName.equals(getString(R.string.组选包胆))) {
                            max = randomOneItem(1);
                            allBetNum = officialSelectorList.size() * 54;
                        } else if (typeThreeName.equals(getString(R.string.一码不定位))) {
                            max = randomOneItem(1);
                            allBetNum = officialSelectorList.size();
                        } else if (typeThreeName.equals(getString(R.string.二码不定位))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        }
                    } else if (typeOneName.equals(getString(R.string.四星))) {
                        if (typeThreeName.equals(getString(R.string.复式))) {
//                            max =   randomMoreItemOneNum();//多个布局没个item都要选中,不用滚动到底部
                            randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.组选24))) {
                            max = randomOneItem(4);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 4);
                        } else if (typeThreeName.equals(getString(R.string.组选12))) {
                            randomMoreItemMoreNum(2);
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
                        } else if (typeThreeName.equals(getString(R.string.组选6))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        } else if (typeThreeName.equals(getString(R.string.组选4))) {
//                            max =   randomMoreItemOneNum();
                            randomMoreItemOneNum();
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 1, 2);
                        } else if (typeThreeName.equals(getString(R.string.一码不定位))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                        } else if (typeThreeName.equals(getString(R.string.二码不定位))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        }
                    } else if (typeOneName.equals(getString(R.string.五星))) {
                        if (typeThreeName.equals(getString(R.string.复式))) {
                            randomMoreItemOneNum();
//                            max =    randomMoreItemOneNum();
                            allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
                        } else if (typeThreeName.equals(getString(R.string.组选120))) {
                            max = randomOneItem(5);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 5);
                        } else if (typeThreeName.equals(getString(R.string.组选60))) {
                            max = randomMoreItemMoreNum(3);
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 3, 2);
                        } else if (typeThreeName.equals(getString(R.string.组选30))) {
                            max = randomMoreItemMoreNumWuXin30(2);
                            allBetNum = BetNumUtil.combinZuXuan30WithSectionArray(officialSelectorList, 2, 2);
                        } else if (typeThreeName.equals(getString(R.string.组选20))) {
                            max = randomMoreItemMoreNum(2);
                            allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
                        } else if (typeThreeName.equals(getString(R.string.组选10)) || typeThreeName.equals(getString(R.string.组选5))) {
                            randomMoreItemOneNum();
//                            max =   randomMoreItemOneNum();
                            allBetNum = BetNumUtil.combinZuXuan10_5WithSectionArray(officialSelectorList, typeThreeName, 1, 2);
                        } else if (typeThreeName.equals(getString(R.string.一码不定位))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                        } else if (typeThreeName.equals(getString(R.string.二码不定位))) {
                            max = randomOneItem(2);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                        } else if (typeThreeName.equals(getString(R.string.三码不定位))) {
                            max = randomOneItem(3);
                            allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
                        } else if (typeThreeName.equals(getString(R.string.一帆风顺)) || typeThreeName.equals(getString(R.string.好事成双)) || typeThreeName.equals(getString(R.string.三星报喜)) || typeThreeName.equals(getString(R.string.四季发财))) {
                            max = randomOneItem(1);
                            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
                        }
                    }
                    betNumGfTv.setText(allBetNum + "");
                    jixuanGfTv.setText(getString(R.string.重置));
                    BigDecimal bigDecimal = new BigDecimal(officialAmountEt.getText().toString()).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
                    if (jiaoBtn.isChecked()) {
                        bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
                    }
                    allBetAmountStr = bigDecimal + "";
                    betAmountGfTv.setText(allBetAmountStr);
                    sscOfficialAdapter.notifyDataSetChanged();
                    officialbetRecy.getLayoutManager().scrollToPosition(max);
//                    officialbetRecy.smoothScrollToPosition(max);

                }
                //点击机选 重置后,清空全大小单双清的选中状态
                if (onRandomListener != null) {
                    onRandomListener.onRandomClilck(officialSelectorList);
                }
                break;
            case R.id.yuan:
                yuanBtn.setClickable(false);
                jiaoBtn.setClickable(true);
                String toString = officialAmountEt.getText().toString();
                BigDecimal bigDecimal = BigDecimal.ZERO;//输入框金额bigDecimal值
                if (StringMyUtil.isEmptyString(toString)) {
                    bigDecimal = BigDecimal.ZERO;
                } else {
                    bigDecimal = new BigDecimal(toString)/*.setScale(2, RoundingMode.HALF_UP)*/;
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    BigDecimal highAmount = bigDecimal.multiply(new BigDecimal(odds + "")/*.setScale(2,RoundingMode.HALF_UP)*/);
                    String str = Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.元);
                    maxAmountTv.setText(Html.fromHtml(str));
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    String remarkStr = remark + Utils.getString(R.string.分号最高奖金) + "<font color=\"#FF0000\">" + odds + "</font>" + Utils.getString(R.string.元);
                    betShuoMingTv.setText(Html.fromHtml(remarkStr));
                }
                //底部总投注金额
                String num = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                allBetAmountStr = num;
                if (betAmoumtTv != null) {
                    betAmountGfTv.setText(num);
                }
                break;
            case R.id.jiao:
                yuanBtn.setClickable(true);
                jiaoBtn.setClickable(false);
                String toString1 = officialAmountEt.getText().toString();
                BigDecimal bigDecimal1 = BigDecimal.ZERO;//输入框金额bigDecimal值
                if (StringMyUtil.isEmptyString(toString1)) {
                    bigDecimal1 = BigDecimal.ZERO;
                } else {
                    bigDecimal1 = new BigDecimal(toString1)/*.setScale(2, RoundingMode.HALF_UP)*/;
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    BigDecimal highAmount = bigDecimal1.multiply(new BigDecimal(odds + "").divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP));
                    String str = Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.元);
                    maxAmountTv.setText(Html.fromHtml(str));
                }
                if (!StringMyUtil.isEmptyString(odds)) {
                    BigDecimal highAmount = bigDecimal1.multiply(new BigDecimal(odds + "").divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP));
                    String remarkStr = remark + Utils.getString(R.string.分号最高奖金) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.元);
                    betShuoMingTv.setText(Html.fromHtml(remarkStr));
                }
                //底部总投注金额
                String num1 = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
//                String  num1=   new BigDecimal(allBetAmountStr).divide(new BigDecimal(10+""))+"";
                allBetAmountStr = num1;
                betAmountGfTv.setText(num1);
                break;
            case R.id.jian:
                if (officialAmonnt == 1) {
                    return;
                } else {
                    officialAmonnt--;
                }
                officialAmountEt.setText(officialAmonnt + "");
                //底部总投注金额
                if (yuanBtn.isChecked()) {
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.元)));
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                } else {
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")).divide(new BigDecimal(10 + "")) + "</font>" + Utils.getString(R.string.元)));
                }
                officialAmountEt.setSelection(officialAmountEt.getText().length());//将光标移动到末尾
                officialAmountEt.requestFocus();//获取焦点
                betAmountGfTv.setText(allBetAmountStr);

                break;
            case R.id.jia:
                officialAmonnt++;
                //输入框
                officialAmountEt.setText(officialAmonnt + "");
                //底部总投注金额   //底部最高可中
                if (yuanBtn.isChecked()) {
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")) + "</font>" + Utils.getString(R.string.元)));
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                } else {
                    allBetAmountStr = new BigDecimal(officialAmonnt + "").multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
                    maxAmountTv.setText(Html.fromHtml(Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + new BigDecimal(odds).multiply(new BigDecimal(officialAmonnt + "")).divide(new BigDecimal(10 + "")) + "</font>" + Utils.getString(R.string.元)));
                }

                officialAmountEt.setSelection(officialAmountEt.getText().length());//将光标移动到末尾
                officialAmountEt.requestFocus();//获取焦点
                betAmountGfTv.setText(allBetAmountStr);
                break;
            case R.id.sure_bet_gf:
                int sizeGf = officialSelectorList.size();
                String editTextGf = officialAmountEt.getText().toString();
                if(stopTv.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.当前彩种已封盘));
                }
                else if (sizeGf == 0) {
                    showToast(getString(R.string.请选择玩法));
                } else if (StringMyUtil.isEmptyString(editTextGf)) {
                    showToast(Utils.getString(R.string.请输入下注金额));
                } else {
                    sureBetGf.setClickable(false);
                    if (allBetAmountStr.equals("0")) {
                        if (yuanBtn.isChecked()) {
                            allBetAmountStr = allBetNum * 2 + "";
                        } else {
                            BigDecimal bigDecimal2 = new BigDecimal(allBetNum * 2).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP);
                            allBetAmountStr = bigDecimal2 + "";
                        }
                    }
                    if (yuanBtn.isChecked()) {
                        gfBetAmoumtTv.setText("2");
                    } else {
                        gfBetAmoumtTv.setText("0.2");
//                        allBetAmountStr  =   new BigDecimal(allBetAmountStr)/*.divide(new BigDecimal(10+""))*/+"";
                    }
                    ArrayList<SscOfiicialBetMedol> list = new ArrayList<>();
                    initGfSureBetPopData(list); //投注清单pop的数据处理
                    gfLotteryNameTv.setText(name.getText().toString() + "-" + typeOneName + typeThreeName);//彩票名
                    gfQishuTv.setText(nowQishu);//投注期数
                    gfAllBetNumTv.setText(allBetNum + "");
                    gfAllBetAmountTv.setText(allBetAmountStr);
                    gfSureBetPop.showAtLocation(sureBetGf, Gravity.CENTER, 0, 0);
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 0.5f);
                    hintKbTwo();//隐藏软键盘

                }
                break;
            case R.id.gf_sure_bet_cancel:
                if(gfSureBetPop!=null){
                    gfSureBetPop.dismiss();
                }
                break;
            case R.id.gf_sure_bet_sure:
                gfSureButton.setClickable(false);
                gfSureBetPop.dismiss();
                requestBetGf(officialAmountEt.getText().toString());
                break;
            case R.id.rotate_image:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            case R.id.show_classfy_linear://点击弹出彩票分类pop
                customPopupWindow.showClassfyPop(showClassfyLinear, this);
                break;
            case R.id.xingyong_play:
                if(isopenOffice.equals("0")){
                    showToast(getString(R.string.暂无官方彩));
                }else {
                    if (xingyongTv.getText().equals(getString(R.string.信用玩法))) {
                        choosePlayTypeGf.setVisibility(View.VISIBLE);
                        gfRecycleLinear.setVisibility(View.VISIBLE);
                        gfBottomLinear.setVisibility(View.VISIBLE);
                        gfAmountLinear.setVisibility(View.VISIBLE);
                        choosePlayType.setVisibility(View.GONE);
                        RecycleLinear.setVisibility(View.GONE);
                        BottomLinear.setVisibility(View.GONE);
                        xingyongTv.setText(getString(R.string.官方玩法));
                    } else {
                        choosePlayTypeGf.setVisibility(View.GONE);
                        gfRecycleLinear.setVisibility(View.GONE);
                        gfBottomLinear.setVisibility(View.GONE);
                        gfAmountLinear.setVisibility(View.GONE);
                        choosePlayType.setVisibility(View.VISIBLE);
                        RecycleLinear.setVisibility(View.VISIBLE);
                        BottomLinear.setVisibility(View.VISIBLE);
                        xingyongTv.setText(getString(R.string.信用玩法));
                    }
                    Animation loadAnimationXy = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                    ;
                    loadAnimationXy.setFillAfter(true);//动画结束后,保持结束时的角度
                    xingYongIma.startAnimation(loadAnimationXy);
                }

//                if(clickedNum==1){
//                    initOfficialRecycle();
//                    initGfChooseTypePop();
//                }
//                clickedNum++;
                break;
            case R.id.right_menu:
                customPopupWindow.showMenuPop(rightMenu, this);
                break;
            case R.id.today_open_result:
                customPopupWindow.initSscTodayResultData(this, game, type_id, todayOpenResultLinear);
////                customPopupWindow.showHappy8TodayResultPop(todayOpenResultLinear,this);
                break;
            case R.id.choose_play_type:
                choosePlayTypePop.showAsDropDown(popTargetLinear, Gravity.BOTTOM, 0, 0);
                ProgressDialogUtil.darkenBackground(this, 0.5f);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                ;
                loadAnimation.setFillAfter(true);//动画结束后,保持结束时的角度
                chooseImg.startAnimation(loadAnimation);
                break;
            case R.id.choose_play_type_gf:
                choosePlayTypePopGf.showAsDropDown(popTargetLinear, Gravity.BOTTOM, 0, 0);
                ProgressDialogUtil.darkenBackground(this, 0.5f);
                Animation anima = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                ;
                anima.setFillAfter(true);//动画结束后,保持结束时的角度
                chooseImgGf.startAnimation(anima);
                break;
            case R.id.happy8_liangmian:
                requestRule();
                if (mRecy != null && mRecy.getLayoutManager() != null) {
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
                choosePlayTypePop.dismiss();

                betNum.setText(selecterList.size() + "");
//                playTypeText.setText(hunheRb.getText().toString());
                pcddTypeText.setText(liangmianBtn.getText().toString());

                break;
            case R.id.happy8_wuxing:
                requestRule();
                choosePlayTypePop.dismiss();
                if (mRecy != null) {
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
                betNum.setText(selecterList.size() + "");
//                playTypeText.setText(boseRb.getText().toString());
                pcddTypeText.setText(oneToFiveBtn.getText().toString());

                break;
            case R.id.happy8_zhengma:
                requestRule();
                choosePlayTypePop.dismiss();
                if (mRecy != null) {
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
                betNum.setText(selecterList.size() + "");
                pcddTypeText.setText(qianzhonghouBtn.getText().toString());

                break;
            case R.id.random_button:
                int max = 0;
                if (randomButton.getText().toString().equals(getString(R.string.机选))) {
                    xingyongPlayRandom();
                } else {
                    for (int i = 0; i < sscModelList.size(); i++) {
                        sscModelList.get(i).setStatus(0);
                    }
                    diyiqiuSelectList.clear();
                    dierqiuSelectList.clear();
                    disanqiuSelectList.clear();
                    disiqiuSelectList.clear();
                    diwuqiuSelectList.clear();
                    selecterList.clear();
                    int size = selecterList.size();
                    amountEdit.setText("");
                    betNum.setText(size + "");
                    randomButton.setText(getString(R.string.机选));
                }
                for (int i = 0; i < selecterList.size(); i++) {
                    String rule_id = selecterList.get(i).getRule_id();
                    for (int j = 0; j < sscModelList.size(); j++) {
                        SscModel sscModel = sscModelList.get(j);
                        if (sscModel.getRule_id().equals(rule_id)) {
                            if (j > max) {
                                max = j;
                            }
                        }
                    }
                }
                sscAdapter.notifyDataSetChanged();
                mRecy.getLayoutManager().scrollToPosition(max);
                break;
            case R.id.bet_button:
                if(Utils.isFastClick()){
                    return;
                }
                int size = selecterList.size();
                String editText = amountEdit.getText().toString();
                if(stopTv.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.当前彩种已封盘));
                }
                else if (size == 0) {
                    showToast(getString(R.string.请选择玩法));
                } else if (StringMyUtil.isEmptyString(editText)) {
                    showToast(Utils.getString(R.string.请输入下注金额));
                } else {
                    Collections.sort(selecterList);
                    List<TouzhuModel> touzhuList = new ArrayList<>();
                    for (int i = 0; i < selecterList.size(); i++) {
                        TouzhuModel touzhuModel = new TouzhuModel();
                        List<String> groupname = Arrays.asList(selecterList.get(i).getGroupname().split("-"));
                        if (groupname.size()==2){
                            touzhuModel.setGroupname(groupname.get(1));
                        } else if (groupname.size()==3){
                            touzhuModel.setGroupname(groupname.get(1)+"-"+groupname.get(2));
                        }else {
                            touzhuModel.setGroupname(groupname.get(0));
                        }
                        touzhuModel.setName(selecterList.get(i).getBetType());
                        touzhuModel.setId(String.valueOf(selecterList.get(i).getRule_id()));
                        touzhuModel.setMoney(amountEdit.getText().toString());
                        touzhuList.add(touzhuModel);
                    }
                    Intent intent = new Intent();
                    intent.setClass(SscBetActivity.this, TouzhuActivity.class);
                    intent.putExtra("touzhuList", (Serializable) touzhuList);
                    intent.putExtra("game", String.valueOf(game));
                    intent.putExtra("type_id", String.valueOf(type_id));
                    intent.putExtra("money", amountEdit.getText().toString());
                    intent.putExtra("qishu", nowQishu);
                    intent.putExtra("index", "");
                    intent.putExtra("ma", "");
                    startActivityForResult(intent, ReqTouzhu);

                }
                break;
            case R.id.sure_bet_cancel:
                sureBetPop.dismiss();
                break;
            case R.id.sure_bet_sure:
                sureButton.setClickable(false);
                requestBet(selecterList.size(), amountEdit.getText().toString());
                break;
            case R.id.countdown_pop_sure:
                CountDownEndPop.dismiss();
                break;
            case R.id.bet_actionbar_back:
                finish();
                break;
            case R.id.fail_refresh_tv:
                getCountTime(count_down_fail_loading_iv);
                initOpenResult();
                break;
            default:
                break;
        }
    }

    //对投注成功后进行数据重置 更新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // 这里写信用投注成功后清除数据
            for (int i = 0; i < sscModelList.size(); i++) {
                sscModelList.get(i).setStatus(0);
            }
            selecterList.clear();
            diyiqiuSelectList.clear();
            dierqiuSelectList.clear();
            disanqiuSelectList.clear();
            disiqiuSelectList.clear();
            diwuqiuSelectList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size + "");
            sscAdapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.机选));
            getMoney();//投注后,更新资金信息
        }

    }

    /*
    信用玩法机选按钮处理
     */
    private void xingyongPlayRandom() {
        selecterList.clear();//每次调用都清空选中列表(避免递归时崩溃)
        if(sscModelList.size()==0){
            return;
        }
        int random = new Random().nextInt(sscModelList.size());
        sscModelList.get(random).setStatus(1);
        selecterList.add(sscModelList.get(random));
        int size = selecterList.size();
        betNum.setText(size + "");
        randomButton.setText(Utils.getString(R.string.重置));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            //因为布局中title也放在sscMedolList中,所以可能随机到title,此处做判断,如果随机到的是title,重新随机
            if (betType.equals(Utils.getString(R.string.总和龙虎和)) || betType.equals(Utils.getString(R.string.第一球)) || betType.equals(Utils.getString(R.string.第二球)) || betType.equals(Utils.getString(R.string.第三球)) || betType.equals(Utils.getString(R.string.第四球)) || betType.equals(Utils.getString(R.string.第五球)) || betType.equals(Utils.getString(R.string.前三)) || betType.equals(Utils.getString(R.string.中三)) || betType.equals(Utils.getString(R.string.后三))) {
                xingyongPlayRandom();
            } else {//没有随机到title,跳出递归
                break;
            }
        }

    }

    /**
     * 一个item布局,机选重置数据处理
     *
     * @param ballCount 至少需要选几球(用于生成随机数)
     */
    public int randomOneItem(int ballCount) {
        ArrayList<SscOfiicialBetMedol> allMedolList = new ArrayList<>();
        for (int j = 0; j < sscofficialFourModelList.size(); j++) {
            allMedolList.add(sscofficialFourModelList.get(j));
        }

        for (int k = 0; k < ballCount; k++) {
            int random = new Random().nextInt(allMedolList.size());
            SscOfiicialBetMedol sscOfiicialBetMedol = allMedolList.get(random);
            for (int j = 0; j < sscofficialFourModelList.size(); j++) {
                SscOfiicialBetMedol ofiicialBetMedol = sscofficialFourModelList.get(j);
                if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                    officialSelectorList.add(ofiicialBetMedol);
                    ofiicialBetMedol.setStatus(1);
                }
            }
            allMedolList.remove(sscOfiicialBetMedol);
        }
        int max = 0;
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol medol = officialSelectorList.get(i);
            String id1 = medol.getId();//个十百千id
            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                String id = sscOfiicialBetMedolArrayList.get(j).getId();
                if (id.equals(id1)) {
                    if (j > max) {
                        max = j;
                    }
                }
            }
        }
        return max;
    }

    /*
  机选 重置 多个item布局 数据处理(每个位置同时选择1个)
   */
//    public int randomMoreItemOneNum() {
    public void randomMoreItemOneNum() {
        ArrayList<String> nameLsit = new ArrayList<>();
        ArrayList<SscOfiicialBetMedol> seletedRandomMedolList = new ArrayList<>();//选中一个的item随机到的medol(用于移除相同code的medol)
        HashMap<String, ArrayList<SscOfiicialBetMedol>> allMedolMap = BetNumUtil.seletedMap(sscofficialFourModelList);//将四级列表数据根据name存入map
        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            if (!nameLsit.contains(sscOfiicialBetMedol.getName())) {
                nameLsit.add(sscOfiicialBetMedol.getName());
            }
        }
        for (int i = 0; i < nameLsit.size(); i++) {
            String name = nameLsit.get(i);
            ArrayList<SscOfiicialBetMedol> copyList = new ArrayList<>();
            ArrayList<SscOfiicialBetMedol> list = allMedolMap.get(name);
            for (int j = 0; j < list.size(); j++) {
                copyList.add(list.get(j));
            }
            SscOfiicialBetMedol medolCopy = null;
            for (int j = 0; j < list.size(); j++) {
                SscOfiicialBetMedol medol = list.get(j);
                if (i == 1) {//第二次取随机数时移除之前随机到的code
                    if (seletedRandomMedolList.get(0).getCodes().equals(medol.getCodes())) {
                        copyList.remove(medol);//移除和选中一个item随机到的code一致的medol
                        medolCopy = medol;
                    }
                }

            }

            list.remove(medolCopy);//移除和选中一个item随机到的code一致的medol
            int random = new Random().nextInt(list.size());
            SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
            seletedRandomMedolList.add(sscOfiicialBetMedol);
            for (int k = 0; k < sscofficialFourModelList.size(); k++) {
                SscOfiicialBetMedol ofiicialBetMedol = sscofficialFourModelList.get(k);
                if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                    officialSelectorList.add(ofiicialBetMedol);
                    ofiicialBetMedol.setStatus(1);
                }
            }

        }
//        int max=0;
//        for (int i = 0; i < officialSelectorList.size(); i++) {
//            SscOfiicialBetMedol medol = officialSelectorList.get(i);
//            String id1 = medol.getId();//个十百千id
//            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
//                String id = sscOfiicialBetMedolArrayList.get(j).getId();
//                if(id.equals(id1)){
//                    if(j>max){
//                        max=j;
//                    }
//                }
//            }
//        }
//        return max;

    }

    /**
     * 机选 重置 多个item布局数据处理(每个位置必须选择的个数不同,需要选中多个单位号)
     *
     * @param ballCount 单号位需要选中的个数
     */
    public int randomMoreItemMoreNum(int ballCount) {
        ArrayList<String> nameLsit = new ArrayList<>();
        ArrayList<SscOfiicialBetMedol> seletedRandomMedolList = new ArrayList<>();//选中一个的item随机到的medol(用于移除相同code的medol)
        HashMap<String, ArrayList<SscOfiicialBetMedol>> allMedolMap = BetNumUtil.seletedMap(sscofficialFourModelList);//将四级列表数据根据name存入map
        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            if (!nameLsit.contains(sscOfiicialBetMedol.getName())) {
                nameLsit.add(sscOfiicialBetMedol.getName());
            }
        }
        for (int i = 0; i < nameLsit.size(); i++) {
            String name = nameLsit.get(i);
            ArrayList<SscOfiicialBetMedol> copyList = new ArrayList<>();
            ArrayList<SscOfiicialBetMedol> list = allMedolMap.get(name);
            for (int j = 0; j < list.size(); j++) {
                copyList.add(list.get(j));
            }

//                if(threeName.equals(Utils.getString(R.string.组选12))){
            if (name.equals(Utils.getString(R.string.单号位))) {
                SscOfiicialBetMedol medolCopy = null;//将随机到的medol赋值给medolCopy.用于移除
                for (int j = 0; j < list.size(); j++) {
                    SscOfiicialBetMedol medol = list.get(j);
                    if (seletedRandomMedolList.get(0).getCodes().equals(medol.getCodes())) {
                        copyList.remove(medol);//移除和选中一个item随机到的code一致的medol
                        medolCopy = medol;
                    }
                }
                list.remove(medolCopy);//移除和选中一个item随机到的code一致的medol
                for (int num = 0; num < ballCount; num++) {
                    int random = new Random().nextInt(copyList.size());
                    SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                    for (int k = 0; k < list.size(); k++) {
                        SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                        if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                            officialSelectorList.add(ofiicialBetMedol);
                            ofiicialBetMedol.setStatus(1);
                        }
                    }
                    copyList.remove(sscOfiicialBetMedol);
                    list.remove(sscOfiicialBetMedol);

                }
            } else {
                int random = new Random().nextInt(list.size());
                SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                seletedRandomMedolList.add(sscOfiicialBetMedol);
                for (int k = 0; k < list.size(); k++) {
                    SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                    if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                        officialSelectorList.add(ofiicialBetMedol);
                        ofiicialBetMedol.setStatus(1);
                    }
                }
                copyList.remove(sscOfiicialBetMedol.getCodes());
            }
//                }
        }
        //遍历选中容器,取出id与当前sscOfiicialBetMedolArrayList的对比,如果id相同,并且sscOfiicialBetMedolArrayList的position大于max,将position取出,用于随机后滚动列表到当前position
        int max = 0;
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol medol = officialSelectorList.get(i);
            String id1 = medol.getId();//随机到model的id
            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                String id = sscOfiicialBetMedolArrayList.get(j).getId();
                if (id.equals(id1)) {
                    if (j > max) {
                        max = j;
                    }
                }
            }
        }
        return max;
    }

    /**
     * 机选 重置 五星组选30数据处理(每个位置必须选择的个数不同,需要选中多个二重号)
     *
     * @param ballCount 需要选中二重号的个数
     */


    public int randomMoreItemMoreNumWuXin30(int ballCount) {
        ArrayList<String> nameLsit = new ArrayList<>();
        ArrayList<SscOfiicialBetMedol> seletedRandomMedolList = new ArrayList<>();//选中一个的item随机到的medol(用于移除相同code的medol)
        HashMap<String, ArrayList<SscOfiicialBetMedol>> allMedolMap = BetNumUtil.seletedMap(sscofficialFourModelList);//将四级列表数据根据name存入map
        for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = sscOfiicialBetMedolArrayList.get(i);
            if (!nameLsit.contains(sscOfiicialBetMedol.getName())) {
                nameLsit.add(sscOfiicialBetMedol.getName());
            }
        }
        for (int i = 0; i < nameLsit.size(); i++) {
            String name = nameLsit.get(i);
            ArrayList<SscOfiicialBetMedol> copyList = new ArrayList<>();
            ArrayList<SscOfiicialBetMedol> list = allMedolMap.get(name);
            for (int j = 0; j < list.size(); j++) {
                copyList.add(list.get(j));
            }

            if (name.equals(Utils.getString(R.string.单号位))) {
                ArrayList<SscOfiicialBetMedol> medolCopyList = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    SscOfiicialBetMedol medol = list.get(j);
                    for (int k = 0; k < seletedRandomMedolList.size(); k++) {
                        if (seletedRandomMedolList.get(k).getCodes().equals(medol.getCodes())) {
                            medolCopyList.add(medol);
                        }
                    }
                }
                for (int j = 0; j < medolCopyList.size(); j++) {
                    if (list.contains(medolCopyList.get(j))) {
                        list.remove(medolCopyList.get(j));
                    }
                }
                int random = new Random().nextInt(list.size());
                SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                for (int k = 0; k < list.size(); k++) {
                    SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                    if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                        officialSelectorList.add(ofiicialBetMedol);
                        ofiicialBetMedol.setStatus(1);
                    }
                }
            } else {
                for (int num = 0; num < ballCount; num++) {
                    int random = new Random().nextInt(list.size());
                    SscOfiicialBetMedol sscOfiicialBetMedol = list.get(random);
                    seletedRandomMedolList.add(sscOfiicialBetMedol);
                    for (int k = 0; k < list.size(); k++) {
                        SscOfiicialBetMedol ofiicialBetMedol = list.get(k);
                        if (ofiicialBetMedol.equals(sscOfiicialBetMedol)) {
                            officialSelectorList.add(ofiicialBetMedol);
                            ofiicialBetMedol.setStatus(1);
                        }
                    }
                    list.remove(sscOfiicialBetMedol);
                }

            }
        }
        int max = 0;
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol medol = officialSelectorList.get(i);
            String id1 = medol.getId();//个十百千id
            for (int j = 0; j < sscOfiicialBetMedolArrayList.size(); j++) {
                String id = sscOfiicialBetMedolArrayList.get(j).getId();
                if (id.equals(id1)) {
                    if (j > max) {
                        max = j;
                    }
                }
            }
        }
        return max;
    }

    /**
     * 官方玩法下注
     *
     * @param amount 输入框的倍数
     */

    public void requestBetGf(String amount) {
        NoAlphaProgressDialogUtil.show(SscBetActivity.this,Utils.getString(R.string.正在下注),false);
        String domain = SharePreferencesUtil.getString(SscBetActivity.this, "domain", "");
        String token = SharePreferencesUtil.getString(SscBetActivity.this, "token", "");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String amountType = "";
        if (yuanBtn.isChecked()) {
            amountType = "0";
        } else {
            amountType = "1";
        }
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = df.format(date);

        List<Map<String, Object>> nameList = new ArrayList<Map<String, Object>>();//需要存入map2的参数(map2 需要传入betList)
        Map<String, Object> map2 = new HashMap<String, Object>();//存入ruleId amountType nameList amount的map, 需要存入betList的参数
        List<Map<String, Object>> betList = new ArrayList<Map<String, Object>>();//存入map2的map, 需要作为"betList"参数传入最外层的data map
        ArrayList<String> ruleIdList = new ArrayList<>();

        for (int i = 0; i < officialSelectorList.size(); i++) {
            String id = officialSelectorList.get(i).getId();
            if (!ruleIdList.contains(id)) {
                ruleIdList.add(id);
            }
        }

        //拼接code (拼接个数为ruleIdList的size)
        for (int i = 0; i < ruleIdList.size(); i++) {
            Map<String, Object> map1 = new HashMap<String, Object>();//存入ruleTwoId 和 name的map(最后需要传入nameList)
            String needCode = "";
            for (int j = 0; j < officialSelectorList.size(); j++) {
                SscOfiicialBetMedol sscOfiicialBetMedol = officialSelectorList.get(j);
                if (sscOfiicialBetMedol.getId().equals(ruleIdList.get(i))) {
                    needCode += sscOfiicialBetMedol.getCodes() + ",";
                }
            }
            needCode = needCode.substring(0, needCode.length() - 1);
            map1.put("name", needCode);
            map1.put("ruleTwoId", ruleIdList.get(i));
            nameList.add(map1);
        }
        map2.put("ruleId", threeId);
        map2.put("amountType", amountType);
        map2.put("nameList", nameList);
        map2.put("amount", amount);
        betList.add(map2);

        HashMap<String, Object> data = new HashMap<>();
        data.put("game", 2);
        data.put("userId", user_id);
        data.put("typeId", type_id);
        data.put("lotteryqishu", nowQishu);
        data.put("betList", JSONObject.toJSON(betList));
        data.put("source", 2);
        data.put("amountType", amountType);  //下注类型  0 元  1角
        data.put("curtime", format);//当前时间
        data.put("token", token);//用户token
        Utils.docking(data, RequestUtil.REQUEST_02new, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                NoAlphaProgressDialogUtil.stop(SscBetActivity.this);
                sureBetGf.setClickable(true);
                gfSureButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
                for (int i = 0; i < sscOfiicialBetMedolArrayList.size(); i++) {
                    sscOfiicialBetMedolArrayList.get(i).setStatus(0);
                }
                initAllStatus();
                getMoney();//投注后,更新资金信息
            }

            @Override
            public void failed(MessageHead messageHead) {
                NoAlphaProgressDialogUtil.stop(SscBetActivity.this);
                showToast(messageHead.getInfo());
            }

        });
    }
    /*
    官方玩法初始化所有控件状态(重置 下注成功 点击三级item时调用)
     */
    public void initAllStatus() {
        officialSelectorList.clear();
        int size = officialSelectorList.size();
        for (int i = 0; i < sscofficialFourModelList.size(); i++) {
            sscofficialFourModelList.get(i).setStatus(0);
        }
        officialAmountEt.setText(1 + "");
        betNumGfTv.setText(size + "");
        sscOfficialAdapter.notifyDataSetChanged();
        gfSureBetPop.dismiss();
        jixuanGfTv.setText(Utils.getString(R.string.机选));
        officialAmonnt = 1;
        allBetNum = 0;
        betAmountGfTv.setText(0 + "");
        yuanBtn.performClick();
        if (onBetSucceseListener != null) {
            onBetSucceseListener.onBetSuccese(officialSelectorList);
        }
    }


    /*
    投注清单pop 下注类型的拼接(官方)
    */
    private void initGfSureBetPopData(ArrayList<SscOfiicialBetMedol> list) {
        for (int i = 0; i < officialSelectorList.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = officialSelectorList.get(i);
            String name = sscOfiicialBetMedol.getName();
            if (!list.contains(sscOfiicialBetMedol)) {
                sscOfiicialBetMedol = new SscOfiicialBetMedol();
                sscOfiicialBetMedol.setName(name);
                list.add(sscOfiicialBetMedol);//添加所有选中model
            }
        }
        for (int i = 0; i < list.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = list.get(i);
            for (int j = 0; j < officialSelectorList.size(); j++) {
                SscOfiicialBetMedol model = officialSelectorList.get(j);
                if (sscOfiicialBetMedol.getName().equals(model.getName())) {//如果投注玩法()groupNama一样,则拼接betType
                    String id = model.getId();
                    String modelCodes = model.getCodes();
                    sscOfiicialBetMedol.setId(sscOfiicialBetMedol.getId() + "," + id);
                    String modelCode = sscOfiicialBetMedol.getCodes();
                    if (StringMyUtil.isEmptyString(modelCode)) {//为空时,设为"",否则会显示 null
                        modelCode = "";
                    }
                    sscOfiicialBetMedol.setCodes(modelCode + "," + modelCodes);
                }
            }
        }
        gfSureBetPopMeldolArrayList.clear();

        for (int i = 0; i < list.size(); i++) {
            SscOfiicialBetMedol sscOfiicialBetMedol = list.get(i);
            String name = sscOfiicialBetMedol.getName();
            String codes = sscOfiicialBetMedol.getCodes();
            String substring = codes.substring(1, codes.length());
            String finalStr1 = name + ":" + "<font color=\"#FF0000\">" + substring + "</font>";
            gfSureBetPopMeldolArrayList.add(new GfSureBetPopMeldol(finalStr1));
        }
        /*
        选择排序,去重复(上面遍历sureBetPopMeldolArrayList,会添加多次相同的数据)
         */
        for (int i = 0; i < gfSureBetPopMeldolArrayList.size() - 1; i++) {
            for (int j = i + 1; j < gfSureBetPopMeldolArrayList.size(); j++) {
                if (gfSureBetPopMeldolArrayList.get(i).equals(gfSureBetPopMeldolArrayList.get(j))) {
                    gfSureBetPopMeldolArrayList.remove(j);
                    j--;
                }
            }
        }
        gfSureBetPopAdapter.notifyDataSetChanged();
    }

    /**
     * 信用玩法下注
     *
     * @param size     选中容器的size(下注个数)
     * @param editText 下注金额 (输入框的值)
     */
    private void requestBet(int size, String editText) {
        HashMap<String, Object> data = new HashMap<>();
        String needString = "";
        for (int i = 0; i < size; i++) {
            SscModel sscMedol = selecterList.get(i);
            String rule_id = sscMedol.getRule_id();
            needString += rule_id + ",";
        }
        needString = needString.substring(0, needString.length() - 1);
        String amountStr = "";
        for (int i = 0; i < size; i++) {
            amountStr += editText + ",";
        }
        String domain = SharePreferencesUtil.getString(SscBetActivity.this, "domain", "");
        String token = SharePreferencesUtil.getString(SscBetActivity.this, "token", "");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = System.currentTimeMillis();
        Date date = new Date(l);


        String format = df.format(date);
        amountStr = amountStr.substring(0, amountStr.length() - 1);
        data.put("user_id", user_id);
        data.put("type_id", type_id);
        data.put("rule_id", needString);
        data.put("amount", amountStr);
        data.put("lotteryqishu", nowQishu);
        data.put("source", 2);
        data.put("curtime", format);//当前时间
        data.put("token", token);//用户token
        Utils.docking(data, RequestUtil.REQUEST_02ssc, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                sureButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
                for (int i = 0; i < sscModelList.size(); i++) {
                    sscModelList.get(i).setStatus(0);
                }
                selecterList.clear();
                diyiqiuSelectList.clear();
                dierqiuSelectList.clear();
                disanqiuSelectList.clear();
                disiqiuSelectList.clear();
                diwuqiuSelectList.clear();
                int size = selecterList.size();
                amountEdit.setText("");
                betNum.setText(size + "");
                sscAdapter.notifyDataSetChanged();
                sureBetPop.dismiss();
                randomButton.setText(Utils.getString(R.string.机选));
                getMoney();//投注后,更新资金信息
            }

            @Override
            public void failed(MessageHead messageHead) {
                sureButton.setClickable(true);
                JSONObject headData = messageHead.getData();
                showToast(Utils.getString(R.string.下注失败)+"\n" + messageHead.getInfo());
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
            if(failCount>6){
                return;
            }
//            if(countTime!=0/*||isCrate*/){
            if (countTime <= 0) {
                getCountTime(null);
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
                            ProgressDialogUtil.darkenBackground(SscBetActivity.this, 0.5f);
                        }
                    }
                    isWaitOpen = true;
                }
//            }
            }
            handler.postDelayed(runnableTime, 300);
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
                for (int j = 0; j < myCornerTextViewArrayList.size(); j++) {
                    myCornerTextViewArrayList.get(j).setText(i + "");
                }
            } else {
                for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
                    myCornerTextViewArrayList.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRandom, 150);
        }
    };

    Runnable runnableRequestOpen = new Runnable() {
        @Override
        public void run() {
            if (isWaitOpen&&failCount<=6) {
                initOpenResult();
            } else {
                for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
                    myCornerTextViewArrayList.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRequestOpen, jgTime);
        }
    };
    /*
    是否中奖(中奖后要更新资金信息)
     */
    Runnable runnableZj = new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name", SharePreferencesUtil.getString(SscBetActivity.this, "nickname", ""));
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
        ToBetAtyUtils.toBetActivity(SscBetActivity.this, game, typename, type_id, isopenOffice, isStart);
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
        SscModel sscModel = sscModelList.get(position);
        if (oneToFiveBtn.isChecked()) {
            if (sscModel.getGroupname().equals(Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第一球))) {
                if (sscModel.getStatus() == 1) {
                    diyiqiuSelectList.add(sscModel);
                    if (diyiqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.该玩法一期投注不能超过) + sscaiCount + Utils.getString(R.string.个));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        diyiqiuSelectList.remove(sscModel);
                    }
                } else {
                    diyiqiuSelectList.remove(sscModel);
                }

            } else if (sscModel.getGroupname().equals(Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第二球))) {

                if (sscModel.getStatus() == 1) {
                    dierqiuSelectList.add(sscModel);
                    if (dierqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.该玩法只能同时下注) + sscaiCount + Utils.getString(R.string.个));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        dierqiuSelectList.remove(sscModel);
                    }
                } else {
                    dierqiuSelectList.remove(sscModel);
                }
            } else if (sscModel.getGroupname().equals(Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第三球))) {
                if (sscModel.getStatus() == 1) {
                    disanqiuSelectList.add(sscModel);
                    if (disanqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.该玩法只能同时下注) + sscaiCount + Utils.getString(R.string.个));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        disanqiuSelectList.remove(sscModel);
                    }
                } else {
                    disanqiuSelectList.remove(sscModel);
                }
            } else if (sscModel.getGroupname().equals(Utils.getString(R.string.一到五球)+"-"+Utils.getString(R.string.第四球))) {
                if (sscModel.getStatus() == 1) {
                    disiqiuSelectList.add(sscModel);
                    if (disiqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.该玩法只能同时下注) + sscaiCount + Utils.getString(R.string.个));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        disiqiuSelectList.remove(sscModel);
                    }
                } else {
                    disiqiuSelectList.remove(sscModel);
                }
            } else {
                if (sscModel.getStatus() == 1) {
                    diwuqiuSelectList.add(sscModel);
                    if (diwuqiuSelectList.size() > sscaiCount) {
                        showToast(getString(R.string.该玩法只能同时下注) + sscaiCount + Utils.getString(R.string.个));
//                    SscModel sscModel1 = sscMedolList.get(position);
                        sscModel.setStatus(0);
                        selecterList.remove(sscModel);
                        diwuqiuSelectList.remove(sscModel);
                    }
                } else {
                    diwuqiuSelectList.remove(sscModel);
                }
            }


        }
        //||dierqiuSelectList.size()>8||disanqiuSelectList.size()>8||disiqiuSelectList.size()>8||diwuqiuSelectList.size()>8
        int size = selecterList.size();
        betNum.setText(size + "");//当前选中了多少注,直接设置为seleterList的size(selecterList在每次点击选中item的时候会将当前的position对应的model保存,每次取消选中的时候,将对应的medol移除)

        if (size != 0) {
            randomButton.setText(Utils.getString(R.string.重置));
        } else {
            randomButton.setText(Utils.getString(R.string.机选));
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
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                betButton.setClickable(true);
            }
        });

    }

    /*
    投注清单pop(官方)
    */
    private void initGfSureBetPop() {
        gfSureBetPopAdapter = new GfSureBetPopAdapter(gfSureBetPopMeldolArrayList);
        View view = LayoutInflater.from(this).inflate(R.layout.gf_sure_bet_pop, null);
        View footView = LayoutInflater.from(view.getContext()).inflate(R.layout.gf_sure_bet_pop_recycle_foot, null);
        View headView = LayoutInflater.from(view.getContext()).inflate(R.layout.gf_sure_bet_pop_recycle_head, null);
        gfSureBetPopAdapter.addHeaderView(headView);
        gfSureBetPopAdapter.addFooterView(footView);
        gfLotteryNameTv = headView.findViewById(R.id.gf_lottery_name);
        gfQishuTv = headView.findViewById(R.id.gf_lottery_qishu);
        gfBetAmoumtTv = headView.findViewById(R.id.gf_bet_amount);
        gfAllBetNumTv = footView.findViewById(R.id.gf_all_bet_num);
        gfAllBetAmountTv = footView.findViewById(R.id.gf_all_bet_amout);
        gfCancelButton = view.findViewById(R.id.gf_sure_bet_cancel);
        gfSureButton = view.findViewById(R.id.gf_sure_bet_sure);
        gfSureBetRecy = view.findViewById(R.id.gf_sure_bet_pop_recycly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        gfSureBetRecy.setLayoutManager(linearLayoutManager);
        gfSureBetRecy.setAdapter(gfSureBetPopAdapter);
        gfCancelButton.setOnClickListener(this);
        gfSureButton.setOnClickListener(this);
        gfSureBetPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        gfSureBetPop.setAnimationStyle(R.style.pop_scale_animation);
        gfSureBetPop.setTouchable(true);//响应内部点击
        gfSureBetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing() || !choosePlayTypePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(SscBetActivity.this, 1f);
                }
                gfSureButton.setClickable(true);
                sureBetGf.setClickable(true);
                allBetAmountStr = "0";
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
        return 2;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initAllPop();
        if (!CountDownEndPop.isShowing()) {
            ProgressDialogUtil.darkenBackground(this, 1f);
        }
    }

/*    @Override
    public void onNetChange(boolean netWorkState) {
        super.onNetChange(netWorkState);
        if (!netWorkState) {
            showtoast(Utils.getString(R.string.当前网络不可用,请检查网络));
            findViewById(R.id.no_network).setVisibility(View.VISIBLE);
            ProgressDialogUtil.show(this);
            handler.removeCallbacks(runnableRandom);
            handler.removeCallbacks(runnableRequestOpen);
            handler.removeCallbacks(runnableZj);
            handler.removeCallbacks(runnableTime);
        } else {
            findViewById(R.id.no_network).setVisibility(View.GONE);
            ProgressDialogUtil.stop(this);
            if(runnableRandom==null){

                handler.postDelayed(runnableRandom,150);
            }
            if(runnableRequestOpen==null){
                handler.postDelayed(runnableRequestOpen,jgTime);
            }
            if(runnableZj==null){

                handler.postDelayed(runnableZj,3000);
            }if(runnableTime==null){
                handler.postDelayed(runnableTime,300);
            }
        }
    }*/

    /*
    官方玩法金额输入框监听
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String toString = s.toString();
        String newOdds = "";
        BigDecimal bigDecimal = BigDecimal.ZERO;//输入框金额bigDecimal值
        if (StringMyUtil.isEmptyString(toString)) {
            bigDecimal = BigDecimal.ZERO;
        } else {
            //输入框最高倍数限制10000
            if(Long.parseLong(toString)>=10000){
                //在输入监听时setText需要暂时先移除监听器,避免造成死循环
                toString=10000+"";
                //移除监听器
                officialAmountEt.removeTextChangedListener(this);
                //设置需要设置的值
                officialAmountEt.setText("10000");
                officialAmountEt.setSelection(officialAmountEt.getText().length());//将光标移动到末尾
                //重新设置监听器
                officialAmountEt.addTextChangedListener(this);
            }
            bigDecimal = new BigDecimal(toString)/*.setScale(2, RoundingMode.HALF_UP)*/;
            officialAmonnt=Long.parseLong(toString);
        }
        if (!StringMyUtil.isEmptyString(odds)) {
            if (yuanBtn.isChecked()) {
                newOdds = odds;
                allBetAmountStr = bigDecimal.multiply(new BigDecimal(allBetNum * 2 + "")).setScale(2, RoundingMode.HALF_UP) + "";
            } else if (jiaoBtn.isChecked()) {
                newOdds = new BigDecimal(odds).divide(new BigDecimal(10 + "")) + "";
                allBetAmountStr = bigDecimal.multiply(new BigDecimal(allBetNum * 2 + "")).divide(new BigDecimal(10 + "")).setScale(2, RoundingMode.HALF_UP) + "";
            }
        }
//        if(StringMyUtil.isEmptyString(allBetAmountStr)){
//            allBetAmountStr =0+"";
//        }
        betAmountGfTv.setText(allBetAmountStr);
        if (!StringMyUtil.isEmptyString(newOdds)) {
            BigDecimal highAmount = bigDecimal.multiply(new BigDecimal(newOdds + "").setScale(2, RoundingMode.HALF_UP));
            String str = Utils.getString(R.string.最高可中) + "<font color=\"#FF0000\">" + highAmount + "</font>" + Utils.getString(R.string.元);
            maxAmountTv.setText(Html.fromHtml(str));
        }
    }

    /*
    投注列表itemTwo点击事件(大小单双复式之外的玩法列表)
     */
    @Override
    public void onItemFourClick(View view, SscOfiicialBetMedol sscOfiicialBetMedol, int position) {
//        int num=0;
        if (typeOneName.equals(getString(R.string.一星))) {
            allBetNum = BetNumUtil.oneToOne(officialSelectorList);
        } else if (typeOneName.equals(getString(R.string.前二)) || typeOneName.equals(getString(R.string.后二))) {
            if (typeThreeName.equals(getString(R.string.直选复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.直选和值))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 2);
//                allBetNum+=num;
            } else if (typeThreeName.equals(getString(R.string.组选复式))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.组选和值))) {
                allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 2);
            } else if (typeThreeName.equals(getString(R.string.组选包胆))) {
                if (officialSelectorList.size() >= 1) {
                    for (int i = 0; i < officialSelectorList.size(); i++) {
                        officialSelectorList.get(i).setStatus(0);
                    }
                    officialSelectorList.clear();
                    sscOfiicialBetMedol.setStatus(1);
                    officialSelectorList.add(sscOfiicialBetMedol);
                    allBetNum = officialSelectorList.size() * 9;
                } else {
                    allBetNum = officialSelectorList.size() * 9;
                }
            }
        } else if (typeOneName.equals(getString(R.string.前三)) || typeOneName.equals(getString(R.string.中三)) || typeOneName.equals(getString(R.string.后三))) {
            if (typeThreeName.equals(getString(R.string.复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.直选和值))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSelectorList, true, 3);
            } else if (typeThreeName.equals(getString(R.string.组选和值))) {
                allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSelectorList, 3);
            } else if (typeThreeName.equals(getString(R.string.组三))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
                allBetNum = allBetNum * 2;
            } else if (typeThreeName.equals(getString(R.string.组六))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
            } else if (typeThreeName.equals(getString(R.string.组选包胆))) {
                if (officialSelectorList.size() >= 1) {
                    for (int i = 0; i < officialSelectorList.size(); i++) {
                        officialSelectorList.get(i).setStatus(0);
                    }
                    officialSelectorList.clear();
                    sscOfiicialBetMedol.setStatus(1);
                    officialSelectorList.add(sscOfiicialBetMedol);
                    allBetNum = officialSelectorList.size() * 54;
                } else {
                    allBetNum = officialSelectorList.size() * 54;
                }
            } else if (typeThreeName.equals(getString(R.string.一码不定位))) {
                allBetNum = officialSelectorList.size();
            } else if (typeThreeName.equals(getString(R.string.二码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            }
        } else if (typeOneName.equals(getString(R.string.四星))) {
            if (typeThreeName.equals(getString(R.string.复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.组选24))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 4);
            } else if (typeThreeName.equals(getString(R.string.组选12))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
            } else if (typeThreeName.equals(getString(R.string.组选6))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.组选4))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 1, 2);
            } else if (typeThreeName.equals(getString(R.string.一码不定位))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            } else if (typeThreeName.equals(getString(R.string.二码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            }
        } else if (typeOneName.equals(getString(R.string.五星))) {
            if (typeThreeName.equals(getString(R.string.复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSelectorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.组选120))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 5);
            } else if (typeThreeName.equals(getString(R.string.组选60))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 3, 2);
            } else if (typeThreeName.equals(getString(R.string.组选30))) {
                allBetNum = BetNumUtil.combinZuXuan30WithSectionArray(officialSelectorList, 2, 2);
            } else if (typeThreeName.equals(getString(R.string.组选20))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSelectorList, 2, 2);
            } else if (typeThreeName.equals(getString(R.string.组选10)) || typeThreeName.equals(getString(R.string.组选5))) {
                allBetNum = BetNumUtil.combinZuXuan10_5WithSectionArray(officialSelectorList, typeThreeName, 1, 2);
            } else if (typeThreeName.equals(getString(R.string.一码不定位))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            } else if (typeThreeName.equals(getString(R.string.二码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.三码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
            } else if (typeThreeName.equals(getString(R.string.一帆风顺)) || typeThreeName.equals(getString(R.string.好事成双)) || typeThreeName.equals(getString(R.string.三星报喜)) || typeThreeName.equals(getString(R.string.四季发财))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            }
        }
        betNumGfTv.setText(allBetNum + "");
        BigDecimal bigDecimal = new BigDecimal(officialAmountEt.getText().toString()).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
        if (jiaoBtn.isChecked()) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
        }
        allBetAmountStr = bigDecimal + "";
        betAmountGfTv.setText(allBetAmountStr);
        if (allBetNum == 0) {
            jixuanGfTv.setText(getString(R.string.机选));
        } else {
            jixuanGfTv.setText(getString(R.string.重置));
        }
    }

    /*
    全大小单双清点击回调o
    */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onQuickClick(View view, ArrayList<SscOfiicialBetMedol> officialSeletorList) {
        if (typeOneName.equals(getString(R.string.一星))) {
            allBetNum = BetNumUtil.oneToOne(officialSeletorList);
        } else if (typeOneName.equals(getString(R.string.前二)) || typeOneName.equals(getString(R.string.后二))) {
            if (typeThreeName.equals(getString(R.string.直选复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.直选和值))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSeletorList, true, 2);
//                allBetNum+=num;
            } else if (typeThreeName.equals(getString(R.string.组选复式))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            } else if (typeThreeName.equals(getString(R.string.组选和值))) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSeletorList, 2);
                }
            }
        } else if (typeOneName.equals(getString(R.string.前三)) || typeOneName.equals(getString(R.string.中三)) || typeOneName.equals(getString(R.string.后三))) {
            if (typeThreeName.equals(getString(R.string.复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(getString(R.string.直选和值))) {
                allBetNum = BetNumUtil.zhiXuanHeZhi(officialSeletorList, true, 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选和值))) {
                allBetNum = (int) BetNumUtil.zhuXuanHeZhi(officialSeletorList, 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.组三))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
                allBetNum = allBetNum * 2;
            } else if (typeThreeName.equals(Utils.getString(R.string.组六))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.一码不定位))) {
                allBetNum = officialSeletorList.size();
            } else if (typeThreeName.equals(Utils.getString(R.string.二码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            }
        } else if (typeOneName.equals(Utils.getString(R.string.四星))) {
            if (typeThreeName.equals(Utils.getString(R.string.复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选24))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 4);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选12))) {
                allBetNum = (int) BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 2, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选6))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选4))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 1, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.一码不定位))) {
                allBetNum = BetNumUtil.oneToOne(officialSeletorList);
            } else if (typeThreeName.equals(Utils.getString(R.string.二码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 2);
            }
        } else if (typeOneName.equals(Utils.getString(R.string.五行))) {
            if (typeThreeName.equals(Utils.getString(R.string.复式))) {
                allBetNum = BetNumUtil.zhiXuanFuShi(officialSeletorList, typeOneName);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选120))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSeletorList.size(), 5);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选60))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 3, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选30))) {
                allBetNum = BetNumUtil.combinZuXuan30WithSectionArray(officialSeletorList, 2, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选20))) {
                allBetNum = BetNumUtil.combinZuXuan12_4_60_20WithSectionArray(officialSeletorList, 2, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.组选10)) || typeThreeName.equals(Utils.getString(R.string.组选5))) {
                allBetNum = BetNumUtil.combinZuXuan10_5WithSectionArray(officialSeletorList, typeThreeName, 1, 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.一码不定位))) {
                allBetNum = BetNumUtil.oneToOne(officialSeletorList);
            } else if (typeThreeName.equals(Utils.getString(R.string.二码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 2);
            } else if (typeThreeName.equals(Utils.getString(R.string.三码不定位))) {
                allBetNum = (int) BetNumUtil.combineWithArraySize(officialSelectorList.size(), 3);
            } else if (typeThreeName.equals(Utils.getString(R.string.一帆风顺)) || typeThreeName.equals(Utils.getString(R.string.好事成双)) || typeThreeName.equals(Utils.getString(R.string.三星报喜)) || typeThreeName.equals(Utils.getString(R.string.四季发财))) {
                allBetNum = BetNumUtil.oneToOne(officialSelectorList);
            }

        }
        betNumGfTv.setText(allBetNum + "");
        BigDecimal bigDecimal = new BigDecimal(officialAmountEt.getText().toString()).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
        if (jiaoBtn.isChecked()) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
        }
        allBetAmountStr = bigDecimal + "";
        betAmountGfTv.setText(allBetAmountStr);
        if (allBetNum == 0) {
            jixuanGfTv.setText(Utils.getString(R.string.机选));
        } else {
            jixuanGfTv.setText(Utils.getString(R.string.重置));
        }
    }

    /*
    投注列表itemOne(大小单双复式)点击事件(四级列表)
     */

    @Override
    public void onItemOneClick(View view, ArrayList<SscOfiicialBetMedol> sscOfiicialBetMedols, int position) {
        allBetNum = BetNumUtil.oneToOne(officialSelectorList);
        betNumGfTv.setText(allBetNum + "");
        String toString = officialAmountEt.getText().toString();
        if (StringMyUtil.isEmptyString(toString)) {
            toString = "1";
        }
        BigDecimal bigDecimal = new BigDecimal(toString).multiply(new BigDecimal(allBetNum + "")).multiply(new BigDecimal(2 + "")).setScale(2, RoundingMode.HALF_UP);
        if (jiaoBtn.isChecked()) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10 + ""));
        }
        allBetAmountStr = bigDecimal + "";
        betAmountGfTv.setText(allBetAmountStr);
        if (allBetNum == 0) {
            jixuanGfTv.setText(getString(R.string.机选));
        } else {
            jixuanGfTv.setText(getString(R.string.重置));
        }
    }

        @Override
        public void onNetChange(boolean netWorkState) {

        }
        @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
        public void updateXian(HbGameClassModel hbGameClassModel){
            customPopupWindow.selectorId(hbGameClassModel);
        }
        //点击机选,清空全大小单双清状态的回调(在四级列表适配器回调)
    public static interface OnRandomListener {
        void onRandomClilck(ArrayList<SscOfiicialBetMedol> selectedList);
    }

    SscBetActivity.OnRandomListener onRandomListener = null;

    public void setOnRandomListener(OnRandomListener onRandomListener) {
        this.onRandomListener = onRandomListener;
    }


    //点击机选,清空全大小单双清状态的回调(在四级列表适配器回调)
    public static interface onBetSucceseListener {
        void onBetSuccese(ArrayList<SscOfiicialBetMedol> selectedList);
    }

    SscBetActivity.onBetSucceseListener onBetSucceseListener = null;

    public void setOnBetSucceseListener(SscBetActivity.onBetSucceseListener onBetSucceseListener) {
        this.onBetSucceseListener = onBetSucceseListener;
    }
}


