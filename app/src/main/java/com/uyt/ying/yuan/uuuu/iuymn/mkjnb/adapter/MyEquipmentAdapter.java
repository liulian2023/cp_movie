package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.EquipmentAllEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.EquipmentGetEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyEquipmentAdapter extends BaseDelegateMultiAdapter<Object, BaseViewHolder> {
    public static int TYPE_MOUNT_MEDAL=1;//坐骑 勋章 viewType
    public static int TYPE_TITLE_BRAND=2;//称号牌viewType
    public static int TYPE_SPLIT=3;//是否获得的分割viewType
    public MyEquipmentAdapter(@Nullable List<Object> data) {
        super(data);
        setMultiTypeDelegate(new BaseMultiTypeDelegate<Object>() {
            @Override
            public int getItemType(@NotNull List<?> list, int i) {
                Object obj = list.get(i);
                if(obj instanceof EquipmentAllEntity.MemberMedalListBean){
                    EquipmentAllEntity.MemberMedalListBean memberMedalListBean = (EquipmentAllEntity.MemberMedalListBean) obj;
                    String medalInfoType = memberMedalListBean.getMedalInfoType();
                    if(medalInfoType.equals("0")||medalInfoType.equals("1")){
                        //坐骑 勋章布局
                        return  TYPE_MOUNT_MEDAL;
                    }else {
                        //称号牌布局
                        return TYPE_TITLE_BRAND;
                    }
                }else {
                    //已获得未获得布局
                    return TYPE_SPLIT;
                }
            }
        });
        getMultiTypeDelegate()
                .addItemType(TYPE_MOUNT_MEDAL, R.layout.mount_medal_item_layout)
                .addItemType(TYPE_TITLE_BRAND,R.layout.title_brand_item_layout)
                .addItemType(TYPE_SPLIT,R.layout.equipment_split_item_layout);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
       if(o instanceof  EquipmentAllEntity.MemberMedalListBean){

           EquipmentAllEntity.MemberMedalListBean memberMedalListBean = (EquipmentAllEntity.MemberMedalListBean) o;
           EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfo = memberMedalListBean.getMedalInfo();
           if(medalInfo == null){
               return;
           }
           String medalInfoType = memberMedalListBean.getMedalInfoType();
           String useDays = medalInfo.getUseDays();
           if(medalInfoType.equals("0")||medalInfoType.equals("1")){
               /**
                * 坐骑 勋章布局
                */
               ImageView imageView =  baseViewHolder.getView(R.id.mount_iv);
               ImageView play_iv =  baseViewHolder.getView(R.id.play_iv);
               LinearLayout end_date_linear = baseViewHolder.getView(R.id.end_date_linear);
               TextView end_date_tv = baseViewHolder.getView(R.id.end_date_tv);
               TextView mount_remark_tv = baseViewHolder.getView(R.id.mount_remark_tv);
               TextView time_tv = baseViewHolder.getView(R.id.time_tv);
               baseViewHolder.setText(R.id.mount_name_tv,medalInfo.getName());
               GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(medalInfo.getImage()),imageView);
//               initRemarkAndTime(memberMedalListBean, medalInfo, end_date_linear, end_date_tv, mount_remark_tv, time_tv);
               String txImage = medalInfo.getTxImage();
                if(StringMyUtil.isNotEmpty(txImage)){
                    play_iv.setVisibility(View.VISIBLE);
                }else {
                    play_iv.setVisibility(View.GONE);

                }
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
               long endDate = memberMedalListBean.getEndDate();
               //customType  1未获取 2已使用 3 未使用
               int customType = medalInfo.getCustomType();
               if(customType==1){
                   //未获得 隐藏有效时间 显示图片右边的天数 显示获取条件
                   end_date_linear.setVisibility(View.GONE);
                   time_tv.setVisibility(View.VISIBLE);
                   mount_remark_tv.setVisibility(View.VISIBLE);
                   if(useDays.equals("0")){
                       time_tv.setText(Utils.getString(R.string.x永久));
                   }else {
                       time_tv.setText(String.format(Utils.getString(R.string.x%s天), useDays));
                   }
                   mount_remark_tv.setText(medalInfo.getConditions());
                   baseViewHolder.setText(R.id.mount_get_btn,Utils.getString(R.string.获取));

               }else if(customType == 2){
                   //2已使用 显示有效时间 隐藏图片右边的天数 隐藏获取条件
                   baseViewHolder.setText(R.id.mount_get_btn,Utils.getString(R.string.取消));
                   end_date_linear.setVisibility(View.VISIBLE);
                   mount_remark_tv.setVisibility(View.INVISIBLE);
                   Date dateAfter = DateUtil.getDateAfter(new Date(), 90);
                   if(useDays.equals("0")&&dateAfter.getTime()<endDate){
                       end_date_tv.setText(Utils.getString(R.string.永久));
                   }else {
                       String format = simpleDateFormat.format(new Date(endDate));
                       end_date_tv.setText(format);
                   }
 /*                  if(endDate==0){
                       end_date_tv.setText(Utils.getString(R.string.永久));
                   }else {
                       String format = simpleDateFormat.format(new Date(endDate));
                       end_date_tv.setText(format);
                   }*/
                   time_tv.setVisibility(View.GONE);

               }else {
                   //未使用 显示有效时间 隐藏图片右边的天数 隐藏获取条件 (时间有限)
                   baseViewHolder.setText(R.id.mount_get_btn,Utils.getString(R.string.穿戴));
                   end_date_linear.setVisibility(View.VISIBLE);
                   mount_remark_tv.setVisibility(View.INVISIBLE);

                   Date dateAfter = DateUtil.getDateAfter(new Date(), 90);
                   if(useDays.equals("0")&&dateAfter.getTime()<endDate){
                       end_date_tv.setText(Utils.getString(R.string.永久));
                   }else {
                       String format = simpleDateFormat.format(new Date(endDate));
                       end_date_tv.setText(format);
                   }
/*                   if(endDate==0){

                   }else {
                       String format = simpleDateFormat.format(new Date(endDate));
                       end_date_tv.setText(format);
                   }*/
                   time_tv.setVisibility(View.GONE);
               }
           }else {
               /**
                * 称号牌布局
                */
               LinearLayout end_date_linear = baseViewHolder.getView(R.id.end_date_linear);
               TextView end_date_tv = baseViewHolder.getView(R.id.end_date_tv);
               TextView title_brand_remark_tv = baseViewHolder.getView(R.id.title_brand_remark_tv);
               TextView time_tv = baseViewHolder.getView(R.id.time_tv);
               ImageView imageView =  baseViewHolder.getView(R.id.title_brand_iv);
               GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(medalInfo.getImage()),imageView);
               long endDate = memberMedalListBean.getEndDate();
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
               //customType  1未获取 2已使用 3 未使用
               int customType = medalInfo.getCustomType();
               if(customType==1){
                   //未获得 隐藏有效时间 显示图片右边的天数 显示获取条件
                   end_date_linear.setVisibility(View.GONE);
                   time_tv.setVisibility(View.VISIBLE);
                   title_brand_remark_tv.setVisibility(View.VISIBLE);
                   if(useDays.equals("0")){
                       time_tv.setText(Utils.getString(R.string.永久));
                   }else {
                       time_tv.setText(String.format(Utils.getString(R.string.x%s天), useDays));
                   }
                   title_brand_remark_tv.setText(medalInfo.getConditions());
                   baseViewHolder.setText(R.id.title_get_btn,Utils.getString(R.string.获取));

               }else if(customType == 2){
                   //已使用 显示有效时间 隐藏图片右边的天数 隐藏获取条件 (时间永久)
                   end_date_linear.setVisibility(View.VISIBLE);
                   title_brand_remark_tv.setVisibility(View.INVISIBLE);

                   Date dateAfter = DateUtil.getDateAfter(new Date(), 90);
                   if(useDays.equals("0")&&dateAfter.getTime()<endDate){
                       end_date_tv.setText(Utils.getString(R.string.永久));
                   }else {
                       String format = simpleDateFormat.format(new Date(endDate));
                       end_date_tv.setText(format);
                   }
                   time_tv.setVisibility(View.GONE);
                   baseViewHolder.setText(R.id.title_get_btn,Utils.getString(R.string.取消));

               }else {
                   //未使用 显示有效时间 隐藏图片右边的天数 隐藏获取条件 (时间有限)
                   end_date_linear.setVisibility(View.VISIBLE);
                   title_brand_remark_tv.setVisibility(View.INVISIBLE);
                   Date dateAfter = DateUtil.getDateAfter(new Date(), 90);
                   if(useDays.equals("0")&&dateAfter.getTime()<endDate){
                       end_date_tv.setText(Utils.getString(R.string.永久));
                   }else {
                       String format = simpleDateFormat.format(new Date(endDate));
                       end_date_tv.setText(format);
                   }
                   time_tv.setVisibility(View.GONE);
                   baseViewHolder.setText(R.id.title_get_btn,Utils.getString(R.string.穿戴));
               }
           }
       }else {
           /**
            * 是否已获得装备 布局
            */
           EquipmentGetEntity equipmentGetEntity = (EquipmentGetEntity) o;
           TextView is_get_tv = baseViewHolder.getView(R.id.is_get_tv);
           if(equipmentGetEntity.isGet()){
               is_get_tv.setText(Utils.getString(R.string.已获得));
           }else {
               is_get_tv.setText(Utils.getString(R.string.未获得));
           }
       }
    }

    private void initRemarkAndTime(EquipmentAllEntity.MemberMedalListBean memberMedalListBean, EquipmentAllEntity.MemberMedalListBean.MedalInfoBean medalInfo, LinearLayout end_date_linear, TextView end_date_tv, TextView title_brand_remark_tv, TextView time_tv) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long endDate = memberMedalListBean.getEndDate();
        if (StringMyUtil.isEmptyString(endDate)) {
            //未获得 隐藏有效时间 显示图片右边的天数 显示获取条件
            end_date_linear.setVisibility(View.GONE);
            time_tv.setVisibility(View.VISIBLE);
            title_brand_remark_tv.setVisibility(View.VISIBLE);
            time_tv.setText(String.format(Utils.getString(R.string.x%s天), medalInfo.getUseDays()));
            title_brand_remark_tv.setText(medalInfo.getConditions());
        } else if (endDate==0) {
            //已获得 显示有效时间 隐藏图片右边的天数 隐藏获取条件 (时间永久)
            end_date_linear.setVisibility(View.VISIBLE);
            title_brand_remark_tv.setVisibility(View.INVISIBLE);
            end_date_tv.setText(Utils.getString(R.string.永久));
            time_tv.setVisibility(View.GONE);
        } else {
            //已获得 显示有效时间 隐藏图片右边的天数 隐藏获取条件 (时间有限)
            end_date_linear.setVisibility(View.VISIBLE);
            title_brand_remark_tv.setVisibility(View.INVISIBLE);
            String format = simpleDateFormat.format(new Date(endDate));
            end_date_tv.setText(format);
            time_tv.setVisibility(View.GONE);
        }
    }
}
