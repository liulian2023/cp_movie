package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;

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

}

