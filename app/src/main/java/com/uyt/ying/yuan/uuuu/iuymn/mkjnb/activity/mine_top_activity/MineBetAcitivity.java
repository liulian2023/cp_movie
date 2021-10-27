package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.MineBetEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildBetAndDealAllPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/*
投注记录
 */
public class MineBetAcitivity extends BaseActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    private TabLayout mtab;
//    private String [] titles={Utils.getString(R.string.全部),Utils.getString(R.string.已中奖),Utils.getString(R.string.未中奖),Utils.getString(R.string.待开奖),Utils.getString(R.string.已撤单)};
    private ArrayList<String> titles =new ArrayList<>();
    private LinearLayout chooseLinear;//筛选按钮
    private LinearLayout wrapLinear;//popwindoew 定位元素
    private View inflateAll;
    private PopupWindow popupWindowAll;
    private RadioButton todayAll;
    private RadioButton yestodayAll;
    private RadioButton sevenAll;
    private Button chooseAll;
    private RecyclerView recyclerViewPup;
    private LayoutInflater layoutInflater;//布局管理器
    private ChildBetAndDealAllPupopwindowAdapter childBetAndDealAllPupopwindowAdapter;//筛选栏数据适配器
    private ArrayList<ChildBetAndDealAllPop> childBetAndDealAllPops =new ArrayList<>();//所有类型筛选popupwindow 数据
    private int  PopPosition;
    private Date startDate;
    private Date endDate;
    private int pageNum=1;
    private int pageSize=15;
    private String remark;
    private TextView chooseText;//点击筛选后,把文字设置为选中的彩种名
    private BigDecimal bonusAll=BigDecimal.ZERO;
    private BigDecimal amountAll=BigDecimal.ZERO;
    private TextView back;
    private ViewPager viewPager;

    /*
    投注页面携带的数据
     */
    private String fromShopping;//投注页面携带的彩票名,跳转到此activity后,筛选栏要默认显示该彩票名
    private int type_id;//跳转到此activity后默认请求该彩票投注记录需要用到的参数
    private int game;//跳转到此activity后默认请求该彩票投注记录需要用到的参数
    private boolean isShopping=false;
    private String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_bet_acitivity);
        getIntentData();
        bindView();
        initView();
        initIsShopping();
//        ProgressDialogUtil.show(this);
    }

    private void getIntentData() {
        fromShopping = getIntent().getStringExtra("fromShopping");
        type_id = getIntent().getIntExtra("type_id",0);
        game = getIntent().getIntExtra("game",0);
        //投注页面调跳转而来,需要中奖默认选中的game type_id设置为跳转携带的game type_id
        if((type_id==0||game==0)&&!StringMyUtil.isEmptyString(fromShopping)){
        type_id = Integer.parseInt(getIntent().getStringExtra("type_id"));
        game = Integer.parseInt(getIntent().getStringExtra("game"));

        }
        if(StringMyUtil.isEmptyString(fromShopping)){
            isShopping=false;

        }else {
            isShopping=true;
        }
        time = getIntent().getStringExtra("time");
    }

    @Override
    protected void init() {

    }

    public static void startAty(Context context,String time){
        Intent intent = new Intent(context, MineBetAcitivity.class);
        intent.putExtra("time",time);
        context.startActivity(intent);
    }
    private void bindView() {
       /*
        筛选popupwindow弹出窗的按钮绑定
         */
        layoutInflater=LayoutInflater.from(this);
        inflateAll = layoutInflater.inflate(R.layout.child_bet_dealall_choose_pupopwindow, null); //筛选弹出窗视图
        recyclerViewPup =inflateAll.findViewById(R.id.child_bet_pupopwindow_recycle);//所有类型筛选popupwindow recycleView
        todayAll =inflateAll.findViewById(R.id.today);//所有类型筛选popupwindoew视图
        yestodayAll =inflateAll.findViewById(R.id.yestoday);//所有类型筛选popupwindow昨天按钮
        sevenAll =inflateAll.findViewById(R.id.this_week);//所有类型筛选popupwindow七天按钮
        chooseAll =inflateAll.findViewById(R.id.choose_button_all);//所有类型筛选popupwindow筛选按钮
        chooseText=findViewById(R.id.chooseText);
        chooseLinear=findViewById(R.id.choose_linear_bet);
        wrapLinear=findViewById(R.id.linear_bet);//popupwindow定位控件
        chooseLinear.setOnClickListener(this);
        chooseAll.setOnClickListener(this);
        todayAll.setOnClickListener(this);
        yestodayAll.setOnClickListener(this);
        sevenAll.setOnClickListener(this);
        if(StringMyUtil.isEmptyString(time)||time.equals(CommonStr.TODAY)){
            todayAll.performClick();
        }else if(time.equals(CommonStr.YESTERDAY)){
            yestodayAll.performClick();
        }else {
            sevenAll.performClick();
        }
        back=findViewById(R.id.mine_bet_return);
        back.setOnClickListener(this);
        viewPager=findViewById(R.id.mViewpager);
    }


        /*
        TabLayout viewPager 相关设置
         */
    public void initView() {
        mtab=findViewById(R.id.mtab);
        titles.add(Utils.getString(R.string.全部));
        titles.add(Utils.getString(R.string.已中奖));
        titles.add(Utils.getString(R.string.未中奖));
        titles.add(Utils.getString(R.string.待开奖));
        titles.add(Utils.getString(R.string.已撤单));
        for (int i = 0; i < titles.size(); i++) {
            mtab.addTab(mtab.newTab());//添加tab
        }
        for (int i = 0; i < titles.size(); i++) {
            mtab.getTabAt(i).setText(titles.get(i));//设置tab名
        }
        mtab.addOnTabSelectedListener(this);//tab选中监听

//        ArrayList<SoftReference<Fragment>> fragmentArrayList =new ArrayList<>();
        ArrayList<Fragment> fragmentArrayList =new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("tabName",titles.get(i));//fragmnet中根据tabName判断需要请求什么数据
            MineBetFragment mineBetFragment = new MineBetFragment();
            mineBetFragment.setArguments(bundle);
            fragmentArrayList.add(mineBetFragment);
        }
//        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);//viewPager适配器
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);//viewPager适配器
        viewPager.setAdapter(fragmentAdapter);
        mtab.setupWithViewPager(viewPager);//viewPager和fragment的绑定
             /*
         筛选弹出框recycleView配置和数据处理
         */
        popupWindowAll = new PopupWindow(inflateAll, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
        popupWindowAll.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindowAll.setTouchable(true);//响应内部点击
        childBetAndDealAllPupopwindowAdapter = new ChildBetAndDealAllPupopwindowAdapter(childBetAndDealAllPops);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewPup.setLayoutManager(gridLayoutManager);
        recyclerViewPup.setAdapter(childBetAndDealAllPupopwindowAdapter);

        /*
        获取筛选栏数据
         */

        Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, -1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                childBetAndDealAllPops.clear();
                ChildBetAndDealAllPop allPop = new ChildBetAndDealAllPop(Utils.getString(R.string.全部彩种), "", 0+"", 0+"");
                childBetAndDealAllPops.add(allPop);

                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray gameClassList = jsonObject1.getJSONArray("gameClassList");
                for (int i = 0; i < gameClassList.size(); i++) {
                    JSONObject jsonObject = gameClassList.getJSONObject(i);
                    String game = jsonObject.getString("game");//大分类
                    String type_id = jsonObject.getString("type_id");//二级分类
                    String typename = jsonObject.getString("typename");//彩票名称
                    childBetAndDealAllPops.add(new ChildBetAndDealAllPop(typename,"",game,type_id));
                }
                if(StringMyUtil.isEmptyString(fromShopping)){//如果从购彩页面传入的值为空,说明不是购彩页面跳转而来的,此时默认选中全部彩种按钮
                    allPop.setStatus(1);//实现默认点击
                    isShopping=false;
                }else{//不为空则默认点击传入的typeName所属的radioButton(并且将isShopping设置为true,第一次下拉刷新时需要根据isShoppng判断需要刷新的数据)
                    isShopping=true;
                    for (int i = 0; i < childBetAndDealAllPops.size(); i++) {
                        ChildBetAndDealAllPop childBetAndDealAllPop = childBetAndDealAllPops.get(i);
                        if(childBetAndDealAllPop.getName().equals(fromShopping)){
                            childBetAndDealAllPop.setStatus(1);//默认点击传入的typeName
                            chooseText.setText(fromShopping);
                        }
                    }
                }
                    childBetAndDealAllPupopwindowAdapter.notifyDataSetChanged();
            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });
        childBetAndDealAllPupopwindowAdapter.setOnItemClickListener(new ChildBetAndDealAllPupopwindowAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(ChildBetAndDealAllPupopwindowAdapter.MyHolder view, int position) {
                PopPosition=position;
                //在此处将isShopping设置为false,即点击筛选按钮重新选择彩票后,,下拉刷新时和切换tab时将不再默认请求购彩界面传入的彩票投注记录
                isShopping=false;
                if(position==0){
                    chooseText.setText(Utils.getString(R.string.全部彩种));
                }else{
                    chooseText.setText(childBetAndDealAllPops.get(position).getName());
                }
            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
         initIsShopping();// 判断是否要将typeId和game设置为购彩页面传入的值
        if(tab.getText().equals(Utils.getString(R.string.全部))){
            pageNum=1;
            remark="";
            initTime();
            EventBus.getDefault().postSticky(new MineBetEvenModel(startDate,endDate,pageNum,pageSize,2,type_id+"",game+"",remark,false,true));
        }
        else if(tab.getText().equals(Utils.getString(R.string.已中奖))){
            pageNum=1;
            remark=Utils.getString(R.string.中奖);
            initTime();
            EventBus.getDefault().postSticky(new MineBetEvenModel(startDate,endDate,pageNum,pageSize,2,type_id+"",game+"",remark,false,false));
//            getMineBet(startDate,endDate,pageNum+"",pageSize+"",2, type_id,game,remark,false);
        }
        else if(tab.getText().equals(Utils.getString(R.string.未中奖))){
            pageNum=1;
            remark=Utils.getString(R.string.未中);
            initTime();
            EventBus.getDefault().postSticky(new MineBetEvenModel(startDate,endDate,pageNum,pageSize,2,type_id+"",game+"",remark,false,false));
//            getMineBet(startDate,endDate,pageNum+"",pageSize+"",2, type_id,game,remark,false);
        }
        else if(tab.getText().equals(Utils.getString(R.string.待开奖))){
            pageNum=1;
            remark=Utils.getString(R.string.等待开奖);
            initTime();
            EventBus.getDefault().postSticky(new MineBetEvenModel(startDate,endDate,pageNum,pageSize,2,type_id+"",game+"",remark,false,false));
//            getMineBet(startDate,endDate,pageNum+"",pageSize+"",2, type_id,game,remark,false);
        }   else if(tab.getText().equals(Utils.getString(R.string.已撤单))){
            pageNum=1;
            remark=Utils.getString(R.string.撤单);
            initTime();
            EventBus.getDefault().postSticky(new MineBetEvenModel(startDate,endDate,pageNum,pageSize,2,type_id+"",game+"",remark,false,false));
//            getMineBet(startDate,endDate,pageNum+"",pageSize+"",2, type_id,game,remark,false);
        }
    }
        /*
        判断是否要将typeId和game设置为购彩页面传入的值
        isShopping的值在点击筛选栏的彩票radioButton后被重置为false
        即用户点击一次radioButton后,将不再请求购彩页面传入的彩票购彩记录
         */
    private void initIsShopping() {
        if(isShopping){//从投注页面跳转而来,默认请求投注页面携带的彩票投注记录
            type_id = getIntent().getIntExtra("type_id",0);
            game = getIntent().getIntExtra("game",0);
            if(type_id==0||game==0){
                type_id = Integer.parseInt(getIntent().getStringExtra("type_id"));
                game = Integer.parseInt(getIntent().getStringExtra("game"));
            }
        }else{//不是从投注页面跳转而来, 请求popWindow选中的彩票投注记录
            if(childBetAndDealAllPops.size()!=0){
                type_id = Integer.parseInt(childBetAndDealAllPops.get(PopPosition).getTypoeId());
                game = Integer.parseInt(childBetAndDealAllPops.get(PopPosition).getGame());
            }

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_linear_bet:
                popupWindowAll.showAsDropDown(wrapLinear,0,0);
                break;
            case R.id.choose_button_all:
//                ProgressDialogUtil.show(MineBetAcitivity.this);
                initTime();
                initIsShopping();
                EventBus.getDefault().postSticky(new MineBetEvenModel(startDate,endDate,pageNum,pageSize,2,type_id+"",game+"",remark,false,true));
            popupWindowAll.dismiss();
            break;
            case R.id.mine_bet_return:
                finish();
                break;
        }
    }
    private void initTime() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int j = 0; j <fragments.size() ; j++) {
            Fragment fragment = fragments.get(j);
            if(fragment!=null){
                try {
                    RefreshLayout refreshLayout= fragment.getView().findViewById(R.id.refreshLayout);
                    refreshLayout.resetNoMoreData();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        if(todayAll.isChecked()){
            Date start = new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(MineBetAcitivity.this, "shijiancha", 0l));
            Date end =  new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(MineBetAcitivity.this, "shijiancha", 0l));
            startDate=start;
            endDate=end;
        }
        else if(yestodayAll.isChecked()){
            Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(MineBetAcitivity.this, "shijiancha", 0l)));//得到昨天的当前时间
            Date startTime = DateUtil.getStartTime(yesterdayCalendar);//得到昨天的开始时间(00:00:00 )
            Date endTime = DateUtil.getEndTime(yesterdayCalendar);//得到昨天的结束时间(23:59:59)
            startDate=startTime;
            endDate=endTime;

        }
        else if(sevenAll.isChecked()){
            Date start = DateUtil.getDateBefore(new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(MineBetAcitivity.this, "shijiancha", 0l)), 6);//得到7天前的当前时间
            Calendar todayCalendar = DateUtil.getTodayCalendar(start);//得到7天前时间的Calendar对象
            Date startTime = DateUtil.getStartTime(todayCalendar);//得到7天前的开始时间(00:00:00)
            Date end = new Date(System.currentTimeMillis()-SharePreferencesUtil.getLong(MineBetAcitivity.this, "shijiancha", 0l));//当前时间为结束时间
            startDate=startTime;
            endDate=end;
        }
    }
    /*
    点击投注详情,进行撤单操作后,返回此activity需要更新注单的状态
     */
    @Override
    protected void onRestart() {
//        getMineBet(startDate,endDate,pageNum+"",pageSize+"",2, type_id,game,remark,false);
//        EventBus.getDefault().postSticky(new MineBetEvenModel(startDate,endDate,pageNum,pageSize,2,type_id+"",game+"",remark,false));
        super.onRestart();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
