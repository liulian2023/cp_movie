package com.uyt.ying.rxhttp.net.common;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.net.model.FlagEventModel;
import com.uyt.ying.rxhttp.net.utils.Base64Utils;
import com.uyt.ying.rxhttp.net.utils.CommonModule;
import com.uyt.ying.rxhttp.net.utils.RxExceptionUtils;
import com.uyt.ying.rxhttp.net.utils.StringMyUtil;
import com.uyt.ying.rxhttp.net.utils.Utils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public abstract class BaseStringObserver<T extends ResponseBody> implements Observer<Response<T>> {

    private static final String RESPONSE_RETURN_ERROR = "";
//    private static final String RESPONSE_RETURN_ERROR = "返回数据为空";

    public BaseStringObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(Response<T> tResponse) {
        if (tResponse.body() == null) {
            onFail(RESPONSE_RETURN_ERROR);
        } else {
            try {
                String url = tResponse.raw().request().url().toString();
                String result = tResponse.body().string();
                Headers headers = tResponse.raw().headers();
                JSONObject json = JSONObject.parseObject(result);
                String data = json.getString("data");
                    if (data != null) {
                        if(url.contains("member/test")){
                            onSuccess(data, headers);
                            return;
                        }
                        String resultData = Base64Utils.decodeBase64String(data);//响应体需要解码
                        Utils.logE("HttpLogger-->"   ,tResponse.raw().request().url()+">>>>>>>"+resultData);
                        JSONObject dataObject = JSONObject.parseObject(resultData);
                        String status = dataObject.getString("status");
                        String message = dataObject.getString("message");
                        if ("success".equals(status)) {
                            onSuccess(result, headers);
                        } else {
                            if(StringMyUtil.isEmptyString(message)){
                                JSONObject head = JSONObject.parseObject(json.getString("head"));
                                String info = head.getString("info");
                                onFail(StringMyUtil.isEmptyString(info)?"": info);
                                Utils.logE("HttpLogger-->"   ,tResponse.raw().request().url()+">>>>>>>"+info);
                            }else {
                                onFail(message);
                                Utils.logE("HttpLogger-->"   ,tResponse.raw().request().url()+">>>>>>>"+message);
                            }

                            String flag = dataObject.getString("flag");
                            if(!TextUtils.isEmpty(flag) ){
                                if (flag.equals("1")) {
                                    //单点登录
                                    EventBus.getDefault().post(new FlagEventModel(1));
                                }else if(flag.equals("2")){
                                    //系统维护
                                    EventBus.getDefault().post(new FlagEventModel(2));
                                }else if(flag.equals("11")){
                                    //游客登录, 提示跳转游客安全中心
                                    EventBus.getDefault().post(new FlagEventModel(11));
                                }
                            }

                        }
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        MobclickAgent.reportError(CommonModule.getAppContext(),e);
        Utils.logE("HttpLogger-->", "onError: 异常"+e.getLocalizedMessage() );
        String errorMsg = RxExceptionUtils.exceptionHandler(e);
        errorMsg = StringMyUtil.isEmptyString(errorMsg)?"":errorMsg;
        errorMsg = errorMsg.equalsIgnoreCase("timeout")?"":errorMsg;
        onFail(errorMsg);
        onRequestEnd();
    }

    @Override
    public void onComplete() {
        onRequestEnd();
    }

    public abstract void onSuccess(String result, Headers headers);

    abstract public void onFail(String msg);

    /**
     * 网络请求开始
     */
    protected void onRequestStart() {
    }

    /**
     * 网络请求结束
     */
    protected void onRequestEnd() {
    }
}
