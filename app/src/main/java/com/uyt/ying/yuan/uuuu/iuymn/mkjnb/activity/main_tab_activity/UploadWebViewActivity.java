package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import butterknife.BindView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;

public class UploadWebViewActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    //加载路径
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_web_view);
        initWebView();
    }

    private void initWebView() {
            url = getIntent().getStringExtra("url");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);//设置存储 不设置显示不出来
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没有网使用缓存
        settings.setJavaScriptEnabled(true);//设置网页可操作
        settings.setUseWideViewPort(true);//设置视图可缩放和下面一起设置
        settings.setLoadWithOverviewMode(true);
        webView.setDownloadListener(new MyDownLoad());
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String requestUrl = request.getUrl().toString();
                if (requestUrl == null) return false;
                if (requestUrl.startsWith("http:") || requestUrl.startsWith("https:") ){
                    view.loadUrl(requestUrl);
                    return false;
                }else{
                    try{
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(requestUrl));
                        startActivity(intent);
                    }catch (Exception e){
//                    ToastUtils.showShort(Utils.getString(R.string.暂无应用打开此链接));
                    }
                    return true;
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==100){
                    if(progressBar!=null){
                        progressBar.setVisibility(View.GONE);
                    }
                }else {
                    if(progressBar!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setProgress(newProgress);
                    }
                }
            }
        });
    }

    @Override
    protected void init() {

    }

    public static void startActivity(Context context, String url){
        Intent intent = new Intent(context, UploadWebViewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
            if (webView.canGoBack())
            {
                webView.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            }else
            {
                finish();
                return true;
            }

        }
        return false;
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    class MyDownLoad implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength) {
            if (url.endsWith(".apk")) {
                /**
                 * 通过系统下载apk
                 */
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        }
    }

}
