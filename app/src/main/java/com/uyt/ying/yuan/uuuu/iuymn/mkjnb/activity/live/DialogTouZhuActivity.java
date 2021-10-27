/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong.TzRecyclerAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.common.MyRequest;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.IsClickModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.State;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouzhuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.TimerUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogTouZhuActivity extends BaseActivity {
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
    @BindView(R.id.live_bet_dismiss_iv)
    ImageView live_bet_dismiss_iv;

    TzRecyclerAdapter mTzRecyclerAdapter;

    boolean flag = true;
    private long mLastClickTime = 0;
    public static final long TIME_INTERVAL = 1000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_touzhu);
        //设置布局在底部
        getWindow().setGravity(Gravity.BOTTOM);
        //设置布局填充满宽度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        //设置进出动画
        overridePendingTransition(R.anim.activity_int_400,R.anim.activity_out_400);
        ButterKnife.bind(this);
        showView();
        clickView();
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
        //布局管理器所需参数，上下文
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //B 通过布局管理器，可以控制条目排列顺序  true：反向显示  false：正常显示(默认)
        linearLayoutManager.setReverseLayout(false);
        //C 设置RecyclerView显示的方向，是水平还是垂直！！ GridLayoutManager.VERTICAL(默认) false
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器 ， 参数 linearLayout
        touzhu_recyclerview.setLayoutManager(linearLayoutManager);
        //接收adapter中返回过来的数据，计算recyclerview 中金额的变化
        mTzRecyclerAdapter.setEtChangedListener((View view, int position, List<TouzhuModel> list) -> showTotalmoney());
    }

    private void clickView() {
        touzhu_tab1_tv1.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv1.getText().toString()));
        touzhu_tab1_tv2.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv2.getText().toString()));
        touzhu_tab1_tv3.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv3.getText().toString()));
        touzhu_tab1_tv4.setOnClickListener(v -> touzhu_tab1_money.setText(touzhu_tab1_tv4.getText().toString()));
        live_bet_dismiss_iv.setOnClickListener(v -> finish());

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
                //全部刷新Adapter的数据
                mTzRecyclerAdapter.notifyDataSetChanged();
                //刷新总金额
                showTotalmoney();
            }
        });

        //投注下单
        touzhu_tab_confirm.setOnClickListener(v -> {
            if (flag) {
                long nowTime = System.currentTimeMillis();
                if (nowTime - mLastClickTime > TIME_INTERVAL) {
                    // do something
                    mLastClickTime = nowTime;
                    flag = false;
                    xiadan();
                }
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
                toal_money = toal_money + Integer.parseInt(touzhuList.get(i).getMoney());
            }

        }
        touzhu_tab_money.setText("" + toal_money);
    }
    private void xiadan() {
        for (TouzhuModel bean : touzhuList) {
            if (StringMyUtil.isEmptyString(bean.getMoney())) {
                showToast(Utils.getString(R.string.投注金额不能为空));
                flag = true;
                return;
            }
        }
        for (TouzhuModel bean : touzhuList) {
            if (Long.parseLong(bean.getMoney()) == 0) {
                showToast(Utils.getString(R.string.金额请大于0));
                flag = true;
                return;
            }
        }
        amount = "";
        for (TouzhuModel bean : touzhuList) {
            amount += bean.getMoney() + ",";
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

        MyRequest.ReqTouzhu(DialogTouZhuActivity.this, game, type_id, curtime, qishu, amount, rule_id, index, ma, 0, new MyRequest.MyRequestListener1() {
            @Override
            public void success1(String content) {
                flag = true;
                Gson gson = new Gson();
                try {
                    state = gson.fromJson(content, State.class);
                    if (state!=null&&state.getStatus().equals("success")) {
                        showToast(Utils.getString(R.string.下注成功));
                    }
                    Intent intent = new Intent();
                    intent.putExtra("state", state.getMessage()); //将计算的值回传回去
                    //通过intent对象返回结果，必须要调用一个setResult方法，
                    //setResult(resultCode, giftRainArray);第一个参数表示结果返回码，一般只要大于1就可以，但是
                    setResult(RESULT_OK, intent);
                    finish(); //结束当前的activity的生命周期
                }catch (Exception e){
                    e.printStackTrace();
                    showToast(e.getMessage());
                }
            }

            @Override
            public void failed1(String message) {
                flag = true;
                showToast( message);
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        //重写finish，解决退出动画无效的问题
        overridePendingTransition(R.anim.activity_int_400,R.anim.activity_out_400);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
