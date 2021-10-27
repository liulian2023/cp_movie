package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.agent_journaling_fragment.ChildJournalingFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.agent_journaling_fragment.TeamJournalingFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

import java.util.Date;
/*
代理报表界面(管理两个fragment)
 */

public class AgentJournalingActivity extends BaseActivity implements View.OnClickListener {
    private TextView returnText;
    private RadioButton teamJournaling;
    private RadioButton childJournaling;
    private LinearLayout chooseLinear2;
    private LinearLayout chooseLinear;
    private ChildJournalingFragment childJournalingFragment;//下级报表
    private TeamJournalingFragment teamJournalingFragment;//团队报表
    private FragmentManager supportFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_journaling);
        bindView();
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        returnText =findViewById(R.id.agent_journaling_return);
        teamJournaling =findViewById(R.id.team_journaling);
        childJournaling =findViewById(R.id.child_journaling);
        chooseLinear2 =findViewById(R.id.choose_linear2);
        chooseLinear =findViewById(R.id.choose_linear);
        returnText.setOnClickListener(this);
        teamJournaling.setOnClickListener(this);
        childJournaling.setOnClickListener(this);
        teamJournaling.performClick();
    }

    @Override
    public void onClick(View v) {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.team_journaling:
                hideAll(transaction);
                if(chooseLinear.getVisibility()!=View.VISIBLE){
                    chooseLinear.setVisibility(View.VISIBLE);
                }
                chooseLinear2.setVisibility(View.GONE);
                if(teamJournalingFragment==null){
                teamJournalingFragment= new TeamJournalingFragment();
                transaction.add(R.id.agent_journaling_content,teamJournalingFragment);
            }else {
                transaction.show(teamJournalingFragment);
            }
            break;
            case R.id.child_journaling:

                hideAll(transaction);
                if(chooseLinear2.getVisibility()!=View.VISIBLE){
                    chooseLinear2.setVisibility(View.VISIBLE);
                }
                chooseLinear.setVisibility(View.GONE);
                if(childJournalingFragment ==null){
                    childJournalingFragment = new ChildJournalingFragment();
                    transaction.add(R.id.agent_journaling_content, childJournalingFragment);
                }else {
                    transaction.show(childJournalingFragment);
                }
                break;
            case R.id.agent_journaling_return:
                finish();
                break;
        }
        transaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if(teamJournalingFragment!=null){transaction.hide(teamJournalingFragment);}
        if(childJournalingFragment !=null){transaction.hide(childJournalingFragment);}
    }
    /*
    下级报表页面点击Utils.getString(R.string.他的团队报表),跳转相关
     */
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        boolean isSearch = intent.getBooleanExtra("isSearch", false);
        String nikename = intent.getStringExtra("nikename");
        if(isSearch){
            TeamJournalingFragment teamJournalingFragment = (TeamJournalingFragment) getSupportFragmentManager().findFragmentById(R.id.agent_journaling_content);
            EditText editText = teamJournalingFragment.getView().findViewById(R.id.search_edit);//将上个页面点击的nikeName显示到输入框
            editText.setText(nikename);
            teamJournalingFragment.initData(new Date(),new Date(),nikename);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
