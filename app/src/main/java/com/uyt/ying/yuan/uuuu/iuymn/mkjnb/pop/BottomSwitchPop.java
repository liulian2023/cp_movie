package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

public class BottomSwitchPop extends PopupWindow implements View.OnClickListener {
    View rootView;
    TextView bottom_switch_1_tv;
    TextView bottom_switch_2_tv;
    TextView bottom_switch_3_tv;
    TextView bottom_switch_cancel_tv;
    Context context;
    String index1Content;
    String index2Content;

    public BottomSwitchPop(Context context,String index1Content,String index2Content) {
        super(context);
        this.context = context;
        this.index1Content = index1Content;
        this.index2Content = index2Content;
        initPop();
        initView();
    }
    public BottomSwitchPop(Context context) {
        super(context);
        this.context = context;
        initView();
        initPop();
    }

    private void initView() {
        rootView = LayoutInflater.from(context).inflate(R.layout.bottom_switch_pop_layout,null);
        bottom_switch_1_tv = rootView.findViewById(R.id.bottom_switch_1_tv);
        bottom_switch_2_tv = rootView.findViewById(R.id.bottom_switch_2_tv);
        bottom_switch_3_tv = rootView.findViewById(R.id.bottom_switch_3_tv);
        bottom_switch_cancel_tv = rootView.findViewById(R.id.bottom_switch_cancel_tv);
        bottom_switch_1_tv.setOnClickListener(this);
        bottom_switch_2_tv.setOnClickListener(this);
        bottom_switch_3_tv.setOnClickListener(this);
        bottom_switch_cancel_tv.setOnClickListener(this);
        if(StringMyUtil.isNotEmpty(index1Content)){
            bottom_switch_1_tv.setText(index1Content);
        }
        if(StringMyUtil.isNotEmpty(index2Content)){
            bottom_switch_2_tv.setText(index2Content);
        }
    }

    private void initPop() {
        this.setContentView(rootView);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground((Activity)context,1f);
            }
        });
        this.showAtLocation(Utils.getContentView((Activity) context), Gravity.BOTTOM,0,0);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.bottom_switch_1_tv:
                    if(actionSheetClickListener!=null){
                        actionSheetClickListener.onActionSheetClickClick(0,v);
                    }
                    break;
                case R.id.bottom_switch_2_tv:
                    if(actionSheetClickListener!=null){
                        actionSheetClickListener.onActionSheetClickClick(1,v);
                    }
                    break;
                    case R.id.bottom_switch_3_tv:
                    if(actionSheetClickListener!=null){
                        actionSheetClickListener.onActionSheetClickClick(2,v);
                    }
                    break;
                case R.id.bottom_switch_cancel_tv:
                    BottomSwitchPop.this.dismiss();
                    break;
                default:
                    break;
            }
    }
    public interface  ActionSheetClickListener{
        void onActionSheetClickClick(int index,View view);
    }
    ActionSheetClickListener actionSheetClickListener;

    public void setActionSheetClickListener(ActionSheetClickListener actionSheetClickListener) {
        this.actionSheetClickListener = actionSheetClickListener;
    }
}
