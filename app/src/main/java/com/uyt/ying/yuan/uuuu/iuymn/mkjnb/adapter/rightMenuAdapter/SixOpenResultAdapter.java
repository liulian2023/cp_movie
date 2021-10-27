/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SixResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SixOpenResultAdapter extends CommonAdapter<SixOpenResultAdapter.MyHolder, SixResultModel> {
    private ArrayList<MyCornerTextView>myCornerTextViewArrayList;
    private ArrayList<TextView>textViewArrayList;
    private ArrayList<String> myColorList;
    public SixOpenResultAdapter(ArrayList<SixResultModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        myCornerTextViewArrayList=new ArrayList<>();
        textViewArrayList=new ArrayList<>();
        myColorList=new ArrayList<>();
        myCornerTextViewArrayList.add(commonHolder.numOneMtv);
        myCornerTextViewArrayList.add(commonHolder.numTwoMtv);
        myCornerTextViewArrayList.add(commonHolder.numThreeMtv);
        myCornerTextViewArrayList.add(commonHolder.numFourMtv);
        myCornerTextViewArrayList.add(commonHolder.numFiveMtv);
        myCornerTextViewArrayList.add(commonHolder.numSixMtv);
        myCornerTextViewArrayList.add(commonHolder.numSevenMtv);
        textViewArrayList.add(commonHolder.typeOne);
        textViewArrayList.add(commonHolder.typeTwo);
        textViewArrayList.add(commonHolder.typeThree);
        textViewArrayList.add(commonHolder.typeFour);
        textViewArrayList.add(commonHolder.typeFive);
        textViewArrayList.add(commonHolder.typeSix);
        textViewArrayList.add(commonHolder.typeSeven);
        SixResultModel itemModel = getItemModel(position);
        commonHolder.qishu.setText(itemModel.getQishu());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        String time = itemModel.getTime();
        Date date = new Date(Long.parseLong(time));
        String format = simpleDateFormat.format(date);
        commonHolder.shijian.setText(format);
        List<String> lotteryNumList = Arrays.asList(itemModel.getLotteryNo().split(","));
        List<String> animalList = Arrays.asList(itemModel.getAnimalAll().split(","));
        List<String> colorList = Arrays.asList(itemModel.getColor().split(","));
        for (int i = 0; i < lotteryNumList.size(); i++) {
           myCornerTextViewArrayList.get(i).setText(lotteryNumList.get(i));
           myCornerTextViewArrayList.get(i).setCornerSize(50);
        }
        for (int i = 0; i <animalList.size() ; i++) {
            textViewArrayList.get(i).setText(animalList.get(i));
        }
        for (int i = 0; i < colorList.size(); i++) {
            String color = colorList.get(i);
            if(color.equals("red")){
                myColorList.add("#ff5151");//red
            }else if(color.equals("blue")){
                myColorList.add("#337ab7");//bule
            }else {
                myColorList.add("#3fa83e"); //green
            }
        }
        for (int i = 0; i < myColorList.size(); i++) {
            myCornerTextViewArrayList.get(i).setfilColor(Color.parseColor(myColorList.get(i)));
        }
    }

    @Override
    public int getLayOutRes() {
        return R.layout.six_open_result_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView qishu;
        private TextView shijian;
        private MyCornerTextView numOneMtv;
        private MyCornerTextView numTwoMtv;
        private MyCornerTextView numThreeMtv;
        private MyCornerTextView numFourMtv;
        private MyCornerTextView numFiveMtv;
        private MyCornerTextView numSixMtv;
        private MyCornerTextView numSevenMtv;
        private TextView typeOne;
        private TextView typeTwo;
        private TextView typeThree;
        private TextView typeFour;
        private TextView typeFive;
        private TextView typeSix;
        private TextView typeSeven;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qishu);
            shijian=itemView.findViewById(R.id.time);
            numOneMtv=itemView.findViewById(R.id.num_one);
            numTwoMtv=itemView.findViewById(R.id.num_two);
            numThreeMtv=itemView.findViewById(R.id.num_three);
            numFourMtv=itemView.findViewById(R.id.num_four);
            numFiveMtv=itemView.findViewById(R.id.num_five);
            numSixMtv=itemView.findViewById(R.id.num_six);
            numSevenMtv=itemView.findViewById(R.id.num_seven);
            typeOne=itemView.findViewById(R.id.num_type_one);
            typeTwo=itemView.findViewById(R.id.num_type_two);
            typeThree=itemView.findViewById(R.id.num_type_three);
            typeFour=itemView.findViewById(R.id.num_type_four);
            typeFive=itemView.findViewById(R.id.num_type_five);
            typeSix=itemView.findViewById(R.id.num_type_six);
            typeSeven=itemView.findViewById(R.id.num_type_seven);
        }
    }
}
