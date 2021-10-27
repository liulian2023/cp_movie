package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;


import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.mine_message_fragment.MineMessageFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.mine_message_fragment.MineNoticeFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;

/*
我的消息
 */
public class MineMessageActivity extends BaseActivity implements View.OnClickListener {
            private TextView back;
            private RadioButton notice;
            private RadioButton  messege;
            private MineNoticeFragment mineNoticeFragment;
            private MineMessageFragment mineMessageFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message);
        bindView();
        boolean toMessage = getIntent().getBooleanExtra("toMessage", false);
        if(toMessage){
           messege.performClick();
        }else{
           notice.performClick();
        }


    }

    @Override
    protected void init() {

    }

    private void bindView() {
        back=findViewById(R.id.mine_message_return);
        notice=findViewById(R.id.notice);
        messege=findViewById(R.id.message);
        notice.setOnClickListener(this);
        messege.setOnClickListener(this);
        back.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        switch (v.getId()){
        case R.id.mine_message_return:
            finish();
            break;
            case R.id.notice:
                hideAll(transaction);
            if(mineNoticeFragment==null){
               mineNoticeFragment = new MineNoticeFragment();
               transaction.add(R.id.mine_message_content,mineNoticeFragment);
            }else {
                transaction.show(mineNoticeFragment);
            }
            break;
            case R.id.message:
                if(LoginIntentUtil.isLogin(MineMessageActivity.this)){
                    hideAll(transaction);
                    if(mineMessageFragment==null){
                        mineMessageFragment=new MineMessageFragment();
                        transaction.add(R.id.mine_message_content,mineMessageFragment);
                    }else{
                        transaction.show(mineMessageFragment);
                    }
                }else {
                    LoginIntentUtil.toLogin(MineMessageActivity.this);
                }

            break;
                default:
                    break;
    }
        transaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if(mineNoticeFragment!=null){transaction.hide(mineNoticeFragment);}
        if(mineMessageFragment!=null){transaction.hide(mineMessageFragment);}
    }


            @Override
            public void onNetChange(boolean netWorkState) {

            }
        }
