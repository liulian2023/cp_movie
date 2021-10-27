package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.DiamondTabAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiamondActivity extends BaseActivity implements XTabLayout.OnTabSelectedListener {
    @BindView(R.id.diamond_tab)
    XTabLayout diamond_tab;
    @BindView(R.id.diamond_viewPager)
    ViewPager diamond_viewPager;
    private ArrayList<String> titleList=new ArrayList<>();
    DiamondTabAdapter diamondTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamond);
        ButterKnife.bind(this);
        //设置布局在底部
        getWindow().setGravity(Gravity.BOTTOM);
        //设置布局填充满宽度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        //设置进出动画
        overridePendingTransition(R.anim.activity_int_400,R.anim.activity_out_400);
        initTab();
    }

    private void initTab() {
        titleList.add(Utils.getString(R.string.充值));
        titleList.add(getString(R.string.打码));
        titleList.add(Utils.getString(R.string.送礼));
        titleList.add(Utils.getString(R.string.邀请));
        diamondTabAdapter = new DiamondTabAdapter(getSupportFragmentManager(),titleList,this);
        diamond_viewPager.setAdapter(diamondTabAdapter);
        diamond_tab.setupWithViewPager(diamond_viewPager);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = diamond_tab.getTabAt(i);
            tabAt.setCustomView(diamondTabAdapter.getTabView(i));
            if(i==0){
                TextView textView= tabAt.getCustomView().findViewById(R.id.diamond_tab_tv);
                textView.setTextColor(ContextCompat.getColor(this,R.color.default_color));
                textView.setTextSize(20);
            }
        }
        diamond_tab.addOnTabSelectedListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        if(tab==null){
            return;
        }
        View customView = tab.getCustomView();
        if(customView==null){
            return;
        }
        TextView textView=  customView.findViewById(R.id.diamond_tab_tv);
        textView.setTextSize(16);
        textView.setTextColor(getColor(R.color.default_color));
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
        if(tab==null){
            return;
        }
        View customView = tab.getCustomView();
        if(customView==null){
            return;
        }
        TextView textView=  customView.findViewById(R.id.diamond_tab_tv);
        textView.setTextColor(Color.parseColor("#999999"));
        textView.setTextSize(14);
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }
}