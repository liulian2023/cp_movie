package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.want_to_invest_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.WxPayMedol;

import java.util.ArrayList;

public class WxPayAdapter extends RecyclerView.Adapter<WxPayAdapter.MyHolder> implements View.OnClickListener
{

    ArrayList<WxPayMedol> wxPayMedolArrayList=new ArrayList<>();
    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    private RecyclerView mRecyclerView;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    public WxPayAdapter(ArrayList<WxPayMedol> wxPayMedolArrayList) {
        this.wxPayMedolArrayList = wxPayMedolArrayList;

    }

    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private WxPayAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(WxPayAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }
    @NonNull
    @Override
    public WxPayAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (i == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        }else{
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wx_pay_recycle_item,viewGroup,false);
        return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final WxPayAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            myHolder.wxPayNum.setText(Utils.getString(R.string.通道)+(position+1));
            WxPayMedol wxPayMedol = wxPayMedolArrayList.get(position);
            if(wxPayMedol.getStatus()==1){
               myHolder.WxPayCheck.setChecked(true);

            }else {
                myHolder.WxPayCheck.setChecked(false);

            }
            myHolder.wxPayName.setText(wxPayMedol.getPassagewayName());
            myHolder.itemView.setTag(position);
            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                        checked(v, (Integer) v.getTag());
                    }
                }
            });
//            if(position==0){
//                myHolder.companyCheck.performClick();
//            }
        }
    }
        private void checked(@NonNull View view, int i) {
        for (int a = 0; a< wxPayMedolArrayList.size(); a++){
            WxPayMedol wxPayMedol = wxPayMedolArrayList.get(a);
            wxPayMedol.setStatus(0);
        }
        wxPayMedolArrayList.get(i).setStatus(1);
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {

    }
    @Override
    public int getItemCount() {
        int count = (wxPayMedolArrayList == null ? 0 : wxPayMedolArrayList.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView wxPayNum;
        TextView wxPayName;
        CheckBox WxPayCheck;
        LinearLayout linearLayout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            wxPayNum=itemView.findViewById(R.id.wx_passageway_num);
            wxPayName=itemView.findViewById(R.id.wx_passageway_name);
            WxPayCheck=itemView.findViewById(R.id.wx_check);
            linearLayout=itemView.findViewById(R.id.wx_click_linear);
        }
    }
}
