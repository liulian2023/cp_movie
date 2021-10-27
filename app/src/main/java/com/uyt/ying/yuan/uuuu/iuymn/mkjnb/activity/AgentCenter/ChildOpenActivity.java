package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import android.content.Intent;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.child_open_fragemt.ChildOpenFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.child_open_fragemt.InviteCodeFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

/*
下级开户界面(管理两个Fragment)
 */
public class ChildOpenActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton childOpenRadio;
    private RadioButton inviteRadio;
    private TextView childOPenRetuern;
    private ChildOpenFragment childOpenFragment;
    private InviteCodeFragment inviteCodeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_open);
        bindView();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        childOpenRadio =findViewById(R.id.child_open);
        inviteRadio =findViewById(R.id.invite_code);
        childOPenRetuern=findViewById(R.id.child_open_return);
        childOPenRetuern.setOnClickListener(this);
        childOpenRadio.setOnClickListener(this);
        inviteRadio.setOnClickListener(this);
        Intent intent = getIntent();
        boolean isStartInviteCode = intent.getBooleanExtra("isStartInviteCode",false);
        if(isStartInviteCode){
            inviteRadio.performClick();
        }else{
            childOpenRadio.performClick();
        }

    }

    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.child_open:
                hideAll(transaction);
                if(childOpenFragment==null){
                    childOpenFragment=new ChildOpenFragment();
                    transaction.add(R.id.child_open_content,childOpenFragment);
                }else{
                    transaction.show(childOpenFragment);
                }
                break;
            case R.id.invite_code:
                hideAll(transaction);
                if(inviteCodeFragment==null){
                    inviteCodeFragment=new InviteCodeFragment();
                    transaction.add(R.id.child_open_content,inviteCodeFragment);
                }else{
                    transaction.show(inviteCodeFragment);
                }
                break;

            case R.id.child_open_return:
                finish();
        }
        transaction.commit();//提交!提交!!提交!!! (记得提交啊小伙子...)
    }

    private void hideAll(FragmentTransaction transaction) {
        if(childOpenFragment!=null){transaction.hide(childOpenFragment);}
        if(inviteCodeFragment!=null){transaction.hide(inviteCodeFragment);}
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
