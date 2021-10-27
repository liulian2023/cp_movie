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
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MarksixLottery;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarksixOpenresultAdapter extends RecyclerView.Adapter<MarksixOpenresultAdapter.VH>{


    List<MarksixLottery.marksixLotteryBean> list;
    Context context;

    public MarksixOpenresultAdapter(Context context, List<MarksixLottery.marksixLotteryBean> list){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.marksix_openresult_item, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MarksixLottery.marksixLotteryBean bean = list.get(position);

        holder.qishu.setText(bean.getLotteryqishu());
        holder.marksix_openresult_time.setText(DateUtil.stampToDate(String.valueOf(bean.getLotterytime())));

        List<String> list = new ArrayList<String>();
        list = Arrays.asList(bean.getLotteryNo().split(","));
        List<String> bkg_list = new ArrayList<>();
        bkg_list = Arrays.asList(bean.getColor().split(","));
        List<String> sxList = new ArrayList<>();
        sxList = Arrays.asList(bean.getAnimal().split(","));
       /* kjList.clear();
        for (String kjbean : list) {
            kjList.add(Integer.parseInt(kjbean));
        }*/
        List<Integer> bkgcolor_list = new ArrayList<>();

        for (String bkgbean : bkg_list) {
            switch (bkgbean) {
                case "red":
                    bkgcolor_list.add(R.drawable.shape_circle_red);
                    break;
                case "blue":
                    bkgcolor_list.add(R.drawable.shape_circle_blue);
                    break;
                case "green":
                    bkgcolor_list.add(R.drawable.shape_circle_green);
                    break;
            }

        }
        holder.marksix_openresult_no_tv1.setText(list.get(0));
        holder.marksix_openresult_no_tv1.setBackground(ContextCompat.getDrawable(context,bkgcolor_list.get(0)));
        holder.marksix_openresult_no_tv2.setText(list.get(1));
        holder.marksix_openresult_no_tv2.setBackground(ContextCompat.getDrawable(context,bkgcolor_list.get(1)));
        holder.marksix_openresult_no_tv3.setText(list.get(2));
        holder.marksix_openresult_no_tv3.setBackground(ContextCompat.getDrawable(context,bkgcolor_list.get(2)));
        holder.marksix_openresult_no_tv4.setText(list.get(3));
        holder.marksix_openresult_no_tv4.setBackground(ContextCompat.getDrawable(context,bkgcolor_list.get(3)));
        holder.marksix_openresult_no_tv5.setText(list.get(4));
        holder.marksix_openresult_no_tv5.setBackground(ContextCompat.getDrawable(context,bkgcolor_list.get(4)));
        holder.marksix_openresult_no_tv6.setText(list.get(5));
        holder.marksix_openresult_no_tv6.setBackground(ContextCompat.getDrawable(context,bkgcolor_list.get(5)));
        holder.marksix_openresult_no_tv7.setText(list.get(6));
        holder.marksix_openresult_no_tv7.setBackground(ContextCompat.getDrawable(context,bkgcolor_list.get(6)));

        holder.marksix_openresult_sx_tv1.setText(sxList.get(0));
        holder.marksix_openresult_sx_tv2.setText(sxList.get(1));
        holder.marksix_openresult_sx_tv3.setText(sxList.get(2));
        holder.marksix_openresult_sx_tv4.setText(sxList.get(3));
        holder.marksix_openresult_sx_tv5.setText(sxList.get(4));
        holder.marksix_openresult_sx_tv6.setText(sxList.get(5));
        holder.marksix_openresult_sx_tv7.setText(sxList.get(6));


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
        @BindView(R.id.marksix_openresult_time)
        TextView marksix_openresult_time;
        @BindView(R.id.marksix_openresult_no_tv1)
        TextView marksix_openresult_no_tv1;
        @BindView(R.id.marksix_openresult_sx_tv1)
        TextView marksix_openresult_sx_tv1;
        @BindView(R.id.marksix_openresult_no_tv2)
        TextView marksix_openresult_no_tv2;
        @BindView(R.id.marksix_openresult_sx_tv2)
        TextView marksix_openresult_sx_tv2;
        @BindView(R.id.marksix_openresult_no_tv3)
        TextView marksix_openresult_no_tv3;
        @BindView(R.id.marksix_openresult_sx_tv3)
        TextView marksix_openresult_sx_tv3;
        @BindView(R.id.marksix_openresult_no_tv4)
        TextView marksix_openresult_no_tv4;
        @BindView(R.id.marksix_openresult_sx_tv4)
        TextView marksix_openresult_sx_tv4;
        @BindView(R.id.marksix_openresult_no_tv5)
        TextView marksix_openresult_no_tv5;
        @BindView(R.id.marksix_openresult_sx_tv5)
        TextView marksix_openresult_sx_tv5;
        @BindView(R.id.marksix_openresult_no_tv6)
        TextView marksix_openresult_no_tv6;
        @BindView(R.id.marksix_openresult_sx_tv6)
        TextView marksix_openresult_sx_tv6;
        @BindView(R.id.marksix_openresult_no_tv7)
        TextView marksix_openresult_no_tv7;
        @BindView(R.id.marksix_openresult_sx_tv7)
        TextView marksix_openresult_sx_tv7;




        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
