package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;

public class RulePop extends BasePopupWindow2 {
    WebView rule_webview;
    Button sure_btn;
    String fileName;
    String title;
    TextView rule_title_tv;
    public RulePop(Context context, boolean focusable, String fileName,String title) {
        super(context, focusable);
        this.fileName = fileName;
        this.title = title;
        rule_webview.loadUrl(fileName);
        rule_title_tv.setText(title);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.turntable_rule_pop_layout, null);
        rule_webview = rootView.findViewById(R.id.rule_webview);
        sure_btn = rootView.findViewById(R.id.sure_btn);
        rule_title_tv = rootView.findViewById(R.id.rule_title_tv);
        WebSettings settings = rule_webview.getSettings();
        settings.setJavaScriptEnabled(true);
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RulePop.this.dismiss();
            }
        });
    }
}
