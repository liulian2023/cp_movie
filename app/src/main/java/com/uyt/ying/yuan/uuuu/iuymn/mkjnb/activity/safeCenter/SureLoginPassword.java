package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import java.util.HashMap;

import okhttp3.Headers;
/*
修改安全密码时的验证登录密码界面
 */

public class SureLoginPassword extends BaseActivity implements View.OnClickListener {
    private Button sureBtn;
    private EditText nowPasswordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_login_password);
        initView();
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.登录密码验证));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
    }

    @Override
    protected void init() {

    }

    public void initView() {
        sureBtn =findViewById(R.id.sure_button);
        nowPasswordEdit=findViewById(R.id.now_password_edit);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_button:
                if(Utils.isNotFastClick()){
                    String nowPassword = nowPasswordEdit.getText().toString();
                    if(StringMyUtil.isEmptyString(nowPassword)){//判断输入原登录密码是否正确
                        showToast(Utils.getString(R.string.请输入当前使用的登录密码));
                    }else {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put("password",nowPassword);
                        HttpApiUtils.CpRequest(SureLoginPassword.this, null, RequestUtil.REQUEST_08me, data, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result, Headers headers) {
                                //上个activity传入的boolean值 ,判断是否有设置安全密码
                                Intent intent = getIntent();
                                boolean havePayPassword = intent.getBooleanExtra("havePayPassword", false);
                                if(havePayPassword){//设置了安全密码,则跳转修改安全密码界面
                                    finish();
                                    startActivity(new Intent(SureLoginPassword.this,ModifyPayPassword.class));

                                }else{//没有设置安全密码,则跳转设置密码界面
                                    finish();
                                    startActivity(new Intent(SureLoginPassword.this,SetPayPasswordActivity.class));
                                }
                            }

                            @Override
                            public void onFailed(String msg) {

                            }
                        });

                    }
                }


            break;
            default:
                break;
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
