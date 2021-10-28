package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RegisterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.SlideWebViewPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LimitTextChange;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
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
注册页面
 */
public class RegisterActivity extends BaseActivity /*implements View.OnClickListener*/ {
    @BindView(R.id.register_wrap_constrainLayout)
    ConstraintLayout register_wrap_constrainLayout;
    @BindView(R.id.toolbar_back_iv)
    ImageView toolbar_back_iv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbar_title_tv;
    @BindView(R.id.invite_code_etv)
    EditText invite_code_etv;
    @BindView(R.id.register_account_etv)
    EditText register_account_etv;
    @BindView(R.id.register_password_etv)
    EditText register_password_etv;
    @BindView(R.id.register_sure_password_etv)
    EditText register_sure_password_etv;
    @BindView(R.id.register_next_btn)
    Button register_next_btn;
    @BindView(R.id.account_presence_tip_tv)
    TextView account_presence_tip_tv;
    @BindView(R.id.account_success_tip_iv)
    ImageView account_success_tip_iv;
    @BindView(R.id.invite_code_tip_tv)
    TextView invite_code_tip_tv;
    @BindView(R.id.kefu_tv)
    TextView kefu_tv;
    @BindView(R.id.phone_code_etv)
    EditText phone_code_etv;
    @BindView(R.id.get_phone_code_tv)
    TextView get_phone_code_tv;
    @BindView(R.id.phone_group)
    Group phone_group;
    @BindView(R.id.register_phone_etv)
    EditText register_phone_etv;
    @BindView(R.id.register_version_name_tv)
    TextView register_version_name_tv;
    private String yqm;
    private String ip;
    private String mobileNumSwitch;
    private String tencent_randstr;
    private String tencent_ticket;
    static String SEND_PHONE_CODE=Utils.getString(R.string.获取验证码);
    int countDownTime = 61;
    MyHandler myHandler;
    private String isOpenPayPass;
    private SlideWebViewPop.Poptype poptype;
    private String appSlidingCheck = SharePreferencesUtil.getString(MyApplication.getInstance(),"appSlidingCheck","");
    private String TAG="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.注册));
        isOpenPayPass= SharePreferencesUtil.getString(RegisterActivity.this,"isOpenPayPass","");
        myHandler = new MyHandler();
        showView();
        LimitTextChange.StringWatcher(register_password_etv);
        LimitTextChange.StringWatcher(register_sure_password_etv);
        LimitTextChange.StringWatcher(invite_code_etv);
        initInvite();
    }

    /**
     * 用户进入app时携带了下载页的邀请码时, 将携带的邀请码写死在view上, 不可修改
     */
    private void initInvite() {
        String inviteCode = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.INVITE_CODE,"");

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
                        invite_code_etv.setText(inviteCode);
                        invite_code_etv.setEnabled(false);
                    }
                }

                @Override
                public void onFailed(String msg) {

                }
            });
        }else {
            invite_code_etv.setText(inviteCode);
            invite_code_etv.setEnabled(false);
        }
    }



    private void showView() {
        mobileNumSwitch =SharePreferencesUtil.getString(this,"mobileNumSwitch","0");
        yqm = SharePreferencesUtil.getString(this,"yqm","");
        if(yqm.equals("0")){
            invite_code_etv.setHint(Utils.getString(R.string.请输入邀请码必填));
        }else {
            invite_code_etv.setHint(Utils.getString(R.string.请输入邀请码选填));
        }
        if(mobileNumSwitch.equals("0")){
            //手机号注册样式
            register_account_etv.setVisibility(View.INVISIBLE);
        }else {
            //用户名注册样式
            register_phone_etv.setVisibility(View.INVISIBLE);
            phone_group.setVisibility(View.GONE);
        }
        if(BuildConfig.DEBUG){
            register_version_name_tv.setText(String.format(Utils.getString(R.string.测试版版本号),BuildConfig.VERSION_NAME));
        }else {
            register_version_name_tv.setText(String.format(Utils.getString(R.string.版本号占位),BuildConfig.VERSION_NAME));
        }
    }
    /**
     * 弹出滑动pop
     * @param sendPhoneCode dismiss后是否需要开启发送验证码的倒计时计时器
     */
    private void initSlideWebViewPop(boolean sendPhoneCode) {
        if(appSlidingCheck.equals("0")){
            //每次弹出滑动验证pop都将滑动验证的数据置空
            tencent_ticket ="";
            tencent_randstr ="";
            if(sendPhoneCode){
                poptype = SlideWebViewPop.Poptype.SMS;
            }else {
                poptype = SlideWebViewPop.Poptype.REGISTER;
            }
            Utils.hideSoftKeyBoard(this);
            SlideWebViewPop slideWebViewPop = new SlideWebViewPop(this, true, poptype);
            slideWebViewPop.setSlideInterface(new SlideWebViewPop.SlideInterface() {
                @Override
                public void onSlideListener(String tencent_randstr, String tencent_ticket) {
                    RegisterActivity.this.tencent_ticket = tencent_ticket;
                    RegisterActivity.this.tencent_randstr = tencent_randstr;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressDialogUtil.darkenBackground(RegisterActivity.this,1f);
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
                            ProgressDialogUtil.darkenBackground(RegisterActivity.this,1f);
                            if(sendPhoneCode&&!slideWebViewPop.isManualDismiss()){//手机号注册, 发送验证码
                                //不是用户手动点击导致dismiss时才开启发送手机验证码的倒计时
                                String toString = get_phone_code_tv.getText().toString();
                                if(toString.equals(SEND_PHONE_CODE)){
                                    resSendPhoneCode(register_phone_etv.getText().toString());
                                }
                            }else {
                                if(!slideWebViewPop.isManualDismiss()){
                                    //账号注册时,dismis后自动进行注册操作
//                                    register_next_btn.performClick();
                                    if(checkEtv()){
                                        requestRegister();
                                    }
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
                //开启发送手机验证码的倒计时
                String toString = get_phone_code_tv.getText().toString();
                if(toString.equals(SEND_PHONE_CODE)){
                    resSendPhoneCode(register_phone_etv.getText().toString());
                }
            }else {
               // 进行注册操作
                    register_next_btn.performClick();
            }
        }
    }

    private void resSendPhoneCode(String phone) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("phone", phone.trim());
        data.put("type", 0);
        data.put("tencent_ticket", tencent_ticket);
        data.put("tencent_randstr", tencent_randstr);
        HttpApiUtils.CpRequest(this, null,RequestUtil.SEND_PHONE_CODE, data, new HttpApiUtils.OnRequestLintener() {
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

    @OnClick({R.id.register_next_btn,R.id.kefu_tv,R.id.get_phone_code_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.get_phone_code_tv:
                if(StringMyUtil.isEmptyString(register_phone_etv.getText().toString())){
                    showToast(Utils.getString(R.string.请先输入手机号));
                }else {
                    if(!get_phone_code_tv.getText().toString().equals(SEND_PHONE_CODE)){
                        return;
                    }
                    //输入手机号后点击获取验证码, 先验证是否可以注册,防止恶意刷滑动
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("phone",register_phone_etv.getText().toString().trim());
                    data.put("refername",invite_code_etv.getText().toString().trim());
                    HttpApiUtils.CpRequest(RegisterActivity.this, null,RequestUtil.CHECK_REGISTER, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            Utils.logE(TAG,"onSuccess:  注册前校验成功 :");
                            initSlideWebViewPop(true);
                        }

                        @Override
                        public void onFailed(String msg) {
                            Utils.logE(TAG,"onFailed:  注册前校验失败 :");
                        }
                    });

                }
                break;
            case R.id.register_next_btn:
                if(Utils.isNotFastClick()){
                    if(mobileNumSwitch.equals("0")){//手机号注册时, 检验输入框数据后请求注册接口
                        if (checkEtv()) {
                            requestRegister();
                        }
                    }else{
                        //使用账号注册时,先判断账号是否合法, 合法时判断是否开启了滑动, 开启了滑动时弹出滑动pop.在dismiss时直接调用注册接口
                        checkAccount();
/*                    if(StringMyUtil.isEmptyString(ali_sessionId)&&appSlidingCheck.equals("0")){
                        initSlideWebViewPop(false);
                    }else {
                        if(checkEtv()){
                            requestRegister();
                        }
                    }*/
                    }
                }
                break;
            case R.id.kefu_tv:
                Intent intentKeFu = new Intent(this, OnLineKeFuActivity.class);
                intentKeFu.putExtra("toKeFu",true);
                startActivity(intentKeFu);
                break;
            default:
                break;
        }
    }

    private void checkAccount() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("inviteCode",invite_code_etv.getText().toString().trim());
        data.put("nickname",register_account_etv.getText().toString().trim());
        HttpApiUtils.CpRequest(RegisterActivity.this,null, RequestUtil.CHECK_ACCOUNT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String message1 = jsonObject.getString("message1");
                String message2 = jsonObject.getString("message2");
                if(StringMyUtil.isEmptyString(message1)&&StringMyUtil.isEmptyString(message2)){
                    if(/*StringMyUtil.isEmptyString(ali_sessionId)&&*/appSlidingCheck.equals("0")){
                        initSlideWebViewPop(false);
                    }else {
                        if(checkEtv()){
                            requestRegister();
                        }
                    }
                }else {
                    if(StringMyUtil.isNotEmpty(message1)){
                        showToast(message1);
                    }else if(StringMyUtil.isNotEmpty(message2)){
                        showToast(message2);
                    }
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    class MyHandler extends Handler {}

    private  void requestRegister() {
        register_next_btn.setClickable(false);
        HashMap<String, Object> data = new HashMap<>();
        data.put("refername", invite_code_etv.getText().toString());
        data.put("password", register_password_etv.getText().toString());
        String phone = register_phone_etv.getText().toString();
        if(mobileNumSwitch.equals("0")){
            data.put("phone", phone.trim());
            // 测试用,万能验证码
//             data.put("phoneSMSCode","888888");
            data.put("phoneSMSCode",phone_code_etv.getText().toString().trim());
        }else {//账号登录
            data.put("nickname", register_account_etv.getText().toString().trim());
            data.put("tencent_randstr", tencent_randstr);
            data.put("tencent_ticket", tencent_ticket);
        }
        HttpApiUtils.CpRequest(this, null,RequestUtil.REQUEST_06tb, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.注册成功正在登录));
                RegisterEntity registerEntity = JSONObject.parseObject(result, RegisterEntity.class);
                RegisterEntity.MemberInfoBean memberInfo = registerEntity.getMemberInfo();
                String message = registerEntity.getMessage();
                String phone = memberInfo.getPhone();
                String password = memberInfo.getPassword();
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.USER_PASSWORD,StringMyUtil.isEmptyString(password)?"":password);
                //注册成功,请求融云
                RequestLoginUtils.requestSuccess(RegisterActivity.this, result, (String) data.get("nickName"),(String)data.get("password"), register_next_btn);
            }

            @Override
            public void onFailed(String msg) {
                tencent_ticket ="";
                tencent_randstr ="";
                register_next_btn.setClickable(true);

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
    @OnTextChanged(value = R.id.register_account_etv,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChange(Editable editable){
        //限制中文输入
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);

                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
        if(account_presence_tip_tv.getVisibility()!=View.GONE){
            account_presence_tip_tv.setVisibility(View.GONE);
        }
        if(account_success_tip_iv.getVisibility()!=View.GONE){
            account_success_tip_iv.setVisibility(View.GONE);
        }
    }
    private boolean checkEtv() {
        String accountStr = register_account_etv.getText().toString();
        if(yqm.equals("0")){
            String inviteStr = invite_code_etv.getText().toString();
            if(StringMyUtil.isEmptyString(inviteStr)){
                showToast(Utils.getString(R.string.邀请码不能为空));
                return false;
            }
            if(inviteStr.length()<6||inviteStr.length()>8){
                showToast(Utils.getString(R.string.邀请码应为6到8位));
                return false;
            }
        }
        if(mobileNumSwitch.equals("0")){
            String phoneNum = register_phone_etv.getText().toString();
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
        }else {
            int accountLength = accountStr.length();
            if(/*accountLength<6||accountLength>11||*/!Utils.checkAccount(accountStr)){
                showToast(Utils.getString(R.string.请输入字母开头6到11位字母加数字的账号));
                return false;
            }
        }

        String passwordStr = register_password_etv.getText().toString();
        int passwordLength = passwordStr.length();
        if(/*passwordLength<6||passwordLength>16||*/!Utils.checkPsw(passwordStr)){
            showToast(Utils.getString(R.string.请输入6到16位字母加数字的密码));
            return false;
        }
        if(accountStr.equals(passwordStr)){
            showToast(Utils.getString(R.string.账号和密码不能一样));
            return  false;
        }
        String surePasswordStr = register_sure_password_etv.getText().toString();
        if(!passwordStr.equals(surePasswordStr)){
            showToast(Utils.getString(R.string.两次输入的密码不一致));
            return false;
        }
        return true;

    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}