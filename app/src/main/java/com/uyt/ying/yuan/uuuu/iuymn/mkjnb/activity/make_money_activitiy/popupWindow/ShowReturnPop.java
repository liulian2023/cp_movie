package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class ShowReturnPop extends PopupWindow {
    Activity activity;
    private View inflate;
    ImageView showReturnImg;
    TextView k3Text;
    TextView sscText;
    TextView happy8Text;
    TextView xuan5Text;
    TextView farmText;
    TextView raceText;
    TextView sixText;
    TextView dandanText;
    TextView happy10Text;
    ArrayList<String> rateList = new ArrayList<>();

    public ShowReturnPop(Context context, Activity activity, ArrayList<String> rateList) {
        super(context);
        this.activity = activity;
        this.rateList = rateList;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflate = inflater.inflate(R.layout.show_return_pupopwindow, null);
        initView();
        initPopWindow();
    }

    private void initView() {
         showReturnImg =inflate.findViewById(R.id.showReturnImg);
         k3Text =inflate.findViewById(R.id.k3);
         sscText =inflate.findViewById(R.id.ssc);
         happy8Text =inflate.findViewById(R.id.happy8);
         xuan5Text =inflate.findViewById(R.id.xuan5);
         farmText =inflate.findViewById(R.id.farm);
         raceText =inflate.findViewById(R.id.race);
         sixText =inflate.findViewById(R.id.six);
         dandanText =inflate.findViewById(R.id.dandan);
         happy10Text =inflate.findViewById(R.id.happy10);
        k3Text.setText(rateList.get(0));
        sscText.setText(rateList.get(1));
        happy8Text.setText(rateList.get(2));
        xuan5Text.setText(rateList.get(3));
        farmText.setText(rateList.get(4));
        raceText.setText(rateList.get(5));
        sixText.setText(rateList.get(6));
        dandanText.setText(rateList.get(7));
        happy10Text.setText(rateList.get(8));

        showReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowReturnPop.this.dismiss();
            }
        });
    }

    private void initPopWindow() {
        this.setContentView(inflate);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
//        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(R.color.black_50));
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(activity,R.color.alpha_80_black));
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM,0,0);
        ProgressDialogUtil.darkenBackground(activity,0.5f);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(activity,1f);
            }
        });
    }
}
