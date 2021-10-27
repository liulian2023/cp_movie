package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public class VersionUtils {

    /**
     * 获取当前app version code
     */
    public static long getAppVersionCode(Context context) {
        long appVersionCode = 0;
        try {
           PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode =packageInfo.getLongVersionCode();
            } else {
                appVersionCode =packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Utils.logE("", e.getMessage());
        }
        return appVersionCode;
    }

    /**
     * 获取当前app version name
     */
    public static String getAppVersionName(Context context) {
        String appVersionName = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appVersionName =packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Utils.logE("", e.getMessage());
        }
        return appVersionName;
    }
}
