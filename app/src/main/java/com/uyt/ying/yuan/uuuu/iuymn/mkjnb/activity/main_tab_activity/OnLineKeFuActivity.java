/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uyt.ying.yuan.BuildConfig;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
  在线客服  webView跳转页面
 */
public class OnLineKeFuActivity extends BaseActivity {
    Context mContext;
    private static final int REQUEST_CODE_ALBUM = 0x01;
    private static final int REQUEST_CODE_CAMERA = 0x02;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 0x03;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private String mCurrentPhotoPath;
    private String mLastPhothPath;
    private Thread mThread;
    private boolean showFloat;
     @BindView(R.id.progressBar)
     ProgressBar mProgressBar;
     @BindView(R.id.toolbar_right_tv)
     TextView toolbar_right_tv;
     @BindView(R.id.webview)
     WebView mWebView;


    String kefuUrl; //服务器崩溃时在开屏图设置的临时在线客服地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_kefu);
        kefuUrl = getIntent().getStringExtra("kefuUrl");
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this, Utils.getString(R.string.在线客服));
        mContext = this;
        //进度条初始化
        initProgressBar();
        //webview初始化
        initWebView();
    }




    @Override
    protected void init() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread = null;
        mHandler = null;
    }


        private void initProgressBar() {
            mProgressBar.setMax(100);
        }

        private void initWebView() {

            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }


            });
            mWebView.setWebChromeClient(new WebChromeClient() {
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
        String onlineCustomer = SharePreferencesUtil.getString(OnLineKeFuActivity.this, "onlineCustomer", "");
        if(StringMyUtil.isNotEmpty(kefuUrl)){
            mWebView.loadUrl(kefuUrl);
        }else {

            mWebView.loadUrl(onlineCustomer);
        }

//        mWebView.loadUrl("file:///android_asset/upload.html");
//        mWebView.loadUrl("https://static.meiqia.com/dist/standalone.html?_=t&eid=120945");
    }


    @Override
    public void onBackPressedSupport() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(mWebView.canGoBack()){
                mWebView.goBack();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            takePhoto();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults == null && grantResults.length == 0) {
            return;
        }

        if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            } else {
                // Permission Denied
                new AlertDialog.Builder(mContext)
                        .setTitle(Utils.getString(R.string.无法拍照))
                        .setMessage(Utils.getString(R.string.您未授予拍照权限))
                        .setNegativeButton(Utils.getString(R.string.取消), null)
                        .setPositiveButton(Utils.getString(R.string.去设置), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent localIntent = new Intent();
                                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                                startActivity(localIntent);
                            }
                        }).create().show();
            }

        }

    }

    /**
     * 选择相机或者相册
     */
    public void uploadPicture() {
                chooseAlbumPic();
    }
    /**
     * 拍照
     */
    private void takePhoto() {

        StringBuilder fileName = new StringBuilder();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileName.append(UUID.randomUUID()).append("_upload.png");
        File tempFile = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mCurrentPhotoPath = tempFile.getAbsolutePath();
        startActivityForResult(intent, REQUEST_CODE_CAMERA);

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
    public void onNetChange(boolean netWorkState) {

    }

}
