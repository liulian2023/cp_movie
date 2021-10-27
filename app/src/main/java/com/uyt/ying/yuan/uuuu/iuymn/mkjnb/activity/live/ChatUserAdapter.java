package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.ChatUserEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class ChatUserAdapter extends BaseQuickAdapter<ChatUserEntity.MemberHeadPortraitListBean, BaseViewHolder> {

    public ChatUserAdapter(int layoutResId, @Nullable List<ChatUserEntity.MemberHeadPortraitListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatUserEntity.MemberHeadPortraitListBean item) {
        ImageView iv = helper.getView(R.id.iv_item_chatuser);
        GlideLoadViewUtil.LoadCircleView(getContext(), Utils.CPImageUrlCheck(getContext(), item.getImage()),iv);
    }
}
