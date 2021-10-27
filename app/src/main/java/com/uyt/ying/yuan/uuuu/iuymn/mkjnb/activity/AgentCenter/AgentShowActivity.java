package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.AgentCenter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;

public class AgentShowActivity extends BaseActivity implements View.OnClickListener {
    private TextView back;
    private TextView actionCenter;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_show);
        bindView();
        getdata();
        webView=findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings  .setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            //页面开始加载时显示进度条
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                            PopupWindowUtil.showShareWindow(AtyMoreActivity.this);
                ProgressDialogUtil.show(AgentShowActivity.this, "loading", false);
                super.onPageStarted(view, url, favicon);
            }

            //页面结束加载时隐藏进度条
            @Override
            public void onPageFinished(WebView view, String url) {
//                            PopupWindowUtil.disMiss(AtyMoreActivity.this);
                ProgressDialogUtil.stop(AgentShowActivity.this);
                super.onPageFinished(view, url);

            }
        });

    }

    private void getdata() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type",4);
        Utils.docking(data, RequestUtil.REQUEST_28rzq, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content)  {
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject promptWords = jsonObject.getJSONObject("promptWords");
                String content1 = promptWords.getString("content");
                //加入图片自适应
                webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+content1,"text/html", "utf-8",null);
            }

            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }

    @Override
    protected void init() {

    }

    private void bindView() {
        back=findViewById(R.id.action_bar_return);
        actionCenter=findViewById(R.id.action_bar_text);
        actionCenter.setText(Utils.getString(R.string.代理说明));
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_return:
                finish();
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
