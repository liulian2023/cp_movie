package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.forget_password_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;


public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener {
    private TextView actionBarCenter;
    private TextView back;
    private EditText newPasswordEdit;
    private EditText surePasswordEdit;
    private Button sureButoon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        bindView();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        back=findViewById(R.id.action_bar_return);
        actionBarCenter=findViewById(R.id.action_bar_text);
        newPasswordEdit=findViewById(R.id.new_passwoed_edit);
        surePasswordEdit=findViewById(R.id.sure_new_passwoedt_edit);
        sureButoon=findViewById(R.id.sure_button);
        sureButoon.setOnClickListener(this);
        actionBarCenter.setText(Utils.getString(R.string.修改登录密码));
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
                break;
            case R.id.sure_button:
                if(StringMyUtil.isEmptyString(newPasswordEdit.getText().toString())){

                    showToast(Utils.getString(R.string.请输入新密码));
                }
                else if(StringMyUtil.isEmptyString(surePasswordEdit.getText().toString())){

                    showToast(Utils.getString(R.string.请再次输入提款密码));
                }
                else if(!newPasswordEdit.getText().toString().equals(surePasswordEdit.getText().toString())){
                    showToast(Utils.getString(R.string.两次输入的密码不一致,请重新输入));
                }
                else {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("user_id",getIntent().getLongExtra("user_id",0l));
                    data.put("type",6);//修改类型(0.旧密码修改登录密码 1修改支付密码 2修改头像3 绑定手机 4修改性别 5修改生日 6通过安全密码修改登录密码)
                    data.put("value",surePasswordEdit.getText().toString());
                    data.put("oldvalue",getIntent().getStringExtra("safePassword"));
                    Utils.docking(data, RequestUtil.REQUEST_10rzq, 0, new DockingUtil.ILoaderListener() {
                        @Override
                        public void success(String content) {
                            JSONObject jsonObject = JSONObject.parseObject(content);
                            String message = jsonObject.getString("message");
                            showToast(message);
                            LoginIntentUtil.toLogin(ModifyPasswordActivity.this,getIntent().getStringExtra("nickName"));
                    }

                        @Override
                        public void failed(MessageHead messageHead) {
                            showToast(messageHead.getInfo());
                        }
                    });
                }
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
