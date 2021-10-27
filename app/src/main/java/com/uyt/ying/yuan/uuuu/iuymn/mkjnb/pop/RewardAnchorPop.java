package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.ForbiddenMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveRewardMessage;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.adapter.RewardAnchorAdapter;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.base.BasePopupWindow2;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.RewardAnchorEntity;
import com.uyt.ying.yuan.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Headers;

public class RewardAnchorPop extends BasePopupWindow2 {
    RecyclerView reward_anchor_recycler;
    RewardAnchorAdapter rewardAnchorAdapter;
    ArrayList<RewardAnchorEntity>rewardAnchorEntityArrayList = new ArrayList<>();
    LiveFragment liveFragment;

    public RewardAnchorPop(Context context, boolean focusable,LiveFragment liveFragment) {
        super(context, focusable);
        this.liveFragment =liveFragment;
        initRecycler();
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.reward_layout_pop,null);
    }

    private void initRecycler() {
        reward_anchor_recycler = rootView.findViewById(R.id.reward_anchor_recycler);
        rewardAnchorAdapter = new RewardAnchorAdapter(R.layout.reward_anchor_recycler_item,rewardAnchorEntityArrayList);
        reward_anchor_recycler.setLayoutManager(new GridLayoutManager(mContext,4));
        reward_anchor_recycler.setAdapter(rewardAnchorAdapter);
        rewardAnchorAdapter.addChildClickViewIds(R.id.reward_anchor_wrap_linear);
        rewardAnchorAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                RewardAnchorEntity rewardAnchorEntity = rewardAnchorEntityArrayList.get(position);
                for (int i = 0; i < rewardAnchorEntityArrayList.size(); i++) {
                    rewardAnchorEntityArrayList.get(i).setSelector(false);
                }
                rewardAnchorEntity.setSelector(true);
                rewardAnchorAdapter.notifyDataSetChanged();
                // 请求打赏接口
                requestRewardAnchor(rewardAnchorEntity);
            }
        });
        /**
         * a = 中奖金额 * 系统参数的百分比
         * b = 系统参数的最大值
         * double maxValue = min(a,b);
         * 在 0 ～ maxValue 中随机4个值，4个值不要一样
         */
        ;
        BigDecimal ratePrice = new BigDecimal(liveFragment.zjPrice).multiply(new BigDecimal(SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.ANCHOR_REWARD_RATE, ""))).divide(new BigDecimal("100"));
        BigDecimal maxPrice = new BigDecimal(SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.ANCHOR_REWARD_MAX_PRICE, ""));
        BigDecimal max = maxPrice.min(ratePrice);
        while(rewardAnchorEntityArrayList.size()<4){
            BigDecimal randomRedPacketBetweenMinAndMax = Utils.getRandomRewardPriceWeenMinAndMax(new BigDecimal("0"), max);
            RewardAnchorEntity rewardAnchorEntity = new RewardAnchorEntity(randomRedPacketBetweenMinAndMax + "");
            if(!rewardAnchorEntityArrayList.contains(rewardAnchorEntity)){
                rewardAnchorEntityArrayList.add(rewardAnchorEntity);
            }
        }
        rewardAnchorAdapter.notifyDataSetChanged();
    }

    /**
     * 打赏主播
     */
    private void requestRewardAnchor( RewardAnchorEntity rewardAnchorEntity ) {
        if(liveFragment == null || liveFragment.mLiveData == null){
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorMemberId",liveFragment.mLiveData.getAnchorMemberId());
        data.put("giftId",0);
        data.put("giftCount",1);
        data.put("remoteLiveManagementId",liveFragment.mLiveData.getRemoteLiveManagementId());
        data.put("anchorRewardPrice",rewardAnchorEntity.getPrice());
        HttpApiUtils.CpRequest(liveFragment.getActivity(), liveFragment, RequestUtil.SEND_GIFT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result, Headers headers) {
                ToastUtil.showToast(Utils.getString(R.string.打赏成功));
                /**
                 *发送打赏消息
                 */
                LiveRewardMessage liveRewardMessage = new LiveRewardMessage(RongLibUtils.setUserInfo(liveFragment.getContext(),liveFragment.managerTypeEntity),rewardAnchorEntity.getPrice());
                RongLibUtils.sendMessage(liveFragment.roomId,liveRewardMessage);
                RewardAnchorPop.this.dismiss();
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
}
