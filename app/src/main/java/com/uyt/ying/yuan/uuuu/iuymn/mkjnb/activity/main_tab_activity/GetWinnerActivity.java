package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.get_winner_fragments.GetWinnerFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.get_winner_fragments.YesTodayWinOrLoseFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
//import com.example.administrator.aaa.utils.PopupWindowUtil;

/*
首页中奖信息页面
 */
public class GetWinnerActivity extends BaseActivity implements View.OnClickListener {
    private GetWinnerFragment getWinnerFragment;
    private YesTodayWinOrLoseFragment yesTodayWinOrLoseFragment;
    private RadioButton getwinner;
    private RadioButton yestoday;
    private TextView returnTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_winner_web_view);
        bindView();

    }

    @Override
    protected void init() {

    }

    private void bindView() {
        returnTv=findViewById(R.id.getwinner_return);
        returnTv.setOnClickListener(this);
        getwinner=findViewById(R.id.get_winner_radio);
        yestoday=findViewById(R.id.yestoday_radio);
        getwinner.setOnClickListener(this);
        yestoday.setOnClickListener(this);
        getwinner.performClick();
    }

    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        switch (v.getId()){
            case R.id.get_winner_radio:
                hideAll(transaction);
                if(getWinnerFragment==null){
                    getWinnerFragment=new GetWinnerFragment();
                    transaction.add(R.id.get_winner_content,getWinnerFragment);
                }else {
                    transaction.show(getWinnerFragment);
                }
                break;
            case R.id.yestoday_radio:
                hideAll(transaction);
                if(yesTodayWinOrLoseFragment==null){
                    yesTodayWinOrLoseFragment=new YesTodayWinOrLoseFragment();
                    transaction.add(R.id.get_winner_content,yesTodayWinOrLoseFragment);
                }else {
                    transaction.show(yesTodayWinOrLoseFragment);
                }
                break;
            case  R.id.getwinner_return:
                finish();
                break;
        }
        transaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if(getWinnerFragment!=null){transaction.hide(getWinnerFragment);}
        if(yesTodayWinOrLoseFragment!=null){transaction.hide(yesTodayWinOrLoseFragment);}
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
