package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.BetAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.SscModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class SscAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SscModel>sscMedolList=new ArrayList<>();
    private ArrayList<SscModel> selecterList=new ArrayList<>();

    public SscAdapter(ArrayList<SscModel> happy8MedolArrayList, ArrayList<SscModel> selecterList) {
        this.sscMedolList = happy8MedolArrayList;
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
        final SscModel sscModel = sscMedolList.get(position);
        switch (itemViewType) {
            case 0:
                 TypeViewHolder typeViewHolder = (TypeViewHolder) viewHolder;
                 typeViewHolder.typeTv.setText(sscModel.getBetType());
                break;
            case 1:
                final ItemViewHolder itemViewHolder= (ItemViewHolder) viewHolder;
                itemViewHolder.betType.setText(sscModel.getBetType());
                itemViewHolder.betRebate.setText(Utils.getString(R.string.赔 )+sscModel.getRebate());
                if(sscModel.getStatus()==1){
                    itemViewHolder.itemView.setSelected(true);
                }else{
                    itemViewHolder.itemView.setSelected(false);
                }
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!itemViewHolder.itemView.isSelected()){
//                   commonHolder.itemView.setSelected(true);
                            sscModel.setStatus(1);
                            selecterList.add(sscModel);
                        }else {
//                   commonHolder.itemView.setSelected(false);
//                   selecterList.remove(position);
                            sscModel.setStatus(0);
                            selecterList.remove(sscModel);
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
        //跟据position对应的条目返回对应的样式（Type）
//        if(position ==0 ||position ==5||position ==12||position==15||position==28||position==32||position==37) {
        if(sscMedolList.get(0).getBetType().equals(Utils.getString(R.string.总和龙虎和))){
            if(position ==0 ||position ==8||position ==13||position==18||position==23||position==28) {
                return 0;
            } else  {
                return 1;
            }
        }else if(sscMedolList.get(0).getBetType().equals(Utils.getString(R.string.第一球))){
            if (position == 0||position==11||position==22||position==33||position==44) {
                return 0;
            }else{
                return 1;
            }

        }else {
            if (position==0||position==6||position==12){
                return 0;
            }else{
                return 1;
            }
        }
    }
    @Override
    public int getItemCount() {
        return sscMedolList.size();
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
