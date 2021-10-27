package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvp.contract;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBaseView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BannerData;

import java.util.List;

public interface LiveContract extends IBaseView {

    interface LiveView extends IBaseView {
        void showBanner(List<BannerData> list);
        void showCaizhong();
        void showLiveData();

    }

    interface ILivePresenter extends IBasePresenter<LiveView> {
        void getBanner();
        void getCaizhong();
        void getLiveData();

    }

}
