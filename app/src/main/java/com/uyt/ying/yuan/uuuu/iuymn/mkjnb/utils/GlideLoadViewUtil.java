/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.uyt.ying.yuan.R;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;


public class GlideLoadViewUtil {

    //Glide加载圆角矩形图片
    public static void LoadRetcCirView(Context context,String imgUrl,int Corner,ImageView imageView){
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(Corner);
        //通过RequestOptions扩展功能
        RequestOptions options= RequestOptions.bitmapTransform(roundedCorners)/*.placeholder(R.drawable.ucrop_ic_next)*/;
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void LoadRetcCirView(Context context,int drawable,int Corner,ImageView imageView){
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(Corner);
        //通过RequestOptions扩展功能
        RequestOptions options= RequestOptions.bitmapTransform(roundedCorners)/*.placeholder(R.drawable.ucrop_ic_next)*/;
        Glide.with(context)
                .load(drawable)
                .apply(options)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    //glide加载圆形图
    public static void LoadCircleView(Context context,String imgUrl,ImageView imageView){
        Activity activity = (Activity) context;
        boolean destroyed = activity.isDestroyed();
        boolean finishing = activity.isFinishing();
        if(context!=null&&!finishing&&!destroyed){
            Glide.with(context)
                    .load(imgUrl)
                    .circleCrop()
                    .skipMemoryCache(false)
                    //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

    }
    //glide加载圆形图
    public static void LoadCircleViewRes(Context context,int drawble ,ImageView imageView){
        Glide.with(context)
                .load(drawble)
                .circleCrop()
                .skipMemoryCache(false)
                //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
    //glide加载圆形图
    public static void FLoadTitleView(Fragment fragment, String imgUrl, ImageView imageView){
        if(fragment!=null&&fragment.getActivity()!=null&&!fragment.getActivity().isFinishing()&&!fragment.getActivity().isDestroyed()){
            Glide.with(fragment)
                    .load(imgUrl)
                    .circleCrop()
                    .skipMemoryCache(false)
                    .error(R.drawable.system_default_title)
                    .placeholder(R.drawable.system_default_title)
                    //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
        }
    public static void loadTitleView(Activity activity, String imgUrl, ImageView imageView){
        if(activity!=null&&!activity.isFinishing()){
            Glide.with(activity)
                    .load(imgUrl)
                    .circleCrop()
                    .skipMemoryCache(false)
                    .error(R.drawable.system_default_title)
                    //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

//    ----------------------------------------------------------------------------------------------------------------------------------------------



    public static void LoadCornersView(Context context,String imgUrl,int corners,ImageView imageView){
        Activity activity = (Activity) context;
        boolean destroyed = activity.isDestroyed();
        boolean finishing = activity.isFinishing();
        if(context!=null&&!finishing&&!destroyed){
            Glide.with(context)
                    .load(Utils.checkImageUrl(imgUrl))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .apply(RequestOptions.bitmapTransform(mation))
                    .apply(new RequestOptions()
                            .transforms(new CenterCrop(), new RoundedCorners(Utils.dip2px(context,corners))
                            ))
                    .into(imageView);
        }

    }
    public static void LoadCornersViewRes(Context context,int res,int corners,ImageView imageView){
        Activity activity = (Activity) context;
        boolean destroyed = activity.isDestroyed();
        boolean finishing = activity.isFinishing();
        if(context!=null&&!finishing&&!destroyed){
            Glide.with(context)
                    .load(res)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .apply(RequestOptions.bitmapTransform(mation))
                    .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(Utils.dip2px(context,corners))))
                    .into(imageView);
        }

    }

    public static void FLoadCornersView(Fragment fragment, String imgUrl, int corners, ImageView imageView){
        Glide.with(fragment)
                .load(Utils.checkImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(RequestOptions.bitmapTransform(mation))
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(Utils.dip2px(fragment.getContext(),corners))))
                .into(imageView);
    }

    public static void LoadNormalView(Context context,String imgUrl,ImageView imageView){
        imgUrl = Utils.checkImageUrl(imgUrl);
        Activity activity = (Activity) context;
        boolean destroyed = activity.isDestroyed();
        boolean finishing = activity.isFinishing();
        if(context!=null&&!finishing&&!destroyed){
            Glide.with(context)
                    .load(Utils.checkImageUrl(imgUrl))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    public static void LoadNormalViewRes(Context context,int  res,ImageView imageView){
        Activity activity = (Activity) context;
        boolean destroyed = activity.isDestroyed();
        boolean finishing = activity.isFinishing();
        if(context!=null&&!finishing&&!destroyed){
            Glide.with(context)
                    .load(res)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

    }
    public static void LoadNormalView(Context context,String imgUrl,ImageView imageView,int error){
        Activity activity = (Activity) context;
        boolean destroyed = activity.isDestroyed();
        boolean finishing = activity.isFinishing();
        if(context!=null&&!finishing&&!destroyed){
            Glide.with(context)
                    .load(imgUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(error)
                    .into(imageView);
        }

    }

    public static void FLoadNormalView(Fragment fragment,String imgUrl,ImageView imageView){
        Glide.with(fragment)
                .load(Utils.checkImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void LoadMaoBoliNormalView(String imgUrl,ImageView imageView){
        Glide.with(MyApplication.getInstance())
                .load(imgUrl)
                .skipMemoryCache(true)
//                .apply(RequestOptions.bitmapTransform(new BlurTransformation(MyApplication.getInstance(),10,2)))
                .into(imageView);

    }

    public static void setBackGround(Context context, String url, View view){
        Glide.with(context)
                .load(Utils.checkImageUrl(url))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        view.setBackground(resource);
                    }
                });
    }
}
