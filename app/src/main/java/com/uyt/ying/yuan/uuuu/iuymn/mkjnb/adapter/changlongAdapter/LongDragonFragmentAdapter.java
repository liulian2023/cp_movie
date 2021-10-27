

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class LongDragonFragmentAdapter extends FragmentPagerAdapter {
    private List<String> titles=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();

    public LongDragonFragmentAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
