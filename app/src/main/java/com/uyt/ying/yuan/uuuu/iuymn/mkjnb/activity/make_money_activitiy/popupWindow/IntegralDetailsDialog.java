package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.popupWindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.make_money_activitiy.integral_details_fragment.TotalCommisionFragment;

import java.util.ArrayList;

public class IntegralDetailsDialog extends DialogFragment implements XTabLayout.OnTabSelectedListener {
    private View view;
    private TextView knowTv;


    XTabLayout xTabLayout;
    ViewPager viewPager;
    ViewGroup childLinear;
    ArrayList<String> titleList = new ArrayList<>();
    IntegraDetailsTabAdapter integraDetailsTabAdapter;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.integral_details_pop, null);
        initView();
        initTab();

        return view;
    }





    @Override
    public void onStart()
    {
        super.onStart();
        Window dialogWindow = getDialog().getWindow();
        if (dialogWindow != null) {
            dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
            dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            lp.windowAnimations = android.R.style.Animation_InputMethod;
            dialogWindow.setAttributes(lp);
        }
    }


    private void initTab() {
        titleList.add(Utils.getString(R.string.累计积分));
        titleList.add(Utils.getString(R.string.消费积分));
        integraDetailsTabAdapter = new IntegraDetailsTabAdapter(getChildFragmentManager(),titleList,getContext());
        viewPager.setAdapter(integraDetailsTabAdapter);
        xTabLayout.setupWithViewPager(viewPager);
        xTabLayout.setClickable(false);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = xTabLayout.getTabAt(i);
            tabAt.setCustomView(integraDetailsTabAdapter.getTabView(i));
            if(i==0){
                TextView textView= tabAt.getCustomView().findViewById(R.id.home_tab_title_tv);
                textView.setTextColor(Color.parseColor("#F21818"));
                textView.setTextSize(17);
            }
        }
      xTabLayout.setOnTabSelectedListener(this);
    }


    private void initView() {
        knowTv= view.findViewById(R.id.i_know_tv);
        xTabLayout=view.findViewById(R.id.integral_details_tab);
        childLinear =view.findViewById(R.id.child_linear);
        viewPager = view.findViewById(R.id.integral_details_viewpager);
        knowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntegralDetailsDialog.this.dismiss();
            }
        });
    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(17);
        textView.setTextColor(Color.parseColor("#F21818"));
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(12);
        textView.setTextColor(Color.parseColor("#666666"));
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }

    static class IntegraDetailsTabAdapter extends FragmentPagerAdapter {
        private ArrayList<String> titleList;
        private Context context;

        public IntegraDetailsTabAdapter(@NonNull FragmentManager fm,  ArrayList<String> titleList, Context context) {
            super(fm);
            this.titleList = titleList;
            this.context = context;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
/*            if(position==0){
                return TotalCommisionFragment.newInstance(position);
            }else if(position==1){
                return ConsumptionInteralFragment.newInstance(position);
            }
            return null;*/
            return TotalCommisionFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
        //自定义tab item布局
        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.home_tab_view_layout, null);
            TextView tv = v.findViewById(R.id.home_tab_title_tv);
            tv.setText(titleList.get(position));
            return v;
        }
    }
}
