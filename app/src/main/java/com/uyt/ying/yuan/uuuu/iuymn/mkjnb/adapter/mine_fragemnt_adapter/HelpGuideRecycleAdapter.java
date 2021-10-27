package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HelpGuideMedol;

import java.util.ArrayList;

public class HelpGuideRecycleAdapter extends RecyclerView.Adapter<HelpGuideRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<HelpGuideMedol> helpGuideMedolArrayList=new ArrayList<>();

    public HelpGuideRecycleAdapter(ArrayList<HelpGuideMedol> helpGuideMedolArrayList) {
        this.helpGuideMedolArrayList = helpGuideMedolArrayList;
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    }
    private HelpGuideRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
    @NonNull
    @Override
    public HelpGuideRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.help_guide_recycle_item, viewGroup, false);
       MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull HelpGuideRecycleAdapter.MyHolder myHolder, int i) {
    myHolder.helpText.setText(helpGuideMedolArrayList.get(i).getTheme());
    myHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return helpGuideMedolArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView helpText;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            helpText=itemView.findViewById(R.id.help_text);
        }
    }
}
