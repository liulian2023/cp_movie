package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong.happy8;

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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter.Happy8Adapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy8Model;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;
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

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import pl.droidsonroids.gif.GifImageView;

public class Happy8Activity extends BaseActivity implements View.OnClickListener, CustomPopupWindow.OnItemClidkListener, CustomPopupWindow.OnMenuPopClickListener,  Happy8Adapter.OnRecyclerViewItemClickListener {
    private LinearLayout timeLinear;
    private LinearLayout timeLoadingLinear;
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
    private RadioButton liangmian;//??????button
    private RadioButton wuxing;//??????button
    private RadioButton zhengma;//??????button
    private LinearLayout openResultLinear;
    private MyCornerTextView k8NumTv1;
    private MyCornerTextView k8NumTv2;
    private MyCornerTextView k8NumTv3;
    private MyCornerTextView k8NumTv4;
    private MyCornerTextView k8NumTv5;
    private MyCornerTextView k8NumTv6;
    private MyCornerTextView k8NumTv7;
    private MyCornerTextView k8NumTv8;
    private MyCornerTextView k8NumTv9;
    private MyCornerTextView k8NumTv10;
    private MyCornerTextView k8NumTv11;
    private MyCornerTextView k8NumTv12;
    private MyCornerTextView k8NumTv13;
    private MyCornerTextView k8NumTv14;
    private MyCornerTextView k8NumTv15;
    private MyCornerTextView k8NumTv16;
    private MyCornerTextView k8NumTv17;
    private MyCornerTextView k8NumTv18;
    private MyCornerTextView k8NumTv19;
    private MyCornerTextView k8NumTv20;

    private TextView betNum;//??????????????????
    private EditText amountEdit;//?????????????????????
    private TextView betButton;//????????????
    private TextView randomButton;//????????????

    private ArrayList<Happy8Model> zongheList =new ArrayList<>();
    private ArrayList<Happy8Model> qianhouheList =new ArrayList<>();
    private ArrayList<Happy8Model> danshaungheList =new ArrayList<>();
    private ArrayList<Happy8Model> wuxingList =new ArrayList<>();
    private ArrayList<Happy8Model> zhengmaList =new ArrayList<>();
    /*
   recycleView????????????
    */
    private ArrayList<Happy8Model> selecterList=new ArrayList<>();//????????????item??????
    private RecyclerView mRecy;
    private Happy8Adapter happy8Adapter;
    private ArrayList<Happy8Model> happy8ModelArrayList =new ArrayList<>();
    /*
    ????????????pop
     */
    private PopupWindow sureBetPop;//
    private TextView sureButton;
    private TextView cancelButton;

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
    private  List<String> openResultList;

//    private PopupWindow fengpanPop;

    private String todayZJ ;
    private LinearLayout noNetLeanear;
    private LinearLayout open_result_linear;
    private TextView fail_refresh_tv;
    public static final int ReqTouzhu = 101;
    private ConstraintLayout loadingLinear;
    private TextView stoptv;
    private long jgTime;
    private  String isopenOffice;
    private String isStart;
    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    private TextView is_stop_tv;
    private GifImageView count_down_fail_loading_iv;
    private int failCount=0;
    private PublishSubject failCountObservable =PublishSubject.create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_cai);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        getIntentData();
        Utils.saveLotteryHistory(game,type_id);
        bindView();//????????????
        initChoosePlayTypePop();
        initAllPop();//???????????? ???????????? ???????????????,???????????????
//        initFenpangPop(); //??????pop
        getCountTime(null);//???????????????
        initRecycle();
        getMoney();
        initCountDownEndPop();//??????????????????pop
        observableFailCount();//???????????????????????????

    }

    private void getIntentData() {
        ProgressDialogUtil.show(this);
        user_id = SharePreferencesUtil.getLong(Happy8Activity.this, "user_id", 0l);
         /*
        ??????????????????,???????????????????????????????????????
         */
        Intent intent = getIntent();
        type_id = intent.getIntExtra("type_id", 0);
        game = intent.getIntExtra("game", 0);
        typename = intent.getStringExtra("typename");
        isopenOffice = intent.getStringExtra("isopenOffice");//0 ???????????????
        if(StringMyUtil.isEmptyString(isopenOffice)){
            isopenOffice="";
        }
        isStart = intent.getStringExtra("isStart");
    }

    private void observableFailCount() {
        failCountObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
    @Override
    protected void init() {

    }
    private void initRecycle() {
        mRecy.setAdapter(happy8Adapter);//??????spanSize???????????????????????????
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 30);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //??????position????????????????????????size
            @Override
            public int getSpanSize(int position) {
                if(liangmian.isChecked()){
                    if(position ==0 ||position ==10||position ==14) {
                        return 30;
                    }else{
                        return 10;//???1/3
                    }
                } else if(wuxing.isChecked()){
                    if(position==0){
                        return 30;
                    }else if(position==1||position==2){
                        return 15;
                    }else {
                        return 10;
                    }
                } else{
                    if(position==0){
                        return 30;
                    }else {
                        return 6;
                    }
//
                }
            }


        });
        mRecy.setLayoutManager(gridLayoutManager);
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//????????????
        happy8Adapter.setOnItemClickListener(this);
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
                if(!CountDownEndPop.isShowing()){
                    ProgressDialogUtil.darkenBackground(Happy8Activity.this,1f);
                }
                Animation loadAnimation = AnimationUtils.loadAnimation(Happy8Activity.this, R.anim.img_rotate_end_anim);;
                chooseImg.startAnimation(loadAnimation);
            }
        });

    }
    private void requestRule() {
        loadingLinear.setVisibility(View.VISIBLE);
        String jsonStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.LOTTERY_RULE + getGame()+type_id, "");
        if(StringMyUtil.isEmptyString(jsonStr)){
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id",user_id);//??????????????????
            data.put("type_id",type_id);
            data.put("game", getGame());//??????????????????
            Utils.docking(data, RequestUtil.REQUEST_01hapc, -1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LOTTERY_RULE +getGame()+type_id,content);
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
        ProgressDialogUtil.stop(Happy8Activity.this);
        happy8ModelArrayList.clear();
//                selecterList.clear();
        zongheList.clear();
        qianhouheList.clear();
        danshaungheList.clear();
        wuxingList.clear();
        zhengmaList.clear();
        JSONObject jsonObject1 = JSONObject.parseObject(content);
        JSONArray rulelistAll = jsonObject1.getJSONArray("rulelistAll");
        for (int i = 0; i < rulelistAll.size(); i++) {
            JSONObject jsonObject = rulelistAll.getJSONObject(i);
            JSONArray rulelist = jsonObject.getJSONArray("Rulelist");
            for (int j = 0; j < rulelist.size(); j++) {
                JSONObject jsonObject2 = rulelist.getJSONObject(j);
                Integer group_id = jsonObject2.getInteger("group_id");
                String name = jsonObject2.getString("name");
                BigDecimal odds = jsonObject2.getBigDecimal("odds");
                String id = jsonObject2.getString("id");
                String groupname = jsonObject2.getString("groupname");

                if(group_id==1){
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,Utils.getString(R.string.??????)+"-"+groupname);
                    ArrayList<String> idList=new ArrayList<>();
                    for (int a = 0; a < selecterList.size(); a++) {
                        String rule_id = selecterList.get(a).getRule_id();
                        idList.add(rule_id);
                    }
                    if(idList.contains(id)){
                        happy8Medol.setStatus(1);
                    }
                    zongheList.add(happy8Medol);
                }else if(group_id==0){
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,Utils.getString(R.string.??????)+"-"+groupname);
                    ArrayList<String>idList=new ArrayList<>();
                    for (int a = 0; a < selecterList.size(); a++) {
                        String rule_id = selecterList.get(a).getRule_id();
                        idList.add(rule_id);
                    }
                    if(idList.contains(id)){
                        happy8Medol.setStatus(1);
                    }
                    qianhouheList.add(happy8Medol);
                }else if(group_id==2){
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,Utils.getString(R.string.??????)+"-"+groupname);
                    ArrayList<String>idList=new ArrayList<>();
                    for (int a = 0; a < selecterList.size(); a++) {
                        String rule_id = selecterList.get(a).getRule_id();
                        idList.add(rule_id);
                    }
                    if(idList.contains(id)){
                        happy8Medol.setStatus(1);
                    }
                    danshaungheList.add(happy8Medol);
                }else if(group_id==3){
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,groupname);
                    ArrayList<String>idList=new ArrayList<>();
                    for (int a = 0; a < selecterList.size(); a++) {
                        String rule_id = selecterList.get(a).getRule_id();
                        idList.add(rule_id);
                    }
                    if(idList.contains(id)){
                        happy8Medol.setStatus(1);
                    }
                    wuxingList.add(happy8Medol);
                }else {
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,groupname);
                    ArrayList<String>idList=new ArrayList<>();
                    for (int a = 0; a < selecterList.size(); a++) {
                        String rule_id = selecterList.get(a).getRule_id();
                        idList.add(rule_id);
                    }
                    if(idList.contains(id)){
                        happy8Medol.setStatus(1);
                    }
                    zhengmaList.add(happy8Medol);
                }
            }
        }

        if(liangmian.isChecked()){
            Happy8Model happy8Medol = new Happy8Model(Utils.getString(R.string.??????), "", "",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.??????));
            happy8ModelArrayList.add(happy8Medol);
            ArrayList<String>idList=new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            for (int i = 0; i < zongheList.size(); i++) {
                Happy8Model happy8Medol1 = zongheList.get(i);
                String rule_id = happy8Medol.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol.setStatus(1);
                }
                happy8ModelArrayList.add(happy8Medol1);
            }
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < qianhouheList.size(); i++) {
                Happy8Model happy8Medol1 = qianhouheList.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy8ModelArrayList.add(happy8Medol1);

            }
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.?????????),"","",Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????)));
            for (int i = 0; i < danshaungheList.size(); i++) {
                Happy8Model happy8Medol1 = danshaungheList.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy8ModelArrayList.add(happy8Medol1);

            }
        }else if(wuxing.isChecked()){
            ArrayList<String>idList=new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.??????),"","",Utils.getString(R.string.??????)));
            for (int i = 0; i < wuxingList.size(); i++) {
                Happy8Model happy8Medol = wuxingList.get(i);
                String rule_id = happy8Medol.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol.setStatus(1);
                }
                happy8ModelArrayList.add(happy8Medol);
            }
        }else {
            ArrayList<String>idList=new ArrayList<>();
            for (int a = 0; a < selecterList.size(); a++) {
                String rule_id = selecterList.get(a).getRule_id();
                idList.add(rule_id);
            }
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.??????),"","",Utils.getString(R.string.??????)));
            for (int i = 0; i <zhengmaList.size() ; i++) {
                Happy8Model happy8Medol = zhengmaList.get(i);
                String rule_id = happy8Medol.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol.setStatus(1);
                }
                happy8ModelArrayList.add(happy8Medol);
            }
        }
        happy8Adapter.notifyDataSetChanged();
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
                if(!customPopupWindow.bjTodayOpenResultPop.isShowing()&&!sureBetPop.isShowing()&&!customPopupWindow.classfyPop.isShowing()&&!customPopupWindow.menuPop.isShowing()&&!choosePlayTypePop.isShowing()&&!customPopupWindow.gameRulePop.isShowing()){
                    ProgressDialogUtil.darkenBackground(Happy8Activity.this,1f);
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
        count_down_fail_loading_iv=findViewById(R.id.count_down_fail_loading_iv);
        open_result_linear=findViewById(R.id.open_result_linear);
        fail_refresh_tv=findViewById(R.id.fail_refresh_tv);
        fail_refresh_tv.setOnClickListener(this);
        timeLinear=findViewById(R.id.time_linear);
        timeLoadingLinear=findViewById(R.id.time_load_linear);
        stoptv=findViewById(R.id.stop_textview);
        stoptv.setOnClickListener(this);
        loadingLinear=findViewById(R.id.loading_linear);
        actionBarBack=findViewById(R.id.bet_actionbar_back);
        actionBarBack.setOnClickListener(this);
        noNetLeanear=findViewById(R.id.no_internet);
        hourText=findViewById(R.id.hour);
        minutesText=findViewById(R.id.minutes);
        secondsText=findViewById(R.id.seconds);

        openResultLinear=findViewById(R.id.beijin_happy8_open_result);
        k8NumTv1=findViewById(R.id.bj_num_one);
        k8NumTv2=findViewById(R.id.bj_num_two);
        k8NumTv3=findViewById(R.id.bj_num_three);
        k8NumTv4=findViewById(R.id.bj_num_four);
        k8NumTv5=findViewById(R.id.bj_num_five);
        k8NumTv6=findViewById(R.id.bj_num_six);
        k8NumTv7=findViewById(R.id.bj_num_seven);
        k8NumTv8=findViewById(R.id.bj_num_eight);
        k8NumTv9=findViewById(R.id.bj_num_nine);
        k8NumTv10=findViewById(R.id.bj_num_ten);
        k8NumTv11=findViewById(R.id.bj_num_11);
        k8NumTv12=findViewById(R.id.bj_num_12);
        k8NumTv13=findViewById(R.id.bj_num_13);
        k8NumTv14=findViewById(R.id.bj_num_14);
        k8NumTv15=findViewById(R.id.bj_num_15);
        k8NumTv16=findViewById(R.id.bj_num_16);
        k8NumTv17=findViewById(R.id.bj_num_17);
        k8NumTv18=findViewById(R.id.bj_num_18);
        k8NumTv19=findViewById(R.id.bj_num_19);
        k8NumTv20=findViewById(R.id.bj_num_20);

        myCornerTextViewArrayLi.add(k8NumTv1);
        myCornerTextViewArrayLi.add(k8NumTv2);
        myCornerTextViewArrayLi.add(k8NumTv3);
        myCornerTextViewArrayLi.add(k8NumTv4);
        myCornerTextViewArrayLi.add(k8NumTv5);
        myCornerTextViewArrayLi.add(k8NumTv6);
        myCornerTextViewArrayLi.add(k8NumTv7);
        myCornerTextViewArrayLi.add(k8NumTv8);
        myCornerTextViewArrayLi.add(k8NumTv9);
        myCornerTextViewArrayLi.add(k8NumTv10);
        myCornerTextViewArrayLi.add(k8NumTv11);
        myCornerTextViewArrayLi.add(k8NumTv12);
        myCornerTextViewArrayLi.add(k8NumTv13);
        myCornerTextViewArrayLi.add(k8NumTv14);
        myCornerTextViewArrayLi.add(k8NumTv15);
        myCornerTextViewArrayLi.add(k8NumTv16);
        myCornerTextViewArrayLi.add(k8NumTv17);
        myCornerTextViewArrayLi.add(k8NumTv18);
        myCornerTextViewArrayLi.add(k8NumTv19);
        myCornerTextViewArrayLi.add(k8NumTv20);
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

        mRecy=findViewById(R.id.happy_bet_recycle);
        happy8Adapter=new Happy8Adapter(happy8ModelArrayList,selecterList);

        name=findViewById(R.id.name);
        name.setText(typename);

        View view = LayoutInflater.from(this).inflate(R.layout.pcdandan_play_type_pop,null);
        choosePlayTypePop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);

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

        betNum=findViewById(R.id.bet_num);
        amountEdit=findViewById(R.id.amount_edit);
        betButton=findViewById(R.id.bet_button);
        randomButton=findViewById(R.id.random_button);
        randomButton.setOnClickListener(this);
        betButton.setOnClickListener(this);


        liangmian=view.findViewById(R.id.happy8_liangmian);
        liangmian.setOnClickListener(this);
        wuxing=view.findViewById(R.id.happy8_wuxing);
        wuxing.setOnClickListener(this);
        zhengma=view.findViewById(R.id.happy8_zhengma);
        zhengma.setOnClickListener(this);
        liangmian.performClick();
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
        customPopupWindow.initHappy8TodayResult(this,type_id,false);
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
            handler.postDelayed(runnableRandom,250);
//            System.out.println("gundong  isWait");
        }
        if (isWaitOpen){
            if(runnableRequestOpen!=null){
                handler.removeCallbacks(runnableRequestOpen);
            }
            handler.postDelayed(runnableRequestOpen,jgTime);
//            System.out.println(Utils.getString(R.string.????????????10s  isWait));
        }
        if(CountDownEndPop.isShowing()){
            ProgressDialogUtil.darkenBackground(this,0.5f);
        }
        if(runnableZj!=null){
            handler.removeCallbacks(runnableZj);
        }
        handler.postDelayed(runnableZj,2000);
//        handler.postDelayed(runnableBoolen,5000);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        float alpha = lp.alpha;
        if(alpha==1f){
            CountDownEndPop.dismiss();
        }
    }

    private void getCountTime(GifImageView count_down_fail_loading_iv) {
       showLoadingLinear();
       if(count_down_fail_loading_iv!=null){
           count_down_fail_loading_iv.setVisibility(View.VISIBLE);
       }
        HashMap<String, Object> data = new HashMap<>();
        String domain = SharePreferencesUtil.getString(Happy8Activity.this, "domain", "");
        data.put("type_id",type_id);
        data.put("source",2);
        Utils.docking(data, RequestUtil.REQUEST_03ha, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                if(count_down_fail_loading_iv!=null){
                    count_down_fail_loading_iv.setVisibility(View.GONE);
                }
                failCount = 0;
                failCountObservable.onNext(failCount);
                JSONObject jsonObject = JSONObject.parseObject(content);
                String stoptimestr = jsonObject.getString("stoptimestr");//?????????????????????
                nowQishu = jsonObject.getString("qishu");//????????????
                if(StringMyUtil.isEmptyString(nowQishu)){//????????????,????????????
                    stoptv.setVisibility(View.VISIBLE);
                    ProgressDialogUtil.darkenBackground(Happy8Activity.this,0.7f);
                    nowQishuText.setText("000000 "+Utils.getString(R.string.???));
                    lastQiShuText.setText("0000000 "+Utils.getString(R.string.???????????????));
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
                    stoptv.setVisibility(View.GONE);
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
                        long nowTime = System.currentTimeMillis();//????????????
                        shijiancha = SharePreferencesUtil.getLong(Happy8Activity.this,"shijiancha",0l);//?????????????????????????????????
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
                            timeLinear.setVisibility(View.VISIBLE);
                            timeLoadingLinear.setVisibility(View.GONE);
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
                if(count_down_fail_loading_iv!=null){
                    count_down_fail_loading_iv.setVisibility(View.GONE);
                }
                failCount++;
                failCountObservable.onNext(failCount);
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
        Utils.docking(data, RequestUtil.REQUEST_04ha, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                failCount=0;
                failCountObservable.onNext(failCount);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("happyLotterylist");
                if(happytenLotterylist.size()==0){
                    showToast(getString(R.string.????????????????????????));
                    return;
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
                failCount++;
                failCountObservable.onNext(failCount);
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
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            case R.id.show_classfy_linear://????????????????????????pop
                customPopupWindow.showClassfyPop(showClassfyLinear,this);
                break;
            case R.id.xingyong_play:
                showToast(getString(R.string.???????????????????????????));
                break;
            case R.id.right_menu:
                customPopupWindow.showMenuPop(rightMenu,this);
                break;
            case R.id.today_open_result:
                customPopupWindow.initHappy8TodayResultData(this,todayOpenResultLinear,type_id);
//                CustomPopupWindow.showHappy8TodayResultPop(todayOpenResultLinear,this);
                break;
            case R.id.choose_play_type:
                choosePlayTypePop.showAsDropDown(popTargetLinear, Gravity.BOTTOM,0,0);
                ProgressDialogUtil.darkenBackground(this,0.5f);
                Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.chat_img_rotate_start_anim);;
                loadAnimation.setFillAfter(true);//???????????????,????????????????????????
                chooseImg.startAnimation(loadAnimation);
                break;

            case R.id.happy8_liangmian:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size()+"");
//                playTypeText.setText(hunheRb.getText().toString());
                pcddTypeText.setText(liangmian.getText().toString());
                break;
            case R.id.happy8_wuxing:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size()+"");
//                playTypeText.setText(boseRb.getText().toString());
                pcddTypeText.setText(wuxing.getText().toString());
                break;
            case R.id.happy8_zhengma:
                requestRule();
                choosePlayTypePop.dismiss();
                betNum.setText(selecterList.size()+"");
//                playTypeText.setText(temaRb.getText().toString());
                pcddTypeText.setText(zhengma.getText().toString());
                break;
            case R.id.random_button:
                int max=0;
                if(randomButton.getText().toString().equals(Utils.getString(R.string.??????))){
                    xingYongRandom();
                }else{
                    for (int i = 0; i < happy8ModelArrayList.size(); i++) {
                        happy8ModelArrayList.get(i).setStatus(0);
                    }
                    selecterList.clear();
                    int size = selecterList.size();
                    amountEdit.setText("");
                    betNum.setText(size+"");
                    randomButton.setText(Utils.getString(R.string.??????));
                }
                for (int i = 0; i < selecterList.size(); i++) {
                    String rule_id = selecterList.get(i).getRule_id();
                    for (int j = 0; j < happy8ModelArrayList.size(); j++) {
                        Happy8Model happy8Model = happy8ModelArrayList.get(j);
                        if(happy8Model.getRule_id().equals(rule_id)){
                            if(j>max){
                                max=j;
                            }
                        }
                    }
                }
                happy8Adapter.notifyDataSetChanged();
                mRecy.getLayoutManager().scrollToPosition(max);
                break;
            case R.id.bet_button:
                if(Utils.isFastClick()){
                    return;
                }
                int size = selecterList.size();
                String editText = amountEdit.getText().toString();
                if(stoptv.getVisibility()==View.VISIBLE){
                    showToast(Utils.getString(R.string.?????????????????????));
                }
                else  if(size==0){
                    showToast(Utils.getString(R.string.???????????????));
                } else if(StringMyUtil.isEmptyString(editText)){
                    showToast(Utils.getString(R.string.?????????????????????));
                }else{
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
                    intent.setClass(Happy8Activity.this, TouzhuActivity.class);
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
                requestBet(selecterList.size(),  amountEdit.getText().toString());
                break;
            case R.id.countdown_pop_sure:
                CountDownEndPop.dismiss();
                break;
            case R.id.bet_actionbar_back:
                finish();
                break;
            case R.id.fail_refresh_tv:
                initOpenResult();
                getCountTime(count_down_fail_loading_iv);
                break;
            default:
                break;

        }
    }
    //???????????????????????????????????? ????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // ??????????????????????????????????????????
            for (int i = 0; i < happy8ModelArrayList.size(); i++) {
                happy8ModelArrayList.get(i).setStatus(0);
            }
            selecterList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size+"");
            happy8Adapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.??????));
            getMoney();
        }

    }

    private void xingYongRandom() {
        selecterList.clear();
        if(happy8ModelArrayList.size()==0){
            return;
        }
        int random = new Random().nextInt(happy8ModelArrayList.size());
        happy8ModelArrayList.get(random).setStatus(1);
        selecterList.add(happy8ModelArrayList.get(random));
        int size = selecterList.size();
        betNum.setText(size+"");
        randomButton.setText(Utils.getString(R.string.??????));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            if(betType.equals(Utils.getString(R.string.??????))||betType.equals(Utils.getString(R.string.?????????))||betType.equals(Utils.getString(R.string.?????????))||betType.equals(Utils.getString(R.string.??????))||betType.equals(Utils.getString(R.string.??????))){
                xingYongRandom();
            }else{
                break;
            }
        }
    }

    private void requestBet(int size, String editText) {
        HashMap<String, Object> data = new HashMap<>();
        String needString="";
        for (int i = 0; i < size; i++) {
            Happy8Model happy8Medol = selecterList.get(i);
            String rule_id = happy8Medol.getRule_id();
            needString+=rule_id+",";
        }
        needString = needString.substring(0, needString.length()-1);
        String amountStr="";
        for (int i = 0; i < size; i++) {
            amountStr+=editText+",";
        }
        String token = SharePreferencesUtil.getString(Happy8Activity.this, "token", "");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String domain = SharePreferencesUtil.getString(Happy8Activity.this, "domain", "");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = df.format(date);
        amountStr = amountStr.substring(0, amountStr.length()-1);
        data.put("user_id",user_id);
        data.put("type_id",type_id);
        data.put("rule_id",needString);
        data.put("amount",amountStr);
        data.put("lotteryqishu",nowQishu);
        data.put("source",2);
        data.put("curtime",format);//????????????
        data.put("token",token);//??????token
        Utils.docking(data, RequestUtil.REQUEST_02ha, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                betButton.setClickable(true);
                sureButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(content);
                showToast(jsonObject.getString("message"));
                for (int i = 0; i < happy8ModelArrayList.size(); i++) {
                    happy8ModelArrayList.get(i).setStatus(0);
                }
                selecterList.clear();
                int size = selecterList.size();
                amountEdit.setText("");
                betNum.setText(size+"");
                happy8Adapter.notifyDataSetChanged();
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
     ?????????????????????
      */
    Runnable runnableTime =new Runnable() {
        @Override
        public void run() {
            if(failCount>6){
                return;
            }
            if (countTime<=0){
                getCountTime(null);
            }else {
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
                        if(!isFinishing()&&CountDownEndPop!=null&!CountDownEndPop.isShowing()&&secondsText.getText().toString().equals("00")){
                            CountDownEndPop.showAtLocation(minutesText, Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(Happy8Activity.this,0.5f);
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
            handler.postDelayed(runnableRandom,250);
        }
    };

    Runnable runnableRequestOpen =new Runnable() {
        @Override
        public void run() {
            if(isWaitOpen&&failCount<=6){
                handler.removeCallbacks(runnableRequestOpen);
                initOpenResult();
            }else{
                for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
                    myCornerTextViewArrayLi.get(i).setText(openResultList.get(i));
                }
            }
            handler.postDelayed(runnableRequestOpen,jgTime);
        }
    };
    /*
    ????????????(??????????????????????????????)
     */
    Runnable runnableZj =new Runnable() {
        @Override
        public void run() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_name",SharePreferencesUtil.getString(Happy8Activity.this,"nickname",""));
            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
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
        ToBetAtyUtils.toBetActivity(Happy8Activity.this,game,typename,type_id,isopenOffice,isStart);
        finish();
    }

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
        betNum.setText(size+"");
        if(size!=0){
            randomButton.setText(Utils.getString(R.string.??????));
        }else {
            randomButton.setText(Utils.getString(R.string.??????));
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        if(!CountDownEndPop.isShowing()){
            ProgressDialogUtil.darkenBackground(this,1f);
        }
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
        return 6;
    }
    @Subscribe (threadMode = ThreadMode.MAIN,sticky =  true)
    public void updateXian(HbGameClassModel hbGameClassModel){
      customPopupWindow.selectorId(hbGameClassModel);
    }


    @Override
    public void onNetChange(boolean netWorkState) {
        if(netWorkState){
            noNetLeanear.setVisibility(View.GONE);
            ProgressDialogUtil.stop(this);
            handler.post(runnableRandom);
            handler.post(runnableRequestOpen);
            handler.post(runnableZj);
            handler.post(runnableTime);
        }else {
            noNetLeanear.setVisibility(View.VISIBLE);
            ProgressDialogUtil.show(this);
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
