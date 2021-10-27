
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

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ScreenUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.UpLevelRewardRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UpLevelRewardModel;
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

public class UpLevelRewardActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecycle;
    private UpLevelRewardRecycleAdapter upLevelRewardRecycleAdapter;
    private ArrayList<UpLevelRewardModel> upLevelRewardModelArrayList =new ArrayList<>();
    private TextView levelTv;
    private TextView jinjiRewardTv;
    private Button receiveBtn;
    private ConstraintLayout loadingLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day_reward);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.晋级奖励));
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
        View headView= LayoutInflater.from(this).inflate(R.layout.reward_recycle_head,null);
        ImageView head_iv = headView.findViewById(R.id.head_iv);
        int mScreenWidth = ScreenUtils.getWight(this);
        head_iv.getLayoutParams().width =mScreenWidth;
        head_iv.getLayoutParams().height = (mScreenWidth - ScreenUtils.dip2px(this, 30f)) * 180/ 720;
        String liveIcon5 = Utils.getHomeLogo("liveIcon5");
        GlideLoadViewUtil.LoadNormalView(this,liveIcon5,head_iv);
        levelTv=headView.findViewById(R.id.level);
        jinjiRewardTv=headView.findViewById(R.id.jiangli_amount);
        receiveBtn=headView.findViewById(R.id.receiveBtn);
        receiveBtn.setOnClickListener(this);
        View foot= LayoutInflater.from(this).inflate(R.layout.reward_recycle_foot,null);
        upLevelRewardRecycleAdapter.addHeaderView(headView);
//        upLevelRewardRecycleAdapter.addFooterView(foot);
        //REQUEST_08rzq
        initData();

    }

    private void initData() {
        loadingLinear.setVisibility(View.VISIBLE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", SharePreferencesUtil.getLong(UpLevelRewardActivity.this,"user_id",0l));
        Utils.docking(data, RequestUtil.REQUEST_08rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(UpLevelRewardActivity.this);
                upLevelRewardModelArrayList.clear();
                upLevelRewardModelArrayList.add(new UpLevelRewardModel(Utils.getString(R.string.等级),Utils.getString(R.string.成长积分),Utils.getString(R.string.晋级奖励),Utils.getString(R.string.跳级奖励)));

                JSONObject jsonObject = JSONObject.parseObject(content);
                Integer pointGrade = jsonObject.getInteger(CommonStr.GRADE);//用户等级
                BigDecimal jinjiAmount = jsonObject.getBigDecimal("promotion");//晋级奖励金额
                levelTv.setText("VIP"+(pointGrade+1));
                jinjiRewardTv.setText(jinjiAmount+"");
                if(jinjiAmount.compareTo(BigDecimal.ZERO)==-1||jinjiAmount.compareTo(BigDecimal.ZERO)==0){
                    receiveBtn.setText(Utils.getString(R.string.不可领取));
                    receiveBtn.setBackgroundColor(Color.parseColor("#E8E8E8"));
                    receiveBtn.setClickable(false);
                    receiveBtn.setTextColor(ContextCompat.getColor(UpLevelRewardActivity.this,R.color.alpha_60_black));
                }else {
                    receiveBtn.setText(Utils.getString(R.string.领取));
                    receiveBtn.setBackground(ContextCompat.getDrawable(UpLevelRewardActivity.this,R.drawable.game_rule_sure_shape));
                    receiveBtn.setClickable(true);
                }
                JSONArray memberGradeMechanismList = jsonObject.getJSONArray("memberGradeMechanismList");
                for (int i = 0; i < memberGradeMechanismList.size(); i++) {
                    JSONObject jsonObject1 = memberGradeMechanismList.getJSONObject(i);
                    int grade = jsonObject1.getInteger("grade");//列表中的会员等级
                    String growthIntegral = jsonObject1.getString("growthIntegral");//成长积分
                    String promotion = jsonObject1.getString("promotion");//晋级奖励
                    String skip = jsonObject1.getString("skip");//跳级奖励
//                    if(growthIntegral.equals("0")){
//                        upLevelRewardModelArrayList.add(new UpLevelRewardModel("VIP"+(grade+1),0.5+"",0.5+"",0.5+""));
//                    }else {
                        upLevelRewardModelArrayList.add(new UpLevelRewardModel("VIP"+(grade+1),growthIntegral,promotion,skip));
//                    }

                }
                upLevelRewardRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
                ProgressDialogUtil.stop(UpLevelRewardActivity.this);
            }
        });
    }

    private void bindView() {
        loadingLinear=findViewById(R.id.loading_linear);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.receiveBtn:
                HashMap<String, Object> data = new HashMap<>();
                data.put("user_id",SharePreferencesUtil.getLong(UpLevelRewardActivity.this,"user_id",0l));
                Utils.docking(data, RequestUtil.REQUEST_zz6, 0, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content)  {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        showToast(jsonObject.getString("message"));
                        receiveBtn.setText(Utils.getString(R.string.不可领取));
                        receiveBtn.setBackgroundColor(Color.parseColor("#E8E8E8"));
                        receiveBtn.setClickable(false);
                        jinjiRewardTv.setText(0.00+"");
                        receiveBtn.setTextColor(getResources().getColor(R.color.alpha_60_black));
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
