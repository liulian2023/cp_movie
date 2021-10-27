package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.game_report_fragment.GameReportFragment;
import com.uyt.ying.yuan.R;

import java.util.ArrayList;

public class GameReportTabAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titleList;
    private Context context;

    public GameReportTabAdapter(@NonNull FragmentManager fm, ArrayList<String> titleList, Context context) {
        super(fm);
        this.titleList = titleList;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return GameReportFragment.newInstance(context,position);
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
        tv.setTextColor(Color.parseColor("#ffffff"));
        if (position == 0) {
            tv.setTextSize(17);
        }
        return v;
    }
}
