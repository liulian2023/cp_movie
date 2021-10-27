package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.InviteAndMakeMoneyActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.DiamondAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DiamondEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Headers;

public class DiamondFragment extends BaseFragment {
    @BindView(R.id.to_recharge_btn)
    Button to_recharge_btn;
    @BindView(R.id.recharge_amount_tv)
    TextView recharge_amount_tv;
    @BindView(R.id.bottom_tip_tv)
    TextView bottom_tip_tv;
    @BindView(R.id.diamond_recycler)
    RecyclerView diamond_recycler;
    DiamondAdapter diamondAdapter;
    ArrayList<DiamondEntity.GainZhuanShiConditionsListBean>diamondEntityArrayList = new ArrayList<>();
    private Unbinder unbinder;
    int  type=0;
    String title;
    public static int OPEN_BET_RULE_POP=111111;

    public static DiamondFragment newInstance(int position,String title) {
        DiamondFragment fragment = new DiamondFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diamond, container, false);
        unbinder = ButterKnife.bind(this, view);
        getArgumentsData();
        initRecycler();
        return view;
    }

    private void initRecycler() {
        diamondAdapter = new DiamondAdapter(R.layout.diamond_parent_recycler,diamondEntityArrayList);
        diamond_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        diamond_recycler.setAdapter(diamondAdapter);
    }
        //0 邀请 1 充值 2 打码 3 送礼
    private void getArgumentsData() {
        String title = getArguments().getString("title");
        if(title.equals(Utils.getString(R.string.邀请))){
            type= 0;
            to_recharge_btn.setText(Utils.getString(R.string.立即邀请));
        }else if(title.equals(Utils.getString(R.string.充值))){
            type = 1;
            to_recharge_btn.setText(Utils.getString(R.string.立即充值));
        }else if(title.equals(getString(R.string.打码))){
            type = 2;
            to_recharge_btn.setText(Utils.getString(R.string.立即投注));
        }else if(title.equals(Utils.getString(R.string.送礼))){
            type = 3;
            to_recharge_btn.setText(Utils.getString(R.string.立即送礼));
        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        HashMap<String, Object> data = new HashMap<>();
        data.put("type",type);
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.TAKE_DIAMOND, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                diamondEntityArrayList.clear();
                DiamondEntity diamondEntity = JSONObject.parseObject(result, DiamondEntity.class);
                List<DiamondEntity.GainZhuanShiConditionsListBean> gainZhuanShiConditionsList = diamondEntity.getGainZhuanShiConditionsList();
                diamondEntityArrayList.addAll(gainZhuanShiConditionsList);
                diamondAdapter.notifyDataSetChanged();


                // 拿第一个没领取到礼物的item 计算还需充值多少
                String price = diamondEntity.getPrice();//type为0 返回已邀请人数；type为1返回今日充值总额；type为2返回今日打码总额；type为3返回今日送礼总额；type为4日常任务配置
                price = StringMyUtil.isEmptyString(price)?"0":price;
                String nextPrice=null;
                for (int i = 0; i < gainZhuanShiConditionsList.size(); i++) {
                    DiamondEntity.GainZhuanShiConditionsListBean gainZhuanShiConditionsListBean = gainZhuanShiConditionsList.get(i);
                    String userGainSuccess = gainZhuanShiConditionsListBean.getUserGainSuccess();//用户是否获取(0否;1是)
                    if(userGainSuccess.equals("0")){
                        nextPrice =  gainZhuanShiConditionsListBean.getPrice();
                        break;
                    }
                }
                if(StringMyUtil.isEmptyString(nextPrice)){
                    //为空即为全部完成
                    bottom_tip_tv.setText(Utils.getString(R.string.已领取全部礼物!));
                }else {
                    if(nextPrice.contains(".")||price.contains(".")){
                        BigDecimal nextNum = new BigDecimal(nextPrice);
                        BigDecimal num = new BigDecimal(price);
                        if(type == 0){
                            bottom_tip_tv.setText(Utils.getString(R.string.再邀请)+(nextNum.subtract(num))+Utils.getString(R.string.人,可领取更多礼物));
                        }else if(type == 1){
                            bottom_tip_tv.setText(Utils.getString(R.string.再充值)+(nextNum.subtract(num))+Utils.getString(R.string.元,可领取更多礼物));
                        }else if(type == 2){
                            bottom_tip_tv.setText(Utils.getString(R.string.再投注)+(nextNum.subtract(num))+Utils.getString(R.string.元,可领取更多礼物));
                        }else if(type == 3){
                            bottom_tip_tv.setText(Utils.getString(R.string.再送礼)+(nextNum.subtract(num))+Utils.getString(R.string.元,可领取更多礼物));
                        }
                    }else {
                        int nextNum = Integer.parseInt(nextPrice);
                        int num = Integer.parseInt(price);
                        if(type == 0){
                            bottom_tip_tv.setText(Utils.getString(R.string.再邀请)+(nextNum-num)+Utils.getString(R.string.人,可领取更多礼物));
                        }else if(type == 1){
                            bottom_tip_tv.setText(Utils.getString(R.string.再充值)+(nextNum-num)+Utils.getString(R.string.元,可领取更多礼物));
                        }else if(type == 2){
                            bottom_tip_tv.setText(Utils.getString(R.string.再投注)+(nextNum-num)+Utils.getString(R.string.元,可领取更多礼物));
                        }else if(type == 3){
                            bottom_tip_tv.setText(Utils.getString(R.string.再送礼)+(nextNum-num)+Utils.getString(R.string.元,可领取更多礼物));
                        }
                    }

                }
                if(type == 0){
                    SpannableString spannableString = new SpannableString(Utils.getString(R.string.我已邀请) + price + Utils.getString(R.string.人,去邀请));
                    setTextSpan(price, spannableString,4);

                }else if(type == 1){
                    SpannableString spannableString = new SpannableString(Utils.getString(R.string.今日已充值) + price + Utils.getString(R.string.元,去充值));
                    setTextSpan(price, spannableString,5);

                }else if(type == 2){
                    SpannableString spannableString = new SpannableString(getString(R.string.今日已打码) + price + getString(R.string.元去投注));
                    setTextSpan(price, spannableString,5);

                }else if(type == 3){
                    SpannableString spannableString = new SpannableString(Utils.getString(R.string.今日已送礼) + price + Utils.getString(R.string.元,去送礼));
                    setTextSpan(price, spannableString,5);
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private void setTextSpan(String price, SpannableString spannableString,int startIndex) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#F5214E"));//字体颜色
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(22,true);//字体大小
        spannableString.setSpan(foregroundColorSpan,startIndex,startIndex+price.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(absoluteSizeSpan,startIndex,startIndex+price.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        recharge_amount_tv.setText(spannableString);
    }
    @OnClick({R.id.to_recharge_btn})
    public void  onclicK (View view){
        switch (view.getId()){
            case R.id.to_recharge_btn:
                //0 邀请 1 充值 2 打码 3 送礼
                if(type == 0){
                    InviteAndMakeMoneyActivity.startAty(getContext(),"");
                    getActivity().finish();
                }else if(type == 1){
                    String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                    if(isTest.equals("-1")){
                        Utils.initSkipVisitorSafeCenterPop(getContext(),getActivity());
                    }else {
                        startActivity(new Intent(getContext(),RechargeActivity.class));
                        getActivity().finish();
                    }
                }else if(type == 2){
//                    startActivity(new Intent(getContext(), LiveShoppingActivity.class));
                    getActivity().setResult(OPEN_BET_RULE_POP);
                    getActivity().finish();
                }else if(type == 3){
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}