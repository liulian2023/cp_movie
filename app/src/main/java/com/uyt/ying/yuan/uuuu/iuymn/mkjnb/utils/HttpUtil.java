package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

import org.json.JSONException;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * created  by xxxx on 2019/11/16.
 */
public class HttpUtil {

    public static RequestBody postRequest(HashMap<String,Object> data,boolean isCp){
        RequestBody requestBody =null;
        org.json.JSONObject root = new org.json.JSONObject();
        try {
            putCommonParams(data);
            if(isCp){
                data = (HashMap<String, Object>) JsonUtil.getDefReqMap(data);
                return setPostRequestBody(data);
//                requestBody  = RequestBody.create(MediaType.parse("multipart/form-data"), );
            }else {
                Iterator<String> iterator = data.keySet().iterator();
                String key = "";
                while (iterator.hasNext()) {
                    key = iterator.next().toString();
                    root.put(key, String.valueOf(data.get(key)));
                }
                requestBody =RequestBody.create(MediaType.parse("application/json"), root.toString());

                return requestBody;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void putCommonParams(HashMap<String, Object> data) {
//        String domain = SharePreferencesUtil.getString(MyApplication.getInstance(), "domain", "");
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        String domain = sp.getNewBaseUrl();
        domain = StringMyUtil.isEmptyString(domain)? BuildConfig.API_HOST1:domain;
        String token = SharePreferencesUtil.getString(MyApplication.getInstance(), "token", "");
        Long user_id1 = SharePreferencesUtil.getLong(MyApplication.getInstance(), "user_id", 0l);
        if(!data.containsKey("loginType")){
            data.put("loginType",1);
        }
        if (!StringMyUtil.isEmptyString(domain) && !data.containsKey("domain")) {
            data.put("domain", domain);
        }
        data.put("source", 2);
        if (!StringMyUtil.isEmptyString(token) && !data.containsKey("token")) {
            data.put("token", token);
            data.put("tokenInfo", token);
        }
        if (user_id1 != 0l) {
            data.put("user_id", user_id1);
        }
        if(!data.containsKey("deviceNumber")){
            data.put("deviceNumber", AESUtil.encrypt(DeviceUtils.getUniqueId(MyApplication.getInstance())));//设备号
        }
        if(!data.containsKey("appVersionName")){
            data.put("appVersionName", VersionUtils.getAppVersionName(MyApplication.getInstance()));
        }
        if(!data.containsKey("appVersionName")){
            data.put("appVersionName", SystemUtil.getSystemVersion());
        }
        if(!data.containsKey("mobileBrandModels")) {
            data.put("mobileBrandModels", SystemUtil.getSystemModel());
        }
        if(!data.containsKey("mobileSystemVersion")){
            data.put("mobileSystemVersion", SystemUtil.getSystemVersion());
        }
        if(!data.containsKey("mobileBrand")){
            data.put("mobileBrand",SystemUtil.getDeviceBrand());
        }
    }

    public static Map<String,Object> getRequest(HashMap<String,Object>data){
        return data;
    }

    public static RequestBody uploadRequest(String filePath){
        HashMap<String, Object> data = new HashMap<>();
        putCommonParams(data);
        Map<String, Object> map = JsonUtil.getDefReqMap(data);
        File file = new File(filePath);//filePath为图片位置
        RequestBody  fileBody  = RequestBody.create(MediaType.parse("image/jpg"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("data", JSONObject.toJSONString(map.get("data")))
                .build();
        return requestBody;
    }

    private static RequestBody setPostRequestBody(Map<String, Object> BodyParams) {
        RequestBody body = null;
        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, String.valueOf(BodyParams.get(key)));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }

}
