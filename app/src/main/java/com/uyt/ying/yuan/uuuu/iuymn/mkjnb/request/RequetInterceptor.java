/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author：PengJunShan.

 * 时间：On 2019-05-05.

 * 描述：日志拦截器
 */

public class RequetInterceptor implements Interceptor {

  /**
   * 这个chain里面包含了request和response，所以你要什么都可以从这里拿
   */
  @Override
  public Response intercept(Chain chain) throws IOException {

    /**
     * 可以添加公共头部参数如token
     */
    Request request = chain.request()
        .newBuilder()
//        .header("TOKEN", token)
//        .header("ID", id)
        .build();

    /**
     * 开始时间
     */
    long startTime = System.currentTimeMillis();
    Utils.logE("TAG","\n"+"requestUrl=" + request.url());
    String method = request.method();

    if ("POST".equals(method)) {
      try {
        JSONObject jsonObject = new JSONObject();
        if (request.body() instanceof FormBody) {
          FormBody body = (FormBody) request.body();
          for (int i = 0; i < body.size(); i++) {
            jsonObject.put(body.encodedName(i), body.encodedValue(i));
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    Response response = chain.proceed(request);
    /**
     * 这里不能直接使用response.body().string()的方式输出日志
     * 因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一个新的response给应用层处理
     */
    ResponseBody responseBody = response.peekBody(1024 * 1024);
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    return response;


  }


}
