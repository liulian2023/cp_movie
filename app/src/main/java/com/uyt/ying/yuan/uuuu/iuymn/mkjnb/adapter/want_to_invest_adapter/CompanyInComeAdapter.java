package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.want_to_invest_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.CompanyMedol;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class CompanyInComeAdapter extends RecyclerView.Adapter<CompanyInComeAdapter.MyHolder> implements View.OnClickListener
{

    ArrayList<CompanyMedol> companyMedolArrayList=new ArrayList<>();
    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    private RecyclerView mRecyclerView;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    public CompanyInComeAdapter(ArrayList<CompanyMedol> companyMedolArrayList) {
        this.companyMedolArrayList = companyMedolArrayList;

    }

    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private CompanyInComeAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(CompanyInComeAdapter.OnRecyclerViewItemClickListener listener) {
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
    public CompanyInComeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOTER) {
//            VIEW_FOOTER.setOnClickListener(this);
            return new MyHolder(VIEW_FOOTER);
        } else if (i == TYPE_HEADER) {
//            VIEW_HEADER.setOnClickListener(this);
            return new MyHolder(VIEW_HEADER);
        }else{
            View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_income_recycle_item,viewGroup,false);
        return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final CompanyInComeAdapter.MyHolder myHolder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            myHolder.passagewayNum.setText(Utils.getString(R.string.通道)+(position+1));
            CompanyMedol companyMedol = companyMedolArrayList.get(position);
            if(companyMedol.getStatus()==1){
               myHolder.companyCheck.setChecked(true);

            }else {
                myHolder.companyCheck.setChecked(false);

            }

            myHolder.passagewayName.setText(companyMedol.getPassagewayName());
            myHolder.companyCheck.setTag(position);
            myHolder.companyCheck.setOnClickListener(this);
//            if(position==0){
//                myHolder.companyCheck.performClick();
//            }
        }
    }
        private void checked(@NonNull View view, int i) {
        for (int a = 0; a< companyMedolArrayList.size(); a++){
            CompanyMedol companyMedol = companyMedolArrayList.get(a);
            companyMedol.setStatus(0);
        }
        companyMedolArrayList.get(i).setStatus(1);
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
            checked(v, (Integer) v.getTag());
        }
    }
    @Override
    public int getItemCount() {
        int count = (companyMedolArrayList == null ? 0 : companyMedolArrayList.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView passagewayNum;
        TextView passagewayName;
        CheckBox companyCheck;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            passagewayNum=itemView.findViewById(R.id.company_passageway_num);
            passagewayName=itemView.findViewById(R.id.company_passageway_name);
            companyCheck=itemView.findViewById(R.id.company_check);
        }
    }
}
