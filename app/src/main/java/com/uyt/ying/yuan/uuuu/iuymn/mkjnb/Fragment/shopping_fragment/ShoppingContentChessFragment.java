package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ChessTxWebViewActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.ShoppingContentChessAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessListBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MenuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentChessModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SaveChessLotteryUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Headers;

public class ShoppingContentChessFragment extends BaseFragment implements ShoppingContentChessAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.shopping_content_recycler)
    RecyclerView shopping_content_recycler;
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

    ShoppingContentChessAdapter shoppingContentChessAdapter;
    ArrayList<ShoppingContentChessModel.ClassfyModel> classfyModelArrayList = new ArrayList<>();
    ArrayList<ShoppingContentChessModel.ChessEntity.DataBean>typeTwoModelArrayList = new ArrayList<>();


    private Unbinder unbinder;
    private MenuModel.ChessGameListBean chessGameListBean;
    private SaveChessLotteryUtil saveChessLotteryUtil = new SaveChessLotteryUtil();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        getArgumentsData();
        initRecycler();
        refreshLayout.setEnableLoadMore(false);
//        initRefresh();
        return view;
    }

    private void initRefresh() {
        RefreshUtil.initRefresh(new SoftReference<>(getContext()), refreshLayout, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }
        });
    }

    private void initRecycler() {
            shoppingContentChessAdapter = new ShoppingContentChessAdapter(classfyModelArrayList,getContext(),typeTwoModelArrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            shopping_content_recycler.setLayoutManager(layoutManager);
            shopping_content_recycler.setAdapter(shoppingContentChessAdapter);
            shoppingContentChessAdapter.setOnItemClickListener(this);
        //顶部棋牌分类item
        classfyModelArrayList.add(new ShoppingContentChessModel.ClassfyModel(chessGameListBean.getName2(),chessGameListBean.getGame()));
//        String chessList = SharePreferencesUtil.getString(MyApplication.getInstance(), "chessList", "");
        ChessListBean instance = ChessListBean.getInstance();
        List<ShoppingContentChessModel.ChessEntity.DataBean> data = instance.getData();
        //棋牌数据为空,请求到数据后添加到列表,否则直接拿缓存的数据 ()
        if(data==null || data.size()==0){
            requestAllChessList(true);
        }else {
            addTypeTwoListData(data);
//            requestAllChessList(false);
        }

    }

    /**
     * 更新棋牌列表数据缓存,并将数据添加到列表
     * @param addData 是否需要添加棋牌到列表
     */
    private void requestAllChessList(boolean addData) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("loginType",1);
        HttpApiUtils.CPnormalRequest(getActivity(),null, RequestUtil.CHESS_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
//                SharePreferencesUtil.putString(MyApplication.getInstance(),"chessList",result);
                ShoppingContentChessModel.ChessEntity chessEntity = JSONObject.parseObject(result, ShoppingContentChessModel.ChessEntity.class);
                ChessListBean chessListBean = ChessListBean.getInstance();
                chessListBean.setData(chessEntity.getData());
                if(addData){
                    addTypeTwoListData(chessEntity.getData());
                }
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    private void addTypeTwoListData( List<ShoppingContentChessModel.ChessEntity.DataBean> data ) {
        //棋牌游戏列表item
        typeTwoModelArrayList.addAll(data);
        shoppingContentChessAdapter.notifyDataSetChanged();
    }

    private void getArgumentsData() {
        chessGameListBean = (MenuModel.ChessGameListBean) getArguments().getSerializable("chessGameListBean");
    }

    public static ShoppingContentChessFragment newInstance (MenuModel.ChessGameListBean chessGameListBean){
        ShoppingContentChessFragment shoppingContentChessFragment = new ShoppingContentChessFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("chessGameListBean",chessGameListBean);
        shoppingContentChessFragment.setArguments(bundle);
        return shoppingContentChessFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }


    @Override
    public void onItemClick(View view, ArrayList<ShoppingContentChessModel.ChessEntity.DataBean> chessList, int position) {
        if(LoginIntentUtil.isLogin(getContext())){
            ShoppingContentChessModel.ChessEntity.DataBean dataBean = chessList.get(position);
            String jsonString = JSONObject.toJSONString(dataBean);
            saveChessLotteryUtil.saveChessLotteryHistory(jsonString);
            String id = dataBean.getId();
            int game = dataBean.getGame();
            HashMap<String, Object> data = new HashMap<>();
            data.put("game",game);
            data.put("id",id);
            data.put("loginType",1);
            HttpApiUtils.CpRequest(getActivity(), ShoppingContentChessFragment.this,RequestUtil.PLAY_CHESS, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String url = jsonObject.getString("url");
                    ChessTxWebViewActivity.startAty(getContext(),url,game,id);
                }

                @Override
                public void onFailed(String msg) {
                }
            });
        }else {
            LoginIntentUtil.toLogin(getActivity());
        }

    }
}
