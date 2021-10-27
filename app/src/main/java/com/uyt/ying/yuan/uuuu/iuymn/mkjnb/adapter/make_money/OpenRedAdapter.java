package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.make_money;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.OpenRedBeen;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.util.ArrayList;

public class OpenRedAdapter extends CommonAdapter<OpenRedAdapter.MyHolder, OpenRedBeen> {
    int[] res ={R.drawable.nn_font_texiao_n10_0,R.drawable.nn_font_texiao_n10_1,R.drawable.nn_font_texiao_n10_2,R.drawable.nn_font_texiao_n10_3,R.drawable.nn_font_texiao_n10_4};
    Context context;

    public OpenRedAdapter(ArrayList<OpenRedBeen> list, Context context) {
        super(list);
        this.context = context;
    }



    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        OpenRedBeen itemModel = getItemModel(position);
        TextView amount_tv = commonHolder.amount_tv;
        LinearLayout amount_linear = commonHolder.amount_linear;
        ImageView animation_iv = commonHolder.animation_iv;
        ImageView red_background_iv = commonHolder.red_background_iv;
        amount_tv.setText(itemModel.getAmount());

        boolean opened = itemModel.isOpened();
        if(opened){
            amount_linear.setVisibility(View.VISIBLE);
            //红包金额
            amount_tv.setText(itemModel.getAmount());
            //图片已开的背景
            red_background_iv.setImageResource(R.drawable.kaihoongbao);
            animation_iv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    animation_iv.setVisibility(View.VISIBLE);
                    AnimationDrawable animationDrawable= (AnimationDrawable) animation_iv.getBackground();
                    animationDrawable.start();
                }
            },200);
/*            amount_tv.postDelayed(new Runnable() {
                @Override
                public void run() {*/
            amount_tv.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.BounceIn)
                            .duration(600)
                            .repeat(1)
                            .playOn(amount_tv);

 /*               }
            },600);*/
        }else {
            amount_tv.setVisibility(View.GONE);
            animation_iv.setVisibility(View.GONE);
            amount_linear.setVisibility(View.GONE);
            red_background_iv.setImageResource(itemModel.getDrawableId());
           /* if(position%6==0){
                red_background_iv.setImageResource(R.drawable.open_red1);
            }else if(position%6==1){
                red_background_iv.setImageResource(R.drawable.open_red2);
            }else if(position%6==2){
                red_background_iv.setImageResource(R.drawable.open_red3);
            }else if(position%6==3){
                red_background_iv.setImageResource(R.drawable.open_red4);
            }else if(position%6==4){
                red_background_iv.setImageResource(R.drawable.open_red5);
            }else if(position%6==5){
                red_background_iv.setImageResource(R.drawable.open_red6);
            }*/
        }

    }

    @Override
    public int getLayOutRes() {
        return R.layout.open_red_recycler_item_layout;
    }

    public static class MyHolder extends CommonHolder {
        ImageView  red_background_iv ;
        LinearLayout amount_linear;
        TextView amount_tv;
        ImageView animation_iv ;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            animation_iv = itemView.findViewById(R.id.animation_iv);
            amount_tv = itemView.findViewById(R.id.amount_tv);
            amount_linear = itemView.findViewById(R.id.amount_linear);
            red_background_iv = itemView.findViewById(R.id.red_background_iv);
        }
    }
}
