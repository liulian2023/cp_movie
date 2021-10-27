package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.GiftNumEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class GiftNumAdapter extends BaseQuickAdapter<GiftNumEntity.GiftNumListBean, BaseViewHolder> {

    public GiftNumAdapter(int layoutResId, @Nullable List<GiftNumEntity.GiftNumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftNumEntity.GiftNumListBean item) {
        TextView tv_num = helper.getView(R.id.tv_item_giftnum);
        TextView tv_des = helper.getView(R.id.tv_item_giftdes);
        LinearLayout ll_item_giftnum_parent = helper.getView(R.id.ll_item_giftnum_parent);
        tv_des.setText(item.getDes());

        if (item.isIsSelect()){
            ll_item_giftnum_parent.setSelected(true);
        }else {
            ll_item_giftnum_parent.setSelected(false);
        }

        if (helper.getLayoutPosition()==0){
            tv_num.setVisibility(View.GONE);
            tv_num.setText("");
            tv_des.setGravity(Gravity.CENTER);
        }else {
            tv_num.setVisibility(View.VISIBLE);
            tv_num.setText(item.getNum()+"");
        }

    }
}
