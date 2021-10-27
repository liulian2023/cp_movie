
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.Dragon_fragmemt;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment.LongDragonFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.HomeTwoFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter.ChangLongAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChangLongBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TouXiangUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

/*
长龙助手下注fragment
 */
public class DragonBetFragment extends BaseFragment implements ChangLongAdapter.EndListener, CommonAdapter.OnRecyclerViewItemClickListener, TextWatcher, View.OnClickListener {
    private RecyclerView mRecy;
    private ChangLongAdapter changLongAdapter;
    private ArrayList<ChangLongBetModel>changLongBetModelArrayList=new ArrayList<>();
    private ArrayList<ChangLongBetModel>selectorList=new ArrayList<>();
    private ArrayList<String> gameClassIdList=new ArrayList<>();//倒计时结束,正在开奖中的彩票id 集合
    private long currentLotteryTime;//倒计时结束时间
    private boolean isPause=false;
    private RefreshLayout refreshLayout;
    private String TAG="DragonBetFragment";
    private String chatRoomId/*="7b99f4f9a89742d29b81183a4790f853"*/;
    String tokenChatroom/*="BGe/L2YYyE1iw+yLI/D/bKrF+sb+AdE5DZUHqe01o1nfvHcBDpmL/iZyZ1OP13ZNsJs8d4cvujAKO+oH3EdNug=="*/;
    private EditText amountEdit;
    private TextView canWinTv;
    private TextView clearTv;
    private TextView betNumTv;
    private TextView allAmountTv;
    private TextView sureBetTv;
    private int recyPosition;

    private long user_id;
    private String userNickName;
    private int pointGrade;
    private String rebateLeft;

    /*(
    下注清单pop
     */
    private PopupWindow sureBetPop;
    private TextView typenameTv;
    private TextView lotteryQishuTv;
    private TextView betAmountTv;
    private TextView betContentTv;
    private TextView cancelTv;
    private TextView sureTv;

    /*
    分享投注pop
     */
    private PopupWindow sharebetPop;
    private TextView shareBetCancelTv;
    private TextView shareBerSureTv;

    private String typename;
    private String betRule;
 //   private String unBetRule;
    private String betGroupName;

    private int firstItemPosition;
    private int lastItemPosition;

    //    private ArrayList<Integer>indexList=new ArrayList<>();
    private PopupWindow goChatroomPop;
    private TextView cancelGoChatroom;
    private TextView sureGoChatroom;
    private ConstraintLayout loadingLinear;
    private LinearLayout nodataLinear;
    private LinearLayout errorLinear;
    private TextView refreshTv;
    LongDragonFragment longDragonFragment;
    private ViewPager parentViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chang_long_bet, container, false);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        user_id=SharePreferencesUtil.getLong(getActivity(),"user_id",0l);
        userNickName=SharePreferencesUtil.getString(getActivity(),"userNickName","");
        pointGrade=SharePreferencesUtil.getInt(getActivity(), CommonStr.GRADE,0);
        chatRoomId = SharePreferencesUtil.getString(getActivity(), "chatroomId", "");
        tokenChatroom = SharePreferencesUtil.getString(getActivity(), "chatroomToken", "");
        bindView(view);
        initRecycle(view);
        initReFresh(view);
        handler.postDelayed(runnableCurrent,1000);//倒计时
        handler.postDelayed(runnableKaiJiang,3000);//每8秒请求一次倒计时结束的长龙
        handler.postDelayed(runnableAll,60000);//每分钟请求一次全部长龙()
        initSureBetPop();
        initShareBetPop();
        Fragment parentFragment = getParentFragment();
        if(parentFragment instanceof LongDragonFragment){
            longDragonFragment = (LongDragonFragment) parentFragment;
        }
        if(longDragonFragment!=null){
            HomeTwoFragment homeTwoFragment = longDragonFragment.getHomeTwoFragment();
            if(homeTwoFragment!=null){
                parentViewPager = homeTwoFragment.getmViewPager();
            }
        }


        return view;
    }

    private void initReFresh(View view) {
        refreshLayout=view.findViewById(R.id.refresh);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getDragonList(false);
            }
        });
    }
    /**
     * 更新是否是限定彩票
     * @param hbGameClassModel  包含限定彩种的idList的model
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        selectorId(hbGameClassModel);
    }
    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        String gameStr = hbGameClassModel.getGamelist();
        //如果在首页没有筛选出game和type_id,在此处重新筛选一次并处理数据
        if(StringMyUtil.isEmptyString(gameStr)){
            String navigateList = SharePreferencesUtil.getString(getContext(), "navigateList", "");
            if(StringMyUtil.isNotEmpty(navigateList)){
                JSONArray gameClassList = JSONObject.parseObject(navigateList).getJSONArray("gameClassList");
                String type_id_list="";
                String game_list="";
                for (int i = 0; i < gameClassList.size(); i++) {
                    JSONObject jsonObject = gameClassList.getJSONObject(i);
                    String lottery_id = jsonObject.getString("id");
                    if(idList.contains(lottery_id)){
                        String type_id = jsonObject.getString("type_id");
                        String game = jsonObject.getString("game");
                        type_id_list=type_id_list+type_id+",";
                        game_list=game_list+game+",";
                    }
                }
                List<String> gameList = Arrays.asList(game_list.split(","));
                List<String> typeIdList = Arrays.asList(type_id_list.split(","));
                for (int i = 0; i < changLongBetModelArrayList.size(); i++) {
                    ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(i);
                    for (int j = 0; j < gameList.size(); j++) {
                        if((changLongBetModel.getGame()+"").equals(gameList.get(j))&&(changLongBetModel.getType_id()+"").equals(typeIdList.get(j))){
                            changLongBetModel.setXian(true);
                        }
                    }
                }

            }

        }else {//首页有筛选出game和type_id,直接处理数据
            String[] gameSplit = gameStr.split(",");
            List<String> gameList = Arrays.asList(gameSplit);
            List<String> typeIdList = Arrays.asList(hbGameClassModel.getTyptIdList().split(","));
            for (int i = 0; i < changLongBetModelArrayList.size(); i++) {
                ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(i);
                for (int j = 0; j < gameList.size(); j++) {
                    if((changLongBetModel.getGame()+"").equals(gameList.get(j))&&(changLongBetModel.getType_id()+"").equals(typeIdList.get(j))){
                        changLongBetModel.setXian(true);
                        break;
                    }
                }
            }

        }


        if(null!=changLongAdapter){
            changLongAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getDragonList(true);
    }
    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        refreshTv=view.findViewById(R.id.reload_tv);
        nodataLinear=view.findViewById(R.id.nodata_linear);
        loadingLinear=view.findViewById(R.id.loading_linear);
        amountEdit=view.findViewById(R.id.amount_edit);
        amountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)}); //限制输入位数
        canWinTv=view.findViewById(R.id.can_win_amount);
        clearTv=view.findViewById(R.id.clear_textview);
        betNumTv=view.findViewById(R.id.bet_num);
        allAmountTv=view.findViewById(R.id.amount_num);
        sureBetTv=view.findViewById(R.id.sure_bet);
        amountEdit.addTextChangedListener(this);
        clearTv.setOnClickListener(this);
        sureBetTv.setOnClickListener(this);
    }

    private void initRecycle(View view) {
        mRecy=view.findViewById(R.id.changlong_recycle);
        changLongAdapter=new ChangLongAdapter(changLongBetModelArrayList,getActivity(),selectorList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecy.setLayoutManager(linearLayoutManager);
        mRecy.setAdapter(changLongAdapter);
        changLongAdapter.setOnEndListener(this);
        changLongAdapter.setOnItemClickListener(this);
        /*
        recycleView 滚动监听
         */
        mRecy.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    firstItemPosition = linearManager.findFirstVisibleItemPosition();

                }
            }
        });

        getDragonList(true);//请求列表数据
    }
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    getMoney();
                    break;
            }
        }
    };
    Runnable runnableCurrent =new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnableCurrent,1000);
            if(currentLotteryTime!=0){
                if(!isPause){
                    changLongAdapter.notifyDataSetChanged();
                }

            }
        }
    };
    Runnable runnableAll =new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnableAll,60000);
            if(isPause){
                return;
            }
            if(parentViewPager!=null){
                int currentItem = parentViewPager.getCurrentItem();
                if(longDragonFragment!=null){
                    if(currentItem!=longDragonFragment.getPosition()){
                        return;
                    }
                }
            }
            getDragonList(false);
        }
    };
    /*
  每8秒请求一次倒计时结束,开奖号码尚未更新的彩票
   */
    Runnable runnableKaiJiang =new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnableKaiJiang,3000);
            if(isPause){
                return;
            }
            if(parentViewPager!=null){
                int currentItem = parentViewPager.getCurrentItem();
                if(longDragonFragment!=null){
                    if(currentItem!=longDragonFragment.getPosition()){
                        return;
                    }
                }
            }
            //id容器不为空, 则请求接口(为空时请求,解析json时会报空指针异常)
            if(gameClassIdList.size()!=0){
                HashMap<String, Object> dataId = new HashMap<>();
                String needId = "";
                //将id取出,并用逗号分割,作为请求接口的参数
                for(String id : gameClassIdList){
////                    if(lastItemPosition!=0){
                    needId +=id + ",";
//                    }
                }
                if(needId.length()>1){
                    needId = needId.substring(0, needId.length()-1);
                    dataId.put("dragonId",needId );
                    HttpApiUtils.CPnormalRequest(getActivity(), DragonBetFragment.this, RequestUtil.REQUEST_asscl, dataId, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            JSONObject jsonObject1 = JSONObject.parseObject(result);
                            JSONArray dragonList = jsonObject1.getJSONArray("dragonList");
                            for (int i = 0; i < dragonList.size(); i++) {
                                JSONObject jsonObject = dragonList.getJSONObject(i);
                                String image = jsonObject.getString("image");
                                String imageUrl=Utils.getFirstImgurl(getActivity())+image;
                                Integer game = jsonObject.getInteger("game");
                                String currentLotteryQiShu = jsonObject.getString("currentLotteryQiShu");//倒计时的期数
                                currentLotteryTime = Long.parseLong(jsonObject.getString("currentLotteryTime"));//倒计时时间
                                String type_id = jsonObject.getString("type_id");//
                                String rulename = jsonObject.getString("rulename");//大小单双
                                String groupname = jsonObject.getString("groupname");//第几球
                                //第几期长龙(显示在大小单双右侧),且用于判断是否为长龙(循环请求开奖结果时,time为0,需要移除当前lottery)
                                String times = jsonObject.getString("times");
                                // )
                                String typename = jsonObject.getString("typename");//彩票名
                                //上一期期数(用于判断是否在开奖(上一期期数和当前倒计时期数相等,说明在开奖,需要请求接口,更新倒计时))
                                String lastLotteryQiShu = jsonObject.getString("lastLotteryQiShu");
                                String dragonId = jsonObject.getString("dragonId");
                                String rules = jsonObject.getString("rules");
                                JSONArray ruleList = JSONObject.parseArray(rules);
                                String rebate = SharePreferencesUtil.getString(getActivity(), game + "", "1");//用户返点(登录时存入)
                                String ruleNameLeft="";
                                String ruleNameRight="";
                                String rebateLeft="";
                                String rebateRight="";
                                String ruleIdLeft="";
                                String ruleIdRight="";
                                for (int j = 0; j < ruleList.size(); j++) {
                                    JSONObject jsonObject2 = ruleList.getJSONObject(j);
                                    String name = jsonObject2.getString("name");//投注linear 大小
                                    String id = jsonObject2.getString("id");// 投注linear rule_id
                                    BigDecimal odds = jsonObject2.getBigDecimal("odds");//赔率
                                    BigDecimal bigDecimal = new BigDecimal(10+"").subtract(new BigDecimal(rebate)) .divide(new BigDecimal("100"));
                                    BigDecimal  finalOdds=odds.subtract(bigDecimal.multiply(odds)).setScale(3, BigDecimal.ROUND_FLOOR);//赔率的计算公式
                                    if(j==0){
                                        ruleNameLeft=name;
                                        rebateLeft=finalOdds+"";
                                        ruleIdLeft=id;
                                    }else if(j==1){
                                        ruleNameRight=name;
                                        rebateRight=finalOdds+"";
                                        ruleIdRight=id;
                                    }
                                }
                                String shijiancha=SharePreferencesUtil.getLong(getActivity(),"shijiancha",0l)+"";
//                            for (int j = 0; j < changLongBetModelArrayList.size(); j++) {
//                                ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(j);
                                //time为0,开奖结果已更新,且当前item不再是长龙
                                if(times.equals("0")){
                                    //将当前item从idList中移除(下次请求不再请求)
                                    gameClassIdList.remove(dragonId);
                                    for (int j = 0; j < changLongBetModelArrayList.size(); j++) {
                                        ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(j);
                                        //将当前item在reycleView中移除
                                        if(changLongBetModel.getDragonId().equals(dragonId)){
                                            changLongBetModelArrayList.remove(changLongBetModel);
                                            changLongAdapter.notifyItemRemoved(j);
                                        }
                                    }

                                }else {
                                    for (int j = 0; j < changLongBetModelArrayList.size(); j++) {
                                        ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(j);
                                        if(changLongBetModel.getDragonId().equals(dragonId)){//遍历列表数据,如果列表数据中item的id与请求到的id一致且倒计时时间不一样,说明该彩票开奖结果已更新,
                                            if(!changLongBetModel.getQishu().equals(currentLotteryQiShu)){
                                                gameClassIdList.remove(dragonId);
                                                ChangLongBetModel changLongBetModel1 = new ChangLongBetModel(game,type_id,ruleIdLeft,ruleIdRight,imageUrl,
                                                        typename,currentLotteryQiShu,currentLotteryTime+"",groupname,rulename,
                                                        times,ruleNameLeft,rebateLeft,ruleNameRight,rebateRight,shijiancha,lastLotteryQiShu,dragonId);
                                                LongDragonFragment longDragonFragment = (LongDragonFragment) getParentFragment();
                                                if(longDragonFragment!=null){
//                                                    longDragonFragment.showToast();
                                                }
                                                changLongBetModelArrayList.remove(j);
                                                changLongBetModelArrayList.add(j,changLongBetModel1);
                                                changLongAdapter.notifyItemChanged(j);
                                            }
                                        }
                                    }
                                }

//                            }
                            }
                        }

                        @Override
                        public void onFailed(String msg) {

                        }
                    });
                }
            }
        }
    };

    private void getDragonList(boolean showLoad) {
        if(showLoad){
            nodataLinear.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        ErrorUtil.hideErrorLayout(mRecy,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        data.put("dragonId","");

        Utils.docking(data, RequestUtil.REQUEST_asscl, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getActivity());
                changLongBetModelArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray dragonList = jsonObject1.getJSONArray("dragonList");
                if(dragonList.size()==0&&showLoad){
                    nodataLinear.setVisibility(View.VISIBLE);
                }else {
                    nodataLinear.setVisibility(View.GONE);
                }
                refreshLayout.finishRefresh();
                for (int i = 0; i < dragonList.size(); i++) {
                    JSONObject jsonObject = dragonList.getJSONObject(i);
                    String image = jsonObject.getString("image");
                    String imageUrl=Utils.getFirstImgurl(getActivity())+image;
                    Integer game = jsonObject.getInteger("game");
                    String currentLotteryQiShu = jsonObject.getString("currentLotteryQiShu");//倒计时的期数
                    currentLotteryTime = Long.parseLong(jsonObject.getString("currentLotteryTime"));//倒计时时间
                    String type_id = jsonObject.getString("type_id");
                    String rulename = jsonObject.getString("rulename");//大小单双
                    String groupname = jsonObject.getString("groupname");//第几球
                    String times = jsonObject.getString("times");//第几期(大小单双右侧的)
                    String typename = jsonObject.getString("typename");//彩票名
                    String lastLotteryQiShu = jsonObject.getString("lastLotteryQiShu");//上一期期数(用于判断是否在开奖(上一期期数和当前倒计时期数相等,说明在开奖,需要请求接口,更新倒计时))
                    String dragonId = jsonObject.getString("dragonId");
                    String rules = jsonObject.getString("rules");
                    Long lottery_id = jsonObject.getLong("id");
                    JSONArray ruleList = JSONObject.parseArray(rules);
                    String rebate = SharePreferencesUtil.getString(getActivity(), game + "", "1");//用户返点(登录时存入)
                    String ruleNameLeft="";
                    String ruleNameRight="";
                    String rebateLeft="";
                    String rebateRight="";
                    String ruleIdLeft="";
                    String ruleIdRight="";
                    for (int j = 0; j < ruleList.size(); j++) {
                        JSONObject jsonObject2 = ruleList.getJSONObject(j);
                        String name = jsonObject2.getString("name");//投注linear 大小
                        String id = jsonObject2.getString("id");// 投注linear rule_id
                        BigDecimal odds = jsonObject2.getBigDecimal("odds");//赔率
                        BigDecimal bigDecimal = new BigDecimal("10").subtract(new BigDecimal(rebate)).divide(new BigDecimal("100"));
                        BigDecimal  finalOdds=odds.subtract(bigDecimal.multiply(odds)).setScale(3, BigDecimal.ROUND_FLOOR);//赔率的计算公式
                        if(j==0){
                            ruleNameLeft=name;
                            rebateLeft=finalOdds+"";
                            ruleIdLeft=id;
                        }else if(j==1){
                            ruleNameRight=name;
                            rebateRight=finalOdds+"";
                            ruleIdRight=id;
                        }
                    }
//                    String serviceTime = Utils.getFileData("time");

//                        String shijiancha=(System.currentTimeMillis()-Long.parseLong(serviceTime))+"";
                    String shijiancha=SharePreferencesUtil.getLong(getActivity(),"shijiancha",0l)+"";
                    ChangLongBetModel changLongBetModel = new ChangLongBetModel(game,type_id,ruleIdLeft,ruleIdRight,imageUrl,
                            typename,currentLotteryQiShu,currentLotteryTime+"",groupname,rulename,
                            times,ruleNameLeft,rebateLeft,ruleNameRight,rebateRight,shijiancha,lastLotteryQiShu,dragonId);
                    changLongBetModel.setId(lottery_id==null?0l:lottery_id);
                    changLongBetModelArrayList.add(changLongBetModel);


                }
                HbGameClassModel instance = HbGameClassModel.getInstance();
                if(null!=instance){
                    selectorId(instance);
                }
                changLongAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(getActivity());
                refreshLayout.finishRefresh(false);
                ErrorUtil.showErrorLayout(DragonBetFragment.this,mRecy,errorLinear,refreshTv);
            }
        });
    }
    /*
    倒计时结束的监听
     */
    @Override
    public void onEnd(String id, int index) {
        ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(index);
        String dragonId = changLongBetModel.getDragonId();
        if(!gameClassIdList.contains(dragonId)/*&&index>=firstItemPosition&&index<=lastItemPosition*/){//当前结束倒计时的item不存在,且当前item可见时,才将id取出
            gameClassIdList.add(dragonId);
//            indexList.add(index);
        }
    }
    /*
    recycleView点击事件
     */
    @Override
    public void onItemClick(View view, int position) {
        if(position>=changLongBetModelArrayList.size()){
            //防止点击的时候刚好有长龙被移出列表造成下标越界
            return;
        }
        ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(position);
        rebateLeft = changLongBetModel.getRebateLeft();
        recyPosition=position;
        int size = selectorList.size();
        betNumTv.setText(size+"");
        //输入框的值
        String toString = amountEdit.getText().toString();
        BigDecimal bigDecimal = BigDecimal.ZERO;
        if(!StringMyUtil.isEmptyString(toString)){
            bigDecimal=  new BigDecimal(toString).setScale(2);
        }
        BigDecimal winAmount = BigDecimal.ZERO;
        BigDecimal allAmount = BigDecimal.ZERO;
        if(size==0){
            winAmount=BigDecimal.ZERO;
            allAmount=BigDecimal.ZERO;
        }else {
            //最高可赢金额

            winAmount=  bigDecimal.multiply(new BigDecimal(changLongBetModel.getRebateLeft())).setScale(2,RoundingMode.HALF_UP);
            //总共投注金额
            allAmount=bigDecimal.multiply(new BigDecimal(size)).setScale(0,RoundingMode.HALF_UP);
        }
        canWinTv.setText(winAmount+"");
        allAmountTv.setText(allAmount+"");
    }
    @Override
    public void onResume() {
        super.onResume();
        isPause=false;
    }

    @Override
    public void onStart() {
        super.onStart();
        isPause=false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isPause=true;
    }

    @Override
    public void onStop() {
        super.onStop();
        isPause=true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnableCurrent);
        handler.removeCallbacks(runnableKaiJiang);
        handler.removeCallbacks(runnableAll);
        EventBus.getDefault().unregister(this);
    }
    /*
    输入框监听
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int size = selectorList.size();
        String toString = s.toString();
        BigDecimal bigDecimal =BigDecimal.ZERO;//输入框金额bigDecimal值
        if(StringMyUtil.isEmptyString(toString)){
            bigDecimal=BigDecimal.ZERO;
        }else {
            bigDecimal=  new BigDecimal(toString).setScale(2);
        }
        BigDecimal winAmount = BigDecimal.ZERO;
        BigDecimal allAmount = BigDecimal.ZERO;
        if(size==0){
            winAmount=BigDecimal.ZERO;
            allAmount=BigDecimal.ZERO;
        }else {
            //最高可赢金额
            winAmount=  bigDecimal.multiply(new BigDecimal(rebateLeft)).setScale(2,RoundingMode.HALF_UP);
            //总共投注金额
            allAmount=bigDecimal.multiply(new BigDecimal(size)).setScale(0,RoundingMode.HALF_UP);
        }
        canWinTv.setText(winAmount+"");
        allAmountTv.setText(allAmount+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //清空按钮
            case R.id.clear_textview:
                amountEdit.setText("");
                canWinTv.setText("0");
                betNumTv.setText("0");
                allAmountTv.setText("0");
                for (int i = 0; i < selectorList.size(); i++) {
                    selectorList.get(i).setStatusRight(0);
                    selectorList.get(i).setStatusLeft(0);
                }
                selectorList.clear();
                showToast(Utils.getString(R.string.已清空));

                break;
            //点击确定投注
            case R.id.sure_bet:
                if(LoginIntentUtil.isLogin(getContext())){
                    String toString = amountEdit.getText().toString();
                    if(StringMyUtil.isEmptyString(toString)){
                        showToast(Utils.getString(R.string.请输入金额));
                    }else if(selectorList.size()==0){
                        showToast(Utils.getString(R.string.请选择玩法));
                    }else{
                        hintKbTwo();//隐藏软键盘
                        ChangLongBetModel changLongBetModel = selectorList.get(0);
                        String qishu = changLongBetModel.getQishu();
                        String allAmount= allAmountTv.getText().toString();
                        String rule_id="";
                        String betRule="";
                        if(changLongBetModel.isLeft()){
                            rule_id= changLongBetModel.getRuleId_Left();
                            betRule= changLongBetModel.getBetTypeLeft();
                        }else {
                            rule_id= changLongBetModel.getRuleId_Right();
                            betRule= changLongBetModel.getBetTypeRight();
                        }

                        typenameTv.setText(changLongBetModel.getTypaname());
                        lotteryQishuTv.setText(qishu);
                        betAmountTv.setText(allAmount);
                        betContentTv.setText(betRule);
                        cancelTv.setOnClickListener(this);
                        sureTv.setOnClickListener(this);
                        sureBetPop.setAnimationStyle(R.style.pop_scale_animation);
                        sureBetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                ProgressDialogUtil.darkenBackground(getActivity(),1f);
                            }

                        });
                        if(!sureBetPop.isShowing()){
                            sureBetPop.showAtLocation(amountEdit,Gravity.CENTER,0,0);
                            ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                        }
                    }
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }

                break;
            case R.id.sure_bet_cancel:
                sureBetPop.dismiss();
                break;
            case R.id.sure_bet_sure:
                sureTv.setClickable(false);
                sureBetPop.dismiss();
                ChangLongBetModel changLongBetModel = selectorList.get(0);
                int game=changLongBetModel.getGame();
                String type_id=changLongBetModel.getType_id();
                String qishu = changLongBetModel.getQishu();
                String allAmount= allAmountTv.getText().toString();
                String rule_id="";
        //        String betRule="";
                if(changLongBetModel.isLeft()){
                    rule_id= changLongBetModel.getRuleId_Left();
              //      betRule= changLongBetModel.getBetTypeLeft();
                }else {
                    rule_id= changLongBetModel.getRuleId_Right();
            //        betRule= changLongBetModel.getBetTypeRight();
                }
                requestBet(getActivity(), user_id, game, Integer.parseInt(type_id), rule_id, allAmount, qishu);
                Message obtain = Message.obtain();
                obtain.what=100;

                handler.sendMessageDelayed(obtain,500);
                break;
        }
    }

    private void initSureBetPop() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.changlong_sure_bet_pop,null);
        sureBetPop= new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        typenameTv=view.findViewById(R.id.lottery_name);
        lotteryQishuTv=view.findViewById(R.id.lottery_qishu);
        betAmountTv=view.findViewById(R.id.bet_amount);
        betContentTv=view.findViewById(R.id.rule_name);
        cancelTv=view.findViewById(R.id.sure_bet_cancel);
        sureTv=view.findViewById(R.id.sure_bet_sure);
    }

    private void getMoney() {
        LongDragonFragment fragment = (LongDragonFragment) getParentFragment();
        TextView moneyTv=  fragment.view.findViewById(R.id.money_textview);
        LinearLayout showMoneyLinear=   fragment.view.findViewById(R.id.show_money_linear);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);

        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                BigDecimal amount = memberMoney.getBigDecimal("amount");
                moneyTv.setText("¥"+amount+"");
                showMoneyLinear.setVisibility(View.GONE);
                moneyTv.setVisibility(View.VISIBLE);
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }
    public  void requestBet(final Context context, final long user_id, final int game, final int type_id, final String rule_id, final String amount, final String lotteryqishu){
        String url="";
        if(game==1){
            url= RequestUtil.REQUEST_7r;//快三
        }else if(game==2){
            url=RequestUtil.REQUEST_02ssc;//时时彩
        }else if(game==3){
            url=RequestUtil.REQUEST_02sc;//赛车
        }else if(game==4){
            url=RequestUtil.REQUEST_03lhc;//六合彩
        }else if(game==5){
            url=RequestUtil.REQUEST_03dd;//pc蛋蛋
        }else if(game==6){
            url=RequestUtil.REQUEST_02ha;//北京快乐8
        }else if(game==7){
            url=RequestUtil.REQUEST_02farm;//农场
        }else if(game==8){
            url=RequestUtil.REQUEST_02happyten;//广东快乐10分
        }else if(game==9){
            url=RequestUtil.REQUEST_02xuanwu;//11选5
        }
        HashMap<String, Object> data = new HashMap<>();
        final String token = SharePreferencesUtil.getString(context, "token", "");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = df.format(date);
        data.put("user_id",user_id);
        data.put("type_id",type_id);
        data.put("rule_id",rule_id);
        data.put("amount",amount);
        data.put("lotteryqishu",lotteryqishu);
        data.put("source",2);
        data.put("curtime",format);//当前时间
        data.put("token",token);//用户token
//        data.put("lianma",lianma);//用户token
        Utils.docking(data, url, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                sureTv.setClickable(true);
                amountEdit.setText("");
                canWinTv.setText("0");
                betNumTv.setText("0");
                allAmountTv.setText("0");
                for (int i = 0; i < selectorList.size(); i++) {
                    selectorList.get(i).setStatusRight(0);
                    selectorList.get(i).setStatusLeft(0);
                }
                selectorList.clear();
                showToast(Utils.getString(R.string.下注成功));
                //不分享到直播间
                if(!sharebetPop.isShowing()){
//                    sharebetPop.showAtLocation(amountEdit,Gravity.CENTER,0,0);
//                    ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                }
                shareBetCancelTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharebetPop.dismiss();
                    }
                });
                shareBerSureTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharebetPop.dismiss();
                        ChangLongBetModel changLongBetModel = changLongBetModelArrayList.get(recyPosition);
                        if(changLongBetModel.isLeft()){
                            betRule= changLongBetModel.getBetTypeLeft();
                         //   unBetRule=changLongBetModel.getBetTypeRight();
                            betGroupName = changLongBetModel.getPlayType();
                        }else {
                            betRule= changLongBetModel.getBetTypeRight();
                        //    unBetRule=changLongBetModel.getBetTypeLeft();
                            betGroupName = changLongBetModel.getPlayType();
                        }

                        String touXiang = TouXiangUtil.getMineTouXiang(getActivity());
                        String type_id=changLongBetModel.getType_id();
                        typename = changLongBetModel.getTypaname();
                       /* Intent intent = new Intent(getActivity(), ChatroomListActivity.class);
                        intent.putExtra("touXiang",touXiang);
                        intent.putExtra("type_id",type_id);
                        intent.putExtra("rule_id",rule_id);
                        intent.putExtra("lotteryqishu",lotteryqishu);
                        intent.putExtra("game",game+"");
                        intent.putExtra("typename",typename);
                        intent.putExtra("amount",amount);
                        intent.putExtra("betRule",betRule);
                        intent.putExtra("betGroupName",betGroupName);
                        intent.putExtra("user_id",user_id+"");
                        intent.putExtra("fromWhere","betNote");
                        intent.putExtra("isShare",true);
                        startActivity(intent);*/


                    }

                });

            }
            @Override
            public void failed(MessageHead messageHead) {
                sureTv.setClickable(true);
                JSONObject headData = messageHead.getData();
                String flag = headData.getString("flag");
                showToast(Utils.getString(R.string.下注失败)+"\n" + messageHead.getInfo());
                sureBetPop.dismiss();

            }
        });
    }

    private void initShareBetPop() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.share_bet_pop,null);
        sharebetPop= new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        sharebetPop.setAnimationStyle(R.style.pop_scale_animation);
        sharebetPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
            }
        });
        shareBerSureTv=view.findViewById(R.id.share_bet_sure);
        shareBetCancelTv=view.findViewById(R.id.share_bet_cancel);
    }

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getActivity().getCurrentFocus()!=null){
            if (getActivity().getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}