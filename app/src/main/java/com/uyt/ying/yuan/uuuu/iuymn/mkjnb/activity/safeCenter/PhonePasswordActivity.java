package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.SlideWebViewPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RequestLoginUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;

public class PhonePasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText phoneEdit;
    private EditText payPasswodEdit;
    private Button sureBtn;
    private EditText phone_code_edv;
    private TextView get_phone_code_tv;
    private boolean fromLogin;
    private String loginResult;
    private String password;
    private String account;


    private String tencent_ticket;
    private String tencent_randstr;
    static String SEND_PHONE_CODE=Utils.getString(R.string.获取验证码);
    int countDownTime = 61;
    MyHandler myHandler;
    private SlideWebViewPop.Poptype poptype;
    private String appSlidingCheck = SharePreferencesUtil.getString(MyApplication.getInstance(),"appSlidingCheck","");
    class MyHandler extends Handler {}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_password);
        myHandler = new MyHandler();
        initView();
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
    }

    @Override
    protected void init() {

    }

    public void initView() {
        get_phone_code_tv = findViewById(R.id.get_phone_code_tv);
        phone_code_edv = findViewById(R.id.phone_code_edv);
        phoneEdit = findViewById(R.id.phone_password_edit);
        payPasswodEdit = findViewById(R.id.pay_password_edit);
        sureBtn = findViewById(R.id.sure_button);
        get_phone_code_tv.setOnClickListener(this);
        sureBtn.setOnClickListener(this);
        Intent intent = getIntent();
        fromLogin = intent.getBooleanExtra("fromLogin",false);
        boolean havePhone = intent.getBooleanExtra("havePhone", false);
        loginResult = intent.getStringExtra("loginResult");
        password = intent.getStringExtra("password");
        account = intent.getStringExtra("account");
        if (havePhone) {//更改actionBar的内容
            CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.修改密保手机));
        } else {
            CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.设置密保手机));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_phone_code_tv:
                if(StringUtils.isEmpty(phoneEdit.getText().toString())){
                    showToast(Utils.getString(R.string.请输入手机号));
                }else if(get_phone_code_tv.getText().toString().equals(SEND_PHONE_CODE)){
                    initSlideWebViewPop(true);
                }
                break;
            case R.id.sure_button:
                Intent intent = getIntent();
                boolean havePhone = intent.getBooleanExtra("havePhone", false);
                String payPassword = SharePreferencesUtil.getString(this, "payPassword", "");
                String phone = phoneEdit.getText().toString();
                String Password = payPasswodEdit.getText().toString();
                if (StringMyUtil.isEmptyString(phone)) {
                    showToast(Utils.getString(R.string.手机号不能为空));
                } else if (phone.length() != 11) {
                    showToast(Utils.getString(R.string.手机格式不规范,请重新输入));
                } else if (StringMyUtil.isEmptyString(Password)) {
                    showToast(Utils.getString(R.string.提款密码不能为空));
                } else if(StringMyUtil.isEmptyString(tencent_randstr)&&appSlidingCheck.equals("0")){
                    if(BuildConfig.DEBUG){
                        showToast(Utils.getString(R.string.滑动验证失败, DEBUG 放行));
                        requestBindPhone(phone,Password);
                    }else {
                        showToast(Utils.getString(R.string.请先获取手机验证码));
                    }
                } else if(StringMyUtil.isEmptyString(phone_code_edv.getText().toString())){
                    showToast(Utils.getString(R.string.请输入手机验证码));
                }/*else if (!payPassword.equals(Password)&&!StringMyUtil.isEmptyString(payPassword)) {
                    showtoast(Utils.getString(R.string.安全密码错误,请重新输入));
                } */else {
                    requestBindPhone(phone, Password);
                }
                break;
        }
    }

    private void requestBindPhone(String phone, String password) {
        Map<String, Object> dataSetPhone = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        dataSetPhone.put("user_id", user_id);//user_id
        dataSetPhone.put("type", 3);//3表示绑定手机
        dataSetPhone.put("value", phone);//手机号码
        dataSetPhone.put("oldvalue", password);
        dataSetPhone.put("code",phone_code_edv.getText().toString());
        Utils.docking(dataSetPhone, RequestUtil.REQUEST_10rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                SharePreferencesUtil.putString(PhonePasswordActivity.this,"phone",phone);//修改成功,重新存入phone的值(个人中心和安全中心需要用到)
                if(fromLogin){
                    showToast(Utils.getString(R.string.绑定手机成功,正在登录));
                    RequestLoginUtils.requestSuccess(PhonePasswordActivity.this,loginResult,account,password,"");
                }else {
                    showToast(Utils.getString(R.string.绑定手机成功));
                    finish();
                }

            }
            @Override
            public void failed(MessageHead messageHead) {
                showToast(messageHead.getInfo());
                tencent_randstr ="";
                tencent_ticket ="";
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
            tencent_randstr ="";
            tencent_ticket ="";
            poptype = SlideWebViewPop.Poptype.SMS;

            Utils.hideSoftKeyBoard(this);
            SlideWebViewPop slideWebViewPop = new SlideWebViewPop(this, true, poptype);
            slideWebViewPop.setSlideInterface(new SlideWebViewPop.SlideInterface() {
                @Override
                public void onSlideListener(String tencent_randstr, String tencent_ticket) {
                    PhonePasswordActivity.  this.tencent_randstr = tencent_randstr;
                    PhonePasswordActivity.  this.tencent_ticket = tencent_ticket;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(PhonePasswordActivity.this,1f);
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
                            ProgressDialogUtil.darkenBackground(PhonePasswordActivity.this,1f);
                            if(sendPhoneCode&&!slideWebViewPop.isManualDismiss()){//手机号注册, 发送验证码
                                //不是用户手动点击导致dismiss时才开启发送手机验证码的倒计时
                                String toString = get_phone_code_tv.getText().toString();
                                if(toString.equals(SEND_PHONE_CODE)){
                                    resSendPhoneCode(phoneEdit.getText().toString());
                                }
                            }
                        }
                    });
                }
            });
            slideWebViewPop.showAtLocation(get_phone_code_tv, Gravity.CENTER,0,0);
            ProgressDialogUtil.darkenBackground(this,0.5f);
        }else {
            if(sendPhoneCode){//手机号注册, 发送验证码
                //不是用户手动点击导致dismiss时才开启发送手机验证码的倒计时
                String toString = get_phone_code_tv.getText().toString();
                if(toString.equals(SEND_PHONE_CODE)){
                    resSendPhoneCode(phoneEdit.getText().toString());
                }
            }
        }

    }
    private void resSendPhoneCode(String phone) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone);
        data.put("type", 3);//0 注册 1 忘记密码/登录 2 修改手机号 绑定手机号
        data.put("tencent_randstr", tencent_randstr);
        data.put("tencent_ticket", tencent_ticket);
        HttpApiUtils.CpRequest(this,null, RequestUtil.SEND_PHONE_CODE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.验证码发送成功));
                countDownTime = 61;
                myHandler.post(countDownRunnable);
            }

            @Override
            public void onFailed(String msg) {
                tencent_randstr ="";
                tencent_ticket ="";
            }
        });
    }
    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            countDownTime--;
            if(countDownTime>=1){
                get_phone_code_tv.setText(countDownTime+Utils.getString(R.string.s后重发));
                myHandler.postDelayed(countDownRunnable,1000);
            }else {
                get_phone_code_tv.setText(SEND_PHONE_CODE);
                countDownTime=61;
                myHandler.removeCallbacks(countDownRunnable);
            }
        }
    };
    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }
}
