//package com.example.administrator.aaa.adapter;
//
//import android.media.Image;
//import androidx.viewpager.widget.PagerAdapter;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//import android.widget.ImageView;
//
//import java.util.List;
//
///**
// *  首页viewPager适配器
// */
//public class HomePagerAdapter extends android.support.v4.view.PagerAdapter {
//    public List<ImageView> mList;
//    @Override
//    public int getCount() {
//        // 返回整数的最大值
//        return Integer.MAX_VALUE;
//    }
//
//    public HomePagerAdapter(List<ImageView> mList) {
//        this.mList = mList;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        // 修改position,因为getCount()设为无限值, 为保证每个item都有值, 取余
//        position = position % mList.size();
//        //判断当前view有没有父View,有的话先移除,否则会报错;
//       View view =  mList.get(position);
//        ViewParent parent = view.getParent();
//        if(parent!=null){
//            container.removeAllViews();
//        }
//        // 将图片控件添加到容器
//        container.addView(mList.get(position));
//
//        return mList.get(position);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        //super.destroyItem(container, position, object);
//
//        position = position % mList.size();
//        container.removeView(mList.get(position));
//    }
//}