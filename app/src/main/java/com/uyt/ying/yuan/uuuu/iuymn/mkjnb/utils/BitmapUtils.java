
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
    /**
     * 把原图按比例压缩
     *
     * @param srcPath 原图的路径
     * @param maxSize 指定的图片最大尺寸
     * @return 压缩后的图片
     */
    public static Bitmap getCompressPhoto(String srcPath, int maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);//此时返回bm为空
        options.inJustDecodeBounds = false;
        int sampleSize = 1;//1代表不压缩
        if (options.outHeight > 0 && options.outWidth > 0) {
            while (options.outHeight / sampleSize > maxSize || options.outWidth / sampleSize > maxSize) {
                sampleSize = sampleSize + 1;
            }
        } else {
            sampleSize = sampleSize << 1;
        }
        options.inSampleSize = sampleSize;//设置缩放比例
        try {
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(srcPath, options);
        } catch (OutOfMemoryError e) {
            options.inSampleSize *= 2;
            bitmap = BitmapFactory.decodeFile(srcPath, options);
        }
        return bitmap;
    }
    /**
     * 质量压缩
     *
     * @param srcPath 原图路径
     * @param context
     * @param max 要压缩到多大以内（单位：kb）
     * @return
     */
    public static String  compressReSave(String srcPath, Context context, int max) {
        String filePath = "";
            Bitmap image = BitmapFactory.decodeFile(srcPath);
            if(image==null){
                return srcPath;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 90;
            while (baos.toByteArray().length / 1024 > max) { // 循环判断如果压缩后图片是否大于maxkb,大于继续压缩
                baos.reset(); // 重置baos即清空baos
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                if (options > 10){
                    options -= 10;// 每次都减少10
                } else {
                    options -= 5;
                }

                if (options == 5){//这里最多压缩到5，options不能小于0
                    break;
                }
            }
            FileOutputStream outStream = null;
            filePath = createFile(context, "copy",srcPath);
            try {
                outStream = new FileOutputStream(filePath);
                // 把数据写入文件
                outStream.write(baos.toByteArray());
                // 记得要关闭流！
                outStream.close();
                return filePath;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (outStream != null) {
                        // 记得要关闭流！
                        outStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


    }

    public static String createFile(Context context, String pathName,String srcPath) {
        String path = "";
        File file = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + pathName);
        } else {
            file = new File(context.getFilesDir().getPath() + File.separator + pathName);
        }
        if (file != null) {
            if (!file.exists()) {
                file.mkdir();
            }
            File output;
            if(srcPath.endsWith(".gif")){
                output  = new File(file, System.currentTimeMillis() + ".gif");
            }else {
                output  = new File(file, System.currentTimeMillis() + ".jpg");
            }
            try {
                if (output.exists()) {
                    output.delete();
                } else {
                    output.createNewFile();
                }
                path = output.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }


    /**
     * @param image         需要模糊的图片
     * @param blurRadius    模糊的半径（1-25之间）
     * @return 模糊处理后的Bitmap
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius, int outWidth, int outHeight) {
        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, outWidth, outHeight, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        // 设置渲染的模糊程度, 25f是最大模糊度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            blurScript.setRadius(blurRadius);
        }
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

}
