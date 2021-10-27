package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BeiJInOpenResultModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BeiJinOpenResultModel2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RaceOpenAdapter extends RecyclerView.Adapter<RaceOpenAdapter.MyHolder> {
    private Context context;
    private ArrayList<BeiJInOpenResultModel>beiJInOpenResultModels =new ArrayList<>();//外层适配器数据
    private ArrayList<BeiJinOpenResultModel2>beiJInOpenResultModel2s =new ArrayList<>();//里层适配器数据


    public RaceOpenAdapter(Context context, ArrayList<BeiJInOpenResultModel> beiJInOpenResultModels, ArrayList<BeiJinOpenResultModel2> beiJInOpenResultModel2s) {
        this.context = context;
        this.beiJInOpenResultModels = beiJInOpenResultModels;
        this.beiJInOpenResultModel2s = beiJInOpenResultModel2s;
    }

    @NonNull
    @Override
    public RaceOpenAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //外ceng适配器item视图
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.beijin_open_recycle_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceOpenAdapter.MyHolder myHolder, int position) {
        BeiJInOpenResultModel beiJInOpenResultModel = beiJInOpenResultModels.get(position);//外层recycleView数据been类
        //--------------  外层数据相关  -----------------------
        myHolder.qishu.setText(beiJInOpenResultModel.getQishu());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String shijian = beiJInOpenResultModel.getShijian();
        Date date = new Date(Long.parseLong(shijian));
        String format = simpleDateFormat.format(date);
        myHolder.shijian.setText(format);
        //-------------- 外层数据相关 -----------------------

        //-------------- 里层recycleView相关配置 ------------------------------
        RaceOpenAdapterRight adapter = new RaceOpenAdapterRight(beiJInOpenResultModel2s);//里层适配器
        myHolder.recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myHolder.recyclerView.setLayoutManager(linearLayoutManager);

        //里层适配器数据相关
        if (beiJInOpenResultModel.isGuanYa()){//冠亚/龙虎的开奖数据需要做特殊处理
            for (int i = 0; i < beiJInOpenResultModels.size(); i++) {
                //因为此处里层数据是从外层数据容器中取得,所以这里需要判断(如果数据是分开处理的,不用判断)
                //此处做判断,如果当前item的position 和 数据容器的下标相同才将数据取出(否则会把容器里的数据都遍历出来,并添加到里层recycleView中,导致里层recycleView的item数据都是一样的数据,并且是外层recycleView中所有item的数据)
                if(position==i){
                    beiJInOpenResultModel2s.clear();
                    String numList = beiJInOpenResultModel.getNumList();
                    String[] split = numList.split(",");
                    List<String> strings = Arrays.asList(split);
                    for (int j = 0; j < strings.size(); j++) {
                        beiJInOpenResultModel2s.add(new BeiJinOpenResultModel2(strings.get(j),true));
                    }
                }
        }
        }else{
            for (int i = 0; i < beiJInOpenResultModels.size(); i++) {
                if(position==i){
                    beiJInOpenResultModel2s.clear();
                    String numList = beiJInOpenResultModel.getNumList();
                    String[] split = numList.split(",");
                    List<String> strings = Arrays.asList(split);
                    for (int j = 0; j < strings.size(); j++) {
                        beiJInOpenResultModel2s.add(new BeiJinOpenResultModel2(strings.get(j),false));
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();

        //-------------- 里层recycleView相关配置 ------------------------------
    }

    @Override
    public int getItemCount() {
        return beiJInOpenResultModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView qishu;
        private TextView shijian;
        private RecyclerView recyclerView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            qishu=itemView.findViewById(R.id.qishu);
            shijian=itemView.findViewById(R.id.shijian);
            recyclerView=itemView.findViewById(R.id.beijin_open_recycle_item_recycle);
        }
    }
}
