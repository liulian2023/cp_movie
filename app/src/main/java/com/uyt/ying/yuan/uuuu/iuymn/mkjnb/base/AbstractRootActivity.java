/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.os.Bundle;

import androidx.annotation.Nullable;


import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * created  by xxxx on 2019/9/20.
 */
public abstract class AbstractRootActivity extends SupportActivity {
    private static final String TAG = AbstractRootActivity.class.getSimpleName();
    protected AbstractRootActivity mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        GetIntentData();

        initView();

        mContext = this;
        onViewCreated();
        // 添加activity到栈
        ActivityUtil.getInstance().addActivity(this);

        initEventAndData();
    }
    public void GetIntentData(){}

    public void initView() { }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder = null;
        ActivityUtil.getInstance().finishActivity(this);
    }



    /**
     * 获取布局对象 留给子类实现
     */
    protected abstract int getLayout();


    /**
     * view 的创建 留给子类实现
     */
    protected abstract void onViewCreated();


    /**
     * 初始化数据留给子类实现
     */
    protected abstract void initEventAndData();


    protected static Boolean mIsExit = false;

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
            ActivityUtil.getInstance().AppExit(this);
        }
    }




}
