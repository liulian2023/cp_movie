/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter;

import android.graphics.Color;
import androidx.annotation.NonNull;

import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineBetModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LongDragonBetNoteRecycleAdapter extends CommonAdapter<LongDragonBetNoteRecycleAdapter.MyHolder,MineBetModel> {
    public LongDragonBetNoteRecycleAdapter(ArrayList<MineBetModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        long l = Long.parseLong(list.get(position).getCreatedDate());
        String format = simpleDateFormat.format(l);
        commonHolder.typeName.setText(list.get(position).getTypeName());
        commonHolder.time.setText(format);
        commonHolder.qishu.setText(Utils.getString(R.string.第)+list.get(position).getLotteryqishu()+Utils.getString(R.string.期));
        if(list.get(position).getRemark().equals(Utils.getString(R.string.中奖))){
            commonHolder.remark.setText(Utils.getString(R.string.已中奖));
            commonHolder.amount.setText(list.get(position).getBonus().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//中奖金额
            commonHolder.remark.setTextColor(Color.RED);
            commonHolder.amount.setTextColor(Color.RED);

        }else if(list.get(position).getRemark().equals(Utils.getString(R.string.未中))){
            commonHolder.remark.setText(Utils.getString(R.string.未中奖));
            commonHolder.amount.setText(list.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
            commonHolder.remark.setTextColor(Color.parseColor("#868686"));
            commonHolder.amount.setTextColor(Color.parseColor("#868686"));

        }else if (list.get(position).getRemark().equals(Utils.getString(R.string.撤单))){
            commonHolder.remark.setText(Utils.getString(R.string.已撤单));
            commonHolder.amount.setText(list.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
            commonHolder.remark.setTextColor(Color.parseColor("#868686"));
            commonHolder.amount.setTextColor(Color.parseColor("#868686"));
        }
        else{
            commonHolder.remark.setText(Utils.getString(R.string.等待开奖));
            commonHolder.amount.setText(list.get(position).getAmount().setScale(2,BigDecimal.ROUND_DOWN)+Utils.getString(R.string.元));//投注金额
            commonHolder.remark.setTextColor(Color.parseColor("#868686"));
            commonHolder.amount.setTextColor(Color.parseColor("#868686"));
        }
        commonHolder.groundName.setText("["+list.get(position).getGroupname()+"]"+list.get(position).getRulename());
        commonHolder.itemView.setTag(position);
        commonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.mine_bet_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView typeName;
        TextView qishu;
        TextView time;
        TextView amount;
        TextView groundName;
        TextView remark;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            typeName=itemView.findViewById(R.id.mine_bet_typeName);
            qishu=itemView.findViewById(R.id.mine_bet_qishu);
            time=itemView.findViewById(R.id.mine_bet_time);
            amount=itemView.findViewById(R.id.mine_bet_amount);
            groundName=itemView.findViewById(R.id.mine_bet_groudName);
            remark=itemView.findViewById(R.id.mine_bet_remark);
        }
    }
}
