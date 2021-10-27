package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.RankTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.SortEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SortChildViewModel extends BaseViewModel<SortChildRepo> {

    public SortChildViewModel(@NonNull Application application) {
        super(application);
    }
    private MutableLiveData<SortEntity> sortEntityData;

    public MutableLiveData<SortEntity> getSortEntityData(){
        if (sortEntityData==null){
            sortEntityData = new MutableLiveData<>();
        }
        return sortEntityData;
    }
    /**
     * 请求排行榜数据  type  1 礼物榜 2 中奖榜 3 趣约红包榜 4 专享红包榜
     */
    public void reqSortData(RankTypeEnum type,int pageNo,int pageSize, boolean isRestart){
        mRepository.getSortData(type,pageNo,pageSize, new CallBack() {
            @Override
            public void Success(Object object) {
                if (object instanceof SortEntity){
                    sortEntityData = getSortEntityData();
                    SortEntity sortEntity = sortEntityData.getValue();
                    if (sortEntity==null){
                        sortEntity = new SortEntity();
                    }
                    List<SortEntity.RankingListBean> dataList = sortEntity.getRankingList();
                    if (dataList==null){
                        dataList = new ArrayList<>();
                    }
                    List<SortEntity.RankingListBean> reqList = ((SortEntity) object).getRankingList();
                    if (isRestart){
                        dataList.clear();
                    }
                    dataList.addAll(reqList);
                    sortEntity.setRankingList(dataList);
                    sortEntityData.postValue(sortEntity);
                }
            }

            @Override
            public void Faild(String msg) {
                LogUtils.e(msg);
                ToastUtil.showToast(msg);
            }
        });
    }



}
