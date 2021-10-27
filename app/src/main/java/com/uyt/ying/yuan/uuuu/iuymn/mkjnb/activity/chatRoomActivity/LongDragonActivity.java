

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.Dragon_fragmemt.DragonBetFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.Dragon_fragmemt.DragonBetNoteFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter.LongDragonFragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
/*
长龙助手activity
 */

public class LongDragonActivity extends BaseActivity implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    private TextView actionBarBackTv;
    private ImageView helpIv;
    private TextView actionCenterTv;
    private ArrayList<String> titles=new ArrayList<>();
    private TabLayout mTab;
    private ViewPager mViewPager;
    private LinearLayout showMoneyLinear;
//    private ImageView eyeImg;
//    private TextView yueTv;
    private TextView moneyTv;
    private long user_id;

    private PopupWindow shuomingPop;                                                   
    private TextView popBackTv;
    private TextView popBarCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_long);
        user_id= SharePreferencesUtil.getLong(LongDragonActivity.this,"user_id",0l);
        bindView();
        initTabViewPager();//tabLayout viaewPager初始化
    }

    @Override
    protected void init() {

    }

    private void initTabViewPager() {
        titles.add(getString(R.string.我要投注));
        titles.add(getString(R.string.最近投注));
        mTab=findViewById(R.id.mtab);
        //添加tab
        for (int i=0;i<titles.size();i++) {
            mTab.addTab(mTab.newTab());
        }
        //设置tab名
        for (int i=0;i<titles.size();i++) {
            mTab.getTabAt(i).setText(titles.get(i));
        }
        mTab.addOnTabSelectedListener(this);

        ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
        fragmentArrayList.add(new DragonBetFragment());
        fragmentArrayList.add(new DragonBetNoteFragment());
        LongDragonFragmentAdapter fragmentAdapter = new LongDragonFragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);
        mViewPager.setAdapter(fragmentAdapter);
        mTab.setupWithViewPager(mViewPager);

    }

    private void bindView() {
        actionBarBackTv=findViewById(R.id.changlong_return);
        actionBarBackTv.setOnClickListener(this);
        actionCenterTv=findViewById(R.id.action_bar_text);
        actionCenterTv.setText(Utils.getString(R.string.长龙助手));
        helpIv=findViewById(R.id.changlong_help);
        helpIv.setOnClickListener(this);
        mViewPager=findViewById(R.id.changlong_fragment_content);
        actionBarBackTv.setOnClickListener(this);
        showMoneyLinear=findViewById(R.id.show_money_linear);
        showMoneyLinear.setOnClickListener(this);
//        eyeImg=findViewById(R.id.eye_img);
//        yueTv=findViewById(R.id.yue_textview);
        moneyTv=findViewById(R.id.money_textview);
        moneyTv.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.changlong_return:
            finish();
            break;
        case R.id.show_money_linear:
            getMoney();
            break;
        case R.id.money_textview:
            showMoneyLinear.setVisibility(View.VISIBLE);
            moneyTv.setVisibility(View.GONE);
            break;
        case R.id.changlong_help:
            View view = LayoutInflater.from(LongDragonActivity.this).inflate(R.layout.changlong_shuoming_pop,null);
            shuomingPop= new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            shuomingPop.setAnimationStyle(R.style.pop_scale_animation);
            popBackTv=view.findViewById(R.id.action_bar_return);
            popBarCenter=view.findViewById(R.id.action_bar_text);
            popBarCenter.setText(Utils.getString(R.string.长龙说明));
            shuomingPop.showAsDropDown(actionBarBackTv, Gravity.CENTER,0,0);
            popBackTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shuomingPop.dismiss();
                }
            });
            break;
    }
    }
    private void getMoney() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);

        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                BigDecimal amount = memberMoney.getBigDecimal("amount");
                moneyTv.setText("¥"+amount+"");
                showMoneyLinear.setVisibility(View.GONE);
                moneyTv.setVisibility(View.VISIBLE);
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    /*
    tab选中监听
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
