package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveLotteryEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.OpenNoMulAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.OpenNoMulBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.BetRulePopChildAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.BetRulePopTabAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.LiveFragmentViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

import static android.view.View.GONE;


public class CusBetRulePop extends PopupWindow {

    private Activity mActivity;
    private Fragment mContext;
    private final View view;

    private ImageView iv_lotterybet_pop;
    private TextView tv_lotterypop_name;
    private TextView tv_lotterypop_qishu;
    private TextView tv_lotterypop_countdown;
    private RecyclerView recy_lotterybetpop;

    private TextView tv_betpop_amount;
    private TextView tv_betpop_charge;
    private TextView tv_betpop_bet;
    private ImageView iv_betpop_selectmoney;
    private ImageView iv_betpop_chouma;
    private TextView tv_betpop_chouma;
    private TextView tv_lotterypop_lastopenresult;
    private ImageView change_lottery_iv;

    private ImageView xian_iv;

    private TextView game_rule_tv;
    private TextView open_result_tv;
    private TextView bet_note_tv;



    private LiveFragmentViewModel mLiveFragmentViewModel;
    private List<OpenNoMulBean> mOpenNoMulList = new ArrayList<>();//开奖结果列表
    private OpenNoMulAdapter mOpenNoMulAdapter;  //开奖结果adapter
    /**
     * 投注相关数据
     */
    private RecyclerView recy_tab;
    private RecyclerView recy_touzhu;
    private BetRulePopChildAdapter mBetRulePopChildAdapter;
    private BetRulePopTabAdapter mBetRulePopTabAdapter;
    private List<BetPopAllModel.BetPopTabModel> betTabList = new ArrayList<>();
    private List<BetPopAllModel.BetPopChildModel> betChildList = new ArrayList<>();

    public enum CLICKTYPE{
        RECHARGE,
        SELECTMA,
        BET,
        CHANGE,
        BET_NOTE,
        OPEN_RESULT,
        GAME_RULE
    }

    public interface OnItemClickListener {
        void onItemClick(CLICKTYPE clickType);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public CusBetRulePop(Activity mActivity, Fragment mContext) {
        super(mActivity);
        this.mActivity = mActivity;
        this.mContext = mContext;
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.lottery_bet_pop_layout, null);
        mLiveFragmentViewModel = ViewModelProviders.of(mContext).get(LiveFragmentViewModel.class);
        initView();
        initPopWindow();
    }
    private void initView() {
        tv_lotterypop_lastopenresult = view.findViewById(R.id.tv_lotterypop_lastopenresult);
        change_lottery_iv = view.findViewById(R.id.change_lottery_iv);
        iv_lotterybet_pop = view.findViewById(R.id.iv_lotterybet_pop);
        tv_lotterypop_name = view.findViewById(R.id.tv_lotterypop_name);
        tv_lotterypop_qishu = view.findViewById(R.id.tv_lotterypop_qishu);
        tv_lotterypop_countdown = view.findViewById(R.id.tv_lotterypop_countdown);
        recy_lotterybetpop = view.findViewById(R.id.recy_lotterybetpop);
        recy_tab = view.findViewById(R.id.recy_tab_betpop);
        recy_touzhu = view.findViewById(R.id.recy_touzhu);
        xian_iv = view.findViewById(R.id.xian_iv);

        game_rule_tv = view.findViewById(R.id.game_rule_tv);
        open_result_tv = view.findViewById(R.id.open_result_tv);
        bet_note_tv = view.findViewById(R.id.bet_note_tv);

        tv_betpop_amount = view.findViewById(R.id.tv_betpop_amount);
        tv_betpop_charge = view.findViewById(R.id.tv_betpop_charge);
        tv_betpop_bet = view.findViewById(R.id.tv_betpop_bet);
        iv_betpop_selectmoney = view.findViewById(R.id.iv_betpop_selectmoney);
        iv_betpop_chouma = view.findViewById(R.id.iv_betpop_chouma);
        tv_betpop_chouma = view.findViewById(R.id.tv_betpop_chouma);

        /**
         * 初始化开奖结果recyclerView
         */
        mOpenNoMulAdapter = new OpenNoMulAdapter(mOpenNoMulList);
        AutoLineFeedLayoutManager layoutManager = new AutoLineFeedLayoutManager();
        layoutManager.setAutoMeasureEnabled(true);
        recy_lotterybetpop.setLayoutManager(layoutManager);
        recy_lotterybetpop.setItemAnimator(new DefaultItemAnimator());
        recy_lotterybetpop.setHasFixedSize(true);
        recy_lotterybetpop.setAdapter(mOpenNoMulAdapter);

        /**
         * 初始化 recy_tab
         */
        mBetRulePopTabAdapter = new BetRulePopTabAdapter(R.layout.item_betrulepop_tab, betTabList);
        GridLayoutManager gridLayoutManager_tab = new GridLayoutManager(mActivity, 3);
        gridLayoutManager_tab.setOrientation(RecyclerView.VERTICAL);
        recy_tab.setLayoutManager(gridLayoutManager_tab);
        recy_tab.setAdapter(mBetRulePopTabAdapter);
        mBetRulePopTabAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                mLiveFragmentViewModel.selectTabPosition(position);
            }
        });

        /**
         * 初始化投注 childrecy
         */
        mBetRulePopChildAdapter = new BetRulePopChildAdapter(R.layout.live_bet_reycle_item_layout, betChildList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 60);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recy_touzhu.setLayoutManager(gridLayoutManager);
        recy_touzhu.setAdapter(mBetRulePopChildAdapter);
        mBetRulePopChildAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                mLiveFragmentViewModel.selectChildPosition(position);
            }
        });
        /**
         * 观察彩票动态数据 更新结果  开奖结果  倒计时
         */
        mLiveFragmentViewModel.getOpenLottery().observe(mContext, (LiveLotteryEntity liveLotteryEntity) -> {

            if (liveLotteryEntity.getTypeName() != null) {
                tv_lotterypop_name.setText(liveLotteryEntity.getTypeName());
            }
            if (liveLotteryEntity.getOpenQishu() != null) {
                    tv_lotterypop_lastopenresult.setText(liveLotteryEntity.getOpenQishu());
            }
            if(liveLotteryEntity.getCurrentQishu()!=null){
                tv_lotterypop_qishu.setText(liveLotteryEntity.getCurrentQishu());

            }
//            if (liveLotteryEntity.getCurrCountTime() != null) {
//                tv_lotterypop_countdown.setText(liveLotteryEntity.getCurrCountTime());
//            }
            if (liveLotteryEntity.getImage_url() != null) {
                GlideLoadViewUtil.LoadNormalView(mActivity, Utils.CPImageUrlCheck(mActivity, liveLotteryEntity.getImage_url()), iv_lotterybet_pop);
            }
            if (liveLotteryEntity.getOpenMulNoList() != null) {
                mOpenNoMulList.clear();
                mOpenNoMulList.addAll(liveLotteryEntity.getOpenMulNoList());
                mOpenNoMulAdapter.notifyDataSetChanged();
            }
            if(liveLotteryEntity.isXian()){
                xian_iv.setVisibility(View.VISIBLE);
            }else {
                xian_iv.setVisibility(GONE);
            }
        });
        mLiveFragmentViewModel.getCountDownLiveData().observe(mContext,s ->  tv_lotterypop_countdown.setText(s));

        /**
         * 观察tab 和 childrecy 数据 刷新界面
         */
        mLiveFragmentViewModel.getBetPopAllData().observe(mContext, (BetPopAllModel betPopAllModel) -> {
            //更新tab
            if (betPopAllModel.getBetPopTabModelList() != null) {
                betTabList.clear();
                betTabList.addAll(betPopAllModel.getBetPopTabModelList());
                mBetRulePopTabAdapter.notifyDataSetChanged();
            }
            /**
             * 从betPopAllModel中取tab选中对应的child数据
             */
            if (betPopAllModel.getBetPopChildModelList() != null) {
                betChildList.clear();
                for (int i = 0; i < betPopAllModel.getBetPopTabModelList().size(); i++) {
                    if (betPopAllModel.getBetPopTabModelList().get(i).isSelect()) {
                        for (int j = 0; j < betPopAllModel.getBetPopChildModelList().size(); j++) {
                            if (betPopAllModel.getBetPopTabModelList().get(i).getId() == betPopAllModel.getBetPopChildModelList().get(j).getParentId()) {
                                betChildList.add(betPopAllModel.getBetPopChildModelList().get(j));
                            }
                        }
                    }
                }
                mBetRulePopChildAdapter.notifyDataSetChanged();
            }
        });

        /**
         * 观察筹码
         */
        mLiveFragmentViewModel.getBetJoinAllLiveData().observe(mContext,(BetJoinAllModel betJoinAllModel)->{
            if (betJoinAllModel.getBetJoinMaModelList()!=null){
                for (int i=0;i<betJoinAllModel.getBetJoinMaModelList().size();i++){
                    BetJoinAllModel.BetJoinMaModel betJoinMaModel = betJoinAllModel.getBetJoinMaModelList().get(i);
                    int danjia = betJoinMaModel.getDanjia();
                    if (betJoinMaModel.isCurrent()){
                        if(danjia+"".length()==4){
                            tv_betpop_chouma.setTextSize(5);
                        }else {
                            tv_betpop_chouma.setTextSize(8);
                        }
                        tv_betpop_chouma.setText(betJoinMaModel.getDanjia()+"");
                        switch (i){
                            case 0:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma1);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 1:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma2);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 2:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma3);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 3:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma4);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 4:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma5);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 5:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma6);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 6:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma7);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 7:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma8);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 8:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma9);
                                tv_betpop_chouma.setVisibility(GONE);
                                break;
                            case 9:
                                iv_betpop_chouma.setImageResource(R.drawable.chouma10);
                                if(betJoinMaModel.getDanjia() == 0){
                                    iv_betpop_chouma.setImageResource(R.drawable.chouma_10);
                                    tv_betpop_chouma.setVisibility(GONE);
                                }else {
                                    iv_betpop_chouma.setImageResource(R.drawable.chouma10);
                                    tv_betpop_chouma.setVisibility(View.VISIBLE);
                                }

                                break;
                        }
                    }
                }
            }
        });
//
        /**
         * 观察用户金额数据
         */
/*        mLiveFragmentViewModel.getAmountLiveData().observe(mContext,(MemberMoney memberMoney)->{
            if (memberMoney!=null&&memberMoney.getMemberMoney()!=null){
                tv_betpop_amount.setText( MoneyUtils.parseMoey(String.valueOf(memberMoney.getMemberMoney().getAmount())));
            }
        });*/
        HttpApiUtils.CPnormalRequest(mActivity, mContext, RequestUtil.REQUEST_06rzq, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberMoney = jsonObject.getJSONObject("memberMoney");
                BigDecimal amount = memberMoney.getBigDecimal("amount");
                tv_betpop_amount.setText(amount+"");
            }

            @Override
            public void onFailed(String msg) {

            }
        });
        /**
         * 点击事件处理
         */
        //充值
        tv_betpop_charge.setOnClickListener(v -> mOnItemClickListener.onItemClick(CLICKTYPE.RECHARGE));
        iv_betpop_selectmoney.setOnClickListener( v->mOnItemClickListener.onItemClick(CLICKTYPE.SELECTMA));
        iv_betpop_chouma.setOnClickListener( v->mOnItemClickListener.onItemClick(CLICKTYPE.SELECTMA));
        change_lottery_iv.setOnClickListener( v->mOnItemClickListener.onItemClick(CLICKTYPE.CHANGE));
        game_rule_tv.setOnClickListener( v->mOnItemClickListener.onItemClick(CLICKTYPE.GAME_RULE));
        open_result_tv.setOnClickListener( v->mOnItemClickListener.onItemClick(CLICKTYPE.OPEN_RESULT));
        bet_note_tv.setOnClickListener( v->mOnItemClickListener.onItemClick(CLICKTYPE.BET_NOTE));
        //投注
        tv_betpop_bet.setOnClickListener(v -> {
         //   mLiveFragmentViewModel.reqBet("3")
            mOnItemClickListener.onItemClick(CLICKTYPE.BET);
        });
    }


    private void initPopWindow() {
        this.setContentView(view);
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popupAnimationNormol150);
//        ColorDrawable dw = new ColorDrawable(0xE6000000);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(mActivity, 0.5f);//0.0-1.0

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(mActivity, 1f);
                if(mContext instanceof LiveFragment){
                    LiveFragment liveFragment = (LiveFragment) mContext;
                    liveFragment.mViewModel.setShowingAmount(false);
                }
            }
        });
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
