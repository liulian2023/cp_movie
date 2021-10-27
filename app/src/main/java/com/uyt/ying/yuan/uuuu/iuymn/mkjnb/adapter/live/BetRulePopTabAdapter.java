package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetPopAllModel;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class BetRulePopTabAdapter extends BaseQuickAdapter<BetPopAllModel.BetPopTabModel, BaseViewHolder> {

    public BetRulePopTabAdapter(int layoutResId, @Nullable List<BetPopAllModel.BetPopTabModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BetPopAllModel.BetPopTabModel item) {
        helper.setText(R.id.tv_item_betrulepop_tab,item.getName());

        if (item.isSelect()){
            helper.setBackgroundColor(R.id.tv_item_betrulepop_tab, ContextCompat.getColor(getContext(),R.color.betpop_back_check));
        }else {
            helper.setBackgroundColor(R.id.tv_item_betrulepop_tab,ContextCompat.getColor(getContext(),R.color.betpop_back_uncheck));
        }
    }
}
