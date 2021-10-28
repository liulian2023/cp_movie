package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.KJCountDownTimer;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyCountDownTimer;

import java.text.SimpleDateFormat;
import java.util.Date;

//倒计时的统一管理类  内部与外部通信接口，给客户端提供不同样式的倒计时对象和TextView对象
public class TimerUtils {
    public static final int KJ_STYLE = 0;
  //  public static final int VIP_STYLE = 1;
    public static final int DEFAULT_STYLE = 1;

    public static final String TIME_STYLE_ONE = "HH:mm:ss";
    public static final String TIME_STYLE_TWO = "yyyy-MM-dd HH:mm:ss";

    public static MyCountDownTimer getTimer(int style, Context mContext, long mGapTime, String mTimePattern, int mDrawableId){
        MyCountDownTimer mCountDownTimer = null;
        switch (style){
            case KJ_STYLE:
                mCountDownTimer = new KJCountDownTimer(mContext,mGapTime,mTimePattern,mDrawableId);
                break;
          /*  case VIP_STYLE:
                mCountDownTimer = new VIPCountDownTimer(mContext,mGapTime,mTimePattern,mDrawableId);
                break;*/
            case DEFAULT_STYLE:
                mCountDownTimer = new MyCountDownTimer(mContext,mGapTime,mTimePattern,mDrawableId);
                break;
        }
        return mCountDownTimer;
    }
    //得到倒计时字符串中的数值块部分
    public static String[] getNumInTimerStr(String mTimerStr){
        return mTimerStr.split("[^\\d]");
    }
    //得到倒计时中字符串中的非数值的字符串,并把数值过滤掉重新组合成一个字符串，并把字符串拆分字符数组，也就是保存倒计时中间的间隔
    public static char[] getNonNumInTimerStr(String mTimerStr){
        return mTimerStr.replaceAll("\\d","").toCharArray();
    }
    //设置字体颜色
    public static ForegroundColorSpan getTextColorSpan(String color){
        ForegroundColorSpan mSpan = null;
        if (mSpan == null){
            mSpan = new ForegroundColorSpan(Color.parseColor(color));
        }
        return mSpan;
    }
    //设置内容的Span
    public static void setContentSpan(SpannableString mSpan, Object span, int start,
                                      int end) {
        mSpan.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    //获取当前"HH:mm:ss"
    public static String getTimeStyleOne(){
      //  Long time_now = System.currentTimeMillis()/1000;
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE_ONE);
        //获取String类型的时间
        String time_now = sdf.format(date);
        return time_now;
    }
    public static String getTimeStyleTwo(){
        //  Long time_now = System.currentTimeMillis()/1000;
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_STYLE_ONE);
        //获取String类型的时间
        String time_now = sdf.format(date);
        return time_now;
    }


}

