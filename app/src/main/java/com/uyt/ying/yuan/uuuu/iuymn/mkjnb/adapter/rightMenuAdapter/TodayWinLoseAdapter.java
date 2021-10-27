package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TodayWinLoseModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class TodayWinLoseAdapter extends CommonAdapter<TodayWinLoseAdapter.MyHolder, TodayWinLoseModel> {
    public TodayWinLoseAdapter() {
    }

    public TodayWinLoseAdapter(ArrayList<TodayWinLoseModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        TodayWinLoseModel itemModel = getItemModel(position);
        commonHolder.qishu.setText(itemModel.getQishu());
        commonHolder.betAmountTv.setText(itemModel.getBetAmount()+"");
        commonHolder.winAmountTv.setText(itemModel.getWinAmount()+"");
        commonHolder.winloseAmountTv.setText(itemModel.getWinLoseAmount()+"");
    }

    @Override
    public int getLayOutRes() {
        return R.layout.today_win_lose_recy_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView qishu;
        private TextView betAmountTv;
        private TextView winAmountTv;
        private TextView winloseAmountTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qihao);
            betAmountTv=itemView.findViewById(R.id.bet_amount);
            winAmountTv=itemView.findViewById(R.id.win_amount);
            winloseAmountTv=itemView.findViewById(R.id.win_lose_amount);
        }
    }
}
