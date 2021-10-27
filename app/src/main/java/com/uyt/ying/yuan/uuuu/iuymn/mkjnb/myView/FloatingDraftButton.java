package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.azhon.appupdate.utils.ScreenUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class FloatingDraftButton extends AppCompatImageView implements View.OnTouchListener {
    int lastX, lastY;
    int originX, originY;
    final int screenWidth ;
    final int screenHeight ;
    private ArrayList<ImageView> imageViewButtons = new ArrayList<ImageView>();

    public FloatingDraftButton(Context context) {
        this(context, null);
    }

    public FloatingDraftButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingDraftButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = ScreenUtil.getWith(context);
        screenHeight = ScreenUtil.getHeight(context);
        setOnTouchListener(this);
    }
    //注册归属的imageView
    public void registerButton(ImageView imageView){
        imageViewButtons.add(imageView);
    }
    public ArrayList<ImageView> getButtons(){
        return imageViewButtons;
    }
    public int getButtonSize(){
        return imageViewButtons.size();
    }
    //是否可拖拽  一旦展开则不允许拖拽
    public boolean isDraftable(){
        for(ImageView btn: imageViewButtons){
            if(btn.getVisibility() == View.VISIBLE ){
                return false;
            }
        }
        return true;
    }
    //当被拖拽后其所属的imageView 也要改变位置
    private void slideButton(int l, int t, int r, int b){
        for(ImageView imageView: imageViewButtons){
            imageView.layout(l, t, r, b);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(!isDraftable()){
            return false;
        }
        int ea = event.getAction();
        switch (ea) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();// 获取触摸事件触摸位置的原始X坐标
                lastY = (int) event.getRawY();
                originX = lastX;
                originY = lastY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                int l = v.getLeft() + dx;
                int b = v.getBottom() + dy;
                int r = v.getRight() + dx;
                int t = v.getTop() + dy;
                // 下面判断移动是否超出屏幕
                if (l < 0) {
                    l = 0;
                    r = l + v.getWidth();
                }
                if (t < 0) {
                    t = 0;
                    b = t + v.getHeight();
                }
                if (r > screenWidth) {
                    r = screenWidth;
                    l = r - v.getWidth();
                }
                if (b > screenHeight) {
                    b = screenHeight;
                    t = b - v.getHeight();
                }
                v.layout(l, t, r, b);
                slideButton(l,t,r,b);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                v.postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                int distance = (int) event.getRawX() - originX + (int)event.getRawY() - originY;
                Utils.logE("DIstance",distance+"");
                if (Math.abs(distance)<20) {
                    //当变化太小的时候什么都不做 OnClick执行
                }else {
                    return true;
                }
                break;
        }
        return false;

    }


}
