package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

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

public class SignInChildAdapter extends BaseQuickAdapter<DiamondChildEntity, BaseViewHolder> {
    public SignInChildAdapter(int layoutResId, @Nullable List<DiamondChildEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, DiamondChildEntity childEntity) {
        ImageView sign_in_iv = baseViewHolder.getView(R.id.sign_in_iv);
        TextView sign_in_name_tv = baseViewHolder.getView(R.id.sign_in_name_tv);
        TextView sign_in_date_tv = baseViewHolder.getView(R.id.sign_in_date_tv);
        sign_in_date_tv.setText(childEntity.getCountContent());
        sign_in_date_tv.setTextSize(10);
        sign_in_name_tv.setText(childEntity.getName());
        String url = childEntity.getUrl();
        if(StringMyUtil.isEmptyString(childEntity.getUrl())){
            sign_in_iv.setImageResource(R.drawable.zs);
        }else {
            GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(url),sign_in_iv);
        }
    }
}
