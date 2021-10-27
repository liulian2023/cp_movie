package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.RakeBackRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RakeBackModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ChooseTypePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RakeBackActivity extends BaseActivity implements View.OnClickListener {
    private TextView actionCenter;
    private TextView timeLeftTextView;//左边的时间选择
    private TextView timeRightTextView;//右边的时间选择
//    private TextView nothing;//没有数据时的顶部提示控件
    private LinearLayout nothing;//没有数据时的顶部提示控件
    private TextView priceText;//返佣收益textView
    private TextView action_bar_right;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//请求接口时的格式
    private SimpleDateFormat sdfShow = new SimpleDateFormat("yyyy-MM-dd");
    private TextView back;//返回键
    private   Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
    private int pageNo=1;
    private int pageSize=15;
    private String startDate/*=sdf.format(DateUtil.getDateBefore(new Date(),7))*/;
    private String endDate/*=sdf.format(new Date())*/;
    private ArrayList<RakeBackModel> rakeBackModelArrayList = new ArrayList<>();//recycleView数据容器
    //    private ArrayList<RakeBackModel> refreshLoadMoreList =new ArrayList<>();
    private int dataSize;//判断上拉加载是否有数据
    private RecyclerView recyclerView;
    private RakeBackRecycleAdapter rakeBackRecycleAdapter;
    private RefreshLayout refreshLayout;//上拉刷新控件
    private Date selectedDateLeft;
    private Date selectedDateRight;
    private Calendar mixDate;
    private Calendar maxDate ;
    private ConstraintLayout loaningLinear;
    private String appName;
    ChooseTypePop chooseTypePop;
    String type ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rake_back);
        appName=SharePreferencesUtil.getString(RakeBackActivity.this,"appName","");
        mixDate=Calendar.getInstance();
        maxDate=Calendar.getInstance();
        initTime();
        bindView();
        initRecycleView();
        initView();
        initRefreshLoadMore();//上拉 下拉监听
    }

    @Override
    protected void init() {

    }

    private void initTime() {
        /*
       默认请求的数据
         */
        Date dayStartTime = null;
        //盈众申博默认请求7天的数据
        if(appName.equals(getString(R.string.盈众彩票))||appName.equals(getString(R.string.申博国际))){
            Date dateBefore = DateUtil.getDateBefore(new Date(), 7);//得到7天前的时间
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateBefore);
            dayStartTime = DateUtil.getStartTime(instance);//得到七天前的开始时间(00:00:00)
            Calendar calendarMix = Calendar.getInstance();
            calendarMix.setTime(dateBefore);
            mixDate.set(calendarMix.get(Calendar.YEAR),calendarMix.get(Calendar.MONTH),calendarMix.get(Calendar.DAY_OF_MONTH));

        }
        //其他平台默认请求月初到当天的数据
        else {
            Date monthBegin = DateUtil.getMonthBegin(new Date());//月初的时间
            Calendar instance = Calendar.getInstance();
            instance.setTime(monthBegin);
            dayStartTime = DateUtil.getStartTime(instance);//月初的开始时间
            mixDate.set(instance.get(Calendar.YEAR)-100,0,30);
        }
//        Calendar todayCalendar = DateUtil.getTodayCalendar(dateBefore);//得到7天前的calendar对象
//        Date startTime = DateUtil.getMonthBegin(new Date());//月初的时间
        selectedDateLeft =dayStartTime;//第一次进入时,将左侧选中的时间设置为七天前
        Calendar calendarMax = Calendar.getInstance();
        calendarMax.setTime(new Date());
        maxDate.set(calendarMax.get(Calendar.YEAR),calendarMax.get(Calendar.MONTH),calendarMax.get(Calendar.DAY_OF_MONTH));

        startDate=sdf.format(selectedDateLeft);//请求数据时的开始时间
        Calendar todayCalendar1 = DateUtil.getTodayCalendar(new Date());//得到当天的calendar对象
        Date endTime = DateUtil.getEndTime(todayCalendar1);//得到当天的结束时间(23:59:59)
        selectedDateRight =new Date();//第一次进入时,将右侧选中的时间设置为当前时间
        endDate=sdf.format(endTime);//请求数据时的结束时间
    }
    /*
    上拉加载 下拉刷新配置相关
     */
    private void initRefreshLoadMore() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//数据未满一页时,禁用上拉加载
        refreshLoadMoreListener();
    }
    /*
    上拉加载 下拉刷新的监听事件
     */
    private void refreshLoadMoreListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo=1;
                getChildCommission(user_id,pageNo+"",pageSize+"",startDate,endDate,false,false);
                refreshLayout.finishRefresh();
            }
        })  ;
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                getChildCommission(user_id,pageNo+"",pageSize+"",startDate,endDate,true,false);

                refreshLayout.finishLoadMore();
            }
        });
    }
    /*
    recycleView相关配置
     */
    private void initRecycleView() {
        recyclerView=findViewById(R.id.rake_back_recycle);
        rakeBackRecycleAdapter = new RakeBackRecycleAdapter(rakeBackModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rakeBackRecycleAdapter);
    }
    /*
   绑定控件
     */
    private void bindView() {
        loaningLinear=findViewById(R.id.loading_linear);
        actionCenter=findViewById(R.id.action_bar_text);
        actionCenter.setText(Utils.getString(R.string.佣金明细));
        timeLeftTextView=findViewById(R.id.time_left);
        action_bar_right=findViewById(R.id.action_bar_right);
        action_bar_right.setText(Utils.getString(R.string.全部));
        action_bar_right.setVisibility(View.VISIBLE);
        timeRightTextView=findViewById(R.id.time_right);
        nothing=findViewById(R.id.nodata_linear);
        priceText=findViewById(R.id.price_num);
        refreshLayout=findViewById(R.id.refreshLayout);
        back=findViewById(R.id.action_bar_return);
        back.setOnClickListener(this);
        timeLeftTextView.setOnClickListener(this);
        timeRightTextView.setOnClickListener(this);
        action_bar_right.setOnClickListener(this);
    }

    public void initView() {
        if(appName.equals(getString(R.string.盈众彩票))||appName.equals(getString(R.string.申博国际))){
            timeLeftTextView.setText( sdfShow.format( DateUtil.getDateBefore(new Date(), 7)));

        }else {
            timeLeftTextView.setText( sdfShow.format( DateUtil.getMonthBegin(new Date())));
        }
        timeRightTextView.setText(sdfShow.format(new Date()));
        /*
        默认请求本月1号到当前时间的数据
         */
        getChildCommission(user_id,pageNo+"",pageSize+"",startDate,endDate,false,true);
    }
    /**
     * 请求接口数据
     * @param user_id   id
     * @param pageNo    请求的页数
     * @param pageSize  每页的数据条数
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param isLoad   是否是上拉加载时的调用
     */
    private void getChildCommission(Long user_id, String pageNo, String pageSize, String startDate, String endDate, final Boolean isLoad,boolean showLoad) {

//        ProgressDialogUtil.show(RakeBackActivity.this);
        if(showLoad){
            nothing.setVisibility(View.GONE);
            loaningLinear.setVisibility(View.VISIBLE);
        }
        String token = SharePreferencesUtil.getString(RakeBackActivity.this, "token", "");
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        data.put("pageNo",pageNo);
        data.put("pageSize",pageSize);
        data.put("startDate",startDate);
        data.put("endDate",endDate);
        data.put("source",2);
        data.put("token",token);
        if(StringMyUtil.isNotEmpty(type)){
            data.put("type",type);
        }
        Utils.docking(data, RequestUtil.REQUEST_24rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
//                ProgressDialogUtil.stop(RakeBackActivity.this);
                loaningLinear.setVisibility(View.GONE);
                if(!isLoad){
                    rakeBackModelArrayList.clear();
                }
//                refreshLoadMoreList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                Long totalSize = jsonObject1.getLong("totalSize");
                if(totalSize==0){
                    if(!isLoad){
                        nothing.setVisibility(View.VISIBLE);
                    }else{
                        nothing.setVisibility(View.GONE);
                    }
                    if(refreshLayout!=null){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
                JSONArray childCommissionList = jsonObject1.getJSONArray("childCommissionList");
                int size = childCommissionList.size();
                dataSize=size;
                BigDecimal totalCommission = jsonObject1.getBigDecimal("totalCommission");
                priceText.setText(totalCommission+"");
                for (int i=0;i<childCommissionList.size();i++) {
                    JSONObject jsonObject = childCommissionList.getJSONObject(i);
                    String remark = jsonObject.getString("remark");
                    String createdDate = jsonObject.getString("createdDate");
                    BigDecimal price = jsonObject.getBigDecimal("price");
                    rakeBackModelArrayList.add(new RakeBackModel(remark,createdDate,price+Utils.getString(R.string.元)));
//                    refreshLoadMoreList.add(new RakeBackModel(remark,createdDate,price+Utils.getString(R.string.元)));
                }
                rakeBackRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
//                ProgressDialogUtil.stop(RakeBackActivity.this);
                loaningLinear.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.time_left:
//                showPickerViewTimeLeft();
                showPickerTimeLeft();
                break;
            case R.id.time_right:
//                showPickerViewTimeRight();
                showPickerTimeRight();
                break;
            case R.id.action_bar_return:
                finish();
                break;
            case R.id.action_bar_right:
                initChooseTypePop();
                chooseTypePop.showAsDropDown(action_bar_right,0,0,Gravity.CENTER);
                ProgressDialogUtil.darkenBackground(RakeBackActivity.this,0.7f);
                break;
        }
    }

    private void initChooseTypePop() {
            if(chooseTypePop == null){
                chooseTypePop = new ChooseTypePop(this,false);
                chooseTypePop.setOnPopClickListener(new BasePopupWindow2.OnPopClickListener() {
                    @Override
                    public void onPopClick(View view) {
                        switch (view.getId()){
                            case R.id.income_tv:
                                type = "0";
                                action_bar_right.setText(Utils.getString(R.string.收入));
                                break;
                            case R.id.all_type_tv:
                                type = "";
                                action_bar_right.setText(Utils.getString(R.string.全部));
                                break;
                            case R.id.expenditure_tv:
                                type="1";
                                action_bar_right.setText(Utils.getString(R.string.支出));
                                break;
                            default:
                                break;
                        }
                        pageNo=1;
                        getChildCommission(user_id,pageNo+"",pageSize+"",startDate,endDate,false,true);
                        chooseTypePop.dismiss();
                    }
                });
            }
    }

    private void showPickerTimeLeft(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDateLeft);
        TimePickerView timePickerView= new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                pageNo=1;
                refreshLayout.resetNoMoreData();
                selectedDateLeft =date;
                Calendar todayCalendar = DateUtil.getTodayCalendar(date);
                Date startTime = DateUtil.getStartTime(todayCalendar);
                startDate=sdf.format(startTime);
                timeLeftTextView.setText(sdfShow.format(startTime));
                getChildCommission(user_id,pageNo+"",pageSize+"",startDate,endDate,false,true);
            }
        })
                .setCancelText(Utils.getString(R.string.取消))//取消按钮文字
                .setSubmitText(Utils.getString(R.string.确定))//确认按钮文字
                .setTextXOffset(10,10,10,10,10,10)
//                        .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText(Utils.getString(R.string.开始时间))//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(Color.parseColor("#e6351a"))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(mixDate,maxDate)//起始终止年月日设定
                .setLabel(Utils.getString(R.string.年),Utils.getString(R.string.月),Utils.getString(R.string.日),Utils.getString(R.string.时),Utils.getString(R.string.分),Utils.getString(R.string.秒))//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDecorView(findViewById(R.id.content))
                .setDate(calendar)
                .build();
        timePickerView.show();
    }
    /*
    开始时间的时间选择器
     */
    /*private void showPickerViewTimeLeft() {
        com.airsaid.pickerviewlibrary. TimePickerView mTimePickerView = new  com.airsaid.pickerviewlibrary.TimePickerView(this, com.airsaid.pickerviewlibrary. TimePickerView.Type.YEAR_MONTH_DAY);
        // 设置点击外部是否消失
        mTimePickerView.setCancelable(true);
        // 设置滚轮字体大小
        mTimePickerView.setTextSize(5f);
        // 设置标题
//        mTimePickerView.setTitle(Utils.getString(R.string.设置生日));
        // 设置取消文字
        mTimePickerView.setCancelText(Utils.getString(R.string.取消));
        // 设置取消文字颜色
        mTimePickerView.setCancelTextColor(Color.WHITE);
        // 设置取消文字大小
        mTimePickerView.setCancelTextSize(15f);
        // 设置确定文字
        mTimePickerView.setSubmitText(Utils.getString(R.string.确定));
        // 设置确定文字颜色
        mTimePickerView.setSubmitTextColor(Color.WHITE);
        // 设置确定文字大小
        mTimePickerView.setSubmitTextSize(15f);
        // 设置头部背景
        mTimePickerView.setHeadBackgroundColor(Color.parseColor("#e6351a"));
        //设置选中时间
        mTimePickerView.setTime(selectedDateLeft);//selectedDateLeft 在第一次进入 和每次选择时间后赋值
        mTimePickerView.setOnTimeSelectListener(new  com.airsaid.pickerviewlibrary.TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                pageNo=1;
                refreshLayout.resetNoMoreData();
                selectedDateLeft=date;
                Calendar todayCalendar = DateUtil.getTodayCalendar(date);
                Date startTime = DateUtil.getStartTime(todayCalendar);
                startDate=sdf.format(startTime);
                timeLeftTextView.setText(sdfShow.format(startTime));
                getChildCommission(user_id,pageNo+"",pageSize+"",startDate,endDate,false);
            }
        });
        mTimePickerView.show();
    }*/

    private void showPickerTimeRight(){
        //每次点击右侧时间选择都配置默认选中时间(默认选中时间需要传入当天开始时间的Calendar对象)
        Calendar calendar = Calendar.getInstance();
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(selectedDateRight);
        calendar.setTime(DateUtil.getStartTime(calendarStart));
        String format = sdf.format(selectedDateRight);
//        calendarEnd.setTime(selectedDateRight);
//        calendar.setTime(DateUtil.getEndTime(calendarEnd));
        com.bigkoo.pickerview.view.TimePickerView  timePickerView =new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
   /*
                因为每次选择都要请求接口,所以初始化pageNo 和refreLaout的加载状态
                每次选择时间,都把选择的时间以接口要求的格式赋值给结束时间
                 */
                pageNo=1;
                refreshLayout.resetNoMoreData();
                selectedDateRight =date;
                Calendar todayCalendar = DateUtil.getTodayCalendar(date);
                Date endTime = DateUtil.getEndTime(todayCalendar);//当天的结束时间
                endDate=sdf.format(endTime);
                timeRightTextView.setText(sdfShow.format(endTime));
                getChildCommission(user_id,pageNo+"",pageSize+"",startDate,endDate,false,true);
            }
        })
                .setCancelText(Utils.getString(R.string.取消))//取消按钮文字
                .setSubmitText(Utils.getString(R.string.确定))//确认按钮文字
                .setTextXOffset(10,10,10,10,10,10)
//                        .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText(Utils.getString(R.string.结束时间))//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(Color.parseColor("#e6351a"))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(mixDate,maxDate)//起始终止年月日设定
                .setLabel(Utils.getString(R.string.年),Utils.getString(R.string.月),Utils.getString(R.string.日),Utils.getString(R.string.时),Utils.getString(R.string.分),Utils.getString(R.string.秒))//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDecorView( findViewById(R.id.content))//设置pickView需要显示的父元素(解决pickView遮盖虚拟按键的问题)
                .setDate(calendar)
                .build();
        timePickerView.show();

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
