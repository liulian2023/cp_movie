package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TurntableResultModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TurntableResultAdapter extends BaseQuickAdapter<TurntableResultModel, BaseViewHolder>{
    public TurntableResultAdapter(int layoutResId, @Nullable List<TurntableResultModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, TurntableResultModel turntableResultModel) {
        ImageView turntable_result_iv = baseViewHolder.getView(R.id.turntable_result_iv);
        TextView turntable_result_remark_tv = baseViewHolder.getView(R.id.turntable_result_remark_tv);
        turntable_result_iv.setImageResource(turntableResultModel.getDrawableId());
        turntable_result_remark_tv.setText(turntableResultModel.getRemark().replace(" ",""));
    }
}
