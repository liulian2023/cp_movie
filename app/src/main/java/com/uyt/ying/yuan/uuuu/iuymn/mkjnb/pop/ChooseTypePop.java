package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;

public class ChooseTypePop extends BasePopupWindow2 {
    private TextView all_type_tv;
    private  TextView income_tv;
    private  TextView expenditure_tv;
    public ChooseTypePop(Context context, boolean focusable) {
        super(context, focusable);
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.rake_back_time_choose_layout,null);
        all_type_tv = rootView.findViewById(R.id.all_type_tv);
        income_tv = rootView.findViewById(R.id.income_tv);
        expenditure_tv = rootView.findViewById(R.id.expenditure_tv);
        all_type_tv.setOnClickListener(this);
        income_tv.setOnClickListener(this);
        expenditure_tv.setOnClickListener(this);
    }
}
