package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessBetEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChessBetMoreActivity extends BaseActivity {
    @BindView(R.id.bet_type_tv)
    TextView bet_type_tv;
    @BindView(R.id.order_status_tv)
    TextView order_status_tv;
    @BindView(R.id.game_type_tv)
    TextView game_type_tv;
    @BindView(R.id.bet_time_tv)
    TextView bet_time_tv;
    @BindView(R.id.bet_amount_tv)
    TextView bet_amount_tv;
    @BindView(R.id.win_amount_tv)
    TextView win_amount_tv;
    @BindView(R.id.bet_num_tv)
    TextView bet_num_tv;
    @BindView(R.id.copy_tv)
    TextView copy_tv;
    ChessBetEntity.TouZhuListBean touZhuListBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_bet_more);
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);

        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this,Utils.getString(R.string.下注详情));
        getIntentData();
        initView();
    }

    private void initView() {
        if(touZhuListBean!=null){
            int game = touZhuListBean.getGame();
            String gameName="";
            if(game==50){
                gameName=Utils.getString(R.string.开元棋牌);
            }
            bet_type_tv.setText(gameName);
            order_status_tv.setText(Utils.getString(R.string.完成));
            game_type_tv.setText(touZhuListBean.getTypename());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            bet_time_tv.setText(simpleDateFormat.format(new Date(touZhuListBean.getStartTime())));
            bet_amount_tv.setText("¥"+touZhuListBean.getBetAmount());
            String profitAmount = touZhuListBean.getProfitAmount();
            if(Double.parseDouble(profitAmount)>0){
                win_amount_tv.setTextColor(Color.parseColor("#F75240"));
            }else {
                win_amount_tv.setTextColor(Color.parseColor("#13B42D"));
            }
            win_amount_tv.setText("¥"+ profitAmount);
            bet_num_tv.setText(touZhuListBean.getGameId());
        }
    }

    private void getIntentData() {
        touZhuListBean= (ChessBetEntity.TouZhuListBean) getIntent().getSerializableExtra("touZhuListBean");
    }

    public static void  startAty(Context context, ChessBetEntity.TouZhuListBean touZhuListBean){
        Intent intent = new Intent(context, ChessBetMoreActivity.class);
        intent.putExtra("touZhuListBean",touZhuListBean);
        context.startActivity(intent);
    }
    @OnClick({R.id.copy_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.copy_tv:
                Utils.copyStr("bet_num",bet_num_tv.getText().toString());
                break;
                default:
                    break;
        }
    }
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
