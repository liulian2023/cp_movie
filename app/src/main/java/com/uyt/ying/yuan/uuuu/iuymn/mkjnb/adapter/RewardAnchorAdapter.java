package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RewardAnchorEntity;
import com.uyt.ying.yuan.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class RewardAnchorAdapter extends BaseQuickAdapter<RewardAnchorEntity,BaseViewHolder> {

    public RewardAnchorAdapter(int layoutResId, @Nullable List<RewardAnchorEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, RewardAnchorEntity rewardAnchorEntity) {
        LinearLayout reward_anchor_wrap_linear =  baseViewHolder.getView(R.id.reward_anchor_wrap_linear);
        TextView reward_anchor_price_tv =  baseViewHolder.getView(R.id.reward_anchor_price_tv);
        reward_anchor_price_tv.setText(rewardAnchorEntity.getPrice());
        boolean selector = rewardAnchorEntity.isSelector();
        if (selector){
            reward_anchor_wrap_linear.setBackgroundResource(R.drawable.gift_selecte);
            reward_anchor_price_tv.setTextColor(Color.WHITE);
        }else {
            reward_anchor_wrap_linear.setBackgroundResource(R.drawable.reward_anchor_item_un_selecte);
            reward_anchor_price_tv.setTextColor(ContextCompat.getColor(MyApplication.getInstance(),R.color.default_color));
        }
    }
}
