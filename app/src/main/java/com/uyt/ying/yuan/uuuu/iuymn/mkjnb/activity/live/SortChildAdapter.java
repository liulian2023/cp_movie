package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.SortEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class SortChildAdapter extends BaseQuickAdapter<SortEntity.RankingListBean, BaseViewHolder> {
    int position;
    public SortChildAdapter(int layoutResId, @Nullable List<SortEntity.RankingListBean> data,int position) {
        super(layoutResId, data);
        this.position = position;
    }

    @Override
    protected void convert(BaseViewHolder helper, SortEntity.RankingListBean item) {
        TextView tv_range = helper.getView(R.id.tv_range);
        tv_range.setText("");
        tv_range.setBackground(null);
        switch (helper.getAdapterPosition()){
            case 0:
                tv_range.setBackgroundResource(R.drawable.ic_top1);
                break;
            case 1:
                tv_range.setBackgroundResource(R.drawable.ic_top2);
                break;
            case 2:
                tv_range.setBackgroundResource(R.drawable.ic_top3);
                break;
                default:
                    tv_range.setText((helper.getAdapterPosition()+1)+"");
        }

        ImageView iv_avatar = helper.getView(R.id.iv_avatar);
        String image = item.getImage();
        if(RankTypeEnum.valueOf(position+1)==RankTypeEnum.GIFT){
            if(!image.startsWith("http")){
                image = Utils.checkLiveImageUrl(image);
            }
        }else {
            image = Utils.checkImageUrl(image);
        }
        GlideLoadViewUtil.LoadCircleView(getContext(), Utils.CPImageUrlCheck(getContext(), image),iv_avatar);
        helper.setText(R.id.tv_money,""+item.getRedPrice());
        helper.setText(R.id.tv_name,item.getNickname());
    }
}
