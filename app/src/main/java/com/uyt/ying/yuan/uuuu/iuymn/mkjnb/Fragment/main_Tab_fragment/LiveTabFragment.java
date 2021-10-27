package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.androidkun.xtablayout.XTabLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment.LiveListFragment;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.SearchLiveActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.AppStatisticsModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveClassfyEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class LiveTabFragment extends BaseFragment implements XTabLayout.OnTabSelectedListener, View.OnClickListener {

    public ImageView searchIv;
    public ConstraintLayout loadingLinear;
    public LinearLayout error_linear;
    public TextView reloadTv;
    //    public TabLayout mTab;
    public XTabLayout mTab;
    public ArrayList<LiveClassfyEntity.CategoryListBean> titleList  = new ArrayList<>();
    //    public NoSlidingViewPager mViewPager;
    public ViewPager mViewPager;
    public LiveTabAdapter liveTabAdapter;
    boolean toRecommend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_tab, container, false);
        getArgumentsData();
        bindView(view);
        return view;
    }

    private void getArgumentsData() {
        toRecommend = getArguments().getBoolean("toRecommend");
    }

    public static LiveTabFragment newInstance(boolean toRecommend) {
        Bundle args = new Bundle();
        args.putBoolean("toRecommend",toRecommend);
        LiveTabFragment fragment = new LiveTabFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(getActivity(),R.color.white));
        StatusBarUtil.setDarkMode(getActivity());
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initTab();
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        initTab();
    }

    public void initTab() {
        titleList.clear();
        titleList.add(addCategoryListBean("-1",Utils.getString(R.string.关注)));
        titleList.add(addCategoryListBean("-2",Utils.getString(R.string.推荐)));
        titleList.add(addCategoryListBean("-3",Utils.getString(R.string.最新)));
        titleList.add(addCategoryListBean("",Utils.getString(R.string.附近)));
        titleList.add(addCategoryListBean("-4",Utils.getString(R.string.收费)));

        HashMap<String, Object> data = new HashMap<>();
        if(!AppStatisticsModel.getInstance().isAppStatistics()){
//            data.put("page",1);
        }
        loadingLinear.setVisibility(View.VISIBLE);
        error_linear.setVisibility(View.GONE);
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.LIVE_CLASSFY, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
      /*          if(!AppStatisticsModel.getInstance().isAppStatistics()){
                    AppStatisticsModel.getInstance().setAppStatistics(true);
                }*/
                loadingLinear.setVisibility(View.GONE);
                LiveClassfyEntity liveClassfyEntity = JSONObject.parseObject(result, LiveClassfyEntity.class);
                List<LiveClassfyEntity.CategoryListBean> categoryList = liveClassfyEntity.getCategoryList();
                titleList.addAll(categoryList);
                handTab();
            }

            @Override
            public void onFailed(String msg) {
                loadingLinear.setVisibility(View.GONE);
                error_linear.setVisibility(View.VISIBLE);
//                handTab();
            }
        });
    }

    private void handTab() {
        liveTabAdapter =new LiveTabAdapter(getChildFragmentManager(),titleList,getContext());
        mViewPager.setAdapter(liveTabAdapter);
        mTab.setupWithViewPager(mViewPager);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = mTab.getTabAt(i);
            tabAt.setCustomView(liveTabAdapter.getTabView(i));
            if(i==0){
                TextView textView= tabAt.getCustomView().findViewById(R.id.home_tab_title_tv);
                textView.setTextColor(Color.parseColor("#FF2F81"));
                textView.setTextSize(20);
            }
        }
        mTab.addOnTabSelectedListener(LiveTabFragment.this);
        mViewPager.setOffscreenPageLimit(2);
        /**
         * 热门中点击更多跳转时需要默认选中 推荐 tab
         */
        if(toRecommend){
            mViewPager.setCurrentItem(1);
        }
    }

    @NotNull
    private LiveClassfyEntity.CategoryListBean addCategoryListBean(String categoryId,String name) {
        LiveClassfyEntity.CategoryListBean categoryListBean = new LiveClassfyEntity.CategoryListBean();
        if(name.equals(Utils.getString(R.string.附近))){
            String province = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.PROVINCE, "");
            categoryListBean.setArea(province);
        }else {
            categoryListBean.setCategoryId(categoryId);
        }
        categoryListBean.setName(name);
        return categoryListBean;
    }

    public void bindView(View view) {
        reloadTv=view.findViewById(R.id.reload_tv);
        error_linear=view.findViewById(R.id.error_linear);
        loadingLinear=view.findViewById(R.id.loading_linear);
        searchIv=view.findViewById(R.id.live_search_iv);
        mTab=view.findViewById(R.id.live_tab);
        mViewPager=view.findViewById(R.id.live_fragment_viewpager);
        searchIv.setOnClickListener(this);
        reloadTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.live_search_iv:
//                startActivity(new Intent(getContext(), SearchActivity.class));
                startActivity(new Intent(getContext(), SearchLiveActivity.class));
                break;
            case R.id.reload_tv:
                initTab();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#FF2F81"));
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextColor(Color.parseColor("#767676"));
        textView.setTextSize(16);
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }

     class LiveTabAdapter extends FragmentPagerAdapter {
        private ArrayList<LiveClassfyEntity.CategoryListBean> titleList;
        private Context context;

        public LiveTabAdapter(FragmentManager fm, ArrayList<LiveClassfyEntity.CategoryListBean> titleList, Context context) {
            super(fm);
            this.titleList = titleList;
            this.context = context;
        }

        @Override
        public Fragment getItem(int i) {
            return LiveListFragment.newInstance(i,titleList.get(i));
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position).getName();
        }
        //自定义tab item布局
        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.home_tab_view_layout, null);
            TextView tv = v.findViewById(R.id.home_tab_title_tv);
            tv.setText(titleList.get(position).getName());
            if (position == 0) {
                tv.setTextSize(17);
            }
            return v;
        }
    }
}
