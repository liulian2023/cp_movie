package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.RongLibUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.Fragment.live_chat_fragment.rongyun.message.LiveFollowMessage;
import com.uyt.ying.yuan.R;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.activity.live.LiveFragment;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.eventBusModel.FollowEvenModel;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model.ManagerTypeEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.api.HttpApiUtils;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.net.entity.LiveEntity;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.request.RequestUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.GlideLoadViewUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.MyApplication;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ProgressDialogUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.SharePreferencesUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.ToastUtil;
import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.Utils;
import org.greenrobot.eventbus.EventBus;
import java.util.HashMap;
import okhttp3.Headers;

public class AutoFollowPop extends PopupWindow implements View.OnClickListener {
    private Button follow_btn;
    private ImageView title_iv;
    private TextView nick_name_tv;

    Fragment fragment;
    String liveId;//主播id
    String account;//主播账号
    Context context;
    View targetView;
    LiveEntity.AnchorMemberRoomListBean mLiveData;
    ManagerTypeEntity managerTypeEntity;
    public AutoFollowPop(Context context, LiveFragment fragment, LiveEntity.AnchorMemberRoomListBean mLiveData, View targetView) {
        super(context);
        this.context = context;
        this.fragment = fragment;
        liveId = mLiveData.getAnchorMemberId()+"";
        account = mLiveData.getAnchorAccount();
        this.mLiveData = mLiveData;
        this.targetView = targetView;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =inflater.inflate(R.layout.auto_follow_pop_layout, null);
        follow_btn = v.findViewById(R.id.follow_btn);
        title_iv = v.findViewById(R.id.title_iv);
        nick_name_tv = v.findViewById(R.id.nick_name_tv);
        if(mLiveData.getIsPrivate()==1){
            GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkLiveImageUrl(mLiveData.getHeadImg()), title_iv);
        }else {
            GlideLoadViewUtil.FLoadTitleView(fragment,Utils.checkImageUrl(mLiveData.getHeadImg()), title_iv);
        }
        nick_name_tv.setText(mLiveData.getAnchorNickName());
        //点击收藏
        follow_btn.setOnClickListener(this);
        initPop(v);
    }



    private void initPop(View v) {
        this.setContentView(v);
        this.setAnimationStyle(R.style.pop_bottom_to_top_150);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(fragment.getActivity(),1f);
            }
        });
        this.showAtLocation(targetView, Gravity.BOTTOM,0,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.follow_btn:
                if(Utils.isNotFastClick()){
                    requestCollect();
                }
                break;
            default:
                break;
        }
    }

    private void requestCollect() {
            HashMap<String, Object> data = new HashMap<>();
            data.put("anchorMemberId",liveId);
            HttpApiUtils.CpRequest(fragment.getActivity(), fragment,RequestUtil.FOLLOW, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result, Headers headers) {
                    ToastUtil.showToast(Utils.getString(R.string.关注成功));
                    String zkCode = SharePreferencesUtil.getString(MyApplication.getInstance(), "zkCode", "");
                    if(fragment instanceof LiveFragment){
                        //关注成功发送消息
                         LiveFragment liveFragment = (LiveFragment) fragment;
                        LiveFollowMessage liveFollowMessage = new LiveFollowMessage(zkCode,RongLibUtils.setUserInfo(MyApplication.getInstance(),liveFragment.managerTypeEntity));
                        RongLibUtils.sendMessage(liveFragment.roomId,liveFollowMessage);
                    }
                    EventBus.getDefault().postSticky(new FollowEvenModel(liveId+"",true));
                    AutoFollowPop.this.dismiss();
                }
                @Override
                public void onFailed(String msg) {
                }
            });
    }
}
