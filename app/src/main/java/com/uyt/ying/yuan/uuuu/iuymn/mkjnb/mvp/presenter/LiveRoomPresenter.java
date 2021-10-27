/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvp.presenter;


import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvp.contract.LiveRoomContract;

public class LiveRoomPresenter extends BasePresenter<LiveRoomContract.ILiveRoomView> implements LiveRoomContract.ILiveRoomPresenter {

    /**
     * 根据专题ID 类型
     * @param id
     * @param selectType
     * @param pageNo
     * @param pageSize
     */

    @Override
    public void getLiveRoomList(long id, int selectType,int pageNo,int pageSize ,boolean isRefresh) {
//        HttpApiImpl.getInstance2()
//                .getRoomList(id,selectType,pageNo,pageSize)
//                .compose(RxTransformerUtils.io_main())
//                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mViewRef.get())))
//                .subscribe(new BaseEntityObserver<LiveRoomEntity>(){
//                    @Override
//                    public void onSuccess(LiveRoomEntity entity) {
//                        getView().showLiveRoomList(entity,isRefresh);
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//                        LogUtils.e(StringMyUtil.isEmptyString(msg)?"":msg);
//                        getView().showErrorMsg(StringMyUtil.isEmptyString(msg)?"":msg);
//                    }
//
//                    @Override
//                    protected void onRequestStart() {
//                        getView().showLoading();
//                    }
//
//                    @Override
//                    protected void onRequestEnd() {
//                        getView().closeLoading();
//                    }
//                });
    }
}
