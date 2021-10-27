package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.IBasePresenter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.MvpBaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm.SortChildViewModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.SortEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SortChildFragment extends MvpBaseFragment {

    @BindView(R.id.recy_sortchild)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartrefresh_sortchild)
    SmartRefreshLayout smartrefresh_sortchild;

    public static final String POSITION = "position";
    private int position;
    private SortChildViewModel mViewModel;
    private SortChildAdapter mAdapter;
    private List<SortEntity.RankingListBean> mSortDataList = new ArrayList<>();
    private int pageNo =1;
    private int pageSize =10;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    public static SortChildFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        SortChildFragment sortChildFragment1 = new SortChildFragment();
        bundle.putInt(POSITION, position);
        sortChildFragment1.setArguments(bundle);
        return sortChildFragment1;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sortchild;
    }

    @Override
    protected void initView() {
        position = getArguments().getInt(POSITION);
        mViewModel = ViewModelProviders.of(this).get(SortChildViewModel.class);

        /**
         * 初始化recyclerview
         */
        mAdapter = new SortChildAdapter(R.layout.item_recy_sortchild, mSortDataList,position);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        UpdateUi();
    }


    @Override
    protected void initEventAndData() {
        ReqData(true);

        smartrefresh_sortchild.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartrefresh_sortchild.finishRefresh(500);
                pageNo = 1;
                ReqData(true);
            }
        });
        smartrefresh_sortchild.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smartrefresh_sortchild.finishLoadMore(500);
                pageNo++;
                ReqData(false);
            }
        });
    }

    private void ReqData(boolean isRestart){
        /**
         * 根据position 判断请求哪一类型的排行榜数据
         */
        switch (RankTypeEnum.valueOf(position + 1)) {
            case GIFT:
                mViewModel.reqSortData(RankTypeEnum.GIFT,pageNo,pageSize, isRestart);
                break;
            case CHOCK:
                mViewModel.reqSortData(RankTypeEnum.CHOCK,pageNo,pageSize, isRestart);
                break;
            case QY:
                mViewModel.reqSortData(RankTypeEnum.QY, pageNo,pageSize,isRestart);
                break;
            case ZX:
                mViewModel.reqSortData(RankTypeEnum.ZX, pageNo,pageSize,isRestart);
                break;
            default:
                break;
        }
    }

    private void UpdateUi() {
        mViewModel.getSortEntityData().observe(this, (SortEntity entity) -> {
            mSortDataList.clear();
            mSortDataList.addAll(entity.getRankingList());
            mAdapter.notifyDataSetChanged();
        });
    }


}
