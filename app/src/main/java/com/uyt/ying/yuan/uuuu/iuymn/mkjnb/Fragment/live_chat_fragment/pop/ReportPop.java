package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop;

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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

import okhttp3.Headers;

public class ReportPop extends PopupWindow implements View.OnClickListener {
    Fragment fragment;
    String liveId;//主播id
    TextView report_tv;
    TextView block_tv;
    TextView report_cancel_tv;
    View v;
    Context context;
    boolean isSuperManager;
    public ReportPop(Context context, Fragment fragment, String liveId,boolean isSuperManager) {
        super(context);
        this.context = context;
        this.fragment = fragment;
        this.liveId = liveId;
        this.isSuperManager = isSuperManager;
         v = LayoutInflater.from(fragment.getContext()).inflate(R.layout.roport_live_pop_layout, null);
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
        report_cancel_tv=v.findViewById(R.id.report_cancel_tv);
        report_tv=v.findViewById(R.id.report_tv);
        block_tv=v.findViewById(R.id.block_tv);
        if(isSuperManager){
            block_tv.setVisibility(View.VISIBLE);
        }else {
            block_tv.setVisibility(View.GONE);

        }
        report_tv.setOnClickListener(this);
        block_tv.setOnClickListener(this);
        report_cancel_tv.setOnClickListener(this);
    }

    private void requestBlockUser() {
        if(fragment instanceof LiveFragment){
            LiveFragment liveFragment = (LiveFragment) fragment;
            String anchorAccount = liveFragment.getmLiveData().getAnchorAccount();
            HashMap<String, Object> data = new HashMap<>();
            data.put("killedAnchorAccount",anchorAccount);
            data.put("maintainStatus","1");
            data.put("liveRoomId",liveFragment.roomId);
            HttpApiUtils.CpRequest(fragment.getActivity(), fragment, RequestUtil.BLOCK_USER, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    ToastUtil.showToast(Utils.getString(R.string.封号成功));
                    dismiss();
                }

                @Override
                public void onFailed(String msg) {

                }
            });
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.report_cancel_tv:
               ReportPop.this.dismiss();
                break;
            case R.id.report_tv:
                new ReportListPop(context,fragment,liveId);
                ReportPop.this.dismiss();
                break;
            case R.id.block_tv:
                requestBlockUser();
                break;
                default:
                    break;
        }
    }
}
