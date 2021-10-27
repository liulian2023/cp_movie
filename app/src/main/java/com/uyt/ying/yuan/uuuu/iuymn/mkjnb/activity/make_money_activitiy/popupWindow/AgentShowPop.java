package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.enum_pakege.PopType;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

public class AgentShowPop extends PopupWindow {
    Activity activity;
    private View view;
    private TextView contentTv;
    private TextView knowTv;
    private TextView titleTv;
    PopType popType;
    int type;
    public AgentShowPop(Context context, Activity activity, PopType popType) {
        super(context);
        this.activity = activity;
        this.popType = popType;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.agent_show_pop, null);
        initView();
        initPopWindow();
    }

    private void initPopWindow() {
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(activity,1f);
            }
        });
    }

    private void initView() {
        contentTv= view.findViewById(R.id.agent_show_content_tv);
        knowTv= view.findViewById(R.id.i_know_tv);
        titleTv=view.findViewById(R.id.title_iv);
        switch (popType){
            case RULE:
                titleTv.setText(Utils.getString(R.string.邀请活动规则));
                getdata(8);
                break;
            case FRIEMD:
                titleTv.setText(Utils.getString(R.string.有效邀请说明));
                getdata(9);
                break;
                default:
                    break;
        }
        knowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgentShowPop.this.dismiss();
            }
        });

    }

    private void getdata(int type) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type",type);
        Utils.docking(data, RequestUtil.REQUEST_28rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject promptWords = jsonObject.getJSONObject("promptWords");
                String content1 = promptWords.getString("content");
//                contentTv.setText(content1);
                contentTv.setText(Html.fromHtml(content1));
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }
}
