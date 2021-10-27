package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.agent_center_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShowByLotteryType;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShowByLotteryRecycleAdapter extends RecyclerView.Adapter<ShowByLotteryRecycleAdapter.MyHolder> {
    ArrayList<ShowByLotteryType> showByLotteryTypeArrayList =new ArrayList<>();
    public ShowByLotteryRecycleAdapter(ArrayList<ShowByLotteryType> showByLotteryTypeArrayList) {
        this.showByLotteryTypeArrayList = showByLotteryTypeArrayList;
    }

    @NonNull
    @Override
    public ShowByLotteryRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_by_lottery_type_recycle_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowByLotteryRecycleAdapter.MyHolder myHolder, int i) {
        myHolder.name.setText(showByLotteryTypeArrayList.get(i).getName());
        myHolder.xiazhu.setText(showByLotteryTypeArrayList.get(i).getXiazhu());
        myHolder.zhongjiang.setText(showByLotteryTypeArrayList.get(i).getZhongjiang());
        String yingkui = showByLotteryTypeArrayList.get(i).getYingkui();
        BigDecimal bigDecimal = new BigDecimal(yingkui);
        myHolder.yingkui.setText(yingkui);
    }

    @Override
    public int getItemCount() {
        return showByLotteryTypeArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView xiazhu;
        TextView zhongjiang;
        TextView yingkui;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.lottery_name);
            xiazhu =itemView.findViewById(R.id.xiazhu);
            zhongjiang =itemView.findViewById(R.id.zhongjiang);
            yingkui =itemView.findViewById(R.id.yingkui);
        }
    }
}
