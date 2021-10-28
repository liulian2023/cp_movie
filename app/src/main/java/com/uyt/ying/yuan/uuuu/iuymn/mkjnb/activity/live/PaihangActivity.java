package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live.PaihangTabAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.MvpBaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActionBarUtil;
import com.google.android.material.tabs.TabLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.Const.RANKTYPE;

public class PaihangActivity extends MvpBaseActivity {

    @BindView(R.id.toolbar_common)
    Toolbar toolbar_common;
    @BindView(R.id.tv_commonbar_title)
    TextView tv_commonbar_title;
    @BindView(R.id.tab_paihang)
    TabLayout tab_paihang;
    @BindView(R.id.viewpager_paihang)
    ViewPager viewpager_paihang;
    @BindView(R.id.iv_commonbar_back)
    ImageView iv_commonbar_back;

    private PaihangTabAdapter paihangTabAdapter;
    private List<String> titlesList = new ArrayList<>();//={Utils.getString(R.string.礼物榜),Utils.getString(R.string.中奖榜),Utils.getString(R.string.趣约红包榜),Utils.getString(R.string.专享红包榜)};

    private RankTypeEnum rankTypeEnum;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_paihang;
    }

    @Override
    public void initView() {
        rankTypeEnum = (RankTypeEnum) getIntent().getSerializableExtra(RANKTYPE);
        ActionBarUtil.initPaiHangBar(this,toolbar_common);
        tv_commonbar_title.setText(Utils.getString(R.string.排行榜));
        /**
         * 初始化tablayout viewpager 子fragment
         */
        paihangTabAdapter = new PaihangTabAdapter(getSupportFragmentManager(),this,titlesList);
        viewpager_paihang.setAdapter(paihangTabAdapter);
        tab_paihang.setupWithViewPager(viewpager_paihang);
        /**
         * 刷新tab
          */
        RankTypeEnum[] rankType = RankTypeEnum.values();
        for (int i=0;i<rankType.length;i++){
            titlesList.add(rankType[i].getDes());
        }
        paihangTabAdapter.notifyDataSetChanged();
        /**
         * 根据传递过来的类型 切换当前item  位置
         */
        if (rankType!=null){
           // tab_paihang.getTabAt(rankTypeEnum.ordinal()).select();
            viewpager_paihang.setCurrentItem(rankTypeEnum.ordinal());
        }

    }

    @Override
    protected void initEventAndData() {
        iv_commonbar_back.setOnClickListener(v -> onBackPressedSupport());
    }


}
