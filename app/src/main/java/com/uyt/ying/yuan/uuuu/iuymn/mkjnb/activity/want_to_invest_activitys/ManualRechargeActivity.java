package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.want_to_invest_activitys;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ManualWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.RechargeAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MabualRechargeCenterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManualEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManualRechargeData;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ManualRechargeTipPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.RechargeSkipCountDownPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.serevice.ChatHeadService;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ?????? (?????????????????????????????????rechargeActivity???????????????)
 */
public class ManualRechargeActivity extends BaseActivity implements BasePopupWindow2.OnPopClickListener {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    RechargeAdapter rechargeAdapter;
    ArrayList<Object>manualRechargeEntityArrayList = new ArrayList<>();
    //????????????????????????
    private String rechargeAmount;

    ManualRechargeData manualRechargeData;//??????activity???????????????
    public  enum SkipType{
        WE_CHAT,
        ALI_PAY,
        QQ;
    }
    SkipType skipType;
    String currentLoadUrl="";
    ManualRechargeTipPop manualRechargeTipPop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_recharge);
        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.????????????));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        getIntentData();
        initRecycler();
    }
    private void getIntentData() {
        manualRechargeData = (ManualRechargeData) getIntent().getSerializableExtra("list");
    }

    @Override
    protected void init() {

    }

    private void initRecycler() {
        rechargeAdapter = new RechargeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rechargeAdapter);

        View footView= LayoutInflater.from(this).inflate(R.layout.manual_recharge_foot_layout,null);
        rechargeAdapter.addFooterView(footView);

        /**
         * ???item??????????????????
         */
        rechargeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Object o = manualRechargeEntityArrayList.get(position);
/*                if(o instanceof AisleEntity){
                    *//**
                     *     ??????????????????
                     *//*
                    AisleEntity aisleEntity = (AisleEntity) o;
                    for (int i = 0; i < manualRechargeEntityArrayList.size(); i++) {
                        Object itemBeen = manualRechargeEntityArrayList.get(i);
                        if(itemBeen instanceof AisleEntity ){
                            AisleEntity been = (AisleEntity) itemBeen;
                            been.setCheck(true);
                        }
                        aisleEntity.setCheck(false);
                    }
                }*/
                rechargeAdapter.notifyDataSetChanged();
            }

        });
        /**
         * ???item???view??????????????????
         */
        rechargeAdapter.addChildClickViewIds(R.id.recharge_copy_tv,R.id.weChat_linear,R.id.aliPay_linear,R.id.qq_linear,R.id.contact_now_btn);
        rechargeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Object o = manualRechargeEntityArrayList.get(position);
                switch (view.getId()){
                    case R.id.recharge_copy_tv:
                    /*
                    ????????????????????????
                     */
                        Utils.copyStr("rechargeAccount",SharePreferencesUtil.getString(MyApplication.getInstance(),"nickname",""));
                        break;
                    case R.id.weChat_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.????????????????????????));
                        }else {
                            showTipPop(SkipType.WE_CHAT);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.aliPay_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.????????????????????????));
                        }else {
                            showTipPop(SkipType.ALI_PAY);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.qq_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.????????????????????????));
                        }else {
                            showTipPop(SkipType.QQ);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.contact_now_btn:
                    /*
                    ????????????
                     */
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.????????????????????????));
                        }else {

                            ManualEntity manualEntity = (ManualEntity) o;
                            /*
                            ?????????????????????????????????????????????????????????
                             */
                            Class<? extends Service> service;
                            service = ChatHeadService.class;
                            stopService( new Intent(ManualRechargeActivity.this, service));
                            /*
                            ?????????????????????????????????????????????????????????????????????item, ??????????????????????????????????????????ManualWebViewActivity?????????,???????????????????????????????????????????????????
                             */
                            if(!currentLoadUrl.equals(manualEntity.getOnlineService())){
                                //??????????????????????????????????????????????????????,??????ManualWebViewActivity
                                ActivityUtil.getInstance().finishActivity(ManualWebViewActivity.class);
                            }
                            currentLoadUrl=manualEntity.getOnlineService();
                            SharePreferencesUtil.putString(MyApplication.getInstance(),"currentManualEntity", JSONObject.toJSONString(manualEntity));
                            ManualWebViewActivity.startAty(ManualRechargeActivity.this,manualEntity.getShopNickName(),manualEntity.getOnlineService());

                        }
                        break;
                    default:
                        break;
                }
            }
        });
        /**
         * ??????????????????
         */
        rechargeAdapter.setOnTextChangeListener(new RechargeAdapter.TextChangeListener() {
            @Override
            public void onTextChange(View view, String s) {

                rechargeAmount=s;
                if(StringMyUtil.isEmptyString(s)){
                    s="0";
                }
//                if(StringMyUtil.isNotEmpty(rechargeAmount)){
                for (int i = 0; i < manualRechargeEntityArrayList.size() ; i++) {
                    Object o = manualRechargeEntityArrayList.get(i);
                    if(o instanceof  ManualEntity){
                        if(new BigDecimal(s).compareTo(new BigDecimal(getManualEntity( o).getAmount()))>=0){
                            getManualEntity(o).setStatus(1);
                        }else {
                            getManualEntity(o).setStatus(0);
                        }
                        rechargeAdapter.setData(i,getManualEntity(o));
                    }
                }
//                }
            }
        });
        List<ManualEntity> rechargeBankList = manualRechargeData.getRechargeBankList();

        /*  ??????item??????  ??????????????????*/
        MabualRechargeCenterEntity manuaMabualRechargeCenterEntity = new MabualRechargeCenterEntity(SharePreferencesUtil.getString(MyApplication.getInstance(), "nickname", ""),"");
        manualRechargeEntityArrayList.add(manuaMabualRechargeCenterEntity);
      
        /*??????????????????item*/
        for (int i = 0; i < rechargeBankList.size(); i++) {
            manualRechargeEntityArrayList.add(rechargeBankList.get(i));
        }
        rechargeAdapter.setList(manualRechargeEntityArrayList);
    }

    private ManualEntity getManualEntity(Object o) {
        ManualEntity manualEntity = (ManualEntity) o;
        return manualEntity;
    }

    /**
     * ??????qq ?????? ????????? ??????????????????, ???????????????qq/??????/????????? ??????
     * @param o
     */
    private void copyContactNum(Object o) {
        ManualEntity manualEntity = (ManualEntity) o;
        ClipboardManager clipboardManager= (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);//?????????clipboardManager??????
        ClipData mClipData=null;
        switch (skipType){
            case QQ:
                mClipData   =  ClipData.newPlainText("rechargeAccount",manualEntity.getQq());//??????????????????????????????  newPlainText
                break;
            case ALI_PAY:
                mClipData   =  ClipData.newPlainText("rechargeAccount",manualEntity.getZfb());
                break;
            case WE_CHAT:
                mClipData   =  ClipData.newPlainText("rechargeAccount",manualEntity.getWx());
                break;
            default:
                break;
        }
        clipboardManager.setPrimaryClip(mClipData);
    }

    private void showTipPop(SkipType skipType) {
        initManualTipPop();
        this. skipType = skipType;
        manualRechargeTipPop.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);
        ProgressDialogUtil.darkenBackground(ManualRechargeActivity.this,0.5f);
    }

    /**
     * ?????????????????????,??????????????????pop
     * ????????????pop disMiss????????????????????????pop
     * ???????????????pop????????? ????????????/?????????/qq
     */
    private void initManualTipPop() {
        if(manualRechargeTipPop==null){
            manualRechargeTipPop = new ManualRechargeTipPop(ManualRechargeActivity.this,true,"",Utils.getString(R.string.????????????));
            manualRechargeTipPop.setOnPopClickListener(ManualRechargeActivity.this);
            manualRechargeTipPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    ProgressDialogUtil.darkenBackground(ManualRechargeActivity.this,1f);
                    initSkipCountDownPop();
                }
            });
        }
    }

    /**
     * ????????????pop disMiss???????????????????????????pop
     */
    private void initSkipCountDownPop() {
        RechargeSkipCountDownPop rechargeSkipTipPop = new RechargeSkipCountDownPop(ManualRechargeActivity.this,false, skipType);
        rechargeSkipTipPop.setmOnDissmissListener(new BasePopupWindow2.OnDissmissListener() {
            @Override
            public void onDismiss() {
                if(rechargeSkipTipPop!=null){
                    rechargeSkipTipPop.getHandler().removeCallbacks(null);
                    /**
                     *  ?????????????????????
                     */
                    switch (skipType){
                        case QQ:
                            startOtherActivity("com.tencent.mobileqq","QQ");
                            break;
                        case WE_CHAT:
                            startOtherActivity("com.tencent.mm",Utils.getString(R.string.??????));
                            break;
                        case ALI_PAY:
                            startOtherActivity("com.eg.android.AlipayGphone",Utils.getString(R.string.?????????));
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        rechargeSkipTipPop.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);
        ProgressDialogUtil.darkenBackground(ManualRechargeActivity.this,0.5f);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Override
    public void onPopClick(View view) {
        switch (view.getId()){
            case R.id.manual_recharge_pop_sure_tv:

                if(manualRechargeTipPop!=null){
                    manualRechargeTipPop.dismiss();
                }
                break;
            default:
                break;
        }
    }


    private void startOtherActivity(String pakegeName,String appName) {
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage(pakegeName);
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            showToast(Utils.getString(R.string.??????????????????????????????)+appName+Utils.getString(R.string.??????????????????));
        }
    }

}