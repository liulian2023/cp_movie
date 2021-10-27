package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.GameTypeEnum;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryListItem;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class CusLotteryListRecyAdapter extends BaseQuickAdapter<LotteryListItem, BaseViewHolder> {

    public CusLotteryListRecyAdapter(int layoutResId, @Nullable List<LotteryListItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LotteryListItem item) {
        LinearLayout llIslive = helper.getView(R.id.ll_lotterylist_recyitem_islive);
        TextView tv_name = helper.getView(R.id.tv_lotterylist_recyitem_name);
        ImageView iv_xian = helper.getView(R.id.iv_lotterylist_recyitem_xian);
        ImageView iv_name = helper.getView(R.id.iv_lotterylist_recyitem_name);
        tv_name.setText(item.getName());
        if (item.getGame()== GameTypeEnum.LIVESHOP.getValue()){
//            iv_name.setImageResource(R.drawable.all_lottery_icon);
            GlideLoadViewUtil.LoadNormalView(getContext(),Utils.getHomeLogo("liveIcon8"),iv_name);
        }else {
            GlideLoadViewUtil.LoadNormalView(getContext(), Utils.CPImageUrlCheck(getContext(),item.getImgUrl()),iv_name);
        }

        if (item.isLive()){
            llIslive.setVisibility(View.VISIBLE);
            tv_name.setVisibility(View.GONE);
        }else {
            llIslive.setVisibility(View.GONE);
            tv_name.setVisibility(View.VISIBLE);
        }
        if (item.isXian()){
            iv_xian.setVisibility(View.VISIBLE);
        }else {
            iv_xian.setVisibility(View.GONE);
        }
    }
}
