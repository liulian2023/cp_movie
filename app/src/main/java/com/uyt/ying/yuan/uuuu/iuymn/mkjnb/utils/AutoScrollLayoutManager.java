package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;
/**
 * 设置RecycelView自动滚动的速度
 */

import android.content.Context;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class AutoScrollLayoutManager extends LinearLayoutManager {
    public AutoScrollLayoutManager(Context context) {
        super(context);
    }

    public AutoScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public AutoScrollLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    //  修改系统默认的滚动速度需要实现该方法；
    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int
            position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Nullable
                    @Override
                    public PointF computeScrollVectorForPosition(int targetPosition) {
                        return AutoScrollLayoutManager.this.computeScrollVectorForPosition
                                (targetPosition);
                    }

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        // 计算滑动每个像素需要的时间，这里应该与屏幕适配；
                        return 20f / displayMetrics.density;
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}


