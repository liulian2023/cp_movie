package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Happy10Model;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class Happy10Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Happy10Model>happy10MedolList=new ArrayList<>();
    private ArrayList<Happy10Model> selecterList=new ArrayList<>();

    public Happy10Adapter(ArrayList<Happy10Model> happy8MedolArrayList, ArrayList<Happy10Model> selecterList) {
        this.happy10MedolList = happy8MedolArrayList;
        this.selecterList = selecterList;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder=null;
        Context context = viewGroup.getContext();
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.happy8_recycle_item_one, viewGroup, false);
                viewHolder = new TypeViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.happy8_recycle_item_two, viewGroup, false);
                viewHolder = new ItemViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        int itemViewType = getItemViewType(position);
        final Happy10Model happy8Medol = happy10MedolList.get(position);
        switch (itemViewType) {
            case 0:
                 TypeViewHolder typeViewHolder = (TypeViewHolder) viewHolder;
                 typeViewHolder.typeTv.setText(happy8Medol.getBetType());
                break;
            case 1:
                final ItemViewHolder itemViewHolder= (ItemViewHolder) viewHolder;
                itemViewHolder.betType.setText(happy8Medol.getBetType());
                itemViewHolder.betRebate.setText(Utils.getString(R.string.??? )+happy8Medol.getRebate());
                if(happy8Medol.getStatus()==1){
                    itemViewHolder.itemView.setSelected(true);
                }else{
                    itemViewHolder.itemView.setSelected(false);
                }
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selecterList.remove(happy8Medol);
                        if(happy8Medol.getStatus()==0){
                            happy8Medol.setStatus(1);
                                selecterList.add(happy8Medol);
                        }else {
                            happy8Medol.setStatus(0);
                        }

                        if(mOnItemClickListener!=null){
                            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                        }
                        notifyDataSetChanged();
                    }
                });
            itemViewHolder.itemView.setTag(position);

        }
    }
    @Override
    public int getItemViewType(int position) {
        //??????position???????????????????????????????????????Type???
//        if(position ==0 ||position ==5||position ==12||position==15||position==28||position==32||position==37) {
        if(happy10MedolList.get(0).getGroupname().equals(Utils.getString(R.string.??????)+"-"+Utils.getString(R.string.?????????))){
            if(position ==0 ||position ==11||position ==22||position==33||position==44||position==53||position==62||position==71) {
                return 0;
            } else   {
                return 1;
            }
        }else {
            if (position == 0) {
                return 0;
            }else{
                return 1;
            }

        }
    }
    @Override
    public int getItemCount() {
        return happy10MedolList.size();
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
        private TextView typeTv;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            typeTv=itemView.findViewById(R.id.type_item_text);
        }
    }
    public class   ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView betType;
        private TextView betRebate;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            betType=itemView.findViewById(R.id.bet_type);
            betRebate=itemView.findViewById(R.id.bet_rebate);
        }
    }
}
