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
 * 弃用 (人工充值和其他充值都在rechargeActivity中统一处理)
 */
public class ManualRechargeActivity extends BaseActivity implements BasePopupWindow2.OnPopClickListener {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    RechargeAdapter rechargeAdapter;
    ArrayList<Object>manualRechargeEntityArrayList = new ArrayList<>();
    //输入框的充值金额
    private String rechargeAmount;

    ManualRechargeData manualRechargeData;//上级activity传入的数据
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
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.人工充值));
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
         * 给item设置点击事件
         */
        rechargeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Object o = manualRechargeEntityArrayList.get(position);
/*                if(o instanceof AisleEntity){
                    *//**
                     *     点击切换通道
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
         * 给item子view添加点击事件
         */
        rechargeAdapter.addChildClickViewIds(R.id.recharge_copy_tv,R.id.weChat_linear,R.id.aliPay_linear,R.id.qq_linear,R.id.contact_now_btn);
        rechargeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Object o = manualRechargeEntityArrayList.get(position);
                switch (view.getId()){
                    case R.id.recharge_copy_tv:
                    /*
                    点击复制充值账号
                     */
                        Utils.copyStr("rechargeAccount",SharePreferencesUtil.getString(MyApplication.getInstance(),"nickname",""));
                        break;
                    case R.id.weChat_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.请先输入充值金额));
                        }else {
                            showTipPop(SkipType.WE_CHAT);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.aliPay_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.请先输入充值金额));
                        }else {
                            showTipPop(SkipType.ALI_PAY);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.qq_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.请先输入充值金额));
                        }else {
                            showTipPop(SkipType.QQ);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.contact_now_btn:
                    /*
                    客服充值
                     */
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.请先输入充值金额));
                        }else {

                            ManualEntity manualEntity = (ManualEntity) o;
                            /*
                            每次进入客服充值页面都销毁之前的悬浮窗
                             */
                            Class<? extends Service> service;
                            service = ChatHeadService.class;
                            stopService( new Intent(ManualRechargeActivity.this, service));
                            /*
                            每次进入客服充值先判断跟上次点击的是否是同一个item, 如果不是同一个则需要手动销毁ManualWebViewActivity的实例,使此次进入时能够执行完整的生命周期
                             */
                            if(!currentLoadUrl.equals(manualEntity.getOnlineService())){
                                //上次点击的客服路径和此次点击的不一致,销毁ManualWebViewActivity
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
         * 充值金额回调
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

        /*  中间item数据  设置充值账号*/
        MabualRechargeCenterEntity manuaMabualRechargeCenterEntity = new MabualRechargeCenterEntity(SharePreferencesUtil.getString(MyApplication.getInstance(), "nickname", ""),"");
        manualRechargeEntityArrayList.add(manuaMabualRechargeCenterEntity);
      
        /*底部充值入口item*/
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
     * 点击qq 微信 支付宝 的立即联系时, 复制充值的qq/微信/支付宝 号码
     * @param o
     */
    private void copyContactNum(Object o) {
        ManualEntity manualEntity = (ManualEntity) o;
        ClipboardManager clipboardManager= (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);//实例化clipboardManager对象
        ClipData mClipData=null;
        switch (skipType){
            case QQ:
                mClipData   =  ClipData.newPlainText("rechargeAccount",manualEntity.getQq());//复制文本数据到粘贴板  newPlainText
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
     * 点击立即联系后,弹出代充提示pop
     * 代充提示pop disMiss后弹出跳转倒计时pop
     * 跳转倒计时pop结束后 跳转微信/支付宝/qq
     */
    private void initManualTipPop() {
        if(manualRechargeTipPop==null){
            manualRechargeTipPop = new ManualRechargeTipPop(ManualRechargeActivity.this,true,"",Utils.getString(R.string.代充提示));
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
     * 代充提示pop disMiss后弹出的跳转倒计时pop
     */
    private void initSkipCountDownPop() {
        RechargeSkipCountDownPop rechargeSkipTipPop = new RechargeSkipCountDownPop(ManualRechargeActivity.this,false, skipType);
        rechargeSkipTipPop.setmOnDissmissListener(new BasePopupWindow2.OnDissmissListener() {
            @Override
            public void onDismiss() {
                if(rechargeSkipTipPop!=null){
                    rechargeSkipTipPop.getHandler().removeCallbacks(null);
                    /**
                     *  倒计时结束跳转
                     */
                    switch (skipType){
                        case QQ:
                            startOtherActivity("com.tencent.mobileqq","QQ");
                            break;
                        case WE_CHAT:
                            startOtherActivity("com.tencent.mm",Utils.getString(R.string.微信));
                            break;
                        case ALI_PAY:
                            startOtherActivity("com.eg.android.AlipayGphone",Utils.getString(R.string.支付宝));
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
            showToast(Utils.getString(R.string.检查到您的手机未安装)+appName+Utils.getString(R.string.请安装后再试));
        }
    }

}