package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.reflect.Field;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils.dp2px;

public class UrlImageSpan extends ImageSpan {
    private String url;
    private TextView tv;
    private boolean picShowed;
    int width;
    int height;
    public UrlImageSpan(Context context, String url, TextView tv,int width,int height,int defaultDraw) {
        super(context, defaultDraw);
        this.url = url;
        this.tv = tv;
        this.width = width;
        this.height = height;
    }

    @Override
    public Drawable getDrawable() {
        if (!picShowed) {
            Glide.with(tv.getContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Resources resources = tv.getContext().getResources();
                    int targetWidth = (int) (resources.getDisplayMetrics().widthPixels * 0.8);
//                    Bitmap zoom = zoom(resource, targetWidth);
                    Bitmap zoom = getNewBitmap(resource, width,height);
                    BitmapDrawable b = new BitmapDrawable(resources, zoom);

                    b.setBounds(0, 0, b.getIntrinsicWidth(), b.getIntrinsicHeight());
                    Field mDrawable;
                    Field mDrawableRef;
                    try {
                        mDrawable = ImageSpan.class.getDeclaredField("mDrawable");
                        mDrawable.setAccessible(true);
                        mDrawable.set(UrlImageSpan.this, b);

                        mDrawableRef = DynamicDrawableSpan.class.getDeclaredField("mDrawableRef");
                        mDrawableRef.setAccessible(true);
                        mDrawableRef.set(UrlImageSpan.this, null);

                        picShowed = true;
                        tv.setText(tv.getText());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }

            });

        }
        return super.getDrawable();
    }

    public static Bitmap getNewBitmap(@NonNull Bitmap bitmap, int newWidth , int newHeight){
        // 获得图片的宽高.
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例. (要先转为px)
        float scaleWidth = ((float) dp2px(newWidth)) / width;
        float scaleHeight = ((float) dp2px(newHeight)) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBitmap;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = 0;
        transY = ((bottom-top) - b.getBounds().bottom)/2+top;
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}