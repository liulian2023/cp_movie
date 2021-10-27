/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoSlidingViewPager extends ViewPager {
    private boolean slide = false;// false 禁止ViewPager左右滑动。

    public NoSlidingViewPager(@NonNull Context context) {
        super(context);
    }

    public NoSlidingViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setScrollable(boolean slide) {
        this.slide = slide;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return slide;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return slide;
    }

}
