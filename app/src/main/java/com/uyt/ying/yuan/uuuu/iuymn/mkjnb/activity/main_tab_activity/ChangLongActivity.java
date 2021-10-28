package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.uyt.ying.yuan.R;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.MineBetAcitivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.io.File;


/*
长龙助手  在线客服 互动大厅 webView跳转页面
 */
public class ChangLongActivity extends BaseActivity {
    private WebView webView;
    private static final String TAG = "LongDragonActivity";
    private boolean toChangLong;
    private boolean toHuDong;
    private boolean toKeFu;
    private String type_id;
    private String typename;
    private String game;
    private ValueCallback<Uri> mUploadMessage;
    private boolean videoFlag = false;
    private ProgressBar mProgressBar;

    Context mContext;
    private static final int REQUEST_CODE_ALBUM = 0x01;
    private static final int REQUEST_CODE_CAMERA = 0x02;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 0x03;
    WebView mWebView;
//    ProgressBar mProgressBar;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private String mCurrentPhotoPath;
    private Thread mThread;
    private String mLastPhothPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_long);
        bindView();
        Intent intent = getIntent();
        toChangLong = intent.getBooleanExtra("toChangLong", false);
        toHuDong = intent.getBooleanExtra("toHuDong", false);
        toKeFu = intent.getBooleanExtra("toKeFu", false);

    }

    @Override
    protected void init() {

    }

    @Override
    protected void onResume() {
        initWebView();
        super.onResume();
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        String frontUrl = SharePreferencesUtil.getString(this, "frontUrl", "");
        String deviceCode = SharePreferencesUtil.getString(this, "deviceCode", "");
        String token = SharePreferencesUtil.getString(this, "token", "");
        if(toChangLong){//跳转长龙助手
        webView.loadUrl(frontUrl+"home/getReward?user_id="+user_id+"&deviceCode="+deviceCode+"&token="+token+"&type="+"8");
        }
        else if(toHuDong){//跳转互动大厅
            webView.loadUrl(frontUrl+"home/getReward?user_id="+user_id+"&deviceCode="+deviceCode+"&token="+token+"&type="+"5");
        }
        else if(toKeFu){//跳转在线客服
            String onlineCustomer = SharePreferencesUtil.getString(ChangLongActivity.this, "onlineCustomer", "");
            webView.loadUrl(onlineCustomer);
//            webView.loadUrl("file:///android_asset/upload.html");
        }
        //自定义WebChromeClient, 辅助webView进行打开相册或调用相机的操作
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
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

        });
        /*
        webView 监听相关
         */
        webView.setWebViewClient(new WebViewClient(){
            /*
              使用原生浏览器访问网页(解决调用外部浏览器的问题)
               */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url)) {
                    videoFlag = url.contains("vedio");
                }
                webView.loadUrl(url);
                return true;
            }
                /*
            网页开始加载
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (ChangLongActivity.this!=null&&!ChangLongActivity.this.isFinishing()) {
                ProgressDialogUtil.show(ChangLongActivity.this);
                }
            }
                   /*
            网页结束加载
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (ChangLongActivity.this!=null&&!ChangLongActivity.this.isFinishing()) {
                    ProgressDialogUtil.stop(ChangLongActivity.this);
                }

            }
        /*
        监听webView每次加载的url
         */
            @Override
            public void onLoadResource(WebView view, String url) {
                if(url.equals("1")){//h5按下返回键是回到h5的首页,app端应该回到mainActivity,判断url中是否包含h5首页的字符,监听到后结束当前activity,返回到mainAvtivity
                    webView.stopLoading();
                    finish();
                }
                if(url.contains("7")){
                    showToast(Utils.getString(R.string.账户已过期或账号被冻结请联系客服处理));
                    LoginIntentUtil.toLogin(ChangLongActivity.this);
                    finish();
                }
                if(url.contains("5")){//点击下方的更多投注记录
                    startActivity(new Intent(ChangLongActivity.this,MineBetAcitivity.class));
                    webView.stopLoading();
                }
                super.onLoadResource(view, url);
            }

        });
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

    private void bindView() {
        webView=findViewById(R.id.changlong_webview);
        mProgressBar=findViewById(R.id.progressBar);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK&&webView.canGoBack()) ) {
                webView.goBack(); //goBack()表示返回WebView的上一页面
                return true;
        }else {
            finish();
            return true;
        }
    }
    @Override
    protected void onStop() {
       ProgressDialogUtil.stop(ChangLongActivity.this);
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }

            webView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            try {
                webView.destroy();
            } catch (Throwable ex) {

            }
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
