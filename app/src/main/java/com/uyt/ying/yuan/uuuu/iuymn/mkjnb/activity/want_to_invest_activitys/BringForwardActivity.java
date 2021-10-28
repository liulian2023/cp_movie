package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.want_to_invest_activitys;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

/*
转账教程页面
 */
public class BringForwardActivity extends AppCompatActivity {
        private boolean fromWx;
        private boolean fromZfb;
        private TextView actionCenter;
        private TextView back;
        private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bring_forward);
        fromWx=getIntent().getBooleanExtra("wx",false);
        fromZfb=getIntent().getBooleanExtra("zfb",false);
        bindView();
        initView();
        initWebView();
    }

            private void initWebView() {
                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);

                Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
                String frontUrl = SharePreferencesUtil.getString(this, "frontUrl", "");
                String deviceCode = SharePreferencesUtil.getString(this, "deviceCode", "");
                String token = SharePreferencesUtil.getString(this, "token", "");

                if(fromWx){
                webView.loadUrl("4");
                }
                else  if(fromZfb){
                webView.loadUrl("4");
                }

         /*
        webView 监听相关
         */
                webView.setWebViewClient(new WebViewClient(){
                    /*
                      使用原生浏览器访问网页(解决调用外部浏览器的问题)
                       */
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        webView.loadUrl(url);
                        return true;
                    }
                    /*
                网页开始加载
                 */
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        if (BringForwardActivity.this!=null&&!BringForwardActivity.this.isFinishing()) {
                            ProgressDialogUtil.show(BringForwardActivity.this);
                        }
                    }

                    /*
             网页结束加载
              */
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
//                PopupWindowUtil.disMiss(LongDragonActivity.this);
                        if (BringForwardActivity.this!=null&&!BringForwardActivity.this.isFinishing()) {
                            ProgressDialogUtil.stop(BringForwardActivity.this);
                        }

                    }
                    /*
                    监听webView每次加载的url(解决按下返回键,返回的是h5的首页问题)
                     */
                    @Override
                    public void onLoadResource(WebView view, String url) {
                        if(url.equals("11")){//h5按下返回键是回到h5的首页,app端应该回到homeActivity,判断url中是否包含h5首页的字符,监听到后结束当前activity,返回到homeAvtivity
                            finish();
                        }
                        super.onLoadResource(view, url);
                    }
                });
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
            public void initView() {
                if(fromWx){
//                    imageView.setImageResource(R.drawable.);
                    actionCenter.setText(Utils.getString(R.string.微信转银行卡教程));
                }
                else if(fromZfb){
                    actionCenter.setText(Utils.getString(R.string.支付宝转银行卡教程));
                }
            }

            private void bindView() {

                actionCenter=findViewById(R.id.action_bar_text);
                back=findViewById(R.id.action_bar_return);
                webView=findViewById(R.id.zhuanzhang_webview);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }

    @Override
    protected void onStop() {
        ProgressDialogUtil.stop(BringForwardActivity.this);
        super.onStop();
    }
 }
