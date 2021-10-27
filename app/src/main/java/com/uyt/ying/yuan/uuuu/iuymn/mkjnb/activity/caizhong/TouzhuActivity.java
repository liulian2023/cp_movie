

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.caizhong;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.MultipleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.TzRecyclerAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MultipleModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.State;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TimerUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouzhuActivity extends BaseActivity {

    String money;
    String qishu;
    String ma;
    String index;
    //   GetGroup getGroup;
    List<TouzhuModel> touzhuList;
    //    List<GetGroup.GameruleBean> gameRulelist;
//    HashMap<String,Boolean> isClickMap;
    List<IsClickModel> isClickList;
    //    List<TouzhuModel> list;
    private Map<String, Object> dataXiadan;//下单请求参数
    String game, type_id;
    String curtime;
    String amount = "";
    String rule_id = "";
    State state;//下单状态


    @BindView(R.id.action_bar_return)
    TextView action_bar_return;
    @BindView(R.id.touzhu_recyclerview)
    RecyclerView touzhu_recyclerview;
    @BindView(R.id.touzhu_tab1_money)
    EditText touzhu_tab1_money;
    @BindView(R.id.touzhu_tab1_tv1)
    TextView touzhu_tab1_tv1;
    @BindView(R.id.touzhu_tab1_tv2)
    TextView touzhu_tab1_tv2;
    @BindView(R.id.touzhu_tab1_tv3)
    TextView touzhu_tab1_tv3;
    @BindView(R.id.touzhu_tab1_tv4)
    TextView touzhu_tab1_tv4;
    @BindView(R.id.touzhu_tab_num)
    TextView touzhu_tab_num;
    @BindView(R.id.touzhu_tab_money)
    TextView touzhu_tab_money;
    @BindView(R.id.touzhu_tab_confirm)
    Button touzhu_tab_confirm;
    @BindView(R.id.multiple_money_recycler)
    RecyclerView multiple_money_recycler;
    MultipleAdapter multipleAdapter;
    ArrayList<MultipleModel> multipleList = new ArrayList<>();
    TzRecyclerAdapter mTzRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_touzhu);
        ButterKnife.bind(this);

        showView();
        clickView();

        intiMultipleRecycler();
    }

    private void intiMultipleRecycler() {
        /**
         * 金额倍数recycler
         */
        multipleAdapter = new MultipleAdapter(R.layout.multiple_money_recycler_item,multipleList);
        multiple_money_recycler.setLayoutManager(new GridLayoutManager(this,5));
        multiple_money_recycler.setAdapter(multipleAdapter);
        MultipleModel firstModel = new MultipleModel("1");
        firstModel.setCheck(true);
        multipleList.add(firstModel);
        multipleList.add(new MultipleModel("2"));
        multipleList.add(new MultipleModel("5"));
        multipleList.add(new MultipleModel("10"));
        multipleList.add(new MultipleModel("20"));
        multipleAdapter.notifyDataSetChanged();
        multipleAdapter.addChildClickViewIds(R.id.multiple_tv);
        multipleAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                for (int i = 0; i < multipleList.size(); i++) {
                    MultipleModel multipleModel = multipleList.get(i);
                    multipleModel.setCheck(false);
                }
                MultipleModel multipleModel = multipleList.get(position);
                multipleModel.setCheck(true);
                multipleAdapter.notifyDataSetChanged();
                for (int i = 0; i < touzhuList.size(); i++) {
                    TouzhuModel touzhuModel = touzhuList.get(i);
//                    touzhuModel.setMoney(Integer.parseInt(touzhuModel.getMoney())*Integer.parseInt(multipleModel.getContent())+"");
                    touzhuModel.setMultiple(multipleModel.getContent());
                }
                mTzRecyclerAdapter.notifyDataSetChanged();
                //刷新总金额
                showTotalmoney();
            }
        });
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        type_id = intent.getStringExtra("type_id");
        money = intent.getStringExtra("money");
        qishu = intent.getStringExtra("qishu");
        ma = intent.getStringExtra("ma");
        index = intent.getStringExtra("index");

        touzhuList = (List<TouzhuModel>) intent.getSerializableExtra("touzhuList");
    }


    private void showView() {

        touzhu_tab_num.setText("" + touzhuList.size());
        touzhu_tab_money.setText("" + touzhuList.size() * Long.parseLong(money));//首次显示总金额
        touzhu_tab1_money.setText(money);//首次显示快速金额

        mTzRecyclerAdapter = new TzRecyclerAdapter(this, touzhuList);
        touzhu_recyclerview.setAdapter(mTzRecyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        touzhu_recyclerview.setLayoutManager(linearLayoutManager);
        mTzRecyclerAdapter.setEtChangedListener((View view, int position, List<TouzhuModel> list) -> showTotalmoney());
    }

    private void clickView() {
        action_bar_return.setOnClickListener(v -> onBackPressed());
        touzhu_tab1_tv1.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv1.getText().toString()));
        touzhu_tab1_tv2.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv2.getText().toString()));
        touzhu_tab1_tv3.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv3.getText().toString()));
        touzhu_tab1_tv4.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv4.getText().toString()));


        // 快速金额
        touzhu_tab1_money.setSelection(touzhu_tab1_money.getText().toString().length());
        touzhu_tab1_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //如果EditText中的数据不为空，且长度大于指定的最大长度
                if (!TextUtils.isEmpty(s) && s.length() > 8) {
                    //删除指定长度之后的数据
                    s.delete(8, touzhu_tab1_money.getSelectionEnd());
                }
                for (int i = 0; i < touzhuList.size(); i++) {
                    touzhuList.get(i).setMoney(s.toString());
                }
                mTzRecyclerAdapter.notifyDataSetChanged();
                //刷新总金额
                showTotalmoney();
            }
        });

        //投注下单
        touzhu_tab_confirm.setOnClickListener(v -> {
            if(Utils.isNotFastClick()){
                xiadan();
            }
        });
    }
    //显示总金额
    public void showTotalmoney() {
        int toal_money = 0;
        for (int i = 0; i < touzhuList.size(); i++) {
            if (StringMyUtil.isEmptyString(touzhuList.get(i).getMoney())) {
                toal_money = toal_money + 0;
            } else {
                toal_money = toal_money + (Integer.parseInt(touzhuList.get(i).getMoney())*Integer.parseInt(touzhuList.get(i).getMultiple()));
            }

        }
        touzhu_tab_money.setText("" + toal_money);
    }
    private void xiadan() {
        for (TouzhuModel bean : touzhuList) {
            if (StringMyUtil.isEmptyString(bean.getMoney())) {
                showToast(Utils.getString(R.string.投注金额不能为空));
                return;
            }
        }
        for (TouzhuModel bean : touzhuList) {
            if (Long.parseLong(bean.getMoney()) == 0) {
                showToast(Utils.getString(R.string.金额请大于0));
                return;
            }
        }
        amount = "";
        for (TouzhuModel bean : touzhuList) {
            amount += (Integer.parseInt(bean.getMoney())*Integer.parseInt(bean.getMultiple())) + ",";
        }
        if (amount.length() > 1) {
            amount = amount.substring(0, amount.length() - 1);
        }
        curtime = TimerUtils.getTimeStyleTwo();   //2019-07-10 13:44:16
        rule_id = "";
        for (TouzhuModel bean : touzhuList) {
            rule_id += bean.getId() + ",";
        }
        if (rule_id.length() > 1) {
            rule_id = rule_id.substring(0, rule_id.length() - 1);
        }
        MyRequest.ReqTouzhu(TouzhuActivity.this, game, type_id, curtime, qishu, amount, rule_id, index, ma, 0, new MyRequest.MyRequestListener1() {
            @Override
            public void success1(String content) {
                Gson gson = new Gson();
                try {
                    state = gson.fromJson(content, State.class);
                    if (state!=null&&state.getStatus().equals("success")) {
                        showToast(Utils.getString(R.string.下注成功));
                    }
                    Intent intent = new Intent();
                    intent.putExtra("state", state.getMessage()); //将计算的值回传回去
                    //通过intent对象返回结果，必须要调用一个setResult方法，
                    //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
                    setResult(102, intent);
                    finish(); //结束当前的activity的生命周期
                }catch (Exception e){
                    e.printStackTrace();
                    showToast(e.getMessage());
                }
            }

            @Override
            public void failed1(String  failMesage) {
                showToast(Utils.getString(R.string.下注失败)+"\n" + failMesage);
            }
        });
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
