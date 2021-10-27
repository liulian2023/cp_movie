

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.Dragon_fragmemt.DragonBetNoteFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.Dragon_fragmemt.DragonBetFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.HomeTwoFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity.LongGragonPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter.LongDragonFragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
/*
长龙助手
 */

public class LongDragonFragment extends BaseFragment implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
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
    public View view;
   public HomeTwoFragment homeTwoFragment;
   public int position;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_chang_long,null);
        user_id= SharePreferencesUtil.getLong(getContext(),"user_id",0l);
        bindView(view);
        initTabViewPager(view);//tabLayout viaewPager初始化
        Fragment parentFragment = getParentFragment();
        if(parentFragment instanceof HomeTwoFragment){
            homeTwoFragment = (HomeTwoFragment) parentFragment;
        }
        position=getArguments().getInt("position");
        return view;
    }

    public static LongDragonFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        LongDragonFragment longDragonFragment = new LongDragonFragment();
        bundle.putInt("position", position);
        longDragonFragment.setArguments(bundle);
        return longDragonFragment;
    }
    private void initTabViewPager(View view) {
        titles.add(Utils.getString(R.string.我要)+Utils.getString(R.string.投注));
        titles.add(Utils.getString(R.string.最近)+Utils.getString(R.string.投注));
        mTab=view.findViewById(R.id.mtab);
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
        LongDragonFragmentAdapter fragmentAdapter = new LongDragonFragmentAdapter(getChildFragmentManager(), titles, fragmentArrayList);
        mViewPager.setAdapter(fragmentAdapter);
        mTab.setupWithViewPager(mViewPager);

    }

    private void bindView(View view) {
        actionBarBackTv=view.findViewById(R.id.changlong_return);
        actionBarBackTv.setOnClickListener(this);
        actionCenterTv=view.findViewById(R.id.action_bar_text);
        actionCenterTv.setText(Utils.getString(R.string.长龙助手));
        helpIv=view.findViewById(R.id.changlong_help);
        helpIv.setOnClickListener(this);
        mViewPager=view.findViewById(R.id.changlong_fragment_content);
        actionBarBackTv.setOnClickListener(this);
        showMoneyLinear=view.findViewById(R.id.show_money_linear);
        showMoneyLinear.setOnClickListener(this);
//        eyeImg=view.findViewById(R.id.eye_img);
//        yueTv=view.findViewById(R.id.yue_textview);
        moneyTv=view.findViewById(R.id.money_textview);
        moneyTv.setOnClickListener(this);

    }
    public void showToast(){
        if(mViewPager.getCurrentItem()==0){
            showToast(Utils.getString(R.string.长龙列表已更新,下拉获取最新长龙列表));
        }
    }
    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.changlong_return:
            getActivity().finish();
            break;
        case R.id.show_money_linear:
            if(LoginIntentUtil.isLogin(getContext())){

                getMoney();
            }else {
                LoginIntentUtil.toLogin(getActivity());
            }
            break;
        case R.id.money_textview:
            showMoneyLinear.setVisibility(View.VISIBLE);
            moneyTv.setVisibility(View.GONE);
            break;
        case R.id.changlong_help:
            shuomingPop= new LongGragonPop(getContext(),LongDragonFragment.this);
            shuomingPop.showAtLocation(moneyTv,Gravity.CENTER,0,0);
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

    public HomeTwoFragment getHomeTwoFragment() {
        return homeTwoFragment;
    }

    public int getPosition() {
        return position;
    }
}
