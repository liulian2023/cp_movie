package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.OnLineKeFuActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BankCardRechargeCenterEntity;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.AliPayViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ManualWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.want_to_invest_activitys.ManualRechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.want_to_invest_activitys.PayWebviewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.RechargeAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.RechargeHeadAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManualEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MabualRechargeCenterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RechargeCenterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RechargeModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.USDTEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ManualRechargeTipPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.RechargeSkipCountDownPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.USTDQrCodePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.serevice.ChatHeadService;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActivityUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.QRCodeUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SavePhoto;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dalong.marqueeview.MarqueeView;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.jetbrains.annotations.NotNull;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;

public class RechargeActivity extends BaseActivity implements View.OnClickListener, BasePopupWindow2.OnPopClickListener {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.recharge_marquee_view)
    MarqueeView recharge_marquee_view;
    @BindView(R.id.toolbar_right_iv)
    ImageView toolbar_right_iv;
    @BindView(R.id.recharge_recycler)
    RecyclerView recharge_recycler;
    RechargeAdapter rechargeAdapter ;
    ArrayList<Object>rechargeEntityList = new ArrayList<>();
    RecyclerView recharge_head_recycler;
    RechargeHeadAdapter rechargeHeadAdapter;
    ArrayList<RechargeModel.BankAllListBean> rechargeHeadArrayList = new ArrayList<>();
    List<RechargeModel.BankAllListBean> bankAllList;
    RechargeModel.BankAllListBean currentBankAllBean;

    TextView payee_name_tv;
    TextView payee_name_copy_tv;
    TextView payee_account_tv;
    TextView payee_account_copy_tv;
    TextView bank_name_tv;
    TextView bank_name_two_tv;
    TextView bank_name_copy_tv;
    TextView bank_name_two_copy_tv;
    WebView recharge_tip_wbv;
    Button recharge_sure_btn;
    ImageView qr_image_iv;
    Group qr_image_group;
    Group bank_name_group;
    Group bank_name_two_group;
    ConstraintLayout recharge_foot_wrap_constraintLayout;
    View footView;
    RechargeModel.BankAllListBean.RechargeBankListBean currentAisleEntity;
    private PopupWindow popupWindowQr;//二维码pop
    private String price;//非人工充值的充值金额
    private String rechargeAmount;//人工充值的充值金额
    //非人工充值的付款人输入框回调
    private String nickName;
    private EditText wx_amount_etv;
    private EditText payer_etv;
    private EditText usdt_id_etv;
    private String ustdId;
    private EditText currency_amount_etv;
    private String ustdAmount="0";//ustd 充值金额
    private String ustdNum="0";// ustd 充值货币数量
    private String noticeContent;
    private ManualRechargeTipPop noticeTipPop;
    private String TAG = "RechargeActivity";
    boolean isWebViewLoad =false;//是否是跳转webView充值,跳转webView充值返回后不充值price

    ManualRechargeActivity.SkipType skipType;
    String currentLoadUrl="";
    ManualRechargeTipPop manualRechargeTipPop;
    private String manualTip=Utils.getString(R.string.温馨提示：\n1.代充只负责充值，如果您有其他问题，请联系我们在线客服处理，代充如果向您骚扰或者其他行为，欢迎向我们举报，举报有奖。2.代充人员不定期更换，为避免不必要的损失，每次支付请到平台提单，以官方为准，谢谢！3.代充时请向代充人员提供您的会员账号（会员账号可以在代充页面复制），以及金额！);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.充值));
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        ButterKnife.bind(this);
        toolbar_right_iv.setVisibility(View.VISIBLE);
        toolbar_right_iv.setImageResource(R.drawable.kefu_icon);
        toolbar_right_iv.setOnClickListener(this);
        initRecycler();
        requestNotice();
    }

    /**
     * 请求充值公告
     */
    private void requestNotice() {
        recharge_marquee_view.setOnClickListener(this);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type",8);
        data.put("pageNo",1);
        data.put("pageSize",15);
        HttpApiUtils.CPnormalRequest(this, null, RequestUtil.REQUEST_33r, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                String text="";
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray extensionNoticeInfoList = jsonObject1.getJSONArray("extensionNoticeInfoList");
                if(extensionNoticeInfoList.size()!=0){
                    JSONObject jsonObject = extensionNoticeInfoList.getJSONObject(0);
                    noticeContent = jsonObject.getString("content");
                    recharge_marquee_view.setText(jsonObject.getString("contentTxt"));
                    recharge_marquee_view.startScroll();
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void initRecycler() {
        rechargeAdapter = new RechargeAdapter();
//        recharge_recycler.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recharge_recycler.setLayoutManager(gridLayoutManager);
        recharge_recycler.setAdapter(rechargeAdapter);
        /**
         * 通道名布局一行显示3个 其他占满一行
         */
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = rechargeAdapter.getItemViewType(position);
                if(itemViewType == RechargeAdapter.TYPE_ONE){
                    return 1;
                }else {
                    return 3;
                }
            }
        });

        View headView = LayoutInflater.from(this).inflate(R.layout.recharge_recycler_head_view,null);
        rechargeAdapter.addHeaderView(headView);
        bindHeadView(headView);
        footView = LayoutInflater.from(this).inflate(R.layout.recharge_recycler_foot_layout,null);
        bindFootView(footView);
        /**
         * 给item子view添加点击事件
         */
        rechargeAdapter.addChildClickViewIds(R.id.recharge_copy_tv,R.id.weChat_linear,R.id.aliPay_linear,R.id.qq_linear,R.id.contact_now_btn,
                R.id.copy_address_linear,R.id.qr_code_linear,R.id.app_tutorial_tv,R.id.imtoken_tutorial_tv);

        itemChildClick();
        rechargeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Object object = rechargeEntityList.get(position);
                if(object instanceof  RechargeModel.BankAllListBean.RechargeBankListBean){
                    /**
                     * 通道名点击事件
                     * aisleEntity 当前点击的bean
                     */
                    int index=0;
                    RechargeModel.BankAllListBean.RechargeBankListBean aisleEntity = (RechargeModel.BankAllListBean.RechargeBankListBean) object;
                    aisleItemClick(index, aisleEntity);
                }
            }
        });
        itemTextChangeListener();
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestBankAllList();
    }

    private void itemTextChangeListener() {
        rechargeAdapter.setOnTextChangeListener(new RechargeAdapter.TextChangeListener() {
            @Override
            public void onTextChange(View view, String s) {
                switch (view.getId()){
                    case R.id.wx_amount_etv:
                        //人工充值的充值金额输入回调
                        price = s;
                        wx_amount_etv= (EditText) view;
                        break;
                    case R.id.payer_etv:
                        //非人工充值的付款人输入框回调
                        nickName=s;
                        payer_etv = (EditText) view;
                        break;
                    case R.id.recharge_amount_etv:
                        //选中人工充值通道时的充值金额输入框回调
                        rechargeAmount=s;
                        if(StringMyUtil.isEmptyString(s)){
                            s="0";
                        }

                        for (int i = 0; i < rechargeEntityList.size() ; i++) {
                            Object o = rechargeEntityList.get(i);
                            if(o instanceof ManualEntity){
                                if(new BigDecimal(s).compareTo(new BigDecimal(getManualEntity( o).getAmount()))>=0){
                                    getManualEntity(o).setStatus(1);
                                }else {
                                    getManualEntity(o).setStatus(0);
                                }
                                rechargeAdapter.setData(i,getManualEntity(o));
                            }
                        }
                        break;
                    case R.id.usdt_id_etv:
                        /**
                         * ustd id输入回调
                         */
                        usdt_id_etv = (EditText) view;
                        ustdId=s;
                        break;
                    case R.id.currency_amount_etv:
                        currency_amount_etv = (EditText) view;
                        ustdNum = s;
                        BigDecimal amount = new BigDecimal(StringMyUtil.isEmptyString(s)?"0":s).multiply(new BigDecimal(currentAisleEntity.getUsdtRate())).setScale(4, BigDecimal.ROUND_FLOOR);
                        ustdAmount = amount+"";
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void itemChildClick() {
        rechargeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Object o = rechargeEntityList.get(position);
                switch (view.getId()){
                    case R.id.recharge_copy_tv:
                    /*
                    点击复制充值账号
                     */
                        Utils.copyStr("rechargeAccount", SharePreferencesUtil.getString(MyApplication.getInstance(),"nickname",""));
                        break;
                    case R.id.weChat_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.请先输入充值金额));
                        }else {
                            showTipPop(ManualRechargeActivity.SkipType.WE_CHAT);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.aliPay_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.请先输入充值金额));
                        }else {
                            showTipPop(ManualRechargeActivity.SkipType.ALI_PAY);
                            copyContactNum(o);
                        }
                        break;
                    case R.id.qq_linear:
                        if(StringMyUtil.isEmptyString(rechargeAmount)){
                            showToast(Utils.getString(R.string.请先输入充值金额));
                        }else {
                            showTipPop(ManualRechargeActivity.SkipType.QQ);
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
                            stopService( new Intent(RechargeActivity.this, service));
                            /*
                            每次进入客服充值先判断跟上次点击的是否是同一个item, 如果不是同一个则需要手动销毁ManualWebViewActivity的实例,使此次进入时能够执行完整的生命周期
                             */
                            if(!currentLoadUrl.equals(manualEntity.getOnlineService())){
                                //上次点击的客服路径和此次点击的不一致,销毁ManualWebViewActivity
                                ActivityUtil.getInstance().finishActivity(ManualWebViewActivity.class);
                            }
                            currentLoadUrl=manualEntity.getOnlineService();
                            SharePreferencesUtil.putString(MyApplication.getInstance(),"currentManualEntity", JSONObject.toJSONString(manualEntity));
                            ManualWebViewActivity.startAty(RechargeActivity.this,manualEntity.getShopNickName(),manualEntity.getOnlineService());
                        }
                        break;
                    case R.id.qr_code_linear:
                        /**
                         * ustd 收款二维码
                         */
                        USTDQrCodePop ustdQrCodePop = new USTDQrCodePop(RechargeActivity.this,true,RechargeActivity.this,currentAisleEntity);
                        ustdQrCodePop.showAtLocation(recharge_recycler,Gravity.CENTER,0,0);
                        ProgressDialogUtil.darkenBackground(RechargeActivity.this,0.7f);
                        break;
                    case R.id.copy_address_linear:
                        /**
                         * 复制USTD收款地址
                         */
                        Utils.copyStr("USTD",currentAisleEntity.getAccount());
                        break;
                    case R.id.app_tutorial_tv:
                    case R.id.imtoken_tutorial_tv:
                        USTDWebViewActivity.startAty(RechargeActivity.this,initUrl());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private ManualEntity getManualEntity(Object o) {
        ManualEntity manualEntity = (ManualEntity) o;
        return manualEntity;
    }
    /**
     * webview url 相关初始化
     */
    private String initUrl() {
        String url="";
        String frontUrl = SharePreferencesUtil.getString(MyApplication.getInstance(), "frontUrl", "");
//        if(StringMyUtil.isNotEmpty(frontUrl)){
             url=frontUrl+"teaching";
//        }
        return url;
    }
    private void showTipPop(ManualRechargeActivity.SkipType skipType) {
        initManualTipPop();
        this. skipType = skipType;
        manualRechargeTipPop.showAtLocation(recharge_recycler, Gravity.CENTER, 0, 0);
        ProgressDialogUtil.darkenBackground(RechargeActivity.this,0.5f);
    }

    /**
     * 点击立即联系后,弹出代充提示pop
     * 代充提示pop disMiss后弹出跳转倒计时pop
     * 跳转倒计时pop结束后 跳转微信/支付宝/qq
     */
    private void initManualTipPop() {
        if(manualRechargeTipPop==null){
            manualRechargeTipPop = new ManualRechargeTipPop(RechargeActivity.this,true,checkRemark(currentAisleEntity.getRemark(),manualTip),Utils.getString(R.string.代充提示));
            manualRechargeTipPop.setOnPopClickListener(this);
            manualRechargeTipPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    ProgressDialogUtil.darkenBackground(RechargeActivity.this,1f);
                    initSkipCountDownPop();
                }
            });
        }
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
    /**
     * 代充提示pop disMiss后弹出的跳转倒计时pop
     */
    private void initSkipCountDownPop() {
        RechargeSkipCountDownPop rechargeSkipTipPop = new RechargeSkipCountDownPop(RechargeActivity.this,false, skipType);
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
        rechargeSkipTipPop.showAtLocation(recharge_recycler, Gravity.CENTER, 0, 0);
        ProgressDialogUtil.darkenBackground(RechargeActivity.this,0.5f);
    }
    private void bindFootView(View footView) {
        payee_name_tv = footView.findViewById(R.id.payee_name_tv);
        payee_name_copy_tv = footView.findViewById(R.id.payee_name_copy_tv);
        payee_account_tv = footView.findViewById(R.id.payee_account_tv);
        payee_account_copy_tv = footView.findViewById(R.id.payee_account_copy_tv);
        bank_name_copy_tv = footView.findViewById(R.id.bank_name_copy_tv);
        bank_name_two_copy_tv = footView.findViewById(R.id.bank_name_two_copy_tv);
        bank_name_tv = footView.findViewById(R.id.bank_name_tv);
        bank_name_two_tv = footView.findViewById(R.id.bank_name_two_tv);
        recharge_sure_btn = footView.findViewById(R.id.recharge_sure_btn);
        recharge_tip_wbv = footView.findViewById(R.id.recharge_tip_wbv);
        qr_image_iv = footView.findViewById(R.id.qr_image_iv);
        qr_image_group = footView.findViewById(R.id.qr_image_group);
        bank_name_two_group = footView.findViewById(R.id.bank_name_two_group);
        bank_name_group = footView.findViewById(R.id.bank_name_group);
        recharge_foot_wrap_constraintLayout = footView.findViewById(R.id.recharge_foot_wrap_constraintLayout);
        payee_name_copy_tv.setOnClickListener(this);
        bank_name_copy_tv.setOnClickListener(this);
        bank_name_two_copy_tv.setOnClickListener(this);
        payee_account_copy_tv.setOnClickListener(this);
        recharge_sure_btn.setOnClickListener(this);

        WebSettings settings = recharge_tip_wbv.getSettings();
        settings.setJavaScriptEnabled(true);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_right_iv:
                startActivity(new Intent(RechargeActivity.this,OnLineKeFuActivity.class));
                break;
            case R.id.payee_name_copy_tv:
                Utils.copyStr("payee_name",payee_account_tv.getText().toString());
                break ;
            case R.id.bank_name_copy_tv:
                Utils.copyStr("bank_name",bank_name_tv.getText().toString());
                break ;
            case R.id.bank_name_two_copy_tv:
                Utils.copyStr("bank_name_two_tv", bank_name_two_tv.getText().toString());
                break;
            case R.id.payee_account_copy_tv:
                Utils.copyStr("payee_account",payee_account_tv.getText().toString());
                break ;
            case R.id.recharge_sure_btn:
                if(checkEtv()&&Utils.isNotFastClick()){
                    requestRecharge();
                }
                break;
            case R.id.recharge_marquee_view:
                if(noticeTipPop==null){
                    noticeTipPop = new ManualRechargeTipPop(this,true,noticeContent,Utils.getString(R.string.重要通知));
                    noticeTipPop.setOnPopClickListener(this);
                }
                noticeTipPop.showAtLocation(recharge_marquee_view,Gravity.CENTER,0,0);
                ProgressDialogUtil.darkenBackground(RechargeActivity.this,0.5f);
                break;
            default:
                break;
        }
    }

    /**
     * 请求充值(非人工充值通道)
     */
    private void requestRecharge() {
        if(currentBankAllBean == null||currentBankAllBean==null){
            return;
        }
        isWebViewLoad=false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String depositDate = simpleDateFormat.format(new Date());//充值时间
        HashMap<String, Object> data = new HashMap<>();
        if(currentAisleEntity.getType().equals("16")){
            //usdt 充值
            data.put("price",ustdAmount);
            data.put("usdtId",ustdId);
        }else {
            //其他充值
            data.put("price",price);
        }
        data.put("bank_id",currentAisleEntity.getId());
        data.put("name",nickName);//付款人(微信昵称)
        data.put("depositDate",depositDate);
        data.put("type",currentAisleEntity.getType());//充值类型 0银行 1微信 2支付宝 5多得宝 6佰富 7利盈 9百汇付
        HttpApiUtils.CpRequest(RechargeActivity.this,null, RequestUtil.CREATE_RECHARGE_OLDER, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String payTypeImage = jsonObject.getString("payTypeImage");//二维码路径
                String windowLocationHref = jsonObject.getString("windowLocationHref");//跳转路径
                String htmlContent = jsonObject.getString("htmlContent");
                String htmlStr = jsonObject.getString("htmlStr");
                //有二维码路径则显示二维码
                if(!StringMyUtil.isEmptyString(payTypeImage)){
                    LayoutInflater inflater = LayoutInflater.from(RechargeActivity.this);
                    View inflate = inflater.inflate(R.layout.qr_code_popupwindow, null);//读取布局管理器
                    popupWindowQr = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);//实例化popupWindow
                    popupWindowQr.setAnimationStyle(R.style.popupAnimationNormol2);//设置进出动画
                    final ImageView imageView =inflate.findViewById(R.id.qr_image);
                    Bitmap qrCodeBitmap = QRCodeUtil.createQRImage(payTypeImage, 150, 150, ErrorCorrectionLevel.H);
                    imageView.setImageBitmap(qrCodeBitmap);
                    popupWindowQr.showAtLocation(getWindow().getDecorView() , Gravity.CENTER, 0, 50); // 显示弹出窗口//显示弹出窗口
                    ProgressDialogUtil.darkenBackground(RechargeActivity.this,0.3f);
                    TextView sava =inflate.findViewById(R.id.save_photo);
                    sava.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String[] PERMISSIONS = {
                                    "android.permission.READ_EXTERNAL_STORAGE",
                                    "android.permission.WRITE_EXTERNAL_STORAGE" };
                            //检测是否有写的权限
                            int permission = ContextCompat.checkSelfPermission(RechargeActivity.this,
                                    "android.permission.WRITE_EXTERNAL_STORAGE");
                            if (permission != PackageManager.PERMISSION_GRANTED) {

                                ActivityCompat.requestPermissions(RechargeActivity.this, PERMISSIONS,1);
                            }
                            try {
                                //创建savephoto类保存图片
                                SavePhoto savePhoto = new SavePhoto(RechargeActivity.this);
                                savePhoto.SaveBitmapFromView(imageView);
                                showToast(Utils.getString(R.string.保存成功));
                                popupWindowQr.dismiss();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    ImageView backImg=inflate.findViewById(R.id.back_img);
                    backImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindowQr.dismiss();
                        }
                    });
                    popupWindowQr.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            ProgressDialogUtil.darkenBackground(RechargeActivity.this,1f);
                        }
                    });
//

                }
                //返回网址路径,webView跳转
                else if(!StringMyUtil.isEmptyString(windowLocationHref)||StringMyUtil.isNotEmpty(htmlStr)){
                    AliPayViewActivity.startAty(RechargeActivity.this,windowLocationHref,htmlStr);
                    isWebViewLoad=true;
                } else if(!StringMyUtil.isEmptyString(htmlContent)){//跳转前端页面
                    Intent intent = new Intent(RechargeActivity.this, PayWebviewActivity.class);
//                                intent.putStringArrayListExtra("keys",keys);
//                                intent.putStringArrayListExtra("values",values);
                    intent.putExtra("htmlContent",htmlContent);
                    startActivity(intent);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
                    isWebViewLoad=true;
                }
                ProgressDialogUtil.stop(RechargeActivity.this);
                if(wx_amount_etv!=null){
                    if(!isWebViewLoad){
                        wx_amount_etv.setText("");
                    }
                }
                if(payer_etv!=null){
                    payer_etv.setText("");
                }if(usdt_id_etv!=null){
                    usdt_id_etv.setText("");
                }
                if(currency_amount_etv!=null){
                    currency_amount_etv.setText("");
                }
            }
            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private boolean checkEtv(){
        if(currentAisleEntity == null){
            showToast(Utils.getString(R.string.暂无充值通道));
            return false;
        }
        if(currentAisleEntity.getType().equals("16")){
            if(new BigDecimal(ustdNum).compareTo(BigDecimal.ZERO)==0){
                showToast(Utils.getString(R.string.请输入货币数量));
                return false;
            }else {
                double parseFloat = Double.parseDouble(ustdNum);
                double max = Double.parseDouble(currentAisleEntity.getUp());
                double mix = Double.parseDouble(currentAisleEntity.getDown());
                if(parseFloat < mix || parseFloat > max){
                    showToast(Utils.getString(R.string.货币数量超出区间,请重新输入));
                    return  false;
                }
            }
            if(StringMyUtil.isEmptyString(ustdId)){
                showToast(Utils.getString(R.string.请输入区块链交易ID));
            }
        }else {
            if(StringMyUtil.isEmptyString(price)){
                showToast(Utils.getString(R.string.请输入充值金额));
                return false;
            }else {
                double parseFloat = Double.parseDouble(price);
                double max = Double.parseDouble(currentAisleEntity.getUp());
                double mix = Double.parseDouble(currentAisleEntity.getDown());
                if(parseFloat < mix || parseFloat > max){
                    showToast(Utils.getString(R.string.充值金额超出区间,请重新输入));
                    return  false;
                }
                if(StringMyUtil.isEmptyString(currentAisleEntity.getThirdPartyFlag())){
                    if(StringMyUtil.isEmptyString(nickName)){
                        showToast(Utils.getString(R.string.请输入付款人));
                        return false;
                    }
                }
            }
        }

        return true;
    }
    /**
     * 通道item点击事件处理
     * @param index 用于记录通道item布局当前所在的位置
     * @param aisleEntity 当前点击的item model
     */
    private void aisleItemClick(int index, RechargeModel.BankAllListBean.RechargeBankListBean aisleEntity) {
        currentAisleEntity = aisleEntity;
        /**
         * footView 充值提示
         */
        initRechargeTipStr();

        /**-------------------------- 初始化选中效果  ------  start*/
        int checkIndex=0;
        RechargeModel.BankAllListBean.RechargeBankListBean checkBean = null;
        for (int i = 0; i < rechargeEntityList.size(); i++) {
            Object o = rechargeEntityList.get(i);
            if(o instanceof RechargeModel.BankAllListBean.RechargeBankListBean){
                RechargeModel.BankAllListBean.RechargeBankListBean bean = (RechargeModel.BankAllListBean.RechargeBankListBean) o;
                if(bean.isCheck()){
                    checkIndex=i;
                    bean.setCheck(false);
                    checkBean=bean;
                    break;
                }
            }
        }

        aisleEntity.setCheck(true);
        rechargeAdapter.setData(checkIndex,checkBean);
        rechargeAdapter.setData(rechargeEntityList.indexOf(aisleEntity),aisleEntity);
        /**-------------------------初始化选中效果  ------  end*/

        /** ------------- 重新设置RechargeCenterEntity item布局 和footView 布局start ---------------------*/
        for (int i = 0; i < rechargeEntityList.size(); i++) {
            //将当前列表中的RechargeCenterEntity MabualRechargeCenterEntity BankCardRechargeCenterEntity USDTEntity 布局都删除
            Object obj = rechargeEntityList.get(i);
            if(obj instanceof MabualRechargeCenterEntity || obj instanceof RechargeCenterEntity || obj instanceof USDTEntity || obj instanceof BankCardRechargeCenterEntity ){
                index=i;
                rechargeEntityList.remove(i);
                break;
            }
        }

        for (int i = 0; i <rechargeEntityList.size() ; i++) {
            //将所有人工充值的两种布局都删掉
            if(rechargeEntityList.get(i) instanceof ManualEntity){
                rechargeAdapter.remove(rechargeEntityList.get(i));
                rechargeEntityList.remove(i--);
            }
        }

        //重新添加MabualRechargeCenterEntity或RechargeCenterEntity或BankCardRechargeCenterEntity布局
        List<ManualEntity> shopInfoVoList = aisleEntity.getShopInfoVoList();
        if(shopInfoVoList !=null && shopInfoVoList.size()!=0){
            /**
             * 如果当前选中的通道shopInfoVoList不为空则为人工充值通道,则添加人工充值的中间布局 (输入框 复制按钮 充值账户textView)
             */
            MabualRechargeCenterEntity mabualRechargeCenterEntity = new MabualRechargeCenterEntity(SharePreferencesUtil.getString(MyApplication.getInstance(), "nickname", ""),aisleEntity.getCzFdRate());
            rechargeEntityList.add(index, mabualRechargeCenterEntity);
            rechargeAdapter.setData(index, mabualRechargeCenterEntity);

            /**
             *调整footView 选中人工充值通道时,footView只保留底部的提示文字webView
             */
            recharge_foot_wrap_constraintLayout.setVisibility(View.GONE);//隐藏矩形框
            recharge_sure_btn.setVisibility(View.GONE);//隐藏确定按钮
            recharge_tip_wbv.setVisibility(View.VISIBLE);//显示底部webView

            /**
             * 添加人工充值的店铺数据
             */
            if( shopInfoVoList !=null){
                for (int i = 0; i < shopInfoVoList.size(); i++) {
                    rechargeEntityList.add(shopInfoVoList.get(i));
                }
                rechargeAdapter.setList(rechargeEntityList);
            }
        }else {
            //选中的不是人工充值通道
            if(aisleEntity.getType().equals("16")){
                /**
                 *当前选中的通道是USTD转账
                 */
                String toJSONString = JSONObject.toJSONString(currentAisleEntity);
                USDTEntity usdtEntity = JSONObject.parseObject(toJSONString, USDTEntity.class);
                rechargeEntityList.add(usdtEntity);
                rechargeAdapter.setData(index,usdtEntity);

            }else if(aisleEntity.getType().equals("0")){
                //公司入款中间布局
                List<String> stringList = initCustomAmount(aisleEntity);
                BankCardRechargeCenterEntity bankCardRechargeCenterEntity = new BankCardRechargeCenterEntity(aisleEntity.getDown(),aisleEntity.getUp(),aisleEntity.getThirdPartyFlag(),aisleEntity.getUseCzPriceManualInput(),stringList,aisleEntity.getCzFdRate());
                rechargeEntityList.add(bankCardRechargeCenterEntity);
                rechargeAdapter.setData(index, bankCardRechargeCenterEntity);
            }else {
                /**
                 * 当前选中的通道是微信/qq/支付宝
                 */
                List<String> stringList = initCustomAmount(aisleEntity);
                //添加微信/公司入款/qq/支付宝的中间布局(付款人 充值金额)
                RechargeCenterEntity rechargeCenterEntity = new RechargeCenterEntity(aisleEntity.getDown(),aisleEntity.getUp(),aisleEntity.getThirdPartyFlag(),aisleEntity.getUseCzPriceManualInput(),stringList,aisleEntity.getCzFdRate());
                rechargeEntityList.add(index, rechargeCenterEntity);
                rechargeAdapter.setData(index, rechargeCenterEntity);
            }

            /**
             * 调整footView
             */
            if(aisleEntity.getType().equals("0")){
                /**
                 * 公司入款显示开户行 隐藏扫码支付等第三方支付view
                 */
                recharge_foot_wrap_constraintLayout.setVisibility(View.VISIBLE);//显示矩形框
                bank_name_group.setVisibility(View.VISIBLE);//显示开户行
                bank_name_two_group.setVisibility(View.VISIBLE);//显示开户支行
                qr_image_group.setVisibility(View.GONE);//隐藏扫码支付
                recharge_sure_btn.setVisibility(View.VISIBLE);//显示确定按钮
                recharge_tip_wbv.setVisibility(View.VISIBLE);//显示底部webView
                bank_name_tv.setText(aisleEntity.getBankName());//开户行
                bank_name_two_tv.setText(aisleEntity.getBankDot());
                payee_name_tv.setText(aisleEntity.getName());//收款人
                payee_account_tv.setText(aisleEntity.getAccount());//收款账号
            }else {
                /**
                 * 不是公司入款统一隐藏开户支行
                 */
                bank_name_two_group.setVisibility(View.GONE);
                    if(StringMyUtil.isNotEmpty(aisleEntity.getThirdPartyFlag())||aisleEntity.getType().equals("16")){
                        /**
                         *          为第三方支付或者选中USTD通道时,只显示提示文字webView 和确定按钮
                         */

                        recharge_foot_wrap_constraintLayout.setVisibility(View.GONE);
                        recharge_sure_btn.setVisibility(View.VISIBLE);
                        recharge_tip_wbv.setVisibility(View.VISIBLE);//显示底部webView

                    }else {
                        /**
                         *   显示扫码支付 隐藏开户行
                         */
                        footView.setVisibility(View.VISIBLE);
                        recharge_foot_wrap_constraintLayout.setVisibility(View.VISIBLE);
                        bank_name_group.setVisibility(View.GONE);
                        qr_image_group.setVisibility(View.VISIBLE);
                        recharge_sure_btn.setVisibility(View.VISIBLE);//显示确定按钮
                        recharge_tip_wbv.setVisibility(View.VISIBLE);//显示底部webView
                        payee_name_tv.setText(aisleEntity.getName());//收款人
                        payee_account_tv.setText(aisleEntity.getAccount());//收款账号
                        GlideLoadViewUtil.LoadNormalView(this, Utils.checkImageUrl(aisleEntity.getPayTypeImage()),qr_image_iv);

                    }


            }

        }
        /** ------------- 重新设置RechargeCenterEntity item 和footView 布局 end ---------------------*/
    }

    @NotNull
    private List<String> initCustomAmount(RechargeModel.BankAllListBean.RechargeBankListBean aisleEntity) {
        String czPrices = aisleEntity.getCzPrices();
        List stringList = new ArrayList(Arrays.asList(czPrices.split(",")));
        Iterator<String> it = stringList.iterator();
        while(it.hasNext()){
            String x = it.next();
            if(x.equals("0")){
                it.remove();
            }

        }
        return stringList;
    }

    /**
     * 底部充值通道content
     */
    private void initRechargeTipStr() {
        if(currentAisleEntity==null||currentBankAllBean==null){
            return;
        }
        String remark = currentAisleEntity.getRemark();
        String tipContent="";
        if (currentBankAllBean == null){
            return;
        }
        String type = currentBankAllBean.getType();
        List<ManualEntity> shopInfoVoList = currentAisleEntity.getShopInfoVoList();
        if(shopInfoVoList!=null&&shopInfoVoList.size()!=0){
            tipContent =checkRemark(remark,manualTip);
            Utils.webViewLoadColorStr(tipContent,recharge_tip_wbv,"#FF6D23");
            return;
        }
        if(type.equals("0")){
            //公司入款
            tipContent = checkRemark(remark,Utils.getString(R.string.请转账到以上收款账户转账成功后工作人员将第一时间为您入款));
        }else if(type.equals("1")){
                //微信
                tipContent = checkRemark(remark, Utils.getString(R.string.打开微信扫描二维码或转账到银行卡进行充值));
            }else if(type.equals("2")){
                //支付宝
                 tipContent = checkRemark(remark, Utils.getString(R.string.打开支付宝扫描二维码或转账到银行卡进行充值));
            }else if(type.equals("3")){
                //qq钱包
                 tipContent = checkRemark(remark, Utils.getString(R.string.请转账到以上收款账户转账成功后工作人员将第一时间为您入款));
            }else if(type.equals("16")){
            //USDT
            tipContent =checkRemark(remark,Utils.getString(R.string.温馨提示使用数字货币存取款));
          }else {
            tipContent =checkRemark(remark,manualTip);
            }
        Utils.webViewLoadColorStr(tipContent,recharge_tip_wbv,"#FF6D23");
    }

    @NotNull
    private String checkRemark(String remark, String s) {
        String tipContent;
        if (StringMyUtil.isEmptyString(remark)) {
            tipContent = s;
        } else {
            tipContent = remark;
        }
        return tipContent.equals(Utils.getString(R.string.人工充值))?manualTip:tipContent;
    }

    private void bindHeadView(View headView) {
        recharge_head_recycler  = headView.findViewById(R.id.recharge_head_recycler);
        rechargeHeadAdapter = new RechargeHeadAdapter(R.layout.recharge_head_recycler_item, rechargeHeadArrayList);
        recharge_head_recycler.setLayoutManager(new GridLayoutManager(this,4));
        recharge_head_recycler.setAdapter(rechargeHeadAdapter);
        rechargeHeadAdapter.addChildClickViewIds(R.id.recharge_head_wrap_constraintLayout);
        rechargeHeadAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                for (int i = 0; i < rechargeHeadArrayList.size(); i++) {
                    rechargeHeadArrayList.get(i).setSelector(false);
                }
                RechargeModel.BankAllListBean bankAllListBean = rechargeHeadArrayList.get(position);
                currentBankAllBean = bankAllListBean;
                bankAllListBean.setSelector(true);
                rechargeHeadAdapter.notifyDataSetChanged();
                //更换通道item
                if(currentBankAllBean!=null){
                    setAisleData(0);
                }
            }
        });
        requestBankAllList();
    }

    /**
     * 请求所有充值通道
     */
    private void requestBankAllList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("bankAll",1);
        data.put("flag",1);
//        HttpApiUtils.cpShowLoadRequest(this, null, RequestUtil.RECHARGE_LIST, data, loading_linear, error_linear, reload_tv, recharge_recycler, false, false, new HttpApiUtils.OnRequestLintener() {
        HttpApiUtils.CpRequest(this, null, RequestUtil.RECHARGE_LIST, data,  new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                RechargeModel rechargeModel = JSONObject.parseObject(result, RechargeModel.class);
                bankAllList = rechargeModel.getBankAllList();
                RefreshUtil.success(1,null,loading_linear,nodata_linear,bankAllList.size(),false,false, rechargeHeadArrayList);
                // -------------------- 处理头部充值方式recyclerView   start --------------------------------------------
                for (int i = 0; i < bankAllList.size(); i++) {
                    RechargeModel.BankAllListBean bankAllListBean = bankAllList.get(i);
                    if(i==0){
                        bankAllListBean.setSelector(true);
                        currentBankAllBean =bankAllListBean;
                    }
                    rechargeHeadArrayList.add(bankAllListBean);
                }

                rechargeHeadAdapter.notifyDataSetChanged();
                // -------------------------------------------- 处理头部充值方式recyclerView   start --------------------------------------------

                //--------------------------------------------处理通道名item数据 start --------------------------------------------
                if(currentBankAllBean!=null){
                    rechargeAdapter.addFooterView(footView);
                    setAisleData(0);
                }
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(false,false,1,null);
            }
        });
    }

    /**
     * 添加通道item
     */
    private void setAisleData(int position){
        if(currentBankAllBean==null){
            return;
        }
        rechargeEntityList.clear();
            /**
             * 头部支付大类点击 ,添加对应的通道
             */
            //选中状态初始化
            List<RechargeModel.BankAllListBean.RechargeBankListBean> rechargeBankList = currentBankAllBean.getRechargeBankList();
            for (int i = 0; i < rechargeBankList.size(); i++) {
                RechargeModel.BankAllListBean.RechargeBankListBean rechargeBankListBean = rechargeBankList.get(i);
                if(i==0){
                    rechargeBankListBean.setCheck(true);
                }else {
                    rechargeBankListBean.setCheck(false);
                }
                rechargeEntityList.add(rechargeBankListBean);
            }
         String type = currentBankAllBean.getType();
        if(rechargeBankList.size()!=0){
            RechargeModel.BankAllListBean.RechargeBankListBean rechargeBankListBean = rechargeBankList.get(position);
            List<String> stringList = initCustomAmount(rechargeBankListBean);
            if(type.equals("16")){
                //添加USDT的中间布局
                rechargeEntityList.add(new USDTEntity());
            }else if(type.equals("6")){
                //type == 6 人工充值
                //添加人工充值的中间布局(输入框 复制按钮 充值账户textView)
                MabualRechargeCenterEntity mabualRechargeCenterEntity = new MabualRechargeCenterEntity(SharePreferencesUtil.getString(MyApplication.getInstance(), "nickname", ""),rechargeBankListBean.getCzFdRate());
                rechargeEntityList.add(mabualRechargeCenterEntity);
            }else if(type.equals("0")){
                //公司入款中间布局
                BankCardRechargeCenterEntity bankCardRechargeCenterEntity = new BankCardRechargeCenterEntity(rechargeBankListBean.getDown(),rechargeBankListBean.getUp(),rechargeBankListBean.getThirdPartyFlag(),rechargeBankListBean.getUseCzPriceManualInput(),stringList,rechargeBankListBean.getCzFdRate());
                rechargeEntityList.add(bankCardRechargeCenterEntity);
            }else {
                //添加微信/qq/支付宝的中间布局(付款人 充值金额)
                RechargeCenterEntity rechargeCenterEntity = new RechargeCenterEntity(rechargeBankListBean.getDown(),rechargeBankListBean.getUp(),rechargeBankListBean.getThirdPartyFlag(),rechargeBankListBean.getUseCzPriceManualInput(),stringList,rechargeBankListBean.getCzFdRate());
                rechargeEntityList.add(rechargeCenterEntity);
            }
        }
//        }
        rechargeAdapter.setList(rechargeEntityList);
        aisleItemClick(position, (RechargeModel.BankAllListBean.RechargeBankListBean) rechargeEntityList.get(0));

    }

    @Override
    public void onPopClick(View view) {
        switch (view.getId()){
            case R.id.manual_recharge_pop_sure_tv:
                if(manualRechargeTipPop!=null){
                    manualRechargeTipPop.dismiss();
                }
                if(noticeTipPop!=null){
                    noticeTipPop.dismiss();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        recharge_marquee_view.startScroll();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    private void startAlipayActivity(String url) {
        LogUtils.d("alipay", "startUp");
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            startActivity(intent);
            LogUtils.d("alipay", "start intent = " + intent.toString());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.d("alipay", "error " + e.getMessage());
        }
    }
    /**
     * 检查是否安装了某应用
     *
     * @param packageName 包名
     * @return
     */
    public static boolean isAvilible(String packageName, Context mContext) {
        final PackageManager packageManager = mContext.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
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