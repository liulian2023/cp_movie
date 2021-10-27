package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment.MineFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.SignInAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DiamondChildEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SignInEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class SignInPop extends BasePopupWindow2 {

    TextView sign_date_tv;
    ImageView sign_rule_iv;
    ImageButton sign_in_btn;

    RecyclerView sign_in_recycler;
    SignInAdapter signInAdapter;
    ArrayList<SignInEntity.GainZhuanShiConditionsListBean>signInEntityArrayList = new ArrayList<>();
    MineFragment mineFragment;
    private String todaySignInFlag="0";
    private RulePop rulePop;

    public SignInPop(Context context, boolean focusable, MineFragment mineFragment) {
        super(context, focusable);
        this.mineFragment = mineFragment;
        initRecycler();
    }

    private void initRecycler() {
        signInAdapter = new SignInAdapter(R.layout.sign_in_recycler_item_layout,signInEntityArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        sign_in_recycler.setLayoutManager(gridLayoutManager);
        sign_in_recycler.setAdapter(signInAdapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                SignInEntity.GainZhuanShiConditionsListBean gainZhuanShiConditionsListBean = signInEntityArrayList.get(position);
                String price = gainZhuanShiConditionsListBean.getPrice();
                if(price.equals("8")){
                    return 2;
                }
                return 1;
            }
        });
        signInAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                SignInEntity.GainZhuanShiConditionsListBean gainZhuanShiConditionsListBean = signInEntityArrayList.get(position);
                ArrayList<DiamondChildEntity> childList = gainZhuanShiConditionsListBean.getChildList();
                if(childList.size()>1){
                    // 弹出宝箱pop
                    SignInChildPop signInChildPop = new SignInChildPop(mineFragment.getContext(),true,childList);
                    signInChildPop.showAtLocation(mineFragment.version_name_tv, Gravity.BOTTOM,0,150);
                    ProgressDialogUtil.darkenBackground(mineFragment.getActivity(),0.6f);

                }
            }
        });
        requestSignInList();
    }

    private void requestSignInList() {
        HttpApiUtils.CPnormalRequest((Activity) mContext, mineFragment, RequestUtil.SIGN_IN_LIST, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                signInEntityArrayList.clear();
                SignInEntity signInEntity = JSONObject.parseObject(result, SignInEntity.class);
                List<SignInEntity.GainZhuanShiConditionsListBean> gainZhuanShiConditionsList = signInEntity.getGainZhuanShiConditionsList();
                signInEntityArrayList.addAll(gainZhuanShiConditionsList);
                signInAdapter.notifyDataSetChanged();
                String thisWeekTitle = signInEntity.getThisWeekTitle();
                sign_date_tv.setText(thisWeekTitle);
                todaySignInFlag = signInEntity.getTodaySignInFlag();//0今天未签到；1今天已经签到
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.sign_in_pop_layout,null);
        sign_date_tv = rootView.findViewById(R.id.sign_date_tv);
        sign_rule_iv = rootView.findViewById(R.id.sign_rule_iv);
        sign_in_recycler = rootView.findViewById(R.id.sign_in_recycler);
        sign_in_btn = rootView.findViewById(R.id.sign_in_btn);
        sign_rule_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // 规则弹窗
                initRulePop();
                rulePop.showAtLocation(mineFragment.version_name_tv, Gravity.CENTER,0,0);
                ProgressDialogUtil.darkenBackground(mineFragment.getActivity(),0.6f);
            }
        });
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(todaySignInFlag.equals("1")){
                    ToastUtil.showToast(Utils.getString(R.string.今日已签到));
                }else {
                    // 签到
                    requestSignIn();
                }
            }
        });
    }

    private void initRulePop() {
        if(rulePop ==null){
//            rulePop = new RulePop(mContext,true,"file:///android_asset/signIn_rule.html",Utils.getString(R.string.签到规则));
            rulePop = new RulePop(mContext,true, Utils.checkImageUrl("/rules/signIn_rule.html"),Utils.getString(R.string.签到规则));
        }
        rulePop.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground((Activity)mContext,0.6f);
            }
        });
    }

    private void requestSignIn() {
        HttpApiUtils.CpRequest( mineFragment.getActivity(), mineFragment, RequestUtil.SIGN_IN, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                    ToastUtil.showToast(Utils.getString(R.string.签到成功));
                    requestSignInList();
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
}
