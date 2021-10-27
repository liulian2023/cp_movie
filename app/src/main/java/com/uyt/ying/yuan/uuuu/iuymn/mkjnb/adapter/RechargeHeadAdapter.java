package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RechargeModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class RechargeHeadAdapter extends BaseQuickAdapter<RechargeModel.BankAllListBean,BaseViewHolder> {
    public RechargeHeadAdapter(int layoutResId, @Nullable List<RechargeModel.BankAllListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, RechargeModel.BankAllListBean bankAllListBean) {
      ConstraintLayout recharge_head_wrap_constraintLayout = baseViewHolder.getView(R.id.recharge_head_wrap_constraintLayout);
      ImageView is_check_iv = baseViewHolder.getView(R.id.is_check_iv);
      ImageView bank_icon_iv = baseViewHolder.getView(R.id.bank_icon_iv);
        TextView bank_name_tv = baseViewHolder.getView(R.id.bank_name_tv);
        if(StringMyUtil.isNotEmpty(bankAllListBean.getImage())){
            GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(bankAllListBean.getImage()),bank_icon_iv);
        }else {
            bank_icon_iv.setImageResource(R.drawable.rengongcz_icon);
        }
        String name = bankAllListBean.getName();
        if(name.length()>5){
            bank_name_tv.setTextSize(11);
        }else {
            bank_name_tv.setTextSize(12);
        }
        bank_name_tv.setText(name);
        boolean check = bankAllListBean.isSelector();
        if(check){
            recharge_head_wrap_constraintLayout.setBackgroundResource(R.drawable.recharge_head_item_check_shape);
            is_check_iv.setVisibility(View.VISIBLE);
        }else {
            recharge_head_wrap_constraintLayout.setBackgroundResource(R.drawable.recharge_head_item_un_check_shape);
            is_check_iv.setVisibility(View.GONE);
        }
        // 设置背景颜色
        TextView recharge_head_tip_tv = baseViewHolder.getView(R.id.recharge_head_tip_tv);
        String label = bankAllListBean.getLabel();
        if(StringMyUtil.isNotEmpty(label)){
            GradientDrawable background = (GradientDrawable) recharge_head_tip_tv.getBackground();
            recharge_head_tip_tv.setVisibility(View.VISIBLE);
            recharge_head_tip_tv.setText(label);
            if(label.equals(Utils.getString(R.string.奖))){
                background.setColor(Color.parseColor("#B546EB"));
            }else if(label.equals(Utils.getString(R.string.火))){
                background.setColor(Color.parseColor("#F62226"));
            }else if(label.equals(Utils.getString(R.string.热))){
                background.setColor(Color.parseColor("#F26D21"));
            }else if(label.equals(Utils.getString(R.string.优))){
                background.setColor(Color.parseColor("#F6B059"));
            }else if(label.equals(Utils.getString(R.string.安))){
                background.setColor(Color.parseColor("#4BACF1"));
            }else if(label.equals(Utils.getString(R.string.极))){
                background.setColor(Color.parseColor("#14D123"));
            }else {
                recharge_head_tip_tv.setVisibility(View.GONE);
            }
        }else {
            recharge_head_tip_tv.setVisibility(View.GONE);
        }

    }
}
