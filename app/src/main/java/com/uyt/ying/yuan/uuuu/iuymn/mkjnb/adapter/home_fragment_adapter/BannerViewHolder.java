/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BannerData;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.zhpan.bannerview.holder.ViewHolder;

public class BannerViewHolder implements ViewHolder<BannerData.ExtensionNoticeInfoListBean> {
    private ImageView mImageView;
    @Override
    public View createView(ViewGroup viewGroup, Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, viewGroup, false);
        mImageView = view.findViewById(R.id.banner_imageView);
        return view;
    }

    @Override
    public void onBind(Context context, BannerData.ExtensionNoticeInfoListBean data, int position, int size) {
        Glide.with(context)
                .load(Utils.checkImageUrl(data.getImage()))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }


}
