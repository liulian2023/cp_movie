package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.LoginActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.LoginActivity2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.LoginActivity3;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

public class LoginIntentUtil {
    public static boolean isLogin(Context context) {
        boolean isLogin = SharePreferencesUtil.getBoolean(context, "isLogin", false);
            return isLogin;
    }
    public static void toLogin(Activity activity) {
        /**
         * loginPageVersion 0 旧版登录界面, 1 视频登录界面
         */
        String loginPageVersion = Utils.getHomeLogo("loginPageVersion");
        loginPageVersion = StringMyUtil.isEmptyString(loginPageVersion)?"0":loginPageVersion;
        Intent intent;
        if(loginPageVersion.equals("0")){
            intent = new Intent(activity, LoginActivity.class);
        }else if(loginPageVersion.equals("2")) {
            intent = new Intent(activity, LoginActivity3.class);
        }else {
            intent = new Intent(activity, LoginActivity2.class);
        }
        activity.startActivity(intent);
    }
    public static void toLogin(Activity activity, boolean singleLogin) {
        String loginPageVersion = Utils.getHomeLogo("loginPageVersion");
        loginPageVersion = StringMyUtil.isEmptyString(loginPageVersion)?"0":loginPageVersion;
        Intent intent;
        if(loginPageVersion.equals("0")){
            intent = new Intent(activity, LoginActivity.class);
        }else if(loginPageVersion.equals("2")) {
            intent = new Intent(activity, LoginActivity3.class);
        }else {
            intent = new Intent(activity, LoginActivity2.class);
        }
        intent.putExtra("singleLogin",singleLogin);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    public static void toLogin(Context context, boolean singleLogin) {
        String loginPageVersion = Utils.getHomeLogo("loginPageVersion");
        loginPageVersion = StringMyUtil.isEmptyString(loginPageVersion)?"0":loginPageVersion;
        Intent intent;
        if(loginPageVersion.equals("0")){
            intent = new Intent(context, LoginActivity.class);
        }else if(loginPageVersion.equals("2")) {
            intent = new Intent(context, LoginActivity3.class);
        }else {
            intent = new Intent(context, LoginActivity2.class);
        }
        intent.putExtra("singleLogin",singleLogin);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static void toLogin(Activity activity,  int flag, boolean singleLogin) {
        String loginPageVersion = Utils.getHomeLogo("loginPageVersion");
        loginPageVersion = StringMyUtil.isEmptyString(loginPageVersion)?"0":loginPageVersion;
        Intent intent;
        if(loginPageVersion.equals("0")){
            intent = new Intent(activity, LoginActivity.class);
        }else if(loginPageVersion.equals("2")) {
            intent = new Intent(activity, LoginActivity3.class);
        }else {
            intent = new Intent(activity, LoginActivity2.class);
        }
            intent.putExtra("flag",flag);
            intent.putExtra("singleLogin",singleLogin);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
    }
    public static void toLogin(Activity activity, String nickName) {
        String loginPageVersion = Utils.getHomeLogo("loginPageVersion");
        loginPageVersion = StringMyUtil.isEmptyString(loginPageVersion)?"0":loginPageVersion;
        Intent intent;
        if(loginPageVersion.equals("0")){
            intent = new Intent(activity, LoginActivity.class);
        }else if(loginPageVersion.equals("2")) {
            intent = new Intent(activity, LoginActivity3.class);
        }else {
            intent = new Intent(activity, LoginActivity2.class);
        }
        intent.putExtra("nickName",nickName);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
