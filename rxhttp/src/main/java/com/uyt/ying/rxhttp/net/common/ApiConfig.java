package com.uyt.ying.rxhttp.net.common;

import android.content.Context;
import android.util.ArrayMap;

import com.uyt.ying.rxhttp.net.utils.AppContextUtils;
import com.uyt.ying.rxhttp.net.utils.SslSocketConfigure;

import java.io.Serializable;

/**
 * 网络请求配置文件
 */

public class ApiConfig implements Serializable {

    private static String mBaseUrl1;
    private static String mBaseUrl2;
    private static int mDefaultTimeout = 20000;
    private static int mSucceedCode;
    private static ArrayMap<String, String> mHeads;
    private static boolean mOpenHttps;
    private static SslSocketConfigure mSslSocketConfigure;

    private ApiConfig(Builder builder) {
        mBaseUrl1 = builder.baseUrl1;
        mBaseUrl2 = builder.baseUrl2;
        mDefaultTimeout = builder.defaultTimeout;
        mSucceedCode = builder.succeedCode;
        mHeads = builder.heads;
        mOpenHttps = builder.openHttps;
        mSslSocketConfigure = builder.sslSocketConfigure;
    }

    public void init(Context appContext) {
        AppContextUtils.init(appContext);
    }

    public static String getBaseUrl1() {
        return mBaseUrl1;
    }

    public static String getBaseUrl2() {
        return mBaseUrl2;
    }

    public static int getDefaultTimeout() {
        return mDefaultTimeout;
    }

    public static int getSucceedCode() {
        return mSucceedCode;
    }

    public static ArrayMap<String, String> getHeads() {
        return mHeads;
    }

    public static void setHeads(ArrayMap<String, String> mHeads) {
        ApiConfig.mHeads = mHeads;
    }


    public static boolean getOpenHttps() {
        return mOpenHttps;
    }


    public static SslSocketConfigure getSslSocketConfigure() {
        return mSslSocketConfigure;
    }

    public static final class Builder {

        private String baseUrl1;
        private String baseUrl2;

        private int defaultTimeout;

        private int succeedCode;

        private ArrayMap<String, String> heads;

        private boolean openHttps = false;

        private SslSocketConfigure sslSocketConfigure;

        public Builder setSucceedCode(int succeedCode) {
            this.succeedCode = succeedCode;
            return this;
        }

        public Builder setBaseUrl1(String mBaseUrl1) {
            this.baseUrl1 = mBaseUrl1;
            return this;
        }
        public Builder setBaseUrl2(String mBaseUrl2) {
            this.baseUrl2 = mBaseUrl2;
            return this;
        }

        public Builder setDefaultTimeout(int defaultTimeout) {
            this.defaultTimeout = defaultTimeout;
            return this;
        }

        public Builder setOpenHttps(boolean open) {
            this.openHttps = open;
            return this;
        }

        public Builder setSslSocketConfigure(SslSocketConfigure sslSocketConfigure) {
            this.sslSocketConfigure = sslSocketConfigure;
            return this;
        }

        public ApiConfig build() {
            return new ApiConfig(this);
        }
    }
}
