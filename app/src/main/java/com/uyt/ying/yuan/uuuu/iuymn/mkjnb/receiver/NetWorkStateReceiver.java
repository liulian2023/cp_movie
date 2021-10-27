

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.NetWorkPopwindow;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class NetWorkStateReceiver extends BroadcastReceiver {
    public static boolean haveNet=false;
    boolean isLastNoNet = false;


    Handler handler = new Handler();
    @Override
    public void onReceive(Context context, Intent intent) {

        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            boolean netWorkState = wifiNetworkInfo.isConnected() | dataNetworkInfo.isConnected();
            if (BaseActivity.netEvent != null)
                BaseActivity.netEvent.onNetChange(netWorkState);

        } else {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            Utils.logE("networks", "" + networks);
            if (networks.length > 0) {
                boolean netWorkState = true;
                haveNet=true;
                if(haveNet&&NetWorkPopwindow.popupWindow!=null&&NetWorkPopwindow.popupWindow.isShowing()){
                    NetWorkPopwindow.popupWindow.dismiss();
                }
                if (BaseActivity.netEvent != null)
                    BaseActivity.netEvent.onNetChange(netWorkState);

               /* if (!isLastNoNet){
                    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                    String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
                    //获取当前所在的Activity名称
                    String contextActivity = runningActivity.substring(runningActivity.lastIndexOf(".") + 1);
                    Log.i("", "contextActivity:" + contextActivity);
                    Intent intent1 = new Intent();
                    intent1.setClass(context,MainActivity.class);
                    context.startActivity(intent1);
                    isLastNoNet = true;
                }*/

            } else {
                haveNet=false;
                ActivityUtil instance1 = ActivityUtil.getInstance();
                Activity topActivity = instance1.currentActivity();
                if(topActivity!=null&&!topActivity.isFinishing()){
                    View contentView = Utils.getContentView(topActivity);
                    if(!topActivity.isFinishing()){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(NetWorkPopwindow.popupWindow!=null){
                                    NetWorkPopwindow.popupWindow.showAtLocation(contentView, Gravity.CENTER,0,0);
                                }
                            }
                        },500);
                    }
                }

                boolean netWorkState = false;
                if (BaseActivity.netEvent != null)
                    BaseActivity.netEvent.onNetChange(netWorkState);

               /* if (isLastNoNet){
                    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                    String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
                    //获取当前所在的Activity名称
                    String contextActivity = runningActivity.substring(runningActivity.lastIndexOf(".") + 1);
                    Log.i("", "contextActivity:" + contextActivity);
                    Intent intent1 = new Intent();
                    intent1.setClass(context,ErrorActivity.class);
                    context.startActivity(intent1);
                }*/

            }

        }
    }




    // 网络状态变化接口
    public interface NetChangeListener {
        void onNetChange(boolean netWorkState);
    }
}
