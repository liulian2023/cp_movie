package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DiamondChildEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiamondChildAdapter extends BaseQuickAdapter<DiamondChildEntity, BaseViewHolder> {
    public DiamondChildAdapter(int layoutResId, @Nullable List<DiamondChildEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, DiamondChildEntity childEntity) {
        ImageView diamond_big_bg_iv = baseViewHolder.getView(R.id.diamond_big_bg_iv);
        ImageView diamond_iv = baseViewHolder.getView(R.id.diamond_iv);
        ImageView is_get_iv = baseViewHolder.getView(R.id.is_get_iv);
        TextView diamont_count_tv = baseViewHolder.getView(R.id.diamont_count_tv);
        TextView diamond_name_tv = baseViewHolder.getView(R.id.diamond_name_tv);
        GlideLoadViewUtil.LoadCornersViewRes(getContext(),R.drawable.zb_bj,6,diamond_big_bg_iv);
        boolean get = childEntity.isGet();
        if(get){
            is_get_iv.setVisibility(View.VISIBLE);
        }else {
            is_get_iv.setVisibility(View.GONE);
        }
        diamond_name_tv.setText(childEntity.getName());
        diamont_count_tv.setText(childEntity.getCountContent());
        String url = childEntity.getUrl();
        if(StringMyUtil.isEmptyString(childEntity.getUrl())){
            diamond_iv.setImageResource(R.drawable.chouma2);
        }else {
            GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(url),diamond_iv);
        }
    }
}
