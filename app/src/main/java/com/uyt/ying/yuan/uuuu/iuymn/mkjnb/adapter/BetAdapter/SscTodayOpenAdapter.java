package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscTodayOpenMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SscTodayOpenAdapter extends CommonAdapter<SscTodayOpenAdapter.MyHolder, SscTodayOpenMedol> {
    private ArrayList<MyCornerTextView> myCornerTextViewArrayList;
    public SscTodayOpenAdapter() {
    }

    public SscTodayOpenAdapter(ArrayList<SscTodayOpenMedol> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        initList(commonHolder);
        SscTodayOpenMedol itemModel = getItemModel(position);
        commonHolder.qihaoTv.setText(itemModel.getQihao());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        commonHolder.timeTv.setText(simpleDateFormat.format(new Date(Long.parseLong(itemModel.getTime()))));
        String lotteryNo = itemModel.getLotteryNo();
        String[] split = lotteryNo.split(",");
        List<String> strings = Arrays.asList(split);
        Long hezhi =Long.parseLong(strings.get(0))+Long.parseLong(strings.get(1))+Long.parseLong(strings.get(2))+Long.parseLong(strings.get(3))+Long.parseLong(strings.get(4));
        for (int i = 0; i < myCornerTextViewArrayList.size(); i++) {
            MyCornerTextView myCornerTextView = myCornerTextViewArrayList.get(i);
            myCornerTextView.setText(strings.get(i));
            myCornerTextView.setfilColor(Color.parseColor("#0091de"));
            myCornerTextView.setCornerSize(50);
        }
        commonHolder.hezhiOneTv.setText(hezhi+"");
        String markdx = itemModel.getMarkdx();
        if(markdx.equals(Utils.getString(R.string.大))){
            commonHolder.hezhiTwoTv.setTextColor(Color.parseColor("#f6399c"));
        }
        else{
            commonHolder.hezhiTwoTv.setTextColor(Color.parseColor("#38a6e4"));
        }
        commonHolder.hezhiTwoTv.setText(markdx);
        String markds = itemModel.getMarkds();
        if(markds.equals(Utils.getString(R.string.双))){
            commonHolder.hezhiThreeTv.setTextColor(Color.parseColor("#f6399c"));
        }else {
            commonHolder.hezhiThreeTv.setTextColor(Color.parseColor("#38a6e4"));
        }
        commonHolder.hezhiThreeTv.setText(markds);
        String marklh = itemModel.getMarklh();
        if(itemModel.getGame()==9){//11选5,开奖结果pop不需要显示龙虎
            commonHolder.hezhiFourTv.setVisibility(View.GONE);
        }else {
            commonHolder.hezhiFourTv.setVisibility(View.VISIBLE);
            if(marklh.equals(Utils.getString(R.string.虎))){
                commonHolder.hezhiFourTv.setTextColor(Color.parseColor("#f6399c"));
            }else if(marklh.equals(Utils.getString(R.string.龙))){
                commonHolder.hezhiFourTv.setTextColor(Color.parseColor("#38a6e4"));
            } else {//和
                commonHolder.hezhiFourTv.setTextColor(Color.parseColor("#E73F3F"));
            }
            commonHolder.hezhiFourTv.setText(marklh);
        }

    }

    private void initList(MyHolder commonHolder) {
        myCornerTextViewArrayList=new ArrayList<>();
        myCornerTextViewArrayList.add(commonHolder.num1Tv);
        myCornerTextViewArrayList.add(commonHolder.num2Tv);
        myCornerTextViewArrayList.add(commonHolder.num3Tv);
        myCornerTextViewArrayList.add(commonHolder.num4Tv);
        myCornerTextViewArrayList.add(commonHolder.num5Tv);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.ssc_today_result_recycle_item;
    }

    public  static class MyHolder extends CommonHolder {
        private TextView qihaoTv;
        private TextView hezhiOneTv;
        private TextView hezhiTwoTv;
        private TextView hezhiThreeTv;
        private TextView hezhiFourTv;
        private TextView timeTv;
        private MyCornerTextView num1Tv;
        private MyCornerTextView num2Tv;
        private MyCornerTextView num3Tv;
        private MyCornerTextView num4Tv;
        private MyCornerTextView num5Tv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qihaoTv=itemView.findViewById(R.id.qishu);
            hezhiOneTv=itemView.findViewById(R.id.hezhi_one);
            hezhiTwoTv=itemView.findViewById(R.id.hezhi_two);
            hezhiThreeTv=itemView.findViewById(R.id.hezhi_three);
            hezhiFourTv=itemView.findViewById(R.id.hezhi_four);
            num1Tv=itemView.findViewById(R.id.ssc_num_one);
            num2Tv=itemView.findViewById(R.id.ssc_num_two);
            num3Tv=itemView.findViewById(R.id.ssc_num_three);
            num4Tv=itemView.findViewById(R.id.ssc_num_four);
            num5Tv=itemView.findViewById(R.id.ssc_num_five);
            timeTv=itemView.findViewById(R.id.time);
        }
    }
}
