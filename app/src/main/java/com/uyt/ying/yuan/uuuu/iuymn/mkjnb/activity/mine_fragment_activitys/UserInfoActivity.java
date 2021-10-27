package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.user_info_fragment.UserInfoFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.user_info_fragment.UserLevelFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

/*
个人信息activity（管理两个fragment）
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton userInfo;
    private RadioButton userLevel;
    private  UserInfoFragment userInfoFragment;
    private  UserLevelFragment userLevelFragment;
    private TextView UserInfoReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
    }

    @Override
    protected void init() {

    }

    public void initView() {
        userInfo =findViewById(R.id.info);
        userLevel =findViewById(R.id.user_level);
        UserInfoReturn=findViewById(R.id.user_info_return);
        userInfo.setOnClickListener(this);
        userLevel.setOnClickListener(this);
        UserInfoReturn.setOnClickListener(this);
        userInfo.performClick();
    }
    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.info:
                //每次点击切换都先隐藏所有碎片
                hideall(transaction);
                if(userInfoFragment==null){
                userInfoFragment = new UserInfoFragment();
                transaction.add(R.id.user_info_content,userInfoFragment);
                }else{
                    transaction.show(userInfoFragment);
                }
                break;
            case R.id.user_level:
                hideall(transaction);
                if(userLevelFragment==null){
                    userLevelFragment = new UserLevelFragment();
                    transaction.add(R.id.user_info_content,userLevelFragment);
                }
                else{
                    transaction.show(userLevelFragment);
                }
                break;
                //退出键
            case R.id.user_info_return:
                finish();
        }
        //每个碎片管理器只能有一个FragmentTransaction实例,不用将FragmentTransaction设为成员变量
        transaction.commit();
    }
   //隐藏所有碎片
    private void hideall(FragmentTransaction transaction) {
        if(userInfoFragment!=null){transaction.hide(userInfoFragment);}
        if(userLevelFragment!=null){transaction.hide(userLevelFragment);}
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
