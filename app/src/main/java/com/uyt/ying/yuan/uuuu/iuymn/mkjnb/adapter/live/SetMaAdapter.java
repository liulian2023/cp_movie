package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BetJoinAllModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class SetMaAdapter extends BaseQuickAdapter<BetJoinAllModel.BetJoinMaModel, BaseViewHolder> {

    public SetMaAdapter(int layoutResId, @Nullable List<BetJoinAllModel.BetJoinMaModel> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, BetJoinAllModel.BetJoinMaModel item) {
        ImageView iv = helper.getView(R.id.iv_item_sema);
        iv.setBackground(null);
        TextView tv = helper.getView(R.id.tv_item_setma_num);
        ImageView bg_iv = helper.getView(R.id.choose_chip_background_iv);
        tv.setText("");
        LinearLayout cos_layout = helper.getView(R.id.col_item_setma_parent);
        cos_layout.setBackground(null);

        helper.setText(R.id.tv_item_setma_tag,"");
        if (item.isSelect()){
            bg_iv.setVisibility(View.VISIBLE);
        }else {
            bg_iv.setVisibility(View.INVISIBLE);
        }
        //最后一个 判断是否为0 为0 自定义（白色）  不为0  【修改筹码】（橘色）
        if (helper.getLayoutPosition()==getItemCount()-1){
            if (item.getDanjia()==0){
                iv.setImageResource(R.drawable.chouma_10);
                tv.setTextSize(10);
                tv.setText(Utils.getString(R.string.自定义));
                tv.setVisibility(View.GONE);
            }else {
                if((item.getDanjia()+"").length()==4){
                    tv.setTextSize(10);
                }else {
                    tv.setTextSize(14);
                }
                tv.setText(item.getDanjia()+"");
                helper.setTextColor(R.id.tv_item_setma_tag,ContextCompat.getColor(getContext(),R.color.darkorange));
                tv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.drawable.chouma10);
            }
            if (item.isCurrent()){
                helper.setText(R.id.tv_item_setma_tag, Utils.getString(R.string.当前筹码));
                helper.setTextColor(R.id.tv_item_setma_tag,ContextCompat.getColor(getContext(),R.color.white));
            }
        }else {
            tv.setVisibility(View.GONE);
            if (item.isCurrent()){
                helper.setText(R.id.tv_item_setma_tag,Utils.getString(R.string.当前筹码));
                helper.setTextColor(R.id.tv_item_setma_tag, ContextCompat.getColor(getContext(),R.color.white));
            }

            tv.setText(item.getDanjia()+"");
            switch (helper.getLayoutPosition()){
                case 0:
                    iv.setImageResource(R.drawable.chouma1);
                    break;
                case 1:
                    iv.setImageResource(R.drawable.chouma2);
                    break;
                case 2:
                    iv.setImageResource(R.drawable.chouma3);
                    break;
                case 3:
                    iv.setImageResource(R.drawable.chouma4);
                    break;
                case 4:
                    iv.setImageResource(R.drawable.chouma5);
                    break;
                case 5:
                    iv.setImageResource(R.drawable.chouma6);
                    break;
                case 6:
                    iv.setImageResource(R.drawable.chouma7);
                    break;
                case 7:
                    iv.setImageResource(R.drawable.chouma8);
                    break;
                case 8:
                    iv.setImageResource(R.drawable.chouma9);
                    break;
            }


        }


     }


}
