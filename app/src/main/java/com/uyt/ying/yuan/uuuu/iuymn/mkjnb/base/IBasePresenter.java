

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

public interface IBasePresenter<T extends IBaseView> {

    /**
     * 绑定view到presenter
     * @param view
     *
     */
    void onAttachView(T view);
    /**
     * 解绑view
     */
    void onDetachView();
}

