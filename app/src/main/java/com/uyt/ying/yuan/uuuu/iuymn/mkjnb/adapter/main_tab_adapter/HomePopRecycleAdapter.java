package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.HomePopMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;

import java.util.ArrayList;

public class HomePopRecycleAdapter extends RecyclerView.Adapter<HomePopRecycleAdapter.MyHolder>/* implements View.OnClickListener*/ {
    private ArrayList<HomePopMedol>homePopMedolArrayList=new ArrayList<>();
    Context context;
    private String FirstImageUrl;
    public HomePopRecycleAdapter(ArrayList<HomePopMedol> homePopMedolArrayList,Context context) {
        this.homePopMedolArrayList = homePopMedolArrayList;
        this.context = context;
    }


    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private HomePopRecycleAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(HomePopRecycleAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public HomePopRecycleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_pop_recycle_item, viewGroup, false);
        FirstImageUrl=SharePreferencesUtil.getString(viewGroup.getContext(),"FirstImageUrl","");
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomePopRecycleAdapter.MyHolder myHolder, int position) {
        HomePopMedol homePopMedol = homePopMedolArrayList.get(position);
        if(position==0){
            myHolder.linearLayoutHide.setVisibility(View.VISIBLE);
            myHolder.redSplite.setVisibility(View.VISIBLE);
            myHolder.linearLayout.setBackgroundColor(Color.parseColor("#f2f2f2"));
        }
        myHolder.title.setText(homePopMedol.getTitle());
        WebSettings settings = myHolder.content.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
//        myHolder.content.loadData(homePopMedol.getContent(),"text/html;charset=utf-8", "utf-8");
        myHolder.content.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+homePopMedol.getContent(),"text/html", "utf-8",null);

        myHolder.titleSmall.setText(homePopMedol.getTitleSmall());
        if(homePopMedol.getImgUrl().equals(FirstImageUrl+"null")){
            myHolder.imageView.setVisibility(View.GONE);
        }
        else {
            Glide.with(context)
                    .load(homePopMedol.getImgUrl())
                    .into(myHolder.imageView);
        }
        myHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myHolder.linearLayoutHide.getVisibility()==View.GONE){
                myHolder.linearLayoutHide.setVisibility(View.VISIBLE);
                myHolder.linearLayout.setBackgroundColor(Color.parseColor("#f2f2f2"));
                myHolder.redSplite.setVisibility(View.VISIBLE);
                }
                else{
                    myHolder.linearLayoutHide.setVisibility(View.GONE);
                    myHolder.linearLayout.setBackgroundColor(Color.WHITE);
                    myHolder.redSplite.setVisibility(View.GONE);
                }
            }

        });
        myHolder.linearLayout.setTag(position);
    }
    @Override
    public int getItemCount() {
        return homePopMedolArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView titleSmall;
        WebView content;
        ImageView imageView;
        LinearLayout linearLayoutHide;
        LinearLayout linearLayout;
        TextView redSplite;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.home_pop_title);
            titleSmall= itemView.findViewById(R.id.home_tiele_small);
            content= itemView.findViewById(R.id.home_pop_content);
            imageView= itemView.findViewById(R.id.home_pop_image);
            linearLayout= itemView.findViewById(R.id.show_more);
            linearLayoutHide= itemView.findViewById(R.id.home_pop_more_linear);
            redSplite= itemView.findViewById(R.id.red_splite);
        }
    }
}
