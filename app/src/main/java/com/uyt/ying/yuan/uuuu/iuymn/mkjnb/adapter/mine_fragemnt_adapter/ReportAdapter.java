package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ReportEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class ReportAdapter extends CommonAdapter<ReportAdapter.MyHolder, ReportEntity.GameReportBean> {
    Fragment fragment;

    public ReportAdapter(ArrayList<ReportEntity.GameReportBean> list, Fragment fragment) {
        super(list);
        this.fragment = fragment;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        ReportEntity.GameReportBean itemModel = getItemModel(position);
        //图片
        Glide.with(fragment)
                .load(Utils.checkImageUrl(itemModel.getImage()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(commonHolder.report_title_iv);
        commonHolder.report_typename_tv.setText(itemModel.getName2());//typename
        commonHolder.bet_count_tv.setText(itemModel.getTzNum()+"");//下注注量
        commonHolder.bet_amount_tv.setText(itemModel.getTzPrice());//投注金额
        TextView winnig_amount_tv = commonHolder.winning_amount_tv;
        winnig_amount_tv.setText(itemModel.getProfitAndLoss());//中奖金额
        if(Double.parseDouble(itemModel.getProfitAndLoss())>0){
            winnig_amount_tv.setTextColor(Color.parseColor("#F75240"));
        }else {
            winnig_amount_tv.setTextColor(Color.parseColor("#13B42D"));
        }
        ConstraintLayout report_item_constraintLayout = commonHolder.report_item_constraintLayout;
        report_item_constraintLayout.setTag(position);
        report_item_constraintLayout.setOnClickListener(this);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.game_report_recycler_item;
    }

    public static class MyHolder extends CommonHolder {
            ImageView report_title_iv;
            TextView report_typename_tv;
            ImageView report_more_iv;
            TextView bet_count_tv;
            TextView bet_amount_tv;
            TextView winning_amount_tv;
            ConstraintLayout report_item_constraintLayout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            report_title_iv=itemView.findViewById(R.id.report_title_iv);
            report_typename_tv=itemView.findViewById(R.id.report_typename_tv);
            report_more_iv=itemView.findViewById(R.id.report_more_iv);
            bet_count_tv=itemView.findViewById(R.id.bet_count_tv);
            bet_amount_tv=itemView.findViewById(R.id.bet_amount_tv);
            winning_amount_tv =itemView.findViewById(R.id.winnig_amount_tv);
            report_item_constraintLayout=itemView.findViewById(R.id.report_item_constraintLayout);
        }
    }
}
