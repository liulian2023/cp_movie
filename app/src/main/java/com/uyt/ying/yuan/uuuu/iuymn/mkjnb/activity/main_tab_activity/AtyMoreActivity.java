package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;

/*
活动详情页面
 */
public class AtyMoreActivity extends BaseActivity {
    private WebView webView;
    private TextView back;
    private TextView actionBarCenter;
    private String content;
    private String title;
    private String image;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_more);
        Intent intent = getIntent();
        content = intent.getStringExtra("content");
        title = intent.getStringExtra("title");
        image = intent.getStringExtra("image");
        initView();
        initWebview();
    }

    @Override
    protected void init() {

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initWebview() {

//        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
//        String frontUrl = SharePreferencesUtil.getString(this, "frontUrl", "");
//        String deviceCode = SharePreferencesUtil.getString(this, "deviceCode", "");
//        String token = SharePreferencesUtil.getString(this, "token", "");
        actionBarCenter.setText(title);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //加入图片自适应
        webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+content,"text/html", "utf-8",null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            //页面开始加载时显示进度条
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                            PopupWindowUtil.showShareWindow(AtyMoreActivity.this);
                ProgressDialogUtil.show(AtyMoreActivity.this, "loading", false);
                super.onPageStarted(view, url, favicon);
            }

            //页面结束加载时隐藏进度条
            @Override
            public void onPageFinished(WebView view, String url) {
//                            PopupWindowUtil.disMiss(AtyMoreActivity.this);
                ProgressDialogUtil.stop(AtyMoreActivity.this);
                super.onPageFinished(view, url);
            }
        });
    }

    public void initView() {
        back = findViewById(R.id.action_bar_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        actionBarCenter = findViewById(R.id.action_bar_text);
        webView = findViewById(R.id.win_or_lose_webview);
        imageView=findViewById(R.id.aty_recycleview_image);
        Glide.with(this)
                .load(image)
                .into(imageView);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
