/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

public class AnimUtils {

    public static void rotateAnim(View view){

        Animation anim =new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true); // 设置保持动画最后的状态
        anim.setDuration(500); // 设置动画时间
        anim.setInterpolator(new AccelerateInterpolator()); // 设置插入器
        view.startAnimation(anim);

    }

    //获取动画实例  旋转   x角度
    public static Animation getAnimation(int x){
        Animation rotateAnimation = new RotateAnimation(0f, x, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDetachWallpaper(true);

        return  rotateAnimation;
    }

    public static void setViewSize(Activity activity, View view,int width,int height) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
      //  int dpTop = dp2px( 10);
     //   int dpRight = dp2px( 10);
   //     int dpLeft=dp2px( 10);



        margin.setMargins(0, 0, 0, 0);

        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(margin);
        layoutParams.width = width;
        layoutParams.height= height;
        view.setLayoutParams(layoutParams);
    }


}
