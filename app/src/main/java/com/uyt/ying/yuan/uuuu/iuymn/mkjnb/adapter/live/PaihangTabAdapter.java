package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.SortChildFragment;

import java.util.List;

public class PaihangTabAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<String> titlesList;

    public PaihangTabAdapter(@NonNull FragmentManager fm, Context mContext, List<String> titlesList) {
        super(fm);
        this.mContext = mContext;
        this.titlesList = titlesList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment = SortChildFragment.newInstance(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return null == titlesList ? 0: titlesList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }


}
