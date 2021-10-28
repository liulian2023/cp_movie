package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment;

import android.content.Intent;
import  android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.AtyMoreActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.BannerWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.EveryDayRewardActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.UpLevelRewardActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.AtyRecycleViewAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AtyCenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Headers;

/**
 * 活动中心fragment
 */
public class AtyFragment extends BaseFragment {
    private ArrayList<AtyCenter> atyCenterArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private AtyRecycleViewAdapter atyRecycleViewAdapter;
    private int showCodeAmountMyself;//是否显示每日加奖活动
    private int showMemberGradeAward;//是否显示晋级奖励活动
    private ConstraintLayout loadingLinear;
    private LinearLayout actionBarLinear;
    private String TAG ="AtyFragment";

    public static AtyFragment newInstance() {
        Bundle args = new Bundle();
        AtyFragment fragment = new AtyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_aty, container, false);
        loadingLinear=view.findViewById(R.id.loading_linear);
        showCodeAmountMyself=SharePreferencesUtil.getInt(getContext(),"showCodeAmountMyself",1);
        showMemberGradeAward=SharePreferencesUtil.getInt(getContext(),"showMemberGradeAward",1);
        initRecycle(view);
        initRefresh(view);
        return view;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
/*        StatusBarUtil.setColor(getActivity(), Color.WHITE);
        StatusBarUtil.setLightMode(getActivity(),false);*/
        requestAppStatistics(3);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData(true);
    }

    private void requestAppStatistics(int page){
        if(SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)!=0){
            HashMap<String, Object> data = new HashMap<>();
            data.put("page",page);//1 直播页面 2游戏页面 3 活动页面 4 会员中心 5 直播观看次数 6 直播观看时长 7 app安装统计
            data.put("installApps", Utils.getInstallApps(getContext()));
            HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.WATCH_MINUTES, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    Utils.logE(TAG, "onSuccess: 用户行为统计成功 page ="+page );
                }

                @Override
                public void onFailed(String msg) {
                    Utils.logE(TAG, "onSuccess: 用户行为统计失败 page ="+page+ msg );
                }
            });
        }
    }
    private void initRefresh(View view) {
        refreshLayout=view.findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData(true);
                refreshLayout.finishRefresh();
            }
        });
    }

    private void initRecycle(View view) {
        recyclerView = view.findViewById(R.id.aty_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        atyRecycleViewAdapter = new AtyRecycleViewAdapter(atyCenterArrayList,getContext());
        recyclerView.setAdapter(atyRecycleViewAdapter);
        atyRecycleViewAdapter.setOnItemClickListener(new AtyRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(!LoginIntentUtil.isLogin(getContext())){
                    LoginIntentUtil.toLogin(getActivity());
                }else {
                    AtyCenter atyCenter = atyCenterArrayList.get(position);
                    if(atyCenter.getTitle().equals(getString(R.string.晋级奖励))){
                        Intent intent = new Intent(getContext(), UpLevelRewardActivity.class);
                        startActivity(intent);
                    }else if(atyCenter.getTitle().equals(Utils.getString(R.string.每日加奖))){
                        Intent intent1 = new Intent(getContext(), EveryDayRewardActivity.class);
                        startActivity(intent1);
                    }else{
                        String link = atyCenter.getLink();
                        if(StringMyUtil.isNotEmpty(link)){
                            BannerWebViewActivity.startActivity(getContext(),link,atyCenter.getTitle());
                        }else {
                            Intent intent2 = new Intent(getContext(),AtyMoreActivity.class);
                            intent2.putExtra("content", atyCenter.getContentMore());
                            intent2.putExtra("title", atyCenter.getTitle());
                            intent2.putExtra("image", atyCenter.getImageUrl());
                            startActivity(intent2);
                   /*             String webViewContent = atyCenter.getContent();
                                if(StringMyUtil.isNotEmpty(webViewContent)){
                                    BannerWebViewActivity.startActivity(getContext(),webViewContent,atyCenter.getTitle());
                                }*/

                        }

                    }
                }

            }
        });
    }

    private void initData(boolean isOnCreate) {
        loadingLinear.setVisibility(View.VISIBLE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",1);
        data.put("pageSize",15);
        data.put("type",4);//0.logo  1轮播图  2消息 3弹出公告  4优惠活动
        if(isOnCreate){
//            data.put("page",1);
        }
        Utils.docking(data, RequestUtil.REQUEST_33r, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                loadingLinear.setVisibility(View.GONE);
                atyCenterArrayList.clear();
                if(showMemberGradeAward==0){
                    String liveIcon5 = Utils.getHomeLogo("liveIcon5");
                    AtyCenter aty = new AtyCenter(R.drawable.ac_icon1, Utils.getString(R.string.晋级奖励), getString(R.string.会员每晋升一个等级),liveIcon5,1,1,"");
                    atyCenterArrayList.add(aty);
                }
                if(showCodeAmountMyself==0){
                    String liveIcon6 = Utils.getHomeLogo("liveIcon6");
                    AtyCenter aty1 = new AtyCenter(R.drawable.ac_icon1, getString(R.string.每日加奖), getString(R.string.每日加奖是根据会员昨日投注金额),liveIcon6,1,1,"");
                    atyCenterArrayList.add(aty1);
                }
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONArray extensionNoticeInfoList = jsonObject1.getJSONArray("extensionNoticeInfoList");
                for (int i = 0; i < extensionNoticeInfoList.size(); i++) {
                    JSONObject jsonObject = extensionNoticeInfoList.getJSONObject(i);
                    String contentTxt = jsonObject.getString("contentTxt");//活动内容
                    String content2 = jsonObject.getString("content");//活动内容
                    String themeTwo = jsonObject.getString("themeTwo");//活动标题
                    String themetype = jsonObject.getString("themetype");
                    String link = jsonObject.getString("link");
                    String theme = jsonObject.getString("theme");
                    link = StringMyUtil.isEmptyString(link)?"":link;
                    long id = jsonObject.getLong("id");
                    if(themetype.equals("1")){
                        AtyCenter atyCenter = new AtyCenter(R.drawable.level_image, themeTwo, contentTxt, theme, 2, id, content2);
                        atyCenter.setLink(link);
                        atyCenter.setThemetype(themetype);
                        atyCenterArrayList.add(atyCenter);
                    }else {
                        AtyCenter atyCenter = new AtyCenter(R.drawable.level_image, theme, contentTxt, theme, 2, id, content2);
                        atyCenter.setThemetype(themetype);
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
