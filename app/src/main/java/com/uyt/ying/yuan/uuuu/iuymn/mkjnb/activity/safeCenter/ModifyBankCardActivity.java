package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
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
修改银行卡界面
 */
public class ModifyBankCardActivity extends BaseActivity implements View.OnClickListener {

private TextView userName;
private TextView cardNum;
private TextView address;
private TextView bankName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_bank_card);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.修改银行卡));
        initView();
        getBankCardInfo();
    }

    @Override
    protected void init() {

    }

    private void getBankCardInfo() {
        Map<String, Object> dataBackCardInfo = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        dataBackCardInfo.put("user_id",user_id);
        Utils.docking(dataBackCardInfo, RequestUtil.REQUEST_13r, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                String accountName = memberInfo.getString("accountName");//开户名
                String bankCard = memberInfo.getString("bankCard");//银行卡号
                String bankDot = memberInfo.getString("bankDot");//开户地址
                String name = memberInfo.getString("bankName");//银行名
                userName.setText(accountName);
                cardNum.setText(bankCard);
                address.setText(bankDot);
                bankName.setText(name);
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }
    public void initView() {

        userName =findViewById(R.id.modify_card_name);
        cardNum =findViewById(R.id.modify_card_num);
        address =findViewById(R.id.modify_address);
        bankName =findViewById(R.id.modify_bank_name);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_bar_return:
                finish();
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
