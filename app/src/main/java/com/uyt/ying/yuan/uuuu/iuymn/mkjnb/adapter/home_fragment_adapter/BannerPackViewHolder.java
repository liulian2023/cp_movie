package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BannerPackData;
import com.zhpan.bannerview.holder.ViewHolder;

public class BannerPackViewHolder implements ViewHolder<BannerPackData> {
    private ImageView iv;
    private TextView tv;

    @Override
    public View createView(ViewGroup viewGroup, Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_pack_layout, viewGroup, false);
        iv = view.findViewById(R.id.iv_banner);
        tv = view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void onBind(Context context, BannerPackData data, int position, int size) {
        tv.setText(data.getTitle());
        Glide.with(context)
                .load(data.getResId())
                .into(iv);
    }
}
