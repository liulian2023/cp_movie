package com.uyt.ying.rxhttp.net.common;

import android.text.TextUtils;


import com.uyt.ying.rxhttp.net.converter.GsonConverterFactory;
import com.uyt.ying.rxhttp.net.https.UnSafeTrustManager;
import com.uyt.ying.rxhttp.net.interceptor.HttpBaseUrlInterceptor;
import com.uyt.ying.rxhttp.net.interceptor.HttpCacheInterceptor;
import com.uyt.ying.rxhttp.net.interceptor.HttpHeaderInterceptor;
import com.uyt.ying.rxhttp.net.interceptor.HttpLoggerInterceptor;
import com.uyt.ying.rxhttp.net.utils.AppContextUtils;
import com.uyt.ying.rxhttp.net.utils.SSL;
import com.uyt.ying.rxhttp.net.utils.SSLSocketClient;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retrofit+RxJava网络请求封装
 */
public class RetrofitFactory {

    private static volatile CompositeDisposable mCompositeDisposable;
    private final Retrofit.Builder  build;
    private Retrofit retrofit;
   // public final CacheProvider cacheProvider;

    private RetrofitFactory() {

        // 指定缓存路径,缓存大小100Mb
        File cacheFile = new File(AppContextUtils.getContext().getCacheDir(), "HttpCache");
        File rxcacheFile = new File(AppContextUtils.getContext().getCacheDir(), "RxCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);


          OkHttpClient.Builder httpClientBuilder =null;
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
                if(sp.getAppVestFlag()==1){
                    httpClientBuilder=new OkHttpClient().newBuilder()
                            .readTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                            .connectTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                            .addInterceptor(HttpLoggerInterceptor.getLoggerInterceptor())
                            .addInterceptor(new HttpHeaderInterceptor())
                            .addNetworkInterceptor(new HttpCacheInterceptor())
                            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                            .sslSocketFactory(new SSL(trustAllCert), trustAllCert)
                            //允许失败重试
                            .retryOnConnectionFailure(true)
                            .cache(cache);
                }else {
                    httpClientBuilder = new OkHttpClient().newBuilder()
                            .readTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                            .connectTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                            .addInterceptor(HttpLoggerInterceptor.getLoggerInterceptor())
                            .addInterceptor(new HttpHeaderInterceptor())
                            .addNetworkInterceptor(new HttpCacheInterceptor())
                            .addInterceptor(new HttpBaseUrlInterceptor())
                            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                            .sslSocketFactory(new SSL(trustAllCert), trustAllCert)
                            //允许失败重试
                            .retryOnConnectionFailure(true)
                            .cache(cache);
                }

/*        if (ApiConfig.getOpenHttps()) {
            httpClientBuilder.sslSocketFactory(1 == ApiConfig.getSslSocketConfigure().getVerifyType() ?
                    SslSocketFactory.getSSLSocketFactory(ApiConfig.getSslSocketConfigure().getCertificateInputStream()) :
                    SslSocketFactory.getSSLSocketFactory(), new UnSafeTrustManager());
            httpClientBuilder.hostnameVerifier(new UnSafeHostnameVerify());
        }*/

        OkHttpClient httpClient = httpClientBuilder.build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .registerTypeAdapterFactory(new NullTypeAdapterFactory())
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        build = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        if (!TextUtils.isEmpty(ApiConfig.getBaseUrl2())) {
            retrofit = build.baseUrl(ApiConfig.getBaseUrl2()).build();
        }

//        cacheProvider = new RxCache.Builder()
//                .persistence(rxcacheFile, new GsonSpeaker())
//                .using(CacheProvider.class);

    }


    private RetrofitFactory(long timeout) {

        // 指定缓存路径,缓存大小100Mb
        File cacheFile = new File(AppContextUtils.getContext().getCacheDir(), "HttpCache");
        File rxcacheFile = new File(AppContextUtils.getContext().getCacheDir(), "RxCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder()
                .readTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .addInterceptor(HttpLoggerInterceptor.getLoggerInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                //允许失败重试
                .retryOnConnectionFailure(true)
                .cache(cache);


/*        if (ApiConfig.getOpenHttps()) {
            httpClientBuilder.sslSocketFactory(1 == ApiConfig.getSslSocketConfigure().getVerifyType() ?
                    SslSocketFactory.getSSLSocketFactory(ApiConfig.getSslSocketConfigure().getCertificateInputStream()) :
                    SslSocketFactory.getSSLSocketFactory(), new UnSafeTrustManager());
            httpClientBuilder.hostnameVerifier(new UnSafeHostnameVerify());
        }*/

        OkHttpClient httpClient = httpClientBuilder.build();


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .registerTypeAdapterFactory(new NullTypeAdapterFactory())
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        build = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        if (!TextUtils.isEmpty(ApiConfig.getBaseUrl2())) {
            retrofit = build.baseUrl(ApiConfig.getBaseUrl2()).build();
        }

//        cacheProvider = new RxCache.Builder()
//                .persistence(rxcacheFile, new GsonSpeaker())
//                .using(CacheProvider.class);

    }
    private static class RetrofitFactoryHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();

    }



    public static final RetrofitFactory getInstance() {
        return RetrofitFactoryHolder.INSTANCE;
    }
    public static final RetrofitFactory getInstance(long timeout) {
        return RetrofitFactoryHolder.INSTANCE;
    }

    /**
     * 获取单例CompositeDisposable
     *
     * @return
     */
    public static CompositeDisposable getCompositeDisposableInstance() {
        if (mCompositeDisposable == null) {
            synchronized (CompositeDisposable.class) {
                mCompositeDisposable = new CompositeDisposable();
            }
        }
        return mCompositeDisposable;
    }

    /**
     * 根据Api接口类生成Api实体
     *
     * @param clazz 传入的Api接口类
     * @return Api实体类
     */
    public <T> T create(Class<T> clazz) {
        checkNotNull(retrofit, "BaseUrl not init,you should init first!");
        return retrofit.create(clazz);
    }

    /**
     * 根据Api接口类生成Api实体
     *
     * @param baseUrl baseUrl
     * @param clazz   传入的Api接口类
     * @return Api实体类
     */
    public <T> T create(String baseUrl, Class<T> clazz) {
        return build.baseUrl(baseUrl).build().create(clazz);
    }

    private <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }


    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            UnSafeTrustManager unSafeTrustManager = new UnSafeTrustManager();
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{unSafeTrustManager}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return ssfFactory;
    }

    //定义一个信任所有证书的TrustManager
/*    final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };*/

    final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };
}
