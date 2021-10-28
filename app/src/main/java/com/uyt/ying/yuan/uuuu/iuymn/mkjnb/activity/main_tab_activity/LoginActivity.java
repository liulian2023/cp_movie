package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.SlideWebViewPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.NetworkUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RequestLoginUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.Headers;
/*
登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.login_wrap_constrainLayout)
    ConstraintLayout login_wrap_constrainLayout;
    @BindView(R.id.toolbar_back_iv)
    ImageView toolbar_back_iv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbar_title_tv;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbar_right_tv;
    @BindView(R.id.account_login_app_name_tv)
    TextView account_login_app_name_tv;
    @BindView(R.id.account_login_btn)
    Button account_login_btn;
    @BindView(R.id.account_login_account_etv)
    EditText account_login_account_etv;
    @BindView(R.id.account_login_password_etv)
    EditText account_login_password_etv;

    @BindView(R.id.account_forget_password_tv)
    TextView account_forget_password_tv;
    @BindView(R.id.account_login_account_clear_iv)
    ImageView account_login_account_clear_iv;
    @BindView(R.id.account_login_password_clear_iv)
    ImageView account_login_password_clear_iv;
    /*    @BindView(R.id.account_slide_webView)
        WebView account_slide_webView;*/
/*    @BindView(R.id.account_loading_iv)
    GifImageView account_loading_iv;*/
    @BindView(R.id.account_swich_password_iv)
    ImageView account_swich_password_iv;
    @BindView(R.id.account_forget_passwoed_tv2)
    TextView account_forget_passwoed_tv2;
    @BindView(R.id.account_kefu_tv)
    TextView account_kefu_tv;
    @BindView(R.id.phone_login_phone_etv)
    EditText phone_login_phone_etv;
    @BindView(R.id.phone_code_etv)
    EditText phone_login_password_etv;
    @BindView(R.id.get_phone_code_tv)
    TextView get_phone_code_tv;
    /*    @BindView(R.id.phone_slide_webView)
        WebView phone_slide_webView;*/
    @BindView(R.id.phone_login_btn)
    Button phone_login_btn;
    @BindView(R.id.phone_kefu_tv)
    TextView phone_kefu_tv;
    @BindView(R.id.login_version_name_tv)
    TextView login_version_name_tv;
    @BindView(R.id.phone_login_constraintLayout)
    ConstraintLayout phone_login_constraintLayout;
    @BindView(R.id.account_login_constraintLayout)
    ConstraintLayout account_login_constraintLayout;

    @BindView(R.id.phone_login_change_btn)
    Button phone_login_change_btn;
    @BindView(R.id.account_login_change_btn)
    Button account_login_change_btn;
    @BindView(R.id.account_to_register_btn)
    Button account_to_register_btn;
    @BindView(R.id.phone_to_register_btn)
    Button phone_to_register_btn;


    //触发融云和后台单点登录时都会传入的字段,判断是否需要弹出下线pop
    private boolean singleLogin;
    //出发后台单点登录时传入的字段,用于判断下线pop的显示内容
    int flag;
    public long firstTime = 0;
    private PopupWindow singleLoginPop;

    //忘记密码,成功找回后传回的nickName,进入页面时显示
    private String nickName;
    /*final RxPermissions rxPermissions = new RxPermissions(this);
    String mImei;*/
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private String account;
    private String password;
    private String paypassword;
    private String ip;

    String tencent_randstr;
    String tencent_ticket;
    private String appName;
    private String frontUrl;
    private String url;
    private String domain;

    static String SEND_PHONE_CODE=Utils.getString(R.string.获取验证码);
    int countDownTime = 61;

    private boolean mPasswordVisible= false;

    int flipCount=0;
    private String mobileNumSwitch;
    private String appSlidingCheck;
    private String visitor_login_close;
    private String visitor_login_sliding_check;
    private String visitorLogin;
    private String inviteCode;

    //    String isLoginCustom;//右侧客服按钮显示/隐藏
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
//        toolbar_back_iv.setVisibility(View.GONE);
        toolbar_title_tv.setText(Utils.getString(R.string.登录));
        getIntentData();
        if(singleLogin){
            ActivityUtil.getInstance().finishActivityWithoutLoginActivity();
        }
        if(BuildConfig.DEBUG){
            login_version_name_tv.setText(String.format(Utils.getString(R.string.测试版版本号),BuildConfig.VERSION_NAME));
        }else {
            login_version_name_tv.setText(String.format(Utils.getString(R.string.版本号占位),BuildConfig.VERSION_NAME));
        }
        initSharePreferencesData();
        initInvite();

        account_login_account_etv.setSelection(account_login_account_etv.getText().length());

        handler.postDelayed(runnable,500);
    }

    private void requestVisitorBtnShow() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("visitorLogin",1);
        data.put("inviteCode",inviteCode);
//        data.put("inviteCode","6pahi");
        HttpApiUtils.CPnormalRequest(this, null, RequestUtil.VISITOR_SHOW, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                visitorLogin = jsonObject.getString("visitorLogin");
                if(visitor_login_close.equals("0")&&visitorLogin.equals("1")){
                    toolbar_right_tv.setText(Utils.getString(R.string.游客登录));
                }else {
                    toolbar_right_tv.setText(Utils.getString(R.string.注册));
                }
                toolbar_right_tv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void initSharePreferencesData() {
        appName = SharePreferencesUtil.getString(this, "appName", "");
        if (StringMyUtil.isNotEmpty(appName)) {
            account_login_app_name_tv.setText(appName);
        }
        frontUrl = SharePreferencesUtil.getString(LoginActivity.this, "frontUrl", "");
        domain = SharePreferencesUtil.getString(LoginActivity.this, CommonStr.DOMAIN, "");
        mobileNumSwitch = SharePreferencesUtil.getString(LoginActivity.this, "mobileNumSwitch", "");
        appSlidingCheck = SharePreferencesUtil.getString(LoginActivity.this, "appSlidingCheck", "0");
        visitor_login_close = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.VISITOR_LOGIN_CLOSE, "");
        visitor_login_sliding_check = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.VISITOR_LOGIN_SLIDING_CHECK, "");

    }

    @OnTextChanged(value = {R.id.account_login_password_etv,R.id.phone_code_etv},callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onPasswordTextChange(Editable editable){
        //限制中文输入
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
        //清空密码的图标不要
  /*              String string = login_password_etv.getText().toString();
                if(StringMyUtil.isEmptyString(string)){
                    login_password_clear_iv.setVisibility(View.GONE);
                }else {
                    login_password_clear_iv.setVisibility(View.VISIBLE);
                }*/

    }

    @OnTextChanged(value = {R.id.account_login_account_etv},callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onAccountTextChange(Editable editable){
        //限制中文输入
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
        String toString = account_login_account_etv.getText().toString();
        if(StringMyUtil.isEmptyString(toString)){
            account_login_account_clear_iv.setVisibility(View.GONE);
        }else {
            account_login_account_clear_iv.setVisibility(View.VISIBLE);
        }
    }
    @OnClick({R.id.account_login_account_clear_iv,R.id.phone_login_phone_clear_iv,R.id.account_login_password_clear_iv, R.id.account_login_btn,R.id.phone_login_btn,
            R.id.account_forget_password_tv,R.id.get_phone_code_tv,R.id.toolbar_back_iv,R.id.account_kefu_tv, R.id.toolbar_right_tv,R.id.account_forget_passwoed_tv2,
            R.id.account_swich_password_iv,R.id.phone_login_change_btn,R.id.account_login_change_btn,R.id.phone_kefu_tv,R.id.phone_to_register_btn,R.id.account_to_register_btn
    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.get_phone_code_tv:
                /**
                 *先检查输入框  再判断账号是否存在, 账号存在才弹出滑动pop, 防止恶意刷滑动
                 */
                if(checkPhone(false)&&get_phone_code_tv.getText().toString().equals(SEND_PHONE_CODE)){
                    checkPhoneIsExit(true,phone_login_phone_etv.getText().toString());
                }
                break;
            case R.id.account_login_btn:
                if (CheckEdit()&&Utils.isNotFastClick()){
                    checkPhoneIsExit(false,account_login_account_etv.getText().toString());
//                    initSlideWebViewPop(false);
                }
                break;
            case R.id.phone_login_btn:
                if (checkPhone(true)&&Utils.isNotFastClick()){
                    Message msg = Message.obtain();
                    msg.what =2;
                    msg.obj = ip;
                    handler.sendMessage(msg);
                }
                break;
            case R.id.toolbar_right_tv:
                if(Utils.isNotFastClick()){

                    if(toolbar_right_tv.getText().toString().equals(Utils.getString(R.string.注册))){
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    }else {
                        /**
                         * 游客登录
                         */
                        if(visitor_login_sliding_check.equals("0")){
                            /**
                             * 开启游客登录的滑动验证.弹出严冬验证pop
                             */
                            initSlideWebViewPop(false,true);
                        }else {
                            /**
                             * 没看起游客登录的滑动验证, 直接请求登录接口
                             */
                            requestVisitorLogin();
                        }

                    }
                }

                break;
            case R.id.phone_to_register_btn:
            case R.id.account_to_register_btn:
                /**
                 * 注册
                 */
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.account_forget_password_tv:
            case R.id.account_forget_passwoed_tv2:
                if(mobileNumSwitch.equals("0")){
                    startActivity(new Intent(LoginActivity.this, PhoneFindPasswordActivity.class));
                }else {
                    startActivity(new Intent(LoginActivity.this, AccountFindPasswordActivity.class));
                }
                break;
            case R.id.account_login_account_clear_iv:
                account_login_account_etv.setText("");
                break;
            case R.id.phone_login_phone_clear_iv:
                phone_login_phone_etv.setText("");
                break;
            case R.id.account_login_password_clear_iv:
                account_login_password_etv.setText("");
                break;
            case R.id.toolbar_back_iv:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.kefu_tv:
            case R.id.account_kefu_tv:
            case R.id.phone_kefu_tv:
                Intent intentKeFu = new Intent(LoginActivity.this, OnLineKeFuActivity.class);
                intentKeFu.putExtra("toKeFu",true);
                startActivity(intentKeFu);
                break;
            //点击更换密码可见状态
            case R.id.account_swich_password_iv:
                if(mPasswordVisible){
                    account_swich_password_iv.setImageResource(R.drawable.kanjian_icon);
                    account_login_password_etv.setTransformationMethod(PasswordTransformationMethod.getInstance());//密码隐藏
                    account_login_password_etv.setSelection(account_login_password_etv.getText().length());//光标移到最后
                }else {
                    account_login_password_etv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//密码显示
                    account_swich_password_iv.setImageResource(R.drawable.yinc_icon);
                    account_login_password_etv.setSelection(account_login_password_etv.getText().length());//光标移到最后
                }
                mPasswordVisible= !mPasswordVisible;
                break;
            case R.id.phone_login_change_btn:
            case R.id.account_login_change_btn:
                tencent_randstr ="";
                tencent_ticket ="";
                flip( false, false);
                flipCount= flipCount == 0?1:0;
                // 交换v1，v2
/*                ConstraintLayout v = phone_login_constraintLayout;
                phone_login_constraintLayout = account_login_constraintLayout;
                account_login_constraintLayout = v;*/
                break;

            default:
                break;
        }
    }
    /**
     *  判断账号时候存在, 账号存在才弹出滑动pop, 防止恶意刷滑动
     */
    private void checkPhoneIsExit(boolean sendPhoneCode,String value) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("nickname",value);
        HttpApiUtils.CpRequest(LoginActivity.this, null,RequestUtil.FORGET_PASSWORD, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                initSlideWebViewPop(sendPhoneCode,false);
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void requestIp() {
//        ProgressDialogUtil.show(LoginActivity.this,"loading...",false);
        NetworkUtil.GetNetIp("http://ipinfo.io/ip", new NetworkUtil.Listener() {
            @Override
            public void success(String content) {
//                ProgressDialogUtil.stop(LoginActivity.this);
                ip = content;
                ip = ip.replace("\n","");
                Message msg = Message.obtain();
                msg.what =2;
                msg.obj = ip;
                handler.sendMessage(msg);
            }

            @Override
            public void failed(String content) {
//                ProgressDialogUtil.stop(LoginActivity.this);
                ip = "127.0.0.1";
                Message msg = Message.obtain();
                msg.what =2;
                msg.obj = ip;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * 弹出滑动pop
     * @param sendPhoneCode dismiss后是否需要开启发送验证码的倒计时计时器  (手机号登录时传true)
     */
    private void initSlideWebViewPop(boolean sendPhoneCode,boolean isVisitorLogin) {
        if(appSlidingCheck.equals("0")||isVisitorLogin){
            //每次弹出滑动验证pop都将滑动验证的数据置空
            tencent_randstr ="";
            tencent_ticket ="";
            Utils.hideSoftKeyBoard(this);
            SlideWebViewPop.Poptype poptype;
            if(sendPhoneCode){
                poptype = SlideWebViewPop.Poptype.SMS;
            }else {
                poptype = SlideWebViewPop.Poptype.LOGIN;
            }
            SlideWebViewPop slideWebViewPop = new SlideWebViewPop(LoginActivity.this, true, poptype);
            slideWebViewPop.setSlideInterface(new SlideWebViewPop.SlideInterface() {
                @Override
                public void onSlideListener(String tencent_randstr, String tencent_ticket) {
                    LoginActivity.this.tencent_randstr = tencent_randstr;
                    LoginActivity.this.tencent_ticket = tencent_ticket;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(LoginActivity.this,1f);
                            slideWebViewPop.dismiss();
                        }
                    });
                }
            });

            /*
            手机验证码登录时,pop disMiss后需要开启短信验证码的倒计时
             */
            slideWebViewPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(LoginActivity.this,1f);
                            if(sendPhoneCode&&!slideWebViewPop.isManualDismiss()){
                                //不是用户手动点击导致dismiss时才开启发送手机验证码的倒计时
                                String toString = get_phone_code_tv.getText().toString();
                                if(toString.equals(SEND_PHONE_CODE)){
                                    resSendPhoneCode(phone_login_phone_etv.getText().toString());
                                }
                            }else {
                                if(!slideWebViewPop.isManualDismiss()){
                                    if(isVisitorLogin){
                                        //游客登录
                                        requestVisitorLogin();
                                    }else {
                                        //点击账号登录的登录按钮
                                        Message msg = Message.obtain();
                                        msg.what =2;
                                        msg.obj = ip;
                                        handler.sendMessage(msg);
                                    }

                                }
                            }
                        }
                    });
                }
            });
            slideWebViewPop.showAtLocation(get_phone_code_tv, Gravity.CENTER,0,0);
            ProgressDialogUtil.darkenBackground(LoginActivity.this,0.5f);
        }else {
            if(sendPhoneCode){
                String toString = get_phone_code_tv.getText().toString();
                if(toString.equals(SEND_PHONE_CODE)){
                    resSendPhoneCode(phone_login_phone_etv.getText().toString());
                }
            }else {
                    //点击账号登录的登录按钮
                    Message msg = Message.obtain();
                    msg.what =2;
                    msg.obj = ip;
                    handler.sendMessage(msg);
                }

        }


    }

    /**
     * 用户进入app时携带了下载页的邀请码时, 将携带的邀请码写死在view上, 不可修改
     */
    private void initInvite() {
        inviteCode = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.INVITE_CODE,"");
        if(StringMyUtil.isEmptyString(inviteCode)){
            /**
             * 开屏图时粘贴板和接口都没拿到邀请码时, 再请求一次邀请码接口(避免开屏图时接口请求失败时邀请码没拿到)
             */
            HashMap<String, Object> data = new HashMap<>();
            data.put("deviceInfo","1");
            HttpApiUtils.CPnormalRequest(this, null, RequestUtil.CHECK_REGISTER, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    /**
                     * 返回的邀请码不为空直接使用返回的邀请码
                     */
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String inviteCode = jsonObject.getString("inviteCode");
                    if(StringMyUtil.isNotEmpty(inviteCode)){
                        LoginActivity.this.inviteCode  = inviteCode;
                    }
                    requestVisitorBtnShow();
                }

                @Override
                public void onFailed(String msg) {
                    requestVisitorBtnShow();
                }
            });
        }else {
            requestVisitorBtnShow();
        }
    }
    /**
     * 请求游客登录
     */
    private void requestVisitorLogin() {
        showToast(Utils.getString(R.string.正在进行游客登录));
        HashMap<String, Object> data = new HashMap<>();
        data.put("refername",inviteCode);
        data.put("tencent_randstr", tencent_randstr);
        data.put("tencent_ticket", tencent_ticket);
        HttpApiUtils.CpRequest(LoginActivity.this, null, RequestUtil.VISITOR_LOGIN, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                RequestLoginUtils.requestSuccess(LoginActivity.this,result,account,password,"");
            }

            @Override
            public void onFailed(String msg) {
                tencent_randstr ="";
                tencent_ticket ="";
            }
        });
    }

    private void getIntentData() {
        nickName=getIntent().getStringExtra("nickName");
        singleLogin=getIntent().getBooleanExtra("singleLogin",false);
        flag=getIntent().getIntExtra("flag",0);
         /* isLoginCustom= SharePreferencesUtil.getString(LoginActivity.this,"isLoginCustom","");
        if(isLoginCustom.equals("0")){
            kefu.setVisibility(View.VISIBLE);
        }else {
            kefu.setVisibility(View.INVISIBLE);
        }
        if (isOpenPayPass.equals("0")){
            ll_login_tkm.setVisibility(View.VISIBLE);
        }else {
            ll_login_tkm.setVisibility(View.GONE);
        }*/
        if( !StringMyUtil.isEmptyString(nickName)){
            account_login_account_etv.setText(nickName);
        }else {
            account_login_account_etv.setText(SharePreferencesUtil.getString(LoginActivity.this, "account", ""));
        }
    }

    @Override
    protected void init() {
    }
    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            if(singleLogin){
                View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.single_login_pop,null);
                TextView countDownEndSure=view.findViewById(R.id.countdown_pop_sure);
                TextView topTv=view.findViewById(R.id.top_text);
                TextView buttomTv=view.findViewById(R.id.bottom_text);
                //触发后台单点登录时跳而来
                if(flag==1){
                    topTv.setText(Utils.getString(R.string.Token无效));
                    buttomTv.setText(Utils.getString(R.string.请重新登录));
                }
                //触发融云单点登录时跳转而来
                else {
                    topTv.setText(Utils.getString(R.string.当前账号在另一地点登录));
                    buttomTv.setText(Utils.getString(R.string.您已被强制下线));
                }
                countDownEndSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singleLoginPop.dismiss();
                    }
                });
                singleLoginPop= new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                singleLoginPop.setAnimationStyle(R.style.popAlphaanim0_5);
                singleLoginPop.setTouchable(true);//响应内部点击
                singleLoginPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if(!LoginActivity.this.isFinishing()&&!LoginActivity.this.isDestroyed()){
                            ProgressDialogUtil.darkenBackground(LoginActivity.this,1f);
                        }
                    }
                });
                if(!LoginActivity.this.isFinishing()&&!LoginActivity.this.isDestroyed()){
                    singleLoginPop.showAtLocation(account_login_account_etv, Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(LoginActivity.this,0.5f);
                }
            }
        }
    };
    private boolean CheckEdit(){
        account = account_login_account_etv.getText().toString();
        password = account_login_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(account)){
            showToast(Utils.getString(R.string.账号不能为空));
            return false;
        }else if(StringMyUtil.isEmptyString(password)){
            showToast(Utils.getString(R.string.密码不能为空));
            return false;
        }
/*        if (!Utils.checkAccount(account)){
            showtoast(Utils.getString(R.string.请输入字母开头6-11位字母加数字的账号));
            return false;
        } else  if (!Utils.checkPsw(password)){
            showtoast(Utils.getString(R.string.请输入6-16位字母加数字的密码));
            return false;
        } */
        else if(account.length()<6){
            showToast(Utils.getString(R.string.账号不符合规范请重新输入));
            return false;
        }else  if (!Utils.checkPsw(password)){
            showToast(Utils.getString(R.string.请输入6到16位字母加数字的密码));
            return false;
        }
        return true;
    }

    private boolean checkPhone(boolean needPhoneCode){
        String phone = phone_login_phone_etv.getText().toString();
        if(StringMyUtil.isEmptyString(phone)){
            showToast(Utils.getString(R.string.请输入手机号));
            return  false;
        }else if(phone.length()!=11){
            showToast(Utils.getString(R.string.手机号格式不正确));
            return  false;
        }
        if(needPhoneCode){
            if(StringMyUtil.isEmptyString(phone_login_password_etv.getText().toString())){
                showToast(Utils.getString(R.string.请输入手机验证码));
                return false;
            }
        }
        return true;
    }

    private void loginSuccess(String message) {
        if (message.equals(Utils.getString(R.string.登入成功))) {
            SharePreferencesUtil.putString(LoginActivity.this, "account", account);
            SharePreferencesUtil.putString(LoginActivity.this, "password", password);
            SharePreferencesUtil.putString(LoginActivity.this, "RemembePassword", password);
            SharePreferencesUtil.putBoolean(LoginActivity.this, "isLogin", true);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("fromLogin",true);//登录成功回到首页需要弹出活动pop
            startActivity(intent);
            finish();
        } else {
            showToast(Utils.getString(R.string.账号或密码错误));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
          /*  long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {
                showtoast(Utils.getString(R.string.再按一次退出程序));
                firstTime = secondTime;
            } else {
                Intent intent = new Intent(getApplicationContext(), AboutPersonService.class);
                stopService(intent);
                ActivityUtil.getInstance().exitSystem();
            }*/
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            countDownTime--;
            if(countDownTime>=1){
                get_phone_code_tv.setText(countDownTime+Utils.getString(R.string.s后重发));
                handler.postDelayed(countDownRunnable,1000);
            }else {
                get_phone_code_tv.setText(SEND_PHONE_CODE);
                countDownTime=61;
                handler.removeCallbacks(countDownRunnable);
            }
        }
    };
    private void resSendPhoneCode(String phone) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        data.put("type", 2);
        data.put("tencent_randstr", tencent_randstr);
        data.put("tencent_ticket", tencent_ticket);
        HttpApiUtils.CpRequest(this, null,RequestUtil.SEND_PHONE_CODE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.验证码发送成功));
                countDownTime = 61;
                handler.post(countDownRunnable);
            }

            @Override
            public void onFailed(String msg) {
                tencent_randstr ="";
                tencent_ticket ="";
            }
        });
    }
    /*
    用户登录成功后,需要调用application中连接融云服务器的方法,并创建消息监听
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Long user_id = SharePreferencesUtil.getLong(LoginActivity.this, "user_id", 0l);
            switch (msg.what) {
                //请求融云信息
                case 1:

                    HashMap<String, Object> data = new HashMap<>();
                    data.put("user_id", user_id);
                    data.put("loginType","1");
                    Utils.docking(data, RequestUtil.REQUEST_Chat1, 0, new DockingUtil.ILoaderListener() {
                        @Override
                        public void success(String content)  {
                            JSONObject jsonObject = JSONObject.parseObject(content);
                            String token = null;
                            String appKey = null;
                            try {
                                token = AESUtil.decrypt(jsonObject.getString("token"));
                                appKey = AESUtil.decrypt(jsonObject.getString("appKey"));
                                SharePreferencesUtil.putString(LoginActivity.this,"rongYunKey",appKey);
                                SharePreferencesUtil.putString(LoginActivity.this, "chatroomToken", token);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String chatRoomId = jsonObject.getString("chatroomId");
                            SharePreferencesUtil.putString(LoginActivity.this, "chatroomId", chatRoomId);
                            //是否是管理员 0 否1 是
                            String isSuperRoomManager = jsonObject.getString("isSuperRoomManager");
                            SharePreferencesUtil.putString(LoginActivity.this,"isSuperRoomManager",isSuperRoomManager);
                            //融云id
                            String rcUsId = jsonObject.getString("rcUsId");
                            SharePreferencesUtil.putString(LoginActivity.this,"rcUsId",rcUsId);

                            String vipSpeak = jsonObject.getString("vipSpeak");//vip发言特权

                            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.VIP_SPEAK,vipSpeak);

                            //   Application application = MyApplicationLike.getInstance();
//                        String chatroomToken = SharePreferencesUtil.getString(LoginActivity.this, "chatroomToken", "");
//                        String chatRoomId = SharePreferencesUtil.getString(LoginActivity.this, "chatRoomId", "");
                            RongLibUtils.initRongYun(appKey);
                            RongLibUtils.connectRongYun(token);
                            //     myApplication.rongYunListener();
                        }

                        @Override
                        public void failed(MessageHead messageHead) {
                        }
                    });
                    break;
                case 2:
                    if(StringMyUtil.isEmptyString(tencent_randstr)&&appSlidingCheck.equals("0")){
                        showToast(Utils.getString(R.string.请完成滑动验证));
                    }else {
                        //请求登录接口
//                        ProgressDialogUtil.show(LoginActivity.this,Utils.getString(R.string.登录中),false);
                        HashMap<String, Object> data1 = new HashMap<>();
                        if(phone_login_constraintLayout.getVisibility() == View.VISIBLE){
                            data1.put("phoneSMSCode",phone_login_password_etv.getText().toString());
                            data1.put("nickname",phone_login_phone_etv.getText().toString());
                        }else {
                            data1.put("nickname",account_login_account_etv.getText().toString());
                            data1.put("password", account_login_password_etv.getText().toString());
                            data1.put("ip", ip);
                            data1.put("tencent_randstr", tencent_randstr);
                            data1.put("tencent_ticket", tencent_ticket);
                        }
                        HttpApiUtils.CpRequest(LoginActivity.this,null, RequestUtil.REQUEST_10r, data1, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                account_login_btn.setClickable(true);
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                                String password = memberInfo.getString("password");
                                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.USER_PASSWORD,StringMyUtil.isEmptyString(password)?"":password);
                                RequestLoginUtils.requestSuccess(LoginActivity.this,result,account,password,"");
                            }

                            @Override
                            public void onFailed(String msg) {
//                                account_slide_webView.loadUrl(url);
                                tencent_randstr ="";
                                tencent_ticket ="";
                            }
                        });
/*                        Utils.docking(data1, RequestUtil.REQUEST_10r, 0, new DockingUtil.ILoaderListener() {
                            @Override
                            public void success(String content) {

                            }

                            @Override
                            public void failed(MessageHead messageHead) {
//                        PopupWindowUtil.disMiss(LoginActivity.this);


                            }
                        });*/

                    }

                    break;
            }
        }
    };



    @Override
    public void onNetChange(boolean netWorkState) {

    }

    /**
     * 3d 翻转
     * @param scale 是否开启scale效果
     * @param reverse 是否开始reverse效果
     */
    private void flip( boolean scale, boolean reverse) {
        final int duration = 300;
        final int degree = reverse ? 90 : -90;
        final int degree2 = -degree;

        final ObjectAnimator a, b;
        if (!scale) {
            a = ObjectAnimator.ofFloat(login_wrap_constrainLayout, "rotationY", 0, degree);
            b = ObjectAnimator.ofFloat(login_wrap_constrainLayout, "rotationY", degree2, 0);
        } else {
            final float scaleX = 0.5f;
            final float scaleY = 0.5f;
            a = ObjectAnimator.ofPropertyValuesHolder(login_wrap_constrainLayout,
                    PropertyValuesHolder.ofFloat("rotationY", 0, degree),
                    PropertyValuesHolder.ofFloat("scaleX", 1, scaleX),
                    PropertyValuesHolder.ofFloat("scaleY", 1, scaleY));
            b = ObjectAnimator.ofPropertyValuesHolder(login_wrap_constrainLayout,
                    PropertyValuesHolder.ofFloat("rotationY", degree2, 0),
                    PropertyValuesHolder.ofFloat("scaleX", scaleX, 1),
                    PropertyValuesHolder.ofFloat("scaleY", scaleY, 1));
        }

        a.setInterpolator(new LinearInterpolator());
        b.setInterpolator(new LinearInterpolator());
        a.setDuration(duration);
        b.setDuration(duration);

        a.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(flipCount==0){
                    phone_login_constraintLayout.setVisibility(View.GONE);
                    account_login_constraintLayout.setVisibility(View.VISIBLE);
                }else {
                    phone_login_constraintLayout.setVisibility(View.VISIBLE);
                    account_login_constraintLayout.setVisibility(View.GONE);
                }

                if (scale) { // 恢复scale
                    login_wrap_constrainLayout.setScaleX(1);
                    login_wrap_constrainLayout.setScaleY(1);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        if(flipCount==0){
            phone_login_constraintLayout.setVisibility(View.VISIBLE);
            account_login_constraintLayout.setVisibility(View.GONE);
        }else {
            account_login_constraintLayout.setVisibility(View.VISIBLE);
            phone_login_constraintLayout.setVisibility(View.GONE);
        }
        if(phone_login_constraintLayout.getVisibility() == View.VISIBLE){
            toolbar_title_tv.setText(Utils.getString(R.string.手机号登录));
        }else {
            toolbar_title_tv.setText(Utils.getString(R.string.登录));
        }
        AnimatorSet set = new AnimatorSet();
        set.play(a).before(b);
        set.start();
    }
}