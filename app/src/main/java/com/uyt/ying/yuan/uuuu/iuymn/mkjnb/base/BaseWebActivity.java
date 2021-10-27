package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseWebActivity extends BaseActivity {

    @BindView(R.id.base_webView)
    WebView base_webView;
    @BindView(R.id.web_progress)
    ProgressBar web_progress;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private String mCurrentPhotoPath;
    private Thread mThread;
    private String mLastPhothPath;
    private static final int REQUEST_CODE_ALBUM = 0x01;
    private static final int REQUEST_CODE_CAMERA = 0x02;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 0x03;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseweb);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        initWebView();
        CommonToolbarUtil.initToolbar(this,getToolBarTitle());
        doSomething();
    }

    @Override
    protected void init() {

    }

    public void doSomething(){

    }
    private void initWebView() {
       WebSettings mWebSettings = base_webView.getSettings();
       mWebSettings.setSupportZoom(true);

        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebSettings.setLoadWithOverviewMode(true);

        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setLoadsImagesAutomatically(true);

        //调用JS方法.
        mWebSettings.setJavaScriptEnabled(true);
        //设置 DOM Storage缓存 避免游戏加载不出画面
        mWebSettings.setDomStorageEnabled(true);

        base_webView.setWebChromeClient(webChromeClient);
        base_webView.setWebViewClient(webViewClient);
        String url = getUrl();
        if(StringMyUtil.isEmptyString(url)){
            base_webView.loadUrl(getAssetsPath());
        }else {
            if(url.startsWith("http")){
                base_webView.loadUrl(url);
            }else {
                //加载html代码时候,设置图片全屏
                String varjs = "<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>";
                base_webView.loadData(varjs + url, "text/html", "UTF-8");

            }
        }

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
            if(newProgress==100){
                if(web_progress!=null){
                    web_progress.setVisibility(View.GONE);
                }
            }else {
                if(web_progress!=null){
                    web_progress.setVisibility(View.VISIBLE);
                    web_progress.setProgress(newProgress);
                }
            }
        }
        //For Android  >= 5.0
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

            uploadMessageAboveL = filePathCallback;
            uploadPicture();
            return true;
        }


        //For Android  >= 4.1
        public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
            uploadMessage = valueCallback;
            uploadPicture();
        }
    };

    public String getUrl(){
        return getIntent().getStringExtra("url");
    }
    public String getAssetsPath(){
        return getIntent().getStringExtra("assetPath");
    }
    public String getToolBarTitle(){
        return "";
}

    @Override
    protected void onDestroy() {
        if (base_webView != null) {
            ViewParent parent = base_webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(base_webView);
            }

            base_webView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            base_webView.getSettings().setJavaScriptEnabled(false);
            base_webView.clearHistory();
            base_webView.clearView();
            base_webView.removeAllViews();
            try {
                base_webView.destroy();
            } catch (Throwable ex) {

            }
        }
        super.onDestroy();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    public void uploadPicture() {
        chooseAlbumPic();
    }
    /**
     * 选择相册照片
     */
    private void chooseAlbumPic() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_CODE_ALBUM);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ALBUM || requestCode == REQUEST_CODE_CAMERA) {

            if (uploadMessage == null && uploadMessageAboveL == null) {
                return;
            }

            //取消拍照或者图片选择时
            if (resultCode != RESULT_OK) {
                //一定要返回null,否则<input file> 就是没有反应
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(null);
                    uploadMessageAboveL = null;

                }
            }

            //拍照成功和选取照片时
            if (resultCode == RESULT_OK) {
                Uri imageUri = null;

                switch (requestCode) {
                    case REQUEST_CODE_ALBUM:

                        if (data != null) {
                            imageUri = data.getData();
                        }

                        break;
                    case REQUEST_CODE_CAMERA:
                        if (!TextUtils.isEmpty(mCurrentPhotoPath)) {
                            File file = new File(mCurrentPhotoPath);
                            Uri localUri = Uri.fromFile(file);
                            Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
                            sendBroadcast(localIntent);
                            imageUri = Uri.fromFile(file);
                            mLastPhothPath = mCurrentPhotoPath;
                        }
                        break;
                }


                //上传文件
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(imageUri);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(new Uri[]{imageUri});
                    uploadMessageAboveL = null;

                }

            }

        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(base_webView.canGoBack()){
                base_webView.goBack();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
