
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.yueBao_activity;

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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

/*
转出余额宝
 */
public class OutYueBaoActivity extends BaseActivity implements View.OnClickListener {
    private TextView actionBarTv;
    //全部textView(点击填充editText)
    private TextView allAmountTv;
    private long user_id;
    //用户余额
    private String userAmount;
    private EditText amountEt;
    private TextView actionBack;
    private Button zhuanChuVtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_yue_bao);
        user_id= SharePreferencesUtil.getLong(this,"user_id",0l);
        bindView();
        getUserAmount();
    }

    private void getUserAmount() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.YUEBAO_INFO, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONObject yueBaoVo = jsonObject1.getJSONObject("yueBaoVo");
                //余额宝余额
                String amount = yueBaoVo.getString("amount");
                userAmount=amount;
                amountEt.setText("");
                amountEt.setHint(Utils.getString(R.string.可转出金额0.00-)+userAmount);

            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        zhuanChuVtn=findViewById(R.id.zhuanchu_btn);
        zhuanChuVtn.setOnClickListener(this);
        actionBack=findViewById(R.id.action_bar_return);
        actionBack.setOnClickListener(this);
        amountEt=findViewById(R.id.amount_edit);
        allAmountTv=findViewById(R.id.all_amount);
        allAmountTv.setOnClickListener(this);
        actionBarTv=findViewById(R.id.action_bar_text);
        actionBarTv.setText(Utils.getString(R.string.余额宝转出));
    }
        private void zhuanChu(){
            ProgressDialogUtil.show(OutYueBaoActivity.this);
            zhuanChuVtn.setClickable(false);
            HashMap<String, Object> data = new HashMap<>();
            data.put("user_id",user_id);
            data.put("amount",amountEt.getText().toString());
            Utils.docking(data, RequestUtil.OUT_YUEBAO, 0, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content)  {
                    ProgressDialogUtil.stop(OutYueBaoActivity.this);
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    showToast(jsonObject.getString("message"));
                    zhuanChuVtn.setClickable(true);
//                    getUserAmount();
                    finish();
                }
                @Override
                public void failed(MessageHead messageHead) {
                    ProgressDialogUtil.stop(OutYueBaoActivity.this);
                    showToast(messageHead.getInfo());
                    zhuanChuVtn.setClickable(true);
                }
            });
        }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all_amount:
                if(StringMyUtil.isNotEmpty(userAmount)){
                    amountEt.setText(userAmount);
                    amountEt.setSelection(amountEt.getText().length());
                }
                break;
            case R.id.action_bar_return:
                finish();
                break;
            //点击 转出按钮
            case R.id.zhuanchu_btn:
                zhuanChu();
                break;
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
