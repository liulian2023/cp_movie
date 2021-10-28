package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.greenrobot.eventbus.EventBus;

public abstract class MvpBaseActivity <T extends IBasePresenter> extends AbstractRootActivity implements IBaseView{

    public static final String TAG = BaseActivity.class.getSimpleName();

    protected KProgressHUD mKProgressHUD;
    protected T mPresenter;

    protected  abstract T  createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        ActivityUtil.getInstance().addActivity(this);
    }

    @Override
    protected void onViewCreated() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
            Utils.logE(TAG,".---mPresenter 不为空" + mPresenter.getClass());
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
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.onDetachView();
            mPresenter = null;
        }
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
        ActivityUtil.getInstance().removeActivity(this);
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 移除Activity
            ActivityUtil.getInstance().removeActivity(this);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void showLoading() {
        if(mKProgressHUD==null){
            mKProgressHUD = KProgressHUD.create(this);
        }
        if(!mKProgressHUD.isShowing()){
            mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        }

    }

    @Override
    public void closeLoading() {
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void showNormal() { }

    @Override
    public void showError() { }

    @Override
    protected int getLayout() {
        return 0;
    }



    @Override
    protected void initEventAndData() { }



    @Override
    public void reload() { }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }



}
