package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

//import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
//import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<String> titles=new ArrayList<>();
//    private List<SoftReference<Fragment>> fragments=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();

/*    public FragmentAdapter(FragmentManager fm, List<String> titles, List<SoftReference<Fragment>> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }  */
    public FragmentAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
  /*      SoftReference<Fragment> fragmentSoftReference = fragments.get(i);
        if(fragmentSoftReference.get()!=null){
            return fragmentSoftReference.get();
        }
        Fragment fragment = new Fragment();
        fragments.add(i,new SoftReference<>(fragment));
        return fragment;*/
        return  fragments.get(i);
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
