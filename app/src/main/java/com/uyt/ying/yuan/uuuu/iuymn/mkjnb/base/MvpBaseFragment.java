/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.AbstractRootActivity.mIsExit;

public abstract class MvpBaseFragment  <T extends IBasePresenter> extends AbstractRootFragment implements IBaseView {

    private static final String TAG = BaseFragment.class.getSimpleName();

    protected T mPresenter;
    protected  abstract T  createPresenter();

    @Override
    public void onAttach(Context onAttach) {
        super.onAttach(onAttach);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        if(mPresenter != null){
            Utils.logE(TAG,Utils.getString(R.string.=== mPresenter 不为空) + mPresenter.getClass());
            mPresenter.onAttachView(this);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.onDetachView();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mPresenter != null){
            mPresenter = null;
        }
    }

    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
                if (Const.ERROR_STATE.equals(state)) {
                    showErrorMsg(state);
                } else if (Const.LOADING_STATE.equals(state)) {
                    showLoading();
                } else if (Const.COMPLETE_STATE.equals(state)) {
                    closeLoading();
                }
            }
        }
    };

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void showNormal() { }

    @Override
    public void showError() { }


    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    protected int getLayoutId() { return 0; }

    @Override
    protected void initEventAndData() { }

    @Override
    public void reload() {
        ToastUtil.showToast(Utils.getString(R.string.点击重新加载));
    }


    /**
     * 直接退出APP
     */
    protected void QuickExit() {
        ActivityUtil.getInstance().exitSystem();
    }

    protected void QuickExitBkg() {
        ActivityUtil.getInstance().exitSystem();
    }


    /**
     * 双击退出APP
     */
    protected void doubleClickExit() {
        Timer exitTimer = null;
        if (!mIsExit) {
            mIsExit = true;
            ToastUtil.showToast(getString(R.string.exit_again));
            exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 2000);
        } else {
            ActivityUtil.getInstance().exitSystem();
        }
    }

    /**
     * 双击退出APP和后台
     */
    protected void doubleClickExitAndBkg() {
        Timer exitTimer = null;
        if (!mIsExit) {
            mIsExit = true;
            ToastUtil.showToast(getString(R.string.exit_again));
            exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 2000);
        } else {
            ActivityUtil.getInstance().AppExit(_mActivity);
        }
    }


}
