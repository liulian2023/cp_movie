package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;

import java.util.HashMap;

import okhttp3.Headers;

public class RequestLoginUtils {
    private static String TAG="RequestLoginUtils";

    public static void loginSuccess(Context context, String message, String account, String password, String paypassword,Button button) {
        if (message.equals(Utils.getString(R.string.登入成功))) {
            SharePreferencesUtil.putString(context, "account", account);
            SharePreferencesUtil.putString(context, "password", password);
            SharePreferencesUtil.putString(context, "RemembePassword", password);
            SharePreferencesUtil.putString(context, "payPassword", paypassword);
            SharePreferencesUtil.putBoolean(context, "isLogin", true);
            //首页版本控制 2 的时候 才请求融云
                Activity activity=(Activity)context;
                if(activity instanceof BaseActivity){
                    BaseActivity baseActivity = (BaseActivity) activity;
                    baseActivity.closeLoading();
                }
               connectRongYun(context,button);
        } else {
            ToastUtil.showToast(Utils.getString(R.string.网络不给力,登录失败));
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("showAtyPop",true);
            context.startActivity(intent);
            Activity activity=(Activity)context;
            if(activity instanceof BaseActivity){
                BaseActivity baseActivity = (BaseActivity) activity;
                baseActivity.closeLoading();
            }
            if(button!=null){
                button.setClickable(true);
            }
            activity.finish();
        }
    }


    public  static  void connectRongYun(Context context,Button button){
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", SharePreferencesUtil.getLong(context,"user_id",0l));
        data.put("loginType","1");
        HttpApiUtils.CpRequest((Activity)context,null, RequestUtil.REQUEST_Chat1, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                Utils.logE( TAG,Utils.getString(R.string.获取聊天室token成功  >>>>)+Utils.getString(R.string.连接融云服务器开始) );
                JSONObject jsonObject = JSONObject.parseObject(result);
                String chatRoomId = jsonObject.getString("chatroomId");
                String token = null;
                String appKey = null;
                try {
                    token = AESUtil.decrypt(jsonObject.getString("token"));
                    appKey = AESUtil.decrypt(jsonObject.getString("appKey"));
                    SharePreferencesUtil.putString(context,"rongYunKey",appKey);
                    SharePreferencesUtil.putString(context, "chatroomToken", token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SharePreferencesUtil.putString(context, "chatroomId", chatRoomId);
                //是否是管理员 0 否1 是
                String isSuperRoomManager = jsonObject.getString("isSuperRoomManager");
                SharePreferencesUtil.putString(context,"isSuperRoomManager",isSuperRoomManager);
                //融云id
                String rcUsId = jsonObject.getString("rcUsId");
                SharePreferencesUtil.putString(context,"rcUsId",rcUsId);
                String vipSpeak = jsonObject.getString("vipSpeak");//vip发言特权

                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.VIP_SPEAK,vipSpeak);
                RongLibUtils.initRongYun(appKey);
                RongLibUtils.connectRongYun(token);
                ToastUtil.showToast(Utils.getString(R.string.登录成功));
                if(button!=null){
                    button.setClickable(true);
                }
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("fromLogin",true);
                intent.putExtra("showAtyPop",true);
                context.startActivity(intent);
                Activity activity=(Activity)context;
                activity.finish();
            }

            @Override
            public void onFailed(String msg) {
                if(button!=null){
                    button.setClickable(true);
                }
                Utils.logE(TAG, Utils.getString(R.string.获取聊天室token失败: ) );
//                if(messageHead.getCode().equals("00")){
                //获取聊天室token成功或者失败都默认登录成功,跳转首页(盈众和申博没有聊天室,无法获取到聊天室token)
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("fromLogin",true);
                intent.putExtra("showAtyPop",true);
                context.startActivity(intent);
                Activity activity=(Activity)context;
                if(activity instanceof BaseActivity){
                    BaseActivity baseActivity = (BaseActivity) activity;
                    baseActivity.closeLoading();
                }
                activity.finish();
            }
        });
    }

    public static void requestSuccess(Context context, String content, String account, String password, String paypassword) {
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        JSONObject jsonObject = JSONObject.parseObject(content);
        String message = jsonObject.getString("message");//是否登录成功
        String token = jsonObject.getString("token");//用户token
        SharePreferencesUtil.putString(MyApplication.getInstance(), "token", token);
        sp.setToken(token);
        SharePreferencesUtil.putString(MyApplication.getInstance(), "LoginMessage", message);
        JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
        String image = memberInfo.getString("image");
        Long id = memberInfo.getLong("id");

        String userNickName = memberInfo.getString("userNickName");//用户昵称(可修改的)(聊天室用到)
        String nickname = memberInfo.getString("nickname");//用户昵称(不可修改的)(投注页面中奖后更新余额需要用到)
        Integer pointGrade = memberInfo.getInteger(CommonStr.GRADE);//会员等级
        Integer isagent = memberInfo.getInteger("isagent");
        String isTest = memberInfo.getString("isTest");
        SharePreferencesUtil.putString(MyApplication.getInstance(),"isTest",isTest);
        sp.setUserId(id);
        SharePreferencesUtil.putLong(MyApplication.getInstance(), "user_id", id);//保存用户id
        SharePreferencesUtil.putString(MyApplication.getInstance(), "userNickName", userNickName);
        SharePreferencesUtil.putInt(MyApplication.getInstance(), CommonStr.GRADE, pointGrade + 1);
        SharePreferencesUtil.putString(MyApplication.getInstance(), "nickname", nickname);
        SharePreferencesUtil.putString(MyApplication.getInstance(), "image", image);//储存头像(聊天室取出)
        SharePreferencesUtil.putInt(MyApplication.getInstance(), "isagent", isagent);//是否是代理)

        JSONObject memberAgent = jsonObject.getJSONObject("memberAgent");//返点信息
        float k3Rate = memberAgent.getFloatValue("k3Rate");//快三返点
        float happytenRate = memberAgent.getFloatValue("happytenRate");//快乐十分返点
        float sscaiRate = memberAgent.getFloatValue("sscaiRate");//时时彩返点
        float happy8Rate = memberAgent.getFloatValue("happy8Rate");//快乐8返点
        float xuanwuRate = memberAgent.getFloatValue("xuanwuRate");//11选5返点
        float farmRate = memberAgent.getFloatValue("farmRate");//重庆农场返点
        float raceRate = memberAgent.getFloatValue("raceRate");//赛车返点
        float sixRate = memberAgent.getFloatValue("sixRate");//六合彩返点
        float danRate = memberAgent.getFloatValue("danRate");//pc蛋蛋返点



        SharePreferencesUtil.putString(MyApplication.getInstance(), "1", k3Rate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "2", sscaiRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "3", raceRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "4", sixRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "5", danRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "6", happy8Rate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "7", farmRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "8", happytenRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "9", xuanwuRate + "");
        loginSuccess(context,message,account,password,"",null);//登录成功相关
    }

    public static void requestSuccess(Context context, String content, String account, String password, Button button) {
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        JSONObject jsonObject = JSONObject.parseObject(content);
        String message = jsonObject.getString("message");//是否登录成功
        String token = jsonObject.getString("token");//用户token
        SharePreferencesUtil.putString(MyApplication.getInstance(), "token", token);
        sp.setToken(token);
        SharePreferencesUtil.putString(MyApplication.getInstance(), "LoginMessage", message);
        JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
        String image = memberInfo.getString("image");
        Long id = memberInfo.getLong("id");

        String userNickName = memberInfo.getString("userNickName");//用户昵称(可修改的)(聊天室用到)
        String nickname = memberInfo.getString("nickname");//用户昵称(不可修改的)(投注页面中奖后更新余额需要用到)
        Integer pointGrade = memberInfo.getInteger(CommonStr.GRADE);//会员等级
        Integer isagent = memberInfo.getInteger("isagent");
        sp.setUserId(id);
        SharePreferencesUtil.putLong(MyApplication.getInstance(), "user_id", id);//保存用户id
        SharePreferencesUtil.putString(MyApplication.getInstance(), "userNickName", userNickName);
        SharePreferencesUtil.putInt(MyApplication.getInstance(), CommonStr.GRADE, pointGrade + 1);
        SharePreferencesUtil.putString(MyApplication.getInstance(), "nickname", nickname);
        SharePreferencesUtil.putString(MyApplication.getInstance(), "image", image);//储存头像(聊天室取出)
        SharePreferencesUtil.putInt(MyApplication.getInstance(), "isagent", isagent);//是否是代理)

        JSONObject memberAgent = jsonObject.getJSONObject("memberAgent");//返点信息
        float k3Rate = memberAgent.getFloatValue("k3Rate");//快三返点
        float happytenRate = memberAgent.getFloatValue("happytenRate");//快乐十分返点
        float sscaiRate = memberAgent.getFloatValue("sscaiRate");//时时彩返点
        float happy8Rate = memberAgent.getFloatValue("happy8Rate");//快乐8返点
        float xuanwuRate = memberAgent.getFloatValue("xuanwuRate");//11选5返点
        float farmRate = memberAgent.getFloatValue("farmRate");//重庆农场返点
        float raceRate = memberAgent.getFloatValue("raceRate");//赛车返点
        float sixRate = memberAgent.getFloatValue("sixRate");//六合彩返点
        float danRate = memberAgent.getFloatValue("danRate");//pc蛋蛋返点



        SharePreferencesUtil.putString(MyApplication.getInstance(), "1", k3Rate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "2", sscaiRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "3", raceRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "4", sixRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "5", danRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "6", happy8Rate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "7", farmRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "8", happytenRate + "");
        SharePreferencesUtil.putString(MyApplication.getInstance(), "9", xuanwuRate + "");
        loginSuccess(context,message,account,password,"",button);//登录成功相关
    }

}
