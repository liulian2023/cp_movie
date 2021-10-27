package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.GouCaiDaTingAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CountDown;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerFragemnt extends BaseFragment {
    private RecyclerView mRecy;
    private RefreshLayout refreshLayout;
    private ArrayList<CountDown> countDowns = new ArrayList<>();
    GouCaiDaTingAdapter gouCaiDaTingAdapter;
    LinearLayoutManager linearLayoutManager;
    private Map<String, Object> dataAll;
    private ArrayList<String> gameClassIdList=new ArrayList<>();
//    private Handler handler =new Handler();
    private MyHandler handler = new MyHandler();
    private ArrayList<String> currentIdList=new ArrayList<>();
    private ArrayList<String> currentCopyIdList=new ArrayList<>();
    private boolean isCreate =false;
    private int firstItemPosition;
    private int lastItemPosition;
    private ConstraintLayout loadingLinear;
    private View viewCopy;
    private RunnableTime runnableTime;
    private RunnableOpen runnableOpen;
    private RunnableCurretEnd runnableCurretEnd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.shopping_viewpager_fragment, container, false);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        refreshLayout=view.findViewById(R.id.refresh);
        runnableTime=new RunnableTime(new WeakReference<>(this));
        runnableOpen=new RunnableOpen(new WeakReference<>(this));
        runnableCurretEnd=new RunnableCurretEnd(new WeakReference<>(this));
        initView(view);//配置适配器，添加Tab头部,Tab头部点击事件，
        //下拉刷新
        isCreate=true;
        return view;
    }
    public static ViewPagerFragemnt newInstance (int position,String tabName){
        ViewPagerFragemnt viewPagerFragemnt = new ViewPagerFragemnt();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putString("tabName",tabName);
        viewPagerFragemnt.setArguments(bundle);
        return viewPagerFragemnt;

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void updateXian(HbGameClassModel hbGameClassModel){
        selectorId(hbGameClassModel);
    }
    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        for (int i = 0; i < countDowns.size(); i++) {
            CountDown countDown = countDowns.get(i);
            for (int j = 0; j < idList.size(); j++) {
                if((countDown.getId()+"").equals(idList.get(j))){
                    countDown.setXian(true);
                    break;
                }
            }
        }
        if(null!=gouCaiDaTingAdapter){

            gouCaiDaTingAdapter.notifyDataSetChanged();
        }
    }
    static class MyHandler extends Handler{}
    @Override
    public void onResume() {
        super.onResume();
            if(isCreate){
                refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
                initLottoryType();//请求全部或者分类数据
                onRefreshListener();//下拉刷新
            }
        handler.postDelayed(runnableTime,1000);//倒计时
        handler.postDelayed(runnableOpen,10000);//每10秒请求一次接口,处理开奖数据
        handler.postDelayed(runnableCurretEnd,3000);//每5秒请求倒计时结束彩票的新倒计时
        isCreate=false;
        isPause=false;
    }

    /*
    下拉刷新
     */
    private void onRefreshListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initLottoryType();
                refreshLayout.finishRefresh();
            }
        });
    }
    /*
    请求数据
     */
    private void initLottoryType() {
//        String game = getArguments().getString("game", "");
        String game = getArguments().getString("tabName", "");
        if(game.equals(Utils.getString(R.string.全部))){
            getAllLottery();
        }
        else {
            getClassIdLottry();
        }
    }
    private void initView(View view) {
        loadingLinear=view.findViewById(R.id.loading_linear);
        /*
        recycleView配置相关
         */
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecy = view.findViewById(R.id.goucai_recycleview);
        gouCaiDaTingAdapter = new GouCaiDaTingAdapter(countDowns,getActivity());
        mRecy.setLayoutManager(linearLayoutManager);
        mRecy.setAdapter(gouCaiDaTingAdapter);
        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState==RecyclerView.SCROLL_STATE_IDLE){//停止滚动或没有滚动
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
                super.onScrollStateChanged(recyclerView, newState);
            }


        });
        //倒计时结束监听
        onEndListener();
        //recycleView点击事件
        recycleViewClicked();

    }
    /*
    倒计时结束监听
     */
    private void onEndListener() {
        gouCaiDaTingAdapter.setOnEndListener(new GouCaiDaTingAdapter.EndListener() {
            @Override
            public void onEnd(final String id, final int index) {
                //倒计时结束后将id取出, 每5秒统一请求(之前是倒计时结束后立刻请求,接口请求过于频繁)
                if(!currentIdList.contains(id)/*&&index>=firstItemPosition&&index<=lastItemPosition*/){
                    currentIdList.add(id);
                    currentCopyIdList.add(id);
                }

            }
        });
    }
    //循环请求倒计时的runnable
 static class RunnableCurretEnd implements Runnable {
        WeakReference<ViewPagerFragemnt>viewPagerFragemntWeakReference;
        public RunnableCurretEnd(WeakReference<ViewPagerFragemnt> viewPagerFragemntWeakReference) {
            this.viewPagerFragemntWeakReference = viewPagerFragemntWeakReference;
        }

        @Override
      public void run() {
            ViewPagerFragemnt viewPagerFragemnt = viewPagerFragemntWeakReference.get();
             if(null==viewPagerFragemnt){
                return;
            }
            if(viewPagerFragemnt.isPause){
              return;
          }
            viewPagerFragemnt. currentEnd();
            viewPagerFragemnt.  handler.postDelayed(this,3000);

      }
  };
    /*

    倒计时结束后,重新请求倒计时
     */
    public void currentEnd() {
        if(currentIdList.size()!=0){
            Map<String, Object> dataId = new HashMap<>();
            String needId = "";
            for(String s : currentIdList){//将id取出,并用逗号分割,作为请求接口的参数
                needId +=   s + ",";
            }
            needId = needId.substring(0, needId.length()-1);
            dataId.put("gameClassId", needId);
            Utils.docking(dataId, RequestUtil.REQUEST_02dh, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    JSONArray gameClassList = jsonObject.getJSONArray("gameClassList");
                    for (int i = 0; i < gameClassList.size(); i++) {
                        JSONObject jsonObject1 = gameClassList.getJSONObject(i);
                        String image = jsonObject1.getString("image");
                        String firstImageUrl = SharePreferencesUtil.getString(getActivity(), "FirstImageUrl", "");
                        String finalUrl = firstImageUrl + image;
                        //---------------------------------------------------------------------------------------------------
    /*
    请求前端接口需要用到的参数
     */
                        Integer type_id = jsonObject1.getInteger("type_id");
                        String isopenOffice = jsonObject1.getString("isopenOffice");
                        String isStart = jsonObject1.getString("isStart");
                        Integer game = jsonObject1.getInteger("game");
                        String typename = jsonObject1.getString("typename");
                        //-------------------------------------------------------------------------------------------------
                        String lastLotteryQiShu = jsonObject1.getString("lastLotteryQiShu");//上一期期数
                        String waitLotteryQiShu = jsonObject1.getString("waitLotteryQiShu");//等待开奖
                        String currentLotteryQiShu = jsonObject1.getString("currentLotteryQiShu");//当前期数
                        String id = jsonObject1.getString("id");
                        long l1 = Long.parseLong(waitLotteryQiShu);
                        long lo = l1 +1;
                        String currentLotteryTime = jsonObject1.getString("currentLotteryTime");//倒计时结束时间
                        String lastLotteryNo = jsonObject1.getString("lastLotteryNo");//开奖数字
                        String[] split = lastLotteryNo.split(",");
                        List<String> strings1 = Arrays.asList(split);
                        String num = "";
                        for (int b = 0; b < strings1.size(); b++) {
                            String str = strings1.get(b);
                            num += str + " ";
                        }
//                        Long countTime = SharePreferencesUtil.getLong(getActivity(), "lcountTime", 0l);//本地和服务器时间差
                        Long countTime = SharePreferencesUtil.getLong(getActivity(), "shijiancha", 0l);//本地和服务器时间差
                        CountDown newCountDown = new CountDown(finalUrl, typename, num, Utils.getString(R.string.距离第) + currentLotteryQiShu +Utils.getString(R.string.期截止还有), "1", currentLotteryTime, countTime, id, type_id,isopenOffice,isStart,game,waitLotteryQiShu,lastLotteryQiShu);
                        for (int j = 0; j < countDowns.size(); j++) {
                            CountDown countDown = countDowns.get(j);
                            if(countDown.getId().equals(id)){
                                countDowns.remove(j);
                                countDowns.add(j, newCountDown);
                                gouCaiDaTingAdapter.notifyItemChanged(j);
                            }
                        }
                        for (int j = 0; j < currentCopyIdList.size(); j++) {
                            String s = currentCopyIdList.get(j);
                            if(id.equals(s)){
                                currentIdList.remove(s);
                            }
                        }
                    }
                }

                @Override
                public void failed(MessageHead messageHead) {
                    System.out.println(messageHead.getInfo());
                }
            });
        }

    }

    /*
    请求分类列表下的数据(根据fragment的game筛选)
     */
    private void getClassIdLottry() {
        dataAll = new HashMap<>();
        dataAll.put("isHot", "");
        Utils.docking(dataAll, RequestUtil.REQUEST_01dhnew, 1  , new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(getActivity());
                countDowns.clear();
                Long countTime = SharePreferencesUtil.getLong(getActivity(), "shijiancha", 0l);
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONArray gameClassList = jsonObject.getJSONArray("gameClassList");
                //GouCaiFragment中传入的数据,key为tab栏对应的game名
//                String classId = SharePreferencesUtil.getString(getActivity(), (getArguments().getString("game")).hashCode()+"", "");
                String classId = SharePreferencesUtil.getString(getActivity(), (getArguments().getString("tabName")).hashCode()+"", "");
                String[] splitId = classId.split(",");
                List<String> stringId = Arrays.asList(splitId);//得到分类下的彩票id
                for (int i = 0; i < gameClassList.size(); i++) {
                    JSONObject jsonObject1 = gameClassList.getJSONObject(i);
                    String id = jsonObject1.getString("id");
                    if (stringId.contains(id)) {
                        String image = jsonObject1.getString("image");
                        String firstImageUrl = SharePreferencesUtil.getString(getActivity(), "FirstImageUrl", "");
                        String finalUrl = firstImageUrl + image;
                        //---------------------------------------------------------------------------------------------------
        /*
        请求前端接口需要用到的参数
         */
                        Integer type_id = jsonObject1.getInteger("type_id");
                        String isopenOffice = jsonObject1.getString("isopenOffice");
                        String isStart = jsonObject1.getString("isStart");
                        Integer game = jsonObject1.getInteger("game");
                        String typename = jsonObject1.getString("typename");
                        //-------------------------------------------------------------------------------------------------
                        String lastLotteryQiShu = jsonObject1.getString("lastLotteryQiShu");
                        String waitLotteryQiShu = jsonObject1.getString("waitLotteryQiShu");//等待开奖
                        String currentLotteryQiShu = jsonObject1.getString("currentLotteryQiShu");//当前期数
                        long l1 = Long.parseLong(waitLotteryQiShu);
                        long lo = l1 +1;
                         ;
                       String lastLotteryNo = jsonObject1.getString("lastLotteryNo");
                        if(StringMyUtil.isEmptyString(lastLotteryNo)){
                            lastLotteryNo="";
                        }
                        String[] split = lastLotteryNo.split(",");
                        List<String> strings1 = Arrays.asList(split);
                        String num = "";
                        for (int b = 0; b < strings1.size(); b++) {
                            String str = strings1.get(b);
                            num += str + " ";
                        }
                        String currentLottery = jsonObject1.getString("currentLotteryTime");//倒计时结束时间
//                        if(isStart.equals("1")){
                            CountDown countDown = new CountDown(finalUrl, typename, num, Utils.getString(R.string.距离第) + currentLotteryQiShu +Utils.getString(R.string.期截止还有), "1", currentLottery, countTime, id,type_id,isopenOffice,isStart,game,waitLotteryQiShu,lastLotteryQiShu);
                            countDowns.add(countDown);
//                        }
                    }
                }
                gouCaiDaTingAdapter.notifyDataSetChanged();
                HbGameClassModel instance = HbGameClassModel.getInstance();
                if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
                    selectorId(instance);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop();
            }
        });
    }
    private void recycleViewClicked() {
        gouCaiDaTingAdapter.setOnItemClickListener(new GouCaiDaTingAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                view.setClickable(false);
                viewCopy=view;
                if(!LoginIntentUtil.isLogin(getActivity())){
                    LoginIntentUtil.toLogin(getActivity());
                }
                else{
                    CountDown countDown = countDowns.get(position);
                    int game = countDown.getGame();
                    int type_id = countDown.getType_id();
                    String isStart = countDown.getIsStart();
                    String isopenOffice = countDown.getIsopenOffice();
                    String name = countDown.getName();
                    ToBetAtyUtils.toBetActivity(getActivity(),game,name,type_id,isopenOffice,isStart);

                }

            }
        });
    }
    private void getAllLottery() {
        loadingLinear.setVisibility(View.VISIBLE);
        dataAll = new HashMap<>();
        dataAll.put("gameClassId", "");
        Utils.docking(dataAll, RequestUtil.REQUEST_02dh, -1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
//                ProgressDialogUtil.stop();
                loadingLinear.setVisibility(View.GONE);
                countDowns.clear();
                JSONObject jsonObject = JSONObject.parseObject(content);
                String server = jsonObject.getString("serverTime");
/*                long serverTime = Long.parseLong(server);//服务器时间
                long nowTime = System.currentTimeMillis();//当前时间
                long countTime = serverTime - nowTime;//时间差
                SharePreferencesUtil.putLong(getActivity(), "countTime", countTime);*/
                JSONArray gameClassList = jsonObject.getJSONArray("gameClassList");
                for (int i = 0; i < gameClassList.size(); i++) {
                    JSONObject jsonObject1 = gameClassList.getJSONObject(i);
                    String isStart = jsonObject1.getString("isStart");
                    //---------------------------------------------------------------------------------------------------
//                    if(isStart.equals("1")){
                        /*
            请求后台接口需要用到的参数
             */
                        Integer type_id = jsonObject1.getInteger("type_id");
                        String isopenOffice = jsonObject1.getString("isopenOffice");

                        Integer game = jsonObject1.getInteger("game");
                        String typename = jsonObject1.getString("typename");
                        //-------------------------------------------------------------------------------------------------

                        String image = jsonObject1.getString("image");
                        String firstImageUrl = SharePreferencesUtil.getString(getActivity(), "FirstImageUrl", "");
                        String finalUrl = firstImageUrl + image;
                        String id = jsonObject1.getString("id");
                        String lastLotteryQiShu = jsonObject1.getString("lastLotteryQiShu");
                        String waitLotteryQiShu = jsonObject1.getString("waitLotteryQiShu");//等待开奖
                        String currentLotteryQiShu = jsonObject1.getString("currentLotteryQiShu");//当前期数
//                    if(!lastLotteryQiShu.equals(waitLotteryQiShu)){
//
//                    }
                    String lastLotteryNo = jsonObject1.getString("lastLotteryNo");
                    if(waitLotteryQiShu!=null&&lastLotteryNo!=null){
                        long l1 = Long.parseLong(waitLotteryQiShu);
                        long lo = l1 +1;
                        String[] split = lastLotteryNo.split(",");
                        List<String> strings1 = Arrays.asList(split);
                        String num = "";
                        for (int b = 0; b < strings1.size(); b++) {
                            String str = strings1.get(b);
                            num += str + " ";
                        }
                        String currentLotteryTime = jsonObject1.getString("currentLotteryTime");//倒计时结束时间

                        CountDown countDown = new CountDown(finalUrl, typename, num, Utils.getString(R.string.距离第) + currentLotteryQiShu +Utils.getString(R.string.期截止还有), "1", currentLotteryTime, 100, id,type_id,isopenOffice,isStart,game,waitLotteryQiShu,lastLotteryQiShu);
                        countDowns.add(countDown);
                    }

//                    }
                }
                gouCaiDaTingAdapter.notifyDataSetChanged();
//                for (CountDown itemInfo : countDowns) {
//                    itemInfo.setCurrentLotteryTime(itemInfo.getCurrentLotteryTime());
//                }
                HbGameClassModel instance = HbGameClassModel.getInstance();
                if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
                    selectorId(instance);
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop();
                loadingLinear.setVisibility(View.GONE);
            }
        });
    }

    static class RunnableTime implements Runnable{
        WeakReference<ViewPagerFragemnt> viewPagerFragemntWeakReference;
        public RunnableTime(WeakReference<ViewPagerFragemnt> viewPagerFragemntWeakReference) {
            this.viewPagerFragemntWeakReference = viewPagerFragemntWeakReference;
        }
        @Override
        public void run() {
            ViewPagerFragemnt viewPagerFragemnt = viewPagerFragemntWeakReference.get();
            if(null==viewPagerFragemnt){
                return;
            }
            if(!viewPagerFragemnt.isPause) {
                for (int i = 0; i < viewPagerFragemnt.countDowns.size(); i++) {
                    CountDown countDown = viewPagerFragemnt.countDowns.get(i);
                    if(countDown.getIsStart().equals("1")){
                        String currentLotteryTime = countDown.getCurrentLotteryTime();
                        long time = Long.parseLong(currentLotteryTime);
                        time = time - 1000;
                    }
                }
                viewPagerFragemnt.gouCaiDaTingAdapter.notifyDataSetChanged();
            }
            viewPagerFragemnt. handler.postDelayed(this,1000);
        }
    };
    /*
   每十秒请求一次倒计时结束,开奖号码尚未更新的彩票
    */
  static class RunnableOpen implements Runnable {
      WeakReference<ViewPagerFragemnt> viewPagerFragemntWeakReference;

        public RunnableOpen(WeakReference<ViewPagerFragemnt> viewPagerFragemntWeakReference) {
            this.viewPagerFragemntWeakReference = viewPagerFragemntWeakReference;
        }
        @Override
        public void run() {
            ViewPagerFragemnt viewPagerFragemnt = viewPagerFragemntWeakReference.get();
            if(null==viewPagerFragemnt){
                return;
            }
            viewPagerFragemnt.  handler.postDelayed(this,10000);
            if(viewPagerFragemnt.isPause){
                return;
            }
            viewPagerFragemnt. gameClassIdList.clear();
            for (int i = 0; i < viewPagerFragemnt.countDowns.size(); i++) {//遍历列表数据,如果上一期期数与等待开奖期数不一致,说明该彩票开奖号码尚未更新,将id取出
                CountDown countDown =viewPagerFragemnt. countDowns.get(i);
                if(StringMyUtil.isNotEmpty(countDown.getLastLotteryQiShu())){
                    if(!countDown.getLastLotteryQiShu().equals(countDown.getWaitPeriod())){
                        if(countDown.getIsStart().equals("1")){
                            viewPagerFragemnt.  gameClassIdList.add(countDown.getId());
                        }
                    }
                }

            }
            if(viewPagerFragemnt.gameClassIdList.size()!=0){//id容器不为空, 则请求接口(为空时请求,解析json时会报空指针异常)
                Map<String, Object> dataId = new HashMap<>();
                String needId = "";
                for(String id :viewPagerFragemnt. gameClassIdList){//将id取出,并用逗号分割,作为请求接口的参数
                    needId +=   id + ",";
                }
                needId = needId.substring(0, needId.length()-1);
//                System.out.println("needId =   "+needId);
                dataId.put("gameClassId",needId );
                Utils.docking(dataId, RequestUtil.REQUEST_02dh, 0, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content) {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        JSONArray gameClassList = jsonObject.getJSONArray("gameClassList");
//
                        for (int i = 0; i < gameClassList.size(); i++) {
                            JSONObject jsonObject1 = gameClassList.getJSONObject(i);

                            String waitLotteryQiShu = jsonObject1.getString("waitLotteryQiShu");//等待开奖
                            String currentLotteryQiShu = jsonObject1.getString("currentLotteryQiShu");//当前期数
                            String id = jsonObject1.getString("id");
                            long l1 = Long.parseLong(waitLotteryQiShu);
                            long lo = l1 +1;
                            String lastLotteryNo = jsonObject1.getString("lastLotteryNo");//开奖数字
                            String[] split = lastLotteryNo.split(",");
                            List<String> strings1 = Arrays.asList(split);
                            String num = "";
                            for (int b = 0; b < strings1.size(); b++) {
                                String str = strings1.get(b);
                                num += str + " ";
                            }
                            for (int j = 0; j <viewPagerFragemnt. countDowns.size(); j++) {
                                CountDown countDown = viewPagerFragemnt.countDowns.get(j);
                                if(countDown.getId().equals(id)){//遍历列表数据,如果列表数据中item的id与此事请求到的id一致且开奖号码不一致,说明该彩票开奖结果已更新,
                                    if(!countDown.getPeriodsNum().equals(num)){
                                        countDown.setPeriodsNum(num);//开奖结果已更新则更新中该item的中奖号码
                                        viewPagerFragemnt. gouCaiDaTingAdapter.notifyItemChanged(j);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void failed(MessageHead messageHead) {

                    }
                });
            }

        }
    };

    boolean isPause=false;


    @Override
    public void onStart() {
        super.onStart();
        isPause=false;
        /*
        判空,只有在触发recycleView点击事件回调,viewCopy不为空时才设置为true,
        第一次进入onStart时,viewCopy为null
         */
        if(viewCopy!=null){
            viewCopy.setClickable(true);
        }
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
        handler.removeCallbacks(runnableTime);//移除runnable
        handler.removeCallbacks(runnableOpen);
        handler.removeCallbacks(runnableCurretEnd);
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
    }
}
