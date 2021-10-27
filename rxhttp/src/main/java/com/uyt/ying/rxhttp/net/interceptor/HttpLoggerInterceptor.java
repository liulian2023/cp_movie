package com.uyt.ying.rxhttp.net.interceptor;

import com.uyt.ying.rxhttp.net.utils.Utils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * created  by xxxx on 2019/9/12.
 */
public class HttpLoggerInterceptor{
    public static HttpLoggingInterceptor getLoggerInterceptor() {
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
                Utils.logE("HttpLogger-->", message.toString());
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}
