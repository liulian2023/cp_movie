package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;
import android.content.Context;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;

public class ManualRechargeTipPop extends BasePopupWindow2 {
    WebView manual_recharge_pop_content_wbv;
    TextView manual_recharge_pop_sure_tv;
    TextView manual_recharge_pop_title_tv;
    String content;
    String fileName;
    String title;
    public ManualRechargeTipPop(Context context, boolean focusable,String content,String title) {
        super(context, focusable);
        this.content = content;
        this.title = title;
        initWebViewData();
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        manual_recharge_pop_title_tv.setText(title);
    }

    public ManualRechargeTipPop(Context context, String fileName,String title) {
        super(context, true);
        this.fileName = fileName;
        this.title = title;
        initWebViewData();
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        manual_recharge_pop_title_tv.setText(title);
    }
    private void initWebViewData() {
        if(StringMyUtil.isNotEmpty(content)){
            String wrapStr   = "<html> \n" +
                    "<head> \n" +
                    "<style type=\"text/css\"> \n" +
                    "body {font-size:13px;}\n" +
                    "</style> \n" +
                    "</head> \n" +
                    "<body>" +
                    "<script type='text/javascript'>" +
                    "window.onload = function(){\n" +
                    "var $img = document.getElementsByTagName('img');\n" +
                    "for(var p in  $img){\n" +
                    " $img[p].style.width = '100%%';\n" +
                    "$img[p].style.height ='auto'\n" +
                    "}\n" +
                    "}" +
                    "</script>" +
                    content
                    + "</body>" +
                    "</html>";
            manual_recharge_pop_content_wbv.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+wrapStr,"text/html", "utf-8",null);
        }else {
            manual_recharge_pop_content_wbv.loadUrl(fileName);
        }


    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.manual_recharge_pop_layout, null);
        manual_recharge_pop_content_wbv = rootView.findViewById(R.id.manual_recharge_pop_content_wbv);
        manual_recharge_pop_sure_tv = rootView.findViewById(R.id.manual_recharge_pop_sure_tv);
        manual_recharge_pop_title_tv = rootView.findViewById(R.id.manual_recharge_pop_title_tv);
        manual_recharge_pop_sure_tv.setOnClickListener(this);
        WebSettings settings = manual_recharge_pop_content_wbv.getSettings();
        settings.setJavaScriptEnabled(true);
    }

}
