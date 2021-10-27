package com.uyt.ying.rxhttp.net.interceptor;


import com.uyt.ying.rxhttp.net.utils.NetworkUtils;
import com.uyt.ying.rxhttp.net.utils.Utils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;

/**
 * created  by xxxx on 2019/9/11.
 * 配置缓存的拦截器
 */
public class HttpCacheInterceptor implements Interceptor {
    @Override
    @EverythingIsNonNull
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //没网强制从缓存读取
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Utils.logE("-->", "no network");
        }

        Response originalResponse = chain.proceed(request);

        if (NetworkUtils.isConnected()) {
            //有网的时候读接口上的@Headers里的配置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }
}