package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChooseAreaEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChooseAreaAdapter extends BaseQuickAdapter<ChooseAreaEntity,BaseViewHolder> {
    public ChooseAreaAdapter(int layoutResId, @Nullable List<ChooseAreaEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ChooseAreaEntity chooseAreaEntity) {
        RadioButton choose_area_recycler_name_rbtn = baseViewHolder.getView(R.id.choose_area_recycler_name_rbtn);
        choose_area_recycler_name_rbtn.setText(chooseAreaEntity.getArea());
        if(chooseAreaEntity.getStatus()==1) {
            choose_area_recycler_name_rbtn.setChecked(true);
        }else{
            choose_area_recycler_name_rbtn.setChecked(false);
        }

    }
}
