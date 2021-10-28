package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.agent_journaling_fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter.AgentJournalingActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ChildJournalingFragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.ShowByLotteryRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChildJournaling;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShowByLotteryType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
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

import okhttp3.Headers;

public class ChildJournalingFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<ChildJournaling>childJournalingArrayList =new ArrayList<>();//下级报表列表数据
    private ArrayList<ShowByLotteryType>showByLotteryTypeArrayList =new ArrayList<>();//按彩种查看列表数据
    private ArrayList<String>nickNameList =new ArrayList<>();//下级昵称列表(用于判断输入的下级昵称是否存在)
    private RecyclerView recyclerView;//下级代理列表recycleView
    private ChildJournalingFragmentAdapter childJournalingFragmentAdapter;//下级代理recycle适配器
    private ShowByLotteryRecycleAdapter showByLotteryRecycleAdapter;//按彩种查看recycle适配器
    private View inflateChild;//筛选弹出框视图
    private PopupWindow popupWindowChild;//筛选弹窗布局
    private LinearLayout actionBar;
    private LinearLayout chooseLinear;//用于popupwindow定位的控件
    private RadioButton today;//今日筛选按钮
    private RadioButton yestoday;//昨日筛选按钮
    private RadioButton seven;//七天筛选按钮
    private RadioButton touzhu;//投注筛选按钮
    private RadioButton yinkui;//盈亏筛选按钮
    private RadioButton chongzhi;//充值筛选按钮
    private RadioButton fanyong;//返佣筛选按钮
    private RadioButton shengxu;//升序筛选按钮
    private RadioButton jiangxu;//降序筛选按钮
    private Button chooseButton;//筛选按钮
    private EditText searchEdit;//输入框内容
    private  TextView searchText;
    private  Date start;//请求数据时的开始时间
    private  Date end;//请求数据时的结束时间
    private  String orderBy;//请求数据时的排序方式
    private int pageNum=1;
    private int pageSize=15;
    private TextView cz;//下级详情报表的充值
    private TextView tx;
    private TextView tz;
    private TextView zj;
    private TextView gryj;
    private TextView hdfd;
    private TextView gryk;
    private TextView teamTable;//下级报表详情的Utils.getString(R.string.他的团队报表)按钮
    private TextView lettoryShow;//下级报表详情的Utils.getString(R.string.按彩种查看)按钮
    private LinearLayout linearLayoutParrent;
    private RefreshLayout refreshLayout;
    private LinearLayout nothing;
    private int dataSize;
    private ConstraintLayout loadingLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;
    private boolean isShowLoad=false;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.child_journaling_fragment,container,false);
        initView();
        bindView(view);
        initRecycle(view);
        initRefresh();
        return view;
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",true,isShowLoad);
    }

    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initOrder( false);
//                getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",false);
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
               pageNum++;
//                initOrder();
               getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",true,false);

                refreshLayout.finishLoadMore();
            }
        });

    }

    public void initView() {
         /*
        筛选弹出窗相关配置(下级报表)
         */
        LayoutInflater inflater = LayoutInflater.from(getContext());//布局管理器
        inflateChild = inflater.inflate(R.layout.child_journaling_popupwindow, null);//筛选弹出窗视图
        popupWindowChild = new PopupWindow(inflateChild, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
        popupWindowChild.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        popupWindowChild.setTouchable(true);//响应内部点击
        popupWindowChild.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(getActivity(),1f);
            }
        });
    }



    private void bindView(View view) {
        errorLinear=view.findViewById(R.id.error_linear);
        reloadTv=view.findViewById(R.id.reload_tv);
        reloadTv.setOnClickListener(this);
        loadingLinear=view.findViewById(R.id.loading_linear);
        chooseLinear=getActivity().findViewById(R.id.choose_linear2);
        actionBar=getActivity().findViewById(R.id.linear);//筛选弹出框的定位元素
        searchEdit =view.findViewById(R.id.search_edit);
        today =inflateChild.findViewById(R.id.today);
        yestoday =inflateChild.findViewById(R.id.yestoday);
        seven =inflateChild.findViewById(R.id.seven);
        touzhu =inflateChild.findViewById(R.id.touzhu);
        yinkui =inflateChild.findViewById(R.id.yinkui);
        chongzhi =inflateChild.findViewById(R.id.chongzhi);
        fanyong =inflateChild.findViewById(R.id.fanyong);
        shengxu =inflateChild.findViewById(R.id.shengxu);
        jiangxu =inflateChild.findViewById(R.id.jiangxu);
        chooseButton =inflateChild.findViewById(R.id.choose_button);
        searchText =view.findViewById(R.id.search_text);
        refreshLayout=view.findViewById(R.id.refresh);
        nothing=view.findViewById(R.id.nodata_linear);
        chooseLinear.setOnClickListener(this);
        chooseButton.setOnClickListener(this);
        today.setOnClickListener(this);
        today.performClick();
        seven.setOnClickListener(this);
        touzhu.setOnClickListener(this);
        touzhu.performClick();
        yinkui.setOnClickListener(this);
        chongzhi.setOnClickListener(this);
        fanyong.setOnClickListener(this);
        shengxu.setOnClickListener(this);
        jiangxu.setOnClickListener(this);
        jiangxu.performClick();
        searchText.setOnClickListener(this);;
    }

/*
下级报表recycleView配置
 */
    private void initRecycle(View view) {
        recyclerView =  view.findViewById(R.id.child_journaling_recycle);
        childJournalingFragmentAdapter = new ChildJournalingFragmentAdapter(childJournalingArrayList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(childJournalingFragmentAdapter);
        if(start==null||end==null){
            start=new Date();end=new Date();
        }
        getChildInfo(pageNum+"",pageSize+"",start,end,"","2","0",false,true);
        //recycleView item点击事件
        childJournalingFragmentAdapter.setOnItemClickListener(new ChildJournalingFragmentAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                switch (view.getId()){
                    case R.id.big_linear:
                        teamTable=view.findViewById(R.id.team_table);
                        linearLayoutParrent =view.findViewById(R.id.lettory_show_linear);// 彩种查看的父元素(没有投注要隐藏这个控件)
                        if(childJournalingArrayList.get(position).getTouzhu().equals("0.00")){
                            linearLayoutParrent.setVisibility(View.GONE);//没有投注,则隐藏按彩种查看
                      }
                        ImageView imageView =view.findViewById(R.id.img);
                        imageView.setPivotX(imageView.getWidth()/2);
                        imageView.setPivotY(imageView.getHeight()/2);//支点在图片中心
                        LinearLayout linearLayout = view.findViewById(R.id.buttom_linear);
                        if(linearLayout.getVisibility()==View.GONE){
                            linearLayout.setVisibility(View.VISIBLE);
                            imageView.setRotation(90);
                        }else{
                            linearLayout.setVisibility(View.GONE);
                            imageView.setRotation(0);
                        }
                        break;
                    case R.id.team_table:
                        //跳转到新的下级报表页面,并在新的页面进行数据处理
                        Intent intent = new Intent(getContext(), AgentJournalingActivity.class);
                        //用于activity启动是判断是否需要进行数据处理
                         intent.putExtra("isSearch", true);
                        //将当前点击的nikeName传入
                        intent.putExtra("nikename", childJournalingArrayList.get(position).getName());
                        startActivity(intent);
                        getActivity().finish();//跳转之后,结束当前activity,避免多个相同activity同时存在
                        break;
                    case R.id.lettory_show:
                        LayoutInflater inflater = LayoutInflater.from(getContext());//布局管理器
                        View view1 = inflater.inflate(R.layout.show_by_lottery_type_popupwindow, null);//筛选弹出窗视图
                        TextView textView =view1.findViewById(R.id.dingwei);//定位用的控件
                        LinearLayout back = view1.findViewById(R.id.x);//退出图片
                        final PopupWindow popupWindow = new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化筛选popupWindow
                        popupWindow.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
                        popupWindow.setTouchable(true);//响应内部点击
                        popupWindow.showAtLocation(textView,Gravity.BOTTOM,0,0);
                        ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                        back.setOnClickListener(new View.OnClickListener() {//点击退出
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                ProgressDialogUtil.darkenBackground(getActivity(),1f);
                            }
                        });
//                        popupwindow中recycleView相关配置
                        RecyclerView recyclerView =view1.findViewById(R.id.show_by_lottery_type_recycle);

                        showByLotteryRecycleAdapter = new ShowByLotteryRecycleAdapter(showByLotteryTypeArrayList);
                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                        recyclerView.setAdapter(showByLotteryRecycleAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager1);
                        //得到当前点击的item所携带的数据(用于请求接口)
                        Date start = childJournalingArrayList.get(position).getStart();
                        Date end = childJournalingArrayList.get(position).getEnd();
                        String orderBy = childJournalingArrayList.get(position).getOrderBy();
                        String name = childJournalingArrayList.get(position).getName();
                        long id = childJournalingArrayList.get(position).getId();

                        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formatStart = simpleDateFormat.format(start);
                        String formatEnd = simpleDateFormat.format(end);
                        HashMap<String, Object> showByType = new HashMap<>();
                        showByType.put("user_id",user_id);
                        showByType.put("pageNo",1);
                        showByType.put("pageSize",20);
                        showByType.put("startDate",formatStart);
                        showByType.put("endDate",formatEnd);
                        showByType.put("childNickname",name);
                        showByType.put("orderBy",orderBy);
                        showByType.put("isColor","1");//是否按彩种查看(0否1是)
                        HttpApiUtils.CPnormalRequest(getActivity(), ChildJournalingFragment.this, RequestUtil.REQUEST_REPORT_LIST, showByType, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                showByLotteryTypeArrayList.clear();
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                JSONArray colorList = jsonObject.getJSONArray("colorList");
                                for (int i=0;i<colorList.size();i++) {
                                    JSONObject jsonObject1 = colorList.getJSONObject(i);
                                    String typename = jsonObject1.getString("typename");//彩票名称
                                    BigDecimal profitTotalPrice = jsonObject1.getBigDecimal("profitTotalPrice");//盈亏
                                    BigDecimal bettingTotalPrice = jsonObject1.getBigDecimal("bettingTotalPrice");//投注
                                    BigDecimal lotteryTotalPrice = jsonObject1.getBigDecimal("lotteryTotalPrice");//中奖金额
                                    showByLotteryTypeArrayList.add(new ShowByLotteryType(typename,bettingTotalPrice+"",lotteryTotalPrice+"",profitTotalPrice+""));
                                }
                                showByLotteryRecycleAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailed(String msg) {

                            }
                        });

                }

            }
        });

    }
    /*
    请求下级报表数据
     */
    private void getChildInfo(String pagenum,String pageSize,final Date start, final Date end, final String nickName, final String orderBy, String isColor, final boolean isLoad,boolean showLoad) {
//        ProgressDialogUtil.show(getActivity());
        isShowLoad=showLoad;
        if(showLoad){
            nothing.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        ErrorUtil.hideErrorLayout(recyclerView,errorLinear);
        HashMap<String, Object> dataChild = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(getContext(), "user_id", 0l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatStart = simpleDateFormat.format(start);
        String formatEnd = simpleDateFormat.format(end);
        dataChild.put("user_id",user_id);
        dataChild.put("pageNo",pagenum);
        dataChild.put("pageSize",pageSize);
        dataChild.put("startDate",formatStart);
        dataChild.put("endDate",formatEnd);
        dataChild.put("childNickname",nickName);
        dataChild.put("orderBy",orderBy);
       Utils.docking(dataChild, RequestUtil.REQUEST_REPORT_LIST, 0, new DockingUtil.ILoaderListener() {
           @Override
           public void success(String content) {
//               ProgressDialogUtil.stop(getActivity());
               loadingLinear.setVisibility(View.GONE);
               JSONObject jsonObject = JSONObject.parseObject(content);
               JSONArray teamList = jsonObject.getJSONArray("teamList");
               Long totalsize = jsonObject.getLong("totalsize");
               int size = teamList.size();
               dataSize=size;
               if(!isLoad){
               childJournalingArrayList.clear();
               nickNameList.clear();
               }

             if(totalsize==0){
                 if(!isLoad){
                     nothing.setVisibility(View.VISIBLE);
                 }else{
                     nothing.setVisibility(View.GONE);
                 }
                 refreshLayout.finishLoadMoreWithNoMoreData();
             }
               for (int i=0;i<teamList.size();i++) {
                   JSONObject jsonObject1 = teamList.getJSONObject(i);
                   String pointGrade = jsonObject1.getString(CommonStr.GRADE);//vip等级
                   BigDecimal profitTotalPrice = jsonObject1.getBigDecimal("profitTotalPrice");//盈亏
                   String nickname = jsonObject1.getString("nickname");//账号
                   BigDecimal investmentAmount = jsonObject1.getBigDecimal("investmentAmount");//投注金额
                   BigDecimal czPrice = jsonObject1.getBigDecimal("czPrice");//充值金额
                   BigDecimal txPrice = jsonObject1.getBigDecimal("txPrice");//提现金额
                   BigDecimal winMoney = jsonObject1.getBigDecimal("winMoney");//中奖金额
                   BigDecimal activityReturn = jsonObject1.getBigDecimal("activityReturn");//活动返点
                   BigDecimal commission = jsonObject1.getBigDecimal("commission");//个人佣金
                   Long id = jsonObject1.getLong("id");//下级id
                   nickNameList.add(nickname);//添加用户名,用于搜索时判断是否有该下级
                   childJournalingArrayList.add(new ChildJournaling(nickname,pointGrade,investmentAmount+"",profitTotalPrice+"",czPrice+"",txPrice+"",winMoney+"",commission+"",activityReturn+"",id,start,end,orderBy));
               }
               childJournalingFragmentAdapter.notifyDataSetChanged();
           }

           @Override
           public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
//               ProgressDialogUtil.stop(getActivity());
               ErrorUtil.showErrorLayout(ChildJournalingFragment.this,recyclerView,errorLinear,reloadTv);
           }
       });

    }
/*
输入框内容相关配置
 */
    private String initEdit(){
        String editText = searchEdit.getText().toString();
        if (editText.isEmpty()) {
            editText = "";
        }
        return  editText;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload_tv:
                getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",false,isShowLoad);//请求下级报表数据
                break;
            //点击筛选弹出框
            case R.id.choose_linear2:
                popupWindowChild.showAsDropDown(actionBar , 0, 0); // 相对固定元素,无偏移
                ProgressDialogUtil.darkenBackground(getActivity(),0.5f);
                break;
            case R.id.choose_button:
            if(today.isChecked()){
                start = new Date();
                end = new Date();
                initOrder(true);
            }
            else if(yestoday.isChecked()){
                Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());//得到昨天的当前时间
                start= DateUtil.getStartTime(yesterdayCalendar);//得到昨天的开始时间
                end = DateUtil.getEndTime(yesterdayCalendar);//得到昨天到的结束时间
                initOrder(true);
            }
            else if(seven.isChecked()){
               start = DateUtil.getDateBefore(new Date(),6);
               end=new Date();
               initOrder(true);
            }
            popupWindowChild.dismiss();
                break;
            case R.id.search_text:
//                System.out.println(searchEdit.getText().toString()+"  tostring");
                if(nickNameList.contains(searchEdit.getText().toString())){
                    if(today.isChecked()){
                        start = new Date();
                        end = new Date();
                        initOrder(true);
                    }
                    else if(yestoday.isChecked()){
                        Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());//得到昨天的当前时间
                        start= DateUtil.getStartTime(yesterdayCalendar);//得到昨天的开始时间
                        end = DateUtil.getEndTime(yesterdayCalendar);//得到昨天到的结束时间
                        initOrder(true);
                    }
                    else if(seven.isChecked()){
                        start = DateUtil.getWeekTimeStart();
                        end = DateUtil.getWeekTimeEnd();
                        initOrder(true);
                    }
                }else {
                    showToast(Utils.getString(R.string.未找到该下级请重新输入));
                }
                break;
        }
    }
        /*
        判断orberBy的值,并请求下级报表数据
         */
    private void initOrder(boolean showLoad) {
        refreshLayout.resetNoMoreData();
        pageNum=1;
        if(jiangxu.isChecked()){
            if(touzhu.isChecked()){
                orderBy ="2";
            }else if(yinkui.isChecked()){
                orderBy="4";
            }else if(chongzhi.isChecked()){
                orderBy="6";
            }else if(fanyong.isChecked()){
                orderBy="8";
            }
        }
        else if(shengxu.isChecked()){
            if(touzhu.isChecked()){
                orderBy ="1";
            }else if(yinkui.isChecked()){
                orderBy="3";
            }else if(chongzhi.isChecked()){
                orderBy="5";
            }else if(fanyong.isChecked()){
                orderBy="7";
            }
        }
        getChildInfo(pageNum+"",pageSize+"",start,end,initEdit(),orderBy,"0",false,showLoad);//请求下级报表数据
    }
}
