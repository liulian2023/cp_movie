package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/*
修改安全密码界面
 */
public class ModifyPayPassword extends BaseActivity implements View.OnClickListener {
    private EditText oldPayPassword;
    private EditText newPayPassword;
    private EditText sureNewPayPassword;
    private Button sureButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pay_password);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.修改提款密码));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
        initView();
    }

    @Override
    protected void init() {

    }

    public void initView() {
        oldPayPassword=findViewById(R.id.now_pay_password_edit);
        newPayPassword=findViewById(R.id.new_pay_password_edit);
        sureNewPayPassword=findViewById(R.id.sure_new_pay_password_edit);
        sureButton=findViewById(R.id.sure_button);
        sureButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_button:
                String oldPayPass = oldPayPassword.getText().toString();
                final String newPayPass = newPayPassword.getText().toString();
                String sureNewPayPass = sureNewPayPassword.getText().toString();
                String payPassword = SharePreferencesUtil.getString(this, "payPassword", "");
                if(StringMyUtil.isEmptyString(oldPayPass)){
                    showToast(Utils.getString(R.string.原密码不能为空));
                }
                else if(StringMyUtil.isEmptyString(newPayPass)||StringMyUtil.isEmptyString(sureNewPayPass)){
                    showToast(Utils.getString(R.string.新密码不能为空));
                }else if(!newPayPass.equals(sureNewPayPass)){
                    showToast(Utils.getString(R.string.前后输入的新密码不一致));
                }
                else if(newPayPass.equals(payPassword)){
                    showToast(Utils.getString(R.string.新密码不能与旧密码相同));
                }
                else{
                    Map<String,Object> modifyPayPassword =new HashMap<>();
                    Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
                    modifyPayPassword.put("user_id",user_id);//user_id
                    modifyPayPassword.put("type",1);//1表示修改安全密码
                    modifyPayPassword.put("value",newPayPass);//新密码
                    modifyPayPassword.put("oldvalue",oldPayPass);//旧密码
                    Utils.docking(modifyPayPassword, RequestUtil.REQUEST_10rzq, 0, new DockingUtil.ILoaderListener(){
                        @Override
                        public void success(String content) {
                            JSONObject jsonObject = JSONObject.parseObject(content);
                            String message = jsonObject.getString("message");
                            showToast(message);
                            SharePreferencesUtil.putString(ModifyPayPassword.this,"payPassword",newPayPass);
                            String payPassword1 = SharePreferencesUtil.getString(ModifyPayPassword.this, "payPassword", "");
                            finish();
                            Intent intent = new Intent(ModifyPayPassword.this, SafeCenterActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                        @Override
                        public void failed(MessageHead messageHead) {
                            showToast(messageHead.getInfo());
                        }
                    });
                }
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
