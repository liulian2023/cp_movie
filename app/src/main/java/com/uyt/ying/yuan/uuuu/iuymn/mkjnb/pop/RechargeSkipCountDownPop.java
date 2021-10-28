package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.want_to_invest_activitys.ManualRechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class RechargeSkipCountDownPop extends BasePopupWindow2 {
    private TextView tip_tv;
    private TextView countDown_tv;
    private int countTime=3;
    private ManualRechargeActivity.SkipType skipType;
    String appName;
    Handler handler = new Handler();
    public RechargeSkipCountDownPop(Context context, boolean focusable, ManualRechargeActivity.SkipType skipType) {
        super(context, focusable);
        this.skipType = skipType;
        setFocusable(false);
        setOutsideTouchable(false);
        initAppName();
        handler.postDelayed(countDownRunnable,1000);
    }

    private void initAppName() {
        switch (skipType){
            case WE_CHAT:
                appName=Utils.getString(R.string.微信);
                initStr();
                break;
            case QQ:
                appName="QQ";
                initStr();
                break;
            case ALI_PAY:
                appName=Utils.getString(R.string.支付宝);
                initStr();
                break;
            default:
                break;
        }
    }
    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            if(countTime>=0){
                countTime--;
                countDown_tv.setText(String.format(Utils.getString(R.string.秒后将打开),countTime,appName));
                if(countTime==0){
                    handler.removeCallbacks(this);
                    RechargeSkipCountDownPop.this.dismiss();
                }
                handler.postDelayed(this,1000);
            }

        }
    };
    private void initStr() {
        String tipContent;
        tipContent=String.format(Utils.getString(R.string.账号已复制请前往联系代充完成充值),appName,appName);
        tip_tv.setText(tipContent);
        countDown_tv.setText(String.format(Utils.getString(R.string.秒后将打开),countTime,appName));
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.recharge_tip_pop_layout,null);
        tip_tv = rootView.findViewById(R.id.tip_tv);
        countDown_tv = rootView.findViewById(R.id.countDown_tv);
    }

    public Handler getHandler() {
        return handler;
    }
}
