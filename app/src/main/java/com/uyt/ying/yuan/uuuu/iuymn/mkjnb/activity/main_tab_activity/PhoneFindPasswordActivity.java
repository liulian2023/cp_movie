package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.SlideWebViewPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LimitTextChange;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class PhoneFindPasswordActivity extends BaseActivity {
    @BindView(R.id.forget_account_etv)
    EditText forget_account_etv;
    @BindView(R.id.forget_success_tip_iv)
    ImageView forget_success_tip_iv;
    @BindView(R.id.forget_phone_tv)
    TextView forget_phone_tv;
    @BindView(R.id.phone_code_etv)
    EditText phone_code_etv;
    @BindView(R.id.send_phone_code_tv)
    TextView send_phone_code_tv;
    @BindView(R.id.forget_password_etv)
    EditText forget_password_etv;
    @BindView(R.id.forget_sure_password_etv)
    EditText forget_sure_password_etv;
    @BindView(R.id.find_password_btn)
    Button find_password_btn;
    @BindView(R.id.find_password_group)
    Group find_password_group;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbar_right_tv;
    static String SEND_PHONE_CODE=Utils.getString(R.string.获取验证码);
    int countDownTime = 61;
    MyHandler myHandler;
    private String phone;
    private String tencent_randstr;
    private String tencent_ticket;
    private SlideWebViewPop.Poptype poptype;
    private String appSlidingCheck = SharePreferencesUtil.getString(MyApplication.getInstance(),"appSlidingCheck","");
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.忘记密码));
        myHandler=new MyHandler(new SoftReference<>(this));
        LimitTextChange.StringWatcher(forget_account_etv);
        toolbar_right_tv.setText(Utils.getString(R.string.登录));
    }


    @OnClick({R.id.find_password_btn,R.id.send_phone_code_tv,R.id.toolbar_right_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.find_password_btn:
                String nicknameStr = forget_account_etv.getText().toString();
                if(find_password_group.getVisibility()!=View.VISIBLE){
                    /**
                     * 验证手机号/账号是否合法
                     */
                    if(StringMyUtil.isEmptyString(nicknameStr)){
                        showToast(Utils.getString(R.string.请输入手机号或者账号));
                    }else {
                        checkAccountPhone(nicknameStr);
                    }
                }else {
                    if(checkAllEtv()){
                        requestFindPassword();
                    }
                }


                break;
            case R.id.send_phone_code_tv:
                //点击获取验证码时,使账号输入框失去焦点(请求手机号,用于发送验证码)
//                forget_account_etv.setFocusable(false);
//                send_phone_code_tv.  requestFocus();

                String toString = send_phone_code_tv.getText().toString();
//                if(checkAccountEtv()){
                    if(toString.equals(SEND_PHONE_CODE)){
//                        resSendPhoneCode(forget_account_etv.getText().toString());
                        initSlideWebViewPop(true);
                    }
//                }
                break;
            case R.id.toolbar_right_tv:
                LoginIntentUtil.toLogin(PhoneFindPasswordActivity.this);
                break;
                default:
                    break;
        }
    }

    private void requestFindPassword() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("nickname",nickname.trim());
        data.put("phone",phone.trim());
        // 测试用, 万能验证码
//                    data.put("phoneSMSCode","888888");
        data.put("phoneSMSCode",phone_code_etv.getText().toString().trim());
        data.put("password",forget_password_etv.getText().toString().trim());
        HttpApiUtils.CpRequest(this,null, RequestUtil.FORGET_PASSWORD, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String message = jsonObject.getString("message");
                showToast(message);
                finish();
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void checkAccountPhone(String nicknameStr) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("nickname",nicknameStr);
        HttpApiUtils.CpRequest(PhoneFindPasswordActivity.this, null,RequestUtil.FORGET_PASSWORD, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                phone = jsonObject.getString("phone");
                nickname = jsonObject.getString("nickname");
                if(StringMyUtil.isNotEmpty(nickname)){
                    if(StringMyUtil.isNotEmpty(phone)){
                        if(nicknameStr.length()==11&&Utils.isAllNum(nicknameStr)){
                            forget_phone_tv.setVisibility(View.GONE);
                        }else {
                            forget_phone_tv.setText(Utils.getString(R.string.已绑定手机号)+phone);
                            forget_phone_tv.setVisibility(View.VISIBLE);
                        }
                        forget_success_tip_iv.setVisibility(View.VISIBLE);
                        forget_account_etv.setEnabled(false);//去掉点击时编辑框下面横线:
                        forget_account_etv.setFocusable(false);//不可编辑
                        forget_account_etv.setFocusableInTouchMode(false);//不可编辑
                        find_password_group.setVisibility(View.VISIBLE);
                        find_password_btn.setText(Utils.getString(R.string.立即修改));
                    }else {
                         //只有账号 跳转账号找回密码页面
                        startActivity(new Intent(PhoneFindPasswordActivity.this,AccountFindPasswordActivity.class));
                        finish();
                    }

                }

            }

            @Override
            public void onFailed(String msg) {
//                                forget_phone_tv.setText(Utils.getString(R.string.账号不存在!));
//                                forget_phone_tv.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 弹出滑动pop
     * @param sendPhoneCode dismiss后是否需要开启发送验证码的倒计时计时器  (手机号登录时传true)
     */
    private void initSlideWebViewPop(boolean sendPhoneCode) {
        if(appSlidingCheck.equals("0")){
            //每次弹出滑动验证pop都将滑动验证的数据置空
            tencent_ticket ="";
            tencent_randstr ="";
            poptype = SlideWebViewPop.Poptype.SMS;
            Utils.hideSoftKeyBoard(this);
            SlideWebViewPop slideWebViewPop = new SlideWebViewPop(this, true, poptype);
            slideWebViewPop.setSlideInterface(new SlideWebViewPop.SlideInterface() {
                @Override
                public void onSlideListener(String tencent_randstr, String tencent_ticket) {
                    PhoneFindPasswordActivity.  this.tencent_randstr = tencent_randstr;
                    PhoneFindPasswordActivity. this.tencent_ticket = tencent_ticket;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(PhoneFindPasswordActivity.this,1f);
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
                            ProgressDialogUtil.darkenBackground(PhoneFindPasswordActivity.this,1f);
                            if(sendPhoneCode&&!slideWebViewPop.isManualDismiss()){//手机号注册, 发送验证码
                                //不是用户手动点击导致dismiss时才开启发送手机验证码的倒计时
                                String toString = send_phone_code_tv.getText().toString();
                                if(toString.equals(SEND_PHONE_CODE)){
                                    resSendPhoneCode(phone);
                                }
                            }
                        }
                    });
                }
            });
            slideWebViewPop.showAtLocation(send_phone_code_tv, Gravity.CENTER,0,0);
            ProgressDialogUtil.darkenBackground(this,0.5f);
        }else {
            if(sendPhoneCode){//手机号注册, 发送验证码
                //不是用户手动点击导致dismiss时才开启发送手机验证码的倒计时
                String toString = send_phone_code_tv.getText().toString();
                if(toString.equals(SEND_PHONE_CODE)){
                    resSendPhoneCode(phone);
                }
            }
        }


    }
    private boolean checkAllEtv() {
        String nickname = forget_account_etv.getText().toString();
        String phoneCodeStr = phone_code_etv.getText().toString();
        String password = forget_password_etv.getText().toString();
        String surePassword = forget_sure_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(nickname)){
            showToast(Utils.getString(R.string.请输入账号));
            return false;
        }
        if(StringMyUtil.isEmptyString(phoneCodeStr)){
            showToast(Utils.getString(R.string.请输入手机验证码));
            return false;
        }
        if (StringMyUtil.isEmptyString(password)) {
            showToast(Utils.getString(R.string.请输入密码));
            return  false;
        }
        if(StringMyUtil.isEmptyString(surePassword)){
            showToast(Utils.getString(R.string.请输入确认密码));
            return false;
        }
        if(!password.equals(surePassword)){
            showToast(Utils.getString(R.string.两次输入的密码不一致请重新输入));
            return false;
        }
        if(StringMyUtil.isEmptyString(tencent_ticket)&&appSlidingCheck.equals("0")){
            showToast(Utils.getString(R.string.请先完成滑动验证));
            return false;
        }
        return true;
    }


    private boolean checkAccountEtv(){
        String account = forget_account_etv.getText().toString();
        if(StringMyUtil.isEmptyString(account)){
            showToast(Utils.getString(R.string.请输入手机号或者账号));
            return  false;
        } /*else if(StringMyUtil.isEmptyString(ali_sessionId)){
            showtoast(Utils.getString(R.string.请完成滑动验证再获取验证码));
            return false;
        }*/
        return true;
    }

    private void resSendPhoneCode(String phone) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        data.put("type", 1);
        data.put("nickname", nickname);
        data.put("tencent_ticket", tencent_ticket);
        data.put("tencent_randstr", tencent_randstr);
        HttpApiUtils.CpRequest(this, null,RequestUtil.FORGET_PASSWORD, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.验证码发送成功));
                countDownTime = 61;
                myHandler.post(countDownRunnable);
            }

            @Override
            public void onFailed(String msg) {
                forget_phone_tv.setText(msg);
                forget_phone_tv.setVisibility(View.VISIBLE);
                tencent_randstr ="";
                tencent_ticket ="";
            }
        });
    }
    static class MyHandler extends Handler {
        SoftReference<PhoneFindPasswordActivity> forgetPasswordActivitySoftReference;

        public MyHandler(SoftReference<PhoneFindPasswordActivity> forgetPasswordActivitySoftReference) {
            this.forgetPasswordActivitySoftReference = forgetPasswordActivitySoftReference;
        }

        public SoftReference<PhoneFindPasswordActivity> getForgetPasswordActivitySoftReference() {
            return forgetPasswordActivitySoftReference;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            PhoneFindPasswordActivity phoneFindPasswordActivity = forgetPasswordActivitySoftReference.get();
            super.handleMessage(msg);
        }
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
