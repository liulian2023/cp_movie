package com.uyt.ying.rxhttp.net.utils;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.BuildConfig;

public class Utils {

    public static void logE(String tag,String msg){
        if(BuildConfig.DEBUG){
          Log.e(tag,msg);
        }
    }
    public static boolean isJson(String content) {
        if(TextUtils.isEmpty(content)){
            return false;
        }
        boolean isJsonObject = true;

        try {
            JSONObject.parseObject(content);
        } catch (Exception e) {
            isJsonObject = false;
        }

        if(!isJsonObject ){ //不是json格式
            return false;
        }
        return true;
    }

}


