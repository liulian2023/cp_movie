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
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.绑定银行卡));

        new Thread(new Runnable() {
            @Override
            public void run() {
                initJsonData();
            }
        }).start();


        initView();

    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
            isLoaded=true;
        }


    }
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
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
        chooseBank =findViewById(R.id.choose_bank);//选择银行
        choosBankText =findViewById(R.id.bind_bank_name);//银行名字TextVIew
        address =findViewById(R.id.address);//开户地址
        bankAddress =findViewById(R.id.bank_address);//银行分行
        cardName =findViewById(R.id.card_name);//开户人姓名
        cardNum =findViewById(R.id.card_num);//银行卡号
        sureCardNum =findViewById(R.id.sure_card_num);//确认银行卡号
        payPassword =findViewById(R.id.card_pay_password);//安全密码
        sureButton =findViewById(R.id.sure_button);//确认按钮
        chooseBank.setOnClickListener(this);
//        address.setOnClickListener(this);
        sureButton.setOnClickListener(this);

        /**
         * 当前没有绑定过银行卡, 将设为默认view隐藏, 请求接口时强制设为默认
         * 绑定过银行卡将开户名锁定
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
            case R.id.address_linear://开户省市
                if(isLoaded){
                    OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            //返回的分别是三个级别的选中位置
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
                            kaihushengshi =tx;//开户网点
                            address.setText(tx);
                        }
                    })
                            .setTitleText(Utils.getString(R.string.城市选择))
                            .setDividerColor(Color.BLACK)
                            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                            .setContentTextSize(20)
                            .build();
                    pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                    pvOptions.show();
                }else {
                    showToast(Utils.getString(R.string.正在获取城市信息请稍后));
                }

              /*  CityPickerView mCityPickerView = new CityPickerView(this);
                // 设置点击外部是否消失
                mCityPickerView.setCancelable(true);
                // 设置滚轮字体大小
                mCityPickerView.setTextSize(20f);
                // 设置标题
                mCityPickerView.setTitle(Utils.getString(R.string.选择城市));
                // 设置取消文字
                mCityPickerView.setCancelText(Utils.getString(R.string.取消));
                // 设置取消文字颜色
                mCityPickerView.setCancelTextColor(Color.RED);
                // 设置取消文字大小
                mCityPickerView.setCancelTextSize(15f);
                // 设置确定文字
                mCityPickerView.setSubmitText(Utils.getString(R.string.确定));
                // 设置确定文字颜色
                mCityPickerView.setSubmitTextColor(Color.RED);
                // 设置确定文字大小
                mCityPickerView.setSubmitTextSize(15f);
                // 设置头部背景
                mCityPickerView.setHeadBackgroundColor(Color.GRAY);
                mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener() {
                    @Override
                    public void onCitySelect(String prov, String city, String area) {
                        // 省、市、区 分开获取
//                        Utils.logE(TAG, Utils.getString(R.string.省: ) + prov + Utils.getString(R.string. 市: ) + city + Utils.getString(R.string. 区: ) + area);
                    }
                    @Override
                    public void onCitySelect(String str) {
                        // 一起获取
                        kaihushengshi =str;//开户网点
                        address.setText(str);
                    }
                });
                mCityPickerView.show();*/
                break;
            case R.id.choose_bank://选择银行
                if(popupWindow==null){
                    initPopupWindow();
                }
                popupWindow.showAtLocation(chooseBank , Gravity.CENTER, 0, 0); // 显示弹出窗口//显示弹出窗口
                /*
                滑出更换头像页面相关设置
                 */
                break;
            case R.id.action_bar_return:
                finish();
                break;
            case R.id.choose_bank_return://银行列表返回键
                popupWindow.dismiss();
                break;
            case R.id.sure_button:
                String Password = SharePreferencesUtil.getString(this, "payPassword", "");
                if(StringMyUtil.isEmptyString(choosBankText.getText().toString())){
                    showToast(Utils.getString(R.string.请选择银行));
                }else if(StringMyUtil.isEmptyString(address.getText().toString())){
                    showToast(Utils.getString(R.string.请选择开户省市));
                }else if(StringMyUtil.isEmptyString(bankAddress.getText().toString())){
                    showToast(Utils.getString(R.string.请输入银行分行));
                }else if(StringMyUtil.isEmptyString(cardName.getText().toString())){
                    showToast(Utils.getString(R.string.请输入开户名姓名));
                }else if(StringMyUtil.isEmptyString(cardNum.getText().toString())){
                    showToast(Utils.getString(R.string.请输入银行卡号));
                }else if(cardNum.getText().toString().length()<16){
                    showToast(Utils.getString(R.string.银行卡格式不规范请重新输入));
                } else if(StringMyUtil.isEmptyString(sureCardNum.getText().toString())){
                    showToast(Utils.getString(R.string.请再次输入银行卡号));
                }else if(!cardNum.getText().toString().equals(sureCardNum.getText().toString())){
                    showToast(Utils.getString(R.string.两次卡号输入不一致请重新输入));
                }else if(StringMyUtil.isEmptyString(payPassword.getText().toString())){
                    showToast(Utils.getString(R.string.请输入提款密码));
                }/*else if(!.equals(Password)){
                    showtoast(Utils.getString(R.string.安全密码不正确,请重新输入));
                }*/else{
                    requestBindBankCard();
                }
                break;
                default:
                    break;
        }
    }

    /**
     * 请求绑定银行卡
     */
    private void requestBindBankCard() {
        Long user_id = SharePreferencesUtil.getLong(this, "user_id", 0l);
        HashMap<String,Object> dataBindBank =new HashMap<>();
        dataBindBank.put("user_id",user_id);//用户名
        dataBindBank.put("bankCard",cardNum.getText().toString());//银行卡号
        dataBindBank.put("bankName",bankName);//银行名称
        if(memberBankInfoVoList==null||memberBankInfoVoList.size()==0){//当前绑定的是第一涨银行卡, 必须设为默认
            dataBindBank.put("isDefault",1);//是否默认
        }else {
            dataBindBank.put("isDefault",set_default_switch.isChecked()?1:0);//是否默认
        }
        dataBindBank.put("bankDot",kaihushengshi+bankAddress.getText().toString());//银行地址(开户省市加分行)
        dataBindBank.put("accountName",cardName.getText().toString());//开户姓名
        dataBindBank.put("payPassword",payPassword.getText().toString());//安全密码
        HttpApiUtils.CpRequest(this, null, RequestUtil.REQUEST_wt5, dataBindBank, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.银行卡绑定成功));
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
            LayoutInflater inflater = LayoutInflater.from(this);//获得LayoutInflater对象
            View inflate = inflater.inflate(R.layout.choose_bank_popup_window_recycle, null);//读取布局管理器
            popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
            popupWindow.setAnimationStyle(R.style.popupAnimation);//设置进出动画
            chooseBankReturn =inflate.findViewById(R.id.choose_bank_return);//银行列表返回键
            chooseBankReturn.setOnClickListener(this);
            initRecycle(inflate);
    }

    private void initRecycle( View inflate) {
        RecyclerView recyclerView =inflate.findViewById(R.id.choose_bank_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BindBankCardActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        chooseBankRecycleAdapter = new ChooseBankRecycleAdapter(bankList);
        recyclerView.setAdapter(chooseBankRecycleAdapter);
        setRecycleViewOnClickListener();//recycleView点击事件
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
