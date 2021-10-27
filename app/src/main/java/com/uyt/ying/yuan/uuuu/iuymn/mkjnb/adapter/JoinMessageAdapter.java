package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyImageSpan;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.UrlImageSpan;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class JoinMessageAdapter extends BaseQuickAdapter<LiveMessageModel, BaseViewHolder> {
    Fragment fragment;
    public JoinMessageAdapter(int layoutResId, @Nullable List<LiveMessageModel> data,Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LiveMessageModel liveMessageModel) {
            String userName = liveMessageModel.getUserName();
            String textContent = liveMessageModel.getTextContent();
            int level = Integer.parseInt(StringMyUtil.isEmptyString(liveMessageModel.getLevel()) ? "1" : liveMessageModel.getLevel());
            String zkCode = liveMessageModel.getZkCode();
            String managerType= liveMessageModel.getManagerType();
            String levelIcon = liveMessageModel.getLevelIcon();
            String medalIcon = liveMessageModel.getMedalIcon();
            String titleIcon = liveMessageModel.getTitleIcon();
            TextView  exitJoinCotentTv = baseViewHolder.getView(R.id.exit_join_content_tv);
            /**
             * 消息内容colorSpan
             */
            ForegroundColorSpan defaultColorSpan = new ForegroundColorSpan(Color.WHITE);
            /**
             * 用户名colorSpan
             */
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FFCB14"));

            SpannableStringBuilder joinSpanBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, exitJoinCotentTv);
            String s ;
            if(liveMessageModel.getStatus().equals("0")){
                s=Utils.getString(R.string.进入直播间);
            }else {
                s=Utils.getString(R.string.退出直播间);
            }
            joinSpanBuilder.append(liveMessageModel.getUserName(),foregroundColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            joinSpanBuilder.append(s,defaultColorSpan,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                SpannableString exitSpan = new SpannableString("    "+liveMessageModel.getUserName() + s);
//                initLevelIcon(level, exitSpan,userName,managerType,true);
            exitJoinCotentTv.setTextColor(Color.WHITE);
            exitJoinCotentTv.setText(joinSpanBuilder);


    }

    @NotNull
    private SpannableStringBuilder initLevelEquipmentIcon(String managerType, String levelIcon, String medalIcon, String titleIcon, TextView textNameContentTv) {
        managerType = StringMyUtil.isEmptyString(managerType)?"":managerType;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int drawType = R.drawable.saixuan1;
        if(managerType.equals("1")){
            //主播没有等级icon
            drawType=R.drawable.jinyan_zb_icon;
        }else if(managerType.equals("2")){
//            drawType=R.drawable.chaoguang;//超管取消
        }else if(managerType.equals("3")){
            drawType=R.drawable.fangguang;
        }else {
            //普通用户没有身份icon
        }
        if(drawType!=R.drawable.saixuan1){
            //添加身份icon
            Bitmap bitmapType = BitmapFactory.decodeResource(fragment.getResources(), drawType);
            MyImageSpan typeSpan = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(bitmapType, 35, 15));
            spannableStringBuilder.append(" ",typeSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if(!managerType.equals("1")){
            //不是主播, 添加等级icon 勋章icon medalIcon
            if(StringMyUtil.isNotEmpty(levelIcon)){
                /**
                 * 等级icon
                 */
                UrlImageSpan levelUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(levelIcon), textNameContentTv,35,15,R.drawable.touming);
                spannableStringBuilder.append(" ", levelUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(" ");
            }
            if(StringMyUtil.isNotEmpty(medalIcon)){
                /**
                 * 勋章icon
                 */
                UrlImageSpan medalUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(medalIcon), textNameContentTv,15,15,R.drawable.touming2);
                spannableStringBuilder.append(" ", medalUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(" ");
            }

            if(StringMyUtil.isNotEmpty(titleIcon)){
                /**
                 * 称号牌icon
                 */
                List<String> titleIconList = Arrays.asList(titleIcon.split(","));
                for (int i = 0; i < titleIconList.size(); i++) {
                    UrlImageSpan titleUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(titleIconList.get(i)), textNameContentTv,35,15,R.drawable.touming);
                    spannableStringBuilder.append(" ", titleUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//称号牌icon
                    spannableStringBuilder.append(" ");
                }
            }
        }


        return spannableStringBuilder;
    }
}
