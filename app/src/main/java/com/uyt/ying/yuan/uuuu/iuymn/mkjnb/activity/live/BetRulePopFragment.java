package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.widget.TextView;

import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.MvpBaseFragment;

import butterknife.BindView;

public class BetRulePopFragment extends MvpBaseFragment {

    @BindView(R.id.tv_betrulechild)
    TextView tv_betrulechild;

 //   int position = 0;




    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_betrulepop;
    }

    @Override
    protected void initView() {
      //  position = getArguments().getInt("position",0);
        LogUtils.e("BetRulePopFragment:initView");

    }

    @Override
    protected void initEventAndData() {
        LogUtils.e("BetRulePopFragment:initEventAndData");
        tv_betrulechild.setText(""+777);
    }


}
