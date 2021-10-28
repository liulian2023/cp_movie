package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BlockEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ForbiddenEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//public class ForbiddenAdapter extends BaseQuickAdapter<ForbiddenEntity.ChatRoomGagListBean, BaseViewHolder> {
public class ForbiddenAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public ForbiddenAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
      TextView end_time_tv =  baseViewHolder.getView(R.id.end_time_tv);
      TextView recharge_marquee_view =  baseViewHolder.getView(R.id.recharge_marquee_view);
      TextView operating_tv =  baseViewHolder.getView(R.id.operating_tv);
        if(o instanceof ForbiddenEntity.ChatRoomGagListBean){
            //禁言
            ForbiddenEntity.ChatRoomGagListBean chatRoomGagListBean = (ForbiddenEntity.ChatRoomGagListBean) o;
            recharge_marquee_view.setText(chatRoomGagListBean.getUserNickName());
            end_time_tv.setVisibility(View.VISIBLE);
            end_time_tv.setText(DateUtil.formatTime(chatRoomGagListBean.getEndForbiddenDate()));
            operating_tv.setText(Utils.getString(R.string.解禁));
        }else {
            //拉黑
            end_time_tv.setVisibility(View.INVISIBLE);
            BlockEntity.MemberKillMemberListBean memberKillMemberListBean = (BlockEntity.MemberKillMemberListBean) o;
            String killedAnchorUserName = memberKillMemberListBean.getKilledAnchorUserNickName();
            if(StringMyUtil.isEmptyString(killedAnchorUserName)){
                //拉黑的是用户
                recharge_marquee_view.setText(memberKillMemberListBean.getKilledUserNickName());
            }else {
                //拉黑的是主播
                recharge_marquee_view.setText(String.format(Utils.getString(R.string.主播),memberKillMemberListBean.getKilledAnchorUserNickName()));
            }
            operating_tv.setText(Utils.getString(R.string.解封));
        }
    }
}
