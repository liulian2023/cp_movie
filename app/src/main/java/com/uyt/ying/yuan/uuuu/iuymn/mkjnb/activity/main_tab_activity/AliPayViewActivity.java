package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AliPayViewActivity extends BaseActivity {
    @BindView(R.id.chess_webView)
    WebView chess_webView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbar_right_tv;
    String windowLocationHref;
    String htmlStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_web_view);
        ButterKnife.bind(this);
//        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string. 充值));
        getIntentData();
        initView();
        initWebView();
        mProgressBar.setMax(100);
    }
    public static void startAty(Context context,String windowLocationHref,String htmlStr){
        Intent intent = new Intent(context, AliPayViewActivity.class);
        intent.putExtra("windowLocationHref",windowLocationHref);
        intent.putExtra("htmlStr",htmlStr);
        context.startActivity(intent);
    }
    private void getIntentData() {
        windowLocationHref=getIntent().getStringExtra("windowLocationHref");
        htmlStr=getIntent().getStringExtra("htmlStr");
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
        if(StringMyUtil.isNotEmpty(windowLocationHref)){
            chess_webView.loadUrl(windowLocationHref);
        }else {
            chess_webView.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+htmlStr,"text/html", "utf-8",null);
        }

        chess_webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_BACK && chess_webView.canGoBack()){
                        //表示按返回键时的操作
                        chess_webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //TODO 弹窗退出
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
/*        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }*/

        @Override
        public void onLoadResource(WebView view, String url) {
            if(url.startsWith("alipays")){
//                chess_webView.stopLoading();
//                RouteUtils.openBrowser(AliPayViewActivity.this,testStr);
                String testStr = Utils.checkImageUrl("zhifu/zfb.html?") + Uri.encode(url);
                RouteUtils.openBrowser(AliPayViewActivity.this,testStr);
                finish();
/*                if(isAvilible("com.eg.android.AlipayGphone", AliPayViewActivity.this)){
                    startOtherActivity(url);
                }else {
                    showToast(Utils.getString(R.string.未安装支付宝));
                }*/
            }
            if(url.startsWith("weixin")){
//                chess_webView.stopLoading();
                String testStr = Utils.checkImageUrl("zhifu/wx.html?") + Uri.encode(url);
                RouteUtils.openBrowser(AliPayViewActivity.this,testStr);
/*                if(isAvilible("com.tencent.mm", AliPayViewActivity.this)){
                    startOtherActivity(url);
                }else {
                    showToast(Utils.getString(R.string.未安装微信));
                }*/
                finish();
            }
//                                    super.onLoadResource(view, url);
        }

    };

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            }else {
                mProgressBar.setVisibility(View.VISIBLE);

            }
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
    private void startOtherActivity(String url) {
        Intent intent;
        try {
            intent = Intent.parseUri(url,
                    Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
