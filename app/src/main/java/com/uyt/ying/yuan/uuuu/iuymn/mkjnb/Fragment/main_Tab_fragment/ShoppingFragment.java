package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment.AllLotteryFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment.GouCaiDaTinFragment;
import com.uyt.ying.yuan.R;
//import com.example.administrator.aaa.adapter.agent_journaling_adapter.AllLotteryAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.Lottety;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import java.util.ArrayList;

public class ShoppingFragment extends BaseFragment implements View.OnClickListener {
//    private AllLotteryAdapter lottetyAdapter;
    private ArrayList<Lottety> lottetyList  = new ArrayList<>();
    private ImageView goucaidatin;
    private ImageView allTottety;
    private GouCaiDaTinFragment gouCaiDaTinFragment;
    private AllLotteryFragment allLotteryFragment;
    private FragmentManager fragmentManager;
//    private RecyclerView mRecyclerView;


    /*
      bundle.putBoolean("forAllTollery",forAllTollery);//跳转购彩大厅默认选中全部彩票(彩票分类)
                bundle.putBoolean("fromBetFragment",forGouCai);//跳转购彩大厅默认选中购彩大厅(倒计时)
     */

    public static ShoppingFragment newInstance() {
        Bundle args = new Bundle();
        ShoppingFragment fragment = new ShoppingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ShoppingFragment newInstance1(boolean forAllLollery,boolean forGouCai){
        ShoppingFragment shoppingFragment = new ShoppingFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("forAllLollery",forAllLollery);
        bundle.putBoolean("forGouCai",forGouCai);
        shoppingFragment.setArguments(bundle);
        return  shoppingFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_buy, container, false);

//            ActionBarUtil.initMainActionbar(getActivity(),view.findViewById(R.id.shopping_action_bar_linear),R.color.action_bar_color);


        goucaidatin =view.findViewById(R.id.goucaidatin);
        allTottety = view.findViewById(R.id.all_tottety);
        goucaidatin.setOnClickListener(this);
        allTottety.setOnClickListener(this);
        boolean forAllLollery = getArguments().getBoolean("forAllLollery");
        boolean forGouCai = getArguments().getBoolean("forGouCai");
        if(forAllLollery){
            allTottety.performClick();//模拟点击
        }
        else if(forGouCai){
             goucaidatin.performClick();//模拟点击
         }

         else {
            goucaidatin.performClick();//模拟点击
        }
        ProgressDialogUtil.darkenBackground(getActivity(),1f);
         return view;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(getContext(),R.color.action_bar_color));
        StatusBarUtil.setDarkMode(getActivity());
    }


    private void hideAllFragment(FragmentTransaction transaction) {
        if(gouCaiDaTinFragment!= null){transaction.hide(gouCaiDaTinFragment);}
        if(allLotteryFragment != null){transaction.hide(allLotteryFragment);}
    }
    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()){
            case R.id.goucaidatin:
                goucaidatin.setBackgroundColor(Color.parseColor("#be090c"));
                allTottety.setBackgroundColor(Color.parseColor("#e6351a"));
                if(gouCaiDaTinFragment == null){
                    gouCaiDaTinFragment = new GouCaiDaTinFragment();
                    transaction.add(R.id.shopping_content,gouCaiDaTinFragment);
                }else{
                    transaction.show(gouCaiDaTinFragment);
                }
                break;
            case  R.id.all_tottety:
                allTottety.setBackgroundColor(Color.parseColor("#be090c"));
                goucaidatin.setBackgroundColor(Color.parseColor("#e6351a"));
                if(allLotteryFragment == null){
                    allLotteryFragment = new AllLotteryFragment();
                    transaction.add(R.id.shopping_content, allLotteryFragment);
                }else{
                    transaction.show(allLotteryFragment);
                }
                break;
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
