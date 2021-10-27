package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.lang.ref.SoftReference;

/**
 * windowSoftInputMode="adjustNothing" 时,监听键盘高度. (覆盖一层宽度为0,高度为match的pop,通过监听pop高度变化来获取键盘高度)
 */
public class HeightProvider extends PopupWindow implements ViewTreeObserver.OnGlobalLayoutListener  {
    private SoftReference<Activity>  activitySoftReference;
    private View rootView;
    private HeightListener listener;
    private int heightMax; // 记录popup内容区的最大高度

    public HeightProvider(SoftReference<Activity>  activitySoftReference) {
        super(activitySoftReference.get());
        this.activitySoftReference = activitySoftReference;
        if(activitySoftReference.get()==null){
            return;
        }
        // 基础配置
        rootView = new View(activitySoftReference.get());
        setContentView(rootView);

        // 监听全局Layout变化
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        setBackgroundDrawable(new ColorDrawable(0));

        // 设置宽度为0，高度为全屏
        setWidth(0);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);

        // 设置键盘弹出方式
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
    }

    public HeightProvider init() {
        if (!isShowing()&&activitySoftReference.get()!=null) {
            final View view = activitySoftReference.get().getWindow().getDecorView();
            // 延迟加载popupwindow，如果不加延迟就会报错
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(view!=null&&!activitySoftReference.get().isDestroyed()&&!activitySoftReference.get().isFinishing()&&activitySoftReference.get()!=null){
                        showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                    }
                }
            },200);
        }
        return this;
    }

    public HeightProvider setHeightListener(HeightListener listener) {
        this.listener = listener;
        return this;
    }


    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        if (rect.bottom > heightMax) {
            heightMax = rect.bottom;
        }

        // 两者的差值就是键盘的高度
        int keyboardHeight = heightMax - rect.bottom;
        if (listener != null) {
            listener.onHeightChanged(keyboardHeight);
        }
    }


    public interface HeightListener {
        void onHeightChanged(int height);
    }

}
