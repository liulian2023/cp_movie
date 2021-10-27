/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class RefreshUtil {
    /**
     * 下拉刷新 上拉加载
     * @param context
     * @param refreshLayout 刷新控件
     * @param onRefreshLoadMoreLintener 刷新加载回调
     */
    public static void initRefreshLoadMore(SoftReference<Context> context, RefreshLayout refreshLayout, OnRefreshLoadMoreLintener onRefreshLoadMoreLintener){
        if(context.get()==null){
            return;
        }
        Activity activity = (Activity) context.get();
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setRefreshFooter(new ClassicsFooter(context.get()));
        refreshLayout.setRefreshHeader(new ClassicsHeader(context.get()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(onRefreshLoadMoreLintener!=null&&!activity.isFinishing()){
                    onRefreshLoadMoreLintener.onRefresh(refreshLayout);
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(onRefreshLoadMoreLintener!=null&&!activity.isFinishing()){
                    onRefreshLoadMoreLintener.onLoadMore(refreshLayout);
                }
            }
        });
    }


    /**
     *  上拉加载 没有下拉刷新
     * @param context
     * @param refreshLayout 刷新控件
     * @param onRefreshLoadMoreLintener 刷新加载回调
     */
    public static void initLoadMore(SoftReference<Context> context, RefreshLayout refreshLayout, OnRefreshLoadMoreLintener onRefreshLoadMoreLintener){
        if(context.get()==null){
            return;
        }
        Activity activity = (Activity) context.get();
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setRefreshFooter(new ClassicsFooter(context.get()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(onRefreshLoadMoreLintener!=null&&!activity.isFinishing()){
                    onRefreshLoadMoreLintener.onLoadMore(refreshLayout);
                }
            }
        });
    }
    /**
     *  下拉刷新 没有上拉加载
     * @param context
     * @param refreshLayout 刷新控件
     * @param onRefreshLoadMoreLintener 刷新加载回调
     */
    public static void initRefresh(SoftReference<Context> context, RefreshLayout refreshLayout, OnRefreshLoadMoreLintener onRefreshLoadMoreLintener){
        if(context.get()==null){
            return;
        }
        Activity activity = (Activity) context.get();
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setRefreshHeader(new ClassicsHeader(context.get()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(onRefreshLoadMoreLintener!=null&&!activity.isFinishing()){
                    onRefreshLoadMoreLintener.onRefresh(refreshLayout);
                }
            }
        });
    }

    /**
     * 有刷新加载的请求,成功时的系列操作
     * @param pageNum  没用到 可以去掉
     * @param refreshLayout 刷新控件
     * @param loadingLinear 加载中视图
     * @param nodataLinear  没有数据的空视图
     * @param size  判断有无数据的size(一般直接传入数据list的size)
     * @param isLoadMore 上拉加载时的调用
     * @param isRefresh  下拉刷新时的调用
     * @param list  recycleView的数据容器
     */
    public static void success(int pageNum, RefreshLayout refreshLayout, ConstraintLayout loadingLinear, LinearLayout nodataLinear, int size, boolean isLoadMore, boolean isRefresh, ArrayList<?> list) {
        if(!isLoadMore){
            //不是加载更多时的每次调用都清空数据,初始化refresLayout加载更多的状态
            list.clear();
            pageNum=1;
            if(refreshLayout!=null){
                refreshLayout.resetNoMoreData();
            }

            if(size==0){
                if(isRefresh){
                    if(refreshLayout!=null){
                        refreshLayout.finishRefreshWithNoMoreData();
                    }
                }
                if(nodataLinear!=null){
                    nodataLinear.setVisibility(View.VISIBLE);
                }
            }else {
                if(isRefresh){
                    if(refreshLayout!=null){
                        refreshLayout.finishRefresh();
                    }

                }
                if(nodataLinear!=null){
                    nodataLinear.setVisibility(View.GONE);
                }
            }

        }else {
            //加载更多时调用
            if(size==0){
                refreshLayout.finishLoadMoreWithNoMoreData();
            }else {
                refreshLayout.finishLoadMore();
            }
        }
    }



    /**
     * 有刷新加载的请求,失败时的系列操作
     * @param isrefresh 是否是下拉刷新时的调用
     * @param isloadmore 是否是上拉加载时的调用
     * @param pageNum  分页的页数
     * @param refreshLayout  刷新控件
     */
    public static void fail(boolean isrefresh, boolean isloadmore, int pageNum, RefreshLayout refreshLayout){
        if(isloadmore){
            pageNum--;
            if(refreshLayout!=null){

                refreshLayout.finishLoadMore(false);
            }
        }
        if(isrefresh){
            if(refreshLayout!=null){
                refreshLayout.finishRefresh(false);
            }
        }
    }



    /**
     * 刷新加载的回调
     */
    public  interface  OnRefreshLoadMoreLintener{
        void onRefresh(RefreshLayout refreshLayout);
        void onLoadMore(RefreshLayout refreshLayout);
    }

}
