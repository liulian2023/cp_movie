///*
// * Copyright (c) 2019.  xxxx
// */
//
//package com.cambodia.zhanbang.xunbo.utils;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Environment;
//import android.os.Parcelable;
//import android.provider.MediaStore;
//import android.text.format.DateFormat;
//import android.view.View;
//import android.webkit.ValueCallback;
//import android.webkit.WebChromeClient;
//import android.webkit.WebView;
//import android.widget.Toast;
//
//import java.io.File;
//import java.util.Calendar;
//import java.util.Locale;
//
//public class MyWebChromeClient extends WebChromeClient {
//    private Activity mActivity;
//    private android.webkit.ValueCallback<Uri[]> mUploadCallbackAboveL;
//    private android.webkit.ValueCallback<Uri> mUploadCallbackBelow;
//    private Uri imageUri;
//    private int REQUEST_CODE = 1234;
//
//    public MyWebChromeClient(Activity mActivity) {
//        this.mActivity = mActivity;
//    }
//
//    @Override
//    public void onProgressChanged(WebView view, int newProgress) {
//
//    }
//
//    /**
//     * 8(Android 2.2) <= API <= 10(Android 2.3)回调此方法
//     */
//    private void openFileChooser(android.webkit.ValueCallback<Uri> uploadMsg) {
//        // (2)该方法回调时说明版本API < 21，此时将结果赋值给 mUploadCallbackBelow，使之 != null
//        mUploadCallbackBelow = uploadMsg;
//        takePhoto();
//    }
//
//    /**
//     * 11(Android 3.0) <= API <= 15(Android 4.0.3)回调此方法
//     */
//    public void openFileChooser(android.webkit.ValueCallback<Uri> uploadMsg, String acceptType) {
//        // 这里我们就不区分input的参数了，直接用拍照
//        openFileChooser(uploadMsg);
//    }
//
//    /**
//     * 16(Android 4.1.2) <= API <= 20(Android 4.4W.2)回调此方法
//     */
//    public void openFileChooser(android.webkit.ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
//        // 这里我们就不区分input的参数了，直接用拍照
//        openFileChooser(uploadMsg);
//    }
//
//    /**
//     * API >= 21(Android 5.0.1)回调此方法
//     */
//    @Override
//    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
//        // (1)该方法回调时说明版本API >= 21，此时将结果赋值给 mUploadCallbackAboveL，使之 != null
//        mUploadCallbackAboveL = valueCallback;
//        takePhoto();
//        return true;
//    }
//
//    /**
//     * 调用相机
//     */
//    private void takePhoto() {
//        // 指定拍照存储位置的方式调起相机
//        String filePath = Environment.getExternalStorageDirectory() + File.separator
//                + Environment.DIRECTORY_PICTURES + File.separator;
//        String fileName = "IMG_" + DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
//        imageUri = Uri.fromFile(new File(filePath + fileName));
//
////        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
////        startActivityForResult(intent, REQUEST_CODE);
//
//
//        // 选择图片（不包括相机拍照）,则不用成功后发刷新图库的广播
////        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
////        i.addCategory(Intent.CATEGORY_OPENABLE);
////        i.setType("image/*");
////        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_CODE);
//
//        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//
//        Intent Photo = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        Intent chooserIntent = Intent.createChooser(Photo, "Image Chooser");
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});
//
//        mActivity.startActivityForResult(chooserIntent, REQUEST_CODE);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE) {
//            // 经过上边(1)、(2)两个赋值操作，此处即可根据其值是否为空来决定采用哪种处理方法
//            if (mUploadCallbackBelow != null) {
//                chooseBelow(resultCode, data);
//            } else if (mUploadCallbackAboveL != null) {
//                chooseAbove(resultCode, data);
//            } else {
//            }
//        }
//    }
//
//}
//
