package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChatUserinfoModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.util.ArrayList;

public class UserInfoAdapter extends CommonAdapter<UserInfoAdapter.MyHolder, ChatUserinfoModel> {
    Context context;
    public UserInfoAdapter(ArrayList<ChatUserinfoModel> list, Context context) {
        super(list);
        this.context = context;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        ChatUserinfoModel itemModel = getItemModel(position);
//        Glide.with(context)
//                .load(itemModel.getImageUrl())
//                .placeholder(R.drawable.rotate_pro)
//                .into(commonHolder.imageView);

        Glide.with(context)
                .load(itemModel.getImageUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(commonHolder.imageView);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.chat_user_info_item;
    }

    public static class MyHolder extends CommonHolder {
        private ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.lottery_image);
        }
    }
}
