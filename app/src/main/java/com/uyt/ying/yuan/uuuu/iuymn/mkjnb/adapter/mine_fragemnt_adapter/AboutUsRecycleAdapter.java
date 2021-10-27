package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.mine_fragemnt_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AboutUsMedol;
import java.util.ArrayList;

public class AboutUsRecycleAdapter extends RecyclerView.Adapter<AboutUsRecycleAdapter.MyHolder> implements View.OnClickListener {
    private ArrayList<AboutUsMedol> aboutUsModelList=new ArrayList<>();

    public AboutUsRecycleAdapter(ArrayList<AboutUsMedol> aboutUsModelList) {
        this.aboutUsModelList = aboutUsModelList;
    }
    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private AboutUsRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
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
    public AboutUsRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.about_us_recycle_item, viewGroup, false);
       MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsRecycleAdapter.MyHolder myHolder, int i) {
    myHolder.aboutUsText.setText(aboutUsModelList.get(i).getTheme());
    myHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return aboutUsModelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView aboutUsText;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            aboutUsText=itemView.findViewById(R.id.about_us_text);
        }
    }
}
