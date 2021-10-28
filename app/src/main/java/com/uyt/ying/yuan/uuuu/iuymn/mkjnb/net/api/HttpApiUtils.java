/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.alibaba.fastjson.JSONObject;

import com.uyt.ying.rxhttp.net.common.BaseStringObserver;
import com.uyt.ying.rxhttp.net.utils.RxUtil;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.MvpBaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ErrorUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.HttpUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils.getApiHost;

public class HttpApiUtils {

    private static String TAG = "HttpApiUtils";

    /**
     * (显示加载中,没有errorLayout)
     * @param activity 上下文
     * @param url 接口后缀,用于跟httpApi中的注解url做对比,决定调用哪个方法
     * @param data 数据源
     * @param onRequestLintener  请求结果回调
     */

    public static void request(Activity activity,String url, HashMap<String, Object> data,  OnRequestLintener onRequestLintener) {
        if(null==activity){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {

                try {
                    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
                    long oldTime = sp.getShiJianCha();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                    Date  date = dateFormat.parse(headers.get("Date"));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
                    date = calendar.getTime();
                    long time = date.getTime();
                    long nowTime = System.currentTimeMillis() - time;
                    if (oldTime == 0) {//第一次存入(后面有时间差为0的一并忽略,存新值)
                        sp.setShiJianCha(nowTime);
                    } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                        sp.setShiJianCha(nowTime);
                    } else {
                        sp.setShiJianCha(oldTime);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                if(jsonObject.getString("status").equals("200")){
                    if(onRequestLintener!=null){
                        onRequestLintener.onSuccess(result,headers);
                    }
                }else {
                    if(onRequestLintener!=null){
                        String message = jsonObject.getString("message");
                        ToastUtil.showToast(message);
                        onRequestLintener.onFailed(message);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if(onRequestLintener!=null){
                    ToastUtil.showToast(msg);
                    onRequestLintener.onFailed(msg);
                }
            }

            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if((Activity)activity instanceof BaseActivity){
                    //筛选加载时不需要背景变暗的接口
                    if(url.equals(RequestUtil.ALL_CHANNEL)){//首页频道分类接口
                        ((BaseActivity) activity).showLoadingNoAlpha();
                    }else {
                        ((BaseActivity) activity).showLoading();
                    }
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if((Activity)activity instanceof BaseActivity){
                    ((BaseActivity) activity).closeLoading();
                }
            }
        };
        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody =null;
        HashMap<String ,Object>map =null;
        String getOrPost = split[0];
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody=HttpUtil.postRequest(data,false);
        }
        HttpApiImpl httpApi = HttpApiImpl.getInstance2();
        IHttpApi iHttpApiT = httpApi.iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {

            if(method.getName().equalsIgnoreCase(split[1])){
                try {
//                    int parameterCount = method.getParameterCount();
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    responseObservable
                            .compose(RxUtil.rxSchedulerHelper())
                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                            .subscribe(observer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     *  普通请求 只请求数据  没有ui操作
     * @param activity
     * @param url
     * @param data
     * @param onRequestLintener
     */
    public static void normalRequest(Activity activity,String url, HashMap<String, Object> data,  OnRequestLintener onRequestLintener) {
        if(null==activity){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                try {
                    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
                    long oldTime = sp.getShiJianCha();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                    Date  date = dateFormat.parse(headers.get("Date"));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
                    date = calendar.getTime();
                    long time = date.getTime();
                    long nowTime = System.currentTimeMillis() - time;
                    if (oldTime == 0) {//第一次存入(后面有时间差为0的一并忽略,存新值)
                        sp.setShiJianCha(nowTime);
                    } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                        sp.setShiJianCha(nowTime);
                    } else {
                        sp.setShiJianCha(oldTime);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JSONObject jsonObject = JSONObject.parseObject(result);
                if(jsonObject.getString("status").equals("200")){
                    if(onRequestLintener!=null){
                        onRequestLintener.onSuccess(result,headers);
                    }
                }else {
                    if(onRequestLintener!=null){
                        String message = jsonObject.getString("message");
                        ToastUtil.showToast(message);
                        onRequestLintener.onFailed(message);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if(onRequestLintener!=null){
                   ToastUtil.showToast(msg);
                   onRequestLintener.onFailed(msg);
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
            }
        };
        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody =null;
        HashMap<String ,Object>map =null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody=HttpUtil.postRequest(data,false);
        }
        IHttpApi iHttpApiT = HttpApiImpl.getInstance2().iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {

            if(method.getName().equalsIgnoreCase(split[1])){
                try {
//                    int parameterCount = method.getParameterCount();
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    responseObservable
                            .compose(RxUtil.rxSchedulerHelper())
//                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                            .subscribe(observer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     *  包含加载中, 上拉刷新 下拉加载 失败视图  空视图的请求
     * @param activity  上下文
     * @param fragment 当前所在的fragment  (如果在activity中请求,传null)
     * @param url 接口路径
     * @param data 数据源
     * @param loadingLinear  加载中的视图
     * @param errorLinear 加载失败的视图
     * @param reloadTv   点击刷新的按钮(具体看错误页面的布局,如果没有刷新按钮可以不传)
     * @param view  加载中 加载失败  空视图需要隐藏的view (主要是rececleView或者refreshLayout)
     * @param isLoadmore  是否是加载更多时调用
     * @param isrefresh  是否是下拉刷新时调用
     * @param onRequestLintener  请求结果回调
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static void showLoadRequest(Activity activity, Fragment fragment, String url, HashMap<String, Object> data, ConstraintLayout loadingLinear, LinearLayout errorLinear, TextView reloadTv, View view, boolean isLoadmore, boolean isrefresh, OnRequestLintener onRequestLintener) {
            if(null==activity&&fragment==null){
                return;
            }
            BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                try {
                    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
                    long oldTime = sp.getShiJianCha();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                    Date  date = dateFormat.parse(headers.get("Date"));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
                    date = calendar.getTime();
                    long time = date.getTime();
                    long nowTime = System.currentTimeMillis() - time;
                    if (oldTime == 0) {//第一次存入(后面有时间差为0的一并忽略,存新值)
                        sp.setShiJianCha(nowTime);
                    } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                        sp.setShiJianCha(nowTime);
                    } else {
                        sp.setShiJianCha(oldTime);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                if(jsonObject.getString("status").equals("200")){
                    if(null!=onRequestLintener){
                        onRequestLintener.onSuccess(result,headers);
                    }
                }else {
                    if(null!=onRequestLintener){
                        String message = jsonObject.getString("message");
                        ToastUtil.showToast(message);
                        onRequestLintener.onFailed(message);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showToast(msg);
                if(null!=onRequestLintener){
                    onRequestLintener.onFailed(msg);
                }
                if(null!=errorLinear){
                    if(null!=fragment){
                        ErrorUtil.showErrorLayout(fragment,view,errorLinear,reloadTv);
                    }else {
                        ErrorUtil.showErrorLayout(activity,view,errorLinear,reloadTv);
                    }
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if(!isLoadmore&&!isrefresh){
                    if(null!=loadingLinear){
                        loadingLinear.setVisibility(View.VISIBLE);
                    }
                    if(null!=errorLinear){
                      ErrorUtil.hideErrorLayout(view,errorLinear);
                    }
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if(null!=loadingLinear){
                    loadingLinear.setVisibility(View.GONE);
                }
            }
        };

        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody = null;
        HashMap<String,Object> map = null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody= HttpUtil.postRequest(data,false);
        }
        IHttpApi iHttpApiT = HttpApiImpl.getInstance2().iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    responseObservable
                            .compose(RxUtil.rxSchedulerHelper())
                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                            .subscribe(observer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     *  有头部的列表 (包含加载中, 上拉刷新 下拉加载 失败视图  空视图的请求,将各种状态的视图添加到尾部,这样视图出现的时候不会将头部也一起覆盖)
     * @param activity  上下文
     * @param fragment 当前所在的fragment  (如果在activity中请求,传null)
     * @param url 接口路径
     * @param data 数据源
     * @param loadingLinear  加载中的视图
     * @param errorLinear 加载失败的视图
     * @param reloadTv   点击刷新的按钮(具体看错误页面的布局,如果没有刷新按钮可以不传)
     * @param view  加载中 加载失败  空视图需要隐藏的view (主要是rececleView或者refreshLayout)
     * @param isLoadmore  是否是加载更多时调用
     * @param isrefresh  是否是下拉刷新时调用
     * @param onRequestLintener  请求结果回调
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static void footShowLoadRequest(Activity activity, Fragment fragment, String url, HashMap<String, Object> data, ConstraintLayout loadingLinear, LinearLayout errorLinear, TextView reloadTv, View view, boolean isLoadmore, boolean isrefresh, OnRequestLintener onRequestLintener) {
        if(null==activity){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                try {
                    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
                    long oldTime = sp.getShiJianCha();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                    Date  date = dateFormat.parse(headers.get("Date"));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
                    date = calendar.getTime();
                    long time = date.getTime();
                    long nowTime = System.currentTimeMillis() - time;
                    if (oldTime == 0) {//第一次存入(后面有时间差为0的一并忽略,存新值)
                        sp.setShiJianCha(nowTime);
                    } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                        sp.setShiJianCha(nowTime);
                    } else {
                        sp.setShiJianCha(oldTime);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                if(jsonObject.getString("status").equals("200")){
                    if(null!=onRequestLintener){
                        onRequestLintener.onSuccess(result,headers);
                    }
                }else {
                    if(null!=onRequestLintener){
                        String message = jsonObject.getString("message");
                        ToastUtil.showToast(message);
                        onRequestLintener.onFailed(message);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showToast(msg);
                if(null!=onRequestLintener){
                    onRequestLintener.onFailed(msg);
                }
                if(null!=errorLinear){
                    if(null!=fragment){
                        ErrorUtil.showErrorLayout(fragment,view,errorLinear,reloadTv);
                    }else {
                        ErrorUtil.showErrorLayout((Activity)activity,view,errorLinear,reloadTv);
                    }
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if(!isLoadmore&&!isrefresh){
                    if(null!=loadingLinear){
                        loadingLinear.setVisibility(View.VISIBLE);
                    }
                    if(null!=errorLinear){
                            ErrorUtil.hideErrorLayout(view,errorLinear);
                    }
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if(null!=loadingLinear){
                    loadingLinear.setVisibility(View.GONE);
                }
            }
        };

        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody = null;
        HashMap<String,Object> map = null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody= HttpUtil.postRequest(data,false);
        }

        IHttpApi iHttpApiT = HttpApiImpl.getInstance2().iHttpApiT;

        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {

                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    responseObservable
                            .compose(RxUtil.rxSchedulerHelper())
                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                            .subscribe(observer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    /**
     * 上传文件(图片)
     * @param activity  上下文
     * @param url  接口路径
     * @param filePath 图片路径
     * @param onRequestLintener  请求结果回调
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static void upload(Activity activity,String url, String filePath,  OnRequestLintener onRequestLintener) {
        if(null==activity){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = Utils.initOldCpData(url, result);
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                if(null!=jsonObject){
                    if(jsonObject.getString("status").equals("success")){
                        if(onRequestLintener!=null){
                            onRequestLintener.onSuccess(resultData,headers);
                        }
                    }else {
                        if(onRequestLintener!=null){
                            String message = jsonObject.getString("message");
                            ToastUtil.showToast(message);
                            onRequestLintener.onFailed(message);
                        }
                    }
                }else {
                    JSONObject object = JSONObject.parseObject(result);
                    String failString = object.getJSONObject("head").getString("info");
                    if(null!=onRequestLintener){
                        ToastUtil.showToast(failString);
                        onRequestLintener.onFailed(failString);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                if(onRequestLintener!=null){
                    ToastUtil.showToast(msg);
                    onRequestLintener.onFailed(msg);
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if((Activity)activity instanceof BaseActivity){
                        ((BaseActivity) activity).showLoading();

                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if((Activity)activity instanceof BaseActivity){
                    ((BaseActivity) activity).closeLoading();

                }
            }
        };
        String methodName = getUploadMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody =HttpUtil.uploadRequest(filePath);
//        IHttpApi iHttpApiT = new HttpApiImpl(API_HOST1).iHttpApiT;
        String apiHost = getApiHost();
        IHttpApi iHttpApiT = new HttpApiImpl(apiHost).iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
//                    int parameterCount = method.getParameterCount();
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    responseObservable
                            .compose(RxUtil.rxSchedulerHelper())
                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                            .subscribe(observer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }




    /**
     * 筛选IHttpApi中上传图片的方法
     * @param url  接口路径
     * @return
     */
    private static String getUploadMethodName(String url) {
        Method[] declaredMethods1 = IHttpApi.class.getDeclaredMethods();
        for (Method method:declaredMethods1) {
            GET getAnnotation = method.getAnnotation(GET.class);
            POST postAnnotation = method.getAnnotation(POST.class);
            if(getAnnotation==null && postAnnotation==null){
                continue;
            }
            String value =getAnnotation!=null? getAnnotation.value():postAnnotation.value();
            if(url.equals(value)){
                if(method.getName().equals("uploadImg")||method.getName().equals("modifyTitle")){
                    return (getAnnotation!=null?"get":"post")+","+method.getName();
                }
            }
        }
        return null;
    }

    /**
     * 通过url筛选IHttpApi中对应的方法
     * @param url  接口路径(url必须和IHttpApi注释中的路径一致)
     * @return
     */
    private static String getMethodName(String url) {
        Method[] declaredMethods1 = IHttpApi.class.getDeclaredMethods();
        for (Method method:declaredMethods1) {
            GET getAnnotation = method.getAnnotation(GET.class);
            POST postAnnotation = method.getAnnotation(POST.class);
            if(getAnnotation==null && postAnnotation==null){
                continue;
            }
            String value =getAnnotation!=null? getAnnotation.value():postAnnotation.value();
            if(url.equals(value)){
                    return (getAnnotation!=null?"get":"post")+","+method.getName();
            }
        }
        return null;
    }
    public  static interface OnRequestLintener{
        void onSuccess(String result, Headers headers);
        void onFailed(String msg);
    }


    /**
     *  普通请求 只请求数据  没有ui操作
     * @param activity
     * @param url
     * @param data
     * @param onRequestLintener
     */
    public static void CPnormalRequest(Activity activity,Fragment fragment,String url, HashMap<String, Object> data,  OnRequestLintener onRequestLintener) {
        if(null==activity&&fragment==null){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = Utils.initOldCpData(url, result);
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                if(null!=jsonObject){
                    if(jsonObject.getString("status").equals("success")){
                        if(onRequestLintener!=null){
                            onRequestLintener.onSuccess(resultData,headers);
                        }
                    }
                }/*else {
                    JSONObject object = JSONObject.parseObject(result);
                    String failString = object.getJSONObject("head").getString("info");
                    if(null!=onRequestLintener){
                        if(!url.contains("liveBroadCast/getHeavenRedPacket")){
                            ToastUtils.showToast(failString);
                        }
                        onRequestLintener.onFaild(failString);
                    }

                }*/

            }

            @Override
            public void onFail(String msg) {
                if(onRequestLintener!=null){
                        ToastUtil.showToast(msg);
                    onRequestLintener.onFailed(msg);
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
            }
        };
        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody =null;
        HashMap<String ,Object>map =null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody=HttpUtil.postRequest(data,true);
        }
        String apiHost = getApiHost();
//        IHttpApi iHttpApiT = new HttpApiImpl(API_HOST1).iHttpApiT;
        IHttpApi iHttpApiT = new HttpApiImpl(apiHost).iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
//                    int parameterCount = method.getParameterCount();
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    //
                    Observable<Response<ResponseBody>> compose = responseObservable
                            .compose(RxUtil.rxSchedulerHelper());
                    if(fragment!=null){
                        if(!url.equals(RequestUtil.WATCH_MINUTES)){
                            compose.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(fragment))).subscribe(observer);
                        }else {
                            compose.subscribe(observer);

                        }
                    }else {
                        if(null!=activity){
                            if(!url.equals(RequestUtil.WATCH_MINUTES)){
                                compose.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity))).subscribe(observer);
                            }else{
                                compose.subscribe(observer);
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * (显示加载中,没有errorLayout)
     * @param activity 上下文
     * @param url 接口后缀,用于跟httpApi中的注解url做对比,决定调用哪个方法
     * @param data 数据源
     * @param onRequestLintener  请求结果回调
     */

    public static void CpRequest(Activity activity,Fragment fragment,String url, HashMap<String, Object> data,  OnRequestLintener onRequestLintener) {
        if(null==activity){
            ToastUtil.showToast(Utils.getString(R.string.数据错误请重试));
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = Utils.initOldCpData(url, result);
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                if(null!=jsonObject){
                    if(jsonObject.getString("status").equals("success")){
                        if(onRequestLintener!=null){
                            onRequestLintener.onSuccess(resultData,headers);
                        }
                    }
                }else {
                    JSONObject object = JSONObject.parseObject(result);
                    String failString = object.getJSONObject("head").getString("info");
                    if(null!=onRequestLintener){
                        ToastUtil.showToast(failString);
                        onRequestLintener.onFailed(failString);
                    }
                }

            }

            @Override
            public void onFail(String msg) {
                 if(onRequestLintener!=null){
                    ToastUtil.showToast(msg);
                    onRequestLintener.onFailed(msg);
                }
            }

            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if(activity.isFinishing()||activity ==null){
                    return;
                }
                if((Activity)activity instanceof BaseActivity){
                    //筛选加载时不需要背景变暗的接口
                    if(url.equals(RequestUtil.ALL_CHANNEL)){//首页频道分类接口
                        ((BaseActivity) activity).showLoadingNoAlpha();
                    }else {
                        ((BaseActivity) activity).showLoading();
                    }
                }
                if(activity instanceof MvpBaseActivity){
                    ((MvpBaseActivity) activity).showLoading();
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if(activity.isFinishing()||activity ==null){
                    return;
                }
                if((Activity)activity instanceof BaseActivity){
                    ((BaseActivity) activity).closeLoading();
                }
                if(activity instanceof  MvpBaseActivity){
                    ((MvpBaseActivity) activity).closeLoading();
                }
            }
        };
        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody =null;
        HashMap<String ,Object>map =null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody=HttpUtil.postRequest(data,true);
        }
        String apiHost = getApiHost();
//        IHttpApi iHttpApiT = new HttpApiImpl(API_HOST1).iHttpApiT;
        IHttpApi iHttpApiT = new HttpApiImpl(apiHost).iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
//                    int parameterCount = method.getParameterCount();
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    if(null == fragment){
                        responseObservable
                                .compose(RxUtil.rxSchedulerHelper())
                                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                                .subscribe(observer);
                    }else {
                        responseObservable
                                .compose(RxUtil.rxSchedulerHelper())
                                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) fragment)))
                                .subscribe(observer);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    /**
     *  包含加载中, 上拉刷新 下拉加载 失败视图  空视图的请求
     * @param activity  上下文
     * @param fragment 当前所在的fragment  (如果在activity中请求,传null)
     * @param url 接口路径
     * @param data 数据源
     * @param loadingLinear  加载中的视图
     * @param errorLinear 加载失败的视图
     * @param reloadTv   点击刷新的按钮(具体看错误页面的布局,如果没有刷新按钮可以不传)
     * @param view  加载中 加载失败  空视图需要隐藏的view (主要是rececleView或者refreshLayout)
     * @param isLoadmore  是否是加载更多时调用
     * @param isrefresh  是否是下拉刷新时调用
     * @param onRequestLintener  请求结果回调
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static void cpShowLoadRequest(Activity activity, Fragment fragment, String url, HashMap<String, Object> data, ConstraintLayout loadingLinear, LinearLayout errorLinear, TextView reloadTv, View view, boolean isLoadmore, boolean isrefresh, OnRequestLintener onRequestLintener) {
        if(null==activity&&fragment==null){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = Utils.initOldCpData(url, result);
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                if(null!=jsonObject){
                    if(jsonObject.getString("status").equals("success")){
                        if(onRequestLintener!=null){
                            onRequestLintener.onSuccess(resultData,headers);
                        }
                    }
                }else {
                    JSONObject object = JSONObject.parseObject(result);
                    String failString = object.getJSONObject("head").getString("info");
                    if(null!=onRequestLintener){
                        ToastUtil.showToast(failString);
                        onRequestLintener.onFailed(failString);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showToast(msg);
                if(null!=onRequestLintener){
                    onRequestLintener.onFailed(msg);
                }
                if(null!=errorLinear){
                    if(null!=fragment){
                        ErrorUtil.showErrorLayout(fragment,view,errorLinear,reloadTv);
                    }else {
                        ErrorUtil.showErrorLayout(activity,view,errorLinear,reloadTv);
                    }
                }
                if(null!=loadingLinear){
                    loadingLinear.setVisibility(View.GONE);
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if(!isLoadmore&&!isrefresh){
                    if(null!=loadingLinear){
                        loadingLinear.setVisibility(View.VISIBLE);
                    }
                    if(null!=errorLinear){
                        ErrorUtil.hideErrorLayout(view,errorLinear);
                    }
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if(null!=loadingLinear){
                    loadingLinear.setVisibility(View.GONE);
                }

            }
        };

        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody = null;
        HashMap<String,Object> map = null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody= HttpUtil.postRequest(data,true);
        }
        String apiHost = getApiHost();
//        IHttpApi iHttpApiT = new HttpApiImpl(API_HOST1).iHttpApiT;
        IHttpApi iHttpApiT = new HttpApiImpl(apiHost).iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    if(fragment!=null){
                        responseObservable
                                .compose(RxUtil.rxSchedulerHelper())
                                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) fragment)))
                                .subscribe(observer);
                    }else {
                        responseObservable
                                .compose(RxUtil.rxSchedulerHelper())
                                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                                .subscribe(observer);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static void cpHeadShowLoadRequest(Activity activity, Fragment fragment, String url, HashMap<String, Object> data, ConstraintLayout loadingLinear, LinearLayout errorLinear, TextView reloadTv, View view, boolean isLoadmore, boolean isrefresh, OnRequestLintener onRequestLintener) {
        if(null==activity){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = Utils.initOldCpData(url, result);
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                if(null!=jsonObject){
                    if(jsonObject.getString("status").equals("success")){
                        if(onRequestLintener!=null){
                            onRequestLintener.onSuccess(resultData,headers);
                        }
                    }
                }else {
                    JSONObject object = JSONObject.parseObject(result);
                    String failString = object.getJSONObject("head").getString("info");
                    if(null!=onRequestLintener){
                        ToastUtil.showToast(failString);
                        onRequestLintener.onFailed(failString);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showToast(msg);
                if(null!=onRequestLintener){
                    onRequestLintener.onFailed(msg);
                }
                if(null!=errorLinear){
                    if(null!=fragment){
                        ErrorUtil.headShowErrorLayout(fragment,errorLinear,reloadTv);
                    }else {
                        ErrorUtil.headShowErrorLayout(activity,errorLinear,reloadTv);
                    }
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if(!isLoadmore&&!isrefresh){
                    if(null!=loadingLinear){
                        loadingLinear.setVisibility(View.VISIBLE);
                    }
                    if(null!=errorLinear){
                        errorLinear.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if(null!=loadingLinear){
                    loadingLinear.setVisibility(View.GONE);
                }
            }
        };

        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody = null;
        HashMap<String,Object> map = null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody= HttpUtil.postRequest(data,true);
        }
//        IHttpApi iHttpApiT = new HttpApiImpl(API_HOST1).iHttpApiT;
        String apiHost = getApiHost();
        IHttpApi iHttpApiT = new HttpApiImpl(apiHost).iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    if(fragment!=null){
                        responseObservable
                                .compose(RxUtil.rxSchedulerHelper())
                                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                                .subscribe(observer);
                    }else {
                        responseObservable
                                .compose(RxUtil.rxSchedulerHelper())
                                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                                .subscribe(observer);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     *  包含加载中, 上拉刷新 下拉加载 失败视图  空视图的请求(pop中发起请求)
     * @param activity  上下文
     * @param popupWindow 当前所在的pop  (
     * @param url 接口路径
     * @param data 数据源
     * @param loadingLinear  加载中的视图
     * @param errorLinear 加载失败的视图
     * @param reloadTv   点击刷新的按钮(具体看错误页面的布局,如果没有刷新按钮可以不传)
     * @param view  加载中 加载失败  空视图需要隐藏的view (主要是rececleView或者refreshLayout)
     * @param isLoadmore  是否是加载更多时调用
     * @param isrefresh  是否是下拉刷新时调用
     * @param onRequestLintener  请求结果回调
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static void cpPopShowLoadRequest(Activity activity, PopupWindow popupWindow, String url, HashMap<String, Object> data, ConstraintLayout loadingLinear, LinearLayout errorLinear, TextView reloadTv, View view, boolean isLoadmore, boolean isrefresh, OnRequestLintener onRequestLintener) {
        if(null==activity){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = Utils.initOldCpData(url, result);
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                if(null!=jsonObject){
                    if(jsonObject.getString("status").equals("success")){
                        if(onRequestLintener!=null){
                            onRequestLintener.onSuccess(resultData,headers);
                        }
                    }
                }else {
                    JSONObject object = JSONObject.parseObject(result);
                    String failString = object.getJSONObject("head").getString("info");
                    if(null!=onRequestLintener){
                        ToastUtil.showToast(failString);
                        onRequestLintener.onFailed(failString);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showToast(msg);
                if(null!=onRequestLintener){
                    onRequestLintener.onFailed(msg);
                }
                if(null!=errorLinear){
                    if(null!=popupWindow){
                        ErrorUtil.showErrorLayout(popupWindow,view,errorLinear,reloadTv);
                    }
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if(!isLoadmore&&!isrefresh){
                    if(null!=loadingLinear){
                        loadingLinear.setVisibility(View.VISIBLE);
                    }
                    if(null!=errorLinear){
                        ErrorUtil.hideErrorLayout(view,errorLinear);
                    }
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if(null!=loadingLinear){
                    loadingLinear.setVisibility(View.GONE);
                }
            }
        };

        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody = null;
        HashMap<String,Object> map = null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody= HttpUtil.postRequest(data,true);
        }
//        IHttpApi iHttpApiT = new HttpApiImpl(API_HOST1).iHttpApiT;

        String apiHost = getApiHost();
        IHttpApi iHttpApiT = new HttpApiImpl(apiHost).iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    responseObservable
                            .compose(RxUtil.rxSchedulerHelper())
                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) activity)))
                            .subscribe(observer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    /**
     *  包含加载中, 上拉刷新 下拉加载 失败视图  空视图的请求(pop中发起请求)
     * @param dialogFragment  上下文
     * @param dialogFragment 当前所在的dialogFragment
     * @param url 接口路径
     * @param data 数据源
     * @param loadingLinear  加载中的视图
     * @param errorLinear 加载失败的视图
     * @param reloadTv   点击刷新的按钮(具体看错误页面的布局,如果没有刷新按钮可以不传)
     * @param view  加载中 加载失败  空视图需要隐藏的view (主要是rececleView或者refreshLayout)
     * @param isLoadmore  是否是加载更多时调用
     * @param isrefresh  是否是下拉刷新时调用
     * @param onRequestLintener  请求结果回调
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static void cpDialogShowLoadRequest( DialogFragment dialogFragment, String url, HashMap<String, Object> data, ConstraintLayout loadingLinear, LinearLayout errorLinear, TextView reloadTv, View view, boolean isLoadmore, boolean isrefresh, OnRequestLintener onRequestLintener) {
        if(null==dialogFragment){
            return;
        }
        BaseStringObserver<ResponseBody> observer = new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = Utils.initOldCpData(url, result);
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                if(null!=jsonObject){
                    if(jsonObject.getString("status").equals("success")){
                        if(onRequestLintener!=null){
                            onRequestLintener.onSuccess(resultData,headers);
                        }
                    }
                }else {
                    JSONObject object = JSONObject.parseObject(result);
                    String failString = object.getJSONObject("head").getString("info");
                    if(null!=onRequestLintener){
                        ToastUtil.showToast(failString);
                        onRequestLintener.onFailed(failString);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showToast(msg);
                if(null!=onRequestLintener){
                    onRequestLintener.onFailed(msg);
                }
                if(null!=errorLinear){
                    if(null!=dialogFragment){
                        ErrorUtil.showErrorLayout(dialogFragment,view,errorLinear,reloadTv);
                    }
                }
            }
            @Override
            protected void onRequestStart() {
                super.onRequestStart();
                if(!isLoadmore&&!isrefresh){
                    if(null!=loadingLinear){
                        loadingLinear.setVisibility(View.VISIBLE);
                    }
                    if(null!=errorLinear){
                        ErrorUtil.hideErrorLayout(view,errorLinear);
                    }
                }
            }

            @Override
            protected void onRequestEnd() {
                super.onRequestEnd();
                if(null!=loadingLinear){
                    loadingLinear.setVisibility(View.GONE);
                }
            }
        };

        String methodName = getMethodName(url);
        String[] split = methodName.split(",");
        RequestBody requestBody = null;
        HashMap<String,Object> map = null;
        String getOrPost = split[0];
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(getOrPost.equalsIgnoreCase("get")){
            map= (HashMap<String, Object>) HttpUtil.getRequest(data);
        }else {
            requestBody= HttpUtil.postRequest(data,true);
        }
//        IHttpApi iHttpApiT = new HttpApiImpl(API_HOST1).iHttpApiT;
        String apiHost = getApiHost();
        IHttpApi iHttpApiT = new HttpApiImpl(apiHost).iHttpApiT;
        Method[] declaredMethods = iHttpApiT.getClass().getDeclaredMethods();
        for (Method method:declaredMethods) {
            if(method.getName().equalsIgnoreCase(split[1])){
                try {
                    int parameterCount = method.getParameterTypes().length;
                    Object invoke;
                    if(getOrPost.equalsIgnoreCase("get")){
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  map);
                    }else {
                        invoke =parameterCount==0?method.invoke(iHttpApiT): method.invoke(iHttpApiT,  requestBody);
                    }
                    Observable<Response<ResponseBody>> responseObservable = (Observable<Response<ResponseBody>>) invoke;
                    responseObservable
                            .compose(RxUtil.rxSchedulerHelper())
                            .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) dialogFragment)))
                            .subscribe(observer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
