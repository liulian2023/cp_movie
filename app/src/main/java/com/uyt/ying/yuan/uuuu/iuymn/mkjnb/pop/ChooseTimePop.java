package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;

public class ChooseTimePop extends PopupWindow implements View.OnClickListener {
    Activity activity;
    View rootView;
    TextView today_tv;
    TextView yesterday_tv;
    TextView week_tv;
    public ChooseTimePop(Context context, Activity activity) {
        super(context);
        this.activity = activity;
        intiView();
        initPop();
    }

    private void initPop() {
        this.setContentView(rootView);
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popAlphaanim0_3);
        ColorDrawable dw = new ColorDrawable(Color.WHITE);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if(activity!=null){
                    ProgressDialogUtil.darkenBackground(activity,1f);
                }
            }
        });
    }

    private void intiView() {
        rootView = LayoutInflater.from(activity).inflate(R.layout.choose_time_pop,null);
        today_tv = rootView.findViewById(R.id.today_tv);
        yesterday_tv = rootView.findViewById(R.id.yesterday_tv);
        week_tv = rootView.findViewById(R.id.week_tv);
        today_tv.setOnClickListener(this);
        yesterday_tv.setOnClickListener(this);
        week_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(onChooseTimeClickLintener!=null){
            onChooseTimeClickLintener.onChooseTimeClick(v);
        }
    }
    public static interface   OnChooseTimeClickLintener{
        void onChooseTimeClick(View view);
    }
    OnChooseTimeClickLintener onChooseTimeClickLintener;

    public void setOnChooseTimeClickLintener(OnChooseTimeClickLintener onChooseTimeClickLintener) {
        this.onChooseTimeClickLintener = onChooseTimeClickLintener;
    }
}
