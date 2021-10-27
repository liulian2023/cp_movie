/*
 * Copyright (c) 2019.  xxxx
 */

package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.caizhong;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RaceLottery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RaceOpenresultAdapter extends RecyclerView.Adapter<RaceOpenresultAdapter.VH>{


    List<RaceLottery.RaceLotteryBean> list;
    int[] qius;//球点数
    Context context;

    public RaceOpenresultAdapter(Context context, List<RaceLottery.RaceLotteryBean> list){

        this.list = list;
        this.context = context;
        qius = Const.getRaceqiuArray(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.race_openresult_item, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        RaceLottery.RaceLotteryBean bean = list.get(position);
        holder.qishu.setText(bean.getLotteryqishu());
//        holder.time.setText(DateUtil.stampToCountDown(String.valueOf(bean.getLotterytime())));
        long lotterytime = bean.getLotterytime();
        Date date = new Date(lotterytime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String format = simpleDateFormat.format(date);
        holder.time.setText(format);
        List<String> list = new ArrayList<String>();
        List<Integer> kjList = new ArrayList<>();
        list = Arrays.asList(bean.getLotteryNo().split(","));
        for (String ss : list) {
            kjList.add(qius[Integer.parseInt(ss) - 1]);
        }
        holder.race_openresult_iv1.setImageResource(kjList.get(0));
        holder.race_openresult_iv2.setImageResource(kjList.get(1));
        holder.race_openresult_iv3.setImageResource(kjList.get(2));
        holder.race_openresult_iv4.setImageResource(kjList.get(3));
        holder.race_openresult_iv5.setImageResource(kjList.get(4));
        holder.race_openresult_iv6.setImageResource(kjList.get(5));
        holder.race_openresult_iv7.setImageResource(kjList.get(6));
        holder.race_openresult_iv8.setImageResource(kjList.get(7));
        holder.race_openresult_iv9.setImageResource(kjList.get(8));
        holder.race_openresult_iv10.setImageResource(kjList.get(9));

        holder.race_hezhi_one.setText(String.valueOf(bean.getSum()));
        holder.race_hezhi_two.setText(bean.getMarkdx());
        holder.race_hezhi_three.setText(bean.getMarkds());
        if (bean.getMarkdx().equals(Utils.getString(R.string.大))){
            holder.race_hezhi_two.setTextColor(ContextCompat.getColor(context,R.color.racepink));
        }else {
            holder.race_hezhi_two.setTextColor(ContextCompat.getColor(context,R.color.raceblue));
        }
        if (bean.getMarkds().equals(Utils.getString(R.string.双))){
            holder.race_hezhi_three.setTextColor(ContextCompat.getColor(context,R.color.racepink));
        }else {
            holder.race_hezhi_three.setTextColor(ContextCompat.getColor(context,R.color.raceblue));
        }


    }

    @Override
    public int getItemCount() {
        if (list!=null&&list.size()>0){
            return list.size();
        }else {
            return 0;
        }
    }

    public  class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.qishu)
        TextView qishu;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.race_openresult_iv1)
        ImageView race_openresult_iv1;
        @BindView(R.id.race_openresult_iv2)
        ImageView race_openresult_iv2;
        @BindView(R.id.race_openresult_iv3)
        ImageView race_openresult_iv3;
        @BindView(R.id.race_openresult_iv4)
        ImageView race_openresult_iv4;
        @BindView(R.id.race_openresult_iv5)
        ImageView race_openresult_iv5;
        @BindView(R.id.race_openresult_iv6)
        ImageView race_openresult_iv6;
        @BindView(R.id.race_openresult_iv7)
        ImageView race_openresult_iv7;
        @BindView(R.id.race_openresult_iv8)
        ImageView race_openresult_iv8;
        @BindView(R.id.race_openresult_iv9)
        ImageView race_openresult_iv9;
        @BindView(R.id.race_openresult_iv10)
        ImageView race_openresult_iv10;
        @BindView(R.id.race_hezhi_one)
        TextView race_hezhi_one;
        @BindView(R.id.race_hezhi_two)
        TextView race_hezhi_two;
        @BindView(R.id.race_hezhi_three)
        TextView race_hezhi_three;


        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
