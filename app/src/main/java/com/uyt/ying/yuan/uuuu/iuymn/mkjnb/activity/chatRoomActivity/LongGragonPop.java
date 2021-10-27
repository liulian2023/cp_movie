package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.chatRoomActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class LongGragonPop extends PopupWindow implements View.OnClickListener {
    Fragment fragment;
    Activity activity;
    View rootView;
    Context context;
    ImageView long_dragon_iv;
    private TextView popBackTv;
    private TextView popBarCenter;
    public LongGragonPop(Context context, Fragment fragment) {
        super(context);
        this.context =context;
        this.fragment = fragment;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.changlong_shuoming_pop, null);
        initView();
        initPop();
    }

    public LongGragonPop(Context context, Activity activity) {
        super(context);
        this.activity = activity;
        this.context =context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.changlong_shuoming_pop, null);
        initView();
        initPop();
    }

    private void initPop() {
        this.setContentView(rootView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.pop_scale_animation);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

    }

    private void initView() {
        long_dragon_iv=rootView.findViewById(R.id.long_dragon_iv);
        popBackTv=rootView.findViewById(R.id.action_bar_return);
        popBarCenter=rootView.findViewById(R.id.action_bar_text);
        popBarCenter.setText(Utils.getString(R.string.长龙说明));
        popBackTv.setOnClickListener(this);
        popBackTv.setTextColor(ContextCompat.getColor(context,R.color.default_color));
        popBarCenter.setTextColor(ContextCompat.getColor(context,R.color.default_color));
        rootView.findViewById(R.id.action_bar_wrap_linear).setBackgroundColor(Color.WHITE);
        GlideLoadViewUtil.LoadNormalView(context, Utils.checkImageUrl(Utils.getHomeLogo("liveIcon7")),long_dragon_iv);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.action_bar_return:
            LongGragonPop.this.dismiss();
            break;
        default:
            break;
    }
    }
}
