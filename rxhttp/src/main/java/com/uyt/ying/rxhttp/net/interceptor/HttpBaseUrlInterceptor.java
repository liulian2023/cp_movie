package com.uyt.ying.rxhttp.net.interceptor;
import android.text.TextUtils;

import com.uyt.ying.rxhttp.net.common.RxLibConstants;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HttpBaseUrlInterceptor implements Interceptor  {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //获取request的创建者builder
        Request.Builder requestBuilder = request.newBuilder();

        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        HttpUrl newBaseUrl = null;
        String newBaseUrl1 = sp.getNewBaseUrl();
        //有切换过路线
        if(!TextUtils.isEmpty(newBaseUrl1)){
            //获取头信息的集合
            List<String> urlnameList = request.headers(RxLibConstants.NEED_CHANGE_URL);
            if(urlnameList!=null&&urlnameList.size()>0){//标识头部集合不为空
                //删除原有配置中的值,就是namesAndValues集合里的值
                requestBuilder.removeHeader(RxLibConstants.NEED_CHANGE_URL);
                //获取头信息中配置的value
                String urlname = urlnameList.get(0);
                if("true".equals(urlname)){//如果项目有多个baseUrl，则在需要替换的baseUrl请求时添加NEED_CHANGE_URL为 true的请求头
                    newBaseUrl = HttpUrl.parse(newBaseUrl1);
                }else {//标识请求头的value不为true，则不替换
                    newBaseUrl = oldHttpUrl;
                }
            }else {//没有添加标识头部的替换(现在所有接口都没有添加标识头部)
                newBaseUrl = HttpUrl.parse(newBaseUrl1);
            }
        }else {
            newBaseUrl = oldHttpUrl;
        }
        //重建新的HttpUrl，修改需要修改的url部分
        HttpUrl newFullUrl = oldHttpUrl
                .newBuilder()
                .scheme(newBaseUrl.scheme())
                .host(newBaseUrl.host())
                .port(newBaseUrl.port())
                .build();
        //重建这个request，通过builder.url(newFullUrl).build()；

        Request newRequest = requestBuilder.url(newFullUrl).build();

        return chain.proceed(newRequest);
    }
}
