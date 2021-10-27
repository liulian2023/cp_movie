/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.R;

public class ActionBarUtil {

    public static void initMainActionbar(Activity activity,View view,int colorId){
        int[] colors = { ContextCompat.getColor(activity,colorId),  ContextCompat.getColor(activity,colorId)};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        view .setBackground(gradientDrawable);
        StatusBarUtil.setGradientColor(activity, view);
    }

    public static void initPaiHangBar(Activity activity,View view){
        int[] colors = { ContextCompat.getColor(activity,R.color.mPaihangColor1),     ContextCompat.getColor(activity,R.color.mPaihangColor2)};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        view .setBackground(gradientDrawable);
        StatusBarUtil.setGradientColor(activity, view);
    }
    public static void setGradientView(Activity activity,View view,int startColor,int endColor){
        int[] colors = { ContextCompat.getColor(activity,startColor),     ContextCompat.getColor(activity,endColor)};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        view .setBackground(gradientDrawable);
    }


    public static void initActionbar(Activity activity, LinearLayout actionLeftLinear, TextView actionTv, String titleStr){
        if(actionTv!=null&&actionLeftLinear!=null){
            actionTv.setText(titleStr);
            actionLeftLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity!=null&&!activity.isFinishing()){
                        activity.finish();
                    }
                }
            });
        }

    }
}
