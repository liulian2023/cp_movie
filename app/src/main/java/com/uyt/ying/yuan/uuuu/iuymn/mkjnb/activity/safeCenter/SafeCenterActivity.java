package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
安全中心界面
 */
public class SafeCenterActivity extends BaseActivity implements View.OnClickListener {
private TextView loginTime;
private TextView safeLevel;
private TextView modifiPayPassword;
private TextView phonePassword;
private TextView phonePassword1;
private LinearLayout loginPassword;
private LinearLayout modifySafePassword;
private LinearLayout mobile_linear;
private LinearLayout bankCard;
private ImageView one;
private ImageView two;
private ImageView three;
private ImageView four;
private ImageView five;
private boolean havePayPassword;
private boolean havePhone;
private boolean haveBank;
private String mobileNumSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sava_center);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.安全中心));
        mobileNumSwitch=SharePreferencesUtil.getString(MyApplication.getInstance(),"mobileNumSwitch","");
        initView();
        initdata();


    }

    @Override
    protected void init() {

    }

    public void initView() {
        loginTime =findViewById(R.id.login_time);
        loginPassword=findViewById(R.id.modify_login_password);
        mobile_linear =findViewById(R.id.mobile_linear);
        modifySafePassword=findViewById(R.id.modify_safe_password);
        bankCard=findViewById(R.id.bank_card);
        one =findViewById(R.id.img_one);
        two =findViewById(R.id.img_two);
        three =findViewById(R.id.img_three);
        four =findViewById(R.id.img_four);
        five =findViewById(R.id.img_five);
        safeLevel=findViewById(R.id.safe_level);
        modifiPayPassword=findViewById(R.id.modifiPayPassword);
        phonePassword=findViewById(R.id.phonePassword);
        phonePassword1=findViewById(R.id.phonePassword1);
        loginPassword.setOnClickListener(this);
        modifySafePassword.setOnClickListener(this);
        mobile_linear.setOnClickListener(this);
        bankCard.setOnClickListener(this);
        if(mobileNumSwitch.equals("0")){
            mobile_linear.setVisibility(View.VISIBLE);
        }else {
            mobile_linear.setVisibility(View.GONE);

        }
    }
    /*
    上次登录时间,安全级别,判断有无设置安全密码相关
     */
    private void initdata() {
        HashMap<String, Object> dataLoginInfo = new HashMap<>();
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        dataLoginInfo.put("user_id",user_id);
        Utils.docking(dataLoginInfo, RequestUtil.REQUEST_13r, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONArray.parseObject(content);
                UserInfoEntity userInfoEntity = JSONObject.parseObject(content, UserInfoEntity.class);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                String lastModifiedDate = memberInfo.getString("lastModifiedDate");
                Long aLong = Long.valueOf(lastModifiedDate);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aLong);
                String format1 = format.format(date);
                loginTime.setText(Utils.getString(R.string.上次登录时间:)+format1);
                String paypassword = memberInfo.getString("paypassword");//安全密码
//                String bankCard = memberInfo.getString("bankCard");//银行卡
                List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> memberBankInfoVoList = userInfoEntity.getMemberInfo().getMemberBankInfoVoList();
                String phone = memberInfo.getString("phone");//密保手机
                int num =2;
                if(!StringMyUtil.isEmptyString(paypassword)){
                    num++;
                    modifiPayPassword.setText(Utils.getString(R.string.修改提款密码));
                    havePayPassword=true;
                }
                else{
                    modifiPayPassword.setText(Utils.getString(R.string.设置提款密码));
                    havePayPassword=false;
                }
//                if(!StringMyUtil.isEmptyString(bankCard)){
                if(memberBankInfoVoList!=null&&memberBankInfoVoList.size()!=0){
                    num++;
                    haveBank =true;
                }else{
                    haveBank =false;
                }
                if(!StringMyUtil.isEmptyString(phone)){
                    num++;
                    phonePassword.setText(Utils.getString(R.string.已绑定密保手机));
                    phonePassword1.setText(phone);
                    havePhone=true;
                }
                else {
                    phonePassword.setText(Utils.getString(R.string.设置密保手机));
                    phonePassword1.setText(Utils.getString(R.string.设置));
                    havePhone=false;
                }

                if(num==2){
                    one.setImageResource(R.drawable.safe_center_true);
                    two.setImageResource(R.drawable.safe_center_true);
                    three.setImageResource(R.drawable.safe_center_false);
                    four.setImageResource(R.drawable.safe_center_false);
                    five.setImageResource(R.drawable.safe_center_false);
                    safeLevel.setText(Utils.getString(R.string.您的账号安全级别为低));
                }
               if(num==3){
                   one.setImageResource(R.drawable.safe_center_true);
                   two.setImageResource(R.drawable.safe_center_true);
                   three.setImageResource(R.drawable.safe_center_true);
                   four.setImageResource(R.drawable.safe_center_false);
                   five.setImageResource(R.drawable.safe_center_false);
                   safeLevel.setText(Utils.getString(R.string.您的账号安全级别为中));
               }
               else if(num==4){
                   one.setImageResource(R.drawable.safe_center_true);
                   two.setImageResource(R.drawable.safe_center_true);
                   three.setImageResource(R.drawable.safe_center_true);
                   four.setImageResource(R.drawable.safe_center_true);
                   five.setImageResource(R.drawable.safe_center_false);
                   safeLevel.setText(Utils.getString(R.string.您的账号安全级别为中));
               }
               else if(num==5){
                   one.setImageResource(R.drawable.safe_center_true);
                   two.setImageResource(R.drawable.safe_center_true);
                   three.setImageResource(R.drawable.safe_center_true);
                   four.setImageResource(R.drawable.safe_center_true);
                   five.setImageResource(R.drawable.safe_center_true);
                   safeLevel.setText(Utils.getString(R.string.您的账号安全级别为高));
                }

//            SharePreferencesUtil.putBoolean(SafeCenterActivity.this,"havePhone",false);
            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
                break;
            case R.id.modify_login_password://点击跳转修改登录密码界面
                startActivity(new Intent(SafeCenterActivity.this, ModifyLoginPasswordActivity.class));
//                finish();
                break;

            case R.id.modify_safe_password://点击跳转修改安全密码界面
                //用于判断是否有设置安全密码
                Intent intent = new Intent(SafeCenterActivity.this, SureLoginPassword.class);
                intent.putExtra("havePayPassword",havePayPassword);
                startActivity(intent);
//                finish();
              break;
            case R.id.mobile_linear://管理密保手机
                if(havePayPassword){
                    Intent intent1 = new Intent(SafeCenterActivity.this, PhonePasswordActivity.class);
                    intent1.putExtra("havePhone",havePhone);
                    startActivity(intent1);
//                finish();
                }else{
                    //提示先设置安全密码
                    AlertDialog isExit = new AlertDialog.Builder(this).create();
                    isExit.setTitle(Utils.getString(R.string.提示));
                    isExit.setMessage(Utils.getString(R.string.未设置提款密码,前去设置!));
                    isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Intent intent = getIntent();
                            //                boolean havePayPassword = intent.getBooleanExtra("havePayPassword", false);
                            Intent intent = new Intent(SafeCenterActivity.this, SureLoginPassword.class);
                            intent.putExtra("havePayPassword",false);
                            startActivity(intent);
//                            finish();
                        }
                    });
                    isExit.show();
                }

                break;
            case R.id.bank_card:
                if(havePayPassword){
                    if(havePhone){
                        Intent intent2 = new Intent(this, BankCardManageActivity.class);
                        intent2.putExtra("haveBank",haveBank);
                        startActivity(intent2);
//                    finish();
                    }else{
                        if(mobileNumSwitch.equals("1")){//不需要绑定手机号
                            Intent intent11 = new Intent(SafeCenterActivity.this, BankCardManageActivity.class);
                            intent11.putExtra("haveBank",haveBank);
                            startActivity(intent11);
                            finish();
                        }else {
                            AlertDialog isExit = new AlertDialog.Builder(this).create();
                            isExit.setTitle(Utils.getString(R.string.提示));
                            isExit.setMessage(Utils.getString(R.string.您未设置密保手机,请前往设置));
                            isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SafeCenterActivity.this, PhonePasswordActivity.class);
                                    intent.putExtra("havePhone",havePhone);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            isExit.show();
                        }

                    }
                }else {
                    //提示先设置安全密码
                    AlertDialog isExit = new AlertDialog.Builder(this).create();
                    isExit.setTitle(Utils.getString(R.string.提示));
                    isExit.setMessage(Utils.getString(R.string.未设置提款密码,前去设置!));
                    isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Intent intent = getIntent();
                            //                boolean havePayPassword = intent.getBooleanExtra("havePayPassword", false);
                            Intent intent = new Intent(SafeCenterActivity.this, SureLoginPassword.class);
                            intent.putExtra("havePayPassword",false);
                            startActivity(intent);
//                            finish();
                        }
                    });
                    isExit.show();
                }
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initdata();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
