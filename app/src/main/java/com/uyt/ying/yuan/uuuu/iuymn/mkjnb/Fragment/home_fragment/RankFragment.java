package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.home_fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.RankActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.RankAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RankEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ActionBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Headers;

import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr.DUSHEN;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr.FUHAO;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr.GONGXIAN;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr.MINGXING;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr.YAOQING;
import static com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr.ZHUANXIANG;

public class RankFragment extends BaseFragment {
    @BindView(R.id.rank_refresh)
    SmartRefreshLayout rank_refresh;
    @BindView(R.id.rank_recycler)
    RecyclerView rank_recycler;
    @BindView(R.id.radioGroup_linear)
    LinearLayout radioGroup_linear;
    @BindView(R.id.day_rank_rbt)
    RadioButton day_rank_rbt;
    @BindView(R.id.week_rank_rbt)
    RadioButton week_rank_rbt;
    @BindView(R.id.month_rank_rbt)
    RadioButton month_rank_rbt;
    ConstraintLayout rankLoadingLinear;
    LinearLayout rankErrorLinear;
    TextView rankReloadTv;

    ImageView one_title_iv;
    TextView one_name_tv;
    ImageView one_level_iv;
    TextView one_amount_tv;
    TextView one_win_rate_tv;
    Guideline one_guideline;
    LinearLayout one_onlive_linear;

    ImageView two_title_iv;
    TextView two_name_tv;
    ImageView two_level_iv;
    TextView two_amount_tv;
    TextView two_win_rate_tv;
    Guideline two_guideline;
    LinearLayout two_onlive_linear;

    ImageView three_title_iv;
    TextView three_name_tv;
    ImageView three_level_iv;
    TextView three_amount_tv;
    TextView three_win_rate_tv;
    Guideline three_guideline;
    LinearLayout three_onlive_linear;

    ImageView head_big_biv;
    int position;
    RankAdapter rankAdapter ;
    ArrayList<RankEntity.RankingListBean> rankEntityArrayList = new ArrayList<>();
    int current=1;
    int type=0;
    int dayRbtClickCount=0;
    private View view;
    private Unbinder unbinder;
    private String title;
    private int typeId;
    private RankEntity.RankingListBean oneBeen;
    private RankEntity.RankingListBean twobean;
    private RankEntity.RankingListBean threeBeen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_in_come_rank, container, false);
        unbinder = ButterKnife.bind(this, view);
        getArgmentsData();
        initRecycler();
        inirRefresh();
        return view;
    }

    private void inirRefresh() {
        rank_refresh.setEnableLoadMore(false);
        rank_refresh.setEnableHeaderTranslationContent(false);//内容不偏移
        rank_refresh.setRefreshHeader(new WaveSwipeHeader(getContext()));
        rank_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestRankList(true);
            }
        });

    }

    private void getArgmentsData() {
        position = getArguments().getInt("position");
        title = getArguments().getString("title");
        if(title.equals(MINGXING)){
            typeId=22;
        }else if(title.equals(GONGXIAN)){
            typeId=21;
        }else if(title.equals(FUHAO)){
            typeId=23;
        }else if(title.equals(DUSHEN)){
            typeId=20;
        }else if(title.equals(YAOQING)){
            typeId=24;
        }else {
            typeId=26;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    public static RankFragment newInstance(int positon,String title){
        RankFragment rankFragment = new RankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",positon);
        bundle.putString("title",title);
        rankFragment.setArguments(bundle);
        return rankFragment;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if(getActivity() instanceof RankActivity){
            ColorStateList colorStateList;
            RankActivity rankActivity = (RankActivity) getActivity();
            if(title.equals(MINGXING)){
                colorStateList = ContextCompat.getColorStateList(getContext(),R.color.mingxin_rank_button_clolor);
                day_rank_rbt.setTextColor(colorStateList);
                week_rank_rbt.setTextColor(colorStateList);
                month_rank_rbt.setTextColor(colorStateList);
                one_amount_tv.setText(Utils.getString(R.string.礼物 - - -));
                two_amount_tv.setText(Utils.getString(R.string.礼物 - - -));
                three_amount_tv.setText(Utils.getString(R.string.礼物 - - -));

                one_onlive_linear.setVisibility(View.VISIBLE);
                two_onlive_linear.setVisibility(View.VISIBLE);
                three_onlive_linear.setVisibility(View.VISIBLE);

                one_level_iv.setVisibility(View.GONE);
                two_win_rate_tv.setVisibility(View.GONE);
                two_level_iv.setVisibility(View.GONE);
                three_level_iv.setVisibility(View.GONE);
                three_win_rate_tv.setVisibility(View.GONE);
                one_win_rate_tv.setVisibility(View.GONE);
                two_guideline.setGuidelinePercent(0.428f);
                three_guideline.setGuidelinePercent(0.428f);
                one_guideline.setGuidelinePercent(0.376f);
                head_big_biv.setImageResource(R.drawable.mxb_bg);
                rankActivity.starusbar_view.setImageResource(R.drawable.mxbdingbu_bg);
                radioGroup_linear.setVisibility(View.VISIBLE);
            }else if(title.equals(GONGXIAN)){
                colorStateList = ContextCompat.getColorStateList(getContext(),R.color.gongxian_rank_button_clolor);
                day_rank_rbt.setTextColor(colorStateList);
                week_rank_rbt.setTextColor(colorStateList);
                month_rank_rbt.setTextColor(colorStateList);

                one_onlive_linear.setVisibility(View.GONE);
                two_onlive_linear.setVisibility(View.GONE);
                three_onlive_linear.setVisibility(View.GONE);

                head_big_biv.setImageResource(R.drawable.gxb_bg);
                rankActivity.starusbar_view.setImageResource(R.drawable.gx_db_bg);

                three_win_rate_tv.setVisibility(View.GONE);
                two_win_rate_tv.setVisibility(View.GONE);
                one_win_rate_tv.setVisibility(View.GONE);
                radioGroup_linear.setVisibility(View.VISIBLE);
                one_level_iv.setVisibility(View.VISIBLE);
                two_level_iv.setVisibility(View.VISIBLE);
                three_level_iv.setVisibility(View.VISIBLE);
                one_amount_tv.setText(Utils.getString(R.string.礼物 - - -));
                two_amount_tv.setText(Utils.getString(R.string.礼物 - - -));
                three_amount_tv.setText(Utils.getString(R.string.礼物 - - -));
                three_guideline.setGuidelinePercent(0.428f);
                two_guideline.setGuidelinePercent(0.428f);
                one_guideline.setGuidelinePercent(0.376f);
            }else if(title.equals(FUHAO)){
                colorStateList = ContextCompat.getColorStateList(getContext(),R.color.fuhao_rank_button_clolor);
                day_rank_rbt.setTextColor(colorStateList);
                week_rank_rbt.setTextColor(colorStateList);
                month_rank_rbt.setTextColor(colorStateList);

                one_onlive_linear.setVisibility(View.GONE);
                two_onlive_linear.setVisibility(View.GONE);
                three_onlive_linear.setVisibility(View.GONE);

                head_big_biv.setImageResource(R.drawable.fhb_bg);
                rankActivity.starusbar_view.setImageResource(R.drawable.fh_db_bg);
                three_win_rate_tv.setVisibility(View.GONE);
                two_win_rate_tv.setVisibility(View.GONE);
                one_win_rate_tv.setVisibility(View.GONE);
                radioGroup_linear.setVisibility(View.VISIBLE);
                one_level_iv.setVisibility(View.VISIBLE);
                two_level_iv.setVisibility(View.VISIBLE);
                three_level_iv.setVisibility(View.VISIBLE);
                one_amount_tv.setText(Utils.getString(R.string.奖金:****));
                two_amount_tv.setText(Utils.getString(R.string.奖金:****));
                three_amount_tv.setText(Utils.getString(R.string.奖金:****));
                three_guideline.setGuidelinePercent(0.428f);
                two_guideline.setGuidelinePercent(0.428f);
                one_guideline.setGuidelinePercent(0.376f);

            }else if(title.equals(DUSHEN)){
                colorStateList = ContextCompat.getColorStateList(getContext(),R.color.dushen_rank_button_clolor);

                day_rank_rbt.setTextColor(colorStateList);
                week_rank_rbt.setTextColor(colorStateList);
                month_rank_rbt.setTextColor(colorStateList);

                one_onlive_linear.setVisibility(View.GONE);
                two_onlive_linear.setVisibility(View.GONE);
                three_onlive_linear.setVisibility(View.GONE);

                head_big_biv.setImageResource(R.drawable.dsb_bg);
                rankActivity.starusbar_view.setImageResource(R.drawable.dsbdingbu_bg);
                //胜率tv 初始化
                three_win_rate_tv.setVisibility(View.VISIBLE);
                two_win_rate_tv.setVisibility(View.VISIBLE);
                one_win_rate_tv.setVisibility(View.VISIBLE);
                //日榜 周榜... 初始化
                radioGroup_linear.setVisibility(View.VISIBLE);
                //等级图标
                one_level_iv.setVisibility(View.VISIBLE);
                two_level_iv.setVisibility(View.VISIBLE);
                three_level_iv.setVisibility(View.VISIBLE);
                //礼物  红包 场次初始化
                one_amount_tv.setText(Utils.getString(R.string.场次 - - -));
                two_amount_tv.setText(Utils.getString(R.string.场次 - - -));
                three_amount_tv.setText(Utils.getString(R.string.场次 - - -));

                three_win_rate_tv.setText(Utils.getString(R.string.胜率: - - -));
                two_win_rate_tv.setText(Utils.getString(R.string.胜率: - - -));
                one_win_rate_tv.setText(Utils.getString(R.string.胜率: - - -));
                //专享 邀请 布局高度初始化
                three_guideline.setGuidelinePercent(0.428f);
                two_guideline.setGuidelinePercent(0.428f);
                one_guideline.setGuidelinePercent(0.356f);

            }else if(title.equals(YAOQING)){
                head_big_biv.setImageResource(R.drawable.yqb_bg);
                rankActivity.starusbar_view.setImageResource(R.drawable.yqdingbu_bg);

                one_onlive_linear.setVisibility(View.GONE);
                two_onlive_linear.setVisibility(View.GONE);
                three_onlive_linear.setVisibility(View.GONE);

                three_win_rate_tv.setVisibility(View.GONE);
                two_win_rate_tv.setVisibility(View.GONE);
                one_win_rate_tv.setVisibility(View.GONE);
                radioGroup_linear.setVisibility(View.GONE);
                one_level_iv.setVisibility(View.VISIBLE);
                two_level_iv.setVisibility(View.VISIBLE);
                three_level_iv.setVisibility(View.VISIBLE);
                one_amount_tv.setText(Utils.getString(R.string.红包 - - -));
                two_amount_tv.setText(Utils.getString(R.string.红包 - - -));
                three_amount_tv.setText(Utils.getString(R.string.红包 - - -));


                three_guideline.setGuidelinePercent(0.328f);
                two_guideline.setGuidelinePercent(0.328f);
                one_guideline.setGuidelinePercent(0.276f);
            }else if(title.equals(ZHUANXIANG)){

                head_big_biv.setImageResource(R.drawable.zxb_bg);
                rankActivity.starusbar_view.setImageResource(R.drawable.zxdingbu_bg);
                three_win_rate_tv.setVisibility(View.GONE);
                two_win_rate_tv.setVisibility(View.GONE);
                one_win_rate_tv.setVisibility(View.GONE);
                radioGroup_linear.setVisibility(View.GONE);

                one_onlive_linear.setVisibility(View.GONE);
                two_onlive_linear.setVisibility(View.GONE);
                three_onlive_linear.setVisibility(View.GONE);

                one_amount_tv.setText(Utils.getString(R.string.红包 - - -));
                two_amount_tv.setText(Utils.getString(R.string.红包 - - -));
                three_amount_tv.setText(Utils.getString(R.string.红包 - - -));
                three_guideline.setGuidelinePercent(0.328f);
                two_guideline.setGuidelinePercent(0.328f);
                one_guideline.setGuidelinePercent(0.276f);
            }
        }

        requestRankList(false);

    }


    private void initRecycler() {
        rankAdapter = new RankAdapter(R.layout.rank_recycler_item,rankEntityArrayList,this);
        rank_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        rank_recycler.setAdapter(rankAdapter);
/*        rankAdapter.setAnimationEnable(true);
        rankAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        rankAdapter.setAnimationFirstOnly(false);*/
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.rank_recycler_head,null);
        bindHeadView(headView);
        rankAdapter.addHeaderView(headView);
        day_rank_rbt.performClick();
/*        View statusView = LayoutInflater.from(getContext()).inflate(R.layout.status_layout,null);
        bindStatusView(statusView);
        rankAdapter.addHeaderView(statusView);*/
        rankAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                RankEntity.RankingListBean rankingListBean = rankEntityArrayList.get(position);
                int viewType = rankingListBean.getViewType();
                if(viewType==1){
                    //明星榜才响应点击事件
                    String status = rankingListBean.getStatus();
                    if(status.equals("1")){
                        // 跳转直播间
                        if(!LoginIntentUtil.isLogin(getContext())){
                            LoginIntentUtil.toLogin(getActivity());
                        }else {
                            RouteUtils.start2LiveActivity(getContext(),rankingListBean,"", CommonStr.AREA_DEFAULT_VALUE,1);
                        }
                    }else {
                        showToast(Utils.getString(R.string.主播已下播));
                    }
                }
            }
        });
        rank_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //头部视图消失时,改变顶部radioGroup的背景色(头部的position为0)
                RecyclerView.LayoutManager layoutManager = rank_recycler.getLayoutManager();
                if(layoutManager instanceof LinearLayoutManager){
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if(firstVisibleItemPosition!=0){
                        radioGroup_linear.setBackgroundColor(Color.parseColor("#FD5F82"));
                        if(title.equals(MINGXING)){
                            ActionBarUtil.setGradientView(getActivity(),radioGroup_linear,R.color.mingxing_start_color,R.color.mingxing_end_color);
                        }else if(title.equals(GONGXIAN)){
                            ActionBarUtil.setGradientView(getActivity(),radioGroup_linear,R.color.gongxian_start_color,R.color.gongxian_end_color);
                        }else if(title.equals(FUHAO)){
                            ActionBarUtil.setGradientView(getActivity(),radioGroup_linear,R.color.fuhao_start_color,R.color.fuhao_end_color);
                        }else if(title.equals(DUSHEN)){
                            ActionBarUtil.setGradientView(getActivity(),radioGroup_linear,R.color.dushen_start_color,R.color.dushen_end_color);
                        }else if(title.equals(YAOQING)){
                            ActionBarUtil.setGradientView(getActivity(),radioGroup_linear,R.color.yaoqing_start_color,R.color.yaoqing_end_color);
                        }else if(title.equals(ZHUANXIANG)){
                            ActionBarUtil.setGradientView(getActivity(),radioGroup_linear,R.color.zhuanxiang_start_color,R.color.zhuanxiang_end_color);
                        }

                    }else {
                        radioGroup_linear.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
    }

    private void bindHeadView(View headView) {

        one_title_iv = headView.findViewById(R.id.one_title_iv);
        one_name_tv = headView.findViewById(R.id.one_name_tv);
        one_level_iv = headView.findViewById(R.id.one_level_iv);
        one_amount_tv = headView.findViewById(R.id.one_amount_tv);
        one_win_rate_tv = headView.findViewById(R.id.one_win_rate_tv);
        one_guideline = headView.findViewById(R.id.one_guideline);
        one_onlive_linear = headView.findViewById(R.id.one_onlive_linear);

        two_title_iv = headView.findViewById(R.id.two_title_iv);
        two_name_tv = headView.findViewById(R.id.two_name_tv);
        two_level_iv = headView.findViewById(R.id.two_level_iv);
        two_amount_tv = headView.findViewById(R.id.two_amount_tv);
        two_win_rate_tv = headView.findViewById(R.id.two_win_rate_tv);
        two_guideline = headView.findViewById(R.id.two_guideline);
        two_onlive_linear = headView.findViewById(R.id.two_onlive_linear);

        three_title_iv = headView.findViewById(R.id.three_title_iv);
        three_name_tv = headView.findViewById(R.id.three_name_tv);
        three_level_iv = headView.findViewById(R.id.three_level_iv);
        three_amount_tv = headView.findViewById(R.id.three_amount_tv);
        head_big_biv = headView.findViewById(R.id.head_big_biv);
        three_win_rate_tv = headView.findViewById(R.id.three_win_rate_tv);
        three_guideline = headView.findViewById(R.id.three_guideline);
        three_onlive_linear = headView.findViewById(R.id.three_onlive_linear);

        rankLoadingLinear =headView.findViewById(R.id.rank_loading_linear);
        rankErrorLinear = headView.findViewById(R.id.rank_error_linear);
        rankReloadTv = headView.findViewById(R.id.rank_reload_tv);

        one_title_iv.setOnClickListener(this::onClick);
        two_title_iv.setOnClickListener(this::onClick);
        three_title_iv.setOnClickListener(this::onClick);
    }



    @OnClick({R.id.day_rank_rbt,R.id.week_rank_rbt,R.id.month_rank_rbt})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.day_rank_rbt:
                dayRbtClickCount++;
                if(dayRbtClickCount!=1){
                    initType(0);
                }
                break;
            case R.id.week_rank_rbt:
                initType(20);
                break;
            case R.id.month_rank_rbt:
                initType(2);
                break;
            case R.id.reload_tv:
                requestRankList(false);
                break;
            case R.id.one_title_iv:
//                if(rankEntityArrayList!=null&&rankEntityArrayList.size()!=0){
                    headSkipToLive(oneBeen);
//                }
                break;
            case R.id.two_title_iv:
                if(rankEntityArrayList!=null&&rankEntityArrayList.size()>=2){
                    headSkipToLive(twobean);
                }
                break;
            case R.id.three_title_iv:
//                if(rankEntityArrayList!=null&&rankEntityArrayList.size()>=3){
                    headSkipToLive(threeBeen);
//                }
                break;

            default:
                break;
        }
    }

    /**
     * 明星榜头部的头像点击事件
     * @param rankingListBean
     */
    private void headSkipToLive( RankEntity.RankingListBean rankingListBean ) {
        //明星榜才响应点击事件
        if(title.equals(MINGXING)){
            if(!LoginIntentUtil.isLogin(getContext())){
                LoginIntentUtil.toLogin(getActivity());
            }else {
                if(rankingListBean!=null){
                    String status = rankingListBean.getStatus();
                    if(status.equals("1")){
                        RouteUtils.start2LiveActivity(getContext(),rankingListBean,"",CommonStr.AREA_DEFAULT_VALUE,1);
                    }else {
                        showToast(Utils.getString(R.string.主播已下播));
                    }
                }
            }

        }
    }

    private void initType(int type) {
        this.type = type;
        current=1;
        requestRankList(false);
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestRankList(false);
    }

    private void requestRankList(boolean isRefresh) {

            RankEntity.RankingListBean recordsBean = new RankEntity.RankingListBean();
            initRankOne(recordsBean);
            initRankTwo(recordsBean);
            initRankThree(recordsBean);


        HashMap<String, Object> data= new HashMap<>();
        data.put("size",50);
        data.put("type",type);
        data.put("typeId",typeId);
        HttpApiUtils.cpHeadShowLoadRequest(getActivity(), this, RequestUtil.ALL_RANK_LIST, data, rankLoadingLinear, rankErrorLinear, rankReloadTv, rank_refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                RankEntity rankEntity = JSONObject.parseObject(result, RankEntity.class);
                List<RankEntity.RankingListBean> rankingList = rankEntity.getRankingList();
                int size = rankingList.size();
                RefreshUtil.success(current,rank_refresh,rankLoadingLinear,null, size,false,isRefresh,rankEntityArrayList);
                rankEntityArrayList.addAll(rankingList);
                if(size<50){
                    for (int i = size; i < 50; i++) {
                        RankEntity.RankingListBean mineRankEntity = new RankEntity.RankingListBean();
                        mineRankEntity.setRedPrice("0.00");
                        mineRankEntity.setAnchorGift("0.00");
                        mineRankEntity.setZjPrice("0.00");
                        mineRankEntity.setTotalQuYueHBPrice("0.00");
                        mineRankEntity.setTotalZxHBPrice("0.00");
                        mineRankEntity.setZjNum(0);
                        mineRankEntity.setTzNum(1);
                        mineRankEntity.setNickname(Utils.getString(R.string.等待上榜));
                        mineRankEntity.setUserNickName(Utils.getString(R.string.等待上榜));
                        mineRankEntity.setImage("");
                        rankEntityArrayList.add(mineRankEntity);
                    }
                }
                for (int i = 0; i < rankEntityArrayList.size(); i++) {
                    ////        1 明星榜  2贡献榜  3富豪榜 4赌神榜 5邀请棒  6 专享榜
                    if(title.equals(MINGXING)){
                        setBeanViewType(1);
                    }else if(title.equals(GONGXIAN)){
                        setBeanViewType(2);
                    }else if(title.equals(FUHAO)){
                        setBeanViewType(3);
                    }else if(title.equals(DUSHEN)){
                        setBeanViewType(4);
                    }else if(title.equals(YAOQING)){
                        setBeanViewType(5);
                    }else {
                        setBeanViewType(6);
                    }
                }
                initAllData(rankEntityArrayList);
                rankAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,false,1,rank_refresh);
            }
        });
    }

    private void setBeanViewType(int viewType) {
        for (int i = 0; i < rankEntityArrayList.size(); i++) {
            rankEntityArrayList.get(i).setViewType(viewType);
        }
    }

    private void initAllData(List<RankEntity.RankingListBean> recordsBeans) {
           /* RankEntity.RankingListBean*/ oneBeen = recordsBeans.get(0);
            /*RankEntity.RankingListBean*/ twobean = recordsBeans.get(1);
            /*RankEntity.RankingListBean*/ threeBeen = recordsBeans.get(2);
            initRankOne(oneBeen);
            initRankTwo(twobean);
            initRankThree(threeBeen);


        /*
        处理完head中的数据后, 要将head中的3个数据剔除
         */
        for (int i = 0; i < 3; i++) {
            recordsBeans.remove(0);
        }

    }
    private void initRankThree(RankEntity.RankingListBean threeBean) {

        if(title.equals(MINGXING)){
            GlideLoadViewUtil.FLoadTitleView(RankFragment.this, Utils.checkLiveImageUrl(threeBean.getImage()) , three_title_iv);
            three_name_tv.setText(threeBean.getNickname());
            three_amount_tv.setText(threeBean.getRedPrice().contains(Utils.getString(R.string.礼物))?threeBean.getRedPrice():Utils.getString(R.string.礼物: )+threeBean.getRedPrice());
            if(threeBean.getStatus().equals("1")){
                three_onlive_linear.setVisibility(View.VISIBLE);
            }else {
                three_onlive_linear.setVisibility(View.GONE);
            }
        }else{
            GlideLoadViewUtil.FLoadTitleView(RankFragment.this,Utils.checkImageUrl(threeBean.getImage()), three_title_iv);
            if(title.equals(GONGXIAN)){
                three_name_tv.setText(threeBean.getUserNickName());
                three_amount_tv.setText(threeBean.getAnchorGift().contains(Utils.getString(R.string.礼物))?threeBean.getAnchorGift():Utils.getString(R.string.礼物: )+threeBean.getAnchorGift());
                int pointGrade =Integer.parseInt(threeBean.getPointGrade());
                setHeadLevelIv(pointGrade,three_level_iv);
            }else if(title.equals(FUHAO)){
                three_name_tv.setText(threeBean.getNickname());
                three_amount_tv.setText(Utils.getString(R.string.奖金:****));
                setHeadLevelIv(threeBean.getGrade(),three_level_iv);
            }else if(title.equals(DUSHEN)){
                three_name_tv.setText(threeBean.getNickname());
                int zjNum = threeBean.getZjNum();
                int tzNum = threeBean.getTzNum();
                if(StringMyUtil.isEmptyString(zjNum)){
                    three_amount_tv.setText(Utils.getString(R.string.场次: - - - ));
                    three_win_rate_tv.setText(Utils.getString(R.string.胜率: - - - ));
                }else {
                    three_amount_tv.setText(Utils.getString(R.string.场次: )+ tzNum +Utils.getString(R.string.中)+ zjNum);//设置中奖场次
                    if(zjNum==0){
                        three_win_rate_tv.setText(Utils.getString(R.string.胜率： 0.00%));
                    }else {
                        three_win_rate_tv.setText(Utils.getString(R.string.胜率: )+new BigDecimal(Double.parseDouble(zjNum+"")/Double.parseDouble(tzNum+"")*100).setScale(2,BigDecimal.ROUND_HALF_DOWN)+"%");//设置中奖比例
                    }
                }
                setHeadLevelIv(threeBean.getGrade(),three_level_iv);
            }else if(title.equals(YAOQING)){
                three_name_tv.setText(threeBean.getNickname());
                three_amount_tv.setText(threeBean.getTotalQuYueHBPrice().contains(Utils.getString(R.string.红包))?threeBean.getTotalQuYueHBPrice():Utils.getString(R.string.红包: )+threeBean.getTotalQuYueHBPrice());
                setHeadLevelIv(threeBean.getGrade(),three_level_iv);
            }else if(title.equals(ZHUANXIANG)){
                three_name_tv.setText(threeBean.getNickname());
                three_amount_tv.setText(threeBean.getTotalZxHBPrice().contains(Utils.getString(R.string.红包))?threeBean.getTotalZxHBPrice():Utils.getString(R.string.红包: )+threeBean.getTotalZxHBPrice());
                setHeadLevelIv(threeBean.getGrade(),three_level_iv);
            }
        }
    }

    private void setHeadLevelIv(int grade,ImageView level_iv) {
        level_iv.setVisibility(View.VISIBLE);
        grade=grade+1;
        if(grade==0){
            //等于0时.是请求数据前的默认赋值, 显示level 1
//            level_iv.setImageResource(Utils.getLevelDrawble(1));
            GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(1)),level_iv);
        }else {
            GlideLoadViewUtil.LoadNormalView(getContext(),Utils.checkImageUrl(Utils.getLevelUrl(grade)),level_iv);
//            level_iv.setImageResource(Utils.getLevelDrawble(grade));
        }

    }

    private void initRankTwo(RankEntity.RankingListBean twobean) {

        //头像
        if(title.equals(MINGXING)){
            GlideLoadViewUtil.FLoadTitleView(RankFragment.this, Utils.checkLiveImageUrl(twobean.getImage()) , two_title_iv);
            two_name_tv.setText(twobean.getNickname());
            two_amount_tv.setText(twobean.getRedPrice().contains(Utils.getString(R.string.礼物))?twobean.getRedPrice():Utils.getString(R.string.礼物: )+twobean.getRedPrice());
            if(twobean.getStatus().equals("1")){
                two_onlive_linear.setVisibility(View.VISIBLE);
            }else {
                two_onlive_linear.setVisibility(View.GONE);
            }
        }else{
            GlideLoadViewUtil.FLoadTitleView(RankFragment.this,Utils.checkImageUrl(twobean.getImage()), two_title_iv);
            if(title.equals(GONGXIAN)){
                two_name_tv.setText(twobean.getUserNickName());
                two_amount_tv.setText(twobean.getAnchorGift().contains(Utils.getString(R.string.礼物))?twobean.getAnchorGift():Utils.getString(R.string.礼物: )+twobean.getAnchorGift());
                setHeadLevelIv(Integer.parseInt(twobean.getPointGrade()),two_level_iv);
            }else if(title.equals(FUHAO)){
                two_name_tv.setText(twobean.getNickname());
//                two_amount_tv.setText(twobean.getZjPrice());
                two_amount_tv.setText(Utils.getString(R.string.奖金:****));
                setHeadLevelIv(twobean.getGrade(),two_level_iv);
            }else if(title.equals(DUSHEN)){
                two_name_tv.setText(twobean.getNickname());
                int zjNum = twobean.getZjNum();
                int tzNum = twobean.getTzNum();
                if(StringMyUtil.isEmptyString(zjNum)){
                    two_amount_tv.setText(Utils.getString(R.string.场次: - - - ));
                    two_win_rate_tv.setText(Utils.getString(R.string.胜率: - - - ));
                }else {
                    two_amount_tv.setText(Utils.getString(R.string.场次: )+ tzNum +Utils.getString(R.string.中)+ zjNum);//设置中奖场次
                    if(zjNum==0){
                        two_win_rate_tv.setText(Utils.getString(R.string.胜率： 0.00%));
                    }else {
                        two_win_rate_tv.setText(Utils.getString(R.string.胜率: )+new BigDecimal(Double.parseDouble(zjNum+"")/Double.parseDouble(tzNum+"")*100).setScale(2,BigDecimal.ROUND_HALF_DOWN)+"%");//设置中奖比例
                    }
                }
                setHeadLevelIv(twobean.getGrade(),two_level_iv);
            }else if(title.equals(YAOQING)){
                two_name_tv.setText(twobean.getNickname());
                two_amount_tv.setText(twobean.getTotalQuYueHBPrice().contains(Utils.getString(R.string.红包))?twobean.getTotalQuYueHBPrice():Utils.getString(R.string.红包: )+twobean.getTotalQuYueHBPrice());
                setHeadLevelIv(twobean.getGrade(),two_level_iv);
            }else if(title.equals(ZHUANXIANG)){
                two_name_tv.setText(twobean.getNickname());
                two_amount_tv.setText(twobean.getTotalZxHBPrice().contains(Utils.getString(R.string.红包))?twobean.getTotalZxHBPrice():Utils.getString(R.string.红包: )+twobean.getTotalZxHBPrice());
                setHeadLevelIv(twobean.getGrade(),two_level_iv);
            }
        }


    }

    private void initRankOne(RankEntity.RankingListBean oneBeen) {
        //头像
        if(title.equals(MINGXING)){
            GlideLoadViewUtil.FLoadTitleView(RankFragment.this, Utils.checkLiveImageUrl(oneBeen.getImage()) , one_title_iv);
            one_name_tv.setText(oneBeen.getNickname());
            one_amount_tv.setText(oneBeen.getRedPrice().contains(Utils.getString(R.string.礼物))?oneBeen.getRedPrice():Utils.getString(R.string.礼物: )+oneBeen.getRedPrice());
            if(oneBeen.getStatus().equals("1")){
                one_onlive_linear.setVisibility(View.VISIBLE);
            }else {
                one_onlive_linear.setVisibility(View.GONE);
            }
        }else{
            GlideLoadViewUtil.FLoadTitleView(RankFragment.this,Utils.checkImageUrl(oneBeen.getImage()), one_title_iv);
            if(title.equals(GONGXIAN)){
                one_name_tv.setText(oneBeen.getUserNickName());
                one_amount_tv.setText(oneBeen.getAnchorGift().contains(Utils.getString(R.string.礼物))?oneBeen.getAnchorGift():Utils.getString(R.string.礼物: )+oneBeen.getAnchorGift());
                setHeadLevelIv(Integer.parseInt(oneBeen.getPointGrade()),one_level_iv);
            }else if(title.equals(FUHAO)){
                one_name_tv.setText(oneBeen.getNickname());
                one_amount_tv.setText(Utils.getString(R.string.奖金:****));
                setHeadLevelIv(oneBeen.getGrade(),one_level_iv);
            }else if(title.equals(DUSHEN)){
                one_name_tv.setText(oneBeen.getNickname());
                int zjNum = oneBeen.getZjNum();
                int tzNum = oneBeen.getTzNum();
                if(StringMyUtil.isEmptyString(zjNum)){
                    one_amount_tv.setText(Utils.getString(R.string.场次: - - - ));
                    one_win_rate_tv.setText(Utils.getString(R.string.胜率: - - - ));
                }else {
                    one_amount_tv.setText(Utils.getString(R.string.场次: )+ tzNum +Utils.getString(R.string.中)+ zjNum);//设置中奖场次
                    if(zjNum==0){
                        one_win_rate_tv.setText(Utils.getString(R.string.胜率： 0.00%));
                    }else {
                        one_win_rate_tv.setText(Utils.getString(R.string.胜率: )+new BigDecimal(Double.parseDouble(zjNum+"")/Double.parseDouble(tzNum+"")*100).setScale(2,BigDecimal.ROUND_HALF_DOWN)+"%");//设置中奖比例
                    }
                }
                setHeadLevelIv(oneBeen.getGrade(),one_level_iv);
            }else if(title.equals(YAOQING)){
                one_name_tv.setText(oneBeen.getNickname());
                one_amount_tv.setText(oneBeen.getTotalQuYueHBPrice().contains(Utils.getString(R.string.红包))?oneBeen.getTotalQuYueHBPrice():Utils.getString(R.string.红包: )+oneBeen.getTotalQuYueHBPrice());
                setHeadLevelIv(oneBeen.getGrade(),one_level_iv);
            }else if(title.equals(ZHUANXIANG)){
                one_name_tv.setText(oneBeen.getNickname());
                one_amount_tv.setText(oneBeen.getTotalZxHBPrice().contains(Utils.getString(R.string.红包))?oneBeen.getTotalZxHBPrice():Utils.getString(R.string.红包: )+oneBeen.getTotalZxHBPrice());
                setHeadLevelIv(oneBeen.getGrade(),one_level_iv);
            }
        }

    }




}
