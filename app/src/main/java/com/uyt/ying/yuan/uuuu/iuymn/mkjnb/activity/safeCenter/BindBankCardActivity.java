package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

/*import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;*/
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.safe_center_adapter.ChooseBankRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.JsonBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GetJsonDataUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class BindBankCardActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout chooseBank;
    private TextView address;
    private TextView choosBankText;
    private TextView default_card_split_tv;
    private EditText bankAddress;
    private EditText cardName;
    private EditText cardNum;
    private EditText sureCardNum;
    private EditText payPassword;
    private Switch set_default_switch;
    private Button sureButton;
    private TextView chooseBankReturn;
    private String kaihushengshi;
    private String bankName;
    private  PopupWindow popupWindow;
    private ArrayList<String> bankList = new ArrayList<>();
    private  ChooseBankRecycleAdapter chooseBankRecycleAdapter;
    private Thread mThread;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isLoaded;
    private  LinearLayout adressLinear;

    private LinearLayout set_default_linear;
    private List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> memberBankInfoVoList;
    private String bankListJson = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank_card);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.???????????????));

        new Thread(new Runnable() {
            @Override
            public void run() {
                initJsonData();
            }
        }).start();


        initView();

    }
    private void initJsonData() {//????????????

        /**
         * ?????????assets ????????????Json??????????????????????????????????????????????????????
         * ???????????????????????????
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//??????assets????????????json????????????

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//???Gson ????????????

        /**
         * ??????????????????
         *
         * ???????????????????????????JavaBean????????????????????????????????? IPickerViewData ?????????
         * PickerView?????????getPickerViewText????????????????????????????????????
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//????????????
            ArrayList<String> cityList = new ArrayList<>();//????????????????????????????????????
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//??????????????????????????????????????????

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//??????????????????????????????
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//????????????
                ArrayList<String> city_AreaList = new ArrayList<>();//??????????????????????????????

                //??????????????????????????????????????????????????????????????????null ?????????????????????????????????????????????
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//??????????????????????????????
            }

            /**
             * ??????????????????
             */
            options2Items.add(cityList);

            /**
             * ??????????????????
             */
            options3Items.add(province_AreaList);
            isLoaded=true;
        }


    }
    public ArrayList<JsonBean> parseData(String result) {//Gson ??????
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            org.json.JSONArray data = new org.json.JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return detail;
    }
    @Override
    protected void init() {

    }

    public void initView() {
        default_card_split_tv=findViewById(R.id.default_card_split_tv);
        set_default_switch=findViewById(R.id.set_default_switch);
        set_default_linear=findViewById(R.id.set_default_linear);

        adressLinear=findViewById(R.id.address_linear);
        adressLinear.setOnClickListener(this);
        chooseBank =findViewById(R.id.choose_bank);//????????????
        choosBankText =findViewById(R.id.bind_bank_name);//????????????TextVIew
        address =findViewById(R.id.address);//????????????
        bankAddress =findViewById(R.id.bank_address);//????????????
        cardName =findViewById(R.id.card_name);//???????????????
        cardNum =findViewById(R.id.card_num);//????????????
        sureCardNum =findViewById(R.id.sure_card_num);//??????????????????
        payPassword =findViewById(R.id.card_pay_password);//????????????
        sureButton =findViewById(R.id.sure_button);//????????????
        chooseBank.setOnClickListener(this);
//        address.setOnClickListener(this);
        sureButton.setOnClickListener(this);

        /**
         * ??????????????????????????????, ???????????????view??????, ?????????????????????????????????
         * ????????????????????????????????????
         */
        UserInfoEntity userInfoEntity= Utils.getUserInfoEntity();
        memberBankInfoVoList = userInfoEntity.getMemberInfo().getMemberBankInfoVoList();
        if(memberBankInfoVoList ==null||memberBankInfoVoList.size()==0){
            set_default_linear.setVisibility(View.GONE);
            default_card_split_tv.setVisibility(View.GONE);
        }else {
            set_default_linear.setVisibility(View.VISIBLE);
            default_card_split_tv.setVisibility(View.VISIBLE);
            UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean memberBankInfoVoListBean = memberBankInfoVoList.get(0);
            String user_name = memberBankInfoVoListBean.getAccountName();
            cardName.setText(user_name);
            cardName.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_linear://????????????
                if(isLoaded){
                    OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            //?????????????????????????????????????????????
                            String opt1tx = options1Items.size() > 0 ?
                                    options1Items.get(options1).getPickerViewText() : "";

                            String opt2tx = options2Items.size() > 0
                                    && options2Items.get(options1).size() > 0 ?
                                    options2Items.get(options1).get(options2) : "";

                            String opt3tx = options2Items.size() > 0
                                    && options3Items.get(options1).size() > 0
                                    && options3Items.get(options1).get(options2).size() > 0 ?
                                    options3Items.get(options1).get(options2).get(options3) : "";

                            String tx = opt1tx + opt2tx + opt3tx;
                            kaihushengshi =tx;//????????????
                            address.setText(tx);
                        }
                    })
                            .setTitleText(Utils.getString(R.string.????????????))
                            .setDividerColor(Color.BLACK)
                            .setTextColorCenter(Color.BLACK) //???????????????????????????
                            .setContentTextSize(20)
                            .build();
                    pvOptions.setPicker(options1Items, options2Items, options3Items);//???????????????
                    pvOptions.show();
                }else {
                    showToast(Utils.getString(R.string.?????????????????????????????????));
                }

              /*  CityPickerView mCityPickerView = new CityPickerView(this);
                // ??????????????????????????????
                mCityPickerView.setCancelable(true);
                // ????????????????????????
                mCityPickerView.setTextSize(20f);
                // ????????????
                mCityPickerView.setTitle(Utils.getString(R.string.????????????));
                // ??????????????????
                mCityPickerView.setCancelText(Utils.getString(R.string.??????));
                // ????????????????????????
                mCityPickerView.setCancelTextColor(Color.RED);
                // ????????????????????????
                mCityPickerView.setCancelTextSize(15f);
                // ??????????????????
                mCityPickerView.setSubmitText(Utils.getString(R.string.??????));
                // ????????????????????????
                mCityPickerView.setSubmitTextColor(Color.RED);
                // ????????????????????????
                mCityPickerView.setSubmitTextSize(15f);
                // ??????????????????
                mCityPickerView.setHeadBackgroundColor(Color.GRAY);
                mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener() {
                    @Override
                    public void onCitySelect(String prov, String city, String area) {
                        // ??????????????? ????????????
//                        Utils.logE(TAG, Utils.getString(R.string.???: ) + prov + Utils.getString(R.string. ???: ) + city + Utils.getString(R.string. ???: ) + area);
                    }
                    @Override
                    public void onCitySelect(String str) {
                        // ????????????
                        kaihushengshi =str;//????????????
                        address.setText(str);
                    }
                });
                mCityPickerView.show();*/
                break;
            case R.id.choose_bank://????????????
                if(popupWindow==null){
                    initPopupWindow();
                }
                popupWindow.showAtLocation(chooseBank , Gravity.CENTER, 0, 0); // ??????????????????//??????????????????
                /*
                ????????????????????????????????????
                 */
                break;
            case R.id.action_bar_return:
                finish();
                break;
            case R.id.choose_bank_return://?????????????????????
                popupWindow.dismiss();
                break;
            case R.id.sure_button:
                String Password = SharePreferencesUtil.getString(this, "payPassword", "");
                if(StringMyUtil.isEmptyString(choosBankText.getText().toString())){
                    showToast(Utils.getString(R.string.???????????????));
                }else if(StringMyUtil.isEmptyString(address.getText().toString())){
                    showToast(Utils.getString(R.string.?????????????????????));
                }else if(StringMyUtil.isEmptyString(bankAddress.getText().toString())){
                    showToast(Utils.getString(R.string.?????????????????????));
                }else if(StringMyUtil.isEmptyString(cardName.getText().toString())){
                    showToast(Utils.getString(R.string.????????????????????????));
                }else if(StringMyUtil.isEmptyString(cardNum.getText().toString())){
                    showToast(Utils.getString(R.string.?????????????????????));
                }else if(cardNum.getText().toString().length()<16){
                    showToast(Utils.getString(R.string.???????????????????????????????????????));
                } else if(StringMyUtil.isEmptyString(sureCardNum.getText().toString())){
                    showToast(Utils.getString(R.string.???????????????????????????));
                }else if(!cardNum.getText().toString().equals(sureCardNum.getText().toString())){
                    showToast(Utils.getString(R.string.??????????????????????????????????????????));
                }else if(StringMyUtil.isEmptyString(payPassword.getText().toString())){
                    showToast(Utils.getString(R.string.?????????????????????));
                }/*else if(!.equals(Password)){
                    showtoast(Utils.getString(R.string.?????????????????????,???????????????));
                }*/else{
                    requestBindBankCard();
                }
                break;
                default:
                    break;
        }
    }

    /**
     * ?????????????????????
     */
    private void requestBindBankCard() {
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        HashMap<String,Object> dataBindBank =new HashMap<>();
        dataBindBank.put("user_id",user_id);//?????????
        dataBindBank.put("bankCard",cardNum.getText().toString());//????????????
        dataBindBank.put("bankName",bankName);//????????????
        if(memberBankInfoVoList==null||memberBankInfoVoList.size()==0){//????????????????????????????????????, ??????????????????
            dataBindBank.put("isDefault",1);//????????????
        }else {
            dataBindBank.put("isDefault",set_default_switch.isChecked()?1:0);//????????????
        }
        dataBindBank.put("bankDot",kaihushengshi+bankAddress.getText().toString());//????????????(?????????????????????)
        dataBindBank.put("accountName",cardName.getText().toString());//????????????
        dataBindBank.put("payPassword",payPassword.getText().toString());//????????????
        HttpApiUtils.CpRequest(this, null, RequestUtil.REQUEST_wt5, dataBindBank, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.?????????????????????));
                Intent intent = new Intent(BindBankCardActivity.this, BankCardManageActivity.class);
                intent.putExtra("haveBank",true);
                SharePreferencesUtil.putString(BindBankCardActivity.this,"bankName",choosBankText.getText().toString());
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailed(String msg) {

            }
        });

    }

    public void initPopupWindow() {
            LayoutInflater inflater = LayoutInflater.from(this);//??????LayoutInflater??????
            View inflate = inflater.inflate(R.layout.choose_bank_popup_window_recycle, null);//?????????????????????
            popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//?????????popupWindow
            popupWindow.setAnimationStyle(R.style.popupAnimation);//??????????????????
            chooseBankReturn =inflate.findViewById(R.id.choose_bank_return);//?????????????????????
            chooseBankReturn.setOnClickListener(this);
            initRecycle(inflate);
    }

    private void initRecycle( View inflate) {
        RecyclerView recyclerView =inflate.findViewById(R.id.choose_bank_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BindBankCardActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        chooseBankRecycleAdapter = new ChooseBankRecycleAdapter(bankList);
        recyclerView.setAdapter(chooseBankRecycleAdapter);
        setRecycleViewOnClickListener();//recycleView????????????
        initListData();

    }

    private void initListData() {
        bankListJson = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.BANK_LIST,"");
        if(StringUtils.isEmpty(bankListJson)){
            requestBankList(true);
        }else {
            handBankListJson(bankListJson);
            requestBankList(false);
        }

    }

    private void requestBankList(boolean addData) {
        HashMap<String,Object> dataBankList =new HashMap<>();
        dataBankList.put("pageNo",1);
        dataBankList.put("pageSize",100);
        HttpApiUtils.CPnormalRequest(this, null, RequestUtil.REQUEST_22rzq, dataBankList, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(MyApplication.getInstance(), CommonStr.BANK_LIST,result);
                if(addData){
                    handBankListJson(result);
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void handBankListJson(String bankListJson) {
        bankList.clear();
        JSONObject jsonObject = JSONObject.parseObject(bankListJson);
        JSONArray bankInfoList = jsonObject.getJSONArray("bankInfoList");
        for (int i = 0; i < bankInfoList.size(); i++) {
            JSONObject jsonObject1 = bankInfoList.getJSONObject(i);
            String bankName = jsonObject1.getString("bankName");
            bankList.add(bankName);
        }

        chooseBankRecycleAdapter.notifyDataSetChanged();
    }

    private void setRecycleViewOnClickListener() {
        chooseBankRecycleAdapter.setOnItemClickListener(new ChooseBankRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                bankName= bankList.get(position);
                choosBankText.setText(bankName);
                popupWindow.dismiss();
            }
        });
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
