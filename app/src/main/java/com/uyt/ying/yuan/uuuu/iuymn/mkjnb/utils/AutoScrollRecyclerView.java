package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * recycleView禁止滑动相关(只用于滚动展示)
 */
public class AutoScrollRecyclerView extends RecyclerView {
    public AutoScrollRecyclerView(@NonNull Context context) {
        super(context);
    }
    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // 拦截事件；
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        // 拦截触摸事件；
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // 消费事件；
        return true;
    }


}
