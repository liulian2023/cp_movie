package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.graphics.Color;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DiamondChildEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DiamondEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DiamondAdapter extends BaseQuickAdapter<DiamondEntity.GainZhuanShiConditionsListBean, BaseViewHolder> {

    public DiamondAdapter(int layoutResId, @Nullable List<DiamondEntity.GainZhuanShiConditionsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, DiamondEntity.GainZhuanShiConditionsListBean gainZhuanShiConditionsListBean) {
        TextView item_title_tv = baseViewHolder.getView(R.id.item_title_tv);
        TextView item_status_tv = baseViewHolder.getView(R.id.item_status_tv);
        RecyclerView diamond_child_recycler = baseViewHolder.getView(R.id.diamond_child_recycler);
        String type = gainZhuanShiConditionsListBean.getType();
        boolean isGet=false;
        if (type.equals("0")){
            item_title_tv.setText(String.format(Utils.getString(R.string.邀请人礼包),gainZhuanShiConditionsListBean.getPrice()));
        }else if(type.equals("1")){
            item_title_tv.setText(String.format(Utils.getString(R.string.每日充值元礼包),gainZhuanShiConditionsListBean.getPrice()));
        }else if(type.equals("2")){
            item_title_tv.setText(String.format(Utils.getString(R.string.每日打码元礼包),gainZhuanShiConditionsListBean.getPrice()));
        }else if(type.equals("3")){
            item_title_tv.setText(String.format(Utils.getString(R.string.每日送礼元礼包),gainZhuanShiConditionsListBean.getPrice()));
        }
        String userGainSuccess = gainZhuanShiConditionsListBean.getUserGainSuccess();//用户是否获取(0否;1是)
        if(userGainSuccess.equals("0")){
            item_status_tv.setText(Utils.getString(R.string.未达到));
            item_status_tv.setTextColor(Color.parseColor("#999999"));
            isGet=false;
        }else {
            item_status_tv.setText(Utils.getString(R.string.已获得));
            item_status_tv.setTextColor(Color.parseColor("#FA0A0A"));
            isGet=true;
        }


       // -------------------------------------------------------------------   child recyclerView start -----------------------
        ArrayList<DiamondChildEntity> childList = new ArrayList<>();
        String gainZhuanShi = gainZhuanShiConditionsListBean.getGainZhuanShi();

        DiamondEntity.GainZhuanShiConditionsListBean.GainZhuanShiByGiftBean gainZhuanShiByGift = gainZhuanShiConditionsListBean.getGainZhuanShiByGift();
        if(gainZhuanShiByGift!=null){
            String medalInfo0Id = gainZhuanShiByGift.getMedalInfo0Id();
            String medalInfo0Status = gainZhuanShiByGift.getMedalInfo0Status();
            if(StringMyUtil.isNotEmpty(medalInfo0Id)&& medalInfo0Status.equals("1")){
                /**
                 * 添加坐骑item
                 */

                String type0Days = gainZhuanShiByGift.getType0Days();//坐骑天数
                String medalInfo0Image = gainZhuanShiByGift.getMedalInfo0Image();
                String medalInfo0Name = gainZhuanShiByGift.getMedalInfo0Name();
                DiamondChildEntity diamondChildEntity = new DiamondChildEntity(medalInfo0Name, medalInfo0Image,  type0Days + Utils.getString(R.string.天),isGet);
                childList.add(diamondChildEntity);
            }
            String medalInfo1Id = gainZhuanShiByGift.getMedalInfo1Id();
            String medalInfo1Status = gainZhuanShiByGift.getMedalInfo1Status();
            if(StringMyUtil.isNotEmpty(medalInfo1Id)&&medalInfo1Status.equals("1")){
                /**
                 *    添加勋章item
                 */
                String type1Days = gainZhuanShiByGift.getType1Days();
                String medalInfo1Image = gainZhuanShiByGift.getMedalInfo1Image();
                String medalInfo1Name = gainZhuanShiByGift.getMedalInfo1Name();
                DiamondChildEntity diamondChildEntity = new DiamondChildEntity(medalInfo1Name, medalInfo1Image,  type1Days + Utils.getString(R.string.天),isGet);
                childList.add(diamondChildEntity);
            }
            String medalInfo2Id = gainZhuanShiByGift.getMedalInfo2Id();
            String medalInfo2Status = gainZhuanShiByGift.getMedalInfo2Status();
            if(StringMyUtil.isNotEmpty(medalInfo2Id)&&medalInfo2Status.equals("1")){
                /**
                 *    添加称号牌item
                 */
                String type2Days = gainZhuanShiByGift.getType2Days();
                String meda2Info1Image = gainZhuanShiByGift.getMedalInfo2Image();
                String meda2Info1Name = gainZhuanShiByGift.getMedalInfo2Name();
                DiamondChildEntity diamondChildEntity = new DiamondChildEntity(meda2Info1Name, meda2Info1Image,  type2Days + Utils.getString(R.string.天),isGet);
                childList.add(diamondChildEntity);
            }
        }
        if(StringMyUtil.isNotEmpty(gainZhuanShi)&&!gainZhuanShi.equals("0")){
            /**
             *     添加钻石item
             */

            DiamondChildEntity diamondChildEntity = new DiamondChildEntity(Utils.getString(R.string.钻石), "",  gainZhuanShi + Utils.getString(R.string.个),isGet);
            childList.add(diamondChildEntity);
        }
        DiamondChildAdapter diamondChildAdapter = new DiamondChildAdapter(R.layout.diamond_child_recycler_item, childList);
        diamond_child_recycler.setLayoutManager(new GridLayoutManager(getContext(),4));
        diamond_child_recycler.setAdapter(diamondChildAdapter);
        diamondChildAdapter.notifyDataSetChanged();

        // -------------------------------------------------------------------   child recyclerView end -----------------------
    }
}
