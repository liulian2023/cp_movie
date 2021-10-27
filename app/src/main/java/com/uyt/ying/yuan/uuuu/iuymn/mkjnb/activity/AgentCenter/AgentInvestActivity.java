package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.math.BigDecimal;
import java.util.HashMap;

public class AgentInvestActivity extends BaseActivity implements View.OnClickListener {
        private EditText userAccount;//会员账号输入框
        private EditText anvestNumEdit;//充值金额输入框
        private EditText passWordEdit;//安全密码输入框
        private TextView commissionNum;//佣金余额
        private Button sureBtn;//确定按钮
        private Button resetBtn;//重置按钮
        private TextView actionCenter;//actionBar中间的textView
        private TextView actionRigeht;//actionBar右边的textView
        private ImageView userNameImg;//actionBar左边的textView
        private TextView userNametext;//会员账号输入款下隐藏的用户真实姓名textView
        private Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        private  String userName;//会员账号输入框内容
        private String acvesNum;//充值金额输入框内容
        private String passWord;//安全密码输入框内容
        private TextView back; //返回键
        boolean sureBtn_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_anvest);
        bindView();
        initView();
    }

    @Override
    protected void init() {

    }

    public void initView() {
        //获取佣金余额(进入activity显示)
        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id",user_id);
        Utils.docking(data, RequestUtil.REQUEST_06rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(AgentInvestActivity.this);
                JSONObject jsonObject1 = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject1.getJSONObject("memberMoney");
                BigDecimal commission = memberMoney.getBigDecimal("commission");
                commissionNum.setText(commission+"");
            }

            @Override

            public void failed(MessageHead messageHead) {
                ProgressDialogUtil.stop(AgentInvestActivity.this);
            }
        });
    }

    private void bindView() {
        userAccount=findViewById(R.id.user_num);
        anvestNumEdit=findViewById(R.id.anvest_num);
        passWordEdit=findViewById(R.id.password_edit);
        commissionNum=findViewById(R.id.commission_num);
        sureBtn=findViewById(R.id.sure_button);
        resetBtn=findViewById(R.id.reset_button);
        userNameImg=findViewById(R.id.userName_image);
        actionCenter=findViewById(R.id.action_bar_text);
        actionRigeht=findViewById(R.id.action_bar_right);
        userNametext=findViewById(R.id.userName_text);
        back=findViewById(R.id.action_bar_return);
        actionCenter.setText(Utils.getString(R.string.代理充值));
        actionRigeht.setText(Utils.getString(R.string.明细));
        actionRigeht.setVisibility(View.VISIBLE);
        resetBtn.setOnClickListener(this);
        sureBtn.setOnClickListener(this);
        userNameImg.setOnClickListener(this);
        actionRigeht.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.sure_button:
               /*
               点击确定按钮, 判断输入框内容是否符合规范,  请求成功---提示订单提交成功  请求失败--- 提示错误信息
                */
               userName= userAccount.getText().toString();//会员账号输入框内容
               acvesNum=anvestNumEdit.getText().toString();//充值金额输入框内容
               passWord=passWordEdit.getText().toString();//安全密码输入框内容
               if(StringMyUtil.isEmptyString(userName)){

                   showToast(Utils.getString(R.string.请输入会员账号));
               }else if(StringMyUtil.isEmptyString(acvesNum)){
                   showToast(Utils.getString(R.string.请输入充值金额));
               }else if(StringMyUtil.isEmptyString(passWord)){

                   showToast(Utils.getString(R.string.请输入提款密码));
               }else {

                   if(clickGapFilter(1000)&&sureBtn_flag){
                       sureBtn_flag = false;
                       HashMap<String, Object> data = new HashMap<>();
                       data.put("nickname",userName);
                       data.put("parent_id",user_id);
                       data.put("price",acvesNum);
                       data.put("paypassword",passWord);
                       Utils.docking(data, RequestUtil.REQUEST_26rzq, 0, new DockingUtil.ILoaderListener() {
                           @Override
                           public void success(String content) {
                               sureBtn_flag = true;
                               JSONObject jsonObject = JSONObject.parseObject(content);
                               String message = jsonObject.getString("message");
                               showToast(message);
                               Intent intent = new Intent(AgentInvestActivity.this, AgentCenterActivity.class);
                               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               startActivity(intent);
                           }

                           @Override
                           public void failed(MessageHead messageHead) {
                               sureBtn_flag = true;
                               showToast(messageHead.getInfo());
                           }
                       });
                   }

               }
               break;
           case R.id.reset_button:
               /*
               点击重置,清空所有控件的内容
                */
               userAccount.setText("");
               anvestNumEdit.setText("");
               passWordEdit.setText("");
               userNametext.setText("");
               userNametext.setVisibility(View.INVISIBLE);
               showToast(Utils.getString(R.string.已重置));
               break;
           case R.id.userName_image:
               /*
               判断输入的会员账号是够符合规范,正确 --- 显示真实姓名  错误---提示错误信息
                */
               userName= userAccount.getText().toString();//会员账号输入框内容
               if(StringMyUtil.isEmptyString(userName)){
                   showToast(Utils.getString(R.string.请输入会员账号));
                   userNametext.setVisibility(View.INVISIBLE);
               }else{
                   HashMap<String, Object> dataRealName = new HashMap<>();
                   dataRealName.put("parent_id",user_id);
                   dataRealName.put("nickname",userName);
                   Utils.docking(dataRealName, RequestUtil.REQUEST_25rzq, 0, new DockingUtil.ILoaderListener() {
                       @Override
                       public void success(String content) {
                           JSONObject jsonObject = JSONObject.parseObject(content);
                               String realname = jsonObject.getString("realname");
                               if(StringMyUtil.isEmptyString(realname)){
                                   userNametext.setText(Utils.getString(R.string.暂无));
                               }else {
                                   userNametext.setText(Utils.getString(R.string.姓名:)+realname);
                               }
                               userNametext.setVisibility(View.VISIBLE);
                       }

                       @Override
                       public void failed(MessageHead messageHead) {
                           showToast(messageHead.getInfo());
                           userNametext.setVisibility(View.INVISIBLE);
                       }
                   });
               }
               break;
               /*
               点击右边的佣金明细, 跳转
                */
           case R.id.action_bar_right:
               startActivity(new Intent(AgentInvestActivity.this,RakeBackActivity.class));
               break;
               //返回键
           case R.id.action_bar_return:
               finish();
               break;
       }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
