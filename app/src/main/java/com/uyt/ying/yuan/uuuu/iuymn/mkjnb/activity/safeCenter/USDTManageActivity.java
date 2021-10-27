package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import androidx.constraintlayout.widget.Group;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class USDTManageActivity extends BaseActivity {
    @BindView(R.id.no_bind_tv)
    TextView no_bind_tv;
    @BindView(R.id.add_button)
    Button add_button;
    @BindView(R.id.usdt_address_tv)
    TextView usdt_address_tv;
    @BindView(R.id.usdt_card_group)
    Group usdt_card_group;
    @BindView(R.id.usdt_tip_tv)
    TextView usdt_tip_tv;
    boolean isEmpty ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_s_d_t_manage);
        ButterKnife.bind(this);
        StatusBarUtil.setDarkMode(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.USDT账户管理));
        getIntentData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isEmpty){
            usdt_card_group.setVisibility(View.GONE);
            no_bind_tv.setVisibility(View.VISIBLE);
            add_button.setVisibility(View.VISIBLE);
            usdt_tip_tv.setText(Utils.getString(R.string.温馨提示:\n1.绑定地址必须为USDT-ERC20地址;\n2.请务必再三确认连接名称为：ERC20地址;\n3.如绑定地址遇到未知问题请及时联系客服。));
        }else {
            usdt_card_group.setVisibility(View.VISIBLE);
            no_bind_tv.setVisibility(View.GONE);
            add_button.setVisibility(View.GONE);
            usdt_tip_tv.setText(Utils.getString(R.string.温馨提示：\n如需修改USDT地址请联系客服。));
            requestUserInfo();
            Utils.RequestUsingEquipment(this,null);
        }

    }

    private void requestUserInfo() {
        HttpApiUtils.CpRequest(this, null, RequestUtil.REQUEST_13r, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(MyApplication.getInstance(),"userInfo",result);//储存用户信息
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                UserInfoEntity.MemberInfoBean memberInfo = userInfoEntity.getMemberInfo();
                String usdtAccount = memberInfo.getUsdtAccount();
                if(StringMyUtil.isNotEmpty(usdtAccount)){
                    usdt_address_tv.setText(usdtAccount);
                }else {
                    usdt_card_group.setVisibility(View.GONE);
                    no_bind_tv.setVisibility(View.VISIBLE);
                    add_button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private void getIntentData() {
        isEmpty = getIntent().getBooleanExtra("isEmpty",false);
    }
    @OnClick({R.id.add_button,R.id.usdt_tip_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_button:
            case R.id.usdt_tip_tv:
                // 跳转添加USDT账号
                startActivity(new Intent(USDTManageActivity.this,BindUSDTActivity.class));
                break;

            default:
                break;
        }
    }
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }



    public static void  startAty(Context context, boolean isEmpty,boolean killSelf){
            Intent intent = new Intent(context, USDTManageActivity.class);
            intent.putExtra("isEmpty",isEmpty);
            if(killSelf){
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            context.startActivity(intent);
    }
}