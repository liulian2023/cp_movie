package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.MainActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineBetAcitivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.HttpUrlUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;

import java.util.Map;

public class ShoppingWebViewActivity extends BaseActivity {


        private WebView webView;
        private int type_id;
        private String isopenOffice;
        private String isStart;
        private int game;
        private String typename;
        private boolean fromKf; //用于onReStart()中,判断是否是从客服中心返回的
    //点击跳转客服中心后,返回此activity时,界面会停在跳转到客服中心前的界面(需要再按一次返回键才会重新加载页面)所以在onReStart()中判断如果是从客服中心返回的.代码控制按下一次返回键
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_web_view);
        Intent intent = getIntent();
        type_id = intent.getIntExtra("type_id",0);
        isopenOffice=intent.getStringExtra("isopenOffice");
        isStart=intent.getStringExtra("isStart");
        game=intent.getIntExtra("game",0);
        typename=intent.getStringExtra("typename");
        bindView();
        initWebView();
    }

    @Override
    protected void init() {

    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        String frontUrl = SharePreferencesUtil.getString(this, "frontUrl", "");
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        String deviceCode = SharePreferencesUtil.getString(this, "deviceCode", "");
        String token = SharePreferencesUtil.getString(this, "token", "");
        String url = frontUrl + "home/getGame?user_id=" + user_id + "&deviceCode=" + deviceCode + "&token=" + token + "&type_id=" + type_id + "&isopenOffice=" + isopenOffice + "&isStart=" + isStart + "&game=" + game + "&typename=" + typename;
        webView.loadUrl(frontUrl + "home/getGame?user_id=" + user_id + "&deviceCode=" + deviceCode + "&token=" + token + "&type_id=" + type_id + "&isopenOffice=" + isopenOffice + "&isStart=" + isStart + "&game=" + game + "&typename=" + typename);
        /*
        webView 监听相关
         */
        webView.setWebViewClient(new WebViewClient(){
            /*
              使用原生浏览器访问网页(解决调用外部浏览器的问题)
               在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
               这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，就可以不打开地址，取消这个操作，
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
                if (ShoppingWebViewActivity.this!=null&&!ShoppingWebViewActivity.this.isFinishing()) {
                    if(!fromKf){
                    ProgressDialogUtil.show(ShoppingWebViewActivity.this);
                    }
                }

            }
            /*
     网页结束加载
      */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (ShoppingWebViewActivity.this!=null&&!ShoppingWebViewActivity.this.isFinishing()) {
                    ProgressDialogUtil.stop(ShoppingWebViewActivity.this);
                }
            }
            /*
            监听webView每次加载的url
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                //  (解决按下返回键,返回h5首页的问题)
                if(url.equals("1")){//h5按下返回键是回到h5的首页,app端应该回到homeActivity,判断url中是否包含h5首页的字符,监听到后结束当前activity,返回到homeAvtivity
                    finish();//结束当前activity
                }
                if(url.contains("1")){
                    showToast(Utils.getString(R.string.账户已过期,或账号被冻结,请联系客服处理));
                    LoginIntentUtil.isLogin(ShoppingWebViewActivity.this);
                    finish();
                }
                //点击投注记录,跳转到原生投注记录页面(并调用stopLoading停止加载原网页,解决跳转activity后,仍然会请求h5投注记录的问题)
                else if (url.contains("1")) {
                        String urlDecoderString = HttpUrlUtil.getURLDecoderString(url);//对url进行解码(解决中文参数乱码问题)
                        Map<String, String> map = HttpUrlUtil.urlSplit(urlDecoderString);//得到url中的参数.并以key value形式保存在map中
                    /*
                    遍历map,并分别取出type_id typename game
                     */
                    String typeid = null;
                    String game1 = null;
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                            /*
                            分别取出type_id typename game的值
                             */
                        if(key.equals("type_id")){
                            typeid=entry.getValue();
                        }
                        if(key.equals("typename")) {
                            typename=entry.getValue();
                        }
                        if(key.equals("game")){
                            game1=entry.getValue();
                        }
//                        type_id + "&isopenOffice=" + isopenOffice + "&isStart=" + isStart + "&game=" + game + "&typename=" + typename

                    }
                        Intent intent = new Intent(ShoppingWebViewActivity.this, MineBetAcitivity.class);
                        intent.putExtra("fromShopping",typename);//跳转到投注记录页面后,用于筛选按钮旁的textView显示彩票名
                        intent.putExtra("type_id",typeid);//跳转到投注记录后,默认请求所点击的彩票投注记录时需要用到的参数
                        intent.putExtra("game",game1);//跳转到投注记录后,默认请求所点击的彩票投注记录时需要用到的参数
                        startActivity(intent);
                        webView.stopLoading();//停止加载原网页(关键代码)
                }
//                //点击交易中心,跳转到原生页面
//                else if(url.contains("https://xunbo678.com/myaccount/rechargeMode")){
//                    startActivity(new Intent(ShoppingWebViewActivity.this,WantToInvestActivity.class));
//                    webView.stopLoading();
//                }

                //点击会员中心.跳转原生界面
                else if(url.equals("1")){
                    Intent intent = new Intent(ShoppingWebViewActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("toMineFragment",true);
                    startActivity(intent);
                    webView.stopLoading();
                }
                //点击在线客服,跳转在线客服webView
                else if(url.equals("1")){
                    fromKf=true;
                    Intent intent = new Intent(ShoppingWebViewActivity.this, ChangLongActivity.class);
                    intent.putExtra("toKeFu",true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    webView.stopLoading();
                }
            }
        });
    }

    private void bindView() {
        webView=findViewById(R.id.webView);
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
    protected void onStop() {
        ProgressDialogUtil.stop(ShoppingWebViewActivity.this);
        super.onStop();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        if(fromKf){//从客服界面返回时,自动点击一次返回键(ProgressDialog会留在页面),所以在onPause()中再按一次返回键,让ProgressDialog disMiss
////            onKeyDown(KeyEvent.KEYCODE_BACK, null);
////            ProgressDialogUtil.stop(ShoppingWebViewActivity.this);
////                this.getWindow().getDecorView().performClick();//赛车类点击右侧的客服中心,返回后,会有一层暗幕,需要点击一次才能恢复亮度(前端说已经改好了....)
//        }
//        fromKf=false;
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(fromKf){//disMiss  ProgressDialog
//            onKeyDown(KeyEvent.KEYCODE_BACK, null);
//            ProgressDialogUtil.stop(ShoppingWebViewActivity.this);
//        }
//        fromKf=false;
//    }

}
