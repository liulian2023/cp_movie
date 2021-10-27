package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.RankTabAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonSurePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.gyf.immersionbar.ImmersionBar;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RankActivity extends BaseActivity  implements XTabLayout.OnTabSelectedListener {
    @BindView(R.id.statusbar_view)
    public   ImageView starusbar_view;
    @BindView(R.id.rank_viewpager)
    ViewPager rank_viewpager;
    @BindView(R.id.rank_tab)
    XTabLayout rank_tab;
    @BindView(R.id.rank_back_iv)
    ImageView rank_back_iv;
    @BindView(R.id.rank_pop_iv)
    ImageView rank_pop_iv;
    @BindView(R.id.rank_action_linear)
    LinearLayout rank_action_linear;
    private ArrayList<String> titleList=new ArrayList<>();
    RankTabAdapter rankTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ButterKnife.bind(this);
/*        int[] colors = { getResources().getColor(R.color.dushen_start_color),     getResources().getColor(R.color.dushen_end_color)};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        starusbar_view .setBackground(gradientDrawable);
        rank_action_linear .setBackground(gradientDrawable);*/
        ImmersionBar.with(this)
                .navigationBarColor(R.color.transparent)
//                .statusBarView(starusbar_view)
                .init();
        initTab();

    }


        @OnClick({R.id.rank_back_iv,R.id.rank_pop_iv})
        public void onClick(View v){
        switch (v.getId()){
            case R.id.rank_back_iv:
                finish();
                break;
            case R.id.rank_pop_iv:
                CommonSurePop commonSurePop = new CommonSurePop(RankActivity.this, false, Utils.getString(R.string.说明), Utils.getString(R.string.排行榜内容每小时更新一次));
                commonSurePop.showAtLocation(rank_back_iv, Gravity.CENTER,0,0);
                ProgressDialogUtil.darkenBackground(RankActivity.this,0.7f);
                break;
                default:
                    break;
        }
        }
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    public void initTab() {
        titleList.add(CommonStr.MINGXING);
        titleList.add(CommonStr.GONGXIAN);
        titleList.add(CommonStr.FUHAO);
        titleList.add(CommonStr.DUSHEN);
        titleList.add(CommonStr.YAOQING);
        titleList.add(CommonStr.ZHUANXIANG);
        rankTabAdapter=new RankTabAdapter(getSupportFragmentManager(),titleList,this);
        rank_viewpager.setAdapter(rankTabAdapter);
        rank_tab.setupWithViewPager(rank_viewpager);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = rank_tab.getTabAt(i);
            tabAt.setCustomView(rankTabAdapter.getTabView(i));
            if(i==0){
                TextView textView= tabAt.getCustomView().findViewById(R.id.home_tab_title_tv);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setTextSize(20);
            }
        }
        rank_tab.addOnTabSelectedListener(this);

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
        TextView textView=  customView.findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
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
        TextView textView=  customView.findViewById(R.id.home_tab_title_tv);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setTextSize(16);
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }
}
