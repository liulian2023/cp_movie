package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_top_adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.QuotaChangeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.QuotaModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class QuotaChangeAdapter extends CommonAdapter<QuotaChangeAdapter.MyHolder, QuotaModel> {
    QuotaChangeActivity quotaChangeActivity;
    public QuotaChangeAdapter(ArrayList<QuotaModel> list, QuotaChangeActivity quotaChangeActivity) {
        super(list);
        this.quotaChangeActivity = quotaChangeActivity;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        QuotaModel itemModel = getItemModel(position);
        commonHolder.quota_name_tv.setText(itemModel.getName());
        String amount = itemModel.getAmount();
        commonHolder.quota_amount_tv.setText(amount);
        TextView quota_out_tv = commonHolder.quota_out_tv;
        TextView quota_in_tv = commonHolder.quota_in_tv;
        ImageView quota_refresh_iv = commonHolder.quota_refresh_iv;
        GlideLoadViewUtil.LoadNormalView(quotaChangeActivity, Utils.checkImageUrl(itemModel.getImageUrl()),commonHolder.quota_iv);
//        commonHolder.quota_iv.setImageResource(itemModel.getImageUrl());
        quota_out_tv.setTag(position);
        quota_in_tv.setTag(position);
        quota_refresh_iv.setTag(position);
        //未开启自动转换时可点击
        if(itemModel.getAutoConvert()==0){
            if(Double.parseDouble(amount)>0){
                quota_out_tv.setClickable(true);
                quota_out_tv.setBackgroundResource(R.drawable.quota_in_out_can_click_selector);
                quota_out_tv.setOnClickListener(QuotaChangeAdapter.this);
            }else {
                quota_out_tv.setClickable(false);
                quota_out_tv.setBackgroundResource(R.drawable.quota_in_out_can_not_click_selector);
            }
            if(Double.parseDouble(quotaChangeActivity.big_amount_tv.getText().toString())>0){
                quota_in_tv.setClickable(true);
                quota_in_tv.setBackgroundResource(R.drawable.quota_in_out_can_click_selector);
                quota_in_tv.setOnClickListener(QuotaChangeAdapter.this);
            }else {
                quota_in_tv.setClickable(false);
                quota_in_tv.setBackgroundResource(R.drawable.quota_in_out_can_not_click_selector);
            }
        }else {
            //开启自动转入时禁用点击
            quota_out_tv.setClickable(false);
            quota_in_tv.setClickable(false);
            quota_out_tv.setBackgroundResource(R.drawable.quota_in_out_can_not_click_selector);
            quota_in_tv.setBackgroundResource(R.drawable.quota_in_out_can_not_click_selector);
        }
        quota_refresh_iv.setOnClickListener(QuotaChangeAdapter.this);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.quota_change_recycler_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView quota_name_tv;
        TextView quota_amount_tv;
        TextView quota_in_tv;
        TextView quota_out_tv;
        ImageView quota_refresh_iv;
        ImageView quota_iv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            quota_name_tv=itemView.findViewById(R.id.quota_name_tv);
            quota_amount_tv=itemView.findViewById(R.id.quota_amount_tv);
            quota_in_tv=itemView.findViewById(R.id.quota_in_tv);
            quota_out_tv=itemView.findViewById(R.id.quota_out_tv);
            quota_refresh_iv=itemView.findViewById(R.id.quota_refresh_iv);
            quota_iv=itemView.findViewById(R.id.quota_iv);

        }
    }
}
