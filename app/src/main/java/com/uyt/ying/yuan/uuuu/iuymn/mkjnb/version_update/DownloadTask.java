package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.version_update;

import android.os.AsyncTask;
import android.os.Environment;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.VersionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * AsyncTask 三个参数
 * Params 执行AsyncTask时需要传入的参数 用于在后台中使用
 * Progress 后台任务执行执行的进度 (这里将已下载大小也一起传入了)
 * Result 后台任务执行完毕后,返回的值 (onPostExecute中可以根据返回值做对应的处理)
 */
public class DownloadTask extends AsyncTask<String, Long, Integer> {
    private static String TAG="DownloadTask";
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;
    private DownloadListener listener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private long lastProgress;
    private      File file = null;
    //文件总大小
    private long contentLength;
    //已下载文件大小
//    private  long downloadedLength ;
    //io流每次读取后的新下载大小(需要显示在pop上)
    private long newDownloadLength;

    /**
     *将listene放入构造参数,用于在回调中处理下载时的各种状态
     * @param listener
     */
    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    /**
     * 此方法所有操作都在子线程中执行, 用于做耗时操作
     * @param params  对应AsyncTask中params参数
     * @return 后台任务执行完毕后,返回的值 (onPostExecute中可以根据返回值做对应的处理,对应AsyncTask中result参数)
     */
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        try {
           // 记录已下载的文件长度
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            /*TODO 下载时的文件名前加上当前版本号(避免用户下载完成后没有安装,也没有删除安装包,所以下载文件一直存在,导致以后每次更新时,都会将此文件当成是断点续传的基础)
               每次更新版本时,versionCode必须加1
             */
            fileName= VersionUtils.getAppVersionCode(MyApplication.getInstance())+""+fileName.substring(1);
            String directory = Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + "/"+fileName);
            if (file.exists()) {
                Utils.deleteNormalFile(file);
//                downloadedLength = file.length();
            }
             contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILED;
            }/* else if (contentLength == downloadedLength) {
            // 已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }*/
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
            // 断点下载，指定从哪个字节开始下载
//                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
//                savedFile.seek(downloadedLength); // 跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if(isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                    // 计算已下载的百分比
                        //计算每次读取io后的下载文件大小
                         newDownloadLength = total /*+ downloadedLength*/;
                        long progress = (int) (newDownloadLength * 100 /
                                contentLength);
                        //更新进度,此方法调用后,onProgressUpdate()会马上得到执行
                        publishProgress(progress , newDownloadLength);
                        Utils.logE(TAG, "doInBackground: progress="+progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    /**
     *  后台任务中调用了publishProgress(Progress...) 后,此方法会马上执行
     * @param values 后台任务中publishProgress()方法传入的值(对应AsyncTask中Progress参数)
     */
    @Override
    protected void onProgressUpdate(Long... values) {
        //实时下载进度
        long progress = values[0];
        //实时下载大小
        long downloadLen=values[1];
        if (progress > lastProgress) {
            listener.onProgress(progress,downloadLen,contentLength);
            lastProgress = progress;
        }
    }

    /**
     * 后台任务return后,此方法得到执行
     * @param status 对应AsyncTask中的Result参数
     */
    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                listener.onSuccess(file.getAbsolutePath());
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused(newDownloadLength,contentLength);
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }
    public void pauseDownload() {
        isPaused = true;
    }
    public void cancelDownload() {
        isCanceled = true;
    }
    /*
    获取需要下载的文件大小
     */
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }
}
