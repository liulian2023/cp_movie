package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;

import java.util.HashMap;
import java.util.Map;



import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.BuildConfig;


/**
 * 常量工具类
 *
 * @author hardy
 * @version 1.0
 */
public class CommonUtil {
    public static final String Interface_url  = BuildConfig.API_HOST1;  //BuildConfig.API_HOST;

    public static String img_url2 = urlParameter().get("img_url2");//本地图片加载路径

    //登录用户信息
    public static final String LOGIN_USER = "login_user_info";
    //登录用户返点信息
    public static final String LOGIN_USER_AGENT = "login_user_info_agent";
    //登录用户的token
    public static final String USER_TOKEN_INFO = "tokenInfo";
    //登录用户信息
    public static final String SYS_PARAM = "systemt_parameter";
    //前端域名访问的key
    public static final String httpurl = "HTTPURL";
    //域名统计用
    public static final String httpdomain = "HTTPDOMAIN";

    public static final String GET_REQUEST="get";
    public static final String POST_REQUEST="post";

    /**
     * 获取图片路径
     *
     * @return
     */
    public static Map<String, String> urlParameter() {

        Map<String, String> map = new HashMap<String, String>();
        String result;
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("type", "1");
            //获取微信参数
            result = DockingUtil.docking(data, RequestUtil.REQUEST_10005);
            JSONObject payApplicationJson = JSONObject.parseObject(result);
            Map<String, Object> payApplication = (Map<String, Object>) payApplicationJson;
            String imageUrl = String.valueOf(payApplication.get("imageUrl"));
            map.put("img_url2", imageUrl);
            img_url2 = imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取APP下载路径
     *
     * @param
     * @return
     * @Title:
     */
    public static Map<String, String> appurlParameter() {
        Map<String, String> map = new HashMap<String, String>();
        String result;
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("type", "2");
            //获取微信参数
            result = DockingUtil.docking(data, RequestUtil.REQUEST_10005);
            JSONObject payApplicationJson = JSONObject.parseObject(result);
            Map<String, Object> payApplication = (Map<String, Object>) payApplicationJson;
            String appUrl = String.valueOf(payApplication.get("imageUrl"));
            map.put("img_url2", appUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取浏览器版本信息
     *
     * @param agent
     * @return
     * @Title: getBrowserName
     */
    public static String getBrowserName(String agent) {
        if (agent.toLowerCase().indexOf("micromessenger") > 0) {
            return "weixin";
        } else if (agent.toLowerCase().indexOf("qq") > 0) {
            return "qq";
        } else if (agent.toLowerCase().indexOf("alipay") > 0) {
            return "zfb";
        } else if (agent.toLowerCase().indexOf("mqqbrowser") > 0) {
            return "mqqbrowser";
        } else {
            return "others";//其他浏览器
        }
    }

    /**
     * 获取手机类型信息
     *
     * @param agent
     * @return
     * @Title: getBrowserName
     */
    public static String getPhoneType(String agent) {
        if (agent.toLowerCase().indexOf("android") > 0) {
            return "android";
        } else if (agent.toLowerCase().indexOf("iphone") > 0) {
            return "iphone";
        } else {
            return "others";//其他类型手机
        }
    }


}
