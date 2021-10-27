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

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter.USDTManageActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
//import com.example.administrator.aaa.utils.PopupWindowUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest.ReqMemberMoney1;

public class BalanceWithdrawFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    private TextView myBalance;//我的余额
    private TextView canUseBalance;//可提余额
    private EditText priceEdit;//提现金额
    private TextView nowBalance;//提现后余额
    private Spinner banlance_spinner;
    private  EditText passwordEdit;
    private TextView streamTextView;
    private Button withdrawButton;
    private BigDecimal amount;
    private BigDecimal restAmount;
    private String moneyString;
    private ArrayList<String> bankCardList =new ArrayList<>();
    private ArrayList<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean>bankCardBeanList = new ArrayList<>();
    static final  String  BIND_USDT=Utils.getString(R.string.USDT(点击绑定USDT));
    private LinearLayout rate_linear;
    private LinearLayout usdt_num_linear;
    private TextView rate_tv;
    private TextView usdt_num_tv;
    int drawDirection=1;
    private String usdtRate="0";
    private boolean canUseUSDT;

    private UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean currentBankCardBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.banlance_withfdraw_fragment,container,false);
        moneyString=SharePreferencesUtil.getString(getContext(),"moneyString","");
        bindView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getMoney();
        requestUSDTRate();
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
    private void getMoney() {
        bankCardList.clear();
        bankCardBeanList.clear();
        HashMap<String, Object> aboutPerson = new HashMap<>();//REQUEST_06rzq
        long userId = SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        aboutPerson.put("user_id",userId);
        HttpApiUtils.CpRequest(getActivity(), this, RequestUtil.REQUEST_13r, aboutPerson, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ProgressDialogUtil.stop(getContext());
                SharePreferencesUtil.putString(getContext(),"userInfo",result);//储存用户信息
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                UserInfoEntity.MemberInfoBean memberInfo = userInfoEntity.getMemberInfo();
                List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> memberBankInfoVoList = memberInfo.getMemberBankInfoVoList();
                for (int i = 0; i < memberBankInfoVoList.size(); i++) {
                    UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = memberBankInfoVoList.get(i);
                    String bankCard = memberBankInfoVoListBean.getBankCard();
                    String bankName = memberBankInfoVoListBean.getBankName();
                    int isDefault = memberBankInfoVoListBean.getIsDefault();
                    if(isDefault==1){
                        bankCardList.add(0,bankName+":"+bankCard);
                        bankCardBeanList.add(0,memberBankInfoVoListBean);
                    }else {
                        bankCardList.add(bankName+":"+bankCard);
                        bankCardBeanList.add(memberBankInfoVoListBean);
                    }
                }
                String usdtAccount = memberInfo.getUsdtAccount();
                if(StringMyUtil.isEmptyString(usdtAccount)){
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
                }
                if(getActivity()!=null&&!getActivity().isFinishing()){
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, bankCardList);
                    banlance_spinner.setAdapter(stringArrayAdapter);
                }
            }

            @Override
            public void onFailed(String msg) {

            }
        });

        ReqMemberMoney1(getContext(), 0, new MyRequest.MyRequestListener() {
            @Override
            public void success(String content) {
                ProgressDialogUtil.stop(getContext());
                try{
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                    amount = memberMoney.getBigDecimal("amount");//余额
                    restAmount = memberMoney.getBigDecimal("restAmount");//余额
                    myBalance.setText(amount+"");
                    canUseBalance.setText(amount+"");
                    streamTextView.setText(restAmount+"");
                    nowBalance.setText(amount+"");

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(String content) {
                //   showtoast(Utils.getString(R.string.请求数据错误));
                ProgressDialogUtil.stop(getContext());
            }
        });
    }


    private void bindView(View view) {
        rate_tv=view.findViewById(R.id.rate_tv);
        usdt_num_tv=view.findViewById(R.id.usdt_num_tv);
        rate_linear=view.findViewById(R.id.rate_linear);
        usdt_num_linear=view.findViewById(R.id.usdt_num_linear);
        myBalance=view.findViewById(R.id.myBalance);
        canUseBalance=view.findViewById(R.id.can_use_banlance);
        priceEdit=view.findViewById(R.id.withdraw_price_edit);
        nowBalance=view.findViewById(R.id.now_balance);
        banlance_spinner=view.findViewById(R.id.banlance_spinner);
        banlance_spinner.setOnItemSelectedListener(this);
        passwordEdit=view.findViewById(R.id.withdraw_password_edit);
        withdrawButton=view.findViewById(R.id.withdraw_button);
        streamTextView = view.findViewById(R.id.withdraw_stream_tv);
        priceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.limitEtvBigDecimal(priceEdit,2,s);

            }
            /*
            提现金额输入框的监听
             */
            @Override
            public void afterTextChanged(Editable s) {
                String toString = s.toString();
                if(toString.endsWith(".")){
                    return;
                }
                if(!StringMyUtil.isEmptyString(toString)){//输入内容不为空,监听输入内容
                    /**
                     *      处理提现后余额
                     */

                    String price = priceEdit.getText().toString();
                        BigDecimal   bigDecimal1 = new BigDecimal(price);  //输入的提现金额
                    if(amount!=null){
                        BigDecimal subtract = amount.subtract(bigDecimal1);//余额和输入金额的差
                        nowBalance.setText(subtract+"");
                        if(subtract.compareTo(BigDecimal.ZERO)==-1){//如果差小于0,提现后余额显示为0
                            nowBalance.setText("0");
                        }
                    }
                    /**
                     *    处理货币数量
                     */

                    if(StringMyUtil.isEmptyString(usdtRate)||new BigDecimal(usdtRate).compareTo(BigDecimal.ZERO)==0){
                        //usdtRate 为空或者为0 不作处理
                        return;
                    }
                    BigDecimal amount = new BigDecimal(toString).divide(new BigDecimal(usdtRate),4, BigDecimal.ROUND_FLOOR);
                    if(amount.compareTo(BigDecimal.ZERO)==0){
                        usdt_num_tv .setText("0.00");
                    }else {
                        usdt_num_tv.setText(amount+"");
                    }

                } else {//为空显示原始的余额
                    nowBalance.setText(amount+"");
                    usdt_num_tv .setText("0.00");
                }

            }
        });
        withdrawButton.setOnClickListener(new View.OnClickListener() {
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
        if(currentBankCardBean==null){
            return;
        }
        withdrawButton.setClickable(false);
        HashMap<String, Object> data = new HashMap<>();
        data.put("price",price);
        data.put("user_id",user_id);
        data.put("memberBankInfoId",currentBankCardBean.getId());
        data.put("payPassword",password);
        data.put("token",token);
        data.put("drawDirection",drawDirection);
        HttpApiUtils.CpRequest(getActivity(), this, RequestUtil.REQUEST_wt4, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                withdrawButton.setClickable(true);
                JSONObject jsonObject = JSONObject.parseObject(result);
                String message = jsonObject.getString("message");
                ProgressDialogUtil.stop(getActivity());
                showToast(message);
                getActivity().finish();
            }

            @Override
            public void onFailed(String msg) {
                withdrawButton.setClickable(true);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentBankCardBean = bankCardBeanList.get(position);
        String content= (String) banlance_spinner.getSelectedItem();
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
            }else {
                //未选中提现到USDT 隐藏相关view
                drawDirection=1;
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
