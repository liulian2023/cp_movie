package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.main_Tab_fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment.AllLotteryFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment.ShoppingContentChessFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.shopping_fragment.ShoppingContentRecommendFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RouteUtils;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.main_tab_activity.ChessSearchActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.GameReportActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.QuotaChangeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.RechargeActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.mine_top_activity.WantToWithdrawActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.safeCenter.SafeCenterActivity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.CommonAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.main_tab_adapter.ShoppingMeneAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BaseFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.MenuModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.UserInfoEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.LoginIntentUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.StatusBarUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Headers;


public class ShoppingTwoFragment extends BaseFragment implements CommonAdapter.OnRecyclerViewItemClickListener {
    private Unbinder unbinder;
    @BindView(R.id.menu_recycler)
    RecyclerView menu_recycler;
    @BindView(R.id.logo_iv)
    ImageView logo_iv;
    @BindView(R.id.back_iv)
    ImageView back_iv;
    @BindView(R.id.search_iv)
    ImageView search_iv;
    @BindView(R.id.shopping_wrap_relative)
    RelativeLayout shopping_wrap_relative;
    @BindView(R.id.user_amount_linear)
    LinearLayout user_amount_linear;
    @BindView(R.id.to_invest_linear)
    LinearLayout to_invest_linear;
    @BindView(R.id.to_with_draw_linear)
    LinearLayout to_with_draw_linear;
    @BindView(R.id.to_quota_change_linear)
    LinearLayout to_quota_change_linear;
    @BindView(R.id.to_deal_note_linear)
    LinearLayout to_deal_note_linear;
    @BindView(R.id.user_nick_name_tv)
    TextView user_nick_name_tv;
    @BindView(R.id.user_amount_tv)
    TextView user_amount_tv;
    @BindView(R.id.refresh_amount_iv)
    ImageView refresh_amount_iv;

    ShoppingMeneAdapter shoppingMeneAdapter;
    ArrayList<MenuModel.ChessGameListBean> menuModelArrayList = new ArrayList<>();

    private List<UserInfoEntity.MemberInfoBean.memberBankInfoVoListBean> memberBankInfoVoList;
    private FragmentManager childFragmentManager;
    boolean showBackBtn;
    boolean showActionBar;
    private String TAG ="ShoppingTwoFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_two, container, false);
        unbinder = ButterKnife.bind(this, view);
        String logoUrl = Utils.getHomeLogo("liveIcon4");
        if(StringMyUtil.isNotEmpty(logoUrl)){
            GlideLoadViewUtil.FLoadNormalView(this,logoUrl,logo_iv);
        }
        initToolbar();
        return view;
    }
    public static ShoppingTwoFragment newInstance(boolean showBackBtn,boolean showActionBar){
        ShoppingTwoFragment shoppingTwoFragment = new ShoppingTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("showBackBtn",showBackBtn);
        bundle.putBoolean("showActionBar",showActionBar);
        shoppingTwoFragment.setArguments(bundle);
        return shoppingTwoFragment;
    }

    private void initToolbar() {
        showBackBtn=getArguments().getBoolean("showBackBtn");
        showActionBar=getArguments().getBoolean("showActionBar");
        if(showBackBtn){
            back_iv.setVisibility(View.VISIBLE);
        }else {
            back_iv.setVisibility(View.GONE);
        }
        if(showActionBar){
            shopping_wrap_relative.setVisibility(View.VISIBLE);
        }else {
            shopping_wrap_relative.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecycler();
    }
    private void requestAppStatistics(int page){
        if(SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)!=0){
            HashMap<String, Object> data = new HashMap<>();

            data.put("page",page);//1 直播页面 2游戏页面 3 活动页面 4 会员中心 5 直播观看次数 6 直播观看时长 7 app安装统计
            data.put("installApps", Utils.getInstallApps(getContext()));
            HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.WATCH_MINUTES, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    Utils.logE(TAG, "onSuccess: 用户行为统计成功 page ="+page );
                }

                @Override
                public void onFailed(String msg) {
                    Utils.logE(TAG, "onSuccess: 用户行为统计失败 page ="+page+ msg );
                }
            });
        }
    }

    private void initRecycler() {
        childFragmentManager = getChildFragmentManager();
        shoppingMeneAdapter = new ShoppingMeneAdapter(menuModelArrayList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        menu_recycler.setLayoutManager(layoutManager);
        menu_recycler.setAdapter(shoppingMeneAdapter);

        requestLeftRecycle();

        shoppingMeneAdapter.setOnItemClickListener(this);
        //默认显示第一个item数据(为您推荐)
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        transaction.replace(R.id.shopping_content_frameLayout, ShoppingContentRecommendFragment.newInstance(menuModelArrayList.get(0)));
        transaction.commit();
    }

    private void updatePerson() {
        HashMap<String, Object> data = new HashMap<>();//REQUEST_06rzq
        long userId = SharePreferencesUtil.getLong(getContext(),"user_id",0L);
        data.put("user_id",userId);
        HttpApiUtils.CPnormalRequest(getActivity(), this, RequestUtil.REQUEST_13r, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                SharePreferencesUtil.putString(getContext(),"userInfo",result);//储存用户信息
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                String userNickName = memberInfo.getString("userNickName");
                SharePreferencesUtil.putString(getContext(),"userNickName",userNickName);
                user_nick_name_tv.setText(userNickName);
                if(userNickName.length()>7){
                    user_nick_name_tv.setTextSize(12);
                }else {
                    user_nick_name_tv.setTextSize(13);
                }
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                memberBankInfoVoList = userInfoEntity.getMemberInfo().getMemberBankInfoVoList();//银行卡列表
                String money = jsonObject.getString("money");//用户余额
                user_amount_tv.setText(money);

            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
    /**
     * 左侧菜单栏
     */
    private void requestLeftRecycle() {
        MenuModel.ChessGameListBean chessGameListBean = new MenuModel.ChessGameListBean();
        chessGameListBean.setGame(-1);
        chessGameListBean.setName2(Utils.getString(R.string.为您推荐));
        chessGameListBean.setSelector(true);
        menuModelArrayList.add(chessGameListBean);
        HashMap<String, Object> data = new HashMap<>();
        HttpApiUtils.CPnormalRequest(getActivity(),this, RequestUtil.CHESS_GAME_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                MenuModel menuModel = JSONObject.parseObject(result, MenuModel.class);
                List<MenuModel.ChessGameListBean> chessGameList = menuModel.getChessGameList();
                menuModelArrayList.addAll(chessGameList);
                shoppingMeneAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarUtil.setColor(getActivity(), Color.WHITE);
        StatusBarUtil.setDarkMode(getActivity());
        if(Utils.isNotFastClick()){
            requestAppStatistics(2);
        }
        if(LoginIntentUtil.isLogin(getContext())){
            updatePerson();
        }else {
            user_nick_name_tv.setText(Utils.getString(R.string.立即登录));
        }

    }


    @OnClick({R.id.search_iv,R.id.back_iv,R.id.user_amount_linear,R.id.to_invest_linear,R.id.to_with_draw_linear,R.id.to_quota_change_linear,R.id.to_deal_note_linear,R.id.user_nick_name_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.search_iv:
                ChessSearchActivity.startAty(getContext());
                break;
            case R.id.back_iv:
                getActivity().finish();
                break;
            case R.id.user_amount_linear:
            case R.id.user_nick_name_tv:
                if(LoginIntentUtil.isLogin(getContext())){
                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
                    refresh_amount_iv.startAnimation(animation);
                    updatePerson();
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }
                break;
            case R.id.to_invest_linear:
                if(LoginIntentUtil.isLogin(getContext())){
                    String isTest = SharePreferencesUtil.getString(MyApplication.getInstance(), "isTest", "");
                    if(isTest.equals("-1")){
                        Utils.initSkipVisitorSafeCenterPop(getActivity(),getActivity());
                    }else {
                        startActivity(new Intent(getContext(), RechargeActivity.class));//点击跳转我要充值
                    }
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }
                break;
            case R.id.to_deal_note_linear:
                if(LoginIntentUtil.isLogin(getActivity())){

                    startActivity(new Intent(getContext(), GameReportActivity.class));
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }
                break;
            case R.id.to_quota_change_linear:
                if(LoginIntentUtil.isLogin(getActivity())){

                    QuotaChangeActivity.startAty(getContext());
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }
                break;
            case R.id.to_with_draw_linear:
                if(LoginIntentUtil.isLogin(getContext())){
                    if(memberBankInfoVoList==null||memberBankInfoVoList.size()==0){
                        AlertDialog bindPhone = new AlertDialog.Builder(getContext()).create();
                        bindPhone.setTitle(Utils.getString(R.string.温馨提示));
                        bindPhone.setMessage(Utils.getString(R.string.您的账号信息尚未完善请前往完善));
                        bindPhone.setButton(DialogInterface.BUTTON_NEGATIVE, Utils.getString(R.string.取消), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        bindPhone.setButton(DialogInterface.BUTTON_POSITIVE, Utils.getString(R.string.确定), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.passwordIsEmpty()){
                                    RouteUtils.skipVisitorSafeCenter(getContext());
                                }else {

                                    startActivity(new Intent(getContext(), SafeCenterActivity.class));
                                }
                            }
                        });
                        bindPhone.show();
                    }else{

                        startActivity(new Intent(getContext(), WantToWithdrawActivity.class));//点击跳转我要提现
                    }
                }else {
                    LoginIntentUtil.toLogin(getActivity());
                }

                break;
            default:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    /**
     * 左侧分类列表点击事件
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        MenuModel.ChessGameListBean chessGameListBean = menuModelArrayList.get(position);
        int game = chessGameListBean.getGame();
        if(game==-1) {
            //为您推荐
            ShoppingContentRecommendFragment shoppingContentRecommendFragment = ShoppingContentRecommendFragment.newInstance(chessGameListBean);
            transaction.replace(R.id.shopping_content_frameLayout, shoppingContentRecommendFragment);
        }else if(game<50){
            //彩票游戏
            transaction.replace(R.id.shopping_content_frameLayout, new AllLotteryFragment());
        }else {
            //棋牌游戏
            transaction.replace(R.id.shopping_content_frameLayout, ShoppingContentChessFragment.newInstance(chessGameListBean));
        }
        transaction.commit();
    }
}
