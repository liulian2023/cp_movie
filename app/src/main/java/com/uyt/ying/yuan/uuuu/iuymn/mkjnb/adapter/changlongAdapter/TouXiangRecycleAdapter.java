
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.changlongAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TouXiangMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;


import java.util.ArrayList;

public class TouXiangRecycleAdapter extends CommonAdapter<TouXiangRecycleAdapter.MyHolder, TouXiangMedol> {

    Context context;
    public TouXiangRecycleAdapter(ArrayList<TouXiangMedol> list,Context context) {
        super(list);
        this.context=context;
    }
    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        TouXiangMedol itemModel = getItemModel(position);
        Glide.with(context)
                .load(itemModel.getImage())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(commonHolder.imageView);
        commonHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag(
                    ));
                }
            }
        });
        commonHolder.imageView.setTag(position);
    }
    @Override
    public int getLayOutRes() {
        return R.layout.chatroom_touxiang_recycle;
    }

    public static class MyHolder extends CommonHolder {
        private ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.chat_room_touxiang_item);
        }
    }
}
