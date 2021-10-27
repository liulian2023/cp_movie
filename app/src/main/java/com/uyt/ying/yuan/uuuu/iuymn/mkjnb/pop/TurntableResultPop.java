package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.TurntableResultAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.TurntableResultModel;

import java.util.ArrayList;

public class TurntableResultPop extends BasePopupWindow2 {
    Button i_know_button;
    LinearLayout one_linear;
    ImageView one_iv;
    TextView one_remark_tv;
    RecyclerView turntable_result_recycler;
    TurntableResultAdapter turntableResultAdapter;
    ArrayList<TurntableResultModel> turntableResultModelArrayList = new ArrayList<>();
    public TurntableResultPop(Context context, boolean focusable, ArrayList<TurntableResultModel> turntableResultModelArrayList) {
        super(context, focusable);
        this. turntableResultModelArrayList = turntableResultModelArrayList;
        initData();
    }

    private void initData() {
            if(turntableResultModelArrayList.size()==1){
                one_linear.setVisibility(View.VISIBLE);
                turntable_result_recycler.setVisibility(View.GONE);

            }else {
                one_linear.setVisibility(View.INVISIBLE);
                turntable_result_recycler.setVisibility(View.VISIBLE);

            }

        TurntableResultModel turntableResultModel = turntableResultModelArrayList.get(0);
        one_remark_tv.setText(turntableResultModel.getRemark().replace(" ",""));
        one_iv.setImageResource(turntableResultModel.getDrawableId());

        turntableResultAdapter = new TurntableResultAdapter(R.layout.turntable_result_recycler_item,turntableResultModelArrayList);
        turntable_result_recycler.setLayoutManager(new GridLayoutManager(mContext,5));
        turntable_result_recycler.setAdapter(turntableResultAdapter);
        turntableResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.turntable_result_pop_layout,null);
        turntable_result_recycler = rootView.findViewById(R.id.turntable_result_recycler);
        i_know_button = rootView.findViewById(R.id.i_know_button);
        one_linear = rootView.findViewById(R.id.one_linear);
        one_remark_tv = rootView.findViewById(R.id.one_remark_tv);
        one_iv = rootView.findViewById(R.id.one_iv);
        i_know_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurntableResultPop.this.dismiss();
            }
        });

    }
}
