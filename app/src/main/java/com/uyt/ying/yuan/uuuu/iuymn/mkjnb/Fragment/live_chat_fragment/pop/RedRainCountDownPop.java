package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;

public class RedRainCountDownPop extends PopupWindow {
    View view;
    int countTime=10;
    CountDownHandler countDownHandler;
    LiveFragment liveFragment;
    TextView countdown_tv;
    ImageView dismiss_iv;

    static class CountDownHandler extends Handler {}

    public RedRainCountDownPop(Context context,LiveFragment liveFragment) {
        super(context);
        this.liveFragment= liveFragment;

        if(liveFragment==null){
            return;
        }
        view = LayoutInflater.from(liveFragment.getContext()).inflate(R.layout.red_rain_countdown_pop_layout, null);
        initView();
        initPop();
        countDownHandler=new RedRainCountDownPop.CountDownHandler();
        countDownHandler.postDelayed(countDownRunnable,1000);
    }

    private void initView() {
        countdown_tv=view.findViewById(R.id.countdown_tv);
        dismiss_iv=view.findViewById(R.id.dismiss_iv);
        dismiss_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedRainCountDownPop.this.dismiss();
            }
        });
    }

    private void initPop() {
        this.setContentView(view);
        this.setAnimationStyle(R.style.popupAnimationNormol);
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(false);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                countDownHandler.removeCallbacks(countDownRunnable);
                countDownHandler.removeCallbacksAndMessages(null);
            }
        });
    }

    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            countTime--;
            if(countTime>0){
                countdown_tv.setText(countTime+"");
            }else {
                RedRainCountDownPop.this.dismiss();
            }
            countDownHandler.postDelayed(this,1000);
        }

    };
}
