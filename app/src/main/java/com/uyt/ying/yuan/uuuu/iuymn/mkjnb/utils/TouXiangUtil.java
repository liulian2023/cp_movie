package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

public class TouXiangUtil {

    public static String getMineTouXiang(Context context) {
        String finalImgUrl;
        String firstImageUrl = SharePreferencesUtil.getString(context, "FirstImageUrl", "");
//        String image = SharePreferencesUtil.getString(context, "image", "");
        String image = SharePreferencesUtil.getString(context, "oldUserImage", "");
        if(!StringMyUtil.isEmptyString(image)){//新注册的账号没有image字段,需要给一个默认头像
            image =image.replace(firstImageUrl,"");
            finalImgUrl = firstImageUrl+image;
        }else{//默认头像
            finalImgUrl=Utils.getString(R.string.沒有头像);
        }
        return finalImgUrl;
    }
    public static String getOtherTouXiang(Context context,String image) {
        String finalImgUrl;
        String firstImageUrl = SharePreferencesUtil.getString(context, "FirstImageUrl", "");
        if(!StringMyUtil.isEmptyString(image)){//新注册的账号没有image字段,需要给一个默认头像
            image =image.replace(firstImageUrl,"");
            finalImgUrl = firstImageUrl+image;
        }else{//默认头像
            finalImgUrl=Utils.getString(R.string.沒有头像);
        }
        return finalImgUrl;
    }
}