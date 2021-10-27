package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.ForbiddenMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.ForbiddenAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.BlockEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ForbiddenEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.StringMyUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.RefreshUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;


public class ForbiddenPop extends BasePopupWindow2 implements  OnItemChildClickListener {
    ConstraintLayout loading_linear;
    LinearLayout error_linear;
    TextView reload_tv;
    SmartRefreshLayout refresh;
    TextView forbidden_list_tv;
    TextView block_list_tv;
    TextView end_time_tip_tv;
    RecyclerView forbidden_recycler;
    ForbiddenAdapter forbiddenAdapter;
    ArrayList<Object> dataBeanArrayList = new ArrayList<>();
    LiveEntity.AnchorMemberRoomListBean mLiveData;
    LiveFragment liveFragment;
    boolean isForbiddenClick=true;

    public ForbiddenPop(Context context, boolean focusable, LiveEntity.AnchorMemberRoomListBean mLiveData, LiveFragment liveFragment) {
        super(context, focusable);
        this.mLiveData = mLiveData;
        this.liveFragment = liveFragment;
        setAnimationStyle(R.style.pop_bottom_to_top_150);
        setHeight((int) (0.5* Utils.intgetWinndowHeight((Activity) mContext)));
        initRecycler();
        initRefresh();
        forbidden_list_tv.performClick();
    }

    private void initRefresh() {
        RefreshUtil.initRefresh(new SoftReference<>(mContext), refresh, new RefreshUtil.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if(isForbiddenClick){
                    requestForbiddenList(true);
                }else {
                    requestBlockList(true);
                }
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        if(isForbiddenClick){
            requestForbiddenList(false);
        }else {
            requestBlockList(false);
        }
    }

    @Override
    public void initView() {
        super.initView();
        rootView= LayoutInflater.from(mContext).inflate(R.layout.forbidden_list_pop_layout,null);
        loading_linear = rootView.findViewById(R.id.loading_linear);
        error_linear = rootView.findViewById(R.id.error_linear);
        reload_tv = rootView.findViewById(R.id.reload_tv);
        end_time_tip_tv = rootView.findViewById(R.id.end_time_tip_tv);
        refresh = rootView.findViewById(R.id.refresh);
        forbidden_recycler = rootView.findViewById(R.id.forbidden_recycler);
        block_list_tv = rootView.findViewById(R.id.block_list_tv);
        forbidden_list_tv = rootView.findViewById(R.id.forbidden_list_tv);

        forbidden_list_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isForbiddenClick=true;
                forbidden_list_tv.setTextColor(Color.parseColor("#FF2F81"));
                block_list_tv.setTextColor(Color.WHITE);
                requestForbiddenList(false);
                end_time_tip_tv.setVisibility(View.VISIBLE);
            }
        });
        block_list_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isForbiddenClick=false;
                block_list_tv.setTextColor(Color.parseColor("#FF2F81"));
                forbidden_list_tv.setTextColor(Color.WHITE);
                requestBlockList(false);
                end_time_tip_tv.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void requestBlockList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        HttpApiUtils.cpPopShowLoadRequest((Activity) mContext, ForbiddenPop.this, RequestUtil.BLOCK_LIST, data, loading_linear, error_linear, reload_tv, refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                dataBeanArrayList.clear();
                BlockEntity blockEntity = JSONObject.parseObject(result, BlockEntity.class);
                List<BlockEntity.MemberKillMemberListBean> memberKillMemberList = blockEntity.getMemberKillMemberList();
                RefreshUtil.success(1,refresh,loading_linear,null,memberKillMemberList.size(),false,isRefresh,dataBeanArrayList);
                dataBeanArrayList.addAll(memberKillMemberList);
                forbiddenAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,false,1,refresh);
            }
        });
    }

    private void initRecycler() {
        forbiddenAdapter = new ForbiddenAdapter(R.layout.forbidden_list_recycler_item,dataBeanArrayList);
        forbidden_recycler.setLayoutManager(new LinearLayoutManager(mContext));
        forbidden_recycler.setAdapter(forbiddenAdapter);
        forbiddenAdapter.addChildClickViewIds(R.id.operating_tv);
        forbiddenAdapter.setOnItemChildClickListener(this);

    }

    private void requestForbiddenList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorAccount",mLiveData.getAnchorAccount());
        data.put("pageNo",1);
        data.put("pageSize",10000);
        HttpApiUtils.cpPopShowLoadRequest((Activity) mContext, this, RequestUtil.FORBIDDEM_LIST, data, loading_linear, error_linear, reload_tv, refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                dataBeanArrayList.clear();
                ForbiddenEntity forbiddenEntity = JSONObject.parseObject(result, ForbiddenEntity.class);
                List<ForbiddenEntity.ChatRoomGagListBean> chatRoomGagList = forbiddenEntity.getChatRoomGagList();
                RefreshUtil.success(1,refresh,loading_linear,null,chatRoomGagList.size(),false,isRefresh,dataBeanArrayList);
                dataBeanArrayList.addAll(chatRoomGagList);
                forbiddenAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String msg) {
                RefreshUtil.fail(isRefresh,false,1,refresh);
            }
        });
    }


    /**
     * 列表点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Object o = dataBeanArrayList.get(position);
        if(o instanceof  ForbiddenEntity.ChatRoomGagListBean){
            requestCancelForbidden(position, (ForbiddenEntity.ChatRoomGagListBean) o);
        }else {
            requestCancelBlock(position, (BlockEntity.MemberKillMemberListBean) o);
        }

    }

    private void requestCancelBlock(int position, BlockEntity.MemberKillMemberListBean o) {
        HashMap<String, Object> data = new HashMap<>();
        BlockEntity.MemberKillMemberListBean memberKillMemberListBean = o;
        if(StringMyUtil.isEmptyString(memberKillMemberListBean.getKilledAnchorMemberId())){
            //取消拉黑用户
            data.put("killedUserId",memberKillMemberListBean.getKilledUserId());
        }else {
            //取消拉黑主播
            data.put("killedAnchorMemberId",memberKillMemberListBean.getKilledAnchorMemberId());
            data.put("killedAnchorAccount",memberKillMemberListBean.getKilledAnchorAccount());
        }
        data.put("liveRoomId",liveFragment.roomId);
        data.put("maintainStatus","0");
        HttpApiUtils.CpRequest((Activity) mContext,liveFragment , RequestUtil.BLOCK_USER, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ToastUtil.showToast(Utils.getString(R.string.解封成功));
                dismiss();
                dataBeanArrayList.remove(position);
                forbiddenAdapter.notifyItemRemoved(position);
            }

            public void onFailed(String msg) {

            }
        });
    }

    private void requestCancelForbidden(int position, ForbiddenEntity.ChatRoomGagListBean o) {
        ForbiddenEntity.ChatRoomGagListBean chatRoomGagListBean = o;
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorAccount",mLiveData.getAnchorAccount());
        data.put("bannedUserId",chatRoomGagListBean.getUser_id());
        HttpApiUtils.CpRequest((Activity) mContext, liveFragment, RequestUtil.CANCEL_FORBIDDEM, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                String message = jsonObject.getString("message");
                ToastUtil.showToast(message);
                dataBeanArrayList.remove(position);
                forbiddenAdapter.notifyItemRemoved(position);
                ForbiddenMessage forbiddenMessage = new ForbiddenMessage("0", chatRoomGagListBean.getUserNickName(), SharePreferencesUtil.getString(mContext, "zkCode", "") );
                forbiddenMessage.setUserInfoJson(RongLibUtils.setUserInfo(mContext,liveFragment.managerTypeEntity));
                RongLibUtils.sendMessage(liveFragment.roomId,forbiddenMessage);
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }
}
