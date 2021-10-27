package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;
import java.util.Map;
/*
修改登录密码界面
 */
public class ModifyLoginPasswordActivity extends BaseActivity implements View.OnClickListener {
private EditText oldPassword;
private EditText newPassword;
private EditText sureNewPassword;
private Button modifyPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_login_password);
        initView();
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.修改登录密码));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
    }

    @Override
    protected void init() {

    }

    public void initView() {
        oldPassword =findViewById(R.id.old_password_edit);
        newPassword =findViewById(R.id.new_passwoed_edit);
        sureNewPassword =findViewById(R.id.sure_new_passwoedt_edit);
        modifyPassword=findViewById(R.id.sure_button);
        modifyPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*
            修改登录密码相关
             */
            case R.id.sure_button:
                if(StringMyUtil.isEmptyString(oldPassword.getText().toString())){//判断原密码是否输入正确
                    showToast(Utils.getString(R.string.请输入原密码));
                }
                else {
                    String string = newPassword.getText().toString();
                    if(!string.equals(sureNewPassword.getText().toString())||newPassword.getText().toString()==null||newPassword.getText().toString().equals("")||sureNewPassword.getText().toString()==null||sureNewPassword.getText().toString().equals("")){
                       //判断两次输入的新密码是否一致
                        showToast(Utils.getString(R.string.两次密码不一致));
                    }
                    else{//请求接口. 修改密码
                        Map<String,Object> modifyPassword =new HashMap<>();
                        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
                        modifyPassword.put("user_id",user_id);//user_id
                        modifyPassword.put("type",0);//0表示通过旧密码修改登录密码
                        modifyPassword.put("value",newPassword.getText().toString());//新密码
                        modifyPassword.put("oldvalue",oldPassword.getText().toString());//旧密码

                        Utils.docking(modifyPassword, RequestUtil.REQUEST_10rzq, 0, new DockingUtil.ILoaderListener(){
                            @Override
                            public void success(String content) {
                                showToast(Utils.getString(R.string.修改密码成功));
                                SharePreferencesUtil.putString(ModifyLoginPasswordActivity.this,"password",newPassword.getText().toString());
                                finish();
                            }
                            @Override
                            public void failed(MessageHead messageHead) {
                                showToast(messageHead.getInfo());
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
