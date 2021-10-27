package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BankCardRechargeCenterEntity;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CustomAmountEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManualEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MabualRechargeCenterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RechargeCenterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RechargeModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.USDTEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RechargeAdapter extends BaseDelegateMultiAdapter<Object, BaseViewHolder> {
    public static int TYPE_ONE=1;
    public static int TYPE_TWO=2;
    public static int TYPE_THREE=3;
    public static int TYPE_FOUR=4;
    public static int TYPE_FIVE=5;
    public static int TYPE_SIX=6;
    public static int TYPE_SEVEN=7;
    public RechargeAdapter() {
        super();
        setMultiTypeDelegate(new BaseMultiTypeDelegate<Object>() {
            @Override
            public int getItemType(@NotNull List<?> list, int i) {
                Object o = list.get(i);
                if(o instanceof RechargeModel.BankAllListBean.RechargeBankListBean){
                    //通道名布局
                    return TYPE_ONE;
                }
                else if(o instanceof MabualRechargeCenterEntity){
                    //中间充值金额以及复制按钮布局(人工充值)
                    return TYPE_TWO;
                }else if(o instanceof RechargeCenterEntity){
                    //中间充值金额以及复制按钮布局(非人工充值)
                    return TYPE_FIVE;
                }else if(o instanceof ManualEntity) {
                    //人工充值 充值方式布局
                    if(SharePreferencesUtil.getString(MyApplication.getInstance(),"showShopInfoOnlineService","").equals("1")){
                        return TYPE_THREE;
                    }else {
                        return TYPE_FOUR;
                    }
                }else if(o instanceof BankCardRechargeCenterEntity){
                    return TYPE_SEVEN;
                }else {
                    //USDT充值
                    return TYPE_SIX;
                }
            }
        });
        getMultiTypeDelegate()
                .addItemType(TYPE_ONE,R.layout.aisle_item_layout)//通道布局
                .addItemType(TYPE_TWO,R.layout.manul_recharge_center_layout)//中间充值账号布局(人工充值)
                .addItemType(TYPE_THREE,R.layout.manual_recharge_item)//qq 微信 支付宝充值入口布局
                .addItemType(TYPE_FOUR,R.layout.customer_service_recharge_layout)//客服充值入口
                .addItemType(TYPE_FIVE,R.layout.manul_recharge_center_layout2)//中间充值账号布局(微信支付宝qq)
                .addItemType(TYPE_SIX,R.layout.usdt_recharge_center_layout)//中间充值账号布局(USDT)
                .addItemType(TYPE_SEVEN,R.layout.manul_recharge_center_layout3);//中间充值账号布局(银行卡转账)
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
        if(o instanceof RechargeModel.BankAllListBean.RechargeBankListBean){
            /**
             *通道名item
             */
            handleAisleItem(baseViewHolder, (RechargeModel.BankAllListBean.RechargeBankListBean) o);
        }else if(o instanceof MabualRechargeCenterEntity){
            /**
             *  人工充值中间布局item  充值金额 复制按钮 付款人item
             */
            handleMabualRechargeCenterItem(baseViewHolder, (MabualRechargeCenterEntity) o);
        }else if(o instanceof RechargeCenterEntity ){
            /**
             *  微信qq支付宝 付款人回调回activity
             */
            handleRechargeCenterItem(baseViewHolder, (RechargeCenterEntity) o);
        }else if( o instanceof BankCardRechargeCenterEntity){
            /**
             *  公司入款 付款人回调回activity
             */
            handleBankCardRechargeCenterItem(baseViewHolder, (BankCardRechargeCenterEntity) o);
        }else if(o instanceof ManualEntity) {
            /**
             * 人工充值item
             */
            initManualItem(baseViewHolder, (ManualEntity) o);
        }else {
            /**
             * USDT转账item
             */
            handUSDTItem(baseViewHolder, (USDTEntity) o);
        }

    }

    private void handUSDTItem(@NotNull BaseViewHolder baseViewHolder, USDTEntity o) {
        USDTEntity usdtEntity = o;

        LinearLayout recharge_tip_linear= baseViewHolder.getView(R.id.recharge_tip_linear);
        String czFdRate = usdtEntity.getCzFdRate();
        if(StringMyUtil.isEmptyString(czFdRate)){
            recharge_tip_linear.setVisibility(View.GONE);
        }else {
            recharge_tip_linear.setVisibility(View.VISIBLE);
            TextView reward_tip_tv= baseViewHolder.getView(R.id.reward_tip_tv);
            reward_tip_tv.setText(String.format(Utils.getString(R.string.充值奖励%s%%,最高送88888元!),czFdRate));
        }

        baseViewHolder.setText(R.id.address_tv,usdtEntity.getAccount());//收款地址
        String usdtRate = usdtEntity.getUsdtRate();
        baseViewHolder.setText(R.id.exchange_rate_tv, usdtRate);//存款汇率
        EditText usdt_id_etv = baseViewHolder.getView(R.id.usdt_id_etv);
        EditText currency_amount_etv = baseViewHolder.getView(R.id.currency_amount_etv);
        currency_amount_etv.setHint(String.format(Utils.getString(R.string.充值数量范围:%s-%s),usdtEntity.getDown(),usdtEntity.getUp()));
        TextView transfer_amount_tv = baseViewHolder.getView(R.id.transfer_amount_etv);
        //收款地址
        usdt_id_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //限制中文输入
                if (s.length() > 0) {
                    for (int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if (c >= 0x4e00 && c <= 0X9fff) {
                            s.delete(i,i+1);
                        }
                    }
                }

                if(onTextChangeListener !=null){
                    onTextChangeListener.onTextChange(usdt_id_etv,usdt_id_etv.getText().toString());
                }
            }
        });
        //充值金额设置(currency_amount_etv 充值个数*充值汇率就是充值金额)
        currency_amount_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.limitEtvBigDecimal(currency_amount_etv,4,s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().endsWith(".")){
                    return;
                }
                String toString = s.toString();
                toString = StringMyUtil.isEmptyString(toString)?"0":toString;
                BigDecimal amount = new BigDecimal(toString).multiply(new BigDecimal(usdtRate)).setScale(4, BigDecimal.ROUND_FLOOR);
                if(amount.compareTo(BigDecimal.ZERO)==0){
                    transfer_amount_tv.setText("0.00");
                }else {
                    transfer_amount_tv.setText(amount+"");
                }
                if(onTextChangeListener !=null){
                    onTextChangeListener.onTextChange(currency_amount_etv,toString);
                }

            }
        });
    }

    /**
     * 人工充值item 数据处理
     * @param baseViewHolder
     * @param o
     */
    private void initManualItem(@NotNull BaseViewHolder baseViewHolder, ManualEntity o) {
        ManualEntity manualEntity = o;
        String unit="";
        long amount = new BigDecimal(manualEntity.getAmount()).longValue();
        long firstNum = 0;
        if(amount>=100&&amount<1000){
            unit=Utils.getString(R.string.百);
            firstNum=amount/100;
        }else if(amount>=1000&&amount<10000){
            unit=Utils.getString(R.string.千);
            firstNum=amount/1000;
        }else if(amount>=10000){
            unit=Utils.getString(R.string.万);
            firstNum=amount/10000;
        }

        if(SharePreferencesUtil.getString(MyApplication.getInstance(),"showShopInfoOnlineService","").equals("1")){
            /**
             *   qq 微信 支付宝 充值方式 布局格式
             */
            TextView manual_name_tv = baseViewHolder.getView(R.id.manual_name_tv);
            String shopNickName = manualEntity.getShopNickName();
            manual_name_tv.setText(shopNickName);
            TextView available_quota_tv = baseViewHolder.getView(R.id.available_quota_tv);
            setAvailableQuota(available_quota_tv, unit, firstNum);
            if(manualEntity.getStatus()==1){
                //输入金额超出范围  设置不可点击
                baseViewHolder.setImageResource(R.id.qq_icon,R.drawable.qq_bkd);
                baseViewHolder.setImageResource(R.id.we_chat_icon,R.drawable.weix_bkd);
                baseViewHolder.setImageResource(R.id.ali_icon,R.drawable.zhifub_bkd);
                baseViewHolder.setTextColor(R.id.ali_tv, Color.parseColor("#D0D0D0"));
                baseViewHolder.setTextColor(R.id.we_chat_tv, Color.parseColor("#D0D0D0"));
                baseViewHolder.setTextColor(R.id.qq_tv, Color.parseColor("#D0D0D0"));
                baseViewHolder.getView(R.id.qq_linear).setClickable(false);
                baseViewHolder.getView(R.id.weChat_linear).setClickable(false);
                baseViewHolder.getView(R.id.aliPay_linear).setClickable(false);
            }else {
                //超出金额未超出范围 (为空设置不可点击)
                if(StringMyUtil.isNotEmpty(manualEntity.getWx())){
                    baseViewHolder.setImageResource(R.id.we_chat_icon,R.drawable.weix);
                    baseViewHolder.setTextColor(R.id.we_chat_tv, Color.parseColor("#02BB29"));
                    baseViewHolder.getView(R.id.weChat_linear).setClickable(true);
                }else {
                    baseViewHolder.setImageResource(R.id.we_chat_icon,R.drawable.weix_bkd);
                    baseViewHolder.setTextColor(R.id.we_chat_tv, Color.parseColor("#D0D0D0"));
                    baseViewHolder.getView(R.id.weChat_linear).setClickable(false);
                }
                if(StringMyUtil.isNotEmpty(manualEntity.getQq())){
                    baseViewHolder.setImageResource(R.id.qq_icon,R.drawable.qq);
                    baseViewHolder.setTextColor(R.id.qq_tv, Color.parseColor("#037EF3"));
                    baseViewHolder.getView(R.id.qq_linear).setClickable(true);
                }else {
                    baseViewHolder.setImageResource(R.id.qq_icon,R.drawable.qq_bkd);
                    baseViewHolder.setTextColor(R.id.qq_tv, Color.parseColor("#D0D0D0"));
                    baseViewHolder.getView(R.id.qq_linear).setClickable(false);
                }
                if(StringMyUtil.isNotEmpty(manualEntity.getZfb())){
                    baseViewHolder.setImageResource(R.id.ali_icon,R.drawable.zhifub);
                    baseViewHolder.setTextColor(R.id.ali_tv, Color.parseColor("#00AAEE"));
                    baseViewHolder.getView(R.id.aliPay_linear).setClickable(true   );
                }else {
                    baseViewHolder.setImageResource(R.id.ali_icon,R.drawable.zhifub_bkd);
                    baseViewHolder.setTextColor(R.id.ali_tv, Color.parseColor("#D0D0D0"));
                    baseViewHolder.getView(R.id.aliPay_linear).setClickable(false);

                }

            }

        }else{
            /**
             * 客服充值 布局格式
             */
            if(manualEntity.getStatus()==1){
                //不可点击
                baseViewHolder.getView(R.id.contact_now_btn).setClickable(false);
                baseViewHolder.setBackgroundResource(R.id.contact_now_btn,R.drawable.contact_now_not_check_shape);
            }else {
                //可点击
                baseViewHolder.getView(R.id.contact_now_btn).setClickable(true);
                baseViewHolder.setBackgroundResource(R.id.contact_now_btn,R.drawable.contact_now_selector);
            }
            baseViewHolder.setText(R.id.customer_recharge_name_tv,manualEntity.getShopNickName());
            TextView customer_recharge_available_quota_tv = baseViewHolder.getView(R.id.customer_recharge_available_quota_tv);
            setAvailableQuota(customer_recharge_available_quota_tv, unit, firstNum);
        }
    }
    /**
     * 公司入款中间item数据处理
     * @param baseViewHolder
     * @param o
     */
    private void handleBankCardRechargeCenterItem(@NotNull BaseViewHolder baseViewHolder, BankCardRechargeCenterEntity o) {
        BankCardRechargeCenterEntity bankCardRechargeCenterEntity = o;
        String useCzPriceManualInput = bankCardRechargeCenterEntity.getUseCzPriceManualInput();//0否；1是(充值金额手动输入)
        String czFdRate = bankCardRechargeCenterEntity.getCzFdRate();
        String thirdPartyFlag = bankCardRechargeCenterEntity.getThirdPartyFlag();

        LinearLayout recharge_tip_linear= baseViewHolder.getView(R.id.recharge_tip_linear);
        if(StringMyUtil.isEmptyString(czFdRate)){
            recharge_tip_linear.setVisibility(View.GONE);
        }else {
            recharge_tip_linear.setVisibility(View.VISIBLE);
            TextView reward_tip_tv= baseViewHolder.getView(R.id.reward_tip_tv);
            reward_tip_tv.setText(String.format(Utils.getString(R.string.充值奖励%s%%,最高送88888元!),czFdRate));
        }
        EditText wx_amount_etv= baseViewHolder.getView(R.id.wx_amount_etv);
        EditText payer_etv= baseViewHolder.getView(R.id.payer_etv);
        Group payer_group= baseViewHolder.getView(R.id.payer_group);
        Group amount_group= baseViewHolder.getView(R.id.amount_group);
        if(StringMyUtil.isNotEmpty(thirdPartyFlag)){
            //第三方支付隐藏收款人
            payer_group.setVisibility(View.GONE);
        }else {
            payer_group.setVisibility(View.VISIBLE);
        }
        if(useCzPriceManualInput.equals("0")){
            //隐藏金额输入框
            amount_group.setVisibility(View.GONE);
        }else {
            amount_group.setVisibility(View.VISIBLE);
        }
        // 加载快捷输入的金额recyclerView
        initCustomAmountRecyclerBankCard(baseViewHolder, bankCardRechargeCenterEntity,wx_amount_etv);
        payer_etv.setText("");
        wx_amount_etv.setText("");
        wx_amount_etv.setHint(String.format(Utils.getString(R.string.请输入金额占位), bankCardRechargeCenterEntity.getMix(),"-", bankCardRechargeCenterEntity.getMax()));
        wx_amount_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(onTextChangeListener !=null){
                    onTextChangeListener.onTextChange(wx_amount_etv,wx_amount_etv.getText().toString());
                }
            }
        });
        payer_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(onTextChangeListener !=null){
                    onTextChangeListener.onTextChange(payer_etv,payer_etv.getText().toString());
                }
            }
        });
    }

    /**
     * 微信qq支付宝中间item数据处理
     * @param baseViewHolder
     * @param o
     */
    private void handleRechargeCenterItem(@NotNull BaseViewHolder baseViewHolder, RechargeCenterEntity o) {
        RechargeCenterEntity rechargeCenterEntity = o;
        String useCzPriceManualInput = rechargeCenterEntity.getUseCzPriceManualInput();//0否；1是(充值金额手动输入)

        LinearLayout recharge_tip_linear= baseViewHolder.getView(R.id.recharge_tip_linear);
        String czFdRate = rechargeCenterEntity.getCzFdRate();
        if(StringMyUtil.isEmptyString(czFdRate)){
            recharge_tip_linear.setVisibility(View.GONE);
        }else {
            recharge_tip_linear.setVisibility(View.VISIBLE);
            TextView reward_tip_tv= baseViewHolder.getView(R.id.reward_tip_tv);
            reward_tip_tv.setText(String.format(Utils.getString(R.string.充值奖励最高送88888元占位),czFdRate));
        }
        EditText wx_amount_etv= baseViewHolder.getView(R.id.wx_amount_etv);
        EditText payer_etv= baseViewHolder.getView(R.id.payer_etv);
        Group payer_group= baseViewHolder.getView(R.id.payer_group);
        Group amount_group= baseViewHolder.getView(R.id.amount_group);
        TextView recycler_margin_tv= baseViewHolder.getView(R.id.recycler_margin_tv);
        if(StringMyUtil.isNotEmpty(rechargeCenterEntity.getThirdPartyFlag())){
            //第三方支付隐藏收款人
            payer_group.setVisibility(View.GONE);
        }else {
            payer_group.setVisibility(View.VISIBLE);
        }
        if(useCzPriceManualInput.equals("0")){
            //隐藏金额输入框
            amount_group.setVisibility(View.GONE);
        }else {
            amount_group.setVisibility(View.VISIBLE);
        }
        // 加载快捷输入的金额recyclerView
        initCustomAmountRecycler(baseViewHolder, rechargeCenterEntity,wx_amount_etv);
        payer_etv.setText("");
        wx_amount_etv.setText("");
        wx_amount_etv.setHint(String.format(Utils.getString(R.string.请输入金额%s%s%s), rechargeCenterEntity.getMix(),"-", rechargeCenterEntity.getMax()));
        wx_amount_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(onTextChangeListener !=null){
                    onTextChangeListener.onTextChange(wx_amount_etv,wx_amount_etv.getText().toString());
                }
            }
        });
        payer_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(onTextChangeListener !=null){
                    onTextChangeListener.onTextChange(payer_etv,payer_etv.getText().toString());
                }
            }
        });
    }

    /**
     * 人工充值中间布局item 数据处理
     * @param baseViewHolder
     * @param o
     */
    private void handleMabualRechargeCenterItem(@NotNull BaseViewHolder baseViewHolder, MabualRechargeCenterEntity o) {
        MabualRechargeCenterEntity manuaMabualRechargeCenterEntity = o;
        EditText recharge_amount_etv= baseViewHolder.getView(R.id.recharge_amount_etv);

        LinearLayout recharge_tip_linear= baseViewHolder.getView(R.id.recharge_tip_linear);
        String czFdRate = manuaMabualRechargeCenterEntity.getCzFdRate();
        if(StringMyUtil.isEmptyString(czFdRate)){
            recharge_tip_linear.setVisibility(View.GONE);
        }else {
            recharge_tip_linear.setVisibility(View.VISIBLE);
            TextView reward_tip_tv= baseViewHolder.getView(R.id.reward_tip_tv);
            reward_tip_tv.setText(String.format(Utils.getString(R.string.充值奖励%s%%,最高送88888元!),czFdRate));
        }
//            recharge_amount_etv.setText("");
        baseViewHolder.setText(R.id.recharge_account_tv, manuaMabualRechargeCenterEntity.getRechargeAccount());
        //充值金额回调回activity
        recharge_amount_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(onTextChangeListener !=null){
                    onTextChangeListener.onTextChange(recharge_amount_etv,recharge_amount_etv.getText().toString());
                }
            }
        });
    }

    /**
     * 通道名item数据处理
     * @param baseViewHolder
     * @param o
     */
    private void handleAisleItem(@NotNull BaseViewHolder baseViewHolder, RechargeModel.BankAllListBean.RechargeBankListBean o) {
        RechargeModel.BankAllListBean.RechargeBankListBean aisleEntity = o;

        TextView aisle_num_tv= baseViewHolder.getView(R.id.aisle_num_tv);
        String title = aisleEntity.getTitle();
        String czFdRate = aisleEntity.getCzFdRate();
        TextView recharge_reward_rate_tv= baseViewHolder.getView(R.id.recharge_reward_rate_tv);
        if(StringMyUtil.isEmptyString(czFdRate)){
            recharge_reward_rate_tv.setVisibility(View.INVISIBLE);
        }else {
            recharge_reward_rate_tv.setVisibility(View.VISIBLE);
            recharge_reward_rate_tv.setText(String.format("+%s%%",czFdRate));
        }
        if(title.length()<=7){
            aisle_num_tv.setTextSize(13);
        } else if(title.length()>7&&title.length()<9){
            aisle_num_tv.setTextSize(12);
        }else {
            aisle_num_tv.setTextSize(11);
        }
            aisle_num_tv.setText(title);
//        baseViewHolder.setText(R.id.aisle_remark_tv, bankName);
       ConstraintLayout aisle_constraintLayout= baseViewHolder.getView(R.id.aisle_constraintLayout);

        ImageView aisle_check_iv= baseViewHolder.getView(R.id.aisle_check_iv);
        if(aisleEntity.isCheck()){
            aisle_check_iv.setVisibility(View.VISIBLE);
            aisle_constraintLayout.setBackgroundResource(R.drawable.recharge_head_item_check_shape);

        }else {
            aisle_constraintLayout.setBackgroundResource(R.drawable.recharge_head_item_un_check_shape);
            aisle_check_iv.setVisibility(View.GONE);
        }
    }

    /***
     * 快捷金额 recyclerView
     * @param baseViewHolder
     * @param rechargeCenterEntity
     * @param wx_amount_etv  充值金额输入框
     */
    private void initCustomAmountRecycler(@NotNull BaseViewHolder baseViewHolder, RechargeCenterEntity rechargeCenterEntity, EditText wx_amount_etv) {
        handQuickAmount(baseViewHolder, wx_amount_etv, rechargeCenterEntity.getCzPrices());
    }
    private void initCustomAmountRecyclerBankCard(@NotNull BaseViewHolder baseViewHolder, BankCardRechargeCenterEntity bankCardRechargeCenterEntity, EditText wx_amount_etv) {
        handQuickAmount(baseViewHolder, wx_amount_etv, bankCardRechargeCenterEntity.getCzPrices());
    }

    private void handQuickAmount(@NotNull BaseViewHolder baseViewHolder, EditText wx_amount_etv, List<String> czPrices) {
        RecyclerView custom_amount_recycler = baseViewHolder.getView(R.id.custom_amount_recycler);
        ArrayList<CustomAmountEntity> customAmountEntityArrayList = new ArrayList<>();
        for (int i = 0; i < czPrices.size(); i++) {
            customAmountEntityArrayList.add(new CustomAmountEntity(czPrices.get(i)));
        }
        CustomAmountAdapter customAmountAdapter = new CustomAmountAdapter(R.layout.custom_amount_item, customAmountEntityArrayList);
        custom_amount_recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        custom_amount_recycler.setAdapter(customAmountAdapter);
        customAmountAdapter.notifyDataSetChanged();
        customAmountAdapter.addChildClickViewIds(R.id.custom_amount_tv);
        customAmountAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                for (int i = 0; i < customAmountEntityArrayList.size(); i++) {
                    CustomAmountEntity customAmountEntity = customAmountEntityArrayList.get(i);
                    customAmountEntity.setStatus(0);
                }
                customAmountEntityArrayList.get(position).setStatus(1);
                wx_amount_etv.setText(customAmountEntityArrayList.get(position).getAmount());
                customAmountAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setAvailableQuota(TextView view, String unit, long firstNum) {
        if(firstNum==0){
            view.setText(String.format(Utils.getString(R.string.可用额度二占位),firstNum,unit));
        }else {
            view.setText(String.format(Utils.getString(R.string.可用额度三占位),firstNum,unit,"+"));
        }
    }

    public interface TextChangeListener{
    void onTextChange(View view,String s);
    }
    private TextChangeListener onTextChangeListener;

    public void setOnTextChangeListener(TextChangeListener onTextChangeListener) {
        this.onTextChangeListener = onTextChangeListener;
    }
}

