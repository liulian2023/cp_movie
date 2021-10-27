package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.receiver.NetWorkStateReceiver;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class NetWorkPopwindow {
public static PopupWindow popupWindow;



    public  static  void initPop(){
    Context instance = MyApplication.getInstance();
    View view= LayoutInflater.from(instance).inflate(R.layout.no_internet,null);
    popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,true);
    TextView reLoadTv=view.findViewById(R.id.tv_reload);
    reLoadTv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    });
    //  popupWindow.showAtLocation(contentView, Gravity.CENTER,0,0);
    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            if(! NetWorkStateReceiver.haveNet){
                ActivityUtil instance1 = ActivityUtil.getInstance();
                Activity topActivity = instance1.currentActivity();
                View contentView = Utils.getContentView(topActivity);
//                handler.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
                if(topActivity!=null&&!topActivity.isFinishing()){
                    NetWorkPopwindow.popupWindow.showAtLocation(contentView, Gravity.CENTER,0,0);
                }
//                    }
//                },500);

            }
        }
    });

}


}
