package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.gift;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveMessageModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

import static android.graphics.BitmapFactory.decodeResource;

/**
 * svga 动画管理
 */
public class AssetsSvgaManage {
    private String TAG="GiftSvgaMage";
    private ArrayList<LiveMessageModel> liveMessageModelArrayList = new ArrayList<>();
    private SVGAImageView svgaImageView; // 播放控件
    private Context context;
    SVGAParser svgaParser ;
    public AssetsSvgaManage(Context context, SVGAImageView svgaImageView) {
        this.context = context;
        this.svgaImageView = svgaImageView;
        svgaParser = new SVGAParser(context);
        initSvgaImageView();
    }

    /**
     * 动画播放监听
     */
    private void initSvgaImageView() {
        svgaImageView.setFillMode(SVGAImageView.FillMode.Backward);
        svgaImageView.setLoops(1);//设置动画执行一次，如果不设置就会一直循环播放动画，
        svgaImageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {
                Utils.logE(TAG,"onFinished: 动画暂停播放");
            }

            /**
             * 播放结束后从集合中移出当前播放的动画,然后接着播放集合中第一个动画
             */
            @Override
            public void onFinished() {
                Utils.logE(TAG, "onFinished: 动画播放结束");
                if(liveMessageModelArrayList!=null && liveMessageModelArrayList.size()>0){
                    liveMessageModelArrayList.remove(0);
                    if(liveMessageModelArrayList!=null && liveMessageModelArrayList.size()>0){
                        decodeSvga(liveMessageModelArrayList.get(0));
                    }else {
                        stopSVGA();
                    }
                }else {
                    stopSVGA();
                }
            }

            @Override
            public void onRepeat() {

            }
            @Override
            public void onStep(int i, double v) {

            }
        });
    }

    /**
     *  将url解析成播放svga动画需要的实体类
     * @param liveMessageModel svga动画地址
     */
    public void startAnimator(LiveMessageModel liveMessageModel ) {
        liveMessageModelArrayList.add(liveMessageModelArrayList.size(),liveMessageModel);
        if(liveMessageModelArrayList.size() == 1){
            decodeSvga(liveMessageModel);
        }


    }
    /**
     *  将url解析成播放svga动画需要的实体类
     * @param liveMessageModel 消息实体类
     */

    private void decodeSvga(LiveMessageModel liveMessageModel) {
        try{
            String fileName = swichLevel(liveMessageModel);
            if(liveMessageModelArrayList.size()>0){
                svgaParser.decodeFromAssets(fileName, new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(SVGAVideoEntity svgaVideoEntity) {

                        SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                        String userName = liveMessageModel.getUserName();
                        String level = liveMessageModel.getLevel();
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("Lv."+level +"  "+ userName +Utils.getString(R.string.进入直播间));
                        //等级字体大小
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(28),0,level.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        //等级字体样式(斜体 加粗)
                        spannableStringBuilder.setSpan( new StyleSpan(Typeface.BOLD_ITALIC),0,level.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        //昵称颜色span
//                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.YELLOW), 6, userName.length()+6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                        TextPaint textPaint = new TextPaint();
                        textPaint.setColor(Color.WHITE);
                        textPaint.setTextSize(25);
                        dynamicEntity.setDynamicText(new StaticLayout(
                                spannableStringBuilder,//文本内容
                                0,//开始位置
                                spannableStringBuilder.length(),//结束位置,
                                textPaint,//文本画笔对象
                                0,//布局高度.超出宽度换行显示
                                Layout.Alignment.ALIGN_NORMAL,//对齐方式
                                1.0f,//行间距倍数，默认是1
                                0.0f,//行距增加值，默认是0。
                                false//文本顶部和底部是否留白
                        ), "content");
                        String portrait = liveMessageModel.getPortrait();
                        if(StringMyUtil.isEmptyString(portrait)){
                            //没有设置过头像时portrait为空,设置默认头像
                            Bitmap bitmap = decodeResource(context.getResources(), R.drawable.system_default_title);
                            dynamicEntity.setDynamicImage(bitmap,"avatar");
                        }else {
                            dynamicEntity.setDynamicImage(Utils.checkImageUrl(portrait),"avatar");
                        }

                        SVGADrawable drawable = new SVGADrawable(svgaVideoEntity,dynamicEntity);
                        svgaImageView.setImageDrawable(drawable);
                        svgaImageView.startAnimation();

                    }

                    @Override
                    public void onError() {
                        Utils.logE(TAG,"onError: 礼物特效播放失败" );
                        //如果动画数组列表大于0,移除第一位的动画,继续循环解析
                        if (liveMessageModelArrayList.size() > 0) {
                            liveMessageModelArrayList.remove(0);
                            if(liveMessageModelArrayList.size()>0){
                                decodeSvga(liveMessageModelArrayList.get(0));
                            }
                        } else {
                            stopSVGA();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void stopSVGA() {
        if (svgaImageView.isAnimating() && liveMessageModelArrayList.size() == 0) {
            svgaImageView.stopAnimation();
        }
    }
    private String swichLevel(LiveMessageModel liveMessageModel) {
        String fileName="join_lv1_5.svga";
        return fileName;
    }

}
