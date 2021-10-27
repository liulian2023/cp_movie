package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.test;

import androidx.lifecycle.LifecycleOwner;

import com.uyt.ying.rxhttp.net.common.BaseStringObserver;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.rxhttp.net.utils.RxTransformerUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.MvpBaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiImpl;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import okhttp3.Headers;
import okhttp3.ResponseBody;

import static com.uyt.ying.yuan.BuildConfig.API_HOST1;

public class MTestActivity extends MvpBaseActivity {

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_testactivity;
    }

    @Override
    public void initView() {
        super.initView();

        try {
            new HttpApiImpl(API_HOST1).getCountDown("",2)
                    .compose(RxTransformerUtils.io_main())
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                    .subscribe(new BaseStringObserver<ResponseBody>(){

                        @Override
                        public void onSuccess(String result, Headers headers) {
                            LogUtils.e(result);
                        }

                        @Override
                        public void onFail(String msg) {
                            LogUtils.e(msg);
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
