package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
第一次设置安全密码界面
 */
public class SetPayPasswordActivity extends BaseActivity implements View.OnClickListener {
        private Button sureButton;
        private EditText newPayPassword;
        private EditText SurePayPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pay_password);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.设置提款密码));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
        initView();
    }

    @Override
    protected void init() {

    }

    public void initView() {

        newPayPassword=findViewById(R.id.new_pay_password_edit);
        SurePayPassword=findViewById(R.id.sure_new_pay_password_edit);
        sureButton =findViewById(R.id.sure_button);
        sureButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_button:
                String loginPassword = SharePreferencesUtil.getString(SetPayPasswordActivity.this, "password", "");
                if(StringMyUtil.isEmptyString(newPayPassword.getText().toString())||StringMyUtil.isEmptyString(SurePayPassword.getText().toString())){
                    showToast(Utils.getString(R.string.新密码不能为空));
                }else if(loginPassword.equals(newPayPassword.getText().toString())){
                    showToast(Utils.getString(R.string.提款密码不能与登录密码相同));
                }
                else if(!newPayPassword.getText().toString().equals(SurePayPassword.getText().toString())){
                    showToast(Utils.getString(R.string.前后密码不一致,请重新输入));
                }
                else{
                    Map<String, Object> dataSetPayPassword = new HashMap<>();
                    Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
                    dataSetPayPassword.put("user_id",user_id);//user_id
                    dataSetPayPassword.put("type",1);//1表示修改安全密码
                    dataSetPayPassword.put("value",newPayPassword.getText().toString());//新密码
//                    dataSetPayPassword.put("oldvalue",null);//旧密码
                    Utils.docking(dataSetPayPassword, RequestUtil.REQUEST_10rzq, 0, new DockingUtil.ILoaderListener(){
                        @Override
                        public void success(String content) {
                            showToast(Utils.getString(R.string.设置密码成功));
                            SharePreferencesUtil.putString(SetPayPasswordActivity.this,"payPassword",newPayPassword.getText().toString());
                            finish();
                            startActivity(new Intent(SetPayPasswordActivity.this,SafeCenterActivity.class));
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
