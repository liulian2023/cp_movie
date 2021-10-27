package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvp.contract;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.NoticeMulBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBaseView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.PaihangEntity;

import java.util.List;

public interface LiveRoomFragmentContract  extends IBaseView {

    interface LiveRoomFragmentView extends IBaseView {
        void showPaihang(PaihangEntity entity);

        void showNotice(List<NoticeMulBean> list);
    }

    interface LiveRoomFragmentPresenter extends IBasePresenter<LiveRoomFragmentView> {
         void getPaihang();
         void getNotice();
      //   void getZjInfo();

    }

}
