package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

import okhttp3.Headers;

public class ReportListPop extends PopupWindow implements View.OnClickListener {
    Fragment fragment;
    String liveId;//主播id

    Context context;
    TextView ad_deceive_tv;
    TextView negative_tv;
    TextView abuse_tv;
    TextView other_content_tv;
    TextView report_list_cancel_tv;
    View v;
    public ReportListPop(Context context, Fragment fragment, String liveId) {
        super(context);
        this.context = context;
        this.fragment = fragment;
        this.liveId = liveId;
         v = LayoutInflater.from(fragment.getContext()).inflate(R.layout.roport_live_pop_list_layout, null);
        initView();
        initPop();

    }

    private void initPop() {
        this.setContentView(v);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        this.showAtLocation(Utils.getContentView(fragment.getActivity()), Gravity.BOTTOM,0,0);
    }

    private void initView() {
        report_list_cancel_tv=v.findViewById(R.id.bottom_switch_cancel_tv);
        other_content_tv=v.findViewById(R.id.other_content_tv);
        abuse_tv=v.findViewById(R.id.abuse_tv);
        negative_tv=v.findViewById(R.id.negative_tv);
        ad_deceive_tv=v.findViewById(R.id.ad_deceive_tv);
        report_list_cancel_tv.setOnClickListener(this);
        other_content_tv.setOnClickListener(this);
        abuse_tv.setOnClickListener(this);
        ad_deceive_tv.setOnClickListener(this);
        report_list_cancel_tv.setOnClickListener(this);
        negative_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_switch_cancel_tv:
                ReportListPop.this.dismiss();
                break;
            case R.id.other_content_tv:
                requestReportAnchor(other_content_tv.getText().toString());
                break;
            case R.id.abuse_tv:
                requestReportAnchor(abuse_tv.getText().toString());
                break;
            case R.id.negative_tv:
                requestReportAnchor(negative_tv.getText().toString());
                break;
            case R.id.ad_deceive_tv:
                requestReportAnchor(ad_deceive_tv.getText().toString());
                break;
                default:
                    break;
        }
    }

    private void requestReportAnchor(String content) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorMemberId",liveId);
        data.put("content",content);
        HttpApiUtils.CpRequest((Activity) context,fragment, RequestUtil.REPORT_ANCHOR, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ToastUtil.showToast(Utils.getString(R.string.感谢您的举报我们会尽快核实处理));
                ReportListPop.this.dismiss();
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }
}
