package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.rxhttp.net.common.BaseStringObserver;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class LiveRepo extends BaseRepository {

    public void getDataList(int pageNo,int pageSize, String categoryId,String area,CallBack callBack) {
        /**
         * Test MVVM retroft 请求数据
         */
        //     Observable<LiveEntity> observable= new  HttpApiImpl(API_HOST1)

//        Observable<Response<ResponseBody>> observable= new HttpApiImpl(API_HOST1)
        Observable<Response<ResponseBody>> observable= new HttpApiImpl(Utils.getApiHost())
                .getLiveList(RequestUtil.LIVEROOM_LIST,pageNo,pageSize, categoryId,area);
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                Utils.initEntity(result,LiveEntity.class, callBack);
            }

            @Override
            public void onFail(String msg) {
                callBack.Faild(msg);
            }
        });
    }



}
