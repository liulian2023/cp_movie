package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ChanelEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ScreenUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils.dp2px;

public class ChannelChildAdapter extends BaseQuickAdapter<ChanelEntity.DataBean.MoviesBean, BaseViewHolder>
{

    public ChannelChildAdapter(int layoutResId, @Nullable List<ChanelEntity.DataBean.MoviesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChanelEntity.DataBean.MoviesBean item) {
        ImageView iv = helper.getView(R.id.live_iv);
        iv.getLayoutParams().width = getWidth();
        iv.getLayoutParams().height = getWidth();
        GlideLoadViewUtil.LoadNormalView(getContext(), Utils.ImageUrlCheck(item.getMoviePhoto()),iv);
        helper.setText(R.id.live_title_tv,item.getMovieName());

        /**
         * 根据CpId 筛选 cpName
         */
        String navigateList = SharePreferencesUtil.getString(getContext(), "navigateList", "");
        Gson gson = new GsonBuilder().serializeNulls().create();
        NavigateEntity navigateEntity = gson.fromJson(navigateList, NavigateEntity.class);
        for (int i = 0; i < navigateEntity.getGameClassList().size(); i++) {
            if (item.getCpId() == navigateEntity.getGameClassList().get(i).getId()) {
                String cpName = navigateEntity.getGameClassList().get(i).getTypename();
                helper.setText(R.id.live_lottery_name_tv,cpName);
            }
        }
    }

    private int getWidth(){
        int mScreenWidth = ScreenUtils.getWight(getContext());
        return (mScreenWidth-dp2px(10)-ScreenUtils.dip2px(getContext(), 30f))/2;
    }
}
