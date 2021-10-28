package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy8OpenResultMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Happy8RecycleAdapter extends CommonAdapter<Happy8RecycleAdapter.MyHolder, Happy8OpenResultMedol> {
    private ArrayList<MyCornerTextView>  myCornerTextViewArrayList;
    public Happy8RecycleAdapter() {
    }
    public Happy8RecycleAdapter(ArrayList<Happy8OpenResultMedol> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        initList(commonHolder);
        Happy8OpenResultMedol itemModel = getItemModel(position);
        commonHolder.qishu.setText(itemModel.getQishu());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" HH:mm:ss");
        String format = simpleDateFormat.format(new Date(Long.parseLong(itemModel.getTime())));
        commonHolder.time.setText(format);
        if(!itemModel.isZongHe()){
            commonHolder.sumLinear.setVisibility(View.VISIBLE);
            commonHolder.zongheLinear.setVisibility(View.GONE);
            String lotteryNo = itemModel.getLotteryNo();
            String[] split = lotteryNo.split(",");
            List<String> strings = Arrays.asList(split);
            for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
                MyCornerTextView myCornerTextView = myCornerTextViewArrayList.get(i);
                myCornerTextView.setText(strings.get(i));
                myCornerTextView.setfilColor(Color.parseColor("#0091de"));
                myCornerTextView.setCornerSize(50);
            }
        }else {
            commonHolder.sumLinear.setVisibility(View.GONE);
            commonHolder.zongheLinear.setVisibility(View.VISIBLE);
            String lotteryNo = itemModel.getLotteryNo();
            String[] split = lotteryNo.split(",");
            List<String> strings = Arrays.asList(split);
            commonHolder.zonghe.setText(strings.get(0));
            MyCornerTextView daxiao1 = commonHolder.daxiao;
            daxiao1.setText(strings.get(1));
            daxiao1.setCornerSize(10);
            if(strings.get(1).equals(Utils.getString(R.string.大))){
                daxiao1.setfilColor(Color.parseColor("#3174ff"));
            }else {
                daxiao1.setfilColor(Color.parseColor("#ff7604"));
            }
            commonHolder.danshuang.setText(strings.get(2));
            commonHolder.danshuang.setCornerSize(10);
            if(strings.get(2).equals(Utils.getString(R.string.双))){
                commonHolder.danshuang.setfilColor(Color.parseColor("#ff7604"));
            }else {
                commonHolder.danshuang.setfilColor(Color.parseColor("#3174ff"));
            }
            commonHolder.wuxing.setText(strings.get(3));
            if(strings.get(3).equals(Utils.getString(R.string.金))){
                commonHolder.wuxing.setTextColor(Color.parseColor("#FFBA00"));
            }else if(strings.get(3).equals(Utils.getString(R.string.木))){
                commonHolder.wuxing.setTextColor(Color.parseColor("#B45526"));
            }else if(strings.get(3).equals(Utils.getString(R.string.水))){
                commonHolder.wuxing.setTextColor(Color.parseColor("#0088FE"));
            }else if(strings.get(3).equals(Utils.getString(R.string.火))){
                commonHolder.wuxing.setTextColor(Color.parseColor("#FD7302"));
            }else {
                commonHolder.wuxing.setTextColor(Color.parseColor("#575757"));
            }

            commonHolder.qinhouhe.setText(strings.get(4));
            commonHolder.qinhouhe.setCornerSize(10);
            if(strings.get(4).equals(Utils.getString(R.string.前多))){
                commonHolder.qinhouhe.setfilColor(Color.parseColor("#5191FF"));
            }else if(strings.get(4).equals(Utils.getString(R.string.后多))) {
                commonHolder.qinhouhe.setfilColor(Color.parseColor("#FF6C00"));
            }else if(strings.get(4).equals(Utils.getString(R.string.前后括号和))) {
                commonHolder.qinhouhe.setfilColor(Color.parseColor("#53DB45"));
            }

            commonHolder.danshaunge.setText(strings.get(5));
            commonHolder.danshaunge.setCornerSize(10);
            if(strings.get(5).equals(Utils.getString(R.string.单多))){
                commonHolder.danshaunge.setfilColor(Color.parseColor("#5191FF"));
            }else if(strings.get(5).equals(Utils.getString(R.string.双多))) {
                commonHolder.danshaunge.setfilColor(Color.parseColor("#FF6C00"));
            }else if(strings.get(5).equals(Utils.getString(R.string.单双括号和))) {
                commonHolder.danshaunge.setfilColor(Color.parseColor("#53DB45"));
            }
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
        return R.layout.happy8_open_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView qishu;
        private TextView time;;
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
        private TextView zonghe;
        private MyCornerTextView daxiao;
        private MyCornerTextView danshuang;
        private TextView wuxing;
        private MyCornerTextView qinhouhe;
        private MyCornerTextView danshaunge;
        private LinearLayout sumLinear;
        private LinearLayout zongheLinear;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qishu);
            time=itemView.findViewById(R.id.shijian);
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

            zonghe=itemView.findViewById(R.id.hezhi_sum);
            daxiao=itemView.findViewById(R.id.zonghe_num_daxiao);
            danshuang=itemView.findViewById(R.id.zonghe_num_dashuang);
            wuxing=itemView.findViewById(R.id.zonghe_num_wuxing);
            qinhouhe=itemView.findViewById(R.id.zonghe_num_qinhouhe);
            danshaunge=itemView.findViewById(R.id.zonghe_num_danshuanghe);
            sumLinear=itemView.findViewById(R.id.num_linear);
            zongheLinear=itemView.findViewById(R.id.hezhi_linear);

        }
    }
}
