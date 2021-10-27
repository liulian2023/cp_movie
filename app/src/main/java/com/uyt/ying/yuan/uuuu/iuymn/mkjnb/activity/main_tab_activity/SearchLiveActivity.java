
package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.uyt.ying.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter.LiveListAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.UpdateGiftAmountModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.flowLayout.FlowLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.flowLayout.TagAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.myView.flowLayout.TagFlowLayout;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.DateUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SaveLiveSearchUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class SearchLiveActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private ConstraintLayout searchActionBar;
    SharedPreferenceHelperImpl mSharedPreferenceHelperImpl =new SharedPreferenceHelperImpl();
    LinearLayout errorLinear;
    TextView reloadTv;
    ConstraintLayout loadingLinear;
    LinearLayout nodataLinear;
    LinearLayout backLinearLayout;
    EditText searchEt;
    TextView searchTv;
    TextView hotSearchTv;
    RelativeLayout searchHistoryRlt;
    LinearLayout deleteLinear;
    SmartRefreshLayout refreshLayout;
 /*   @BindView(R.id.search_history_recycle)
    RecyclerView searchHistoryRecy;*/

    TagFlowLayout historyFlowLayout;
    RecyclerView mRecy;
    TagFlowLayout hotSearchFlowlayout;

    /*
    main recyleview
     */
    private LiveListAdapter searchAdapter;
    private ArrayList<LiveEntity.AnchorMemberRoomListBean> searchModelArrayList=new ArrayList<>();
/*    *//*
    热门搜索recyecleView
     *//*
    private ArrayList<SearchHistoryModel>hotSearchModelList=new ArrayList<>();*/
    //历史记录保存util
    private SaveLiveSearchUtil saveSearchUtil =new SaveLiveSearchUtil();
    //搜索记录list
    private List<String> searchHistoryList;
    //流式布局的字item
    private TextView historyItemTv;
    private TextView hotSearchitemTv;
    private int pageNum=1;
    private String navigateList;
    private  SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private boolean isCreate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_actvity);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//注册eventBus
        }
        bindView();
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        //搜索记录
        initSearchHistory();
        //热门搜索
//        initHotSearchRecy();
        //搜索结果
        initRecycle();
        initRefresh();
    }
    /**
     * 更新主播收藏状态
     * @param followEvenModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateIsFollow(FollowEvenModel followEvenModel){
        for (int i = 0; i < searchModelArrayList.size(); i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = searchModelArrayList.get(i);
            if((anchorMemberRoomListBean.getAnchorMemberId()+"").equals(followEvenModel.getId())){
                if(followEvenModel.isFollow()){
                    anchorMemberRoomListBean.setIsFollow(1);
                }else {
                    anchorMemberRoomListBean.setIsFollow(0);
                }
                break;
            }
        }
    }

    /**
     * 更新主播礼物金额
     * @param updateGiftAmountModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateGiftAmount(UpdateGiftAmountModel updateGiftAmountModel){
        for (int i = 0; i < searchModelArrayList.size(); i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = searchModelArrayList.get(i);
            if((anchorMemberRoomListBean.getAnchorMemberId()+"").equals(updateGiftAmountModel.getId())){
                anchorMemberRoomListBean.setGiftAmount(updateGiftAmountModel.getGiftAmount()+"");
                break;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);//取消eventBus注册
    }
    private void initRefresh() {

        RefreshUtil.initRefreshLoadMore(new SoftReference<>(this), refreshLayout, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum=1;
                getSearchResult(pageNum,true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                getSearchResult(pageNum,false,true);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isCreate){
            isCreate=false;
            return;
        }
//        getSearchResult(pageNum,false,false);
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        pageNum=1;
        getSearchResult(pageNum,false,false);
    }
    private void bindView() {
        searchActionBar=findViewById(R.id.search_actionbar_relativelayout);
        loadingLinear=findViewById(R.id.loading_linear);
        nodataLinear=findViewById(R.id.nodata_linear);
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        backLinearLayout=findViewById(R.id.chess_search_back_linear);
        searchEt=findViewById(R.id.search_edit);
        searchTv=findViewById(R.id.search_tv);
        hotSearchTv=findViewById(R.id.hot_search_tv);
        searchHistoryRlt=findViewById(R.id.search_history_relativeLayout);
        deleteLinear=findViewById(R.id.search_delete_linear);
        refreshLayout=findViewById(R.id.search_refresh);
        historyFlowLayout=findViewById(R.id.id_flowlayout);
        mRecy=findViewById(R.id.search_recycle);
        hotSearchFlowlayout=findViewById(R.id.hot_search_flowLayout);
        searchTv.setOnClickListener(this);
        searchTv.setClickable(false);
        searchEt.addTextChangedListener(this);
        deleteLinear.setOnClickListener(this);
        backLinearLayout.setOnClickListener(this);
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    searchTv.performClick();
                }
                return false;
            }
        });
    }
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    //点击搜索后,更新历史记录的ui
                    historyFlowLayout.setAdapter(new TagAdapter<String>(searchHistoryList) {
                        @Override
                        public View getView(FlowLayout parent, int position, String s) {
                            historyItemTv = (TextView) LayoutInflater.from(SearchLiveActivity.this).inflate(R.layout.history_fralayout_item,
                                    historyFlowLayout, false);
                            historyItemTv.setText(s);
                            return historyItemTv;
                        }
                    });
                    break;

                case 2:
                  /*  //更新热门搜索的ui
                    hotSearchFlowlayout.setAdapter(new TagAdapter<SearchHistoryModel>(hotSearchModelList) {
                        @Override
                        public View getView(FlowLayout parent, int position, SearchHistoryModel searchHistoryModel) {
                            hotSearchitemTv= (TextView) LayoutInflater.from(SearchLiveActivity.this).inflate(R.layout.history_fralayout_item,
                                    hotSearchFlowlayout,false);
                            hotSearchitemTv.setText(hotSearchModelList.get(position).getContent());
                            return hotSearchitemTv;
                        }
                    });*/
                    break;
                default:
                    break;
            }
        }
    };
/*    *//*
      热门搜索recyeleView
       *//*
    private void initHotSearchRecy() {

        hotSearchFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                SearchHistoryModel searchHistoryModel = hotSearchModelList.get(position);
                searchEt.setText(searchHistoryModel.getContent());
                searchEt.setSelection(searchEt.getText().toString().length());
                clickSearchBtn();
                return true;
            }
        });
        getHotSearchData();
    }*/
    /*
    请求热门搜索数据
     */
/*    private void getHotSearchData() {
        String sysParameter = mSharedPreferenceHelperImpl.getSysParameter();
        SysParameterEntity sysParameterEntity = JSONObject.parseObject(sysParameter, SysParameterEntity.class);
        String searchSetting = sysParameterEntity.getSearchSetting();
        JSONObject jsonObject = JSONObject.parseObject(searchSetting);
        String searchHot = jsonObject.getString("searchHot");
        List<String> stringList = Arrays.asList(searchHot.split(","));
        for (int i = 0; i < stringList.size(); i++) {
            hotSearchModelList.add(new SearchHistoryModel(stringList.get(i)));
        }
        handler.sendEmptyMessage(2);
    }*/
    /*
    搜索历史flowLayout
     */
    private void initSearchHistory() {
        historyFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String s = searchHistoryList.get(position);
                searchEt.setText(s);
                searchEt.setSelection(searchEt.getText().toString().length());
                clickSearchBtn();
                return true;
            }
        });
        getCacheData();
    }
    /*
    取出最新的聊天记录
     */
    private void getCacheData() {
        searchHistoryList = saveSearchUtil.getSearchHistory();
        //通知更新ui
        handler.sendEmptyMessage(1);
    }

    /*
    main recycleView(搜索结果列表)
     */
    private void initRecycle() {
        searchAdapter=new LiveListAdapter(this);
        mRecy.setLayoutManager(new GridLayoutManager(this,2));
        mRecy.setAdapter(searchAdapter);
        searchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(! LoginIntentUtil.isLogin(SearchLiveActivity.this)){
                    LoginIntentUtil.toLogin(SearchLiveActivity.this);
                }else {
                    LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = searchModelArrayList.get(position);

                    RouteUtils.start2LiveActivity(SearchLiveActivity.this,anchorMemberRoomListBean,"", CommonStr.AREA_DEFAULT_VALUE,1);
                }
            }
        });

    }
    //请求搜索结果
    private void getSearchResult(int pageNo,boolean isRefresh,boolean isLoadMore) {
        navigateList= SharePreferencesUtil.getString(this,"navigateList","");
        if(StringMyUtil.isEmptyString(navigateList)){//彩票数据没有缓存,请求彩票数据并做缓存处理
            Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, -1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    navigateList=content;
                    SharePreferencesUtil.putString(SearchLiveActivity.this,"navigateList",content);
                    requestMovieData(pageNo, isRefresh, isLoadMore);
                }
                @Override
                public void failed(MessageHead messageHead) {

                }
            });
        }else {//有缓存直接处理数据
            requestMovieData(pageNo, isRefresh, isLoadMore);
        }
    }

    private void requestMovieData(int pageNo, boolean isRefresh, boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("keyWord",searchEt.getText().toString());
        data.put("pageNo",pageNo);
        data.put("pageSize",16);
        HttpApiUtils.cpShowLoadRequest(this, null, RequestUtil.LIVEROOM_LIST, data, loadingLinear, errorLinear, reloadTv, refreshLayout, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//解析json
                JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
                LiveEntity liveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.AnchorMemberRoomListBean> anchorMemberRoomList = liveEntity.getAnchorMemberRoomList();
                int size = anchorMemberRoomList.size();
                RefreshUtil.success(pageNum,refreshLayout,loadingLinear,nodataLinear, size,isLoadMore,isRefresh,searchModelArrayList);
                for (int i = 0; i < anchorMemberRoomList.size(); i++) {
                    LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = anchorMemberRoomList.get(i);
                    long cpId = anchorMemberRoomListBean.getCpId();
                    for (int j = 0; j < lotteryArray.size(); j++) {
                        JSONObject object = lotteryArray.getJSONObject(j);
                        long lotteryId = object.getLong("id");
                        if(lotteryId==cpId){
                            if(!searchModelArrayList.contains(anchorMemberRoomListBean)){
                                anchorMemberRoomListBean.setLotteryName(object.getString("typename"));
                                String game = object.getString("game");
                                anchorMemberRoomListBean.setGame(game);
                                searchModelArrayList.add(anchorMemberRoomListBean);
                            }
                            break;
                        }
                    }
                }

                searchAdapter.setList(searchModelArrayList);
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,isLoadMore,pageNo,refreshLayout);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击搜索
            case R.id.search_tv:
                clickSearchBtn();
                break;
            case R.id.search_delete_linear:
                //点击删除聊天记录.,将sp中的聊天记录设为空,取出新缓存后更新ui
                mSharedPreferenceHelperImpl.setLiveSearchCache("");
                getCacheData();
                showToast(Utils.getString(R.string.搜索记录删除成功));
                break;
            case R.id.chess_search_back_linear:
                finish();
                break;
        }
    }
    /*
    点击搜索按钮后的系列操作
     */
    private void clickSearchBtn() {
        //隐藏显或示电影recycleView 搜索历史  热门搜索
        if (refreshLayout.getVisibility() != View.VISIBLE) {
            //电影列表外层的刷新控件(隐藏刷新控件,列表也会隐藏)
            refreshLayout.setVisibility(View.VISIBLE);
            mRecy.setVisibility(View.VISIBLE);
            //历史记录列表
            historyFlowLayout.setVisibility(View.GONE);
            //热门搜索
            hotSearchFlowlayout.setVisibility(View.GONE);
            //历史记录提示框(包括删除图标)
            searchHistoryRlt.setVisibility(View.GONE);
            //热门搜索提示框
            hotSearchTv.setVisibility(View.GONE);
        }
        //收回软键盘
        Utils.hideSoftKeyBoard(SearchLiveActivity.this);
        //储存搜索记录
        saveSearchUtil.saveSearchHistory(searchEt.getText().toString());
        //取出新的搜索记录
        getCacheData();
        pageNum = 1;
        //请求搜索结果
        getSearchResult(pageNum, false, false);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //输入框为空时,搜索按钮设为不可点击(每次点击会将输入框中的值储存到搜索记录中,空字符不进行储存)
        if(StringMyUtil.isEmptyString(searchEt.getText().toString())){
            searchTv.setClickable(false);
            if(refreshLayout.getVisibility()!=View.GONE){
                refreshLayout.setVisibility(View.GONE);
                mRecy.setVisibility(View.GONE);
                errorLinear.setVisibility(View.GONE);
                nodataLinear.setVisibility(View.GONE);
                historyFlowLayout.setVisibility(View.VISIBLE);
                hotSearchFlowlayout.setVisibility(View.VISIBLE);
                searchHistoryRlt.setVisibility(View.VISIBLE);
                //todo 热门搜索没有了
//                hotSearchTv.setVisibility(View.VISIBLE);
                hotSearchTv.setVisibility(View.GONE);
            }
        }else {
            searchTv.setClickable(true);
        }
    }
    @Override
    protected void init() {

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
