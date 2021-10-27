///*
// * Copyright (c) 2019.  xxxx
// */
//
//package com.cambodia.zhanbang.xunbo.adapter.home_fragment_adapter;
//
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.fragment.app.FragmentStatePagerAdapter;
//
//
//import com.cambodia.zhanbang.xunbo.model.ChannelTabModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChannelTabAdapter extends FragmentStatePagerAdapter {
//    private List<ChannelTabModel> channelTabModelArrayList=new ArrayList<>();
//    private List<ChannelChildFragment> fragments=new ArrayList<>();
//    //    private List<SoftReference<AllChannelFragment>> fragments=new ArrayList<>();
//    /*    public ChannelTabAdapter(@NonNull FragmentManager fm, List<ChannelTabModel> channelTabModelArrayList, List<SoftReference<AllChannelFragment>> fragments) {
//            super(fm);
//            this.channelTabModelArrayList = channelTabModelArrayList;
//            this.fragments = fragments;
//        }*/
//
//    public ChannelTabAdapter(@NonNull FragmentManager fm, List<ChannelTabModel> channelTabModelArrayList, List<ChannelChildFragment> fragments) {
//        super(fm);
//        this.channelTabModelArrayList = channelTabModelArrayList;
//        this.fragments = fragments;
//    }
//
//    @Override
//    public Fragment getItem(int i) {
//        return fragments.get(i);
//    }
//
//    @Override
//    public int getCount() {
//        return channelTabModelArrayList.size();
//    }
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return channelTabModelArrayList.get(position).getTitle();
//    }
//}
