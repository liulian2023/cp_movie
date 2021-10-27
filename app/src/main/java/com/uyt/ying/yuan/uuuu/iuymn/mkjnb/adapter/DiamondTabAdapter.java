
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.DiamondFragment;

import java.util.ArrayList;

public class DiamondTabAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titleList;
    private Context context;

    public DiamondTabAdapter(FragmentManager fm, ArrayList<String> titleList, Context context) {
        super(fm);
        this.titleList = titleList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return DiamondFragment.newInstance(position,titleList.get(position));
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
        View v = LayoutInflater.from(context).inflate(R.layout.diamond_tab_view_layout, null);
        TextView tv = v.findViewById(R.id.diamond_tab_tv);
        tv.setText(titleList.get(position));
        if (position == 0) {
           tv.setTextSize(16);
           tv.setTextColor(ContextCompat.getColor(context,R.color.default_color));
        }
        return v;
    }
}
