package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.uyt.ying.yuan.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public  class PopupWindowUtil {
    static View mContentView = null;
    static PopupWindow mPopUpWindow=null;
    static LinearLayout linearLayout=null;
    static RefreshLayout mRefreshLayout=null;
    public static void showShareWindow(final Activity activity){

        if(mContentView == null){
            mContentView = LayoutInflater.from(activity).inflate(R.layout.progress_popup_window, null);
            linearLayout=mContentView.findViewById(R.id.popupwindow);
            Drawable background = linearLayout.getBackground();
            background.setAlpha(30);//设置透明度
        }
        if(mPopUpWindow == null){
            mPopUpWindow = new PopupWindow(mContentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);//实例化popupWindow
            mPopUpWindow.setOutsideTouchable(false);
        }
        if(!activity.isFinishing()){
        mPopUpWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, -200);
        }
    }
    public static void disMiss(Activity activity){

        if(mContentView == null){
            mContentView = LayoutInflater.from(activity).inflate(R.layout.progress_popup_window, null);
            linearLayout=mContentView.findViewById(R.id.popupwindow);
            Drawable background = linearLayout.getBackground();
            background.setAlpha(30);
        }
        if(mPopUpWindow == null){
            mPopUpWindow = new PopupWindow(mContentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);//实例化popupWindow
            mPopUpWindow.setOutsideTouchable(false);
        }
        mPopUpWindow.dismiss();
    }

    public  static void refreshLoadMoreListener(Activity activity){
        if(mRefreshLayout==null){
            mRefreshLayout=activity.findViewById(R.id.refresh);
            mRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));
            //设置 Footer 为 经典样式
            mRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
            mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        }
    }
    public static void darkenBackground(Activity activity,Float bgcolor) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = bgcolor;
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            activity.getWindow().setAttributes(lp);
    }
    public static interface onDisMissListener{
        void onDisMissListener(PopupWindow popupWindow);
    }
    PopupWindowUtil.onDisMissListener disMissListener =null;

    public void setOnDisMissListener(onDisMissListener disMissListener) {
        this.disMissListener = disMissListener;
    }

}
