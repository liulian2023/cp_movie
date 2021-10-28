
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.view.Gravity;

import com.hjq.toast.ToastUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

public class ToastUtil {

    public static void showToast(String message){
        if(StringMyUtil.isEmptyString(message)||message.equalsIgnoreCase("timeout")||message.contains(Utils.getString(R.string.接口请求频繁))){
            return;
        }
        ToastUtils.setGravity(Gravity.CENTER,0,0);
        ToastUtils.show(message);
    }
}
