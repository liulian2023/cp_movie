package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.yuebao_adapter;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.YueBaoMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class YueBaoAdapter extends CommonAdapter<YueBaoAdapter.MyHolder, YueBaoMedol> {
    public YueBaoAdapter(ArrayList<YueBaoMedol> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        YueBaoMedol itemModel = getItemModel(position);
        String time = itemModel.getTime();
        Date date = new Date(Long.parseLong(time));
        String amountType = itemModel.getAmountType();
        //amountType为1或3为入账
        if(amountType.equals("1")||amountType.equals("3")){
            commonHolder.amountTv.setText("+"+itemModel.getAmount());
            commonHolder.amountTv.setTextColor(Color.RED);
        }else {
            //2 或 4 为出账
            commonHolder.amountTv.setText("-"+itemModel.getAmount());
            commonHolder.amountTv.setTextColor(Color.parseColor("#8b8b8b"));
        }
        commonHolder.remarkTv.setText(itemModel.getRemark());
        commonHolder.timeTv.setText(format.format(date));

    }

    @Override
    public int getLayOutRes() {
        return R.layout.child_deal_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView remarkTv;
        private TextView timeTv;
        private TextView amountTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            remarkTv=itemView.findViewById(R.id.nikename);
            amountTv=itemView.findViewById(R.id.amount);
            timeTv=itemView.findViewById(R.id.time);
        }
    }
}
