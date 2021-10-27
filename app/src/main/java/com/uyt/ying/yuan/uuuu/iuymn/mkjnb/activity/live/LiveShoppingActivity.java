package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.ShoppingTwoFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

public class LiveShoppingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_gou_cai);
        bindView();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.shopping_content, ShoppingTwoFragment.newInstance(true,true));
        transaction.commit();
    }

    private void bindView() {
    }

    @Override
    protected void init() {

    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
