package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.EquipmentAllEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;

import okhttp3.Headers;

public class DiamondPayPop extends BasePopupWindow2 {

    ImageView mount_big_iv;
    TextView diamond_amount_tv;
    TextView mount_amount_tv;
    TextView mount_name_tv1;
    Button diamond_cancel_btn;
    Button diamond_buy_btn;
    SmartRefreshLayout smartRefreshLayout;
    EquipmentAllEntity.MemberMedalListBean memberMedalListBean;
    public DiamondPayPop(Context context, boolean focusable, EquipmentAllEntity.MemberMedalListBean memberMedalListBean, SmartRefreshLayout smartRefreshLayout) {
        super(context, focusable);
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(mContext,R.color.white));
        this.setBackgroundDrawable(dw);
        setAnimationStyle(R.style.pop_bottom_to_top_150);
        this.memberMedalListBean = memberMedalListBean;
        this.smartRefreshLayout = smartRefreshLayout;
        initData();
    }
    private void requestUserInfo() {
        HttpApiUtils.CpRequest((Activity) mContext, null, RequestUtil.REQUEST_06rzq, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
/*                SharePreferencesUtil.putString(MyApplication.getInstance(),"userInfo",result);//储存用户信息
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                String totalZhuanShi = userInfoEntity.getTotalZhuanShi();
                if(StringMyUtil.isNotEmpty(totalZhuanShi)){
                    diamond_amount_tv.setText(totalZhuanShi);
                }*/
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                String totalZhuanShi = memberMoney.getString("totalZhuanShi");
                if(StringMyUtil.isNotEmpty(totalZhuanShi)){
                    diamond_amount_tv.setText(totalZhuanShi);
                }

            }
            @Override
            public void onFailed(String msg) {

            }
        });
    }
    private void initData() {
        EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfo = memberMedalListBean.getMedalInfo();
        String medalConditions = medalInfo.getMedalConditions();
        EquipmentAllEntity.MemberMedalListBean.MedalInfoBean.MedalConditionsBean medalConditionsBean = JSONObject.parseObject(medalConditions, EquipmentAllEntity.MemberMedalListBean.MedalInfoBean.MedalConditionsBean.class);
        String useDays = medalInfo.getUseDays();
        int customType = medalInfo.getCustomType();
        if(customType==1){
            /**
             * 未获得 useDays为0 显示永久
             */
            if(useDays.equals("0")){
                mount_amount_tv.setText(String.format(Utils.getString(R.string.%s钻石/永久),medalConditionsBean.getBuyZhuanShi()));
            }else {
                if(medalConditionsBean!=null){
                    mount_amount_tv.setText(String.format(Utils.getString(R.string.%s钻石/%s天),medalConditionsBean.getBuyZhuanShi(), useDays));
                }
            }
        }


        GlideLoadViewUtil.LoadNormalView(mContext,medalInfo.getImage(),mount_big_iv);
        mount_name_tv1.setText(medalInfo.getName());
        requestUserInfo();
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.diamond_pay_pop_layout,null);
        mount_big_iv = rootView.findViewById(R.id.mount_big_iv);
        diamond_amount_tv = rootView.findViewById(R.id.diamond_amount_tv);
        mount_name_tv1 = rootView.findViewById(R.id.mount_name_tv1);
        mount_amount_tv = rootView.findViewById(R.id.mount_amount_tv);
        diamond_cancel_btn = rootView.findViewById(R.id.diamond_cancel_btn);
        diamond_buy_btn = rootView.findViewById(R.id.diamond_buy_btn);
        diamond_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiamondPayPop.this.dismiss();
            }
        });
        diamond_buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("id",memberMedalListBean.getMedalInfo().getId());
                HttpApiUtils.CpRequest((Activity) mContext, null, RequestUtil.BUY_MOUNT, data, new HttpApiUtils.OnRequestLintener() {
                    @Override
                    public void onSuccess(String result, Headers headers) {
                        ToastUtil.showToast(Utils.getString(R.string.购买成功));
                        DiamondPayPop.this.dismiss();
                        if(smartRefreshLayout!=null&&mContext!=null&&!((Activity) mContext).isFinishing()){
                            smartRefreshLayout.autoRefresh();
                        }
                    }

                    @Override
                    public void onFailed(String msg) {

                    }
                });
            }
        });
    }
}
