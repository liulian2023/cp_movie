package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChessWebViewActivity extends BaseActivity {
    @BindView(R.id.chess_webView)
    WebView chess_webView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_web_view);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForWindow(this);
        getIntentData();
        initView();
        initWebView();
    }
    public static void startAty(Context context,String url){
        Intent intent = new Intent(context, ChessWebViewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    private void getIntentData() {
        url=getIntent().getStringExtra("url");
    }

    private void initWebView() {
        WebSettings mWebSettings = chess_webView.getSettings();
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setLoadsImagesAutomatically(true);

        //调用JS方法.
        mWebSettings.setJavaScriptEnabled(true);
        //设置 DOM Storage缓存 避免游戏加载不出画面
        mWebSettings.setDomStorageEnabled(true);

        chess_webView.setWebChromeClient(webChromeClient);
        chess_webView.setWebViewClient(webViewClient);
        chess_webView.loadUrl(url);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }

    private void initView() {
        chess_webView = findViewById(R.id.chess_webView);
    }

    WebViewClient webViewClient = new WebViewClient(){

        /**
         * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    };

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }


}
