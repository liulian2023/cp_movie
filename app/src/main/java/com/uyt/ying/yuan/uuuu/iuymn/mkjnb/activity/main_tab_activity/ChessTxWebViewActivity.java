package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.QuotaChangeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.FloatingDraftButton;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.AnimationUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class ChessTxWebViewActivity extends BaseActivity {
  /*  @BindView(R.id.chess_webView)
    WebView chess_webView;*/
    @BindView(R.id.floatingActionButton)
    FloatingDraftButton floatingDraftButton;

    @BindView(R.id.chess_refresh_iv)
    ImageView chess_refresh_iv;
    @BindView(R.id.chess_return_iv)
    ImageView chess_return_iv;
    @BindView(R.id.chess_change_quota_iv)
    ImageView chess_change_quota_iv;
    @BindView(R.id.rotation_iv)
    ImageView rotation_iv;
    @BindView(R.id.chess_webView_linear)
    LinearLayout chess_webView_linear;
    WebView chess_webView;
    String url;
    int game;
    String id;
    CommonTipPop commonTipPop;
    static int TO_QUOTA_CHANGE=1;
    private String TAG = "ChessTxWebViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_tx_web_view);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForWindow(this);
        getIntentData();
        chess_webView=  new WebView(this);
        chess_webView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        chess_webView_linear.addView(chess_webView);
        initWebView();
        floatingDraftButton.registerButton(chess_refresh_iv);
        floatingDraftButton.registerButton(chess_return_iv);
        floatingDraftButton.registerButton(chess_change_quota_iv);
        floatingDraftButton.registerButton(rotation_iv);
        GlideLoadViewUtil.LoadCircleViewRes(this,R.drawable.ic_launch_1,floatingDraftButton);
    }

    public static void startAty(Context context,String url,int game,String id){
        Intent intent = new Intent(context, ChessTxWebViewActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("game",game);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
    private void getIntentData() {
        url=getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");
        game = getIntent().getIntExtra("game",50);
    }

    private void initWebView() {
        WebSettings mWebSettings = chess_webView.getSettings();
        mWebSettings.setDefaultTextEncodingName("utf-8");

        //调用JS方法.
        mWebSettings.setJavaScriptEnabled(true);
       //设置 DOM Storage缓存 避免游戏加载不出画面
        mWebSettings.setDomStorageEnabled(true);
        //设置可以访问文件
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setLoadWithOverviewMode(true);//缩放至屏幕大小
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setUseWideViewPort(true); //设置加载进来的页面自适应手机屏幕（可缩放）
        //设置支持缩放
       /*


        mWebSettings.setUseWideViewPort(true); //将图片调整至适合Webview大小
        mWebSettings.setSupportZoom(true);//支持缩放

        mWebSettings.setLoadsImagesAutomatically(true);//支持自动加载图片

        //进行缓存
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置缓存模式
        mWebSettings.setAppCacheEnabled(true);//加载缓存
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/gamecache";
        mWebSettings.setAppCachePath(path);//设计缓存路径*/
//        chess_webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);//开启硬件加速


        chess_webView.setWebChromeClient(webChromeClient);
        chess_webView.setWebViewClient(webViewClient);
        chess_webView.loadUrl(url);

    }
    @OnClick({R.id.floatingActionButton,R.id.chess_change_quota_iv,R.id.chess_return_iv,R.id.chess_refresh_iv,R.id.rotation_iv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.floatingActionButton:
                //弹出动态Button
                AnimationUtil.slideButtons(ChessTxWebViewActivity.this,floatingDraftButton);
                break;
            case R.id.chess_change_quota_iv:
                AnimationUtil.slideButtons(ChessTxWebViewActivity.this,floatingDraftButton);
//                QuotaChangeActivity.startAty(ChessTxWebViewActivity.this);
                Intent intent = new Intent(ChessTxWebViewActivity.this, QuotaChangeActivity.class);
                startActivityForResult(intent,TO_QUOTA_CHANGE);
                break;
            case R.id.chess_return_iv:
                finish();
                break;
            case R.id.chess_refresh_iv:
                AnimationUtil.slideButtons(ChessTxWebViewActivity.this,floatingDraftButton);
                chess_webView.loadUrl(url);
                break;
            case R.id.rotation_iv:
                AnimationUtil.slideButtons(ChessTxWebViewActivity.this,floatingDraftButton);
                rotationWebView();
                break;
                default:
                    break;
        }
    }

    private void rotationWebView() {
        AlertDialog isExit = new AlertDialog.Builder(ChessTxWebViewActivity.this).create();
        isExit.setTitle(Utils.getString(R.string.温馨提示));
        isExit.setMessage(Utils.getString(R.string.此功能仅在无法正常游戏并提示旋转屏幕的情况使用若要恢复竖屏需要退出重进));
        isExit.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        isExit.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.立即旋转), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chess_webView.evaluateJavascript(" var width = window.innerWidth,\n" +
                        "        height = window.innerHeight,\n" +
                        "        htmlWrapper = document.documentElement,\n" +
                        "        style = \"\";\n" +
                        "    if (width >= height) { \n" +
                        "        style += \"width:\" + width + \"px;\";  \n" +
                        "        style += \"height:\" + height + \"px;\";\n" +
                        "        style += \"-webkit-transform: rotate(0); transform: rotate(0);\";\n" +
                        "        style += \"-webkit-transform-origin: \" + width / 2 + \"px \" + width / 2 + \"px;\";\n" +
                        "        style += \"transform-origin: \" + width / 2 + \"px \" + width / 2 + \"px;\";\n" +
                        "    } else { \n" +
                        "        style += \"width:\" + height + \"px;\";\n" +
                        "        style += \"height:\" + width + \"px;\";\n" +
                        "        style += \"-webkit-transform: rotate(90deg); transform: rotate(90deg);\";\n" +
                        "        \n" +
                        "        style += \"-webkit-transform-origin: \" + width / 2 + \"px \" + width / 2 + \"px;\";\n" +
                        "        style += \"transform-origin: \" + width / 2 + \"px \" + width / 2 + \"px;\";\n" +
                        "    }\n" +
                        "    htmlWrapper.style.cssText = style;\n" +
                        "    document.body.style.overflow='hidden'", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {

                    }
                });
            }
        });
        isExit.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(commonTipPop ==null){
                commonTipPop =new CommonTipPop(this,this,Utils.getString(R.string.退出),Utils.getString(R.string.确定要退出吗));
                commonTipPop.setOnClickLintener(new CommonTipPop.OnClickLintener() {
                    @Override
                    public void onSureClick(View view) {
                        commonTipPop.dismiss();
                        finish();
                    }

                });
            }
            commonTipPop.showAtLocation(chess_return_iv, Gravity.CENTER,0,0);
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }

    WebViewClient webViewClient = new WebViewClient(){

        /**
         * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            System.out.println("shouldOverrideUrlLoading :     "+url);
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
    protected void onDestroy() {
        super.onDestroy();
        if (chess_webView != null) {
            ViewParent parent = chess_webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(chess_webView);
            }
            chess_webView.stopLoading();
            chess_webView.getSettings().setJavaScriptEnabled(false);
            chess_webView.clearHistory();
            chess_webView.clearView();
            chess_webView.removeAllViews();
            chess_webView.destroy();
            chess_webView=null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TO_QUOTA_CHANGE&&resultCode==RESULT_OK){
            HashMap<String, Object> map = new HashMap<>();
            map.put("game",game);
            map.put("id",id);
            map.put("loginType",1);
            HttpApiUtils.CPnormalRequest(this,null, RequestUtil.PLAY_CHESS, map, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    Utils.logE(TAG, Utils.getString(R.string.刷新棋牌余额成功) );
                }

                @Override
                public void onFailed(String msg) {
                }
            });
        }
    }



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
