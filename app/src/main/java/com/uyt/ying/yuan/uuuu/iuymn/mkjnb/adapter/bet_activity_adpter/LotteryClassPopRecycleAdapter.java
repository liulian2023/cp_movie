package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.bet_activity_adpter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.rightMenuAdapter.ClassFyAdapterTwo;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LotteryClassModel;


import java.util.ArrayList;
import java.util.List;

public class LotteryClassPopRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> /*implements View.OnClickListener*/ {
    private Context context;
    private ArrayList<LotteryClassModel> lotteryClassModelArrayList = new ArrayList<>();
    private ArrayList<LotteryClassModel> lotteryClassModelArrayListTwo =new ArrayList<>();

    public LotteryClassPopRecycleAdapter(ArrayList<LotteryClassModel> lotteryClassModelArrayList, Context context, ArrayList<LotteryClassModel> lotteryClassModelArrayListTwo) {
        this.lotteryClassModelArrayList = lotteryClassModelArrayList;
        this.context = context;
        this.lotteryClassModelArrayListTwo = lotteryClassModelArrayListTwo;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder=null;
        //根据viewType生成viewHolder
        Context context = viewGroup.getContext();
                view = LayoutInflater.from(context).inflate(R.layout.lottery_classify_recycle_one_item, viewGroup, false);
                viewHolder = new LotteryClassPopRecycleAdapter.lottetyTypeViewHodler(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        //根据条目的类型给holder中的控件填充数据
                lottetyTypeViewHodler typeViewHodler =(lottetyTypeViewHodler) viewHolder;
        LotteryClassModel lotteryClassModel = lotteryClassModelArrayList.get(position);
        List<String> idList = lotteryClassModel.getIdList();
        typeViewHodler.typeText.setText(lotteryClassModel.getTypename());
//                vh.fireId.setImageResource(lottetyArray.get(position).getImageUrl());
                Glide.with(context)
                        .load(lotteryClassModel.getImageurl())
                        .into(typeViewHodler.typeImage);
                //里层适配器
        ArrayList<LotteryClassModel> list = new ArrayList<>();
        ClassFyAdapterTwo classFyAdapterTwo = new ClassFyAdapterTwo(list, context);
        typeViewHodler.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        typeViewHodler.recyclerView.setAdapter(classFyAdapterTwo);
        for (int i = 0; i < lotteryClassModelArrayListTwo.size(); i++) {
            LotteryClassModel lotteryClassModel1 = lotteryClassModelArrayListTwo.get(i);
            if(idList.contains(lotteryClassModel1.getId())){
                list.add(lotteryClassModel1);
            }
        }
        classFyAdapterTwo.notifyDataSetChanged();
        classFyAdapterTwo.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.OnclassfyClick(view,position,list);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lotteryClassModelArrayList.size();
    }
//    @Override
//    public void onClick(View v) {
//        if(mOnItemClickListener!=null){
//            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
//        }
//    }

    public  static interface OnRecyclerViewItemClickListener{
        void OnclassfyClick(View view, int position, ArrayList<LotteryClassModel> modelList );
    }
    private LotteryClassPopRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(LotteryClassPopRecycleAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class lottetyTypeViewHodler extends RecyclerView.ViewHolder {
        TextView typeText;
        ImageView typeImage;
        RecyclerView recyclerView;
        public lottetyTypeViewHodler(View itemView) {
            super(itemView);
            typeText = (TextView) itemView.findViewById(R.id.lottery_classfy_text);
            typeImage = itemView.findViewById(R.id.lottery_classify_image);
            recyclerView = itemView.findViewById(R.id.classfy_recycle_two);
        }
    }
}
