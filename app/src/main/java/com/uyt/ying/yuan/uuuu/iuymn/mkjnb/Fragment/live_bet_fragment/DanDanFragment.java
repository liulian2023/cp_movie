//package com.cambodia.zhanbang.xunbo.Fragment.live_bet_fragment;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
//import com.cambodia.zhanbang.xunbo.R;
//import com.cambodia.zhanbang.xunbo.activity.live.DialogTouZhuActivity;
//import com.cambodia.zhanbang.xunbo.activity.live.LiveRoomActivity;
//import com.cambodia.zhanbang.xunbo.activity.mine_top_activity.WantToInvestActivity;
//import com.cambodia.zhanbang.xunbo.adapter.live_bet_adapter.ChipRecycleAdapter;
//import com.cambodia.zhanbang.xunbo.adapter.live_bet_adapter.FramAdapter;
//import com.cambodia.zhanbang.xunbo.base.BaseFragment;
//import com.cambodia.zhanbang.xunbo.model.ChipModel;
//import com.cambodia.zhanbang.xunbo.model.DanDanEntity;
//import com.cambodia.zhanbang.xunbo.model.Happy10Model;
//import com.cambodia.zhanbang.xunbo.model.PlayLotteryModel;
//import com.cambodia.zhanbang.xunbo.model.SscEntity;
//import com.cambodia.zhanbang.xunbo.model.TouzhuModel;
//import com.cambodia.zhanbang.xunbo.myView.MyCornerTextView;
//import com.cambodia.zhanbang.xunbo.net.entity.LiveRoomEntity;
//import com.cambodia.zhanbang.xunbo.request.DockingUtil;
//import com.cambodia.zhanbang.xunbo.request.MessageHead;
//import com.cambodia.zhanbang.xunbo.request.RequestUtil;
//import com.cambodia.zhanbang.xunbo.request.StringMyUtil;
//import com.cambodia.zhanbang.xunbo.utils.GlideLoadViewUtil;
//import com.cambodia.zhanbang.xunbo.utils.SharePreferencesUtil;
//import com.cambodia.zhanbang.xunbo.utils.Utils;
//import com.cambodia.zhanbang.xunbo.widget.DirectionalViewPager;
//import com.cambodia.zhanbang.xunbo.widget.VerticalViewPager;
//import com.cambodia.zhanbang.xunbo.widget.VerticalXViewPager;
//
//import java.io.Serializable;
//import java.lang.ref.WeakReference;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import static com.cambodia.zhanbang.xunbo.activity.caizhong.happy8.Happy8Activity.ReqTouzhu;
//import static com.cambodia.zhanbang.xunbo.activity.live.LiveRoomActivity.MOVIEBEAN;
//
//public class DanDanFragment extends BaseFragment implements View.OnClickListener {
//    private RecyclerView betRecycle;
//    private FramAdapter framAdapter;
//    private ArrayList<Happy10Model> selectorList = new ArrayList<>();
//    private ArrayList<Happy10Model> framModelList = new ArrayList<>();
//    //点击切换下注列表的tab
//    private RadioButton longHuHeRbt;//大小单双
//    private RadioButton liangMianRbt;//混合
//    private RadioButton qianSanRbt;//波色
//    private TextView hourTv,minuteTv,secandTv,hourMaoHaoTv;   //倒计时
//    private TextView qishuTv;//期数
//    private MyCornerTextView oneTv,twoTv,threeTv,fourTv;//开奖结果
//    private ArrayList<MyCornerTextView> cornerTextViewArrayList = new ArrayList<>();//存放开奖结果tv的容器
//    private List<String> openResultList;//开奖结果num容器
//    private ImageView lottetyIv;//彩票图片
//    private TextView lotteryNameTv;
//
//    private ImageView chipIv;
//    private TextView chipTv;
//
//
//    private String nowQishu;
//    private String tqtime;
//    private long millionSeconds;
//    private Long shijiancha;
//    private long countTime;
//    private boolean isWaitOpen=true;
//    private int num=1;
//    private ArrayList<String>list =new ArrayList<>();
//    private Long user_id ;
//    private int type_id;
//    private String navigateList;
//    private  int cpId;
//    private LiveRoomEntity.DataBean.MoviesBean mLiveRoomData;
//
//    private TextView lastLotteryNumTv;//上期开奖
//
//    private String selectorChipNum;
//    String ruleContent;
//
//    private LinearLayout chooseAmountLinear;
//
//    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
//    /*
//    设置筹码pop
//     */
//    private PopupWindow chooseChipPop;
//    private RecyclerView chooseChipRecycle;
//    private ChipRecycleAdapter chipRecycleAdapter;
//    private ArrayList<ChipModel>chipModelArrayList = new ArrayList<>();
//    private ArrayList<ChipModel>chipModelArrayListCopy= new ArrayList<>();
//    private Button chooseChipSureBtn;
//    private ImageView disMissChooseChipPopIv;
//
//    private PopupWindow chooseChipEtvPop;
//    private EditText chooseChipEtv;
//    private TextView chooseCancelTv;
//    private TextView chooseSureTv;
//
//    private String chooseEtvStr;
//    private int count=0;
//    private String todayZJ;
//    private TextView userMoneyTv;
//
//    private TextView betTv;
//
//    private TextView toWithDrawTv;//充值按钮
//
//    private ImageView refreshIv;
//
//    private ImageView lotteryIv;
//
//    LiveRoomActivity liveRoomActivity;
//    VerticalViewPager vp;
//    com.cambodia.zhanbang.xunbo.activity.live.LiveRoomFragment LiveRoomFragment;
//    PlayLotteryModel playLotteryModel;
//    /*
//       投注清单页面pop
//     */
//    public MyHandler handler;
//
//    private ConstraintLayout loadingLinear;
//
//    private LinearLayout timeTvLinear;
//    private LinearLayout timeLoadingLinear;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_dandan, container, false);
//        handler= new MyHandler(new WeakReference<>(this));
//        initIntentShareData();
//        bindView(view);
//        initRecycle();
//        requestCurrentTime();
//        getMoney();
//        return view;
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(runnableTime!=null){
//            handler.removeCallbacks(runnableTime);
//        }
//        handler.postDelayed(runnableTime,300);
//
//        if (isWaitOpen){
//            if(runnableRequestOpen!=null){
//                handler.removeCallbacks(runnableRequestOpen);
//            }
//            handler.postDelayed(runnableRequestOpen,4000);
////            System.out.println(Utils.getString(R.string.请求接口10s  isWait));
//        }
//        if(runnableZj!=null){
//            handler.removeCallbacks(runnableZj);
//        }
//        handler.postDelayed(runnableZj, 3000);
////        handler.postDelayed(runnableBoolen,4000);
//    }
//
//    private void initIntentShareData() {
//        liveRoomActivity = (LiveRoomActivity) getActivity();
//        vp = liveRoomActivity.vp;
//        LiveRoomFragment = (com.cambodia.zhanbang.xunbo.activity.live.LiveRoomFragment) liveRoomActivity.fragments.get(/*vp.getCurrentItem()*/liveRoomActivity.fragmentPosition);
//        user_id= SharePreferencesUtil.getLong(getContext(),"user_id",0l);
//        navigateList= SharePreferencesUtil.getString(getContext(),"navigateList","");
//        Intent intent = getActivity().getIntent();
//        mLiveRoomData = (LiveRoomEntity.DataBean.MoviesBean) intent.getSerializableExtra(MOVIEBEAN);
//        cpId= mLiveRoomData.getCpId();
//        playLotteryModel=LiveRoomFragment.playLotteryModel;
//        type_id=playLotteryModel.getType_id();
///*        NavigateModel navigateModel = JSONObject.parseObject(navigateList, NavigateModel.class);
//        List<NavigateModel.GameClassListBean> gameClassList = navigateModel.getGameClassList();
//        for (int i = 0; i < gameClassList.size(); i++) {
//            NavigateModel.GameClassListBean gameClassListBean = gameClassList.get(i);
//            int id = gameClassListBean.getId();
//            if(cpId==id){
//                type_id=gameClassListBean.getType_id();
//                break;
//            }*/
///*        if(id==80){
//            lotteryNameTv.setText(gameClassListBean.getTypename());
//            type_id=gameClassListBean.getType_id();
//        }*/
////
////        }
//    }
//
//    /*    Handler handler =new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch (msg.what){
//                    case 1:
//                        initOpenResult();//开奖结果数据处理
//                        break;
//                    case 2:
//                        getMoney();
//                        break;
//                }
//            }
//        };*/
////handler 改为静态内部类， 并在构造方法中弱引用传入当前的引用（静态内部类不会持有外部类的引用，避免内存泄漏）
//    static class MyHandler extends Handler {
//        WeakReference<DanDanFragment> danDanFragmentWeakReference;
//        public MyHandler(WeakReference<DanDanFragment> danDanFragmentWeakReference) {
//            this.danDanFragmentWeakReference = danDanFragmentWeakReference;
//        }
//
//        public WeakReference<DanDanFragment> getDanDanFragmentWeakReference() {
//            return danDanFragmentWeakReference;
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            DanDanFragment danDanFragment= danDanFragmentWeakReference.get();
//            switch (msg.what){
//                case 1:
//                    if(null!=danDanFragment){
//                        danDanFragment.initOpenResult();//开奖结果数据处理
//                    }
//                    break;
//                case 2:
//                    if(null!=danDanFragment){
//                        danDanFragment.getMoney();
//                    }
//                    break;
//            }
//        }
//    }
//    /*
//请求玩家资金信息
// */
//    private void getMoney() {
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("user_id",user_id);
//        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
//            @Override
//            public void success(String content)  {
//                JSONObject jsonObject = JSONObject.parseObject(content);
//                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
//                BigDecimal amount = memberMoney.getBigDecimal("amount");
//                userMoneyTv.setText(amount+"");
//            }
//
//            @Override
//            public void failed(MessageHead messageHead) {
//
//            }
//        });
//    }
//
//    /*
//     请求开奖结果
//    */
//    private void initOpenResult() {
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("type_id",type_id);
//        data.put("pageNo",1);
//        data.put("pageSize",20);
//        data.put("flag",1);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(Utils.getString(R.string.请求开奖结果  >>>   )+simpleDateFormat.format(new Date()));
//        Utils.docking(data, RequestUtil.REQUEST_05dd, 0, new DockingUtil.ILoaderListener() {
//            @Override
//            public void success(String content)  {
//                JSONObject jsonObject1 = JSONObject.parseObject(content);
//                JSONArray farmLotterylist = jsonObject1.getJSONArray("danLotterylist");
//                if(farmLotterylist.size()==0){
//                    showtoast(Utils.getString(R.string.开奖结果请求失败,请退出界面重试));
//                    return;
//                }
//                JSONObject jsonObject = farmLotterylist.getJSONObject(0);
//                String lotteryqishu = jsonObject.getString("lotteryqishu");
//                String lotteryNo = jsonObject.getString("lotteryNo");
//                String sum = jsonObject.getString("sum");
//                String[] split = lotteryNo.split(",");
//                for (int i = 0; i < split.length; i++) {
//                    String s = split[i];
//                    if(list.contains(s)){
//                        split[i]="0"+s;
//                    }
//                }
//                openResultList = Arrays.asList(split);
//                for (int i = 0; i < cornerTextViewArrayList.size(); i++) {
//                    cornerTextViewArrayList.get(i).setText(openResultList.get(i));
//                }
//                fourTv.setText(sum);
//                if(!StringMyUtil.isEmptyString(nowQishu)){
//                    if(Long.parseLong(lotteryqishu)==(Long.parseLong(nowQishu)-1)){
//                        isWaitOpen=false;
//
//                    }else {
//                        isWaitOpen=true;
//                    }
//                }else {
//                    showtoast(Utils.getString(R.string.开奖结果请求失败,正在重新请求));
//                }
//            }
//
//            @Override
//            public void failed(MessageHead messageHead) {
//                System.out.println(messageHead.getInfo());
//            }
//        });
//    }
//    /*
// 倒计时处理相关
//  */
//    Runnable runnableTime =new Runnable() {
//        @Override
//        public void run() {
//            if(countTime<=0){
//                requestCurrentTime();
//            }else {
//                countTime = millionSeconds + shijiancha - System.currentTimeMillis() -(Long.parseLong(tqtime)*1000);
////                countTime = countTime - 1000;
//                long hours = (countTime/* - days * (1000 * 60 * 60 * 24)*/) / (1000 * 60 * 60);
//                long minutes = (countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60)) / (1000 * 60);
//                long seconds = (countTime /*- days * (1000 * 60 * 60 * 24)*/ - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
//                hourTv.setText(hours + "");
//                minuteTv.setText(minutes + "");
//                secandTv.setText(seconds + "");
//                if (hours == 0) {
//                    hourTv.setVisibility(View.INVISIBLE);
//                    hourMaoHaoTv.setVisibility(View.INVISIBLE);
//                } else if(hours<10){
//                    hourTv.setVisibility(View.VISIBLE);
//                    hourMaoHaoTv.setVisibility(View.VISIBLE);
//                    hourTv.setText("0" + hours);
//                }else {
//                    hourTv.setVisibility(View.VISIBLE);
//                    hourMaoHaoTv.setVisibility(View.VISIBLE);
//                    hourTv.setText("" + hours);
//                }
//                if (minutes == 0 || minutes < 10) {
//                    minuteTv.setText("0" + minutes);
//                } else {
//
//                    minuteTv.setText("" + minutes);
//
//                }
//                if (seconds == 0 || seconds < 10) {
//
//                    secandTv.setText("0" + seconds);
//                } else {
//
//                    secandTv.setText("" + seconds);
//                }
//                if(countTime<=1){
//                    countTime=0;
//                    isWaitOpen=true;
//                }
//            }
//            handler.postDelayed(runnableTime,300);
//        }
//    };
//
//    /*
//未开奖时,循环请求开奖结果
// */
//    Runnable runnableRequestOpen =new Runnable() {
//        @Override
//        public void run() {
//            if(isWaitOpen){
//                initOpenResult();
//            }else{
//                for (int i = 0; i < cornerTextViewArrayList.size(); i++) {
//                    cornerTextViewArrayList.get(i).setText(openResultList.get(i));
//                }
//
//            }
//            handler.postDelayed(runnableRequestOpen,4000);
//        }
//    };
//
//    /*
//     是否中奖(中奖后要更新资金信息)
//      */
//    Runnable runnableZj = new Runnable() {
//        @Override
//        public void run() {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("user_name", SharePreferencesUtil.getString(getContext(), "nickname", ""));
//            Utils.docking(data, RequestUtil.REQUEST_zj_tb, 0, new DockingUtil.ILoaderListener() {
//                @Override
//                public void success(String content)  {
//                    JSONObject jsonObject = JSONObject.parseObject(content);
//                    todayZJ = jsonObject.getString("todayZJ");
//                    if (todayZJ.equals("1")) {
//                        Message message = Message.obtain();
//                        message.what = 2;
//                        handler.sendMessage(message);
//                    }
//
//                }
//
//                @Override
//                public void failed(MessageHead messageHead) {
//
//                }
//            });
//            handler.postDelayed(runnableZj, 2000);
//        }
//    };
//
//    private void requestCurrentTime() {
//        timeLoadingLinear.setVisibility(View.VISIBLE);
//        timeTvLinear.setVisibility(View.GONE);
//        HashMap<String, Object> data = new HashMap<>();
//        String domain = SharePreferencesUtil.getString(getContext(), "domain", "");
//        data.put("type_id",type_id);
//        data.put("source",2);
//        Utils.docking(data, RequestUtil.REQUEST_04dd, 0, new DockingUtil.ILoaderListener() {
//            @Override
//            public void success(String content)  {
//
//                JSONObject jsonObject = JSONObject.parseObject(content);
//                String stoptimestr = jsonObject.getString("stoptimestr");//倒计时结束时间
//                nowQishu = jsonObject.getString("qishu");//当前期数
//                if(StringMyUtil.isEmptyString(nowQishu)) {//期数为空,表示封盘
//
//                }
//                else{
//                    if(runnableTime==null){
//                        handler.postDelayed(runnableTime,300);
//                    }
//                    if(runnableRequestOpen==null){
//                        handler.postDelayed(runnableRequestOpen,4000);
//                    }
//                    tqtime = jsonObject.getString("tqtime");//封盘时间(倒计时需要减去封盘时间)
//                    qishuTv.setText(nowQishu+Utils.getString(R.string. 期));
//                    lastLotteryNumTv.setText(Long.parseLong(nowQishu)-1+Utils.getString(R.string.期开奖:));
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    try {
//                        millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//倒计时结束时间
////                        time = Long.parseLong(Utils.getFileData("time"));//服务器时间
//                        long nowTime = System.currentTimeMillis();//当前时间
////                        shijiancha = time- nowTime;//服务器时间和当地时间差
//                        shijiancha = SharePreferencesUtil.getLong(getContext(),"shijiancha",0l);//服务器时间和当地时间差
//                        countTime = millionSeconds + shijiancha - nowTime -(Long.parseLong(tqtime)*1000);
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                WeakReference<DanDanFragment> danDanFragmentWeakReference = handler.getDanDanFragmentWeakReference();
//                                DanDanFragment danDanFragment = danDanFragmentWeakReference.get();
//                                if(null!=danDanFragment){
//                                    danDanFragment.timeTvLinear.setVisibility(View.VISIBLE);
//                                    timeLoadingLinear.setVisibility(View.GONE);
//                                }
//
//                            }
//                        },1000);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    if(num==1){//第一次请求倒计时数据时,才发送消息通知请求开奖结果(因为请求一次后,期数不会为空,可以直接请求开奖结果)
//                        Message message = Message.obtain();
//                        message.what=1;
//                        handler.sendMessage(message);
//                    }
//                    num++;
//                }
//
//            }
//            @Override
//            public void failed(MessageHead messageHead) {
//                timeLoadingLinear.setVisibility(View.VISIBLE);
//                timeTvLinear.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    private void initRecycle() {
//        framAdapter=new FramAdapter(framModelList,selectorList);
//        betRecycle.setAdapter(framAdapter);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),12);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if(qianSanRbt.isChecked()){
//                    return 4;
//                }else{
//                    return 3;
//                }
//            }
//        });
//
//        betRecycle.setLayoutManager(gridLayoutManager);
//        requestRuleList();
///*        framModelList.add(new Happy10Model(Utils.getString(R.string.大),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.小),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.单),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   ;
//        framModelList.add(new Happy10Model(Utils.getString(R.string.双),"1.98","1","1"))   */;
//
//    }
//
//    private void requestRuleList() {
//        if(StringMyUtil.isEmptyString(ruleContent)){
//            if(loadingLinear.getVisibility()!=View.VISIBLE){
//                loadingLinear.setVisibility(View.VISIBLE);
//            }
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("user_id",user_id);
//            data.put("type_id",type_id);
//            data.put("game",getGame());
//            Utils.docking(data, RequestUtil.REQUEST_01dd, -1, new DockingUtil.ILoaderListener() {
//                @Override
//                public void success(String content) {
//                    if(loadingLinear.getVisibility()!=View.GONE){
//                        loadingLinear.setVisibility(View.GONE);
//                    }
//                    ruleContent=content;
//                    handlerJson(content);
//                }
//
//                @Override
//                public void failed(MessageHead messageHead) {
//                    if(loadingLinear.getVisibility()!=View.GONE){
//                        loadingLinear.setVisibility(View.GONE);
//                    }
//                }
//            });
//        }else {
//            handlerJson(ruleContent);
//        }
//
//    }
//
//    private void handlerJson(String content) {
//        framModelList.clear();
//        //清空选中model list
//        clearSelectorList();
//        DanDanEntity danDanEntity = JSONObject.parseObject(content, DanDanEntity.class);
//        List<DanDanEntity.DanRulelistBean> danRulelist = danDanEntity.getDanRulelist();
//
//        if(longHuHeRbt.isChecked()){
//            //大小单双选中时  取混合中的大小单双
//            for (int i = 0; i < 4; i++) {
//                DanDanEntity.DanRulelistBean danRulelistBean = danRulelist.get(i);
//                String groupname = danRulelistBean.getGroupname();
//                String name = danRulelistBean.getName();
//                String odds = danRulelistBean.getOdds();
//                int id = danRulelistBean.getId();
//                Happy10Model happy10Model = new Happy10Model();
//                happy10Model.setBetType(name);
//                happy10Model.setRebate(odds+"");
//                happy10Model.setRule_id(id+"");
//                happy10Model.setGroupname(groupname);
//                framModelList.add(happy10Model);
//            }
//        }else if(liangMianRbt.isChecked()){
//            //混合选中时， 取混合中 5-8的数据
//            for (int i = 4; i < 8; i++) {
//                DanDanEntity.DanRulelistBean danRulelistBean = danRulelist.get(i);
//                String groupname = danRulelistBean.getGroupname();
//                String name = danRulelistBean.getName();
//                String odds = danRulelistBean.getOdds();
//                int id = danRulelistBean.getId();
//                Happy10Model happy10Model = new Happy10Model();
//                happy10Model.setBetType(name);
//                happy10Model.setRebate(odds+"");
//                happy10Model.setRule_id(id+"");
//                happy10Model.setGroupname(groupname);
//                framModelList.add(happy10Model);
//            }
//        }else {
//            for (int i = 0; i < danRulelist.size(); i++) {
//                DanDanEntity.DanRulelistBean danRulelistBean = danRulelist.get(i);
//                String groupname = danRulelistBean.getGroupname();
//                if(groupname.endsWith(Utils.getString(R.string.波色))){
//                    String name = danRulelistBean.getName();
//                    String odds = danRulelistBean.getOdds();
//                    int id = danRulelistBean.getId();
//                    Happy10Model happy10Model = new Happy10Model();
//                    happy10Model.setBetType(name);
//                    happy10Model.setRebate(odds+"");
//                    happy10Model.setRule_id(id+"");
//                    happy10Model.setGroupname(groupname);
//                    framModelList.add(happy10Model);
//                }
//            }
//        }
//
//        framAdapter.notifyDataSetChanged();
//    }
//
//    private void clearSelectorList() {
//
//        selectorList.clear();
//    }
//
//    private int getGame() {
//        /*1》快3，
//        2》时时彩，
//        3》赛车，
//        4》六合彩，
//        5》蛋蛋，
//        6》快8，
//        7》农场，
//        8》快乐10分，
//        9》11选5*/
//        return 5;
//    }
//    private void bindView(View view) {
//
//        timeTvLinear  =view.findViewById(R.id.time_tv_linear);
//        timeLoadingLinear=view.findViewById(R.id.time_load_linear);
//
//        chipModelArrayListCopy.add(new ChipModel(2+"",R.drawable.chouma1));
//        chipModelArrayListCopy.add(new ChipModel(5+"",R.drawable.chouma2));
//        chipModelArrayListCopy.add(new ChipModel(10+"",R.drawable.chouma3));
//        chipModelArrayListCopy.add(new ChipModel(20+"",R.drawable.chouma4));
//        chipModelArrayListCopy.add(new ChipModel(50+"",R.drawable.chouma5));
//        chipModelArrayListCopy.add(new ChipModel(100+"",R.drawable.chouma6));
//        chipModelArrayListCopy.add(new ChipModel(200+"",R.drawable.chouma7));
//        chipModelArrayListCopy.add(new ChipModel(500+"",R.drawable.chouma8));
//        chipModelArrayListCopy.add(new ChipModel(1000+"",R.drawable.chouma9));
//
//
//        loadingLinear=view.findViewById(R.id.loading_linear_dandan);
//        lotteryIv=view.findViewById(R.id.lottery_imageview);
//        GlideLoadViewUtil.LoadCircleView(getContext(),playLotteryModel.getImageUrl(),lotteryIv);
//        refreshIv=view.findViewById(R.id.play_refresh_iv);
//        toWithDrawTv=view.findViewById(R.id.with_draw_tv);
//        betTv=view.findViewById(R.id.bet_tv);
//        userMoneyTv=view.findViewById(R.id.user_money_amount);
//        String currentAmout = sp.getCurrentAmout();
//        chipIv=view.findViewById(R.id.fram_bet_amount_iv);
//        chipTv=view.findViewById(R.id.fram_bet_amount_tv);
//        initChipIvBackGroud(currentAmout);
//        chipTv.setText(currentAmout);
//        chipTv.setTextSize(currentAmout.length()>=4?10:12);
//        betRecycle=view.findViewById(R.id.fram_bet_recycle);
//        longHuHeRbt =view.findViewById(R.id.one_rbt);
//        longHuHeRbt.performClick();
//        liangMianRbt =view.findViewById(R.id.two_rbt);
//        qianSanRbt =view.findViewById(R.id.three_rbt);
//        hourTv=view.findViewById(R.id.play_hour_tv);
//        hourMaoHaoTv=view.findViewById(R.id.play_hour_maohao_tv);
//        minuteTv=view.findViewById(R.id.play_minute_tv);
//        secandTv=view.findViewById(R.id.play_secand_tv);
//        qishuTv=view.findViewById(R.id.lottery_qishu_tv);
//        lotteryNameTv=view.findViewById(R.id.lottery_name_tv);
//        lotteryNameTv.setText(playLotteryModel.getName());
//        lastLotteryNumTv=view.findViewById(R.id.last_lottery_num_tv);
//        chooseAmountLinear=view.findViewById(R.id.choose_amount_linear);
//        oneTv=view.findViewById(R.id.fram_one_num);
//        twoTv=view.findViewById(R.id.fram_two_num);
//        threeTv=view.findViewById(R.id.fram_three_num);
//        fourTv=view.findViewById(R.id.fram_four_num);
//
//        cornerTextViewArrayList.add(oneTv);
//        cornerTextViewArrayList.add(twoTv);
//        cornerTextViewArrayList.add(threeTv);
////        cornerTextViewArrayList.add(fourTv);
//
//        longHuHeRbt.setOnClickListener(this);
//        liangMianRbt.setOnClickListener(this);
//        qianSanRbt.setOnClickListener(this);
//        chooseAmountLinear.setOnClickListener(this);
//        betTv.setOnClickListener(this);
//        toWithDrawTv.setOnClickListener(this);
//        refreshIv.setOnClickListener(this);
//        //开奖结果样式设置
//        for (int i = 0; i < cornerTextViewArrayList.size(); i++) {
//            cornerTextViewArrayList.get(i).setfilColor(Color.parseColor("#bf1f24"));
//            cornerTextViewArrayList.get(i).setCornerSize(50);
//        }
//        fourTv.setfilColor(Color.parseColor("#bf1f24"));
//        fourTv.setCornerSize(50);
///*        list.add("1");list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        list.add("8");
//        list.add("9");*/
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()){
//            case R.id.one_rbt:
//            case R.id.two_rbt:
//            case R.id.three_rbt:
//                requestRuleList();
//                break;
//            case R.id.choose_amount_linear:
////                if(null==chooseChipPop){
//                initChooseChipPop();
////                }
//                chooseChipPop.showAtLocation(vp, Gravity.BOTTOM,0,0);
//                break;
//            case R.id.choose_chip_dismiss_iv:
//                if(null!=chooseChipPop&&chooseChipPop.isShowing()){
//                    chooseChipPop.dismiss();
//                }
//                break;
//            case R.id.choose_chip_sure_btn:
//                ChipModel chipModel = null;
//                for (int i = 0; i < chipModelArrayList.size(); i++) {
//                    chipModel = chipModelArrayList.get(i);
//                    boolean check = chipModel.isCheck();
//                    if(check){
//                        String amount = chipModel.getAmount();
//                        selectorChipNum= amount;
//                        break;
//                    }
//
//                }
//                for (int i = 0; i < chipModelArrayList.size(); i++) {
//                    chipModelArrayList.get(i).setCurrent(false);
//                }
//                chipModel.setCurrent(true);
//                sp.setCurrentAmout(chipModel.getAmount());
//                sp.setIsCustomChip(chipModel.isCustom());
//                chipRecycleAdapter.notifyDataSetChanged();
//                if(null!=chipTv&&null!=chipIv){
//                    chipTv.setText(selectorChipNum);
//                    initChipIvBackGroud(selectorChipNum);
//                }
//                chooseChipPop.dismiss();
//                break;
//            case R.id.choose_sure:
//                String toString = chooseChipEtv.getText().toString();
//                if(StringMyUtil.isEmptyString(toString)){
//                    showtoast(Utils.getString(R.string.请输入筹码金额));
//                }else if(Integer.parseInt(toString)<1||Integer.parseInt(toString)>10000){
//                    showtoast(Utils.getString(R.string.输入筹码金额超出范围));
//                }else {
//                    for (int i = 0; i < chipModelArrayList.size(); i++) {
//                        chipModelArrayList.get(i).setCheck(false);
//                    }
//                    ChipModel model = chipModelArrayList.get(chipModelArrayList.size() - 1);
//                    model.setAmount(toString);//设置自定义金额
//                    model.setCheck(true);
//                    sp.setCustomAmout(toString);
//                    chipRecycleAdapter.notifyDataSetChanged();
//                    chooseChipEtvPop.dismiss();
//                }
//
//                break;
//            case R.id.choose_cancel:
//                chooseChipEtvPop.dismiss();
//                break;
//            case R.id.bet_tv:
//                if(selectorList.size()==0){
//                    showtoast(Utils.getString(R.string.请至少选择一项));
//                }else {
//                    LiveRoomFragment.isSkip=true;
//                    Collections.sort(selectorList);
//                    List<TouzhuModel> touzhuList = new ArrayList<>();
//                    for (int i = 0; i < selectorList.size(); i++) {
//                        TouzhuModel touzhuModel = new TouzhuModel();
//                        List<String> groupname = Arrays.asList(selectorList.get(i).getGroupname().split("-"));
//                        if (groupname.size()==2){
//                            touzhuModel.setGroupname(groupname.get(1));
//                        } else if (groupname.size()==3){
//                            touzhuModel.setGroupname(groupname.get(1)+"-"+groupname.get(2));
//                        }else {
//                            touzhuModel.setGroupname(groupname.get(0));
//                        }
//                        touzhuModel.setName(selectorList.get(i).getBetType());
//                        touzhuModel.setId(String.valueOf(selectorList.get(i).getRule_id()));
//                        touzhuModel.setMoney(chipTv.getText().toString());
//                        touzhuList.add(touzhuModel);
//                    }
//
//                    Intent intent = new Intent();
//                    intent.setClass(getActivity(), DialogTouZhuActivity.class);
////                intent.setClass(getActivity(), TouzhuActivity.class);
//                    intent.putExtra("touzhuList", (Serializable) touzhuList);
//                    intent.putExtra("game", String.valueOf(getGame()));
//                    intent.putExtra("type_id", String.valueOf(type_id));
//                    intent.putExtra("money",chipTv.getText().toString());
//                    intent.putExtra("qishu", nowQishu);
//                    intent.putExtra("index", "");
//                    intent.putExtra("ma", "");
//                    startActivityForResult(intent, ReqTouzhu);
//                }
//
//                break;
//            case R.id.with_draw_tv:
//                LiveRoomFragment.isSkip=true;
//                startActivity(new Intent(getContext(), WantToInvestActivity.class));
//                break;
//            case R.id.play_refresh_iv:
////             LiveRoomFragment LiveRoomFragment = (LiveRoomFragment) getParentFragment();
//
//                if(null != LiveRoomFragment) {
//                    PopupWindow betRulePop = LiveRoomFragment.betRulePop;
//                    PopupWindow lotteryPop = LiveRoomFragment.lotteryPop;
//                    if (null!= betRulePop && null != lotteryPop&&null!=liveRoomActivity) {
//                        betRulePop.dismiss();
//                        navigateList = SharePreferencesUtil.getString(getContext(), "navigateList", "");
//                        LiveRoomFragment.handlerJson(navigateList);
//                        lotteryPop.showAtLocation(vp,Gravity.BOTTOM,0,0);
//                        betRulePop.dismiss();
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void initChipIvBackGroud(String chipNum) {
//        if(sp.getIsCustomChip()){
//            chipIv.setImageResource(R.drawable.chouma10);
//        }else {
//            for (int i = 0; i < chipModelArrayListCopy.size(); i++) {
//                if(chipNum.equals(chipModelArrayListCopy.get(i).getAmount())){
//                    if(i==0){
//                        chipIv.setImageResource(R.drawable.chouma1);
//                    }else if(i==1){
//                        chipIv.setImageResource(R.drawable.chouma2);
//                    }else if(i==2){
//                        chipIv.setImageResource(R.drawable.chouma3);
//                    }else if(i==3){
//                        chipIv.setImageResource(R.drawable.chouma4);
//                    }else if(i==4){
//                        chipIv.setImageResource(R.drawable.chouma5);
//                    }else if(i==5){
//                        chipIv.setImageResource(R.drawable.chouma6);
//                    }else if(i==6){
//                        chipIv.setImageResource(R.drawable.chouma7);
//                    }else if(i==7){
//                        chipIv.setImageResource(R.drawable.chouma8);
//                    }else if(i==8){
//                        chipIv.setImageResource(R.drawable.chouma9);
//                    }else {
//                        chipIv.setImageResource(R.drawable.chouma10);
//                    }
//                }
//            }
//        }
//
//    }
//    private void initChooseChipPop() {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.choose_chip_pop,null);
//        chooseChipSureBtn=view.findViewById(R.id.choose_chip_sure_btn);
//        chooseChipSureBtn.setOnClickListener(this);
//        disMissChooseChipPopIv=view.findViewById(R.id.choose_chip_dismiss_iv);
//        disMissChooseChipPopIv.setOnClickListener(this);
//        chooseChipRecycle=view.findViewById(R.id.choose_chip_recycle);
//        chipRecycleAdapter=new ChipRecycleAdapter(chipModelArrayList);
//        chooseChipRecycle.setLayoutManager(new GridLayoutManager(getContext(),5));
//        chooseChipRecycle.setAdapter(chipRecycleAdapter);
//        chooseChipPop=new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
//        chooseChipPop.setAnimationStyle(R.style.popupAnimationNormol150);
//        //2，5，10，20，50，100，200，500，1000
//        chipModelArrayList.clear();
//        chipModelArrayList.add(new ChipModel(2+"",R.drawable.chouma1));
//        chipModelArrayList.add(new ChipModel(5+"",R.drawable.chouma2));
//        chipModelArrayList.add(new ChipModel(10+"",R.drawable.chouma3));
//        chipModelArrayList.add(new ChipModel(20+"",R.drawable.chouma4));
//        chipModelArrayList.add(new ChipModel(50+"",R.drawable.chouma5));
//        chipModelArrayList.add(new ChipModel(100+"",R.drawable.chouma6));
//        chipModelArrayList.add(new ChipModel(200+"",R.drawable.chouma7));
//        chipModelArrayList.add(new ChipModel(500+"",R.drawable.chouma8));
//        chipModelArrayList.add(new ChipModel(1000+"",R.drawable.chouma9));
//        ChipModel lastChipModel = new ChipModel(sp.getCustomAmout(), R.drawable.chouma10);
//        chipModelArrayList.add(lastChipModel);
//        lastChipModel.setCustom(true);
//        if(sp.getIsCustomChip()){
//            lastChipModel.setCurrent(true);
//            lastChipModel.setCheck(true);
//        }else {
//            for (int i = 0; i < chipModelArrayList.size()-1; i++) {
//                ChipModel chipModel = chipModelArrayList.get(i);
//                if(chipModel.getAmount().equals(sp.getCurrentAmout())){
//                    chipModel.setCurrent(true);
//                    chipModel.setCheck(true);
//                    break;
//                }
//            }
//        }
//
//        chipRecycleAdapter.notifyDataSetChanged();
//        chipRecycleAdapter.setOnShowPopLintener(new ChipRecycleAdapter.OnShowPopLintener() {
//            @Override
//            public void onShowPop(ChipModel chipModel) {
//                if(null==chooseChipEtvPop){
//                    initChooseChipEtvPop();
//                }
//                LiveRoomActivity liveRoomActivity = (LiveRoomActivity) getActivity();
//                chooseChipEtvPop.showAtLocation(liveRoomActivity.vp,Gravity.CENTER,0,0);
//            }
//        });
//    }
//    /*
//   跟投pop初始化
//    */
//    private void initChooseChipEtvPop() {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.choose_chip_amount_pop, null);
//        chooseChipEtvPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//        chooseChipEtvPop.setAnimationStyle(R.style.pop_scale_animation);
//        chooseChipEtv = view.findViewById(R.id.chip_amount_edit);
//        chooseCancelTv =view.findViewById(R.id.choose_cancel);
//        chooseSureTv=view.findViewById(R.id.choose_sure);
//        chooseSureTv.setOnClickListener(this);
//        chooseCancelTv.setOnClickListener(this);
//        chooseChipEtvPop.setAnimationStyle(R.style.pop_scale_animation);
//        chooseChipEtvPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                chooseEtvStr=chooseChipEtv.getText().toString();
//                if(chooseEtvStr.length()>=4){
//                    chipTv.setTextSize(10);
//                }else {
//                    chipTv.setTextSize(12);
//                }
//                chipTv.setText(chooseEtvStr);
//            }
//        });
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        handler.removeCallbacksAndMessages(null);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==ReqTouzhu&&resultCode==RESULT_OK){
//            getMoney();
//            for (int i = 0; i < framModelList.size(); i++) {
//                framModelList.get(i).setStatus(0);
//                selectorList.clear();
//            }
//            framAdapter.notifyDataSetChanged();
//        }
//    }
//
//}
