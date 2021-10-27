package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.user_info_adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserLevel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class UserLevelRecycleAdapter extends BaseQuickAdapter<UserLevel, BaseViewHolder> {


    public UserLevelRecycleAdapter(int layoutResId, @Nullable List<UserLevel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserLevel item) {
        ImageView level_iv = helper.getView(R.id.level_iv);
        TextView levev_vip = helper.getView(R.id.levev_vip);
        LinearLayout level_linear = helper.getView(R.id.level_linear);
        if(getItemPosition(item)==0){
            level_linear.setVisibility(View.GONE);
            levev_vip.setVisibility(View.VISIBLE);
            helper.setText(R.id.levev_vip,"vip"+item.getVip());
        }else {
            level_linear.setVisibility(View.VISIBLE);
            levev_vip.setVisibility(View.GONE);
//            level_iv.setImageResource(Utils.getLevelDrawble(Integer.parseInt(item.getVip())));
            GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(Integer.parseInt(item.getVip()))),level_iv);
        }
        helper.setText(R.id.level_level,item.getLevel());
        helper.setText(R.id.level_integeal,item.getIntegral());

    }


}
