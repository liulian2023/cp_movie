package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;

public class ForbiddenTimePop extends BasePopupWindow2 {
    TextView one_hour_tv;
    TextView six_hour_tv;
    TextView twelve_tv;
    TextView one_day_tv;
    TextView one_week_tv;
    TextView one_month_tv;
    public ForbiddenTimePop(Context context) {
        super(context,false);
        setAnimationStyle(R.style.pop_bottom_to_top_150);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.forbidden_time_pop,null);
        one_hour_tv =rootView.findViewById(R.id.one_hour_tv);
        six_hour_tv =rootView.findViewById(R.id.six_hour_tv);
        twelve_tv =rootView.findViewById(R.id.twelve_tv);
        one_day_tv =rootView.findViewById(R.id.one_day_tv);
        one_week_tv =rootView.findViewById(R.id.one_week_tv);
        one_month_tv =rootView.findViewById(R.id.one_month_tv);
        one_hour_tv.setOnClickListener(this);
        six_hour_tv.setOnClickListener(this);
        twelve_tv.setOnClickListener(this);
        one_day_tv.setOnClickListener(this);
        one_week_tv.setOnClickListener(this);
        one_month_tv.setOnClickListener(this);

    }
}
