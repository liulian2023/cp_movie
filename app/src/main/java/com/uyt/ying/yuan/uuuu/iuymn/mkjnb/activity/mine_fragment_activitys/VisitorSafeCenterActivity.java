package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
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

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class VisitorSafeCenterActivity extends BaseActivity {
    @BindView(R.id.phone_etv)
    EditText phone_etv;
    @BindView(R.id.login_password_etv)
    EditText login_password_etv;
    @BindView(R.id.pay_password_etv)
    EditText pay_password_etv;
    @BindView(R.id.phone_code_etv)
    EditText phone_code_etv;
    @BindView(R.id.visitor_sure_btn)
    Button visitor_sure_btn;
    @BindView(R.id.get_phone_code_tv)
    TextView get_phone_code_tv;
    MyHandler myHandler;
    static String SEND_PHONE_CODE=Utils.getString(R.string.获取验证码);
    private String tencent_randstr;
    private String tencent_ticket;
    private int countDownTime = 61;
    private String appSlidingCheck = SharePreferencesUtil.getString(MyApplication.getInstance(),"appSlidingCheck","");
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_safe_center);
        myHandler= new MyHandler();
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");
        /**
         * 一键登录的用户进入页面时.将登录时的手机号写死再输入框, 不允许修改
         */
        if(StringMyUtil.isNotEmpty(phone)){
            phone_etv.setText(phone);
            phone_etv.setEnabled(false);
        }
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.安全中心));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        LimitTextChange.StringWatcher(login_password_etv);
        LimitTextChange.StringWatcher(pay_password_etv);
        LimitTextChange.StringWatcher(phone_code_etv);

    }
    @OnClick({R.id.get_phone_code_tv,R.id.visitor_sure_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.visitor_sure_btn:
                if(Utils.isNotFastClick()){
                    if(checkRtv()){
                        HashMap<String, Object> data = new HashMap<>();
                        data.put("password",login_password_etv.getText().toString());
                        data.put("paypassword",pay_password_etv.getText().toString());
                        data.put("phone",phone_etv.getText().toString());
                        data.put("phoneSMSCode",phone_code_etv.getText().toString());
                        HttpApiUtils.CpRequest(VisitorSafeCenterActivity.this, null, RequestUtil.VISITOR_BIND, data, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                SharePreferencesUtil.putString(MyApplication.getInstance(), CommonStr.USER_PASSWORD,login_password_etv.getText().toString());
                                SharePreferencesUtil.putString(MyApplication.getInstance(), "password",login_password_etv.getText().toString());
                                showToast(Utils.getString(R.string.认证成功));
                                finish();

                            }

                            @Override
                            public void onFailed(String msg) {

                            }
                        });
                    }
                }
                break;
            case R.id.get_phone_code_tv:
                if(Utils.isNotFastClick()){
                    if(StringMyUtil.isEmptyString(phone_etv.getText().toString())){
                        showToast(Utils.getString(R.string.请先输入手机号));
                    }else {
                        if(!get_phone_code_tv.getText().toString().equals(SEND_PHONE_CODE)){
                            return;
                        }
                        initSlideWebViewPop();
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean checkRtv() {
        String phoneNum = phone_etv.getText().toString();
        if(StringMyUtil.isEmptyString(phoneNum)){
            showToast(Utils.getString(R.string.请输入手机号));
            return  false;
        }else if(phoneNum.length()!=11){
            showToast(Utils.getString(R.string.请输入正确的手机号));
            return  false;
        }
        String phoneCode = phone_code_etv.getText().toString();
        if(StringMyUtil.isEmptyString(phoneCode)){
            showToast(Utils.getString(R.string.请输入手机验证码));
            return false;
        }
        String passwordStr = login_password_etv.getText().toString();
        if(!Utils.checkPsw(passwordStr)){
            showToast(Utils.getString(R.string.请输入6到16位字母加数字的密码));
            return false;
        }
        String payPassword = pay_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(payPassword)){
            showToast(Utils.getString(R.string.请输入提款密码));
            return false;
        }
        return true;
    }

    class MyHandler extends Handler {}


    private void resSendPhoneCode(String phone) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone.trim());
        if(StringMyUtil.isNotEmpty(VisitorSafeCenterActivity.this.phone)){
            data.put("type", 4);
        }else {
            data.put("type", 3);
        }
        data.put("tencent_ticket", tencent_ticket);
        data.put("tencent_randstr", tencent_randstr);
        HttpApiUtils.CpRequest(this, null, RequestUtil.SEND_PHONE_CODE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.验证码发送成功));
                countDownTime = 61;
                myHandler.post(countDownRunnable);
            }

            @Override
            public void onFailed(String msg) {
                tencent_ticket ="";
                tencent_randstr ="";
            }
        });
    }

    /**
     * 弹出滑动pop
     *
     */
    private void initSlideWebViewPop() {
        if(appSlidingCheck.equals("0")){
            //每次弹出滑动验证pop都将滑动验证的数据置空
            tencent_ticket ="";
            tencent_randstr ="";
            Utils.hideSoftKeyBoard(this);
            SlideWebViewPop slideWebViewPop = new SlideWebViewPop(this, true, SlideWebViewPop.Poptype.SMS);
            slideWebViewPop.setSlideInterface(new SlideWebViewPop.SlideInterface() {
                @Override
                public void onSlideListener(String tencent_randstr, String tencent_ticket) {
                    VisitorSafeCenterActivity.this. tencent_ticket = tencent_ticket;
                    VisitorSafeCenterActivity.this. tencent_randstr = tencent_randstr;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(VisitorSafeCenterActivity.this,1f);
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
                            ProgressDialogUtil.darkenBackground(VisitorSafeCenterActivity.this,1f);
                            if(!slideWebViewPop.isManualDismiss()){//手机号注册, 发送验证码
                                //不是用户手动点击导致dismiss时才开启发送手机验证码的倒计时
                                String toString = get_phone_code_tv.getText().toString();
                                if(toString.equals(SEND_PHONE_CODE)){
                                    resSendPhoneCode(phone_etv.getText().toString());
                                }
                            }
                        }
                    });
                }
            });
            slideWebViewPop.showAtLocation(get_phone_code_tv, Gravity.CENTER,0,0);
            ProgressDialogUtil.darkenBackground(this,0.5f);
        }else {
            resSendPhoneCode(phone_etv.getText().toString());
        }


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
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}