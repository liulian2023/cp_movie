package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PreviewCacheModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TollEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;

public class AutoTollPop extends PopupWindow implements View.OnClickListener {
    ImageView toll_close_iv;
    Button toll_amount_time_btn;
    TextView toll_amount_tv;
    ImageView toll_amount_iv;
    ImageView toll_back_iv;
    TextView toll_type_tv;
    TextView live_price_tv;
    TextView toll_back_tv;
    Switch pay_type_switch;
    LiveFragment liveFragment;
    private View rootView;
    public LayoutInflater inflater;
    public  TextView count_down_tv;
    Context mContext;
    private String anchorSubscribeEndDate;
    private String anchorSubscribe;
    private String autoAnchorSubscribe;
    private String amount="0";
    int previewTime=15;
    String CONTINUE_WATCH = Utils.getString(R.string.????????????);
    PreviewHanlder previewHanlder;
    PopStatus popStatus;
    long watchCountTime;//????????????????????????????????????????????????
    public enum PopStatus{
        NORMAL,//????????????(???????????????????????????)
        SHOW_COUNTDOWN,//????????????????????????(?????????????????????)
        PREVIEW;//????????????(?????????????????????)
    }
    public AutoTollPop(Context context ,LiveFragment liveFragment,PopStatus popStatus) {
        super(context);
        mContext = context;
        this.liveFragment = liveFragment;
        this.popStatus = popStatus;
        previewHanlder = new PreviewHanlder();
        initView();
        initPop();
        getMoney();
    }

    private void initPop() {
        this.setContentView(rootView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //???????????????????????????
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground((Activity)mContext,1f);
                previewHanlder.removeCallbacksAndMessages(null);
            }
        });
    }

    public void initView() {
        rootView = inflater.from(mContext).inflate(R.layout.auto_pay_pop_layout,null);
        toll_close_iv = rootView.findViewById(R.id.toll_close_iv);
        count_down_tv = rootView.findViewById(R.id.count_down_tv);
        toll_amount_time_btn = rootView.findViewById(R.id.toll_amount_time_btn);
        toll_amount_tv = rootView.findViewById(R.id.toll_amount_tv);
        toll_amount_iv = rootView.findViewById(R.id.toll_amount_iv);
        toll_type_tv = rootView.findViewById(R.id.toll_type_tv);
        live_price_tv = rootView.findViewById(R.id.live_price_tv);
        toll_back_iv = rootView.findViewById(R.id.toll_back_iv);
        toll_back_tv = rootView.findViewById(R.id.toll_back_tv);

        pay_type_switch = rootView.findViewById(R.id.pay_type_switch);
        if(popStatus == PopStatus.NORMAL){
            count_down_tv.setText(Utils.getString(R.string.????????????????????????));
            toll_amount_time_btn.setText(Utils.getString(R.string.??????));
            live_price_tv.setText(liveFragment.getAnchorSubscribe()+Utils.getString(R.string.???10??????));
            liveFragment.drawerlayout.closeDrawer(GravityCompat.END);
//            liveFragment.can_not_play_iv.setVisibility(View.VISIBLE);
            liveFragment.isNeedToll=true;
            liveFragment.myjzvd.goOnPlayOnPause();
        }else if(popStatus == PopStatus.SHOW_COUNTDOWN){
            //???????????????
            toll_amount_time_btn.setText(Utils.getString(R.string.????????????));
            live_price_tv.setText(liveFragment.getAnchorSubscribe()+Utils.getString(R.string.???10??????));
            subscribeCountdown();

        }else {
            //????????????????????????
            initPreviewTimesCache();
            //???????????????
            toll_amount_time_btn.setText(Utils.getString(R.string.??????));
            live_price_tv.setText(liveFragment.getAnchorSubscribe()+Utils.getString(R.string.???10??????));
            count_down_tv.setText(Utils.getString(R.string.??????????????????????????????????????????)+previewTime+Utils.getString(R.string.???));
            //??????????????????
            liveFragment. drawerlayout.closeDrawer(GravityCompat.END);
            previewHanlder.postDelayed(previewRunnable,1000);
        }

        anchorSubscribe = liveFragment.getAnchorSubscribe();
        autoAnchorSubscribe = liveFragment.getAutoAnchorSubscribe();
        if(autoAnchorSubscribe.equals("1")){
            pay_type_switch.setChecked(true);
        }else {
             pay_type_switch.setChecked(false);
        }

        toll_close_iv.setOnClickListener(this::onClick);
        toll_amount_tv.setOnClickListener(this::onClick);
        toll_amount_iv.setOnClickListener(this::onClick);
        toll_amount_time_btn.setOnClickListener(this::onClick);
        pay_type_switch.setOnClickListener(this::onClick);
        toll_back_tv.setOnClickListener(this::onClick);
        toll_back_iv.setOnClickListener(this::onClick);
        pay_type_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    autoAnchorSubscribe="1";
                    if(liveFragment.toll_type_tv!=null){
                        liveFragment.toll_type_tv.setText(Utils.getString(R.string.??????));
                    }

                }else {
                    autoAnchorSubscribe="0";
                    if(liveFragment.toll_type_tv!=null){
                        liveFragment.toll_type_tv.setText(Utils.getString(R.string.??????));
                    }
                }
                if(liveFragment!=null){
                    liveFragment.setAutoAnchorSubscribe(autoAnchorSubscribe);
                }
            }
        });
    }

    /**
     * ????????????????????????(???????????????add??????dataBean)
     */
    private void initPreviewTimesCache() {
        PreviewCacheModel previewCacheModel;
        List<PreviewCacheModel.DataBean> dataBeanList;
        String cache = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.PREVIEW_CACHE, "");
        if(StringMyUtil.isNotEmpty(cache)){
            previewCacheModel = JSONObject.parseObject(cache, PreviewCacheModel.class);
            dataBeanList = previewCacheModel.getDataBeanList();
        }else {
            previewCacheModel = new PreviewCacheModel();
            dataBeanList = new ArrayList<PreviewCacheModel.DataBean>();
        }
        PreviewCacheModel.DataBean dataBean = new PreviewCacheModel.DataBean();
        dataBean.setDate(System.currentTimeMillis());
        dataBean.setUrl(liveFragment.getmLiveData().getLiveUrl());
        dataBeanList.add(dataBean);
        previewCacheModel.setDataBeanList(dataBeanList);
        SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.PREVIEW_CACHE,JSONObject.toJSONString(previewCacheModel));
    }

    private void subscribeCountdown() {
        liveFragment.tollAmountSubject
                .observeOn(AndroidSchedulers.mainThread())//??????????????????
                .subscribeOn(Schedulers.io())//?????????io??????
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Long value) {
                        watchCountTime = value;
                        if(value<=0){
                            count_down_tv.setText(Utils.getString(R.string.????????????????????????));
                            toll_amount_time_btn.setText(Utils.getString(R.string.??????));
                        }else {
                            String str_time;
                            int mHour = (int) ((value / 1000) / (60 * 60));  //  ???3600 ??????  ????????????
                            int mMin = (int) (((value / 1000) % (60 * 60)) / 60);//  ???3600 ????????????60 ??????????????????
                            int mSecond = (int) ((value / 1000) % 60); //  ???60 ??????  ??????????????????
                            str_time = DateUtil.timeMode(mHour)+":"+ DateUtil.timeMode(mMin) + ":" + DateUtil.timeMode(mSecond);
                            count_down_tv.setText(Utils.getString(R.string.??????????????????)+str_time);
                            if(value>30*1000){
                                toll_amount_time_btn.setText(CONTINUE_WATCH);
                            }else {
                                toll_amount_time_btn.setText(Utils.getString(R.string.??????));
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    Runnable previewRunnable = new Runnable() {
        @Override
        public void run() {
            boolean continueCountDown=true;
            if(liveFragment!=null&&!liveFragment.isUnBind){
                if(previewTime<=0){
                    count_down_tv.setText(Utils.getString(R.string.??????????????????));
                    if( liveFragment!=null/*&& liveFragment.can_not_play_iv!=null*/){
//                        liveFragment.can_not_play_iv.setVisibility(View.VISIBLE);
                        liveFragment.isNeedToll=true;
                    }
                    liveFragment.myjzvd.goOnPlayOnPause();
                    continueCountDown=false;
                }else {
                    previewTime--;
                    count_down_tv.setText(Utils.getString(R.string.??????????????????????????????????????????)+previewTime+Utils.getString(R.string.???));
                }
                if(continueCountDown){
                    previewHanlder.postDelayed(this,1000);
                }
            }
        }
    };
    class PreviewHanlder extends Handler{}
       /*
     ??????????????????
       */
    private void getMoney() {
        HashMap<String, Object> dataMoney = new HashMap<>();//REQUEST_06rzq
        long user_id = SharePreferencesUtil.getLong(mContext,"user_id",0L);
        dataMoney.put("user_id",user_id);
        Utils.docking(dataMoney, RequestUtil.REQUEST_06rzq, 1, new DockingUtil.ILoaderListener() {
            @Override
            public void success(String content) {
                SharePreferencesUtil.putString(mContext,"moneyString",content);
                JSONObject jsonObject = JSONObject.parseObject(content);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                /*
                ????????????
                 */
                amount = memberMoney.getString("amount");
                toll_amount_tv.setText(amount);
            }
            @Override
            public void failed(MessageHead messageHead) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        FragmentActivity activity = liveFragment.getActivity();
        switch (v.getId()){
            case R.id.toll_close_iv:
                /**
                 * ???????????????????????????
                 */
                AutoTollPop.this.dismiss();
                if(popStatus!=PopStatus.SHOW_COUNTDOWN){
                    //????????????????????????????????????,?????????????????????????????????????????????,????????????????????????, ????????????????????????
                    initCloseIvClick(activity);
                }else {
                    //??????????????????????????????,???????????????????????????, ?????????????????????????????????????????????
                    if(previewTime<=0){
                        initCloseIvClick(activity);
                    }
                }
                break;
            case R.id.toll_amount_tv:
            case R.id.toll_amount_iv:
                String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                if(isTest.equals("-1")){
                    Utils.initSkipVisitorSafeCenterPop(mContext,(Activity) mContext);
                }else {

                    mContext.startActivity(new Intent(mContext, RechargeActivity.class));
                }
                break;
            case R.id.toll_amount_time_btn:
                if(toll_amount_time_btn.getText().equals(CONTINUE_WATCH)/*||watchCountTime<=30*1000*/){
                    //???????????????????????????,??????????????????pop
                    AutoTollPop.this.dismiss();
                }else {
                    requestToll();
                }
                break;
            case R.id.pay_type_switch:
//                double mineAmount = Double.parseDouble(amount);
                double roomAmount = Double.parseDouble(liveFragment.getAnchorSubscribe());
/*                if(pay_type_switch.isChecked()){
                    if(mineAmount <=0||mineAmount<roomAmount){
                        ToastUtils.showToast(Utils.getString(R.string.??????????????????,????????????));
                        pay_type_switch.setChecked(false);
                        return;
                    }
                }*/
                if(roomAmount >0){
                    if(StringMyUtil.isNotEmpty(liveFragment.getAnchorSubscribeEndDate())){
                        //????????????????????????,???????????????????????????????????????,???????????????,????????????????????????????????????
                        if(liveFragment!=null){
                            requestSwichAuto();
                        }
                    }
                }

                break;
            case R.id.toll_back_iv:
            case R.id.toll_back_tv:
                if(activity!=null){
                    activity.finish();
                }
                break;
            default:
                break;
        }
    }

    private void initCloseIvClick(FragmentActivity activity) {
        if(liveFragment!=null){
            if(activity !=null){
                if(activity instanceof LiveActivity){
                    LiveActivity liveActivity = (LiveActivity) activity;
//                                activity.finish();
                    //?????????????????????????????????, ??????viewpagerDataList2.size??????
//                    if(liveActivity.viewpagerDataList2.size()>liveActivity.curPosition+1){
                    if(liveActivity.viewpagerDataList.size()>liveActivity.curPosition+1){
                        liveActivity.mViewPager.setCurrentItem(liveActivity.curPosition+1);
                    }else {
                        if(liveActivity!=null){
                            ToastUtil.showToast(Utils.getString(R.string.???????????????????????????));
                            liveActivity.finish();
                        }
                    }
                }
            }
        }
    }

    /**
     *??????????????????
     */
    private void requestSwichAuto() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("remoteLiveManagementId",liveFragment.getmLiveData().getRemoteLiveManagementId());
        data.put("autoAnchorSubscribe",autoAnchorSubscribe);
        data.put("anchorAccount",liveFragment.getmLiveData().getAnchorAccount());
        HttpApiUtils.CpRequest(liveFragment.getActivity(),liveFragment, RequestUtil.CHANGE_AUTO_TOLL, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String autoAnchorSubscribe = jsonObject.getString("autoAnchorSubscribe");
                liveFragment.setAutoAnchorSubscribe(autoAnchorSubscribe);
                if(autoAnchorSubscribe.equals("1")){
                    ToastUtil.showToast(Utils.getString(R.string.?????????????????????));
                }else {
                    ToastUtil.showToast(Utils.getString(R.string.?????????????????????));
                }

            }
            @Override
            public void onFailed(String msg) {
                //???????????????,???switch????????????????????????????????????
                if(pay_type_switch.isChecked()){
                    pay_type_switch.setChecked(false);
                    ToastUtil.showToast(Utils.getString(R.string.????????????????????????));
                }else {
                    pay_type_switch.setChecked(true);
                    ToastUtil.showToast(Utils.getString(R.string.????????????????????????));
                }
            }
        });
    }

    /**
     * ??????????????????
     */
    public void  requestToll() {
        HashMap<String, Object> data = new HashMap<>();
        if(liveFragment!=null){
            data.put("remoteLiveManagementId",liveFragment.getmLiveData().getRemoteLiveManagementId());
            data.put("autoAnchorSubscribe",autoAnchorSubscribe);
            HttpApiUtils.CpRequest(liveFragment.getActivity(), liveFragment,RequestUtil.TOLL_ANCHOR, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    AutoTollPop.this.dismiss();
                    TollEntity tollEntity = JSONObject.parseObject(result, TollEntity.class);
                    anchorSubscribe = tollEntity.getAnchorSubscribe();
                    liveFragment.setAnchorSubscribe(anchorSubscribe);
                    anchorSubscribeEndDate = tollEntity.getAnchorSubscribeEndDate();
                    liveFragment.setAnchorSubscribeEndDate(anchorSubscribeEndDate);
                    autoAnchorSubscribe = tollEntity.getAutoAnchorSubscribe();
                    liveFragment.setAutoAnchorSubscribe(autoAnchorSubscribe);
                    toll_amount_time_btn.setText(Utils.getString(R.string.??????));
                    live_price_tv.setText(anchorSubscribe +"/"+Utils.getString(R.string.?????????));
                    liveFragment.myjzvd.startVideo();

                    liveFragment.isNeedToll=false;
//                    liveFragment.can_not_play_iv.setVisibility(View.GONE);
                    liveFragment. drawerlayout.openDrawer(GravityCompat.END);
/*                    if(autoAnchorSubscribe.equals("1")){
                        pay_type_switch.setChecked(true);
                    }else {
                        pay_type_switch.setChecked(false);
                    }*/
                    try {
                        if(liveFragment!=null&&!liveFragment.isUnBind){
                            liveFragment.initTollCountDown();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(String msg) {
                    liveFragment.isNeedToll=true;

                    liveFragment.myjzvd.goOnPlayOnPause();
                }
            });
        }
    }

}
