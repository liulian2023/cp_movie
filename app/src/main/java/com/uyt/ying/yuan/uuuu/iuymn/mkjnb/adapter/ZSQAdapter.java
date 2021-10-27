package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ZSQEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ZSQAdapter extends BaseQuickAdapter<ZSQEntity, BaseViewHolder> {


    public ZSQAdapter(int layoutResId, @Nullable List<ZSQEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ZSQEntity zsqEntity) {
       TextView zsq_tv =  baseViewHolder.getView(R.id.zsq_tv);
        int status = zsqEntity.getStatus();
        if(status==0){
            zsq_tv.setBackgroundResource(R.drawable.seleter_uncheck);
        }else {
            zsq_tv.setBackgroundResource(R.drawable.seleter_check);
        }
    }
}
