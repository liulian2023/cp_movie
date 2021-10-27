package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.QuotaChangeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.QuotaModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;

public class RechargePop extends PopupWindow implements View.OnClickListener {
    public enum PopType{
        IN,
        OUT;
    }
    PopType popType;
    Activity activity;
    View rootView;
    public    EditText recharge_etv;
    TextView recharge_cancel_tv;
    TextView recharge_sure_tv;
    Button all_btn;
    TextView recharge_pop_title_tv;
    QuotaModel currentModel;
    public RechargePop(Context context, Activity activity,PopType popType,QuotaModel currentModel) {
        super(context);
        this.activity = activity;
        this.popType = popType;
        this.currentModel = currentModel;
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
        rootView = LayoutInflater.from(activity).inflate(R.layout.exit_sure_pop,null);
        recharge_etv=rootView.findViewById(R.id.recharge_etv);
        recharge_cancel_tv=rootView.findViewById(R.id.recharge_cancel_tv);
        recharge_sure_tv=rootView.findViewById(R.id.recharge_sure_tv);
        all_btn=rootView.findViewById(R.id.all_btn);
        recharge_pop_title_tv=rootView.findViewById(R.id.recharge_pop_title_tv);
        if(popType==PopType.IN){
            recharge_pop_title_tv.setText(Utils.getString(R.string.转入));
            all_btn.setText(Utils.getString(R.string.全部转入));
        }else {
            recharge_pop_title_tv.setText(Utils.getString(R.string.转出));
            all_btn.setText(Utils.getString(R.string.全部转出));

        }
        recharge_sure_tv.setOnClickListener(this);
        recharge_cancel_tv.setOnClickListener(this);
        all_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recharge_sure_tv:
                if(onClickLintener!=null){
                    onClickLintener.onSureClick(v,popType);
                }
                break;
            case R.id.recharge_cancel_tv:
                RechargePop.this.dismiss();
                break;
            case R.id.all_btn:
                if(activity instanceof QuotaChangeActivity){
                    QuotaChangeActivity quotaChangeActivity = (QuotaChangeActivity) activity;
                    if(popType==PopType.IN){
                        recharge_etv.setText(quotaChangeActivity.big_amount_tv.getText().toString());
                    }else {
                        recharge_etv.setText(currentModel.getAmount());
                    }
                }
                break;
                default:
                    break;
        }
    }
  public   interface OnClickLintener{
        void onSureClick(View v, PopType popType);
    }
    OnClickLintener onClickLintener;

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }
}
