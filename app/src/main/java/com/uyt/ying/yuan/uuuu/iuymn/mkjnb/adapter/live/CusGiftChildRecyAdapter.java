package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.GiftEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;



public class CusGiftChildRecyAdapter extends BaseQuickAdapter<GiftEntity.GiftListsBean, BaseViewHolder> {
    int isUesToy;
    public CusGiftChildRecyAdapter(int layoutResId, @Nullable List<GiftEntity.GiftListsBean> data,int isUesToy) {
        super(layoutResId, data);
        this.isUesToy = isUesToy;
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftEntity.GiftListsBean item) {
        ImageView iv = helper.getView(R.id.iv_gift_image);
        ConstraintLayout cons_gift = helper.getView(R.id.cons_gift);
        ImageView jinbi = helper.getView(R.id.jinbi);
        ImageView use_toy_iv = helper.getView(R.id.use_toy_iv);
        String gear = item.getGear();
        String gearTime = item.getGearTime();
        if(isUesToy==1&&StringMyUtil.isNotEmpty(gear)&&!gear.equals("0")&&StringMyUtil.isNotEmpty(gearTime)&&!gearTime.equals("0")){
            use_toy_iv.setVisibility(View.VISIBLE);
        }else {
            use_toy_iv.setVisibility(View.GONE);
        }
        if (item.getGiftTitle().equals(Utils.getString(R.string.发红包))){
            helper.setText(R.id.tv_gift_name,item.getGiftTitle())
                    .setText(R.id.tv_gift_price,Utils.getString(R.string.来一场红包雨));
            iv.setImageResource(R.drawable.gift_fhb);
            cons_gift.setBackgroundResource(R.drawable.gift_un_selecte);
            jinbi.setVisibility(View.GONE);
        }else {
            if(item.getGiftTitle().equals(Utils.getString(R.string.位置))){
                //占位置的最后一个item隐藏所有view
                iv.setVisibility(View.INVISIBLE);
                helper.getView(R.id.tv_gift_name).setVisibility(View.INVISIBLE);
                helper.getView(R.id.tv_gift_price).setVisibility(View.INVISIBLE);
                helper.getView(R.id.cons_gift).setVisibility(View.INVISIBLE);
                jinbi.setVisibility(View.INVISIBLE);
            }else {
                GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkLiveImageUrl(item.getGiftLogo()),iv);
                helper.setText(R.id.tv_gift_name,item.getGiftTitle())
                        .setText(R.id.tv_gift_price,""+item.getGiftAmount());
                if (item.isSecect()){
                    cons_gift.setBackgroundResource(R.drawable.gift_selecte);
                }else {
                    cons_gift.setBackgroundResource(R.drawable.gift_un_selecte);
                }
                jinbi.setVisibility(View.VISIBLE);
            }

        }
    }

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1f) {
            //恢复屏幕亮度时需要移除flag,否则在切换activity时会有短暂黑屏
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        context.getWindow().setAttributes(lp);
    }


}
