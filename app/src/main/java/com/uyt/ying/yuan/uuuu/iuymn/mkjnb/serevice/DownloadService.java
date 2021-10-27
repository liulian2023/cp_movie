package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.serevice;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.VersionUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.version_update.DownloadListener;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.version_update.DownloadTask;

import java.io.File;

/*
整篇没有用通知, 下载进度等处理都是直接回调到fregment中处理
 */
public class DownloadService extends Service {
    private DownloadTask downloadTask;
    private String downloadUrl;

    /**
     * 将DownloadTask中的回调结果回调到fragemnt中
     */
    private DownloadListener listener = new DownloadListener() {

        @Override
        public void onProgress(long progress, long downloadedLength, long contentLength) {
//            getNotificationManager().notify(1, getNotification(Utils.getString(R.string.下载中),progress));
            if(onDownLoadLintener!=null){
                onDownLoadLintener.onProgress(progress,downloadedLength,contentLength);
            }

        }
        @Override
        public void onSuccess(String filePath) {
            downloadTask = null;
            // 下载成功时将前台服务通知关闭，将结果回调
            stopForeground(true);
//            getNotificationManager().notify(1, getNotification(Utils.getString(R.string.下载完成),
//                    -1));
            if(onDownLoadLintener!=null){
                onDownLoadLintener.onSuccess(filePath);
            }
        }

        @Override
        public void onFailed() {
            downloadTask = null;
// 下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
//            getNotificationManager().notify(1, getNotification(Utils.getString(R.string.下载失败),
//                    -1));
            if(onDownLoadLintener!=null){
                onDownLoadLintener.onFailed();
            }
            Utils.logE("DownloadService", Utils.getString(R.string.onFailed:下载失败));
            ToastUtil.showToast(Utils.getString(R.string.更新包下载失败,请重新下载));
        }

        @Override
        public void onPaused(long downloadedLength, long contentLength) {
            downloadTask = null;
            if(onDownLoadLintener!=null){
                onDownLoadLintener.onPaused(downloadedLength,contentLength);
            }
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            if(onDownLoadLintener!=null){
                onDownLoadLintener.onCanceled();
            }
//            stopForeground(true);
            ToastUtil.showToast("Canceled");
        }
    };
    private DownloadBinder mBinder = new DownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class DownloadBinder extends Binder {
        //提供方法得到当前DownloadService对象,用于得到onDownLoadLintener回调
        public DownloadService getService(){
            return DownloadService.this;
        }
        //开始下载时,调用downloadTask.execute()方法,随后downloadTask中的一系列方法都会得到执行,
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
//                startForeground(1, getNotification("Downloading...", 0));
            }
        }
        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }
        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            } else {
                if (downloadUrl != null) {
// 取消下载时需将文件删除，并将通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.
                            lastIndexOf("/"));
                    fileName= VersionUtils.getAppVersionCode(MyApplication.getInstance())+""+fileName.substring(1);
                    String directory = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory+"/" + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
//                    getNotificationManager().cancel(1);
//                    stopForeground(true);
                }
            }
        }
    }
    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

//    private Notification getNotification(String title, int progress) {
//        String channeId = "1";
//        String channelName = "default";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channeId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
//            channel.enableLights(true);         // 开启指示灯，如果设备有的话
//            channel.setLightColor(Color.RED);   // 设置指示灯颜色
//            channel.setShowBadge(true);         // 检测是否显示角标
//            getNotificationManager().createNotificationChannel(channel);
//            Intent intent = new Intent(this, MainActivity.class);
//            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"1");
//            builder.setSmallIcon(R.mipmap.ic_launchers);//设置小图标
//            builder.setContentTitle(title);//标题
//            builder.setContentText(Utils.getString(R.string.正在下载!));//内容
//            builder.setWhen(System.currentTimeMillis());    //时间
//
////            builder.setContentIntent(pi);//设置点击后跳转的页面
////                builder.setAutoCancel(true);//设置点击后自动消除  (也可以根据notify中传入的id 手动清除   notificationManager.cancel(id); )
//            //3.获取通知
//            Notification notification = builder.build();
//
//            //4.发送通知
//            getNotificationManager().notify(100, notification);        if (progress >= 0) {
//// 当progress大于或等于0时才需显示下载进度
//                builder.setContentText(progress + "%");
//                builder.setProgress(100, progress, false);
//            }
//            return builder.build();
//        }
//            return null;
//    }
  public   DownloadListener onDownLoadLintener =null;

    public void setOnDownLoadLintener(DownloadListener onDownLoadLintener) {
        this.onDownLoadLintener = onDownLoadLintener;
    }
}
