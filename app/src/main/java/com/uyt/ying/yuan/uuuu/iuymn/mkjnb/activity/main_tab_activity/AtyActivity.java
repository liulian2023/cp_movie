package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.AtyRecycleViewAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AtyCenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActionBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AtyActivity extends AppCompatActivity {
    private ArrayList<AtyCenter> atyCenterArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private AtyRecycleViewAdapter atyRecycleViewAdapter;
    private int showCodeAmountMyself;//是否显示每日加奖活动
    private int showMemberGradeAward;//是否显示晋级奖励活动
    private ConstraintLayout loadingLinear;
    private LinearLayout actionBarLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty);
        loadingLinear=findViewById(R.id.loading_linear);
        showCodeAmountMyself=SharePreferencesUtil.getInt(this,"showCodeAmountMyself",1);//是否显示每日嘉奖
        showMemberGradeAward=SharePreferencesUtil.getInt(this,"showMemberGradeAward",1);//是否显示每日
        initRecycle();
        initRefresh();
        initData();//请求活动数据
        ActionBarUtil.initMainActionbar(this,findViewById(R.id.aty_action_bar_linear),R.color.action_bar_color);
        findViewById(R.id.aty_action_bar_return).setOnClickListener(new View.OnClickListener() {//返回键
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void initRefresh() {
        refreshLayout=findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(AtyActivity.this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
                refreshLayout.finishRefresh();
            }
        });
    }

    private void initRecycle() {
        recyclerView = findViewById(R.id.aty_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AtyActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        atyRecycleViewAdapter = new AtyRecycleViewAdapter(atyCenterArrayList,AtyActivity.this);
        recyclerView.setAdapter(atyRecycleViewAdapter);
        atyRecycleViewAdapter.setOnItemClickListener(new AtyRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(!LoginIntentUtil.isLogin(AtyActivity.this)){
                    LoginIntentUtil.toLogin(AtyActivity.this);
                }else {
                    AtyCenter atyCenter = atyCenterArrayList.get(position);
                    if(atyCenter.getTitle().equals(Utils.getString(R.string.晋级奖励))){
                        Intent intent = new Intent(AtyActivity.this, UpLevelRewardActivity.class);
                        startActivity(intent);
                    }else if(atyCenter.getTitle().equals(Utils.getString(R.string.每日加奖))){
                        Intent intent1 = new Intent(AtyActivity.this, EveryDayRewardActivity.class);
                        startActivity(intent1);
                    }else{
                        String link = atyCenter.getLink();
                        if(StringMyUtil.isNotEmpty(link)){
                            BannerWebViewActivity.startActivity(AtyActivity.this,link,atyCenter.getTitle());
                        }else {
                            Intent intent2 = new Intent(AtyActivity.this,AtyMoreActivity.class);
                            intent2.putExtra("content", atyCenter.getContentMore());
                            intent2.putExtra("title", atyCenter.getTitle());
                            intent2.putExtra("image", atyCenter.getImageUrl());
                            startActivity(intent2);
                        }

                    }
                }

            }
        });
    }

    private void initData() {
        loadingLinear.setVisibility(View.VISIBLE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",15);
        data.put("type",4);//0.logo  1轮播图  2消息 3弹出公告  4优惠活动
        Utils.docking(data, RequestUtil.REQUEST_33r, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                atyCenterArrayList.clear();
                if(showMemberGradeAward==0){
                    AtyCenter aty = new AtyCenter(R.drawable.ac_icon1, Utils.getString(R.string.晋级奖励), Utils.getString(R.string.会员每晋升一个等级都能获得奖励最高可得88888元),"weizhi",1,1,"");
                    atyCenterArrayList.add(aty);
                }
                if(showCodeAmountMyself==0){
                    AtyCenter aty1 = new AtyCenter(R.drawable.ac_icon1, Utils.getString(R.string.每日加奖), Utils.getString(R.string.每日加奖是根据会员昨日投注金额进行一定比例的加奖),"weizhi",1,1,"");
                    atyCenterArrayList.add(aty1);
                }
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray extensionNoticeInfoList = jsonObject1.getJSONArray("extensionNoticeInfoList");
                for (int i = 0; i < extensionNoticeInfoList.size(); i++) {
                    JSONObject jsonObject = extensionNoticeInfoList.getJSONObject(i);
                    String contentTxt = jsonObject.getString("contentTxt");//活动内容
                    String content2 = jsonObject.getString("content");//活动内容
                    String themeTwo = jsonObject.getString("themeTwo");//活动标题
                    String image = jsonObject.getString("image");
                    String link = jsonObject.getString("link");
                    link = StringMyUtil.isEmptyString(link)?"":link;
                    String firstImageUrl = SharePreferencesUtil.getString(AtyActivity.this, "FirstImageUrl", "");
                    long id = jsonObject.getLong("id");
                    if(StringMyUtil.isEmptyString(image)){
                        String theme = jsonObject.getString("theme");//图片
                        String finalUrl=firstImageUrl+theme;
                        //what字段用于判断是否是前两个固定的活动,1 是 2 否,固定的活动加载本地图片
                        AtyCenter atyCenter = new AtyCenter(R.drawable.ac_icon1, themeTwo, contentTxt, finalUrl, 2, id, content2);
                        atyCenter.setLink(link);
                        atyCenterArrayList.add(atyCenter);
                    }else{
                        String finalUrl=firstImageUrl+image;
                        AtyCenter atyCenter = new AtyCenter(R.drawable.ac_icon1, themeTwo, contentTxt, finalUrl, 2, id, content2);

                        atyCenterArrayList.add(atyCenter);
                    }
                }
                atyRecycleViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(MessageHead messageHead) {
                loadingLinear.setVisibility(View.GONE);
            }
        });
    }
}
