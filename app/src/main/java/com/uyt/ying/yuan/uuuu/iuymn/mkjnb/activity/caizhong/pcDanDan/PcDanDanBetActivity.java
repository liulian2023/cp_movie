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
    private TextView hourText;//??????????????????
    private TextView minutesText;//??????????????????
    private TextView secondsText;//???????????????
    private LinearLayout openResultLinear;
    private MyCornerTextView numOne;//????????????????????????textView
    private MyCornerTextView numTwo;//????????????????????????textView
    private MyCornerTextView numThree;//????????????????????????textView
    private MyCornerTextView numHeZhi;//?????????????????????textView
    private long countTime;//???????????????
    private TextView nowQishuText;//????????????
    private TextView lastQiShuText;//???????????????(??????????????????????????????????????????,????????????????????????????????????(lastQiShuText??????10?????????????????????????????????????????????lotteryqishu?????????,??????????????????,??????????????????))
    private String nowQishu;//????????????

    private boolean isWaitOpen = true;//?????????????????????(??????????????????true,????????????????????????isWaitOpen???runnable?????????????????????,??????????????????????????????????????????????????????)
    private String one;//???????????????????????????
    private String two;//???????????????????????????
    private String three;//???????????????????????????
    private ImageView rotateImg;//??????????????????
    private int num = 1;//???????????????????????????????????????????????????
    private LinearLayout showClassfyLinear;//????????????????????????pop
    private TextView rightMenu;//????????????????????????pop
    private LinearLayout todayOpenResultLinear;//??????????????????????????????
    private LinearLayout choosePlayType;//????????????????????????pop
    private LinearLayout popTargetLinear;//????????????pop????????????
    /*
    recycleView????????????
     */
    private ArrayList<PcDanDanRecycleModel> selecterList = new ArrayList<>();//????????????item??????
    private RecyclerView mRecy;
    private PcDanDanRecycleAdapter pcDanDanRecycleAdapter;
    private ArrayList<PcDanDanRecycleModel> pcDanDanRecycleModelArrayList = new ArrayList<>();
    /*
    ????????????pop
     */
    private PopupWindow choosePlayTypePop;
    private RadioButton hunheRb;//??????button
    private RadioButton boseRb;//??????button
    private RadioButton temaRb;//??????button

    private TextView playTypeText;//item?????????????????????textView

    private TextView betNum;//??????????????????
    private EditText amountEdit;//?????????????????????
    private TextView betButton;//????????????
    private TextView randomButton;//????????????

    private TextView memberMoneyText;//????????????
    private TextView pcddTypeText;//?????????????????????
    private ImageView chooseImg;//????????????pop?????????,?????????????????????
    /*
    ????????????pop
     */
    private PopupWindow sureBetPop;//
    private TextView lotteryNameTv;//?????????
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

    private TextView name;//????????????????????????name

    private LinearLayout xingyongLinear;//????????????????????????

    private PopupWindow CountDownEndPop;//?????????????????????pop
    private TextView lastQiShuTv;  //???????????????
    private TextView newQiShuTv; //????????????
    private TextView countDownEndSure;//???????????????pop???????????????

    private TextView actionBarBack;//?????????

    private long millionSeconds;//?????????????????????
    private long time;//???????????????
    //    private long nowTime;//????????????
    private long shijiancha;//?????????????????????????????????
    private String tqtime;//????????????

    private Long user_id;
    /*
    ????????????activity????????????
     */
    private int type_id;
    private int game;
    private String typename;
    //??????????????????????????????pop
//    private PopupWindow fengpanPop;
    private long jgTime;
    private String todayZJ;

    /*
   ????????????pop
    */
    public PopupWindow menuPop;
    public TextView betNote;//??????????????? Utils.getString(R.string.????????????)
    public TextView openResult;//??????????????? Utils.getString(R.string.????????????)
    public TextView gameRule;//??????????????? Utils.getString(R.string.????????????)
    public TextView twoCahngLongTv;//??????????????? Utils.getString(R.string.????????????)
    public TextView investTv;//??????????????? Utils.getString(R.string.????????????)
    public TextView mineCenterTv;//??????????????? Utils.getString(R.string.????????????)
    public TextView todayWinLoseTv;//??????????????? Utils.getString(R.string.????????????)
    public TextView onlineKf;//???????????????Utils.getString(R.string.????????????)

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
        bindView();//????????????
        initChoosePlayTypePop();//??????????????????
        initAllPop();//???????????? ???????????? ???????????????,???????????????
//        initFenpangPop(); //??????pop
        getCountTime();//???????????????
        initRecycle();//recycleView????????????
        getMoney();//??????????????????
        initSureBetPop();//????????????pop
        initCountDownEndPop();//??????????????????pop

    }

    private void getIntentData() {
        //user_id
        user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        /*
        ??????????????????,???????????????????????????????????????
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
    ??????????????????????????????????????????pop
     */
    private void initCountDownEndPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.countdown_end_pop, null);
        lastQiShuTv = view.findViewById(R.id.last_qishu);
        newQiShuTv = view.findViewById(R.id.new_qihao);
        countDownEndSure = view.findViewById(R.id.countdown_pop_sure);
        countDownEndSure.setOnClickListener(this);
        CountDownEndPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        CountDownEndPop.setAnimationStyle(R.style.popAlphaanim0_5);
        CountDownEndPop.setTouchable(true);//??????????????????
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
    ????????????????????????
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
    ?????????????????????pop(??????actionBar????????????)
     */
    private void initChoosePlayTypePop() {
        choosePlayTypePop.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePop.setTouchable(true);//??????????????????
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
    ????????????recycleView????????????
     */
    private void initRecycle() {
        //??????spanSize???????????????????????????(????????????????????????????????????)
        mRecy.setAdapter(pcDanDanRecycleAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 60);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //??????position????????????????????????size
            @Override
            public int getSpanSize(int position) {
                //???activity????????????????????????????????????item????????????????????????(???adapter ???getViewType???,??????????????????position???item??????(?????????????????????item,??????????????????,??????????????????position????????????))
                if (hunheRb.isChecked()) {
                    if (position == 8 || position == 9 || position == 10) {
                        return 20;//??????item???1/3
                    } else {
                        return 15;//???1/4
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
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//????????????
        pcDanDanRecycleAdapter.setOnItemClickListener(this);

//          requestRule();//??????????????????
    }

    /*
    ????????????????????????
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
            if (hunheRb.isChecked()) {//actionBar?????????Utils.getString(R.string.??????)??????
                if (jsonObject.getString("groupname").equals(Utils.getString(R.string.??????))) {//groupname???Utils.getString(R.string.hunhe),??????
                    String name = jsonObject.getString("name");//??????(?????? ?????? ?????? .....)
                    String odds = jsonObject.getString("odds");//??????
                    String id = jsonObject.getString("id");//??????id
                    String groupname = jsonObject.getString("groupname");//????????????
                    PcDanDanRecycleModel pcDanDanRecycleModel = new PcDanDanRecycleModel(name, odds, id, groupname);
                /*
                  ????????????(???????????????????????????,????????????????????????????????????,(??????status?????????0,????????????).?????????????????????rule_id???????????????item???????????????????????????,???????????????????????????,??????????????????????????????stutas?????????1(????????????))
                 */
                    ArrayList<String> idList = new ArrayList<>();
                    for (int j = 0; j < selecterList.size(); j++) {//???????????????item,??????rule_id
                        String rule_id = selecterList.get(j).getRule_id();
                        idList.add(rule_id);
                    }
                    if (idList.contains(id)) {//?????????,??????????????????,??????rele_id???????????????????????????item???rule_id(????????????????????????????????????item?????????????????????)
                        pcDanDanRecycleModel.setStatus(1);//status?????????1 (????????????)
                    }
                    pcDanDanRecycleModelArrayList.add(pcDanDanRecycleModel);
                }
            } else if (boseRb.isChecked()) {
                if (jsonObject.getString("groupname").equals(Utils.getString(R.string.??????))) {
                    String name = jsonObject.getString("name");//??????(?????? ?????? ?????? .....)
                    String odds = jsonObject.getString("odds");//??????
                    String id = jsonObject.getString("id");//??????id
                    String groupname = jsonObject.getString("groupname");//????????????
                    PcDanDanRecycleModel pcDanDanRecycleModel = new PcDanDanRecycleModel(name, odds, id, groupname);

                    //??????,??????item???????????????
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
                if (jsonObject.getString("groupname").equals(Utils.getString(R.string.??????))) {
                    String name = jsonObject.getString("name");//??????(?????? ?????? ?????? .....)
                    String odds = jsonObject.getString("odds");//??????
                    String id = jsonObject.getString("id");//??????id
                    ArrayList<String> idList = new ArrayList<>();
                    String groupname = jsonObject.getString("groupname");//????????????
                    PcDanDanRecycleModel pcDanDanRecycleModel = new PcDanDanRecycleModel(name, odds, id, groupname);

                    //??????,??????item???????????????
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
        /*1??????3???
        2???????????????
        3????????????
        4???????????????
        5????????????
        6??????8???
        7????????????
        8?????????10??????
        9???11???5*/
        return 5;
    }

    /*
    ????????????pop,???????????? ???
     */
    private void initAllPop() {
        //????????????pop????????????
        customPopupWindow.initClassfyPop(this, this);
        //????????????pop???????????? typename????????????????????????button
        customPopupWindow.initClassfyData(this, typename);
//        ????????????pop
        customPopupWindow.initMenuPop(this, this);
        //?????? ???????????? typename:????????? game:??????game  tyoe_id: ??????type_id
        customPopupWindow.toBetNote(this, typename, game, type_id);
        //?????? ????????????
        customPopupWindow.toInvestCenter(this);
        //???????????? ??????
        customPopupWindow.tovVipCenter(this);
        //?????? ???????????? type_id: ???????????????typ_id  lotteryClassId: ?????????????????????id
        customPopupWindow.toOpenResult(this, type_id, game);
        //classFyId ????????????id(??????????????????????????????????????????????????????) typename: ?????????: ???????????????????????????????????????  ?????????,??????????????????????????????????????????pop
        customPopupWindow.initGameRule(this, game, typename, isrestore,false);
        //??????????????????pop
        customPopupWindow.showGameRulePop(this,false);
        ;
        //????????????pop???????????????
        customPopupWindow.initPcddTodayResult(this, type_id,false);
        //??????????????????
        customPopupWindow.toTwoChangLongAty(this, game, type_id);
        //??????????????????
        customPopupWindow.toTodayWinLose(this, game, type_id);
        //??????????????????
        customPopupWindow.toOnlineKf(this);
        jgTime = customPopupWindow.getJgTIme(game, type_id);
    }


    /*
    handler ????????????
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(runnableTime!=null){
        handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime, 300);//?????????runnable
        if (isWaitOpen) {
            if(runnableRandom!=null){
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom, 150);//??????????????????runnabe
//            System.out.println("gundong  isWait");
        }
        if (isWaitOpen) {
            if(runnableRequestOpen!=null){
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen, jgTime);//?????????????????????,???10???????????????????????????
//            System.out.println(Utils.getString(R.string.????????????10s  isWait));
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
    ??????????????????
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
                    showToast(getString(R.string.????????????????????????));
                    return;
                }
                JSONObject jsonObject = danLotterylist.getJSONObject(0);
                String lotteryqishu = jsonObject.getString("lotteryqishu");
                if (!StringMyUtil.isEmptyString(nowQishu)) {
                    if (Long.parseLong(lotteryqishu) == (Long.parseLong(nowQishu) - 1)) {//?????????,??????????????????
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
//                        System.out.println(Utils.getString(R.string.?????????????????? isWaite));
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
    ???????????????
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
                String stoptimestr = jsonObject.getString("stoptimestr");//?????????????????????
                nowQishu = jsonObject.getString("qishu");//????????????
                if (StringMyUtil.isEmptyString(nowQishu)) {//????????????,????????????
//                    fengpanPop.showAtLocation(actionBarBack, Gravity.CENTER, 0, 0);
                    stopTv.setVisibility(View.VISIBLE);
                    ProgressDialogUtil.darkenBackground(PcDanDanBetActivity.this, 0.7f);
                    nowQishuText.setText(Utils.getString(R.string.???));
                    lastQiShuText.setText("-- -- -- "+Utils.getString(R.string.???????????????));
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
                    tqtime = jsonObject.getString("tqtime");//????????????(?????????????????????????????????)
                    nowQishuText.setText(nowQishu + Utils.getString(R.string. ???));
                    lastQiShuText.setText((Long.parseLong(nowQishu) - 1) + " "+Utils.getString(R.string.???????????????));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    try {
                        millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//?????????????????????
//                        time = Long.parseLong(Utils.getFileData("time"));//???????????????
                        long nowTime = System.currentTimeMillis();//????????????
                        shijiancha = SharePreferencesUtil.getLong(PcDanDanBetActivity.this, "shijiancha", 0l);//?????????????????????????????????
                        //?????????=????????????-???????????????????????????-????????????-(??????????????????(??????:s)*10000)
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
                    if (num == 1) {//?????????????????????????????????,???????????????????????????????????????(?????????????????????,??????????????????)(????????????,)
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
//                showtoast(Utils.getString(R.string.???????????????));
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
        ?????????????????? ???????????????
         */
        betNum = findViewById(R.id.bet_num);
        amountEdit = findViewById(R.id.amount_edit);
        betButton = findViewById(R.id.bet_button);
        randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(this);
        betButton.setOnClickListener(this);
        /*
        ????????????pop
         */
        hunheRb = view.findViewById(R.id.happy8_liangmian);
        hunheRb.setOnClickListener(this);
        hunheRb.setText(Utils.getString(R.string.??????));
        boseRb = view.findViewById(R.id.happy8_wuxing);
        boseRb.setOnClickListener(this);
        boseRb.setText(Utils.getString(R.string.??????));
        temaRb = view.findViewById(R.id.happy8_zhengma);
        temaRb.setOnClickListener(this);
        temaRb.setText(Utils.getString(R.string.??????));
        hunheRb.performClick();
        /*
        ????????????????????????
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
                case 1://??????????????????????????????,???????????????????????????(??????????????????????????????????????????????????????????????????,?????????????????????)
                    initOpenResult();//????????????????????????
                    break;
                case 2:
                    getMoney();
                    break;
            }
        }
    };

    /*
    ?????????????????????
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
    ?????????????????????????????????
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
    ?????????????????????,???10?????????????????????????????????
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
         ????????????(??????????????????????????????)
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
                showToast(Utils.getString(R.string.??????????????????));
                break;
            //????????????
            case R.id.rotate_image:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                ;
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            //????????????????????????pop
            case R.id.show_classfy_linear:
                customPopupWindow.showClassfyPop(showClassfyLinear, this);
                break;
            //?????? ?????????????????????linear
            case R.id.xingyong_play:
                showToast(Utils.getString(R.string.???????????????????????????));
                break;
            //????????????????????????
            case R.id.right_menu:
                customPopupWindow.showMenuPop(rightMenu, this);
                break;
            //??????????????????????????????
            case R.id.today_open_result:
                customPopupWindow.initPcddTodayResultData(this, todayOpenResultLinear, type_id);
                break;
            //????????????????????????pop
            case R.id.choose_play_type:
                choosePlayTypePop.showAsDropDown(popTargetLinear, Gravity.BOTTOM, 0, 0);
                ProgressDialogUtil.darkenBackground(this, 0.5f);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);
                ;
                loadAnimation.setFillAfter(true);//???????????????,????????????????????????
                chooseImg.startAnimation(loadAnimation);
                break;
            //????????????pop?????????????????????(????????? Utils.getString(R.string.hunhe) ??????)
            case R.id.happy8_liangmian:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size() + "");
                playTypeText.setText(hunheRb.getText().toString());
                pcddTypeText.setText(hunheRb.getText().toString());
                break;
            //..... ???????????????  (??????)
            case R.id.happy8_wuxing:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size() + "");
                playTypeText.setText(boseRb.getText().toString());
                pcddTypeText.setText(boseRb.getText().toString());
                break;
            //..... ???????????????  (??????)
            case R.id.happy8_zhengma:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size() + "");
                playTypeText.setText(temaRb.getText().toString());
                pcddTypeText.setText(temaRb.getText().toString());
                break;
            //????????????????????????
            case R.id.random_button:
                int max = 0;
                if (randomButton.getText().toString().equals(Utils.getString(R.string.??????))) {
                    xingYongRandom();
                } else {
                    for (int i = 0; i < pcDanDanRecycleModelArrayList.size(); i++) {
                        pcDanDanRecycleModelArrayList.get(i).setStatus(0);
                    }
                    selecterList.clear();
                    int size = selecterList.size();
                    amountEdit.setText("");
                    betNum.setText(size + "");
                    randomButton.setText(Utils.getString(R.string.??????));
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
            //????????????????????????
            case R.id.bet_button:
                if(Utils.isFastClick()){
                    return;
                }
                int size = selecterList.size();
                String editText = amountEdit.getText().toString();
                if(stopTv.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.?????????????????????));
                }
               else if (size == 0) {
                    showToast(Utils.getString(R.string.???????????????));
                } else if (StringMyUtil.isEmptyString(editText)) {

                    showToast(Utils.getString(R.string.?????????????????????));
                } else {
                    /* betButton.setClickable(false);
                    String needString = "";
                    String needString2 = "";
                    String needString3 = "";
                    ArrayList<String> ruleTypeList = new ArrayList<>();
                    *//*
                    ???????????? ?????? ?????? ?????? ??? ????????????
                     *//*
                    for (int i = 0; i < selecterList.size(); i++) {
                        PcDanDanRecycleModel pcDanDanRecycleModel = selecterList.get(i);
                        if (pcDanDanRecycleModel.getGroupname().equals(Utils.getString(R.string.hunhe))) {
                            String betType = pcDanDanRecycleModel.getBetType();
                            needString += betType + " ";
                        } else if (pcDanDanRecycleModel.getGroupname().equals(Utils.getString(R.string.??????))) {
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
                    lotteryNameTv.setText(name.getText().toString());//?????????
                    qishuTv.setText(nowQishu);//????????????
                    betAmoumtTv.setText(inputTv);//???????????????
                    //????????????item?????????,??????groupname,?????????(??????????????????groupname?????????item????????????)
                    for (int i = 0; i < selecterList.size(); i++) {
                        PcDanDanRecycleModel pcDanDanRecycleModel = selecterList.get(i);
                        String groupname = pcDanDanRecycleModel.getGroupname();
                        ruleTypeList.add(groupname);
                    }
                    //??????groupname??????????????????,???????????????
                    if (!ruleTypeList.contains(Utils.getString(R.string.hunhe))) {
                        gameTypeLInear.setVisibility(View.GONE);
                    } else {
                        gameTypeLInear.setVisibility(View.VISIBLE);
                    }
                    if (!ruleTypeList.contains(Utils.getString(R.string.??????))) {
                        gameTypeLInear2.setVisibility(View.GONE);
                    } else {
                        gameTypeLInear2.setVisibility(View.VISIBLE);
                    }
                    if (!ruleTypeList.contains(Utils.getString(R.string.??????))) {
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
                    hintKbTwo();//???????????????*/
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
            //????????????pop??????????????????
            case R.id.sure_bet_cancel:
                sureBetPop.dismiss();
                break;
            //??????????????????????????????
            case R.id.sure_bet_sure:
                sureButton.setClickable(false);
                requestBet(selecterList.size(), amountEdit.getText().toString());
                break;
            //???????????????????????????pop??????????????????
            case R.id.countdown_pop_sure:
                CountDownEndPop.dismiss();
                break;
            //actionbar ?????????
            case R.id.bet_actionbar_back:
                finish();
                break;
        }
    }

    //???????????????????????????????????? ????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // ??????????????????????????????????????????
            for (int i = 0; i < pcDanDanRecycleModelArrayList.size(); i++) {
                pcDanDanRecycleModelArrayList.get(i).setStatus(0);
            }
            selecterList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size + "");
            pcDanDanRecycleAdapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.??????));
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
        randomButton.setText(Utils.getString(R.string.??????));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            if (betType.equals(Utils.getString(R.string.??????)) || betType.equals(Utils.getString(R.string.??????)) || betType.equals(Utils.getString(R.string.??????))) {
                xingYongRandom();
            } else {
                break;
            }
        }
    }
        /*
        ???????????????
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
     * ???????????? ????????????
     *
     * @param size     ??????item???size
     * @param editText amount??????????????????
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
        data.put("curtime", format);//????????????
        data.put("token", token);//??????token
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
                randomButton.setText(Utils.getString(R.string.??????));
                getMoney();
            }

            @Override
            public void failed(MessageHead messageHead) {
                betButton.setClickable(true);
                sureButton.setClickable(true);
                JSONObject headData = messageHead.getData();
                String flag = headData.getString("flag");
                showToast(Utils.getString(R.string.????????????)+"\n" + messageHead.getInfo());
                sureBetPop.dismiss();

            }
        });
    }

    /*
    ????????????pop
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
        sureBetPop.setTouchable(true);//??????????????????
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
     * ????????????pop recycleView??????????????????
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
     * ????????????pop ???item?????????????????????,(??????????????????)
     * @param view
     */
    @Override
    public void onMenuClicked(View view) {

    }

    /**
     * recycleView item????????????
     *
     * @param view     ?????????item
     * @param position item???potision
     */
    @Override
    public void onItemClick(View view, int position) {
        int size = selecterList.size();
        betNum.setText(size + "");//????????????????????????,???????????????seleterList???size(selecterList?????????????????????item????????????????????????position?????????model??????,???????????????????????????,????????????medol??????)
        if (size != 0) {
            randomButton.setText(Utils.getString(R.string.??????));
        } else {
            randomButton.setText(Utils.getString(R.string.??????));
        }
    }

    /*
    ??????handler??????????????????
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
            showToast(Utils.getString(R.string.????????????????????????????????????));
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
