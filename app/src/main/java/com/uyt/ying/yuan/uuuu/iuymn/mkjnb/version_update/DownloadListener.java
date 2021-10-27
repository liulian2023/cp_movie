package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.version_update;

public interface DownloadListener {
    void onProgress(long progress, long downloadedLength, long contentLength);
    void onSuccess(String filePath);
    void onFailed();
    void onPaused(long downloadedLength, long contentLength);
    void onCanceled();
}
