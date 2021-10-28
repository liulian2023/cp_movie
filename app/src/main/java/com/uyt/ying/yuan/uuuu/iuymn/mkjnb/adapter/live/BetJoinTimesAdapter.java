package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinAllModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.List;

public class BetJoinTimesAdapter extends BaseQuickAdapter<BetJoinAllModel.BetJoinTimeModel, BaseViewHolder> {

    public BetJoinTimesAdapter(int layoutResId, @Nullable List<BetJoinAllModel.BetJoinTimeModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BetJoinAllModel.BetJoinTimeModel item) {
        helper.setText(R.id.tv_item_betjoin_times,item.getTimes()+ Utils.getString(R.string.倍));
        if (item.isSelect()){
            helper.getView(R.id.tv_item_betjoin_times).setSelected(true);
        }else {
            helper.getView(R.id.tv_item_betjoin_times).setSelected(false);
        }
    }
}
