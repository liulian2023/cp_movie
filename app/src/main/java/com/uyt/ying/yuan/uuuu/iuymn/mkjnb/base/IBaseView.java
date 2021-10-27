/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

public interface IBaseView {

    /**
     * 正常显示
     */
    void showNormal();

    /**
     * 显示错误
     */
    void showError();

    /**
     * 显示错误信息
     * @param errorMsg 错误信息
     */
    void showErrorMsg(String errorMsg);

    /**
     * 正在加载
     */
    void showLoading();
    /**
     * 关闭loding
     */
    void closeLoading();

    /**
     * 页面重新加载
     */
    void reload();

}
