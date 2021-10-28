package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter.GameReportTabAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtils2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameReportActivity extends BaseActivity implements XTabLayout.OnTabSelectedListener {
    @BindView(R.id.game_report_tab)
    XTabLayout game_report_tab;
    @BindView(R.id.game_report_viewpager)
    ViewPager game_report_viewpager;
    @BindView(R.id.toolbar_back_iv)
    ImageView toolbar_back_iv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbar_title_tv;
    public ArrayList<String> titleList  = new ArrayList<>();
    public GameReportTabAdapter gameReportTabAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_report);
        ButterKnife.bind(this);
        initTab();
        View viewById = findViewById(R.id.toolbar_linear);
        viewById.setBackgroundColor(ContextCompat.getColor(this,R.color.transparent));//toolbar设置透明
        toolbar_back_iv.setImageResource(R.drawable.icon_arrow_back);//设置白色返回键
        toolbar_title_tv.setTextColor(Color.WHITE);
        CommonToolbarUtil.initToolbar(this, Utils.getString(R.string.游戏详情));//标题

        //rootView第一个子view的图片顶入状态栏
        StatusBarUtils2.with(this)
                .init();
        //设置toolBar父view的marginTop()
        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) layoutParams;
        layoutParams1.setMargins(0, getStatusBarHeight(this),0,0);
        viewById.setLayoutParams(layoutParams1);

    }

    private void initTab() {
        titleList.add(Utils.getString(R.string.今日));
        titleList.add(Utils.getString(R.string.昨日));
        titleList.add(Utils.getString(R.string.近七天));

        //  titleList.add(Utils.getString(R.string.开奖));
        //  titleList.add(Utils.getString(R.string.频道));
        gameReportTabAdapter =new GameReportTabAdapter(getSupportFragmentManager(),titleList,this);
        game_report_viewpager.setAdapter(gameReportTabAdapter);
        game_report_tab.setupWithViewPager(game_report_viewpager);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = game_report_tab.getTabAt(i);
            tabAt.setCustomView(gameReportTabAdapter.getTabView(i));
            if(i==0){
                TextView textView= tabAt.getCustomView().findViewById(R.id.home_tab_title_tv);
                textView.setTextSize(20);
            }
        }
        game_report_tab.addOnTabSelectedListener(this);
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(20);
        boolean isHotTab = tab.getPosition() == 0;
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(16);
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }
}
