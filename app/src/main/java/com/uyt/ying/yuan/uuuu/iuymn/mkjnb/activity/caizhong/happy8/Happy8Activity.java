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
    private TextView hourText;//倒计时小时数
    private TextView minutesText;//倒计时分钟数
    private TextView secondsText;//倒计时秒数
    private long countTime ;//倒计时时间
    private TextView nowQishuText;//当前期数
    private TextView lastQiShuText;//上一期期数(用于显示在滚动开奖结果的上方,并且用于判断是否需要滚动(lastQiShuText与每10秒请求的开奖结果中第一个数据的lotteryqishu相同时,表示已经开奖,需要停止滚动))
    private String nowQishu;//当前期数
    private ImageView rotateImg;//余额刷新图片
    private boolean isWaitOpen =true;//是否在等待开奖(初始值必须为true,否则所有需要判断isWaitOpen判断的runnable都不会循环操作,导致开奖号码一直停在第一次赋值的时候)
    private int num=1;//用于判断是否是第一次请求倒计时接口
    private LinearLayout showClassfyLinear;//点击弹出彩种分类pop
    private TextView rightMenu;//点击弹出右侧菜单pop
    private LinearLayout todayOpenResultLinear;//点击弹出今日开奖结果
    private LinearLayout choosePlayType;//点击弹出玩法类型pop
    private LinearLayout popTargetLinear;//玩法类型pop定位控件
    /*
   玩法类型pop
    */
    private PopupWindow choosePlayTypePop;
    private RadioButton liangmian;//两面button
    private RadioButton wuxing;//五行button
    private RadioButton zhengma;//特码button
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

    private TextView betNum;//已选中多少注
    private EditText amountEdit;//投注金额输入框
    private TextView betButton;//下注按钮
    private TextView randomButton;//机选按钮

    private ArrayList<Happy8Model> zongheList =new ArrayList<>();
    private ArrayList<Happy8Model> qianhouheList =new ArrayList<>();
    private ArrayList<Happy8Model> danshaungheList =new ArrayList<>();
    private ArrayList<Happy8Model> wuxingList =new ArrayList<>();
    private ArrayList<Happy8Model> zhengmaList =new ArrayList<>();
    /*
   recycleView相关控件
    */
    private ArrayList<Happy8Model> selecterList=new ArrayList<>();//当前选中item容器
    private RecyclerView mRecy;
    private Happy8Adapter happy8Adapter;
    private ArrayList<Happy8Model> happy8ModelArrayList =new ArrayList<>();
    /*
    下注清单pop
     */
    private PopupWindow sureBetPop;//
    private TextView sureButton;
    private TextView cancelButton;

    private TextView memberMoneyText;//用户余额
    private TextView pcddTypeText;//选择栏的玩法名
    private ImageView chooseImg;//玩法选择pop弹出时,需要旋转的图片

    private TextView name;//彩票分类栏的彩票name

    private LinearLayout xingyongLinear;//信用官方玩法切换

    private PopupWindow CountDownEndPop;//倒计时结束时的pop
    private TextView    lastQiShuTv;  //截止的期数
    private TextView    newQiShuTv; //新的期数
    private TextView countDownEndSure;//倒计时结束pop的确定按钮

    private TextView actionBarBack;//返回键

    private long millionSeconds;//倒计时结束时间
    private long time;//服务器时间
    private long shijiancha;//服务器时间与本地时间差
    private String tqtime;//提前时间

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
        bindView();//绑定控件
        initChoosePlayTypePop();
        initAllPop();//投注记录 开奖结果 彩票分类等,相关初始化
//        initFenpangPop(); //封盘pop
        getCountTime(null);//请求倒计时
        initRecycle();
        getMoney();
        initCountDownEndPop();//倒计时结束的pop
        observableFailCount();//订阅倒计时失败次数

    }

    private void getIntentData() {
        ProgressDialogUtil.show(this);
        user_id = SharePreferencesUtil.getLong(Happy8Activity.this, "user_id", 0l);
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
        mRecy.setAdapter(happy8Adapter);//设置spanSize之前要先设置适配器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 30);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //返回position对应的条目所占的size
            @Override
            public int getSpanSize(int position) {
                if(liangmian.isChecked()){
                    if(position ==0 ||position ==10||position ==14) {
                        return 30;
                    }else{
                        return 10;//占1/3
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
//        mRecy.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.GRIDLAYOUT));//添加间距
        happy8Adapter.setOnItemClickListener(this);
    }
    /*
    玩法类型pop
     */
    private void initChoosePlayTypePop() {
        choosePlayTypePop.setAnimationStyle(R.style.popAlphaanim0_3);
        choosePlayTypePop.setTouchable(true);//响应内部点击
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
            data.put("user_id",user_id);//版本号对比用
            data.put("type_id",type_id);
            data.put("game", getGame());//版本号对比用
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
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,Utils.getString(R.string.两面)+"-"+groupname);
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
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,Utils.getString(R.string.两面)+"-"+groupname);
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
                    Happy8Model happy8Medol = new Happy8Model(name, odds + "", id,Utils.getString(R.string.两面)+"-"+groupname);
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
            Happy8Model happy8Medol = new Happy8Model(Utils.getString(R.string.总和), "", "",Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.总和));
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
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.前后和),"","",Utils.getString(R.string.两面)+"-"+Utils.getString(R.string.前后和)));
            for (int i = 0; i < qianhouheList.size(); i++) {
                Happy8Model happy8Medol1 = qianhouheList.get(i);
                String rule_id = happy8Medol1.getRule_id();
                if(idList.contains(rule_id)){
                    happy8Medol1.setStatus(1);
                }
                happy8ModelArrayList.add(happy8Medol1);

            }
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.单双和),"","",Utils.getString(R.string.连码)+"-"+Utils.getString(R.string.单双和)));
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
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.五行),"","",Utils.getString(R.string.五行)));
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
            happy8ModelArrayList.add(new Happy8Model(Utils.getString(R.string.正码),"","",Utils.getString(R.string.正码)));
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
        CountDownEndPop.setTouchable(true);//响应内部点击
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
        开奖结果样式设置
         */

        for (int i = 0; i < myCornerTextViewArrayLi.size(); i++) {
            myCornerTextViewArrayLi.get(i).setfilColor(Color.parseColor("#bf1f24"));
            myCornerTextViewArrayLi.get(i).setCornerSize(50);
        }
    }

    private void initAllPop() {
        //彩票分类pop相关设置
        customPopupWindow.initClassfyPop(this,this);
        //彩票分类pop数据请求 typename用于默认选中某个button
        customPopupWindow.initClassfyData(this,typename);
//        右侧菜单pop
        customPopupWindow.initMenuPop(this,this);
        //跳转 投注记录 typename:彩票名 game:彩票game  tyoe_id: 彩票type_id
        customPopupWindow.toBetNote(this,typename,game,type_id);
        //跳转 充值中心
        customPopupWindow.toInvestCenter(this);
        //跳转会员中心
        customPopupWindow.tovVipCenter(this);
        //跳转 开奖结果 type_id: 当前彩票的typ_id  lotteryClassId: 当前彩种分类的id
        customPopupWindow.toOpenResult(this,type_id,game);
        //game 彩票类型(用于判断需要跳转到哪种类型的游戏规则) typename: 彩票名: 用于设置规则说明中的彩票名
        customPopupWindow.initGameRule(this,game,typename,0,false);
        //弹出游戏规则pop
        customPopupWindow.showGameRulePop(this,false);;
        //开奖结果pop视图初始化
        customPopupWindow.initHappy8TodayResult(this,type_id,false);
        //跳转两面长龙
        customPopupWindow.toTwoChangLongAty(this,game,type_id);
        //跳转今日输赢
        customPopupWindow.toTodayWinLose(this,game,type_id);
        //跳转在线客服
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
//            System.out.println(Utils.getString(R.string.请求接口10s  isWait));
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
                String stoptimestr = jsonObject.getString("stoptimestr");//倒计时结束时间
                nowQishu = jsonObject.getString("qishu");//当前期数
                if(StringMyUtil.isEmptyString(nowQishu)){//期数为空,表示封盘
                    stoptv.setVisibility(View.VISIBLE);
                    ProgressDialogUtil.darkenBackground(Happy8Activity.this,0.7f);
                    nowQishuText.setText("000000 "+Utils.getString(R.string.期));
                    lastQiShuText.setText("0000000 "+Utils.getString(R.string.期开奖号码));
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
                    tqtime = jsonObject.getString("tqtime");//封盘时间(倒计时需要减去封盘时间)
                    nowQishuText.setText(nowQishu+Utils.getString(R.string. 期));
                    lastQiShuText.setText((Long.parseLong(nowQishu)-1)+" "+Utils.getString(R.string.期开奖号码));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//倒计时结束时间
                        long nowTime = System.currentTimeMillis();//当前时间
                        shijiancha = SharePreferencesUtil.getLong(Happy8Activity.this,"shijiancha",0l);//服务器时间和当地时间差
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
                    if(num==1){//第一次请求倒计时数据时,才发送消息通知请求开奖结果(因为请求一次后,期数不会为空,可以直接请求开奖结果)
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
//                showtoast(Utils.getString(R.string.网络不给力));
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
                    showToast(getString(R.string.开奖结果获取失败));
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
                showToast(Utils.getString(R.string.该彩种已封盘));
                break;
            case R.id.rotate_image:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                rotateImg.startAnimation(animation);
                getMoney();
                break;
            case R.id.show_classfy_linear://点击弹出彩票分类pop
                customPopupWindow.showClassfyPop(showClassfyLinear,this);
                break;
            case R.id.xingyong_play:
                showToast(getString(R.string.暂无该游戏官方玩法));
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
                loadAnimation.setFillAfter(true);//动画结束后,保持结束时的角度
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
                if(randomButton.getText().toString().equals(Utils.getString(R.string.机选))){
                    xingYongRandom();
                }else{
                    for (int i = 0; i < happy8ModelArrayList.size(); i++) {
                        happy8ModelArrayList.get(i).setStatus(0);
                    }
                    selecterList.clear();
                    int size = selecterList.size();
                    amountEdit.setText("");
                    betNum.setText(size+"");
                    randomButton.setText(Utils.getString(R.string.机选));
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
                    showToast(Utils.getString(R.string.当前彩种已封盘));
                }
                else  if(size==0){
                    showToast(Utils.getString(R.string.请选择玩法));
                } else if(StringMyUtil.isEmptyString(editText)){
                    showToast(Utils.getString(R.string.请输入下注金额));
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
    //对投注成功后进行数据重置 更新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReqTouzhu) {
            // 这里写信用投注成功后清除数据
            for (int i = 0; i < happy8ModelArrayList.size(); i++) {
                happy8ModelArrayList.get(i).setStatus(0);
            }
            selecterList.clear();
            int size = selecterList.size();
            amountEdit.setText("");
            betNum.setText(size+"");
            happy8Adapter.notifyDataSetChanged();
            sureBetPop.dismiss();
            randomButton.setText(Utils.getString(R.string.机选));
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
        randomButton.setText(Utils.getString(R.string.重置));
        for (int i = 0; i < selecterList.size(); i++) {
            String betType = selecterList.get(i).getBetType();
            if(betType.equals(Utils.getString(R.string.总和))||betType.equals(Utils.getString(R.string.前后和))||betType.equals(Utils.getString(R.string.单双和))||betType.equals(Utils.getString(R.string.五行))||betType.equals(Utils.getString(R.string.正码))){
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
        data.put("curtime",format);//当前时间
        data.put("token",token);//用户token
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
     倒计时处理相关
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
    开奖结果未更新前的动画
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
    是否中奖(中奖后要更新资金信息)
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
     * 彩票分类recycleView 点击事件回调
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
     * 右侧菜单pop的点击事件回调
     * @param view
     */
    @Override
    public void onMenuClicked(View view) {

    }


    /**
     * 投注规则recycleView点击事件回调
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        int size = selecterList.size();
        betNum.setText(size+"");
        if(size!=0){
            randomButton.setText(Utils.getString(R.string.重置));
        }else {
            randomButton.setText(Utils.getString(R.string.机选));
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
        /*1》快3，
        2》时时彩，
        3》赛车，
        4》六合彩，
        5》蛋蛋，
        6》快8，
        7》农场，
        8》快乐10分，
        9》11选5*/
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
