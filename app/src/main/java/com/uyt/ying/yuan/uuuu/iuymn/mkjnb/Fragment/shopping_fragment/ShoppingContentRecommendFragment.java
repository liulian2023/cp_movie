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
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.ShoppingContentRecommendAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.HbGameClassModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ChessListBean;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MenuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.NavigateEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentRecommendModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ShoppingContentChessModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SaveChessLotteryUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToBetAtyUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Headers;

/**
 * ????????????fragment
 */
public class ShoppingContentRecommendFragment extends BaseFragment implements ShoppingContentRecommendAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    @BindView(R.id.error_linear)
    LinearLayout errorLinear;
    @BindView(R.id.reload_tv)
    TextView reloadTv;
    @BindView(R.id.loading_linear)
    ConstraintLayout loadingLinear;
    @BindView(R.id.nodata_linear)
    LinearLayout nodataLinear;
    @BindView(R.id.shopping_content_recycler)
    RecyclerView shopping_content_recycler;
    ShoppingContentRecommendAdapter shoppingContentRecommendAdapter;
    ArrayList<ShoppingContentChessModel.ClassfyModel> classfyModelArrayList = new ArrayList<>();
    ArrayList<ShoppingContentRecommendModel>typeTwoModelArrayList = new ArrayList<>();
    SaveChessLotteryUtil saveChessLotteryUtil =  new SaveChessLotteryUtil();
    private Unbinder unbinder;
    private MenuModel.ChessGameListBean chessGameListBean;
    private String TAG="ShoppingContentRecommendFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_content, container, false);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        unbinder = ButterKnife.bind(this, view);
        refresh.setEnableRefresh(false);
        refresh.setEnableLoadMore(false);
        getArgumentsData();
        initRecycler();
        return view;
    }


    @Override
    public void errorRefresh() {
        super.errorRefresh();
        handAllData();
    }

    @Override
    public void onStart() {
        super.onStart();
        handAllData();
//        requestHotChessGame();
    }

    private void handAllData() {
        classfyModelArrayList.clear();
        typeTwoModelArrayList.clear();
        getHistoryData();
        ChessListBean instance = ChessListBean.getInstance();
        List<ShoppingContentChessModel.ChessEntity.DataBean> data = instance.getData();
        //??????????????????,?????????????????????????????????,??????????????????????????????
        if(data==null || data.size()==0){
            requestHotChessGame(false);
        }else {
            addChessData(data);
        }
        getHotLotteryGame();
    }

    private void initRecycler() {
            shoppingContentRecommendAdapter = new ShoppingContentRecommendAdapter(classfyModelArrayList,getContext(),typeTwoModelArrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            shopping_content_recycler.setLayoutManager(layoutManager);
            shopping_content_recycler.setAdapter(shoppingContentRecommendAdapter);
            shoppingContentRecommendAdapter.setOnItemClickListener(this);
    }

    /**
     * ????????????????????????????????????
     */
    private void requestHotChessGame(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("loginType",1);
        HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.CHESS_LIST, data, loadingLinear, errorLinear, reloadTv, (View) refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ShoppingContentChessModel.ChessEntity chessEntity = JSONObject.parseObject(result, ShoppingContentChessModel.ChessEntity.class);
                ChessListBean chessListBean = ChessListBean.getInstance();
                List<ShoppingContentChessModel.ChessEntity.DataBean> data = chessEntity.getData();
                chessListBean.setData(data);
                loadingLinear.setVisibility(View.GONE);
                if(data.size()==0){
                    nodataLinear.setVisibility(View.VISIBLE);
                }else {
                    nodataLinear.setVisibility(View.GONE);

                }
//                RefreshUtil.success(1,refreshLayout,loadingLinear,nodataLinear,data.size(),false,isRefresh,classfyModelArrayList);
                addChessData(data);
            }

            @Override
            public void onFailed(String msg) {
                loadingLinear.setVisibility(View.GONE);
                nodataLinear.setVisibility(View.GONE);
                errorLinear.setVisibility(View.VISIBLE);
            }
        });
    }

    private void addChessData(List<ShoppingContentChessModel.ChessEntity.DataBean> data) {
        if(!hasHistory()){
            getHistoryData();
        }
        //??????type??????item
        ShoppingContentChessModel.ClassfyModel classfyModel = new ShoppingContentChessModel.ClassfyModel(Utils.getString(R.string.????????????), chessGameListBean.getGame());
        classfyModelArrayList.add(classfyModel);
        //????????????item
        for (int i = 0; i < data.size(); i++) {
            ShoppingContentChessModel.ChessEntity.DataBean dataBean = data.get(i);
            int isHot = dataBean.getIsHot();
            //????????????????????????????????????
            if(isHot==1){
                ShoppingContentRecommendModel shoppingContentRecommendModel = new ShoppingContentRecommendModel();
                shoppingContentRecommendModel.setChessDataBean(dataBean);
                if(!typeTwoModelArrayList.contains(shoppingContentRecommendModel)){
                    typeTwoModelArrayList.add(shoppingContentRecommendModel);
                }
            }
        }
        shoppingContentRecommendAdapter.notifyDataSetChanged();
    }

    private boolean hasHistory() {
        boolean hasHistory=false;
        for (int i = 0; i < classfyModelArrayList.size(); i++) {
            ShoppingContentChessModel.ClassfyModel classfyModel = classfyModelArrayList.get(i);
            boolean history = classfyModel.isHistory();
            if(history){
                hasHistory = true;
            }
            break;
        }
        return hasHistory;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky =true)
    public void updateXian(HbGameClassModel hbGameClassModel){
        selectorId(hbGameClassModel);
    }
    /**
     * ??????????????????(??????????????????)
     * @param hbGameClassModel
     */
    private void  selectorId(HbGameClassModel hbGameClassModel) {
        List<String> idList = Arrays.asList(hbGameClassModel.getGameIdListStr().split(","));
        for (int i = 0; i < typeTwoModelArrayList.size(); i++) {
            ShoppingContentRecommendModel shoppingContentRecommendModel = typeTwoModelArrayList.get(i);
            for (int j = 0; j < idList.size(); j++) {
                NavigateEntity.GameClassListBean lotteryDataBean = shoppingContentRecommendModel.getLotteryDataBean();
                if(lotteryDataBean!=null){
                    if((lotteryDataBean.getId()+"").equals(idList.get(j))){
                        lotteryDataBean.setXian(true);
                        break;
                    }
                }

            }
        }
        if(null!=shoppingContentRecommendAdapter){
            shoppingContentRecommendAdapter.notifyDataSetChanged();
        }
    }
    /**
     * ??????????????????????????????????????????
     */
    private void getHotLotteryGame() {
        //??????????????????????????????
        String navigateList = SharePreferencesUtil.getString(getContext(), "navigateList", "");
        if(StringMyUtil.isNotEmpty(navigateList)){
            NavigateEntity navigateEntity = JSONObject.parseObject(navigateList, NavigateEntity.class);
            List<NavigateEntity.GameClassListBean> gameClassList = navigateEntity.getGameClassList();
            HbGameClassModel instance = HbGameClassModel.getInstance();
            String typtIdList = instance.getTyptIdList();
            for (int i = 0; i < gameClassList.size(); i++) {
                NavigateEntity.GameClassListBean gameClassListBean = gameClassList.get(i);
                int isHot = gameClassListBean.getIsHot();
                //??????????????????
                if(isHot==1){
                    ShoppingContentRecommendModel shoppingContentRecommendModel = new ShoppingContentRecommendModel();
                    shoppingContentRecommendModel.setLotteryDataBean(gameClassListBean);
                    if(!typeTwoModelArrayList.contains(shoppingContentRecommendModel)){

                        typeTwoModelArrayList.add(shoppingContentRecommendModel);
                    }
                }
            }
        }
        shoppingContentRecommendAdapter.notifyDataSetChanged();
        HbGameClassModel instance = HbGameClassModel.getInstance();
        if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
            selectorId(instance);
        }
    }

    /**
     * ????????????????????????????????????
     */
    private void getHistoryData() {
        //????????????????????????/????????????
        List<String> lotteryHistory = saveChessLotteryUtil.getChessLotteryHistory();
        //??????????????????????????????????????????????????? ????????????item
        if(lotteryHistory.size()!=0) {
            ShoppingContentChessModel.ClassfyModel classfyModel = new ShoppingContentChessModel.ClassfyModel(Utils.getString(R.string.????????????), chessGameListBean.getGame());
            //????????????, adapter???isHistory???true?????????????????????item??????,???????????????????????????item??????
            classfyModel.setHistory(true);
//            if(!classfyModelArrayList.contains(classfyModel)){
            classfyModelArrayList.add(classfyModel);
//        }
            //?????????????????????list
            for (int i = 0; i < lotteryHistory.size(); i++) {
                String text = lotteryHistory.get(i);
                //??????lastLotteryQiShu ???????????????
                if(text.contains("lastLotteryQiShu")){
                    NavigateEntity.GameClassListBean gameClassListBean = JSONObject.parseObject(text, NavigateEntity.GameClassListBean.class);
                    ShoppingContentRecommendModel shoppingContentRecommendModel = new ShoppingContentRecommendModel();
                    shoppingContentRecommendModel.setLotteryDataBean(gameClassListBean);
                    //????????????, adapter???isHistory???true?????????????????????item??????,???????????????????????????item??????
                    shoppingContentRecommendModel.setHistory(true);
                    typeTwoModelArrayList.add(shoppingContentRecommendModel);
                }else {
                    ShoppingContentChessModel.ChessEntity.DataBean dataBean = JSONObject.parseObject(text, ShoppingContentChessModel.ChessEntity.DataBean.class);
                        ShoppingContentRecommendModel shoppingContentRecommendModel = new ShoppingContentRecommendModel();
                        shoppingContentRecommendModel.setChessDataBean(dataBean);
                        shoppingContentRecommendModel.setHistory(true);
                        typeTwoModelArrayList.add(shoppingContentRecommendModel);
                }
            }
        }
        shoppingContentRecommendAdapter.notifyDataSetChanged();
        HbGameClassModel instance = HbGameClassModel.getInstance();
        if(StringMyUtil.isNotEmpty(instance.getGameIdListStr())){
            selectorId(instance);
        }
    }

    private void getArgumentsData() {
         chessGameListBean = (MenuModel.ChessGameListBean) getArguments().getSerializable("chessGameListBean");
    }

    public static ShoppingContentRecommendFragment newInstance (MenuModel.ChessGameListBean chessGameListBean){
        ShoppingContentRecommendFragment shoppingContentChessFragment = new ShoppingContentRecommendFragment();
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(View view, ArrayList<ShoppingContentRecommendModel> shoppingContentRecommendModelArrayList, int position) {
        if(LoginIntentUtil.isLogin(getContext())){
            ShoppingContentRecommendModel shoppingContentRecommendModel = shoppingContentRecommendModelArrayList.get(position);
            ShoppingContentChessModel.ChessEntity.DataBean chessDataBean = shoppingContentRecommendModel.getChessDataBean();
            NavigateEntity.GameClassListBean lotteryDataBean = shoppingContentRecommendModel.getLotteryDataBean();
            //????????????????????????webView
            if(chessDataBean!=null){
                //????????????.??????????????????
                String jsonString = JSONObject.toJSONString(chessDataBean);
                saveChessLotteryUtil.saveChessLotteryHistory(jsonString);
                String id = chessDataBean.getId();
                int game = chessDataBean.getGame();
                HashMap<String, Object> data = new HashMap<>();
                data.put("game",game);
                data.put("id",id);
                data.put("loginType",1);
                HttpApiUtils.CpRequest(getActivity(), ShoppingContentRecommendFragment.this,RequestUtil.PLAY_CHESS, data, new HttpApiUtils.OnRequestLintener() {
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
                //??????????????????(???????????????????????????????????????????????????)
                ToBetAtyUtils.toBetActivity(getContext(),lotteryDataBean.getGame(),lotteryDataBean.getTypename(),lotteryDataBean.getType_id(),lotteryDataBean.getIsopenOffice()+"",lotteryDataBean.getIsStart()+"");
            }
        }else {
            LoginIntentUtil.toLogin(getActivity());
        }

    }
}
