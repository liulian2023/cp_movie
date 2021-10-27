package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.liveActivityEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class ActivityIconAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    private boolean haveRedCountdown;

    public void setHaveRedCountdown(boolean haveRedCountdown) {
        this.haveRedCountdown = haveRedCountdown;
    }
    public ActivityIconAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        ImageView imageView = helper.getView(R.id.iv_item_activityicon);
        TextView tv_item_activityicon = helper.getView(R.id.tv_item_activityicon);
        TextView red_rain_countDown_tv = helper.getView(R.id.red_rain_countDown_tv);
        if(item instanceof  liveActivityEntity.LiveShowPosition2Bean){
            liveActivityEntity.LiveShowPosition2Bean liveShowPosition2Bean = (liveActivityEntity.LiveShowPosition2Bean) item;
            GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(liveShowPosition2Bean.getImage()),imageView);
            String theme = liveShowPosition2Bean.getTheme();
            int liveShowPage = liveShowPosition2Bean.getLiveShowPage();
            String timeStr = liveShowPosition2Bean.getTimeStr();
            handleItem(tv_item_activityicon, red_rain_countDown_tv, theme, timeStr, liveShowPage);
        }else if(item instanceof liveActivityEntity.LiveShowPosition3Bean){
            liveActivityEntity.LiveShowPosition3Bean liveShowPosition3Bean = (liveActivityEntity.LiveShowPosition3Bean) item;
            GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(liveShowPosition3Bean.getImage()),imageView);
            String theme = liveShowPosition3Bean.getTheme();
            int liveShowPage = liveShowPosition3Bean.getLiveShowPage();
            String timeStr = liveShowPosition3Bean.getTimeStr();
            handleItem(tv_item_activityicon, red_rain_countDown_tv, theme, timeStr, liveShowPage);
        }else {
            liveActivityEntity.LiveShowPosition4Bean liveShowPosition4Bean = (liveActivityEntity.LiveShowPosition4Bean) item;
            GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(liveShowPosition4Bean.getImage()),imageView);
            String theme = liveShowPosition4Bean.getTheme();
            String timeStr = liveShowPosition4Bean.getTimeStr();
            int liveShowPage = liveShowPosition4Bean.getLiveShowPage();
            handleItem(tv_item_activityicon, red_rain_countDown_tv, theme, timeStr, liveShowPage);
        }
    }

    private void handleItem(TextView tv_item_activityicon, TextView red_rain_countDown_tv, String theme, String timeStr, int liveShowPage) {
/*         if(StringMyUtil.isEmptyString(theme)){
            tv_item_activityicon.setVisibility(View.GONE);
        }else {
            tv_item_activityicon.setVisibility(View.VISIBLE);
        }*/
        tv_item_activityicon.setText(theme);
        if(liveShowPage == 4){
            red_rain_countDown_tv.setText(timeStr);
            red_rain_countDown_tv.setVisibility(View.VISIBLE);
        }else {
            if(haveRedCountdown){
                red_rain_countDown_tv.setVisibility(View.INVISIBLE);
            }else {
                red_rain_countDown_tv.setVisibility(View.GONE);
            }
        }
    }

}

