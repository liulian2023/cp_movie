package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.forget_password_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RandomCode;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {
        private TextView actionBarCenter;
        private TextView back;
        private TextView code;
        private EditText accountEdit;
        private EditText codeEdit;
        private Button sureButoon;
//        private  String account;
//        private String yzCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        bindView();
        initView();
    }

    @Override
    protected void init() {

    }

    public void initView() {
        code.setText(RandomCode.code());
    }

    private void bindView() {
        back=findViewById(R.id.action_bar_return);
        actionBarCenter=findViewById(R.id.action_bar_text);
        code=findViewById(R.id.code);
        accountEdit=findViewById(R.id.account_edit);
        codeEdit=findViewById(R.id.code_edit);
        sureButoon=findViewById(R.id.sure_button);
        sureButoon.setOnClickListener(this);
        actionBarCenter.setText(Utils.getString(R.string.忘记密码));
        back.setOnClickListener(this);
        code.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
            case R.id.code:
               code.setText(RandomCode.code());
                break;
            case R.id.sure_button:
            if(StringMyUtil.isEmptyString(accountEdit.getText().toString())){
                showToast(Utils.getString(R.string.请输入账号));
            }
            else if(StringMyUtil.isEmptyString(codeEdit.getText().toString())){
                showToast(Utils.getString(R.string.请输入验证码));
            }
            else if(!code.getText().equals(codeEdit.getText().toString())){
                showToast(Utils.getString(R.string.验证码不正确请重新输入));
                code.setText(RandomCode.code());
            }
            else{
                HashMap<String, Object> data = new HashMap<>();
                data.put("nickname", accountEdit.getText().toString());
                Utils.docking(data, RequestUtil.REQUEST_07me, 0, new DockingUtil.ILoaderListener() {
                    @Override
                    public void success(String content) {
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        Long user_id = jsonObject.getLong("user_id");
                        Intent intent = new Intent(ForgetPasswordActivity.this, FindPasswordActicity.class);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("nickName",accountEdit.getText().toString());
                        startActivity(intent);
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
    protected void onRestart() {
        super.onRestart();
        code.setText(RandomCode.code());
        codeEdit.setText("");
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
