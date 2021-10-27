package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class BetJoinMoneyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public BetJoinMoneyAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv = helper.getView(R.id.tv_item_betjoin_money);
        if(item.length()==4){
            tv.setTextSize(10);
        }else {
            tv.setTextSize(12);
        }
//        tv.setText(item);
        switch (helper.getLayoutPosition()){
            case 0:
                tv.setBackgroundResource(R.drawable.chouma3);
                break;
            case 1:
                tv.setBackgroundResource(R.drawable.chouma5);
                break;
            case 2:
                tv.setBackgroundResource(R.drawable.chouma6);
                break;
            case 3:
                tv.setBackgroundResource(R.drawable.chouma9);
                break;
        }

    }
}
