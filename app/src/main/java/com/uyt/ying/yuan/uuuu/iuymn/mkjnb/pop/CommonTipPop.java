package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;

public class CommonTipPop extends PopupWindow implements View.OnClickListener {
    private  String title;
    private  String tip;
    private String sureContent;
    Activity activity;
    View rootView;
    TextView exit_pop_title_tv;
    TextView exit_cancel_tv;
    TextView exit_sure_tv;
    TextView common_tip_tv;
    public CommonTipPop(Context context, Activity activity,String title,String tip) {
        super(context);
        this.activity = activity;
        this.title = title;
        this.tip = tip;
        initView();
        initPop();
    }
    public CommonTipPop(Context context, Activity activity,String title,String tip,String sureContent) {
        super(context);
        this.activity = activity;
        this.title = title;
        this.tip = tip;
        this.sureContent = sureContent;
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
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if(activity!=null){
                    ProgressDialogUtil.darkenBackground(activity,1f);
                }
            }
        });
    }

    private void initView() {
        rootView = LayoutInflater.from(activity).inflate(R.layout.exit_pop,null);
        exit_pop_title_tv=rootView.findViewById(R.id.exit_pop_title_tv);
        exit_cancel_tv=rootView.findViewById(R.id.exit_cancel_tv);
        exit_sure_tv=rootView.findViewById(R.id.exit_sure_tv);
        common_tip_tv=rootView.findViewById(R.id.common_tip_tv);
        common_tip_tv.setText(tip);
        exit_pop_title_tv.setText(title);
        if(StringMyUtil.isNotEmpty(sureContent)){
            exit_sure_tv.setText(sureContent);
        }
        exit_sure_tv.setOnClickListener(this);
        exit_cancel_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_cancel_tv:
                CommonTipPop.this.dismiss();
                if(onCancelClickListener!=null){
                    onCancelClickListener.onCancelClick(v);
                }
                break;
            case R.id.exit_sure_tv:
                if(onClickLintener!=null){
                    onClickLintener.onSureClick(exit_sure_tv);
                }
                break;
                default:
                    break;
        }
    }

    public TextView getExit_cancel_tv() {
        return exit_cancel_tv;
    }

    public interface  OnClickLintener{
        void onSureClick(View view);

    }
    OnClickLintener onClickLintener;

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }

    public interface  OnCancelClickListener{
        void onCancelClick(View view);

    }
    OnCancelClickListener onCancelClickListener;

    public void setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
    }
}
