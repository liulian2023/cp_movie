package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.want_to_invest_activitys;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class PayWebviewActivity extends AppCompatActivity {
        private WebView webView;
//        private ArrayList<String> keys;
//        private ArrayList<String> values;
        private String htmlContent;
        private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_webview);
//        keys = getIntent().getStringArrayListExtra("key");
//        values = getIntent().getStringArrayListExtra("values");
        htmlContent=getIntent().getStringExtra("htmlContent");


//
        bindView();
    }

    private void bindView() {
        webView=findViewById(R.id.pay_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        String frontUrl = SharePreferencesUtil.getString(this, "frontUrl", "");
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        String deviceCode = SharePreferencesUtil.getString(this, "deviceCode", "");
        String token = SharePreferencesUtil.getString(this, "token", "");
        url = frontUrl + "home/getReward?user_id=" + user_id + "&deviceCode=" + deviceCode + "&token=" + token+"&type="+14;
        /*
        解析json,将参数key value取出,拼接到url
         */
        try {
            JSONObject jsonObject = new JSONObject(htmlContent);
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value=jsonObject.getString(key);
                url=url+"&"+key+"="+value;
            }
            webView.loadUrl(url);
        } catch (JSONException e) {
            e.printStackTrace();
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
                if (PayWebviewActivity.this!=null&&!PayWebviewActivity.this.isFinishing()) {
                    ProgressDialogUtil.show(PayWebviewActivity.this);
                }
            }

            /*
     网页结束加载
      */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                PopupWindowUtil.disMiss(LongDragonActivity.this);
                if (PayWebviewActivity.this!=null&&!PayWebviewActivity.this.isFinishing()) {
                    ProgressDialogUtil.stop(PayWebviewActivity.this);
                }

            }
            /*
            监听webView每次加载的url(解决按下返回键,返回的是h5的首页问题)
             */
            @Override
            public void onLoadResource(WebView view, String url) {

                super.onLoadResource(view, url);
            }
        });
        /*
        webView返回键的处理,
         */
//        webView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
////                    Log.d(TAG, "---webView onKey-----" );
//                    //按返回键操作并且能回退网页
//                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
//                        //后退
////                        Log.d(TAG, "---webView onKey canG0back-----" );
//                        webView.goBack();
////                        webView.goForward(); //需要webview.canGoForward()返回为true
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
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

    /**
     * 重写onKeyDown，当浏览网页，WebView可以后退时执行后退操作。
     * false 执行安卓返回方法即webview返回上一页 true 表示h5处理返回事件，android端不再处理
     * @param
     * @param
     * @return
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Log.d(TAG, "---android onKeyDown -----" );
//            webView.evaluateJavascript("javascript:_Native_backListener()", new ValueCallback<String>() {
//                @Override
//                public void onReceiveValue(String value) {
//                    if ("false".equals(value) || "null".equals(value)) {
//                        Log.d(TAG, "---android onKeyDown  false or null-----" );
//                        if (webView.canGoBack()){
//                            webView.goBack();
//                            Log.d(TAG, "---android onKeyDown  false or null   canGoback-----" );
//                        }
//                    }else if ("true".equals(value)){
//                        // h5处理，客户端不做任何操作
//                        Log.d(TAG, "---android onKeyDown  true  -----" );
//                    }
//                }
//            });
//        }
//        return true;
//    }

//    //设置返回键动作（防止按返回键直接退出程序)
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        AudioManager audio = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                audio.adjustStreamVolume(
//                        AudioManager.STREAM_MUSIC,
//                        AudioManager.ADJUST_RAISE, // 增加音量
//                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
//                return true;
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                audio.adjustStreamVolume(
//                        AudioManager.STREAM_MUSIC,
//                        AudioManager.ADJUST_LOWER,//减少音量
//                        AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
//                return true;
//        }
//
//        //当webview不是处于第一页面时，返回上一个页面
//        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
//            webView.goBack();
//            return true;
//        } else {//当webview处于第一页面时,直接退出程序
//            finish();
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }




    @Override
    protected void onStop() {
        ProgressDialogUtil.stop(PayWebviewActivity.this);
        super.onStop();
    }



}
