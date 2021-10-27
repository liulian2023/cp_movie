package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HotCenterEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.uyt.ying.yuan.R;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class LiveCenterListAdapter extends BaseDelegateMultiAdapter<Object, BaseViewHolder> {
    Fragment fragment;
    Activity activity;
    public static int LIVE_BEAN_TYPE=1;
    public static int CLASSIFICATION_BEAN_TYPE=2;
    public LiveCenterListAdapter(Fragment fragment) {
        this.fragment = fragment;
        setViewType();
    }


    public LiveCenterListAdapter(Activity activity) {
        this.activity = activity;
        setViewType();
    }

    private void setViewType() {
        setMultiTypeDelegate(new BaseMultiTypeDelegate<Object>() {
            @Override
            public int getItemType(@NotNull List<?> list, int i) {
                Object o = list.get(i);
                if(o instanceof LiveEntity.AnchorMemberRoomListBean ){
                    return LIVE_BEAN_TYPE;
                }else if(o instanceof HotCenterEntity) {
                    return  CLASSIFICATION_BEAN_TYPE;
                }else {
                    return LIVE_BEAN_TYPE;
                }
            }
        });
        /**
         * 原需求需要用到多布局, 后面该需求了. 只有一种布局
         */
        getMultiTypeDelegate()
                .addItemType(LIVE_BEAN_TYPE,R.layout.live_center_list_recycle_item)
                .addItemType(CLASSIFICATION_BEAN_TYPE,R.layout.hot_live_center_type_one_item);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
        if(o instanceof LiveEntity.AnchorMemberRoomListBean ){
            LiveEntity.AnchorMemberRoomListBean itemModel = (LiveEntity.AnchorMemberRoomListBean) o;
            LinearLayout live_cover_linear = baseViewHolder.getView(R.id.live_cover_linear);
            ImageView imageView = baseViewHolder.getView(R.id.live_iv);
            TextView lotteryNameTv = baseViewHolder.getView(R.id.live_lottery_name_tv);
            TextView live_nickname_tv = baseViewHolder.getView(R.id.live_nickname_tv);
            TextView liveNameTv = baseViewHolder.getView(R.id.live_title_tv);
            TextView live_watch_num_tv = baseViewHolder.getView(R.id.live_watch_num_tv);
            ImageView is_toll_iv = baseViewHolder.getView(R.id.is_toll_iv);
            TextView area_tv = baseViewHolder.getView(R.id.area_tv);
            ImageView use_toy_iv = baseViewHolder.getView(R.id.use_toy_iv);
            if(itemModel.getIsUseToy()==1){
                use_toy_iv.setVisibility(View.VISIBLE);
            }else {
                use_toy_iv.setVisibility(View.GONE);
            }
            liveNameTv.setText(itemModel.getTitle());
            lotteryNameTv.setText(itemModel.getLotteryName());
            live_nickname_tv.setText(itemModel.getAnchorNickName());

            if(activity==null){
                if(itemModel.getIsPrivate()==0){
                    GlideLoadViewUtil.FLoadCornersView(fragment, Utils.checkImageUrl(itemModel.getCover()),7, imageView);
                }else {
                    GlideLoadViewUtil.FLoadCornersView(fragment, Utils.checkLiveImageUrl(itemModel.getCover()),7, imageView);
                }
            }else {
                if(itemModel.getIsPrivate()==0){
                    GlideLoadViewUtil.LoadCornersView(activity, Utils.checkImageUrl(itemModel.getCover()),7, imageView);
                }else {
                    GlideLoadViewUtil.LoadCornersView(activity, Utils.checkLiveImageUrl(itemModel.getCover()),7, imageView);
                }
            }
            String anchorSubscribe = StringMyUtil.isEmptyString(itemModel.getAnchorSubscribe())?"0":itemModel.getAnchorSubscribe();
            if(new BigDecimal(anchorSubscribe).compareTo(BigDecimal.ZERO)>0){
                is_toll_iv.setVisibility(View.VISIBLE);
            }else {
                is_toll_iv.setVisibility(View.GONE);
            }
            int totalPeople = itemModel.getBasePeopleNum();
            String unit ="";
            float total = totalPeople *1.0f;
            String title = "";
            if(totalPeople > 10000){
                unit = Utils.getString(R.string.万);
                total = total/10000.f;
                title = String.format("%.1f%s",total,unit);
            }else {
                title = String.format("%d",totalPeople);
            }

            live_watch_num_tv.setText(title);
            String area = itemModel.getArea();
            if(StringUtils.isEmpty(area)){
                area_tv.setVisibility(View.GONE);
            }else {
                area_tv.setVisibility(View.VISIBLE);
                if(area.contains("-")){
                    String[] split = area.split("-");
                    area_tv.setText(split[1]);
                }else {
                    area_tv.setText(area);
                }
            }
            lotteryNameTv.setVisibility(View.INVISIBLE); //彩种不显示
            String game = itemModel.getGame();
            if(game.equals("1")){
                lotteryNameTv.setBackgroundResource(R.drawable.yfks_bg);
            }else if(game.equals("2")){
                lotteryNameTv.setBackgroundResource(R.drawable.ssc_bg);
            }else if(game.equals("3")){
                lotteryNameTv.setBackgroundResource(R.drawable.yfsc_bg);
            }else if(game.equals("4")){
                lotteryNameTv.setBackgroundResource(R.drawable.lhc_bg);
            }else if(game.equals("5")){
                lotteryNameTv.setBackgroundResource(R.drawable.rddd_bg);
            }else if(game.equals("6")){
                lotteryNameTv.setBackgroundResource(R.drawable.yfsc_bg);
            }else if(game.equals("7")){
                lotteryNameTv.setBackgroundResource(R.drawable.ssc_bg);
            }else if(game.equals("8")){
                lotteryNameTv.setBackgroundResource(R.drawable.js28_bg);
            }else if(game.equals("9")){
                lotteryNameTv.setBackgroundResource(R.drawable.xuanwu_num_5);
            }
        }else if(o instanceof HotCenterEntity){
            HotCenterEntity hotCenterEntity = (HotCenterEntity) o;
            baseViewHolder.setText(R.id.hot_center_tv,hotCenterEntity.getName());

        }


    }

}
