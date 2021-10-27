package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.Base64Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import okhttp3.Headers;

public class CPDataParseUtils {

    public static String ParseHeaderResult(String result, Headers headers) {
        Context mContext = MyApplication.getInstance();

        JSONObject json = JSONObject.parseObject(result);
        JSONObject jsonHead = JSONObject.parseObject(json.getString("head"));

        String resultData = "";

        if ("00".equals(jsonHead.getString("code"))) {
            if (json.getString("data") != null) {
                try {
                    resultData = Base64Utils.decodeBase64String(json.getString("data"));//响应体需要解码
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String timestamp = jsonHead.getString("timestamp");//服务器时间
                if (timestamp!=null){
                    Long oldTime = SharePreferencesUtil.getLong(mContext, "shijiancha", 0l);
//                    long nowTime = System.currentTimeMillis() - Long.parseLong(timestamp);
                    long nowTime = new Date().getTime() - Long.parseLong(timestamp);
                    if (oldTime == 0) {//第一次存入(后面有时间差为0的一并忽略,存新值)
                        SharePreferencesUtil.putLong(mContext, "shijiancha", nowTime);
                    } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                        SharePreferencesUtil.putLong(mContext, "shijiancha", nowTime);
                    } else {
                        SharePreferencesUtil.putLong(mContext, "shijiancha", oldTime);
                    }
                }

            }
        }
        return resultData;


    }

    /**
     * 去除重复主播  根据主播 account
     */
    public static List<LiveEntity.AnchorMemberRoomListBean> removeDuplicateAnchor(List<LiveEntity.AnchorMemberRoomListBean> anchors) {
        Set<LiveEntity.AnchorMemberRoomListBean> set = new TreeSet<>((LiveEntity.AnchorMemberRoomListBean o1, LiveEntity.AnchorMemberRoomListBean o2)->
             o1.getAnchorAccount().compareTo(o2.getAnchorAccount())
        );
        set.addAll(anchors);
        return new ArrayList<>(set);
    }
}
