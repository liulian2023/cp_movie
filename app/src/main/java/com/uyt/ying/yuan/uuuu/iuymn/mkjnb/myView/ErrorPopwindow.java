

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;

public class ErrorPopwindow {
public  PopupWindow popupWindow;
public String url;

    public ErrorPopwindow( String url) {
        this.url = url;
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void setPopupWindow(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }

    public  void initPop(){
    Context instance = MyApplication.getInstance();
    View view= LayoutInflater.from(instance).inflate(R.layout.no_internet,null);
    popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,true);
    TextView reLoadTv=view.findViewById(R.id.tv_reload);
    reLoadTv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            ActivityUtil instance1 = ActivityUtil.getInstance();
            Activity topActivity = instance1.currentActivity();
            if(topActivity instanceof BaseActivity){
//                ((BaseActivity) topActivity).errorRefresh(url);
            }
        }
    });


}


}
