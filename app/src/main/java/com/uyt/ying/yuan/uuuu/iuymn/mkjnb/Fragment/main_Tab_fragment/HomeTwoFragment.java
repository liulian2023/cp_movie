package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.androidkun.xtablayout.XTabLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.OnLineKeFuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.RankActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeLiveDataSuccessEven;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.RongcloudHBParameter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter.HomeTabAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class HomeTwoFragment extends BaseFragment implements /*TabLayout.BaseOnTabSelectedListener, */View.OnClickListener, XTabLayout.OnTabSelectedListener {
//    public LinearLayout actionBarLinear;
    public RelativeLayout actionBarLinear;
    public ImageView rank_iv;
    public ImageView onlineKfIv;
//    public TabLayout mTab;
    public XTabLayout mTab;
    public ArrayList<String> titleList  = new ArrayList<>();
//    public NoSlidingViewPager mViewPager;
    public ViewPager mViewPager;
    public HomeTabAdapter homeTabAdapter;
    public int hotTabCount=1;
    private boolean homeLiveDataSuccess = false;

    public static HomeTwoFragment newInstance() {
        Bundle args = new Bundle();
        HomeTwoFragment fragment = new HomeTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_two, container, false);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        bindView(view);
//        ActionBarUtil.initMainActionbar(getActivity(),actionBarLinear,R.color.white);
        initTab();
        //请求专享红包限定的彩种
        requestHBParameter();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void requestHBParameter() {
        Utils.docking(new HashMap<>(), RequestUtil.HB_PARAMETER, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                RongcloudHBParameter hbParameter = JSONObject.parseObject(content, RongcloudHBParameter.class);
                RongcloudHBParameter.RongcloudHBParameterBean rongcloudHBParameter = hbParameter.getRongcloudHBParameter();
                int zxHBSwitch = rongcloudHBParameter.getZxHBSwitch();
//                if(zxHBSwitch==1){
                    String zxHBGameClassIds = rongcloudHBParameter.getZxHBGameClassIds();
                    HbGameClassModel instance = HbGameClassModel.getInstance();
                    instance.setHbParameter(hbParameter);
                    instance.setGameIdListStr(zxHBSwitch==1?zxHBGameClassIds:"");
                    EventBus.getDefault().postSticky(instance);
//                }
            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(getActivity(),R.color.white));
        StatusBarUtil.setDarkMode(getActivity());
    }

    public void initTab() {
        //cp+ 直播
            titleList.add(Utils.getString(R.string.热门));
            titleList.add(Utils.getString(R.string.活动));

            titleList.add(getString(R.string.长龙));
//            titleList.add(Utils.getString(R.string.附近));

        homeTabAdapter=new HomeTabAdapter(getChildFragmentManager(),titleList,getContext());
        mViewPager.setAdapter(homeTabAdapter);
        mTab.setupWithViewPager(mViewPager);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = mTab.getTabAt(i);
            tabAt.setCustomView(homeTabAdapter.getTabView(i));
           if(i==0){
               TextView textView= tabAt.getCustomView().findViewById(R.id.home_tab_title_tv);
               textView.setTextColor(Color.parseColor("#FF2F81"));
               textView.setTextSize(20);
           }
        }
        mTab.addOnTabSelectedListener(this);
        mViewPager.setOffscreenPageLimit(3);
    }
    public void bindView(View view) {
        actionBarLinear=view.findViewById(R.id.home_action_linear);
        rank_iv =view.findViewById(R.id.rank_iv);
        onlineKfIv=view.findViewById(R.id.home_on_line_kf_iv);
        mTab=view.findViewById(R.id.home_tab);
        mViewPager=view.findViewById(R.id.home_fragment_viewpager);
        rank_iv.setOnClickListener(this);
        onlineKfIv.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void homeLiveDataEven(HomeLiveDataSuccessEven homeLiveDataSuccessEven) {
        homeLiveDataSuccess = homeLiveDataSuccessEven.isSuccess();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rank_iv:
//                startActivity(new Intent(getContext(), SearchActivity.class));
                if(!homeLiveDataSuccess){
                    return;
                }
                startActivity(new Intent(getContext(), RankActivity.class));
                break;
            case R.id.home_on_line_kf_iv:
                if(!homeLiveDataSuccess){
                    return;
                }
                Intent intent = new Intent(getContext(), OnLineKeFuActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#FF2F81"));
        boolean isHotTab = tab.getPosition() == 0;
        if(isHotTab){
            hotTabCount++;
        }

    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextColor(Color.parseColor("#767676"));
        textView.setTextSize(16);
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }
}
