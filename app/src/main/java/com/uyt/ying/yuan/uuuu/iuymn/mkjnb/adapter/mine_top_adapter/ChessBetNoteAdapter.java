package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessBetEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class ChessBetNoteAdapter extends CommonAdapter<ChessBetNoteAdapter.MyHolder, ChessBetEntity.TouZhuListBean> {
    Context context;

    public ChessBetNoteAdapter(ArrayList<ChessBetEntity.TouZhuListBean> list, Context context) {
        super(list);
        this.context = context;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        ChessBetEntity.TouZhuListBean itemModel = getItemModel(position);
        commonHolder.game_rule_type_name_tv.setText(itemModel.getTypename());//棋牌名
        commonHolder.bet_amount_tv.setText(itemModel.getValidBetAmount());//有效投注金额
        TextView winning_amount_tv = commonHolder.winning_amount_tv;
        String profitAmount = itemModel.getProfitAmount();
        winning_amount_tv.setText(profitAmount);//中奖金额
        if(Double.parseDouble(profitAmount)>0){
            winning_amount_tv.setTextColor(Color.parseColor("#F75240"));
        }else {
                winning_amount_tv.setTextColor(Color.parseColor("#13B42D"));
        }
        ImageView circle_iv = commonHolder.circle_iv;
        if(position%6==0){
            circle_iv.setImageResource(R.drawable.hong);
        }else if(position%6==1){
            circle_iv.setImageResource(R.drawable.lan);
        }else if(position%6==2){
            circle_iv.setImageResource(R.drawable.huang);
        }else if(position%6==3){
            circle_iv.setImageResource(R.drawable.l);
        }else if(position%6==4){
            circle_iv.setImageResource(R.drawable.zi);
        }else {
            circle_iv.setImageResource(R.drawable.cen);

        }
        ImageView game_note_more_iv = commonHolder.game_note_more_iv;
        game_note_more_iv.setTag(position);
        game_note_more_iv.setOnClickListener(this);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.game_note_recycler_item;
    }

    public static class MyHolder extends CommonHolder {
        ImageView circle_iv;
        TextView game_rule_type_name_tv;
        TextView bet_amount_tv;
        TextView winning_amount_tv;
        ImageView game_note_more_iv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            circle_iv=itemView.findViewById(R.id.circle_iv);
            game_rule_type_name_tv=itemView.findViewById(R.id.game_rule_type_name_tv);
            bet_amount_tv=itemView.findViewById(R.id.bet_amount_tv);
            winning_amount_tv=itemView.findViewById(R.id.winning_amount_tv);
            game_note_more_iv=itemView.findViewById(R.id.game_note_more_iv);
        }
    }
}
