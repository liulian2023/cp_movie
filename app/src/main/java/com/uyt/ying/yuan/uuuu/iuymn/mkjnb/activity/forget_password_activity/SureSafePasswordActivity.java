package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.forget_password_activity;

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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

public class SureSafePasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText passWordEdit;
    private Button sureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_safe_password);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.验证提款密码));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setLightMode(this,true);
        bindView();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        sureButton=findViewById(R.id.sure_button);
        passWordEdit=findViewById(R.id.pay_password_edit);
        sureButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_button:
                if(StringMyUtil.isEmptyString(passWordEdit.getText().toString())){
                    showToast(getString(R.string.请输入提款密码));
                }else {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("user_id",getIntent().getLongExtra("user_id",1l));
                    data.put("paypassword",passWordEdit.getText().toString());
                    Utils.docking(data, RequestUtil.REQUEST_08me, 0, new DockingUtil.ILoaderListener() {
                        @Override
                        public void success(String content) {
                            Intent intent = new Intent(SureSafePasswordActivity.this, ModifyPasswordActivity.class);
                            intent.putExtra("user_id",getIntent().getLongExtra("user_id",0l));
                            intent.putExtra("safePassword",passWordEdit.getText().toString());
                            intent.putExtra("nickName",getIntent().getStringExtra("nickName"));
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
    public void onNetChange(boolean netWorkState) {

    }
}
