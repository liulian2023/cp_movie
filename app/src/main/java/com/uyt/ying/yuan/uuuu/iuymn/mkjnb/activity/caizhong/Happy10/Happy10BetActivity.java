package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.Happy10;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
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

public class Happy10BetActivity extends BaseActivity implements View.OnClickListener, CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener,  Happy10Adapter.OnRecyclerViewItemClickListener {
    private LinearLayout timeLoadingLinear;
    private LinearLayout timeLinear;
    private TextView hourText;//??????????????????
    private TextView minutesText;//??????????????????
    private TextView secondsText;//???????????????
    private long countTime ;//???????????????
    private TextView nowQishuText;//????????????
    private TextView lastQiShuText;//???????????????(??????????????????????????????????????????,????????????????????????????????????(lastQiShuText??????10?????????????????????????????????????????????lotteryqishu?????????,??????????????????,??????????????????))
    private String nowQishu;//????????????
    private ImageView rotateImg;//??????????????????
    private boolean isWaitOpen =true;//?????????????????????(??????????????????true,????????????????????????isWaitOpen?????????runnable?????????????????????,??????????????????????????????????????????????????????)
    private int num=1;//???????????????????????????????????????????????????
    private LinearLayout showClassfyLinear;//????????????????????????pop
    private TextView rightMenu;//????????????????????????pop
    private LinearLayout todayOpenResultLinear;//??????????????????????????????
    private LinearLayout choosePlayType;//????????????????????????pop
    private LinearLayout popTargetLinear;//????????????pop????????????
    /*
   ????????????pop
    */
    private PopupWindow choosePlayTypePop;
    private LinearLayout openResultLinear;
    private RecyclerView choosePlayRecy;
    private BetChoosePlayAdapter betChoosePlayAdapter;
    private ArrayList<BetChoosePalyModel>betChoosePalyModelArrayList=new ArrayList<>();
    private int popPosition=0;
    //??????????????????view
    private MyCornerTextView happy10NumTv1;
    private MyCornerTextView happy10NumTv3;
    private MyCornerTextView happy10NumTv2;
    private MyCornerTextView happy10NumTv4;
    private MyCornerTextView happy10NumTv5;
    private MyCornerTextView happy10NumTv6;
    private MyCornerTextView happy10NumTv7;
    private MyCornerTextView happy10NumTv8;

    //??????????????????
    private TextView betNum;//??????????????????
    private EditText amountEdit;//?????????????????????
    private TextView betButton;//????????????
    private TextView randomButton;//????????????


    //???????????????, ????????????????????????
    private ArrayList<Happy10Model> bothOne =new ArrayList<>();
    private ArrayList<Happy10Model> bothTwo=new ArrayList<>();
    private ArrayList<Happy10Model> bothThree=new ArrayList<>();
    private ArrayList<Happy10Model> bothFour=new ArrayList<>();
    private ArrayList<Happy10Model> bothFive=new ArrayList<>();
    private ArrayList<Happy10Model> bothSix=new ArrayList<>();
    private ArrayList<Happy10Model> bothSeven =new ArrayList<>();
    private ArrayList<Happy10Model> bothEight =new ArrayList<>();
    private ArrayList<ArrayList<Happy10Model>> bothAllList =new ArrayList<>();//??????????????????????????????
    //??????????????????????????????????????????
    private ArrayList<Happy10Model> ballNumList =new ArrayList<>();
    //??????????????????????????????
    private ArrayList<Happy10Model> liamaList =new ArrayList<>();

    /*
   recycleView????????????
    */
    private ArrayList<Happy10Model> selecterList=new ArrayList<>();//????????????item??????
    private RecyclerView mRecy;
    private Happy10Adapter happy10Adapter;
    private ArrayList<Happy10Model> happy10ModelList =new ArrayList<>();
    public static final int ReqTouzhu = 101;

    /*
    ????????????pop
     */
    private PopupWindow sureBetPop;
    private TextView lotteryNameTv;//?????????
    private TextView qishuTv;//??????
    private TextView betAmoumtTv;//????????????(????????????)
    private TextView allBetNumTv;//????????????
    private TextView allBetAmountTv;//???????????????
    private TextView sureButton;
    private TextView cancelButton;
    private RecyclerView sureBetRecy;
    private SureBetPopAdapter sureBetPopAdapter;
    private ArrayList<SureBetPopMeldol> sureBetPopMeldolArrayList=new ArrayList<>();

    private TextView memberMoneyText;//????????????
    private TextView pcddTypeText;//?????????????????????
    private ImageView chooseImg;//????????????pop?????????,?????????????????????

    private TextView name;//????????????????????????name

    private LinearLayout xingyongLinear;//????????????????????????

    private PopupWindow CountDownEndPop;//?????????????????????pop
    private TextView    lastQiShuTv;  //???????????????
    private TextView    newQiShuTv; //????????????
    private TextView countDownEndSure;//???????????????pop???????????????

    private TextView actionBarBack;//?????????

    private long millionSeconds;//?????????????????????
    private long time;//???????????????
    private long shijiancha;//?????????????????????????????????
    private String tqtime;//????????????

    private Long user_id ;
    private int type_id;
    private int game;
    private String typename;

    private ArrayList<MyCornerTextView> myCornerTextViewArrayLi=new ArrayList<>();
    private List<String> openResultList;//????????????num??????

//    private PopupWindow fengpanPop;//????????????????????????pop
    private String[] bothTileList ={Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????),Utils.getString(R.string.?????????)};//?????? ?????????????????????groupName
    private RadioButton lianmaOneBtn;
    private RadioButton lianmaTwoBtn;
    private RadioButton lianmaThreeBtn;
    private RadioButton lianmaFourBtn;
    private RadioButton lianmaFiveBtn;
    private RadioGroup lianmaGroup;
    private String lianmaType;//?????????????????????,??????????????????(????????????????????????????????????,??????text??????????????????type)
    private  ArrayList<String> lianMaIdList =new ArrayList<>();
    private String todayZJ;
    private long jgTime;
    private TextView stopTv;
    private ConstraintLayout loadingLienar;
    private  String isopenOffice;
    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    private TextView is_stop_tv;
    private String isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy10_bet);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        getIntentData();
        Utils.saveLotteryHistory(game,type_id);
        bindView();//????????????
        initChoosePlayTypePop();
        initAllPop();//???????????? ???????????? ???????????????,???????????????
//        initFenpangPop(); //??????pop
        getCountTime();//???????????????
        initRecycle();//recycleView??????
        getMoney();//??????????????????
        initSureBetPop();//????????????pop
        initCountDownEndPop();//??????????????????pop
        requestRule("");
    }

    private void getIntentData() {
        user_id = SharePreferencesUtil.getLong(Happy10BetActivity.this, "user_id", 0l);
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

    @Override
    protected void init() {
    }
//    private void initFenpangPop() {
//        View view = LayoutInflater.from(this).inflate(R.layout.fengpang_popwindow,null);
//        fengpanPop=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
//        fengpanPop.setAnimationStyle(R.style.popAlphaanim0_5);
//        fengpanPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,1f);
//            }
//        });
//    }
    private void initRecycle() {
        mRecy.setAdapter(happy10Adapter);//??????spanSize???????????????????????????
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 60);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //??????position?????????item?????????size
            @Override
            public int getSpanSize(int position) {
//                if(betChoosePalyModelArrayList.get(0).getStatus()==1){
                if(popPosition==0){
                    if(position ==0 ||position ==11||position ==22||position==33||position==44||position==53||position==62||position==71)  {
                        return 60;
                    }
                    else if(position==45||position==46||position==47||position==48||position==49||position==50||position==51||position==52
                            ||position==54 ||position==55||position==56||position==57||position==58||position==59||position==60||position==61
                            ||position==63||position==64||position==65||position==66||position==67||position==68||position==69||position==70
                            ||position==72||position==73||position==74||position==75||position==76||position==77||position==78||position==79  )
                    {
                        return 15;
                    }else{
                        return 12;
                    }
//                }else if(betChoosePalyModelArrayList.get(1).getStatus()==1||betChoosePalyModelArrayList.get(2).getStatus()==1||betChoosePalyModelArrayList.get(3).getStatus()==1||betChoosePalyModelArrayList.get(4).getStatus()==1){
                }else if(popPosition==1||popPosition==2||popPosition==3||popPosition==4){
                    if (position == 0) {
                        return 60;
                    }else if(position==21||position==22||position==23||position==24||position==25||position==26||position==27||position==28||position==29||position==30||position==31||position==32){
                        return 15;
                    }else {
                        return 12;
                    }

                }else if(popPosition==5||popPosition==6||popPosition==7||popPosition==8) {
                    if (position==0){
                        return 60;
                    }else if(position==21||position==22||position==23||position==24||position==25||position==26||position==27||position==28||position==29||position==30||position==31||position==32){
                        return 15;
                    }else if(position==33||position==34||position==35){
                        return 20;
                    }else
                    {
                        return 12;
                    }
                }else {
                    if(position==0){
                        return 60;
                    }else {
                        return 12;
                    }
                }
            }


        });
        mRecy.setLayoutManager(gridLayoutManager);
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//????????????
        happy10Adapter.setOnItemClickListener(this);
    }
    /*
    ????????????pop
     */
    private void initChoosePlayTypePop() {
        choosePlayTypePop.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePop.setTouchable(true);//??????????????????
        choosePlayTypePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = Happy10BetActivity.this.getWindow().getAttributes();
                if(!CountDownEndPop.isShowing()&&!sureBetPop.isShowing()&&!customPopupWindow.classfyPop.isShowing()&&!customPopupWindow.menuPop.isShowing()){
                    ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(Happy10BetActivity.this, R.anim.img_rotate_end_anim);;
                chooseImg.startAnimation(loadAnimation);
            }

        });

    }
    private void requestRule(final String lianmaType) {
        loadingLienar.setVisibility(View.VISIBLE);
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + getGame()+type_id, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id",user_id);
            data.put("type_id",type_id);
            data.put("game",getGame());
            Utils.docking(data, RequestUtil.REQUEST_01gdhappy, -1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LOTTERY_RULE + getGame()+type_id,content);
                    handRuleJson(content, lianmaType);
                }

                @Override
                public void failed(MessageHead messageHead) {
                    loadingLienar.setVisibility(View.GONE);
                }
            });
        }else {
            handRuleJson(jsonStr, lianmaType);
        }

    }

    private void handRuleJson(String content, String lianmaType) {
        loadingLienar.setVisibility(View.GONE);
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
            if(isboth==1){//???????????????
                for (int j = 0; j < bothTileList.length; j++) {
                    String title = bothTileList[j];
                    for (int k = 0; k < rulelist.size(); k++) {
                        JSONObject jsonObject2 = rulelist.getJSONObject(k);
                        String name = jsonObject2.getString("name");
                        BigDecimal odds = jsonObject2.getBigDecimal("odds");
                        String id = jsonObject2.getString("id");
                        String groupname = jsonObject2.getString("groupname");
                        if(title.equals(groupname)){
                            Happy10Model happy8Model = new Happy10Model(name, odds+"", id, Utils.getString(R.string.??????)+"-" + groupname);
                            bothAllList.get(j).add(happy8Model);
                        }
                    }
                }
            } else if (isboth==0&&type==0&&(balled==1||balled==2||balled==3||balled==4||balled==5||balled==6||balled==7||balled==8)){//???1-8????????????
                for (int j = 0; j < bothTileList.length; j++) {
                    String title = bothTileList[j];
                    for (int k = 0; k < rulelist.size(); k++) {
                        JSONObject jsonObject2 = rulelist.getJSONObject(k);
                        Integer group_id = jsonObject2.getInteger("group_id");
                        String name = jsonObject2.getString("name");
                        BigDecimal odds = jsonObject2.getBigDecimal("odds");
                        String id = jsonObject2.getString("id");
                        String groupname = jsonObject2.getString("groupname");
                        if(title.equals(groupname)){
                            Happy10Model happy10Medol = new Happy10Model(name, odds+"", id, title+"-" + groupname);
                            ballNumList.add(happy10Medol);
                        }
                    }
                }
            } else if(isboth==0&&balled==8&&type==2){
                for (int k = 0; k < rulelist.size(); k++) {
                    JSONObject jsonObject2 = rulelist.getJSONObject(k);
//                                Integer group_id = jsonObject2.getInteger("id");
                    String typeName = jsonObject2.getString("name");
                    BigDecimal odds = jsonObject2.getBigDecimal("odds");
                    String name="";
                    String id = jsonObject2.getString("id");
                    String groupname = jsonObject2.getString("groupname");
                    for (int j = 1; j < 21; j++) {
                        name=j+"";
                        Happy10Model happy8Model = new Happy10Model(name, odds + "", id, Utils.getString(R.string.??????)+"-" + lianmaType);
                        happy8Model.setLianmaId(j+"");
                        happy8Model.setLianmaType(typeName);
                        liamaList.add(happy8Model);
                    }
                }
            }
        }
        if(popPosition==0){
            Happy10Model happy8Medol = new Happy10Model(Utils.getString(R.string.?????????), "", "",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????));
            happy10ModelList.add(happy8Medol);
            ArrayList<String> idList=new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            for (int i = 0; i < bothOne.size(); i++) {
                Happy10Model happy8Medol1 = bothOne.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < bothTwo.size(); i++) {
                Happy10Model happy8Medol1 = bothTwo.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);

            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < bothThree.size(); i++) {
                Happy10Model happy8Medol1 = bothThree.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);

            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < bothFour.size(); i++) {
                Happy10Model happy8Medol1 = bothFour.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < bothFive.size(); i++) {
                Happy10Model happy8Medol1 = bothFive.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < bothSix.size(); i++) {
                Happy10Model happy8Medol1 = bothSix.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < bothSeven.size(); i++) {
                Happy10Model happy8Medol1 = bothSeven.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
            happy10ModelList.add(new Happy10Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < bothEight.size(); i++) {
                Happy10Model happy8Medol1 = bothEight.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy10ModelList.add(happy8Medol1);
            }
        }
        else if(popPosition==1||popPosition==2||popPosition==3||popPosition==4||popPosition==5||popPosition==6||popPosition==7||popPosition==8){
            ArrayList<String>idList=new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            String type = betChoosePalyModelArrayList.get(popPosition).getType();
            happy10ModelList.add(new Happy10Model(type,"","",type+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < ballNumList.size(); i++) {
                Happy10Model happy8Medol = ballNumList.get(i);
                String rule_id = happy8Medol.getRule_id();
                String groupname = happy8Medol.getGroupname();
                String betType = happy8Medol.getBetType();
                if(idList.contains(rule_id)){
                    happy8Medol.setStatus(1);
                }
                if(groupname.equals(type+"-"+type)){
                    happy10ModelList.add(happy8Medol);
                }
            }
        }
        else {
            ArrayList<String>idList=new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            happy10ModelList.add(new Happy10Model(lianmaType,"","",Utils.getString(R.string.??????)+"-"+typename));
            for (int i = 0; i <liamaList.size() ; i++) {
                Happy10Model happy8Medol = liamaList.get(i);
                String rule_id = happy8Medol.getRule_id();
                String lianmaId = happy8Medol.getLianmaId();
                if(idList.contains(rule_id)&&lianMaIdList.contains(lianmaId+rule_id)){
                    happy8Medol.setStatus(1);
                }
                if(lianmaType.equals(happy8Medol.getLianmaType())){//?????? ????????? ?????????...???,???????????????textView??????,???????????????????????????????????????
                    happy10ModelList.add(happy8Medol);
                }
            }

        }
        happy10Adapter.notifyDataSetChanged();
    }

    private void getMoney() {


        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                BigDecimal amount = memberMoney.getBigDecimal("amount");
                memberMoneyText.setText(amount+"");
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }
    private void initCountDownEndPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.countdown_end_pop,null);
        lastQiShuTv=view.findViewById(R.id.last_qishu);
        newQiShuTv=view.findViewById(R.id.new_qihao);
        countDownEndSure=view.findViewById(R.id.countdown_pop_sure);
        countDownEndSure.setOnClickListener(this);
        CountDownEndPop= new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        CountDownEndPop.setAnimationStyle(R.style.popAlphaanim0_5);
        CountDownEndPop.setTouchable(true);//??????????????????
        CountDownEndPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(!customPopupWindow.happy10TodayOpenResultPop.isShowing()&&!sureBetPop.isShowing()&&!customPopupWindow.classfyPop.isShowing()&&!customPopupWindow.menuPop.isShowing()&&!choosePlayTypePop.isShowing()&&!customPopupWindow.gameRulePop.isShowing()){
                    ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,1f);
                }
            }
        });
    }
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1://??????????????????????????????,???????????????????????????(??????????????????????????????????????????????????????????????????,?????????????????????)
                    initOpenResult();//????????????????????????
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
        loadingLienar=findViewById(R.id.loading_linear);
        timeLinear=findViewById(R.id.time_linear);
        timeLoadingLinear=findViewById(R.id.time_load_linear);
        stopTv=findViewById(R.id.stop_textview);
        stopTv.setOnClickListener(this);
        actionBarBack=findViewById(R.id.bet_actionbar_back);
        actionBarBack.setOnClickListener(this);

        hourText=findViewById(R.id.hour);
        minutesText=findViewById(R.id.minutes);
        secondsText=findViewById(R.id.seconds);

        openResultLinear=findViewById(R.id.ssc_open_resule_linear);
        happy10NumTv1=findViewById(R.id.num_one);
        happy10NumTv2=findViewById(R.id.num_two);
        happy10NumTv3=findViewById(R.id.num_three);
        happy10NumTv4=findViewById(R.id.num_four);
        happy10NumTv5=findViewById(R.id.num_five);
        happy10NumTv6=findViewById(R.id.num_six);
        happy10NumTv7=findViewById(R.id.num_seven);
        happy10NumTv8=findViewById(R.id.num_eight);
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
        betNum=findViewById(R.id.bet_num);
        amountEdit=findViewById(R.id.amount_edit);
        betButton=findViewById(R.id.bet_button);
        randomButton=findViewById(R.id.random_button);
        randomButton.setOnClickListener(this);
        betButton.setOnClickListener(this);

        rotateImg=findViewById(R.id.rotate_image);
        rotateImg.setOnClickListener(this);
        nowQishuText=findViewById(R.id.now_qishu);
        lastQiShuText=findViewById(R.id.last_qishu);
        showClassfyLinear=findViewById(R.id.show_classfy_linear);
        showClassfyLinear.setOnClickListener(this);
        rightMenu=findViewById(R.id.right_menu);
        rightMenu.setOnClickListener(this);
        todayOpenResultLinear=findViewById(R.id.today_open_result);
        todayOpenResultLinear.setOnClickListener(this);
        choosePlayType=findViewById(R.id.choose_play_type);
        choosePlayType.setOnClickListener(this);
        popTargetLinear=findViewById(R.id.choose_pop_target);

        mRecy=findViewById(R.id.happy10_bet_recycle);
        happy10Adapter=new Happy10Adapter(happy10ModelList,selecterList);

        name=findViewById(R.id.name);
        name.setText(typename);
        /*
        ????????????poprecycleView??????
         */
        View view = LayoutInflater.from(this).inflate(R.layout.happy10_play_type_pop,null);
        choosePlayTypePop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        choosePlayRecy=view.findViewById(R.id.happy_10_choose_play_recy);
        betChoosePlayAdapter=new BetChoosePlayAdapter(betChoosePalyModelArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        choosePlayRecy.setLayoutManager(gridLayoutManager);
        choosePlayRecy.setAdapter(betChoosePlayAdapter);
        BetChoosePalyModel betChoosePalyModel = new BetChoosePalyModel(Utils.getString(R.string.??????));
        betChoosePalyModel.setStatus(1);
        betChoosePalyModelArrayList.add(betChoosePalyModel);
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.?????????)));
        betChoosePalyModelArrayList.add(new BetChoosePalyModel(Utils.getString(R.string.??????)));

        //????????????????????????????????????????????????????????????(???????????????????????????,??????????????????????????????,???????????????(?????????????????????????????????,??????))
        bothAllList.add(bothOne);
        bothAllList.add(bothTwo);
        bothAllList.add(bothThree);
        bothAllList.add(bothFour);
        bothAllList.add(bothFive);
        bothAllList.add(bothSix);
        bothAllList.add(bothSeven);
        bothAllList.add(bothEight);

        //????????????????????????
        lianmaGroup=findViewById(R.id.lianma_radioGroup);
        lianmaOneBtn=findViewById(R.id.lianma_one);
        lianmaTwoBtn=findViewById(R.id.lianma_two);
        lianmaThreeBtn=findViewById(R.id.lianma_three);
        lianmaFourBtn=findViewById(R.id.lianma_four);
        lianmaFiveBtn=findViewById(R.id.lianma_five);
        lianmaOneBtn.setOnClickListener(this);
        lianmaTwoBtn.setOnClickListener(this);
        lianmaThreeBtn.setOnClickListener(this);
        lianmaFourBtn.setOnClickListener(this);
        lianmaFiveBtn.setOnClickListener(this);
        lianmaOneBtn.performClick();

        /**
         *????????????pop???????????????
         */
        betChoosePlayAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==betChoosePalyModelArrayList.size()-1){//??????????????????,???????????????????????????button,?????????????????????
                    lianmaGroup.setVisibility(View.VISIBLE);
                    clearSelector();//????????????
                    happy10Adapter.notifyDataSetChanged();
                }else{//???????????? Utils.getString(R.string.??????)????????????,???????????????????????????????????????????????????item,?????????,??????????????????????????????,???????????????????????????(??????clear,??????remove.
                    // ????????????????????? ?????? ??????,???????????????????????????????????????,?????? ?????? ?????????????????????,???????????????????????? ?????? ???item)
                    lianmaGroup.setVisibility(View.GONE);
                    for (int i = 0; i < selecterList.size(); i++) {
                        Happy10Model happy8Model = selecterList.get(i);
                        if(happy8Model.getGroupname().contains(Utils.getString(R.string.??????))){//???????????????medel???groupname??????Utils.getString(R.string.??????),???????????????????????????
//                            selecterList.remove(happy8Model);
                            clearSelector();
                            randomButton.setText(Utils.getString(R.string.??????));
                            happy10Adapter.notifyDataSetChanged();
                        }
                    }
                }
                popPosition=position;
                requestRule(lianmaType);
                choosePlayTypePop.dismiss();
                pcddTypeText.setText(betChoosePalyModelArrayList.get(popPosition).getType());
                if(mRecy!=null){
                    mRecy.getLayoutManager().scrollToPosition(0);
                }
            }
        });

        xingyongLinear=findViewById(R.id.xingyong_play);
        xingyongLinear.setOnClickListener(this);
        if(isopenOffice.equals("0")){
            xingyongLinear.setVisibility(View.INVISIBLE);
            xingyongLinear.setClickable(false);
        }else {
            xingyongLinear.setVisibility(View.VISIBLE);
            xingyongLinear.setClickable(true);
        }
        pcddTypeText=findViewById(R.id.pcdd_type);
        chooseImg=findViewById(R.id.choose_img);
//        playTypeText=findViewById(R.id.play_type_text);
        memberMoneyText=findViewById(R.id.member_money);




        /*
        ????????????????????????
         */

        for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
            myCornerTextViewArrayLi.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayLi.get(i).setCornerSize(50);
        }

    }

    private void initAllPop() {
        //????????????pop????????????
        customPopupWindow.initClassfyPop(this,this);
        //????????????pop???????????? typename????????????????????????button
        customPopupWindow.initClassfyData(this,typename);
//        ????????????pop
        customPopupWindow.initMenuPop(this,this);
        //?????? ???????????? typename:????????? game:??????game  tyoe_id: ??????type_id
        customPopupWindow.toBetNote(this,typename,game,type_id);
        //?????? ????????????
        customPopupWindow.toInvestCenter(this);
        //??????????????????
        customPopupWindow.tovVipCenter(this);
        //?????? ???????????? type_id: ???????????????typ_id  lotteryClassId: ?????????????????????id
        customPopupWindow.toOpenResult(this,type_id,game);
        //game ????????????(??????????????????????????????????????????????????????) typename: ?????????: ???????????????????????????????????????
        customPopupWindow.initGameRule(this,game,typename,0,false);
        //??????????????????pop
        customPopupWindow.showGameRulePop(this,false);;
        //????????????pop???????????????
        customPopupWindow.initHappy10TodayResult(this,game,type_id,false);
        //??????????????????
        customPopupWindow.toTwoChangLongAty(this,game,type_id);
        //??????????????????
        customPopupWindow.toTodayWinLose(this,game,type_id);
        //??????????????????
        customPopupWindow.toOnlineKf(this);
        jgTime=customPopupWindow.getJgTIme(game,type_id);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(runnableTime!=null){
            handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime,300);
        if(isWaitOpen){
            if(runnableRandom!=null){
                handler.removeCallbacks(runnableRandom);
            }
            handler.postDelayed(runnableRandom,150);
//            System.out.println("gundong  isWait");
        }
        if (isWaitOpen){
            if(runnableRequestOpen!=null){
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen,jgTime);
//            System.out.println(Utils.getString(R.string.????????????10s  isWait));
        }
//        handler.postDelayed(runnableBoolen,5000);
        if(runnableZj!=null){
            handler.removeCallbacks(runnableZj);
        }
        handler.postDelayed(runnableZj,2000);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        float alpha = lp.alpha;
        if(alpha==1f){
            CountDownEndPop.dismiss();
        }
    }

    private void getCountTime() {
        showLoadingLinear();
        HashMap<String, Object> data = new HashMap<>();
        String domain = SharePreferencesUtil.getString(Happy10BetActivity.this, "domain", "");
        data.put("type_id",type_id);
        data.put("source",2);
        Utils.docking(data, RequestUtil.REQUEST_03happyten, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                String stoptimestr = jsonObject.getString("stoptimestr");//?????????????????????
                nowQishu = jsonObject.getString("qishu");//????????????
                if(StringMyUtil.isEmptyString(nowQishu)){//????????????,????????????
                    stopTv.setVisibility(View.VISIBLE);
//                    fengpanPop.showAtLocation(actionBarBack, Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,0.7f);
                    nowQishuText.setText(Utils.getString(R.string.???));
                    lastQiShuText.setText("-- -- -- "+Utils.getString(R.string.???????????????));
                    hourText.setText("--");
                    minutesText.setText("--");
                    secondsText.setText("--");
                    handler.removeCallbacks(runnableRandom);
                    handler.removeCallbacks(runnableTime);
                    handler.removeCallbacks(runnableRequestOpen);
                    openResultLinear.setVisibility(View.INVISIBLE);}
                else{
//                    if(fengpanPop.isShowing()){
//                        fengpanPop.dismiss();
//                    }
                    stopTv.setVisibility(View.GONE);
                    if(openResultLinear.getVisibility()!=View.VISIBLE){
                        openResultLinear.setVisibility(View.VISIBLE);
                    }
                    if(runnableRandom==null){
                        handler.postDelayed(runnableRandom,150);
                    }
                    if(runnableTime==null){
                        handler.postDelayed(runnableTime,300);
                    }
                    if(runnableRequestOpen==null){
                        handler.postDelayed(runnableRequestOpen,jgTime);
                    }

                        tqtime = jsonObject.getString("tqtime");//????????????(?????????????????????????????????)
                    nowQishuText.setText(nowQishu+Utils.getString(R.string. ???));
                    lastQiShuText.setText((Long.parseLong(nowQishu)-1)+" "+Utils.getString(R.string.???????????????));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//?????????????????????
//                        time = Long.parseLong(Utils.getFileData("time"));//???????????????
                        long nowTime = System.currentTimeMillis();//????????????
                        shijiancha = SharePreferencesUtil.getLong(Happy10BetActivity.this,"shijiancha",0l);//?????????????????????????????????
                        countTime = millionSeconds + shijiancha - nowTime -(Long.parseLong(tqtime)*1000);
//                        if(num==1){
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                showTimeLInear();
                                }
                            },600);
//                        }
                        /*else {
                            timeLoadingLinear.setVisibility(View.GONE);
                            timeLinear.setVisibility(View.VISIBLE);
                        }*/
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(num==1){//?????????????????????????????????,???????????????????????????????????????(?????????????????????,??????????????????,??????????????????????????????)
                        Message message = Message.obtain();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                    num++;
                }

            }
            @Override
            public void failed(MessageHead messageHead) {
//                showtoast(messageHead.getInfo());
//                showtoast(Utils.getString(R.string.???????????????));
                showLoadingLinear();
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
        if(isStart.equals("0")){
            is_stop_tv.setVisibility(View.VISIBLE);
            timeLoadingLinear.setVisibility(View.GONE);
            timeLinear.setVisibility(View.GONE);
        }else {
            timeLinear.setVisibility(View.VISIBLE);
            timeLoadingLinear.setVisibility(View.GONE);
            is_stop_tv.setVisibility(View.GONE);
        }
    }
    private void initOpenResult() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        Utils.docking(data, RequestUtil.REQUEST_04happyten, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("happytenLotterylist");
                if(happytenLotterylist.size()==0){
                    if(happytenLotterylist.size()==0){
                        showToast(getString(R.string.????????????????????????));
                        return;
                    }
                }
                JSONObject jsonObject = happytenLotterylist.getJSONObject(0);
                String lotteryqishu = jsonObject.getString("lotteryqishu");
                if(!StringMyUtil.isEmptyString(nowQishu)){
                    if(Long.parseLong(lotteryqishu)==(Long.parseLong(nowQishu)-1)){
                        isWaitOpen=false;
                        String lotteryNo = jsonObject.getString("lotteryNo");
                        String[] split = lotteryNo.split(",");
                        openResultList = Arrays.asList(split);
                        for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
                            myCornerTextViewArrayLi.get(i).setText(openResultList.get(i));
                        }
                    }else {
                        isWaitOpen=true;
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
        switch (v.getId()){
            case R.id.stop_textview:
                showToast(Utils.getString(R.string.??????????????????));
                break;
            case R.id.rotate_image:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);;
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            case R.id.show_classfy_linear://????????????????????????pop
                customPopupWindow.showClassfyPop(showClassfyLinear,this);
                break;
            case R.id.xingyong_play:
                showToast(Utils.getString(R.string.???????????????));
                break;
            case R.id.right_menu:
                customPopupWindow.showMenuPop(rightMenu,this);
                break;
            case R.id.today_open_result:
                customPopupWindow.initHappy10TodayResultData(this,todayOpenResultLinear,type_id);
//                customPopupWindow.showHappy8TodayResultPop(todayOpenResultLinear,this);
                break;
            case R.id.choose_play_type:
                choosePlayTypePop.showAsDropDown(popTargetLinear, Gravity.BOTTOM,0,0);
                ProgressDialogUtil.darkenBackground(this,0.5f);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);;
                loadAnimation.setFillAfter(true);//???????????????,????????????????????????
                chooseImg.startAnimation(loadAnimation);
                break;
                //chat_chat_img_rotate_start_anim
            case R.id.lianma_one:
                requestRule(lianmaOneBtn.getText().toString());
                lianmaType=lianmaOneBtn.getText().toString();
                clearSelector();
                break;
            case R.id.lianma_two:
                requestRule(lianmaTwoBtn.getText().toString());
                lianmaType=lianmaTwoBtn.getText().toString();
                clearSelector();//??????????????????
                break;
            case R.id.lianma_three:
                requestRule(lianmaThreeBtn.getText().toString());
                lianmaType=lianmaThreeBtn.getText().toString();
                clearSelector();
                break;
            case R.id.lianma_four:
                requestRule(lianmaFourBtn.getText().toString());
                lianmaType=lianmaFourBtn.getText().toString();
                clearSelector();
                break;
            case R.id.lianma_five:
                requestRule(lianmaFiveBtn.getText().toString());
                lianmaType=lianmaFiveBtn.getText().toString();
                clearSelector();
                break;
            case R.id.random_button:
                int max=0;
                if(lianmaGroup.getVisibility()!=View.VISIBLE){ //??????????????????,??????????????????
                    if(randomButton.getText().toString().equals(Utils.getString(R.string.??????))){
                        xingYongRandom();
                    }else{
                        for (int i = 0; i < happy10ModelList.size(); i++) {
                            happy10ModelList.get(i).setStatus(0);
                        }
                        clearSelector();
                    }
                }else{
                    //???????????????????????????????????????
                    if(lianmaOneBtn.isChecked()||lianmaTwoBtn.isChecked()){

                        if(randomButton.getText().toString().equals(Utils.getString(R.string.??????))){
                            randomBet(happy10ModelList,2);
                            betNum.setText(1+"");
                            randomButton.setText(Utils.getString(R.string.??????));
                        }else{
                            for (int i = 0; i < happy10ModelList.size(); i++) {
                                happy10ModelList.get(i).setStatus(0);
                            }
                            clearSelector();
                        }
                    }
                    //???????????????
                    else if(lianmaThreeBtn.isChecked()){//
                        if(randomButton.getText().toString().equals(Utils.getString(R.string.??????))){
                            randomBet(happy10ModelList,3);
                            betNum.setText(1+"");
                            randomButton.setText(Utils.getString(R.string.??????));
                        }else{
                            for (int i = 0; i < happy10ModelList.size(); i++) {
                                happy10ModelList.get(i).setStatus(0);
                            }
                            clearSelector();
                        }
                    }
                    //???????????????
                    else if(lianmaFourBtn.isChecked()){//


                        if(randomButton.getText().toString().equals(Utils.getString(R.string.??????))){
                            randomBet(happy10ModelList,4);
                            betNum.setText(1+"");
                            randomButton.setText(Utils.getString(R.string.??????));
                        }else{
                            for (int i = 0; i < happy10ModelList.size(); i++) {
                                happy10ModelList.get(i).setStatus(0);
                            }
                            clearSelector();
                        }
                    }
                    //???????????????
                    else{//
                        if(randomButton.getText().toString().equals(Utils.getString(R.string.??????))){
                            randomBet(happy10ModelList,5);
                            betNum.setText(1+"");
                            randomButton.setText(Utils.getString(R.string.??????));
                        }else{
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
                        if(happy10Model.getRule_id().equals(rule_id)){
                            if(j>max){
                                max=j;
                            }
                        }
                    }
                }
                happy10Adapter.notifyDataSetChanged();
                mRecy.getLayoutManager().scrollToPosition(max);
                break;
            case R.id.bet_button:
                if(Utils.isFastClick()){
                    return;
                }
                int size = selecterList.size();
                String editText = amountEdit.getText().toString();
                if(stopTv.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.?????????????????????));
                }
               else if(lianmaGroup.getVisibility()==View.VISIBLE){
                    if(lianmaOneBtn.isChecked()||lianmaTwoBtn.isChecked()){
                        if(size<2){
                            showToast(Utils.getString(R.string.???????????????));
                        }else if(StringMyUtil.isEmptyString(editText)){
                            showToast(Utils.getString(R.string.?????????????????????));
                        }
                        else{
                           /* betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //????????????pop???????????????
                            lotteryNameTv.setText(name.getText().toString());//?????????
                            qishuTv.setText(nowQishu);//????????????
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,0.5f);
                            hintKbTwo();//???????????????*/
                            checkBet(true);
                        }
                    }else if(lianmaThreeBtn.isChecked()){
                        if(size<3){
                            showToast(Utils.getString(R.string.???????????????));
                        }else if(StringMyUtil.isEmptyString(editText)){
                            showToast(Utils.getString(R.string.?????????????????????));
                        }else{
                           /* betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //????????????pop???????????????
                            lotteryNameTv.setText(name.getText().toString());//?????????
                            qishuTv.setText(nowQishu);//????????????
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,0.5f);
                            hintKbTwo();//???????????????*/
                            checkBet(true);
                        }
                    }else if(lianmaFourBtn.isChecked()){
                        if(size<4){
                            showToast(Utils.getString(R.string.???????????????));
                        }else if(StringMyUtil.isEmptyString(editText)){
                            showToast(Utils.getString(R.string.?????????????????????));
                        }
                        else{
                            /*betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //????????????pop???????????????
                            lotteryNameTv.setText(name.getText().toString());//?????????
                            qishuTv.setText(nowQishu);//????????????
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,0.5f);
                            hintKbTwo();//???????????????*/
                            checkBet(true);
                        }
                    }else{
                        if(size<5){
                            showToast(Utils.getString(R.string.???????????????));
                        }else if(StringMyUtil.isEmptyString(editText)){
                            showToast(Utils.getString(R.string.?????????????????????));
                        }
                        else{
                            /*betButton.setClickable(false);
                            ArrayList<Happy10Model> list = new ArrayList<>();
                            initSureBetPopData(list); //????????????pop???????????????
                            lotteryNameTv.setText(name.getText().toString());//?????????
                            qishuTv.setText(nowQishu);//????????????
                            allBetNumTv.setText(1+"");
                            allBetAmountTv.setText((Long.parseLong(inputTv))+"");
                            betAmoumtTv.setText(inputTv);
                            sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,0.5f);
                            hintKbTwo();//???????????????*/
                            checkBet(true);
                        }
                    }
                }else {
                    if(size==0){
                        showToast(Utils.getString(R.string.???????????????));
                    } else if(StringMyUtil.isEmptyString(editText)){
                        showToast(Utils.getString(R.string.?????????????????????));
                    }else{
                        /*betButton.setClickable(false);
                        ArrayList<Happy10Model> list = new ArrayList<>();
                        initSureBetPopData(list); //????????????pop???????????????
                        lotteryNameTv.setText(name.getText().toString());//?????????
                        qishuTv.setText(nowQishu);//????????????
                        allBetNumTv.setText(size+"");
                        allBetAmountTv.setText((Long.parseLong(inputTv)*size)+"");
                        betAmoumtTv.setText(inputTv);
                        sureBetPop.showAtLocation(betButton,Gravity.CENTER,0,0);
                        ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,0.5f);
                        hintKbTwo();//???????????????*/
                        checkBet(false);
                }

                }
                break;
            case R.id.sure_bet_cancel:
                sureBetPop.dismiss();
                break;
            case R.id.sure_bet_sure:
                sureButton.setClickable(false);
                if(lianmaGroup.getVisibility()==View.VISIBLE){//????????????
                    requestBet(selecterList.size(),  amountEdit.getText().toString(),true);
                }else{//????????????
                    requestBet(selecterList.size(),  amountEdit.getText().toString(),false);
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

    private void checkBet(boolean isLianma){
        List<TouzhuModel> touzhuList = new ArrayList<>();
        Collections.sort(selecterList);
        if (!isLianma){
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
        }else {
            TouzhuModel touzhuModel = new TouzhuModel();
            List<String> groupname = Arrays.asList(selecterList.get(0).getGroupname().split("-"));
            if (groupname.size()==2){
                touzhuModel.setGroupname(groupname.get(1));
            } else if (groupname.size()==3){
                touzhuModel.setGroupname(groupname.get(1)+"-"+groupname.get(2));
            }else {
                touzhuModel.setGroupname(groupname.get(0));
            }


            String name = "";
            for (int i=0;i<selecterList.size();i++){
                name = name + selecterList.get(i).getBetType()+",";
            }
            name = name.substring(0,name.length()-1);
            touzhuModel.setName(name);
            touzhuModel.setId(String.valueOf(selecterList.get(0).getRule_id()));
            touzhuModel.setMoney(amountEdit.getText().toString());
            touzhuList.add(touzhuModel);
        }

        String ma = "";
        if (isLianma){
            for (Happy10Model happy10Model:selecterList){
                ma = ma+happy10Model.getBetType()+",";
            }
            ma = ma.substring(0,ma.length()-1);
        }

        Intent intent = new Intent();
        intent.setClass(Happy10BetActivity.this, TouzhuActivity.class);
        intent.putExtra("touzhuList", (Serializable) touzhuList);
        intent.putExtra("game", String.valueOf(game));
        intent.putExtra("type_id", String.valueOf(type_id));
        intent.putExtra("money", amountEdit.getText().toString());
        intent.putExtra("qishu", nowQishu);
        intent.putExtra("index", "");
        intent.putExtra("ma", ma);
        startActivityForResult(intent, ReqTouzhu);
    }

    //???????????????????????????????????? ????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // ??????????????????????????????????????????
            for (int i = 0; i < happy10ModelList.size(); i++) {
                happy10ModelList.get(i).setStatus(0);
            }
            selecterList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size+"");
            happy10Adapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.??????));
            getMoney();//?????????,??????????????????
        }

    }

    /**
     * ????????????
     * @param happy10MedolList
     * @param num  ?????????????????????
     */
    private void randomBet(ArrayList<Happy10Model> happy10MedolList,int num) {
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
            if(betType.equals(Utils.getString(R.string.?????????))|| betType.equals(R.string.?????????)|| betType.equals(Utils.getString(R.string.?????????))|| betType.equals(Utils.getString(R.string.?????????))|| betType.equals(Utils.getString(R.string.????????????))){
                randomBet(happy10MedolList,num);
                break;
            }else {
                break;
            }
        }
    }

    private void clearSelector() {
        selecterList.clear();
        int size = selecterList.size();
        amountEdit.setText("");
        betNum.setText(size + "");
        randomButton.setText(Utils.getString(R.string.??????));
    }

    private void initSureBetPopData(ArrayList<Happy10Model> list) {
        for (int i = 0; i <selecterList.size() ; i++) {
            Happy10Model happy8Model = selecterList.get(i);
            String groupname = happy8Model.getGroupname();
            String betType = happy8Model.getBetType();
            if(!list.contains(happy8Model)){
                happy8Model=new Happy10Model();
                happy8Model.setGroupname(groupname);
//                happy8Model.setBetType(betType);
                list.add(happy8Model);
            }
        }
        for (int i = 0; i <list.size() ; i++) {
            Happy10Model happy8Model = list.get(i);
            for (int j = 0; j <selecterList.size() ; j++) {
                Happy10Model model = selecterList.get(j);
                if(happy8Model.getGroupname().equalsIgnoreCase(model.getGroupname())){
                    String rule_id = model.getRule_id();
                    String betType = model.getBetType();
                    String rebate = model.getRebate();
                    happy8Model.setRule_id(happy8Model.getRule_id()+","+rule_id);
                    String modelBetType = happy8Model.getBetType();
                    if(StringMyUtil.isEmptyString(modelBetType)){
                        modelBetType="";
                    }
                    happy8Model.setBetType(modelBetType +","+betType);
                }
            }
        }
        sureBetPopMeldolArrayList.clear();

        for (int i = 0; i < list.size(); i++) {
            Happy10Model model1 = list.get(i);
            String groupname = model1.getGroupname();
            String betType = model1.getBetType();
            String substring = betType.substring(1, betType.length());
            String finalStr1=groupname+":"+"<font color=\"#FF0000\">"+substring+"</font>";
            sureBetPopMeldolArrayList.add(new SureBetPopMeldol(finalStr1));
        }
          /*
        ????????????,?????????(??????????????????sureBetPopMeldolArrayList,??????????????????????????????)
         */
        for (int i = 0; i < sureBetPopMeldolArrayList.size()-1; i++) {
            for (int j = i+1; j < sureBetPopMeldolArrayList.size(); j++) {
                if(sureBetPopMeldolArrayList.get(i).equals(sureBetPopMeldolArrayList.get(j))){
                    sureBetPopMeldolArrayList.remove(j);
                    j--;
                }
            }
        }
        sureBetPopAdapter.notifyDataSetChanged();
    }
    private void xingYongRandom() {
        selecterList.clear();
        if(happy10ModelList.size()==0){
            return;
        }
        int random = new Random().nextInt(happy10ModelList.size());
        happy10ModelList.get(random).setStatus(1);
        selecterList.add(happy10ModelList.get(random));
        int size = selecterList.size();
        betNum.setText(size+"");
        randomButton.setText(Utils.getString(R.string.??????));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            if(betType.equals(Utils.getString(R.string.?????????))||betType.equals(Utils.getString(R.string.?????????))||betType.equals(Utils.getString(R.string.?????????))
                    ||betType.equals(Utils.getString(R.string.?????????))||betType.equals(Utils.getString(R.string.?????????))||betType.equals(Utils.getString(R.string.?????????))
                    ||betType.equals(Utils.getString(R.string.?????????))||betType.equals(Utils.getString(R.string.?????????))){
                xingYongRandom();
            }else{
                break;
            }
        }
    }
    private void requestBet(int size, String editText,boolean isLianma) {
        HashMap<String, Object> data = new HashMap<>();
        if(!isLianma){
            String needString="";
            for (int i = 0; i < size; i++) {
                Happy10Model happy8Medol = selecterList.get(i);
                String rule_id = happy8Medol.getRule_id();
                needString+=rule_id+",";
            }
            needString = needString.substring(0, needString.length()-1);
            String amountStr="";
            for (int i = 0; i < size; i++) {
                amountStr+=editText+",";
            }
            amountStr = amountStr.substring(0, amountStr.length()-1);
            String token = SharePreferencesUtil.getString(Happy10BetActivity.this, "token", "");
            String domain = SharePreferencesUtil.getString(Happy10BetActivity.this, "domain", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long l = System.currentTimeMillis();
            Date date = new Date(l);
            String format = df.format(date);
            data.put("user_id",user_id);
            data.put("type_id",type_id);
            data.put("rule_id",needString);
            data.put("amount",amountStr);
            data.put("lotteryqishu",nowQishu);
            data.put("source",2);
            data.put("curtime",format);//????????????
            data.put("token",token);//??????token
        }else{
            String rule_id = selecterList.get(0).getRule_id();
            String lianma="";//????????????????????????
            for (int i = 0; i < size; i++) {
                String betType = selecterList.get(i).getBetType();
                lianma+=betType+",";
            }
            lianma = lianma.substring(0, lianma.length()-1);
            String amount =editText.toString();
            String token = SharePreferencesUtil.getString(Happy10BetActivity.this, "token", "");
            String domain = SharePreferencesUtil.getString(Happy10BetActivity.this, "domain", "");
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            long l = System.currentTimeMillis();
            Date date = new Date(l);
            String format = df.format(date);
            data.put("user_id",user_id);
            data.put("type_id",type_id);
            data.put("rule_id",rule_id);
            data.put("amount",amount);
            data.put("lotteryqishu",nowQishu);
            data.put("source",2);
            data.put("curtime",format);//????????????
            data.put("token",token);//??????token
            data.put("lianma",lianma);//??????token
        }

        Utils.docking(data, RequestUtil.REQUEST_02happyten, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
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
                betNum.setText(size+"");
                happy10Adapter.notifyDataSetChanged();
                sureBetPop.dismiss();
                randomButton.setText(Utils.getString(R.string.??????));
                getMoney();//?????????,??????????????????
            }
            @Override
            public void failed(MessageHead messageHead) {
                betButton.setClickable(true);
                sureButton.setClickable(true);
                JSONObject headData = messageHead.getData();
                showToast(Utils.getString(R.string.????????????)+"\n" + messageHead.getInfo());
                sureBetPop.dismiss();

            }
        });
    }

    /*
     ?????????????????????
      */
    Runnable runnableTime =new Runnable() {
        @Override
        public void run() {
          if(countTime<=0){
              getCountTime();
          }else{
              countTime = millionSeconds + shijiancha - System.currentTimeMillis() -(Long.parseLong(tqtime)*1000);
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
              if(countTime<=1){
                  countTime=0;
                  if(CountDownEndPop!=null){
                      lastQiShuTv.setText(nowQishu);
                      newQiShuTv.setText((Long.parseLong(nowQishu)+1)+"");
                      if(!isFinishing()&&CountDownEndPop!=null&&!CountDownEndPop.isShowing()&&secondsText.getText().toString().equals("00")){
                          CountDownEndPop.showAtLocation(minutesText, Gravity.CENTER,0,0);
                          ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,0.5f);
                      }
                  }
                  isWaitOpen=true;
              }
          }

                handler.postDelayed(runnableTime,300);

        }
    };

    /*
    ?????????????????????????????????
     */
    Runnable runnableRandom =new Runnable() {
        @Override
        public void run() {
            if(isWaitOpen){
                int i = new Random().nextInt(9) + 1;
                for (int j = 0; j < myCornerTextViewArrayLi.size(); j++) {
                    myCornerTextViewArrayLi.get(j).setText(i+"");
                }
            }
            else{
                for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
                    myCornerTextViewArrayLi.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRandom,150);
        }
    };

    Runnable runnableRequestOpen =new Runnable() {
        @Override
        public void run() {
            if(isWaitOpen){
                initOpenResult();
            }else{
                for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
                    myCornerTextViewArrayLi.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRequestOpen,jgTime);
        }
    };

    /**
     * ????????????recycleView ??????????????????
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
        ToBetAtyUtils.toBetActivity(Happy10BetActivity.this,game,typename,type_id,isopenOffice,isStart);
        finish();
    }
      /*
      ????????????(??????????????????????????????)
       */
    Runnable runnableZj =new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name",SharePreferencesUtil.getString(Happy10BetActivity.this,"nickname",""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    todayZJ = jsonObject.getString("todayZJ");
                    if(todayZJ.equals("1")){
                        Message message = Message.obtain();
                        message.what=2;
                        handler.sendMessage(message);
                    }

                }

                @Override
                public void failed(MessageHead messageHead) {

                }
            });
            handler.postDelayed(runnableZj,2000);
        }
    };
    /**
     * ????????????pop?????????????????????
     * @param view
     */
    @Override
    public void onMenuClicked(View view) {

    }

    /**
     * ????????????recycleView??????????????????
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        int size = selecterList.size();
        if(popPosition==betChoosePalyModelArrayList.size()-1){//????????? ????????????(?????????????????????item??????????????????,?????????,??????????????????id??????????????????)
            String lianmaId = happy10ModelList.get(position).getLianmaId();
            String rule_id = happy10ModelList.get(position).getRule_id();
            if(lianMaIdList.contains(lianmaId+rule_id)){
                    lianMaIdList.remove(lianmaId+rule_id);;
            }else {
                lianMaIdList.add(lianmaId+rule_id);
            }
        }

        if(lianmaGroup.getVisibility()==View.VISIBLE){
            if(lianmaOneBtn.isChecked()||lianmaTwoBtn.isChecked()){
                if(size>2){
                    showToast(Utils.getString(R.string.???????????????2?????????));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                }else if(size<2){
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.??????));
                }else {
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.??????));
                }
            }else if(lianmaThreeBtn.isChecked()){
                if(size>3){
                    showToast(Utils.getString(R.string.???????????????3?????????));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                }else if(size<3){
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.??????));
                }else{
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.??????));
                }
            }else if(lianmaFourBtn.isChecked()){
                if(size>4){
                    showToast(Utils.getString(R.string.???????????????4?????????));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                }else if(size<4){
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.??????));
                }else{
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.??????));
                }
            }else{
                if(size>5){
                    showToast(Utils.getString(R.string.???????????????5?????????));
                    Happy10Model happy8Model = happy10ModelList.get(position);
                    happy8Model.setStatus(0);
                    betNum.setText("1");
                    selecterList.remove(happy8Model);
                }else if(size<5){
                    betNum.setText("0");
                    randomButton.setText(Utils.getString(R.string.??????));
                }else{
                    betNum.setText("1");
                    randomButton.setText(Utils.getString(R.string.??????));
                }
            }

        }
        else{
            betNum.setText(size+"");//????????????????????????,???????????????seleterList???size(selecterList?????????????????????item????????????????????????position?????????model??????,???????????????????????????,????????????medol??????)
            if(size!=0){
                randomButton.setText(Utils.getString(R.string.??????));
            }else {
                randomButton.setText(Utils.getString(R.string.??????));
            }
        }
    }
    /*
    ????????????pop
     */
    private void initSureBetPop() {
        sureBetPopAdapter=new SureBetPopAdapter(sureBetPopMeldolArrayList);
        View view = LayoutInflater.from(this).inflate(R.layout.sure_bet_pop,null);
        View footView = LayoutInflater.from(view.getContext()).inflate(R.layout.sure_bet_pop_recycle_foot, null);
        View headView = LayoutInflater.from(view.getContext()).inflate(R.layout.sure_bet_pop_recycle_head, null);
        sureBetPopAdapter.addHeaderView(headView);
        sureBetPopAdapter.addFooterView(footView);
        lotteryNameTv=headView.findViewById(R.id.lottery_name);
        qishuTv=headView.findViewById(R.id.lottery_qishu);
        betAmoumtTv=headView.findViewById(R.id.bet_amount);
        allBetNumTv=footView.findViewById(R.id.all_bet_num);
        allBetAmountTv=footView.findViewById(R.id.all_bet_amout);
        cancelButton=view.findViewById(R.id.sure_bet_cancel);
        sureButton=view.findViewById(R.id.sure_bet_sure);
        sureBetRecy=view.findViewById(R.id.sure_bet_pop_recycly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        sureBetRecy.setLayoutManager(linearLayoutManager);
        sureBetRecy.setAdapter(sureBetPopAdapter);
        cancelButton.setOnClickListener(this);
        sureButton.setOnClickListener(this);
        sureBetPop= new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        sureBetPop.setAnimationStyle(R.style.pop_scale_animation);
        sureBetPop.setTouchable(true);//??????????????????
        sureBetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(!CountDownEndPop.isShowing()||!choosePlayTypePop.isShowing()){
                    ProgressDialogUtil.darkenBackground(Happy10BetActivity.this,1f);
                }
                betButton.setClickable(true);
            }
        });

    }
        /*
        ???????????????
         */

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
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
        /*1??????3???
        2???????????????
        3????????????
        4???????????????
        5????????????
        6??????8???
        7????????????
        8?????????10??????
        9???11???5*/
        return 8;
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        initAllPop();
        if(!CountDownEndPop.isShowing()){
            ProgressDialogUtil.darkenBackground(this,1f);
        }
//        handler.postDelayed(runnableRandom,150);
//        handler.postDelayed(runnableRequestOpen,jgTime);
//        handler.postDelayed(runnableTime,300);
//        handler.postDelayed(runnableZj,2000);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        customPopupWindow.selectorId(hbGameClassModel);
    }
    @Override
    public void onNetChange(boolean netWorkState) {
        if(!netWorkState){
            showToast(Utils.getString(R.string.????????????????????????????????????));
            findViewById(R.id.no_network).setVisibility(View.VISIBLE);
            ProgressDialogUtil.show(this);
            handler.removeCallbacks(runnableRandom);
            handler.removeCallbacks(runnableRequestOpen);
            handler.removeCallbacks(runnableZj);
            handler.removeCallbacks(runnableTime);

        }else{
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
