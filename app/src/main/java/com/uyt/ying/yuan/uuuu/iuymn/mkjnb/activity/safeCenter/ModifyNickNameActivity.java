package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import java.util.HashMap;

import okhttp3.Headers;

public class ModifyNickNameActivity extends BaseActivity implements View.OnClickListener {
        private EditText userNameEdit;
        private Button sureBtn;
        private String name = SharePreferencesUtil.getString(ModifyNickNameActivity.this, "nickname", "");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_nick_name);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.修改昵称));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        bindView();
        initPerson();
    }

    private void initPerson() {
        HttpApiUtils.CPnormalRequest(this, null, RequestUtil.REQUEST_13r, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                Integer userNickNameNums = memberInfo.getInteger("userNickNameNums");//昵称可修改次数
                Integer userNickNameUseNum = memberInfo.getInteger("userNickNameUseNum");//昵称已修改次数
                int count = userNickNameNums - userNickNameUseNum;
                 userNameEdit.setHint(Utils.getString(R.string.请输入您要修改的昵称(剩余修改次数)+count+Utils.getString(R.string.次)));
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        userNameEdit=findViewById(R.id.nick_name_edit);
        sureBtn=findViewById(R.id.sure_button);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.sure_button:
                final String nickname = userNameEdit.getText().toString();
                if(StringMyUtil.isEmptyString(nickname)){
                    showToast(Utils.getString(R.string.请输入您要修改的昵称));
                }/*else if(nickname.length()<3||nickname.length()>7){
                    showtoast(Utils.getString(R.string.昵称仅限3-10个汉字));
                }*/else if(nickname.equals(name)){
                    showToast(Utils.getString(R.string.修改后的昵称不能与账户名相同));
                }/*else if(nickname.contains(Utils.getString(R.string.萌新))){
                    showtoast(Utils.getString(R.string.昵称不能包含'萌新'));
                }*/
                else {
                    HashMap<String, Object> data = new HashMap<>();
                    Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
                    data.put("user_id",user_id);
                    data.put("type",7);
                    data.put("value",nickname.trim());
                    HttpApiUtils.CpRequest(ModifyNickNameActivity.this, null, RequestUtil.REQUEST_10rzq, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            JSONObject jsonObject = JSONObject.parseObject(result);
                            String message = jsonObject.getString("message");
                            showToast(message);
                            Intent intent = new Intent();
                            intent.putExtra("nickname",nickname);
                            setResult(RESULT_OK,intent);
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


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
