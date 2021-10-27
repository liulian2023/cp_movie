package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BeiJinOpenResultModel2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LotteryNumColorUtil;

import java.util.ArrayList;

public class RaceOpenAdapterRight extends RecyclerView.Adapter<RaceOpenAdapterRight.MyHolder> {
    private ArrayList<BeiJinOpenResultModel2>beiJinOpenResultModel2s =new ArrayList<>();

    public RaceOpenAdapterRight(ArrayList<BeiJinOpenResultModel2> beiJinOpenResultModel2s) {
        this.beiJinOpenResultModel2s = beiJinOpenResultModel2s;
    }

    @NonNull
    @Override
    public RaceOpenAdapterRight.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.beijin_open_recycle_item_recycle_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceOpenAdapterRight.MyHolder myHolder, int i) {
        BeiJinOpenResultModel2 beiJinOpenResultModel2 = beiJinOpenResultModel2s.get(i);
        String result = beiJinOpenResultModel2.getResult();
        MyCornerTextView textView = myHolder.textView;
        textView.setText(result);
        textView.setTextColor(Color.WHITE);
        textView.setCornerSize(10);//设置圆角(默认为8)
        if(i==beiJinOpenResultModel2s.size()-1){//最后一个item不设置margin(每个item只设置了margin_right,最后一个不用设置)
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0,0,0);
            myHolder.linearLayout.setLayoutParams(layoutParams);
        }
        if(beiJinOpenResultModel2.isGuangYa()){
            if(result.equals(Utils.getString(R.string.大))||result.equals(Utils.getString(R.string.单))||result.equals(Utils.getString(R.string.龙))){
//                textView.setfilColor(R.color.bigDanLong);
                textView.setfilColor(Color.parseColor("#3174ff"));
                textView.setTextColor(Color.parseColor("#ffffff"));
            }else if(result.equals(Utils.getString(R.string.小))||result.equals(Utils.getString(R.string.双))||result.equals(Utils.getString(R.string.虎))){
//                textView.setfilColor(R.color.smallShuangHu);
                textView.setfilColor(Color.parseColor("#ff7604"));
                textView.setTextColor(Color.parseColor("#ffffff"));
            }
            else {
                textView.setTextColor(Color.RED);
                textView.setfilColor(Color.WHITE);
            }
        }
        else {
            if(result.equals(Utils.getString(R.string.大))||result.equals(Utils.getString(R.string.单))||result.equals(Utils.getString(R.string.龙))){
//                textView.setfilColor(R.color.bigDanLong);
                textView.setfilColor(Color.parseColor("#3174ff"));
                textView.setTextColor(Color.parseColor("#ffffff"));
            }else if(result.equals(Utils.getString(R.string.小))||result.equals(Utils.getString(R.string.双))||result.equals(Utils.getString(R.string.虎))){
//                textView.setfilColor(R.color.smallShuangHu);
                textView.setfilColor(Color.parseColor("#ff7604"));
                textView.setTextColor(Color.parseColor("#ffffff"));
            }
            else {
                LotteryNumColorUtil.initLotteryNumColor(textView);
            }
        }

    }



    @Override
    public int getItemCount() {
        return beiJinOpenResultModel2s.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private MyCornerTextView textView;
        private LinearLayout linearLayout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.open_recycle_item_text);
            linearLayout=itemView.findViewById(R.id.open_recycle_item_linear);
        }
    }
}
