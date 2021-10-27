package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import pl.droidsonroids.gif.GifImageView;

public class SlideWebViewPop extends BasePopupWindow2 {
    ImageView slide_close_iv;
    WebView slide_webView;
    GifImageView loading_iv;
    String  frontUrl ;
    ProgressBar mProgressBar;
    ImageView toolbar_back_iv;
    TextView toolbar_title_tv;

    boolean manualDismiss =false;//是否是用户手动点击左上角的关闭按钮dismiss
    public enum Poptype{
        REGISTER,
        LOGIN,
        SMS;
    }
    Poptype poptype;
    public SlideWebViewPop(Context context, boolean focusable,Poptype poptype) {
        super(context, focusable);
        this.poptype=poptype;
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(mContext,R.color.white));
        this.setBackgroundDrawable(dw);
        initWebView();
        initUrl();
    }

    /**
     * webview url 相关初始化
     */
    private void initUrl() {
        frontUrl= SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.FRONT_URL, "");
        if(StringMyUtil.isNotEmpty(frontUrl)){
            String url="";
            // 加载业务页面。
            url = switchUrl(frontUrl, url);
//            slide_webView.loadUrl(url);
            slide_webView.loadUrl(url);
        }else {
            initFrontUrl();
        }
    }


    private void initWebView() {
        // 设置屏幕自适应。
        slide_webView.getSettings().setUseWideViewPort(true);
        slide_webView.getSettings().setLoadWithOverviewMode(true);
        // 建议禁止缓存加载，以确保在攻击发生时可快速获取最新的滑动验证组件进行对抗。
        slide_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置不使用默认浏览器，而直接使用WebView组件加载页面。
        slide_webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mProgressBar.setMax(100);
        slide_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if(newProgress==100){
                    loading_iv.setVisibility(View.GONE);
                    slide_webView.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        // 设置WebView组件支持加载JavaScript。
        slide_webView.getSettings().setJavaScriptEnabled(true);
        // 建立JavaScript调用Java接口的桥梁。
        slide_webView.addJavascriptInterface(new JsBridge(), "jsBridge");
    }

    /*
获取前端域名
*/
    private void initFrontUrl() {
        HashMap<String, Object> data = new HashMap<>();
        Utils.docking(data, RequestUtil.REQUEST_10004, 0, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                String frontUrl = jsonObject.getString(CommonStr.FRONT_URL);
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.FRONT_URL,frontUrl);
                String loadUrl="";

                loadUrl = switchUrl(frontUrl, loadUrl);
                slide_webView.loadUrl(loadUrl);
                try {
                    URL url = new URL(frontUrl);
                    String protocol = url.getProtocol();
                    String host = url.getHost();
                    String domain=protocol+"://"+host;
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.DOMAIN,domain);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(MessageHead messageHead) {
            }
        });
    }

    @NotNull
    private String switchUrl(String frontUrl, String loadUrl) {
        String initUrl = frontUrl.endsWith("/") ? frontUrl : frontUrl + "/";
        switch (poptype) {
            case REGISTER:
                loadUrl = initUrl + "huadong/androidRegisterVerify.html";
                break;
            case LOGIN:
                loadUrl = initUrl + "huadong/androidLoginVerify.html";
                break;
            case SMS:
                loadUrl = initUrl + "huadong/androidSmsVerify.html";
                break;
            default:
                break;
        }
        return loadUrl;
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.slide_web_view_pop_layout,null);
        slide_close_iv = rootView.findViewById(R.id.slide_close_iv);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        toolbar_back_iv = rootView.findViewById(R.id.toolbar_back_iv);
        toolbar_title_tv = rootView.findViewById(R.id.toolbar_title_tv);
        toolbar_title_tv.setText(Utils.getString(R.string.滑动验证));
        slide_webView = rootView.findViewById(R.id.slide_webView);
        loading_iv = rootView.findViewById(R.id.loading_iv);
        slide_close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualDismiss=true;
                dismiss();
            }
        });
        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualDismiss=true;
                dismiss();
            }
        });


    }
    public class JsBridge  {
        @JavascriptInterface
        public void getData(String callData) {
            if(StringMyUtil.isNotEmpty(callData)){
                JSONObject jsonObject = JSONObject.parseObject(callData);
                String tencent_randstr = jsonObject.getString("tencent_randstr");
                String tencent_ticket = jsonObject.getString("tencent_ticket");
                if(slideInterface!=null){
                    slideInterface.onSlideListener(tencent_randstr,tencent_ticket);
                }
            }

//            dismiss();
        }
    }

    public interface  SlideInterface{
        void onSlideListener(String tencent_randstr,String tencent_ticket);
    }
    SlideInterface slideInterface;

    public void setSlideInterface(SlideInterface slideInterface) {
        this.slideInterface = slideInterface;
    }

    public boolean isManualDismiss() {
        return manualDismiss;
    }


}
