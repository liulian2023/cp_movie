package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter.QuotaChangeAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AutoChangeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessBalanceEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.QuotaModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.CommonTipPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.RechargePop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Headers;

public class QuotaChangeActivity extends BaseActivity {
    @BindView(R.id.quota_change_recycler)
    RecyclerView quota_change_recycler;
    @BindView(R.id.big_amount_tv)
    public   TextView big_amount_tv;
    @BindView(R.id.all_recycle_btn)
    Button all_recycle_btn;
    @BindView(R.id.quota_change_swich)
    Switch quota_change_swich;
    @BindView(R.id.big_refresh_iv)
    ImageView big_refresh_iv;
    @BindView(R.id.toolbar_right_iv)
    ImageView toolbar_right_iv;
    QuotaChangeAdapter quotaChangeAdapter;
    ArrayList<QuotaModel> quotaModelArrayList  = new ArrayList<>();
    private ChessBalanceEntity chessBalanceEntity;
    CommonTipPop commonTipPop;
    RechargePop rechargePop;
    private QuotaModel currentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quota_chanege);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.????????????));
        initRecycler();
        requestBalance("");
        //toolbar????????????
        toolbar_right_iv.setImageResource(R.drawable.quota_change_tip_iv);
        toolbar_right_iv.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = toolbar_right_iv.getLayoutParams();
        layoutParams.width= Utils.dip2px(this,25);
        layoutParams.height= Utils.dip2px(this,25);
        toolbar_right_iv.setLayoutParams(layoutParams);
    }

    /**
     * ????????????????????????
     * @param game ???????????????
     */
    private void requestBalance(String game) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("loginType",1);
        if(StringMyUtil.isNotEmpty(game)){
            data.put("game",game);

        }
        HttpApiUtils.CpRequest(this,null, RequestUtil.CHESS_BALANCE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                chessBalanceEntity = JSONObject.parseObject(result, ChessBalanceEntity.class);
                //????????????
                big_amount_tv.setText(chessBalanceEntity.getMemberMoney());
                int autoConvert = chessBalanceEntity.getAutoConvert();
                List<ChessBalanceEntity.ChessGameListBean> chessGameList = chessBalanceEntity.getChessGameList();
                if(StringMyUtil.isEmptyString(game)){
                    quotaModelArrayList.clear();
                    if(chessGameList!=null){
                        for (int i = 0; i < chessGameList.size(); i++) {
                            ChessBalanceEntity.ChessGameListBean chessGameListBean = chessGameList.get(i);
                            QuotaModel quotaModel = new QuotaModel(chessGameListBean.getName2(), chessGameListBean.getFreeMoney(), chessGameListBean.getImage(), autoConvert);
                            quotaModel.setGame(chessGameListBean.getGame());
                            quotaModelArrayList.add(quotaModel);
                        }
                    }
                    quotaChangeAdapter.notifyDataSetChanged();
                }else {
                    for (int i = 0; i <quotaModelArrayList.size(); i++) {
                        QuotaModel currentModel = quotaModelArrayList.get(i);
                        String currentModelGame = currentModel.getGame();
                        for (int j = 0; j < chessGameList.size(); j++) {
                            ChessBalanceEntity.ChessGameListBean chessGameListBean = chessGameList.get(j);
                            String chessGameListBeanGame = chessGameListBean.getGame();
                            if(currentModelGame.equals(chessGameListBeanGame)){
                                currentModel.setAmount(chessGameListBean.getFreeMoney());
                                currentModel.setImageUrl(chessGameListBean.getImage());
                                currentModel.setName(chessGameListBean.getName2());
                                quotaChangeAdapter.notifyItemChanged(i);
                                showToast(Utils.getString(R.string.????????????));
                                break;
                            }
                        }
                    }
                }

                //????????????????????????

                if(autoConvert==1){
                    quota_change_swich.setChecked(true);
                    cantralCheckble(true);

                }else {
                    quota_change_swich.setChecked(false);
                    cantralCheckble(false);
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    /**
     * ??????recyclerView item ???????????????
     * @param swichChecked swich??????????????????
     */
    private void cantralCheckble(boolean swichChecked){
        if(swichChecked){
            for (int i = 0; i < quotaModelArrayList.size(); i++) {
                quotaModelArrayList.get(i).setAutoConvert(1);
            }
        }else {
            for (int i = 0; i < quotaModelArrayList.size(); i++) {
                quotaModelArrayList.get(i).setAutoConvert(0);
            }
        }
        quotaChangeAdapter.notifyDataSetChanged();
    }
    private void initRecycler() {
        quotaChangeAdapter = new QuotaChangeAdapter(quotaModelArrayList,this);
        quota_change_recycler.setLayoutManager(new LinearLayoutManager(this));
        quota_change_recycler.setAdapter(quotaChangeAdapter);
        quotaChangeAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentModel = quotaModelArrayList.get(position);
                switch (view.getId()){
                    case R.id.quota_out_tv:
                        rechargeOrWithDrawClicked(RechargePop.PopType.OUT,currentModel.getGame());
                        break;
                    case R.id.quota_in_tv:
                        rechargeOrWithDrawClicked(RechargePop.PopType.IN,currentModel.getGame());
                        break;
                    case R.id.quota_refresh_iv:
                        requestBalance(currentModel.getGame());
                        break;
                    default:
                        break;
                }
            }
        });
    }
    /**
     *????????????
     */
    private void recyclerAllAmout() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("loginType",1);
        HttpApiUtils.CpRequest(QuotaChangeActivity.this,null, RequestUtil.RECYCLER_BALANCE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                showToast(Utils.getString(R.string.????????????));
                JSONObject jsonObject = JSONObject.parseObject(result);
                String chessMemberMoneyFreeMoney = jsonObject.getString("chessMemberMoneyFreeMoney");
                String memberMoney = jsonObject.getString("memberMoney");

                for (int i = 0; i < quotaModelArrayList.size(); i++) {
                    quotaModelArrayList.get(i).setAmount("0.00");
                }
                quotaChangeAdapter.notifyDataSetChanged();
                big_amount_tv.setText(memberMoney);
                commonTipPop.dismiss();
            }
            @Override
            public void onFailed(String msg) {
            }
        });
    }

    /**
     * ????????????????????????
     */
    private void RequestAutoChange() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("loginType",1);
        HttpApiUtils.CpRequest(QuotaChangeActivity.this, null,RequestUtil.AUTO_CHANGE_QUOTA, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                AutoChangeEntity autoChangeEntity = JSONObject.parseObject(result, AutoChangeEntity.class);
                int autoConvert = autoChangeEntity.getAutoConvert();//?????????????????? 0 ??? 1???
                if(autoConvert==1){
                    setResult(RESULT_OK);
                    quota_change_swich.setChecked(true);
                    cantralCheckble(true);
                }else {
                    quota_change_swich.setChecked(false);
                    cantralCheckble(false);
                }

            }

            @Override
            public void onFailed(String msg) {
                //???????????????,???swicj????????????????????????????????????
                if(quota_change_swich.isChecked()){
                    quota_change_swich.setChecked(false);
                    cantralCheckble(false);
                }else {
                    quota_change_swich.setChecked(true);
                    cantralCheckble(true);
                }
            }
        });
    }
    /**
     * ????????????/??????
     * @param popType
     */
    private void rechargeOrWithDrawClicked(RechargePop.PopType popType,String game) {
        rechargePop = new RechargePop(QuotaChangeActivity.this, QuotaChangeActivity.this, popType,currentModel);
        rechargePop.showAtLocation(all_recycle_btn, Gravity.CENTER, 0, 0);
        ProgressDialogUtil.darkenBackground(this,0.7f);
        rechargePop.setOnClickLintener(new RechargePop.OnClickLintener() {
            @Override
            public void onSureClick(View v, RechargePop.PopType popType) {
                String rechargeStr = rechargePop.recharge_etv.getText().toString();
                if(StringMyUtil.isEmptyString(rechargeStr)){
                    showToast(Utils.getString(R.string.???????????????));
                }else {
                    if(popType== RechargePop.PopType.IN){
                        // ????????????
                        requestRechargeOrWithDraw(rechargeStr, RequestUtil.CHESS_RECHARGE,game);
                    }else {
                        // ????????????
                        requestRechargeOrWithDraw(rechargeStr, RequestUtil.CHESS_WITHDRAW,game);
                    }
                }
            }
        });
    }

    /**
     * ????????????/????????????
     * @param rechargeStr pop?????????????????????
     * @param chessRecharge ????????????
     */
    private void requestRechargeOrWithDraw(String rechargeStr, String chessRecharge,String game) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("game", game);
        data.put("price", rechargeStr);
        data.put("loginType", 1);
        HttpApiUtils.CpRequest(QuotaChangeActivity.this,null, chessRecharge, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                rechargeWithDrawSuccess(result,game);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    /**
     * ??????/????????????  ui??????
     * @param result ???????????????json??????
     */
    private void rechargeWithDrawSuccess(String result,String game) {
        JSONObject jsonObject = JSONObject.parseObject(result);

        ChessBalanceEntity chessBalanceEntity = JSONObject.parseObject(result, ChessBalanceEntity.class);

        //??????cp????????????
        big_amount_tv.setText(chessBalanceEntity.getMemberMoney());
        showToast(Utils.getString(R.string.????????????));
        rechargePop.dismiss();
        List<ChessBalanceEntity.ChessGameListBean> chessGameList = chessBalanceEntity.getChessGameList();
        //????????????/????????????????????????
        for (int i = 0; i < chessGameList.size(); i++) {
            ChessBalanceEntity.ChessGameListBean chessGameListBean = chessGameList.get(i);
            if(game.equals(chessGameListBean.getGame())){
                String freeMoney = chessGameListBean.getFreeMoney();
                //??????????????????
                currentModel.setAmount(freeMoney);
                break;
            }
        }
        quotaChangeAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.all_recycle_btn,R.id.quota_change_swich,R.id.big_refresh_iv,R.id.toolbar_right_iv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.all_recycle_btn:
                //??????????????????????????????????????????????????????toast??????
                boolean canRecycler=false;
                for (int i = 0; i < quotaModelArrayList.size(); i++) {
                    String amount = quotaModelArrayList.get(i).getAmount();
                    if(Double.parseDouble(amount)>0){
                        canRecycler=true;
                        break;
                    }
                }
                if(!canRecycler){
                    showToast(Utils.getString(R.string.????????????????????????));
                }else {
                    //?????????????????????????????????, ??????pop
                    if(commonTipPop==null){
                        commonTipPop=new CommonTipPop(QuotaChangeActivity.this, QuotaChangeActivity.this,Utils.getString(R.string.????????????),Utils.getString(R.string.????????????????????????));
                        commonTipPop.setOnClickLintener(new CommonTipPop.OnClickLintener() {
                            @Override
                            public void onSureClick(View view) {
                                recyclerAllAmout();
                            }
                        });
                    }
                    commonTipPop.showAtLocation(all_recycle_btn, Gravity.CENTER,0,0);
                    ProgressDialogUtil.darkenBackground(QuotaChangeActivity.this,0.7f);
                }
                break;
            case R.id.quota_change_swich:
                if(!quota_change_swich.isChecked()){
                    //?????????????????????, ????????????
                    RequestAutoChange();
                }else {
                    //?????????????????????, ????????????
                    boolean checkBle=true;
                    //?????????????????????????????????,???????????????????????????????????????????????????
                    for (int i = 0; i < quotaModelArrayList.size(); i++) {
                        String amount = quotaModelArrayList.get(i).getAmount();
                        if(Double.parseDouble(amount)>0){
                            checkBle=false;
                            break;
                        }
                    }
                    if(checkBle){
                        RequestAutoChange();
                    }else {
                        showToast(Utils.getString(R.string.?????????????????????????????????????????????????????????));
                        quota_change_swich.setChecked(false);
                    }
                }
                break;
            case R.id.big_refresh_iv:
                requestBalance("");
                break;
            case R.id.toolbar_right_iv:
                String tip=Utils.getString(R.string.????????????????????????????????????????????????????????????????????????????????????);
                CommonTipPop commonTipPop = new CommonTipPop(QuotaChangeActivity.this, QuotaChangeActivity.this, Utils.getString(R.string.??????), tip);
                commonTipPop.showAtLocation(big_refresh_iv,Gravity.CENTER,0,0);
                ProgressDialogUtil.darkenBackground(QuotaChangeActivity.this,0.6f);
                commonTipPop.setOnClickLintener(new CommonTipPop.OnClickLintener() {
                    @Override
                    public void onSureClick(View view) {
                        commonTipPop.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }




    @Override
    protected void init() {

    }
    public static void startAty(Context context){
        Intent intent = new Intent(context, QuotaChangeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
