package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_fragment_activitys;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.feedback_fragment.MineFeedbackFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.feedback_fragment.WantToFeedbackFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.FragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonToolbarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MemoryLeakUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.google.android.material.tabs.TabLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.feedback_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.feedback_fragment_content)
    ViewPager mViewPager;
    private ArrayList<Fragment>fragmentArrayList;
    private ArrayList<String> titles=new ArrayList<>();
    public int movieId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_back);
        ButterKnife.bind(this);
        CommonToolbarUtil.initToolbar(this, Utils.getString(R.string.意见反馈));
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.white));
        StatusBarUtil.setLightMode(this,true);
        getIntentData();
        initTabLayout();
    }

    @Override
    protected void init() {

    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
    private void getIntentData() {
        movieId=getIntent().getIntExtra("movieId",0);
    }

    private void initTabLayout() {
        titles.add(Utils.getString(R.string.我要反馈));
        titles.add(Utils.getString(R.string.我的反馈));
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setText(titles.get(i));
        }
        fragmentArrayList=new ArrayList<>();
        fragmentArrayList.add(new WantToFeedbackFragment());
        fragmentArrayList.add(new MineFeedbackFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);
        mViewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        if(movieId!=0){
            mViewPager.setCurrentItem(1);
        }else {
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentArrayList.clear();
        //华为inputMethodManager内存泄漏
        MemoryLeakUtil.fixLeak(this);
    }
}
