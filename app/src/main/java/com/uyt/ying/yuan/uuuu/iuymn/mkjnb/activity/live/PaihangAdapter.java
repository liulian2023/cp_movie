package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.PaihangEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils.CPImageUrlCheck;

public class PaihangAdapter extends BaseQuickAdapter<PaihangEntity.BonusRecordListBean, BaseViewHolder> {

    public PaihangAdapter(int layoutResId, @Nullable List<PaihangEntity.BonusRecordListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PaihangEntity.BonusRecordListBean item) {
        helper.setText(R.id.tv_nicheng, item.getNickname())
                .setText(R.id.tv_shouyi, "¥" + item.getYesterdayProfit());

        ImageView iv = helper.getView(R.id.iv_paihang_avatar);
        GlideLoadViewUtil.LoadCircleView(getContext(), CPImageUrlCheck(getContext(), item.getImage()), iv);
        TextView tv = helper.getView(R.id.tv_rank);
        tv.setText("");
        tv.setBackground(null);
        if (item.getRank()==0){
            tv.setBackgroundResource(R.drawable.num_one);
        }else if (item.getRank()==1){
            tv.setBackgroundResource(R.drawable.num_two);
        }else if (item.getRank()==2){
            tv.setBackgroundResource(R.drawable.num_three);
        }else {
            tv.setText("" + (item.getRank() + 1));
        }


    }
}
