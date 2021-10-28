package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.want_to_withdraw;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter.USDTManageActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class CommissionWithdrawFragment extends BaseFragment implements TextWatcher, AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private ArrayList<String> bankCardList =new ArrayList<>();
    private ArrayList<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean>bankCardBeanList = new ArrayList<>();
    private UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean currentBankCardBean;
    private TextView myCommission;//我的佣金
    private EditText priceEdit;//提现金额
    private TextView nowBalance;//提现后余额
    private  EditText passwordEdit;
    private Button commissionButton;
    private BigDecimal commission;
    private String  bankNameAndAccount;
    private String moneyString;
    private LinearLayout rate_linear;
    private LinearLayout usdt_num_linear;
    private TextView rate_tv;
    private TextView usdt_num_tv;
    int drawDirection=0;
    static final  String  BIND_USDT=Utils.getString(R.string.USDT点击绑定USDT);
    private String usdtRate="0";
    private boolean canUseUSDT=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getActivity()).inflate(R.layout.commission_withfdraw_fragment,container,false);
        moneyString=SharePreferencesUtil.getString(getContext(),"moneyString","");
        bindView(view);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        requestUserInfo();
        requestUSDTRate();
        Utils.RequestUsingEquipment(getActivity(),this);
    }

    private void requestUSDTRate() {
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.USDT_RATE, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                usdtRate = jsonObject.getString("usdtRate");//USDT汇率  为空时不能提现到usdtRate
                if(StringMyUtil.isNotEmpty(usdtRate)){
                    rate_tv.setText(usdtRate);
                    canUseUSDT=true;
                }else {
                    canUseUSDT=false;
                }

            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    private void bindView(View view) {
        rate_tv=view.findViewById(R.id.rate_tv);
        usdt_num_tv=view.findViewById(R.id.usdt_num_tv);
        rate_linear=view.findViewById(R.id.rate_linear);
        usdt_num_linear=view.findViewById(R.id.usdt_num_linear);
        spinner=view.findViewById(R.id.commission_spinner);
        spinner.setOnItemSelectedListener(this);
        myCommission=view.findViewById(R.id.my_commission);
        priceEdit=view.findViewById(R.id.commission_draw_edit);
        priceEdit.addTextChangedListener(this);
        nowBalance=view.findViewById(R.id.my_amount);
        passwordEdit=view.findViewById(R.id.commission_password_edit);
        commissionButton=view.findViewById(R.id.commission_button);
        commissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price = priceEdit.getText().toString();
                Long user_id = SharePreferencesUtil.getLong(getActivity(), "user_id", 0l);
                String token = SharePreferencesUtil.getString(getActivity(), "token", "");
                String password = passwordEdit.getText().toString();
                if(StringMyUtil.isEmptyString(price)){
                    showToast(Utils.getString(R.string.请输入提现金额));
                }else if(StringMyUtil.isEmptyString(password)){
                    showToast(Utils.getString(R.string.请输入提款密码));
                }
                else {
                    if(drawDirection==2){
                        if(!canUseUSDT){
                            showToast(Utils.getString(R.string.USDT提现通道已关闭));
                        }else {
                            requestDraw(price, user_id, token, password);
                        }
                    }else {
                        requestDraw(price, user_id, token, password);
                    }
                }
            }
        });
    }

    private void requestDraw(String price, Long user_id, String token, String password) {
        if(currentBankCardBean == null){
            return;
        }
        commissionButton.setClickable(false);
//        BigDecimal bigDecimal = new BigDecimal(Long.parseLong(price)).setScale(2);
        HashMap<String, Object> data = new HashMap<>();
        data.put("price",price);
        data.put("user_id",user_id);
        data.put("payPassword",password);
        data.put("memberBankInfoId",currentBankCardBean.getId());
        data.put("token",token);
        data.put("drawDirection",drawDirection);//提现方向 0 银行卡  1 余额
        HttpApiUtils.CpRequest(getActivity(), this, RequestUtil.REQUEST_12rzq, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                commissionButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(result);
                String message = jsonObject.getString("message");
                showToast(message);
                getActivity().finish();
            }

            @Override
            public void onFailed(String msg) {
                commissionButton.setClickable(true);
            }
        });
    }

    private void requestUserInfo() {
        bankCardList.clear();
        bankCardBeanList.clear();
        bankCardList.add(Utils.getString(R.string.我的余额));
        UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = new UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean();
        memberBankInfoVoListBean.setBankCard(Utils.getString(R.string.我的余额));
        bankCardBeanList.add(memberBankInfoVoListBean);

        HashMap<String, Object> aboutPerson = new HashMap<>();//REQUEST_06rzq
        long userId = SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        aboutPerson.put("user_id",userId);

        HttpApiUtils.CpRequest(getActivity(), this, RequestUtil.REQUEST_13r, aboutPerson, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(getContext(),"userInfo",result);//储存用户信息
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                UserInfoEntity.MemberInfoBean memberInfo = userInfoEntity.getMemberInfo();
                List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> memberBankInfoVoList = memberInfo.getMemberBankInfoVoList();
                for (int i = 0; i < memberBankInfoVoList.size(); i++) {
                    UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = memberBankInfoVoList.get(i);
                    String bankCard = memberBankInfoVoListBean.getBankCard();
                    String bankName = memberBankInfoVoListBean.getBankName();
                    int isDefault = memberBankInfoVoListBean.getIsDefault();
                    //todo  佣金只能转余额
/*                    if(isDefault==1){
                        bankCardList.add(1,bankName+":"+bankCard);
                        bankCardBeanList.add(1,memberBankInfoVoListBean);
                    }else {
                        bankCardList.add(bankName+":"+bankCard);
                        bankCardBeanList.add(memberBankInfoVoListBean);
                    }*/
                }

/*                String bankName = memberInfo.getBankName();
                String bankCard = memberInfo.getBankCard();
                String substring = bankCard.substring(bankCard.length() - 4);
                bankNameAndAccount = bankName + " ***********" + substring;
                bankCardList.add(bankNameAndAccount);*/

                commission = userInfoEntity.getCommission();
                myCommission.setText(commission+"");
                nowBalance.setText(commission+"");

                String usdtAccount = memberInfo.getUsdtAccount();
                //todo  佣金只能转余额
   /*             if(StringMyUtil.isEmptyString(usdtAccount)){
                    //没有ustd账号
                    bankCardList.add(BIND_USDT);
                    UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = new UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean();
                    memberBankInfoVoListBean.setBankCard(BIND_USDT);
                    bankCardBeanList.add(memberBankInfoVoListBean);
                }else {
                    bankCardList.add("USDT:"+usdtAccount);
                    UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = new UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean();
                    memberBankInfoVoListBean.setBankCard("USDT:"+usdtAccount);
                    bankCardBeanList.add(memberBankInfoVoListBean);
                }*/

                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, bankCardList);
                spinner.setAdapter(stringArrayAdapter);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
    /*
    输入框内容监听
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Utils.limitEtvBigDecimal(priceEdit,2,s);
    }

    @Override
    public void afterTextChanged(Editable s) {
        String toString = s.toString();
        if(toString.endsWith(".")){
            return;
        }
        if(!StringMyUtil.isEmptyString(toString)){//输入内容不为空,监听输入内容
            //处理可用余额
            BigDecimal bigDecimal1 = new BigDecimal(toString);  //输入的提现金额
            BigDecimal subtract = commission.subtract(bigDecimal1);//余额和输入金额的差
            nowBalance.setText(subtract+"");
            if(subtract.compareTo(BigDecimal.ZERO)==-1){//如果差小于0,提现后余额显示为0
                nowBalance.setText("0");
            }
            //处理货币数量
            if(StringMyUtil.isEmptyString(usdtRate)||new BigDecimal(usdtRate).compareTo(BigDecimal.ZERO)==0){
                //usdtRate 为空或者为0 不作处理
                return;
            }
            BigDecimal account = new BigDecimal(toString).divide(new BigDecimal(usdtRate),4, BigDecimal.ROUND_FLOOR);
                    if(account.compareTo(BigDecimal.ZERO)==0){
                        usdt_num_tv .setText("0.00");
                    }else {
                        usdt_num_tv.setText(account+"");
                    }
        }
        else {//为空显示原始的余额
            nowBalance.setText(commission+"");
            usdt_num_tv .setText("0.00");

        }
    }
    /*
    下拉列表监听
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentBankCardBean = bankCardBeanList.get(position);
        String content= (String) spinner.getSelectedItem();
        if(content.equals(BIND_USDT)){
            drawDirection=2;
            //跳转usdt管理页面
            USDTManageActivity.startAty(getContext(),true,false);
        }else {
            if(content.contains("USDT")){
                drawDirection=2;
                //选中提现到USDT 显示相关view
                rate_linear.setVisibility(View.VISIBLE);
                usdt_num_linear.setVisibility(View.VISIBLE);
            }else if(content.equals(Utils.getString(R.string.我的余额))){
                drawDirection=1;
                rate_linear.setVisibility(View.GONE);
                usdt_num_linear.setVisibility(View.GONE);
            } else {
                //未选中提现到USDT 隐藏相关view
                drawDirection=0;
                rate_linear.setVisibility(View.GONE);
                usdt_num_linear.setVisibility(View.GONE);
            }
        }
        TextView textView= (TextView) view;
        textView.setTextSize(14);
        textView.setTextColor(Color.parseColor("#fe4942"));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
