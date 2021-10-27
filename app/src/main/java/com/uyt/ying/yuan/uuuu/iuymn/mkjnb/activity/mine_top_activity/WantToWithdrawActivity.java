package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.want_to_withdraw.BalanceWithdrawFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.want_to_withdraw.CommissionWithdrawFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

public class WantToWithdrawActivity extends BaseActivity implements View.OnClickListener {
    private TextView back;
    private RadioButton balance;
    private RadioButton  commission;
    private CommissionWithdrawFragment commissionWithdrawFragment;
    private BalanceWithdrawFragment balanceWithdrawFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_withdraw);
        bindView();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        back=findViewById(R.id.withdraw_return);
        balance=findViewById(R.id.withdraw_yue);
        commission=findViewById(R.id.withdraw_commissition);
        balance.setOnClickListener(this);
        commission.setOnClickListener(this);
        back.setOnClickListener(this);
        balance.performClick();
    }

    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.withdraw_return:
                finish();
                break;
            case R.id.withdraw_commissition:
                hideAll(transaction);
                if(commissionWithdrawFragment==null){
                    commissionWithdrawFragment=new CommissionWithdrawFragment();
                    transaction.add(R.id.withdraw_content,commissionWithdrawFragment);
                }else{
                    transaction.show(commissionWithdrawFragment);
                }
                break;
            case R.id.withdraw_yue:
                hideAll(transaction);
                if(balanceWithdrawFragment==null){
                    balanceWithdrawFragment=new BalanceWithdrawFragment();
                    transaction.add(R.id.withdraw_content,balanceWithdrawFragment);
                }else{
                    transaction.show(balanceWithdrawFragment);
                }
                break;
        }
        transaction.commit();
    }


    private void hideAll(FragmentTransaction transaction) {
        if(commissionWithdrawFragment!=null){transaction.hide(commissionWithdrawFragment);}
        if(balanceWithdrawFragment!=null){transaction.hide(balanceWithdrawFragment);}
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
