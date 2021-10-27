package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment;

import okhttp3.Headers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import java.util.HashMap;

public class SendRedActivity extends BaseActivity implements View.OnClickListener {

    private EditText redNumEtv;
    private EditText amountEtv;
    private TextView redTimeTv;
    private OptionsPickerView optionsPickerView;
    private Button sendSureBtn;
    private LinearLayout recharge_linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_red);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.发红包));

        bindView();
    }

    @Override
    protected void init() {

    }
    public static void  startAty (Context context,String chatRoomId){
        Intent intent = new Intent(context, SendRedActivity.class);
        intent.putExtra("chatRoomId",chatRoomId);
        context.startActivity(intent);
    }
    private void bindView() {
        redNumEtv=findViewById(R.id.red_num_etv);
        amountEtv=findViewById(R.id.red_amount_etv);
        redTimeTv=findViewById(R.id.red_time_tv);
        recharge_linear=findViewById(R.id.recharge_linear);
        sendSureBtn=findViewById(R.id.send_red_sure_btn);
        redTimeTv.setOnClickListener(this);
        sendSureBtn.setOnClickListener(this);
        recharge_linear.setOnClickListener(this);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_bar_return:
                finish();
                break;
                // 弹出时间选择
            case R.id.red_time_tv:
                break;
            case R.id.send_red_sure_btn:
                String redNum = redNumEtv.getText().toString();
                String redAmount = amountEtv.getText().toString();
                if(StringMyUtil.isEmptyString(redNum)){
                    showToast(Utils.getString(R.string.请输入红包个数));
                }else if(StringMyUtil.isEmptyString(redAmount)){
                    showToast(Utils.getString(R.string.请输入红包金额));
                }else {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("type", 0);//type  0 普通红包 1 炫耀红包
                    data.put("content", Utils.getString(R.string.普通红包));//红包备注
                    data.put("num", redNum);//红包个数
                    data.put("price", redAmount);//红包金额
                    HashMap<String, Object> userInfoMap = new HashMap<>();
                    userInfoMap.put("userId",SharePreferencesUtil.getLong(SendRedActivity.this,"user_id",0l));
                    userInfoMap.put("name",SharePreferencesUtil.getString(SendRedActivity.this,"userNickName",""));
                    userInfoMap.put("level",SharePreferencesUtil.getInt(SendRedActivity.this, CommonStr.GRADE,1));
                    userInfoMap.put("portrait",Utils.checkImageUrl(SharePreferencesUtil.getString(SendRedActivity.this,"image","")));
                    String s = JSONObject.toJSONString(userInfoMap);
                    data.put("userInfoJson", s);//用户信息
                    data.put("chatRoomId", getIntent().getStringExtra("chatRoomId"));//聊天室id
                    HttpApiUtils.CpRequest(SendRedActivity.this, null,RequestUtil.LIVE_SEND_RED, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            showToast(Utils.getString(R.string.发送成功));
                            //发送成功返回直播间
                            finish();
                        }

                        @Override
                        public void onFailed(String msg) {
                        }
                    });
                }

                break;
            case R.id.recharge_linear:
                String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                if(isTest.equals("-1")){
                    Utils.initSkipVisitorSafeCenterPop(SendRedActivity.this,SendRedActivity.this);
                }else {
                    startActivity(new Intent(SendRedActivity.this, RechargeActivity.class));
                }
                break;
                default:
                    break;
        }
    }
}
