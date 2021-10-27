package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveRankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class LiveRankRecyclerAdapter extends BaseQuickAdapter<LiveRankEntity.ListBean, BaseViewHolder> {
    Fragment fragment;
    public LiveRankRecyclerAdapter(int layoutResId, @Nullable List<LiveRankEntity.ListBean> data,Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRankEntity.ListBean item) {
        int adapterPosition = helper.getAdapterPosition();
        TextView rank_num_tv= helper.getView(R.id.rank_num_tv);
        ImageView title_iv= helper.getView(R.id.title_iv);
        ImageView grade_iv= helper.getView(R.id.grade_iv);
        if(adapterPosition==0){
            rank_num_tv.setBackgroundResource(R.drawable.rank_1);
            rank_num_tv.setText("");
        }else if(adapterPosition==1){
            rank_num_tv.setBackgroundResource(R.drawable.rank_2);
            rank_num_tv.setText("");
        }else if(adapterPosition==2){
            rank_num_tv.setBackgroundResource(R.drawable.rank_3);
            rank_num_tv.setText("");
        }else {
            rank_num_tv.setText(adapterPosition+1+"");
            rank_num_tv.setBackground(null);
        }
        GlideLoadViewUtil.FLoadTitleView(fragment, Utils.checkImageUrl(item.getImage()),title_iv);
        helper.setText(R.id.nick_name_tv,item.getUserNickName());
        helper.setText(R.id.gift_amount_tv,item.getAnchorGift());
        GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(Integer.parseInt(item.getPointGrade())+1)),grade_iv);
//        grade_iv.setImageResource(Utils.getLevelDrawble(Integer.parseInt(item.getPointGrade())+1));

    }

}
