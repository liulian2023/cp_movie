package com.uyt.ying.rxhttp.net.utils;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @author xxxx
 * @description 上下文工具类
 * @create 2019/9/3
 */
public final class AppContextUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private AppContextUtils() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        AppContextUtils.mContext = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (mContext != null) return mContext;
        throw new NullPointerException("You must init first");
    }
}
