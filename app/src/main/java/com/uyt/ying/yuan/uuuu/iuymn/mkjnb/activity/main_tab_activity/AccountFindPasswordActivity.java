package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class AccountFindPasswordActivity extends BaseActivity {
    @BindView(R.id.forget_account_etv)
    EditText forget_account_etv;
    @BindView(R.id.pay_password_etv)
    EditText pay_password_etv;
    @BindView(R.id.new_password_etv)
    EditText new_password_etv;
    @BindView(R.id.sure_new_password_etv)
    EditText sure_new_password_etv;
    @BindView(R.id.find_password_btn)
    Button find_password_btn;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbar_right_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_find_password);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.忘记密码));
        toolbar_right_tv.setText(Utils.getString(R.string.登录));
    }
    @OnClick({R.id.toolbar_right_tv,R.id.find_password_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toolbar_right_tv:
                LoginIntentUtil.toLogin(AccountFindPasswordActivity.this,false);
                break;
            case R.id.find_password_btn:
                if(checkEdit()){
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("nickname", forget_account_etv.getText().toString().trim());
                    data.put("paypassword", pay_password_etv.getText().toString().trim());
                    data.put("password", new_password_etv.getText().toString().trim());
                    HttpApiUtils.CpRequest(AccountFindPasswordActivity.this,null, RequestUtil.FORGET_PASSWORD2, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            showToast(Utils.getString(R.string.密码找回成功!));
                            finish();
                        }

                        @Override
                        public void onFailed(String msg) {

                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    private boolean checkEdit(){
        String  account = forget_account_etv.getText().toString();
        String payPassword = pay_password_etv.getText().toString();
        String newPassword = new_password_etv.getText().toString();
        String surePassword = sure_new_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(account)){
            showToast(Utils.getString(R.string.账号不能为空));
            return false;
        }else if(StringMyUtil.isEmptyString(payPassword)){
            showToast(Utils.getString(R.string.提款密码不能为空));
            return false;
        }else if(StringMyUtil.isEmptyString(newPassword)){
            showToast(Utils.getString(R.string.新密码不能为空));
            return false;
        }else if(StringMyUtil.isEmptyString(surePassword)){
            showToast(Utils.getString(R.string.确认密码不能为空));
            return false;
        }
        if (!Utils.checkAccount(account)){
            showToast(Utils.getString(R.string.请输入字母开头6-11位字母加数字的账号));
            return false;
        } else  if (!Utils.checkPsw(newPassword)){
            showToast(Utils.getString(R.string.请输入6-16位字母加数字的密码));
            return false;
        } else if(!newPassword.equals(surePassword)){
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