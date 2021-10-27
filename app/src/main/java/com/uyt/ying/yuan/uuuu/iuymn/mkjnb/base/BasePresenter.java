/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;


import java.lang.ref.WeakReference;


public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T>{
    public WeakReference<T> mViewRef;


    @Override
    public void onAttachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }


    
    public T getView() {
        if (isViewAttached()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    /**
     * 判断是否与View建立了关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
    @Override
    public void onDetachView() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }

    }
}