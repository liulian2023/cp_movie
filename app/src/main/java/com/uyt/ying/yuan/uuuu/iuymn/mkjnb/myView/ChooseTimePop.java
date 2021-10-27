package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
        /*
        开奖结果中 时间选择popwindow
         */
public class ChooseTimePop  {
    private static PopupWindow chooseTimePop;
    private static TextView timeOne;
    private static TextView timeTwo;
    private static TextView timeThree;
    private static TextView timeFour;
    private static TextView timeFive;
    private static TextView timeSix;
    private static TextView timeSeven;
    private static String todayDate;
    private static View backgroundView;

    public static void initPop(final Context context, final ImageView imageView, final OnItemClidkListener onItemClidkListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.race_open_result_time_pop, null);
        backgroundView=view.findViewById(R.id.background_view);//pop下面的阴影部分
        timeOne =view.findViewById(R.id.time_one);
        timeTwo =view.findViewById(R.id.time_two);
        timeThree =view.findViewById(R.id.time_three);
        timeFour =view.findViewById(R.id.time_four);
        timeFive =view.findViewById(R.id.time_five);
        timeSix =view.findViewById(R.id.time_six);
        timeSeven =view.findViewById(R.id.time_seven);
        timeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(onItemClidkListener!=null){
                        onItemClidkListener.onItemClick((TextView) v,chooseTimePop);
                    }
            }
        });
        timeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClidkListener!=null){
                    onItemClidkListener.onItemClick((TextView) v,chooseTimePop);
                }
            }
        });
        timeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClidkListener!=null){
                    onItemClidkListener.onItemClick((TextView) v,chooseTimePop);
                }
            }
        });
        timeFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClidkListener!=null){
                    onItemClidkListener.onItemClick((TextView) v,chooseTimePop);
                }
            }
        });
        timeFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClidkListener!=null){
                    onItemClidkListener.onItemClick((TextView) v,chooseTimePop);
                }
            }
        });
        timeSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClidkListener!=null){
                    onItemClidkListener.onItemClick((TextView) v,chooseTimePop);
                }
            }
        });
        timeSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClidkListener!=null){
                    onItemClidkListener.onItemClick((TextView) v,chooseTimePop);
                }
            }
        });
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        todayDate = simpleDateFormat.format(date);//今天日期
        Date dateAfter1 = DateUtil.getDateBefore(date, 1);//1天前的日期
        Date dateAfter2 = DateUtil.getDateBefore(date, 2);//2天前的日期
        Date dateAfter3 = DateUtil.getDateBefore(date, 3);//3天前的日期
        Date dateAfter4 = DateUtil.getDateBefore(date, 4);//4天前的日期
        Date dateAfter5 = DateUtil.getDateBefore(date, 5);//5天前的日期
        Date dateAfter6 = DateUtil.getDateBefore(date, 6);//6天前的日期
        timeOne.setText(todayDate);
        timeTwo.setText(simpleDateFormat.format(dateAfter1));
        timeThree.setText(simpleDateFormat.format(dateAfter2));
        timeFour.setText(simpleDateFormat.format(dateAfter3));
        timeFive.setText(simpleDateFormat.format(dateAfter4));
        timeSix.setText(simpleDateFormat.format(dateAfter5));
        timeSeven.setText(simpleDateFormat.format(dateAfter6));
        chooseTimePop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        chooseTimePop.setAnimationStyle(R.style.popupAnimationNormol);//设置进出动画
        backgroundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTimePop.dismiss();//点击阴影部分,弹回pop(因为这里的布局是MATCH_PARENT,点击弹回,相当于响应外部点击)
            }
        });
        //pop的弹回监听
        chooseTimePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {//pop弹回时,将图片旋转恢复
                imageView.setPivotX(imageView.getWidth()/2);
                imageView.setPivotY(imageView.getHeight()/2);//支点在图片中心
                imageView.setRotation(0);
                darkenBackground((Activity) context,1f);
            }
        });
    }

    public static void showTimePop(Context context,View targetView,ImageView imageView){
        if(chooseTimePop!=null){
            chooseTimePop.showAsDropDown(targetView,0,0,Gravity.BOTTOM);
        }
//        darkenBackground((Activity) context,0.5f);
        backgroundView.getBackground().setAlpha(50);
        imageView.setPivotX(imageView.getWidth()/2);
        imageView.setPivotY(imageView.getHeight()/2);//支点在图片中心
        imageView.setRotation(180);
    }

    /**
     * 设置背景亮度
     * @param activity activity实例
     * @param bgcolor 亮度值(0f-1f)值越小,背景越暗
     */
    public static void darkenBackground(Activity activity, Float bgcolor) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgcolor;
        if(bgcolor==1f){
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else {

            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        activity.getWindow().setAttributes(lp);
    }

    public static interface OnItemClidkListener{
        void onItemClick(TextView view,PopupWindow popupWindow);
    }
    private  ChooseTimePop. OnItemClidkListener onItemClidkListener =null;
    public void setOnItemClidkListener(OnItemClidkListener onItemClidkListener) {
        this.onItemClidkListener = onItemClidkListener;
    }

}
