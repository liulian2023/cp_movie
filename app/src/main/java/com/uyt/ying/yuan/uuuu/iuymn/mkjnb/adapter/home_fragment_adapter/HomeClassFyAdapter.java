/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeGridView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class HomeClassFyAdapter extends CommonAdapter<HomeClassFyAdapter.MyHolder, HomeGridView> {
    Fragment fragment;

    public HomeClassFyAdapter(ArrayList<HomeGridView> list, Fragment fragment) {
        super(list);
        this.fragment = fragment;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        HomeGridView itemModel = getItemModel(position);
        ImageView imageView = commonHolder.imageView;
        HomeGridView.ChessEntity.DataBean chessDataBean = itemModel.getChessDataBean();
        if(chessDataBean ==null){
            //彩票数据
            String urlPath = itemModel.getImageUrl();
            if(urlPath.equals(Utils.getString(R.string.全部彩票))){
                GlideLoadViewUtil.FLoadNormalView(fragment,Utils.getHomeLogo("liveIcon8"),imageView);
            }else {
                Glide.with(fragment)
                        .load(urlPath)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }
            commonHolder.textView.setText(itemModel.getLottoeryName());
            if(itemModel.isXian()){
                commonHolder.xianIv.setVisibility(View.VISIBLE);
            }else {
                commonHolder.xianIv.setVisibility(View.GONE);
            }
        }else {

            Glide.with(fragment)
                    .load(Utils.checkImageUrl(chessDataBean.getImage()))
//                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            commonHolder.textView.setText(chessDataBean.getTypename());
            commonHolder.xianIv.setVisibility(View.GONE);
        }

        commonHolder.wrapLinear.setTag(position);
        commonHolder.wrapLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view, (Integer) view.getTag());
                }
            }
        });

    }

    @Override
    public int getLayOutRes() {
        return R.layout.home_hot_lottery_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private ImageView imageView;
        private TextView textView;
        private LinearLayout wrapLinear;
        private ImageView xianIv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.classfy_img);
            textView=itemView.findViewById(R.id.classfy_content);
            wrapLinear=itemView.findViewById(R.id.classfy_wrap_linear);
            xianIv=itemView.findViewById(R.id.xian_iv);
        }
    }
}
