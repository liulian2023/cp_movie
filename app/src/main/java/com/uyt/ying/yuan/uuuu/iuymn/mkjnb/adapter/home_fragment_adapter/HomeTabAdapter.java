/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment.HotFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment.HotFragment2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment.LongDragonFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.AtyFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class HomeTabAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titleList;
    private Context context;

    public HomeTabAdapter(FragmentManager fm, ArrayList<String> titleList, Context context) {
        super(fm);
        this.titleList = titleList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        String appHomeVersion = Utils.getHomeLogo("appHomeVersion");// appHomeVersion==0 旧版首页  1 新版首页
        if(i==0){
            if(appHomeVersion.equals("0")){
                fragment = HotFragment.newInstance(i);
            }else if(appHomeVersion.equals("1")){
                fragment =  HotFragment2.newInstance(i);
            }else {
                fragment = HotFragment.newInstance(i);
            }
        }else if(i==1){
            fragment =  new AtyFragment();
        }else if(i==2){
            fragment =   LongDragonFragment.newInstance(i);
        }else {
            fragment =  new AtyFragment();

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
    //自定义tab item布局
    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_tab_view_layout, null);
        TextView tv = v.findViewById(R.id.home_tab_title_tv);
        tv.setText(titleList.get(position));
        if (position == 0) {
            tv.setTextSize(17);
        }
        return v;
    }
}
