package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 拦截所有触碰事件的recyclerView,(所有触碰事件在当前view处理完毕,并禁用父类的事件拦截功能,让父类没机会参与事件)
 */
public class MyRecycleView extends RecyclerView {
    float startX = 0f;
    float startY = 0f;
    public MyRecycleView(@NonNull Context context) {
        super(context);
    }

    public MyRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                 startX = ev.getX();
                 startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //来到新的坐标
                float endX = ev.getX();
                float endY = ev.getY();
                //计算偏移量
                float distanceX = endX - startX;
                float distanceY = endY - startY;
/*                //判断滑动方向
                if(Math.abs(distanceX) > Math.abs(distanceY)){
                    //水平方向滑动
                    if(distanceX >0){
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }

//                   ，当滑动到ViewPager的最后一个页面，并且是从右到左滑动
//
                    else if( distanceX <0){
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
//                    其他,中间部分

                    else{
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }else{
                    //竖直方向滑动
                    getParent().requestDisallowInterceptTouchEvent(true);
                }*/
                //只要当前触摸到recycleView无论哪个方向都禁止父类的事件拦截功能,不让父类处理当前的事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);

    }
}
