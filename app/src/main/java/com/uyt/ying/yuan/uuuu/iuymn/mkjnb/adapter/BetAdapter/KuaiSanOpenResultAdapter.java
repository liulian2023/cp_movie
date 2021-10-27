package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;


import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.KuaiSanTodayResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.MyCornerTextView;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonHolder;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class KuaiSanOpenResultAdapter extends CommonAdapter<KuaiSanOpenResultAdapter.MyHolder,KuaiSanTodayResultModel> {
   int[] imgs = new int[]{R.drawable.shaizi001,R.drawable.shaizi002,R.drawable.shaizi003,R.drawable.shaizi004,R.drawable.shaizi005,R.drawable.shaizi006};
    public KuaiSanOpenResultAdapter(ArrayList<KuaiSanTodayResultModel> list) {
        super(list);
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        KuaiSanTodayResultModel itemModel = getItemModel(position);
        String qishu = itemModel.getQishu();
        String lotteryNo = itemModel.getLotteryNo();
        commonHolder.lotteryQiShu.setText(qishu);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
       commonHolder.time.setText(simpleDateFormat.format(new Date( Long.parseLong(itemModel.getTime()))));

        String[] split = lotteryNo.split(",");
        List<String> strings = Arrays.asList(split);

        String numOne = strings.get(0);
        String numTwo = strings.get(1);
        String numThree = strings.get(2);
        int intOne = Integer.parseInt(numOne);
        int intTwo = Integer.parseInt(numTwo);
        int intThree = Integer.parseInt(numThree);
        int intHezhi = intOne + intTwo + intThree;
            commonHolder.diceOne.setImageResource(imgs[Integer.parseInt(numOne)-1]);
            commonHolder.diceTwo.setImageResource(imgs[Integer.parseInt(numTwo)-1]);
            commonHolder.diceThree.setImageResource(imgs[Integer.parseInt(numThree)-1]);

        commonHolder.hezhi.setText(intHezhi+"");
        String remark = itemModel.getRemark();
        if(remark.equals(Utils.getString(R.string.大))){
            commonHolder.daxiao.setfilColor(Color.parseColor("#3174ff"));
        }else if(remark.equals(Utils.getString(R.string.豹子))){
            commonHolder.daxiao.setfilColor(Color.parseColor("#3174ff"));
            //开奖结果是豹子时,需要显示两个字(待处理)
        }
        else {
            commonHolder.daxiao.setfilColor(Color.parseColor("#ff7604"));
        }
        commonHolder.daxiao.setText(remark);
        if(intHezhi%2==0){
            commonHolder.xingtai.setText(Utils.getString(R.string.双));
            commonHolder.xingtai.setfilColor(Color.parseColor("#ff7604"));
        }else{
            commonHolder.xingtai.setText(Utils.getString(R.string.单));
            commonHolder.xingtai.setfilColor(Color.parseColor("#3174ff"));
        }
        commonHolder.xingtai.setCornerSize(10);
    }
    @Override
    public int getLayOutRes() {
        return R.layout.kuaisan_today_result_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        TextView lotteryQiShu;
        ImageView diceOne;
        ImageView diceTwo;
        ImageView diceThree;
        TextView hezhi;
        MyCornerTextView daxiao;
        MyCornerTextView xingtai;
        TextView time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lotteryQiShu=itemView.findViewById(R.id.qishu);
            diceOne=itemView.findViewById(R.id.dice_1);
            diceTwo=itemView.findViewById(R.id.dice_2);
            diceThree=itemView.findViewById(R.id.dice_3);
            hezhi=itemView.findViewById(R.id.hezhi);
            daxiao=itemView.findViewById(R.id.daxiao);
            xingtai=itemView.findViewById(R.id.xingtai);
            time=itemView.findViewById(R.id.time);
        }
    }
}
