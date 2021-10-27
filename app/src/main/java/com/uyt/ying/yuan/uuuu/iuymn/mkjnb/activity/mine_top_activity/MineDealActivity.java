package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildBetAndDealAllPupopwindowAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.FragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.MineDealAllEven;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildBetAndDealAllPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/*
交易记录activity(TabLyout+viewPager)
 */

public class MineDealActivity extends BaseActivity implements TabLayout.BaseOnTabSelectedListener, View.OnClickListener {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private TabLayout mtab;//tab导航栏
    private ArrayList<String>titles =new ArrayList<>();
    private LinearLayout chooseLinearAll;//所有类型类型 筛选控件
    private LinearLayout chooseLinearOut;//提现明细 筛选按钮
    private LinearLayout chooseLinearIn;//充值明细 筛选按钮
    private TextView back;//返回键
    private Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
    private LinearLayout wrapLinear;//popwindoew 定位元素
    private ArrayList<ChildBetAndDealAllPop> childBetAndDealAllPops = new ArrayList<>();//所有类型筛选popupwindow 数据
    private ChildBetAndDealAllPupopwindowAdapter childBetAndDealAllPupopwindowAdapter;//所有类型筛选popupwindow适配器
    private RecyclerView recyclerViewPop;//所有类型筛选popupwindow recycleView
    private LayoutInflater layoutInflater;//布局管理器
    private PopupWindow popupWindowAll;//所有类型筛选栏
    private PopupWindow popupWindowOut;//提现筛选栏
    private PopupWindow popupWindowIn;//充值筛选栏
    private View inflateAll;//所有类型筛选popupwindoew视图
    private RadioButton todayAll;//所有类型筛选popupwindow今天按钮
    private RadioButton yestodayAll;//所有类型筛选popupwindow昨天按钮
    private RadioButton sevenAll;//所有类型筛选popupwindow七天按钮
    private Button chooseAll;//所有类型筛选popupwindow 的筛选按钮
    private int popPosition;//popwindow recyclrView中所选中item的position
    private Date startAll;//全部类型 界面下请求数据时的开始时间
    private Date endAll;
    private int pagenumAll = 1;//用于上拉加载请求数据时,传的pageNo;
    private int pageSizeAll = 15;
    private String typeAll;//全部类型 界面下popupwindow框选中的类型
    private View inflateOut;//提现明细筛选popupwindoew视图
    private RadioButton todayOut;//提现明细筛选popupwindow今天按钮
    private RadioButton yestodayOut;//提现明细筛选popupwindow昨天按钮
    private RadioButton sevenOut;//提现明细筛选popupwindow七天按钮
    private ArrayList<RadioButton> radioButtonOUts = new ArrayList<>();
    private RadioButton allTypeOut;
    private RadioButton loseOut;
    private RadioButton ingOut;
    private RadioButton succeseOut;
    private Button chooseOut;
    private Date startOut;
    private Date endOut;
    private int pagenumOut = 1;
    private int pageSizeOut = 15;
    private String stutasOut;
//
    private View inflateIn;
    private RadioButton todayIn;//提现明细筛选popupwindow今天按钮
    private RadioButton yestodayIn;//提现明细筛选popupwindow昨天按钮
    private RadioButton sevenIn;//提现明细筛选popupwindow七天按钮
    private ArrayList<RadioButton> radioButtonIns = new ArrayList<>();
    private RadioButton allTypeIn;
    private RadioButton loseIn;
    private RadioButton ingIn;
    private RadioButton succeseIn;
    private Button chooseIn;
    private Date startIn;
    private Date endIn;
    private int pagenumIn = 1;
    private int pageSizeIn = 15;
    private String stutasIn;
    private RefreshLayout refreshLayout;//下拉刷新 上拉加载 控件
    private TextView nothing; //请求数据为空时,顶部显示的textView
    private int loadAllSize;//用来判断上拉加载有无数据的变量
    private int loadOutlSize;
    private int loadInSize;
    public ViewPager viewPager;
    private int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_deal);
        bindView();
        initView();
//        ProgressDialogUtil.show(this);
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        layoutInflater = LayoutInflater.from(this);//布局管理器(poppupwindow需要用到)
        wrapLinear = findViewById(R.id.linear_deal);//popupwindow定位控件
        chooseLinearAll = findViewById(R.id.choose_linear_deal1);//右上角的筛选按钮(全部类型)
        chooseLinearOut = findViewById(R.id.choose_linear_deal2);//右上角的筛选按钮(提现明细)
        chooseLinearIn = findViewById(R.id.choose_linear_deal3);//右上角的筛选按钮(交易明细)
//
        /*
        全部类型Tab下的popupwindow弹出窗
         */
        inflateAll = layoutInflater.inflate(R.layout.child_bet_dealall_choose_pupopwindow, null); //筛选弹出窗视图
        recyclerViewPop = inflateAll.findViewById(R.id.child_bet_pupopwindow_recycle);//所有类型筛选popupwindow recycleView
        todayAll = inflateAll.findViewById(R.id.today);//所有类型筛选popupwindoew视图
        yestodayAll = inflateAll.findViewById(R.id.yestoday);//所有类型筛选popupwindow昨天按钮
        sevenAll = inflateAll.findViewById(R.id.this_week);//所有类型筛选popupwindow七天按钮
        chooseAll = inflateAll.findViewById(R.id.choose_button_all);//所有类型筛选popupwindow筛选按钮


          /*
        提现明细Tab下的popupwindow弹出窗
         */
        inflateOut = layoutInflater.inflate(R.layout.child_deal_choose_out_popupdows, null);
        todayOut = inflateOut.findViewById(R.id.today);//提现明细popupwindow今天按钮
        yestodayOut = inflateOut.findViewById(R.id.yestoday);//提现明细popupwindow昨天按钮
        sevenOut = inflateOut.findViewById(R.id.seven);//提现明细popupwindow七天按钮
        chooseOut = inflateOut.findViewById(R.id.choose_button_out);//提现明细popupwindow筛选按钮
        allTypeOut = inflateOut.findViewById(R.id.all_out);//全部类型按钮
        loseOut = inflateOut.findViewById(R.id.lose_out);//提现失败按钮
        ingOut = inflateOut.findViewById(R.id.ing_out);//申请中按钮
        succeseOut = inflateOut.findViewById(R.id.succese_out);//提现成功按钮
        //添加到容器,用于初始化按钮的选中状态
        radioButtonOUts.add(allTypeOut);
        radioButtonOUts.add(loseOut);
        radioButtonOUts.add(ingOut);
        radioButtonOUts.add(succeseOut);

                /*
        充值明细Tab下的popupwindow弹出窗
         */
        inflateIn = layoutInflater.inflate(R.layout.child_deal_choose_in_popupdows, null);
        todayIn = inflateIn.findViewById(R.id.today);
        yestodayIn = inflateIn.findViewById(R.id.yestoday);
        sevenIn = inflateIn.findViewById(R.id.seven);
        ingIn = inflateIn.findViewById(R.id.ing_in);
        loseIn = inflateIn.findViewById(R.id.lose_in);
        succeseIn = inflateIn.findViewById(R.id.succese_in);
        allTypeIn = inflateIn.findViewById(R.id.all_in);
        chooseIn = inflateIn.findViewById(R.id.choose_button_in);
        radioButtonIns.add(ingIn);
        radioButtonIns.add(loseIn);
        radioButtonIns.add(succeseIn);
        radioButtonIns.add(allTypeIn);

       /*
       绑定点击事件
        */

        chooseLinearAll.setOnClickListener(this);
        chooseLinearOut.setOnClickListener(this);
        chooseLinearIn.setOnClickListener(this);
        /*
        全部类型popupwindow点击事件
         */
        chooseAll.setOnClickListener(this);
        todayAll.setOnClickListener(this);
        yestodayAll.setOnClickListener(this);
        sevenAll.setOnClickListener(this);
        todayAll.performClick();
          /*
        提现明细popupwindow点击事件
         */
        chooseOut.setOnClickListener(this);
        todayOut.setOnClickListener(this);
        yestodayOut.setOnClickListener(this);
        sevenOut.setOnClickListener(this);
        allTypeOut.setOnClickListener(this);
        loseOut.setOnClickListener(this);
        ingOut.setOnClickListener(this);
        succeseOut.setOnClickListener(this);
        todayOut.performClick();
        allTypeOut.performClick();
        /*
        充值明细点击事件
         */
        chooseIn.setOnClickListener(this);
        todayIn.setOnClickListener(this);
        yestodayIn.setOnClickListener(this);
        sevenIn.setOnClickListener(this);
        allTypeIn.setOnClickListener(this);
        ingIn.setOnClickListener(this);
        loseIn.setOnClickListener(this);
        succeseIn.setOnClickListener(this);
        todayIn.performClick();
        allTypeIn.performClick();
        back = findViewById(R.id.mine_deal_return);//返回键
        back.setOnClickListener(this);
        viewPager=findViewById(R.id.mViewpager);
    }

    public void initView() {
         /*
        tab 导航栏相关配置
         */
        titles.add(Utils.getString(R.string.所有类型));
        titles.add(Utils.getString(R.string.提现明细));
        titles.add(Utils.getString(R.string.充值明细));
        mtab = findViewById(R.id.mtab);
        for (int i = 0; i < titles.size(); i++) {
            mtab.addTab(mtab.newTab());
        }
        for (int i = 0; i < titles.size(); i++) {
            mtab.getTabAt(i).setText(titles.get(i));
        }
        mtab.addOnTabSelectedListener(this);
//        ArrayList<SoftReference<Fragment>> fragmentArrayList = new ArrayList<>();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            MineDealFragment  mineDealFragment = new MineDealFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", titles.get(i));
            mineDealFragment.setArguments(bundle);
//            fragmentArrayList.add(new SoftReference<>(mineDealFragment));
            fragmentArrayList.add(mineDealFragment);
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);
//        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);
        viewPager.setAdapter(fragmentAdapter);
        mtab.setupWithViewPager(viewPager);

         /*
         所有类型筛选栏recycleView配置和数据处理
         */
        popupWindowAll = new PopupWindow(inflateAll, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
        popupWindowAll.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindowAll.setTouchable(true);//响应内部点击
        childBetAndDealAllPupopwindowAdapter = new ChildBetAndDealAllPupopwindowAdapter(childBetAndDealAllPops);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewPop.setLayoutManager(gridLayoutManager);
        recyclerViewPop.setAdapter(childBetAndDealAllPupopwindowAdapter);
         /*
       筛选栏 recycleView 点击事件
         */
        childBetAndDealAllPupopwindowAdapter.setOnItemClickListener(new ChildBetAndDealAllPupopwindowAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(ChildBetAndDealAllPupopwindowAdapter.MyHolder view, int position) {
                popPosition = position;
            }
        });
          /*
        请求全部类型popupwindoew中recycleView的item数据
         */
        HashMap<String, Object> dataAllType = new HashMap<>();
        dataAllType.put("isHot", "");
        Utils.docking(dataAllType, RequestUtil.REQUEST_01dh, -1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                childBetAndDealAllPops.clear();
                ChildBetAndDealAllPop allPop = new ChildBetAndDealAllPop(Utils.getString(R.string.全部类型), "", "", "");
                childBetAndDealAllPops.add(allPop);
                allPop.setStatus(1);//实现默认点击
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray zhaiyaoList = jsonObject1.getJSONArray("zhaiyaoList");
                for (int i = 0; i < zhaiyaoList.size(); i++) {
                    JSONObject jsonObject = zhaiyaoList.getJSONObject(i);
                    String type = jsonObject.getString("type");//type Tab切换和时搜索时请求接口需要传入的数据需要传入的数据
                    String typeName = jsonObject.getString("typeName");//分类的名字(radioButton显示的内容)
                    childBetAndDealAllPops.add(new ChildBetAndDealAllPop(typeName, "", "", type));
                }
                childBetAndDealAllPupopwindowAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
            }
        });

        /*
        提现明细筛选栏相关配置
         */
        popupWindowOut = new PopupWindow(inflateOut, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
        popupWindowOut.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindowOut.setTouchable(true);//响应内部点击
           /*
        充值明细筛选栏相关配置
         */
        popupWindowIn = new PopupWindow(inflateIn, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
        popupWindowIn.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindowIn.setTouchable(true);//响应内部点击

    }
    /*
    tab选中监听
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
               /*
            tab切换时,对请求接口的参数进行处理,并发送事件.并将参数发送给订阅者(fragment)
             tab切换时,使用postSticky发送粘性事件.在发送事件之后再订阅该事件,也能收到该事件
             (因为TabLayout在initView()中初始化后,会默认选中第一个tab,并发送事件,而此时fragment中尚未订阅该事件,使用普通post会导致接受不到第一次Utils.getString(R.string.所有类型)的事件(空指针异常),并且后面在第一次下拉刷新时,参数为空(请求不到数据))
             */
        if (tab.getText().equals(Utils.getString(R.string.所有类型))) {
            chooseLinearAll.setVisibility(View.VISIBLE);
            chooseLinearOut.setVisibility(View.GONE);
            chooseLinearIn.setVisibility(View.GONE);
            initAllTimeAndStutas();
            initOutStutas();
            initOutTime();
            initInStutas();
            initInTime();
            EventBus.getDefault().postSticky(new MineDealAllEven(user_id,pagenumAll,pageSizeAll,startAll,endAll,typeAll,startOut,endOut,stutasOut,startIn,endIn,stutasIn,false,true));

        } else if (tab.getText().equals(Utils.getString(R.string.提现明细))) {
            chooseLinearAll.setVisibility(View.GONE);
            chooseLinearOut.setVisibility(View.VISIBLE);
            chooseLinearIn.setVisibility(View.GONE);
            initAllTimeAndStutas();
            initOutStutas();
            initOutTime();
            initInStutas();
            initInTime();
            EventBus.getDefault().postSticky(new MineDealAllEven(user_id,pagenumAll,pageSizeAll,startAll,endAll,typeAll,startOut,endOut,stutasOut,startIn,endIn,stutasIn,false,false));
        } else {
            chooseLinearAll.setVisibility(View.GONE);
            chooseLinearOut.setVisibility(View.GONE);
            chooseLinearIn.setVisibility(View.VISIBLE);
            initAllTimeAndStutas();
            initOutStutas();
            initOutTime();
            initInStutas();
            initInTime();
            EventBus.getDefault().postSticky(new MineDealAllEven(user_id,pagenumAll,pageSizeAll,startAll,endAll,typeAll,startOut,endOut,stutasOut,startIn,endIn,stutasIn,false,false));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    private void initAllTimeAndStutas() {
        pagenumAll = 1;
        pagenumOut = 1;
        pagenumIn = 1;
        //evenBus在点击筛选按钮或者切换tab时,会先调用此方法,并发送事件, fragment接收到事件会去请求数据,所以在此方法中将refreshLayout的状态初始化,(避免上拉加载到最后,切换tab或者点击筛选按钮后,加载不出数据)
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int j = 0; j <fragments.size() ; j++) {
            Fragment fragment = fragments.get(j);
            if(fragment!=null){
                View view = fragment.getView();
                if(view!=null){
                    refreshLayout= view.findViewById(R.id.refreshLayout);
                    refreshLayout.resetNoMoreData();
                }
            }
        }
        if (todayAll.isChecked()) {
            Date date = new Date();
            startAll = date;
            endAll = date;
        } else if (yestodayAll.isChecked()) {
            Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());
            Date startTime = DateUtil.getStartTime(yesterdayCalendar);
            Date endTime = DateUtil.getEndTime(yesterdayCalendar);
            startAll = startTime;
            endAll = endTime;
            String format = simpleDateFormat.format(startTime);
            String edsds = simpleDateFormat.format(endTime);
        } else if (sevenAll.isChecked()) {
            Date dateBefore = DateUtil.getDateBefore(new Date(), 6);
            String format = simpleDateFormat.format(dateBefore);
            startAll = dateBefore;
            endAll = new Date();
        }
        if(i==1||childBetAndDealAllPops.size()==0){
            typeAll="";
        }else{
        typeAll = childBetAndDealAllPops.get(popPosition).getTypoeId();
        }
        i++;
    }

    /*
    提现明细status相关初始化
    stutas 为"Utils.getString(R.string.时,表示选中了)全部类型Utils.getString(R.string.按钮
    status为)-1Utils.getString(R.string.时,表示选中了)提现失败Utils.getString(R.string.按钮
    status为)0Utils.getString(R.string.时,表示选中了)申请中Utils.getString(R.string.按钮
    status为)1Utils.getString(R.string.时,表示选中了)提现成功"按钮
    */
    private void initOutStutas() {
        if (allTypeOut.isChecked()) {
            stutasOut = "";
        } else if (loseOut.isChecked()) {
            stutasOut = "-1";
        } else if (ingOut.isChecked()) {
            stutasOut = "0";
        } else if (succeseOut.isChecked()) {
            stutasOut = "1";
        }
    }

    /*
充值明细status相关初始化
stutas 为"Utils.getString(R.string.时,表示选中了)全部类型Utils.getString(R.string.按钮
status为)0Utils.getString(R.string.时,表示选中了)充值中Utils.getString(R.string.按钮
status为)1Utils.getString(R.string.时,表示选中了)充值成功Utils.getString(R.string.按钮
status为)2Utils.getString(R.string.时,表示选中了)充值失败"按钮
*/
    private void initInStutas() {
        //每次请求时初始化每个接口的pageNum
        pagenumAll = 1;
        pagenumOut = 1;
        pagenumIn = 1;
        //evenBus在点击筛选按钮或者切换tab时,会先调用此方法,并发送事件, fragment接收到事件会去请求数据,所以在此方法中将refreshLayout的状态初始化,(避免上拉加载到最后,切换tab或者点击筛选按钮后,加载不出数据)
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int j = 0; j <fragments.size() ; j++) {
            refreshLayout=fragments.get(j).getView().findViewById(R.id.refreshLayout);
            refreshLayout.resetNoMoreData();
        }
        if (allTypeIn.isChecked()) {
            stutasIn = "";
        } else if (loseIn.isChecked()) {
            stutasIn = "2";
        } else if (ingIn.isChecked()) {
            stutasIn = "0";
        } else if (succeseIn.isChecked()) {
            stutasIn = "1";
        }
    }

    /*
提现明细startTime endTime相关初始化
 */
    private void initOutTime() {
        pagenumAll = 1;
        pagenumOut = 1;
        pagenumIn = 1;
        //evenBus在点击筛选按钮或者切换tab时,会先调用此方法,并发送事件, fragment接收到事件会去请求数据,所以在此方法中将refreshLayout的状态初始化,(避免上拉加载到最后,切换tab或者点击筛选按钮后,加载不出数据)
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int j = 0; j <fragments.size() ; j++) {
            refreshLayout=fragments.get(j).getView().findViewById(R.id.refreshLayout);
            refreshLayout.resetNoMoreData();
        }
//        refreshLayout.resetNoMoreData();//恢复没有更多数据之前的状态,当在最后一页调用完上一个完成加载,并标记为么米有更多数据时, 需要将refresh状态重新设置为还有更多数据
        if (todayOut.isChecked()) {
            Date date = new Date();
            startOut = date;
            endOut = date;
        } else if (yestodayOut.isChecked()) {
            Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());
            Date startTime = DateUtil.getStartTime(yesterdayCalendar);
            Date endTime = DateUtil.getEndTime(yesterdayCalendar);
            startOut = startTime;
            endOut = endTime;

        } else if (sevenOut.isChecked()) {
            Date dateBefore = DateUtil.getDateBefore(new Date(), 6);
            startOut = dateBefore;
            endOut = new Date();
        }
    }

    /*
    充值明细startTime endTime相关初始化
     */
    private void initInTime() {
        if (todayIn.isChecked()) {
            Date date = new Date();
            startIn = date;
            endIn = date;
        } else if (yestodayIn.isChecked()) {
            Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());
            Date startTime = DateUtil.getStartTime(yesterdayCalendar);
            Date endTime = DateUtil.getEndTime(yesterdayCalendar);
            startIn = startTime;
            endIn = endTime;

        } else if (sevenIn.isChecked()) {
            Date dateBefore = DateUtil.getDateBefore(new Date(), 6);
            startIn = dateBefore;
            endIn = new Date();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_linear_deal1:
                popupWindowAll.showAsDropDown(wrapLinear, 0, 0);
                break;
            //点击 全部类型poppupwindow底部的筛选按钮
            case R.id.choose_button_all:
//                ProgressDialogUtil.show(MineDealActivity.this);
                initAllTimeAndStutas();
                initOutStutas();
                initOutTime();
                initInStutas();
                initInTime();
                /*
                点击筛选时,对请求接口的参数进行处理,并将参数发送给订阅者(此处用的普通post)
                 */
                EventBus.getDefault().postSticky(new MineDealAllEven(user_id,pagenumAll,pageSizeAll,startAll,endAll,typeAll,startOut,endOut,stutasOut,startIn,endIn,stutasIn,false,true

                ));
                popupWindowAll.dismiss();
                break;
            //点击 弹出提现明细popupwindow
            case R.id.choose_linear_deal2:
                popupWindowOut.showAsDropDown(wrapLinear, 0, 0);
                break;
            //点击 提现明细的oppupwindow底部的筛选按钮
            case R.id.choose_button_out:
                initAllTimeAndStutas();
                initOutStutas();
                initOutTime();
                initInStutas();
                initInTime();
//                getDataOut(user_id, pagenumOut, pageSizeOut, startOut, endOut, stutasOut, false, true);
                EventBus.getDefault().postSticky(new MineDealAllEven(user_id,pagenumAll,pageSizeAll,startAll,endAll,typeAll,startOut,endOut,stutasOut,startIn,endIn,stutasIn,false,true
                ));
                popupWindowOut.dismiss();
                break;
            //清空radioButoon的点击状态(提现明细popupwindow中)
            case R.id.all_out:
                initRadioOut(allTypeOut);
                break;
            //清空radioButoon的点击状态(提现明细popupwindow中)
            case R.id.lose_out:
                initRadioOut(loseOut);
                break;
            //清空radioButoon的点击状态(提现明细popupwindow中)
            case R.id.ing_out:
                initRadioOut(ingOut);
                break;
            //清空radioButoon的点击状态(提现明细popupwindow中)
            case R.id.succese_out:
                initRadioOut(succeseOut);
                break;
            //点击弹出充值明细的popupwindow
            case R.id.choose_linear_deal3:
                popupWindowIn.showAsDropDown(wrapLinear, 0, 0);
                break;
            //点击充值明细poppupwindow的筛选按钮
            case R.id.choose_button_in:
//                ProgressDialogUtil.show(MineDealActivity.this);
                initAllTimeAndStutas();
                initOutStutas();
                initOutTime();
                initInStutas();
                initInTime();
//                getDataIn(user_id, pagenumIn, pageSizeIn, startIn, endIn, stutasIn, false, true);
                EventBus.getDefault().postSticky(new MineDealAllEven(user_id,pagenumAll,pageSizeAll,startAll,endAll,typeAll,startOut,endOut,stutasOut,startIn,endIn,stutasIn,false,true));
                popupWindowIn.dismiss();
                break;
            case R.id.all_in:
                initRadioIn(allTypeIn);
                break;
            //清空radioButoon的点击状态(提现明细popupwindow中)
            case R.id.lose_in:
                initRadioIn(loseIn);
                break;
            //清空radioButoon的点击状态(提现明细popupwindow中)
            case R.id.ing_in:
                initRadioIn(ingIn);
                break;
            //清空radioButoon的点击状态(提现明细popupwindow中)
            case R.id.succese_in:
                initRadioIn(succeseIn);
                break;
            case R.id.mine_deal_return:
                finish();
                break;
        }
    }
    //
    /*(提现明细)
    选中raidoButton时,初始化其他button的选中状态
     */
    private void initRadioOut(RadioButton radioButton) {
        for (int i = 0; i < radioButtonOUts.size(); i++) {
            radioButtonOUts.get(i).setChecked(false);
            radioButton.setChecked(true);
        }
    }
    /*(充值明细)
选中raidoButton时,初始化其他button的选中状态
*/
    private void initRadioIn(RadioButton radioButton) {
        for (int i = 0; i < radioButtonIns.size(); i++) {
            radioButtonIns.get(i).setChecked(false);
            radioButton.setChecked(true);
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}


