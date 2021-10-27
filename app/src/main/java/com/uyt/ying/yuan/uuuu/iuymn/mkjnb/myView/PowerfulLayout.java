/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;

import androidx.customview.widget.ViewDragHelper;

public class PowerfulLayout extends FrameLayout {
    // 屏幕宽高
    private int screenHeight;
    private int screenWidth;
    private ViewDragHelper mDragHelper;
    private long lastMultiTouchTime;// 记录多点触控缩放后的时间
    private ScaleGestureDetector mScaleGestureDetector = null;
    // private View view;
    private int downX;//手指按下的x坐标值
    private int downY;//手指按下的y坐标值
    private boolean needToHandle=true;
    private int moveX;
    private int moveY;
    private float scale;//缩放比例
    private float preScale = 1f;// 默认前一次缩放比例为1

    public PowerfulLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PowerfulLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PowerfulLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mDragHelper = ViewDragHelper.create(this, callback);
        mScaleGestureDetector = new ScaleGestureDetector(context,
                new ScaleGestureListener());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = getMeasuredWidth();
        screenHeight = getMeasuredHeight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean b = mDragHelper.shouldInterceptTouchEvent(ev);// 由mDragHelper决定是否拦截事件，并传递给onTouchEvent
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointerCount = event.getPointerCount(); // 获得多少点
        if (pointerCount > 1) {// 多点触控，
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    needToHandle=false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_POINTER_2_UP://第二个手指抬起的时候
                    lastMultiTouchTime = System.currentTimeMillis();
                    needToHandle=true;
                    break;
                default:
                    break;
            }
            return mScaleGestureDetector.onTouchEvent(event);//让mScaleGestureDetector处理触摸事件
        } else {
            //在500ms后解锁控件的左右滑动
            //防止缩放后控件乱动
            long currentTimeMillis = System.currentTimeMillis();
            if ( currentTimeMillis - lastMultiTouchTime > 500&&needToHandle){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = (int)event.getX();
                        downY = (int)event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX = (int) event.getX();
                        moveY = (int) event.getY();
                        scrollBy(downX-moveX, downY-moveY);
                        downX = moveX;
                        downY = moveY;
                    default:
                        break;
                }
                return true;
            }
        }
        return false;
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        /**
         * 用于判断是否捕获当前child的触摸事件
         *
         * @param child
         *            当前触摸的子view
         * @param pointerId
         * @return true就捕获并解析；false不捕获
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (preScale > 1.5)
                return true;
            return false;
        }

        /**
         * 控制水平方向上的位置
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            if (left < (screenWidth - screenWidth * preScale) / 2)
                left = (int) (screenWidth - screenWidth * preScale) / 2;// 限制mainView可向左移动到的位置
            if (left > (screenWidth * preScale - screenWidth) / 2)
                left = (int) (screenWidth * preScale - screenWidth) / 2;// 限制mainView可向右移动到的位置
            return left;
        }

        public int clampViewPositionVertical(View child, int top, int dy) {

            if (top < (screenHeight - screenHeight * preScale) / 2) {
                top = (int) (screenHeight - screenHeight * preScale) / 2;// 限制mainView可向上移动到的位置
            }
            if (top > (screenHeight * preScale - screenHeight) / 2) {
                top = (int) (screenHeight * preScale - screenHeight) / 2;// 限制mainView可向上移动到的位置
            }
            return top;
        }

    };

    public class ScaleGestureListener implements
            ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float previousSpan = detector.getPreviousSpan();// 前一次双指间距
            float currentSpan = detector.getCurrentSpan();// 本次双指间距
            if (currentSpan < previousSpan) {
                // 缩小
                scale = preScale - (previousSpan - currentSpan) / 1000;
            } else {
                // 放大
                scale = preScale + (currentSpan - previousSpan) / 1000;
            }
            // 缩放view
            if (scale>0.5 && scale<1.5) {
                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).setScaleX(scale);
                    getChildAt(i).setScaleY(scale);
//                  lastMultiTouchTime = System.currentTimeMillis();
                }
            }
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            // 一定要返回true才会进入onScale()这个函数
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            preScale = scale;// 记录本次缩放比例
            lastMultiTouchTime = System.currentTimeMillis();// 记录双指缩放后的时间
        }
    }

}

