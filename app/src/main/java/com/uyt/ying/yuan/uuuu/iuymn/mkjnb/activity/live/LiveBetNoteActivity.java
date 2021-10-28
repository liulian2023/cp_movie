package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

public class LiveBetNoteActivity extends BaseActivity {
    private TabLayout mTab;
    private ArrayList<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    String game;
    String type_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_bet_note);
        //设置布局在底部
        getWindow().setGravity(Gravity.BOTTOM);
        //设置布局填充满宽度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        //设置进出动画
        overridePendingTransition(R.anim.activity_int_400,R.anim.activity_out_400);
        getIntentData();
        bindView();
        initTab();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        game= intent.getIntExtra("game",1)+"";
        type_id=intent.getIntExtra("type_id",1)+"";
    }

    public static  void startAty(Context context,int game, int type_id){
        Intent intent = new Intent(context,LiveBetNoteActivity.class);
        intent.putExtra("type_id",type_id);
        intent.putExtra("game",game);
        context.startActivity(intent);

    }
    private void initTab() {
        titleList.add(Utils.getString(R.string.全部));
        titleList.add(getString(R.string.已中奖));
        titleList.add(getString(R.string.未中奖));
        titleList.add(getString(R.string.待开奖));
        titleList.add(Utils.getString(R.string.已撤单));
        for (int i = 0; i < titleList.size(); i++) {
            mTab.addTab(mTab.newTab());
        }
        viewPager.setAdapter(new LiveBetTabAdapter(getSupportFragmentManager(),titleList));
        mTab.setupWithViewPager(viewPager);
    }

    private void bindView() {
        mTab=findViewById(R.id.live_bet_tab);
        viewPager=findViewById(R.id.live_bet_viewpager);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    class LiveBetTabAdapter extends FragmentPagerAdapter{
        ArrayList<String> titleList = new ArrayList<>();

        public LiveBetTabAdapter(@NonNull FragmentManager fm, ArrayList<String> titleList) {
            super(fm);
            this.titleList = titleList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return LiveBetNoteFragment.newInstance(position,type_id,game);
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
