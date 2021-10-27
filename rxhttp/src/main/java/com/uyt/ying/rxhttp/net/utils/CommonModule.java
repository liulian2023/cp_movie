package com.uyt.ying.rxhttp.net.utils;

import android.content.Context;

public class CommonModule {
    private static Context sAppContext;
    /**
     * 子模块和主模块需要共享全局上下文，故需要在app module初始化时传入
     */
    public static void init(Context appContext) {
        if(sAppContext == null) {
            sAppContext = appContext.getApplicationContext();
        }
    }
    public static Context getAppContext() {
        return sAppContext;
    }

}
