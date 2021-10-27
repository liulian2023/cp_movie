package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;

import android.content.Context;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.cf.msc.sdk.AppVest;
import com.cf.msc.sdk.SecurityConnection;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;


public class DockingUtil {

    public static String docking(Map<String, Object> data, String url) throws Exception {
        String result = doPost(data, url);
        String resultData = "";
        if (!StringMyUtil.isEmptyString(result)) {
            if (result.contains("500 Internal Server Error")) {
                resultData = "";
            } else {
                try {
                    JSONObject json = JSONObject.parseObject(result);
                    JSONObject jsonHead = JSONObject.parseObject(json.getString("head"));
                    if ("00".equals(jsonHead.getString("code"))) {
                        if (json.getString("data") != null) {
                            resultData = Base64Utils.decodeBase64String(json.getString("data"));//响应体需要解码
                            String timestamp = jsonHead.getString("timestamp");//服务器时间
             //               Utils.saveFileData(timestamp + "", "time");//保存时间差
                            Context context = MyApplication.getInstance();
                            Long oldTime = SharePreferencesUtil.getLong(context, "shijiancha", 0l);
//                            long nowTime = System.currentTimeMillis() - Long.parseLong(timestamp);
                            long nowTime = new Date().getTime() - Long.parseLong(timestamp);
                            if (oldTime == 0) {//第一次存入(后面有时间差为0的一并忽略,存新值)
                                SharePreferencesUtil.putLong(context, "shijiancha", nowTime);
                            } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                                SharePreferencesUtil.putLong(context, "shijiancha", nowTime);
                            } else {
                                SharePreferencesUtil.putLong(context, "shijiancha", oldTime);
                            }
                        }
                    } else {
                        /* resultData = "";*/
                        resultData = Base64Utils.decodeBase64String(json.getString("data"));//响应体需要解码
                    }//21740
                }catch (Exception e){
                    e.printStackTrace();
                    Utils.logE("eException",e.getMessage());
                }

            }
        } else {
            resultData = "";
        }
        return resultData;
    }




    private static String doPost(Map<String, Object> data, String url) throws IOException {

        url = iniUrl(url);
        Map<String, Object> map = new HashMap<String, Object>();//请求集合
        Map<String, Object> head = new HashMap<String, Object>();//请求头
        String version = "1.0";
        String timestamp = System.currentTimeMillis() + "";
        String deviceCode = "145158033846";
        head.put("version", version);//版本号
        head.put("timestamp", timestamp + "");//时间搓
        head.put("code", deviceCode);//设备号
        String dataStr = Base64Utils.encodeBase64String(JSONObject.toJSONString(data));//对请求体进行base64编码
//        String dataStr = Base64Utils.encodeBase64String(JSONObject.toJSONString(data));
        //版本号【version】+时间戳【timestamp】+应用密钥【secret】+消息体【data】 取MD5
        String token = "";
        StringBuilder sb = new StringBuilder();
        sb.append(version);
        sb.append(timestamp);
        sb.append("a9437e8561ed45209446e936703dbbbd");
        sb.append(dataStr);
        token = new String(Hex.encodeHex(DigestUtils.md5(String.valueOf(sb))));
//     String token = new String（Hex.encodeHex（DigestUtils.md5（data）））;
        head.put("token", token);
        map.put("head", head);
        map.put("data", dataStr);
        Map<String, Object> aaa = new HashMap<String, Object>();
        aaa.put("data", Base64Utils.encodeBase64String(JSONObject.toJSONString(map)));//请求集合再进行一次base64编码
//      return http.doPost(url, aaa);
        OkHttp3Utils okHttp3Utils = new OkHttp3Utils();
        return okHttp3Utils.doPost_Execute(url, null, aaa);
        //   return "";
       /* final Map<String, String> resultmap = new HashMap<>();
        okHttp3Utils.doPost_Enqueue(url, null, aaa, new OkHttp3Utils.NetCallback() {
            @Override
            public void onFailure(int code, String msg) {
                resultmap.put("reslut",msg);
            }

            @Override
            public void onSuccess(int code, String content) {
                resultmap.put("reslut",content);
            }
        });
        return  resultmap.get("result");*/
    }


    public static String wsUrl = null;

    private static String iniUrl(String url) {
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(sp.getAppVestFlag()==0){
            Properties properties = new Properties();
            String name = "/etc/application.properties";
            DockingUtil.wsUrl = CommonUtil.Interface_url;

            if (new File(name).exists()) {
                try {
                    properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(name), "utf-8")));
                    String wsUrl = properties.getProperty("ws.url");
                    if (wsUrl != null && wsUrl.trim().length() > 0) {
                        DockingUtil.wsUrl = wsUrl;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (url.startsWith("/")){
                return wsUrl +url.substring(1);
            }else {
                return wsUrl + url;
            }
        }else {
             SecurityConnection conn = AppVest.getServerIPAndPort("www.tiantian.com", 1002);
             if(url.startsWith("/")){
                 return String.format("http://%s:%s/web/ws/", conn.getServerIp(), conn.getServerPort())+url.substring(1);
             }else {
                 return String.format("http://%s:%s/web/ws/", conn.getServerIp(), conn.getServerPort())+url;
             }
        }

    }




    public interface ILoaderListener {
        void success(String content);

        void failed(MessageHead messageHead);
    }


}
