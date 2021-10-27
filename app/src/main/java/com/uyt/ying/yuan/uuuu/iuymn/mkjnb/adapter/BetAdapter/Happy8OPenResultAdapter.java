package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy8OpenResultMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Happy8OPenResultAdapter extends CommonAdapter<Happy8OPenResultAdapter.MyHolder, Happy8OpenResultMedol> {
    private ArrayList<MyCornerTextView> myCornerTextViewArrayList;
    public Happy8OPenResultAdapter() {
    }

    public Happy8OPenResultAdapter(ArrayList<Happy8OpenResultMedol> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        initList(commonHolder);
        Happy8OpenResultMedol itemModel = getItemModel(position);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long timeValue = Long.parseLong(itemModel.getTime());
        String format = sdf.format(new Date(timeValue));
        commonHolder.qishu.setText(itemModel.getQishu());
        commonHolder.time.setText(format);
        commonHolder.hezhi.setText(itemModel.getSum());
        String lotteryNo = itemModel.getLotteryNo();
        String[] split = lotteryNo.split(",");
        List<String> strings = Arrays.asList(split);
        for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
            MyCornerTextView myCornerTextView = myCornerTextViewArrayList.get(i);
            myCornerTextView.setText(strings.get(i));
            myCornerTextView.setfilColor(Color.parseColor("#0091de"));
            myCornerTextView.setCornerSize(50);
        }
    }

    private void initList(MyHolder commonHolder) {
        myCornerTextViewArrayList=new ArrayList<>();
        myCornerTextViewArrayList.add(commonHolder.num1);
        myCornerTextViewArrayList.add(commonHolder.num2);
        myCornerTextViewArrayList.add(commonHolder.num3);
        myCornerTextViewArrayList.add(commonHolder.num4);
        myCornerTextViewArrayList.add(commonHolder.num5);
        myCornerTextViewArrayList.add(commonHolder.num6);
        myCornerTextViewArrayList.add(commonHolder.num7);
        myCornerTextViewArrayList.add(commonHolder.num8);
        myCornerTextViewArrayList.add(commonHolder.num9);
        myCornerTextViewArrayList.add(commonHolder.num10);
        myCornerTextViewArrayList.add(commonHolder.num11);
        myCornerTextViewArrayList.add(commonHolder.num12);
        myCornerTextViewArrayList.add(commonHolder.num13);
        myCornerTextViewArrayList.add(commonHolder.num14);
        myCornerTextViewArrayList.add(commonHolder.num15);
        myCornerTextViewArrayList.add(commonHolder.num16);
        myCornerTextViewArrayList.add(commonHolder.num17);
        myCornerTextViewArrayList.add(commonHolder.num18);
        myCornerTextViewArrayList.add(commonHolder.num19);
        myCornerTextViewArrayList.add(commonHolder.num20);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.happy8_today_result_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView qishu;
        private TextView time;
        private TextView hezhi;
        private MyCornerTextView num1;
        private MyCornerTextView num2;
        private MyCornerTextView num3;
        private MyCornerTextView num4;
        private MyCornerTextView num5;
        private MyCornerTextView num6;
        private MyCornerTextView num7;
        private MyCornerTextView num8;
        private MyCornerTextView num9;
        private MyCornerTextView num10;
        private MyCornerTextView num11;
        private MyCornerTextView num12;
        private MyCornerTextView num13;
        private MyCornerTextView num14;
        private MyCornerTextView num15;
        private MyCornerTextView num16;
        private MyCornerTextView num17;
        private MyCornerTextView num18;
        private MyCornerTextView num19;
        private MyCornerTextView num20;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qishu);
            time=itemView.findViewById(R.id.time);
            hezhi=itemView.findViewById(R.id.hezhi);
            num1=itemView.findViewById(R.id.bj_num_one);
            num2=itemView.findViewById(R.id.bj_num_two);
            num3=itemView.findViewById(R.id.bj_num_three);
            num4=itemView.findViewById(R.id.bj_num_four);
            num5=itemView.findViewById(R.id.bj_num_five);
            num6=itemView.findViewById(R.id.bj_num_six);
            num7=itemView.findViewById(R.id.bj_num_seven);
            num8=itemView.findViewById(R.id.bj_num_eight);
            num9=itemView.findViewById(R.id.bj_num_nine);
            num10=itemView.findViewById(R.id.bj_num_ten);
            num11=itemView.findViewById(R.id.bj_num_11);
            num12=itemView.findViewById(R.id.bj_num_12);
            num13=itemView.findViewById(R.id.bj_num_13);
            num14=itemView.findViewById(R.id.bj_num_14);
            num15=itemView.findViewById(R.id.bj_num_15);
            num16=itemView.findViewById(R.id.bj_num_16);
            num17=itemView.findViewById(R.id.bj_num_17);
            num18=itemView.findViewById(R.id.bj_num_18);
            num19=itemView.findViewById(R.id.bj_num_19);
            num20=itemView.findViewById(R.id.bj_num_20);
        }
    }
}
