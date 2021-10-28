package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BankCardAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;


public class BankCardManageActivity extends BaseActivity implements View.OnClickListener {
    private TextView no_bind_tip_tv;
//    private Button addCard;
    private TextView toolbar_right_tv;
    private TextView remaining_num_tv;
    private ConstraintLayout bankcard_foot_wrap_view;

    private RecyclerView bank_card_recycler;
    private BankCardAdapter bankCardAdapter;
    private ArrayList<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean>memberBankInfoVoListBeanArrayList = new ArrayList<>();

    boolean canBind=true;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_manage);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this, Utils.getString(R.string.银行卡管理));
        ininView();
//        toolbar_right_tv.setText(Utils.getString(R.string.添加银行卡));
//        toolbar_right_tv.setVisibility(View.VISIBLE);
        initRecycler();
    }

    private void initRecycler() {
        bankCardAdapter = new BankCardAdapter(R.layout.bankcard_recycler_item,memberBankInfoVoListBeanArrayList);
        bank_card_recycler.setLayoutManager(new LinearLayoutManager(this));
        bank_card_recycler.setAdapter(bankCardAdapter);
        bankCardAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = memberBankInfoVoListBeanArrayList.get(position);
                if(memberBankInfoVoListBean.getIsDefault()==1){
                    return;
                }
                changeDefaultBank(memberBankInfoVoListBean,position);

            }
        });

        requestBankList();
    }

    /**
     * 更改默认银行卡
     * @param memberBankInfoVoListBean
     */
    private void changeDefaultBank(UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean,int position) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("memberBankInfoId",memberBankInfoVoListBean.getId());
        data.put("isDefault",1);
        HttpApiUtils.CpRequest(this, null, RequestUtil.REQUEST_wt5, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.成功切换默认卡));
                for (int i = 0; i < memberBankInfoVoListBeanArrayList.size(); i++) {
                    UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = memberBankInfoVoListBeanArrayList.get(i);
                    if(i==position){
                        memberBankInfoVoListBean.setIsDefault(1);
                    }else {
                        memberBankInfoVoListBean.setIsDefault(0);
                    }
                }
                bankCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    /**
     * 请求已绑定的银行卡列表
     */
    private void requestBankList() {
        HttpApiUtils.CpRequest(this, null, RequestUtil.REQUEST_13r, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(MyApplication.getInstance(),"userInfo",result);//储存用户信息
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> memberBankInfoVoList = userInfoEntity.getMemberInfo().getMemberBankInfoVoList();
                memberBankInfoVoListBeanArrayList.addAll(memberBankInfoVoList);
                 num = SharePreferencesUtil.getInt(MyApplication.getInstance(), CommonStr.REGISTERNUM_BY_ACCOUNTNAME, 5);
                int numStr = num - memberBankInfoVoListBeanArrayList.size();
                if(numStr<=0){
                    canBind  = false;
                }
                numStr= numStr <0?0:numStr;
                remaining_num_tv.setText(numStr +"");
                bankCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    protected void init() {

    }

    private void ininView() {

        remaining_num_tv=findViewById(R.id.remaining_num_tv);

        bankcard_foot_wrap_view=findViewById(R.id.bankcard_foot_wrap_view);
        toolbar_right_tv=findViewById(R.id.toolbar_right_tv);
        bank_card_recycler=findViewById(R.id.bank_card_recycler);
        no_bind_tip_tv=findViewById(R.id.no_bind_tip_tv);
        bankcard_foot_wrap_view.setOnClickListener(this);
        toolbar_right_tv.setOnClickListener(this);
        Intent intent = getIntent();
        boolean haveBank = intent.getBooleanExtra("haveBank", false);
        if(haveBank){
            no_bind_tip_tv.setVisibility(View.GONE);
            bank_card_recycler.setVisibility(View.VISIBLE);

        }
        else{
            bank_card_recycler.setVisibility(View.GONE);
            no_bind_tip_tv.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bankcard_foot_wrap_view://绑定银行卡
            case R.id.toolbar_right_tv:
                if(canBind){
                    startActivity(new Intent(BankCardManageActivity.this,BindBankCardActivity.class));
                }else {
                    showToast(String.format(Utils.getString(R.string.最多只能绑定张银行卡),num+""));
                }
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
