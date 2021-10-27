package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.net.model.BaseEntity;
import com.uyt.ying.rxhttp.net.model.UserEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.Base64Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * created  by xxxx on 2019/11/16.
 */
public class JsonUtil {

    public static <T extends BaseEntity> RequestBody Entity2JsonRequestBody(T entity){
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonStr = gson.toJson(entity,entity.getClass());
        return  RequestBody.create(MediaType.parse("application/json"),jsonStr);
    }

    public static  RequestBody Map2JsonRequestBody(Map map){
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonStr = gson.toJson(map);
        return  RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr);
    }

    public static String getJson(Context context, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName),"utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static List<?> analysisJson(Context context,String fileName) {
        //得到本地json文本内容
        //String fileNames = "car_code.json";
        String myjson = getJson(context, fileName);
        //json转换为集合
        return new Gson().fromJson(myjson, new TypeToken<List<?>>(){}.getType());
    }


    public static UserEntity Str2UserEntity(String userinfo){
        if (TextUtils.isEmpty(userinfo)){
            return null;
        }
        Gson gson = new GsonBuilder().serializeNulls().create();
        UserEntity userEntity = gson.fromJson(userinfo, UserEntity.class);
        return  userEntity;
    }

    public static String UserEntity2Str(UserEntity userEntity){
        if (userEntity==null){
            return null;
        }
        Gson gson = new GsonBuilder().serializeNulls().create();
        String str = gson.toJson(userEntity);
        return  str;
    }

    /**
     * 老CP请求数据封装
     * @param dataMap
     * @return
     */
    public static Map<String,Object> getDefReqMap(Map<String,Object> dataMap){
        Map<String,Object> headerMap = new HashMap<>();
        String version = "1.0";
        String timestamp = System.currentTimeMillis() + "";
        String deviceCode = "145158033846";
        headerMap.put("version", version);//版本号
        headerMap.put("timestamp", timestamp + "");//时间搓
        headerMap.put("code", deviceCode);//设备号

        String dataStr = null;//对请求体进行base64编码
        try {
            dataStr = Base64Utils.encodeBase64String(JSONObject.toJSONString(dataMap));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //版本号【version】+时间戳【timestamp】+应用密钥【secret】+消息体【data】 取MD5
        String token = "";
        StringBuilder sb = new StringBuilder();
        sb.append(version);
        sb.append(timestamp);
        sb.append("a9437e8561ed45209446e936703dbbbd");//密钥
        sb.append(dataStr);
        token = new String(Hex.encodeHex(DigestUtils.md5(String.valueOf(sb))));

        headerMap.put("token", token);
        Map<String, Object> map = new HashMap<String, Object>();//请求集合
        map.put("head", headerMap);
        map.put("data", dataStr);
        Map<String, Object> aaa = new HashMap<String, Object>();
        try {
            aaa.put("data", Base64Utils.encodeBase64String(JSONObject.toJSONString(map)));//请求集合再进行一次base64编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aaa;
    }

}
