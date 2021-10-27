package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter.AgentCenterRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AgentCenterModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

/*
        代理中心activity
 */
public class AgentCenterActivity extends BaseActivity implements View.OnClickListener {
    ArrayList<AgentCenterModel> agentCenterModelArrayList =new ArrayList<>();
    private AgentCenterRecycleAdapter daiLiRecycleAdapter;
    private   RecyclerView recyclerView;
    private TextView commisionTypeTv;
    private TextView leiji;
    private TextView yestodayChild;
    private TextView todayChild;
    private TextView childNum;
    private TextView back;
    private TextView toRakeBack;
    private GifImageView loadImg;
    private LinearLayout errorLienar;
    private TextView reloadTv;
    private String appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_center);
        appName=SharePreferencesUtil.getString(this,"appName","");
        bindView();
        initRecycleView();//recycleView相关
        getChild();//下级报表数据相关
    }

    @Override
    protected void init() {

    }
    /*
    绑定控件
     */
    private void bindView() {
        errorLienar=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        reloadTv.setOnClickListener(this);
        loadImg=findViewById(R.id.load_img);
        commisionTypeTv=findViewById(R.id.commision_type_tv);
        if(appName.equals(Utils.getString(R.string.申博国际))||appName.equals(Utils.getString(R.string.盈众彩票))){
            commisionTypeTv.setText(Utils.getString(R.string.当前佣金));
        }else {
            commisionTypeTv.setText(Utils.getString(R.string.累计佣金));
        }
        leiji=findViewById(R.id.leiji);//累计佣金
        yestodayChild=findViewById(R.id.yestoday_child);//昨日返点
        todayChild=findViewById(R.id.today_child);//今日返点
        childNum=findViewById(R.id.child_num);//下级人数
        back =findViewById(R.id.bind_bank_card_return);
        toRakeBack =findViewById(R.id.to_rake_back_activity);
        back.setOnClickListener(this);
        toRakeBack.setOnClickListener(this);
    }

    /*
    recycleView数据相关
     */
    private void initRecycleView() {
        recyclerView =findViewById(R.id.daili_recyle);
        daiLiRecycleAdapter = new AgentCenterRecycleAdapter(agentCenterModelArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setAdapter(daiLiRecycleAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        View footView = LayoutInflater.from(this).inflate(R.layout.agent_center_recycle_foot,null);
        daiLiRecycleAdapter.addFooterView(footView);
        agentCenterModelArrayList.add(new AgentCenterModel(Utils.getString(R.string.代理说明),R.drawable.dailishuoming));
        agentCenterModelArrayList.add(new AgentCenterModel(Utils.getString(R.string.下级开户),R.drawable.xiajikaihu));
        agentCenterModelArrayList.add(new AgentCenterModel(Utils.getString(R.string.代理报表),R.drawable.dailibaobiao));
        agentCenterModelArrayList.add(new AgentCenterModel(Utils.getString(R.string.下级管理),R.drawable.xiajigaungli));
        agentCenterModelArrayList.add(new AgentCenterModel(Utils.getString(R.string.下级投注明细),R.drawable.xaijitouzhu));
        agentCenterModelArrayList.add(new AgentCenterModel(Utils.getString(R.string.下级交易明细),R.drawable.xiajijiaoyi));
        int showAgentInCome = SharePreferencesUtil.getInt(MyApplication.getInstance(), CommonStr.AGENT_IN_CONE, 1);
        if(showAgentInCome==0){
            agentCenterModelArrayList.add(new AgentCenterModel(Utils.getString(R.string.代理充值),R.drawable.dailichongzhi));
        }
        daiLiRecycleAdapter.setOnItemClickListener(new AgentCenterRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0://点击跳转代理说明
                        startActivity(new Intent(AgentCenterActivity.this,AgentShowActivity.class));
                        break;
                    case 1://点击跳转下级开户
                        startActivity(new Intent(AgentCenterActivity.this,ChildOpenActivity.class));
                        break;
                    case 2://点击跳转代理报表
                        startActivity(new Intent(AgentCenterActivity.this,AgentJournalingActivity.class));
                        break;
                    case 3://点击跳转下级管理
                        startActivity(new Intent(AgentCenterActivity.this,ChildManageActivity.class));
                        break;
                    case 4://点击跳转下级投注明细
                        startActivity(new Intent(AgentCenterActivity.this,ChildBetActivity.class));
                        break;
                    case 5://点击跳转下级交易明细
                        startActivity(new Intent(AgentCenterActivity.this,ChildDealActivity.class));
                        break;
                    case 6://点击跳转代理充值
                        startActivity(new Intent(AgentCenterActivity.this,AgentInvestActivity.class));
                        break;

                }
            }
        });
    }
    /*
    下级报表数据相关
     */
    private void getChild() {
        loadImg.setVisibility(View.VISIBLE);
        errorLienar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        HashMap<String, Object> dataInfo = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        dataInfo.put("user_id",user_id);
        Utils.docking(dataInfo, RequestUtil.REQUEST_wt32, 0, new DockingUtil.ILoaderListener() {//手机端下级报表
            @Override
            public void success(String content) {
                loadImg.setVisibility(View.GONE);
                ProgressDialogUtil.stop(AgentCenterActivity.this);
                hideDialog();
                JSONObject jsonObject = JSONObject.parseObject(content);
                String yesterdayCommission = jsonObject.getString("yesterdayCommission");//昨日返佣
                String todayCommission = jsonObject.getString("todayCommission");//今日返佣
                String childAllRegisterPeopleNum = jsonObject.getString("childAllRegisterPeopleNum");//下级人数
                String agentcommison = jsonObject.getString("agentcommison");//累计返佣
                yestodayChild.setText(Utils.getString(R.string.昨天返佣:)+yesterdayCommission);
                todayChild.setText(Utils.getString(R.string.今日返佣:)+todayCommission);
                childNum.setText(Utils.getString(R.string.我的下级:)+childAllRegisterPeopleNum+Utils.getString(R.string.人));
                if(appName.equals(Utils.getString(R.string.申博国际))||appName.equals(Utils.getString(R.string.盈众彩票))){
                    //显示个人中心的佣金
                    leiji.setText(SharePreferencesUtil.getString(AgentCenterActivity.this, CommonStr.COMMISION,agentcommison));
                    commisionTypeTv.setText(Utils.getString(R.string.当前佣金));
                }else {
                    leiji.setText(agentcommison);
                    commisionTypeTv.setText(Utils.getString(R.string.累计佣金));
                }
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadImg.setVisibility(View.GONE);
                errorLienar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                ProgressDialogUtil.stop(AgentCenterActivity.this);
                hideDialog();
                showToast(messageHead.getInfo());

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind_bank_card_return:
                finish();
                break;
            case R.id.to_rake_back_activity:
                startActivity(new Intent(this,RakeBackActivity.class));
                break;
            case R.id.reload_tv:
                getChild();
                break;
        }
    }

    @Override
    public void hideDialog() {
        super.hideDialog();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
