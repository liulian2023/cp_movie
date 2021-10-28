package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.pcDanDan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
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
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.TouzhuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.PcDanDanRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PcDanDanRecycleModel;
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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PcDanDanBetActivity extends BaseActivity implements View.OnClickListener, CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener, CommonAdapter.OnRecyclerViewItemClickListener {
    private TextView hourText;//倒计时小时数
    private TextView minutesText;//倒计时分钟数
    private TextView secondsText;//倒计时秒数
    private LinearLayout openResultLinear;
    private MyCornerTextView numOne;//第一个开奖结果的textView
    private MyCornerTextView numTwo;//第二个开奖结果的textView
    private MyCornerTextView numThree;//第三个开奖结果的textView
    private MyCornerTextView numHeZhi;//开奖结果和值的textView
    private long countTime;//倒计时时间
    private TextView nowQishuText;//当前期数
    private TextView lastQiShuText;//上一期期数(用于显示在滚动开奖结果的上方,并且用于判断是否需要滚动(lastQiShuText与每10秒请求的开奖结果中第一个数据的lotteryqishu相同时,表示已经开奖,需要停止滚动))
    private String nowQishu;//当前期数

    private boolean isWaitOpen = true;//是否在等待开奖(初始值必须为true,否则所有需要判断isWaitOpen的runnable都不会循环操作,导致开奖号码一直停在第一次赋值的时候)
    private String one;//第一个开奖结果的值
    private String two;//第二个开奖结果的值
    private String three;//第三个开奖结果的值
    private ImageView rotateImg;//余额刷新图片
    private int num = 1;//用于判断是否是第一次请求倒计时接口
    private LinearLayout showClassfyLinear;//点击弹出彩种分类pop
    private TextView rightMenu;//点击弹出右侧菜单pop
    private LinearLayout todayOpenResultLinear;//点击弹出今日开奖结果
    private LinearLayout choosePlayType;//点击弹出玩法类型pop
    private LinearLayout popTargetLinear;//玩法类型pop定位控件
    /*
    recycleView相关控件
     */
    private ArrayList<PcDanDanRecycleModel> selecterList = new ArrayList<>();//当前选中item容器
    private RecyclerView mRecy;
    private PcDanDanRecycleAdapter pcDanDanRecycleAdapter;
    private ArrayList<PcDanDanRecycleModel> pcDanDanRecycleModelArrayList = new ArrayList<>();
    /*
    玩法类型pop
     */
    private PopupWindow choosePlayTypePop;
    private RadioButton hunheRb;//混合button
    private RadioButton boseRb;//波色button
    private RadioButton temaRb;//特码button

    private TextView playTypeText;//item上面的玩法名称textView

    private TextView betNum;//已选中多少注
    private EditText amountEdit;//投注金额输入框
    private TextView betButton;//下注按钮
    private TextView randomButton;//机选按钮

    private TextView memberMoneyText;//用户余额
    private TextView pcddTypeText;//选择栏的玩法名
    private ImageView chooseImg;//玩法选择pop弹出时,需要旋转的图片
    /*
    下注清单pop
     */
    private PopupWindow sureBetPop;//
    private TextView lotteryNameTv;//彩票名
    private TextView qishuTv;
    private TextView betAmoumtTv;
    private TextView gameTypeTv;
    private TextView ruleNameTv;
    private TextView gameTypeTv2;
    private TextView ruleNameTv2;
    private TextView gameTypeTv3;
    private TextView ruleNameTv3;
    private LinearLayout gameTypeLInear;
    private LinearLayout gameTypeLInear2;
    private LinearLayout gameTypeLInear3;
    private TextView allBetNumTv;
    private TextView allBetAmountTv;
    private TextView sureButton;
    private TextView cancelButton;

    private TextView name;//彩票分类栏的彩票name

    private LinearLayout xingyongLinear;//信用官方玩法切换

    private PopupWindow CountDownEndPop;//倒计时结束时的pop
    private TextView lastQiShuTv;  //截止的期数
    private TextView newQiShuTv; //新的期数
    private TextView countDownEndSure;//倒计时结束pop的确定按钮

    private TextView actionBarBack;//返回键

    private long millionSeconds;//倒计时结束时间
    private long time;//服务器时间
    //    private long nowTime;//当前时间
    private long shijiancha;//服务器时间与本地时间差
    private String tqtime;//提前时间

    private Long user_id;
    /*
    跳转到此activity传入的值
     */
    private int type_id;
    private int game;
    private String typename;
    //彩种封盘后需要显示的pop
//    private PopupWindow fengpanPop;
    private long jgTime;
    private String todayZJ;

    /*
   右侧菜单pop
    */
    public PopupWindow menuPop;
    public TextView betNote;//右侧菜单中 Utils.getString(R.string.投注记录)
    public TextView openResult;//右侧菜单中 Utils.getString(R.string.开奖结果)
    public TextView gameRule;//右侧菜单中 Utils.getString(R.string.游戏规则)
    public TextView twoCahngLongTv;//右侧菜单中 Utils.getString(R.string.两面长龙)
    public TextView investTv;//右侧菜单中 Utils.getString(R.string.充值中心)
    public TextView mineCenterTv;//右侧菜单中 Utils.getString(R.string.会员中心)
    public TextView todayWinLoseTv;//右侧菜单中 Utils.getString(R.string.今日输赢)
    public TextView onlineKf;//右侧菜单中Utils.getString(R.string.在线客服)

    public static final int ReqTouzhu = 101;
    private int isrestore = SharePreferencesUtil.getInt(this, "isrestore", 0);
    private TextView stopTv;
    private LinearLayout timeLinear;
    private LinearLayout timeloadLinear;
    private ConstraintLayout loadingLinear;
    private String isopenOffice;

    private static String TAG="PcDanDanBetActivity";
    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    private TextView is_stop_tv;
    private String isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_shu28);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        getIntentData();
        Utils.saveLotteryHistory(game,type_id);
        bindView();//绑定控件
        initChoosePlayTypePop();//选择游戏玩法
        initAllPop();//投注记录 开奖结果 彩票分类等,相关初始化
//        initFenpangPop(); //封盘pop
        getCountTime();//请求倒计时
        initRecycle();//recycleView相关配置
        getMoney();//请求用户余额
        initSureBetPop();//投注清单pop
        initCountDownEndPop();//倒计时结束的pop

    }

    private void getIntentData() {
        //user_id
        user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        /*
        请求开奖结果,跳转投注记录等等需要的参数
         */
        Intent intent = getIntent();
        isStart = intent.getStringExtra("isStart");
        type_id = intent.getIntExtra("type_id", 0);
        game = intent.getIntExtra("game", 0);
        typename = intent.getStringExtra("typename");
        isopenOffice = intent.getStringExtra("isopenOffice");
        if(StringMyUtil.isEmptyString(isopenOffice)){
            isopenOffice="";
        }
    }


    /*
    初始化倒计时结束后需要弹出的pop
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
                if (!sureBetPop.isShowing() && !choosePlayTypePop.isShowing() && !customPopupWindow.menuPop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.pcddTodayOpenResultPop.isShowing()&&!customPopupWindow.gameRulePop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(PcDanDanBetActivity.this, 1f);
                }
            }
        });
    }

    /*
    请求用户资金信息
     */
    private void getMoney() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", user_id);
        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
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
    初始化玩法类型pop(点击actionBar中间弹出)
     */
    private void initChoosePlayTypePop() {
        choosePlayTypePop.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePop.setTouchable(true);//响应内部点击
        choosePlayTypePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(PcDanDanBetActivity.this, 1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(PcDanDanBetActivity.this, R.anim.img_rotate_end_anim);
                ;
                chooseImg.startAnimation(loadAnimation);
            }
        });
    }

    /*
    投注列表recycleView相关配置
     */
    private void initRecycle() {
        //设置spanSize之前要先设置适配器(在后面设置会导致布局混乱)
        mRecy.setAdapter(pcDanDanRecycleAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 60);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //返回position对应的条目所占的size
            @Override
            public int getSpanSize(int position) {
                //在activity中重写该方法可以改变当前item占一列的几个位置(在adapter 的getViewType中,可以设置当前position的item布局(如果需要实现多item,多布局的效果,两处都需要对position进行判断))
                if (hunheRb.isChecked()) {
                    if (position == 8 || position == 9 || position == 10) {
                        return 20;//当前item占1/3
                    } else {
                        return 15;//占1/4
                    }
                } else if (boseRb.isChecked()) {
                    return 20;
                } else {
                    if (position == 25 || position == 27 || position == 26) {
                        return 20;
                    } else {
                        return 12;
                    }
                }
            }


        });
        mRecy.setLayoutManager(gridLayoutManager);
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//添加间距
        pcDanDanRecycleAdapter.setOnItemClickListener(this);

//          requestRule();//请求下注列表
    }

    /*
    请求下注列表数据
     */
    private void requestRule() {
        loadingLinear.setVisibility(View.VISIBLE);
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + getGame()+type_id, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id", user_id);
            data.put("type_id", type_id);
            data.put("game", getGame());
            Utils.docking(data, RequestUtil.REQUEST_01dd, -1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LOTTERY_RULE + getGame()+type_id,content);
                    handRuleJson(content);
                }

                @Override
                public void failed(MessageHead messageHead) {
//                System.out.println(messageHead.getInfo());
                    loadingLinear.setVisibility(View.GONE);
                }
            });
        }else {
            handRuleJson(jsonStr);
        }

    }

    private void handRuleJson(String content) {
        loadingLinear.setVisibility(View.GONE);
        pcDanDanRecycleModelArrayList.clear();
//                selecterList.clear();
        JSONObject jsonObject1 = JSONObject.parseObject(content);
        JSONArray danRulelist = jsonObject1.getJSONArray("danRulelist");
        for (int i = 0; i < danRulelist.size(); i++) {
            JSONObject jsonObject = danRulelist.getJSONObject(i);
            if (hunheRb.isChecked()) {//actionBar中选中Utils.getString(R.string.混合)按钮
                if (jsonObject.getString("groupname").equals(Utils.getString(R.string.混合))) {//groupname为Utils.getString(R.string.hunhe),取出
                    String name = jsonObject.getString("name");//类型(大小 单双 小单 .....)
                    String odds = jsonObject.getString("odds");//赔率
                    String id = jsonObject.getString("id");//玩法id
                    String groupname = jsonObject.getString("groupname");//玩法类型
                    PcDanDanRecycleModel pcDanDanRecycleModel = new PcDanDanRecycleModel(name, odds, id, groupname);
                /*
                  关键代码(每次切换玩法类型时,需要保存切换前的选中状态,(因为status默认为0,为未选中).所以在这里根据rule_id来判断当前item在切换前是否被选中,如果切换之前被选中,那么切换回来的时候将stutas设置为1(选中状态))
                 */
                    ArrayList<String> idList = new ArrayList<>();
                    for (int j = 0; j < selecterList.size(); j++) {//遍历选中的item,取出rule_id
                        String rule_id = selecterList.get(j).getRule_id();
                        idList.add(rule_id);
                    }
                    if (idList.contains(id)) {//切换时,当运行到这里,判断rele_id容器中是否包含当前item的rule_id(包含则说明当前数据对应的item在切换前已点击)
                        pcDanDanRecycleModel.setStatus(1);//status设置为1 (选中效果)
                    }
                    pcDanDanRecycleModelArrayList.add(pcDanDanRecycleModel);
                }
            } else if (boseRb.isChecked()) {
                if (jsonObject.getString("groupname").equals(Utils.getString(R.string.波色))) {
                    String name = jsonObject.getString("name");//类型(大小 单双 小单 .....)
                    String odds = jsonObject.getString("odds");//赔率
                    String id = jsonObject.getString("id");//玩法id
                    String groupname = jsonObject.getString("groupname");//玩法类型
                    PcDanDanRecycleModel pcDanDanRecycleModel = new PcDanDanRecycleModel(name, odds, id, groupname);

                    //同上,保留item的选中状态
                    ArrayList<String> idList = new ArrayList<>();
                    for (int j = 0; j < selecterList.size(); j++) {
                        String rule_id = selecterList.get(j).getRule_id();
                        idList.add(rule_id);
                    }
                    if (idList.contains(id)) {
                        pcDanDanRecycleModel.setStatus(1);
                    }
                    pcDanDanRecycleModelArrayList.add(pcDanDanRecycleModel);
                }
            } else {
                if (jsonObject.getString("groupname").equals(Utils.getString(R.string.特码))) {
                    String name = jsonObject.getString("name");//类型(大小 单双 小单 .....)
                    String odds = jsonObject.getString("odds");//赔率
                    String id = jsonObject.getString("id");//玩法id
                    ArrayList<String> idList = new ArrayList<>();
                    String groupname = jsonObject.getString("groupname");//玩法类型
                    PcDanDanRecycleModel pcDanDanRecycleModel = new PcDanDanRecycleModel(name, odds, id, groupname);

                    //同上,保留item的选中状态
                    for (int j = 0; j < selecterList.size(); j++) {
                        String rule_id = selecterList.get(j).getRule_id();
                        idList.add(rule_id);
                    }
                    if (idList.contains(id)) {
                        pcDanDanRecycleModel.setStatus(1);
                    }
                    pcDanDanRecycleModelArrayList.add(pcDanDanRecycleModel);
                }
            }
        }
        pcDanDanRecycleAdapter.notifyDataSetChanged();
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
        return 5;
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
        customPopupWindow.toBetNote(this, typename, game, type_id);
        //跳转 充值中心
        customPopupWindow.toInvestCenter(this);
        //跳转会员 中心
        customPopupWindow.tovVipCenter(this);
        //跳转 开奖结果 type_id: 当前彩票的typ_id  lotteryClassId: 当前彩种分类的id
        customPopupWindow.toOpenResult(this, type_id, game);
        //classFyId 彩票类型id(用于判断需要跳转到哪种类型的游戏规则) typename: 彩票名: 用于设置规则说明中的彩票名  测试用,后面要换成对应彩种的玩法规则pop
        customPopupWindow.initGameRule(this, game, typename, isrestore,false);
        //弹出游戏规则pop
        customPopupWindow.showGameRulePop(this,false);
        ;
        //开奖结果pop视图初始化
        customPopupWindow.initPcddTodayResult(this, type_id,false);
        //跳转两面长龙
        customPopupWindow.toTwoChangLongAty(this, game, type_id);
        //跳转今日输赢
        customPopupWindow.toTodayWinLose(this, game, type_id);
        //跳转在线客服
        customPopupWindow.toOnlineKf(this);
        jgTime = customPopupWindow.getJgTIme(game, type_id);
    }


    /*
    handler 循环相关
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(runnableTime!=null){
        handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime, 300);//倒计时runnable
        if (isWaitOpen) {
            if(runnableRandom!=null){
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);//开奖结果滚动runnabe
//            System.out.println("gundong  isWait");
        }
        if (isWaitOpen) {
            if(runnableRequestOpen!=null){
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, jgTime);//开奖结果未更新,每10秒请求一次开奖结果
//            System.out.println(Utils.getString(R.string.请求接口10s  isWait));
        }
//        handler.postDelayed(runnableBoolen,5000);
        if(runnableZj!=null){
            handler.removeCallbacks(runnableZj);
        }
        handler.postDelayed(runnableZj, 3000);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        float alpha = lp.alpha;
        if (alpha == 1f) {
            CountDownEndPop.dismiss();
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
        Utils.docking(data, RequestUtil.REQUEST_05dd, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray danLotterylist = jsonObject1.getJSONArray("danLotterylist");
                if(danLotterylist.size()==0){
                    showToast(getString(R.string.开奖结果获取失败));
                    return;
                }
                JSONObject jsonObject = danLotterylist.getJSONObject(0);
                String lotteryqishu = jsonObject.getString("lotteryqishu");
                if (!StringMyUtil.isEmptyString(nowQishu)) {
                    if (Long.parseLong(lotteryqishu) == (Long.parseLong(nowQishu) - 1)) {//相等时,更新开奖结果
                        isWaitOpen = false;
                        String lotteryNo = jsonObject.getString("lotteryNo");
                        String[] split = lotteryNo.split(",");
                        List<String> strings = Arrays.asList(split);
                        one = strings.get(0);
                        two = strings.get(1);
                        three = strings.get(2);
                        numOne.setText(one);
                        numTwo.setText(two);
                        numThree.setText(three);
                        numHeZhi.setText(Long.parseLong(one) + Long.parseLong(two) + Long.parseLong(three) + "");
//                        System.out.println(Utils.getString(R.string.开奖结果暂停 isWaite));
//                        System.out.println("isWait   "+"one =   "+one +"  two=   "+two+" three =  "+three  );
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

    /*
    请求倒计时
     */
    private void getCountTime() {
    showLoadingLinear();
        HashMap<String, Object> data = new HashMap<>();
        String domain = SharePreferencesUtil.getString(PcDanDanBetActivity.this, "domain", "");
        data.put("type_id", type_id);
        data.put("source", 2);
        Utils.docking(data, RequestUtil.REQUEST_04dd, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                String stoptimestr = jsonObject.getString("stoptimestr");//倒计时结束时间
                nowQishu = jsonObject.getString("qishu");//当前期数
                if (StringMyUtil.isEmptyString(nowQishu)) {//期数为空,表示封盘
//                    fengpanPop.showAtLocation(actionBarBack, Gravity.CENTER, 0, 0);
                    stopTv.setVisibility(View.VISIBLE);
                    ProgressDialogUtil.darkenBackground(PcDanDanBetActivity.this, 0.7f);
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
//                    if (fengpanPop.isShowing()) {
//                        fengpanPop.dismiss();
//                    }
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
                        shijiancha = SharePreferencesUtil.getLong(PcDanDanBetActivity.this, "shijiancha", 0l);//服务器时间和当地时间差
                        //倒计时=结束时间-服务器和本地时间差-当前时间-(提前封盘时间(单位:s)*10000)
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
                            timeloadLinear.setVisibility(View.GONE);
                            timeLinear.setVisibility(View.VISIBLE);
                        }*/
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (num == 1) {//第一次请求倒计时数据时,才发送消息通知请求开奖结果(因为请求一次后,期数不为空了)(可以去掉,)
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
//                showtoast(Utils.getString(R.string.网络不给力));
                showLoadingLinear();
            }
        });
    }
    private void showLoadingLinear() {
        if (isStart.equals("0")) {
            timeloadLinear.setVisibility(View.GONE);
            timeLinear.setVisibility(View.GONE);
            is_stop_tv.setVisibility(View.VISIBLE);
        } else {
            timeloadLinear.setVisibility(View.VISIBLE);
            timeLinear.setVisibility(View.GONE);
            is_stop_tv.setVisibility(View.GONE);
        }
    }

    private void showTimeLInear() {
        if(isStart.equals("0")){
            is_stop_tv.setVisibility(View.VISIBLE);
            timeloadLinear.setVisibility(View.GONE);
            timeLinear.setVisibility(View.GONE);
        }else {
            timeLinear.setVisibility(View.VISIBLE);
            timeloadLinear.setVisibility(View.GONE);
            is_stop_tv.setVisibility(View.GONE);
        }
    }
    private void bindView() {
        is_stop_tv=findViewById(R.id.is_stop_tv);
        if(isStart.equals("0")){
            is_stop_tv.setVisibility(View.VISIBLE);
        }else {
            is_stop_tv.setVisibility(View.GONE);
        }
        loadingLinear=findViewById(R.id.loading_linear);
        timeLinear=findViewById(R.id.time_linear);
        timeloadLinear=findViewById(R.id.time_load_linear);
        actionBarBack = findViewById(R.id.bet_actionbar_back);
        actionBarBack.setOnClickListener(this);
        stopTv=findViewById(R.id.stop_textview);
        stopTv.setOnClickListener(this);
        hourText = findViewById(R.id.hour);
        minutesText = findViewById(R.id.minutes);
        secondsText = findViewById(R.id.seconds);
        openResultLinear = findViewById(R.id.open_resule_linear);
        numOne = findViewById(R.id.num_one);
        numTwo = findViewById(R.id.num_two);
        numThree = findViewById(R.id.num_three);
        numHeZhi = findViewById(R.id.num_hezhi);
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

        mRecy = findViewById(R.id.pcdandan_bet_recycle);
        pcDanDanRecycleAdapter = new PcDanDanRecycleAdapter(pcDanDanRecycleModelArrayList, selecterList);

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

        pcddTypeText = findViewById(R.id.pcdd_type);
        chooseImg = findViewById(R.id.choose_img);
        playTypeText = findViewById(R.id.play_type_text);
        memberMoneyText = findViewById(R.id.member_money);
        /*
        底部下注信息 和下注按钮
         */
        betNum = findViewById(R.id.bet_num);
        amountEdit = findViewById(R.id.amount_edit);
        betButton = findViewById(R.id.bet_button);
        randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(this);
        betButton.setOnClickListener(this);
        /*
        玩法类型pop
         */
        hunheRb = view.findViewById(R.id.happy8_liangmian);
        hunheRb.setOnClickListener(this);
        hunheRb.setText(Utils.getString(R.string.混合));
        boseRb = view.findViewById(R.id.happy8_wuxing);
        boseRb.setOnClickListener(this);
        boseRb.setText(Utils.getString(R.string.波色));
        temaRb = view.findViewById(R.id.happy8_zhengma);
        temaRb.setOnClickListener(this);
        temaRb.setText(Utils.getString(R.string.特码));
        hunheRb.performClick();
        /*
        开奖结果样式设置
         */
        numOne.setfilColor(Color.parseColor("#bf1f24"));
        numTwo.setfilColor(Color.parseColor("#bf1f24"));
        numThree.setfilColor(Color.parseColor("#bf1f24"));
        numHeZhi.setfilColor(Color.parseColor("#bf1f24"));
        numOne.setCornerSize(50);
        numTwo.setCornerSize(50);
        numThree.setCornerSize(50);
        numHeZhi.setCornerSize(50);
    }

    @Override
    protected void init() {

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
                            ProgressDialogUtil.darkenBackground(PcDanDanBetActivity.this, 0.5f);
                        }
                    }
                    isWaitOpen = true;
                }

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
                int i1 = new Random().nextInt(9) + 1;
                int i2 = new Random().nextInt(9) + 1;
                int i3 = new Random().nextInt(9) + 1;
                numOne.setText(i1 + "");
                numTwo.setText(i2 + "");
                numThree.setText(i3 + "");
                numHeZhi.setText("?");
            } else {
                numOne.setText(one);
                numTwo.setText(two);
                numThree.setText(three);
                numHeZhi.setText(Long.parseLong(one) + Long.parseLong(two) + Long.parseLong(three) + "");
            }
            handler.postDelayed(runnableRandom, 150);
        }
    };
    /*
    开奖结果未更新,每10秒请求一次开奖结果接口
     */
    Runnable runnableRequestOpen = new Runnable() {
        @Override
        public void run() {
            if (isWaitOpen) {
                initOpenResult();
            } else {
                numOne.setText(one);
                numTwo.setText(two);
                numThree.setText(three);
                numHeZhi.setText(Long.parseLong(one) + Long.parseLong(two) + Long.parseLong(three) + "");
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
            data.put("user_name", SharePreferencesUtil.getString(PcDanDanBetActivity.this, "nickname", ""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stop_textview:
                showToast(Utils.getString(R.string.该彩种已封盘));
                break;
            //图片旋转
            case R.id.rotate_image:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                ;
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            //点击弹出彩票分类pop
            case R.id.show_classfy_linear:
                customPopupWindow.showClassfyPop(showClassfyLinear, this);
                break;
            //官方 传统玩法的切换linear
            case R.id.xingyong_play:
                showToast(Utils.getString(R.string.暂无该游戏官方玩法));
                break;
            //点击弹出右侧菜单
            case R.id.right_menu:
                customPopupWindow.showMenuPop(rightMenu, this);
                break;
            //点击弹出今日开奖结果
            case R.id.today_open_result:
                customPopupWindow.initPcddTodayResultData(this, todayOpenResultLinear, type_id);
                break;
            //点击弹出玩法类型pop
            case R.id.choose_play_type:
                choosePlayTypePop.showAsDropDown(popTargetLinear, Gravity.BOTTOM, 0, 0);
                ProgressDialogUtil.darkenBackground(this, 0.5f);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                ;
                loadAnimation.setFillAfter(true);//动画结束后,保持结束时的角度
                chooseImg.startAnimation(loadAnimation);
                break;
            //玩法类型pop中的第一个按钮(此处为 Utils.getString(R.string.hunhe) 按钮)
            case R.id.happy8_liangmian:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size() + "");
                playTypeText.setText(hunheRb.getText().toString());
                pcddTypeText.setText(hunheRb.getText().toString());
                break;
            //..... 第二个按钮  (波色)
            case R.id.happy8_wuxing:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size() + "");
                playTypeText.setText(boseRb.getText().toString());
                pcddTypeText.setText(boseRb.getText().toString());
                break;
            //..... 第三个按钮  (特码)
            case R.id.happy8_zhengma:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size() + "");
                playTypeText.setText(temaRb.getText().toString());
                pcddTypeText.setText(temaRb.getText().toString());
                break;
            //点击底部机选按钮
            case R.id.random_button:
                int max = 0;
                if (randomButton.getText().toString().equals(Utils.getString(R.string.机选))) {
                    xingYongRandom();
                } else {
                    for (int i = 0; i < pcDanDanRecycleModelArrayList.size(); i++) {
                        pcDanDanRecycleModelArrayList.get(i).setStatus(0);
                    }
                    selecterList.clear();
                    int size = selecterList.size();
                    amountEdit.setText("");
                    betNum.setText(size + "");
                    randomButton.setText(Utils.getString(R.string.机选));
                }
                for (int i = 0; i < selecterList.size(); i++) {
                    String rule_id = selecterList.get(i).getRule_id();
                    for (int j = 0; j < pcDanDanRecycleModelArrayList.size(); j++) {
                        PcDanDanRecycleModel pcDanDanRecycleModel = pcDanDanRecycleModelArrayList.get(j);
                        if (pcDanDanRecycleModel.getRule_id().equals(rule_id)) {
                            if (j > max) {
                                max = j;
                            }
                        }
                    }
                }
                pcDanDanRecycleAdapter.notifyDataSetChanged();
                mRecy.getLayoutManager().scrollToPosition(max);
                break;
            //点击底部下注按钮
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
                    showToast(Utils.getString(R.string.请选择玩法));
                } else if (StringMyUtil.isEmptyString(editText)) {

                    showToast(Utils.getString(R.string.请输入下注金额));
                } else {
                    /* betButton.setClickable(false);
                    String needString = "";
                    String needString2 = "";
                    String needString3 = "";
                    ArrayList<String> ruleTypeList = new ArrayList<>();
                    *//*
                    分别取出 混合 波色 特码 的 投注信息
                     *//*
                    for (int i = 0; i < selecterList.size(); i++) {
                        PcDanDanRecycleModel pcDanDanRecycleModel = selecterList.get(i);
                        if (pcDanDanRecycleModel.getGroupname().equals(Utils.getString(R.string.hunhe))) {
                            String betType = pcDanDanRecycleModel.getBetType();
                            needString += betType + " ";
                        } else if (pcDanDanRecycleModel.getGroupname().equals(Utils.getString(R.string.波色))) {
                            String betType = pcDanDanRecycleModel.getBetType();
                            needString2 += betType + " ";
                        } else {
                            String betType = pcDanDanRecycleModel.getBetType();
                            needString3 += betType + " ";
                        }
                    }
                    if (!StringMyUtil.isEmptyString(needString)) {
                        needString = needString.substring(0, needString.length() - 1);
                    }
                    if (!StringMyUtil.isEmptyString(needString2)) {
                        needString2 = needString2.substring(0, needString2.length() - 1);
                    }
                    if (!StringMyUtil.isEmptyString(needString3)) {
                        needString3 = needString3.substring(0, needString3.length() - 1);
                    }
                    lotteryNameTv.setText(name.getText().toString());//彩票名
                    qishuTv.setText(nowQishu);//投注期数
                    betAmoumtTv.setText(inputTv);//投注总金额
                    //遍历选中item的容器,取出groupname,并保存(用于判断当前groupname对应的item有无投注)
                    for (int i = 0; i < selecterList.size(); i++) {
                        PcDanDanRecycleModel pcDanDanRecycleModel = selecterList.get(i);
                        String groupname = pcDanDanRecycleModel.getGroupname();
                        ruleTypeList.add(groupname);
                    }
                    //当前groupname有投注则显示,没有则隐藏
                    if (!ruleTypeList.contains(Utils.getString(R.string.hunhe))) {
                        gameTypeLInear.setVisibility(View.GONE);
                    } else {
                        gameTypeLInear.setVisibility(View.VISIBLE);
                    }
                    if (!ruleTypeList.contains(Utils.getString(R.string.波色))) {
                        gameTypeLInear2.setVisibility(View.GONE);
                    } else {
                        gameTypeLInear2.setVisibility(View.VISIBLE);
                    }
                    if (!ruleTypeList.contains(Utils.getString(R.string.特码))) {
                        gameTypeLInear3.setVisibility(View.GONE);
                    } else {
                        gameTypeLInear3.setVisibility(View.VISIBLE);
                    }
                    gameTypeTv.setText(hunheRb.getText().toString());
                    gameTypeTv2.setText(boseRb.getText().toString());
                    gameTypeTv3.setText(temaRb.getText().toString());
                    ruleNameTv.setText(needString);
                    ruleNameTv2.setText(needString2);
                    ruleNameTv3.setText(needString3);
                    allBetNumTv.setText(size + "");
                    allBetAmountTv.setText((Long.parseLong(inputTv) * size) + "");
                    sureBetPop.showAtLocation(betButton, Gravity.CENTER, 0, 0);
                    ProgressDialogUtil.darkenBackground(PcDanDanBetActivity.this, 0.5f);
                    hintKbTwo();//隐藏软键盘*/
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
                    intent.setClass(PcDanDanBetActivity.this, TouzhuActivity.class);
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
            //下注清单pop中的取消按钮
            case R.id.sure_bet_cancel:
                sureBetPop.dismiss();
                break;
            //下注清单中的确定按钮
            case R.id.sure_bet_sure:
                sureButton.setClickable(false);
                requestBet(selecterList.size(), amountEdit.getText().toString());
                break;
            //倒计时结束后弹出的pop中的确定按钮
            case R.id.countdown_pop_sure:
                CountDownEndPop.dismiss();
                break;
            //actionbar 返回键
            case R.id.bet_actionbar_back:
                finish();
                break;
        }
    }

    //对投注成功后进行数据重置 更新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // 这里写信用投注成功后清除数据
            for (int i = 0; i < pcDanDanRecycleModelArrayList.size(); i++) {
                pcDanDanRecycleModelArrayList.get(i).setStatus(0);
            }
            selecterList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size + "");
            pcDanDanRecycleAdapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.机选));
            getMoney();
        }

    }

    private void xingYongRandom() {
        selecterList.clear();
        if(pcDanDanRecycleModelArrayList.size()==0){
            return;
        }
        int random = new Random().nextInt(pcDanDanRecycleModelArrayList.size());
        pcDanDanRecycleModelArrayList.get(random).setStatus(1);
        selecterList.add(pcDanDanRecycleModelArrayList.get(random));
        int size = selecterList.size();
        betNum.setText(size + "");
        randomButton.setText(Utils.getString(R.string.重置));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            if (betType.equals(Utils.getString(R.string.混合)) || betType.equals(Utils.getString(R.string.波色)) || betType.equals(Utils.getString(R.string.特码))) {
                xingYongRandom();
            } else {
                break;
            }
        }
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

    /**
     * 点击下注 请求接口
     *
     * @param size     选中item的size
     * @param editText amount输入框的内容
     */
    private void requestBet(int size, String editText) {
        HashMap<String, Object> data = new HashMap<>();
        String needString = "";
        for (int i = 0; i < size; i++) {
            PcDanDanRecycleModel pcDanDanRecycleModel = selecterList.get(i);
            String rule_id = pcDanDanRecycleModel.getRule_id();
            needString += rule_id + ",";
        }
        needString = needString.substring(0, needString.length() - 1);
        String amountStr = "";
        for (int i = 0; i < size; i++) {
            amountStr += editText + ",";
        }
        String token = SharePreferencesUtil.getString(PcDanDanBetActivity.this, "token", "");
        String domain = SharePreferencesUtil.getString(PcDanDanBetActivity.this, "domain", "");
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
        Iterator iter = data.entrySet().iterator();
        Utils.docking(data, RequestUtil.REQUEST_03dd, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                betButton.setClickable(true);
                sureButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
                for (int i = 0; i < pcDanDanRecycleModelArrayList.size(); i++) {
                    pcDanDanRecycleModelArrayList.get(i).setStatus(0);
                }
                selecterList.clear();
                int size = selecterList.size();
                amountEdit.setText("");
                betNum.setText(size + "");
                pcDanDanRecycleAdapter.notifyDataSetChanged();
                sureBetPop.dismiss();
                randomButton.setText(Utils.getString(R.string.机选));
                getMoney();
            }

            @Override
            public void failed(MessageHead messageHead) {
                betButton.setClickable(true);
                sureButton.setClickable(true);
                JSONObject headData = messageHead.getData();
                String flag = headData.getString("flag");
                showToast(Utils.getString(R.string.下注失败)+"\n" + messageHead.getInfo());
                sureBetPop.dismiss();

            }
        });
    }

    /*
    投注清单pop
     */
    private void initSureBetPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pcdd_sure_bet_pop, null);
        lotteryNameTv = view.findViewById(R.id.lottery_name);
        qishuTv = view.findViewById(R.id.lottery_qishu);
        betAmoumtTv = view.findViewById(R.id.bet_amount);
        gameTypeTv = view.findViewById(R.id.play_type);
        ruleNameTv = view.findViewById(R.id.rule_name);
        gameTypeTv2 = view.findViewById(R.id.play_type2);
        ruleNameTv2 = view.findViewById(R.id.rule_name2);
        gameTypeTv3 = view.findViewById(R.id.play_type3);
        ruleNameTv3 = view.findViewById(R.id.rule_name3);
        gameTypeLInear = view.findViewById(R.id.one_type_linear);
        gameTypeLInear2 = view.findViewById(R.id.two_type_linear);
        gameTypeLInear3 = view.findViewById(R.id.three_type_linear);
        allBetNumTv = view.findViewById(R.id.all_bet_num);
        allBetAmountTv = view.findViewById(R.id.all_bet_amout);
        cancelButton = view.findViewById(R.id.sure_bet_cancel);
        sureButton = view.findViewById(R.id.sure_bet_sure);
        cancelButton.setOnClickListener(this);
        sureButton.setOnClickListener(this);
        sureBetPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        sureBetPop.setAnimationStyle(R.style.pop_scale_animation);
        sureBetPop.setTouchable(true);//响应内部点击
        sureBetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!CountDownEndPop.isShowing() && !choosePlayTypePop.isShowing() && !customPopupWindow.classfyPop.isShowing() && !customPopupWindow.menuPop.isShowing()) {
                    ProgressDialogUtil.darkenBackground(PcDanDanBetActivity.this, 1f);
                }
                betButton.setClickable(true);
            }
        });
    }

    /**
     * 彩票分类pop recycleView点击事件回调
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
        ToBetAtyUtils.toBetActivity(PcDanDanBetActivity.this, game, typename, type_id, isopenOffice, isStart);
        finish();
    }

    /**
     * 右侧菜单pop 中item的点击事件回调,(暂时没有用到)
     * @param view
     */
    @Override
    public void onMenuClicked(View view) {

    }

    /**
     * recycleView item点击事件
     *
     * @param view     点击的item
     * @param position item的potision
     */
    @Override
    public void onItemClick(View view, int position) {
        int size = selecterList.size();
        betNum.setText(size + "");//当前选中了多少注,直接设置为seleterList的size(selecterList在每次点击选中item的时候会将当前的position对应的model保存,每次取消选中的时候,将对应的medol移除)
        if (size != 0) {
            randomButton.setText(Utils.getString(R.string.重置));
        } else {
            randomButton.setText(Utils.getString(R.string.机选));
        }
    }

    /*
    移除handler中的循环操作
     */
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
    protected void onRestart() {
        super.onRestart();
        initAllPop();
        if (!CountDownEndPop.isShowing()) {
            ProgressDialogUtil.darkenBackground(this, 1f);
        }
//        handler.postDelayed(runnableRandom,150);
//        handler.postDelayed(runnableRequestOpen,jgTime);
//        handler.postDelayed(runnableTime,300);
//        handler.postDelayed(runnableZj,3000);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        customPopupWindow.selectorId(hbGameClassModel);
    }
    @Override
    public void onNetChange(boolean netWorkState) {
        if (!netWorkState) {
            showToast(Utils.getString(R.string.当前网络不可用请检查网络));
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
    }
}
