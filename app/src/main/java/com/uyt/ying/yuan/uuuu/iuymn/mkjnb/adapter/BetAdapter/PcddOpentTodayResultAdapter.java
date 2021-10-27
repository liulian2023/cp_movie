package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;


import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.PcddTodayResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PcddOpentTodayResultAdapter extends CommonAdapter<PcddOpentTodayResultAdapter.MyHolder, PcddTodayResultModel> {
    public PcddOpentTodayResultAdapter(ArrayList<PcddTodayResultModel> list) {
        super(list);
    }
    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        PcddTodayResultModel itemModel = getItemModel(position);
        String qishu = itemModel.getQishu();
        String lotteryNo = itemModel.getLotteryNo();
        commonHolder.lotteryQiShu.setText(qishu);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String format = simpleDateFormat.format(new Date(Long.parseLong(itemModel.getTime())));
        commonHolder.time.setText(format);

        String[] split = lotteryNo.split(",");
        List<String> strings = Arrays.asList(split);
        String numOne = strings.get(0);
        String numTwo = strings.get(1);
        String numThree = strings.get(2);
        commonHolder.numOne.setText(numOne);
        commonHolder.numTwo.setText(numTwo);
        commonHolder.numThree.setText(numThree);
        commonHolder.numOne.setfilColor(Color.parseColor("#0091de"));
        commonHolder.numTwo.setfilColor(Color.parseColor("#0091de"));
        commonHolder.numThree.setfilColor(Color.parseColor("#0091de"));
        commonHolder.numOne.setCornerSize(50);
        commonHolder.numTwo.setCornerSize(50);
        commonHolder.numThree.setCornerSize(50);
        commonHolder.hezhi.setText(itemModel.getHezhi());
        commonHolder.daxiao.setText(itemModel.getDaxiao());
        commonHolder.xingtai.setText(itemModel.getXingtai());
//  <color name="bigDanLong">#3174ff</color>
//    <color name="smallShuangHu">#ff7604</color>
        if(itemModel.getDaxiao().equals(Utils.getString(R.string.大))){
            commonHolder.daxiao.setfilColor(Color.parseColor("#3174ff"));
        }
        else {
            commonHolder.daxiao.setfilColor(Color.parseColor("#ff7604"));
        }

        if(itemModel.getXingtai().equals(Utils.getString(R.string.双))){
            commonHolder.xingtai.setfilColor(Color.parseColor("#ff7604"));
        }else if(itemModel.getXingtai().equals(Utils.getString(R.string.单))){
            commonHolder.xingtai.setfilColor(Color.parseColor("#3174ff"));
        }else{
            commonHolder.xingtai.setfilColor(Color.parseColor("#0091de"));
        }
        commonHolder.xingtai.setCornerSize(10);
        commonHolder.daxiao.setCornerSize(10);
    }
    @Override
    public int getLayOutRes() {
        return R.layout.pcdd_today_result_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView lotteryQiShu;
        TextView time;
        MyCornerTextView numOne;
        MyCornerTextView numTwo;
        MyCornerTextView numThree;
        TextView hezhi;
        MyCornerTextView daxiao;
        MyCornerTextView xingtai;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lotteryQiShu=itemView.findViewById(R.id.qishu);
            time=itemView.findViewById(R.id.time);
            numOne=itemView.findViewById(R.id.num_one);
            numTwo=itemView.findViewById(R.id.num_two);
            numThree=itemView.findViewById(R.id.num_three);
            hezhi=itemView.findViewById(R.id.hezhi);
            daxiao=itemView.findViewById(R.id.daxiao);
            xingtai=itemView.findViewById(R.id.xingtai);

        }
    }
}
