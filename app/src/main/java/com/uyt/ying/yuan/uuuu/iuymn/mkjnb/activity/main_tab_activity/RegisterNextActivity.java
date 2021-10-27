
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RegisterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DeviceUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LimitTextChange;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RequestLoginUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;
import pl.droidsonroids.gif.GifImageView;

public class RegisterNextActivity extends BaseActivity {
    @BindView(R.id.phone_etv)
    EditText phone_etv;
    @BindView(R.id.register_slide_webView)
    WebView register_slide_webView;
    @BindView(R.id.phone_code_etv)
    EditText phone_code_etv;
    @BindView(R.id.send_phone_code_tv)
    TextView send_phone_code_tv;
    @BindView(R.id.register_btn)
    Button register_btn;
    @BindView(R.id.loading_iv)
    GifImageView loading_iv;
    @BindView(R.id.kefu_tv)
    TextView kefu_tv;
    private String ali_token;
    private String ali_sessionId;
    private String ali_sig;
    static String SEND_PHONE_CODE=Utils.getString(R.string.获取验证码);
    int countDownTime = 61;
    MyHandler myHandler;
    private String ip;
    int REQUEST_REGISTER=1;
    String inviteCode;
    String nickName;
    String password;
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private String appName;
    private String frontUrl;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_register_next);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.绑定手机号));
        getIntentData();
        initWebView();
        initSharePreferencesData();
        myHandler = new MyHandler(new SoftReference<>(this));
        LimitTextChange.StringWatcher(phone_code_etv);
    }
    private void initSharePreferencesData() {
        frontUrl = SharePreferencesUtil.getString(RegisterNextActivity.this, "frontUrl", "");
        if(StringMyUtil.isNotEmpty(frontUrl)){
            // 加载业务页面。
            url = (frontUrl.endsWith("/")?frontUrl:frontUrl+"/")+ "huadong/androidSmsVerify.html";
            register_slide_webView.loadUrl(url);
        }else {
            initFrontUrl();
        }
    }
    private void initFrontUrl() {
        HashMap<String, Object> data = new HashMap<>();
        Utils.docking(data, RequestUtil.REQUEST_10004, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                String frontUrl = jsonObject.getString(CommonStr.FRONT_URL);
                SharePreferencesUtil.putString(RegisterNextActivity.this,CommonStr.FRONT_URL,frontUrl);
                url = (frontUrl.endsWith("/")?frontUrl:frontUrl+"/") + "huadong/androidSmsVerify.html";
                register_slide_webView.loadUrl(url);
                try {
                    URL url = new URL(frontUrl);
                    String protocol = url.getProtocol();
                    String host = url.getHost();
                    String domain=protocol+"://"+host;
                    SharePreferencesUtil.putString(RegisterNextActivity.this,CommonStr.DOMAIN,domain);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }
    private void getIntentData() {
        inviteCode = getIntent().getStringExtra("inviteCode");
        nickName = getIntent().getStringExtra("nickName");
        password = getIntent().getStringExtra("password");
    }

    public static void startActiviy(Context context,String inviteCode,String nickName,String password){
        Intent intent = new Intent(context, RegisterNextActivity.class);
        intent.putExtra("inviteCode",inviteCode);
        intent.putExtra("nickName",nickName);
        intent.putExtra("password",password);
        context.startActivity(intent);
    }
    public class smsInterface {
        @JavascriptInterface
        public void getSlideData(String callData) {
            JSONObject jsonObject = JSONObject.parseObject(callData);
            ali_token=jsonObject.getString("nc_token");
            ali_sessionId=jsonObject.getString("sessionid");
            ali_sig=jsonObject.getString("sig");
        }
    }
    private void initWebView() {
        // 设置屏幕自适应。
        register_slide_webView.getSettings().setUseWideViewPort(true);
        register_slide_webView.getSettings().setLoadWithOverviewMode(true);
        // 建议禁止缓存加载，以确保在攻击发生时可快速获取最新的滑动验证组件进行对抗。
        register_slide_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置不使用默认浏览器，而直接使用WebView组件加载页面。
        register_slide_webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        register_slide_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==100){
                    loading_iv.setVisibility(View.GONE);
                    register_slide_webView.setVisibility(View.VISIBLE);
                }
            }
        });
        // 设置WebView组件支持加载JavaScript。
        register_slide_webView.getSettings().setJavaScriptEnabled(true);
        // 建立JavaScript调用Java接口的桥梁。
        register_slide_webView.addJavascriptInterface(new smsInterface(), "smsInterface");
    }
    @OnClick({R.id.send_phone_code_tv,R.id.register_btn,R.id.kefu_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.send_phone_code_tv:
                String toString = send_phone_code_tv.getText().toString();
                if(checkPhone()){
                    if(toString.equals(SEND_PHONE_CODE)){
                        resSendPhoneCode(phone_etv.getText().toString());
                    }
                }

                break;
            case R.id.register_btn:
                    if(checkAllEtv()&&Utils.isNotFastClick()){
                        requestRegister(register_btn);
                    }
                    break;
            case R.id.kefu_tv:
                Intent intentKeFu = new Intent(RegisterNextActivity.this, OnLineKeFuActivity.class);
                intentKeFu.putExtra("toKeFu",true);
                startActivity(intentKeFu);
                break;
                default:
                    break;
        }
    }


    private boolean checkPhone(){
        String phone = phone_etv.getText().toString();
        if(StringMyUtil.isEmptyString(phone)){
            showToast(Utils.getString(R.string.请输入手机号));
            return  false;
        }else if(phone.length()!=11){
            showToast(Utils.getString(R.string.手机号格式不正确));
            return  false;
        } else if(StringMyUtil.isEmptyString(ali_sessionId)){
           showToast(Utils.getString(R.string.请完成滑动验证再获取验证码));
           return false;
        }
        return true;
    }
    private boolean checkAllEtv(){
        String phoneStr = phone_etv.getText().toString();
        if(StringMyUtil.isEmptyString(phoneStr)){
            showToast(Utils.getString(R.string.请输入手机号));
            return  false;
        }else if(phoneStr.length()!=11){
            showToast(Utils.getString(R.string.手机号格式不正确));
            return  false;
        }
        String phoneCodeStr = phone_code_etv.getText().toString();
        if(StringMyUtil.isEmptyString(phoneCodeStr)){
            showToast(Utils.getString(R.string.请输入手机验证码));
            return false;
        }
        if(StringMyUtil.isEmptyString(ali_sessionId)){
            showToast(Utils.getString(R.string.请完成滑动验证获取验证码));
            return false;
        }
        return true;
    }
    private void resSendPhoneCode(String phone) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        data.put("type", 0);
        data.put("ali_sessionId", ali_sessionId);
        data.put("ali_sig", ali_sig);
        data.put("ali_token",ali_token);
        HttpApiUtils.CpRequest(this, null,RequestUtil.SEND_PHONE_CODE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.验证码发送成功));
                countDownTime = 61;
                myHandler.post(countDownRunnable);
            }

            @Override
            public void onFailed(String msg) {
               register_slide_webView.loadUrl(url);
               ali_token="";
               ali_sessionId="";
               ali_sig="";
            }
        });
    }
    static class MyHandler extends Handler{
        SoftReference<RegisterNextActivity>registerNextActivitySoftReference;

        public MyHandler(SoftReference<RegisterNextActivity> registerNextActivitySoftReference) {
            this.registerNextActivitySoftReference = registerNextActivitySoftReference;
        }

        public SoftReference<RegisterNextActivity> getRegisterNextActivitySoftReference() {
            return registerNextActivitySoftReference;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            RegisterNextActivity registerNextActivity = registerNextActivitySoftReference.get();
            if(registerNextActivity!=null){
            }
            super.handleMessage(msg);
        }
    }

    private  void requestRegister(Button button ) {
        button.setClickable(false);
        String uniqueId = DeviceUtils.getUniqueId(this);//设备号
        HashMap<String, Object> data = new HashMap<>();
        data.put("refername", inviteCode);
        data.put("nickname", nickName);
        data.put("password", password);
        String phone = phone_etv.getText().toString();
        data.put("phone", phone);
//                        data.put("deviceNumber", SystemUtil.getDeviceBrand()+ SystemUtil.getSystemModel()+"("+uniqueId+")");
        data.put("ip",ip);
        data.put("loginType","1");
        // 测试用,万能验证码
//                        data.put("phoneSMSCode","888888");
        data.put("phoneSMSCode",phone_code_etv.getText().toString());
        HttpApiUtils.CpRequest(this, null, RequestUtil.REQUEST_06tb, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                RegisterEntity registerEntity = JSONObject.parseObject(result, RegisterEntity.class);
                String token = registerEntity.getToken();
                String message = registerEntity.getMessage();
                SharePreferencesUtil.putString(RegisterNextActivity.this,"token", token);
                SharePreferencesUtil.putString(RegisterNextActivity.this, "LoginMessage", message);
                RegisterEntity.MemberInfoBean memberInfo = registerEntity.getMemberInfo();

                String image = memberInfo.getImage();
                Long id = memberInfo.getId();
                String userNickName = memberInfo.getUserNickName();//用户昵称(可修改的)(聊天室用到)
                String nickname = memberInfo.getNickname();//用户昵称(不可修改的)(投注页面中奖后更新余额需要用到)
                Integer pointGrade = memberInfo.getPointGrade();//会员等级
                Integer isagent = memberInfo.getIsagent();
                sp.setToken(token);
                SharePreferencesUtil.putLong(RegisterNextActivity.this, "user_id", id);//保存用户id
                SharePreferencesUtil.putString(RegisterNextActivity.this, "userNickName", userNickName);
                SharePreferencesUtil.putInt(RegisterNextActivity.this, CommonStr.GRADE, pointGrade + 1);
                SharePreferencesUtil.putString(RegisterNextActivity.this, "nickname", nickname);
                SharePreferencesUtil.putString(RegisterNextActivity.this, "image", image);//储存头像(聊天室取出)
                SharePreferencesUtil.putInt(RegisterNextActivity.this, "isagent", isagent);//是否是代理)
                sp.setUserId(id);
                RegisterEntity.MemberAgentBean memberAgent = registerEntity.getMemberAgent();
                String k3Rate = memberAgent.getK3Rate();//快三返点
                String happytenRate = memberAgent.getHappytenRate();//快乐十分返点
                String sscaiRate = memberAgent.getSscaiRate();//时时彩返点
                String happy8Rate = memberAgent.getHappy8Rate();//快乐8返点
                String xuanwuRate = memberAgent.getXuanwuRate();//11选5返点
                String farmRate = memberAgent.getFarmRate();//重庆农场返点
                String raceRate = memberAgent.getRaceRate();//赛车返点
                String sixRate = memberAgent.getSixRate();//六合彩返点
                String danRate = memberAgent.getDanRate();//pc蛋蛋返点

                SharePreferencesUtil.putString(RegisterNextActivity.this, "1", k3Rate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "2", sscaiRate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "3", raceRate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "4", sixRate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "5", danRate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "6", happy8Rate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "7", farmRate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "8", happytenRate + "");
                SharePreferencesUtil.putString(RegisterNextActivity.this, "9", xuanwuRate + "");
                //Context context,String message,String account,String password,String paypassword
                RequestLoginUtils.loginSuccess(RegisterNextActivity.this, message, nickName, password, "",button);//登录成功相关
            }

            @Override
            public void onFailed(String msg) {
                if(button!=null){
                    button.setClickable(true);
                }
            }
        });

    }


    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            countDownTime--;
            if(countDownTime>=1){
                send_phone_code_tv.setText(countDownTime+Utils.getString(R.string.s后重发));
                myHandler.postDelayed(countDownRunnable,1000);
            }else {
                send_phone_code_tv.setText(SEND_PHONE_CODE);
                countDownTime=61;
                myHandler.removeCallbacks(countDownRunnable);
            }
        }
    };
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
