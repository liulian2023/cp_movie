package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentRecommendModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentChessModel;

import java.util.ArrayList;



public class ShoppingContentRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<ShoppingContentChessModel.ClassfyModel> typeOneList =new ArrayList<>();
    private ArrayList<ShoppingContentRecommendModel> typeTwoList=new ArrayList<>();

    Context context;
    public ShoppingContentRecommendAdapter(ArrayList<ShoppingContentChessModel.ClassfyModel> typeOneList, Context context, ArrayList<ShoppingContentRecommendModel> typeTwoList) {
        this.typeOneList = typeOneList;
        this.context = context;
        this.typeTwoList = typeTwoList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        RecyclerView.ViewHolder viewHolder = null;
        Context context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.all_tollery_recycleview_one, parent, false);
        viewHolder = new lottetyTypeViewHodler(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        lottetyTypeViewHodler vh = (lottetyTypeViewHodler) holder;
        ShoppingContentChessModel.ClassfyModel classfyModel = typeOneList.get(position);
        vh.type.setText(classfyModel.getClassificationName());
        vh.fireId.setImageResource(R.drawable.lottery_type_icon);
        ArrayList<ShoppingContentRecommendModel> historyModelList = new ArrayList<>();
        ArrayList<ShoppingContentRecommendModel> recommendModelList = new ArrayList<>();
        ShoppingCoontentAllTypeTwoAdapter shoppingCoontentAllTypeTwoAdapter=null;
        if(classfyModel.isHistory()){
            for (int i = 0; i < typeTwoList.size(); i++) {
                ShoppingContentRecommendModel shoppingContentRecommendModel = typeTwoList.get(i);
                if(shoppingContentRecommendModel.isHistory()){
                    historyModelList.add(shoppingContentRecommendModel);
                }
            }
            shoppingCoontentAllTypeTwoAdapter    = new ShoppingCoontentAllTypeTwoAdapter(historyModelList, context);
            shoppingCoontentAllTypeTwoAdapter.setItemClickLintener(new ShoppingCoontentAllTypeTwoAdapter.ItemClickLintener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(view,historyModelList,position);
                    }
                }
            });

        }else {
            for (int i = 0; i < typeTwoList.size(); i++) {
                ShoppingContentRecommendModel shoppingContentRecommendModel = typeTwoList.get(i);
                if(!shoppingContentRecommendModel.isHistory()){
                    recommendModelList.add(shoppingContentRecommendModel);
                }
            }
            shoppingCoontentAllTypeTwoAdapter     = new ShoppingCoontentAllTypeTwoAdapter(recommendModelList, context);

            shoppingCoontentAllTypeTwoAdapter.setItemClickLintener(new ShoppingCoontentAllTypeTwoAdapter.ItemClickLintener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(view,recommendModelList,position);
                    }
                }
            });
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        vh.recyclerView.setLayoutManager(gridLayoutManager);
        vh.recyclerView.setAdapter(shoppingCoontentAllTypeTwoAdapter);
        shoppingCoontentAllTypeTwoAdapter.notifyDataSetChanged();


    }
    @Override
    public int getItemCount() {
        return typeOneList.size();
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, ArrayList<ShoppingContentRecommendModel> shoppingContentRecommendModelArrayList, int position);
    }
    private ShoppingContentRecommendAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(ShoppingContentRecommendAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public class lottetyTypeViewHodler extends RecyclerView.ViewHolder {
        TextView type;
        ImageView fireId;
        RecyclerView recyclerView;
        public lottetyTypeViewHodler(View itemView) {
            super(itemView);
            type =  itemView.findViewById(R.id.lottrty_type);
            fireId = itemView.findViewById(R.id.fire);
            recyclerView=  itemView.findViewById(R.id.all_lottery_recycle_two);
        }
    }


}
