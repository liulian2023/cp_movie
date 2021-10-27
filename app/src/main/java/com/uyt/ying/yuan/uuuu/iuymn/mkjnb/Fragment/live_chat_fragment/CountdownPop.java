package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;

public class CountdownPop extends PopupWindow {
    LiveFragment liveFragment;
    ImageView num_iv;
    View view;
    int countTime=3;
    CountDownHandler countDownHandler;

    public CountdownPop(Context context, LiveFragment liveFragment) {
        super(context);
        if(liveFragment==null){
            return;
        }
        view = LayoutInflater.from(liveFragment.getContext()).inflate(R.layout.red_rain_count_down_pop, null);
        initView();
        initPop();
        countDownHandler=new CountDownHandler();
        countDownHandler.postDelayed(countDownRunnable,1000);
    }

    private void initView() {
        num_iv=view.findViewById(R.id.num_iv);
    }

    private void initPop() {
        this.setContentView(view);
        this.setAnimationStyle(R.style.popupAnimationNormol);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(false);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                countDownHandler.removeCallbacksAndMessages(null);
                if(myOnDismissListener!=null){
                    myOnDismissListener.onDisMissListener();
                }
            }
        });
    }

    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            countTime--;
            if(countTime==3){
                num_iv.setImageResource(R.drawable.num_3);
            }else if(countTime==2){
                num_iv.setImageResource(R.drawable.num_2);
            }else if(countTime==1){
                num_iv.setImageResource(R.drawable.num_1);
            }else {
                CountdownPop.this.dismiss();
            }
            countDownHandler.postDelayed(this,1000);
        }

    };


    static class CountDownHandler extends Handler {}


    public interface MyOnDismissListener {
        void onDisMissListener();
    }
    MyOnDismissListener myOnDismissListener=null;

    public MyOnDismissListener getMyOnDismissListener() {
        return myOnDismissListener;
    }

    public void setMyOnDismissListener(MyOnDismissListener myOnDismissListener) {
        this.myOnDismissListener = myOnDismissListener;
    }
}
