
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;


import io.rong.imlib.RongIMClient;

import static com.uyt.ying.rxhttp.net.common.RxLibConstants.CHESS_LOTTERY_HISTORY;

public class ClearCache {


    public static void clearCache(Context context) {
        RongIMClient.getInstance().disconnect();//断开融云连接
        SharePreferencesUtil.putLong(context,"old_user_id",SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l));
        SharePreferencesUtil.putBoolean(context, "isLogin", false);
        SharePreferencesUtil.remove(context, "token");
        SharePreferencesUtil.remove(context, "user_id");
        SharePreferencesUtil.remove(context, "haveGetRedNum");
        SharePreferencesUtil.remove(context,"giftRainId");
        SharePreferencesUtil.remove(context,"rongYunKey");
        SharePreferencesUtil.remove(context,"chatroomToken");
        SharePreferencesUtil.remove(context,"isTest");
        SharePreferencesUtil.remove(context,CommonStr.USER_PHONE);
        SharePreferencesUtil.remove(context,CommonStr.USER_PASSWORD);
        if(Utils.gameTypeIdList!=null){
            for (int i = 0; i < Utils.gameTypeIdList.size(); i++) {
                SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.LOTTERY_VERSION+Utils.gameTypeIdList.get(i));
            }

        }

        SharePreferencesUtil.remove(context,CHESS_LOTTERY_HISTORY);

    }


}
