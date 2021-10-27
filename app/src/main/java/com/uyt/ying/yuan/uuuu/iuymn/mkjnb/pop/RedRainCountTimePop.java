package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;

public class RedRainCountTimePop extends BasePopupWindow2 {
    public   TextView hour1_tv;
    public  TextView hour2_tv;
    public TextView minute1_tv;
    public   TextView minute2_tv;
    public TextView second1_tv;
    public  TextView second2_tv;

    public  ImageView hour1_iv;
    public  ImageView hour2_iv;
    public  ImageView minute1_iv;
    public   ImageView minute2_iv;
    public ImageView second1_iv;
    public ImageView second2_iv;

    ImageView i_know_iv;

    public RedRainCountTimePop(Context context, boolean focusable) {
        super(context, focusable);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.red_rain_count_time_pop_layout,null);
        hour1_tv = rootView.findViewById(R.id.hour1_tv);
        hour2_tv = rootView.findViewById(R.id.hour2_tv);
        minute1_tv = rootView.findViewById(R.id.minute1_tv);
        minute2_tv = rootView.findViewById(R.id.minute2_tv);
        second1_tv = rootView.findViewById(R.id.second1_tv);
        second2_tv = rootView.findViewById(R.id.second2_tv);
        hour1_iv = rootView.findViewById(R.id.hour1_iv);
        hour2_iv = rootView.findViewById(R.id.hour2_iv);
        minute1_iv = rootView.findViewById(R.id.minute1_iv);
        minute2_iv = rootView.findViewById(R.id.minute2_iv);
        second1_iv = rootView.findViewById(R.id.second1_iv);
        second2_iv = rootView.findViewById(R.id.second2_iv);
        i_know_iv = rootView.findViewById(R.id.i_know_iv);
        i_know_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedRainCountTimePop.this.dismiss();
            }
        });
    }
}
