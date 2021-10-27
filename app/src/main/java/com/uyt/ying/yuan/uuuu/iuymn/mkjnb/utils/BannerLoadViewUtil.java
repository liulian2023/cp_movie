package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;
import android.view.View;


import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter.BannerPackViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter.BannerViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BannerData;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BannerPackData;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import com.zhpan.bannerview.constants.PageStyle;
import com.zhpan.bannerview.indicator.CircleIndicatorView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * created  by xxxx on 2019/11/20.
 */
public class BannerLoadViewUtil {
    /**
     * 创建广告轮播图
     * @param context
     * @param mBannerViewPager
     * @param bannerDataList
     * @param showIndicator
     * @param Interval
     * @param canLoop
     * @param autoPlay
     */
    public static void createBannerView(Context context, BannerViewPager<BannerData, BannerViewHolder> mBannerViewPager,
                                        List<BannerData> bannerDataList, boolean showIndicator, int Interval, boolean canLoop, boolean autoPlay){
        mBannerViewPager
                .setIndicatorVisibility(showIndicator == true?View.VISIBLE:View.INVISIBLE)
                //轮播切换时间
                .setInterval(Interval)
                //是否开启循环
                .setCanLoop(canLoop)
                //是否开启自动轮播
                .setAutoPlay(autoPlay)
                //设置圆角
                .setRoundCorner(Utils.dp2px(7))
                //指示器颜色
                .setIndicatorColor(ContextCompat.getColor(context,R.color.white), ContextCompat.getColor(context,R.color.black))
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                //指示器位置
                .setIndicatorGravity(IndicatorGravity.END)
                //设置指示器的宽度/直径
                .setIndicatorWidth(10)
                //设置指示器样式( DASH:矩形  CIRCLE:圆点)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                //页面滚动时间
                .setScrollDuration(1000)
                //绑定banner视图,以及数据的加载方式
                .setHolderCreator(BannerViewHolder::new)
                //点击事件
                .setOnPageClickListener(position -> {
                    // BannerData bannerData = bannerDataList.get(position);
                    //页面跳转
                //    String url = bannerDataList.get(position).getHref();
                //    String id = bannerDataList.get(position).getId();
                //    RouteUtils.start2WebViewActivity(context,url,id);

                })
                //绑定数据源
                .create(bannerDataList);
    }


    public static void createMulBanner(Context context, BannerViewPager<BannerData, BannerViewHolder> mBannerViewPager,
                                       List<BannerData> bannerDataList, boolean showIndicator, int Interval, boolean canLoop, boolean autoPlay){

        mBannerViewPager.setIndicatorVisibility(showIndicator == true?View.VISIBLE:View.INVISIBLE)
                //轮播切换时间
                .setInterval(Interval)
                //是否开启循环
                .setCanLoop(canLoop)
                //是否开启自动轮播
                .setAutoPlay(autoPlay)
                //设置圆角
                .setRoundCorner(Utils.dp2px(7))
                //指示器颜色
                .setIndicatorColor(ContextCompat.getColor(context,R.color.white), ContextCompat.getColor(context,R.color.black))
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                //指示器位置
                .setIndicatorGravity(IndicatorGravity.END)
                //设置指示器的宽度/直径
                .setIndicatorWidth(10)
                //设置指示器样式( DASH:矩形  CIRCLE:圆点)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                //页面滚动时间
                .setScrollDuration(1000)
                //绑定banner视图,以及数据的加载方式
                .setHolderCreator(BannerViewHolder::new)
                .setPageMargin(Utils.dp2px(20))
                .setRevealWidth(Utils.dp2px(20))
                .setPageStyle(PageStyle.MULTI_PAGE)
                //点击事件
                .setOnPageClickListener(position -> {
                    // BannerData bannerData = bannerDataList.get(position);
                    //页面跳转
                    //    String url = bannerDataList.get(position).getHref();
                    //    String id = bannerDataList.get(position).getId();
                    //    RouteUtils.start2WebViewActivity(context,url,id);

                })
                //绑定数据源
                .create(bannerDataList);


    }

    public static void createCusBanner(Context context, BannerViewPager<BannerPackData, BannerPackViewHolder> mBannerViewPager,
                                       List<BannerPackData> bannerDataList, boolean showIndicator, int Interval, boolean canLoop, boolean autoPlay, CircleIndicatorView indicatorView){
      //  resetBannerViewPager(mBannerViewPager);
        mBannerViewPager.setIndicatorVisibility(showIndicator == true?View.VISIBLE:View.INVISIBLE)
                //轮播切换时间
                .setInterval(Interval)
                //是否开启循环
                .setCanLoop(canLoop)
                //是否开启自动轮播
                .setAutoPlay(autoPlay)
                //设置圆角
                .setRoundCorner(Utils.dp2px(7))
                //指示器颜色
                .setIndicatorColor(ContextCompat.getColor(context,R.color.white), ContextCompat.getColor(context,R.color.black))
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                //指示器位置
             //   .setIndicatorGravity(IndicatorGravity.END)
                //设置指示器的宽度/直径
            //    .setIndicatorWidth(10)
                //设置指示器样式( DASH:矩形  CIRCLE:圆点)
            //    .setIndicatorStyle(IndicatorStyle.CIRCLE)
                //页面滚动时间
            //    .setScrollDuration(1000)
                //绑定banner视图,以及数据的加载方式
                .setHolderCreator(BannerPackViewHolder::new)
                .setIndicatorView(indicatorView)
                //点击事件
                .setOnPageClickListener(position -> {
                    ToastUtil.showToast(""+bannerDataList.get(position).getId());
                })
                //绑定数据源
                .create(bannerDataList);
    }

    public static void  resetBannerViewPager(BannerViewPager<BannerPackData, BannerPackViewHolder> mBannerViewPager) {
        try {
            Field mIndicatorView = BannerViewPager.class.getDeclaredField("mIndicatorView");
            mIndicatorView.setAccessible(true);
            mIndicatorView.set(mBannerViewPager, null);
            Field isCustomIndicator = BannerViewPager.class.getDeclaredField("isCustomIndicator");
            isCustomIndicator.setAccessible(true);
            isCustomIndicator.set(mBannerViewPager, false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
