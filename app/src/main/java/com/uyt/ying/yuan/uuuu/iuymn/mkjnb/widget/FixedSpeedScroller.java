/*
 * Copyright (c) 2019.  xxxx
 */
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;


/**
 * <pre>
 *     @author 杨充
 *     blog  : https://github.com/yangchong211
 *     time  : 2019/6/20
 *     desc  : 自定义Scroller，控制滚动速率
 *     revise:
 * </pre>
 */
public class FixedSpeedScroller extends Scroller {

    private int mDuration = 1500;
    private long mRecentTouchTime;

    FixedSpeedScroller(Context context, Interpolator interpolator, long touchTime) {
        super(context, interpolator);
        this.mRecentTouchTime = touchTime;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // 如果手工滚动,则加速滚动
        int time = 2000;
        if (System.currentTimeMillis() - mRecentTouchTime > time) {
            duration = mDuration;
        } else {
            duration /= 2;
        }
        super.startScroll(startX, startY, dx, dy, duration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    void setDuration(int time) {
        mDuration = time;
    }

}
