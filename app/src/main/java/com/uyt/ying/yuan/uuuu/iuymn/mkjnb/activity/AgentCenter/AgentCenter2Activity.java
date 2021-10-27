package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.AgentCenterAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AgentCenterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AgentReportEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.BottomChooseTimePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;

public class AgentCenter2Activity extends BaseActivity implements View.OnClickListener, BasePopupWindow2.OnPopClickListener {
    @BindView(R.id.toolbar_right_tv)
    TextView toolbar_right_tv;

    @BindView(R.id.agentcenter_recycler)
    RecyclerView agentcenter_recycler;
    AgentCenterAdapter AgentCenterAdapter;
    ArrayList<AgentCenterEntity>agentCenterEntityArrayList = new ArrayList<>();
    private TextView invite_code_tv;
    private TextView choose_date_tv;
    private ImageView choose_date_iv;
    private BottomChooseTimePop bottomChooseTimePop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_center2);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this,true);
        StatusBarUtil.setColor(this, Color.WHITE);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.代理数据));
        initRecycler();
        initView();
        requestTodayData();
    }
    private void requestTodayData() {
        requestAgentReport(new Date(),new Date());
    }
    private void requestYesTodayData() {
        Calendar yesterdayCalendar = DateUtil.getYesterdayCalendar(new Date());//得到昨天的当前时间
        Date startTime = DateUtil.getStartTime(yesterdayCalendar);//得到昨天的开始时间
        Date endTime = DateUtil.getEndTime(yesterdayCalendar);//得到昨天到的结束时间
        requestAgentReport(startTime,endTime);
    }
    private void requestThisWeekData() {
        Date startDate = DateUtil.getDateBefore(new Date(), 6);
        requestAgentReport(startDate,new Date());

    }

    private void requestThisMonthData(){
        Date  startDate = DateUtil.getMonthBegin(new Date());//得到当前月份的开始时间
        requestAgentReport(startDate,new Date());
    }

    private void requestAgentReport(Date startDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatStart = simpleDateFormat.format(startDate);
        String formatEnd = simpleDateFormat.format(endDate);
        HashMap<String, Object> data = new HashMap<>();
        data.put("startDate",formatStart);
        data.put("endDate",formatEnd);
        HttpApiUtils.CpRequest(this, null,RequestUtil.AGENT_CENTER, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                agentCenterEntityArrayList.clear();
                AgentReportEntity agentReportEntity = JSONObject.parseObject(result, AgentReportEntity.class);
                invite_code_tv.setText(agentReportEntity.getInviteCode());
                agentCenterEntityArrayList.add(new AgentCenterEntity(Utils.getString(R.string.邀请人数),agentReportEntity.getInvitePersonNum()));
                agentCenterEntityArrayList.add(new AgentCenterEntity(Utils.getString(R.string.邀请红包收益),agentReportEntity.getYqRedPrice()));
                agentCenterEntityArrayList.add(new AgentCenterEntity(Utils.getString(R.string.下级返佣),agentReportEntity.getTzFyPrice()));
                AgentCenterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private void initView() {
        toolbar_right_tv.setVisibility(View.VISIBLE);
        toolbar_right_tv.setText(Utils.getString(R.string.返佣明细));
        toolbar_right_tv.setOnClickListener(this);
    }


    private void initRecycler() {
        AgentCenterAdapter = new AgentCenterAdapter(R.layout.agentcenter_recycler_item_layout,agentCenterEntityArrayList);
        agentcenter_recycler.setLayoutManager(new LinearLayoutManager(this));
        agentcenter_recycler.setAdapter(AgentCenterAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.agentcenter_head_layout,null);
        AgentCenterAdapter.addHeaderView(headView);
        bindHeadView(headView);
    }
    private void bindHeadView(View headView) {
        invite_code_tv = headView.findViewById(R.id.invite_code_tv);
        choose_date_tv = headView.findViewById(R.id.choose_date_tv);
        choose_date_iv = headView.findViewById(R.id.choose_date_iv);
        choose_date_tv = headView.findViewById(R.id.choose_date_tv);
        choose_date_iv.setOnClickListener(this);
        choose_date_tv.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_date_tv:
            case R.id.choose_date_iv:
                if(bottomChooseTimePop==null){
                    bottomChooseTimePop = new BottomChooseTimePop(AgentCenter2Activity.this,false);
                    bottomChooseTimePop.setOnPopClickListener(AgentCenter2Activity.this);
                    bottomChooseTimePop.getLast_month_tv().setVisibility(View.GONE);
                }
                bottomChooseTimePop.showAtLocation(choose_date_iv, Gravity.BOTTOM,0,0);
                ProgressDialogUtil.darkenBackground(AgentCenter2Activity.this,0.7f);
                break;
            case R.id.toolbar_right_tv:
                //跳转返佣明细
                startActivity(new Intent(this,RakeBackActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPopClick(View view) {

        switch (view.getId()) {
            case R.id.today_tv:
                choose_date_tv.setText(Utils.getString(R.string.今日));
                requestTodayData();
                bottomChooseTimePop.dismiss();
                break;
            case R.id.yestoday_tv:
                choose_date_tv.setText(Utils.getString(R.string.昨日));
                requestYesTodayData();
                bottomChooseTimePop.dismiss();
                break;
            case R.id.week_tv:
                choose_date_tv.setText(Utils.getString(R.string.近7日));
                requestThisWeekData();
                bottomChooseTimePop.dismiss();
                break;
            case R.id.month_tv:
                choose_date_tv.setText(Utils.getString(R.string.本月));
                requestThisMonthData();
                bottomChooseTimePop.dismiss();
                break;
            default:
                break;
        }
    }
}