package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.FragmentAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * 购彩大厅fragmemt
 */
public class GouCaiDaTinFragment extends BaseFragment{
    private TabLayout mtab;
    private ViewPager viewPager;
    private boolean isCreate =false;
    private List<String> titles=new ArrayList<>();
//    private   List<SoftReference<Fragment>> fragmentArrayList =new ArrayList<>();
    private   List<Fragment> fragmentArrayList =new ArrayList<>();
    private FragmentAdapter mFragmentAdapteradapter;
    String navigateList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goucaidatin_fragment, container, false);
        navigateList=SharePreferencesUtil.getString(getContext(),"navigateList","");
        mtab = view.findViewById(R.id.mtab);
        viewPager=view.findViewById(R.id.mPager);
        isCreate=true;
        initTab(view);
        return view;
    }
    private void initTab(final View view) {
        titles.clear();
        titles.add(Utils.getString(R.string.全部));
        if(!StringMyUtil.isEmptyString(navigateList)){
            initJson(navigateList);
        }
            Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, 1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    SharePreferencesUtil.putString(getContext(),"navigateList",content);
                    if(StringMyUtil.isEmptyString(navigateList)){
                        initJson(content);
                    }
                }

                @Override
                public void failed(MessageHead messageHead) {

                }
            });
    }

    public void initJson(String jsonContent) {
        JSONObject jsonObject = JSONObject.parseObject(jsonContent);
        JSONArray lotteryFenLei = jsonObject.getJSONArray("gameInfoList");
        for (int i = 0; i < lotteryFenLei.size(); i++) {
            JSONObject jsonObject1 = lotteryFenLei.getJSONObject(i);
            String name = jsonObject1.getString("name");
            String classIds = jsonObject1.getString("classIds");
            //保存classId,在viewPagerFragment中根据这个值来请求各自的列表数据(key为tab栏对应的title)
            SharePreferencesUtil.putString(getContext(),name.hashCode()+"",classIds);
            titles.add(name);
        }
        //添加tab栏数据
        for (int i = 0; i < titles.size(); i++) {
            mtab.addTab(mtab.newTab().setText(titles.get(i)));
        }
        GouCaiTabAdapter adapter = new GouCaiTabAdapter(getChildFragmentManager(), titles);
        viewPager.setAdapter(adapter);
        mtab.setupWithViewPager(viewPager);
        adapter.notifyDataSetChanged();
    }

    public TabLayout getMtab() {
        return mtab;
    }


    class GouCaiTabAdapter extends FragmentStatePagerAdapter{
        private List<String> titles=new ArrayList<>();

        public GouCaiTabAdapter(@NonNull FragmentManager fm, List<String> titles) {
            super(fm);
            this.titles = titles;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return ViewPagerFragemnt.newInstance(position,titles.get(position));
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }
}

