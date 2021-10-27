
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment.RankFragment;
import com.uyt.ying.yuan.R;

import java.util.ArrayList;

public class RankTabAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titleList;
    private Context context;

    public RankTabAdapter(FragmentManager fm, ArrayList<String> titleList, Context context) {
        super(fm);
        this.titleList = titleList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        return RankFragment.newInstance(position,titleList.get(position));
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
        View v = LayoutInflater.from(context).inflate(R.layout.rank_tab_view_layout, null);
        TextView tv = v.findViewById(R.id.home_tab_title_tv);
        tv.setText(titleList.get(position));
        if (position == 0) {
        tv.setTextSize(17);
        }
        return v;
    }
}
