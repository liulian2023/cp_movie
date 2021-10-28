package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.forget_password_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class FindPasswordActicity extends BaseActivity implements View.OnClickListener {
    private TextView actionBarCenter;
    private TextView back;
    private LinearLayout passSafePassword;
    private Long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password_acticity);
        userId=   getIntent().getLongExtra("user_id",1l);
        bindView();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        back=findViewById(R.id.action_bar_return);
        actionBarCenter=findViewById(R.id.action_bar_text);
        passSafePassword=findViewById(R.id.pass_safe_password);
        passSafePassword.setOnClickListener(this);
        back.setOnClickListener(this);
        actionBarCenter.setText(Utils.getString(R.string.找回密码));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
                break;
            case R.id.pass_safe_password:
                Intent intent = new Intent(FindPasswordActicity.this, SureSafePasswordActivity.class);
                intent.putExtra("user_id",userId);
                intent.putExtra("nickName",getIntent().getStringExtra("nickname"));
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
