package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class RankAdapter extends BaseQuickAdapter<RankEntity.RankingListBean, BaseViewHolder> {
    Fragment fragment;

    public RankAdapter(int layoutResId, @Nullable List<RankEntity.RankingListBean> data, Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankEntity.RankingListBean recordsBean) {
       ImageView rank_jinbi_iv= baseViewHolder.getView(R.id.rank_jinbi_iv);
       ImageView rank_level_iv= baseViewHolder.getView(R.id.rank_level_iv);
       ImageView rank_title_iv= baseViewHolder.getView(R.id.rank_title_iv);
       TextView rank_jinbi_tv= baseViewHolder.getView(R.id.rank_jinbi_tv);
       TextView changci_tv= baseViewHolder.getView(R.id.changci_tv);
       TextView win_rate_tv= baseViewHolder.getView(R.id.win_rate_tv);
       GifImageView onlive_gif_iv= baseViewHolder.getView(R.id.onlive_gif_iv);
       TextView onlive_tip_tv = baseViewHolder.getView(R.id.onlive_tip_tv);
        int adapterPosition = baseViewHolder.getAdapterPosition();
//        1 明星榜  2贡献榜  3富豪榜 4赌神榜 5邀请棒  6 专享榜
        int viewType = recordsBean.getViewType();
        switch (viewType){
            case 1:
               baseViewHolder.setText(R.id.rank_num_tv,(adapterPosition+3)+"");//排名
               GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkLiveImageUrl(recordsBean.getImage()),rank_title_iv);//明星榜用直播的图片域名
               baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getNickname());//昵称
               rank_level_iv.setVisibility(View.GONE);//隐藏等级icon(后期可能会加上直播中icon)
               changci_tv.setVisibility(View.GONE);//隐藏场次tv
               win_rate_tv.setVisibility(View.GONE);//隐藏胜率tv
               rank_jinbi_iv.setVisibility(View.VISIBLE);//显示礼物icon
               rank_jinbi_tv.setVisibility(View.VISIBLE);//显示礼物tv
               rank_jinbi_iv.setImageResource(R.drawable.liwu_icon);//设置礼物icon
               rank_jinbi_tv.setText(recordsBean.getRedPrice());//设置收礼金额
                if(recordsBean.getStatus().equals("1")){
                    onlive_gif_iv.setVisibility(View.VISIBLE);//直播中
                    onlive_tip_tv.setVisibility(View.VISIBLE);
                }else {
                    onlive_tip_tv.setVisibility(View.GONE);
                    onlive_gif_iv.setVisibility(View.GONE);//已下播
                }
                break;
            case 2:
                baseViewHolder.setText(R.id.rank_num_tv,(adapterPosition+3)+"");//排名
                GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkImageUrl(recordsBean.getImage()),rank_title_iv);//贡献榜用cp的图片域名
                baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getUserNickName());//昵称
                rank_level_iv.setVisibility(View.VISIBLE);//显示等级icon
//                rank_level_iv.setImageResource(Utils.getLevelDrawble(Integer.parseInt(recordsBean.getPointGrade())+1));//设置等级icon
                GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(Integer.parseInt(recordsBean.getPointGrade())+1)),rank_level_iv);
                changci_tv.setVisibility(View.GONE);//隐藏场次tv
                win_rate_tv.setVisibility(View.GONE);//隐藏胜率tv
                rank_jinbi_iv.setVisibility(View.VISIBLE);//显示送礼icon
                rank_jinbi_tv.setVisibility(View.VISIBLE);//显示送礼tv
                onlive_gif_iv.setVisibility(View.GONE);//隐藏直播中gif
                onlive_tip_tv.setVisibility(View.GONE);//隐藏直播中提示文字
                rank_jinbi_iv.setImageResource(R.drawable.xin_icon);//设置礼物icon
                rank_jinbi_tv.setText(recordsBean.getAnchorGift());//设置收礼金额
                break;
            case 3:
                baseViewHolder.setText(R.id.rank_num_tv,(adapterPosition+3)+"");//排名
                GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkImageUrl(recordsBean.getImage()),rank_title_iv);//富豪榜用cp的图片域名
                    baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getNickname());//昵称
                rank_level_iv.setVisibility(View.VISIBLE);//显示等级icon

//                rank_level_iv.setImageResource(Utils.getLevelDrawble(recordsBean.getGrade()+1));//设置等级icon

                GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(recordsBean.getGrade()+1)),rank_level_iv);
                changci_tv.setVisibility(View.GONE);//隐藏场次tv
                win_rate_tv.setVisibility(View.GONE);//隐藏胜率tv
                rank_jinbi_iv.setVisibility(View.VISIBLE);//显示中奖icon
                rank_jinbi_tv.setVisibility(View.VISIBLE);//显示中奖tv
                onlive_gif_iv.setVisibility(View.GONE);//隐藏直播中gif
                onlive_tip_tv.setVisibility(View.GONE);//隐藏直播中提示文字
                rank_jinbi_iv.setImageResource(R.drawable.jinbi_icon);//设置中奖icon
//                rank_jinbi_tv.setText(recordsBean.getZjPrice());//设置中奖金额
                rank_jinbi_tv.setText("****");//设置中奖金额
                break;
            case 4:
                baseViewHolder.setText(R.id.rank_num_tv,(adapterPosition+3)+"");//排名
                GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkImageUrl(recordsBean.getImage()),rank_title_iv);//赌神榜用cp的图片域名
                baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getNickname());//昵称
                rank_level_iv.setVisibility(View.VISIBLE);//显示等级icon
//                rank_level_iv.setImageResource(Utils.getLevelDrawble(recordsBean.getGrade()+1));//设置等级icon
                GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(recordsBean.getGrade()+1)),rank_level_iv);

                changci_tv.setVisibility(View.VISIBLE);//显示场次tv
                win_rate_tv.setVisibility(View.VISIBLE);//显示胜率tv
                rank_jinbi_iv.setVisibility(View.GONE);//隐藏中奖icon
                rank_jinbi_tv.setVisibility(View.GONE);//隐藏中奖tv
                onlive_gif_iv.setVisibility(View.GONE);//隐藏直播中gif
                onlive_tip_tv.setVisibility(View.GONE);//隐藏直播中提示文字
                int zjNum = recordsBean.getZjNum();
                int tzNum = recordsBean.getTzNum();
                if(recordsBean.getNickname().equals(Utils.getString(R.string.等待上榜))){
                    changci_tv.setText(Utils.getString(R.string.场次: 0中0));//设置中奖场次
                }else {
                    changci_tv.setText(Utils.getString(R.string.场次: )+ tzNum +Utils.getString(R.string.中)+ zjNum);//设置中奖场次
                }
                if(zjNum==0){
                    win_rate_tv.setText(Utils.getString(R.string.胜率： 0.00%));
                }else {
                    win_rate_tv.setText(Utils.getString(R.string.胜率冒号)+new BigDecimal(Double.parseDouble(zjNum+"")/Double.parseDouble(tzNum+"")*100).setScale(2,BigDecimal.ROUND_HALF_DOWN)+"%");//设置中奖比例
                }
                break;
            case 5:
                baseViewHolder.setText(R.id.rank_num_tv,(adapterPosition+3)+"");//排名
                GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkImageUrl(recordsBean.getImage()),rank_title_iv);//邀请榜用cp的图片域名
                baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getNickname());//昵称
                rank_level_iv.setVisibility(View.VISIBLE);//显示等级icon
//                rank_level_iv.setImageResource(Utils.getLevelDrawble(recordsBean.getGrade()+1));//设置等级icon
                GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(recordsBean.getGrade()+1)),rank_level_iv);

                changci_tv.setVisibility(View.GONE);//隐藏场次tv
                win_rate_tv.setVisibility(View.GONE);//隐藏胜率tv
                rank_jinbi_iv.setVisibility(View.VISIBLE);//显示中奖icon
                rank_jinbi_tv.setVisibility(View.VISIBLE);//显示中奖tv
                onlive_gif_iv.setVisibility(View.GONE);//隐藏直播中gif
                onlive_tip_tv.setVisibility(View.GONE);//隐藏直播中提示文字
                rank_jinbi_iv.setImageResource(R.drawable.hb_qw);//设置邀请红包icon
                rank_jinbi_tv.setText(recordsBean.getTotalQuYueHBPrice());//设置邀请红包金额
                break;
            case 6:
                baseViewHolder.setText(R.id.rank_num_tv,(adapterPosition+3)+"");//排名
                GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkImageUrl(recordsBean.getImage()),rank_title_iv);//专享榜用cp的图片域名
                baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getNickname());//昵称
                rank_level_iv.setVisibility(View.VISIBLE);//显示等级icon
//                rank_level_iv.setImageResource(Utils.getLevelDrawble(recordsBean.getGrade()+1));//设置等级icon

                GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(recordsBean.getGrade()+1)),rank_level_iv);
                changci_tv.setVisibility(View.GONE);//隐藏场次tv
                win_rate_tv.setVisibility(View.GONE);//隐藏胜率tv
                rank_jinbi_iv.setVisibility(View.VISIBLE);//显示中奖icon
                rank_jinbi_tv.setVisibility(View.VISIBLE);//显示中奖tv
                onlive_gif_iv.setVisibility(View.GONE);//隐藏直播中gif
                onlive_tip_tv.setVisibility(View.GONE);//隐藏直播中提示文字
                rank_jinbi_iv.setImageResource(R.drawable.hb_qw);//设置专享红包icon
                rank_jinbi_tv.setText(recordsBean.getTotalZxHBPrice());//设置专享红包金额
                break;
                default:
                    break;

        }

/*        baseViewHolder.setText(R.id.rank_num_tv,(itemPosition+4)+"");
        baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getNickName());
        baseViewHolder.setText(R.id.rank_gift_amount_tv,recordsBean.getTotalAmount());
        ImageView rank_title_iv = baseViewHolder.getView(R.id.rank_title_iv);
        GlideLoadViewUtil.FLoadTitleView(fragment,recordsBean.getImage(),rank_title_iv);*/
/*        if(recordsBean.getSex()==0){
            baseViewHolder.setImageResource(R.id.rank_sex_iv,R.drawable.xingbie_nan);
        }else {
            baseViewHolder.setImageResource(R.id.rank_sex_iv,R.drawable.xingbie_nv);

        }*/
    }

}



