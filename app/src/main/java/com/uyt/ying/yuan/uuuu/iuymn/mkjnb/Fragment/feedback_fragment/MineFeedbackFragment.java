package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.feedback_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.MineFeedbackRecycleAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.UpdateMineFeedback;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MineFeedbackModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;

/*
我的反馈
 */
public class MineFeedbackFragment extends BaseFragment {
    @BindView(R.id.mine_feedback_recycle)
    RecyclerView mRecy;
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.error_linear)
    LinearLayout errorLinear;
    @BindView(R.id.reload_tv)
    TextView reloadTv;
    @BindView(R.id.loading_linear)
    ConstraintLayout loadingLinear;
    @BindView(R.id.nodata_linear)
    LinearLayout nodataLinear;
    private MineFeedbackRecycleAdapter mineFeedbackRecycleAdapter;
    private ArrayList<MineFeedbackModel> mineFeedbackModelArrayList = new ArrayList<>();
    private int pageNum = 1;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_feedback, container, false);
        ButterKnife.bind(this,view);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        initRecycle();
        initRefresh();

        return view;
    }


    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getMineFeedback(pageNum,false,false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateMineFeedback(UpdateMineFeedback updateMineFeedback){
        if(updateMineFeedback.isUpdate()){
            getMineFeedback(1,false,false);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(refreshLayout!=null){
            refreshLayout.closeHeaderOrFooter();
        }
        EventBus.getDefault().unregister(this);


    }


    private void initRefresh() {
        RefreshUtil.initRefreshLoadMore(new SoftReference<>(getContext()), refreshLayout, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum=1;
                getMineFeedback(pageNum,false,true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                getMineFeedback(pageNum,true,false);
            }
        });
    }

    private void initRecycle() {
        mineFeedbackRecycleAdapter = new MineFeedbackRecycleAdapter(mineFeedbackModelArrayList);
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecy.setAdapter(mineFeedbackRecycleAdapter);

        getMineFeedback(1, false, false);
    }

    private void getMineFeedback(int pageNo, boolean isLoadMore, boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo", pageNo);
        data.put("pageSize", 7);
//        data.put("orderField", "createdDate");
        HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.MINE_FEEDBACK, data, loadingLinear, errorLinear, reloadTv, (View) refreshLayout, isLoadMore,isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONArray data = jsonObject.getJSONArray("data");
                int size = data.size();
                RefreshUtil.success(pageNum, refreshLayout, loadingLinear, nodataLinear, size, isLoadMore, isRefresh, mineFeedbackModelArrayList);
                for (int i = 0; i < size; i++) {
                    JSONObject object = data.getJSONObject(i);
                    String id = object.getString("id");
                    String problem = object.getString("problem");
                    String content = object.getString("content");
                    String createdDate = object.getString("createdDate");
                    String solveProgram = object.getString("solveProgram");
                    mineFeedbackModelArrayList.add(new MineFeedbackModel(createdDate, problem, content, id, StringMyUtil.isEmptyString(solveProgram)?"":solveProgram));
                }
                mineFeedbackRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,isLoadMore,pageNo,refreshLayout);
            }
        });
    }
}
