package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.rxhttp.net.common.RetrofitFactory;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.IHttpApi;

import java.util.List;

public class BaseRepository extends AbstractRepository{

    protected IHttpApi httpApi;

    public BaseRepository() {
        if (null==httpApi){
            httpApi = RetrofitFactory.getInstance().create(IHttpApi.class);
        }
    }

    protected void postData(Object eventKey, Object t) {
        postData(eventKey, null, t);
    }


    protected void showPageState(Object eventKey, String state) {
        postData(eventKey, state);
    }

    protected void showPageState(Object eventKey, String tag, String state) {
        postData(eventKey, tag, state);
    }

    protected void postData(Object eventKey, String tag, Object t) {
        LiveBus.getDefault().postEvent(eventKey, tag, t);
    }

    protected void postData(Object eventKey, String tag, List<Object> list) {
        LiveBus.getDefault().postEvent(eventKey, tag, list);
    }

}
