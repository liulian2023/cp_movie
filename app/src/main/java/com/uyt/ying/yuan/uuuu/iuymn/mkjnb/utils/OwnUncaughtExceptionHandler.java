package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OwnUncaughtExceptionHandler  implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable ex) {
        StackTraceElement[] elements = ex.getStackTrace();

        StringBuilder reason =new StringBuilder(ex.toString());

        if (elements !=null && elements.length >0) {

            for (StackTraceElement element : elements) {

                reason.append("\n");

                reason.append(element.toString());

            }

        }

        Utils.logE(Utils.getString(R.string.全局异常), reason.toString());

//        android.os.Process.killProcess(android.os.Process.myPid());

    }


    /**
     * 保存异常信息到本地 Android/data/包名/files/Documents/exception/yyyyMMdd_app_exception.txt
     * @param context 上下文
     * @param errMsg 异常信息
     */
    private void saveException(Context context, String errMsg) {
        if (context == null || TextUtils.isEmpty(errMsg)) return;
        FileOutputStream fos = null;
        try {
            // 创建目录
            String dirPath = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + "/exception/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 根据当天日期来生成文件名
            String date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(new Date());
            // 创建文件
            File file = new File(dirPath, date + "_app_exception.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            fos = new FileOutputStream(file, true);
            fos.write(errMsg.getBytes());
            fos.write("\n".getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

