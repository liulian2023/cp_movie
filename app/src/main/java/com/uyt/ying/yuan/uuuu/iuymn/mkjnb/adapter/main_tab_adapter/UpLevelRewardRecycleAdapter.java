
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UpLevelRewardModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class UpLevelRewardRecycleAdapter extends CommonAdapter<UpLevelRewardRecycleAdapter.MyHolder, UpLevelRewardModel> {
    public UpLevelRewardRecycleAdapter(ArrayList<UpLevelRewardModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        UpLevelRewardModel itemModel = getItemModel(position);
        if(position==0){
            commonHolder.linearLayout.setBackgroundColor(Color.parseColor("#F2F7FF"));
        }else {
            commonHolder.linearLayout.setBackgroundColor(Color.WHITE);
        }
        commonHolder.levelTv.setText(itemModel.getLevel());
        commonHolder.chengZhangTv.setText(itemModel.getChengzhang());
        commonHolder.jinJiTv.setText(itemModel.getJinji());
        commonHolder.tiaoJiTv.setText(itemModel.getTiaoji());
    }

    @Override
    public int getLayOutRes() {
        return R.layout.reward_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView levelTv;
        private TextView chengZhangTv;
        private TextView jinJiTv;
        private TextView tiaoJiTv;
        private LinearLayout linearLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            levelTv=itemView.findViewById(R.id.level);
            chengZhangTv=itemView.findViewById(R.id.jifen);
            jinJiTv=itemView.findViewById(R.id.jiangli);
            tiaoJiTv=itemView.findViewById(R.id.tiaoji);
            linearLayout=itemView.findViewById(R.id.wrap_linear);
        }
    }
}
