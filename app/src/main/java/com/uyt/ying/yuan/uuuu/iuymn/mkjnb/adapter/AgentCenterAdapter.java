package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AgentCenterEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class AgentCenterAdapter extends BaseQuickAdapter<AgentCenterEntity, BaseViewHolder> {
    public AgentCenterAdapter(int layoutResId, @Nullable List<AgentCenterEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AgentCenterEntity item) {
        helper.setText(R.id.num_tv,item.getNum());
        helper.setText(R.id.remark_tv,item.getRemark());
    }
}
