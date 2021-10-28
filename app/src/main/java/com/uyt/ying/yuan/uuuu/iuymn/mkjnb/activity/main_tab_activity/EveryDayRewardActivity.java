
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ScreenUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.UpLevelRewardRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UpLevelRewardModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.turntableView.WheelSurfView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class EveryDayRewardActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecycle;
    private UpLevelRewardRecycleAdapter upLevelRewardRecycleAdapter;
    private ArrayList<UpLevelRewardModel> upLevelRewardModelArrayList =new ArrayList<>();
    private TextView yesTodayBetTV;
    private TextView nowLevel;
    private TextView jiajiangTv;
    private TextView kedeTv;
    private Button receiveBtn;
    private ConstraintLayout loadingLinear;
    WheelSurfView turntable_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day_reward);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.每日嘉奖));
        StatusBarUtil.setColor(this,Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        bindView();
        intiRecycel();
    }

    @Override
    protected void init() {

    }

    private void intiRecycel() {
        mRecycle=findViewById(R.id.every_reward_recycle);
        upLevelRewardRecycleAdapter =new UpLevelRewardRecycleAdapter(upLevelRewardModelArrayList);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecycle.setAdapter(upLevelRewardRecycleAdapter);
        View headView= LayoutInflater.from(this).inflate(R.layout.every_day_reward_recycle_head,null);
        ImageView head_iv = headView.findViewById(R.id.head_iv);
        int mScreenWidth = ScreenUtils.getWight(this);
        head_iv.getLayoutParams().width =mScreenWidth;
        head_iv.getLayoutParams().height = (mScreenWidth - ScreenUtils.dip2px(this, 30f)) * 180/ 720;
        String liveIcon6 = Utils.getHomeLogo("liveIcon6");
        GlideLoadViewUtil.LoadNormalView(this,liveIcon6,head_iv);
        yesTodayBetTV=headView.findViewById(R.id.yestoday_bet);
        nowLevel =headView.findViewById(R.id.now_level);
        jiajiangTv =headView.findViewById(R.id.jiajiang_amount);
        kedeTv =headView.findViewById(R.id.kede_jiajiang);
        receiveBtn=headView.findViewById(R.id.receiveBtn);
        receiveBtn.setOnClickListener(this);
        View foot= LayoutInflater.from(this).inflate(R.layout.everyday_reward_recycle_foot,null);
        upLevelRewardRecycleAdapter.addHeaderView(headView);
        upLevelRewardRecycleAdapter.addFooterView(foot);
        initData();
    }

    private void initData() {
        loadingLinear.setVisibility(View.VISIBLE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", SharePreferencesUtil.getLong(EveryDayRewardActivity.this,"user_id",0l));
        Utils.docking(data, RequestUtil.REQUEST_zz2, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(EveryDayRewardActivity.this);
                upLevelRewardModelArrayList.clear();
                BigDecimal amountA = null;
                BigDecimal amountB = null;
                BigDecimal amountC = null;
                JSONObject jsonObject = JSONObject.parseObject(content);
                String grade = jsonObject.getString("grade");//用户等级
                BigDecimal yeasterdayBettingPrice = jsonObject.getBigDecimal("yeasterdayBettingPrice");//昨日投注
                String rate = jsonObject.getString("rate");//加奖比例
                BigDecimal canReceviceBonus = jsonObject.getBigDecimal("canReceviceBonus");//可得加奖
                nowLevel.setText(grade);
                yesTodayBetTV.setText(yeasterdayBettingPrice+"");
                jiajiangTv.setText(rate+"");
                kedeTv.setText(canReceviceBonus+"");
                if(canReceviceBonus.compareTo(BigDecimal.ZERO)==-1||canReceviceBonus.compareTo(BigDecimal.ZERO)==0){
                    receiveBtn.setText(Utils.getString(R.string.不可领取));
                    receiveBtn.setBackgroundColor(Color.parseColor("#E8E8E8"));
                    receiveBtn.setClickable(false);
                    receiveBtn.setTextColor(ContextCompat.getColor(EveryDayRewardActivity.this,R.color.alpha_60_black));
                }else {
                    receiveBtn.setText(Utils.getString(R.string.领取));
                    receiveBtn.setBackground(ContextCompat.getDrawable(EveryDayRewardActivity.this,R.drawable.game_rule_sure_shape));
                    receiveBtn.setClickable(true);
                }
                JSONArray rechargeCodeAmountMyselfRateList = jsonObject.getJSONArray("rechargeCodeAmountMyselfRateList");
                for (int i = 0; i < rechargeCodeAmountMyselfRateList.size(); i++) {
                    JSONObject jsonObject1 = rechargeCodeAmountMyselfRateList.getJSONObject(i);
                    int grade1 = jsonObject1.getInteger("grade");//列表中的会员等级
                    BigDecimal rateA = new BigDecimal(jsonObject1.getString("rateA"));//比例
                    BigDecimal rateB = new BigDecimal(jsonObject1.getString("rateB"));
                    BigDecimal rateC = new BigDecimal(jsonObject1.getString("rateC"));
                    if(i==0){
                        amountA = jsonObject1.getBigDecimal("amountA").setScale(1);//打码量
                        amountB = jsonObject1.getBigDecimal("amountB").setScale(1);
                        amountC = jsonObject1.getBigDecimal("amountC").setScale(1);
                    }
                    upLevelRewardModelArrayList.add(new UpLevelRewardModel("VIP"+(grade1+1),rateA+"%",rateB+"%",rateC+"%"));
                }
                upLevelRewardModelArrayList.add(0,new UpLevelRewardModel(Utils.getString(R.string.等级投注额),amountA+"+",amountB+"+",amountC+"+"));
                upLevelRewardRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(EveryDayRewardActivity.this);
            }
        });
    }
    private void bindView() {
        turntable_view=findViewById(R.id.turntable_view);
        loadingLinear=findViewById(R.id.loading_linear);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
                break;
            case R.id.receiveBtn:
                HashMap<String, Object> data = new HashMap<>();
                data.put("user_id",SharePreferencesUtil.getLong(EveryDayRewardActivity.this,"user_id",0l));
                Utils.docking(data, RequestUtil.REQUEST_zz7, 0, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content)  {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        showToast(jsonObject.getString("message"));
                        receiveBtn.setText(Utils.getString(R.string.不可领取));
                        receiveBtn.setBackgroundColor(Color.parseColor("#E8E8E8"));
                        receiveBtn.setClickable(false);
                        kedeTv.setText(0.00+"");
//                        jiajiangTv.setText("0%");
                        receiveBtn.setTextColor(ContextCompat.getColor(EveryDayRewardActivity.this,R.color.alpha_60_black));
                    }

                    @Override
                    public void failed(MessageHead messageHead) {

                    }
                });
                break;
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
