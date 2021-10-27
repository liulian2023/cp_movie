

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvp.contract;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBaseView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveRoomEntity;

public interface LiveRoomContract extends IBaseView {

    interface ILiveRoomView extends IBaseView {
       void showLiveRoomList(LiveRoomEntity entity ,boolean isRefresh);
    }

    interface ILiveRoomPresenter extends IBasePresenter<ILiveRoomView> {
        void getLiveRoomList(long id, int selectType,int pageNo,int pageSize,boolean isRefresh);
    }

}