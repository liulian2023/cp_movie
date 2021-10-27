package com.uyt.ying.rxhttp.net.interceptor;


import com.uyt.ying.rxhttp.net.utils.StringMyUtil;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;
import static com.uyt.ying.rxhttp.net.common.RxLibConstants.IMEI;
import static com.uyt.ying.rxhttp.net.common.RxLibConstants.SP_TOKEN;
import static com.uyt.ying.rxhttp.net.common.RxLibConstants.USER_ID;

public class HttpHeaderInterceptor implements Interceptor {

    SharedPreferenceHelperImpl mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();
    String token;
    long user_id;
    String deviceCode;
    @Override
    @EverythingIsNonNull
    public Response intercept(Chain chain) throws IOException {
        token = mSharedPreferenceHelperImpl.getToken();
        user_id=mSharedPreferenceHelperImpl.getUserId();
        deviceCode =mSharedPreferenceHelperImpl.getDeviceCode();
        Request request;
        if(mSharedPreferenceHelperImpl.getAppVestFlag()==1){
            String newBaseUrl = mSharedPreferenceHelperImpl.getNewBaseUrl();
            Request.Builder builder = chain.request().newBuilder();
            if(StringMyUtil.isNotEmpty(newBaseUrl)){
                HttpUrl httpUrl = HttpUrl.parse(newBaseUrl);
                String host = httpUrl.host();
                builder.addHeader("Host",host);
            }

            request = builder
                    .addHeader(SP_TOKEN, token)
                    .addHeader(USER_ID, user_id+"")
                    .addHeader("imeiType", "android")
                    .addHeader(IMEI, deviceCode)
                    .addHeader("userId", user_id+"")
                    .build();
        }else {
            //添加Token请求头
             request = chain.request().newBuilder()
                    .addHeader(SP_TOKEN, token)
                    .addHeader(USER_ID, user_id+"")
                    .addHeader("imeiType", "android")
                    .addHeader(IMEI, deviceCode)
//                .addHeader("Host", "cpapi01.ykydxh.com")
                    .addHeader("userId", user_id+"")
                    .build();
        }

        Response response = chain.proceed(request);
        return response;
    }
}
