
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.YesTodayRankModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class YestodayRankAdapter extends CommonAdapter<YestodayRankAdapter.MyHolder, YesTodayRankModel> {
    public YestodayRankAdapter(ArrayList<YesTodayRankModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        YesTodayRankModel itemModel = getItemModel(position);
        commonHolder.rankMtv.setText(itemModel.getRank());
        commonHolder.rankMtv.setCornerSize(50);
        commonHolder.rankMtv.setfilColor(Color.parseColor("#fc7a4e"));
        commonHolder.nicknameTv.setText(itemModel.getNickname());
        commonHolder.amountTv.setText(itemModel.getAmount());
    }

    @Override
    public int getLayOutRes() {
            return R.layout.yestoday_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private MyCornerTextView rankMtv;
        private TextView nicknameTv;
        private TextView amountTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            rankMtv=itemView.findViewById(R.id.paiming);
            nicknameTv=itemView.findViewById(R.id.nickname);
            amountTv=itemView.findViewById(R.id.win_amount);
        }
    }
}
