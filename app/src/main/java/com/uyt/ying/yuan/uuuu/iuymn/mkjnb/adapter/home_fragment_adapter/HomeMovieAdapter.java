/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.home_fragment_adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomeMovieModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;



import java.util.ArrayList;

public class HomeMovieAdapter extends CommonAdapter<HomeMovieAdapter.MyHolder, HomeMovieModel> {
    Fragment fragment;
    boolean isSearch;
    Activity activity;

    public HomeMovieAdapter(ArrayList<HomeMovieModel> list, Activity activity, boolean isSearch) {
        super(list);
        this.activity = activity;
        this.isSearch = isSearch;
    }

    public HomeMovieAdapter(ArrayList<HomeMovieModel> list, Fragment fragment) {
        super(list);
        this.fragment = fragment;
    }

    public HomeMovieAdapter(ArrayList<HomeMovieModel> list, Activity activity) {
        super(list);
        this.activity = activity;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        if(isSearch){
            commonHolder.bottomLinear.setVisibility(View.GONE);
        }else {
            commonHolder.bottomLinear.setVisibility(View.VISIBLE);
        }
        HomeMovieModel itemModel = getItemModel(position);
        commonHolder.classfyTv.setText(itemModel.getClassfyName());
        commonHolder.lotteryNameTv.setText(itemModel.getLotteryName()+"");
        commonHolder.movieNameTv.setText(itemModel.getMovieName());
        commonHolder.watchNumTv.setText(itemModel.getWatchNum());
        if(null==fragment){
            GlideLoadViewUtil.LoadCornersView(activity,itemModel.getImageUrl(),12,commonHolder.imageView);
        }else {
            GlideLoadViewUtil.FLoadCornersView(fragment,itemModel.getImageUrl(),12,commonHolder.imageView);
        }
        RelativeLayout wrapLinear = commonHolder.wrapLinear;
        wrapLinear.setTag(position);
        wrapLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }


    @Override
    public int getLayOutRes() {
        return R.layout.home_movie_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private RelativeLayout wrapLinear;
        private TextView lotteryNameTv;
        private ImageView imageView;
        private TextView classfyTv;
        private TextView watchNumTv;
        private TextView movieNameTv;
        private RelativeLayout bottomLinear;//搜素结果调用此适配器时,底部的观看次数linear要隐藏
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            wrapLinear=itemView.findViewById(R.id.home_movie_wrap_linear);
            lotteryNameTv=itemView.findViewById(R.id.home_movie_lottery_name_tv);
            imageView=itemView.findViewById(R.id.home_movie_iv);
            classfyTv=itemView.findViewById(R.id.home_movie_classfy_tv);
            watchNumTv=itemView.findViewById(R.id.watch_num_tv);
            movieNameTv=itemView.findViewById(R.id.home_movie_name_tv);
            bottomLinear=itemView.findViewById(R.id.bottom_linear);
        }
    }
}
