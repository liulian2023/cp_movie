package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveRankFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseDialogFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveRankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class LiveRankDialogFragment extends BaseDialogFragment {
    TabLayout live_rank_tab;
    TextView in_rank_tv;
    ImageView title_iv;
    TextView nick_name_tv;
    ImageView grade_iv;
    TextView gift_amount_tv;
    TextView rank_num_tv;
    ViewPager live_rank_viewpager;
    ConstraintLayout bottom_constraintLayout;
    ArrayList<String>titleList = new ArrayList<>();
    liveRankTabAdapter liveRankTabAdapter;
//    LiveFragment liveFragment;
    private View rootView;
    String anchorAccount;
/*
    public LiveRankDialogFragment(LiveFragment liveFragment) {
        this.liveFragment = liveFragment;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                getDialog(). getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                fullScreen( getDialog().getWindow());
            }
        });
        anchorAccount = getArguments().getString("anchorAccount");
        rootView = inflater.inflate(R.layout.live_rank_pop_layout, container, false);
        initView();
        initTab();
        return rootView;
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
            lp.height = (int) (0.6* Utils.intgetWinndowHeight((Activity) getContext()));
            lp.gravity = Gravity.BOTTOM;
            lp.windowAnimations = android.R.style.Animation_InputMethod;
            dialogWindow.setAttributes(lp);
        }
    }

    public  void fullScreen(Window window) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
/*        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;*/
        int uiOptions =
                 View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility(uiOptions);
    }
    public void  initMineInfo(boolean isInRank,int position, LiveRankEntity.ListBean listBean){

            bottom_constraintLayout.setVisibility(View.VISIBLE);
            if(isInRank){
           /*
           已上榜 设置排名
             */
                if(position==0){
                    rank_num_tv.setBackgroundResource(R.drawable.rank_1);
                    rank_num_tv.setText("");
                }else if(position==1){
                    rank_num_tv.setBackgroundResource(R.drawable.rank_2);
                    rank_num_tv.setText("");
                }else if(position==2){
                    rank_num_tv.setBackgroundResource(R.drawable.rank_3);
                    rank_num_tv.setText("");
                }else {
                    rank_num_tv.setText(position+1+"");
                    rank_num_tv.setBackground(null);
                }
                rank_num_tv.setTextSize(14);
            }else {
                rank_num_tv.setText(Utils.getString(R.string.未上榜));
                rank_num_tv.setTextSize(9);
                rank_num_tv.setBackground(null);
            }
            if(listBean!=null){
                GlideLoadViewUtil.FLoadTitleView(this, Utils.checkImageUrl(listBean.getImage()),title_iv);
                nick_name_tv.setText(listBean.getUserNickName());
                gift_amount_tv.setText(listBean.getAnchorGift());

//                grade_iv.setImageResource(getLevelDrawable(Integer.parseInt(listBean.getPointGrade())));
                GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(Integer.parseInt(listBean.getPointGrade()))),grade_iv);
            }
    }

    private void initView() {
        live_rank_tab= rootView.findViewById(R.id.live_rank_tab);
        in_rank_tv= rootView.findViewById(R.id.rank_num_tv);
        title_iv= rootView.findViewById(R.id.title_iv);
        nick_name_tv= rootView.findViewById(R.id.nick_name_tv);
        grade_iv= rootView.findViewById(R.id.grade_iv);
        gift_amount_tv= rootView.findViewById(R.id.gift_amount_tv);
        live_rank_viewpager= rootView.findViewById(R.id.live_rank_viewpager);
        bottom_constraintLayout= rootView.findViewById(R.id.bottom_constraintLayout);
        rank_num_tv= rootView.findViewById(R.id.rank_num_tv);
    }


    private void initTab() {
        titleList.add(Utils.getString(R.string.日榜));
        titleList.add(Utils.getString(R.string.周榜));
        titleList.add(Utils.getString(R.string.月榜));
        for (int i = 0; i < titleList.size(); i++) {
            live_rank_tab.addTab(live_rank_tab.newTab());
            live_rank_tab.getTabAt(i).setText(titleList.get(i));
        }
//        liveRankTabAdapter= new liveRankTabAdapter(liveFragment.getChildFragmentManager());
        liveRankTabAdapter= new liveRankTabAdapter(getChildFragmentManager());
        live_rank_viewpager.setAdapter(liveRankTabAdapter);
        live_rank_tab.setupWithViewPager(live_rank_viewpager);
    }



    class liveRankTabAdapter extends FragmentStatePagerAdapter {
        public liveRankTabAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return LiveRankFragment.newInstance(position,anchorAccount);
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
    }

}
