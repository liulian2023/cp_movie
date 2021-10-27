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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentChessModel;

import java.util.ArrayList;

/**
 * 全部彩票adapter
 */

public class ShoppingContentChessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<ShoppingContentChessModel.ClassfyModel> typeOneList =new ArrayList<>();
    private ArrayList<ShoppingContentChessModel.ChessEntity.DataBean> typeTwoList=new ArrayList<>();

    Context context;
    public ShoppingContentChessAdapter(ArrayList<ShoppingContentChessModel.ClassfyModel> typeOneList, Context context, ArrayList<ShoppingContentChessModel.ChessEntity.DataBean> typeTwoList) {
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
        ArrayList< ShoppingContentChessModel.ChessEntity.DataBean> gameList=new ArrayList<>();
        for (int i = 0; i <typeTwoList.size() ; i++) {
            ShoppingContentChessModel.ChessEntity.DataBean dataBean = typeTwoList.get(i);
                int game = dataBean.getGame();
                if(classfyModel.getGame()==game){
                    gameList.add(dataBean);
                }

        }
        ShoppingCoontentTypeTwoAdapter shoppingCoontentTypeTwoAdapter = new ShoppingCoontentTypeTwoAdapter(gameList, context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        vh.recyclerView.setLayoutManager(gridLayoutManager);
        vh.recyclerView.setAdapter(shoppingCoontentTypeTwoAdapter);
        shoppingCoontentTypeTwoAdapter.notifyDataSetChanged();
        shoppingCoontentTypeTwoAdapter.setItemClickListener(new ShoppingCoontentTypeTwoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,gameList,position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return typeOneList.size();
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, ArrayList<ShoppingContentChessModel.ChessEntity.DataBean> chessList, int position);
    }
    private ShoppingContentChessAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(ShoppingContentChessAdapter.OnRecyclerViewItemClickListener listener) {
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
