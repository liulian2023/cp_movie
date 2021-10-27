package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.widget.RadioButton;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MultipleModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MultipleAdapter extends BaseQuickAdapter<MultipleModel, BaseViewHolder> {
    public MultipleAdapter(int layoutResId, @Nullable List<MultipleModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MultipleModel multipleModel) {
            RadioButton multiple_tv= baseViewHolder.getView(R.id.multiple_tv);
            multiple_tv.setText(multipleModel.getContent()+Utils.getString(R.string.倍));
            if(multipleModel.isCheck()){
                multiple_tv.setChecked(true);
            }else {
                multiple_tv.setChecked(false);
            }
    }
}
