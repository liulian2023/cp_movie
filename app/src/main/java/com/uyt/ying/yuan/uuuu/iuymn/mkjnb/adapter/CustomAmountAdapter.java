package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CustomAmountEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomAmountAdapter extends BaseQuickAdapter<CustomAmountEntity, BaseViewHolder> {
    public CustomAmountAdapter(int layoutResId, @Nullable List<CustomAmountEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CustomAmountEntity customAmountEntity) {
        TextView custom_amount_tv = baseViewHolder.getView(R.id.custom_amount_tv);
        ImageView custom_amount_iv = baseViewHolder.getView(R.id.custom_amount_iv);
        int status = customAmountEntity.getStatus();
        if(status==1){
            custom_amount_tv.setBackgroundResource(R.drawable.recharge_head_item_check_shape);
            custom_amount_iv.setVisibility(View.VISIBLE);
        }else {
            custom_amount_tv.setBackgroundResource(R.drawable.recharge_head_item_un_check_shape);
            custom_amount_iv.setVisibility(View.GONE);
        }
        custom_amount_tv.setText(customAmountEntity.getAmount()+ Utils.getString(R.string.元));

    }
}
