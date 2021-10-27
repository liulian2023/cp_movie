package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MineRecyclerAdapter extends BaseQuickAdapter<MineInfoEntity, BaseViewHolder> {
    public MineRecyclerAdapter(int layoutResId, @Nullable List<MineInfoEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MineInfoEntity mineInfoEntity) {
        ImageView type_iv = baseViewHolder.getView(R.id.type_iv);
        TextView typename_tv = baseViewHolder.getView(R.id.typename_tv);
        MyTextView un_read_tip_tv = baseViewHolder.getView(R.id.un_read_tip_tv);
        un_read_tip_tv.setVisibility(View.GONE);
        if(mineInfoEntity.getRemark().endsWith(Utils.getString(R.string.我的消息))){
            if(mineInfoEntity.getMessageNum()!=0l){
                un_read_tip_tv.setText(mineInfoEntity.getMessageNum()+"");
                un_read_tip_tv.setVisibility(View.VISIBLE);
            }else{
                un_read_tip_tv.setVisibility(View.GONE);
            }
        }
        type_iv.setImageResource(mineInfoEntity.getImageId());
        typename_tv.setText(mineInfoEntity.getRemark());
    }
}
