package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决ScrollView嵌套GridView，GridView显示不全的问题
 * 注意：ScrollView嵌套GridView时，如果GridView很长超出了屏幕的高度，
 * 那么ScrollView会自动滚动到GridView的顶部，
 * 但是我们需要默认在整体页面顶部，所以在初始化的时候就让ScrollView获得焦点，滚动条自然就显示到顶部了。
 * scrollView.setFocusable(true);
 * scrollView.setFocusableInTouchMode(true);
 * scrollView.requestFocus();
 */


public class GridViewForScrollView extends GridView {

    public GridViewForScrollView(Context context) {
        super(context);
    }

    public GridViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重写onMeasure方法，重新计算高度，达到使GridView适应ScrollView的效果
     *
     * @param widthMeasureSpec  宽度测量规则
     * @param heightMeasureSpec 高度测量规则
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Integer.MAX_VALUE:表示int类型能够表示的最大值，值为2的31次方-1
        //>>2:右移N位相当于除以2的N的幂
        //MeasureSpec.AT_MOST：子布局可以根据自己的大小选择任意大小的模式
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }


}
