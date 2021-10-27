package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy10OpenResultMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Happy10OPenResultAdapter extends CommonAdapter<Happy10OPenResultAdapter.MyHolder, Happy10OpenResultMedol> {
    private ArrayList<MyCornerTextView> myCornerTextViewArrayList;
    public Happy10OPenResultAdapter() {
    }

    public Happy10OPenResultAdapter(ArrayList<Happy10OpenResultMedol> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        initList(commonHolder);
        Happy10OpenResultMedol itemModel = getItemModel(position);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long timeValue = Long.parseLong(itemModel.getTime());
        String format = sdf.format(new Date(timeValue));
        commonHolder.qishu.setText(itemModel.getQishu());
        commonHolder.time.setText(format);
        commonHolder.hezhi.setText(itemModel.getHezhi());
        commonHolder.daxiao.setText(itemModel.getDaxiao());
        commonHolder.danshuang.setText(itemModel.getDanshuang());
        String lotteryNo = itemModel.getLotteryNo();
        String[] split = lotteryNo.split(",");
        List<String> strings = Arrays.asList(split);
        for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
            MyCornerTextView myCornerTextView = myCornerTextViewArrayList.get(i);
            myCornerTextView.setText(strings.get(i));
            myCornerTextView.setfilColor(Color.parseColor("#0091de"));
            myCornerTextView.setCornerSize(50);
        }
        if(itemModel.getDaxiao().equals(Utils.getString(R.string.大))){
            commonHolder.daxiao.setTextColor(Color.parseColor("#3174ff"));
        }else{
            commonHolder.daxiao.setTextColor(Color.parseColor("#ff7604"));
        }
        if (itemModel.getDanshuang().equals(Utils.getString(R.string.单))) {
            commonHolder.danshuang.setTextColor(Color.parseColor("#3174ff"));
        }else{
            commonHolder.danshuang.setTextColor(Color.parseColor("#ff7604"));
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

    }

    @Override
    public int getLayOutRes() {
        return R.layout.happy10_today_result_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView qishu;
        private TextView time;
        private TextView hezhi;
        private TextView daxiao;
        private TextView danshuang;
        private MyCornerTextView num1;
        private MyCornerTextView num2;
        private MyCornerTextView num3;
        private MyCornerTextView num4;
        private MyCornerTextView num5;
        private MyCornerTextView num6;
        private MyCornerTextView num7;
        private MyCornerTextView num8;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qishu);
            time=itemView.findViewById(R.id.time);
            hezhi=itemView.findViewById(R.id.happy10_hezhi);
            daxiao=itemView.findViewById(R.id.happy10_daxiao);
            danshuang=itemView.findViewById(R.id.happy10_dashuang);
            num1=itemView.findViewById(R.id.happy10_num_one);
            num2=itemView.findViewById(R.id.happy10_num_two);
            num3=itemView.findViewById(R.id.happy10_num_three);
            num4=itemView.findViewById(R.id.happy10_num_four);
            num5=itemView.findViewById(R.id.happy10_num_five);
            num6=itemView.findViewById(R.id.happy10_num_six);
            num7=itemView.findViewById(R.id.happy10_num_seven);
            num8=itemView.findViewById(R.id.happy10_num_eight);

        }
    }
}
