//package com.cambodia.zhanbang.xunbo.activity.mine_fragment_activitys;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import okhttp3.Headers;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.cambodia.zhanbang.rxhttp.net.utils.LogUtils;
//import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
//import com.cambodia.zhanbang.xunbo.R;
//import com.cambodia.zhanbang.xunbo.activity.live.LiveRoomActivity;
//import com.cambodia.zhanbang.xunbo.adapter.CommonAdapter;
//import com.cambodia.zhanbang.xunbo.adapter.home_fragment_adapter.MovieListAdapter;
//import com.cambodia.zhanbang.xunbo.base.BaseActivity;
//import com.cambodia.zhanbang.xunbo.eventBusModel.CollectEvenModel;
//import com.cambodia.zhanbang.xunbo.model.FindModel;
//import com.cambodia.zhanbang.xunbo.model.HomeMovieModel;
//import com.cambodia.zhanbang.xunbo.net.api.HttpApiUtils;
//import com.cambodia.zhanbang.xunbo.net.entity.LiveRoomEntity;
//import com.cambodia.zhanbang.xunbo.request.RequestUtil;
//import com.cambodia.zhanbang.xunbo.request.StringMyUtil;
//import com.cambodia.zhanbang.xunbo.utils.ActionBarUtil;
//import com.cambodia.zhanbang.xunbo.utils.IntentUtil;
//import com.cambodia.zhanbang.xunbo.utils.RefreshUtil;
//import com.cambodia.zhanbang.xunbo.utils.SharePreferencesUtil;
//import com.cambodia.zhanbang.xunbo.utils.Utils;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.lang.ref.SoftReference;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static com.cambodia.zhanbang.xunbo.activity.live.LiveRoomActivity.MOVIEBEAN;
//import static com.cambodia.zhanbang.xunbo.activity.live.LiveRoomActivity.SELECTTYPE;
//
//public class CollectActivity extends BaseActivity {
//    private RecyclerView recyclerView;
//    private RefreshLayout refreshLayout;
//    private MovieListAdapter homeMovieAdapter;
//    private ArrayList<HomeMovieModel> homeMovieModelArrayList = new ArrayList<>();
//    private int pageNum=1;
//    private int pageSize=10;
//    ConstraintLayout loadingLinear;
//    private LinearLayout nodataLinear;
//    private LinearLayout errorLinear;
//    private TextView reloadTv;
//    private SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
//    private String navigateList;
//    private boolean isCrete=true;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_collect);
//        if(!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);//注册eventBus
//        }
//        bindView();
//        initRefresh();
//        initRecycle();
//        ActionBarUtil.initMainActionbar(this,findViewById(R.id.find_action_bar_linear));
//    }
//
//    @Override
//    protected void init() {
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);//取消eventBus注册
//    }
//    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void updateCollect(CollectEvenModel collectEvenModel){
//        int movieId = collectEvenModel.getMovieId();
//        boolean collect = collectEvenModel.isCollect();
//        for (int i = 0; i < homeMovieModelArrayList.size(); i++) {
//            HomeMovieModel homeMovieModel = homeMovieModelArrayList.get(i);
//            if(movieId==homeMovieModel.getMovieId()){
// /*               if(collect){
//                    homeMovieModel.setIsCollection(1);
//                }else {
//                    homeMovieModel.setIsCollection(0);
//                }*/
//                homeMovieModelArrayList.remove(homeMovieModel);
//            }
//        }
//        homeMovieAdapter.notifyDataSetChanged();
//        if(homeMovieModelArrayList.size()==0){
//            nodataLinear.setVisibility(View.VISIBLE);
//        }else {
//            nodataLinear.setVisibility(View.GONE);
//        }
//    }
//    private void initRecycle() {
//        homeMovieAdapter= new MovieListAdapter(homeMovieModelArrayList,this);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setAdapter(homeMovieAdapter);
//        getFavoriteList(pageNum,false,false);
//        homeMovieAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                if(! IntentUtil.isLogin(CollectActivity.this)){
//                    IntentUtil.toLogin(CollectActivity.this);
//                }
//                else {
//                    HomeMovieModel homeMovieModel = homeMovieModelArrayList.get(position);
//                    LogUtils.e(""+homeMovieModel.getChannelId());
//                    LiveRoomEntity.DataBean.MoviesBean moviesBean = new LiveRoomEntity.DataBean.MoviesBean();
//                    moviesBean.setClassificationId(homeMovieModel.getChannelId());
//                    moviesBean.setId(homeMovieModel.getMovieId());
//                    moviesBean.setMovieName(homeMovieModel.getMovieName());
//                    moviesBean.setMoviePhoto(homeMovieModel.getImageUrl());
//                    moviesBean.setCpId(homeMovieModel.getCpId());
//                    moviesBean.setIsCollection(homeMovieModel.getIsCollection());
//                    moviesBean.setNetworkUrl(homeMovieModel.getNetworkUrl());
//                    moviesBean.setUploadUrl(homeMovieModel.getUploadUrl());
//                    System.out.println(Utils.getString(R.string.点击时的电影id=  )+homeMovieModel.getMovieId()+"   isCollect =  "+ homeMovieModel.getIsCollection());
//                    Intent intent = new Intent();
//                    intent.setClass(CollectActivity.this,LiveRoomActivity.class);
//                    intent.putExtra(MOVIEBEAN,moviesBean);
//                    intent.putExtra(SELECTTYPE,3);
//                    startActivity(intent);
//                }
//
//            }
//        });
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if(isCrete){
//            isCrete=false;
//            return;
//        }
////        getFavoriteList(pageNum,true,false);
//    }
//
//    private void getFavoriteList(int pageNo,boolean isRefresh,boolean isLoadmore) {
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("pageNo",pageNo);
//        data.put("pageSize",10);
//        HttpApiUtils.showLoadRequest(this, null, RequestUtil.MINE_FAVORATE, data, loadingLinear, errorLinear, reloadTv, (View) refreshLayout, isLoadmore, isRefresh, new HttpApiUtils.OnRequestLintener() {
//            @Override
//            public void onSuccess(String result, Headers headers) {
//                navigateList= SharePreferencesUtil.getString(CollectActivity.this,"navigateList","");
//                JSONObject jsonObject1 = JSONObject.parseObject(navigateList);
//                JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
//                JSONObject object = JSONObject.parseObject(result).getJSONObject("data");
//                int totalSize = object.getInteger("totalSize");
//                JSONArray jsonArray = object.getJSONArray("data");
//                int size = jsonArray.size();
//                RefreshUtil.succse(pageNo,refreshLayout,loadingLinear,nodataLinear,size,isLoadmore,isRefresh,homeMovieModelArrayList);
//                for (int i = 0; i < size; i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    Integer id = jsonObject.getInteger("id");
//                    String name = jsonObject.getString("name");
//                    String moviePhoto = jsonObject.getString("moviePhoto");
//                    Integer playTimes = jsonObject.getInteger("playTimes");
//                    String logTime = jsonObject.getString("logTime");
//                    //彩种id
//                    Integer cpId = jsonObject.getInteger("cpId");
//                    String networkUrl = jsonObject.getString("networkUrl");
//                    String uploadUrl = jsonObject.getString("uploadUrl");
//                    Integer isCollection = jsonObject.getInteger("isCollection");
//                    String imageUrl = Utils.getImageUrl(moviePhoto,  sp.getImageDomin());
//                    int lotterySize = lotteryArray.size();
//                    for (int j = 0; j < lotterySize; j++) {
//                        JSONObject lotteryJSONObject = lotteryArray.getJSONObject(j);
//                        Integer lotteryId = lotteryJSONObject.getInteger("id");
//                        if(lotteryId==cpId){
////                                HomeMovieModel typename = new HomeMovieModel(imageUrl, lotteryJSONObject.getString("typename"), channelName, playTimes, movieName, id);
//                            HomeMovieModel homeMovieModel = new HomeMovieModel();
//                            homeMovieModel.setImageUrl(imageUrl);
//                            homeMovieModel.setMovieName(name);
//                            homeMovieModel.setWatchNum(playTimes+"");
//                            homeMovieModel.setNetworkUrl(networkUrl);
//                            homeMovieModel.setUploadUrl(uploadUrl);
//                            homeMovieModel.setCpId(cpId);
//                            homeMovieModel.setLotteryName(lotteryJSONObject.getString("typename"));
//                            homeMovieModel.setIsCollection(isCollection);
//                            homeMovieModel.setMovieId(id);
//                            homeMovieModelArrayList.add(homeMovieModel);
//                            break;
//                        }
//                    }
//
//                }
//                homeMovieAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFaild(String msg) {
//                RefreshUtil.fail(isRefresh,isLoadmore,pageNo,refreshLayout);
//            }
//        });
//    }
//
//    @Override
//    public void errorRefresh() {
//        super.errorRefresh();
//        pageNum=1;
//        getFavoriteList(pageNum,false,false);
//    }
//
//    private void initRefresh() {
//        RefreshUtil.initRefreshLoadMore(new SoftReference<>(this), refreshLayout, new RefreshUtil.OnRefreshLoadMoreLintener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
//                pageNum=1;
//                getFavoriteList(pageNum,true,false);
//            }
//
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
//                pageNum++;
//                getFavoriteList(pageNum,false,true);
//            }
//        });
//    }
//
//    private void bindView( ) {
//        recyclerView=findViewById(R.id.find_recycle);
//        refreshLayout=findViewById(R.id.find_refresh);
//        loadingLinear=findViewById(R.id.loading_linear);
//        errorLinear=findViewById(R.id.error_linear);
//        nodataLinear=findViewById(R.id.nodata_linear);
//        reloadTv=findViewById(R.id.reload_tv);
//    }
//
//    @Override
//    public void onNetChange(boolean netWorkState) {
//
//    }
//}
