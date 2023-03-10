package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.CustomVideoView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.SlideWebViewPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.AESUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RequestLoginUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.alibaba.fastjson.JSONObject;
import com.gyf.immersionbar.ImmersionBar;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.Headers;

/*
????????????
 */
public class LoginActivity2 extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.login_wrap_constrainLayout)
    ConstraintLayout login_wrap_constrainLayout;
    @BindView(R.id.account_login_btn)
    TextView account_login_btn;
    @BindView(R.id.account_login_account_etv)
    EditText account_login_account_etv;
    @BindView(R.id.account_login_password_etv)
    EditText account_login_password_etv;
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
    @BindView(R.id.logo_iv)
    ImageView logo_iv;
    /*    @BindView(R.id.phone_slide_webView)
        WebView phone_slide_webView;*/
    @BindView(R.id.phone_login_btn)
    TextView phone_login_btn;
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

    @BindView(R.id.videoview)
    CustomVideoView videoview;

    @BindView(R.id.account_visitor_login_tv)
    TextView account_visitor_login_tv;
    @BindView(R.id.phone_visitor_login_tv)
    TextView phone_visitor_login_tv;


    //?????????????????????????????????????????????????????????,??????????????????????????????pop
    private boolean singleLogin;
    //??????????????????????????????????????????,??????????????????pop???????????????
    int flag;
    public long firstTime = 0;
    private PopupWindow singleLoginPop;

    //????????????,????????????????????????nickName,?????????????????????
    private String nickName;
    /*final RxPermissions rxPermissions = new RxPermissions(this);
    String mImei;*/
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private String account;
    private String password;
    private String ip;

    String tencent_randstr;
    String tencent_ticket;
    private String appName;
    private String frontUrl;
    private String url;
    private String domain;

    static String SEND_PHONE_CODE=Utils.getString(R.string.???????????????);
    int countDownTime = 61;

    private boolean mPasswordVisible= false;

    int flipCount=0;
    private String mobileNumSwitch;
    private String appSlidingCheck;
    private String phone;
    private String visitor_login_close;
    private String visitor_login_sliding_check;
    private String visitorLogin="";
    private String inviteCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login2);
        ImmersionBar.with(this)
                .navigationBarColor(R.color.transparent)
                .init();
        ButterKnife.bind(this);
        initVideoView();
        getIntentData();
        if(singleLogin){
            ActivityUtil.getInstance().finishActivityWithoutLoginActivity();
        }
        if(BuildConfig.DEBUG){
            login_version_name_tv.setText(String.format(Utils.getString(R.string.??????????????????),BuildConfig.VERSION_NAME));
        }else {
            login_version_name_tv.setText(String.format(Utils.getString(R.string.???????????????),BuildConfig.VERSION_NAME));
        }
        initSharePreferencesData();
        initInvite();

        account_login_account_etv.setSelection(account_login_account_etv.getText().length());
        handler.postDelayed(runnable,500);
        GlideLoadViewUtil.LoadNormalView(this,Utils.getHomeLogo("liveIcon12"),logo_iv);
    }

    private void initSharePreferencesData() {
        appName = SharePreferencesUtil.getString(this, "appName", "");
        frontUrl = SharePreferencesUtil.getString(LoginActivity2.this, "frontUrl", "");
        domain = SharePreferencesUtil.getString(LoginActivity2.this, CommonStr.DOMAIN, "");
        mobileNumSwitch = SharePreferencesUtil.getString(LoginActivity2.this, "mobileNumSwitch", "");
        appSlidingCheck = SharePreferencesUtil.getString(LoginActivity2.this, "appSlidingCheck", "0");
        visitor_login_close = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.VISITOR_LOGIN_CLOSE, "");
        visitor_login_sliding_check = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.VISITOR_LOGIN_SLIDING_CHECK, "");

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
                if(visitorLogin.equals("1")&&visitor_login_close.equals("0")){
                    account_visitor_login_tv.setVisibility(View.VISIBLE);
                    phone_visitor_login_tv.setVisibility(View.VISIBLE);
                }else {
                    account_visitor_login_tv.setVisibility(View.GONE);
                    phone_visitor_login_tv.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
    @Override
    protected void onRestart() {
        initVideoView();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        videoview.stopPlayback();
        super.onStop();
    }

    @OnTextChanged(value = {R.id.account_login_password_etv,R.id.phone_code_etv},callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onPasswordTextChange(Editable editable){
        //??????????????????
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
        //???????????????????????????
  /*              String string = login_password_etv.getText().toString();
                if(StringMyUtil.isEmptyString(string)){
                    login_password_clear_iv.setVisibility(View.GONE);
                }else {
                    login_password_clear_iv.setVisibility(View.VISIBLE);
                }*/

    }

    @OnTextChanged(value = {R.id.account_login_account_etv},callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onAccountTextChange(Editable editable){
        //??????????????????
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
    }
    /**
     * ????????????app????????????????????????????????????, ??????????????????????????????view???, ????????????
     */
    private void initInvite() {
         inviteCode = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.INVITE_CODE,"");
        if(StringMyUtil.isEmptyString(inviteCode)){
            /**
             * ??????????????????????????????????????????????????????, ??????????????????????????????(?????????????????????????????????????????????????????????)
             */
            HashMap<String, Object> data = new HashMap<>();
            data.put("deviceInfo","1");
            HttpApiUtils.CPnormalRequest(this, null, RequestUtil.CHECK_REGISTER, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    /**
                     * ?????????????????????????????????????????????????????????
                     */
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String inviteCode = jsonObject.getString("inviteCode");
                    if(StringMyUtil.isNotEmpty(inviteCode)){
                       LoginActivity2.this.inviteCode = inviteCode;
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

    @OnClick({R.id.account_login_btn,R.id.phone_login_btn,
            R.id.get_phone_code_tv,R.id.account_kefu_tv,R.id.account_forget_passwoed_tv2,
            R.id.account_swich_password_iv,R.id.phone_login_change_btn,R.id.account_login_change_btn,R.id.register_btn,R.id.phone_register_btn,
            R.id.account_visitor_login_tv,R.id.phone_visitor_login_tv
    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.account_visitor_login_tv:
            case R.id.phone_visitor_login_tv:
                if(Utils.isNotFastClick()){
                    /**
                     * ????????????
                     */
                    if(visitor_login_sliding_check.equals("0")){
                        /**
                         * ?????????????????????????????????.??????????????????pop
                         */
                        initSlideWebViewPop(false,true);
                    }else {
                        /**
                         * ????????????????????????????????????, ????????????????????????
                         */
                        requestVisitorLogin();
                    }
                }

                break;
            case R.id.get_phone_code_tv:
                /**
                 * ???????????????
                 *??????????????????  ???????????????????????????, ???????????????????????????pop, ?????????????????????
                 */
                if(checkPhone(false)&&get_phone_code_tv.getText().toString().equals(SEND_PHONE_CODE)){
                    checkPhoneIsExit(true,phone_login_phone_etv.getText().toString());
                }
                break;
            case R.id.account_login_btn:
                /**
                 * ????????????
                 */
                if (CheckEdit()&&Utils.isNotFastClick()){
                    checkPhoneIsExit(false,account_login_account_etv.getText().toString());
//                    initSlideWebViewPop(false);
                }
                break;
            case R.id.phone_login_btn:
                /**
                 * ???????????????
                 */
                if (checkPhone(true)&&Utils.isNotFastClick()){
                    Message msg = Message.obtain();
                    msg.what =2;
                    msg.obj = ip;
                    handler.sendMessage(msg);
                }
                break;
            case R.id.account_forget_password_tv:
            case R.id.account_forget_passwoed_tv2:
                if(mobileNumSwitch.equals("0")){
                    startActivity(new Intent(LoginActivity2.this, PhoneFindPasswordActivity.class));
                }else {
                    startActivity(new Intent(LoginActivity2.this, AccountFindPasswordActivity.class));
                }
                break;
            case R.id.kefu_tv:
            case R.id.account_kefu_tv:
            case R.id.phone_kefu_tv:
                Intent intentKeFu = new Intent(LoginActivity2.this, OnLineKeFuActivity.class);
                intentKeFu.putExtra("toKeFu",true);
                startActivity(intentKeFu);
                break;

            case R.id.account_swich_password_iv:
                /**
                 * ??????????????????????????????
                 */
                if(mPasswordVisible){
                    account_swich_password_iv.setImageResource(R.drawable.mm_kej);
                    account_login_password_etv.setTransformationMethod(PasswordTransformationMethod.getInstance());//????????????
                    account_login_password_etv.setSelection(account_login_password_etv.getText().length());//??????????????????
                }else {
                    account_swich_password_iv.setImageResource(R.drawable.mm_bkj);
                    account_login_password_etv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//????????????
                    account_login_password_etv.setSelection(account_login_password_etv.getText().length());//??????????????????
                }
                mPasswordVisible= !mPasswordVisible;
                break;
            case R.id.phone_login_change_btn:
            case R.id.account_login_change_btn:
                tencent_randstr ="";
                tencent_ticket ="";
                flip( false, false);
                flipCount= flipCount == 0?1:0;
                // ??????v1???v2
/*                ConstraintLayout v = phone_login_constraintLayout;
                phone_login_constraintLayout = account_login_constraintLayout;
                account_login_constraintLayout = v;*/
                break;
            case R.id.register_btn:
            case R.id.phone_register_btn:
                startActivity(new Intent(LoginActivity2.this, RegisterActivity.class));
                break;

            default:
                break;
        }
    }

    /**
     * ??????????????????
     */
    private void requestVisitorLogin() {
        showToast(Utils.getString(R.string.????????????????????????));
        HashMap<String, Object> data = new HashMap<>();
        data.put("refername",inviteCode);
        data.put("tencent_randstr", tencent_randstr);
        data.put("tencent_ticket", tencent_ticket);
        HttpApiUtils.CpRequest(LoginActivity2.this, null, RequestUtil.VISITOR_LOGIN, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                RequestLoginUtils.requestSuccess(LoginActivity2.this,result,account,password,"");
            }

            @Override
            public void onFailed(String msg) {
                tencent_randstr ="";
                tencent_ticket ="";
            }
        });
    }

    /**
     *  ????????????????????????, ???????????????????????????pop, ?????????????????????
     */
    private void checkPhoneIsExit(boolean sendPhoneCode,String value) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("nickname",value);
        HttpApiUtils.CpRequest(LoginActivity2.this, null,RequestUtil.FORGET_PASSWORD, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                initSlideWebViewPop(sendPhoneCode,false);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }



    /**
     * ????????????pop
     * @param sendPhoneCode dismiss?????????????????????????????????????????????????????????  (?????????????????????true)
     */
    private void initSlideWebViewPop(boolean sendPhoneCode,boolean isVisitorLogin) {
        //??????????????????????????????, ??????????????????????????????????????????
        if(appSlidingCheck.equals("0")||isVisitorLogin){
            //????????????????????????pop?????????????????????????????????
            tencent_randstr ="";
            tencent_ticket ="";
            Utils.hideSoftKeyBoard(this);
            SlideWebViewPop.Poptype poptype;
            if(sendPhoneCode){
                poptype = SlideWebViewPop.Poptype.SMS;
            }else {
                poptype = SlideWebViewPop.Poptype.LOGIN;
            }
            SlideWebViewPop slideWebViewPop = new SlideWebViewPop(LoginActivity2.this, true, poptype);
            slideWebViewPop.setSlideInterface(new SlideWebViewPop.SlideInterface() {
                @Override
                public void onSlideListener(String tencent_randstr, String tencent_ticket) {
                    LoginActivity2.this.tencent_randstr = tencent_randstr;
                    LoginActivity2.this.tencent_ticket = tencent_ticket;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(LoginActivity2.this,1f);
                            slideWebViewPop.dismiss();
                        }
                    });
                }
            });

            /*
            ????????????????????????,pop disMiss??????????????????????????????????????????
             */
            slideWebViewPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(LoginActivity2.this,1f);
                            if(sendPhoneCode&&!slideWebViewPop.isManualDismiss()){
                                //??????????????????????????????dismiss?????????????????????????????????????????????
                                String toString = get_phone_code_tv.getText().toString();
                                if(toString.equals(SEND_PHONE_CODE)){
                                    resSendPhoneCode(phone_login_phone_etv.getText().toString());
                                }
                            }else {
                                if(!slideWebViewPop.isManualDismiss()){
                                    if(isVisitorLogin){
                                        //????????????
                                        requestVisitorLogin();
                                    }else {
                                        //???????????????????????????????????????
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
            ProgressDialogUtil.darkenBackground(LoginActivity2.this,0.5f);
        }else {
            if(sendPhoneCode){
                String toString = get_phone_code_tv.getText().toString();
                if(toString.equals(SEND_PHONE_CODE)){
                    resSendPhoneCode(phone_login_phone_etv.getText().toString());
                }
            }else {

                    Message msg = Message.obtain();
                    msg.what =2;
                    msg.obj = ip;
                    handler.sendMessage(msg);
                }


        }


    }

    private void getIntentData() {
        nickName=getIntent().getStringExtra("nickName");
        singleLogin=getIntent().getBooleanExtra("singleLogin",false);
        flag=getIntent().getIntExtra("flag",0);
        if( !StringMyUtil.isEmptyString(nickName)){
            account_login_account_etv.setText(nickName);
        }else {
            account_login_account_etv.setText(SharePreferencesUtil.getString(LoginActivity2.this, "account", ""));
        }
    }

    @Override
    protected void init() {
    }
    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            if(singleLogin){
                View view = LayoutInflater.from(LoginActivity2.this).inflate(R.layout.single_login_pop,null);
                TextView countDownEndSure=view.findViewById(R.id.countdown_pop_sure);
                TextView topTv=view.findViewById(R.id.top_text);
                TextView buttomTv=view.findViewById(R.id.bottom_text);
                //????????????????????????????????????
                if(flag==1){
                    topTv.setText(Utils.getString(R.string.Token??????));
                    buttomTv.setText(Utils.getString(R.string.???????????????));
                }
                //???????????????????????????????????????
                else {
                    topTv.setText(Utils.getString(R.string.?????????????????????????????????));
                    buttomTv.setText(Utils.getString(R.string.?????????????????????));
                }
                countDownEndSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singleLoginPop.dismiss();
                    }
                });
                singleLoginPop= new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                singleLoginPop.setAnimationStyle(R.style.popAlphaanim0_5);
                singleLoginPop.setTouchable(true);//??????????????????
                singleLoginPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if(!LoginActivity2.this.isFinishing()&&!LoginActivity2.this.isDestroyed()){
                            ProgressDialogUtil.darkenBackground(LoginActivity2.this,1f);
                        }
                    }
                });
                if(!LoginActivity2.this.isFinishing()&&!LoginActivity2.this.isDestroyed()){
                    singleLoginPop.showAtLocation(account_login_account_etv, Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(LoginActivity2.this,0.5f);
                }
            }
        }
    };
    private boolean CheckEdit(){
        account = account_login_account_etv.getText().toString();
        password = account_login_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(account)){
            showToast(Utils.getString(R.string.??????????????????));
            return false;
        }else if(StringMyUtil.isEmptyString(password)){
            showToast(Utils.getString(R.string.??????????????????));
            return false;
        }else if(account.length()<6){
            showToast(Utils.getString(R.string.????????????????????????????????????));
            return false;
        }else  if (!Utils.checkPsw(password)){
            showToast(Utils.getString(R.string.?????????6???16???????????????????????????));
            return false;
        }
        return true;
    }

    private boolean checkPhone(boolean needPhoneCode){
        phone = phone_login_phone_etv.getText().toString();
        if(StringMyUtil.isEmptyString(phone)){
            showToast(Utils.getString(R.string.??????????????????));
            return  false;
        }else if(phone.length()!=11){
            showToast(Utils.getString(R.string.????????????????????????));
            return  false;
        }
        if(needPhoneCode){
            if(StringMyUtil.isEmptyString(phone_login_password_etv.getText().toString())){
                showToast(Utils.getString(R.string.????????????????????????));
                return false;
            }
        }
        return true;
    }

    private void loginSuccess(String message) {
        if (message.equals(Utils.getString(R.string.????????????))) {
            SharePreferencesUtil.putString(LoginActivity2.this, "account", account);
            SharePreferencesUtil.putString(LoginActivity2.this, "password", password);
            SharePreferencesUtil.putString(LoginActivity2.this, "RemembePassword", password);
            SharePreferencesUtil.putBoolean(LoginActivity2.this, "isLogin", true);
//            if(cancelLogin){
            Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
            intent.putExtra("fromLogin",true);//??????????????????????????????????????????pop
            startActivity(intent);
//            }
            finish();
        } else {
            showToast(Utils.getString(R.string.?????????????????????));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            startActivity(new Intent(LoginActivity2.this,MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            countDownTime--;
            if(countDownTime>=1){
                get_phone_code_tv.setText(countDownTime+Utils.getString(R.string.s?????????));
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
                showToast(Utils.getString(R.string.?????????????????????));
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
    ?????????????????????,????????????application?????????????????????????????????,?????????????????????
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Long user_id = SharePreferencesUtil.getLong(LoginActivity2.this, "user_id", 0l);
            switch (msg.what) {
                //??????????????????
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
                                SharePreferencesUtil.putString(LoginActivity2.this,"rongYunKey",appKey);
                                SharePreferencesUtil.putString(LoginActivity2.this, "chatroomToken", token);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String chatRoomId = jsonObject.getString("chatroomId");
                            SharePreferencesUtil.putString(LoginActivity2.this, "chatroomId", chatRoomId);
                            //?????????????????? 0 ???1 ???
                            String isSuperRoomManager = jsonObject.getString("isSuperRoomManager");
                            SharePreferencesUtil.putString(LoginActivity2.this,"isSuperRoomManager",isSuperRoomManager);
                            //??????id
                            String rcUsId = jsonObject.getString("rcUsId");
                            SharePreferencesUtil.putString(LoginActivity2.this,"rcUsId",rcUsId);

                            String vipSpeak = jsonObject.getString("vipSpeak");//vip????????????

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
                        showToast(Utils.getString(R.string.?????????????????????));
                    }else {
                        //??????????????????
//                        ProgressDialogUtil.show(LoginActivity.this,Utils.getString(R.string.?????????),false);
                        HashMap<String, Object> data1 = new HashMap<>();
                        data1.put("nickname", StringMyUtil.isEmptyString(account)?phone:account);
                        if(phone_login_constraintLayout.getVisibility() == View.VISIBLE){
                            data1.put("phoneSMSCode",phone_login_password_etv.getText().toString());
                        }else {
                            data1.put("password", password);
                            data1.put("ip", ip);
                            data1.put("tencent_randstr", tencent_randstr);
                            data1.put("tencent_ticket", tencent_ticket);
                        }
                        HttpApiUtils.CpRequest(LoginActivity2.this,null, RequestUtil.REQUEST_10r, data1, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                account_login_btn.setClickable(true);
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                                String password = memberInfo.getString("password");
                                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.USER_PASSWORD,StringMyUtil.isEmptyString(password)?"":password);
                                RequestLoginUtils.requestSuccess(LoginActivity2.this,result,account,password,"");
                            }

                            @Override
                            public void onFailed(String msg) {
//                                account_slide_webView.loadUrl(url);
                                tencent_randstr ="";
                                tencent_ticket ="";
                            }
                        });


                    }

                    break;
            }
        }
    };


    @Override
    public void onNetChange(boolean netWorkState) {

    }

    /**
     * 3d ??????
     * @param scale ????????????scale??????
     * @param reverse ????????????reverse??????
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

                if (scale) { // ??????scale
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
/*        if(phone_login_constraintLayout.getVisibility() == View.VISIBLE){
            toolbar_title_tv.setText(Utils.getString(R.string.???????????????));
        }else {
            toolbar_title_tv.setText(Utils.getString(R.string.??????));
        }*/
        AnimatorSet set = new AnimatorSet();
        set.play(a).before(b);
        set.start();
    }


    private void initVideoView() {
        videoview.setVideoURI(Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.login_backgroup_video));
        // ??????
        videoview.start();
        // ????????????
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });
        videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;// ????????????true?????????????????????????????????????????????
            }
        });
    }

}