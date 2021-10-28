package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.cf.msc.sdk.AppVest;
import com.cf.msc.sdk.SecurityConnection;
import com.uyt.ying.rxhttp.net.common.BaseStringObserver;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.rxhttp.net.utils.RxTransformerUtils;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BaseUrlAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BaseUrlEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiImpl;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ChooseBaseUrlAvtivity extends BaseActivity {
    @BindView(R.id.refresh_request_tv)
    TextView refresh_request_tv;
    @BindView(R.id.base_url_recycler)
    RecyclerView base_url_recycler;
    BaseUrlAdapter baseUrlAdapter;
    ArrayList<BaseUrlEntity.AppRequestDomainsBean>baseUrlEntityArrayList = new ArrayList<>();
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private SharedPreferenceHelperImpl mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();
    private BaseUrlEntity baseUrlEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_base_url_avtivity);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.white));
        StatusBarUtil.setLightMode(this,true);
        CommonToolbarUtil.initToolbar(this, Utils.getString(R.string.选择线路));
        ButterKnife.bind(this);
        initRecycler();
    }

    private void initRecycler() {
        baseUrlAdapter = new BaseUrlAdapter(R.layout.base_url_recycler_item,baseUrlEntityArrayList);
        base_url_recycler.setLayoutManager(new LinearLayoutManager(this));
        base_url_recycler.setAdapter(baseUrlAdapter);
        baseUrlAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                for (int i = 0; i < baseUrlEntityArrayList.size(); i++) {
                    BaseUrlEntity.AppRequestDomainsBean appRequestDomainsBean = baseUrlEntityArrayList.get(i);
                    if(position==i){
                        appRequestDomainsBean.setCheck(true);
                        sp.setNewBaseUrl(appRequestDomainsBean.getDomain());
                        startActivity(new Intent(ChooseBaseUrlAvtivity.this,SplashActivity.class));
                        finish();
                    }else {
                        appRequestDomainsBean.setCheck(false);
                    }
                }
                baseUrlAdapter.notifyDataSetChanged();
            }
        });
        String  urlList = sp.getUrlList();
        String  newBaseUrl = sp.getNewBaseUrl();
         baseUrlEntity = JSONObject.parseObject(urlList, BaseUrlEntity.class);
        chooseBestUrl(baseUrlEntity);
        List<BaseUrlEntity.AppRequestDomainsBean> appRequestDomains = baseUrlEntity.getAppRequestDomains();
        for (int i = 0; i < appRequestDomains.size(); i++) {
            BaseUrlEntity.AppRequestDomainsBean appRequestDomainsBean = appRequestDomains.get(i);
            if(newBaseUrl.equals(appRequestDomainsBean.getDomain())){
                appRequestDomainsBean.setCheck(true);
            }
        }
        baseUrlEntityArrayList.addAll(appRequestDomains);
        baseUrlAdapter.notifyDataSetChanged();

    }
    /**
     * 记录baseUrl访问速度
     * @param baseUrlEntity
     */
    private void chooseBestUrl(BaseUrlEntity baseUrlEntity) {

        List<BaseUrlEntity.AppRequestDomainsBean> appRequestDomains = baseUrlEntity.getAppRequestDomains();
        for (int i = 0; i < appRequestDomains.size(); i++) {
            BaseUrlEntity.AppRequestDomainsBean appRequestDomainsBean1 = appRequestDomains.get(i);
            appRequestDomainsBean1.setStartTime(System.currentTimeMillis());
            appRequestDomainsBean1.setEndTime(0);//每次都清空endTime
//            Observable<Response<ResponseBody>> compose = new HttpApiImpl(appRequestDomainsBean1.getDomain())
            String apiHost;
            if( mSharedPreferenceHelperImpl.getAppVestFlag()==1){
                SecurityConnection conn = AppVest.getServerIPAndPort("www.tiantian.com", 1002);
                apiHost=  String.format("http://%s:%s/web/ws/", conn.getServerIp(), conn.getServerPort());
            }else {
                apiHost = appRequestDomainsBean1.getDomain();
            }
            Observable<Response<ResponseBody>> compose = new HttpApiImpl(apiHost)
                    .pingTest()
                    .timeout(10000, TimeUnit.MILLISECONDS)
                    .compose(RxTransformerUtils.io_main());
            compose
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner)this)))
                    .subscribe(new BaseStringObserver<ResponseBody>() {
                        @Override
                        public void onSuccess(String result, Headers headers) {
                            LogUtils.e("onSuccess " + result);
                            appRequestDomainsBean1.setEndTime(System.currentTimeMillis());
                            appRequestDomainsBean1.setSuccess(true);
                            try {
                             mSharedPreferenceHelperImpl.setNewBaseUrl( appRequestDomainsBean1.getDomain());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            baseUrlAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFail(String msg) {
                            appRequestDomainsBean1.setSuccess(false);
                            baseUrlAdapter.notifyDataSetChanged();
                        }
                    });
        }


    }
    @OnClick(R.id.refresh_request_tv)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.refresh_request_tv:
                chooseBestUrl(baseUrlEntity);
                break;
            default:
                break;
        }
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
