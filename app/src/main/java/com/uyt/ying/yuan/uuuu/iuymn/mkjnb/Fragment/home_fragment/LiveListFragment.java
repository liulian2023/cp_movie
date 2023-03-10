package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.live_adapter.LiveListAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.LiveClassfyEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop.ChooseAreaPop;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.DockingUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.MessageHead;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Headers;

public class LiveListFragment extends BaseFragment {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.nodata_textview)
    TextView nodata_textview;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.choose_area_linear)
    LinearLayout choose_area_linear;
    @BindView(R.id.choose_area_tv)
    TextView choose_area_tv;
    @BindView(R.id.choose_area_iv)
    ImageView choose_area_iv;
    @BindView(R.id.live_list_recycler)
    RecyclerView live_list_recycler;
    LiveListAdapter liveListAdapter;
    TextView change_live_data_tv;
    RecyclerView live_recycler_foot_recycler;
    LiveListAdapter footAdapter;
    LinearLayout foot_nodata_linear;
    TextView foot_nodata_textview;
    ArrayList<LiveEntity.AnchorMemberRoomListBean> footBeanList = new ArrayList<>();
    ArrayList<LiveEntity.AnchorMemberRoomListBean> liveBeanList = new ArrayList<>();
    int pageNo=1;
    LiveClassfyEntity.CategoryListBean categoryListBean;
    private Unbinder mUnbinder;
    private String navigateList;
    private int pageSize =16;
    private int footPageSize =10;
    private int footPageNo=0;
    private ArrayList<String> pageNoList;

    private ArrayList<String> canUsePageNoList = new ArrayList<>();
    private int totalsize;
    private ChooseAreaPop chooseAreaPop;
    //?????????????????????, chooseAreaPop??????????????????????????????true,???????????????????????????.data??????????????????
    private boolean hasChooseArea = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_list, container, false);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mUnbinder = ButterKnife.bind(this, view);
        getArgumentsData();
        initRecycle();
        initRefresh();
        return view;
    }

    private void getArgumentsData() {
        categoryListBean = (LiveClassfyEntity.CategoryListBean) getArguments().getSerializable("categoryListBean");
        navigateList= SharePreferencesUtil.getString(getContext(),"navigateList","");
        /**
         *???????????????, ??????????????????linear ????????????
         */
        if(!categoryListBean.getArea().equals(CommonStr.AREA_DEFAULT_VALUE)){
            choose_area_linear.setVisibility(View.VISIBLE);
            String area = categoryListBean.getArea();
            choose_area_tv.setText(area.equals(Utils.getString(R.string.??????))||area.equals(Utils.getString(R.string.??????))?Utils.getString(R.string.??????):area);
        }else {
            choose_area_linear.setVisibility(View.GONE);
        }
        if(categoryListBean.getCategoryId().equals("-1")){
            nodata_textview.setText(Utils.getString(R.string.????????????????????????));
        }else {
            nodata_textview.setText(Utils.getString(R.string.???????????????????????????));
        }
    }


    public static LiveListFragment newInstance(int position, LiveClassfyEntity.CategoryListBean categoryListBean){
        LiveListFragment liveListFragment = new LiveListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putSerializable("categoryListBean",categoryListBean);
        liveListFragment.setArguments(bundle);
        return liveListFragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        pageNo=1;
        if(StringMyUtil.isNotEmpty(navigateList)){
            requestLiveData(false,false);
        }else {
            Utils.docking(Utils.getNavigateListMap(0), RequestUtil.REQUEST_01dhnew, -1, new DockingUtil.ILoaderListener() {
                @Override
                public void success(String content) {
                    navigateList=content;
                    SharePreferencesUtil.putString(getContext(),"navigateList",content);
                    requestLiveData(false,false);

                }
                @Override
                public void failed(MessageHead messageHead) {
                }
            });
        }
    }

    private void requestLiveData(boolean isRefresh, boolean isLoadMore) {
        if(categoryListBean==null){
         return;
        }
        String categoryId = categoryListBean.getCategoryId();
        String area = categoryListBean.getArea();
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",pageNo);
        data.put("pageSize",pageSize);
        /**
         * ???????????????????????????,????????????????????????????????????
         */
        if(hasChooseArea){
            String chooseAreaStr = choose_area_tv.getText().toString();
            chooseAreaStr = chooseAreaStr.equals(Utils.getString(R.string.??????))?"":chooseAreaStr;
            data.put("area", chooseAreaStr);
        }else {
            /**
             *  ??????????????????id????????????
             */
            if(!categoryId.equals(CommonStr.CATEGORY_DEFAULT_VALUE)){
                data.put("categoryId", categoryId);
            }
            /**
             * ??????????????????, ???????????????????????????????????????(?????????????????????????????????hasChooseArea???true?????????)
             */
            if(!area.equals(CommonStr.AREA_DEFAULT_VALUE)){
                area = area.equals(Utils.getString(R.string.??????))||area.equals(Utils.getString(R.string.??????))?"":area;//????????????ip??????????????????. ????????????????????????????????????, ?????????????????????????????????
                data.put("area", area);
            }
        }
        HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.LIVEROOM_LIST, data, loading_linear, error_linear, reload_tv, refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//??????json
                JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
//                liveBeanList.clear();
                LiveEntity LiveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.AnchorMemberRoomListBean> anchorMemberRoomList = LiveEntity.getAnchorMemberRoomList();
                int size = anchorMemberRoomList.size();
                if(categoryId.equals("-1")){
                    //????????????. ????????????????????????, ?????????????????????????????????
                    RefreshUtil.success(pageNo,refresh,loading_linear,null,size,isLoadMore,isRefresh,liveBeanList);
                }else {
                    RefreshUtil.success(pageNo,refresh,loading_linear,nodata_linear,size,isLoadMore,isRefresh,liveBeanList);
                }
                for (int i = 0; i < size; i++) {
                    LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = anchorMemberRoomList.get(i);
                    long cpId = anchorMemberRoomListBean.getCpId();
                    for (int j = 0; j < lotteryArray.size(); j++) {
                        JSONObject object = lotteryArray.getJSONObject(j);
                        long lotteryId = object.getLong("id");
                        if(lotteryId==cpId){
                            String typename = object.getString("typename");
                            String game = object.getString("game");
                            anchorMemberRoomListBean.setGame(game);
                            anchorMemberRoomListBean.setLotteryName(typename);
                            if(!liveBeanList.contains(anchorMemberRoomListBean)){
                                liveBeanList.add(anchorMemberRoomListBean);
                            }
                            break;
                        }
                    }
                }
                liveListAdapter.setList(liveBeanList);

                /**
                 * ?????????????????????????????????, ???????????? ,?????????????????????
                 */
                    if(categoryId.equals("-1")&&pageNo==1&&size<16){
                        int footerLayoutCount = liveListAdapter.getFooterLayoutCount();
                        if(footerLayoutCount==0){
                            View footView = LayoutInflater.from(getContext()).inflate(R.layout.live_collect_recycler_foot_layout,null);
                            initFootView(footView);
                            liveListAdapter.addFooterView(footView);
                        }
                        if(size==0){
                            foot_nodata_linear.setVisibility(View.VISIBLE);
                        }else {
                            foot_nodata_linear.setVisibility(View.GONE);
                        }
                        requestFootLiveData(true);
                    }else {
                        if(size!=0){
                            //???????????????????????????,?????????????????????size???0?????????????????????
                            liveListAdapter.removeAllFooterView();
                        }
                    }
            }

            @Override
            public void onFailed(String msg) {
            RefreshUtil.fail(isRefresh,isLoadMore,pageNo,refresh);
            }
        });
    }

    private void requestFootLiveData(boolean scrollToFirstPosition) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageNo",footPageNo);
        data.put("pageSize",footPageSize);
        data.put("categoryId", "-5");
        HttpApiUtils.CPnormalRequest(getActivity(), LiveListFragment.this, RequestUtil.LIVEROOM_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                footBeanList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(navigateList);//??????json
                JSONArray lotteryArray = jsonObject1.getJSONArray("gameClassList");
                LiveEntity LiveEntity = JSONObject.parseObject(result, LiveEntity.class);
                totalsize = LiveEntity.getTotalsize();
                footPageNo = LiveEntity.getPageNo();
                List<LiveEntity.AnchorMemberRoomListBean> anchorMemberRoomList = LiveEntity.getAnchorMemberRoomList();
                int size = anchorMemberRoomList.size();
                for (int i = 0; i < size; i++) {
                    LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = anchorMemberRoomList.get(i);
                    long cpId = anchorMemberRoomListBean.getCpId();
                    for (int j = 0; j < lotteryArray.size(); j++) {
                        JSONObject object = lotteryArray.getJSONObject(j);
                        long lotteryId = object.getLong("id");
                        if(lotteryId==cpId){
                            String typename = object.getString("typename");
                            anchorMemberRoomListBean.setLotteryName(typename);
                            String game = object.getString("game");
                            anchorMemberRoomListBean.setGame(game);
                            if(!footBeanList.contains(anchorMemberRoomListBean)){
                                footBeanList.add(anchorMemberRoomListBean);
                            }
                            break;
                        }
                    }
                    }
                footAdapter.setList(footBeanList);
                /**
                 * ????????????????????????, ?????????????????????????????????, ???????????????????????????item, ??????????????????????????????,?????????????????????????????????
                 */
                    if(scrollToFirstPosition&&liveBeanList.size()!=0){
                        live_list_recycler.scrollToPosition(0);
                    }
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
    @OnClick({R.id.choose_area_linear})
    public void  onClick(View view){
        switch (view.getId()){
            case R.id.choose_area_linear:
                initChooseAreaPop();
                chooseAreaPop.showAsDropDown(choose_area_linear, 0,0,Gravity.BOTTOM);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.chat_img_rotate_start_anim);
                animation.setFillAfter(true);//???????????????,????????????????????????
                choose_area_iv.startAnimation(animation);
                break;
            default:
                break;
        }
    }

    /**
     * ????????????pop
     */
    private void initChooseAreaPop() {
        if(chooseAreaPop == null){
            chooseAreaPop =new ChooseAreaPop(getContext(),true);
            chooseAreaPop.setOnPopItemClick(new BasePopupWindow2.OnRecycleItemClick() {
                @Override
                public void onPopItemClick(View view, int position) {
                    if(view instanceof TextView){
                        TextView textView = (TextView) view;
                        choose_area_tv.setText(textView.getText());
                    }
                     hasChooseArea =true;
                    chooseAreaPop.dismiss();
                    requestLiveData(false,false);
                }
            });
            chooseAreaPop.setmOnDissmissListener(new BasePopupWindow2.OnDissmissListener() {
                @Override
                public void onDismiss() {
                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.img_rotate_end_anim);
                    animation.setFillAfter(true);//???????????????,????????????????????????
                    choose_area_iv.startAnimation(animation);
                }
            });
        }
    }

    /**
     * ????????????????????????
     * @param followEvenModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateIsFollow(FollowEvenModel followEvenModel){
        /**
         * ???????????????????????????
         */
        for (int i = 0; i < liveBeanList.size(); i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = liveBeanList.get(i);
            if((anchorMemberRoomListBean.getAnchorMemberId()+"").equals(followEvenModel.getId())){
                if(followEvenModel.isFollow()){
                    anchorMemberRoomListBean.setIsFollow(1);
                }else {
                    anchorMemberRoomListBean.setIsFollow(0);
                }
                break;
            }
        }
        liveListAdapter.notifyDataSetChanged();

        /**
         * ??????foot recyclerView ??????????????????
         */
        for (int i = 0; i < footBeanList.size(); i++) {
            LiveEntity.AnchorMemberRoomListBean anchorMemberRoomListBean = footBeanList.get(i);
            if((anchorMemberRoomListBean.getAnchorMemberId()+"").equals(followEvenModel.getId())){
                if(followEvenModel.isFollow()){
                    anchorMemberRoomListBean.setIsFollow(1);
                }else {
                    anchorMemberRoomListBean.setIsFollow(0);
                }
                break;
            }
        }
        footAdapter.notifyDataSetChanged();
    }
    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestLiveData(false,false);
    }

    private void initRefresh() {
        RefreshUtil.initRefreshLoadMore(new SoftReference<>(getContext()), refresh, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNo=1;
                requestLiveData(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNo++;
                requestLiveData(false,true);
            }
        });
    }

    private void initRecycle() {
        liveListAdapter =new LiveListAdapter(this);
        live_list_recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        live_list_recycler.setAdapter(liveListAdapter);
        liveListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(!LoginIntentUtil.isLogin(getContext())){
                    LoginIntentUtil.toLogin(getActivity());
                }else {
                    LiveEntity.AnchorMemberRoomListBean anchorRoomBean = liveBeanList.get(position);
                    String categoryId = categoryListBean.getCategoryId();
                    String area = categoryListBean.getArea();
                    int curPage = ((position+1) + pageSize-1)/pageSize;
                        RouteUtils.start2LiveActivity(getContext(), anchorRoomBean, categoryId,area,curPage);
                    }
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), LiveTestActivity.class);
//                    startActivity(intent);
            }
        });
    }

    private void initFootView(View footView) {
        foot_nodata_textview = footView.findViewById(R.id.nodata_textview);
        foot_nodata_textview.setText(Utils.getString(R.string.????????????????????????));
        change_live_data_tv = footView.findViewById(R.id.change_live_data_tv);
        foot_nodata_linear = footView.findViewById(R.id.nodata_linear);
        live_recycler_foot_recycler = footView.findViewById(R.id.live_recycler_foot_recycler);
        live_recycler_foot_recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        live_recycler_foot_recycler.setNestedScrollingEnabled(false);
        live_recycler_foot_recycler.setHasFixedSize(true);
        live_recycler_foot_recycler.setFocusable(false);//????????????????????????????????????, ???????????????????????????????????????, ?????????????????????????????????????????????????????????
        footAdapter = new LiveListAdapter(this);
        live_recycler_foot_recycler.setAdapter(footAdapter);

        change_live_data_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * ???????????????
                 */
                randomPage();
                requestFootLiveData(false);
            }
        });
        footAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(!LoginIntentUtil.isLogin(getContext())){
                    LoginIntentUtil.toLogin(getActivity());
                }else {
                    LiveEntity.AnchorMemberRoomListBean anchorRoomBean = footBeanList.get(position);
                    RouteUtils.start2LiveActivity(getContext(), anchorRoomBean,"-5",CommonStr.AREA_DEFAULT_VALUE,footPageNo);
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), LiveTestActivity.class);
//                    startActivity(intent);
                }
            }
        });

    }

    /**
     * ??????????????????
     */
    private  void randomPage() {
        if (canUsePageNoList.size() == 0) {
            int pageSize = footPageSize;
            int totalPage = (int)(totalsize+pageSize-1)/pageSize;
            for (int i = 1; i <= totalPage; i++) {
                if (i != footPageNo) {
                canUsePageNoList.add(i+"");
                }
            }
        }
        if(canUsePageNoList.size()==0){
            showToast(getString(R.string.??????????????????));
            return;
        }

        int randomIndex = new Random().nextInt(canUsePageNoList.size());
        String page = canUsePageNoList.get(randomIndex);
        footPageNo = Integer.parseInt(page);
        canUsePageNoList.remove(page);
    }




}
