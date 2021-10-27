package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.integral_details_fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;


public class ConsumptionInteralFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consumption_interal, container, false);
        return view;
    }



    public static ConsumptionInteralFragment newInstance(int position){
        Bundle bundle = new Bundle();
        ConsumptionInteralFragment consumptionInteralFragment = new ConsumptionInteralFragment();
        bundle.putInt("position", position);
        consumptionInteralFragment.setArguments(bundle);
        return consumptionInteralFragment;
    }
}
