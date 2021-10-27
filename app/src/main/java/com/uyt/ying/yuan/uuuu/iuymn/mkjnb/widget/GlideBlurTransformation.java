package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.widget;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.BitmapUtils;

import java.security.MessageDigest;

public class GlideBlurTransformation extends CenterCrop {
    private Context context;

    public GlideBlurTransformation(Context context) {
        this.context = context;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap bitmap = super.transform(pool, toTransform, outWidth, outHeight);
        return BitmapUtils.blurBitmap(context, bitmap, 25, (int) (outWidth * 0.5), (int) (outHeight * 0.5));
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }
}