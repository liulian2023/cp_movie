
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RongConnectModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import io.rong.imlib.RongIMClient;
        /*
        融云连接状态监听
         */
public class ConnectionStatusListene implements RongIMClient.ConnectionStatusListener {
    Context context;
    public static  String TAG="ConnectionStatusListener ";
    public ConnectionStatusListene(Context context) {
        this.context = context;
    }

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        switch (connectionStatus){
            case CONNECTED://连接成功
                Utils.logE(TAG,Utils.getString(R.string.rongIM 连接成功 ) );
                EventBus.getDefault().post(new RongConnectModel(true));
                break;
            case UNCONNECTED://断开连接。
                Utils.logE(TAG,Utils.getString(R.string.rongIM 断开连接 ) );
                EventBus.getDefault().post(new RongConnectModel(false));
                break;
            case CONNECTING://连接中。
                Utils.logE(TAG,Utils.getString(R.string.rongIM 连接中 ) );
                break;
            case NETWORK_UNAVAILABLE://网络不可用。
                Utils.logE(TAG,Utils.getString(R.string.rongIM 网络不可用 ) );
                EventBus.getDefault().post(new RongConnectModel(false));
                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(context!=null){
                            Utils.logE(TAG,Utils.getString(R.string.rongIM 多处登录,当前账号被强制下线  ) );
                            LoginIntentUtil.toLogin( context,true);
                        }
//                        EventBus.getDefault().postSticky(new SingleLoginEvent(true,0));

                    }
                });
                break;
            case CONN_USER_BLOCKED:
                //用户被封禁
                Utils.logE(TAG,Utils.getString(R.string.rongIM用户被封禁  ) );
                break;
            default:
                break;

        }
    }
}
