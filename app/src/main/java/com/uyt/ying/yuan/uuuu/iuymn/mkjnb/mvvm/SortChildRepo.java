package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import com.uyt.ying.rxhttp.net.common.BaseStringObserver;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RankTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.SortEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class SortChildRepo extends BaseRepository {

    /**
     * 获取排行榜数据
     * type  1 礼物榜 2 中奖榜 3 趣约红包榜 4 专享红包榜
     */
    public void getSortData(RankTypeEnum type,int pageNo,int pageSize, CallBack callBack) {


        /*SortEntity sortEntity = new SortEntity();
        List<SortEntity.DataBean> sortdataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SortEntity.DataBean dataBean = new SortEntity.DataBean();
            dataBean.setUserId(i);
            dataBean.setName(""+i);
            dataBean.setAvatar_url("http://www.gx8899.com/uploads/allimg/190526/3-1Z52611122D62.jpg");
            dataBean.setMoney(900000 - i);
            sortdataList.add(dataBean);
        }
        sortEntity.setData(sortdataList);
        callBack.Success(sortEntity);*/

        int typeId = 22;
        switch (type){
            case GIFT:
                typeId = 22;
                break;
            case CHOCK:
                typeId = 23;
                break;
            case QY:
                typeId = 24;
                break;
            case ZX:
                typeId = 26;
                break;
                default:
                    break;
        }

        Observable<Response<ResponseBody>> observable= new HttpApiImpl(Utils.getApiHost())
                .getYestodayRankList(typeId,pageNo,pageSize);
        addSubscription(observable, new BaseStringObserver<ResponseBody>() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String resultData = CPDataParseUtils.ParseHeaderResult(result, headers);
                Gson gson = new GsonBuilder().serializeNulls().create();
                SortEntity sortEntity = gson.fromJson(resultData,SortEntity.class);
                callBack.Success(sortEntity);
            }

            @Override
            public void onFail(String msg) {
                callBack.Faild(msg);
            }
        });

    }
}
