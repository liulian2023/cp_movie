package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.Headers;

public class BindUSDTActivity extends BaseActivity {
    @BindView(R.id.ustd_username_tv)
    TextView ustd_username_tv;
    @BindView(R.id.ustd_address_etv)
    EditText ustd_address_etv;
    @BindView(R.id.ustd_psd_etv)
    EditText ustd_psd_etv;
    @BindView(R.id.ustd_commit_btn)
    Button ustd_commit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_u_s_d_t);
        ButterKnife.bind(this);
        StatusBarUtil.setDarkMode(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.添加USDT));
        UserInfoEntity userInfoEntity = Utils.getUserInfoEntity();
        if(userInfoEntity!=null){
            String accountName = userInfoEntity.getMemberInfo().getAccountName();
            ustd_username_tv.setText(accountName);
        }
    }

    @Override
    protected void init() {

    }
    @OnClick({R.id.ustd_commit_btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ustd_commit_btn:
                if(checkEtv()&& Utils.isNotFastClick()){
                    requestBindUSDT();
                }
                break;
            default:
                break;
        }
    }
    @OnTextChanged(value=R.id.ustd_address_etv, callback=OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChanged(Editable s) {
        //限制中文输入
        if (s.length() > 0) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    s.delete(i,i+1);
                }
            }
        }
    }
    private void requestBindUSDT() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("usdtAccount",ustd_address_etv.getText().toString());
        data.put("payPassword",ustd_psd_etv.getText().toString());
        HttpApiUtils.CpRequest(this, null, RequestUtil.BIND_USDT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.添加成功));
                USDTManageActivity.startAty(BindUSDTActivity.this,false,true);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private boolean checkEtv() {
        if(StringMyUtil.isEmptyString(ustd_address_etv.getText().toString())){
            showToast(Utils.getString(R.string.请输入您的USDT-ERC20收款地址));
            return false;
        }
        if(StringMyUtil.isEmptyString(ustd_psd_etv.getText().toString())){
            showToast(Utils.getString(R.string.请输入您的提款密码));
            return false;
        }
        return true;
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}