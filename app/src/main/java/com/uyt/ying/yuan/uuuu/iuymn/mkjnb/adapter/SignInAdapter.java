package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.DiamondChildEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SignInEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SignInAdapter extends BaseQuickAdapter<SignInEntity.GainZhuanShiConditionsListBean, BaseViewHolder> {
    public SignInAdapter(int layoutResId, @Nullable List<SignInEntity.GainZhuanShiConditionsListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SignInEntity .GainZhuanShiConditionsListBean gainZhuanShiConditionsListBean) {
        ImageView sign_in_iv = baseViewHolder.getView(R.id.sign_in_iv);
        TextView sign_in_name_tv = baseViewHolder.getView(R.id.sign_in_name_tv);
        TextView sign_in_date_tv = baseViewHolder.getView(R.id.sign_in_date_tv);
        ConstraintLayout sign_in_bg_constraintLayout = baseViewHolder.getView(R.id.sign_in_bg_constraintLayout);
        String userGainSuccess = gainZhuanShiConditionsListBean.getUserGainSuccess();//用户是否获取(0否;1是)

        boolean isGet;
        if(userGainSuccess.equals("0")){
            sign_in_bg_constraintLayout.setVisibility(View.GONE);
            isGet=false;
        }else {
            sign_in_bg_constraintLayout.setVisibility(View.VISIBLE);
            isGet=true;
        }


        ArrayList<DiamondChildEntity> childList = new ArrayList<>();
        String gainZhuanShi = gainZhuanShiConditionsListBean.getGainZhuanShi();
        SignInEntity.GainZhuanShiConditionsListBean.GainZhuanShiByGiftBean gainZhuanShiByGift = gainZhuanShiConditionsListBean.getGainZhuanShiByGift();
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

        gainZhuanShiConditionsListBean.setChildList(childList);

        if(childList.size()==1){
            DiamondChildEntity diamondChildEntity = childList.get(0);
            sign_in_name_tv.setText(diamondChildEntity.getName()+diamondChildEntity.getCountContent());
            String url = diamondChildEntity.getUrl();
            if(StringMyUtil.isNotEmpty(url)){
                GlideLoadViewUtil.LoadNormalView(getContext(), Utils.checkImageUrl(url),sign_in_iv);
            }else {
                sign_in_iv.setImageResource(R.drawable.zs);
            }
        }else {
            sign_in_name_tv.setText(Utils.getString(R.string.豪华宝箱一个));
            sign_in_iv.setImageResource(R.drawable.bx);
        }
        String price = gainZhuanShiConditionsListBean.getPrice();
        if(price.equals("2")){
            sign_in_date_tv.setText(Utils.getString(R.string.第一天));
        }else if(price.equals("3")){
            sign_in_date_tv.setText(Utils.getString(R.string.第二天));
        }else if(price.equals("4")){
            sign_in_date_tv.setText(Utils.getString(R.string.第三天));
        }else if(price.equals("5")){
            sign_in_date_tv.setText(Utils.getString(R.string.第四天));
        }else if(price.equals("6")){
            sign_in_date_tv.setText(Utils.getString(R.string.第五天));
        }else if(price.equals("7")){
            sign_in_date_tv.setText(Utils.getString(R.string.第六天));
        }else {
            sign_in_date_tv.setText(Utils.getString(R.string.第七天));
        }
    }
}
