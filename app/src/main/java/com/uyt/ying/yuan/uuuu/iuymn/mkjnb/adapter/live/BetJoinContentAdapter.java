package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinContentModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class BetJoinContentAdapter extends BaseQuickAdapter<BetJoinContentModel, BaseViewHolder> {



    public BetJoinContentAdapter(int layoutResId, @Nullable List<BetJoinContentModel> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, BetJoinContentModel item) {
        helper.setText(R.id.tv_item_betjoin_name,item.getName())
                .setText(R.id.tv_item_betjoin_type,item.getType())
                .setText(R.id.tv_item_betjoin_times,""+item.getBeishu())
                .setText(R.id.tv_item_tag,""+item.getDanjia()+"x"+item.getBeishu());


    }
}
