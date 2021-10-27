package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.rxhttp.net.utils.LogUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveLotteryEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.MultipleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.BetJoinContentOldAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.BetJoinMoneyAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinContentModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MultipleModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveBus;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class CusBetJoinOldPop extends PopupWindow {

    private Activity mActivity;
    private Fragment mContext;
    private final View view;

    private LiveFragmentViewModel mLiveFragmentViewModel;

    private ImageView iv_betjoin_delete;
    private TextView tv_betjoin_typename;
    private TextView tv_betjoin_qishu;
    private TextView tv_betjoin_countdown;
    private RecyclerView contentRecy;
    private LinearLayout bet_join_wrap_linear;
    private RecyclerView moneyRecy;
    private RecyclerView multiple_money_recycler;
    MultipleAdapter multipleAdapter;
    ArrayList<MultipleModel>multipleList = new ArrayList<>();
    private TextView tv_betjoin_zhushu;
    private TextView tv_betjoin_pop_total;
    private TextView tv_betjoin_pop_amount;
    private ImageView iv_betjoin_pop_confirm;
    private EditText edit_kuaisu_money;
    private TextView tv_kuaisu_one,tv_kuaisu_two,tv_kuaisu_three,tv_kuaisu_four;

    private List<BetJoinContentModel> betContentList = new ArrayList<>();
    private List<String> betMoneyList = new ArrayList<>();
    private BetJoinContentOldAdapter betJoinContentOldAdapter;
    private BetJoinMoneyAdapter betJoinMoneyAdapter;

    private LiveEntity.AnchorMemberRoomListBean mLiveData;
    private int zhushu = 0;
    private int chouma = 0;

    String multiple="1";
    private EditText amountEtv;

    public CusBetJoinOldPop(Activity mActivity, Fragment mContext,LiveEntity.AnchorMemberRoomListBean mLiveData) {
        super(mActivity);
        this.mActivity = mActivity;
        this.mContext = mContext;
        this.mLiveData = mLiveData;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.betjoinold_pop_layout, null);
        mLiveFragmentViewModel = ViewModelProviders.of(mContext).get(LiveFragmentViewModel.class);
        initView();
        initPopWindow();
    }

    private void initView() {
        multiple_money_recycler = view.findViewById(R.id.multiple_money_recycler);
        iv_betjoin_delete = view.findViewById(R.id.iv_betjoin_delete);
        tv_betjoin_typename = view.findViewById(R.id.tv_betjoin_typename);
        tv_betjoin_qishu = view.findViewById(R.id.tv_betjoin_qishu);
        tv_betjoin_countdown = view.findViewById(R.id.tv_betjoin_countdown);
        bet_join_wrap_linear = view.findViewById(R.id.bet_join_wrap_linear);
        contentRecy = view.findViewById(R.id.recy_betjoin_pop_content);
        moneyRecy = view.findViewById(R.id.recy_betjoin_pop_money);
        tv_betjoin_zhushu = view.findViewById(R.id.tv_betjoin_zhushu);
        tv_betjoin_pop_total = view.findViewById(R.id.tv_betjoin_pop_total);
        tv_betjoin_pop_amount = view.findViewById(R.id.tv_betjoin_pop_amount);
        iv_betjoin_pop_confirm = view.findViewById(R.id.iv_betjoin_pop_confirm);
        edit_kuaisu_money = view.findViewById(R.id.edit_betjoin_pop_money);
        /**
         * 金额倍数recycler
         */
        multipleAdapter = new MultipleAdapter(R.layout.multiple_money_recycler_item,multipleList);
        multiple_money_recycler.setLayoutManager(new GridLayoutManager(mActivity,5));
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


                for (int i = 0; i < betContentList.size(); i++) {
                    BetJoinContentModel betJoinContentModel = betContentList.get(i);
                    betJoinContentModel.setMultiple(multipleModel.getContent());
                }
                betJoinContentOldAdapter.notifyDataSetChanged();
                initTotalMoney();
            }
        });

        /**
         * 快捷筹码recycler
         */
        betJoinMoneyAdapter = new BetJoinMoneyAdapter(R.layout.item_betjoin_money,betMoneyList);
        LinearLayoutManager betJoinMoneyManager = new LinearLayoutManager(mActivity);
        betJoinMoneyManager.setOrientation(RecyclerView.HORIZONTAL);
        moneyRecy.setLayoutManager(betJoinMoneyManager);
        moneyRecy.setHasFixedSize(true);
        moneyRecy.setAdapter(betJoinMoneyAdapter);
        betMoneyList.clear();
        betMoneyList.add("10");
        betMoneyList.add("50");
        betMoneyList.add("100");
        betMoneyList.add("1000");
        betJoinMoneyAdapter.notifyDataSetChanged();

        betJoinMoneyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

                for (int i = 0; i < betContentList.size(); i++) {
                    betContentList.get(i).setDanjia(Integer.parseInt(betMoneyList.get(position)));
                }
                betJoinContentOldAdapter.notifyDataSetChanged();
               initTotalMoney();
            }
        });

        /**
         * 初始化content Recy
         */
        betJoinContentOldAdapter = new BetJoinContentOldAdapter(R.layout.item_betjoin_oldcontent, betContentList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        contentRecy.setLayoutManager(linearLayoutManager);
        contentRecy.addItemDecoration(new SpacesItemDecoration(5));
        contentRecy.setHasFixedSize(true);
        contentRecy.setAdapter(betJoinContentOldAdapter);
        betJoinContentOldAdapter.addChildClickViewIds(R.id.iv_item_betjoin_delete);
        betJoinContentOldAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            if (view.getId() == R.id.iv_item_betjoin_delete) {
                /**
                 * 删除选中的数据
                 */
                if(betContentList.size()<=1){
                    ToastUtil.showToast(Utils.getString(R.string.至少选中一项));
                    return;
                }
                mLiveFragmentViewModel.deleteByChildId(betContentList.get(position).getId());
                initTotalMoney();
            }
        });



        betJoinContentOldAdapter.setEtChangedListener((View view, int position, String s) -> {

            initTotalMoney();

        });


        /**
         * 观察数据  取 tab 选中   child选中的数据
         */
        mLiveFragmentViewModel.getBetPopAllData().observe(mContext, (BetPopAllModel betPopModel) -> {
            if (betPopModel.getBetPopChildModelList() != null) {
                /**
                 * 获取倍数 单价  赋值给content item
                 */
                BetJoinAllModel betJoinAllModel = mLiveFragmentViewModel.getBetJoinAllLiveData().getValue();

                for (int i = 0; i < betJoinAllModel.getBetJoinMaModelList().size(); i++) {
                    if (betJoinAllModel.getBetJoinMaModelList().get(i).isSelect()) {
                        chouma = betJoinAllModel.getBetJoinMaModelList().get(i).getDanjia();
                    }
                }
                String multiple="1";
                if(betContentList.size()!=0){
                    multiple  = betContentList.get(0).getMultiple();
                }
                betContentList.clear();
                zhushu = 0;

                for (int j = 0; j < betPopModel.getBetPopChildModelList().size(); j++) {
                    BetPopAllModel.BetPopChildModel bean = betPopModel.getBetPopChildModelList().get(j);
                    if (bean.isSelect()) {
                        zhushu++;
                        BetJoinContentModel betJoinContentModel = new BetJoinContentModel();
                        betJoinContentModel.setId(bean.getId());
                        betJoinContentModel.setRuleId(bean.getRuleId());
                        betJoinContentModel.setType(bean.getGroupname());
                        betJoinContentModel.setName(bean.getName());
                        betJoinContentModel.setChouma(chouma);
                        betJoinContentModel.setDanjia(chouma);
                        betJoinContentModel.setMultiple(multiple);
//                        betJoinContentModel.setDanjia(bean.getDanjia());
                        betContentList.add(betJoinContentModel);

                    }
                }
                betJoinContentOldAdapter.notifyDataSetChanged();
                tv_betjoin_zhushu.setText("" + zhushu);
                initTotalMoney();
            }
        });


        /**
         * 观察 彩票数据
         */
        mLiveFragmentViewModel.getOpenLottery().observe(mContext, (LiveLotteryEntity liveLotteryEntity) -> {
            if (liveLotteryEntity.getTypeName() != null) {
                tv_betjoin_typename.setText(liveLotteryEntity.getTypeName());
            }
            if (liveLotteryEntity.getOpenQishu() != null) {
                tv_betjoin_qishu.setText(Utils.getString(R.string.当前) + liveLotteryEntity.getCurrentQishu() + Utils.getString(R.string.期));
            }
        });

        mLiveFragmentViewModel.getCountDownLiveData().observe(mContext, s -> tv_betjoin_countdown.setText(Utils.getString(R.string.封盘空格) + s));

        /**
         * 观察用户金额数据
         */
/*        mLiveFragmentViewModel.getAmountLiveData().observe(mContext, (MemberMoney memberMoney) -> {
            if (memberMoney != null && memberMoney.getMemberMoney() != null) {
                tv_betjoin_pop_amount.setText(MoneyUtils.parseMoey(String.valueOf(memberMoney.getMemberMoney().getAmount())));
            }
        });*/
        HttpApiUtils.CPnormalRequest(mActivity, mContext, RequestUtil.REQUEST_06rzq, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                BigDecimal amount = memberMoney.getBigDecimal("amount");
                tv_betjoin_pop_amount.setText(amount+"");
            }

            @Override
            public void onFailed(String msg) {

            }
        });
        /**
         * 投注
         */
        iv_betjoin_pop_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isNotFastClick()){
                    mLiveFragmentViewModel.reqBet2(betContentList,mActivity,mContext,mLiveData,CusBetJoinOldPop.this);
                }
            }
        });
        /**
         * 投注成功状态事件
         */
        LiveBus.getDefault().subscribe("betStatus",String.class).observe(mContext,(String s)->{
            LogUtils.e("betStatus:"+s);
            this.dismiss();
        });


        iv_betjoin_delete.setOnClickListener(v -> this.dismiss());
        bet_join_wrap_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amountEtv!=null){
                    Utils.hideSoftKeyBoard(mActivity,amountEtv);
                }
            }
        });

        edit_kuaisu_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(s.toString())){
                    edit_kuaisu_money.setText(""+1);
                }
                if (s.toString().equals("0")){
                    edit_kuaisu_money.setText(""+1);
                }
                if (!TextUtils.isEmpty(s.toString())){
                    for (int i = 0; i < betContentList.size(); i++) {
                        BetJoinContentModel betJoinContentModel = betContentList.get(i);
                        betJoinContentModel.setDanjia(Integer.parseInt(s.toString())*Integer.parseInt(multiple));
                        betJoinContentModel.setQuickAmount(edit_kuaisu_money.getText().toString());
                    }
                    //全部刷新Adapter的数据
                    betJoinContentOldAdapter.notifyDataSetChanged();
                    //刷新总金额
                    initTotalMoney();

                }
                edit_kuaisu_money.setSelection(edit_kuaisu_money.getText().length());
            }
        });
    }

    private void initTotalMoney() {
        //刷新总金额
        int toal_money = 0;
        for (int i = 0; i < betContentList.size(); i++) {
            if (StringMyUtil.isEmptyString(betContentList.get(i).getDanjia())) {
                toal_money = toal_money + 0;
            } else {
                toal_money = toal_money + (betContentList.get(i).getDanjia()*Integer.parseInt(betContentList.get(i).getMultiple()));
            }
            tv_betjoin_pop_total.setText("" + toal_money);
        }
    }


    private void initPopWindow() {
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
        ColorDrawable dw = new ColorDrawable(0xE6000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(mActivity, 0.5f);//0.0-1.0
        this.setOnDismissListener(() ->
                backgroundAlpha(mActivity, 1f)
        );
    }


    /**
     * 设置添加屏幕的背景透明度(值越大,透明度越高)
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1f) {
            //恢复屏幕亮度时需要移除flag,否则在切换activity时会有短暂黑屏
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        context.getWindow().setAttributes(lp);
    }
}
